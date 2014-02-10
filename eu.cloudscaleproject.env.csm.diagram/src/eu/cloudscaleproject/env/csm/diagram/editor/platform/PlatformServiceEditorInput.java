package eu.cloudscaleproject.env.csm.diagram.editor.platform;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import eu.cloudscaleproject.env.csm.core.Entity;

public class PlatformServiceEditorInput implements IEditorInput{

	private Entity entity;

	public PlatformServiceEditorInput(Entity entity) {
		
		this.entity = entity;
	}
	
	public Entity getEntity() {
		return entity;
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
        return entity.getName();
    }

    @Override
    public IPersistableElement getPersistable() {
        return null;
    }

    @Override
    public String getToolTipText() {
        return "Displays a component editor";
    }

    @SuppressWarnings("rawtypes")
	@Override
    public Object getAdapter(Class adapter) {
        return null;
    }

    @Override
    public int hashCode() {
        return entity.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        
        PlatformServiceEditorInput target = (PlatformServiceEditorInput) obj;
        
        if (!entity.getId().equals(target.getEntity().getId()))
            return false;
        
        return true;
    }

}
