package eu.cloudscaleproject.env.analyser.editors.config;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPointRepository;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.ui.SplitComposite;
import eu.cloudscaleproject.env.toolchain.ProjectEditorSelectionService;
import eu.cloudscaleproject.env.toolchain.ui.ConfigEditorView;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;
import eu.cloudscaleproject.env.toolchain.util.PropertyPageComposite;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ConfigComposite extends ConfigEditorView implements IRefreshable, ISelectable{

	private Composite configComposite;
	
	private EMFEditableTreeviewComposite measuringPointsComposite;
	private ConfigMonitorListComposite monitorsComposite;
	private ConfigSLOListComposite sloComposite;

	private EMFEditableTreeviewComposite advancedTreeview;

	private final ConfAlternative alternative;

	private CTabFolder tabFolder;

	public ConfigComposite(final ConfAlternative input, Composite parent, int style){
		super(parent, style, input);

		this.alternative = input;

		GridLayout layout = new GridLayout(1, true);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		getContainer().setLayout(layout);

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
			
			SplitComposite splitComposite = new SplitComposite(tabFolder, SWT.NONE);
			
			//top
			measuringPointsComposite = new EMFEditableTreeviewComposite(input, mpRep, null, splitComposite, style);
			splitComposite.setTopControl(measuringPointsComposite);
			
			//bottom
			PropertyPageComposite pageSheet = new PropertyPageComposite(
						splitComposite, SWT.BORDER, measuringPointsComposite.getPropertySheetPage());
			splitComposite.setBottomControl(pageSheet);
			
			tabItem.setControl(splitComposite);			
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
			
			SplitComposite splitComposite = new SplitComposite(tabFolder, SWT.NONE);
			
			//top
			advancedTreeview = new EMFEditableTreeviewComposite(input, splitComposite, SWT.NONE);
			splitComposite.setTopControl(advancedTreeview);

			//bottom
			PropertyPageComposite pageSheet = new PropertyPageComposite(
					splitComposite, SWT.BORDER, advancedTreeview.getPropertySheetPage());
			splitComposite.setBottomControl(pageSheet);
			
			tabItem.setControl(splitComposite);
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
		//ValidationDiagramHelper.showStatus(project, alternative);
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
}