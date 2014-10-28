package eu.cloudscaleproject.env.method.editor.diagram;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.internal.parts.ContainerShapeEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;

import eu.cloudscaleproject.env.method.common.method.provider.MethodItemProviderAdapterFactory;

public class EditDiagramEditor extends DiagramEditor{
	
	protected ComposedAdapterFactory adapterFactory;

	public EditDiagramEditor() {
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new MethodItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new EcoreAdapterFactory());
	}
	
	@Override
	public void initializeGraphicalViewer() {
		// TODO Auto-generated method stub
		super.initializeGraphicalViewer();
		
		//getSite().setSelectionProvider(new SelectionProvider());
	}
	
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		
		super.init(site, input);
		
		//site.setSelectionProvider(new SelectionProvider());
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
			new ExtendedPropertySheetPage((AdapterFactoryEditingDomain)getEditingDomain()) {
				@Override
				public void setSelectionToViewer(List<?> selection) {
					EditDiagramEditor.this.setFocus();
				}

				@Override
				public void setActionBars(IActionBars actionBars) {
				}
				
				@Override
				public void selectionChanged(IWorkbenchPart part,
						ISelection selection) {
					
					if(selection instanceof StructuredSelection){
						StructuredSelection ss = (StructuredSelection)selection;
						if(ss.getFirstElement() instanceof ContainerShapeEditPart){
							
							ContainerShapeEditPart shape = (ContainerShapeEditPart)ss.getFirstElement();
							PictogramElement el = shape.getPictogramElement();
							
							if(el != null){
								EObject bo = (EObject)Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(el);
								if(bo != null){
									StructuredSelection newSelection = new StructuredSelection(bo);
									super.selectionChanged(part, newSelection);
									return;
								}
							}
						}
					}
					
					super.selectionChanged(part, selection);
				}
			};
		propertySheetPage.setPropertySourceProvider(new AdapterFactoryContentProvider(adapterFactory));
		return propertySheetPage;
	}
}
