package eu.cloudscaleproject.env.usageevolution.editors.composite;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;

public class EditorComposite extends Composite{

	public EditorComposite(final UsageEvolutionAlternative alt, Composite parent, int style) {
		super(parent, style);
		
		Label lblPresets = new Label(this, SWT.NONE);
		lblPresets.setBounds(10, 10, 162, 15);
		lblPresets.setText("Usage evolution presets:");
		
		Button buttonEild = new Button(this, SWT.NONE);
		buttonEild.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				alt.createEILDPreset();
			}
		});
		buttonEild.setBounds(10, 31, 258, 27);
		buttonEild.setText("Exponential increase logarithmic decline");
		
		Button btnOpenUsageEditor = new Button(this, SWT.NONE);
		btnOpenUsageEditor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				alt.openUsageEvolutionEditor();
			}
		});
		btnOpenUsageEditor.setBounds(10, 102, 179, 27);
		btnOpenUsageEditor.setText("Edit Usage evolution model");
		
		Label lblActions = new Label(this, SWT.NONE);
		lblActions.setBounds(10, 81, 54, 15);
		lblActions.setText("Actions:");
		
		Button btnLimboEditor = new Button(this, SWT.NONE);
		btnLimboEditor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				alt.openLimboEditor();
			}
		});
		btnLimboEditor.setBounds(10, 135, 179, 27);
		btnLimboEditor.setText("Edit Limbo model");
		
		
	}
}
