package eu.cloudscaleproject.env.common.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Sash;

public class SplitComposite extends Composite{
	
	private final Sash sash;
	private int layout;

	public SplitComposite(Composite parent, int style) {
		super(parent, style);
		
		final FormLayout form = new FormLayout();
		setLayout (form);
		
		// The meanings of horizontal/vertical are switched
		// Horizontal sash means vertical split composite - splitting line is horizontal, 
		// but the composites are vertical!
		
 		layout = SWT.HORIZONTAL;
		if((style & SWT.HORIZONTAL) == SWT.HORIZONTAL){
			layout = SWT.VERTICAL;
		}
		
		sash = new Sash (this, layout);
		
		final int limit = 120, percent = 50;
		
		final FormData sashData = new FormData ();
		if(layout == SWT.HORIZONTAL){
			sashData.left = new FormAttachment (0, 0);
			sashData.right = new FormAttachment (100, 0);
			sashData.top = new FormAttachment (percent, 0);
		}
		else{
			sashData.left = new FormAttachment (percent, 0);
			sashData.top = new FormAttachment (0, 0);
			sashData.bottom = new FormAttachment (100, 0);
		}
		sash.setLayoutData (sashData);
		
		sash.addListener (SWT.Selection, new Listener () {
			@Override
			public void handleEvent (Event e) {
				Rectangle sashRect = sash.getBounds ();
				Rectangle rect = SplitComposite.this.getClientArea ();
				
				if(layout == SWT.HORIZONTAL){
					int top = rect.height - sashRect.height - limit;
					e.y = Math.max (Math.min (e.y, top), limit);
					if (e.y != sashRect.y)  {
						sashData.top = new FormAttachment (0, e.y);
						SplitComposite.this.layout ();
					}
				}
				else{
					int left = rect.width - sashRect.width - limit;
					e.x = Math.max (Math.min (e.x, left), limit);
					if (e.x != sashRect.x)  {
						sashData.left = new FormAttachment (0, e.x);
						SplitComposite.this.layout ();
					}
				}
			}
		});
		
	}
		
	public void setTopControl(Control control){
		
		FormData controlData = new FormData ();

		if(layout == SWT.HORIZONTAL){
			controlData.left = new FormAttachment (0, 0);
			controlData.right = new FormAttachment (100, 0);
			controlData.top = new FormAttachment (0, 0);
			controlData.bottom = new FormAttachment (sash, 0);
		}
		else{
			controlData.left = new FormAttachment (0, 0);
			controlData.right = new FormAttachment (sash, 0);
			controlData.top = new FormAttachment (0, 0);
			controlData.bottom = new FormAttachment (100, 0);
		}
		
		control.setLayoutData (controlData);
		
	}
	
	public void setBottomControl(Control control){
		
		FormData controlData = new FormData ();
		
		if(layout == SWT.HORIZONTAL){
			controlData.left = new FormAttachment (0, 0);
			controlData.right = new FormAttachment (100, 0);
			controlData.top = new FormAttachment (sash, 0);
			controlData.bottom = new FormAttachment (100, 0);
		}
		else{
			controlData.left = new FormAttachment (sash, 0);
			controlData.right = new FormAttachment (100, 0);
			controlData.top = new FormAttachment (0, 0);
			controlData.bottom = new FormAttachment (100, 0);
		}
		
		control.setLayoutData (controlData);
		
	}

}
