package org.scaledl.overview.properties;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class ImageResource {

	public static Image EDITABLE = AbstractUIPlugin.imageDescriptorFromPlugin("org.scaledl.overview.properties", "icons/edit.png").createImage();
	public static Image LIST = AbstractUIPlugin.imageDescriptorFromPlugin("org.scaledl.overview.properties", "icons/list.gif").createImage();
	public static Image ENTITY = AbstractUIPlugin.imageDescriptorFromPlugin("org.scaledl.overview.properties", "icons/entity.png").createImage();

	public static Image DEPLOYED_ON = AbstractUIPlugin.imageDescriptorFromPlugin("org.scaledl.overview.properties", "icons/deployed_on.png").createImage();

	public static Image LAYER = AbstractUIPlugin.imageDescriptorFromPlugin("org.scaledl.overview.properties", "icons/layer.png").createImage();
	public static Image INFRASTRUCTURE_SERVICE = AbstractUIPlugin.imageDescriptorFromPlugin("org.scaledl.overview.properties", "icons/infrastructure_service.png").createImage();
	public static Image PLATFORM_SERVICE = AbstractUIPlugin.imageDescriptorFromPlugin("org.scaledl.overview.properties", "icons/platform_service.png").createImage();
	public static Image SOFTWARE_SERVICE = AbstractUIPlugin.imageDescriptorFromPlugin("org.scaledl.overview.properties", "icons/software_service.png").createImage();
	
	public static Image INTERFACE = AbstractUIPlugin.imageDescriptorFromPlugin("org.scaledl.overview.properties", "icons/interface.png").createImage();
	public static Image INTERFACE_LIST = AbstractUIPlugin.imageDescriptorFromPlugin("org.scaledl.overview.properties", "icons/interface_list.png").createImage();
	public static Image OPERATION = AbstractUIPlugin.imageDescriptorFromPlugin("org.scaledl.overview.properties", "icons/operation.png").createImage();
	
}
