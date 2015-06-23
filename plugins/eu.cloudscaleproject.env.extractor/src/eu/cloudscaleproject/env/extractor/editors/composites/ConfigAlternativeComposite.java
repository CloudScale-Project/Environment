package eu.cloudscaleproject.env.extractor.editors.composites;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.somox.common.MetricsDetails.GroupID;

import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.extractor.alternatives.ConfingAlternative;
import eu.cloudscaleproject.env.extractor.alternatives.GlobalInputAlternative;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.ui.ConfigEditorView;

public class ConfigAlternativeComposite extends ConfigEditorView implements IRefreshable, ISelectable
{
	private DataBindingContext m_bindingContext;
	private ConfingAlternative configAlternative;
	
	private final PropertyChangeListener projectListener = new PropertyChangeListener()
	{
		@Override
		public void propertyChange(PropertyChangeEvent evt)
		{
			Display.getDefault().asyncExec(new Runnable()
			{
				@Override
				public void run()
				{
					refresh();
				}
			});
		}
	};

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ConfigAlternativeComposite(Composite parent, int style, ConfingAlternative cif)
	{
		super(parent, style, cif);

		this.configAlternative = cif;

		// this.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
		// 1));
		getContainer().setLayout(new GridLayout(2, false));

		{
		Label lblNewLabel = new Label(getContainer(), SWT.NONE);
		lblNewLabel.setText("Input project");

		comboViewer = new ComboViewer(getContainer(), SWT.NONE);
		Combo combo = comboViewer.getCombo();
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		gd_combo.widthHint = 250;
		combo.setLayoutData(gd_combo);
		}

		Composite containerConfiguration = new Composite(getContainer(), SWT.NONE);
		//containerConfiguration.setLayout(new FillLayout(SWT.HORIZONTAL));
		containerConfiguration.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		containerConfiguration.setLayout(new GridLayout(1, false));

		//new SomoxConfigurationComposite(configPersistenceFolder.getSomoxConfiguration(), containerConfiguration, SWT.NONE);

		CTabFolder tabFolder = new CTabFolder(containerConfiguration, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setTabHeight(32);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		{
			SomoxConfigurationComposite composite = new SomoxConfigurationComposite(tabFolder, SWT.NONE, 
					configAlternative, GroupID.GROUP_CLUSTERING, GroupID.GROUP_MERGING);

			CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setControl(composite);
			tabItem.setText("Clustering / Merging");
			tabFolder.setSelection(tabItem);
		}
		{
			SomoxConfigurationComposite composite = new SomoxConfigurationComposite(tabFolder, SWT.NONE, 
					configAlternative, GroupID.GROUP_METRICS);

			CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setControl(composite);
			tabItem.setText("Other metrics");
			tabFolder.setSelection(tabItem);
		}
		{
			ModiscoConfigurationComposite composite = new ModiscoConfigurationComposite(tabFolder, SWT.NONE, configAlternative);
			CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setControl(composite);
			tabItem.setText("Modisco configuration");
			tabFolder.setSelection(tabItem);
		}
		
		tabFolder.setSelection(0);

		m_bindingContext = initDataBindings();
		
		GlobalInputAlternative.getInstance().addPropertyChangeListener("javaProjects",projectListener);
		this.addDisposeListener(new DisposeListener()
		{
			@Override
			public void widgetDisposed(DisposeEvent e)
			{
                GlobalInputAlternative.getInstance().removePropertyChangeListener(projectListener);
			}
		});
	}
	
	private ComboViewer comboViewer;

	@Override
	public void refresh()
	{
		//this.configAlternative.load();
		if (m_bindingContext != null)
			m_bindingContext.dispose();
		m_bindingContext = initDataBindings();
	}
	
	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}

	protected DataBindingContext initDataBindings()
	{
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap observeMap = PojoObservables.observeMap(listContentProvider.getKnownElements(), IProject.class, "name");
		comboViewer.setLabelProvider(new ObservableMapLabelProvider(observeMap));
		comboViewer.setContentProvider(listContentProvider);
		//
		IObservableList selfList = PojoProperties.list("javaProjects").observe(GlobalInputAlternative.getInstance());
		comboViewer.setInput(selfList);
		//
		IObservableValue observeSingleSelectionComboViewer_1 = ViewerProperties.singleSelection().observe(comboViewer);
		IObservableValue projectConfigPersistenceFolderObserveValue = BeanProperties.value("extractedProject").observe(
				configAlternative);
		bindingContext.bindValue(observeSingleSelectionComboViewer_1, projectConfigPersistenceFolderObserveValue, null, null);
		//
		return bindingContext;
	}


	@Override
	public void onSelect() {
		ValidationDiagramService.showStatus(configAlternative.getProject(), GlobalInputAlternative.getInstance());
		ValidationDiagramService.showStatus(configAlternative.getProject(), configAlternative);
		if (configAlternative.getLastResult() != null)
			ValidationDiagramService.showStatus(configAlternative.getProject(), configAlternative.getLastResult());
		else
			ValidationDiagramService.clearStatus(configAlternative.getProject(), ToolchainUtils.EXTRACTOR_RES_ID);
	}
		
}
