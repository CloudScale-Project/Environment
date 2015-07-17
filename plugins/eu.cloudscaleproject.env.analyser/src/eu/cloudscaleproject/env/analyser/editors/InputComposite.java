package eu.cloudscaleproject.env.analyser.editors;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.WizardDialog;
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
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.analyser.editors.input.InputTreeViewComposite;
import eu.cloudscaleproject.env.analyser.editors.input.UsageEvolutionComposite;
import eu.cloudscaleproject.env.analyser.wizard.CreateInputSelectionWizard;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ProjectEditorSelectionService;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.ui.InputEditorView;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class InputComposite extends SidebarEditorComposite{
	
	private static final String SECTION_ALT = "Alternative inputs:";
	
	private final IProject project;
		
	public InputComposite(final IEditorPart editor, Composite parent, int style) {
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
				return new RightPanelComposite(editor, (InputAlternative)resource, parent, SWT.NONE);
			}
		});
	}
	
	private class RightPanelComposite extends InputEditorView implements ISelectable, IRefreshable, IPropertySheetPageProvider{
		
		private final InputTreeViewComposite treeviewComposite;
		private final UsageEvolutionComposite ueComposite;
		
		private final InputAlternative alternative;
		
		private CTabFolder tabFolder;

		public RightPanelComposite(IEditorPart editor, InputAlternative input, Composite parent, int style) {
			super(parent, style, input);

			alternative = input;
			
			GridLayout layout = new GridLayout(1, true);
			layout.marginWidth = 0;
			layout.marginHeight = 0;
			getContainer().setLayout(layout);
			
			tabFolder = new CTabFolder(getContainer(), SWT.BORDER);
			GridData tabFolder_gd = new GridData(SWT.FILL, SWT.FILL, true, true);
			tabFolder.setLayoutData(tabFolder_gd);
			tabFolder.setTabHeight(32);
			
			//basic
			{
				CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
				tabItem.setText("Model editor");
				treeviewComposite = new InputTreeViewComposite(editor, input, tabFolder, SWT.NONE);
				tabItem.setControl(treeviewComposite);
				tabFolder.setSelection(tabItem);
			}
			
			//usage evolution
			{
				CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
				tabItem.setText("Usage evolution");

				ueComposite = new UsageEvolutionComposite(input, tabFolder, style);
				ueComposite.pack();
				tabItem.setControl(ueComposite);
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
		public IPropertySheetPage getPropertySheetPage() {
			if(treeviewComposite != null && !treeviewComposite.isDisposed()){
				return treeviewComposite.getPropertySheetPage();
			}
			return null;
		}
		
		@Override
		public void onSelect() {
			ValidationDiagramService.showStatus(project, CSTool.ANALYSER_INPUT.getID(), alternative);
			ValidationDiagramService.showStatus(project, CSTool.ANALYSER_CONF.getID(), null);
			ValidationDiagramService.showStatus(project, CSTool.ANALYSER_RES.getID(), null);
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
		}
	}

	@Override
	public void handleNewInput(IEditorInput selected) {
		
		CreateInputSelectionWizard createInputAltWizard = new CreateInputSelectionWizard(project);
		WizardDialog wizardDialog = new WizardDialog(this.getShell(), createInputAltWizard);
		wizardDialog.open();
	}
}
