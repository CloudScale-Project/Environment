package eu.cloudscaleproject.env.usageevolution.editors;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;
import eu.cloudscaleproject.env.usageevolution.editors.composite.UsageEvolutionEditorComposite;
import eu.cloudscaleproject.env.usageevolution.wizard.CreateUsageEvolutionWizard;

public class UsageEvolutionComposite extends SidebarEditorComposite {
	
	private final IProject project;
	private final String[] sections = new String[]{"Alternatives:"};
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public UsageEvolutionComposite(IProject project, Composite parent, int style) {
		super(parent, style);
		
		this.project = project;
		
		setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.USAGEEVOLUTION_ID));
		setContentProvider(new SidebarContentProvider() {
			
			@Override
			public String[] getSections() {
				return sections;
			}
			
			@Override
			public String getSection(IEditorInputResource resource) {
				return sections[0];
			}
			
			@Override
			public Composite createComposite(Composite parent, int style,
					IEditorInputResource resource) {
				
				if(resource instanceof UsageEvolutionAlternative){
					UsageEvolutionAlternative alt = (UsageEvolutionAlternative)resource;
					UsageEvolutionEditorComposite editor = new UsageEvolutionEditorComposite(alt, parent, style);
					return editor;
				}
				
				return null;
			}
		});
	}
	
	@Override
	public void handleNewInput(IEditorInput selected) {
		CreateUsageEvolutionWizard createInputAltWizard = new CreateUsageEvolutionWizard(project);
		WizardDialog wizardDialog = new WizardDialog(this.getShell(), createInputAltWizard);
		wizardDialog.open();
	}
}
