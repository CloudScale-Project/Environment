package eu.cloudscaleproject.env.product.gui;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;

public class ButtonImg extends Composite{
	
	static Cursor handCursor = Display.getDefault().getSystemCursor(SWT.CURSOR_HAND);
	public Label img;
	
	public ButtonImg(Composite parent, Image image, int width, int height) {
		
		super(parent, SWT.NONE);

		FillLayout layout = new FillLayout();
		GridData gd_back = new GridData(GridData.HORIZONTAL_ALIGN_CENTER, GridData.VERTICAL_ALIGN_CENTER,
				false, false);
		gd_back.widthHint = width;
		gd_back.heightHint = height;
		this.setLayoutData(gd_back);
		this.setLayout(layout);
		this.setVisible(true);
		
		img = new Label(this, SWT.NONE);
		img.setImage(image);
		img.setBounds(image.getBounds());
		
		this.setCursor(handCursor);
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
