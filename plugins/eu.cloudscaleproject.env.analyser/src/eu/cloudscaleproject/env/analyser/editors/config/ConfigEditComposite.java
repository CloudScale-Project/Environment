package eu.cloudscaleproject.env.analyser.editors.config;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;

import org.eclipse.swt.widgets.ProgressBar;

public class ConfigEditComposite extends Composite{
	
	private final ResourceProvider inputResourceProvider;
	private final ResourceProvider usageResourceProvider;

	private final ComboViewer comboViewerInput;
	private final ComboViewer comboViewerUsage;
	
	private final Button btnRun;
	private final Button btnStop;
	private final ProgressBar progressBar;
	
	private final PropertyChangeListener inputResourceListener = new PropertyChangeListener() {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(ResourceProvider.PROP_RESOURCE_ADDED.equals(evt.getPropertyName())){
				comboViewerInput.add(evt.getNewValue());
			}
			else if(ResourceProvider.PROP_RESOURCE_REMOVED.equals(evt.getPropertyName())){
				comboViewerInput.remove(evt.getOldValue());
			}
		}
	};
	
	private final PropertyChangeListener usageResourceListener = new PropertyChangeListener() {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(ResourceProvider.PROP_RESOURCE_ADDED.equals(evt.getPropertyName())){
				comboViewerUsage.add(evt.getNewValue());
			}
			else if(ResourceProvider.PROP_RESOURCE_REMOVED.equals(evt.getPropertyName())){
				comboViewerUsage.remove(evt.getOldValue());
			}
		}
		
	};
	
	private final IProgressMonitor progress = new IProgressMonitor() {

		private boolean isCanceled = false;
		
		@Override
		public void worked(int work) {
			progressBar.setSelection(work);
		}

		@Override
		public void subTask(String name) {
		}

		@Override
		public void setTaskName(String name) {
		}

		@Override
		public void setCanceled(boolean value) {
			isCanceled = value;
		}

		@Override
		public boolean isCanceled() {
			return isCanceled;
		}

		@Override
		public void internalWorked(double work) {
		}

		@Override
		public void done() {
			progressBar.setSelection(0);
			progressBar.setEnabled(false);
			
			btnRun.setEnabled(true);
			btnStop.setEnabled(false);
		}

		@Override
		public void beginTask(String name, int totalWork) {
			progressBar.setEnabled(true);
			progressBar.setMinimum(0);
			btnRun.setEnabled(false);
			btnStop.setEnabled(true);
			progressBar.setMaximum(totalWork);
		}
	};
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ConfigEditComposite(final IProject project, final ConfAlternative ca, Composite parent, int style) {
		super(parent, style);
		
		this.inputResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID);
		
		//TODO: Handle situation when usage evolution resource provider does not exist (==null)! 
		this.usageResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.USAGEEVOLUTION_ID);
		
		setLayout(new GridLayout(3, false));
		
		Label lblSelectInput = new Label(this, SWT.NONE);
		lblSelectInput.setText("Select input:");
		new Label(this, SWT.NONE);
		{
			comboViewerInput = new ComboViewer(this, SWT.READ_ONLY);
			Combo combo = comboViewerInput.getCombo();
			GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
			gd_combo.minimumWidth = 300;
			combo.setLayoutData(gd_combo);
		}
		
		Label lblSelectUsage = new Label(this, SWT.NONE);
		lblSelectUsage.setText("Select usage evolution:");
		new Label(this, SWT.NONE);
		{
			comboViewerUsage = new ComboViewer(this, SWT.READ_ONLY);
			Combo combo = comboViewerUsage.getCombo();
			GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
			gd_combo.minimumWidth = 300;
			combo.setLayoutData(gd_combo);
		}
		
		btnRun = new Button(this, SWT.NONE);
		btnRun.setText("Run simulation");
		
		btnStop = new Button(this, SWT.NONE);
		btnStop.setText("Stop");
		btnStop.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				progress.setCanceled(true);
			}
		});
		
		progressBar = new ProgressBar(this, SWT.NONE);
		progressBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnRun.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				//run simulation
				ILaunchManager mgr = DebugPlugin.getDefault().getLaunchManager();
				ILaunchConfigurationType lct = mgr.getLaunchConfigurationType("org.palladiosimulator.experimentautomation.application.launchConfigurationType");
				
				try {
					//System.out.println(ca.getExperiments().getURI().toString());
					ILaunchConfigurationWorkingCopy lcwc = lct.newInstance((IFolder)ca.getResource(), ca.getResource().getName());
					lcwc.setAttribute("Experiment Automation", ca.getExperiment().eResource().getURI().toString());
					lcwc.setAttribute("de.uka.ipd.sdq.workflowengine.debuglevel", 2);
					lcwc.setAttribute("outpath", "org.palladiosimulator.temporary");
					lcwc.doSave();
					
					progress.setCanceled(false);
					lcwc.launch(ILaunchManager.DEBUG_MODE, progress);
				} catch (CoreException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		comboViewerInput.setContentProvider(ArrayContentProvider.getInstance());
		comboViewerInput.setLabelProvider(new LabelProvider() {
			
			@Override
			public String getText(Object element) {
				if (element instanceof InputAlternative) {
					InputAlternative ia = (InputAlternative) element;
					return ia.getName();
				}
				return super.getText(element);
			}
			
		});
		
		comboViewerUsage.setContentProvider(ArrayContentProvider.getInstance());
		comboViewerUsage.setLabelProvider(new LabelProvider() {
			
			@Override
			public String getText(Object element) {
				if (element instanceof IEditorInput) {
					return ((IEditorInput) element).getName();
				}
				return super.getText(element);
			}
			
		});
		
		//configure input alternative combo-box
		inputResourceProvider.addListener(inputResourceListener);
		
		comboViewerInput.setInput(inputResourceProvider.getResources());
		
		IFolder inputAlt = (IFolder)ca.getSubResource(ToolchainUtils.KEY_FOLDER_ANALYSER_INPUT_ALT);
		if(inputAlt != null){
			comboViewerInput.setSelection(
					new StructuredSelection(inputResourceProvider.getResource(inputAlt)), 
					true);
		}

		comboViewerInput.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				ca.setInitialModel((InputAlternative)selection.getFirstElement());
			}
		});
		
		//configure usage evolution combo-box
		if(this.usageResourceProvider != null){
			usageResourceProvider.addListener(usageResourceListener);
			
			comboViewerUsage.setInput(usageResourceProvider.getResources());
			
			IFolder ueAl = (IFolder)ca.getSubResource(ToolchainUtils.KEY_FOLDER_USAGEEVOLUTION_ALT);
			if(ueAl != null){
				comboViewerUsage.setSelection(
						new StructuredSelection(usageResourceProvider.getResource(ueAl)), true);
			}
			
			comboViewerUsage.addSelectionChangedListener(new ISelectionChangedListener() {
				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					IStructuredSelection selection = (IStructuredSelection)event.getSelection();
					ca.setUsageEvolution((EditorInputFolder)selection.getFirstElement());
					ca.save();
				}
			});
			
			comboViewerUsage.getCombo().setEnabled(true);
		}
		else {
			comboViewerUsage.getCombo().setEnabled(false);
		}
		
		//on dispose
		addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				inputResourceProvider.removeListener(inputResourceListener);
				if(usageResourceProvider != null){
					usageResourceProvider.removeListener(usageResourceListener);
				}
			}
		});
	}
	
	@Override
	public void update() {
		comboViewerInput.refresh(true);
		comboViewerUsage.refresh(true);
		super.update();
	}

	@Override
	protected void checkSubclass() {
	}
}
