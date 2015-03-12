package eu.cloudscaleproject.env.analyser.editors;

import org.eclipse.core.resources.IFile;
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.dialogs.NewConfigInputDialog;
import eu.cloudscaleproject.env.analyser.editors.config.ConfigBasicComposite;
import eu.cloudscaleproject.env.analyser.editors.config.ConfigCapacity;
import eu.cloudscaleproject.env.analyser.editors.config.ConfigEditComposite;
import eu.cloudscaleproject.env.analyser.editors.config.ConfigMonitorsComposite;
import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.ui.RunComposite;
import eu.cloudscaleproject.env.toolchain.util.ConfigTreeviewComposite;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class ConfigComposite extends SidebarEditorComposite{
	
	private static final String SECTION_ALT = "Alternative conf:";
	
	private final IProject project;
				
	public ConfigComposite(final IEditorPart editor, Composite parent, int style) {
		super(parent, style);
		
		this.project = ExplorerProjectPaths.getProject(editor);
		
		setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_CONF_ID));
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
				return new RightPanelComposite(editor, (ConfAlternative)resource, parent, SWT.NONE);
			}
		});
	}
	
	private class RightPanelComposite extends RunComposite implements IPropertySheetPageProvider{
		
		private ConfigEditComposite editComposite;
		
		private ConfigTreeviewComposite sloTreeview;
		private ConfigMonitorsComposite monitorsComposite;
		private ConfigTreeviewComposite advancedTreeview;
		
		private ConfigTreeviewComposite currentTreeview;
		
		private final ConfAlternative alternative;
		

		public RightPanelComposite(IEditorPart editor, final ConfAlternative input, Composite parent, int style) {
			super(parent, style);
			
			IProject project = ExplorerProjectPaths.getProject(editor);

			this.alternative = input;
					
			GridLayout layout = new GridLayout(1, true);
			layout.marginWidth = 0;
			layout.marginHeight = 0;
			getContainer().setLayout(layout);
			
			String name = input.getTypeEnum() != null ? input.getTypeEnum().toString() : "Normal";
			setTitle(name + " measurement type");
			
			editComposite = new ConfigEditComposite(project, input, getContainer(), SWT.NONE);
			GridData iac_gd = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
			editComposite.setLayoutData(iac_gd);
			editComposite.pack();
			
			final CTabFolder tabFolder = new CTabFolder(getContainer(), SWT.NONE);
			GridData tabFolder_gd = new GridData(SWT.FILL, SWT.FILL, true, true);
			tabFolder.setLayoutData(tabFolder_gd);
			
			tabFolder.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					super.widgetSelected(e);
					Control c = tabFolder.getSelection().getControl();
					if(c instanceof ConfigTreeviewComposite){
						currentTreeview = (ConfigTreeviewComposite)c;
						currentTreeview.setFocus();
						getContainer().update();
					}
				}
			});

			//basic settings
			{
				CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
				tabItem.setText("Basic settings");
				
				if(ConfAlternative.Type.NORMAL.equals(alternative.getTypeEnum())){
					Composite basicComposite = new ConfigBasicComposite(input, tabFolder, SWT.NONE);
					tabItem.setControl(basicComposite);
				}
				else if(ConfAlternative.Type.CAPACITY.equals(alternative.getTypeEnum())){
					Composite capacityComposite = new ConfigCapacity(input, tabFolder, SWT.NONE);
					tabItem.setControl(capacityComposite);
				}
				else if(ConfAlternative.Type.SCALABILITY.equals(alternative.getTypeEnum())){
					Composite capacityComposite = new ConfigCapacity(input, tabFolder, SWT.NONE);
					tabItem.setControl(capacityComposite);
				}
				tabFolder.setSelection(tabItem);
			}
			
			//measurements settings
			{
				CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
				tabItem.setText("Monitors");
				
				ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.V_SCROLL);
				monitorsComposite = new ConfigMonitorsComposite(input, scrolledComposite, style);
				scrolledComposite.setContent(monitorsComposite);
				monitorsComposite.pack();
				scrolledComposite.setExpandHorizontal(true);
				
				tabItem.setControl(scrolledComposite);
			}
			
			//slo settings
			{
				CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
				tabItem.setText("Service level objectives");
				
				sloTreeview = new ConfigTreeviewComposite(input, tabFolder, style);
				sloTreeview.addFilter(new ViewerFilter() {
					@Override
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						
						Resource resource = null;
						
						if(element instanceof Resource){
							resource = (Resource)element;
						}
						if(element instanceof EObject){
							EObject object = (EObject) element;
							resource = object.eResource();
						}
						if(resource != null){
							IFile file = ExplorerProjectPaths.getFileFromEmfResource(resource);
							if(input.getSubResources(ToolchainUtils.KEY_FILE_SLO).contains(file)){
								return true;
							}
						}
						
						return false;
					}
				});
				tabItem.setControl(sloTreeview);
			}
			//advance settings
			{
				CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
				tabItem.setText("Advanced editor");
				
				advancedTreeview = new ConfigTreeviewComposite(input, tabFolder, style);
				tabItem.setControl(advancedTreeview);
			}
		}

		@Override
		public IPropertySheetPage getPropertySheetPage() {
			if(currentTreeview != null){
				return currentTreeview.getPropertySheetPage();
			}
			return null;
		}

		@Override
		protected IStatus doRun(IProgressMonitor m)
		{
				ILaunchManager mgr = DebugPlugin.getDefault().getLaunchManager();
				ILaunchConfigurationType lct = mgr.getLaunchConfigurationType("org.palladiosimulator.experimentautomation.application.launchConfigurationType");
				
				try {
					//System.out.println(ca.getExperiments().getURI().toString());
					ILaunchConfigurationWorkingCopy lcwc = lct.newInstance((IFolder)alternative.getResource(), alternative.getResource().getName());
					lcwc.setAttribute("Experiment Automation", alternative.getExperiment().eResource().getURI().toString());
					lcwc.setAttribute("de.uka.ipd.sdq.workflowengine.debuglevel", 2);
					lcwc.setAttribute("outpath", "org.palladiosimulator.temporary");
					lcwc.doSave();
					
					lcwc.launch(ILaunchManager.DEBUG_MODE, m);
					return Status.OK_STATUS;
				} catch (CoreException e1) {
					return new Status(Status.ERROR, "", e1.getLocalizedMessage());
				}
		}
	}
	
	@Override
	public void handleNewInput(IEditorInput selected) {
		NewConfigInputDialog dialog = new NewConfigInputDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
				new BasicCallback<String[]>() {
			
			@Override
			public void handle(String[] data) {
				ResourceProvider resourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_CONF_ID);
				if(resourceProvider == null){
					throw new IllegalStateException("Sidebar resource provider not set!");
				}
				resourceProvider.createNewResource(data[0], data[1]);
			}
		});
		dialog.open();
	}
}
