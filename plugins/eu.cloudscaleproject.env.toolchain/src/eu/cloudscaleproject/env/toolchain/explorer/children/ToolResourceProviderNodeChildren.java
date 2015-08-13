package eu.cloudscaleproject.env.toolchain.explorer.children;

import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.Image;

import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.Activator;
import eu.cloudscaleproject.env.toolchain.Extensions;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResourceNode;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerUtils;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputJob;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ToolResourceProviderNodeChildren extends ExplorerNodeChildren{

	private final IProject project;
	private final String toolID;
	
	public ToolResourceProviderNodeChildren(IProject project, String toolID, boolean lazy) {
		super(lazy);
		this.project = project;
		this.toolID = toolID;
	}
	
	@Override
	public List<? extends Object> getKeys() {
		return Extensions.getInstance().getResourceProviderFactoryElements(toolID);	
	}

	@Override
	public IExplorerNode getChild(Object key) {
		
		IConfigurationElement element = (IConfigurationElement)key;
		
		String id = element.getAttribute("id");
		String name = element.getAttribute("name");
		String editor = element.getAttribute("editor");

		Image icon = ExplorerUtils.createImage(element, "icon");
		
		IFolder folder = ToolchainUtils.getToolFolder(project, id);
		final ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(id, folder);
		
		EditorInputJob job = new EditorInputJob("Initializing alternatives") {
			
			@Override
			public IStatus execute(IProgressMonitor monitor) {
				
				monitor.beginTask("Initializing '"+ rp.getRootFolder().getName() +"'", IProgressMonitor.UNKNOWN);
				
				for(IEditorInputResource eir : rp.getResources()){
					
					eir.load(monitor);
					eir.validate(monitor);
					
					if(eir instanceof IValidationStatusProvider){
						IValidationStatusProvider vp = (IValidationStatusProvider)eir;						
						ValidationDiagramService.showStatus(project, vp);
						break;
					}
				}
				
				monitor.done();
				
				return new Status(IStatus.OK, Activator.PLUGIN_ID, "Initializing resource provider done");
			}
		};
		job.setUser(false);
		job.schedule();
		
		ToolResourceNodeChildren children = new ToolResourceNodeChildren(editor, rp, true);
		ExplorerResourceNode node = new ExplorerResourceNode(id, rp.getRootFolder(), children);
		node.setName(name);
		node.setIcon(icon, true);
		node.setData(rp);
				
		return node;	
	}

}
