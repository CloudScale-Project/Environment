package eu.cloudscaleproject.env.toolchain.util;

import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public interface SidebarContentProvider {

	public String[] getSections();
	public String getSection(IEditorInputResource resource);
	public Composite createComposite(Composite parent, int style, IEditorInputResource resource);

}
