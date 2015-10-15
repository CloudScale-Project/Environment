package eu.cloudscaleproject.env.toolchain.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractConfigAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeNamePage;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeSelectionPage;

public class CreateConfigAlternativeWizard extends Wizard{

	protected IProject project;
	protected ResourceProvider configProvider;
	
	private AlternativeNamePage nameSelectionPage;
	private AlternativeSelectionPage inputSelectionPage;
	private IEditorInputResource inputResource;

	public CreateConfigAlternativeWizard(IProject project, ResourceProvider configProvider, IEditorInputResource inputResource, ResourceProvider inputProvider) {
		
		this.project = project;
		this.configProvider = configProvider;
		
		this.nameSelectionPage = new AlternativeNamePage(configProvider);
		if (inputProvider != null) this.inputSelectionPage = new AlternativeSelectionPage(inputProvider);
		
		this.inputResource = inputResource;
		
		setWindowTitle("Create alternative");
	}
	
	@Override
	public void addPages() {
		addPage(nameSelectionPage);
		addPage(inputSelectionPage);
	}

	@Override
	public boolean performFinish() {
		
		String altName = nameSelectionPage.getName();
		
		if (this.inputSelectionPage != null) this.inputResource = this.inputSelectionPage.getSelection();

		AbstractConfigAlternative alternative = (AbstractConfigAlternative)configProvider.createNewResource(altName, null);
		initAlternative(alternative);	
		
		alternative.setInputAlternative(this.inputResource);
		
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
