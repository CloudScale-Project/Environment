package org.scaledl.overview.properties.components.tree.factory;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Tree;
import org.scaledl.overview.application.ApplicationPackage;
import org.scaledl.overview.application.Operation;
import org.scaledl.overview.application.OperationInterface;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.core.CorePackage;
import org.scaledl.overview.core.Entity;
import org.scaledl.overview.properties.ImageResource;
import org.scaledl.overview.properties.components.editors.EditorOperations;
import org.scaledl.overview.properties.components.tree.TreeCell;
import org.scaledl.overview.properties.components.tree.TreeRow;
import org.scaledl.overview.properties.components.tree.editors.CellEditorDropdown;
import org.scaledl.overview.properties.components.tree.editors.CellEditorOperation;
import org.scaledl.overview.properties.components.tree.editors.CellEditorString;
import org.scaledl.overview.util.OverviewArchitectureUtil;

public class TreeRowFactory {
	public final Tree treeComposite;
	public final TreeViewer treeViewer;
	
	public TreeRowFactory(TreeViewer treeViewer) {
		this.treeViewer = treeViewer;
		this.treeComposite = treeViewer.getTree();
	}
	
	public List<TreeRow> createEntity(Entity entity, TreeRow parent){
		
		List<TreeRow> out = new ArrayList<TreeRow>();
		
		TreeCell cell10 = new TreeCell("Description");
		TreeCell cell11 = new TreeCell(entity.getDescription());
		cell11.bind(entity, CorePackage.Literals.ENTITY__DESCRIPTION);
		cell11.setEditor(new CellEditorString(treeComposite, entity));
		TreeRow child1 = new TreeRow(treeViewer, new TreeCell[]{cell10, cell11}, parent);
		out.add(child1);

		return out;
	}
	
	public TreeRow createOperationInterfaceList(String name, List<? extends OperationInterface> operationInterfaces, TreeRow parent){
		TreeCell cell0 = new TreeCell(name, ImageResource.INTERFACE_LIST);
		TreeCell cell1 = new TreeCell(operationInterfaces);
		cell1.setShowText(false);
	
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){
			public boolean hasChildren() {

				@SuppressWarnings("unchecked")
				List<OperationInterface> opList = (List<OperationInterface>)(getCell(1).getData());
				if(opList != null && !opList.isEmpty()){
					return true;
				}
				return false;
			};
			public TreeRow[] doGetChildren() {
				
				@SuppressWarnings("unchecked")
				List<OperationInterface> opList = (List<OperationInterface>)(getCell(1).getData());
				TreeRow[] child = new TreeRow[opList.size()];
				
				for(int i=0; i<opList.size(); i++){
					child[i] = createOperationInterface(String.valueOf(i+1)+". Interface", opList.get(i), this);
				}
				return child;
			};
		};
		return treeRow;
	}
	
	public TreeRow createOperationInterface(String name, OperationInterface oi , TreeRow parent){
		TreeCell cell0 = new TreeCell(name, ImageResource.INTERFACE);
		TreeCell cell1 = new TreeCell(oi);
    	cell1.setEditor(new CellEditorString(treeComposite, oi.eContainer()));
    	
    	TreeCell cell2 = null;
    	if(!oi.getRequiringContainer().isEmpty()){
    		List<OperationInterfaceContainer> choices = new ArrayList<OperationInterfaceContainer>();
    		for(OperationInterfaceContainer oic : oi.getRequiringContainer()){
    			if(choices.isEmpty()){
    				choices.addAll(OverviewArchitectureUtil.collectPotentialyResolveConteiners(oic));
    			}
    			else{
    				choices.retainAll(OverviewArchitectureUtil.collectPotentialyResolveConteiners(oic));
    			}
    		}
    		cell2 = new TreeCell(oi.getProvidingContainer());
    		if(!choices.isEmpty()){
    			cell2.setEditor(new CellEditorDropdown(treeComposite, cell2, choices));
    			cell2.bind(oi, ApplicationPackage.Literals.OPERATION_INTERFACE__PROVIDING_CONTAINER);
    		}
    	}
    	
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1, cell2}, parent){
			@Override
			public boolean hasChildren() {
				OperationInterface oi = (OperationInterface)getCell(1).getData();
				
				if(oi != null && !oi.getOperations().isEmpty()){
					return true;
				}
				return false;
			}
			
			@Override
			public TreeRow[] doGetChildren() {
				OperationInterface oi = (OperationInterface)getCell(1).getData();
				
				List<TreeRow> children = new ArrayList<TreeRow>();
				children.addAll(createEntity(oi, this));
				
				for(int i=0; i<oi.getOperations().size(); i++){
					children.add(createOperation(String.valueOf(i+1), oi.getOperations().get(i), this));
				}
				
				return children.toArray(new TreeRow[children.size()]);
			}
		};
		
		treeRow.reloadOnListChange(oi, ApplicationPackage.Literals.OPERATION_INTERFACE__OPERATIONS);
		treeRow.setChildListEditor(new EditorOperations(treeComposite, oi));
		
		return treeRow;
	}
	
	public TreeRow createOperationList(String name, List<? extends Operation> operation, TreeRow parent){
		TreeCell cell0 = new TreeCell(name);
		TreeCell cell1 = new TreeCell(operation);
		cell1.setShowText(false);
	
		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[]{cell0, cell1}, parent){
			public boolean hasChildren() {

				@SuppressWarnings("unchecked")
				List<Operation> opList = (List<Operation>)(getCell(1).getData());
				if(opList != null && !opList.isEmpty()){
					return true;
				}
				return false;
			};
			public TreeRow[] doGetChildren() {
				
				@SuppressWarnings("unchecked")
				List<Operation> opList = (List<Operation>)(getCell(1).getData());
				TreeRow[] child = new TreeRow[opList.size()];
				
				for(int i=0; i<opList.size(); i++){
					child[i] = createOperation(String.valueOf(i+1), opList.get(i), this);
				}
				return child;
			};
		};
		
		return treeRow;
	}
	
	public TreeRow createOperation(String name, Operation op, TreeRow parent) {
		TreeCell cell0 = new TreeCell(name, ImageResource.OPERATION);
		TreeCell cell1 = new TreeCell(op);
		cell1.setEditor(new CellEditorOperation(treeComposite, op));

		TreeRow treeRow = new TreeRow(treeViewer, new TreeCell[] { cell0, cell1 },parent);
		return treeRow;
	}
}
