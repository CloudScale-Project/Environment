package eu.cloudscaleproject.env.overview.wizard.pages;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.scaledl.overview.application.ApplicationPackage;
import org.scaledl.overview.application.OperationInterface;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.architecture.UsageProxy;
import org.scaledl.overview.core.CorePackage.Literals;

import eu.cloudscaleproject.env.overview.wizard.util.IWizardPageControll;
import eu.cloudscaleproject.env.overview.wizard.util.WizardData;

public class ExposedInterfacesWizardPage extends WizardPage implements IWizardPageControll {
	private WizardData data;
	private Table table;

	private SoftwareService softwareService;
	private CheckboxTableViewer checkboxTableViewer;

	private Label lblStatus;

	/**
	 * Create the wizard.
	 */
	public ExposedInterfacesWizardPage(WizardData data) {
		super("wizardPage");
		
		setTitle("Exposed operation interfaces");
		setDescription("Select provided operation interfaces that are exposed to users through UsageProxy.");

		this.data = data;
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(2, false));
		
		Label lblProvidedOpertationInterfaces = new Label(container, SWT.NONE);
		lblProvidedOpertationInterfaces.setText("Provided opertation interfaces");
		new Label(container, SWT.NONE);
		
		checkboxTableViewer = CheckboxTableViewer.newCheckList(container, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table = checkboxTableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		Button btnSelectAll = new Button(composite, SWT.NONE);
		btnSelectAll.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		btnSelectAll.setText("Select All");
		
		Button btnDeselectAll = new Button(composite, SWT.NONE);
		btnDeselectAll.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnDeselectAll.setText("Deselect All");
		
		Button btnSelect = new Button(composite, SWT.NONE);
		btnSelect.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnSelect.setText("Select");
		
		Button btnDeselect = new Button(composite, SWT.NONE);
		btnDeselect.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnDeselect.setText("Deselect");
		
		lblStatus = new Label(container, SWT.NONE);
		lblStatus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblStatus.setText("2 of 13 selected");
		new Label(container, SWT.NONE);

		btnSelectAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkboxTableViewer.setAllChecked(true);
				updateCheckText();
			}
		});

		btnDeselectAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkboxTableViewer.setAllChecked(false);
				updateCheckText();
			}
		});

		btnSelect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkboxTableViewer.setCheckedElements(((IStructuredSelection)checkboxTableViewer.getSelection()).toArray());
				updateCheckText();
			}
		});

		btnDeselect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for (Object o : ((IStructuredSelection)checkboxTableViewer.getSelection()).toArray())
				{
					checkboxTableViewer.setChecked(o, false);
				}
				updateCheckText();
			}
		});
		
		checkboxTableViewer.addCheckStateListener(new ICheckStateListener()
		{
			@Override
			public void checkStateChanged(CheckStateChangedEvent event)
			{
				updateCheckText();
			}
		}
		);
		
	}
	
	private void updateCheckText ()
	{
		int all = checkboxTableViewer.getTable().getItemCount();
		int checked = checkboxTableViewer.getCheckedElements().length;
		lblStatus.setText(String.format("%s of %s selected", checked, all));
	}

	@Override
	public void performNext() {
		UsageProxy usageProxy = (UsageProxy) this.data.getOverviewModel().getArchitecture().getProxies().get(0);

		usageProxy.getRequiredInterfaces().clear();
		
		for (Object o : checkboxTableViewer.getCheckedElements())
		{
			usageProxy.getRequiredInterfaces().add((OperationInterface) o);
		}
	}
	
	@Override
	public void performBack() {
		
	}

	@Override
	public void performUpdate() {
		if (softwareService == this.data.getSoftwareService()) return;

		this.softwareService = this.data.getSoftwareService();
		initDataBindings();
		updateCheckText();
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = EMFObservables.observeMaps(listContentProvider.getKnownElements(), new EStructuralFeature[]{Literals.ENTITY__NAME});
		checkboxTableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		checkboxTableViewer.setContentProvider(listContentProvider);
		//
		IObservableList softwareServiceProvidedInterfacesObserveList = EMFObservables.observeList(Realm.getDefault(), softwareService, ApplicationPackage.Literals.OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES);
		checkboxTableViewer.setInput(softwareServiceProvidedInterfacesObserveList);
		//
		return bindingContext;
	}
}
