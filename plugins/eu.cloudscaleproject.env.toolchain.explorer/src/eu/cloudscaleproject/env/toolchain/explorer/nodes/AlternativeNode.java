package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.swt.graphics.Image;

import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerEditorNode;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResources;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.style.AbstractLabelDecorator;
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
				return text + " [ Alternative ]";
			}
			if(element instanceof AlternativeResourceNode){
				AlternativeResourceNode node = (AlternativeResourceNode)element;
				if(node.getContext().get(Resource.class) != null){
					return text + " [ EMF Model ]";
				}
			}
			
			return text;
		}
		
		@Override
		public Image decorateImage(Image image, Object element) {
			return null;
		}
	}; 
	
	public AlternativeNode(IEclipseContext context, String editorID, IEditorInputResource alternative) {
		this(context, editorID, alternative, null);
	}
	
	public AlternativeNode(IEclipseContext context, String editorID, IEditorInputResource alternative, IExplorerNodeChildren children) {
		super(context, alternative.getID(), editorID, alternative.getResource(), children);
		
		setName(alternative.getName());
		setIcon(ExplorerResources.ALTERNATIVE_16, false);
		
		getContext().set(ILabelDecorator.class, DEFAULT_DECORATOR);
		
		getContext().set(IValidationStatusProvider.class, alternative);
		getContext().set(IEditorInputResource.class, alternative);
	}

}
