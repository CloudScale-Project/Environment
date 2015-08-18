package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
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

	public AlternativeProviderNode(String providerID, String editorID, final ResourceProvider resourceProvider) {
		super(providerID, resourceProvider.getRootFolder(), 
						  new AlternativeProviderNodeChildren(editorID, resourceProvider, true));
		
		setData(resourceProvider);
		
		EditorInputJob job = new EditorInputJob("Initializing alternatives") {
			
			@Override
			public IStatus execute(IProgressMonitor monitor) {
				
				monitor.beginTask("Initializing '"+ resourceProvider.getRootFolder().getName() +"'", IProgressMonitor.UNKNOWN);
				
				for(IEditorInputResource eir : resourceProvider.getResources()){
					
					eir.load(monitor);
					eir.validate(monitor);
					
					if(eir instanceof IValidationStatusProvider){
						IValidationStatusProvider vp = (IValidationStatusProvider)eir;						
						ValidationDiagramService.showStatus(resourceProvider.getProject(), vp);
						//break;
					}
				}
				
				monitor.done();
				
				return new Status(IStatus.OK, Activator.PLUGIN_ID, "Initializing resource provider done");
			}
		};
		job.setUser(false);
		job.schedule();
	}

}
