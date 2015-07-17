package eu.cloudscaleproject.env.spotter.dialogs;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class SelectConfAltDialog extends Dialog{
	
	private final IProject project;
	
	private List list = null;
	private final java.util.List<EditorInputFolder> alternatives;

	public SelectConfAltDialog(IProject project, Shell parentShell, java.util.List<EditorInputFolder> alternatives) {
		super(parentShell);
		this.project = project;
		this.alternatives = alternatives;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		list = new List(container, SWT.BORDER);
		GridData gd_list = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		list.setLayoutData(gd_list);
				
		for(IEditorInputResource res : alternatives){
			list.add(res.getName());
		}
		
		return container;
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if (IDialogConstants.OK_ID == buttonId) {
			int selectionIndex = list.getSelectionIndex();
			if(selectionIndex >= 0){
				IEditorInputResource selectedResource = alternatives.get(selectionIndex);
				selectedResource.validate();
				ValidationDiagramService.showStatus(project, CSTool.SPOTTER_DYN_CONF.getID(), selectedResource);
				
				String iaResName = selectedResource.getProperty(CSTool.SPOTTER_DYN_INPUT.getID());
				ResourceProvider inputResourceProvider 
					= ResourceRegistry.getInstance().getResourceProvider(project, CSTool.SPOTTER_DYN_INPUT);
				IEditorInputResource inputResource = inputResourceProvider.getResource(iaResName);
				if(inputResource != null){
					inputResource.validate();
					ValidationDiagramService.showStatus(project, CSTool.SPOTTER_DYN_INPUT.getID(), inputResource);
				}
			}
		}
		super.buttonPressed(buttonId);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
