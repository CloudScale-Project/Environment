package eu.cloudscaleproject.env.analyser.editors.composite;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
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

public class ConfigAlternativeEditComposite extends Composite {
	
	private final ResourceProvider inputResourceProvider;
	private final ResourceProvider usageResourceProvider;

	private final ComboViewer comboViewerInput;
	private final ComboViewer comboViewerUsage;
	
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
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ConfigAlternativeEditComposite(final IProject project, final ConfAlternative ca, Composite parent, int style) {
		super(parent, style);
		//this.project = project;
		
		this.inputResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID);
		
		//TODO: Handle situation when usage evolution resource provider does not exist (==null)! 
		this.usageResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.USAGEEVOLUTION_ID);
		
		setLayout(new GridLayout(2, false));
		
		Label lblSelectInput = new Label(this, SWT.NONE);
		lblSelectInput.setText("Select input:");
		{
			comboViewerInput = new ComboViewer(this, SWT.READ_ONLY);
			Combo combo = comboViewerInput.getCombo();
			GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
			gd_combo.minimumWidth = 300;
			combo.setLayoutData(gd_combo);
		}
		
		Label lblSelectUsage = new Label(this, SWT.NONE);
		lblSelectUsage.setText("Select usage evolution:");
		{
			comboViewerUsage = new ComboViewer(this, SWT.READ_ONLY);
			Combo combo = comboViewerUsage.getCombo();
			GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
			gd_combo.minimumWidth = 300;
			combo.setLayoutData(gd_combo);
		}
		
		Button btnRun = new Button(this, SWT.NONE);
		btnRun.setText("Run simulation");
		new Label(this, SWT.NONE);
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
					
					lcwc.launch(ILaunchManager.DEBUG_MODE, null);
				} catch (CoreException e1) {
					// TODO Auto-generated catch block
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
		
		IFolder inputAlt = ca.getFolderResource(ToolchainUtils.KEY_FOLDER_ANALYSER_INPUT_ALT);
		if(inputAlt != null){
			comboViewerInput.setSelection(
					new StructuredSelection(inputResourceProvider.getResource(inputAlt)), 
					true);
		}

		comboViewerInput.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				ca.setInitialModel((EditorInputFolder)selection.getFirstElement());
				ca.save();
			}
		});
		
		//configure usage evolution combo-box
		if(this.usageResourceProvider != null){
			usageResourceProvider.addListener(usageResourceListener);
			
			comboViewerUsage.setInput(usageResourceProvider.getResources());
			
			IFolder ueAl = ca.getFolderResource(ToolchainUtils.KEY_FOLDER_USAGEEVOLUTION_ALT);
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
