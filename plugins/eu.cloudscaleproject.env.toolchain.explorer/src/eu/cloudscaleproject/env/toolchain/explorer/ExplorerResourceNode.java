package eu.cloudscaleproject.env.toolchain.explorer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.e4.core.contexts.IEclipseContext;

import eu.cloudscaleproject.env.common.interfaces.IProjectProvider;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerResourceNode extends ExplorerNode implements IProjectProvider{

	private IResource resource;
		
	public ExplorerResourceNode(IEclipseContext context, String id, IExplorerNodeChildren childFactory) {
		super(context, id, childFactory);
	}
	
	public void setResource(IResource resource){
		this.resource = resource;
		if(resource != null){
			this.getContext().set(IResource.class, resource);
			this.getContext().set(IExplorerConstants.NODE_RESOURCE, resource);
		}
		else{
			this.getContext().remove(IResource.class);
			this.getContext().remove(IExplorerConstants.NODE_RESOURCE);
		}
	}
	
	public IResource getResource(){
		return resource;
	}
	
	public IProject getProject(){
		if(resource != null){
			return resource.getProject();
		}
		
		return null;
	}
	
	public boolean canDelete(){
		return true;
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public Object getAdapter(Class adapter) {
				
		if(adapter.equals(IResource.class)){
			return getResource();
		}
		
		return super.getAdapter(adapter);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((resource == null) ? 0 : resource.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {return true;}
		if (obj == null) {return false;}
		
		if (getClass() != obj.getClass()){
			return false;
		}
		
		ExplorerResourceNode other = (ExplorerResourceNode) obj;
		if (resource == null) {
			if (other.resource != null){
				return false;
			}
		} 
		else if (!resource.equals(other.resource)){
			return false;
		}
		
		return true;
	}

}
