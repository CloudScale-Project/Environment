package eu.cloudscaleproject.env.toolchain.explorer;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerResourceNode extends ExplorerNode{

	public final IResource resource;
		
	public ExplorerResourceNode(String id, IResource resource, IExplorerNodeChildren childFactory) {
		super(id, childFactory);
		
		if(resource instanceof IFolder){
			try {
				ExplorerProjectPaths.prepareFolder((IFolder)resource);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		this.resource = resource;
		this.getContext().set(IExplorerConstants.NODE_RESOURCE, resource);
	}
	
	public IResource getResource(){
		Object resource = this.getContext().get(IExplorerConstants.NODE_RESOURCE);
		if(resource instanceof IResource){
			return (IResource)resource;
		}
		return null;
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
