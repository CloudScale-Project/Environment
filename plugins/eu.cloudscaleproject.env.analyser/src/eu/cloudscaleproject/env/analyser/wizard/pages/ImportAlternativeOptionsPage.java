package eu.cloudscaleproject.env.analyser.wizard.pages;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ImportAlternativeOptionsPage extends WizardPage{
	
	private boolean copyIntoProject = false;

	public ImportAlternativeOptionsPage(String title) {
		super(title);
	}
	
	public boolean getCopyIntoProjectParam(){
		return copyIntoProject;
	}

	@Override
	public void createControl(Composite parent) {
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, true));
		
		final Button btnCopyIntoProject = new Button(composite, SWT.CHECK);
		btnCopyIntoProject.setText("Copy selected models into alternative");
		
		btnCopyIntoProject.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				copyIntoProject = btnCopyIntoProject.getSelection();
				super.widgetSelected(e);
			}
		});
		
		setControl(composite);
	}

}
