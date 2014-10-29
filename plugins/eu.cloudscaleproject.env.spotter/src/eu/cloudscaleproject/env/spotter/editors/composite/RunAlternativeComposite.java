package eu.cloudscaleproject.env.spotter.editors.composite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
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
import org.spotter.eclipse.ui.Activator;
import org.spotter.eclipse.ui.ServiceClientWrapper;
import org.spotter.eclipse.ui.UICoreException;
import org.spotter.eclipse.ui.editors.HierarchyEditor;
import org.spotter.eclipse.ui.editors.SpotterConfigEditor;
import org.spotter.eclipse.ui.editors.WorkloadEditor;
import org.spotter.eclipse.ui.editors.factory.ElementFactory;
import org.spotter.eclipse.ui.jobs.DynamicSpotterRunJob;
import org.spotter.eclipse.ui.util.DialogUtils;
import org.spotter.shared.configuration.FileManager;
import org.spotter.shared.configuration.JobDescription;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.spotter.Util;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class RunAlternativeComposite extends Composite{
	
	public static final String DIALOG_TITLE = "DynamicSpotter Diagnosis";

	private static final String MSG_MISS_CONFIG = "DynamicSpotter Configuration '%s' is missing!";
	private static final String MSG_ALREADY_RUNNING = "DynamicSpotter is already running!";
	private static final String MSG_NO_CONNECTION = "No connection to DynamicSpotter Service!";
	private static final String MSG_RUNTIME_ERROR = "Error occured during diagnosis: %s";
	private static final String MSG_SPOTTER_STARTED = "Going to start DynamicSpotter diagnosis for project '%s' now. Continue?";
	
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
		String selectedInputResourceName = editorInput.getProperty(Util.KEY_SELECTED_INPUT);
		if(selectedInputResourceName != null && !selectedInputResourceName.isEmpty()){
			IEditorInputResource selectedEditorInput = inputResourceProvider.getResource(selectedInputResourceName);
			if(selectedEditorInput != null){
				comboViewer.setSelection(new StructuredSelection(selectedEditorInput));
			}
			else{
				editorInput.setProperty(Util.KEY_SELECTED_INPUT, "");
			}
		}

		comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				IEditorInputResource selectedEditorInput = (IEditorInputResource)selection.getFirstElement();
				editorInput.setProperty(Util.KEY_SELECTED_INPUT, selectedEditorInput.getResource().getName());
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
				SpotterConfigEditor.openInstance(
						(IEditorInput) ElementFactory.createEditorInput(SpotterConfigEditor.ID, file), 
						SpotterConfigEditor.ID);
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
				HierarchyEditor.openInstance(
						(IEditorInput) ElementFactory.createEditorInput(HierarchyEditor.ID, file), 
						HierarchyEditor.ID);
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
				Activator activator = Activator.getDefault();
				IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
				IEditorInputResource selectedEditorInput = getSelectedEditorInput();
				
				if(selectedEditorInput == null){
					//TODO: Display error dialog!
					return;
				}
				
				setInput(selectedEditorInput);
				//set configuration
				IFile fileConf = editorInput.getResource().getFile(FileManager.SPOTTER_CONFIG_FILENAME);
				if(fileConf.exists()){
					Properties prop = new Properties();
					try {
						prop.load(fileConf.getContents());
						
						IPath confEditorInputLocation = editorInput.getResource().getLocation();
						
						String hierarchyPath = confEditorInputLocation.append(FileManager.HIERARCHY_FILENAME).toString();
						String envPath = confEditorInputLocation.append(FileManager.ENVIRONMENT_FILENAME).toString();
						
						//create/retrieve results entry
						IEditorInputResource resultEditorInput;
						IEditorInputResource runEditorInput = RunAlternativeComposite.this.editorInput;
						String runEditorInputName = runEditorInput.getResource().getName();
						
						ResourceProvider resultResourceProvider = ResourceRegistry.getInstance().getResourceProvider(
																	project, ToolchainUtils.SPOTTER_DYN_RES_ID);
						
						if(!resultResourceProvider.hasResource(runEditorInputName)){
							resultEditorInput = resultResourceProvider.createNewResource(
									RunAlternativeComposite.this.editorInput.getResource().getName());
						}
						else{
							resultEditorInput = resultResourceProvider.getResource(runEditorInputName);
						}
						resultEditorInput.setName(RunAlternativeComposite.this.editorInput.getName());
						resultEditorInput.setProperty(Util.KEY_SELECTED_INPUT, selectedEditorInput.getResource().getName());
						resultEditorInput.save();
						
						prop.setProperty("org.spotter.conf.problemHierarchyFile", hierarchyPath);
						prop.setProperty("org.spotter.measurement.environmentDescriptionFile", envPath);
						prop.setProperty("org.spotter.resultDir", resultEditorInput.getResource().getLocation().toString());
						
						File f = fileConf.getLocation().toFile();
						try(OutputStream os = new FileOutputStream(f)){
							prop.store(os, "");
						}
						catch(IOException ex){
							ex.printStackTrace();
						}
					} catch (IOException | CoreException e1) {
						e1.printStackTrace();
					}
				}
				
				try {
					fileConf.refreshLocal(IResource.DEPTH_ZERO, null);
				} catch (CoreException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
				ServiceClientWrapper client = activator.getClient(selectedEditorInput.getResource().getName());
				startSpotterRun(RunAlternativeComposite.this.editorInput, client);
			}
		});
		
		load();
	}
	
	private void setInput(IEditorInputResource ei){
		Util.bindEditorInputs((EditorInputFolder)ei, this.editorInput);
	}
	
	private void startSpotterRun(EditorInputFolder editorInput, ServiceClientWrapper client){
		
		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
		
		IFile spotterFile = editorInput.getResource().getFile(FileManager.SPOTTER_CONFIG_FILENAME);
		String spotterFilePath = spotterFile.getLocation().toString();

		if (!spotterFile.exists()) {
			DialogUtils.openError(DIALOG_TITLE, String.format(MSG_MISS_CONFIG, spotterFilePath));
			return;
		}
		if (client.isRunning(true)) {
			DialogUtils.openWarning(DIALOG_TITLE, MSG_ALREADY_RUNNING);
			return;
		}
		if (client.isConnectionIssue()) {
			DialogUtils.openWarning(DIALOG_TITLE, MSG_NO_CONNECTION);
			return;
		}

		boolean startConfirm = DialogUtils.openConfirm(DIALOG_TITLE,
				String.format(MSG_SPOTTER_STARTED, project.getName()));
		if (startConfirm) {
			
			JobDescription jobDescription;
			try {
				jobDescription = Util.createJobDescription(editorInput);
			} catch (UICoreException e) {
				String message = "Unable to read and parse all configuration files!";
				DialogUtils.handleError(message, e);
				return;
			}
			Long jobId = client.startDiagnosis(jobDescription);
			if (jobId != null && jobId != 0) {
				DynamicSpotterRunJob job = new DynamicSpotterRunJob(project, jobId, System.currentTimeMillis());
				job.schedule();
			} else {
				String msg = String.format(MSG_RUNTIME_ERROR, "Could not retrieve a valid job id!");
				DialogUtils.openError(DIALOG_TITLE, msg);
			}
			
		}
	}
	
	private IEditorInputResource getSelectedEditorInput(){
		String selectedInputResourceName = editorInput.getProperty(Util.KEY_SELECTED_INPUT);
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
