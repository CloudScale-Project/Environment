package eu.cloudscaleproject.env.toolchain.ui;

import java.util.LinkedList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.toolchain.Activator;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ValidationDialogHelper
{
	public static void showWarningsDialog (IEditorInputResource alternative)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("Warnings: \n");
		for (String id : alternative.getSelfStatus().getWarningIDs())
		{
			String warning = alternative.getSelfStatus().getWarningMessage(id);
			sb.append(String.format("\t- %s\n", warning));
		}

		MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Validation warnings", sb.toString()); 
	}

	public static void showValidationErrorDialog (IEditorInputResource alternative)
	{
		int numOfwarnings = alternative.getSelfStatus().getWarningIDs().size();
		
		LinkedList<Status> statuses = new LinkedList<>();
		for (String id : alternative.getSelfStatus().getWarningIDs())
		{
			String warning = alternative.getSelfStatus().getWarningMessage(id);
			Status s = new Status (IStatus.ERROR, Activator.PLUGIN_ID, String.format("> %s", warning));
			statuses.add(s);
		}
		
		String msg = String.format("Unable to run alternative '%s'.", alternative.getName());

		String reason = String.format("Alternative validation failed. There are %s validation errors.", numOfwarnings);
		if (numOfwarnings == 1)
		{
			reason = String.format("Alternative validation failed. There is %s validation error.", numOfwarnings);
		}
		else if (numOfwarnings == 0)
		{
			reason = String.format("Alternative validation failed. Validation of input alternative failed.", numOfwarnings);
			statuses.add( new Status (IStatus.ERROR, Activator.PLUGIN_ID, String.format("> %s", "Input alternative has validation errors.")));
		}

		MultiStatus ms = new MultiStatus(Activator.PLUGIN_ID, IStatus.ERROR, statuses.toArray(new Status[0]), reason, null);
		ErrorDialog.openError(Display.getDefault().getActiveShell(), "Run failed", msg, ms);
	}

}
