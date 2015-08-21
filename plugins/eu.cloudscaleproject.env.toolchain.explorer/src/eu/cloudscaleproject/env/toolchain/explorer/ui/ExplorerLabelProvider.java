package eu.cloudscaleproject.env.toolchain.explorer.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.ui.internal.SharedImages;

import eu.cloudscaleproject.env.common.ColorResources;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResourceNode;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.nodes.AlternativeNode;
import eu.cloudscaleproject.env.toolchain.explorer.nodes.AlternativeResourceNode;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerLabelProvider implements IStyledLabelProvider{
	
	private static final Styler DEFAULT_DECORATOR_STYLER = new Styler() {
		
		@Override
		public void applyStyles(TextStyle textStyle) {
			textStyle.foreground = ColorResources.COLOR_CS_BLUE_LIGHT;
		}
	};
	
	private static final Styler DEFAULT_STYLER = new Styler() {

		@Override
		public void applyStyles(TextStyle textStyle) {
		}
	};
	
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
	public StyledString getStyledText(Object element) {
		if(element instanceof IExplorerNode){
			IExplorerNode node = (IExplorerNode)element;
			
			StyledString currentText = null;
			
			IStyledLabelProvider styledLabelProvider = (IStyledLabelProvider)node.getAdapter(IStyledLabelProvider.class);
			if(styledLabelProvider != null){
				currentText = styledLabelProvider.getStyledText(element);
			}
			
			ILabelProvider labelProvider = (ILabelProvider)node.getAdapter(ILabelProvider.class);
			if(currentText == null && labelProvider != null){
				String text = labelProvider.getText(element);
				currentText = new StyledString(text, DEFAULT_STYLER);
			}
			
			ILabelDecorator labelDecorator = (ILabelDecorator)node.getAdapter(ILabelDecorator.class);
			if(labelDecorator != null){
				
				if(currentText == null){
					currentText = new StyledString(node.getName(), DEFAULT_STYLER);
				}
				
				String decoratedText = labelDecorator.decorateText(currentText.getString(), element);
				currentText = StyledCellLabelProvider.styleDecoratedString(decoratedText, DEFAULT_DECORATOR_STYLER, currentText);
			}
			
			if(currentText != null){
				return currentText;
			}
			
			if(element instanceof AlternativeNode){
				return new StyledString(node.getName());
			}
			if(element instanceof AlternativeResourceNode){
				return new StyledString(node.getName());
			}
			
			return new StyledString(node.getName(), DEFAULT_STYLER);
		}
		return new StyledString(element.toString(), DEFAULT_STYLER);
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
