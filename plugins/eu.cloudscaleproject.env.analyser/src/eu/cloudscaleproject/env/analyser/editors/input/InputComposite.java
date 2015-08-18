package eu.cloudscaleproject.env.analyser.editors.input;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ProjectEditorSelectionService;
import eu.cloudscaleproject.env.toolchain.ui.InputEditorView;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class InputComposite extends InputEditorView implements ISelectable, IRefreshable, IPropertySheetPageProvider{
	
	private final InputTreeViewComposite treeviewComposite;
	private final UsageEvolutionComposite ueComposite;
	
	private final InputAlternative alternative;
	
	private CTabFolder tabFolder;

	public InputComposite(InputAlternative input, Composite parent, int style) {
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
			
			Composite composite = new Composite(tabFolder, SWT.NONE);
			composite.setLayout(new GridLayout(1, false));
			
			treeviewComposite = new InputTreeViewComposite(input, composite, SWT.NONE);
			treeviewComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

			Composite pageSheet = new Composite(composite, SWT.NONE);
			pageSheet.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			pageSheet.setLayout(new FillLayout());
			
			final PropertySheetPage page = (PropertySheetPage)treeviewComposite.getPropertySheetPage();
			page.createControl(pageSheet);
			
			ProjectEditorSelectionService.getInstance().addPostSelectionChangedListener(new ISelectionChangedListener() {
				
				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					page.selectionChanged(null, event.getSelection());					
				}
			});
			
			tabItem.setControl(composite);			
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
		/* This is not needed anymore... PropSheetpage is integrated into this composite 
		if(treeviewComposite != null && !treeviewComposite.isDisposed()){
			return treeviewComposite.getPropertySheetPage();
		}
		*/
		return null;
	}
	
	@Override
	public void onSelect() {
		IProject project = alternative.getProject();
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