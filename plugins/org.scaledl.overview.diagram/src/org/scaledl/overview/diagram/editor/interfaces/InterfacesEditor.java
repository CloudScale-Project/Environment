package org.scaledl.overview.diagram.editor.interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.celleditor.AdapterFactoryTreeEditor;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.UnwrappingSelectionProvider;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.scaledl.overview.application.Operation;
import org.scaledl.overview.application.OperationInterface;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.architecture.Architecture;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.PlatformLayer;
import org.scaledl.overview.architecture.PlatformService;
import org.scaledl.overview.architecture.Service;
import org.scaledl.overview.architecture.SoftwareLayer;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.parametertype.ParameterType;
import org.scaledl.overview.util.OverviewUtil;

import eu.cloudscaleproject.env.common.CommonResources;

public class InterfacesEditor extends EditorPart implements IMenuListener, IEditingDomainProvider{
		
	private TreeViewer interfacesTreeViewer = null;
	private TreeViewer datatypeTreeViewer = null;
	private TreeViewer unresolvedInterfacesTreeView = null;
		
	private OICEditor mainEditor;
	private OICEditorInput input;

	public InterfacesEditor(OICEditor editor) {
		this.mainEditor = editor;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter.equals(IPropertySheetPage.class)) {
			return getPropertySheetPage();
		}
		else {
			return super.getAdapter(adapter);
		}
	}
	
	public IPropertySheetPage getPropertySheetPage() {
		PropertySheetPage propertySheetPage =
			new ExtendedPropertySheetPage((AdapterFactoryEditingDomain)mainEditor.getEditingDomain()) {
				@Override
				public void setSelectionToViewer(List<?> selection) {
					InterfacesEditor.this.setFocus();
				}

				@Override
				public void setActionBars(IActionBars actionBars) {
				}
			};
		propertySheetPage.setPropertySourceProvider(new AdapterFactoryContentProvider(mainEditor.getAdapterFactory()));
		return propertySheetPage;
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		Resource resource = input.getOperationInterfaceContainer().eResource();
		try {
			resource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doSaveAs() {
		// disabled
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
	
		if (!(input instanceof OICEditorInput)) {
		      throw new RuntimeException("Wrong input");
		}
		this.input = (OICEditorInput) input;
		setSite(site);
		setInput(input);
		setPartName("Csm name: " + this.input.getOperationInterfaceContainer().toString());
	}

	@Override
	public boolean isDirty() {
		return ((BasicCommandStack)mainEditor.getEditingDomain().getCommandStack()).isSaveNeeded();
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
	    Form form = toolkit.createForm(parent);
	    form.setText(input.getOperationInterfaceContainer().getName());
	    FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL);
	    fillLayout.marginWidth = 5;
	    fillLayout.marginHeight = 5;
	    fillLayout.spacing = 15;
	    form.getBody().setLayout(fillLayout);
	    
	    Composite leftComposite = toolkit.createComposite(form.getBody(), SWT.NONE);
	    toolkit.paintBordersFor(leftComposite);
	    FillLayout fl_leftComposite = new FillLayout(SWT.VERTICAL);
	    fl_leftComposite.spacing = 10;
	    leftComposite.setLayout(fl_leftComposite);
	    
	    Composite rightComposite = new Composite(form.getBody(), SWT.NONE);
	    toolkit.adapt(rightComposite);
	    toolkit.paintBordersFor(rightComposite);
	    FillLayout fl_rightComposite = new FillLayout(SWT.VERTICAL);
	    fl_rightComposite.spacing = 10;
	    rightComposite.setLayout(fl_rightComposite);
	    
	    //
	    // SECTIONS
	    //
	    
	    OperationInterfaceContainer oic = input.getOperationInterfaceContainer();
		
	    // Interfaces list section
	    {
		    Section section = toolkit.createSection(leftComposite, Section.DESCRIPTION | Section.TITLE_BAR);
		    section.setText("Operation interface container editor"); //$NON-NLS-1$
		    section.setDescription("Edit and configure service interfaces and opeartions.");
		    
		    interfacesTreeViewer = createOpInterfacesContainerTreeView(section, OverviewUtil.getCloudEnvironment(oic));
		    interfacesTreeViewer.getTree().addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					getEditorSite().setSelectionProvider(InterfacesEditor.this.interfacesTreeViewer);
				}
			});
		    
		    toolkit.paintBordersFor(interfacesTreeViewer.getTree());
		    section.setClient(interfacesTreeViewer.getTree()); 
	    }
	    
	    // Unresolved interfaces
	    {
	    	/*
	    	if(oic instanceof SoftwareService){
	    		
	    		Section section = toolkit.createSection(leftComposite, Section.DESCRIPTION | Section.TITLE_BAR);
			    section.setText("Edit required interfaces"); //$NON-NLS-1$
			    section.setDescription("Add interfaces, which this software service requires from connected operation interface containers.");
	    		
	    		SoftwareService ss = (SoftwareService)oic;
	    		RequiredInterfaceComposite requiredInterfacesComposite = new RequiredInterfaceComposite(section, ss);
	    		toolkit.paintBordersFor(requiredInterfacesComposite);
			    section.setClient(requiredInterfacesComposite);
	    	}
	    	*/
	    	
	    		
	    	Section section = toolkit.createSection(leftComposite, Section.DESCRIPTION | Section.TITLE_BAR);
			section.setText("Unresolved interfaces"); //$NON-NLS-1$
			section.setDescription("Drag unresolved interface to desired operation interface container to resolve it.");
	    	
			Architecture architecture = OverviewUtil.getArchitecture(oic);
			
			unresolvedInterfacesTreeView = createUnresolvedInterfacesTreeView(section, architecture.getUnresolvedOperationInterfaces());
			
	    	toolkit.paintBordersFor(unresolvedInterfacesTreeView.getTree());
			section.setClient(unresolvedInterfacesTreeView.getTree());
	    }
	    
	    // DataTypes list section
	    {
	    	Section section = toolkit.createSection(rightComposite, Section.DESCRIPTION | Section.TITLE_BAR);
		    section.setText("Data types editor"); //$NON-NLS-1$
		    section.setDescription("Add or modifie data types.");
		    
		    ParameterType parameterType = OverviewUtil.getDataTypes(input.getOperationInterfaceContainer());
		    if(parameterType == null){
		    	parameterType = org.scaledl.overview.parametertype.ParametertypeFactory.eINSTANCE.createParameterType();
		    	//TODO: fix this
		    }
		    datatypeTreeViewer = createDatatypeTreeView(section, parameterType);
		    datatypeTreeViewer.getTree().addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					getEditorSite().setSelectionProvider(InterfacesEditor.this.datatypeTreeViewer);					
				}
			});
		    
		    toolkit.paintBordersFor(datatypeTreeViewer.getTree());
		    section.setClient(datatypeTreeViewer.getTree()); 
	    }
	}
	
	private TreeViewer createOpInterfacesContainerTreeView(Composite parent, CloudEnvironment ce){
		
		TreeViewer treeViewer;
		
		Tree tree = new Tree(parent, SWT.BORDER);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		treeViewer = new TreeViewer(tree);
		
		treeViewer.setContentProvider(new AdapterFactoryContentProvider(mainEditor.getAdapterFactory()){
			@Override
			public Object[] getElements(Object object) {
				// TODO Auto-generated method stub
				Object[] out = super.getElements(object);
				if(out == null || out.length == 0){
					if(object instanceof Object[]){
						return (Object[])object;
					}
				}
				return out;
			}
		});
		treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(mainEditor.getAdapterFactory()){
			@Override
			public Image getImage(Object object) {
				
				if(object instanceof PlatformService){
					return CommonResources.PLATFORM_SERVICE;
				}
				else if (object instanceof SoftwareService){
					return CommonResources.SOFTWARE_SERVICE;
				}
				else if( object instanceof OperationInterface){
					return CommonResources.INTERFACE;
				}
				else if( object instanceof Operation){
					return CommonResources.OPERATION;
				}
				
				return null;
			}
		});
		
		List<OperationInterfaceContainer> oicList = new ArrayList<OperationInterfaceContainer>();
		PlatformLayer pl = ce.getPlatformLayer();
		SoftwareLayer sl = ce.getSoftwareLayer();
		
		for(Service s : pl.getServices()){
			if(s instanceof OperationInterfaceContainer){
				oicList.add((OperationInterfaceContainer)s);
			}
		}
		for(Service s : sl.getServices()){
			if(s instanceof OperationInterfaceContainer){
				oicList.add((OperationInterfaceContainer)s);
			}
		}
		
		treeViewer.setFilters(new ViewerFilter[]{new ViewerFilter() {
			
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				
				if(element instanceof OperationInterfaceContainer){
					return true;
				}
				else if(element instanceof OperationInterface){
					return true;
				}
				else if(element instanceof Operation){
					return true;
				}
				
				return false;
			}
			
		}});
		
		treeViewer.setInput(oicList.toArray(new OperationInterfaceContainer[oicList.size()]));
		treeViewer.expandAll();
		
		createContextMenuFor(treeViewer);
		
		new AdapterFactoryTreeEditor(tree, mainEditor.getAdapterFactory());
		return treeViewer;
	}
	
	private TreeViewer createDatatypeTreeView(Composite parent, Object input){
		
		TreeViewer treeViewer;
		
		Tree tree = new Tree(parent, SWT.BORDER);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		treeViewer = new TreeViewer(tree);
		
		treeViewer.setContentProvider(new AdapterFactoryContentProvider(mainEditor.getAdapterFactory()));
		treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(mainEditor.getAdapterFactory()));
		treeViewer.setInput(input);
		
		treeViewer.expandAll();
		
		createContextMenuFor(treeViewer);
		
		new AdapterFactoryTreeEditor(tree, mainEditor.getAdapterFactory());
		return treeViewer;
	}
	
	private TreeViewer createUnresolvedInterfacesTreeView(Composite parent, Object input){
		TreeViewer treeViewer;
		
		Tree tree = new Tree(parent, SWT.BORDER);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		treeViewer = new TreeViewer(tree);
		
		treeViewer.setContentProvider(new AdapterFactoryContentProvider(mainEditor.getAdapterFactory()));
		treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(mainEditor.getAdapterFactory()));
		treeViewer.setInput(input);
		
				
		new AdapterFactoryTreeEditor(tree, mainEditor.getAdapterFactory());
		return treeViewer;
	}
	
	protected void createContextMenuFor(StructuredViewer viewer) {
		MenuManager contextMenu = new MenuManager("#PopUp");
		contextMenu.add(new Separator("additions"));
		contextMenu.setRemoveAllWhenShown(true);
		contextMenu.addMenuListener(this);
		Menu menu= contextMenu.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(contextMenu, new UnwrappingSelectionProvider(viewer));

		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
		Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance(), LocalSelectionTransfer.getTransfer(), FileTransfer.getInstance() };
		viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));
		viewer.addDropSupport(dndOperations, transfers, new EditingDomainViewerDropAdapter(mainEditor.getEditingDomain(), viewer));
	}
	
	public void menuAboutToShow(IMenuManager menuManager) {
		
		if(this.interfacesTreeViewer.getTree().isFocusControl()){
			getEditorSite().setSelectionProvider(this.interfacesTreeViewer);
		}
		else if(this.datatypeTreeViewer.getTree().isFocusControl()){
			getEditorSite().setSelectionProvider(this.datatypeTreeViewer);
		}
		else{
			return;
		}
		
		mainEditor.getEditorSite().getActionBarContributor().setActiveEditor(this);
		((IMenuListener)mainEditor.getEditorSite().getActionBarContributor()).menuAboutToShow(menuManager);
	}

	@Override
	public void setFocus() {
		
	}

	@Override
	public EditingDomain getEditingDomain() {
		return mainEditor.getEditingDomain();
	}
}
