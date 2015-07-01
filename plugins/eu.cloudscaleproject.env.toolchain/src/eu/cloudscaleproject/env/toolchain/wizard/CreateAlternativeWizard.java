package eu.cloudscaleproject.env.toolchain.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeNamePage;

public class CreateAlternativeWizard extends Wizard{

	protected IProject project;
	protected ResourceProvider provider;
	
	private AlternativeNamePage nameSelectionPage;

	public CreateAlternativeWizard(IProject project, ResourceProvider provider) {
		
		this.project = project;
		this.provider = provider;
		
		nameSelectionPage = new AlternativeNamePage(provider);
		
		setWindowTitle("Create alternative");
	}
	
	@Override
	public void addPages() {
		addPage(nameSelectionPage);
	}

	@Override
	public boolean performFinish() {
		
		String altName = nameSelectionPage.getName();

		IEditorInputResource alternative = (IEditorInputResource)provider.createNewResource(altName, null);
		initAlternative(alternative);	

		ValidationDiagramService.showStatus(project, alternative);
		OpenAlternativeUtil.openAlternative(alternative);
		
		return true;
	}
	

	protected void initAlternative (IEditorInputResource alternative)
	{
		// Nothing to do here - overwrite
	}
	
	
	@Override
	public boolean canFinish()
	{
		if (getContainer().getCurrentPage() == getPages()[getPageCount()-1] 
				&& getContainer().getCurrentPage().isPageComplete())
			return true;

		return false;
	}

}
