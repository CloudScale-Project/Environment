package eu.cloudscaleproject.env.common;

import org.eclipse.swt.graphics.Color;

import eu.cloudscaleproject.env.common.ui.resources.SWTResourceManager;

public class ColorResources
{
	public static Color COLOR_BLACK = SWTResourceManager.getColor(0,0,0);
	public static Color COLOR_WHITE = SWTResourceManager.getColor(255,255,255);
	
	public static Color COLOR_CS_BLUE_DARK = SWTResourceManager.getColor(44,78,107);
	public static Color COLOR_CS_BLUE = SWTResourceManager.getColor(108, 142, 171);
	public static Color COLOR_CS_BLUE_LIGHT = SWTResourceManager.getColor(159, 181, 200);

	public static Color COLOR_ORANGE = SWTResourceManager.getColor(255, 127, 0);
	public static Color COLOR_RED = SWTResourceManager.getColor(255, 22, 22);
	
	public static Color getColor(int r, int g, int b){
		return SWTResourceManager.getColor(r, g, b);
	}
}
