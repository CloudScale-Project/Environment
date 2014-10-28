package org.scaledl.overview.diagram.editor.application;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.scaledl.overview.architecture.SoftwareService;


public class SoftwareServiceEditorInput implements IEditorInput{

	private SoftwareService applicationService;

	public SoftwareServiceEditorInput(SoftwareService softwareService) {
		
		this.applicationService = softwareService;
	}
	
	public SoftwareService getSoftwareService() {
		return applicationService;
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
        return applicationService.getName();
    }

    @Override
    public IPersistableElement getPersistable() {
        return null;
    }

    @Override
    public String getToolTipText() {
        return "Displays an application service editor";
    }

    @SuppressWarnings("rawtypes")
	@Override
    public Object getAdapter(Class adapter) {
        return null;
    }

    @Override
    public int hashCode() {
        return applicationService.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        
        SoftwareServiceEditorInput target = (SoftwareServiceEditorInput) obj;
        
        if (!applicationService.getId().equals(target.getSoftwareService().getId()))
            return false;
        
        return true;
    }

}
