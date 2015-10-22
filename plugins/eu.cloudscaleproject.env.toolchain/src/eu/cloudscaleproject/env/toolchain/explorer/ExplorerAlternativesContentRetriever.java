package eu.cloudscaleproject.env.toolchain.explorer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.contexts.IEclipseContext;

import eu.cloudscaleproject.env.toolchain.Activator;
import eu.cloudscaleproject.env.toolchain.resources.IExplorerContentRetriever;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputJob;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ExplorerAlternativesContentRetriever implements IExplorerContentRetriever{

	private final PropertyChangeListener alternativeListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(EditorInputEMF.PROP_SUB_RESOURCE_CHANGED.equals(evt.getPropertyName())){
				pcs.firePropertyChange(PROP_CHILDREN_CHANGED, null, this);
			}
		}
	};
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	protected IEditorInputResource alternative;
	
	public void initialize(String nodeID, IEclipseContext context){
		alternative = context.get(IEditorInputResource.class);
		if(alternative != null){
			alternative.addPropertyChangeListener(alternativeListener);
			loadAlternative(alternative);
		}
	}
	
	private void loadAlternative(final IEditorInputResource eir){
		if(!eir.isLoaded()){
			EditorInputJob job = new EditorInputJob("Loading alternative ["+ eir.getName() +"]...") {
				
				@Override
				public IStatus execute(IProgressMonitor monitor) {
					monitor.beginTask("Loading alternative ["+ eir.getName() +"]", IProgressMonitor.UNKNOWN);
					if(!eir.isLoaded()){
						eir.load(monitor);
					}
					monitor.done();
					return new Status(IStatus.OK, Activator.PLUGIN_ID, "Loading resource done.");
				}
			};
			job.setUser(false);
			job.schedule();
		}
	}
	
	@Override
	public List<Object> getChildren() {
				
		List<Object> out = new ArrayList<Object>();
		if(alternative instanceof EditorInputEMF){
			EditorInputEMF eie = (EditorInputEMF)alternative;
			out.addAll(eie.getSubResources());
		}
		return out;
	}

	@Override
	public void dispose() {
		if(alternative != null){
			alternative.removePropertyChangeListener(alternativeListener);
		}
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
