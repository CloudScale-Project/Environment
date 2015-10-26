package eu.cloudscaleproject.env.extractor.editors.composites;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.extractor.alternatives.ConfingAlternative;
import eu.cloudscaleproject.env.extractor.wizard.CreateConfigSelectionWizard;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class ConfigSidebarComposite extends SidebarEditorComposite {
	
	private final String[] sections = new String[]{"Configurations:"};
	private IProject project;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ConfigSidebarComposite(IProject project, Composite parent, int style) {
		super(parent, style);
		
		this.project = project;
		
		setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.EXTRACTOR_CONF));
		
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
				return new ConfigAlternativeComposite(parent, style, (ConfingAlternative)resource);
			}
		});
		
		//init();
	}
	
	@Override
	public void handleNewInput(IEditorInput selected)
	{
		CreateConfigSelectionWizard createInputAltWizard = new CreateConfigSelectionWizard(this.project);
		WizardDialog wizardDialog = new WizardDialog(this.getShell(), createInputAltWizard);
		wizardDialog.open();
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}
}
