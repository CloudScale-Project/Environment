package eu.cloudscaleproject.env.csm.properties.components.tree;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;

import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.definition.Descriptor;
import eu.cloudscaleproject.env.csm.properties.components.editors.IEditor;
import eu.cloudscaleproject.env.csm.util.CsmUtil;


public class BasicTreeEditingSupport extends EditingSupport{

	private final int columnIndex;
	
    public BasicTreeEditingSupport(ColumnViewer viewer, int columnIndex) {
        super(viewer);
        this.columnIndex = columnIndex;
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
    	
    	if(element instanceof TreeRow){
    		TreeRow row = (TreeRow)element;

    		if(!row.getCell(columnIndex).getShowText()){
    			return null;
    		}
    		
    		return row.getCell(columnIndex).getEditor();
    	}
        return null;
    }

    @Override
    protected boolean canEdit(Object element) {
    	if(element instanceof TreeRow){
    		TreeRow row = (TreeRow)element;
    		
    		if(!row.getCell(columnIndex).getShowText()){
    			return false;
    		}
    		
    		CellEditor editor = row.getCell(columnIndex).getEditor();
    		
    		if(editor != null && editor instanceof IEditor){
    			return ((IEditor)editor).canEdit();
    		}
    		
    		return false;
    	}
        return false;
    }

    @Override
    protected Object getValue(Object element) {
    	if(element instanceof TreeRow){
    		TreeRow row = (TreeRow)element;
    		return row.getCell(columnIndex).getData();
    	}
        return null;
    }

    @Override
    protected void setValue(final Object element, final Object value) {
    	
		if (element instanceof TreeRow) {
			final TreeRow row = (TreeRow) element;
			final TreeCell cell = row.getCell(columnIndex);
			doSetValue(cell, value);
		}
    }
    
    private void doSetValue(final TreeCell cell, final Object value){
    	
    	if (value == null){
    		return;
    	}
    		
		Object data = cell.getData();

    	if (value instanceof Descriptor && data instanceof EObject) {
    		//handle descriptor editing
    		TransactionalEditingDomain ed = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(
    				((EObject)cell.getData()).eResource().getResourceSet());
    		ed.getCommandStack().execute(new RecordingCommand(ed) {
    			
				@Override
				protected void doExecute() {
					cell.setData(CsmUtil.getSystemDescriptor((EObject)cell.getData(), (Descriptor)value));
				}
			});
    		return;
		}
    	else if(data instanceof Entity && value instanceof String){
    		//handle entity name editing
			final Entity e = (Entity)data;
		
			TransactionalEditingDomain ed = TransactionalEditingDomain.Factory
				.INSTANCE.getEditingDomain(e.eResource().getResourceSet());
			ed.getCommandStack().execute(new RecordingCommand(ed) {
			
					@Override
					protected void doExecute() {
						e.setName((String)value);
					}
				});
		}
		else{
			cell.setData(value);
		}
    }
}