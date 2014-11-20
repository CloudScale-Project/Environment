package eu.cloudscaleproject.env.method.viewer.diagram.features;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.eclipse.core.resources.IFile;
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
import eu.cloudscaleproject.env.method.common.method.Node;
import eu.cloudscaleproject.env.method.common.method.Requirement;
import eu.cloudscaleproject.env.method.common.method.StatusNode;
import eu.cloudscaleproject.env.method.viewer.StatusServiceImpl;


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
			StatusNode statusNode = StatusServiceImpl.getStatusNode((StatusNode)bo);
			
			if (statusNode instanceof Requirement){
				Requirement req = (Requirement)statusNode;
				if(req.getResource() != null && req.getResource() instanceof IFile){
					try {
						IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), (IFile)req.getResource());
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

		if (node.getCommandParam().isEmpty()) {
			executor.execute(node.getCommandId());
		} else {
			executor.execute(node.getCommandId(), node.getCommandParam()
					.toArray(new String[node.getCommandParam().size()]));
		}
		
	}
}
