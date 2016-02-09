package eu.cloudscaleproject.env.extractor.editors.composites;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import eu.cloudscaleproject.env.extractor.alternatives.ConfingAlternative;
import eu.cloudscaleproject.env.extractor.util.SomoxConfigurationUtil;

public class SomoxBlacklistComposite extends Composite
{
	private ConfingAlternative alternative;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SomoxBlacklistComposite(Composite parent, int style, ConfingAlternative alternative)
	{
		super(parent, style);
		
		this.alternative = alternative;
		setLayout(new GridLayout(1, false));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Noto Sans", 11, SWT.NORMAL));
		lblNewLabel.setText("Limit the reverse engineering scope");
		
		Label lblNewLabel_1 = new Label(this, SWT.WRAP);
		GridData gd_lblNewLabel_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_lblNewLabel_1.widthHint = 700;
		gd_lblNewLabel_1.heightHint = 50;
		lblNewLabel_1.setLayoutData(gd_lblNewLabel_1);
		lblNewLabel_1.setText("To limit information present in the reverse engineered model, you can blacklist classes and packages by using regular expression. A blacklist regular expression is for example of the format \"java.* | javax.*\" to exclude all Java libraries.");
		
		final Text text = new Text(this, SWT.BORDER | SWT.MULTI);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		text.setText(parseBlacklist(SomoxConfigurationUtil.getBlacklist(alternative)));
		
		text.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				SomoxConfigurationUtil.setBlacklist(formatBlacklist(text.getText()), SomoxBlacklistComposite.this.alternative);
			}
		});
	}
	
	private String parseBlacklist(String s)
	{
		return s.replaceAll("§", "\n");
	}
	
	private String formatBlacklist(String s)
	{
		return s.replaceAll("\n", "§");
	}
	

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}
}
