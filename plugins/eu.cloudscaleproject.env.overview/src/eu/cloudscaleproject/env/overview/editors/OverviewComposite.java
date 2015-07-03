package eu.cloudscaleproject.env.overview.editors;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.overview.OverviewAlternative;
import eu.cloudscaleproject.env.overview.wizard.OverviewSelectionWizard;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class OverviewComposite extends SidebarEditorComposite {
	
	private final IProject project;
	private static final String[] SECTIONS = new String[]{"Overview models:"};
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public OverviewComposite(IProject project, Composite parent, int style) {
		super(parent, style);
		
		this.project = project;
		
		setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, CSTool.OVERVIEW));
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
					return new OverviewEditor((OverviewAlternative)resource, parent, style);
				}
				return null;
			}
		});
	}
	
	@Override
	public void handleNewInput(IEditorInput selected) {
		OverviewSelectionWizard overviewWizard = new OverviewSelectionWizard(project);
		WizardDialog wizardDialog = new WizardDialog(this.getShell(), overviewWizard);
		wizardDialog.open();
	}
}
