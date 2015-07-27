package eu.cloudscaleproject.env.toolchain;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.custom.CTabItem;

import eu.cloudscaleproject.env.toolchain.editors.ProjectEditor;

public interface ProjectEditorExtension extends IPropertySheetPageProvider{

	public String getID();
	
	public void createTabItem(ProjectEditor editor);
	public CTabItem getTabItem();
	public void handleAction(String action);
	
	public void save(IProgressMonitor monitor);
	public void load(IProgressMonitor monitor);
	
	public boolean isDirty();
	
	public void setFocus();
}
