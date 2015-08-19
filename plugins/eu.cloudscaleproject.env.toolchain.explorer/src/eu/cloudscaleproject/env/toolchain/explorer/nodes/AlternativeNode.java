package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerEditorNode;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class AlternativeNode extends ExplorerEditorNode{

	public AlternativeNode(String editorID, IEditorInputResource alternative) {
		this(editorID, alternative, null);
	}
	
	public AlternativeNode(String editorID, IEditorInputResource alternative, IExplorerNodeChildren children) {
		super(alternative.getID(), editorID, alternative.getResource(), children);
		
		setName(alternative.getName());
		setData(alternative);
	}

}
