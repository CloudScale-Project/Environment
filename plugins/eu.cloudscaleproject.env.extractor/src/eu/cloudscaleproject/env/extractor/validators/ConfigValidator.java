package eu.cloudscaleproject.env.extractor.validators;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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
	
	
	private void validateSomoxConfigs(ConfingAlternative ca)
	{
		IValidationStatus selfStatus = ca.getSelfStatus();
		
		SoMoXConfiguration somoxConfiguration = ca.getSomoxConfiguration();
		
		if (somoxConfiguration == null)
		{
			selfStatus.addWarning("", IValidationStatus.SEVERITY_ERROR, "Somox configuration is missing");
			return;
		}

        if (!(somoxConfiguration.getClusteringConfig().getClusteringMergeThresholdDecrement() > 0 &&
                somoxConfiguration.getClusteringConfig().getClusteringComposeThresholdDecrement() > 0)) {
			selfStatus.addWarning("zeros", IValidationStatus.SEVERITY_ERROR, "The merge and compose threshold increment/decrement have to be positive numbers");
        }
        if (!(somoxConfiguration.getClusteringConfig().getMinComposeClusteringThreshold() < somoxConfiguration.getClusteringConfig().getMaxComposeClusteringThreshold())) {
			selfStatus.addWarning("clustering-minmax", IValidationStatus.SEVERITY_ERROR, "The minimum clustering threshold must be lower than maximum clustering threshold");
        }
        if (!(somoxConfiguration.getClusteringConfig().getMinMergeClusteringThreshold() < somoxConfiguration.getClusteringConfig().getMaxMergeClusteringThreshold())) {
			selfStatus.addWarning("merge-minmax", IValidationStatus.SEVERITY_ERROR, "The minimum merge threshold must be lower than maximum merge threshold");
        }
        
        validateBlacklist(ca);
	}
	
	private void validateBlacklist(ConfingAlternative ca)
	{
		IValidationStatus selfStatus = ca.getSelfStatus();
		SoMoXConfiguration somoxConfiguration = ca.getSomoxConfiguration();
		
		Set<String> blacklist = somoxConfiguration.getBlacklist();
		
		for (String s : blacklist){
			try{
				Pattern.compile(s);
			}catch(PatternSyntaxException e)
			{
				selfStatus.addWarning("blacklist-item-["+s+"]", IValidationStatus.SEVERITY_ERROR, "Blacklist item is invalid -> "+e.getDescription());
			}
		}
	}
	
}
