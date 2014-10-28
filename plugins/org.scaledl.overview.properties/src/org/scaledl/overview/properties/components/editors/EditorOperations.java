package org.scaledl.overview.properties.components.editors;

import org.eclipse.swt.widgets.Composite;
import org.scaledl.overview.application.ApplicationFactory;
import org.scaledl.overview.application.Operation;
import org.scaledl.overview.application.OperationInterface;

public class EditorOperations extends AbstractEditorList<Operation>{
	
	public EditorOperations(Composite c, OperationInterface opInterface){
		super(c, opInterface, opInterface.getOperations());
		setName("Edit operations.");
	}
	@Override
	public Operation createEntry() {
		Operation o = ApplicationFactory.eINSTANCE.createOperation();
		o.setName("noName");
		add(o);
		return o;
	}

	@Override
	public void deleteEntry(Object o){
		removeEntry(o);
	}
	@Override
	public boolean canCreateDeleteEntry() {
		return true;
	}
}
