package eu.cloudscaleproject.env.csm.properties.components.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.graphics.Image;

import eu.cloudscaleproject.env.csm.core.CorePackage;
import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.core.Operation;
import eu.cloudscaleproject.env.csm.core.TypeEnum;

public class TreeCell extends TreeObject{
	
	private final HashMap<Long, IObservable> externalObservables = new HashMap<Long, IObservable>();
	
	private DataBindingContext bindingContext;
	
	private Object data;
	
	private TreeRow treeRow;
	private CellEditor editor = null;
	
	private boolean isEditable;
	private boolean isShowText = true;
	
	private Image image;
	
	public TreeCell(Object o){
		this.data = o;
		this.isEditable = false;
	}

	public TreeCell(Object o, Image image){
		this.data = o;
		this.isEditable = false;
		this.image = image;
	}
	
	public Image getImage(){
		return this.image;
	}
	
	public void setImage(Image image){
		this.image = image;
		if(this.treeRow != null){
			treeRow.update();
		}
	}
	
	public boolean getShowText(){
		return this.isShowText;
	}
	
	public void setShowText(boolean showText){
		this.isShowText = showText;
	}
	
	public void setTreeRow(TreeRow treeRow){
		this.treeRow = treeRow;
	}
	
	public Object getData(){
		return this.data;
	}
	
	public void setData(final Object o){
		firePropertyChange("data", this.data, this.data = o);
	}
	
	public void observe(EObject eobject, EStructuralFeature sf){
		
		long id = (((long)eobject.hashCode()) << 32) + sf.getFeatureID();
		
		if(externalObservables.containsKey(id)){
			return;
		}
		
		IObservableValue ov = EMFObservables.observeValue(eobject, sf);
		ov.addValueChangeListener(new IValueChangeListener() {
			
			@Override
			public void handleValueChange(ValueChangeEvent event) {
				if(treeRow != null){
					treeRow.update();
				}
			}
		});
		
		externalObservables.put(id, ov);
	}
	
	private String doGetText(Object object){

		if (object instanceof Object[]) {
			String out = "List{";
			Object[] list = (Object[]) object;

			for (Object obj : list) {
				out += doGetText(obj);
				out += ", ";
			}
			if (list.length > 0) {
				out = out.substring(0, Math.max(out.length() - 2, 0));
			}

			out += "}";
			return out + "}";
		} else if (object instanceof List<?>) {
			String out = "List{";
			List<?> list = (List<?>) object;

			for (Object obj : list) {
				out += doGetText(obj);
				out += ", ";
			}
			if (list.size() > 0) {
				out = out.substring(0, Math.max(out.length() - 2, 0));
			}
			out += "}";

			return out;
		} else if (object instanceof String) {
			return (String) object;
		} else if (object instanceof Operation) {
			Operation op = (Operation) object;
			
			observe(op, CorePackage.Literals.OPERATION__METHOD_NAME);
			observe(op, CorePackage.Literals.OPERATION__RETURN_VALUE);
			observe(op, CorePackage.Literals.OPERATION__PARAMETERS);

			String out = op.getReturnValue().getLiteral() + " "
					+ op.getMethodName();
			out += " (";
			for (TypeEnum e : op.getParameters()) {
				if (e != null) {
					out += e.getLiteral() + ", ";
				}
			}
			if (!op.getParameters().isEmpty()) {
				out = out.substring(0, out.length() - 2);
			}
			out += ")";

			return out;
		} else if (object instanceof Entity) {
			Entity e = (Entity) object;
			observe(e, CorePackage.Literals.ENTITY__NAME);
			return e.getName();
		} else {
			return String.valueOf(object);
		}
	}
	
	public String getText(){
		return doGetText(this.data);
	}
	
	public boolean isEditable(){
		return this.isEditable;
	}
	
	public void setEditable(boolean editable){
		this.isEditable = editable;
	}
	
	public void setEditor(CellEditor editor){
		
		if(this.editor != null){
			this.editor.dispose();
			this.editor = null;
		}
		
		if(editor == null){
			this.isEditable = false;
			return;
		}
		
		this.editor = editor;
		this.isEditable = true;
	}
	
	public CellEditor getEditor(){
		return this.editor;
	}
	
	public void bind(EObject eobject, EStructuralFeature feature){
		
		if(eobject == null || feature == null){
			return;
		}
		
		TransactionalEditingDomain ed = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(
        		eobject.eResource().getResourceSet());
		
		if(bindingContext == null){
			bindingContext = new DataBindingContext();
		}
		
		
		if(data instanceof List){
			
			this.data = new ArrayList<Object>();
			
			IObservableList emfObservableList = EMFEditObservables.observeList(ed, eobject, feature);
			IObservableList localObservableList = BeanProperties.list(TreeCell.class, "data").observe(this);
			localObservableList.addListChangeListener(new IListChangeListener() {

				@Override
				public void handleListChange(ListChangeEvent event) {
					if(treeRow != null){
						treeRow.reload();
					}
				}
			});
			
			bindingContext.bindList(localObservableList, emfObservableList);
		}
		else{
			
			this.data = new Object();
			
			IObservableValue emfObservable = EMFEditObservables.observeValue(ed, eobject, feature);
			IObservableValue localObservableData = BeanProperties.value(TreeCell.class, "data").observe(this);
			localObservableData.addValueChangeListener(new IValueChangeListener() {
				
				@Override
				public void handleValueChange(ValueChangeEvent event) {
					if(treeRow != null){
						treeRow.reload();
					}
				}
			});
			
			bindingContext.bindValue(localObservableData, emfObservable);
		}
		
		bindingContext.updateTargets();
	}
	
	public String toString(){
		return getText();
	}
	
	public void disposeBindings(){
		
		for(IObservable o : externalObservables.values()){
			o.dispose();
		}
		externalObservables.clear();
		
		if(bindingContext != null){
			this.bindingContext.dispose();
		}
		bindingContext = null;
	}
	
	public void dispose(){
		
		if(this.editor != null){
			this.editor.dispose();
		}

		disposeBindings();
	}
}