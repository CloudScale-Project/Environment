package eu.cloudscaleproject.env.method.viewer.diagram.features;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.MethodStatusContext;
import eu.cloudscaleproject.env.method.common.method.Node;
import eu.cloudscaleproject.env.method.common.method.Requirement;
import eu.cloudscaleproject.env.method.common.method.StatusNode;


public class CommandFeature extends AbstractCustomFeature{
	
	private static final Logger logger = Logger.getLogger(CommandFeature.class.getName());
	
	@Optional @Inject
	private CommandExecutor executor;

	public CommandFeature(IFeatureProvider fp) {
		super(fp);
		CloudscaleContext.inject(this);
	}
	
	@Override
	public String getName() {
		return "Run...";
	}
	
	private Object findBusinessObject(ICustomContext context){
		EObject el = context.getInnerPictogramElement();
		if(el == null){
			el = context.getPictogramElements()[0];
		}
						
		while(el != null){
			if(el instanceof PictogramElement){
				Object object = getBusinessObjectForPictogramElement((PictogramElement)el);
				if(object != null){
					return object;
				}
			}
			el = el.eContainer();
		}
		
		return null;
	}
	
	@Override
	public boolean hasDoneChanges() {
		return false;
	}
	
	@Override
	public boolean canExecute(ICustomContext context) {
		
		Object bo = findBusinessObject(context);
		
		if(bo instanceof Requirement){
			return true;
		}
		else if(bo instanceof Node){
			Node node = (Node)bo;
			if(node.getCommandId() != null && !node.getCommandId().isEmpty()){
				return true;
			}
		}
	
		return false;
	}

	@Override
	public void execute(final ICustomContext context) {

		final Object bo = findBusinessObject(context);
		final Node node = (Node)bo;
		
		if(bo instanceof StatusNode){
			StatusNode statusNode = (StatusNode)bo;
			
			if (statusNode instanceof Requirement){
				Requirement req = (Requirement)statusNode;
				if(req.getResource() != null && req.getResource() instanceof IFile){
					try {
						
						IFile file = (IFile)req.getResource();
						
						//try to find and open diagram file if it exists
						String diagramFilename = file.getName() + "_diagram";
						IFile diagramFile = null;
						
						int i=0;
						IContainer parent = file.getParent();
						while(parent != null){
							diagramFile = parent.getFile(new Path(diagramFilename));
							if(diagramFile.exists()){
								break;
							}
							parent = parent.getParent();
							i++;
							if(i >= 2){break;}
						}
											
						if(diagramFile != null && diagramFile.exists()){
							file = diagramFile;
						}
						//
						
						IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), file);
						return;
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
			
		if(node.getCommandId() == null || node.getCommandId().isEmpty()){
			logger.warning("execute(): Command not specified. Node ID: " + node.getId());
			return;
		}
		
		if (executor == null) {
			logger.warning("execute(): Executor service can not be found. "
					+ "Command will not be executed! Node ID: " + node.getId());
			return;
		}
		
		IValidationStatus validationStatus = null;
		
		EObject eobject = node;
		while(eobject != null && validationStatus == null){
			if(eobject instanceof Node){
				Node n = (Node)eobject;
				Object source = n.getSource();
				//if (source == null) 
				if(source instanceof IValidationStatus){
					validationStatus = (IValidationStatus) source;
				}
			}
			eobject = eobject.eContainer();
		}
		
		String validationId = null;
		eobject = node;
		while(eobject != null){
			if(eobject instanceof StatusNode){
				StatusNode n = (StatusNode)eobject;
				validationId = n.getId();
				break;
			}
			eobject = eobject.eContainer();
		}
		
		
		MethodStatusContext validationContext = new MethodStatusContext(validationId, validationStatus);
		CloudscaleContext.getActiveContext().set(MethodStatusContext.class, validationContext);

		if (node.getCommandParam().isEmpty()) {
			executor.execute(node.getCommandId());
		} else {
			executor.execute(node.getCommandId(), node.getCommandParam()
					.toArray(new String[node.getCommandParam().size()]));
		}
		
	}
}
