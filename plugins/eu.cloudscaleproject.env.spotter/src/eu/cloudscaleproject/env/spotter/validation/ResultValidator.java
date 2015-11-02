package eu.cloudscaleproject.env.spotter.validation;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.toolchain.CSToolResource;

public class ResultValidator implements IResourceValidator{

	@Override
	public String getID()
	{
		return CSToolResource.SPOTTER_DYN_RES.getID();
	}

	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider)
	{
		IValidationStatus status = statusProvider.getSelfStatus();
		status.setIsValid(true); // Always true
	}

}
