package eu.cloudscaleproject.env.extractor.editors.composites;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import eu.cloudscaleproject.env.extractor.ConfigPersistenceFolder;
import eu.cloudscaleproject.env.extractor.InputPersitenceFile;
import eu.cloudscaleproject.env.extractor.wizard.util.Util;

public class ConfigAlternativeComposite extends Composite {

	private Combo combo;
	private List<InputPersitenceFile> inputs;
	private ConfigPersistenceFolder configPersistenceFolder;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ConfigAlternativeComposite(Composite parent, int style, ConfigPersistenceFolder cif) {
		super(parent, style);
		
		this.configPersistenceFolder = cif;
		
		//this.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		this.setLayout(new GridLayout(3, false));
		
		Label lblConfigurationalternative = new Label(this, SWT.NONE);
		lblConfigurationalternative.setFont(SWTResourceManager.getFont("Sans", 14, SWT.NORMAL));
		lblConfigurationalternative.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblConfigurationalternative.setText("Configuration ("+configPersistenceFolder.getName()+")");
		
		Label label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		GridData gd_label = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		gd_label.widthHint = 167;
		label.setLayoutData(gd_label);
		
		Label lblInput = new Label(this, SWT.NONE);
		lblInput.setFont(SWTResourceManager.getFont("Sans", 11, SWT.NORMAL));
		lblInput.setText("Input:");
		
		ComboViewer comboViewer = new ComboViewer(this, SWT.NONE);
		combo = comboViewer.getCombo();
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_combo.widthHint = 170;
		combo.setLayoutData(gd_combo);
		
		Label lblModiscoConfig = new Label(this, SWT.NONE);
		lblModiscoConfig.setFont(SWTResourceManager.getFont("Sans", 11, SWT.NORMAL));
		lblModiscoConfig.setText("Modisco config:    ");
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText("modisco.conf");
		
		Button btnViewModisco = new Button(this, SWT.NONE);
		btnViewModisco.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Util.showConfigurationFile(configPersistenceFolder.getModiscoConfigFile());
			}
		});
		btnViewModisco.setText("View");
		
		Label lblSomoxConfig = new Label(this, SWT.NONE);
		lblSomoxConfig.setFont(SWTResourceManager.getFont("Sans", 11, SWT.NORMAL));
		lblSomoxConfig.setText("Somox config:    ");
		
		Label lblSomoxconf = new Label(this, SWT.NONE);
		lblSomoxconf.setText("somox.conf");
		
		Button btnViewSomox = new Button(this, SWT.NONE);
		btnViewSomox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Util.showConfigurationFile(configPersistenceFolder.getSomoxConfigFile());
			}
		});
		btnViewSomox.setText("View");
		
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		Button btnRunAlternative = new Button(this, SWT.NONE);
		btnRunAlternative.setText("Run alternative");
		btnRunAlternative.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Util.runConfigurationAlternative(configPersistenceFolder);
			}
		});
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		loadInputs();

		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveInputConfig();
			}
		});
	}
	
	private void loadInputs ()
	{
		combo.removeAll();
		String inputAlternative = configPersistenceFolder.getProperty(ConfigPersistenceFolder.KEY_INPUT_ALTERNATIVE);
		int idx = -1;
		// TODO: Inputs
		this.inputs = Util.getInputAlternatives(this.configPersistenceFolder.getProject());
		for (InputPersitenceFile input : this.inputs)
		{
			if (input.getProperty(InputPersitenceFile.KEY_PROJECT_URL) != null)
			{
				combo.add(input.getName());
				if (input.getName().equals(inputAlternative))
				{
					idx = combo.getItemCount()-1;
				}
			}
		}
		
		if (idx >= 0)
			combo.select(idx);
	}
	
	private void saveInputConfig()
	{
        InputPersitenceFile eif = this.inputs.get(combo.getSelectionIndex());
        configPersistenceFolder.setProperty(ConfigPersistenceFolder.KEY_INPUT_ALTERNATIVE, eif.getName());
        configPersistenceFolder.save();

        String projectURI = eif.getProperty(InputPersitenceFile.KEY_PROJECT_URL);
		
		//////////////////////////
		// MODISCO
		//
		ILaunchConfiguration modisco = Util.getLaunchConfiguration(configPersistenceFolder.getModiscoConfigFile());
		String modiscoKey = "discoverer_launch_model";
		String modiscoResourceKey = "org.eclipse.core.resources.IResource";
		String modiscoResourceRegex = modiscoResourceKey + ":/\\w*";
		try {
			// This is XML
			// For now we find resource property with regex and replace with selected value
			String value = modisco.getAttribute(modiscoKey, "");
			value = value.replaceAll(modiscoResourceRegex, modiscoResourceKey + ":"+projectURI);

			// Save property
			ILaunchConfigurationWorkingCopy workingCopy = modisco.getWorkingCopy();
			workingCopy.setAttribute(modiscoKey, value);
			workingCopy.doSave();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//////////////////////////////
		// SOMOX
		//
		ILaunchConfiguration somox = Util.getLaunchConfiguration(configPersistenceFolder.getSomoxConfigFile());
		String somoxInputFileKey = "org.somox.analyzer.inputfile";
		String somoxProjectKey = "org.somox.project";
		String fileExtension = "_java2kdm.xmi";
		try{
			ILaunchConfigurationWorkingCopy workingCopy = somox.getWorkingCopy();

			if (projectURI.charAt(0) == '/') projectURI = projectURI.substring(1);

			workingCopy.setAttribute(somoxInputFileKey, projectURI+"/"+projectURI+fileExtension);
			workingCopy.setAttribute(somoxProjectKey, projectURI);
			workingCopy.doSave();
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void update() {
		this.configPersistenceFolder.load();
		loadInputs();
		super.update();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
