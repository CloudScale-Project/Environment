package eu.cloudscaleproject.env.common.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import eu.cloudscaleproject.env.common.ui.util.ColorHelper;
import eu.cloudscaleproject.env.common.ui.util.GradientHelper;

public class GradientComposite extends Composite{

	private Image image;
	
	private boolean color1Dispose = false;
	private boolean color2Dispose = false;
	
	private Color color1 = null;
	private Color color2 = null;
	
	boolean vertical = false;
	
	public GradientComposite(Composite parent, int style) {
		super(parent, style);
		
		addListener(SWT.Resize, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				
				if (image != null) {
					image.dispose();
				}
				
				if(color1 == null || color2 == null){
					
					if(color1 != null && color1Dispose){
						color1.dispose();
					}
					if(color2 != null && color2Dispose){
						color2.dispose();
					}
					
					color1 = ColorHelper.deviateValue(getBackground(), -0.05);
					color2 = ColorHelper.deviateValue(getBackground(), 0.0);
					color1Dispose = true;
					color2Dispose = true;
				}

				image = GradientHelper.createGradientImageFor(GradientComposite.this,
						color1,
						color2,
						vertical);
				
				GradientComposite.this.setBackgroundImage(image);
			}
		});
		
		addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				if(image != null){
					image.dispose();
				}
				if(color1 != null && color1Dispose){
					color1.dispose();
				}
				if(color2 != null && color2Dispose){
					color2.dispose();
				}
			}
		});
	}
	
	public void setGradientDirection(boolean vertical){
		this.vertical = vertical;
		this.layout();
		this.redraw();
	}
	
	public void setGradientColorStart(int r, int g, int b){
		if(color1 != null && color1Dispose){
			color1.dispose();
		}
		
		color1 = new Color(Display.getDefault(), r, g, b);
		color1Dispose = true;
		this.layout();
		this.redraw();
	}
	
	public void setGradientColorEnd(int r, int g, int b){
		if(color2 != null && color2Dispose){
			color2.dispose();
		}
		
		color2 = new Color(Display.getDefault(), r, g, b);
		color2Dispose = true;
		this.layout();
		this.redraw();
	}
	
	public void setGradientColorStart(Color c){
		if(color1 != null && color1Dispose){
			color1.dispose();
		}
		
		color1 = c;
		color1Dispose = false;
		this.layout();
		this.redraw();
	}
	
	public void setGradientColorEnd(Color c){
		if(color2 != null && color2Dispose){
			color2.dispose();
		}
		
		color2 = c;
		color2Dispose = false;
		this.layout();
		this.redraw();
	}
}
