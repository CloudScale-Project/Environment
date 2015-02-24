package eu.cloudscaleproject.env.analyser.dialogs;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import eu.cloudscaleproject.env.analyser.PCMModelType;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.dialogs.CustomResourceSelectionDialog;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

public class ImportPCMDialog extends Dialog{
		
	public IFile allocation;
	public IFile usage;
	
	private final IProject project;
	
	public ImportPCMDialog(IProject project, Shell parentShell) {
		super(parentShell);
		this.project = project;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 3;
		
		createModelSelection(container, PCMModelType.ALLOCATION, "Allocation:", "Select allocation model", new BasicCallback<IFile>(){
			@Override
			public void handle(IFile object) {
				allocation = object;
			}});
		createModelSelection(container, PCMModelType.USAGE, "Usage:", "Select usage model", new BasicCallback<IFile>(){
			@Override
			public void handle(IFile object) {
				usage = object;
			}});
		
		
		return container;
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if (IDialogConstants.OK_ID == buttonId) {
			ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID);
			InputAlternative ia = (InputAlternative)rp.createNewResource("Imported model", null);
			
			if(allocation != null && allocation.exists()){
				ia.setAllocation(allocation);
			}
			if(usage != null && usage.exists()){
				ia.setSubResource(ToolchainUtils.KEY_FILE_USAGE, usage);
			}
			
			rp.tagResource(ResourceProvider.TAG_SELECTED, ia);
			ia.save();
		}
		super.buttonPressed(buttonId);
	}
		
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
	
	private void createModelSelection(Composite composite, final PCMModelType modeltype, 
														  final String name, 
														  final String text, 
														  final BasicCallback<IFile> callback){
		Label lbl = new Label(composite, SWT.NONE);
		lbl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbl.setText(name);
		
		final Text textPath = new Text(composite, SWT.BORDER);
		textPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnBrowse = new Button(composite, SWT.NONE);
		btnBrowse.setText("Browse...");
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CustomResourceSelectionDialog rs = new CustomResourceSelectionDialog(
						text, 
						text,
						IFile.class,
						new String[]{modeltype.getFileExtension()});
				
				rs.open();
				
				Object sel = rs.getFirstResult();
				if(sel instanceof IFile){
					textPath.setText(((IFile)sel).getProjectRelativePath().toString());
					callback.handle((IFile)sel);
				}
			}
		});
	}
}