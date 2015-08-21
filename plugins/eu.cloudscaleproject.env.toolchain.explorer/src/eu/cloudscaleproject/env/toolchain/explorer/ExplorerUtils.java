package eu.cloudscaleproject.env.toolchain.explorer;

import java.nio.channels.IllegalSelectorException;
import java.util.HashSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MStackElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerUtils {
	
	public static HashSet<MPart> getOpenedEditors(){
		
		EModelService modelService = CloudscaleContext.getGlobalContext().get(EModelService.class);
		MApplication app = CloudscaleContext.getGlobalContext().get(MApplication.class);

		if(modelService == null){
			throw new IllegalSelectorException();
		}
		if(app == null){
			throw new IllegalSelectorException();
		}
		
		HashSet<MPart> out = new HashSet<MPart>();
		MPartStack stack = (MPartStack)modelService.find("org.eclipse.e4.primaryDataStack", app);
		
		for(MStackElement el : stack.getChildren()){
			if(el instanceof MPart){
				MPart part = (MPart)el;
				if(part.getContext() != null){
					out.add(part);
				}
			}
		}
		return out;
	}
	
	public static IProject getProject(IExplorerNode node){
		while(node != null){
			if(node instanceof ExplorerResourceNode){
				ExplorerResourceNode resNode = (ExplorerResourceNode)node;
				if(resNode.getResource() instanceof IProject){
					return (IProject)resNode.getResource();
				}
			}
			node = node.getParent();
		}
		return null;
	}
	
	public static ExplorerResourceNode getProjectNode(IExplorerNode node){
		while(node != null){
			if(node instanceof ExplorerResourceNode){
				ExplorerResourceNode resNode = (ExplorerResourceNode)node;
				if(resNode.getResource() instanceof IProject){
					return (ExplorerResourceNode)node;
				}
			}
			node = node.getParent();
		}
		return null;
	}
	
	public static ExplorerResourceNode getResourceProviderNode(IExplorerNode node){
		while(node != null){
			ResourceProvider rp = (ResourceProvider)node.getAdapter(ResourceProvider.class);
			if(rp != null){
				return (ExplorerResourceNode)node;
			}
			node = node.getParent();
		}
		return null;
	}

}
