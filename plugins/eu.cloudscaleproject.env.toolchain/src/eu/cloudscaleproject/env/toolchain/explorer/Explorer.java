package eu.cloudscaleproject.env.toolchain.explorer;

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.toolchain.explorer.factory.ProjectNodeFactory;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class Explorer {
	
	public static Explorer instance;
	public static Explorer getInstance(){
		
		if(instance == null){
			instance = new Explorer();
		}
		return instance;
	}
	
	private final IEclipseContext context;
	private final IExplorerNode rootNode;
	
	public Explorer() {
		context = EclipseContextFactory.create("Explorer context");
		context.setParent(CloudscaleContext.getCustomContext());
		
		rootNode = new ExplorerNode("eu.cloudscaleproject.env.toolchain.explorer.rootNode", "Root node", new ProjectNodeFactory());		
	}
	
	public IExplorerNode getRoot(){
		return rootNode;
	}
	
	public IEclipseContext getContext(){
		return context;
	}
}
