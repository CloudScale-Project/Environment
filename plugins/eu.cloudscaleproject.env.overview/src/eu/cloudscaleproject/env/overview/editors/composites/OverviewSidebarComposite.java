package eu.cloudscaleproject.env.overview.editors.composites;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.overview.OverviewAlternative;
import eu.cloudscaleproject.env.overview.wizard.CreateOverviewWizard;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class OverviewSidebarComposite extends SidebarEditorComposite {
	
	private final IProject project;
	private static final String[] SECTIONS = new String[]{"Overview models:"};
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public OverviewSidebarComposite(IProject project, Composite parent, int style) {
		super(parent, style);
		
		this.project = project;
		
		setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.OVERVIEW));
		setContentProvider(new SidebarContentProvider() {
			
			@Override
			public String[] getSections() {
				return SECTIONS;
			}
			
			@Override
			public String getSection(IEditorInputResource resource) {
				return SECTIONS[0];
			}
			
			@Override
			public Composite createComposite(Composite parent, int style, IEditorInputResource resource) {
				
				if(resource instanceof OverviewAlternative){				
					return new OverviewComposite(parent, style, (OverviewAlternative)resource);
				}
				return null;
			}
		});
	}
	
	@Override
	public void handleNewInput(IEditorInput selected) {
		CreateOverviewWizard overviewWizard = new CreateOverviewWizard();
		overviewWizard.setProject(project);
		
		WizardDialog wizardDialog = new WizardDialog(this.getShell(), overviewWizard);
		wizardDialog.open();
	}
}
