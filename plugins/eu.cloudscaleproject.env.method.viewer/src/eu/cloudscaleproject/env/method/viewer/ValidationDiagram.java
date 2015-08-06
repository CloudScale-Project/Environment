package eu.cloudscaleproject.env.method.viewer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusListener;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.ResourceValidationStatus;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.notification.diagram.IValidationDiagram;
import eu.cloudscaleproject.env.method.common.method.MethodFactory;
import eu.cloudscaleproject.env.method.common.method.Requirement;
import eu.cloudscaleproject.env.method.common.method.Section;
import eu.cloudscaleproject.env.method.common.method.StatusNode;
import eu.cloudscaleproject.env.method.common.method.Warning;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ValidationDiagram implements IValidationDiagram{
	
	public static final String PROP_STATUS_CHANGED = "eu.cloudscaleproject.env.method.viewer.ValidationDiagram.statusChanged";
	
	private final Resource resource;
	
	private IProject project;
	private TransactionalEditingDomain editingDomain = null;
		
	private HashMap<String, StatusNode> nodes = new HashMap<String, StatusNode>();
	
	private HashMap<String, IValidationStatusProvider> providerBindings = new HashMap<String, IValidationStatusProvider>();
	private HashMap<String, IValidationStatus> statusBindings = new HashMap<String, IValidationStatus>();
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	PropertyChangeListener statusManagerListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent pce) {
			if(StatusManager.PROP_STATUS_PROVIDER_REMOVED.equals(pce.getPropertyName())){
				IValidationStatusProvider statusProvider = (IValidationStatusProvider)pce.getOldValue();
				
				synchronized (ValidationDiagram.this) {
					IValidationStatusProvider bindedProvider = providerBindings.get(statusProvider.getID());
					if(statusProvider.equals(bindedProvider)){
						unbindStatusProvider(statusProvider.getID());
					}
				}
				
			}
		}
	};
	
	PropertyChangeListener statusProviderListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			
			if(IValidationStatusProvider.PROP_STATUS_ADDED.equals(evt.getPropertyName())){
				IValidationStatusProvider statusProvider = (IValidationStatusProvider)evt.getSource();
				
				synchronized (ValidationDiagram.this) {
					IValidationStatusProvider bindedProvider = providerBindings.get(statusProvider.getID());
					IValidationStatus status = (IValidationStatus)evt.getNewValue();
					if(statusProvider.equals(bindedProvider)){
						bindStatus(status);
					}
				}
				
			}
			if(IValidationStatusProvider.PROP_STATUS_REMOVED.equals(evt.getPropertyName())){
				IValidationStatusProvider statusProvider = (IValidationStatusProvider)evt.getSource();
				
				synchronized (ValidationDiagram.this) {
					IValidationStatusProvider bindedProvider = providerBindings.get(statusProvider.getID());
					IValidationStatus status = (IValidationStatus)evt.getOldValue();
					if(statusProvider.equals(bindedProvider)){
						unbindStatus(status);
					}
				}
			}
			refresh();
		}
	};
	
	IValidationStatusListener statuslistener = new IValidationStatusListener() {
		@Override
		public void propertyChange(final PropertyChangeEvent pce) {
			
			TransactionalEditingDomain ed = editingDomain;
			if(ed == null){
				return;
			}
			
			final CommandStack commandStack = ed.getCommandStack();
			
			commandStack.execute(new RecordingCommand((TransactionalEditingDomain) ed){
				
				protected void doExecute(){
					
					IValidationStatus status = (IValidationStatus)pce.getSource();
					StatusNode statusNode = nodes.get(status.getID());
					
					if(statusNode == null){
						return;
					}
					
					synchronized (statusNode) {
						if(IValidationStatus.PROP_WARNING_ADD.equals(pce.getPropertyName())){
							String id = (String)pce.getNewValue();
							Warning w = MethodFactory.eINSTANCE.createWarning();
							w.setId(id);
							w.setSeverity(status.getWarningType(id));
							w.setMessage(status.getWarningMessage(id));
							statusNode.getWarnings().add(w);
						}
						if(IValidationStatus.PROP_WARNING_REMOVE.equals(pce.getPropertyName())){
							Warning warning = null;
							for(Warning w : statusNode.getWarnings()){
								if(pce.getOldValue().equals(w.getId())){
									warning = w;
									break;
								}
							}
							if(warning != null){
								statusNode.getWarnings().remove(warning);
							}
						}
						if(IValidationStatus.PROP_DIRTY.equals(pce.getPropertyName())){
							statusNode.setDirty((boolean)pce.getNewValue());
						}
						if(IValidationStatus.PROP_NAME.equals(pce.getPropertyName())){
							statusNode.setInstanceName((String)pce.getNewValue());
						}
						
						statusNode.setDone(status.isDone());
					}
					
					refresh();
				}
				
			});
		}
	};
	
	public ValidationDiagram(Resource resource){
		this.resource = resource;
		StatusManager.getInstance().addPropertyChangeListener(statusManagerListener);
	}
	
	public void initialize(IDiagramTypeProvider diagramTypeProvider){
				
		TreeIterator<EObject> iter = EcoreUtil.getAllContents(diagramTypeProvider.getDiagram(), true);
		while(iter.hasNext()){
			EObject eobject = iter.next();
			Object bo = null;
			
			if(eobject instanceof PictogramElement){
				bo = diagramTypeProvider.getFeatureProvider().getBusinessObjectForPictogramElement((PictogramElement)eobject);
			}
			
			if(bo instanceof StatusNode){
				StatusNode node = (StatusNode)bo;
				nodes.put(node.getId(), node);
			}
		}
		
		this.editingDomain = diagramTypeProvider.getDiagramBehavior().getEditingDomain(); 
		
		
		for(IValidationStatus status : new ArrayList<IValidationStatus>(statusBindings.values())){
			bindStatus(status);
		}
	}
	
	@Override
	public void show() {
	}

	public Resource getResource(){
		return this.resource;
	}
	
	@Override
	public IProject getProject() {
		return this.project;
	}

	@Override
	public void setProject(IProject project) {
		this.project = project;
	}
	
	public void refresh(){
		pcs.firePropertyChange(PROP_STATUS_CHANGED, null, this);
	}
	
	public void dispose(){
		StatusManager.getInstance().removePropertyChangeListener(statusManagerListener);

		for(IValidationStatusProvider statusProvider : providerBindings.values()){
			statusProvider.removeStatusChangeListener(statusProviderListener);
		}
		for(IValidationStatus status : statusBindings.values()){
			status.removeListener(statuslistener);
		}
		providerBindings.clear();
		statusBindings.clear();
		nodes.clear();
		
	}
	
	public synchronized void bindStatusProvider(String id, IValidationStatusProvider statusProvider){
		
		unbindStatusProvider(id);
		providerBindings.remove(id);
		
		if(statusProvider == null){
			refresh();
			return;
		}
		
		bindStatus(statusProvider.getSelfStatus());
		for(IValidationStatus status : statusProvider.getSubStatuses()){
			bindStatus(status);
		}
		
		statusProvider.addStatusChangeListener(statusProviderListener);
		providerBindings.put(statusProvider.getID(), statusProvider);
		
		refresh();
	}
	
	public synchronized void unbindStatusProvider(String id){
		
		IValidationStatusProvider statusProvider = providerBindings.get(id);
		if(statusProvider == null){
			return;
		}
		
		unbindStatus(statusProvider.getSelfStatus());
		for(IValidationStatus status : statusProvider.getSubStatuses()){
			unbindStatus(status);
		}
		
		statusProvider.removeStatusChangeListener(statusProviderListener);
		providerBindings.remove(statusProvider.getID());
		
		refresh();
	}
	
	private String getStatusDiagramUniqueID(IValidationStatus status){
		String providerID = status.getProvider() != null ? status.getProvider().getID() : "";
		return providerID + status.getID();
	}
	
	public synchronized void bindStatus(IValidationStatus status){
		
		if(status == null){
			return;
		}
		
		IValidationStatus old = statusBindings.get(getStatusDiagramUniqueID(status));
		if(old != null){
			unbindStatus(old);
		}
		
		status.addListener(statuslistener);
		StatusNode node = nodes.get(status.getID());
		
		if(node != null){
			bind(node, status);
		}
		statusBindings.put(getStatusDiagramUniqueID(status), status);
		
		refresh();
	}
	
	public synchronized void unbindStatus(IValidationStatus status){
		
		if(status == null){
			return;
		}
		
		status.removeListener(statuslistener);
		
		StatusNode node = nodes.get(status.getID());
		if(node != null){
			bind(node, null);
		}
		statusBindings.remove(getStatusDiagramUniqueID(status));
		
		refresh();
	}
	
	public synchronized IValidationStatusProvider getActiveStatusProvider(String id){
		return providerBindings.get(id);
	}
	
	private void bind(final StatusNode statusNode, final IValidationStatus status){
		
		TransactionalEditingDomain ed = editingDomain;
		if(ed == null){
			return;
		}
		
		assert(statusNode != null);
		
		if(status == null){
			//clear status
			final CommandStack commandStack = ed.getCommandStack();
			
			commandStack.execute(new RecordingCommand((TransactionalEditingDomain) ed) {
				
				protected void doExecute() {
					
					synchronized (statusNode) {
						statusNode.setInstanceName("");
						statusNode.setSource(null);
						statusNode.setDirty(false);
						statusNode.setDone(false);
						statusNode.getWarnings().clear();
						
						if(statusNode instanceof Section){
							Section section = (Section)statusNode;
							section.setInProgress(false);
						}
						
						if(statusNode instanceof Requirement){
							Requirement req = (Requirement)statusNode;
							req.setResource(null);
						}
					}
				}
			});
			return;
		}
		
		final CommandStack commandStack = ed.getCommandStack();
		commandStack.execute(new RecordingCommand((TransactionalEditingDomain) ed) {
			
			protected void doExecute() {
				
				synchronized (statusNode) {
					statusNode.setInstanceName(status.getName());
					statusNode.setSource(status);
					statusNode.setDirty(status.isDirty());
					statusNode.setDone(status.isDone());
					if(statusNode instanceof Section){
						Section section = (Section)statusNode;
						section.setInProgress(true);
					}
					setWarnings(statusNode, status);
					
					if(statusNode instanceof Requirement){
						if(status instanceof ResourceValidationStatus){
							Requirement req = (Requirement)statusNode;
							req.setResource(((ResourceValidationStatus) status).getResource());
						}
					}
				}
			}
		});		
	}
	
	private void setWarnings(StatusNode node, IValidationStatus status){
		synchronized (node) {
			node.getWarnings().clear();
			for(String id : status.getWarningIDs()){
				Warning w = MethodFactory.eINSTANCE.createWarning();
				w.setId(id);
				w.setSeverity(status.getWarningType(id));
				w.setMessage(status.getWarningMessage(id));
				node.getWarnings().add(w);
			}
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener listener){
		this.pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		this.pcs.removePropertyChangeListener(listener);
	}
}
