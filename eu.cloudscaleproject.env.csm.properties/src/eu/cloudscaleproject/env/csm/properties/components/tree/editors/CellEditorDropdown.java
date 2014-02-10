package eu.cloudscaleproject.env.csm.properties.components.tree.editors;

import java.util.HashSet;
import java.util.List;

import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.csm.properties.components.editors.IEditor;
import eu.cloudscaleproject.env.csm.properties.components.tree.TreeCell;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public class CellEditorDropdown extends ComboBoxViewerCellEditor implements IEditor{
	
	private final List<?> choices;
	
	public CellEditorDropdown(Composite parent, TreeCell treeCell, final List<?> choices) {
		super(parent);
		
		this.choices = choices;
		
		this.setContentProvider(new ContentProvider());
		this.setLabelProvider(new LabelProvider());
        
        this.setActivationStyle(ComboBoxViewerCellEditor.DROP_DOWN_ON_MOUSE_ACTIVATION 
        		| ComboBoxViewerCellEditor.DROP_DOWN_ON_KEY_ACTIVATION
        		| ComboBoxViewerCellEditor.DROP_DOWN_ON_TRAVERSE_ACTIVATION);
        this.setInput(treeCell);
	}

	@Override
	public boolean canEdit() {
		return true;
	}
	
	private class ContentProvider implements IStructuredContentProvider{
		@Override
        public void dispose() {
        }

        @Override
        public void inputChanged(Viewer viewer, Object oldInput,
                Object newInput) {
        }

        @Override
        public Object[] getElements(Object inputElement) {
        	if(inputElement instanceof TreeCell){
        		return choices.toArray();
        	}
        	return null;
        }
	}
	
	private class LabelProvider implements ILabelProvider{

		HashSet<ILabelProviderListener> listeners = new HashSet<ILabelProviderListener>();
		
		@Override
		public void addListener(ILabelProviderListener listener) {
			listeners.add(listener);
		}

		@Override
		public void dispose() {
			listeners.clear();
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			return true;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
			listeners.remove(listener);
		}

		@Override
		public Image getImage(Object element) {
			return null;
		}

		@Override
		public String getText(Object element) {
			return CsmUtil.getTextRepresentation(element);
		}
		
	}
}
