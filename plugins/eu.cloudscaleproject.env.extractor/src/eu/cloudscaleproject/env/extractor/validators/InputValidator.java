package eu.cloudscaleproject.env.extractor.validators;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.notification.ToolValidator;
import eu.cloudscaleproject.env.extractor.InputPersitenceFile;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class InputValidator extends ToolValidator {

	@Override
	public String getToolID() {
		return StatusManager.Tool.EXTRACTOR_INPUT.getID();
	}

	@Override
	public IResource[] getDependantResources(IProject project) {
		return new IResource[]{ToolchainUtils.getToolFolder(project, ToolchainUtils.EXTRACTOR_INPUT_ID)};
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
	protected boolean doValidate(IProject project, IToolStatus status) {
		status.setIsDone(true);

		ResourceProvider inputResourceProvider = ResourceRegistry.getInstance().
				getResourceProvider(project, ToolchainUtils.EXTRACTOR_INPUT_ID);
		
		List<IEditorInputResource> resources = inputResourceProvider.getResources();
		
		if (resources.isEmpty())
		{
			status.setIsInProgress(false);
			status.setIsDone(false);
		}
		else
		{
			boolean isDone = false;
			for (IEditorInputResource r : resources)
			{
				isDone |= validateInput(r);
			}
			
			status.setIsInProgress(true);
			status.setIsDone(isDone);
		}

		return true;
	}

}
