package eu.cloudscaleproject.env.toolchain.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.statushandlers.StatusManager;

import eu.cloudscaleproject.env.common.CommonResources;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputResource;
import eu.cloudscaleproject.env.toolchain.resources.types.IConfigAlternative;

public class FooterResultComposite extends Composite
{


	private MouseAdapter mouseListener = new MouseAdapter()
	{
		@Override
		public void mouseDoubleClick(MouseEvent e)
		{
			if (!status.isOK())
			{
				StatusManager.getManager().handle(status, StatusManager.SHOW);
			}
			else
			{
				OpenAlternativeUtil.openAlternative((EditorInputResource) alternative.getLastResult());
			}
		}
	};

	private Label lblIcon;
	private Label lblText;

	private IStatus status;

	private IConfigAlternative alternative;

	public FooterResultComposite(Composite parent, int style, IConfigAlternative alternative)
	{
		super(parent, style);

		this.alternative = alternative;
		setLayout(new GridLayout(2, false));

		lblIcon = new Label(this, SWT.NONE);
		lblIcon.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));

		lblText = new Label(this, SWT.NONE);
		lblText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));

		lblText.addMouseListener(mouseListener);
		lblIcon.addMouseListener(mouseListener);

		this.addDisposeListener(new DisposeListener()
		{
			
			@Override
			public void widgetDisposed(DisposeEvent e)
			{
			}
		});
	}
	
	public void setStatus (IStatus status)
	{
		this.status = status;
		updateStatus();
		
	}

	private void updateStatus()
	{
		if (status != null && status.isOK())
		{
			lblText.setText("Success... Go to result.");
			lblIcon.setImage(CommonResources.THUMB_UP);
		} else
		{
			lblText.setText("Unsuccsfull... See error.");
			lblIcon.setImage(CommonResources.ERROR);
		}
		
		layout();
	}

}
