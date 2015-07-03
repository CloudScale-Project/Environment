package eu.cloudscaleproject.env.common.notification.views;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatus.Warning;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;

public class StatusLabelProvider implements ILabelProvider{
	
	private final Image infoImage;
	private final Image warningImage;
	private final Image errorImage;

	public StatusLabelProvider() {
		IWorkbench workbench = PlatformUI.getWorkbench();

		infoImage = workbench.getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING).createImage();
		warningImage = workbench.getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING).createImage();
		errorImage = workbench.getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR).createImage();
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
		if(element instanceof Warning){
			Warning w = (Warning)element;			
			switch(w.severity){
				case IValidationStatus.SEVERITY_INFO:
					return infoImage;
				case IValidationStatus.SEVERITY_WARNING:
					return warningImage;
				case IValidationStatus.SEVERITY_ERROR:
					return errorImage;
			}
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		if(element instanceof IValidationStatusProvider){
			IValidationStatusProvider sp = (IValidationStatusProvider)element;
			return sp.getSelfStatus().getName();
		}
		if(element instanceof IValidationStatus){
			IValidationStatus stat = (IValidationStatus)element;
			return stat.getName();
		}
		if(element instanceof Warning){
			Warning w = (Warning)element;
			return w.message;
		}
		return "";
	}

	@Override
	public void dispose() {
		infoImage.dispose();
		warningImage.dispose();
		errorImage.dispose();
	}
}
