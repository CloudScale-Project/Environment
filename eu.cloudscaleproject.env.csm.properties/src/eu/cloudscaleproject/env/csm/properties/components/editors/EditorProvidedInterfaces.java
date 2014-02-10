package eu.cloudscaleproject.env.csm.properties.components.editors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.core.impl.CoreFactoryImpl;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

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
		if(eo instanceof Csm){
			Csm csm = (Csm)eo;
			return csm.getArchitecture().getUnresolvedOperationInterfaces();
		}
		else{
			throw new UnsupportedOperationException("Operation interface container is not contained in Csm object");
		}
	}

	@Override
	public OperationInterface createEntry() {
		OperationInterface oi = CoreFactoryImpl.eINSTANCE.createOperationInterface();
		oi.setName("newOperationInterface");
		this.list.add(oi);
		return oi;
	}

	public void deleteEntry(Object o){
		removeEntry(o);
		
		if(o instanceof OperationInterface){
			OperationInterface opInterface = (OperationInterface)o;
			CsmUtil.deleteInterface(opInterface);
		}
	}

	@Override
	public boolean canCreateDeleteEntry() {
		return true;
	}
}
