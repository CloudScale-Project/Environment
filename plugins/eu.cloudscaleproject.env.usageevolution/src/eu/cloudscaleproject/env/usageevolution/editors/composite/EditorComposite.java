package eu.cloudscaleproject.env.usageevolution.editors.composite;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative.Presets;

public class EditorComposite extends Composite{

	public EditorComposite(final UsageEvolutionAlternative alt, Composite parent, int style) {
		super(parent, style);
		
		setLayout(new GridLayout(1, false));
		
		Label lblPresets = new Label(this, SWT.NONE);
		lblPresets.setLayoutData(new GridData());
		lblPresets.setBounds(10, 10, 162, 15);
		lblPresets.setText("Usage evolution presets:");
		
		//presets
		Composite presets = new Composite(this, SWT.NONE);
		presets.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		presets.setLayout(new GridLayout(1, false));
		
		{
			Button button = new Button(presets, SWT.LEFT);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					alt.createPreset(Presets.LINEAR_TREND);
				}
			});
			button.setBounds(10, 31, 258, 27);
			button.setText("Create linear trend");
		}
		{
			Button button = new Button(presets, SWT.LEFT);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					alt.createPreset(Presets.LINEAR_INCREASE_DECLINE);
				}
			});
			button.setBounds(10, 31, 258, 27);
			button.setText("Create linear increase and decline");
		}
		{
			Button button = new Button(presets, SWT.LEFT);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					alt.createPreset(Presets.SINUSOIDAL);
				}
			});
			button.setBounds(10, 31, 258, 27);
			button.setText("Create sinusoidal");
		}
		{
			Button button = new Button(presets, SWT.LEFT);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					alt.createPreset(Presets.SINUSOIDAL_TREND);
				}
			});
			button.setBounds(10, 31, 258, 27);
			button.setText("Create sinusoidal trend");
		}
		{
			Button button = new Button(presets, SWT.LEFT);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					alt.createPreset(Presets.EXPONENTIAL);
				}
			});
			button.setBounds(10, 31, 258, 27);
			button.setText("Create explonential trend");
		}
		{
			Button button = new Button(presets, SWT.LEFT);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					alt.createPreset(Presets.EXPONENTIAL_LOG);
				}
			});
			button.setBounds(10, 31, 258, 27);
			button.setText("Create explonential increase and log decline");
		}
		
		//actions
		Label lblActions = new Label(this, SWT.NONE);
		lblActions.setLayoutData(new GridData());
		lblActions.setText("Actions:");
		
		Button btnLimboEditor = new Button(this, SWT.NONE);
		btnLimboEditor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				alt.openLimboEditor();
			}
		});
		btnLimboEditor.setLayoutData(new GridData());
		btnLimboEditor.setText("Edit Limbo model");
		
		
	}
}
