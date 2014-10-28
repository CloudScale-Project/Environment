package org.scaledl.overview.properties.components.tree;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class BasicTreeViewFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if(element instanceof TreeRow){
			TreeRow row = (TreeRow)element;
			if(row.getCell(0).getData().equals("Description")){
				return false;
			}
		}
		return true;
	}
	
}