package eu.cloudscaleproject.env.toolchain.ui.widgets;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import eu.cloudscaleproject.env.common.IconSetResources;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.ui.resources.SWTResourceManager;
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
			if(evt.getNewValue() == alternative){
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						if (!ValidationWidget.this.isDisposed()) {
							updateStatus();
						}
					}
				});
			}
			
		}
	};

	private MouseAdapter mouseListener = new MouseAdapter()
	{
		public void mouseUp(MouseEvent e) {
			ValidationStatusHelper.showValidationDialog(alternative);
		};

	};

	private Label lblIcon;
	private Label lblText;

	private Label lblMore;

	public ValidationWidget(Composite parent, int style, IEditorInputResource alternative)
	{
		super(parent, style);

		if (alternative == null) return; // TODO: check if this is needed (if yes --> refactor)

		this.alternative = alternative;

		setLayout(new GridLayout(3, false));

		lblIcon = new Label(this, SWT.NONE);
		lblIcon.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));

		lblText = new Label(this, SWT.NONE);
		lblText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true, 1, 1));

		lblMore = new Label(this, SWT.NONE);
		lblMore.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblMore.setForeground(SWTResourceManager.getColor(SWT.COLOR_LINK_FOREGROUND));
		lblMore.setText("(more...)");

		lblIcon.addMouseListener(mouseListener);
		lblMore.addMouseListener(mouseListener);
		lblMore.setCursor(new Cursor(getDisplay(), SWT.CURSOR_HAND));

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
		
		int errorCount = ValidationStatusHelper.countValidationWarnings(alternative, IValidationStatus.SEVERITY_ERROR); 
		int warningCount = ValidationStatusHelper.countValidationWarnings(alternative, IValidationStatus.SEVERITY_WARNING); 
		int count = errorCount + warningCount;
		
		Image image = IconSetResources.getImage(IconSetResources.OK);
		String msg = "Alternative is valid.";
		lblMore.setVisible(false);
		
		if(errorCount > 0){
			image = IconSetResources.getImage(IconSetResources.ERROR);
			msg = "Alternative is not valid.";
			lblMore.setVisible(true);
			
			if( warningCount > 0 ){
				msg = String.format("Alternative is not valid! Is contains %s errors(s) and %s warning(s)", 
						new int[]{errorCount, warningCount});
			}
		}
		else if(warningCount > 0){
			image = IconSetResources.getImage(IconSetResources.WARNING);
			msg = String.format("Alternative is not valid! Is contains %s warning(s)", 
					new int[]{warningCount});
			lblMore.setVisible(true);
		}
		
		// Valid without warnings 
		// Valid/Invalid with warnings (e.g. input not valid)
		// Invalid without warning -- BUG  workaround
		
		if (!selfStatus.isValid() && count == 0)
		{
			// BUG WORKAROUND
			lblText.setText(String.format("Alternative is not valid.")); 
			lblIcon.setImage(IconSetResources.getImage(IconSetResources.WARNING));
		}
		else{
			lblText.setText(msg); 
			lblIcon.setImage(image);
		}

		pack();
		
		redraw();
	}

}
