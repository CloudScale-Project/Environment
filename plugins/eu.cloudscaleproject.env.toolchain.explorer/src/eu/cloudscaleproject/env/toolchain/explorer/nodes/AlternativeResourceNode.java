package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import org.eclipse.core.resources.IFile;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import eu.cloudscaleproject.env.common.ColorResources;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerEditorNode;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerConstants;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.style.AbstractLabelStyle;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class AlternativeResourceNode extends ExplorerEditorNode{
	
	private static final AbstractLabelStyle DEFAULT_LABEL_STYLE = new AbstractLabelStyle() {
		
		@Override
		public StyledString getStyledText(Object element) {
			if(element instanceof IExplorerNode){
				
				IExplorerNode node = (IExplorerNode)element;
			
				return new StyledString(node.getName(), new Styler() {
					
					@Override
					public void applyStyles(TextStyle textStyle) {
						textStyle.foreground = ColorResources.COLOR_CS_BLUE_DARK;						
					}
					
				});
				
			}
			return null;
		}
		
		@Override
		public Image getImage(Object element) {
			return null;
		}
	};
	
	public AlternativeResourceNode(IEclipseContext context, IFile file) {
		super(context, file.getName(), null, file, null);
		
		ModelType type = ModelType.getModelType(file.getFileExtension());
		if(type != null){
			setName(type.getDisplayName());
		}
		else{
			setName(file.getName());
		}
		
		getContext().set(IExplorerConstants.NODE_DATA, file);
		getContext().set(IFile.class, file);
		getContext().set(IStyledLabelProvider.class, DEFAULT_LABEL_STYLE);
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
