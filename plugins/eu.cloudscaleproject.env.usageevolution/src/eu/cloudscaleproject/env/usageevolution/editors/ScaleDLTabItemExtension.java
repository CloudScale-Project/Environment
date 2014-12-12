package eu.cloudscaleproject.env.usageevolution.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.common.DIExtension;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ProjectEditorExtension;
import eu.cloudscaleproject.env.toolchain.editors.ProjectEditor;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.util.AbstractSidebarMenuComposite;

public class ScaleDLTabItemExtension extends DIExtension implements ProjectEditorExtension{
	
	public static final String ID = "eu.cloudscaleproject.env.usageevolution.tabitemextension";
	
	private static final String SECTION_MAINMENU = "Models:";

	private CTabItem tabItem = null;
	
	private final EditorInput overviewInput = new EditorInput("Overview");
	private final EditorInput usageEvolutionInput = new EditorInput("Usage Evolution");
	private final EditorInput architecturalTemplatesInput = new EditorInput("Architectural Temp.");

	private AbstractSidebarMenuComposite scaledlEditor;

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
		tabItem.setText("ScaleDL");
		
		final IProject project = ExplorerProjectPaths.getProject(editor);

		scaledlEditor = new AbstractSidebarMenuComposite(editor.getTabFolder(), SWT.NONE) {
			
			@Override
			public String[] getSidebarSections() {
				return new String[]{SECTION_MAINMENU};
				
			}
			
			@Override
			public List<IEditorInput> getInputs(String section) {
				List<IEditorInput> out = new ArrayList<IEditorInput>();
				if(SECTION_MAINMENU.equals(section)){
					out.add(overviewInput);
					out.add(usageEvolutionInput);
					out.add(architecturalTemplatesInput);
				}
				return out;
			}
			
			@Override
			public Composite createInputComposite(IEditorInput input, Composite parent, int style) {
				
				if(overviewInput.equals(input)){
					return new OverviewComposite(project, parent, SWT.NONE);
				}
				if(usageEvolutionInput.equals(input)){
					return new UsageEvolutionComposite(project, parent, SWT.NONE);
				}
				if(architecturalTemplatesInput.equals(input)){
					return new ArchitecturalTemplatesComposite(project, parent, SWT.NONE);
				}
				
				return null;
			}
		};
		
		scaledlEditor.init();
		scaledlEditor.showInput(usageEvolutionInput);
		tabItem.setControl(scaledlEditor);
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
