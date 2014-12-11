package eu.cloudscaleproject.env.common.explorer;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ISetSelectionTarget;

public class ExplorerUtils {

	public static IViewPart getExplorerViewPart(){
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		if(page == null){
			return null;
		}
		
		IViewPart view = page.findView(IPageLayout.ID_PROJECT_EXPLORER);
		return view;
	}
	
	public static void selectAndReveal(IResource resource){
		IViewPart explorerPart = getExplorerViewPart();
		if(explorerPart != null && explorerPart instanceof ISetSelectionTarget){
			((ISetSelectionTarget)explorerPart).selectReveal(new StructuredSelection(resource));
		}
	}
	
}
