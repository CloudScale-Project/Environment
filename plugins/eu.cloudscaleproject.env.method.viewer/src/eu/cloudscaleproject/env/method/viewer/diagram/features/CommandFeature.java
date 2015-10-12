package eu.cloudscaleproject.env.method.viewer.diagram.features;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.eclipse.core.internal.databinding.ClassLookupSupport;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
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
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.method.common.method.Node;
import eu.cloudscaleproject.env.method.common.method.Requirement;
import eu.cloudscaleproject.env.method.common.method.Section;
import eu.cloudscaleproject.env.method.common.method.StatusNode;
import eu.cloudscaleproject.env.method.viewer.ValidationDiagram;
import eu.cloudscaleproject.env.method.viewer.ValidationDiagramService;


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
	
	private String findSectionID(Node node){
		//find selected section ID
		EObject tmp = node;
		while (tmp != null && !(tmp instanceof Section)) {
			tmp = tmp.eContainer();
		}
		if(tmp instanceof Section){
			return ((Section)tmp).getId();
		}
		
		return null;
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(final ICustomContext context) {

		final Object bo = findBusinessObject(context);
		final Node node = (Node)bo;
		
		//open resource file
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
		
		//fill all informations in this static context
		IEclipseContext staticContext = EclipseContextFactory.create();
		
		ValidationDiagramService diagramService = CloudscaleContext.getActiveContext().get(ValidationDiagramService.class);
		if(diagramService == null){
			logger.severe("Can not execute command:'" + node.getCommandId() + "'. Validation diagram service does not exist!");
			return;
		}
		
		diagramService.setSelectedNodeID(node.getId());
		diagramService.setSelectedSectionID(findSectionID(node));
		
		ValidationDiagram diagram = diagramService.getActiveDiagram();
		IValidationStatusProvider statusProvider = diagramService.getActiveStatusProvider(node.getId());
		IValidationStatus status = diagramService.getActiveStatus(node.getId());
				
		if(diagram != null){
			staticContext.set(IProject.class, diagram.getProject());
		}
		if(statusProvider != null){
			for(Class clazz : ClassLookupSupport.getTypeHierarchyFlattened(statusProvider.getClass())){
				if(clazz.isInterface()){
					staticContext.set(clazz, statusProvider);
				}
			}
		}
		if(status != null){
			for(Class clazz : ClassLookupSupport.getTypeHierarchyFlattened(status.getClass())){
				if(clazz.isInterface()){
					staticContext.set(clazz, status);
				}
			}
		}
		
		if (node.getCommandParam().isEmpty()) {
			executor.execute(node.getCommandId(), staticContext);
		} else {
			executor.execute(node.getCommandId(), staticContext, node.getCommandParam()
					.toArray(new String[node.getCommandParam().size()]));
		}
		
		staticContext.dispose();
	}
}
