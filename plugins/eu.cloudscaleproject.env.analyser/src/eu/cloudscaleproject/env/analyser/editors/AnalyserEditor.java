package eu.cloudscaleproject.env.analyser.editors;


/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 * TODO:
 * This class is not used!
 *
 */

public class AnalyserEditor /*extends EditorPart implements ProjectEditorExtension */{
	
	/*
	public static final String ID = "";
	
	private static final String SECTION_MAINMENU = "Main menu:";
	
	public static final String ACTION_OPEN_INTRO = "openIntro";
	public static final String ACTION_OPEN_INPUT = "openInput";
	public static final String ACTION_OPEN_RUN = "openRun";
	public static final String ACTION_OPEN_RESULTS = "openResults";
	
	private final EditorInput introInput = new EditorInput("Intro");
	private final EditorInput inputInput = new EditorInput("Input");
	private final EditorInput runInput = new EditorInput("Run");
	private final EditorInput resultsInput = new EditorInput("Results");
	
	private AbstractSidebarMenuComposite analyserEditor;
	private org.eclipse.ui.IEditorInput editorInput = null;

	@Override
	public void init(IEditorSite site, org.eclipse.ui.IEditorInput input) throws PartInitException {
		if(input instanceof EditorInputResource){
			EditorInputResource eir = (EditorInputResource)input;
			eir.load();
		}
		editorInput = input;
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		if(editorInput instanceof EditorInputResource){
			((EditorInputResource)editorInput).save();
		}
	}

	@Override
	public boolean isDirty() {
		if(editorInput instanceof EditorInputResource){
			return ((EditorInputResource)editorInput).isDirty();
		}
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		analyserEditor = new AbstractSidebarMenuComposite(parent, SWT.NONE) {
			
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
					return new IntroComposite(AnalyserEditor.this, parent, SWT.NONE);
				}
				else if(inputInput.equals(input)){
					return new InputComposite(AnalyserEditor.this, parent, SWT.NONE);
				}
				else if(runInput.equals(input)){
					return new ConfigComposite(AnalyserEditor.this, parent, SWT.NONE);
				}
				else if(resultsInput.equals(input)){
					return new ResultsComposite(AnalyserEditor.this, parent, SWT.NONE);
				}
				
				return null;
			}
		};
		
		analyserEditor.init();
		analyserEditor.showInput(introInput);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void doSaveAs() {
		//not used
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
	
	///////////////////////////////////////////////////////////////////////
	// Remove unused!

	@Override
	public IPropertySheetPage getPropertySheetPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createTabItem(ProjectEditor editor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CTabItem getTabItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleAction(String action) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
	*/

}
