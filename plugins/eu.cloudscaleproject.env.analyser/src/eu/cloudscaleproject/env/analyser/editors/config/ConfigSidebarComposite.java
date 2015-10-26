package eu.cloudscaleproject.env.analyser.editors.config;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.wizard.config.CreateConfigAlternativeSelectionWizard;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class ConfigSidebarComposite extends SidebarEditorComposite
{

	private static final String SECTION_ALT = "Alternative conf:";

	private final IProject project;

	public ConfigSidebarComposite(final IEditorPart editor, Composite parent, int style)
	{
		super(parent, style);

		this.project = ExplorerProjectPaths.getProject(editor);

		setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.ANALYSER_CONF));
		setContentProvider(new SidebarContentProvider()
		{

			@Override
			public String[] getSections()
			{
				return new String[] { SECTION_ALT };
			}

			@Override
			public String getSection(IEditorInputResource resource)
			{
				return SECTION_ALT;
			}

			@Override
			public Composite createComposite(Composite parent, int style, IEditorInputResource resource)
			{
				return new ConfigComposite((ConfAlternative) resource, parent, SWT.NONE);
			}
		});
	}

	@Override
	public void handleNewInput(IEditorInput selected)
	{
		CreateConfigAlternativeSelectionWizard createconfigAltWizard = new CreateConfigAlternativeSelectionWizard(project);
		WizardDialog wizardDialog = new WizardDialog(this.getShell(), createconfigAltWizard);
		wizardDialog.open();
	}
}
