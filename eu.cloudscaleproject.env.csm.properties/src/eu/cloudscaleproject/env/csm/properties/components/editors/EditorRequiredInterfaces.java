package eu.cloudscaleproject.env.csm.properties.components.editors;

import java.util.Collection;

import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public class EditorRequiredInterfaces extends AbstractEditorListToList<OperationInterface>{

	private final OperationInterfaceContainer opInterfaceContainer;
	
	public EditorRequiredInterfaces(Composite c, OperationInterfaceContainer opInterfaceContainer){
		super(c, opInterfaceContainer, opInterfaceContainer.getRequiredInterfaces());
		this.opInterfaceContainer = opInterfaceContainer;
		
		setName("Edit required interfaces.");
		setSecondaryListName("Candiates:");
	}
	
	@Override
	public Collection<OperationInterface> getSecondaryList() {
		return CsmUtil.collectPotentialyRequiredInterfaces(opInterfaceContainer);
	}
	
	@Override
	public OperationInterface createEntry() {
		return null;
	}

	public void deleteEntry(Object o){
		return;
	}

	@Override
	public boolean canCreateDeleteEntry() {
		return false;
	}
}
