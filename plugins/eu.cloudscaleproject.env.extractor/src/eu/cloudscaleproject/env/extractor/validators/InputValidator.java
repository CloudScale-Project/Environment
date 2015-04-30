package eu.cloudscaleproject.env.extractor.validators;

import java.util.List;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.extractor.InputPersitenceFile;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class InputValidator implements IResourceValidator {

	@Override
	public String getID() {
		return ToolchainUtils.EXTRACTOR_INPUT_ID;
	}
	
	private boolean validateInput (IEditorInputResource r)
	{
		if (!(r instanceof InputPersitenceFile))
			throw new IllegalStateException("Unknown input resource");

        InputPersitenceFile ipf = (InputPersitenceFile) r;

        String projectUrl = ipf.getProperty(InputPersitenceFile.KEY_PROJECT_URL);
        
        if (projectUrl == null)
        {
            return false;
        }
        
        return true;
	}

	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		
		IValidationStatus status = statusProvider.getSelfStatus();

		ResourceProvider inputResourceProvider = ResourceRegistry.getInstance().
				getResourceProvider(project, ToolchainUtils.EXTRACTOR_INPUT_ID);
		
		List<IEditorInputResource> resources = inputResourceProvider.getResources();
		
		if (resources.isEmpty())
		{
			status.setIsValid(false);
		}
		else
		{
			boolean isDone = false;
			for (IEditorInputResource r : resources)
			{
				isDone |= validateInput(r);
			}			
			status.setIsValid(isDone);
		}
	}

}
