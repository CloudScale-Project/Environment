package eu.cloudscaleproject.env.spotter.editors.composite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
import org.eclipse.swt.widgets.Text;
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
import eu.cloudscaleproject.env.spotter.ResourceUtils;
import eu.cloudscaleproject.env.spotter.ServerService;
import eu.cloudscaleproject.env.spotter.editors.SpotterTabItemExtension;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class RunAlternativeComposite extends Composite{
		
	private final IProject project;
	private final EditorInputFolder editorInput;
	private final ResourceProvider inputResourceProvider;
	
	private Text textName;
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
	
	public RunAlternativeComposite(Composite parent, int style, final EditorInputFolder editorInput) {
		super(parent, style);
		
		this.project = editorInput.getResource().getProject();
		this.editorInput = editorInput;		
		this.inputResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.SPOTTER_DYN_INPUT_ID);
		
		setLayout(new GridLayout(2, false));
		
		Label lblName = new Label(this, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblName.setText("Name:");
		
		textName = new Text(this, SWT.BORDER);
		textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				RunAlternativeComposite.this.editorInput.setName(textName.getText());
				RunAlternativeComposite.this.editorInput.save();
			}
		});
		
		Label lblSelectInput = new Label(this, SWT.NONE);
		lblSelectInput.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSelectInput.setText("Select input:");
		
		//combo viewer
		comboViewer = new ComboViewer(this, SWT.READ_ONLY);
		Combo combo = comboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboViewer.setContentProvider(ArrayContentProvider.getInstance());
		comboViewer.setLabelProvider(new LabelProvider() {
			
			@Override
			public String getText(Object element) {
				if (element instanceof eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput) {
					eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput ia = 
							(eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput) element;
					return ia.getName();
				}
				return super.getText(element);
			}
			
		});
		comboViewer.setInput(inputResourceProvider.getResources());
		String selectedInputResourceName = editorInput.getProperty(ResourceUtils.KEY_PARENT_EDITOR_RESOURCE);
		if(selectedInputResourceName != null && !selectedInputResourceName.isEmpty()){
			IEditorInputResource selectedEditorInput = inputResourceProvider.getResource(selectedInputResourceName);
			if(selectedEditorInput != null){
				comboViewer.setSelection(new StructuredSelection(selectedEditorInput));
			}
			else{
				editorInput.setProperty(ResourceUtils.KEY_PARENT_EDITOR_RESOURCE, "");
			}
		}

		comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				IEditorInputResource selectedEditorInput = (IEditorInputResource)selection.getFirstElement();
				editorInput.setProperty(ResourceUtils.KEY_PARENT_EDITOR_RESOURCE, selectedEditorInput.getResource().getName());
				editorInput.save();
				
				setInput(selectedEditorInput);
			}
		});
		
		//composite with tab folder
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		composite.setLayoutData(gd_composite);
		
		CTabFolder tabFolder = new CTabFolder(composite, SWT.BORDER | SWT.FLAT);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		//tabFolder.setTabHeight(10);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
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
			
			IFile file = editorInput.getResource().getFile("spotter.conf");
			
			if(!file.exists()){
				ResourceUtils.createDefaultFile(file,
						"org.spotter.workload.experiment.cooldown.intervalLength=1" +
						"org.spotter.measurement.environmentDescriptionFile=/home/vito/programs_projects/runtime-product.product/tpcw/ Dynamic Spotter/Configuration/Alternative/mEnv.xml" +
						"org.spotter.resultDir=/home/vito/programs_projects/runtime-product.product/tpcw/ Dynamic Spotter/Results/Alternative" +
						"org.spotter.conf.problemHierarchyFile=/home/vito/programs_projects/runtime-product.product/tpcw/ Dynamic Spotter/Configuration/Alternative/hierarchy.xml" +
						"org.spotter.workload.maxusers=10" +
						"org.spotter.workload.experiment.rampup.numUsersPerInterval=1" +
						"org.spotter.workload.experiment.cooldown.numUsersPerInterval=1" +
						"org.spotter.workload.experiment.duration=10" +
						"org.spotter.workload.experiment.rampup.intervalLength=1"
						);
			}
			
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
			
			IFile file = editorInput.getResource().getFile("mEnv.xml");
			
			if(!file.exists()){
				ResourceUtils.createDefaultFile(file,
						"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
						"<measurementEnvironment xmlns=\"org.spotter.shared.environment.model\">" +
				    		"<workloadAdapter>" +
				    		"<extensionName>workload.satellite.adapter.customized</extensionName>" +
				    		"<config key=\"org.spotter.satellite.adapter.name\" value=\"Customized Workload Satellite Adapter\"/>" +
				    		"<config key=\"org.spotter.workload.simple.userScriptClassName\" value=\"\"/>" +
				    		"<config key=\"org.spotter.workload.simple.userScriptPath\" value=\"\"/>" +
				    		"</workloadAdapter>" +
				    	"</measurementEnvironment>"
				);
			}
			
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
			
			IFile file = editorInput.getResource().getFile("hierarchy.xml");
			
			if(!file.exists()){
				ResourceUtils.createDefaultFile(file,
						"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
						"<root xmlns=\"http://www.sopeco.org/PerformanceProblemHierarchySchema\">" +
						"</root>"
				);
			}
			
			HierarchyEditorInput editorPartInput = new HierarchyEditorInput(file);
			hierEditor.init(SpotterTabItemExtension.editorPart.getEditorSite(), editorPartInput);
			
			tabItem.setControl(hierComposite);
		} catch (PartInitException e1) {
			e1.printStackTrace();
		}
		
		Button btnRunDynamicSpotter = new Button(this, SWT.CENTER);
		btnRunDynamicSpotter.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false, false, 2, 1));
		btnRunDynamicSpotter.setText("Run DynamicSpotter...");
		btnRunDynamicSpotter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
				IEditorInputResource selectedEditorInput = getSelectedEditorInput();
				
				if(selectedEditorInput == null){
					DialogUtils.openError("DynamicSpotter Diagnosis", "Input alternative missing!");
					return;
				}
				
				setInput(selectedEditorInput);
				ServerService.getInstance().runSimulation(project, RunAlternativeComposite.this.editorInput);
			}
		});
		
		load();
	}
	
	private void setInput(IEditorInputResource ei){
		ResourceUtils.bindEditorInputs((EditorInputFolder)ei, this.editorInput);
	}
	
	private IEditorInputResource getSelectedEditorInput(){
		String selectedInputResourceName = editorInput.getProperty(ResourceUtils.KEY_PARENT_EDITOR_RESOURCE);
		if(selectedInputResourceName != null){
			IEditorInputResource selectedEditorInput = inputResourceProvider.getResource(selectedInputResourceName);
			return selectedEditorInput;
		}
		return null;
	}
	
	public void load(){
		String name = editorInput.getName();
		textName.setText(name != null ? name : "");
	}
	
	@Override
	public void update() {
		editorInput.load();
		load();
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
		
		super.update();
	}
}
