package org.scaledl.overview.diagram.editor.interfaces;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.scaledl.overview.application.OperationInterfaceContainer;

public class OICEditorInput implements IEditorInput{

	private OperationInterfaceContainer oic;
	
	public OICEditorInput(OperationInterfaceContainer oic) {
		this.oic = oic;
	}
	
	public OperationInterfaceContainer getOperationInterfaceContainer(){
		return this.oic;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object getAdapter(Class adapter) {
		return null;
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
		return oic.getName();
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return "Opeartion interface container editor.";
	}
	
	@Override
	public int hashCode() {
		return oic.hashCode();
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        
        OICEditorInput target = (OICEditorInput) obj;
        
        if (!oic.equals(target.getOperationInterfaceContainer()))
            return false;
        
        return true;
    }

}
