package eu.cloudscaleproject.env.toolchain.ui.widgets;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import eu.cloudscaleproject.env.common.ColorResources;
import eu.cloudscaleproject.env.common.IconSetResources;
import eu.cloudscaleproject.env.common.IconSetResources.COLOR;
import eu.cloudscaleproject.env.common.IconSetResources.SIZE;
import eu.cloudscaleproject.env.common.dialogs.TextInputDialog;
import eu.cloudscaleproject.env.common.ui.GradientComposite;
import eu.cloudscaleproject.env.common.ui.resources.SWTResourceManager;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class TitleWidget extends GradientComposite
{

	private Label lblTitle;
	private IEditorInput alternative;
	private Composite buttonsContainer;

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
		this.setLayout(new GridLayout(3, false));
		
		this.setGradientDirection(false);
		this.setGradientColorStart(ColorResources.COLOR_CS_BLUE);
		this.setGradientColorEnd(ColorResources.COLOR_CS_BLUE_LIGHT);

		lblTitle = new Label(this, SWT.NONE);
		lblTitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblTitle.setFont(SWTResourceManager.getFont("Sans", 14, SWT.NORMAL));
		lblTitle.setForeground(ColorResources.COLOR_CS_BLUE_DARK);
		
		Label lblIconEdit = new Label(this, SWT.NONE);
		lblIconEdit.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblIconEdit.setImage(IconSetResources.getImage("write", COLOR.BLUE, SIZE.SIZE_16));
		lblIconEdit.setCursor(new Cursor(getDisplay(), SWT.CURSOR_HAND));
		
		buttonsContainer = new Composite(this, SWT.NONE);
		RowLayout rl_composite = new RowLayout(SWT.HORIZONTAL);
		rl_composite.spacing = 10;
		rl_composite.center = true;
		rl_composite.marginTop = 0;
		rl_composite.marginBottom = 0;
		buttonsContainer.setLayout(rl_composite);
		buttonsContainer.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		
		lblTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				showTitleDialog();
			}
		});
		lblIconEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				showTitleDialog();
			}
		});

		updateTitle();
		initButtons();
	}
	
	protected void initButtons ()
	{
		CLabel lblDelete = createContextButton("", IconSetResources.getImage("trash", COLOR.BLUE, SIZE.SIZE_24));
		lblDelete.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseUp(MouseEvent e) {
				showDeleteDialog();
			}
		});;
	}
	
	protected CLabel createContextButton (String text, Image icon)
	{
		CLabel lbl = new CLabel(buttonsContainer, SWT.NONE);
		lbl.setText(text);
		lbl.setImage(icon);
		lbl.setBackground(ColorResources.COLOR_WHITE);
		lbl.setCursor(new Cursor(getDisplay(), SWT.CURSOR_HAND));
		
		return lbl;
	}
	
	protected CLabel createSeparator ()
	{
		CLabel label = new CLabel(buttonsContainer, SWT.SEPARATOR | SWT.VERTICAL);
		label.setRightMargin(1);
		label.setLeftMargin(1);
		label.setBackground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		label.setLayoutData(new RowData(-1, 24));
		return label;
	}
	
	
	private void showTitleDialog()
	{
		TextInputDialog dialog = new TextInputDialog(Display.getDefault().getActiveShell(), alternative.getName());
		dialog.open();
		
		if(dialog.getReturnCode() == IDialogConstants.OK_ID){
			String name = dialog.getText();
			alternative.setName(name);
			updateTitle();
		}
	}
	
	private void showDeleteDialog()
	{
		boolean delete = MessageDialog.open(MessageDialog.QUESTION, Display.getDefault().getActiveShell(), 
				"Delete alternative", "Are you sure to delete alternative?", SWT.NONE);
		
		if (delete)
		{
			((IEditorInputResource)alternative).delete();
		}
	}

	private void updateTitle()
	{
		if (this.alternative == null) return;
		String title = this.alternative.getName();

		if (this.alternative instanceof IEditorInputResource)  
		{
			String type = ((IEditorInputResource)this.alternative).getType();
			if ( type != null && !type.isEmpty())
			{
				title = String.format("%s [%s]", title, type) ;
			}
		}

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
