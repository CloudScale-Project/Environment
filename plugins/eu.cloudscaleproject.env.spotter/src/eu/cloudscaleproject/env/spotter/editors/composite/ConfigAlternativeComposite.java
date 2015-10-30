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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PartInitException;
import org.spotter.eclipse.ui.editors.HierarchyEditor;
import org.spotter.eclipse.ui.editors.HierarchyEditorInput;
import org.spotter.eclipse.ui.editors.SpotterConfigEditor;
import org.spotter.eclipse.ui.editors.SpotterConfigEditorInput;
import org.spotter.eclipse.ui.editors.WorkloadEditor;
import org.spotter.eclipse.ui.editors.WorkloadEditorInput;

import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.spotter.SpotterClientController;
import eu.cloudscaleproject.env.spotter.Util;
import eu.cloudscaleproject.env.spotter.alternatives.ConfigAlternative;
import eu.cloudscaleproject.env.toolchain.ui.ConfigEditorView;

public class ConfigAlternativeComposite extends ConfigEditorView implements IRefreshable, ISelectable{
		
	private final IProject project;

	private final ConfigAlternative confAlternative;
		
	private Composite confComposite;
	private Composite workComposite;
	private Composite hierComposite;

	PropertyChangeListener connecionListener = new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent evt)
			{
				if (ConfigAlternativeComposite.this.isDisposed()) return;

				Display.getDefault().asyncExec(new Runnable() { @Override public void run() { refresh(); } });
			}
		};
	
	private SpotterConfigEditor confEditor = new SpotterConfigEditor(){
		public String getContentDescription() {return "";};
		protected void setContentDescription(String description) {};
	};
	private WorkloadEditor workEditor = new WorkloadEditor(){
		public String getContentDescription() {return "";};
		protected void setContentDescription(String description) {};
	};
	private HierarchyEditor hierEditor = new HierarchyEditor(){
		public String getContentDescription() {return "";};
		protected void setContentDescription(String description) {};
	};
	private Composite warningComposite;

	private Composite mainComposite;
	
	public ConfigAlternativeComposite(IProject project, Composite parent, int style, final ConfigAlternative editorInput) {
		super(parent, style, editorInput);
		
		this.project = project;
		
		this.confAlternative = editorInput;		
		
		getContainer().setLayout(new StackLayout());
		
		warningComposite = new Composite(getContainer(), SWT.BORDER);
		warningComposite.setLayout(new GridLayout(1, false));
		Label lblEmpty = new Label(warningComposite, SWT.CENTER);
		lblEmpty.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, true, true, 1, 1));
		lblEmpty.setText("Spotter client not connected... \nTo enable editing, go to Server section and connect Spotter client.");

		Button btnStartServer = new Button(warningComposite, SWT.NONE);
		GridData gd_btnStartServer = new GridData(SWT.CENTER, SWT.TOP, true, true, 1, 1);
		gd_btnStartServer.verticalIndent = 20;
		gd_btnStartServer.heightHint = 42;
		btnStartServer.setLayoutData(gd_btnStartServer);
		btnStartServer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Util.startBuiltinServerAndConnectAsync(ConfigAlternativeComposite.this.project);
			}
		});
		
		SpotterClientController.getController(project).addPropertyChangeListener(SpotterClientController.PROP_CONNECTION, connecionListener);

		btnStartServer.setText("Start server");
		
		mainComposite = new Composite(getContainer(), SWT.NONE);
		mainComposite.setLayout(new GridLayout(2, false));
		
		//composite with tab folder
		Composite composite = new Composite(mainComposite, SWT.NONE);
		composite.setLayout(new FillLayout());
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		initConfigurationComposites(composite);
		updateEditors();

		this.addDisposeListener(new DisposeListener()
		{
			@Override
			public void widgetDisposed(DisposeEvent e)
			{
				editorInput.unRegisterSpotterEditor(confEditor);
				editorInput.unRegisterSpotterEditor(workEditor);
				editorInput.unRegisterSpotterEditor(hierEditor);
				SpotterClientController.getController(ConfigAlternativeComposite.this.project).
					removePropertyChangeListener(SpotterClientController.PROP_CONNECTION, connecionListener);
			}
		});
	}
	
	private void initConfigurationComposites (Composite container)
	{
		CTabFolder tabFolder = new CTabFolder(container, SWT.BORDER | SWT.FLAT);
		tabFolder.setTabHeight(32);
		//spotter configuration tab
		{
			CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setText("Configuration");
			
			Composite c = new Composite(tabFolder, SWT.NONE);
			c.setLayout(new GridLayout(1, true));
			
			confComposite = new Composite(c, SWT.NONE);
			confComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			
			tabItem.setControl(c);
			tabFolder.setSelection(tabItem);
		} 		
		//spotter workload editor tab
		{
			CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setText("Workload");

			Composite c = new Composite(tabFolder, SWT.NONE);
			c.setLayout(new GridLayout(1, true));
			
			workComposite = new Composite(c, SWT.NONE);
			workComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			
			tabItem.setControl(c);
		}		

		//spotter hierarchy editor tab
		{
			CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setText("Hierarchy");
			
			Composite c = new Composite(tabFolder, SWT.NONE);
			c.setLayout(new GridLayout(1, true));
			
			hierComposite = new Composite(c, SWT.NONE);
			hierComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			
			tabItem.setControl(c);
		}		
		
	}
	
	@Override
	public void refresh() {
		confAlternative.validate();
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
			{
			IFile file = confAlternative.getResource().getFile("spotter.conf");
			SpotterConfigEditorInput editorPartInput = new SpotterConfigEditorInput(file);
			confEditor.init(new DummyEditorSite(), editorPartInput);
			confEditor.createPartControl(confComposite);
			confComposite.layout();
			}

			{
			IFile file = confAlternative.getResource().getFile("mEnv.xml");
			WorkloadEditorInput editorPartInput = new WorkloadEditorInput(file);
			workEditor.init(new DummyEditorSite(), editorPartInput);
			workEditor.createPartControl(workComposite);
			workComposite.layout();
			}

			{
			IFile file = confAlternative.getResource().getFile("hierarchy.xml");
			HierarchyEditorInput editorPartInput = new HierarchyEditorInput(file);
			hierEditor.init(new DummyEditorSite(), editorPartInput);
			hierEditor.createPartControl(hierComposite);
			hierComposite.layout();
			}
			
			confAlternative.registerSpotterEditor(confEditor);
			confAlternative.registerSpotterEditor(workEditor);
			confAlternative.registerSpotterEditor(hierEditor);
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
