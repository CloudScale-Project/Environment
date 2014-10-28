package org.scaledl.overview.properties.components.editors;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.scaledl.overview.core.Entity;
import org.scaledl.overview.properties.components.dialogs.DialogListToListEditor;
import org.scaledl.overview.util.OverviewUtil;

public abstract class AbstractEditorListToList<T> extends AbstractEditorList<T>{

	private String nameListSecondary = "";
	
	public AbstractEditorListToList(Composite c, EObject container, EList<T> list){
		super(c, container, list);
	}
	
	public abstract Collection<T> getSecondaryList();
	
	@Override
	public void openDialog(){
		TransactionalEditingDomain editingDomain = TransactionalEditingDomain.
        		Factory.INSTANCE.getEditingDomain(EcoreUtil.getRootContainer(container).eResource().getResourceSet());

		DialogListToListEditor<T> listEditor = 
				new DialogListToListEditor<T>(this, editingDomain, name, composite.getShell());
		
		Collection<T> secList = getSecondaryList();
		if(secList instanceof EList<?>){
			listEditor.setData(nameListSecondary, (EList<T>)secList);
		}
		else{
			listEditor.setData(nameListSecondary, getSecondaryList());
		}
		
		listEditor.open();
	}
	
	public void setSecondaryListName(String secondaryListName){
		this.nameListSecondary = secondaryListName != null ? secondaryListName : "";
	}
	
	@Override
	public boolean canEdit(){
		if(container instanceof EObject){
			EObject eobject = (EObject)container;
			
			while(eobject != null){
				if(eobject instanceof Entity){
					Entity e = (Entity)eobject;
					if(OverviewUtil.hasExternalModel(e)){return false;}
				}
				eobject = eobject.eContainer();
			}
		}
		
		return true;
	}
}
