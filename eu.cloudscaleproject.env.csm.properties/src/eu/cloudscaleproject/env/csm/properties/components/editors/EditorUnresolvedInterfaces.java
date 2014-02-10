package eu.cloudscaleproject.env.csm.properties.components.editors;

import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.csm.architecture.Architecture;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.core.impl.CoreFactoryImpl;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public class EditorUnresolvedInterfaces extends AbstractEditorList<OperationInterface>{

	private final Architecture archi;
	
	public EditorUnresolvedInterfaces(Composite c, Architecture archi) {
		super(c, archi, archi.getUnresolvedOperationInterfaces());
		this.archi = archi;
		
		setName("Unresolved interfaces editor.");
	}
	
	@Override
	public OperationInterface createEntry() {
		OperationInterface newInterface = CoreFactoryImpl.eINSTANCE.createOperationInterface();
		newInterface.setName("noName");
		archi.getUnresolvedOperationInterfaces().add(newInterface);
		add(newInterface);
		return newInterface;
	}
	
	@Override
	public void deleteEntry(Object o) {
		removeEntry(o);
		
		if(o instanceof OperationInterface){
			CsmUtil.deleteInterface((OperationInterface)o);
		}
	}

	@Override
	public boolean canCreateDeleteEntry() {
		return true;
	}
}
