package eu.cloudscaleproject.env.toolchain.explorer;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.contexts.IEclipseContext;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerResourceNode extends ExplorerNode{

	public final IResource resource;
		
	public ExplorerResourceNode(IEclipseContext context, String id, IResource resource, IExplorerNodeChildren childFactory) {
		super(context, id, childFactory);
		
		if(resource instanceof IFolder){
			try {
				ExplorerProjectPaths.prepareFolder((IFolder)resource);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		this.resource = resource;
		if(resource != null){
			this.getContext().set(IExplorerConstants.NODE_RESOURCE, resource);
		}
	}
	
	public IResource getResource(){
		return resource;
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
