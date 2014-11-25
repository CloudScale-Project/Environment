package eu.cloudscaleproject.env.common;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import eu.cloudscaleproject.env.common.ui.resources.SWTResourceManager;

public class CommonResources {

	public static Image EDITABLE = AbstractUIPlugin.imageDescriptorFromPlugin("eu.cloudscaleproject.env.common", "icons/common/edit.png").createImage();
	public static Image LIST = AbstractUIPlugin.imageDescriptorFromPlugin("eu.cloudscaleproject.env.common", "icons/common/list.gif").createImage();
	public static Image ENTITY = AbstractUIPlugin.imageDescriptorFromPlugin("eu.cloudscaleproject.env.common", "icons/common/entity.png").createImage();

	public static Image DEPLOYED_ON = AbstractUIPlugin.imageDescriptorFromPlugin("eu.cloudscaleproject.env.common", "icons/common/deployed_on.png").createImage();

	public static Image LAYER = AbstractUIPlugin.imageDescriptorFromPlugin("eu.cloudscaleproject.env.common", "icons/common/layer.png").createImage();
	public static Image INFRASTRUCTURE_SERVICE = AbstractUIPlugin.imageDescriptorFromPlugin("eu.cloudscaleproject.env.common", "icons/common/infrastructure_service.png").createImage();
	public static Image PLATFORM_SERVICE = AbstractUIPlugin.imageDescriptorFromPlugin("eu.cloudscaleproject.env.common", "icons/common/platform_service.png").createImage();
	public static Image SOFTWARE_SERVICE = AbstractUIPlugin.imageDescriptorFromPlugin("eu.cloudscaleproject.env.common", "icons/common/software_service.png").createImage();
	
	public static Image INTERFACE = AbstractUIPlugin.imageDescriptorFromPlugin("eu.cloudscaleproject.env.common", "icons/common/interface.png").createImage();
	public static Image INTERFACE_LIST = AbstractUIPlugin.imageDescriptorFromPlugin("eu.cloudscaleproject.env.common", "icons/common/interface_list.png").createImage();
	public static Image OPERATION = AbstractUIPlugin.imageDescriptorFromPlugin("eu.cloudscaleproject.env.common", "icons/common/operation.png").createImage();
	
	public static Color COLOR_BLACK = SWTResourceManager.getColor(0,0,0);
	public static Color COLOR_WHITE = SWTResourceManager.getColor(255,255,255);
	
	public static Color COLOR_CS_BLUE_DARK = SWTResourceManager.getColor(44,78,107);
	public static Color COLOR_CS_BLUE = SWTResourceManager.getColor(108, 142, 171);
	public static Color COLOR_CS_BLUE_LIGHT = SWTResourceManager.getColor(159, 181, 200);
	
	public static Color getColor(int r, int g, int b){
		return SWTResourceManager.getColor(r, g, b);
	}
}
