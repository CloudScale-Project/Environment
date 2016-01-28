package eu.cloudscaleproject.env.analyser.wizard.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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

import eu.cloudscaleproject.env.analyser.PCMResourceSet;
import eu.cloudscaleproject.env.toolchain.ModelType;

public class ModelSelectionPage extends WizardPage{
	
	private static final Logger logger = Logger.getLogger(ModelSelectionPage.class.getName());
		
	private AdapterFactory adapterFactory = null;
	
	private ModelType[] types;
	
	private List<ModelType> selectedModels = new ArrayList<ModelType>();
	private List<ModelType> models = new ArrayList<ModelType>();
	
	private Resource resource = new ResourceImpl();
	
	private CheckboxTableViewer tableView;
	private boolean selectAll = false;
	
	public ModelSelectionPage(String name, AdapterFactory adapterFacoty, ModelType[] types) {
		super(name, name, null);
		
		setTitle(name);

		this.types = types;
		this.adapterFactory = adapterFacoty;
	}
	
	public ModelType[] getSelectedModelTypes(){
		return selectedModels.toArray(new ModelType[selectedModels.size()]);
	}
	
	public void selectAll(){
		if(tableView != null){
			doSelectAll();
		}
		selectAll = true;
	}
	
	private void doSelectAll(){
		for(ModelType t : models){
			selectedModels.add(t);
		}
		tableView.setAllChecked(true);
	}

	@Override
	public void createControl(Composite parent) {
		
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());
		
		for(int i=0; i<types.length; i++){
			EObject rootObject = PCMResourceSet.createModelRootObject(types[i]);
			if(rootObject != null){
				resource.getContents().add(rootObject);
				models.add(types[i]);
			}
			else{
				logger.warning("Model root object can not be created: " + types[i].getDisplayName());
			}
		}
				
		tableView = CheckboxTableViewer.newCheckList(container, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
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
					
					ModelType type = models.get(resource.getContents().indexOf(o));
					selectedModels.add(type);
				}				
			}
		});
		
		if(selectAll){
			doSelectAll();
		}
		
		setControl(container);
	}
	
}