package eu.cloudscaleproject.env.common.ui;

import java.util.HashSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;

import eu.cloudscaleproject.env.common.ui.resources.SWTResourceManager;

public class HoverToggleButton extends Composite{
	
	private Color background = SWTResourceManager.getColor(159, 181, 200);
	private Color backgroundHover = SWTResourceManager.getColor(179, 201, 220);
	private Color backgroundSelected = SWTResourceManager.getColor(139, 161, 180);
	
	private Color foreground = SWTResourceManager.getColor(44,78,107);
	private Color foregroundHover = SWTResourceManager.getColor(44,78,107);
	private Color foregroundSelected = SWTResourceManager.getColor(44,78,107);

	private final GridLayout layout = new GridLayout(1, false);
	private final GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
	
	private final Label label;
	private final MouseHandler mouseHandler = new MouseHandler();
	private HashSet<SelectionListener> selectionListeners = new HashSet<SelectionListener>();
	
	public boolean isHover = false;
	public boolean isSelected = false;

	public HoverToggleButton(Composite parent, int style) {
		super(parent, style);
		this.setLayout(layout);
		
		label = new Label(this, SWT.NONE);
		label.setLayoutData(layoutData);
		
		doSetBackground(background);
		doSetForeground(foreground);
		
		label.addMouseListener(mouseHandler);
		label.addMouseTrackListener(mouseHandler);
		
		this.addMouseListener(mouseHandler);
		this.addMouseTrackListener(mouseHandler);
	}
	
	@Override
	public void redraw() {
		label.redraw();
		this.layout(true);
		super.redraw();
	}
	
	@Override
	public void setBackground(Color color) {
		background = color;
		if(!isSelected && !isHover){
			doSetBackground(background);
		}
	}
	
	public void setBackgroundHover(Color color) {
		backgroundHover = color;
		if(isHover){
			doSetBackground(backgroundHover);
		}
	}
	
	public void setBackgroundSelected(Color color) {
		backgroundSelected = color;
		if(isSelected){
			doSetBackground(backgroundSelected);
		}
	}
	
	@Override
	public void setForeground(Color color) {
		foreground = color;
		if(!isSelected && !isHover){
			doSetForeground(foreground);
		}
	}
	
	public void setForegroundHover(Color color) {
		foregroundHover = color;
		if(isHover){
			doSetBackground(foregroundHover);
		}
	}
	
	public void setForegroundSelected(Color color) {
		foregroundSelected = color;
		if(isSelected){
			doSetBackground(foregroundSelected);
		}
	}
	
	public void doSetBackground(Color color) {
		label.setBackground(color);
		super.setBackground(color);
	}
	
	public void doSetForeground(Color color) {
		label.setForeground(color);
		super.setForeground(color);
	}
	
	public void setText(String text){
		label.setText(text);
	}
	
	public void setAlignmentHorizontal(int i){
		layoutData.horizontalAlignment = i;
		this.layout(true);
	}
	
	public void setAlignmentVertical(int i){
		layoutData.verticalAlignment = i;
		this.layout(true);
	}
	
	public void setIndentHorizontal(int i){
		layoutData.horizontalIndent = i;
		this.layout(true);
	}
	
	public void setIndebtVertical(int i){
		layoutData.verticalIndent = i;
		this.layout(true);
	}
	
	public void setSelection(boolean select){
		if(select){
			doSetBackground(backgroundSelected);
			doSetForeground(foregroundSelected);
		}
		else{
			doSetBackground(background);
			doSetForeground(foreground);
		}
		this.isSelected = select;
	}
		
	public void addSelectionListener(SelectionListener listener){
		selectionListeners.add(listener);
	}
	
	public void removeSelectionListener(SelectionListener listener){
		selectionListeners.remove(listener);
	}
	
	private class MouseHandler implements MouseTrackListener, MouseListener{
		
		@Override
		public void mouseHover(MouseEvent e) {				
		}
		
		@Override
		public void mouseExit(MouseEvent e) {
			if(!HoverToggleButton.this.isSelected){
				doSetBackground(background);
				doSetForeground(foreground);
				redraw();
			}
		}
		
		@Override
		public void mouseEnter(MouseEvent e) {
			if(!HoverToggleButton.this.isSelected){
				doSetBackground(backgroundHover);
				doSetForeground(foregroundHover);
				redraw();
			}
		}
		
		@Override
		public void mouseUp(MouseEvent e) {
			for(SelectionListener l : selectionListeners){
				
				Event ev = new Event();
				ev.widget = HoverToggleButton.this;
				ev.item = HoverToggleButton.this;
				ev.display = e.display;
				
				SelectionEvent se = new SelectionEvent(ev);
				se.display = e.display;
				se.doit = true;
				se.height = HoverToggleButton.this.getSize().y;
				se.item = HoverToggleButton.this;
				se.text = label.getText();
				se.widget = HoverToggleButton.this;
				se.width = HoverToggleButton.this.getSize().x;
				se.x = HoverToggleButton.this.getLocation().x;
				se.y = HoverToggleButton.this.getLocation().y;
				
				l.widgetSelected(se);
			}
			setSelection(true);
		}
		
		@Override
		public void mouseDown(MouseEvent e) {				
		}
		
		@Override
		public void mouseDoubleClick(MouseEvent e) {				
		}
	}

}
