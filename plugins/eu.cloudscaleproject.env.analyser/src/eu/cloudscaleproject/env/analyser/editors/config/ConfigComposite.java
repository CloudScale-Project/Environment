package eu.cloudscaleproject.env.analyser.editors.config;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPointRepository;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ProjectEditorSelectionService;
import eu.cloudscaleproject.env.toolchain.ui.ConfigEditorView;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ConfigComposite extends ConfigEditorView implements IPropertySheetPageProvider, IRefreshable, ISelectable{

	private SelectInputAltComposite editComposite;
	private Composite configComposite;
	
	private EMFEditableTreeviewComposite measuringPointsComposite;
	private ConfigMonitorListComposite monitorsComposite;
	private ConfigSLOListComposite sloComposite;

	private EMFEditableTreeviewComposite advancedTreeview;

	private final IProject project;
	private final ConfAlternative alternative;

	private CTabFolder tabFolder;

	public ConfigComposite(final ConfAlternative input, Composite parent, int style){
		super(parent, style, input);

		this.project = input.getProject();
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
			
			Composite composite = new Composite(tabFolder, SWT.NONE);
			composite.setLayout(new GridLayout(1, false));
			
			measuringPointsComposite = new EMFEditableTreeviewComposite(input, mpRep, null, composite, style);
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
			gridData.minimumHeight = 260;
			gridData.heightHint = 300;
			measuringPointsComposite.setLayoutData(gridData);

			Composite pageSheet = new Composite(composite, SWT.NONE);
			pageSheet.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			pageSheet.setLayout(new FillLayout());
			
			final PropertySheetPage page = (PropertySheetPage)measuringPointsComposite.getPropertySheetPage();
			page.createControl(pageSheet);
			
			ProjectEditorSelectionService.getInstance().addPostSelectionChangedListener(new ISelectionChangedListener() {
				
				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					page.selectionChanged(null, event.getSelection());					
				}
			});
			
			tabItem.setControl(composite);			
		}

		// measurements settings
		// TODO: following composite creation is brutally slow! Fix this.
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
		
		// advance settings
		{
			CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setText("Advanced editor");
			
			Composite composite = new Composite(tabFolder, SWT.NONE);
			composite.setLayout(new GridLayout(1, false));
			
			advancedTreeview = new EMFEditableTreeviewComposite(input, composite, SWT.NONE);
			advancedTreeview.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

			Composite pageSheet = new Composite(composite, SWT.NONE);
			pageSheet.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			pageSheet.setLayout(new FillLayout());
			
			final PropertySheetPage page = (PropertySheetPage)advancedTreeview.getPropertySheetPage();
			page.createControl(pageSheet);
			
			ProjectEditorSelectionService.getInstance().addPostSelectionChangedListener(new ISelectionChangedListener() {
				
				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					page.selectionChanged(null, event.getSelection());					
				}
			});
			
			tabItem.setControl(composite);
		}

		tabFolder.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				final Control c = tabFolder.getSelection().getControl();

				BusyIndicator.showWhile(Display.getDefault(), new Runnable() {
					
					@Override
					public void run() {
						if (c instanceof ISelectable)
						{
							((ISelectable) c).onSelect();
						}
						if (c instanceof IRefreshable)
						{
							((IRefreshable) c).refresh();
						}
					}
				});

				ProjectEditorSelectionService.getInstance().reloadPropertySheetPage();
			}
		});
	}
	
	@Override
	public void onSelect() {
		ValidationDiagramService.showStatus(project, CSTool.ANALYSER_INPUT.getID(), alternative.getInputAlternative());
		ValidationDiagramService.showStatus(project, CSTool.ANALYSER_CONF.getID(), alternative);
		ValidationDiagramService.showStatus(project, CSTool.ANALYSER_RES.getID(), alternative.getLastResult());
	}
	
	@Override
	public void refresh() {
		
		// TODO: Unload proxy resource method is to slow! For now it is commented out.
		// Possible solution is to hide proxy resource in treeview editors.
		//
		//alternative.unloadProxyResources();
		 
		
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
		/* This is not needed anymore... PropSheetpage is integrated into this composite 
		Control c = tabFolder.getSelection().getControl();
		if (c instanceof EMFEditableTreeviewComposite)
		{
			return ((EMFEditableTreeviewComposite) c).getPropertySheetPage();
		}
		*/
		
		return null;
	}
}