package eu.cloudscaleproject.env.analyser.editors;

import java.util.List;

import org.eclipse.core.resources.IProject;
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
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPointRepository;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.dialogs.NewConfigAlternativeDialog;
import eu.cloudscaleproject.env.analyser.editors.config.ConfigBasicComposite;
import eu.cloudscaleproject.env.analyser.editors.config.ConfigCapacity;
import eu.cloudscaleproject.env.analyser.editors.config.ConfigMonitorListComposite;
import eu.cloudscaleproject.env.analyser.editors.config.ConfigSLOListComposite;
import eu.cloudscaleproject.env.analyser.editors.config.SelectInputAltComposite;
import eu.cloudscaleproject.env.analyser.editors.config.UsageEvolutionComposite;
import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ProjectEditorSelectionService;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.ui.ConfigEditorView;
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

	private class RightPanelComposite extends ConfigEditorView implements IPropertySheetPageProvider, IRefreshable, ISelectable{

		private SelectInputAltComposite editComposite;
		private Composite configComposite;
		
		private EMFEditableTreeviewComposite measuringPointsComposite;
		private ConfigMonitorListComposite monitorsComposite;
		private ConfigSLOListComposite sloComposite;
		private UsageEvolutionComposite ueComposite;

		private EMFEditableTreeviewComposite advancedTreeview;

		private final ConfAlternative alternative;

		private CTabFolder tabFolder;

		public RightPanelComposite(IEditorPart editor, final ConfAlternative input, Composite parent, int style){
			super(parent, style, input);

			IProject project = ExplorerProjectPaths.getProject(editor);

			this.alternative = input;

			GridLayout layout = new GridLayout(1, true);
			layout.marginWidth = 0;
			layout.marginHeight = 0;
			getContainer().setLayout(layout);

			editComposite = new SelectInputAltComposite(project, input, getContainer(), SWT.NONE);
			GridData iac_gd = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
			editComposite.setLayoutData(iac_gd);
			editComposite.pack();

			tabFolder = new CTabFolder(getContainer(), SWT.BORDER);
			GridData tabFolder_gd = new GridData(SWT.FILL, SWT.FILL, true, true);
			tabFolder.setLayoutData(tabFolder_gd);
			tabFolder.setTabHeight(32);

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
			
			// measuring points editor
			{
				CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
				tabItem.setText("Measuring points");
				
				List<MeasuringPointRepository> mpReps = alternative.getMeasuringPointRepositories();
				MeasuringPointRepository mpRep = mpReps.isEmpty() ? null : mpReps.get(0);
				
				measuringPointsComposite = new EMFEditableTreeviewComposite(input, 
						mpRep,
						tabFolder, style);
				
				tabItem.setControl(measuringPointsComposite);
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
				tabItem.setText("SLO");

				sloComposite = new ConfigSLOListComposite(input, tabFolder, style);
				sloComposite.pack();
				tabItem.setControl(sloComposite);
			}
			
			//usage evolution
			if(ConfAlternative.Type.NORMAL == alternative.getTypeEnum()){
				CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
				tabItem.setText("Usage evolution");

				ueComposite = new UsageEvolutionComposite(input, tabFolder, style);
				ueComposite.pack();
				tabItem.setControl(ueComposite);
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
		public void onSelect() {
			ValidationDiagramService.showStatus(project, alternative.getInputAlternative());
			ValidationDiagramService.showStatus(project, alternative);
		}
		
		@Override
		public void refresh() {
			
			alternative.unloadProxyResources();
			
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
