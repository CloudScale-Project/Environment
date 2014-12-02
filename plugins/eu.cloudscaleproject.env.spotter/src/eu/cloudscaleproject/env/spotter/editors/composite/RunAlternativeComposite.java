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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.spotter.eclipse.ui.editors.AbstractSpotterEditor;
import org.spotter.eclipse.ui.editors.HierarchyEditor;
import org.spotter.eclipse.ui.editors.HierarchyEditorInput;
import org.spotter.eclipse.ui.editors.SpotterConfigEditor;
import org.spotter.eclipse.ui.editors.SpotterConfigEditorInput;
import org.spotter.eclipse.ui.editors.WorkloadEditor;
import org.spotter.eclipse.ui.editors.factory.ElementFactory;
import org.spotter.eclipse.ui.util.DialogUtils;
import org.spotter.shared.configuration.FileManager;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.spotter.ResourceUtils;
import eu.cloudscaleproject.env.spotter.ServerService;
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
		//
		
		Label lblConfigurationEditors = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblConfigurationEditors.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		lblConfigurationEditors.setText("Configuration Editors:");
		
		Button btnOpenSpotterConfiguration = new Button(this, SWT.NONE);
		btnOpenSpotterConfiguration.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		btnOpenSpotterConfiguration.setText("Open Spotter Configuration Editor...");
		btnOpenSpotterConfiguration.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IFile file = RunAlternativeComposite.this.editorInput
						.getResource().getFile(FileManager.SPOTTER_CONFIG_FILENAME);
				AbstractSpotterEditor.openInstance(new SpotterConfigEditorInput(file), SpotterConfigEditor.ID);
			}
		});
		
		Button btnOpenHierarchy = new Button(this, SWT.NONE);
		btnOpenHierarchy.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		btnOpenHierarchy.setText("Open Hierarchy Editor...");
		btnOpenHierarchy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IFile file = RunAlternativeComposite.this.editorInput
						.getResource().getFile(FileManager.HIERARCHY_FILENAME);
				AbstractSpotterEditor.openInstance(new HierarchyEditorInput(file), HierarchyEditor.ID);
			}
		});
		
		Button btnOpenWorkloadSatellite = new Button(this, SWT.NONE);
		btnOpenWorkloadSatellite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		btnOpenWorkloadSatellite.setText("Open Workload Satellite Adapter Editor...");
		btnOpenWorkloadSatellite.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IFile file = RunAlternativeComposite.this.editorInput
						.getResource().getFile(FileManager.ENVIRONMENT_FILENAME);
				WorkloadEditor.openInstance(
						(IEditorInput) ElementFactory.createEditorInput(WorkloadEditor.ID, file), 
						WorkloadEditor.ID);
			}
		});
		
		Label lblRunActions = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd_lblRunActions = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_lblRunActions.widthHint = 78;
		lblRunActions.setLayoutData(gd_lblRunActions);
		lblRunActions.setText("Run actions:");
		new Label(this, SWT.NONE);
		
		Button btnRunDynamicSpotter = new Button(this, SWT.CENTER);
		btnRunDynamicSpotter.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, true, 1, 1));
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
		super.update();
	}
}
