package eu.cloudscaleproject.env.staticspotter.validators;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.staticspotter.alternatives.ConfigAlternative;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;

public class ConfigValidator implements IResourceValidator {

	public ConfigValidator()
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getID() {
		return ToolchainUtils.SPOTTER_STA_CONF_ID;
	}
	
	@Override
	public void validate(final IProject project, IValidationStatusProvider statusProvider) {
		
		//GlobalInputAlternative.getInstance().setProject(project);
		//GlobalInputAlternative.getInstance().validate();

		ConfigAlternative ca = (ConfigAlternative) statusProvider;

		IValidationStatus status = statusProvider.getSelfStatus();
		status.clearWarnings();
		status.setIsValid(true);

		if (ca.getInputAlternative() == null)
		{
			status.addWarning("", "Input alternative not selected.");
			status.setIsValid(false);
		}
		
		if (!validateCatalog(ca) | !validateEngines(ca))
		{
			status.setIsValid(false);
		}
	}
	
	private boolean validateCatalog (ConfigAlternative ca)
	{
		IValidationStatus selfStatus = ca.getSelfStatus();

		IResource catalog = ca.getSubResource(ConfigAlternative.KEY_CATALOG);
		
		if (catalog == null)
		{
			selfStatus.addWarning("", "Static Spotter catalog is missing.");
			return false;
		}
		
		return true;
	}

	private boolean validateEngines (ConfigAlternative ca)
	{
		IValidationStatus selfStatus = ca.getSelfStatus();

		IResource engines = ca.getSubResource(ConfigAlternative.KEY_ENGINES);

		if (engines == null || !engines.exists())
		{
			selfStatus.addWarning("", "Static Spotter engines definition are missing.");
			return false;
		}
		
		return true;
	}
}
