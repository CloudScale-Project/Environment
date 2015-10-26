package eu.cloudscaleproject.env.csm2pcm.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeNamePage;


//TODO: implement this class - transformation merge has to be re-enabled first
public class TransformIntoExistingAlternativeWizard extends Wizard{

	//private IProject project;
	private AlternativeNamePage nameSelectionPage;

	public TransformIntoExistingAlternativeWizard(IProject project) {
		//this.project = project;
		nameSelectionPage = new AlternativeNamePage(ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.ANALYSER_INPUT));
	}
	
	@Override
	public void addPages() {
		addPage(nameSelectionPage);
	}
	
	@Override
	public boolean performFinish() {
		/*
		String name = nameSelectionPage.getName();
		
		//execute transformation
		
		
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID);
		IEditorInputResource newInputAlternative = rp.createNewResource(name, null);
		
		*/
		
		return true;
	}
	
	@Override
	public boolean canFinish()
	{
		if (getContainer().getCurrentPage() == getPages()[getPageCount()-1])
			return true;

		return false;
	}

}
