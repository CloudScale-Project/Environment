package org.scaledl.overview.diagram.editor.interfaces;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.application.util.ApplicationAdapterFactory;
import org.scaledl.overview.architecture.util.ArchitectureAdapterFactory;
import org.scaledl.overview.parametertype.util.ParametertypeAdapterFactory;
import org.scaledl.overview.util.OverviewAdapterFactory;

public class OICEditor extends MultiPageEditorPart implements IEditingDomainProvider{
	
	public static final String ID = "org.cloudscale.overview.diagram.editor.oiceditor";

	ImportEditor importEditor;
	InterfacesEditor interfacesEditor;
	
	protected ComposedAdapterFactory adapterFactory;
	protected AdapterFactoryEditingDomain editingDomain;
	
	public AdapterFactory getAdapterFactory(){
		return adapterFactory;
	}
	
	@Override
	protected void createPages() {
		
		OICEditorInput editorInput = (OICEditorInput)getEditorInput();
		OperationInterfaceContainer oic = editorInput.getOperationInterfaceContainer();
		
		EditingDomain ed = TransactionUtil.getEditingDomain(oic);
		
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ApplicationAdapterFactory());
		adapterFactory.addAdapterFactory(new ArchitectureAdapterFactory());
		adapterFactory.addAdapterFactory(new ParametertypeAdapterFactory());
		adapterFactory.addAdapterFactory(new OverviewAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new EcoreAdapterFactory());
		
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory, ed.getCommandStack());
		
		try {
			this.importEditor = new ImportEditor();
			//this.importEditor.init(getEditorSite(), getEditorInput());
			
			int pageIndex = addPage(importEditor, getEditorInput());
			setPageText(pageIndex, "Import");
			
			this.interfacesEditor = new InterfacesEditor(this);
			//this.interfacesEditor.init(getEditorSite(), getEditorInput());
			
			pageIndex = addPage(interfacesEditor, getEditorInput());
			System.out.println(pageIndex);
			setPageText(pageIndex, "Provided interfaces");
			
			setActiveEditor(interfacesEditor);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	private class ArrayAdapterFactory extends OverviewAdapterFactory{
		
		protected static OverviewPackage modelPackage;

		public ArrayAdapterFactory() {
			if (modelPackage == null) {
				modelPackage = OverviewPackage.eINSTANCE;
			}
		}

		@Override
		public boolean isFactoryForType(Object object) {
			if (object == modelPackage) {
				return true;
			}
			if (object instanceof EObject) {
				return ((EObject)object).eClass().getEPackage() == modelPackage;
			}
			return false;
		}


		@Override
		public Adapter createAdapter(Notifier target) {
			return modelSwitch.doSwitch((EObject)target);
		}
	}
	*/
	
	@Override
	public boolean isDirty() {
		return false;
		//return ((BasicCommandStack)editingDomain.getCommandStack()).isSaveNeeded();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		//interfacesEditor.doSave(monitor);
		//importEditor.doSave(monitor);
	}

	@Override
	public void doSaveAs() {
		// disabled
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

}
