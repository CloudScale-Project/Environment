package eu.cloudscaleproject.env.extractor.validators;

import org.eclipse.core.resources.IProject;
import org.eclipse.modisco.infra.discovery.catalog.DiscovererDescription;
import org.eclipse.modisco.infra.discovery.core.IDiscoveryManager;
import org.eclipse.modisco.infra.discovery.launch.LaunchConfiguration;
import org.eclipse.modisco.infra.discovery.ui.internal.util.LaunchModelUtils;
import org.somox.configuration.SoMoXConfiguration;

import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.extractor.alternatives.ConfingAlternative;
import eu.cloudscaleproject.env.extractor.alternatives.GlobalInputAlternative;
import eu.cloudscaleproject.env.toolchain.CSTool;

public class ConfigValidator implements IResourceValidator {

	public ConfigValidator()
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getID() {
		return CSTool.EXTRACTOR_CONF.getID();
	}
	
	@Override
	public void validate(final IProject project, IValidationStatusProvider statusProvider) {
		
		GlobalInputAlternative.getInstance().setProject(project);
		GlobalInputAlternative.getInstance().validate();

		ConfingAlternative ca = (ConfingAlternative) statusProvider;
		IValidationStatus status = statusProvider.getSelfStatus();

		status.clearWarnings();
		status.setIsValid(true);

		IProject sourceProject = ca.getExtractedProject();
		if (sourceProject == null || !sourceProject.exists())
		{
			status.addWarning("", IValidationStatus.SEVERITY_ERROR, "Input project not selected.");
			status.setIsValid(false);
		}
		
		if (!validateModiscoConfigs(ca) | !validateSomoxConfigs(ca))
		{
			status.setIsValid(false);
		}
	}
	
	private boolean validateModiscoConfigs (ConfingAlternative ca)
	{
		IValidationStatus selfStatus = ca.getSelfStatus();

		LaunchConfiguration modiscoConfiguration = ca.getModiscoConfiguration();
		
		if (modiscoConfiguration == null)
		{
			selfStatus.addWarning("", IValidationStatus.SEVERITY_ERROR, "Modisco configuration is missing");
			return false;
		}

		String ID_DISCOVERER = "org.eclipse.modisco.java.composition.discoverer.fromProject";
		DiscovererDescription discoverer = IDiscoveryManager.INSTANCE.getDiscovererDescription(ID_DISCOVERER);

		Object paramSerializeTarget = LaunchModelUtils.getDiscoveryParameterValue(modiscoConfiguration, 
				discoverer.getParameterDefinition("SERIALIZE_TARGET"));

		Object paramDeepAnlysis = LaunchModelUtils.getDiscoveryParameterValue(modiscoConfiguration, 
				discoverer.getParameterDefinition("DEEP_ANALYSIS"));

		if (paramDeepAnlysis != Boolean.TRUE || paramSerializeTarget != Boolean.TRUE)
		{
			selfStatus.addWarning("", IValidationStatus.SEVERITY_ERROR, "Modisco is misconfigered : DEEP_ANALYSIS and SERIALIZE_TARGET must be set to TRUE");
			return false;
		}
		
		return true;
		
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
		
		return true;
		
	}
		

}
