package eu.cloudscaleproject.env.analyser.wizard.pages;

import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import eu.cloudscaleproject.env.analyser.PCMModelType;
import eu.cloudscaleproject.env.analyser.PCMResourceSet;
import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.dialogs.CustomResourceSelectionDialog;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.explorer.notification.ExplorerChangeListener;
import eu.cloudscaleproject.env.common.explorer.notification.ExplorerChangeNotifier;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.toolchain.util.CustomAdapterFactory;

public class ImportModelSelectionPage extends WizardPage implements IRefreshable{
		
	private Resource[] selectedResources = new Resource[0];
	
	private ResourceSet resSet = new ResourceSetImpl();
	private CheckboxTableViewer tableView;
	
	private IFolder folder = null;
	
	private final ExplorerChangeListener ecl = new ExplorerChangeListener() {
		
		@Override
		public void resourceChanged(IResourceDelta delta) {
			refresh();
		}
		
		@Override
		public IResource[] getResources() {
			return new IResource[]{folder};
		}
	};

	public ImportModelSelectionPage(String name) {
		this(name, null);
	}
	
	public ImportModelSelectionPage(String name, IFolder from) {
		super(name, name, null);
		setTitle(name);
		this.folder = from;
	}
	
	public Resource[] getSelectedResources(){
		return selectedResources;
	}
	
	@Override
	public void createControl(Composite parent) {
		
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(3, false));
	
		if(folder == null){
			createFolderSelection(container, "Folder:", "Select folder", new BasicCallback<IContainer>() {
	
				@Override
				public void handle(IContainer folder) {
					resSet.getResources().clear();
					findAndLoadResources(folder, resSet);				
					tableView.refresh(true);				
					tableView.setCheckedElements(resSet.getResources().toArray());
					selectedResources = (Resource[])resSet.getResources().toArray();
				}
			});
		}
		
		final ComposedAdapterFactory adapterFactory = new CustomAdapterFactory();
		
		tableView = CheckboxTableViewer.newCheckList(container, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
		tableView.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3 , 1));
		tableView.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		tableView.setLabelProvider(new org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider(
				new AdapterFactoryLabelProvider.StyledLabelProvider(adapterFactory, this.tableView)));
		tableView.setInput(resSet);
		
		tableView.addCheckStateListener(new ICheckStateListener() {
			
			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				Object[] selection = tableView.getCheckedElements();
				selectedResources = new Resource[selection.length];
				
				for(int i=0; i<selection.length; i++){
					Object o = selection[i];

					if (o instanceof EObject)
					{
						EObject eobj = (EObject) o;
						o = eobj.eResource();
					}

					if(o instanceof Resource){
						Resource res = (Resource)o;
						selectedResources[i] = res;
					}
				}
			}
		});
		
		setControl(container);
		
		if(folder != null){
			ExplorerChangeNotifier.getInstance().addListener(ecl);
		}
		container.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				ExplorerChangeNotifier.getInstance().removeListener(ecl);
			}
		});

		refresh();
	}
	
	public void refresh(){
		if(folder != null){
			resSet.getResources().clear();
			findAndLoadResources(folder, resSet);				
			tableView.refresh(true);				
			tableView.setCheckedElements(resSet.getResources().toArray());
			selectedResources = (Resource[])resSet.getResources().toArray();
		}
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
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.AT.getFileExtension());
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
