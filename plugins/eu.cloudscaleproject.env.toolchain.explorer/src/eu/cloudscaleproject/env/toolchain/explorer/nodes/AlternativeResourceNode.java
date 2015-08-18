package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerEditorNode;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerConstants;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class AlternativeResourceNode extends ExplorerEditorNode{

	public AlternativeResourceNode(EditorInputEMF alternative, IFile file) {
		super(file.getName(), null, file, null);
		
		setData(file);

		ModelType type = ModelType.getModelType(file.getFileExtension());
		if(type != null){
			setName(type.getDisplayName());
		}
		else{
			setName(file.getName());
		}
	}
	
	public IEditorInputResource getAlternative(){
		IEditorInputResource eir = context.get(IEditorInputResource.class);
		return eir;
	}

	@Override
	public void openEditor() {
		
		String editorID = (String)getContext().getLocal(IExplorerConstants.NODE_EDITOR_ID);
		if(editorID == null && getResource() instanceof IFile){
			
			//open editor the old way
			try {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				
				//open EMF diagram file if found
				IFile diagramFile = ToolchainUtils.getEMFDiagramFileFromModel((IFile)getResource());
				if(diagramFile != null){
					IDE.openEditor(page, diagramFile);
					return;
				}
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
		super.openEditor();
	}
	
}
