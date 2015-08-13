package eu.cloudscaleproject.env.toolchain.explorer.children;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.Activator;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerEditorNode;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputJob;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class EMFModelChildren extends ExplorerNodeChildren{
	
	private final PropertyChangeListener alternativeListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(EditorInputFolder.PROP_SUB_RESOURCE_CHANGED.equals(evt.getPropertyName())){
				refresh();
			}
		}
	};

	private final EditorInputEMF alternative;
	
	public EMFModelChildren(EditorInputEMF alternative, boolean lazy) {
		super(lazy);
		
		this.alternative = alternative;
		this.alternative.addPropertyChangeListener(alternativeListener);
		
		EditorInputJob job = new EditorInputJob("Loading...") {
			
			@Override
			public IStatus execute(IProgressMonitor monitor) {
				if(!EMFModelChildren.this.alternative.isLoaded()){
					EMFModelChildren.this.alternative.load(monitor);
				}
				return new Status(IStatus.OK, Activator.PLUGIN_ID, "Loading resource done.");
			}
		};
		job.setUser(false);
		job.schedule();
		
	}
	
	@Override
	public List<? extends Object> getKeys() {
		return alternative.getResourceSet().getResources();
	}

	@Override
	public IExplorerNode getChild(Object key) {
		
		Resource res = (Resource)key;
		IFile file = ExplorerProjectPaths.getFileFromEmfResource(res);
		if(file == null || !file.exists()){
			return null;
		}
		
		ExplorerEditorNode node = new ExplorerEditorNode(file.getName(), file, null);
		node.setData(file);
		
		ModelType type = ModelType.getModelType(res);
		node.setName(type.getDisplayName());
		return node;
		
	}

	@Override
	public void dispose() {
		this.alternative.removePropertyChangeListener(alternativeListener);
		super.dispose();
	}
}
