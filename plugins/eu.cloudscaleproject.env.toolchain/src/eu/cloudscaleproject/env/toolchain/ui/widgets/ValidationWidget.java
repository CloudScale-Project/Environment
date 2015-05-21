package eu.cloudscaleproject.env.toolchain.ui.widgets;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
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
import eu.cloudscaleproject.env.toolchain.ui.ValidationStatusHelper;

public class ValidationWidget extends Composite
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
				Display.getDefault().asyncExec(new Runnable()
				{
					@Override
					public void run()
					{
						updateStatus();
					}
				});
		}
	};

	private MouseAdapter mouseListener = new MouseAdapter()
	{
		@Override
		public void mouseDoubleClick(MouseEvent e)
		{
			if (!alternative.getSelfStatus().isValid())
			{
				ValidationStatusHelper.showValidationWarnings(alternative);
			}
		}
	};

	private Label lblIcon;
	private Label lblText;

	public ValidationWidget(Composite parent, int style, IEditorInputResource alternative)
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

		if (alternative == null) return; // 

		initListeners();
		updateStatus();
	}
	
	private void initListeners ()
	{
		alternative.getSelfStatus().addListener(listener);
		alternative.addPropertyChangeListener(listener);
		this.addDisposeListener(new DisposeListener()
		{
			
			@Override
			public void widgetDisposed(DisposeEvent e)
			{
				ValidationWidget.this.alternative.
					getSelfStatus().removeListener(listener);
				ValidationWidget.this.alternative.
					removePropertyChangeListener(listener);
			}
		});
		
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
			int warningCount = ValidationStatusHelper.countValidationWarnings(alternative); 
			// BUG WORKAROUND
			if (warningCount == 0)
			{
				lblText.setText(String.format("Alternative is not valid.")); 
			}
			else
			{
				lblText.setText(String.format("Alternative is not valid : %s warning(s)", 
					ValidationStatusHelper.countValidationWarnings(alternative)));
			}

			lblIcon.setImage(CommonResources.WARNING);
		}
		
		redraw();
	}

}
