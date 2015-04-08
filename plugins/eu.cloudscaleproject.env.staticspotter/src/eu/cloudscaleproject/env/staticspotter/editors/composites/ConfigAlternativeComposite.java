package eu.cloudscaleproject.env.staticspotter.editors.composites;

import javax.inject.Inject;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.wb.swt.SWTResourceManager;
import org.reclipse.structure.inference.DetectPatternsJob;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.common.ui.IRefreshable;
import eu.cloudscaleproject.env.staticspotter.ConfigPersistenceFolder;
import eu.cloudscaleproject.env.staticspotter.util.Util;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.ui.RunComposite;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;

public class ConfigAlternativeComposite extends RunComposite implements IPropertySheetPageProvider, IRefreshable
{
	private DataBindingContext m_bindingContext;

	@Optional
	@Inject
	private CommandExecutor executor;

	private Combo combo;
	private ConfigPersistenceFolder configPersistenceFolder;

	private ComboViewer comboViewer;

	private EditorInputFolder extractorResult;

	private EMFEditableTreeviewComposite treeViewComposite;

	private WritableList extractorResults = new WritableList();

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ConfigAlternativeComposite(Composite parent, int style, final ConfigPersistenceFolder cif)
	{
		super(parent, style);
		CloudscaleContext.inject(this);

		this.configPersistenceFolder = cif;

		getContainer().setLayout(new GridLayout(3, false));

		Label lblInput = new Label(getContainer(), SWT.NONE);
		lblInput.setFont(SWTResourceManager.getFont("Sans", 11, SWT.NORMAL));
		lblInput.setText("Extractor result:");

		comboViewer = new ComboViewer(getContainer(), SWT.NONE);
		combo = comboViewer.getCombo();
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_combo.widthHint = 170;
		combo.setLayoutData(gd_combo);

		Group containerEditor = new Group(getContainer(), SWT.NONE);
		containerEditor.setText("Catalog and Engine models");
		containerEditor.setLayout(new FillLayout(SWT.HORIZONTAL));
		containerEditor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

		this.treeViewComposite = new EMFEditableTreeviewComposite(configPersistenceFolder, containerEditor, SWT.NONE);

		setTitle(configPersistenceFolder.getName());
		
		comboViewer.addSelectionChangedListener(new ISelectionChangedListener()
		{
			@Override
			public void selectionChanged(SelectionChangedEvent event)
			{
				extractorResult = (EditorInputFolder) ((IStructuredSelection) comboViewer.getSelection()).getFirstElement();
			}
		});
		m_bindingContext = initDataBindings();
	}

	@Override
	protected IStatus doRun(IProgressMonitor m)
	{
		final DetectPatternsJob detectPaternJob = Util.createDetectPaternJob(configPersistenceFolder, extractorResult);
		
		IStatus status = detectPaternJob.run(m);
		if (status.isOK())
		{
			// Collect results
			Util.saveAnnotations(configPersistenceFolder, detectPaternJob);
		}

		return status;
	}

	@Override
	public void refresh()
	{
		this.configPersistenceFolder.load();
		setTitle(configPersistenceFolder.getName());
		updateExtractorResults();
		m_bindingContext.updateTargets();
	}
	
	private void updateExtractorResults()
	{
		ResourceProvider resourceProvider = ResourceRegistry.getInstance().getResourceProvider(configPersistenceFolder.getProject(),
				ToolchainUtils.EXTRACTOR_RES_ID);
		
		this.extractorResults.clear();
		this.extractorResults.addAll(resourceProvider.getResources());
	}

	@Override
	public IPropertySheetPage getPropertySheetPage()
	{
		return treeViewComposite.getPropertySheetPage();
	}

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap observeMap = BeansObservables.observeMap(listContentProvider.getKnownElements(), IEditorInputResource.class, "name");
		comboViewer.setLabelProvider(new ObservableMapLabelProvider(observeMap));
		comboViewer.setContentProvider(listContentProvider);
		//
		comboViewer.setInput(extractorResults);
		//
		IObservableValue observeSingleSelectionComboViewer = ViewerProperties.singleSelection().observe(comboViewer);
		IObservableValue extractorResultConfigPersistenceFolderObserveValue = BeanProperties.value("extractorResult").observe(configPersistenceFolder);
		bindingContext.bindValue(observeSingleSelectionComboViewer, extractorResultConfigPersistenceFolderObserveValue, null, null);
		//
		return bindingContext;
	}
}
