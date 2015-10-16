package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import org.eclipse.core.resources.IResource;
import org.eclipse.e4.core.contexts.IEclipseContext;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerEditorNode;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNodeChildren;

public class ConfigurationElementEditorNode extends ExplorerEditorNode{

	public ConfigurationElementEditorNode(IEclipseContext context, 
										  String id, String editorID, IResource resource, IExplorerNodeChildren children) {
		super(context, id, editorID, resource, children);
	}

}
