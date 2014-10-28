package eu.cloudscaleproject.env.analyser.dialogs;

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

import eu.cloudscaleproject.env.analyser.AnalyserUtil;
import eu.cloudscaleproject.env.analyser.InputAlternative;
import eu.cloudscaleproject.env.common.notification.StatusManager;

public class SelectDefaultInputDialog extends Dialog{
	
	@Inject
	private StatusManager statusManager;
	
	private final IProject project;
	
	private List list = null;

	public SelectDefaultInputDialog(IProject project, Shell parentShell) {
		super(parentShell);
		this.project = project;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		list = new List(container, SWT.BORDER);
		GridData gd_list = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		list.setLayoutData(gd_list);
		
		java.util.List<InputAlternative> inputs = AnalyserUtil.getInputAlternatives(project);
		for(InputAlternative input : inputs){
			list.add(input.getName());
		}
		
		return container;
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if (IDialogConstants.OK_ID == buttonId) {
			String selection = list.getSelection().length > 0 ? list.getSelection()[0] : null;
			if(selection != null){
				
				java.util.List<InputAlternative> alt = AnalyserUtil.getInputAlternatives(project);
				
				InputAlternative selectedAlternative = null;
				for(InputAlternative a : alt){
					if(selection.equals(a.getName())){
						selectedAlternative = a;
						break;
					}
				}
				
				AnalyserUtil.setCurrentInputAlternative(project, selectedAlternative);
				
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						statusManager.validate(project, StatusManager.Tool.ANALYSER_INPUT.getID());
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
