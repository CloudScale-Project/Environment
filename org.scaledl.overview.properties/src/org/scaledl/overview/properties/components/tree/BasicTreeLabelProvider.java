package org.scaledl.overview.properties.components.tree;

import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.scaledl.overview.core.Entity;
import org.scaledl.overview.properties.ImageResource;
import org.scaledl.overview.properties.components.editors.IEditor;

public class BasicTreeLabelProvider extends CellLabelProvider {
	

    @Override
    public void update(ViewerCell cell) {
        
        Object o = cell.getElement();
        int columnIndex = cell.getColumnIndex();
        
        if(o instanceof TreeRow){
        	TreeRow treeRow = (TreeRow)o;
        	if(!treeRow.getCell(columnIndex).getShowText()){
        		return;
        	}
        	
        	cell.setText(treeRow.getCell(columnIndex).getText());
        	
        	if(treeRow.getCell(columnIndex).getImage() != null){
        		cell.setImage(treeRow.getCell(columnIndex).getImage());
        	}
        	else{
				if (treeRow.getCell(columnIndex).isEditable()){
					CellEditor editor = treeRow.getCell(columnIndex).getEditor();
					if(editor instanceof IEditor && ((IEditor)editor).canEdit()){
						cell.setImage(ImageResource.EDITABLE);
					}
	        	}
				if(columnIndex == 0){
					if(treeRow.getCell(1).getData() instanceof List<?>){
						cell.setImage(ImageResource.LIST);
					}
					else if(treeRow.getCell(1).getData() instanceof Entity){
						cell.setImage(ImageResource.ENTITY);
					}
				}
        	}
        }   
    }
}