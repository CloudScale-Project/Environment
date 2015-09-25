package eu.cloudscaleproject.env.staticspotter.editors.composites;

import javax.inject.Inject;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.staticspotter.alternatives.ConfigAlternative;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.ui.ConfigEditorView;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;
import eu.cloudscaleproject.env.toolchain.util.PropertyPageComposite;

public class ConfigAlternativeComposite extends ConfigEditorView implements IRefreshable, ISelectable
{
	private DataBindingContext m_bindingContext;

	@Optional
	@Inject
	private CommandExecutor executor;

	private Combo combo;
	private ConfigAlternative configAlternative;

	private ComboViewer comboViewer;

	private EMFEditableTreeviewComposite treeViewComposite;

	private WritableList inputAlternatives = new WritableList();

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ConfigAlternativeComposite(Composite parent, int style, final ConfigAlternative configAlternative)
	{
		super(parent, style, configAlternative);
		CloudscaleContext.inject(this);

		this.configAlternative = configAlternative;

		getContainer().setLayout(new GridLayout(3, false));

		Label lblInput = new Label(getContainer(), SWT.NONE);
		lblInput.setText("Input:");

		comboViewer = new ComboViewer(getContainer(), SWT.NONE);
		combo = comboViewer.getCombo();
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_combo.widthHint = 170;
		combo.setLayoutData(gd_combo);

		Group containerEditor = new Group(getContainer(), SWT.NONE);
		containerEditor.setText("Catalog and Engine models");
		containerEditor.setLayout(new FillLayout(SWT.HORIZONTAL));
		containerEditor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

		this.treeViewComposite = new EMFEditableTreeviewComposite(configAlternative, containerEditor, SWT.NONE);
		
		PropertyPageComposite propertyComposite = new PropertyPageComposite(getContainer(), SWT.NONE, this.treeViewComposite.getPropertySheetPage());
		propertyComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		
		m_bindingContext = initDataBindings();
	}

	@Override
	public void refresh()
	{
		//this.configAlternative.load();
		updateExtractorResults();
		m_bindingContext.updateTargets();
	}
	
	private void updateExtractorResults()
	{
		ResourceProvider resourceProvider = ResourceRegistry.getInstance().getResourceProvider(
													configAlternative.getProject(), CSTool.SPOTTER_STA_INPUT);
		
		this.inputAlternatives.clear();
		this.inputAlternatives.addAll(resourceProvider.getResources());
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
		comboViewer.setInput(inputAlternatives);
		//
		IObservableValue observeSingleSelectionComboViewer = ViewerProperties.singleSelection().observe(comboViewer);
		IObservableValue extractorResultConfigPersistenceFolderObserveValue = BeanProperties.value("inputAlternative").observe(configAlternative);
		bindingContext.bindValue(observeSingleSelectionComboViewer, extractorResultConfigPersistenceFolderObserveValue, null, null);
		//
		return bindingContext;
	}

	@Override
	public void onSelect() {
		
		IProject project = this.configAlternative.getProject();
		
		ValidationDiagramService.showStatus(project, CSTool.SPOTTER_STA_INPUT.getID(), configAlternative.getInputAlternative());
		ValidationDiagramService.showStatus(project, CSTool.SPOTTER_STA_CONF.getID(), configAlternative);
		ValidationDiagramService.showStatus(project, CSTool.SPOTTER_STA_RES.getID(), configAlternative.getLastResult());
	}
}
