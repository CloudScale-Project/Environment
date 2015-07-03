package eu.cloudscaleproject.env.toolchain.resources.dialogs;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ResourceSelectionDialog extends Dialog{

	private final String message;
	private List list = null;
	private final IEditorInputResource[] editorInputs;
	
	private IEditorInputResource selectedResource = null;

	public ResourceSelectionDialog(Shell shell, String title, String message, IProject project, CSTool tool) {
		super(shell);
		
		shell.setText(title);
		this.message = message;
		
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, tool);
		java.util.List<IEditorInputResource> resList = rp.getResources();
		this.editorInputs = resList.toArray(new IEditorInputResource[resList.size()]);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		label.setText(message);
		
		list = new List(container, SWT.BORDER);
		GridData gd_list = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		list.setLayoutData(gd_list);
				
		for(IEditorInput ei : editorInputs){
			list.add(ei.getName());
		}
		
		return container;
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if (IDialogConstants.OK_ID == buttonId) {
			int selectionIndex = list.getSelectionIndex();
			if(selectionIndex >= 0){
				selectedResource = editorInputs[selectionIndex];
			}
		}
		super.buttonPressed(buttonId);
	}
	
	public IEditorInputResource getSelection(){
		return selectedResource;
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
	
}
