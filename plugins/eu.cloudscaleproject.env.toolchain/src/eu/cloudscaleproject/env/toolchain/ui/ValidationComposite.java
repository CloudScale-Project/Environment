package eu.cloudscaleproject.env.toolchain.ui;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import eu.cloudscaleproject.env.common.CommonResources;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusListener;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ValidationComposite extends Composite
{

	private IEditorInputResource alternative;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */

	private final IValidationStatusListener listener = new IValidationStatusListener()
	{
		@Override
		public void propertyChange(PropertyChangeEvent evt)
		{
			if (evt.getPropertyName().equals(IValidationStatus.PROP_VALID))
			{
				Display.getDefault().syncExec(new Runnable()
				{
					@Override
					public void run()
					{
						updateStatus();
					}
				});
			}
		}
	};

	private MouseAdapter mouseListener = new MouseAdapter()
	{
		@Override
		public void mouseDoubleClick(MouseEvent e)
		{
			if (!alternative.getSelfStatus().isValid())
			{
				ValidationDialogHelper.showWarningsDialog(alternative);
			}
		}
	};

	private Label lblIcon;
	private Label lblText;

	public ValidationComposite(Composite parent, int style, IEditorInputResource alternative)
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

		alternative.getSelfStatus().addListener(listener);
		updateStatus();
	}

	private void updateStatus()
	{
		IValidationStatus selfStatus = alternative.getSelfStatus();

		if (selfStatus.isValid())
		{
			lblText.setText("Alternative is valid.");
			lblIcon.setImage(CommonResources.OK);
		} else
		{
			lblText.setText("Alternative is not valid : #warnings=" + selfStatus.getWarningIDs().size());
			lblIcon.setImage(CommonResources.WARNING);
		}
	}

}
