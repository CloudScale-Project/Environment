package eu.cloudscaleproject.env.csm.properties.components.editors;

import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.csm.core.Operation;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.core.impl.CoreFactoryImpl;

public class EditorOperations extends AbstractEditorList<Operation>{
	
	public EditorOperations(Composite c, OperationInterface opInterface){
		super(c, opInterface, opInterface.getOperations());
		setName("Edit operations.");
	}
	@Override
	public Operation createEntry() {
		Operation o = CoreFactoryImpl.eINSTANCE.createOperation();
		o.setMethodName("noName");
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
