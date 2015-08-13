package eu.cloudscaleproject.env.analyser.editors.input;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.analyser.wizard.CreateInputSelectionWizard;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class InputSidebarComposite extends SidebarEditorComposite{
	
	private static final String SECTION_ALT = "Alternative inputs:";
	
	private final IProject project;
		
	public InputSidebarComposite(final IEditorPart editor, Composite parent, int style) {
		super(parent, style);
		
		this.project = ExplorerProjectPaths.getProject(editor);
		
		setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, CSTool.ANALYSER_INPUT));
		setContentProvider(new SidebarContentProvider() {
			
			@Override
			public String[] getSections() {
				return new String[]{SECTION_ALT};
			}
			
			@Override
			public String getSection(IEditorInputResource resource) {
				return SECTION_ALT;
			}
			
			@Override
			public Composite createComposite(Composite parent, int style, IEditorInputResource resource) {
				return new InputComposite((InputAlternative)resource, parent, SWT.NONE);
			}
		});
	}
	
	@Override
	public void handleNewInput(IEditorInput selected) {
		
		CreateInputSelectionWizard createInputAltWizard = new CreateInputSelectionWizard(project);
		WizardDialog wizardDialog = new WizardDialog(this.getShell(), createInputAltWizard);
		wizardDialog.open();
	}
}
