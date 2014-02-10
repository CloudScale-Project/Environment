package eu.cloudscaleproject.env.method;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class MethodEditorInput implements IEditorInput{

	public MethodEditorInput() {
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
    	return "Method Overview";
    }

    @Override
    public IPersistableElement getPersistable() {
        return null;
    }

    @Override
    public String getToolTipText() {
        return "Displays a method overview editor";
    }

    @SuppressWarnings("rawtypes")
	@Override
    public Object getAdapter(Class adapter) {
        return null;
    }

    @Override
    public int hashCode() {
    	return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MethodEditorInput;
    }

}
