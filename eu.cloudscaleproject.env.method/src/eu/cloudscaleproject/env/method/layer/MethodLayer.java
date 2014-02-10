package eu.cloudscaleproject.env.method.layer;


import java.awt.geom.AffineTransform;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.method.SWT2DUtil;

public class MethodLayer {
	
	private Canvas canvas;
	Device device = Display.getCurrent ();
	Color red = new Color (device, 0, 0, 240);
	private AffineTransform transform;
	Point mouseLocation = new Point (0,0);
	
	Cursor helpCursor = new Cursor(device, SWT.CURSOR_HELP);
	
	public MethodLayer(Canvas c, AffineTransform transform) {
		
		this.transform = transform;
		this.canvas = c;
		
		c.addMouseMoveListener(new MouseMoveListener() {
			
			@Override
			public void mouseMove(MouseEvent e) {
				mouseLocation.x = e.x;
				mouseLocation.y = e.y;
				canvas.redraw();
				
				Descriptor desc = getDescriptorAt(mouseLocation);
				
				if (desc!=null)
				{
					canvas.getShell().setCursor(helpCursor);
				}
				else
				{
					canvas.getShell().setCursor(null);
				}
			}
		});
		
		c.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				Descriptor desc = getDescriptorAt(mouseLocation);
				if (desc == null) return;
				
				if (e.button == 1)
				{
					System.out.println("Displaying help: "+desc.getName()+"w/ res: "+desc.getHelpResource());
					PlatformUI.getWorkbench().getHelpSystem().
						displayHelpResource(desc.getHelpResource());
					
				}
			}
		});
	}
	
	public void paint (GC gc)
	{
		gc.setForeground(red);
		gc.setLineWidth(2);
		
		Descriptor desc = getDescriptorAt(mouseLocation);
		if (desc != null)
		{
			Rectangle r = SWT2DUtil.transformRect(transform, desc.getRect());
			gc.drawRectangle(r);
		}
		
	}
	
	public Descriptor getDescriptorAt (Point loc)
	{
		Point locSource = SWT2DUtil.inverseTransformPoint(transform, loc);
		for (Descriptor desc : MethodDescriptors.getDescriptors()) {
			
			
			if (desc.getRect().contains(locSource))
				return desc;
		}
		
		return null;
	}
	
	

}
