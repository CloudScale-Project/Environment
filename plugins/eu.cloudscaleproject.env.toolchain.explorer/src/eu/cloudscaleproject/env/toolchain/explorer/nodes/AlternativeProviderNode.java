package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.contexts.IEclipseContext;

import eu.cloudscaleproject.env.toolchain.Activator;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResourceNode;
import eu.cloudscaleproject.env.toolchain.explorer.children.AlternativeProviderNodeChildren;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputJob;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class AlternativeProviderNode extends ExplorerResourceNode{

	public AlternativeProviderNode(IEclipseContext context, 
								   String providerID, 
								   String editorID, final ResourceProvider resourceProvider) {
		
		super(context, providerID, resourceProvider.getRootFolder(), 
						  new AlternativeProviderNodeChildren(editorID, resourceProvider, true));
		
		getContext().set(ResourceProvider.class, resourceProvider);
		
		EditorInputJob job = new EditorInputJob("Initializing alternatives") {
			
			@Override
			public IStatus execute(IProgressMonitor monitor) {
				
				monitor.beginTask("Initializing '"+ resourceProvider.getRootFolder().getName() +"'", IProgressMonitor.UNKNOWN);
				
				for(IEditorInputResource eir : resourceProvider.getResources()){
					if(!eir.isLoaded()){
						eir.load(monitor);
					}
				}
				
				monitor.done();
				
				return new Status(IStatus.OK, Activator.PLUGIN_ID, "Initializing resource provider done");
			}
		};
		job.setUser(false);
		job.schedule();
	}
	
	@Override
	public boolean canDelete() {
		return false;
	}

}
