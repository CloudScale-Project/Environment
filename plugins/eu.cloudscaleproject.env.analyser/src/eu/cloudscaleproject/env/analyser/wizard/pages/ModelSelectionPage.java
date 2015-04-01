package eu.cloudscaleproject.env.analyser.wizard.pages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.analyser.PCMModelType;
import eu.cloudscaleproject.env.analyser.PCMResourceSet;

public class ModelSelectionPage extends WizardPage{
	
	private List<PCMModelType> selectedModels = new ArrayList<PCMModelType>();
	
	private AdapterFactory adapterFactory = null;
	
	private PCMModelType[] types;
	private Resource resource = new ResourceImpl();
	
	public ModelSelectionPage(String title, AdapterFactory adapterFacoty, PCMModelType[] types) {
		super(title);

		this.types = types;
		this.adapterFactory = adapterFacoty;
	}
	
	public PCMModelType[] getSelectedModelTypes(){
		return selectedModels.toArray(new PCMModelType[selectedModels.size()]);
	}

	@Override
	public void createControl(Composite parent) {
		
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());
		
		for(int i=0; i<types.length; i++){
			EObject rootObject = PCMResourceSet.createModelRootObject(types[i]);
			resource.getContents().add(rootObject);
		}
				
		final CheckboxTableViewer tableView = 
				CheckboxTableViewer.newCheckList(container, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
		tableView.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		tableView.setLabelProvider(new org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider(
				new AdapterFactoryLabelProvider.StyledLabelProvider(adapterFactory, tableView)));

		tableView.setInput(resource);
		
		tableView.addCheckStateListener(new ICheckStateListener() {
			
			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				Object[] selection = tableView.getCheckedElements();
				
				selectedModels.clear();
				
				for(int i=0; i<selection.length; i++){
					Object o = selection[i];
					
					PCMModelType type = types[resource.getContents().indexOf(o)];
					selectedModels.add(type);
				}				
			}
		});
		
		setControl(container);
	}
	
}