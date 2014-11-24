package eu.cloudscaleproject.env.toolchain.resources.dialogs;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.notification.StatusManager.Tool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ResourceSelectionDialog extends Dialog{
	
	private static final Logger logger = Logger.getLogger(ResourceSelectionDialog.class.getName());

	@Inject
	private StatusManager statusManager;
			
	private List list = null;
	
	private final Tool toolEnum;
	private final ResourceProvider resProvider;
	private final IEditorInputResource[] editorInputs;
	
	public static void openDialog(ResourceProvider resProvider, Tool tool){
		Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
		
		if(project != null){
			ResourceSelectionDialog dialog = new ResourceSelectionDialog(shell, resProvider, tool);
			CloudscaleContext.inject(dialog);
			dialog.open();
		}
		else{
			logger.warning("Can't open dialog! Current project can not be retrieved!");
		}
	}

	public ResourceSelectionDialog(Shell parentShell, ResourceProvider resProvider, Tool tool) {
		super(parentShell);		
		java.util.List<IEditorInputResource> resList = resProvider.getResources();
		
		this.toolEnum = tool;
		this.resProvider = resProvider;
		this.editorInputs = resList.toArray(new IEditorInputResource[resList.size()]);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
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
				
				final IEditorInputResource selectedResource = editorInputs[selectionIndex];
				resProvider.tagResource(ResourceProvider.TAG_SELECTED, selectedResource);
				
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						statusManager.validate(selectedResource.getResource().getProject(), toolEnum.getID());
					};
				});
			}
		}
		super.buttonPressed(buttonId);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
	
}
