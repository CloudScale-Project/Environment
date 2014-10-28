package org.scaledl.overview.diagram.editor.basic;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.scaledl.overview.core.CorePackage;
import org.scaledl.overview.core.Entity;
import org.scaledl.overview.diagram.SWTResourceManager;


public class NameComposite extends Composite {
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	private Text txtName;
	private Text txtDescription;
	private Entity entity;
	private TransactionalEditingDomain editingDomain;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public NameComposite(Composite parent, Entity entity) {
		super(parent, SWT.NONE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		this.entity = entity;
		
		setLayout(new FormLayout());
		
		Label lblName = new Label(this, SWT.NONE);
		lblName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblName = new FormData();
		fd_lblName.bottom = new FormAttachment(0, 27);
		fd_lblName.right = new FormAttachment(0, 80);
		fd_lblName.top = new FormAttachment(0, 10);
		fd_lblName.left = new FormAttachment(0, 10);
		lblName.setLayoutData(fd_lblName);
		lblName.setText("Name");
		
		Label lblDescription = new Label(this, SWT.NONE);
		lblDescription.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblDescription = new FormData();
		fd_lblDescription.right = new FormAttachment(0, 93);
		fd_lblDescription.top = new FormAttachment(0, 43);
		fd_lblDescription.left = new FormAttachment(0, 10);
		lblDescription.setLayoutData(fd_lblDescription);
		lblDescription.setText("Description");
		
		txtName = new Text(this, SWT.BORDER);
		
		FormData fd_txtName = new FormData();
		fd_txtName.top = new FormAttachment(0, 10);
		fd_txtName.left = new FormAttachment(lblName, 19);
		fd_txtName.right = new FormAttachment(100, -10);
		txtName.setLayoutData(fd_txtName);
		
		txtDescription = new Text(this, SWT.BORDER | SWT.MULTI);
		fd_txtName.bottom = new FormAttachment(txtDescription, -8);
		FormData fd_txtDescription = new FormData();
		fd_txtDescription.bottom = new FormAttachment(100, -10);
		fd_txtDescription.right = new FormAttachment(100, -10);
		fd_txtDescription.top = new FormAttachment(lblDescription, 0, SWT.TOP);
		fd_txtDescription.left = new FormAttachment(lblDescription, 6);
		txtDescription.setLayoutData(fd_txtDescription);
		
		this.editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(entity.eResource().getResourceSet());
		m_bindingContext = initDataBindings();
	}
	

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTxtDescriptionObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDescription);
		IObservableValue entityDescriptionObserveValue = EMFEditObservables.observeValue(editingDomain, entity, CorePackage.Literals.ENTITY__DESCRIPTION);
		bindingContext.bindValue(observeTextTxtDescriptionObserveWidget, entityDescriptionObserveValue, null, null);
		//
		IObservableValue observeTextTxtNameObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtName);
		IObservableValue entityNameObserveValue = EMFEditObservables.observeValue(editingDomain, entity, CorePackage.Literals.ENTITY__NAME);
		bindingContext.bindValue(observeTextTxtNameObserveWidget, entityNameObserveValue, null, null);
		//
		return bindingContext;
	}
}
