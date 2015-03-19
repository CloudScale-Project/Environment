package eu.cloudscaleproject.env.analyser.dialogs;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

public class NewInputAlternativeDialog extends Dialog{
	
	private final IProject project;
	
	private Text text = null;
	private CCombo combo = null;
	
	public static final String TYPE_NEW = "Create empty";
	public static final String TYPE_IMPORT = "Import existing";
	
	private String name = "";
	private String type = null;
	
	public NewInputAlternativeDialog(IProject project, Shell parentShell) {
		super(parentShell);
		
		this.project = project;
		this.setBlockOnOpen(true);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 2;
		
		Label lblNewAlternativeName = new Label(container, SWT.NONE);
		lblNewAlternativeName.setText("New alternative name:");
		
		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblAlternativeType = new Label(container, SWT.NONE);
		lblAlternativeType.setText("Alternative type:");
		
		combo = new CCombo(container, SWT.BORDER);
		GridData gd_combo = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 115;
		combo.setLayoutData(gd_combo);
		combo.setItems(new String[]{TYPE_NEW, TYPE_IMPORT});
		combo.select(0);
		
		return container;
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		
		ResourceProvider resourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID);
		
		if(IDialogConstants.OK_ID == buttonId){
			name = (text != null && !text.isDisposed()) ? text.getText() : "No name";
			if(combo.getSelectionIndex() >= 0){
				type = combo.getItem(combo.getSelectionIndex());
			}
		}
		
		super.buttonPressed(buttonId);

		if(IDialogConstants.OK_ID == buttonId){

			if(TYPE_NEW.equals(type)){
				InputAlternative alternative = (InputAlternative)resourceProvider.createNewResource(name, null);
				alternative.createEmpty();
			}
			else if(TYPE_IMPORT.equals(type)){
				ImportInputAlternativeDialog importDialog = new ImportInputAlternativeDialog(getShell());
				importDialog.open();
				
				if(IDialogConstants.OK_ID == importDialog.getReturnCode()){
					InputAlternative alternative = (InputAlternative)resourceProvider.createNewResource(name, null);
					for(IFile file : importDialog.getSelectedFiles()){
						alternative.addSubResourceModel(file);
					}
					alternative.save();
					alternative.load();
				}
			}
		}
	}
	
	public String getType(){
		return type;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("Create input alternative");
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(450, 200);
	}
}