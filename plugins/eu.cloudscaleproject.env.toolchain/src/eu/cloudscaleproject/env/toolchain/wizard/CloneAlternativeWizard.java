package eu.cloudscaleproject.env.toolchain.wizard;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeSelectionPage;

public class CloneAlternativeWizard extends CreateAlternativeWizard{

	protected IProject project;
	protected ResourceProvider provider;
	
	private AlternativeSelectionPage alternativeSelectionPage;

	
	public CloneAlternativeWizard(ResourceProvider provider) {
		super (provider.getProject(), provider);
		
		alternativeSelectionPage = new AlternativeSelectionPage();
		alternativeSelectionPage.setResourceProvider(provider);
		
		setWindowTitle("Clone alternative");
	}
	
	@Override
	public void addPages() {
		super.addPages();
		
		addPage(alternativeSelectionPage);
	}

	protected void initAlternative (IEditorInputResource alternative)
	{
		String name = alternative.getName();
		IEditorInputResource selection = alternativeSelectionPage.getSelection();
		alternative.copyFrom(selection.getResource());
		alternative.setName(name);
	}
	

}
