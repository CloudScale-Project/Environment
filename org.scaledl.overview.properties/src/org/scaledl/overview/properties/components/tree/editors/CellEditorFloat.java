package org.scaledl.overview.properties.components.tree.editors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.scaledl.overview.core.Entity;
import org.scaledl.overview.properties.components.editors.IEditor;
import org.scaledl.overview.util.OverviewUtil;

public class CellEditorFloat extends TextCellEditor implements IEditor{

	private final EObject container;
	
	public CellEditorFloat(Composite c, EObject container) {
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
	public void create(Composite parent) {
		super.create(parent);
		
		text.addVerifyListener(new VerifyListener() {

	        @Override
	        public void verifyText(VerifyEvent e) {

	            Text text = (Text)e.getSource();

	            // get old text and create new text by using the VerifyEvent.text
	            final String oldS = text.getText();
	            String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);

	            boolean isDouble = true;
	            try
	            {
	                Double.parseDouble(newS);
	            }
	            catch(NumberFormatException ex)
	            {
	                isDouble = false;
	            }

	            if(!isDouble)
	                e.doit = false;
	        }
	    });
	}
	
	@Override
	protected Object doGetValue() {
		String s = text.getText();

		try {
			return Double.parseDouble(s);
		} catch (NumberFormatException ex) {
			return "";
		}
	}
	
	@Override
	protected boolean dependsOnExternalFocusListener() {
		return false;
	}
}
