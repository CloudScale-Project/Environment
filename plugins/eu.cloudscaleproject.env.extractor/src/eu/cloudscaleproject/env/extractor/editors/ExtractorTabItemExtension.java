package eu.cloudscaleproject.env.extractor.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ProjectEditorExtension;
import eu.cloudscaleproject.env.toolchain.editors.ProjectEditor;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.util.AbstractSidebarMenuComposite;

public class ExtractorTabItemExtension implements ProjectEditorExtension{
	
	public static final String ID = "eu.cloudscaleproject.env.extractor.tabitemextension";
	
	private static final String SECTION_MAINMENU = "Main menu:";
	
	public static final String ACTION_OPEN_INTRO = "openIntro";
	public static final String ACTION_OPEN_INPUT = "openInput";
	public static final String ACTION_OPEN_RUN = "openRun";
	public static final String ACTION_OPEN_RESULTS = "openResults";
	
	private final EditorInput introInput = new EditorInput("Intro");
	private final EditorInput inputInput = new EditorInput("Input");
	private final EditorInput runInput = new EditorInput("Run");
	private final EditorInput resultsInput = new EditorInput("Results");
	
	private CTabItem tabItem = null;
	private AbstractSidebarMenuComposite extractorEditor;
	
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
		tabItem.setText("Extractor");
		
		final IProject project = ExplorerProjectPaths.getProject(editor);
		
		extractorEditor = new AbstractSidebarMenuComposite(editor.getTabFolder(), SWT.NONE) {
			
			@Override
			public String[] getSidebarSections() {
				return new String[]{SECTION_MAINMENU};
			}
			
			@Override
			public List<IEditorInput> getInputs(String section) {
				List<IEditorInput> out = new ArrayList<IEditorInput>();
				if(SECTION_MAINMENU.equals(section)){
					out.add(introInput);
					//out.add(inputInput);
					out.add(runInput);
					out.add(resultsInput);
				}
				return out;
			}
			
			@Override
			public Composite createInputComposite(IEditorInput input, Composite parent, int style) {
				
				if(introInput.equals(input)){
					return new IntroComposite(parent, SWT.NONE);
				}
				else if(inputInput.equals(input)){
					return new InputComposite(project, parent, SWT.NONE);
				}
				else if(runInput.equals(input)){
					return new ConfigComposite(project, parent, SWT.NONE);
				}
				else if(resultsInput.equals(input)){
					return new ResultComposite(project, parent, SWT.NONE);
				}
				
				return null;
			}
		};
		
		extractorEditor.init();
		extractorEditor.showInput(introInput);
		tabItem.setControl(extractorEditor);
	}

	@Override
	public CTabItem getTabItem() {
		return tabItem;
	}
	
	@Override
	public void save(IProgressMonitor monitor) {
		extractorEditor.save(monitor);
	}
	
	@Override
	public void load(IProgressMonitor monitor) {
		extractorEditor.load(monitor, true);
	}

	@Override
	public boolean isDirty() {
		return extractorEditor.isDirty();
	}
	
	@Override
	public void setFocus() {
		extractorEditor.refresh();
	}

	@Override
	public void handleAction(String action) {
		if(extractorEditor != null){
			if(ACTION_OPEN_INTRO.equals(action)){
				extractorEditor.showInput(introInput);
			}
			else if(ACTION_OPEN_INPUT.equals(action)){
				extractorEditor.showInput(inputInput);
			}
			else if(ACTION_OPEN_RUN.equals(action)){
				extractorEditor.showInput(runInput);
			}
			else if(ACTION_OPEN_RESULTS.equals(action)){
				extractorEditor.showInput(resultsInput);
			}
		}
	}
	
	@Override
	public IPropertySheetPage getPropertySheetPage() {
		return extractorEditor.getPropertySheetPage();
	}
}