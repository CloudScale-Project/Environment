package eu.cloudscaleproject.env.overview.editors;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.common.DIExtension;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.overview.OverviewResource;
import eu.cloudscaleproject.env.toolchain.ProjectEditorExtension;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.editors.ProjectEditor;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

/**
 * 
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class OverviewTabItemExtension extends DIExtension implements ProjectEditorExtension{
	
	public static final String ID = "eu.cloudscaleproject.env.overview.tabitemextension";
	
	private static final String[] SECTIONS = new String[]{"Overview models:"};

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

		overviewSidebarEditor = new SidebarEditorComposite(editor.getTabFolder(), SWT.NONE);
		
		overviewSidebarEditor.setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.OVERVIEW_ID));
		overviewSidebarEditor.setContentProvider(new SidebarContentProvider() {
			
			@Override
			public String[] getSections() {
				return SECTIONS;
			}
			
			@Override
			public String getSection(IEditorInputResource resource) {
				return SECTIONS[0];
			}
			
			@Override
			public Composite createComposite(Composite parent, int style, IEditorInputResource resource) {
				
				if(resource instanceof OverviewResource){				
					return new OverviewEditor((OverviewResource)resource, parent, style);
				}
				return null;
			}
		});
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
