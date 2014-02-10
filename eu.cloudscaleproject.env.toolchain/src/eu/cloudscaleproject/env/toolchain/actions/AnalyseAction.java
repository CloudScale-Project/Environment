package eu.cloudscaleproject.env.toolchain.actions;

import javax.inject.Inject;

import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import eu.cloudscaleproject.env.toolchain.handlers.AnalyseTestHandler;

public class AnalyseAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	
	
	//find out why this inject returns null!
	@Inject IExtensionRegistry r;
	
	@Override
	public void run(IAction action) {
		MessageDialog.openInformation(
				window.getShell(),
				"Toolchain",
				"Hello, Eclipse world");
			
			new AnalyseTestHandler().execute(Platform.getExtensionRegistry());
		
	}
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

}
