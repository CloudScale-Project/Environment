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

import eu.cloudscaleproject.env.usageevolution.DlimGenerator;

public class TemplateSelectionPage extends WizardPage{
	
	private DlimGenerator.Template selectedTemplate = null;

	public TemplateSelectionPage() {
		super("Select arrival rate template");
		setTitle("Select arrival rate template");
		setDescription("Please select initial arrival rate template.");
		
		setPageComplete(false);
	}
	
	public DlimGenerator.Template getSelectedTemplate(){
		return selectedTemplate;
	}

	@Override
	public void createControl(Composite parent) {
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblTemplate = new Label(composite, SWT.NONE);
		lblTemplate.setLayoutData(new GridData());
		lblTemplate.setBounds(10, 10, 162, 15);
		lblTemplate.setText("Usage evolution template:");
		
		//templates
		Composite templates = new Composite(composite, SWT.NONE);
		templates.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		templates.setLayout(new GridLayout(1, false));
		
		{
			Button button = new Button(templates, SWT.LEFT | SWT.RADIO);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					selectedTemplate = DlimGenerator.Template.LINEAR_TREND;
					setPageComplete(true);
				}
			});
			button.setBounds(10, 31, 258, 27);
			button.setText("Create linear trend");
		}
		{
			Button button = new Button(templates, SWT.LEFT | SWT.RADIO);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					selectedTemplate = DlimGenerator.Template.LINEAR_INCREASE_DECLINE;
					setPageComplete(true);
				}
			});
			button.setBounds(10, 31, 258, 27);
			button.setText("Create linear increase and decline");
		}
		{
			Button button = new Button(templates, SWT.LEFT | SWT.RADIO);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					selectedTemplate = DlimGenerator.Template.SINUSOIDAL;
					setPageComplete(true);
				}
			});
			button.setBounds(10, 31, 258, 27);
			button.setText("Create sinusoidal");
		}
		{
			Button button = new Button(templates, SWT.LEFT | SWT.RADIO);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					selectedTemplate = DlimGenerator.Template.SINUSOIDAL_TREND;
					setPageComplete(true);
				}
			});
			button.setBounds(10, 31, 258, 27);
			button.setText("Create sinusoidal trend");
		}
		{
			Button button = new Button(templates, SWT.LEFT | SWT.RADIO);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					selectedTemplate = DlimGenerator.Template.EXPONENTIAL;
					setPageComplete(true);
				}
			});
			button.setBounds(10, 31, 258, 27);
			button.setText("Create explonential trend");
		}
		{
			Button button = new Button(templates, SWT.LEFT | SWT.RADIO);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					selectedTemplate = DlimGenerator.Template.EXPONENTIAL_LOG;
					setPageComplete(true);
				}
			});
			button.setBounds(10, 31, 258, 27);
			button.setText("Create explonential increase and log decline");
		}
		
		setControl(composite);
	}

}
