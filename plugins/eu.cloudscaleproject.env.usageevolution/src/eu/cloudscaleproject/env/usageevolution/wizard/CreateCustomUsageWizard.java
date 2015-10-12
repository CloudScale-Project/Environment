package eu.cloudscaleproject.env.usageevolution.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeNamePage;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;
import tools.descartes.dlim.generator.editor.wizards.CustomDlimModelWizard;

public class CreateCustomUsageWizard extends CustomDlimModelWizard{


	private IProject project;
	private ResourceProvider resourceProvider;
	private UsageEvolutionAlternative alternative;
	private AlternativeNamePage namePage;

	public CreateCustomUsageWizard(IProject project, ResourceProvider resourceProvider)
	{
		this.project = project;
		this.resourceProvider = resourceProvider;
		
		init (PlatformUI.getWorkbench(), new StructuredSelection(this.project));
	}
	

	@Override
	public IFile getModelFile()
	{
		return (IFile) alternative.getSubResource(ToolchainUtils.KEY_FILE_LIMBO);
	}
	
	
	@Override
	public boolean performFinish()
	{
		// Create alternative 
		this.alternative = (UsageEvolutionAlternative)resourceProvider.createNewResource(this.namePage.getName(), null);
		
		boolean res = super.performFinish();
		
		if (res)
		{
			// Close just opened editor
			IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
			IWorkbenchPage page = workbenchWindow.getActivePage();
			page.closeEditor(page.getActiveEditor(), false);
			
			// DlimEditor Throws Assertion error because 
			// setActivePage is called async in DlimEditor
			// Not really a problem

			OpenAlternativeUtil.openAlternative(alternative);
		}
		else
		{
			this.alternative.delete();
		}
		
		return res;
	}
	
	@Override
	public void addPages()
	{
		this.namePage = new AlternativeNamePage(resourceProvider);
		addPage(namePage);
		super.addPages();
	}

	@Override
	public void addPage(IWizardPage page)
	{
		// Skip NewFile creation page
		if (page instanceof DlimModelWizardNewFileCreationPage)
		{
			return;
		}
		
		super.addPage(page);
	}
}
