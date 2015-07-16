package eu.cloudscaleproject.env.spotter.editors.composite;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
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
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.spotter.SpotterClientController;
import eu.cloudscaleproject.env.spotter.alternatives.ConfigAlternative;
import eu.cloudscaleproject.env.spotter.editors.SpotterTabItemExtension;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.ui.ConfigEditorView;

public class ConfigAlternativeComposite extends ConfigEditorView implements IRefreshable, ISelectable{
		
	private final IProject project;

	private final ConfigAlternative confAlternative;
		
	private final ComboViewer comboViewer;
		
	private Composite confComposite;
	private Composite workComposite;
	private Composite hierComposite;
	
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
	private Combo combo;
	private DataBindingContext m_bindingContext;

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
		lblEmpty.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		lblEmpty.setText("Spotter client not connected... \nTo enable editing, go to Server section and connect Spotter client.");
		
		mainComposite = new Composite(getContainer(), SWT.NONE);
		mainComposite.setLayout(new GridLayout(2, false));
		
		Label lblSelectInput = new Label(mainComposite, SWT.NONE);
		lblSelectInput.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSelectInput.setText("Input:");
		
		comboViewer = new ComboViewer(mainComposite, SWT.NONE);
		combo = comboViewer.getCombo();
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 170;
		combo.setLayoutData(gd_combo);

		new Label(mainComposite, SWT.NONE);
		new Label(mainComposite, SWT.NONE);
		
		//composite with tab folder
		Composite composite = new Composite(mainComposite, SWT.NONE);
		composite.setLayout(new FillLayout());
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		initConfigurationComposites(composite);
		updateEditors();
		
		m_bindingContext = initDataBindings();
	}
	
	private void initConfigurationComposites (Composite container)
	{
		CTabFolder tabFolder = new CTabFolder(container, SWT.BORDER | SWT.FLAT);
		tabFolder.setTabHeight(32);
		//spotter configuration tab
		{
			CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setText("Configuration");
			
			confComposite = new Composite(tabFolder, SWT.NONE);
			confComposite.setLayout(new FillLayout());
			
			tabItem.setControl(confComposite);
			tabFolder.setSelection(tabItem);
		} 		
		//spotter workload editor tab
		{
			CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setText("Workload");
			
			workComposite = new Composite(tabFolder, SWT.NONE);
			workComposite.setLayout(new FillLayout());
			
			tabItem.setControl(workComposite);
		}		

		//spotter hierarchy editor tab
		{
			CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setText("Hierarchy");
			
			hierComposite = new Composite(tabFolder, SWT.NONE);
			hierComposite.setLayout(new FillLayout());
			
			tabItem.setControl(hierComposite);
		}		
		
	}
	
	@Override
	public void refresh() {

		ResourceProvider resourceProvider = ResourceRegistry.getInstance().getResourceProvider(confAlternative.getProject(),
				CSTool.SPOTTER_DYN_INPUT);

		this.inputAlternatives.clear();
		this.inputAlternatives.addAll(resourceProvider.getResources());
		comboViewer.refresh(true);

		m_bindingContext.updateTargets();
		
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
			confEditor.init(SpotterTabItemExtension.editorPart.getEditorSite(), editorPartInput);
			confEditor.createPartControl(confComposite);
			confComposite.layout();
			}

			{
			IFile file = confAlternative.getResource().getFile("mEnv.xml");
			WorkloadEditorInput editorPartInput = new WorkloadEditorInput(file);
			workEditor.init(SpotterTabItemExtension.editorPart.getEditorSite(), editorPartInput);
			workEditor.createPartControl(workComposite);
			workComposite.layout();
			}

			{
			IFile file = confAlternative.getResource().getFile("hierarchy.xml");
			HierarchyEditorInput editorPartInput = new HierarchyEditorInput(file);
			hierEditor.init(SpotterTabItemExtension.editorPart.getEditorSite(), editorPartInput);
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
		ValidationDiagramService.showStatus(project, CSTool.SPOTTER_DYN_INPUT.getID(), confAlternative.getInputAlternative());
		ValidationDiagramService.showStatus(project, CSTool.SPOTTER_DYN_CONF.getID(), confAlternative);
		ValidationDiagramService.showStatus(project, CSTool.SPOTTER_DYN_RES.getID(), confAlternative.getLastResult());
	}


	private WritableList inputAlternatives = new WritableList();
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap observeMap = BeansObservables.observeMap(listContentProvider.getKnownElements(), IEditorInputResource.class, "name");
		comboViewer.setLabelProvider(new ObservableMapLabelProvider(observeMap));
		comboViewer.setContentProvider(listContentProvider);
		//
		comboViewer.setInput(inputAlternatives);
		//
		IObservableValue observeSingleSelectionComboViewer = ViewerProperties.singleSelection().observe(comboViewer);
		IObservableValue extractorResultConfigPersistenceFolderObserveValue = BeanProperties.value("inputAlternative").observe(confAlternative);
		bindingContext.bindValue(observeSingleSelectionComboViewer, extractorResultConfigPersistenceFolderObserveValue, null, null);
		//
		return bindingContext;
	}

}
