package eu.cloudscaleproject.env.common;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import eu.cloudscaleproject.env.common.ui.resources.SWTResourceManager;

public class CommonResources {

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
	
	public static Color COLOR_BLACK = SWTResourceManager.getColor(0,0,0);
	public static Color COLOR_WHITE = SWTResourceManager.getColor(255,255,255);
	
	public static Color COLOR_CS_BLUE_DARK = SWTResourceManager.getColor(44,78,107);
	public static Color COLOR_CS_BLUE = SWTResourceManager.getColor(108, 142, 171);
	public static Color COLOR_CS_BLUE_LIGHT = SWTResourceManager.getColor(159, 181, 200);
	
	public static Color getColor(int r, int g, int b){
		return SWTResourceManager.getColor(r, g, b);
	}
}
