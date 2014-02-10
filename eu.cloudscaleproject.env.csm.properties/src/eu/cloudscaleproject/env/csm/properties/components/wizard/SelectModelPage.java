package eu.cloudscaleproject.env.csm.properties.components.wizard;

import java.io.File;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import eu.cloudscaleproject.env.csm.diagram.SWTResourceManager;

public class SelectModelPage extends WizardPage {
	private Text txtResourceModelLoc;
	private Text txtSystemModelLoc;

	/**
	 * Create the wizard.
	 */
	public SelectModelPage() {
		super("selectModelPage");
		setTitle("Import model");
		setDescription("System and repository PCM model import.");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		container.setLayout(new FormLayout());
		
		Label lblResourceModel = new Label(container, SWT.NONE);
		lblResourceModel.setFont(SWTResourceManager.getFont("Ubuntu", 13, SWT.NORMAL));
		FormData fd_lblResourceModel = new FormData();
		fd_lblResourceModel.top = new FormAttachment(0, 10);
		fd_lblResourceModel.left = new FormAttachment(0, 10);
		lblResourceModel.setLayoutData(fd_lblResourceModel);
		lblResourceModel.setText("Resource model:");
		
		txtResourceModelLoc = new Text(container, SWT.BORDER);
		FormData fd_txtResourceModelLoc = new FormData();
		fd_txtResourceModelLoc.left = new FormAttachment(lblResourceModel, 6);
		txtResourceModelLoc.setLayoutData(fd_txtResourceModelLoc);
		
		Button btnSelectResourceModel = new Button(container, SWT.NONE);
		fd_txtResourceModelLoc.right = new FormAttachment(btnSelectResourceModel, -6);
		FormData fd_btnSelectResourceModel = new FormData();
		fd_btnSelectResourceModel.left = new FormAttachment(0, 504);
		fd_btnSelectResourceModel.bottom = new FormAttachment(100, -233);
		fd_btnSelectResourceModel.top = new FormAttachment(0, 8);
		btnSelectResourceModel.setLayoutData(fd_btnSelectResourceModel);
		btnSelectResourceModel.setText("Browse...");
		
		Label lblSystemModel = new Label(container, SWT.NONE);
		fd_lblResourceModel.bottom = new FormAttachment(lblSystemModel, -8);
		lblSystemModel.setFont(SWTResourceManager.getFont("Ubuntu", 13, SWT.NORMAL));
		FormData fd_lblSystemModel = new FormData();
		fd_lblSystemModel.top = new FormAttachment(0, 45);
		fd_lblSystemModel.left = new FormAttachment(0, 10);
		lblSystemModel.setLayoutData(fd_lblSystemModel);
		lblSystemModel.setText("System model:");
		
		txtSystemModelLoc = new Text(container, SWT.BORDER);
		fd_txtResourceModelLoc.bottom = new FormAttachment(100, -233);
		FormData fd_txtSystemModelLoc = new FormData();
		fd_txtSystemModelLoc.right = new FormAttachment(txtResourceModelLoc, 0, SWT.RIGHT);
		fd_txtSystemModelLoc.top = new FormAttachment(txtResourceModelLoc, 6);
		fd_txtSystemModelLoc.left = new FormAttachment(txtResourceModelLoc, 0, SWT.LEFT);
		txtSystemModelLoc.setLayoutData(fd_txtSystemModelLoc);
		
		Button btnSelectSystemModel = new Button(container, SWT.NONE);
		btnSelectSystemModel.setText("Browse...");
		FormData fd_btnSelectSystemModel = new FormData();
		fd_btnSelectSystemModel.top = new FormAttachment(txtResourceModelLoc, 6);
		fd_btnSelectSystemModel.left = new FormAttachment(btnSelectResourceModel, 0, SWT.LEFT);
		btnSelectSystemModel.setLayoutData(fd_btnSelectSystemModel);
		
		btnSelectResourceModel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell());
				fd.setFilterExtensions(new String[]{"*.repository"});
				fd.open();
				String loc = fd.getFilterPath() + File.separator +fd.getFileName();
				txtResourceModelLoc.setText(loc);
				System.out.println(loc);
				checkComplete();
			}
		});
		
		btnSelectSystemModel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell());
				fd.setFilterExtensions(new String[]{"*.system"});
				fd.open();
				String loc = fd.getFilterPath() + File.separator +fd.getFileName();
				txtSystemModelLoc.setText(loc);
				System.out.println(loc);
				checkComplete();
				
			}
		});
	}
	
	public String getResourceModel ()
	{
		return txtResourceModelLoc.getText();
	}
	
	public String getSystemModel ()
	{
		return txtSystemModelLoc.getText();
	}
	
	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return super.getErrorMessage();
	}
	
	private void checkComplete()
	{
		if (txtSystemModelLoc.getText().isEmpty() || 
				txtResourceModelLoc.getText().isEmpty())
		{
			setPageComplete(false);
		}
		else
		{
			setPageComplete(true);
		}
		
	}
}
