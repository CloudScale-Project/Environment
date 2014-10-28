package org.scaledl.overview.properties.components.editors;

import java.util.Collection;

import org.eclipse.swt.widgets.Composite;
import org.scaledl.overview.application.OperationInterface;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.util.OverviewArchitectureUtil;

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
		return OverviewArchitectureUtil.collectPotentialyRequiredInterfaces(opInterfaceContainer);
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
