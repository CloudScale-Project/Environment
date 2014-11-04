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

import eu.cloudscaleproject.env.analyser.ConfAlternative;
import eu.cloudscaleproject.env.analyser.InputAlternative;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

public class ConfigAlternativeEditComposite extends Composite {
	
	private final ResourceProvider inputResources;
	private final ComboViewer comboViewer;
	
	private final PropertyChangeListener inputResourceListener = new PropertyChangeListener() {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(ResourceProvider.PROP_RESOURCE_ADDED.equals(evt.getPropertyName())){
				comboViewer.add(evt.getNewValue());
			}
			else if(ResourceProvider.PROP_RESOURCE_REMOVED.equals(evt.getPropertyName())){
				comboViewer.remove(evt.getOldValue());
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
		
		this.inputResources = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID);
		
		setLayout(new GridLayout(3, false));
		
		Label lblSelectInput = new Label(this, SWT.NONE);
		lblSelectInput.setText("Select input:");

		comboViewer = new ComboViewer(this, SWT.READ_ONLY);
		Combo combo = comboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Button btnRun = new Button(this, SWT.NONE);
		btnRun.setText("Run");
		btnRun.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				//run simulation
				ILaunchManager mgr = DebugPlugin.getDefault().getLaunchManager();
				ILaunchConfigurationType lct = mgr.getLaunchConfigurationType("org.palladiosimulator.experimentautomation.application.launchConfigurationType");
				
				try {
					//System.out.println(ca.getExperiments().getURI().toString());
					ILaunchConfigurationWorkingCopy lcwc = lct.newInstance((IFolder)ca.getResource(), ca.getResource().getName());
					lcwc.setAttribute("Experiment Automation", ca.getExperiments().getURI().toString());
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
		
		comboViewer.setContentProvider(ArrayContentProvider.getInstance());
		comboViewer.setLabelProvider(new LabelProvider() {
			
			@Override
			public String getText(Object element) {
				if (element instanceof InputAlternative) {
					InputAlternative ia = (InputAlternative) element;
					return ia.getName();
				}
				return super.getText(element);
			}
			
		});
		
		//init listeners
		inputResources.addListener(inputResourceListener);
		addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				inputResources.removeListener(inputResourceListener);
			}
		});
		
		//init combo viewer
		comboViewer.setInput(inputResources.getResources());
		if(ca.getInput() != null){
			comboViewer.setSelection(new StructuredSelection(inputResources.getResource(ca.getInput().getResource().getName())), true);
		}

		comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				ca.setInput((InputAlternative)selection.getFirstElement());
				ca.save();
			}
		});
	}

	@Override
	protected void checkSubclass() {
	}
}
