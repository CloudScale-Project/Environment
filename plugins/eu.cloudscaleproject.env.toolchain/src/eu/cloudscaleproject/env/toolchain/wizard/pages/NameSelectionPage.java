package eu.cloudscaleproject.env.toolchain.wizard.pages;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class NameSelectionPage extends WizardPage{
	
	private static final String DEFAULT_TITLE = "Alternative name";
	private static final String DEFAULT_DESCRIPTION = "Please insert alternative name.";
	
	private String name = "alternative";
	private Text text;

	public NameSelectionPage() {
		super(DEFAULT_TITLE, DEFAULT_TITLE, null);
		setDescription(DEFAULT_DESCRIPTION);
	}

	@Override
	public void createControl(Composite parent) {
		
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		
		Label lblName = new Label(container, SWT.NONE);
		lblName.setText("Alternative name:");
		
		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		text.setText(name);
		
		text.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				name = text.getText();
				if(!name.isEmpty()){
					setPageComplete(true);
				}
				else{
					setPageComplete(false);
				}
			}
		});
		text.setFocus();
		text.selectAll();
		
		setPageComplete(!name.isEmpty());
		setControl(container);
	}
	
	@Override
	public String getName()
	{
		return name;
	}

}
