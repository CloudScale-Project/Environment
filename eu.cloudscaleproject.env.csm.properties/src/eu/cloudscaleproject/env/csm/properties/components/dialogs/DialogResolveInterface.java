package eu.cloudscaleproject.env.csm.properties.components.dialogs;

import java.util.ArrayList;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.property.Properties;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.util.CsmUtil;
import org.eclipse.swt.widgets.Button;

public class DialogResolveInterface extends Dialog {
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	private Table table;
	private OperationInterfaceContainer opInterfaceContainer;
	private TableViewer tableViewer;

	private ArrayList<OperationInterfaceContainer> possibleContainers;
	private OperationInterface opInterface;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public DialogResolveInterface(Shell parentShell, OperationInterfaceContainer opInterfaceContainer, OperationInterface opInterface) {
		super(parentShell);
		
		
		
		this.opInterfaceContainer = opInterfaceContainer;
		this.opInterface = opInterface;
		
		this.possibleContainers = new ArrayList<OperationInterfaceContainer>();
		this.possibleContainers.addAll(CsmUtil.collectPotentialyResolveConteiners(opInterfaceContainer));
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("Resolve interface dialog");
	}
	

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		tableViewer = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnPossibleProvider = tableViewerColumn.getColumn();
		tblclmnPossibleProvider.setWidth(200);
		tblclmnPossibleProvider.setText("Possible provider");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnDescription = tableViewerColumn_1.getColumn();
		tblclmnDescription.setWidth(444);
		tblclmnDescription.setText("Description");

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		button.setText("Resolve");
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		m_bindingContext = initDataBindings();
	}
	
	@Override
	protected void okPressed() {
		
		IStructuredSelection selection = (IStructuredSelection)tableViewer.getSelection();
		final OperationInterfaceContainer container = (OperationInterfaceContainer)selection.getFirstElement();
		
		if (container != null)
		{
			TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(opInterfaceContainer.eResource().getResourceSet());
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					if (container.getProvidedInterfaces().size() ==1)
					{
						if (container.getProvidedInterfaces().get(0).getOperations().isEmpty())
							container.getProvidedInterfaces().clear();
					}
					container.getProvidedInterfaces().add(opInterface);
				}
			});
		}
		super.okPressed();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(688, 469);
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), OperationInterfaceContainer.class, new String[]{"name", "description"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList selfList = Properties.selfList(OperationInterfaceContainer.class).observe(possibleContainers);
		tableViewer.setInput(selfList);
		//
		return bindingContext;
	}
}
