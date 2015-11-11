package eu.cloudscaleproject.env.toolchain.ui.widgets;

import java.util.HashMap;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
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
import org.eclipse.ui.plugin.AbstractUIPlugin;

import eu.cloudscaleproject.env.common.ColorResources;
import eu.cloudscaleproject.env.common.dialogs.TextInputDialog;
import eu.cloudscaleproject.env.common.ui.resources.SWTResourceManager;
import eu.cloudscaleproject.env.toolchain.Activator;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class TitleWidget extends Composite
{

	private CLabel lblTitle;
	private IEditorInput alternative;
	private Composite buttonsContainer;
	
	private HashMap<CLabel, String> contextButtonsMap = new HashMap<>();
	private boolean contextButtonsVisible = true;

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
		
		this.setBackground(ColorResources.COLOR_CS_BLUE_LIGHT);
		lblTitle = new CLabel(this, SWT.NONE);
		lblTitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblTitle.setFont(SWTResourceManager.getFont("Sans", 14, SWT.NORMAL));
		lblTitle.setForeground(ColorResources.COLOR_CS_BLUE_DARK);
		lblTitle.setLeftMargin(4);
		lblTitle.setRightMargin(0);
		
		Label lblIconEdit = new Label(this, SWT.NONE);
		lblIconEdit.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblIconEdit.setImage(loadImage("resources/icons/write-blue-24.png"));
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
		
		this.addControlListener(new ControlAdapter(){
			@Override
			public void controlResized(ControlEvent e)
			{
				if (TitleWidget.this.getSize().x < 600)
					setContextButtonsTextVisible(false);
				else
					setContextButtonsTextVisible(true);
			}
		});

		updateTitle();
		initButtons();
	}
	
	protected void initButtons ()
	{
		CLabel lblDelete = createContextButton("", loadImage("resources/icons/trash-white-24.png"));
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
		lbl.setLayoutData(new RowData(SWT.DEFAULT, 28));
		lbl.setRightMargin(10);
		lbl.setLeftMargin(8);
		lbl.setText(text);
		lbl.setImage(icon);
		lbl.setBackground(ColorResources.COLOR_CS_BLUE);
		lbl.setForeground(ColorResources.COLOR_WHITE);
		lbl.setCursor(new Cursor(getDisplay(), SWT.CURSOR_HAND));
		
		contextButtonsMap.put(lbl, text);
		
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
			this.layout();
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
	
	private void setContextButtonsTextVisible (boolean visible)
	{
		if (this.contextButtonsVisible == visible) return;

		for (CLabel lbl : contextButtonsMap.keySet())
		{
			if (lbl.getImage() == null) continue;

			if (visible)
			{
				lbl.setText(contextButtonsMap.get(lbl));
			}
			else
			{
				lbl.setText("");
			}
		}
		
		this.contextButtonsVisible = visible;
		
		layout();
	}
	

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}

	protected static Image loadImage(String filepath)
	{
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, filepath).createImage();
	}

}
