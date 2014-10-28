package org.scaledl.overview.properties.components.tree.editors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.scaledl.overview.core.Entity;
import org.scaledl.overview.properties.components.editors.IEditor;
import org.scaledl.overview.util.OverviewUtil;

public class CellEditorString extends TextCellEditor implements IEditor{

	private final EObject container;
	
	public CellEditorString(Composite c, EObject container) {
		super(c);
		this.container = container;
	}
	
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
	
	@Override
	protected void doSetValue(Object value) {
		if(!(value instanceof String)){
			if(value instanceof Entity){
				Entity e = (Entity)value;
				super.doSetValue(e.getName());
			}
		}
		else{
			super.doSetValue(value);
		}
	}
}
