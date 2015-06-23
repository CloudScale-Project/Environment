package eu.cloudscaleproject.env.toolchain.ui.widgets;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.databinding.property.value.IValueProperty;
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
import eu.cloudscaleproject.env.common.notification.StatusManager;
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

	private final PropertyChangeListener listener = new PropertyChangeListener()
	{
		@Override
		public void propertyChange(PropertyChangeEvent evt)
		{
				Display.getDefault().syncExec(new Runnable()
				{
					@Override
					public void run()
					{
						if(!ValidationWidget.this.isDisposed()){
							updateStatus();
						}
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
		StatusManager.getInstance().addPropertyChangeListener(StatusManager.PROP_VALIDATION_COMPLETED, listener);
		this.addDisposeListener(new DisposeListener()
		{
			@Override
			public void widgetDisposed(DisposeEvent e)
			{
				StatusManager.getInstance().removePropertyChangeListener(StatusManager.PROP_VALIDATION_COMPLETED, listener);

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
