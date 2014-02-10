package eu.cloudscaleproject.env.csm.properties.components.tree.editors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.properties.components.editors.IEditor;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

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
					if(CsmUtil.hasExternalModel(e)){return false;}
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
