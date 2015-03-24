package eu.cloudscaleproject.env.analyser.wizard.pages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import eu.cloudscaleproject.env.analyser.PCMModelType;
import eu.cloudscaleproject.env.analyser.PCMResourceSet;

public class ModelSelectionPage extends WizardPage{
	
	private List<PCMModelType> selectedModels = new ArrayList<PCMModelType>();
	
	private AdapterFactory adapterFactory = null;
	private ResourceSet resSet = null;
	private PCMModelType[] types;
	
	public ModelSelectionPage(String title, AdapterFactory adapterFacoty, PCMModelType[] types) {
		this(title, null, adapterFacoty, types);
	}
	
	public ModelSelectionPage(String title, ResourceSet resSet, AdapterFactory adapterFacoty, PCMModelType[] types) {
		super(title);
		
		this.resSet = resSet;
		this.types = types;
		this.adapterFactory = adapterFacoty;
	}
	
	public PCMModelType[] getSelectedModelTypes(){
		return selectedModels.toArray(new PCMModelType[selectedModels.size()]);
	}

	@Override
	public void createControl(Composite parent) {
		
		GridLayout gridLayout = new GridLayout(2, false);
		parent.setLayout(gridLayout);

		Label lblNewAlternativeName = new Label(parent, SWT.NONE);
		lblNewAlternativeName.setText("New alternative name:");

		Text text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		if(resSet == null){
			PCMResourceSet rs = new PCMResourceSet();
			rs.createAll(types);
			resSet = rs;
		}
		
		final CheckboxTableViewer tableView = 
				CheckboxTableViewer.newCheckList(parent, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
		tableView.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2 , 1));
		tableView.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		tableView.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		tableView.setInput(resSet);
		
		tableView.addCheckStateListener(new ICheckStateListener() {
			
			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				Object[] selection = tableView.getCheckedElements();
				
				selectedModels.clear();
				
				for(int i=0; i<selection.length; i++){
					Object o = selection[i];
					if(o instanceof Resource){
						Resource res = (Resource)o;
						selectedModels.add(PCMResourceSet.getTypeFromResource(res));
					}
				}				
			}
		});
		
	}
	
}