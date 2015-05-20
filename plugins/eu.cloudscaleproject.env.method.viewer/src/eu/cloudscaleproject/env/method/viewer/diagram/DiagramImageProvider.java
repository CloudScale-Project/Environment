package eu.cloudscaleproject.env.method.viewer.diagram;

import org.eclipse.graphiti.platform.IPlatformImageConstants;
import org.eclipse.graphiti.ui.platform.AbstractImageProvider;
import org.eclipse.graphiti.ui.platform.IImageProvider;

public class DiagramImageProvider extends AbstractImageProvider implements IImageProvider{

	// The prefix for all identifiers of this image provider
	protected static final String PREFIX = "eu.cloudscaleproject.env.method.viewer.diagram"; 

	// The image identifier for an EReference.
	public static final String IMG_DONE = PREFIX + "done";
	public static final String IMG_WARNING = PREFIX + "warning";
	public static final String IMG_MISSING = IMG_WARNING;
	public static final String IMG_REFRESH = IPlatformImageConstants.IMG_EDIT_REFRESH;
	public static final String IMG_NOT_DONE = IPlatformImageConstants.IMG_ECLIPSE_QUICKASSIST;

	public static final String IMG_DONE_SMALL = PREFIX + "done.small";
	public static final String IMG_WARNING_SMALL = PREFIX + "warning.small";
	public static final String IMG_MISSING_SMALL = IMG_WARNING_SMALL;


	@Override
	protected void addAvailableImages() {
		addImageFilePath(IMG_DONE, "icons/decorator/done_16.png"); //$NON-NLS-1$
		addImageFilePath(IMG_WARNING, "icons/decorator/warning_16.png"); //$NON-NLS-1$

		addImageFilePath(IMG_DONE_SMALL, "icons/decorator/done_8.png"); //$NON-NLS-1$
		addImageFilePath(IMG_WARNING_SMALL, "icons/decorator/warning_8.png"); //$NON-NLS-1$
	}

}
