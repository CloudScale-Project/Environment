package org.scaledl.overview.properties.components.editors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.widgets.Composite;
import org.scaledl.overview.Overview;
import org.scaledl.overview.application.ApplicationFactory;
import org.scaledl.overview.application.OperationInterface;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.util.OverviewArchitectureUtil;

public class EditorProvidedInterfaces extends AbstractEditorListToList<OperationInterface>{

	private final OperationInterfaceContainer opInterfaceContainer;
	
	public EditorProvidedInterfaces(Composite c, OperationInterfaceContainer opInterfaceContainer){
		super(c, opInterfaceContainer, opInterfaceContainer.getProvidedInterfaces());
		this.opInterfaceContainer = opInterfaceContainer;
		
		setName("Provided interfaces:");
		setSecondaryListName("Unresolved interfaces:");
	}
	
	@Override
	public EList<OperationInterface> getSecondaryList() {
		EObject eo = EcoreUtil.getRootContainer(opInterfaceContainer);
		if(eo instanceof Overview){
			Overview overview = (Overview)eo;
			return overview.getArchitecture().getUnresolvedOperationInterfaces();
		}
		else{
			throw new UnsupportedOperationException("Operation interface container is not contained in Csm object");
		}
	}

	@Override
	public OperationInterface createEntry() {
		OperationInterface oi = ApplicationFactory.eINSTANCE.createOperationInterface();
		oi.setName("newOperationInterface");
		this.list.add(oi);
		return oi;
	}

	public void deleteEntry(Object o){
		removeEntry(o);
		
		if(o instanceof OperationInterface){
			OperationInterface opInterface = (OperationInterface)o;
			OverviewArchitectureUtil.deleteInterface(opInterface);
		}
	}

	@Override
	public boolean canCreateDeleteEntry() {
		return true;
	}
}
