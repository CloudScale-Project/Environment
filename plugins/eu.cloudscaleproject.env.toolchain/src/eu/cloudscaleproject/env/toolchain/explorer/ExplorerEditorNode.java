package eu.cloudscaleproject.env.toolchain.explorer;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerEditorNode extends ExplorerResourceNode{
	
	private static final Logger logger = Logger.getLogger(ExplorerEditorNode.class.getName());
	
	@Inject
	private EPartService partService;
	@Inject
	private EModelService modelService;
	@Inject
	private MApplication application;
	
	public ExplorerEditorNode(String partID, IResource resource, IExplorerNodeChildren childFactory) {
		super(partID, resource, childFactory);
	}
	
	public void openEditor(){
		
		String editorID = (String)getContext().getLocal(IExplorerConstants.NODE_EDITOR_ID);
		
		if(editorID == null && getResource() instanceof IFile){
			//open editor the old way
			try {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				IDE.openEditor(page, (IFile)getResource());
			} catch (PartInitException e) {
				e.printStackTrace();
			}
			return;
		}
		
		if(editorID == null){
			logger.warning("Editor id has not been found. IExplorerNode: " + getName());
			return;
		}
		
		MPart part = partService.findPart(editorID);
		
		Object data = getContext().get(IExplorerConstants.NODE_DATA);
		
		if(part == null){
			MPartStack stack = (MPartStack)modelService.find("org.eclipse.e4.primaryDataStack", application);
			if(stack != null){
				part = partService.createPart(editorID);
				stack.getChildren().add(part);
			}
		}
		
		partService.showPart(part, PartState.ACTIVATE);
		
		//fill in context data
		IEclipseContext context = part.getContext();
		
		context.set(data.getClass().getName(), data);
		context.set(ExplorerEditorNode.class, this);
	}
}
