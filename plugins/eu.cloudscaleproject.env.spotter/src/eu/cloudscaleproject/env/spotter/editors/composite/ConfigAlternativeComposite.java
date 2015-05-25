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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.spotter.eclipse.ui.editors.HierarchyEditor;
import org.spotter.eclipse.ui.editors.HierarchyEditorInput;
import org.spotter.eclipse.ui.editors.SpotterConfigEditor;
import org.spotter.eclipse.ui.editors.SpotterConfigEditorInput;
import org.spotter.eclipse.ui.editors.WorkloadEditor;
import org.spotter.eclipse.ui.editors.WorkloadEditorInput;
import org.spotter.eclipse.ui.util.DialogUtils;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.spotter.ResourceUtils;
import eu.cloudscaleproject.env.spotter.ServerService;
import eu.cloudscaleproject.env.spotter.alternatives.ConfigAlternative;
import eu.cloudscaleproject.env.spotter.editors.SpotterTabItemExtension;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.ui.ConfigEditorView;

public class ConfigAlternativeComposite extends ConfigEditorView implements IRefreshable, ISelectable{
		
	private final IProject project;
	private final ResourceProvider inputResourceProvider;

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
	
	public ConfigAlternativeComposite(IProject project, Composite parent, int style, final ConfigAlternative editorInput) {
		super(parent, style, editorInput);
		
		this.project = project;
		
		this.confAlternative = editorInput;		
		this.inputResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.SPOTTER_DYN_INPUT_ID);
		
		getContainer().setLayout(new GridLayout(2, false));
		
		Label lblSelectInput = new Label(getContainer(), SWT.NONE);
		lblSelectInput.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSelectInput.setText("Input:");
		
		comboViewer = new ComboViewer(getContainer(), SWT.NONE);
		combo = comboViewer.getCombo();
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 170;
		combo.setLayoutData(gd_combo);

		new Label(getContainer(), SWT.NONE);
		new Label(getContainer(), SWT.NONE);
		
		//composite with tab folder
		Composite composite = new Composite(getContainer(), SWT.NONE);
		composite.setLayout(new FillLayout());
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		initConfigurationComposites(composite);
		
		m_bindingContext = initDataBindings();
	}
	
	private void initConfigurationComposites (Composite container)
	{
		CTabFolder tabFolder = new CTabFolder(container, SWT.BORDER | SWT.FLAT);
		tabFolder.setTabHeight(32);
		//spotter configuration tab
		try {
			CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setText("Confuguration");
			
			confComposite = new Composite(tabFolder, SWT.NONE);
			confComposite.setLayout(new FillLayout());

			confEditor.addPropertyListener(new IPropertyListener() {
				@Override
				public void propertyChanged(Object source, int propId) {
					if(EditorPart.PROP_DIRTY == propId){
						confEditor.doSave(null);
					}
				}
			});
			
			IFile file = confAlternative.getResource().getFile("spotter.conf");

			SpotterConfigEditorInput editorPartInput = new SpotterConfigEditorInput(file);
			confEditor.init(SpotterTabItemExtension.editorPart.getEditorSite(), editorPartInput);
			
			tabItem.setControl(confComposite);
			tabFolder.setSelection(tabItem);
			
		} catch (PartInitException e1) {
			e1.printStackTrace();
		}
		
		//spotter workload editor tab
		try {
			CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setText("Workload");
			
			workComposite = new Composite(tabFolder, SWT.NONE);
			workComposite.setLayout(new FillLayout());
			
			workEditor.addPropertyListener(new IPropertyListener() {
				@Override
				public void propertyChanged(Object source, int propId) {
					if(EditorPart.PROP_DIRTY == propId){
						workEditor.doSave(null);
					}
				}
			});
			
			IFile file = confAlternative.getResource().getFile("mEnv.xml");
			WorkloadEditorInput editorPartInput = new WorkloadEditorInput(file);
			workEditor.init(SpotterTabItemExtension.editorPart.getEditorSite(), editorPartInput);
			
			tabItem.setControl(workComposite);
		} catch (PartInitException e1) {
			e1.printStackTrace();
		}
		
		//spotter hierarchy editor tab
		try {
			CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setText("Hierarchy");
			
			hierComposite = new Composite(tabFolder, SWT.NONE);
			hierComposite.setLayout(new FillLayout());
			
			hierEditor.addPropertyListener(new IPropertyListener() {
				@Override
				public void propertyChanged(Object source, int propId) {
					if(EditorPart.PROP_DIRTY == propId){
						hierEditor.doSave(null);
					}
				}
			});
			
			IFile file = confAlternative.getResource().getFile("hierarchy.xml");
			
			HierarchyEditorInput editorPartInput = new HierarchyEditorInput(file);
			hierEditor.init(SpotterTabItemExtension.editorPart.getEditorSite(), editorPartInput);
			
			tabItem.setControl(hierComposite);
		} catch (PartInitException e1) {
			e1.printStackTrace();
		}
		
		
	}
	
	@Override
	public void refresh() {
		confAlternative.load();
		comboViewer.refresh(true);
		
		for(Control c : confComposite.getChildren()){
			c.dispose();
		}
		for(Control c : workComposite.getChildren()){
			c.dispose();
		}
		for(Control c : hierComposite.getChildren()){
			c.dispose();
		}
		
		confEditor.createPartControl(confComposite);
		workEditor.createPartControl(workComposite);
		hierEditor.createPartControl(hierComposite);
		
		confComposite.layout();
		workComposite.layout();
		hierComposite.layout();

		ResourceProvider resourceProvider = ResourceRegistry.getInstance().getResourceProvider(confAlternative.getProject(),
				ToolchainUtils.SPOTTER_DYN_INPUT_ID);
		
		this.inputAlternatives.clear();
		this.inputAlternatives.addAll(resourceProvider.getResources());
		
		super.update();
		m_bindingContext.updateTargets();
	}

	@Override
	public void onSelect() {
		ValidationDiagramService.showStatus(project, confAlternative.getInputAlternative());
		ValidationDiagramService.showStatus(project, confAlternative);

		if (confAlternative.getLastResult() != null)
			ValidationDiagramService.showStatus(confAlternative.getProject(), confAlternative.getLastResult());
		else
			ValidationDiagramService.clearStatus(confAlternative.getProject(), ToolchainUtils.EXTRACTOR_RES_ID);
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
