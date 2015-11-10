package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.swt.graphics.Image;

import eu.cloudscaleproject.env.common.BatchExecutor;
import eu.cloudscaleproject.env.common.IconSetResources;
import eu.cloudscaleproject.env.common.IconSetResources.SIZE;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerEditorNode;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResources;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerConstants;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.style.AbstractLabelDecorator;
import eu.cloudscaleproject.env.toolchain.explorer.ui.ExplorerImageDescriptor;
import eu.cloudscaleproject.env.toolchain.resources.types.IConfigAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.resources.types.IInputAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.IResultAlternative;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class AlternativeNode extends ExplorerEditorNode{
		
	private final IEditorInputResource alternative;

	private final PropertyChangeListener alternativeStatusListener = new PropertyChangeListener() {		
	
		@Override		
		public void propertyChange(PropertyChangeEvent evt) {
			BatchExecutor.getInstance().addTask(this, "refresh", new Runnable() {
				
				@Override
				public void run() {
					AlternativeNode.this.refresh();
				}
			});
		}
		
	};		
	
	private static final AbstractLabelDecorator DEFAULT_DECORATOR = new AbstractLabelDecorator() {
		
		@Override
		public String decorateText(String text, Object element) {
			
			if(element instanceof AlternativeNode){
				AlternativeNode node = (AlternativeNode)element;
				IEditorInputResource alternative = node.getAlternative();
				if (alternative instanceof IInputAlternative)
				{
					return text + "  [ Alternative ]";
					
				}
				else if (alternative instanceof IConfigAlternative)
				{
					return text + "  [ Configuration ]";
				}
				else if (alternative instanceof IResultAlternative)
				{
					return text + "  [ Result ]";
				}
				else {
					return text;
				}
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
				if(eir.isLoaded() && eir.isValidated()){
					if(eir.hasStatusEntry(IValidationStatus.SEVERITY_ERROR)){
						ExplorerImageDescriptor id = new ExplorerImageDescriptor(
								image, IconSetResources.getImage(IconSetResources.ERROR.withSize(SIZE.SIZE_8)));
						Image combined = id.createImage();
						return combined;
					}
					else if(eir.hasStatusEntry(IValidationStatus.SEVERITY_WARNING)){
						ExplorerImageDescriptor id = new ExplorerImageDescriptor(
								image, IconSetResources.getImage(IconSetResources.WARNING.withSize(SIZE.SIZE_8)));
						Image combined = id.createImage();
						return combined;
					}
				}
				else{
					//get status from persisted properties
					if(IEditorInputResource.VALUE_STATUS_ERROR.equals(eir.getPersistedStatus())){
						ExplorerImageDescriptor id = new ExplorerImageDescriptor(
								image, IconSetResources.getImage(IconSetResources.ERROR.withSize(SIZE.SIZE_8)));
						Image combined = id.createImage();
						return combined;
					}
					else if(IEditorInputResource.VALUE_STATUS_WARNING.equals(eir.getPersistedStatus())){
						ExplorerImageDescriptor id = new ExplorerImageDescriptor(
								image, IconSetResources.getImage(IconSetResources.WARNING.withSize(SIZE.SIZE_8)));
						Image combined = id.createImage();
						return combined;
					}
				}
			}
			return image;
		}
	}; 
	
	public AlternativeNode(IEclipseContext context, String editorID, IEditorInputResource alternative, ExplorerNodeChildren children) {
		super(context, alternative.getID(), editorID, alternative.getResource(), children);
		
		this.alternative = alternative;
		this.alternative.addStatusChangeListener(alternativeStatusListener);

		setName(alternative.getName());
		
		initIcon(alternative);
		getContext().set(ILabelDecorator.class, DEFAULT_DECORATOR);
		
		getContext().set(IExplorerConstants.NODE_DATA, alternative);
		getContext().set(IValidationStatusProvider.class, alternative);
		getContext().set(IEditorInputResource.class, alternative);
	}
	
	private IEditorInputResource getAlternative()
	{
		return alternative;
	}
	
	private void initIcon (IEditorInputResource alternative)
	{
		if (alternative instanceof IConfigAlternative)
		{
			setIcon(ExplorerResources.ALTERNATIVE_CONFIG_16, false);
		}
		else if (alternative instanceof IResultAlternative)
		{
			setIcon(ExplorerResources.ALTERNATIVE_RESULT_16, false);
		}
		else
		{
			setIcon(ExplorerResources.ALTERNATIVE_INPUT_16, false);
		}
	}
	
	@Override		
	public void dispose() {		
		this.alternative.removeStatusChangeListener(alternativeStatusListener);		
		super.dispose();		
	}

}
