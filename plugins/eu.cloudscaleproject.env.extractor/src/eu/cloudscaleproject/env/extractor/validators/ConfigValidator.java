package eu.cloudscaleproject.env.extractor.validators;

import org.eclipse.core.resources.IProject;
import org.somox.configuration.SoMoXConfiguration;

import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.extractor.alternatives.ConfingAlternative;
import eu.cloudscaleproject.env.toolchain.CSToolResource;

public class ConfigValidator implements IResourceValidator {

	public ConfigValidator()
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getID() {
		return CSToolResource.EXTRACTOR_CONF.getID();
	}
	
	@Override
	public void validate(final IProject project, IValidationStatusProvider statusProvider) {
		
		ConfingAlternative ca = (ConfingAlternative) statusProvider;
		IValidationStatus status = statusProvider.getSelfStatus();

		status.clearWarnings();

		validateSomoxConfigs(ca);

		if (ca.getSelfStatus().getWarnings().length>0)
			status.setIsValid(false);
		else
			status.setIsValid(true);
	}
	
	
	private boolean validateSomoxConfigs(ConfingAlternative ca)
	{
		IValidationStatus selfStatus = ca.getSelfStatus();
		
		SoMoXConfiguration somoxConfiguration = ca.getSomoxConfiguration();
		
		if (somoxConfiguration == null)
		{
			selfStatus.addWarning("", IValidationStatus.SEVERITY_ERROR, "Somox configuration is missing");
			return false;
		}
		
		// TODO: Validate Somox configurations
		
		return true;
		
	}
		

}
