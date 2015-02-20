package eu.cloudscaleproject.env.analyser.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.BasicCallback;

public class NewConfigInputDialog extends Dialog{
	
	private final BasicCallback<String[]> callback;
	
	private Text text = null;
	private CCombo combo = null;
	
	public NewConfigInputDialog(Shell parentShell, BasicCallback<String[]> callback) {
		super(parentShell);
		this.setBlockOnOpen(true);
		this.callback = callback;
		
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 2;
		
		Label lblNewAlternativeName = new Label(container, SWT.NONE);
		lblNewAlternativeName.setText("New alternative name:");
		
		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblAlternativeType = new Label(container, SWT.NONE);
		lblAlternativeType.setText("Alternative type:");
		
		combo = new CCombo(container, SWT.BORDER);
		GridData gd_combo = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 115;
		combo.setLayoutData(gd_combo);
		
		for(ConfAlternative.Type type : ConfAlternative.Type.values()){
			combo.add(type.name());
		}
		combo.select(0);
		
		return container;
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if (IDialogConstants.OK_ID == buttonId) {
			callback.handle(new String[]{getText(), combo.getItem(combo.getSelectionIndex())});
		}
		super.buttonPressed(buttonId);
	}
	
	private String getText(){
		return (text != null && !text.isDisposed()) ? text.getText() : "No name";
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("New alternative name");
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(450, 200);
	}
}