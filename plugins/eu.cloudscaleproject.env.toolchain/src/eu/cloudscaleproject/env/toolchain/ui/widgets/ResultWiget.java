package eu.cloudscaleproject.env.toolchain.ui.widgets;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.statushandlers.StatusManager;

import eu.cloudscaleproject.env.common.IconSetResources;
import eu.cloudscaleproject.env.common.ui.resources.SWTResourceManager;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputResource;
import eu.cloudscaleproject.env.toolchain.resources.types.IConfigAlternative;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;

public class ResultWiget extends Composite
{

	private MouseAdapter mouseListener = new MouseAdapter()
	{
		public void mouseUp(MouseEvent e)
		{
			if (!status.isOK())
			{
				StatusManager.getManager().handle(status, StatusManager.SHOW);
			} else
			{
				OpenAlternativeUtil.openAlternative((EditorInputResource) alternative.getLastResult());
			}
		}
	};

	private Label lblIcon;
	private Label lblText;

	private IStatus status;

	private IConfigAlternative alternative;

	private Label lblMore;

	public ResultWiget(Composite parent, int style, IConfigAlternative alternative)
	{
		super(parent, style);

		this.alternative = alternative;
		setLayout(new GridLayout(3, false));

		lblIcon = new Label(this, SWT.NONE);
		lblIcon.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));

		lblText = new Label(this, SWT.NONE);
		lblText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true, 1, 1));

		lblMore = new Label(this, SWT.NONE);
		lblMore.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		lblMore.setForeground(SWTResourceManager.getColor(SWT.COLOR_LINK_FOREGROUND));
		lblMore.setText("(more...)");

		lblIcon.addMouseListener(mouseListener);
		lblMore.addMouseListener(mouseListener);
		lblMore.setCursor(new Cursor(getDisplay(), SWT.CURSOR_HAND));
	}

	public void setStatus(IStatus status)
	{
		this.status = status;
		updateStatus();

	}

	private void updateStatus()
	{
		if (status != null && status.isOK())
		{
			lblText.setText("Success. ");
			lblMore.setText("(result...)");
			lblIcon.setImage(IconSetResources.getImage(IconSetResources.THUMB_UP));

		} else
		{
			lblText.setText("Problem occured.");
			lblMore.setText("(error...)");
			lblIcon.setImage(IconSetResources.getImage(IconSetResources.ERROR));
		}

		layout();
	}

}
