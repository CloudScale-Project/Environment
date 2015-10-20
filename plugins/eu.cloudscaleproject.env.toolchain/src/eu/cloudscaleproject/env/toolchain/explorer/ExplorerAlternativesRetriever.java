package eu.cloudscaleproject.env.toolchain.explorer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.contexts.IEclipseContext;

import eu.cloudscaleproject.env.toolchain.resources.IExplorerContentRetriever;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IConfigAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.resources.types.IResultAlternative;

public class ExplorerAlternativesRetriever implements IExplorerContentRetriever{
	
	private final PropertyChangeListener resourceProviderListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			pcs.firePropertyChange(PROP_CHILDREN_CHANGED, null, this);
		}
	};

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private IEclipseContext context;
	private ResourceProvider rp;
	
	public void initialize(String nodeID, IEclipseContext context){
		
		IProject project = context.get(IProject.class);
		
		this.context = context;
		
		rp = ResourceRegistry.getInstance().getResourceProvider(project, nodeID);
		rp.addListener(resourceProviderListener);
		
		this.context.set(ResourceProvider.class, rp);
	}
	
	@Override
	public List<Object> getNodeChildren() {
		
		IEditorInputResource parentEir = context.get(IEditorInputResource.class);
		
		List<Object> out = new ArrayList<Object>();		
			
		for(IEditorInputResource eir : rp.getResources()){
			
			if(eir instanceof IConfigAlternative){
				IConfigAlternative ca = (IConfigAlternative)eir;
				if(ca.getInputAlternative() == parentEir){
					out.add(eir);
				}
				continue;
			}
			
			if(eir instanceof IResultAlternative){
				IResultAlternative ra = (IResultAlternative)eir;
				if(ra.getInputAlternative() == parentEir || ra.getConfigAlternative() == parentEir){
					out.add(eir);
				}
				continue;
			}
			
			out.add(eir);
		}
		
		return out;
	}

	@Override
	public void dispose() {
		rp.removeListener(resourceProviderListener);
	}
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

}
