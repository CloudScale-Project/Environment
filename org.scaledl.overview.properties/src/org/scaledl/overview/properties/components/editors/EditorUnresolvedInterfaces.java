package org.scaledl.overview.properties.components.editors;

import org.eclipse.swt.widgets.Composite;
import org.scaledl.overview.application.ApplicationFactory;
import org.scaledl.overview.application.OperationInterface;
import org.scaledl.overview.architecture.Architecture;
import org.scaledl.overview.util.OverviewArchitectureUtil;

public class EditorUnresolvedInterfaces extends AbstractEditorList<OperationInterface>{

	private final Architecture archi;
	
	public EditorUnresolvedInterfaces(Composite c, Architecture archi) {
		super(c, archi, archi.getUnresolvedOperationInterfaces());
		this.archi = archi;
		
		setName("Unresolved interfaces editor.");
	}
	
	@Override
	public OperationInterface createEntry() {
		OperationInterface newInterface = ApplicationFactory.eINSTANCE.createOperationInterface();
		newInterface.setName("noName");
		archi.getUnresolvedOperationInterfaces().add(newInterface);
		add(newInterface);
		return newInterface;
	}
	
	@Override
	public void deleteEntry(Object o) {
		removeEntry(o);
		
		if(o instanceof OperationInterface){
			OverviewArchitectureUtil.deleteInterface((OperationInterface)o);
		}
	}

	@Override
	public boolean canCreateDeleteEntry() {
		return true;
	}
}
