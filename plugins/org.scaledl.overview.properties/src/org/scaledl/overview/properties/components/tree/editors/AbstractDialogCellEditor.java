package org.scaledl.overview.properties.components.tree.editors;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.scaledl.overview.properties.components.tree.TreeCell;

public abstract class AbstractDialogCellEditor extends DialogCellEditor{

	private final TreeCell cell;
	private Label label;
	
	public AbstractDialogCellEditor(Composite parent, TreeCell cell, int style){
		super(parent, style);
		this.cell = cell;
	}

	public AbstractDialogCellEditor(Composite parent, TreeCell cell){
		super(parent);
		this.cell = cell;
	}
	
	@Override
	protected Control createContents(Composite cell) {
        label = new Label(cell, SWT.LEFT);
        label.setFont(cell.getFont());
        label.setBackground(cell.getBackground());
        return label;
	}
	
	@Override
	protected Label getDefaultLabel() {
		return label;
	}
	
	@Override
	protected void updateContents(Object value) {
		if(cell != null){
			label.setText(cell.getText());
		}
	}
	
	@Override
	protected Button createButton(Composite parent) {
		return super.createButton(parent);
	}

	@Override
	protected void doSetFocus() {
		super.doSetFocus();
	}
}
