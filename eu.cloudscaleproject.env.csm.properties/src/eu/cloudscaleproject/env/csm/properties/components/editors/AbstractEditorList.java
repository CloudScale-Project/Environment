package eu.cloudscaleproject.env.csm.properties.components.editors;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.properties.components.dialogs.DialogListEditor;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public abstract class AbstractEditorList<T> implements IListEditor<T>{

	protected final EList<T> list;
	protected final EObject container;
	
	protected Composite composite;
	
	protected String name = "";
	
	public AbstractEditorList(Composite c, EObject container, EList<T> list){
		this.list = list;
		this.container = container;
		this.composite = c;
	}
	
	@Override
	public void openDialog(){
		TransactionalEditingDomain editingDomain = TransactionalEditingDomain.
        		Factory.INSTANCE.getEditingDomain(EcoreUtil.getRootContainer(container).eResource().getResourceSet());

		DialogListEditor<T> listEditor = 
				new DialogListEditor<T>(this, editingDomain, name, composite.getShell());
		
		listEditor.open();
	}
	
	public void setName(String name){
		this.name = name != null ? name : "";
	}
	
	public boolean canEdit(){
		if(container instanceof EObject){
			EObject eobject = (EObject)container;
			
			while(eobject != null){
				if(eobject instanceof Entity){
					Entity e = (Entity)eobject;
					if(CsmUtil.hasExternalModel(e)){return false;}
				}
				eobject = eobject.eContainer();
			}
		}
		
		return true;
	}

	@Override
	public void removeEntry(Object o) {
		list.remove(o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(Object o) {
		list.add((T)o);
	}

	@Override
	public T getEntry(int index) {
		return list.get(index);
	}

	@Override
	public Iterator<T> getIterator() {
		return list.iterator();
	}
}
