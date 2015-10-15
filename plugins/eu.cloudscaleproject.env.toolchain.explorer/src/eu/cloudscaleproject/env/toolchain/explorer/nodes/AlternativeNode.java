package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.swt.graphics.Image;

import eu.cloudscaleproject.env.common.CommonResources;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerEditorNode;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResources;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.style.AbstractLabelDecorator;
import eu.cloudscaleproject.env.toolchain.explorer.ui.ExplorerImageDescriptor;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class AlternativeNode extends ExplorerEditorNode{

	private static final AbstractLabelDecorator DEFAULT_DECORATOR = new AbstractLabelDecorator() {
		
		@Override
		public String decorateText(String text, Object element) {
			
			if(element instanceof AlternativeNode){
				AlternativeNode node = (AlternativeNode)element;
				CSTool tool = CSTool.getTool(node.getID());
				return text + " [ "+tool.getName()+" ]";
			}
			if(element instanceof AlternativeResourceNode){
				AlternativeResourceNode node = (AlternativeResourceNode)element;
				
				IResource resource = node.getResource();
				if(resource instanceof IFile){
					
					ModelType type = ModelType.getModelType(resource.getFileExtension());
					if(type != null){
						return text + " [ EMF Model ]";
					}
					
				}
			}
			
			return text;
		}
		
		@Override
		public Image decorateImage(Image image, Object element) {
			IEditorInputResource eir = null;
			if(element instanceof IExplorerNode){
				eir = ((IExplorerNode) element).getContext().getLocal(IEditorInputResource.class);
			}
			if(eir != null){
				if(!eir.getSelfStatus().isDone()){
					ExplorerImageDescriptor id = new ExplorerImageDescriptor(image, CommonResources.ERROR_8);
					Image combined = id.createImage();
					return combined;
				}
			}
			return image;
		}
	}; 
	
	private final PropertyChangeListener alternativeListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			AlternativeNode.this.refresh();
		}
	};
	
	private final IEditorInputResource alternative;
	
	public AlternativeNode(IEclipseContext context, String editorID, IEditorInputResource alternative, ExplorerNodeChildren children) {
		super(context, alternative.getID(), editorID, alternative.getResource(), children);
		
		this.alternative = alternative;
		this.alternative.addPropertyChangeListener(alternativeListener);
				
		setName(alternative.getName());
		setIcon(ExplorerResources.ALTERNATIVE_16, false);
		
		getContext().set(ILabelDecorator.class, DEFAULT_DECORATOR);
		
		getContext().set(IValidationStatusProvider.class, alternative);
		getContext().set(IEditorInputResource.class, alternative);
	}

	@Override
	public void dispose() {
		this.alternative.removePropertyChangeListener(alternativeListener);
		super.dispose();
	}
}
