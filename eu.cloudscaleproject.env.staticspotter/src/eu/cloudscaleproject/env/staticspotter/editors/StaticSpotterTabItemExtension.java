package eu.cloudscaleproject.env.staticspotter.editors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.toolchain.ProjectEditorExtension;
import eu.cloudscaleproject.env.toolchain.editors.ProjectEditor;

public class StaticSpotterTabItemExtension implements ProjectEditorExtension{

	public static final String ID = "eu.cloudscaleproject.env.staticspotter.tabitemextension";
	private static CTabItem tabItem;
	
	@Override
	public void createTabItem(ProjectEditor editor) {
		tabItem = new CTabItem(editor.getTabFolder(), SWT.NONE);
		tabItem.setText("Static Spotter");
		
		StaticSpotterEditor staticSpotterEditor = new StaticSpotterEditor(editor.getTabFolder(), SWT.NONE);
		tabItem.setControl(staticSpotterEditor);
	}

	@Override
	public CTabItem getTabItem() {
		return tabItem;
	}

	@Override
	public String getID() {

		return ID;
	}

	@Override
	public void handleAction(String action) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public IPropertySheetPage getPropertySheetPage() {
		return null;
	}
}
