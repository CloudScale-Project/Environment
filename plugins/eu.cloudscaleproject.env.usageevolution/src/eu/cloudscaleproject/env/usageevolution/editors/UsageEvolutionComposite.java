package eu.cloudscaleproject.env.usageevolution.editors;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.presentation.DlimModelWizard;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;
import eu.cloudscaleproject.env.usageevolution.editors.composite.EditorComposite;

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
					EditorComposite editor = new EditorComposite(alt, parent, style);
					return editor;
				}
				
				return null;
			}
		});
		//init();
	}
	
	@Override
	public void handleNewInput(IEditorInput selected) {
		
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.USAGEEVOLUTION_ID);
		
		DlimModelWizard createInputAltWizard = new DlimModelWizard();
		createInputAltWizard.init(PlatformUI.getWorkbench(), new StructuredSelection());
		WizardDialog wizardDialog = new WizardDialog(this.getShell(), createInputAltWizard);
		wizardDialog.open();
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}
}
