package eu.cloudscaleproject.env.extractor.validators;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.modisco.infra.discovery.catalog.DiscovererDescription;
import org.eclipse.modisco.infra.discovery.core.IDiscoveryManager;
import org.eclipse.modisco.infra.discovery.launch.LaunchConfiguration;
import org.eclipse.modisco.infra.discovery.ui.internal.util.LaunchModelUtils;

import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.extractor.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.CSToolResource;

public class InputValidator implements IResourceValidator {
	
	private static final String INFO_NO_PROJECT = InputValidator.class.getName() + ".NoProject";
	private static final String INFO_NO_PROJECT_NAME = InputValidator.class.getName() + ".NoProjectName";
	private static final String INFO_NO_PROJECT_JAVA = InputValidator.class.getName() + ".NoProjectJava";

	@Override
	public String getID() {
		return CSToolResource.EXTRACTOR_INPUT.getID();
	}
	

	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		
		IValidationStatus status = statusProvider.getSelfStatus();
		
		InputAlternative ia = (InputAlternative) statusProvider;
		ia.getProject();
				
		status.clearWarnings();
		
		checkProject(ia);
		checkModiscoConfigs(ia);
		
		if (ia.getSelfStatus().getWarnings().length>0)
			status.setIsValid(false);
		else
			status.setIsValid(true);
	}
	
	private boolean checkProject (InputAlternative ia)
	{
		String projectName = ia.getExtractedProjectName();
		
		if (projectName == null || projectName.isEmpty())
		{
			ia.getSelfStatus().addWarning(INFO_NO_PROJECT_NAME, IValidationStatus.SEVERITY_ERROR, "Project is missing.");
			return false;
		}
		
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = root.getProject(projectName);
		
		if (project == null || !project.exists())
		{
			ia.getSelfStatus().addWarning(INFO_NO_PROJECT, IValidationStatus.SEVERITY_ERROR, "Project does not exist. -> Project Name = "+projectName);
			return false;
		}
		
		
		final String JAVA_NATURE_ID = "org.eclipse.jdt.core.javanature";
		try
		{
			IProjectNature nature = project.getNature(JAVA_NATURE_ID);
			if (nature == null) throw new Exception();
		} catch (Exception e)
		{
			ia.getSelfStatus().addWarning(INFO_NO_PROJECT_JAVA, IValidationStatus.SEVERITY_ERROR, "Project is not Java nature.");
			return false;
		}
		
		return true;
	}

	private boolean checkModiscoConfigs (InputAlternative ca)
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
}
