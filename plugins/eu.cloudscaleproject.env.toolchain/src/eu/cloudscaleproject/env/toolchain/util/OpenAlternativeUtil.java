package eu.cloudscaleproject.env.toolchain.util;

import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.editors.ProjectEditor;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.AbstractSidebarEditor.EditorItem;

public class OpenAlternativeUtil
{
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(OpenAlternativeUtil.class.getName());
	
	public static void openAlternative(IEditorInputResource alternative)
	{
		IProject project = alternative.getProject();

		IFile file = ExplorerProjectPaths.getPropertyFile(project);
		FileEditorInput editorInput = new FileEditorInput(file);
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		try
		{
			IEditorPart part = IDE.openEditor(page, editorInput, "eu.cloudscaleproject.env.toolchain.tooleditor");

			if (part instanceof ProjectEditor)
			{
				ProjectEditor pe = (ProjectEditor) part;
				openAlternative(pe, alternative);
			}
		} catch (PartInitException e)
		{
			e.printStackTrace();
		}

	}

	public static void openAlternative(ProjectEditor pe, IEditorInput alternative)
	{
		//
		// Select alternative
		//
		selectAlternative(alternative);
		
		//
		// Select tab
		//
		Control control = findTabItemControl(alternative);
		if (control != null)
		{

			CTabItem[] tabList = pe.getTabFolder().getItems();
			for (CTabItem tab : tabList)
			{
				if (tab.getControl() == control)
				{
					pe.getTabFolder().setSelection(tab);
					break;
				}
			}
		}
	}

	private static Control findTabItemControl(IEditorInput alternative)
	{
		EditorItem editorItem = EditorRegistry.getInstance().getEditorItem(alternative);
		Composite composite = null;

		if (editorItem != null)
		{
			composite = editorItem.getComposite();

			while (composite != null && !(composite.getParent() instanceof CTabFolder))
			{
				composite = composite.getParent();
			}
		}

		return composite;
	}

	private static void selectAlternative(IEditorInput alternative)
	{
		EditorItem editorItem = EditorRegistry.getInstance().getEditorItem(alternative);
		if (editorItem != null)
		{
			EditorItem parent = EditorRegistry.getInstance().getParent(editorItem);
			if (parent != null) parent.select();

			editorItem.select();
		}
	}
}
