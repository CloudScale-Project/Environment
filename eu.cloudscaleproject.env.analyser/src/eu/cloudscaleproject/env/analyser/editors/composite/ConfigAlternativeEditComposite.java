package eu.cloudscaleproject.env.analyser.editors.composite;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import eu.cloudscaleproject.env.analyser.AnalyserUtil;
import eu.cloudscaleproject.env.analyser.ConfAlternative;
import eu.cloudscaleproject.env.analyser.InputAlternative;

public class ConfigAlternativeEditComposite extends Composite {
	
	private final IProject project;
	private final Map<IResource, InputAlternative> inputAlternatives = new HashMap<IResource, InputAlternative>();
	
	private final ComboViewer comboViewer;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ConfigAlternativeEditComposite(final IProject project, final ConfAlternative ca, Composite parent, int style) {
		super(parent, style);
		this.project = project;
		
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

		updateInput();
		comboViewer.setInput(inputAlternatives.values());
		if(ca.getInput() != null){
			//TODO: find out why the selection is not set!
			comboViewer.setSelection(new StructuredSelection(inputAlternatives.get(ca.getInput().getResource())), true);
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
		
	public void updateInput(){
		
		List<InputAlternative> tmp = AnalyserUtil.getInputAlternatives(project);
		
		HashMap<IResource, InputAlternative> alternatives = new HashMap<IResource, InputAlternative>();
		for(InputAlternative ia : tmp){
			alternatives.put(ia.getResource(), ia);
		}
		
		//add missing
		for(InputAlternative ia : alternatives.values()){
			if(!inputAlternatives.containsKey(ia.getResource())){
				inputAlternatives.put(ia.getResource(), ia);
				comboViewer.add(ia);
			}
		}
		
		//remove removed
		Iterator<InputAlternative> iter = inputAlternatives.values().iterator();
		while(iter.hasNext()){
			InputAlternative ia = (InputAlternative)iter.next();
			if(!alternatives.containsKey(ia.getResource())){
				iter.remove();
				comboViewer.remove(ia);
			}
		}
	}
	
	@Override
	public void update() {
		
		if(!isDisposed()){
			updateInput();
		}
		super.update();
	}

	@Override
	protected void checkSubclass() {
	}
}
