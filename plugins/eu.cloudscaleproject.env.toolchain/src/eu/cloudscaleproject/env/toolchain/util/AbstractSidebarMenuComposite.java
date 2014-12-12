package eu.cloudscaleproject.env.toolchain.util;

import org.eclipse.swt.widgets.Composite;

public abstract class AbstractSidebarMenuComposite extends AbstractSidebarEditorComposite{
	
	public AbstractSidebarMenuComposite(Composite parent, int style) {
		super(parent, style);
		
		setNewButtonEnabled(false);
		setNewFromButtonEnabled(false);
		setRemoveButtonEnabled(false);
	}
	
}