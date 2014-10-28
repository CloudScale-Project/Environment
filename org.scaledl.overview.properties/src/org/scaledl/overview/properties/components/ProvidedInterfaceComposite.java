package org.scaledl.overview.properties.components;

import java.util.Set;

import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IMapChangeListener;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.map.MapChangeEvent;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.IEMFListProperty;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.databinding.viewers.TreeStructureAdvisor;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.scaledl.overview.application.ApplicationPackage;
import org.scaledl.overview.application.Operation;
import org.scaledl.overview.application.OperationInterface;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.core.CorePackage;
import org.scaledl.overview.diagram.SWTResourceManager;
import org.scaledl.overview.diagram.Util;
import org.scaledl.overview.diagram.editor.interfaces.OICEditor;
import org.scaledl.overview.diagram.editor.interfaces.OICEditorInput;
import org.scaledl.overview.parametertype.Parameter;
import org.scaledl.overview.parametertype.ParametertypePackage;

import eu.cloudscaleproject.env.common.CommonResources;

public class ProvidedInterfaceComposite extends Composite {
	
	private TreeViewer viewer;
	private OperationInterfaceContainer opInterfaceContainer;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ProvidedInterfaceComposite(Composite parent) {
		super(parent, SWT.NONE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FormLayout());
		setBounds(10, 10, 350, 250);

		viewer = new TreeViewer(this, SWT.BORDER);
		viewer.getTree().setLinesVisible(true);
		
		FormData fd_table = new FormData();
		fd_table.top = new FormAttachment(0, 10);
		fd_table.left = new FormAttachment(0, 10);
		fd_table.right = new FormAttachment(100, -49);
		fd_table.bottom = new FormAttachment(100, -10);
		viewer.getTree().setLayoutData(fd_table);
		
		ObservableListTreeContentProvider cp = new ObservableListTreeContentProvider(
				new TreeFactoryImpl(), new TreeStructureAdvisorImpl());
		viewer.setContentProvider(cp);

		IObservableSet set = cp.getKnownElements();
		IObservableMap[] map = new IObservableMap[6];

		map[0] = EMFProperties.value(CorePackage.Literals.ENTITY__NAME)
				.observeDetail(set);
		map[1] = EMFProperties.value(ApplicationPackage.Literals.OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES)
				.observeDetail(set);
		map[2] = EMFProperties.value(ApplicationPackage.Literals.OPERATION_INTERFACE__OPERATIONS)
				.observeDetail(set);
		map[3] = EMFProperties.value(ApplicationPackage.Literals.OPERATION__PARAMETERS)
				.observeDetail(set);
		map[4] = EMFProperties.value(ApplicationPackage.Literals.OPERATION__RETURN_PARAMETER)
				.observeDetail(set);
		map[5] = EMFProperties.value(ParametertypePackage.Literals.COMPOSITE_PARAMETER__PARAMETERS)
				.observeDetail(set);

		viewer.setLabelProvider(new TreeLabelProviderImpl(map));
		
		Button btnEdit = new Button(this, SWT.NONE);
		btnEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(opInterfaceContainer != null){
					Util.openEditor(new OICEditorInput(opInterfaceContainer), OICEditor.ID);
				}
			}
		});
		btnEdit.setText("Edit");
		FormData fd_btnEdit = new FormData();
		fd_btnEdit.right = new FormAttachment(viewer.getTree(), 45, SWT.RIGHT);
		fd_btnEdit.top = new FormAttachment(0, 10);
		fd_btnEdit.left = new FormAttachment(viewer.getTree(), 6);
		btnEdit.setLayoutData(fd_btnEdit);
	}
	
	public void setInput(OperationInterfaceContainer oic){
		this.opInterfaceContainer = oic;
		
		IEMFListProperty providedInterfacesProp = 
				EMFProperties.list(ApplicationPackage.Literals.OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES);
		viewer.setInput(providedInterfacesProp.observe(oic));
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	/*
	 * 
	 * CONTENT PROVIDER
	 * 
	 */
	
	private static class TreeFactoryImpl implements IObservableFactory {
		
		private IEMFListProperty opInterfaceContainerProp = EMFProperties.multiList(
				ApplicationPackage.Literals.OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES
				);
		
		private IEMFListProperty opInterfaceProp = EMFProperties.multiList(
				ApplicationPackage.Literals.OPERATION_INTERFACE__OPERATIONS
				);

		public IObservable createObservable(final Object target) {
			if (target instanceof IObservableList) {
				return (IObservable) target;
			} else if (target instanceof OperationInterfaceContainer) {
				return opInterfaceContainerProp.observe(target);
			} else if (target instanceof OperationInterface) {
				return opInterfaceProp.observe(target);
			}

			return null;
		}
	}

	private static class TreeStructureAdvisorImpl extends TreeStructureAdvisor {
		@Override
		public Object getParent(Object element) {
			if (element instanceof Operation) {
				return ((Operation) element).eContainer();
			}

			return null;
		}

		@Override
		public Boolean hasChildren(Object element) {
			if (element instanceof OperationInterfaceContainer
					&& (((OperationInterfaceContainer) element).getProvidedInterfaces().size() > 0)) {
				return Boolean.TRUE;
			} 
			else if (element instanceof OperationInterface
					&& (((OperationInterface) element).getOperations().size() > 0)) {
				return Boolean.TRUE;
			}
			
			return super.hasChildren(element);
		}
	}
	
	/*
	 * 
	 * LABEL PROVIDER
	 * 
	 */

	private class TreeLabelProviderImpl extends StyledCellLabelProvider {
		
		private IMapChangeListener mapChangeListener = new IMapChangeListener() {
			public void handleMapChange(MapChangeEvent event) {
				Set<?> affectedElements = event.diff.getChangedKeys();
				if (!affectedElements.isEmpty()) {
					LabelProviderChangedEvent newEvent = new LabelProviderChangedEvent(
							TreeLabelProviderImpl.this,
							affectedElements.toArray());
					fireLabelProviderChanged(newEvent);
				}
			}
		};

		public TreeLabelProviderImpl(IObservableMap... attributeMaps) {
			for (int i = 0; i < attributeMaps.length; i++) {
				attributeMaps[i].addMapChangeListener(mapChangeListener);
			}
		}

		@Override
		public String getToolTipText(Object element) {
			return "#dummy#";
		}

		@Override
		public void update(ViewerCell cell) {
			if (cell.getElement() instanceof OperationInterface) {
				OperationInterface oi = (OperationInterface) cell.getElement();

				StyledString styledString = new StyledString(
						oi.getName() != null ? oi.getName() : "*noname*", null
						);
				
				String decoration = " (" + oi.getOperations().size()
						+ " Operations)";
				styledString.append(decoration, StyledString.COUNTER_STYLER);
				cell.setText(styledString.getString());
				cell.setImage(CommonResources.INTERFACE);
				cell.setStyleRanges(styledString.getStyleRanges());
				
			} else if (cell.getElement() instanceof Operation) {
				Operation o = ((Operation) cell.getElement());
				String returnParam = o.getReturnParameter() != null ? o.getReturnParameter().getName() : "void";
				String arguments = "";
				
				for(Parameter p : o.getParameters()){
					if(p != null){
						arguments += p.getName() + ", ";
					}
				}
				arguments = arguments.isEmpty() ? arguments : arguments.substring(0, arguments.length()-2); 				
				String value = returnParam + " " + o.getName() + "("+arguments+")";
				
				StyledString styledString = new StyledString(value, null);
				cell.setText(styledString.getString());
				cell.setForeground(cell.getControl().getDisplay()
						.getSystemColor(SWT.COLOR_DARK_GRAY));
				cell.setImage(CommonResources.OPERATION);
				cell.setStyleRanges(styledString.getStyleRanges());
			}
		}
	}
}
