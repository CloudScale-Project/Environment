package eu.cloudscaleproject.env.overview.editors;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.common.DIExtension;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ProjectEditorExtension;
import eu.cloudscaleproject.env.toolchain.editors.ProjectEditor;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

/**
 * 
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class OverviewTabItemExtension extends DIExtension implements ProjectEditorExtension{
	
	public static final String ID = "eu.cloudscaleproject.env.overview.tabitemextension";
	
	private CTabItem tabItem = null;
	private SidebarEditorComposite overviewSidebarEditor;

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
		tabItem.setText("Overview");
		
		final IProject project = ExplorerProjectPaths.getProject(editor);

		overviewSidebarEditor = new OverviewComposite(project, editor.getTabFolder(), SWT.NONE);
		
		tabItem.setControl(overviewSidebarEditor);
	}

	@Override
	public CTabItem getTabItem() {
		return tabItem;
	}
	
	@Override
	public void save() {
		overviewSidebarEditor.save();
	}
	
	@Override
	public boolean isDirty() {
		return overviewSidebarEditor.isDirty();
	}

	@Override
	public void handleAction(String action) {
		
	}
	
	@Override
	public IPropertySheetPage getPropertySheetPage() {
		return overviewSidebarEditor.getPropertySheetPage();
	}
	
}
