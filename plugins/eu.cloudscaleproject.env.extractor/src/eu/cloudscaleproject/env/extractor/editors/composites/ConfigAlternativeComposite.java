package eu.cloudscaleproject.env.extractor.editors.composites;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
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
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.ui.RunComposite;

public class ConfigAlternativeComposite extends RunComposite {

	private Combo combo;
	private List<IEditorInputResource> inputs;
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
		getContainer().setLayout(new GridLayout(3, false));

		setTitle(configPersistenceFolder.getName());
		
		Label lblInput = new Label(getContainer(), SWT.NONE);
		lblInput.setFont(SWTResourceManager.getFont("Sans", 11, SWT.NORMAL));
		lblInput.setText("Input:");
		
		ComboViewer comboViewer = new ComboViewer(getContainer(), SWT.NONE);
		combo = comboViewer.getCombo();
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_combo.widthHint = 170;
		combo.setLayoutData(gd_combo);
		
		Label lblModiscoConfig = new Label(getContainer(), SWT.NONE);
		lblModiscoConfig.setFont(SWTResourceManager.getFont("Sans", 11, SWT.NORMAL));
		lblModiscoConfig.setText("Modisco config:    ");
		
		Label lblNewLabel = new Label(getContainer(), SWT.NONE);
		lblNewLabel.setText("modisco.conf");
		
		Button btnViewModisco = new Button(getContainer(), SWT.NONE);
		btnViewModisco.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Util.showConfigurationFile(configPersistenceFolder.getModiscoConfigFile());
			}
		});
		btnViewModisco.setText("View");
		
		Composite containerConfiguration = new Composite(getContainer(), SWT.NONE);
		containerConfiguration.setLayout(new FillLayout(SWT.HORIZONTAL));
		containerConfiguration.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		
		new SomoxConfigurationComposite(configPersistenceFolder.getSomoxConfiguration(), containerConfiguration, SWT.NONE);
		
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

		ResourceProvider resourceProvider = ResourceRegistry.getInstance().getResourceProvider(
				this.configPersistenceFolder.getProject(),
					ToolchainUtils.EXTRACTOR_INPUT_ID);

		this.inputs = resourceProvider.getResources();

		for (IEditorInputResource input : this.inputs)
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
        IEditorInputResource eif = this.inputs.get(combo.getSelectionIndex());
        configPersistenceFolder.setProperty(ConfigPersistenceFolder.KEY_INPUT_ALTERNATIVE, eif.getName());
        configPersistenceFolder.save();
	}
	
	@Override
	public void update() {
		this.configPersistenceFolder.load();
		loadInputs();
		super.update();
	}
	
	@Override
	protected IStatus doRun(IProgressMonitor m)
	{
		Util.ExtractorRunJob job = new Util.ExtractorRunJob(this.configPersistenceFolder);
		return job.run(m);
	}	

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
