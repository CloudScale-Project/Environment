package eu.cloudscaleproject.env.spotter.editors.composite;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
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
import eu.cloudscaleproject.env.spotter.Util;
import eu.cloudscaleproject.env.spotter.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.ui.InputEditorView;

public class InputAlternativeComposite extends InputEditorView implements IRefreshable, ISelectable{
	
	private final InputAlternative inputAlternative;
	
	private IProject project;
	
	private Composite insComposite;
	private Composite meaComposite;
	
	PropertyChangeListener connecionListener = new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent evt)
			{
				if (InputAlternativeComposite.this.isDisposed()) return;
				Display.getDefault().asyncExec(new Runnable()
				{
					@Override
					public void run()
					{
						refresh();
					}
				});
			}
		};
	
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
		lblEmpty.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, true, true, 1, 1));
		lblEmpty.setText("Spotter client not connected... \nTo enable editing, go to Server section and connect Spotter client.");
		
		btnStartServer = new Button(warningComposite, SWT.NONE);
		GridData gd_btnStartServer = new GridData(SWT.CENTER, SWT.TOP, true, true, 1, 1);
		gd_btnStartServer.verticalIndent = 20;
		gd_btnStartServer.heightHint = 42;
		btnStartServer.setLayoutData(gd_btnStartServer);
		btnStartServer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Util.startBuiltinServerAndConnectAsync(InputAlternativeComposite.this.project);
			}
		});
		
		SpotterClientController.getController(project).addPropertyChangeListener(SpotterClientController.PROP_CONNECTION, connecionListener);

		btnStartServer.setText("Start server");
		
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
			
			Composite c = new Composite(tabFolder, SWT.NONE);
			c.setLayout(new GridLayout(1, true));
			
			insComposite = new Composite(c, SWT.NONE);
			insComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

			
			tabItemInstrumentation.setControl(c);
			tabFolder.setSelection(tabItemInstrumentation);
		}
		//create measurements editor
		{
			CTabItem tabItemMeasurement = new CTabItem(tabFolder, SWT.NONE);
			tabItemMeasurement.setText("Measurements");
			
			Composite c = new Composite(tabFolder, SWT.NONE);
			c.setLayout(new GridLayout(1, true));
			
			meaComposite = new Composite(c, SWT.NONE);
			meaComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			
			tabItemMeasurement.setControl(c);
		}

		updateEditors();
		
		this.addDisposeListener(new DisposeListener()
		{
			@Override
			public void widgetDisposed(DisposeEvent e)
			{
				inputAlternative.unRegisterSpotterEditor(insEditor);
				inputAlternative.unRegisterSpotterEditor(meaEditor);
				SpotterClientController.getController(InputAlternativeComposite.this.project).
					removePropertyChangeListener(SpotterClientController.PROP_CONNECTION, connecionListener);
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
	private Button btnStartServer;
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

			insEditor.init(new DummyEditorSite(), inEditorInput);
			meaEditor.init(new DummyEditorSite(), measurementEditorInput);

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
