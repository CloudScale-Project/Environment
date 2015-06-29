
package eu.cloudscaleproject.env.csm2pcm.diagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.CreateCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;

import de.uka.ipd.sdq.pcm.gmf.repository.part.PalladioComponentModelRepositoryDiagramEditor;
import de.uka.ipd.sdq.pcm.gmf.repository.part.PalladioComponentModelRepositoryDiagramEditorPlugin;
import org.palladiosimulator.pcm.repository.CompositeComponent;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.repository.RequiredRole;

/**
 * @generated
 */
public class CreateShortcutAction  {
	
	private static TransactionalEditingDomain editingDomain = null;
	private static  DiagramEditPart editPart;
	private static View view;

    /**
     * @generated
     */
    public static void execute(final Diagram d) {
        /*IEditorPart diagramEditor = HandlerUtil.getActiveEditorChecked(event);
        Shell shell = diagramEditor.getEditorSite().getShell();
        assert diagramEditor instanceof DiagramEditor;
        TransactionalEditingDomain editingDomain = ((DiagramEditor) diagramEditor).getEditingDomain();
        ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);
        assert selection instanceof IStructuredSelection;
        assert ((IStructuredSelection) selection).size() == 1;
        assert ((IStructuredSelection) selection).getFirstElement() instanceof EditPart;
        EditPart selectedDiagramPart = (EditPart) ((IStructuredSelection) selection).getFirstElement();
        final View view = (View) selectedDiagramPart.getModel();
        PalladioComponentModelElementChooserDialog elementChooser = new PalladioComponentModelElementChooserDialog(
                shell, view);
        int result = elementChooser.open();
        if (result != Window.OK) {
            return null;
        }
       
        
        URI selectedModelElementURI = elementChooser.getSelectedModelElementURI();
        final EObject selectedElement;
        try {
            selectedElement = editingDomain.getResourceSet().getEObject(selectedModelElementURI, true);
        } catch (WrappedException e) {
            PalladioComponentModelRepositoryDiagramEditorPlugin.getInstance().logError(
                    "Exception while loading object: " + selectedModelElementURI.toString(), e); //$NON-NLS-1$
            return null;
        }

        if (selectedElement == null) {
            return null;
        }*/
    	editingDomain = null;
    	if (editingDomain == null)
    	{
	    	Display.getDefault().syncExec(new Runnable() {
				
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
					
					for (IEditorReference editorRef : editorReferences)
					{
						if (editorRef.getEditor(false) instanceof PalladioComponentModelRepositoryDiagramEditor)
						{
							PalladioComponentModelRepositoryDiagramEditor pe = (PalladioComponentModelRepositoryDiagramEditor) editorRef.getEditor(false);
							
							editPart = pe.getDiagramEditPart();
							view = (View) editPart.getModel();
							
							editingDomain = ((DiagramEditor) pe).getEditingDomain();
							
							
						}
					}
				}});
    	}
    	
    	if (editingDomain == null)
    	{
    		return;
    	}
    	//TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain 
    	
    	List<OperationInterface> interfaces = getInterfaces(d);
    	if (interfaces == null || interfaces.isEmpty()) return;
    	
    	//Repository repository = (Repository) d.getElement();

 		
    	ICommand command = null;
    	for (OperationInterface oi : getInterfaces(d))
    	{
    		

        	System.out.println("asdf> "+oi.getEntityName() +" re:"+oi.eResource().getURI());
    		CreateViewRequest.ViewDescriptor viewDescriptor = new CreateViewRequest.ViewDescriptor(new EObjectAdapter(
                    oi), Node.class, null,
                    PalladioComponentModelRepositoryDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
    		if (command == null)
    			command = new CreateCommand(editingDomain, viewDescriptor, view);
    		else
    		{
    			break;
    			//command.compose (new CreateCommand(editingDomain, viewDescriptor, view));
    		}
    		
    		command.compose(new PalladioComponentModelCreateShortcutDecorationsCommand(editingDomain, view,
                    viewDescriptor));
    		command.compose(new RefreshElementCommand(oi, editingDomain, view));
    	}
       
       // command = command.compose(new PalladioComponentModelCreateShortcutDecorationsCommand(editingDomain, d, viewDescriptor));
        try {
            OperationHistoryFactory.getOperationHistory().execute(command, new NullProgressMonitor(), null);
        } catch (ExecutionException e) {
            PalladioComponentModelRepositoryDiagramEditorPlugin.getInstance().logError("Unable to create shortcut", e); //$NON-NLS-1$
        }
    
    }
    
    private static List<OperationInterface> getInterfaces(Diagram diagram) {


		if (!(diagram.getElement() instanceof Repository))
		{
			return null;
		}
		Repository repository = (Repository) diagram.getElement();

		List<OperationInterface> interfaces = new ArrayList<OperationInterface>();
		// we create a composition of the commands necessary
		//CreateViewRequest.ViewDescriptor rootDescriptor = new CreateViewRequest.ViewDescriptor(
		//		new EObjectAdapter(repository), Node.class, null, null);
		//ICommand command = new RefreshElementCommand(rootObject,
		//		editingDomain,
		//		diagram);

		for (RepositoryComponent o : repository.getComponents__Repository())
		{
			if (o instanceof CompositeComponent)
			{
				CompositeComponent c = (CompositeComponent) o;
				System.out.println(c.getEntityName());
				for (ProvidedRole r : c.getProvidedRoles_InterfaceProvidingEntity())
				{
					if (r instanceof OperationProvidedRole)
					{
						OperationProvidedRole opr = (OperationProvidedRole) r;
						OperationInterface oi = opr.getProvidedInterface__OperationProvidedRole();
						interfaces.add(oi);
					}
				}
				for (RequiredRole r : c.getRequiredRoles_InterfaceRequiringEntity())
				{
					if (r instanceof OperationRequiredRole)
					{
						OperationRequiredRole opr = (OperationRequiredRole) r;
						OperationInterface oi = opr.getRequiredInterface__OperationRequiredRole();
						interfaces.add(oi);
					}
				}
			
			}
			//diagram.get
		}
		
		return interfaces;
    }

}