package eu.cloudscaleproject.env.analyser.wizard.pages;

import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.palladiosimulator.edp2.models.ExperimentData.util.ExperimentDataAdapterFactory;
import org.palladiosimulator.edp2.models.measuringpoint.provider.MeasuringpointItemProviderAdapterFactory;
import org.palladiosimulator.simulizar.monitorrepository.provider.MonitorrepositoryItemProviderAdapterFactory;

import de.uka.ipd.sdq.pcm.allocation.util.AllocationAdapterFactory;
import de.uka.ipd.sdq.pcm.repository.util.RepositoryAdapterFactory;
import de.uka.ipd.sdq.pcm.seff.util.SeffAdapterFactory;
import de.uka.ipd.sdq.pcm.system.util.SystemAdapterFactory;
import eu.cloudscaleproject.env.analyser.PCMModelType;
import eu.cloudscaleproject.env.analyser.PCMResourceSet;
import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.dialogs.CustomResourceSelectionDialog;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

public class ImportModelSelectionPage extends WizardPage{
		
	private Resource[] selectedResources;
	
	PCMResourceSet resSet = new PCMResourceSet();
	CheckboxTableViewer tableView;
	Button btnCheckCopy;

	public ImportModelSelectionPage(String title) {
		super(title);
	}
	
	public Resource[] getSelectedResources(){
		return selectedResources;
	}

	@Override
	public void createControl(Composite parent) {
		
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(3, false));
	
		createFolderSelection(container, "Folder:", "Select folder", new BasicCallback<IContainer>() {

			@Override
			public void handle(IContainer folder) {
				resSet.getResources().clear();
				findAndLoadResources(folder, resSet);				
				tableView.refresh(true);				
				tableView.setCheckedElements(resSet.getResources().toArray());
			}
			
		});
		
		final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new RepositoryAdapterFactory());
		adapterFactory.addAdapterFactory(new SystemAdapterFactory());
		adapterFactory.addAdapterFactory(new AllocationAdapterFactory());
		adapterFactory.addAdapterFactory(new SeffAdapterFactory());
		adapterFactory.addAdapterFactory(new MeasuringpointItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new MonitorrepositoryItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ExperimentDataAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new EcoreAdapterFactory());
		
		tableView = CheckboxTableViewer.newCheckList(container, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
		tableView.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3 , 1));
		tableView.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		tableView.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		tableView.setInput(resSet);
		
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		btnCheckCopy = new Button(container, SWT.CHECK);
		btnCheckCopy.setText("Copy in project");
		btnCheckCopy.setSelection(false);
		
	}

	private void findAndLoadResources(IContainer folder, ResourceSet resSet){
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.REPOSITORY.getFileExtension());
			for(IFile file : files){
				ExplorerProjectPaths.getEmfResource(resSet, file);
			}
		}
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.SYSTEM.getFileExtension());
			for(IFile file : files){
				ExplorerProjectPaths.getEmfResource(resSet, file);
			}
		}
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.RESOURCE.getFileExtension());
			for(IFile file : files){
				ExplorerProjectPaths.getEmfResource(resSet, file);
			}
		}
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.ALLOCATION.getFileExtension());
			for(IFile file : files){
				ExplorerProjectPaths.getEmfResource(resSet, file);
			}
		}
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.USAGE.getFileExtension());
			for(IFile file : files){
				ExplorerProjectPaths.getEmfResource(resSet, file);
			}
		}
	}
	
	private void createFolderSelection(Composite composite, final String name, final String text,
			final BasicCallback<IContainer> callback) {
		
		Label lbl = new Label(composite, SWT.NONE);
		lbl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbl.setText(name);

		final Text textPath = new Text(composite, SWT.BORDER);
		textPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnBrowse = new Button(composite, SWT.NONE);
		btnBrowse.setText("Browse...");
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CustomResourceSelectionDialog rs = new CustomResourceSelectionDialog(
						text, text, IContainer.class);
				rs.open();

				Object sel = rs.getFirstResult();
				if (sel instanceof IContainer) {
					textPath.setText(((IContainer) sel).getFullPath().toString());
					callback.handle((IContainer) sel);
				}
			}
		});
	}
}
