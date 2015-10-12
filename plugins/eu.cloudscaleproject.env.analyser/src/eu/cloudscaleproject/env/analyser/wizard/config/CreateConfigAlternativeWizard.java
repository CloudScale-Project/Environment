package eu.cloudscaleproject.env.analyser.wizard.config;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative.Type;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeNamePage;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeSelectionPage;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class CreateConfigAlternativeWizard extends Wizard{
	
	private final Type type;
	
	private final ResourceProvider inputResourceProvider;
	private final ResourceProvider confResourceProvider;

	private final AlternativeNamePage selectNamePage;
	private final AlternativeSelectionPage alternativeSelectionPage;
	
	public CreateConfigAlternativeWizard(IProject project, ConfAlternative.Type type) {
		this.type = type;
		
		this.inputResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSTool.ANALYSER_INPUT);
		this.confResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSTool.ANALYSER_CONF);
		
		this.selectNamePage = new AlternativeNamePage(confResourceProvider);
		this.alternativeSelectionPage = new AlternativeSelectionPage("Input alternative selection", 
																	 "Please select the input alternative.", 
																	 inputResourceProvider);
	}
	
	@Override
	public void addPages() {
		
		addPage(this.selectNamePage);
		addPage(alternativeSelectionPage);
		
		super.addPages();
	}

	@Override
	public boolean performFinish() {
		
		ConfAlternative ca = (ConfAlternative)this.confResourceProvider.createNewResource(this.selectNamePage.getName(), type.name());
		
		if(alternativeSelectionPage.getSelection() != null){
			ca.setInputAlternative((InputAlternative)alternativeSelectionPage.getSelection());
			ca.save();
		}		
		OpenAlternativeUtil.openAlternative(ca);
		
		return true;
	}
	
	@Override
	public boolean canFinish()
	{
		if (getContainer().getCurrentPage() == selectNamePage){
			return true;
		}
		if (getContainer().getCurrentPage() == alternativeSelectionPage){
			return true;
		}
		return false;
	}

}
