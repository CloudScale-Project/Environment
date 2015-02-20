package eu.cloudscaleproject.env.analyser.editors.composite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.dialogs.CustomResourceSelectionDialog;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.IDirtyAdapter;
import eu.cloudscaleproject.env.toolchain.util.ISaveableComposite;

public class InputAlternativeEditComposite extends Composite implements ISaveableComposite{
	
	private final InputAlternative alternative;
	private final IDirtyAdapter dirtyAdapter;
	
	private final Text textAlloc;
	private final Text textUsage;
	private Text textName;

	public InputAlternativeEditComposite(IEditorPart editor, InputAlternative ia, Composite parent, int style) {
		super(parent, style);
		
		IProject project = ExplorerProjectPaths.getProject(editor);
		
		this.alternative = ia;
		this.dirtyAdapter = (IDirtyAdapter)editor.getAdapter(IDirtyAdapter.class);
		
		setLayout(new GridLayout(3, false));
		
		Label lblName = new Label(this, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblName.setText("Name:");
		
		textName = new Text(this, SWT.BORDER);
		textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(this, SWT.NONE);
		
		textName.setText(ia.getName());
		textName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				InputAlternativeEditComposite.this.alternative.setName(textName.getText());
				dirtyAdapter.fireDirtyState();
			}
		});
		
		Label lblRepository = new Label(this, SWT.NONE);
		lblRepository.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRepository.setText("Allocation model:");
		
		textAlloc = new Text(this, SWT.BORDER);
		textAlloc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textAlloc.setEditable(false);
		
		Button btnAlloc = new Button(this, SWT.NONE);
		btnAlloc.setText("Browse...");
		
		Label lblUsage = new Label(this, SWT.NONE);
		lblUsage.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUsage.setText("Usage model:");
		
		textUsage = new Text(this, SWT.BORDER);
		textUsage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textUsage.setEditable(false);
		
		Button btnUsage = new Button(this, SWT.NONE);
		btnUsage.setText("Browse...");
		
		final IFolder analyserFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_ANALYSER);
		final IFolder analyserInput = analyserFolder.getFolder(ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_INPUT));
		
		btnAlloc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CustomResourceSelectionDialog dialog = new CustomResourceSelectionDialog(
						"Allocation model", "Select PCM allocation model", IFile.class, new String[]{"allocation"}, analyserInput);
				dialog.open();
				
				IFile selection = (IFile)dialog.getFirstResult();
				if(selection != null){
					textAlloc.setText(selection.getProjectRelativePath().toString());
					InputAlternativeEditComposite.this.alternative.setAllocation(selection);
					dirtyAdapter.fireDirtyState();
				}
			}
		});
		
		btnUsage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CustomResourceSelectionDialog dialog = new CustomResourceSelectionDialog(
						"Reconfiguration folder", "Select reconfiguration scripts folder", IFile.class, new String[]{"usagemodel"}, analyserInput);
				dialog.open();
				
				IFile selection = (IFile)dialog.getFirstResult();
				if(selection != null){
					textUsage.setText(selection.getProjectRelativePath().toString());
		        	InputAlternativeEditComposite.this.alternative.setUsage(selection);
					dirtyAdapter.fireDirtyState();
				}
			}
		});
		
		update();
	}
	
	public InputAlternative getAlternative(){
		return alternative;
	}
	
	public void update(){
		if(!this.isDisposed()){			
			textAlloc.setText(alternative.getAllocation() != null ? alternative.getAllocation().getProjectRelativePath().toString() : "");
			textUsage.setText(alternative.getUsage() != null ? alternative.getUsage().getProjectRelativePath().toString() : "");
		}
		super.update();
	}

	@Override
	public void save() {
		alternative.save();
	}

	@Override
	public void load() {
		alternative.load();
	}

	@Override
	public boolean isDirty() {
		return alternative.isDirty();
	}
}
