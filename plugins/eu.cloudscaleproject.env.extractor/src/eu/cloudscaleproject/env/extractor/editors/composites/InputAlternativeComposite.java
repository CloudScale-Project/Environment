package eu.cloudscaleproject.env.extractor.editors.composites;

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
import eu.cloudscaleproject.env.common.ui.TitleComposite;
import eu.cloudscaleproject.env.extractor.InputPersitenceFile;

public class InputAlternativeComposite extends TitleComposite {

	private Text txtInput;
	private InputPersitenceFile editorInput;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InputAlternativeComposite(Composite parent, int style, final InputPersitenceFile editorInput) {
		super(parent, SWT.NONE);
		this.editorInput = editorInput;
		
		setTitle(editorInput.getName());
		
		getContainer().setLayout(new GridLayout(4, false));

		Label lblInput = new Label(getContainer(), SWT.NONE);
		lblInput.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblInput.setFont(SWTResourceManager.getFont("Sans", 11, SWT.NORMAL));
		lblInput.setText("Project: ");
		
		txtInput = new Text(getContainer(), SWT.BORDER);
		txtInput.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnChoose = new Button(getContainer(), SWT.NONE);
		btnChoose.setText("...");
		new Label(getContainer(), SWT.NONE);
		
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
	public void update() {
		editorInput.load();
		load();
		super.update();
	}
}
