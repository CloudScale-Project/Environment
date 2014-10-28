package org.scaledl.overview.properties.components.tree;

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
import org.scaledl.overview.util.OverviewUtil;

public class TreeCell extends TreeObject{
	
	private final HashMap<Long, IObservable> externalObservables = new HashMap<Long, IObservable>();
	
	private DataBindingContext bindingContext;
	
	private Object data;
	private Object observableObject = null;
	private List<?> observableList = null;
	
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
		return this.data != null ? this.data : (this.observableObject != null ? this.observableObject : this.observableList);
	}
	
	public Object getObservableObject(){
		return this.observableObject;
	}
	
	public List<?> getObservableList(){
		return this.observableList;
	}
	
	public void setObservableObject(final Object o){
		firePropertyChange("observableObject", this.observableObject, this.observableObject = o);
	}
	
	public void setObservableList(final List<?> o){
		firePropertyChange("observableList", this.observableList, this.observableList = o);
	}
	
	public void setData(final Object o){
		if(o instanceof List<?>){
			firePropertyChange("observableList", this.observableList, this.observableList = (List<?>)o);
		}
		else{
			firePropertyChange("observableObject", this.observableObject, this.observableObject = o);
		}
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
	
	/*
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
			
			observe(op, ApplicationPackage.Literals.OPERATION__METHOD_NAME);
			observe(op, ApplicationPackage.Literals.OPERATION__RETURN_VALUE);
			observe(op, ApplicationPackage.Literals.OPERATION__PARAMETERS);

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
		} else if (object == null){
			return "Not set";
		} else {
			return String.valueOf(object);
		}
	}
	*/
	
	public String getText(){
		return OverviewUtil.getTextRepresentation(getData());
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
		
		this.data = null;
		
		if(feature.isMany()){
			this.observableList = new ArrayList<Object>();
			
			IObservableList emfObservableList = EMFEditObservables.observeList(ed, eobject, feature);
			IObservableList localObservableList = BeanProperties.list(TreeCell.class, "observableList").observe(this);
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
			
			this.observableObject = new Object();
			
			IObservableValue emfObservable = EMFEditObservables.observeValue(ed, eobject, feature);
			IObservableValue localObservableData = BeanProperties.value(TreeCell.class, "observableObject").observe(this);
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