package eu.cloudscaleproject.env.usageevolution.editors;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.common.DIExtension;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ProjectEditorExtension;
import eu.cloudscaleproject.env.toolchain.editors.ProjectEditor;

public class UsageTabItemExtension extends DIExtension implements ProjectEditorExtension{
	
	public static final String ID = "eu.cloudscaleproject.env.usageevolution.tabitemextension";
	
	private CTabItem tabItem = null;
	private UsageEvolutionComposite scaledlEditor;

	@Override
	public String getID() {
		return ID;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void createTabItem(ProjectEditor editor) {
		tabItem = new CTabItem(editor.getTabFolder(), SWT.NONE);
		tabItem.setText("Limbo");
		
		final IProject project = ExplorerProjectPaths.getProject(editor);

		scaledlEditor = new UsageEvolutionComposite(project, editor.getTabFolder(), SWT.NONE);
		tabItem.setControl(scaledlEditor);
	}

	@Override
	public CTabItem getTabItem() {
		return tabItem;
	}
	
	@Override
	public void save(IProgressMonitor monitor) {
		scaledlEditor.save(monitor);
	}
	
	@Override
	public void load(IProgressMonitor monitor) {
		scaledlEditor.load(monitor, true);
	}
	
	@Override
	public boolean isDirty() {
		return scaledlEditor.isDirty();
	}
	
	@Override
	public void setFocus() {
		scaledlEditor.onSelect();
	}

	@Override
	public void handleAction(String action) {
		
	}
	
	@Override
	public IPropertySheetPage getPropertySheetPage() {
		return scaledlEditor.getPropertySheetPage();
	}
}
