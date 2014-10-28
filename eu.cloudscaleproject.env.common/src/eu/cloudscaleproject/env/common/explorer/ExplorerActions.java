package eu.cloudscaleproject.env.common.explorer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

public class ExplorerActions {

	public static IEditorPart openEditor(IProject project, IFile file){
			try {
				IEditorDescriptor toolEditor = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(file.getName());
				return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().
						openEditor(new FileEditorInput(file), toolEditor.getId());
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
	
}
