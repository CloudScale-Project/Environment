package eu.cloudscaleproject.env.product.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;

public class ButtonImg {
	
	public Label img;
	
	public ButtonImg(Composite parent, Image image, int width, int height) {
		
		Composite c_back = new Composite(parent, SWT.NONE);
		FillLayout layout = new FillLayout();
		GridData gd_back = new GridData(GridData.HORIZONTAL_ALIGN_CENTER, GridData.VERTICAL_ALIGN_CENTER,
				false, false);
		gd_back.widthHint = width;
		gd_back.heightHint = height;
		c_back.setLayoutData(gd_back);
		c_back.setLayout(layout);
		c_back.setVisible(true);
		
		img = new Label(c_back, SWT.NONE);
		img.setImage(image);
		img.setBounds(image.getBounds());
	}
	
	public void addSelectionListener(final SelectionListener listener){
		
		img.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				Event event = new Event();
				event.type = SWT.Selection;
				event.widget = img; 
				listener.widgetSelected(new SelectionEvent(event));
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
