package eu.cloudscaleproject.env.analyser.editors;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.dialogs.NewConfigAlternativeDialog;
import eu.cloudscaleproject.env.analyser.editors.config.ConfigBasicComposite;
import eu.cloudscaleproject.env.analyser.editors.config.ConfigCapacity;
import eu.cloudscaleproject.env.analyser.editors.config.SelectInputAltComposite;
import eu.cloudscaleproject.env.analyser.editors.config.ConfigMonitorListComposite;
import eu.cloudscaleproject.env.analyser.editors.config.ConfigSLOListComposite;
import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.dialogs.DialogUtils;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.ui.IRefreshable;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ProjectEditorSelectionService;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.ui.RunComposite;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class ConfigComposite extends SidebarEditorComposite
{

	private static final String SECTION_ALT = "Alternative conf:";

	private final IProject project;

	public ConfigComposite(final IEditorPart editor, Composite parent, int style)
	{
		super(parent, style);

		this.project = ExplorerProjectPaths.getProject(editor);

		setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_CONF_ID));
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
				return new RightPanelComposite(editor, (ConfAlternative) resource, parent, SWT.NONE);
			}
		});
	}

	private class RightPanelComposite extends RunComposite implements IPropertySheetPageProvider, IRefreshable{

		private SelectInputAltComposite editComposite;
		private Composite configComposite;

		// private ConfigTreeviewComposite sloTreeview;

		private ConfigMonitorListComposite monitorsComposite;
		private ConfigSLOListComposite sloComposite;

		private EMFEditableTreeviewComposite advancedTreeview;

		private final ConfAlternative alternative;

		private CTabFolder tabFolder;

		public RightPanelComposite(IEditorPart editor, final ConfAlternative input, Composite parent, int style){
			super(parent, style);

			IProject project = ExplorerProjectPaths.getProject(editor);

			this.alternative = input;

			GridLayout layout = new GridLayout(1, true);
			layout.marginWidth = 0;
			layout.marginHeight = 0;
			getContainer().setLayout(layout);

			String name = input.getTypeEnum() != null ? input.getTypeEnum().toString() : "Normal";
			setTitle(name + " measurement type");

			editComposite = new SelectInputAltComposite(project, input, getContainer(), SWT.NONE);
			GridData iac_gd = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
			editComposite.setLayoutData(iac_gd);
			editComposite.pack();

			tabFolder = new CTabFolder(getContainer(), SWT.NONE);
			GridData tabFolder_gd = new GridData(SWT.FILL, SWT.FILL, true, true);
			tabFolder.setLayoutData(tabFolder_gd);

			// basic settings
			{
				CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
				tabItem.setText("Basic settings");

				if (ConfAlternative.Type.NORMAL.equals(alternative.getTypeEnum()))
				{
					configComposite = new ConfigBasicComposite(input, tabFolder, SWT.NONE);
					tabItem.setControl(configComposite);
				} else if (ConfAlternative.Type.CAPACITY.equals(alternative.getTypeEnum()))
				{
					configComposite = new ConfigCapacity(input, tabFolder, SWT.NONE);
					tabItem.setControl(configComposite);
				} else if (ConfAlternative.Type.SCALABILITY.equals(alternative.getTypeEnum()))
				{
					configComposite = new ConfigCapacity(input, tabFolder, SWT.NONE);
					tabItem.setControl(configComposite);
				}
				tabFolder.setSelection(tabItem);
			}

			// measurements settings
			{
				CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
				tabItem.setText("Monitors");

				monitorsComposite = new ConfigMonitorListComposite(input, tabFolder, style);
				monitorsComposite.pack();
				tabItem.setControl(monitorsComposite);
			}

			// slo settings
			{
				CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
				tabItem.setText("Service level objectives");

				sloComposite = new ConfigSLOListComposite(input, tabFolder, style);
				sloComposite.pack();
				tabItem.setControl(sloComposite);
			}
			// advance settings
			{
				CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
				tabItem.setText("Advanced editor");

				advancedTreeview = new EMFEditableTreeviewComposite(input, tabFolder, style);
				tabItem.setControl(advancedTreeview);
			}

			tabFolder.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent e)
				{
					Control c = tabFolder.getSelection().getControl();

					if (c instanceof IRefreshable)
					{
						((IRefreshable) c).refresh();
					}

					ProjectEditorSelectionService.getInstance().reloadPropertySheetPage();
				}
			});
		}
		
		@Override
		public void refresh() {
			
			if(tabFolder.isDisposed()){
				return;
			}
			
			Control c = tabFolder.getSelection().getControl();
			if (c instanceof IRefreshable)
			{
				((IRefreshable) c).refresh();
			}
			ProjectEditorSelectionService.getInstance().reloadPropertySheetPage();
		}

		@Override
		public IPropertySheetPage getPropertySheetPage()
		{
			Control c = tabFolder.getSelection().getControl();
			if (c instanceof EMFEditableTreeviewComposite)
			{
				return ((EMFEditableTreeviewComposite) c).getPropertySheetPage();
			}

			return null;
		}

		@Override
		protected IStatus doRun(IProgressMonitor m)
		{
			//save alternative before simulation run
			if(alternative.getInputAlternative() == null){
				DialogUtils.openError("Can not run this configuration. Input alternative is not set.");
				return Status.CANCEL_STATUS;
			}
			
			alternative.getInputAlternative().save();
			
			alternative.configureResults();
			alternative.save();
			
			ILaunchManager mgr = DebugPlugin.getDefault().getLaunchManager();
			ILaunchConfigurationType lct = mgr
					.getLaunchConfigurationType("org.palladiosimulator.experimentautomation.application.launchConfigurationType");

			try
			{
				// System.out.println(ca.getExperiments().getURI().toString());
				ILaunchConfigurationWorkingCopy lcwc = lct.newInstance((IFolder) alternative.getResource(), alternative.getResource()
						.getName());
				lcwc.setAttribute("Experiment Automation", alternative.getExperiment().eResource().getURI().toString());
				lcwc.setAttribute("de.uka.ipd.sdq.workflowengine.debuglevel", 2);
				lcwc.setAttribute("outpath", "org.palladiosimulator.temporary");
				lcwc.doSave();

				lcwc.launch(ILaunchManager.DEBUG_MODE, m);
				return Status.OK_STATUS;
			} catch (CoreException e1)
			{
				return new Status(Status.ERROR, "", e1.getLocalizedMessage());
			}
		}
	}

	@Override
	public void handleNewInput(IEditorInput selected)
	{
		NewConfigAlternativeDialog dialog = new NewConfigAlternativeDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				new BasicCallback<String[]>()
				{

					@Override
					public void handle(String[] data)
					{
						ResourceProvider resourceProvider = ResourceRegistry.getInstance().getResourceProvider(project,
								ToolchainUtils.ANALYSER_CONF_ID);
						if (resourceProvider == null)
						{
							throw new IllegalStateException("Sidebar resource provider not set!");
						}
						resourceProvider.createNewResource(data[0], data[1]);
					}
				});
		dialog.open();
	}
}
