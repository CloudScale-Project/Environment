package eu.cloudscaleproject.env.usageevolution.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.resource.Resource;

import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.wizard.CreateAlternativeWizard;
import eu.cloudscaleproject.env.usageevolution.DlimGenerator;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;
import eu.cloudscaleproject.env.usageevolution.wizard.pages.TemplateSelectionPage;
import tools.descartes.dlim.Sequence;

public class CreatePredefinedUsageWizard extends CreateAlternativeWizard{
	private TemplateSelectionPage presetSelectionPage = null;

	public CreatePredefinedUsageWizard(IProject project) {
		super(project, ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.USAGEEVOLUTION));
		
		this.project = project;
		
		presetSelectionPage = new TemplateSelectionPage();
	}
	
	@Override
	public void addPages() {
		
		super.addPages();
		addPage(presetSelectionPage);
	}
	
	@Override
	protected void initAlternative(IEditorInputResource alternative) {
		
		doInitAlternative((EditorInputEMF)alternative);
		super.initAlternative(alternative);
	}

	private void doInitAlternative(final EditorInputEMF alternative){
		
		alternative.executeModelChange(new Runnable() {
			
			@Override
			public void run() {

				DlimGenerator.Template selectedPreset = presetSelectionPage.getSelectedTemplate();
				Sequence sequence = DlimGenerator.createTemplateSequance(selectedPreset);

				Resource modelResource = ((UsageEvolutionAlternative)alternative).getModelResource(ToolchainUtils.KEY_FILE_LIMBO);

				modelResource.getContents().clear();
				modelResource.getContents().add(sequence);

				
			}
		});
		
	}
}
