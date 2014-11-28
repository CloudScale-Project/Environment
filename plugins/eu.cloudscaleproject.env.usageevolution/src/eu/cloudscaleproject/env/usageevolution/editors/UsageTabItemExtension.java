package eu.cloudscaleproject.env.usageevolution.editors;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.common.DIExtension;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ProjectEditorExtension;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.editors.ProjectEditor;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;
import eu.cloudscaleproject.env.usageevolution.editors.composite.EditorComposite;

public class UsageTabItemExtension extends DIExtension implements ProjectEditorExtension{
	
	public static final String ID = "eu.cloudscaleproject.env.usageevolution.tabitemextension";
	
	private String[] sections = new String[]{"Alternatives:"};
		
	private CTabItem tabItem = null;
	private SidebarEditorComposite usageEditor;
	
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
		tabItem.setText("Usage evolution");
		
		final IProject project = ExplorerProjectPaths.getProject(editor);
		
		usageEditor = new SidebarEditorComposite(editor.getTabFolder(), SWT.NONE);
		usageEditor.setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.USAGEEVOLUTION_ID));
		usageEditor.setContentProvider(new SidebarContentProvider() {
			
			@Override
			public String[] getSections() {
				return sections;
			}
			
			@Override
			public String getSection(IEditorInputResource resource) {
				return sections[0];
			}
			
			@Override
			public Composite createComposite(Composite parent, int style,
					IEditorInputResource resource) {
				
				if(resource instanceof UsageEvolutionAlternative){
					UsageEvolutionAlternative alt = (UsageEvolutionAlternative)resource;
					EditorComposite editor = new EditorComposite(alt, parent, style);
					return editor;
				}
				
				return null;
			}
		});
		
		tabItem.setControl(usageEditor);
	}

	@Override
	public CTabItem getTabItem() {
		return tabItem;
	}

	@Override
	public void handleAction(String action) {
		
	}

	@Override
	public IPropertySheetPage getPropertySheetPage() {
		return null;
	}
}
