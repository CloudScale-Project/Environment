package eu.cloudscaleproject.env.toolchain.ui;

import java.util.LinkedList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatus.Warning;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.toolchain.Activator;
import eu.cloudscaleproject.env.toolchain.resources.types.IConfigAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ValidationStatusHelper
{
	//
	// HACKISH : TEMPORARY SOLUTION
	//

	/*
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

		for (IValidationStatus status : alternative.getSubStatuses())
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
	*/

	public static int countValidationWarnings(IValidationStatusProvider sp, int severity)
	{
		int count = 0;
		
		for(Warning w : sp.getSelfStatus().getWarnings()){
			if(w.severity == severity){
				count++;
			}
		}

		for (IValidationStatus status : sp.getSubStatuses())
		{
			for(Warning w : status.getWarnings()){
				if(w.severity == severity){
					count++;
				}
			}
		}

		return count;
	}

	public static void showValidationDialog(IEditorInputResource alternative)
	{
		int numOfWarnings = 0; //alternative.getSelfStatus().getWarningIDs().length;
		int numOfErrors = 0;
		
		int numOfDepWarnings = 0;
		int numOfDepErros = 0;

		LinkedList<Status> statuses = new LinkedList<>();
		
		boolean hasErrors = false;
		boolean hasWarnings = false;
		
		//required alternative
		if(alternative instanceof IConfigAlternative){
			IEditorInput ei = ((IConfigAlternative)alternative).getInputAlternative();
			if(ei instanceof IValidationStatusProvider){
				IValidationStatusProvider sp = (IValidationStatusProvider)ei;
				
				numOfDepErros = countValidationWarnings(sp, IValidationStatus.SEVERITY_ERROR);
				numOfDepWarnings = countValidationWarnings(sp, IValidationStatus.SEVERITY_WARNING);
				
				numOfErrors += numOfDepErros;
				numOfWarnings += numOfDepWarnings;

			}
		}
		
		if(numOfDepErros > 0){
			statuses.add(new Status(IStatus.ERROR, 
					Activator.PLUGIN_ID, String.format("> %s", "Input alternative has validation errors.")));
		}
		if(numOfDepWarnings > 0){
			statuses.add(new Status(IStatus.WARNING, 
					Activator.PLUGIN_ID, String.format("> %s", "Input alternative has validation warnings.")));
		}
		
		//add self status warnings
		for(Warning warning : alternative.getSelfStatus().getWarnings()){
			
			if(warning.severity == IValidationStatus.SEVERITY_ERROR){
				hasErrors = true;
				numOfErrors++;
			}
			else if(warning.severity == IValidationStatus.SEVERITY_WARNING){
				hasWarnings = true;
				numOfWarnings++;
			}
			
			String message = warning.message;
			Status s = new Status(warning.getEclipseSeverity(), Activator.PLUGIN_ID, String.format("> %s", message));
			statuses.add(s);
			
		}
		
		//add sub status warnings
		for(IValidationStatus vs : alternative.getSubStatuses()){
			for(Warning warning : vs.getWarnings()){
				
				if(warning.severity == IValidationStatus.SEVERITY_ERROR){
					hasErrors = true;
					numOfErrors++;
				}
				else if(warning.severity == IValidationStatus.SEVERITY_WARNING){
					hasWarnings = true;
					numOfWarnings++;
				}
				
				String message = warning.message;
				Status s = new Status(warning.getEclipseSeverity(), Activator.PLUGIN_ID, String.format("> %s", message));
				statuses.add(s);				
			}
		}

		String msg = String.format("Alternative '%s' validation status.", alternative.getName());
		String reason = String.format("Press 'Details...' button to display suggestions.");
		
		if(numOfErrors > 0){
			msg = String.format("Unable to run alternative '%s'.", alternative.getName());

			if(numOfErrors == 1){
				reason = String.format("Alternative validation failed. There is %s validation error.", numOfErrors);
			}
			else{
				reason = String.format("Alternative validation failed. There are %s validation errors.", numOfErrors);
			}
		}
		else if(numOfWarnings > 0){
			msg = String.format("Running the alternative '%s' may produce inconsisten results.", alternative.getName());

			if(numOfErrors == 1){
				reason = String.format("Alternative validation detected %s validation warnings.", numOfWarnings);
			}
			else{
				reason = String.format("Alternative validation detected %s validation warnings.", numOfWarnings);
			}
		}
		
		//calculate overall severity status
		int severity = IStatus.INFO;
		if(hasErrors){
			severity = IStatus.ERROR;
		}
		else if(hasWarnings && severity != IStatus.ERROR){
			severity = IStatus.WARNING;
		}

		MultiStatus ms = new MultiStatus(Activator.PLUGIN_ID, severity, statuses.toArray(new Status[0]), reason, null);
		ErrorDialog.openError(Display.getDefault().getActiveShell(), "Validation info dialog", msg, ms);
	}

}
