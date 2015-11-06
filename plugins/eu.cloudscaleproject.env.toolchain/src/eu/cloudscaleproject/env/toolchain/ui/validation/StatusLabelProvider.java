package eu.cloudscaleproject.env.toolchain.ui.validation;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatus.Warning;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.toolchain.CSToolResource;

public class StatusLabelProvider implements ILabelProvider{
	
	private final Image infoImage;
	private final Image warningImage;
	private final Image errorImage;

	public StatusLabelProvider() {
		IWorkbench workbench = PlatformUI.getWorkbench();

		//infoImage = workbench.getSharedImages().getImageDescriptor(ISharedImages.IMG_LCL_LINKTO_HELP).createImage();
		//warningImage = workbench.getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING).createImage();
		//errorImage = workbench.getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR).createImage();

		infoImage = workbench.getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK).createImage();
		warningImage = workbench.getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_WARN_TSK).createImage();
		errorImage = workbench.getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_ERROR_TSK).createImage();
	}
	
	@Override
	public void addListener(ILabelProviderListener listener) {
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return true;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {		
	}

	@Override
	public Image getImage(Object element) {
		
		if(element instanceof WarningNode){
			Warning w = ((WarningNode)element).getWarning();			
			switch(w.severity){
				case IValidationStatus.SEVERITY_INFO:
					return infoImage;
				case IValidationStatus.SEVERITY_WARNING:
					return warningImage;
				case IValidationStatus.SEVERITY_ERROR:
					return errorImage;
			}
		}
		
		if(element instanceof String){
			return infoImage; 
		}
		
		return null;
	}

	@Override
	public String getText(Object element) {
		if(element instanceof IValidationStatusProvider){
			IValidationStatusProvider sp = (IValidationStatusProvider)element;
			String tool = CSToolResource.getToolName(sp.getID());
			if(tool == null){
				tool = "Unknown";
			}
			
			return String.format("%s [%s]", sp.getSelfStatus().getName(), tool);
		}
		if(element instanceof IValidationStatus){
			IValidationStatus stat = (IValidationStatus)element;
			return stat.getName();
		}
		if(element instanceof WarningNode){
			Warning w = ((WarningNode)element).getWarning();
			return w.message;
		}
		return element.toString();
	}

	@Override
	public void dispose() {
		infoImage.dispose();
		warningImage.dispose();
		errorImage.dispose();
	}
}
