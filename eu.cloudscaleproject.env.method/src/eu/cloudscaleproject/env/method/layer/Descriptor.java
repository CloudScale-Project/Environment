package eu.cloudscaleproject.env.method.layer;

import org.eclipse.swt.graphics.Rectangle;

public class Descriptor {

	private String name;
	private String helpResource;
	private Rectangle rect;
	
	public Descriptor(Rectangle rect, String name, String helpResource) {
		this.rect = rect;
		this.name = name;
		this.helpResource = helpResource;
	}
	
	public Rectangle getRect() {
		return rect;
	}
	public String getName() {
		return name;
	}
	public String getHelpResource() {
		return helpResource;
	}
	
}
