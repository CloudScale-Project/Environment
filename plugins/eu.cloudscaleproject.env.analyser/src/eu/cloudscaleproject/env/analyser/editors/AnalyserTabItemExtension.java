package eu.cloudscaleproject.env.analyser.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.common.DIExtension;
import eu.cloudscaleproject.env.toolchain.ProjectEditorExtension;
import eu.cloudscaleproject.env.toolchain.editors.ProjectEditor;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.util.AbstractSidebarMenuComposite;

public class AnalyserTabItemExtension extends DIExtension implements ProjectEditorExtension{
	
	public static final String ID = "eu.cloudscaleproject.env.analyser.tabitemextension";
	
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
	private AbstractSidebarMenuComposite analyserEditor;
			
	@Override
	public String getID() {
		return ID;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void createTabItem(final ProjectEditor editor) {
		tabItem = new CTabItem(editor.getTabFolder(), SWT.NONE);
		tabItem.setText("Analyser");
				
		analyserEditor = new AbstractSidebarMenuComposite(editor.getTabFolder(), SWT.NONE) {
			
			@Override
			public String[] getSidebarSections() {
				return new String[]{SECTION_MAINMENU};
			}
			
			@Override
			public List<IEditorInput> getInputs(String section) {
				List<IEditorInput> out = new ArrayList<IEditorInput>();
				if(SECTION_MAINMENU.equals(section)){
					out.add(introInput);
					out.add(inputInput);
					out.add(runInput);
					out.add(resultsInput);
				}
				return out;
			}
			
			@Override
			public Composite createInputComposite(IEditorInput input, Composite parent, int style) {
				
				if(introInput.equals(input)){
					return new IntroComposite(editor, parent, SWT.NONE);
				}
				else if(inputInput.equals(input)){
					return new InputComposite(editor, parent, SWT.NONE);
				}
				else if(runInput.equals(input)){
					return new ConfigComposite(editor, parent, SWT.NONE);
				}
				else if(resultsInput.equals(input)){
					return new ResultsComposite(editor, parent, SWT.NONE);
				}
				
				return null;
			}
		};
		
		analyserEditor.init();
		analyserEditor.showInput(introInput);
		tabItem.setControl(analyserEditor);
	}

	@Override
	public CTabItem getTabItem() {
		return tabItem;
	}
	
	public void save(IProgressMonitor monitor){
		analyserEditor.save(monitor);
	}
	
	@Override
	public void load(IProgressMonitor monitor) {
		analyserEditor.load(monitor, true);
	}
	
	public boolean isDirty(){
		return analyserEditor.isDirty();
	}

	@Override
	public void handleAction(String action) {
		if(analyserEditor != null){
			if(ACTION_OPEN_INTRO.equals(action)){
				analyserEditor.showInput(introInput);
			}
			else if(ACTION_OPEN_INPUT.equals(action)){
				analyserEditor.showInput(inputInput);
			}
			else if(ACTION_OPEN_RUN.equals(action)){
				analyserEditor.showInput(runInput);
			}
			else if(ACTION_OPEN_RESULTS.equals(action)){
				analyserEditor.showInput(resultsInput);
			}
		}
	}
	
	@Override
	public void setFocus() {
		analyserEditor.onSelect();
	}

	@Override
	public IPropertySheetPage getPropertySheetPage() {
		return analyserEditor.getPropertySheetPage();
	}
}
