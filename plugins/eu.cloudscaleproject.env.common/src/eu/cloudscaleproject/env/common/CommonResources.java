package eu.cloudscaleproject.env.common;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class CommonResources
{

	static
	{
		EDITABLE = loadImage("edit.png");
		LIST = loadImage("list.gif");
		ENTITY = loadImage("entity.png");
		DEPLOYED_ON = loadImage("deployed_on.png");
		LAYER = loadImage("layer.png");
		INFRASTRUCTURE_SERVICE = loadImage("infrastructure_service.png");
		PLATFORM_SERVICE = loadImage("platform_service.png");
		SOFTWARE_SERVICE = loadImage("software_service.png");
		INTERFACE = loadImage("interface.png");
		INTERFACE_LIST = loadImage("interface_list.png");
		OPERATION = loadImage("operation.png");

		OK = loadImage("ok_32.png");
		WARNING = loadImage("warning_32.png");
		ERROR = loadImage("error_32.png");
		THUMB_UP = loadImage("thumbup_32.png");
		
		CHART_PIE = loadImage("chart_pie.png");
		CHART_HISTOGRAM = loadImage("chart_histogram.png");
		CHART_LINE = loadImage("chart_line.png");
		
		CHART_PIE_16 = loadImage("chart_pie_16.png");
		CHART_HISTOGRAM_16 = loadImage("chart_histogram_16.png");
		CHART_LINE_16 = loadImage("chart_line_16.png");
	}

	private static Image loadImage(String name)
	{
		String plugin = "eu.cloudscaleproject.env.common";
		String container = "icons/common/";
		return AbstractUIPlugin.imageDescriptorFromPlugin(plugin, container + name).createImage();
	}

	public static Image EDITABLE;
	public static Image LIST;
	public static Image ENTITY;
	public static Image DEPLOYED_ON;
	public static Image LAYER;
	public static Image INFRASTRUCTURE_SERVICE;
	public static Image PLATFORM_SERVICE;
	public static Image SOFTWARE_SERVICE;

	public static Image INTERFACE;
	public static Image INTERFACE_LIST;
	public static Image OPERATION;

	// Validation
	public static Image OK;
	public static Image WARNING;
	public static Image ERROR;
	public static Image THUMB_UP;
	
	// Diagrams
	public static Image CHART_PIE;
	public static Image CHART_HISTOGRAM;
	public static Image CHART_LINE;
	
	public static Image CHART_PIE_16;
	public static Image CHART_HISTOGRAM_16;
	public static Image CHART_LINE_16;


}
