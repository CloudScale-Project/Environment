package eu.cloudscaleproject.env.usageevolution.wizard.pages;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative.Presets;

public class PresetSelectionPage extends WizardPage{
	
	private Presets selectedPreset = null;

	public PresetSelectionPage() {
		super("Select arrival rate preset");
		setTitle("Select arrival rate preset");
		setDescription("Please select initial arrival rate preset.");
		
		setPageComplete(false);
	}
	
	public Presets getSelectedPreset(){
		return selectedPreset;
	}

	@Override
	public void createControl(Composite parent) {
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblPresets = new Label(composite, SWT.NONE);
		lblPresets.setLayoutData(new GridData());
		lblPresets.setBounds(10, 10, 162, 15);
		lblPresets.setText("Usage evolution presets:");
		
		//presets
		Composite presets = new Composite(composite, SWT.NONE);
		presets.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		presets.setLayout(new GridLayout(1, false));
		
		{
			Button button = new Button(presets, SWT.LEFT | SWT.RADIO);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					selectedPreset = Presets.LINEAR_TREND;
					setPageComplete(true);
				}
			});
			button.setBounds(10, 31, 258, 27);
			button.setText("Create linear trend");
		}
		{
			Button button = new Button(presets, SWT.LEFT | SWT.RADIO);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					selectedPreset = Presets.LINEAR_INCREASE_DECLINE;
					setPageComplete(true);
				}
			});
			button.setBounds(10, 31, 258, 27);
			button.setText("Create linear increase and decline");
		}
		{
			Button button = new Button(presets, SWT.LEFT | SWT.RADIO);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					selectedPreset = Presets.SINUSOIDAL;
					setPageComplete(true);
				}
			});
			button.setBounds(10, 31, 258, 27);
			button.setText("Create sinusoidal");
		}
		{
			Button button = new Button(presets, SWT.LEFT | SWT.RADIO);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					selectedPreset = Presets.SINUSOIDAL_TREND;
					setPageComplete(true);
				}
			});
			button.setBounds(10, 31, 258, 27);
			button.setText("Create sinusoidal trend");
		}
		{
			Button button = new Button(presets, SWT.LEFT | SWT.RADIO);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					selectedPreset = Presets.EXPONENTIAL;
					setPageComplete(true);
				}
			});
			button.setBounds(10, 31, 258, 27);
			button.setText("Create explonential trend");
		}
		{
			Button button = new Button(presets, SWT.LEFT | SWT.RADIO);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					selectedPreset = Presets.EXPONENTIAL_LOG;
					setPageComplete(true);
				}
			});
			button.setBounds(10, 31, 258, 27);
			button.setText("Create explonential increase and log decline");
		}
		
		setControl(composite);
	}

}
