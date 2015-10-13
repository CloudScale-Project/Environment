package eu.cloudscaleproject.env.toolchain.explorer.children;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import eu.cloudscaleproject.env.toolchain.Activator;
import eu.cloudscaleproject.env.toolchain.ToolchainExtensions;
import eu.cloudscaleproject.env.toolchain.explorer.Explorer;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.nodes.AlternativeNode;
import eu.cloudscaleproject.env.toolchain.explorer.nodes.AlternativeResourceNode;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputJob;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

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
				Explorer.getInstance().refreshAll();
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
	public List<Object> getKeys() {
		
		List<Object> keys = new ArrayList<Object>();
		
		//uncomment to show alternative hierarchy input->configurations->results
		/*
		if(alternative instanceof IInputAlternative){
			IInputAlternative ia = (IInputAlternative)alternative;
			keys.addAll(ia.getConfigAlternatives());
		}
		if(alternative instanceof IConfigAlternative){
			IConfigAlternative ia = (IConfigAlternative)alternative;
			keys.addAll(ia.getResults());
		}
		*/
		
		keys.addAll(alternative.getSubResources());
		
		return keys;
	}

	@Override
	public IExplorerNode getChild(Object key) {
		
		if(key instanceof IEditorInputResource){
			IEditorInputResource eir = (IEditorInputResource)key;
			String editorID = ToolchainExtensions.getInstance().getToolChildElementEditorID(eir.getID());
			
			IExplorerNodeChildren children = null;		
			if(key instanceof EditorInputEMF){
				children = new AlternativeNodeChildren((EditorInputEMF)key, true);
			}
			
			AlternativeNode node = new AlternativeNode(getNode().getContext(), editorID, (IEditorInputResource)key, children);
			return node;
		}
		else if(key instanceof IResource){
			IResource res = (IResource)key;
			if(!(res instanceof IFile) || !res.exists()){
				return null;
			}
			
			AlternativeResourceNode node = new AlternativeResourceNode(getNode().getContext(), alternative, (IFile)res);		
			return node;
		}
		
		return null;
	}

	@Override
	public void dispose() {
		this.alternative.removePropertyChangeListener(alternativeListener);
		super.dispose();
	}
}
