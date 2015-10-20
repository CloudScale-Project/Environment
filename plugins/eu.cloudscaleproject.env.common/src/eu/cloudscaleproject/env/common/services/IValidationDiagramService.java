package eu.cloudscaleproject.env.common.services;

import java.beans.PropertyChangeListener;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;

public interface IValidationDiagramService {
	
	public static final String PROP_SELECTED_ID = "eu.cloudscaleproject.env.common.notification.diagram.IValidationDiagramService.selectedID";
	
	public static final String PROP_INIT_DIAGRAM = "eu.cloudscaleproject.env.common.notification.diagram.IValidationDiagramService.createDiagram";	
	public static final String PROP_SHOW_DIAGRAM = "eu.cloudscaleproject.env.common.notification.diagram.IValidationDiagramService.showDiagram";	
	
	public static final String PROP_SHOW_STATUS = "eu.cloudscaleproject.env.common.notification.diagram.IValidationDiagramService.showStatus";
	public static final String PROP_CLEAR_STATUS = "eu.cloudscaleproject.env.common.notification.diagram.IValidationDiagramService.clearStatus";

	public IProject getActiveProject();
	public IValidationStatus getActiveStatus(String id);
	public IValidationStatusProvider getActiveStatusProvider(String id);
	
	public String getSelectedNodeID();
	public String getSelectedSectionID();
		
	public void showDiagram(IProject project);
	
	public void showStatus(IProject project, IValidationStatusProvider statusProvider);
	public void showStatusIfEmpty(IProject project, IValidationStatusProvider statusProvider);
	public void clearStatus(IProject project, String id);
	
	public void addPropertyChangeListener(PropertyChangeListener listener);
	public void removePropertyChangeListener(PropertyChangeListener listener);
}
