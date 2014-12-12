package eu.cloudscaleproject.env.staticspotter.editors.composites;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
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

import eu.cloudscaleproject.env.staticspotter.ConfigPersistenceFolder;
import eu.cloudscaleproject.env.staticspotter.InputPersitenceFile;


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
			}
		});
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		

		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
	}
	
	@Override
	public void update() {
		this.configPersistenceFolder.load();
		super.update();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
