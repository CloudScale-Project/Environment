package eu.cloudscaleproject.env.spotter.editors.composite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PartInitException;
import org.spotter.eclipse.ui.editors.InstrumentationEditor;
import org.spotter.eclipse.ui.editors.InstrumentationEditorInput;
import org.spotter.eclipse.ui.editors.MeasurementEditor;
import org.spotter.eclipse.ui.editors.MeasurementEditorInput;

import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.spotter.SpotterClientController;
import eu.cloudscaleproject.env.spotter.alternatives.InputAlternative;
import eu.cloudscaleproject.env.spotter.editors.SpotterTabItemExtension;
import eu.cloudscaleproject.env.toolchain.ui.InputEditorView;

public class InputAlternativeComposite extends InputEditorView implements IRefreshable, ISelectable{
	
	private final InputAlternative inputAlternative;
	
	private IProject project;
	
	private Composite insComposite;
	private Composite meaComposite;
	
	private InstrumentationEditor insEditor = new InstrumentationEditor(){
		public String getContentDescription() {return "";};
		protected void setContentDescription(String description) {};
	};
	
	private MeasurementEditor meaEditor = new MeasurementEditor(){
		public String getContentDescription() {return "";};
		protected void setContentDescription(String description) {};
	};

	private Composite mainComposite;
	private Composite warningComposite;
		
	public InputAlternativeComposite(IProject project, Composite parent, int style, final InputAlternative inputAlternative) {
		super(parent, style, inputAlternative);
		
		this.project = project;
		this.inputAlternative = inputAlternative;

		getContainer().setLayout(new StackLayout());
		
		warningComposite = new Composite(getContainer(), SWT.BORDER);
		warningComposite.setLayout(new GridLayout(1, false));
		Label lblEmpty = new Label(warningComposite, SWT.CENTER);
		lblEmpty.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		lblEmpty.setText("Spotter client not connected... \nTo enable editing, go to Server section and connect Spotter client.");
		
		mainComposite = new Composite(getContainer(), SWT.NONE);
		mainComposite.setLayout(new GridLayout(1, false));
		
		CTabFolder tabFolder = new CTabFolder(mainComposite, SWT.BORDER | SWT.FLAT);
		tabFolder.setTabHeight(32);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		//tabFolder.setTabHeight(10);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));	
		
		//create instrumentation editor
		{
			CTabItem tabItemInstrumentation = new CTabItem(tabFolder, SWT.NONE);
			tabItemInstrumentation.setText("Instrumentations");
			
			insComposite = new Composite(tabFolder, SWT.NONE);
			insComposite.setLayout(new FillLayout());

			
			tabItemInstrumentation.setControl(insComposite);
			tabFolder.setSelection(tabItemInstrumentation);
		}
		//create measurements editor
		{
			CTabItem tabItemMeasurement = new CTabItem(tabFolder, SWT.NONE);
			tabItemMeasurement.setText("Measurements");
			
			meaComposite = new Composite(tabFolder, SWT.NONE);
			meaComposite.setLayout(new FillLayout());
			
			tabItemMeasurement.setControl(meaComposite);
		}

		updateEditors();
		
		this.addDisposeListener(new DisposeListener()
		{
			@Override
			public void widgetDisposed(DisposeEvent e)
			{
				inputAlternative.unRegisterSpotterEditor(insEditor);
				inputAlternative.unRegisterSpotterEditor(meaEditor);
			}
		});
	}
	
	@Override
	public void refresh() {
		updateEditors();
	}

	private void updateEditors ()
	{
		if (SpotterClientController.getController(project).isConnected()) 
		{
			if (!editorsInitialized) initExternalEditors();
			((StackLayout)getContainer().getLayout()).topControl = mainComposite;
			getContainer().layout();
		}
		else
		{
			((StackLayout)getContainer().getLayout()).topControl = warningComposite;
			getContainer().layout();
		}
	}
	
	
	private boolean editorsInitialized;
	private void initExternalEditors ()
	{
		if (editorsInitialized) return;
		if (!SpotterClientController.getController(project).isConnected()) return;
		
		this.editorsInitialized = true;

		try
		{
			IFile file = (IFile) inputAlternative.getSubResource(InputAlternative.KEY_ENVIRONMENT_CONFIG);
			InstrumentationEditorInput inEditorInput = new InstrumentationEditorInput(file);
			MeasurementEditorInput measurementEditorInput = new MeasurementEditorInput(file);

			insEditor.init(SpotterTabItemExtension.editorPart.getEditorSite(), inEditorInput);
			meaEditor.init(SpotterTabItemExtension.editorPart.getEditorSite(), measurementEditorInput);

			insEditor.createPartControl(insComposite);
			meaEditor.createPartControl(meaComposite);

			insComposite.layout();
			meaComposite.layout();

			inputAlternative.registerSpotterEditor(insEditor);
			inputAlternative.registerSpotterEditor(meaEditor);
		} catch (PartInitException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onSelect() {
	}
	
}
