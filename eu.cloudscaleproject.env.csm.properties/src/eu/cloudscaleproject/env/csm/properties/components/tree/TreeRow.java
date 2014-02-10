package eu.cloudscaleproject.env.csm.properties.components.tree;

import java.util.HashMap;

import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.TreeViewer;

import eu.cloudscaleproject.env.csm.properties.components.editors.IListEditor;

public class TreeRow extends TreeObject{
	
	public final TreeViewer treeViewer;
	private final TreeRow parent;
	private final TreeCell[] cells;
	
	private TreeRow[] currentChildren = null;

	private final HashMap<Long, IObservable> externalObservables = new HashMap<Long, IObservable>();
	
	private IListEditor<?> childListEditor;
	
	public TreeRow(TreeViewer treeViewer, TreeCell[] cells, TreeRow parent){
		this.cells = cells;
		this.parent = parent;
		this.treeViewer = treeViewer;
		
		for(TreeCell cell : this.cells){
			if(cell != null){
				cell.setTreeRow(this);
			}
		}
		
	}
	
	public void setChildListEditor(IListEditor<?> editor){
		this.childListEditor = editor;
	}
	
	public IListEditor<?> getChildListEditor(){
		return this.childListEditor;
	}
	
	public boolean hasChildren(){
		return false;
	}
	
	public final TreeRow[] getChildren(){
		if(currentChildren != null){
			for(TreeRow tr : currentChildren){
				tr.dispose();
			}
		}
		
		currentChildren = doGetChildren();
		return currentChildren;
	}
	
	public TreeRow getParent(){
		return this.parent;
	}
	
	public TreeCell[] getCells(){
		return this.cells;
	}
	
	protected TreeRow[] doGetChildren(){
		return null;
	}
	
	public TreeCell getCell(int column){
		TreeCell[] cells = getCells();
		if(column < cells.length){
			return cells[column] != null ? cells[column] : new TreeCell("");
		}
		else{
			return new TreeCell("");
		}
	}
	
	public void reload(){
		if(!treeViewer.isBusy()){
			treeViewer.refresh(this, true);
		}
	}
	
	public void reloadOnValueChange(EObject eobject, EStructuralFeature feature){
		
		long id = (((long)eobject.hashCode()) << 32) + feature.getFeatureID();
		
		if(externalObservables.containsKey(id)){
			return;
		}
		
		TransactionalEditingDomain ed = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(
        		eobject.eResource().getResourceSet());
		
		IObservableList o = EMFEditObservables.observeList(ed, eobject, feature);
		o.addChangeListener(new IChangeListener() {
			
			@Override
			public void handleChange(ChangeEvent event) {
				reload();
			}
		});
		
		externalObservables.put(id, o);
	}

	public void reloadOnListChange(EObject eobject, EStructuralFeature feature){

		long id = (((long)eobject.hashCode()) << 32) + feature.getFeatureID();

		if(externalObservables.containsKey(id)){
			return;
		}

		TransactionalEditingDomain ed = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(
        		eobject.eResource().getResourceSet());
		
		IObservableValue o = EMFEditObservables.observeValue(ed, eobject, feature);
		o.addChangeListener(new IChangeListener() {
			
			@Override
			public void handleChange(ChangeEvent event) {
				reload();
			}
		});

		externalObservables.put(id, o);
	}
	
	public void update(){
		treeViewer.update(this, null);
	}
	
	public void dispose(){
		if(this.cells != null){
			for(TreeCell cell : this.cells){
				if(cell != null){
					cell.dispose();
				}
			}
		}
		
		if(this.currentChildren != null){
			for(TreeRow row: this.currentChildren){
				if(row != null){
					row.dispose();
				}
			}
		}

		this.currentChildren = null;
		
		for(IObservable o : externalObservables.values()){
			o.dispose();
		}
		externalObservables.clear();
	}
}