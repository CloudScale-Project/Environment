package eu.cloudscaleproject.env.toolchain.explorer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.contexts.IEclipseContext;

import eu.cloudscaleproject.env.toolchain.resources.IExplorerContentRetriever;
import eu.cloudscaleproject.env.toolchain.resources.ProjectResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IConfigAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.resources.types.IResultAlternative;

public class ExplorerAlternativesRetriever implements IExplorerContentRetriever{

	private final PropertyChangeListener resourceRegistryListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			
			if(ProjectResourceRegistry.PROP_RESOURCE_PROVIDER_ADDED.equals(evt.getPropertyName())){
				ResourceProvider rp = (ResourceProvider)evt.getNewValue();
				if(rp.getID().equals(resourceProviderID)){
					registerResourceProvider(rp);
				}
				
			}
			if(ProjectResourceRegistry.PROP_RESOURCE_PROVIDER_REMOVED.equals(evt.getPropertyName())){
				ResourceProvider rp = (ResourceProvider)evt.getOldValue();
				if(rp.getID().equals(resourceProviderID)){
					unregisterResourceProvider(resourceProvider);
				}
			}
			
			pcs.firePropertyChange(PROP_CHILDREN_CHANGED, null, this);
		}
	};
	
	private final PropertyChangeListener resourceProviderListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			pcs.firePropertyChange(PROP_CHILDREN_CHANGED, null, this);
		}
	};

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private IProject project;
	private String resourceProviderID;
	
	private IEclipseContext context;
	
	private ProjectResourceRegistry projectResourceRegistry;
	private ResourceProvider resourceProvider;
	
	public void initialize(String nodeID, IEclipseContext context){
		
		this.project = context.get(IProject.class);
		this.resourceProviderID = nodeID;
		
		this.context = context;
		
		this.projectResourceRegistry = ResourceRegistry.getInstance().getProjectResourceRegistry(project);
		this.projectResourceRegistry.addPropertyChangeListener(resourceRegistryListener);
		
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, nodeID);
		if(rp != null){
			registerResourceProvider(rp);
		}
		
	}
	
	private void registerResourceProvider(ResourceProvider rp){
		
		if(rp == null){
			return;
		}
		
		this.resourceProvider = rp;
		
		rp.addListener(resourceProviderListener);
		this.context.set(ResourceProvider.class, rp);
	}
	
	private void unregisterResourceProvider(ResourceProvider rp){

		if(rp == null){
			return;
		}
		
		this.resourceProvider = null;

		rp.removeListener(resourceProviderListener);
		this.context.set(ResourceProvider.class, null);
	}
	
	@Override
	public List<Object> getChildren() {
		
		IEditorInputResource parentEir = context.get(IEditorInputResource.class);
		
		List<Object> out = new ArrayList<Object>();		

		if(resourceProvider == null){
			return out;
		}
			
		for(IEditorInputResource eir : resourceProvider.getResources()){
			
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
		this.projectResourceRegistry.removePropertyChangeListener(resourceRegistryListener);
		unregisterResourceProvider(resourceProvider);
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
