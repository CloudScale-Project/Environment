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

import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;

public class AlternativeNamePage extends WizardPage
{

	private static final String DEFAULT_TITLE = "Alternative name";
	private static final String DEFAULT_DESCRIPTION = "Please insert alternative name.";

	private	String PREFIX = "alternative";
	private String name;
	private Text text;
	private ResourceProvider resourceProvider;

	public AlternativeNamePage(ResourceProvider resourceProvider)
	{
		super(DEFAULT_TITLE, DEFAULT_TITLE, null);
		setDescription(DEFAULT_DESCRIPTION);

		this.resourceProvider = resourceProvider;

		name = findPotentialName();
	}

	@Override
	public void createControl(Composite parent)
	{

		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));

		Label lblName = new Label(container, SWT.NONE);
		lblName.setText("Alternative name:");

		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		text.setText(name);

		text.addModifyListener(new ModifyListener()
		{

			@Override
			public void modifyText(ModifyEvent e)
			{
				name = text.getText();
				checkName();
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

	private void checkName()
	{
		if (name.isEmpty())
		{
			setErrorMessage("Alternative name must not be empty.");
			setPageComplete(false);
			return;
		} 		
		
		if (this.resourceProvider.getResourceByName(name) != null)
		{
			setErrorMessage("Alternative with selected name already exists.");
			setPageComplete(false);
			return;
		}
		
		
		setErrorMessage(null);
		setPageComplete(true);
	}
	
	private String findPotentialName ()
	{
		if (resourceProvider.getResourceByName(PREFIX) == null) return PREFIX;
		
		int count = 0;
		String s = PREFIX+" (" +(++count) +")";
		while (resourceProvider.getResourceByName(s) != null)
		{
			s = PREFIX+" (" +(++count) +")";
		}
		
		return s;
	}
}
