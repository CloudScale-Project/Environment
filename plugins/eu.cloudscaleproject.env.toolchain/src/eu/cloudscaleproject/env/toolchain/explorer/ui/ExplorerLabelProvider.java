package eu.cloudscaleproject.env.toolchain.explorer.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.internal.SharedImages;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResourceNode;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerLabelProvider implements ILabelProvider{
	
	private final SharedImages images = new SharedImages();

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public Image getImage(Object element) {
		
		Image image = null;
		
		if(element instanceof IExplorerNode){
			IExplorerNode node = (IExplorerNode)element;
			image = node.getIcon();
		}
		if(image == null){
			if(element instanceof ExplorerResourceNode){
				ExplorerResourceNode node = (ExplorerResourceNode)element;
				IResource resource = node.getResource();
				
				if(resource instanceof IProject){
					image = images.getImage(org.eclipse.ui.ide.IDE.SharedImages.IMG_OBJ_PROJECT);
				}
				if(resource instanceof IFolder){
					image = images.getImage(SharedImages.IMG_OBJ_FOLDER);
				}
				if(resource instanceof IFile){
					image = images.getImage(SharedImages.IMG_OBJ_FILE);			
				}
			}
		}
		
		return image;
	}

	@Override
	public String getText(Object element) {
		if(element instanceof IExplorerNode){
			IExplorerNode node = (IExplorerNode)element;
			return node.getName();
		}
		return element.toString();
	}
	
	@Override
	public void addListener(ILabelProviderListener listener) {		
	}
	
	@Override
	public void removeListener(ILabelProviderListener listener) {
	}
	
	@Override
	public void dispose() {
	}

}
