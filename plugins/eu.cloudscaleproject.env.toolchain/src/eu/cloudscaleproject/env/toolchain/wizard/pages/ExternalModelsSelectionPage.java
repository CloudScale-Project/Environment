package eu.cloudscaleproject.env.toolchain.wizard.pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
import org.eclipse.swt.widgets.Display;
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

	private HashSet<Resource> selectedResources = new HashSet<Resource>();
	private HashSet<Resource> selectedDiagramResources = new HashSet<Resource>();
	
	private ResourceSet resSet = new ResourceSetImpl();
	private ResourceSet resSetDiagram = new ResourceSetImpl();
	
	private CheckboxTableViewer tableView;
	
	private IFolder folder = null;
	private ICheckStateListener checkStateListener = null;
		
	private final ExplorerChangeListener ecl = new ExplorerChangeListener() {
		
		@Override
		public void resourceChanged(IResourceDelta delta) {
			Display.getDefault().syncExec(new Runnable() {
				
				@Override
				public void run() {
					refresh();
				}
			});
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
	
	public void selectResource(ModelType modelType, boolean state, boolean selectOnlyOne){
		for(Resource r : resSet.getResources()){
			if(modelType.getFileExtension().equals(r.getURI().fileExtension())){
				selectResource(r, state);
				if(selectOnlyOne){
					state = false;
				}
			}
		}
	}
	
	public void selectResource(Resource res, boolean state){
		
		for(EObject eo : res.getContents()){
			tableView.setChecked(eo, state);
			if(state){
				selectedResources.add(eo.eResource());
			}
			else{
				selectedResources.remove(eo.eResource());
			}
		}
		
		Resource dRes = findDiagramResource(res);
		if(dRes != null){
			if(state){
				selectedDiagramResources.add(dRes);
			}
			else{
				selectedDiagramResources.remove(dRes);
			}
		}
		
		for(Resource lRes : findLinked(res)){
			selectResource(lRes, state);
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
					
					selectedResources.clear();
					selectedDiagramResources.clear();
					
					tableView.refresh(true);				
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
								
				Object element = event.getElement();
				boolean state = event.getChecked();
				
				Resource res = null;
				if(element instanceof Resource){
					res = (Resource)element;
				}
				else if(element instanceof EObject){
					res = ((EObject)element).eResource();
				}
				
				if(res != null){
					selectResource(res, state);
				}
												
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
	
	private Resource findDiagramResource(Resource modelResource){
		String diagramName = modelResource.getURI().lastSegment().toString() + "_diagram";
		
		for(Resource r : resSetDiagram.getResources()){
			if(r.getURI().lastSegment().toString().equals(diagramName)){
				return r;
			}
		}
		return null;
	}
	
	private List<Resource> findLinked(Resource resource){
			
		List<Resource> selected = new ArrayList<Resource>();
		Iterator<EObject> iter = EcoreUtil.getAllContents(resource, false);
		while(iter.hasNext()){
			EObject o = iter.next();
			
			{	
				Resource res = o.eResource();
				if(res != null && !selected.contains(res)){
					selected.add(res);
				}
			}
			
			for(EStructuralFeature feature : o.eClass().getEAllStructuralFeatures()){
				Object child = o.eGet(feature, false);
				if(child instanceof InternalEObject){
					InternalEObject ieo = (InternalEObject)child;
					
					if(ieo.eProxyURI() == null
							|| ieo.eProxyURI().scheme().equals("pathmap")){
						continue;
					}
					
					EObject eo = o.eResource().getResourceSet().getEObject(ieo.eProxyURI(), false);
					
					if(eo != null){
						Resource res = eo.eResource();
						if(res != null && !selected.contains(res)){
							selected.add(res);
							selected.addAll(findLinked(res));
						}
					}
				}
			}
			
		}
		
		//remove self from this list!
		selected.remove(resource);
		return selected;
	}
	
	public void refresh(){
		if(folder != null){
			resSet.getResources().clear();
			resSetDiagram.getResources().clear();
			
			selectedResources.clear();
			selectedDiagramResources.clear();
			
			findAndLoadResources(folder);
			tableView.refresh(true);				
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
		
		//select all
		for(Resource res : resSet.getResources()){
			selectResource(res, true);
		}
		
		//Refresh is needed here to recreate table items from the resource set!
		//Only after that, table items can be selected!
		tableView.refresh(true);
		tableView.setAllChecked(true);
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
