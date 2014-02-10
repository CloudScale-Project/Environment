package eu.cloudscaleproject.env.csm.properties.components.tree.editors;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import eu.cloudscaleproject.env.csm.architecture.Architecture;
import eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer;
import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;
import eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.properties.components.editors.EditorInfrastructureLayer;
import eu.cloudscaleproject.env.csm.properties.components.editors.EditorProvidedInterfaces;
import eu.cloudscaleproject.env.csm.properties.components.editors.EditorRequiredInterfaces;
import eu.cloudscaleproject.env.csm.properties.components.editors.EditorSoftwareServices;
import eu.cloudscaleproject.env.csm.properties.components.editors.EditorUnresolvedInterfaces;
import eu.cloudscaleproject.env.csm.properties.components.editors.EditorOperations;
import eu.cloudscaleproject.env.csm.properties.components.editors.IEditor;
import eu.cloudscaleproject.env.csm.properties.components.editors.IListEditor;
import eu.cloudscaleproject.env.csm.properties.components.tree.TreeCell;

public class CellEditorList extends AbstractDialogCellEditor implements IEditor{

	private final IListEditor<?> editor;
	
	public CellEditorList(Composite parent, TreeCell cell, IListEditor<?> editor) {
		super(parent, cell);
		this.editor = editor;
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		editor.openDialog();
		return null;
	}
	public static CellEditorList createISEditor(Composite c, TreeCell cell, InfrastructureLayer il){
		return new CellEditorList(c, cell, new EditorInfrastructureLayer(c, il));
	}
	public static CellEditorList createPIEditor(Composite c, TreeCell cell, OperationInterfaceContainer oic){
		return new CellEditorList(c, cell, new EditorProvidedInterfaces(c, oic));
	}
	public static CellEditorList createRIEditor(Composite c, TreeCell cell, OperationInterfaceContainer oic){
		return new CellEditorList(c, cell, new EditorRequiredInterfaces(c, oic));
	}
	public static CellEditorList createSSEditor(Composite c, TreeCell cell, SoftwareServiceContainer ssc){
		return new CellEditorList(c, cell, new EditorSoftwareServices(c, ssc));
	}
	public static CellEditorList createUIEditor(Composite c, TreeCell cell, Architecture arch){
		return new CellEditorList(c, cell, new EditorUnresolvedInterfaces(c, arch));
	}
	public static CellEditorList createOpEditor(Composite c, TreeCell cell, OperationInterface oi){
		return new CellEditorList(c, cell, new EditorOperations(c, oi));
	}

	@Override
	public boolean canEdit() {
		return editor.canEdit();
	}
}
