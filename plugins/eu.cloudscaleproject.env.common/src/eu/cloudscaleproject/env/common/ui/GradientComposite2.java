package eu.cloudscaleproject.env.common.ui;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.ui.util.ColorHelper;

public class GradientComposite2 extends Composite{
	
	private boolean color1Dispose = false;
	private boolean color2Dispose = false;
	
	private Color color1;
	private Color color2;
	
	boolean vertical = false;
	
	public GradientComposite2(Composite parent, int style) {
		super(parent, style);
		
		addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				if(color1 != null && color1Dispose){
					color1.dispose();
				}
				if(color2 != null && color2Dispose){
					color2.dispose();
				}
			}
		});
	}
	
	@Override
	public void drawBackground(GC gc, int x, int y, int width, int height,
			int offsetX, int offsetY) {
		
		if(color1 == null || color2 == null){
			color1 = ColorHelper.deviateValue(getBackground(), 0.0);
			color2 = ColorHelper.deviateValue(getBackground(), -0.2);
			color1Dispose = true;
			color2Dispose = true;
		}
		else{
			gc.setForeground(color1);
			gc.setBackground(color2);
		}
		
		gc.fillGradientRectangle(x + offsetX, y + offsetY, width - offsetX, height - offsetY, this.vertical);
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
