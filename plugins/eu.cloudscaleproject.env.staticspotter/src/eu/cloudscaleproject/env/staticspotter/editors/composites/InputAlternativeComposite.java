package eu.cloudscaleproject.env.staticspotter.editors.composites;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import eu.cloudscaleproject.env.common.dialogs.CustomResourceSelectionDialog;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.staticspotter.InputPersitenceFile;

public class InputAlternativeComposite extends Composite implements IRefreshable {

	private Text txtInput;
	private InputPersitenceFile editorInput;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InputAlternativeComposite(Composite parent, int style, final InputPersitenceFile editorInput) {
		super(parent, SWT.NONE);
		this.setLayout(new GridLayout(4, false));
		this.editorInput = editorInput;
		
		Label lblConfigurationalternative = new Label(this, SWT.NONE);
		lblConfigurationalternative.setFont(SWTResourceManager.getFont("Sans", 14, SWT.NORMAL));
		lblConfigurationalternative.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1));
		lblConfigurationalternative.setText("Input (Alternative "+editorInput.getName()+")");
		
		
		Label label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		GridData gd_label = new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1);
		gd_label.widthHint = 167;
		label.setLayoutData(gd_label);
		
		Label lblInput = new Label(this, SWT.NONE);
		lblInput.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblInput.setFont(SWTResourceManager.getFont("Sans", 11, SWT.NORMAL));
		lblInput.setText("Project: ");
		
		txtInput = new Text(this, SWT.BORDER);
		txtInput.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnChoose = new Button(this, SWT.NONE);
		btnChoose.setText("...");
		new Label(this, SWT.NONE);
		
		btnChoose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
		        CustomResourceSelectionDialog dialog = new CustomResourceSelectionDialog("Project selection", "Select Java project ...", IProject.class, null);
		        dialog.open();
		        Object selection = dialog.getFirstResult();
		        
		        if (selection instanceof IProject)
		        {
		        	IProject project = (IProject) selection;
		        	String url = project.getFullPath().toPortableString();
		        	txtInput.setText(url);
		        	editorInput.setProperty(InputPersitenceFile.KEY_PROJECT_URL, url);
		        	editorInput.save();
		        	//Util.addInput(InputAlternativeComposite.this.project, InputAlternativeComposite.this.name, url);
		        }
			}
			
		});
		
		load();
	}
	
	private void load ()
	{
		String url = this.editorInput.getProperty(InputPersitenceFile.KEY_PROJECT_URL);
		txtInput.setText(url == null ? "" : url);
	}
	
	@Override
	public void refresh() {
		editorInput.load();
		load();
	}
}
