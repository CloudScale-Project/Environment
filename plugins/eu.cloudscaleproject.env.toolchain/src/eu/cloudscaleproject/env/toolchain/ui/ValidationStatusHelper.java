package eu.cloudscaleproject.env.toolchain.ui;

import java.util.LinkedList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.toolchain.Activator;
import eu.cloudscaleproject.env.toolchain.resources.types.IConfigAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ValidationStatusHelper
{
	public static void showValidationWarnings(IEditorInputResource alternative)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<Alternative validation warnings> \n");

		appendValidationWarnings(sb, alternative);

		if (alternative instanceof IConfigAlternative)
		{
			IEditorInput inputAlternative = ((IConfigAlternative) alternative).getInputAlternative();
			if (inputAlternative instanceof IEditorInputResource)
			{
				IEditorInputResource eir = (IEditorInputResource) inputAlternative;
				if (!eir.getSelfStatus().isDone())
				{
					sb.append("\n<Input validation warnings>\n");
					appendValidationWarnings(sb, eir);
				}
			}
		}

		MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Validation warnings", sb.toString());
	}

	private static void appendValidationWarnings(StringBuilder sb, IEditorInputResource alternative)
	{
		appendValidationWarnings(sb, alternative.getSelfStatus());

		for (IValidationStatus status : alternative.getStatus())
		{
			appendValidationWarnings(sb, status);
		}
	}

	private static void appendValidationWarnings(StringBuilder sb, IValidationStatus status)
	{
		for (String id : status.getWarningIDs())
		{
			String warning = status.getWarningMessage(id);
			sb.append(String.format("\t- %s\n", warning));
		}
	}

	public static int countValidationWarnings(IEditorInputResource alternative)
	{
		int count = alternative.getSelfStatus().getWarningIDs().size();

		for (IValidationStatus status : alternative.getStatus())
		{
			count += status.getWarningIDs().size();
		}

		return count;
	}

	public static void showValidationErrorDialog(IEditorInputResource alternative)
	{
		int numOfwarnings = alternative.getSelfStatus().getWarningIDs().size();

		LinkedList<Status> statuses = new LinkedList<>();
		for (String id : alternative.getSelfStatus().getWarningIDs())
		{
			String warning = alternative.getSelfStatus().getWarningMessage(id);
			Status s = new Status(IStatus.ERROR, Activator.PLUGIN_ID, String.format("> %s", warning));
			statuses.add(s);
		}

		String msg = String.format("Unable to run alternative '%s'.", alternative.getName());

		String reason = String.format("Alternative validation failed. There are %s validation errors.", numOfwarnings);
		if (numOfwarnings == 1)
		{
			reason = String.format("Alternative validation failed. There is %s validation error.", numOfwarnings);
		} else if (numOfwarnings == 0)
		{
			reason = String.format("Alternative validation failed. Validation of input alternative failed.", numOfwarnings);
			statuses.add(new Status(IStatus.ERROR, Activator.PLUGIN_ID, String.format("> %s", "Input alternative has validation errors.")));
		}

		MultiStatus ms = new MultiStatus(Activator.PLUGIN_ID, IStatus.ERROR, statuses.toArray(new Status[0]), reason, null);
		ErrorDialog.openError(Display.getDefault().getActiveShell(), "Run failed", msg, ms);
	}

}
