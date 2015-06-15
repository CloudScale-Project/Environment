package eu.cloudscaleproject.env.toolchain.wizard.pages;

import java.util.ArrayList;
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

import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.dialogs.CustomResourceSelectionDialog;
import eu.cloudscaleproject.env.common.emf.CustomAdapterFactory;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.explorer.notification.ExplorerChangeListener;
import eu.cloudscaleproject.env.common.explorer.notification.ExplorerChangeNotifier;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.toolchain.ModelType;

public class ExternalModelsSelectionPage extends WizardPage implements IRefreshable{
		
	private static final String DEFAULT_TITLE = "External models selection";
	private static final String DEFAULT_DESCRIPTION = "Please select folder and models.";

	private List<Resource> selectedResources = new ArrayList<Resource>();
	private List<Resource> selectedDiagramResources = new ArrayList<Resource>();
	
	private ResourceSet resSet = new ResourceSetImpl();
	private ResourceSet resSetDiagram = new ResourceSetImpl();
	
	private CheckboxTableViewer tableView;
	
	private IFolder folder = null;
	private ICheckStateListener checkStateListener = null;
	
	private boolean disableSelectionListeners = false;
	
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
	private ModelType[] types;

	public ExternalModelsSelectionPage() {
		this (null, ModelType.GROUP_ALL);
	}

	public ExternalModelsSelectionPage(IFolder from, ModelType[] types) {
		this(from, types, null);
	}
	
	public ExternalModelsSelectionPage(IFolder from, ModelType[] types, ICheckStateListener csl) {
		this(DEFAULT_TITLE, DEFAULT_DESCRIPTION, from, types, csl);
		setDescription(DEFAULT_DESCRIPTION);
		this.folder = from;
		this.checkStateListener = csl;
		this.types = types;
	}
	
	public ExternalModelsSelectionPage(String name, String description, IFolder from, ModelType[] types, ICheckStateListener csl) {
		super(name, name, null);
		setDescription(description);
		this.folder = from;
		this.checkStateListener = csl;
		this.types = types;
	}
	
	public Resource[] getSelectedResources(){
		return selectedResources.toArray(new Resource[selectedResources.size()]);
	}
	
	public Resource[] getSelectedDiagramResources(){
		return selectedDiagramResources.toArray(new Resource[selectedDiagramResources.size()]);
	}
	
	public void selectModel(ModelType modelType, boolean state, boolean selectOnlyOne){
		try{
			disableSelectionListeners = true;

			for(Resource r : resSet.getResources()){
				if(modelType.getFileExtension().equals(r.getURI().fileExtension())){
					for(EObject eo : r.getContents()){
						tableView.setChecked(eo, state);
					}
					if(selectOnlyOne){
						state = false;
					}
				}
			}
			collectSelectedResources(tableView.getCheckedElements());
		}
		finally{
			disableSelectionListeners = false;
		}
	}
	
	public void selectModel(EObject modelRoot, boolean state){
		try{
			disableSelectionListeners = true;
			
			if(modelRoot == null){
				return;
			}
			tableView.setChecked(modelRoot, state);
			collectSelectedResources(tableView.getCheckedElements());
		}
		finally{
			disableSelectionListeners = false;
		}
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
					findAndLoadResources(folder);				
					tableView.refresh(true);				
					
					selectedResources.clear();
					selectedDiagramResources.clear();					
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
				
				if(disableSelectionListeners){
					return;
				}
				
				Object[] selection = tableView.getCheckedElements();
				collectSelectedResources(selection);
				
				if(checkStateListener != null){
					checkStateListener.checkStateChanged(event);
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
	
	private void collectSelectedResources(Object... selection){
		
		selectedResources.clear();
		selectedDiagramResources.clear();
		
		for(int i=0; i<selection.length; i++){
			Object o = selection[i];

			if (o instanceof EObject)
			{
				EObject eobj = (EObject) o;
				o = eobj.eResource();
			}

			if(o instanceof Resource){
				Resource res = (Resource)o;				
				selectedResources.add(res);
				
				Resource resDiag = findDiagramResource(res, resSetDiagram);
				if(resDiag != null){
					selectedDiagramResources.add(resDiag);
				}
			}
		}
	}
	
	private Resource findDiagramResource(Resource modelResource, ResourceSet diagramsResSet){
		String diagramName = modelResource.getURI().lastSegment().toString() + "_diagram";
		
		for(Resource r : diagramsResSet.getResources()){
			if(r.getURI().lastSegment().toString().equals(diagramName)){
				return r;
			}
		}
		return null;
	}
	
	public void refresh(){
		if(folder != null){
			resSet.getResources().clear();
			findAndLoadResources(folder);				
			tableView.refresh(true);				
			collectSelectedResources(tableView.getSelection());
		}
	}
	

	private void findAndLoadResources(IContainer folder){
		
		for (ModelType type : types)
		{
             for (IFile file : ExplorerProjectPaths.findResources(folder, type.getFileExtension()))
             {
            	 // TODO:addtional checks
                 ExplorerProjectPaths.getEmfResource(resSet, file);
             }
		}
		for (ModelType type : types)
		{
             for (IFile file : ExplorerProjectPaths.findResources(folder, type.getDiagramFileExtension()))
             {
            	 // TODO:addtional checks
                 ExplorerProjectPaths.getEmfResource(resSetDiagram, file);
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
