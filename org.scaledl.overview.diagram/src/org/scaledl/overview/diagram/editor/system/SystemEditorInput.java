package org.scaledl.overview.diagram.editor.system;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.scaledl.overview.Overview;

public class SystemEditorInput implements IEditorInput{

	private Overview overview;
	private Object selection;

	public SystemEditorInput(Overview csm) {
		
		this.overview = csm;
	}
	
	public Overview getOverview() {
		return overview;
	}
	
	public void setSelection (Object selection)
	{
		this.selection = selection;
	}
	public Object getSelection() {
		return selection;
	}
	
	@Override
    public boolean exists() {
        return true;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return null;
    }

    @Override
    public String getName() {
        return overview.toString(); // TODO 
    }

    @Override
    public IPersistableElement getPersistable() {
        return null;
    }

    @Override
    public String getToolTipText() {
        return "Displays a cloud provider editor";
    }

    @SuppressWarnings("rawtypes")
	@Override
    public Object getAdapter(Class adapter) {
        return null;
    }

    @Override
    public int hashCode() {
        return overview.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        
        SystemEditorInput target = (SystemEditorInput) obj;
        
        if (!overview.equals(target.getOverview()))
            return false;
        
        return true;
    }

}
