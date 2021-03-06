package eu.cloudscaleproject.env.spotter.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.spotter.editors.composite.ConfSidebarComposite;
import eu.cloudscaleproject.env.spotter.editors.composite.InputSidebarComposite;
import eu.cloudscaleproject.env.spotter.editors.composite.IntroComposite;
import eu.cloudscaleproject.env.spotter.editors.composite.ResultsSidebarComposite;
import eu.cloudscaleproject.env.spotter.editors.composite.ServerClientComposite;
import eu.cloudscaleproject.env.toolchain.ProjectEditorExtension;
import eu.cloudscaleproject.env.toolchain.editors.ProjectEditor;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.util.AbstractSidebarMenuComposite;

public class SpotterTabItemExtension implements ProjectEditorExtension{
	
	public static final String ID = "eu.cloudscaleproject.env.spotter.tabitemextension";
	
	private static final String SECTION_MAINMENU = "Main menu:";
	
	public static final String ACTION_OPEN_INTRO = "openIntro";
	public static final String ACTION_OPEN_INPUT = "openInput";
	public static final String ACTION_OPEN_RUN = "openRun";
	public static final String ACTION_OPEN_RESULTS = "openResults";
	
	private final EditorInput introInput = new EditorInput("Intro");
	private final EditorInput serverInput = new EditorInput("Server");
	private final EditorInput inputInput = new EditorInput("Input");
	private final EditorInput runInput = new EditorInput("Run");
	private final EditorInput resultsInput = new EditorInput("Results");
		
	private CTabItem tabItem = null;
	private AbstractSidebarMenuComposite spotterEditor;
	
	public static EditorPart editorPart;
	
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
		tabItem.setText("Dynamic Spotter");
		
		SpotterTabItemExtension.editorPart = editor;
				
		final IProject project = ExplorerProjectPaths.getProject(editor);
		spotterEditor = new AbstractSidebarMenuComposite(editor.getTabFolder(), SWT.NONE) {
			
			@Override
			public String[] getSidebarSections() {
				return new String[]{SECTION_MAINMENU};
			}
			
			@Override
			public List<IEditorInput> getInputs(String section) {
				List<IEditorInput> out = new ArrayList<IEditorInput>();
				if(SECTION_MAINMENU.equals(section)){
					out.add(introInput);
					out.add(serverInput);
					out.add(inputInput);
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
				else if(serverInput.equals(input)){
					return new ServerClientComposite(project, parent, SWT.NONE);
				}
				else if(inputInput.equals(input)){
					return new InputSidebarComposite(project, parent, SWT.NONE);
				}
				else if(runInput.equals(input)){
					return new ConfSidebarComposite(project, parent, SWT.NONE);
				}
				else if(resultsInput.equals(input)){
					return new ResultsSidebarComposite(project, parent, SWT.NONE);
				}
				
				return null;
			}
		};
		
		spotterEditor.init();
		spotterEditor.showInput(introInput);
		tabItem.setControl(spotterEditor);
	}

	@Override
	public CTabItem getTabItem() {
		return tabItem;
	}
	
	@Override
	public void save(IProgressMonitor monitor) {
		spotterEditor.save(monitor);
	}
	
	@Override
	public void load(IProgressMonitor monitor) {
		spotterEditor.load(monitor, true);
	}
	
	@Override
	public boolean isDirty() {
		return spotterEditor.isDirty();
	}
	
	@Override
	public void setFocus() {
		spotterEditor.onSelect();
	}

	@Override
	public void handleAction(String action) {
		if(spotterEditor != null){
			if(ACTION_OPEN_INTRO.equals(action)){
				spotterEditor.showInput(introInput);
			}
			else if(ACTION_OPEN_INPUT.equals(action)){
				spotterEditor.showInput(inputInput);
			}
			else if(ACTION_OPEN_RUN.equals(action)){
				spotterEditor.showInput(runInput);
			}
			else if(ACTION_OPEN_RESULTS.equals(action)){
				spotterEditor.showInput(resultsInput);
			}
		}
	}
	
	@Override
	public IPropertySheetPage getPropertySheetPage() {
		return spotterEditor.getPropertySheetPage();
	}
}