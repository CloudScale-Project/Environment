package eu.cloudscaleproject.env.toolchain.ui.widgets;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import eu.cloudscaleproject.env.common.ColorResources;
import eu.cloudscaleproject.env.common.dialogs.TextInputDialog;
import eu.cloudscaleproject.env.common.ui.GradientComposite;
import eu.cloudscaleproject.env.common.ui.resources.SWTResourceManager;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;

public class TitleWidget extends GradientComposite
{

	private Label lblTitle;
	private IEditorInput alternative;
	
	private MouseAdapter mouseListener = new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {

				TextInputDialog dialog = new TextInputDialog(Display.getDefault().getActiveShell(), alternative.getName());
				dialog.open();
				
				if(dialog.getReturnCode() == IDialogConstants.OK_ID){
					String name = dialog.getText();
					alternative.setName(name);
					updateTitle();
				}
			}
		};


	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TitleWidget(Composite parent, int style, IEditorInput alternative)
	{
		super(parent, style);
		this.alternative = alternative;

		this.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		this.setLayout(new GridLayout(1, false));
		
		this.setGradientDirection(false);
		this.setGradientColorStart(ColorResources.COLOR_CS_BLUE);
		this.setGradientColorEnd(ColorResources.COLOR_CS_BLUE_LIGHT);

		lblTitle = new Label(this, SWT.NONE);
		lblTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		lblTitle.setFont(SWTResourceManager.getFont("Sans", 14, SWT.NORMAL));
		lblTitle.setForeground(ColorResources.COLOR_CS_BLUE_DARK);
		
		lblTitle.addMouseListener(mouseListener);
		this.addMouseListener(mouseListener);

		updateTitle();
	}

	private void updateTitle()
	{
		String title = this.alternative.getName();
/*		if (this.alternative.getType() != null)
		{
			title = String.format("%s [%s]",this.alternative.getName(), this.alternative.getType().toLowerCase()) ;
		}*/

		lblTitle.setText(title);
	}
	
	public String getTitle ()
	{
		return lblTitle.getText();
	}
	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}

}
