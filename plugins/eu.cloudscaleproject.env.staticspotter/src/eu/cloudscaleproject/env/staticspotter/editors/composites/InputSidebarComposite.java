package eu.cloudscaleproject.env.staticspotter.editors.composites;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.staticspotter.alternatives.InputAlternative;
import eu.cloudscaleproject.env.staticspotter.wizard.CreateInputWizard;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class InputSidebarComposite extends SidebarEditorComposite {
	
	private final String[] sections = new String[]{"Inputs:"};
	private IProject project;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InputSidebarComposite(IProject project, Composite parent, int style) {
		super(parent, style);
		
		this.project = project;
		
		setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.SPOTTER_STA_INPUT));
		
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
				return new InputAlternativeComposite(parent, style, (InputAlternative)resource);
			}
		});
	}

	@Override
	public void handleNewInput(IEditorInput selected) {
		CreateInputWizard inputSelectionWizard = new CreateInputWizard();
		inputSelectionWizard.setProject(project);
		
		WizardDialog wizardDialog = new WizardDialog(this.getShell(), inputSelectionWizard);
		wizardDialog.open();
	}
	
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}
}
