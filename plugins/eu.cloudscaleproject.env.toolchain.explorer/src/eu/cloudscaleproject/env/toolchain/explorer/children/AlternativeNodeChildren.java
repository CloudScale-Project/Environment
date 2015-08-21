package eu.cloudscaleproject.env.toolchain.explorer.children;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import eu.cloudscaleproject.env.toolchain.Activator;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.nodes.AlternativeResourceNode;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputJob;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class AlternativeNodeChildren extends ExplorerNodeChildren{
	
	private final PropertyChangeListener alternativeListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(EditorInputFolder.PROP_SUB_RESOURCE_CHANGED.equals(evt.getPropertyName())){
				refresh();
			}
			if(EditorInputFolder.PROP_LOADED.equals(evt.getPropertyName())){
				refresh();
			}
		}
	};

	private final EditorInputEMF alternative;
	
	public AlternativeNodeChildren(final EditorInputEMF alternative, boolean lazy) {
		super(lazy);
		
		this.alternative = alternative;
		this.alternative.addPropertyChangeListener(alternativeListener);
		
		EditorInputJob job = new EditorInputJob("Loading alternative ["+ this.alternative.getName() +"]...") {
			
			@Override
			public IStatus execute(IProgressMonitor monitor) {
				monitor.beginTask("Loading alternative ["+ alternative.getName() +"]", IProgressMonitor.UNKNOWN);
				if(!AlternativeNodeChildren.this.alternative.isLoaded()){
					AlternativeNodeChildren.this.alternative.load(monitor);
				}
				monitor.done();
				return new Status(IStatus.OK, Activator.PLUGIN_ID, "Loading resource done.");
			}
		};
		job.setUser(false);
		job.schedule();
		
	}
	
	@Override
	public List<? extends Object> getKeys() {
		return alternative.getSubResources();
	}

	@Override
	public IExplorerNode getChild(Object key) {
		
		IResource res = (IResource)key;
		if(!(res instanceof IFile) || !res.exists()){
			return null;
		}
		
		AlternativeResourceNode node = new AlternativeResourceNode(alternative, (IFile)res);		
		return node;
		
	}

	@Override
	public void dispose() {
		this.alternative.removePropertyChangeListener(alternativeListener);
		super.dispose();
	}
}
