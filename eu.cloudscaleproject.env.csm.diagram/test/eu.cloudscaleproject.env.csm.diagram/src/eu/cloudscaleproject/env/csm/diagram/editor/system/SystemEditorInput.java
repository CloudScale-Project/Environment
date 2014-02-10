package eu.cloudscaleproject.env.csm.diagram.editor.system;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import eu.cloudscaleproject.env.csm.Csm;

public class SystemEditorInput implements IEditorInput{

	private Csm csm;
	private Object selection;

	public SystemEditorInput(Csm csm) {
		
		this.csm = csm;
	}
	
	public Csm getCsm() {
		return csm;
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
        return csm.toString(); // TODO 
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
        return csm.hashCode();
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
        
        if (!csm.equals(target.getCsm()))
            return false;
        
        return true;
    }

}
