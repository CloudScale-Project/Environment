 
package eu.cloudscaleproject.env.method.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.method.MethodEditorInput;
import eu.cloudscaleproject.env.method.MethodEditorPart;

public class OpenMethod {
	@Execute
	public void execute() {
		//TODO Your code goes here
		
		openEditor();
	}
		
	public static void openEditor ()
	{
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				IWorkbenchPage page = window.getActivePage();
				
				try {
					page.openEditor(new MethodEditorInput(), MethodEditorPart.ID);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
				
			}
		});
	}
}