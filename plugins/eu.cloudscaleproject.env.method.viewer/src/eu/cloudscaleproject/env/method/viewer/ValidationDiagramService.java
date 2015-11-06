package eu.cloudscaleproject.env.method.viewer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.services.IValidationDiagramService;

public class ValidationDiagramService implements IValidationDiagramService{
	
	private static final Logger logger = Logger.getLogger(ValidationDiagramService.class.getName());

	private ValidationDiagram activeDiagram = null;
	
	private String selectedNodeID = null;
	private String selectedSectionID = null;
	
	private HashMap<IProject, ValidationDiagram> diagrams = new HashMap<IProject, ValidationDiagram>();
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	@Override
	public void createDiagram(IProject project) {
		
		if(diagrams.get(project) != null){
			throw new RuntimeException("Validation diagram already exist");
		}
		
		ValidationDiagram diagram = new ValidationDiagram(null);
		diagram.setProject(project);
		diagrams.put(project, diagram);
		pcs.firePropertyChange(PROP_CREATE_DIAGRAM, null, project);
	}
	
	@Override
	public void deleteDiagram(IProject project) {
		ValidationDiagram diagram = diagrams.get(project);
		if(diagram != null){
			diagram.dispose();
			diagrams.remove(project);
			pcs.firePropertyChange(PROP_DELETE_DIAGRAM, project, null);
		}
	}
	
	@Override
	public void showDiagram(IProject project){

		ValidationDiagram diagram = diagrams.get(project);
		pcs.firePropertyChange(PROP_SHOW_DIAGRAM, activeDiagram, activeDiagram = diagram);	
	}
	
	public List<ValidationDiagram> getDiagrams(){
		return new ArrayList<ValidationDiagram>(diagrams.values());
	}
	
	public ValidationDiagram getDiagram(IProject project){
		return diagrams.get(project);
	}
	
	@Override
	public void showStatus(IProject project, IValidationStatusProvider statusProvider){
						
		ValidationDiagram diagramProvider = diagrams.get(project);
		
		if(statusProvider == null){
			logger.severe("Specified IValidationStatusProvider is NULL. Status will not be shown!");
			return;
		}
		
		String id = statusProvider.getID();
		if(id == null || id.isEmpty()){
			logger.severe("IValidationStatusProvider does not have ID specified. Status will not be shown!");
			return;
		}

		if(diagramProvider != null){
			IValidationStatusProvider lastStatus = diagramProvider.getActiveStatusProvider(id);
			if(lastStatus != statusProvider){
				diagramProvider.bindStatusProvider(id, statusProvider);
				
				pcs.firePropertyChange(PROP_SHOW_STATUS, lastStatus, statusProvider);
			}
		}
		else{
			logger.severe("Validation diagram can not be retrieved! Project: " + project.toString());
		}
				
	}
	
	@Override
	public void showStatusIfEmpty(IProject project, IValidationStatusProvider statusProvider){
		
		if(statusProvider == null){
			logger.severe("Specified IValidationStatusProvider is NULL. Status will not be shown!");
			return;
		}
		
		String id = statusProvider.getID();
		if(id == null || id.isEmpty()){
			logger.severe("IValidationStatusProvider does not have ID specified. Status will not be shown!");
			return;
		}
		
		ValidationDiagram diagramProvider = diagrams.get(project);
		if(diagramProvider != null){
			if(diagramProvider.getActiveStatusProvider(id) == null){
				diagramProvider.bindStatusProvider(id, statusProvider);
				
				pcs.firePropertyChange(PROP_SHOW_STATUS, null, statusProvider);	
			}
		}
		else{
			logger.severe("Validation diagram can not be retrieved! Project: " + project.toString());
		}
		
	}
	
	@Override
	public void clearStatus(IProject project, String id){
		
		if(id == null || id.isEmpty()){
			logger.severe("Specified ID is NULL or empty. Status will not be cleared!");
			return;
		}
		
		ValidationDiagram diagramProvider = diagrams.get(project);
		if(diagramProvider != null){
			IValidationStatusProvider currentStatus = diagramProvider.getActiveStatusProvider(id);
			if(currentStatus != null){
				diagramProvider.bindStatusProvider(id, null);
				
				pcs.firePropertyChange(PROP_CLEAR_STATUS, currentStatus, id);	
			}
		}
		else{
			logger.severe("Validation diagram can not be retrieved! Project: " + project.toString());
		}
	}

	public ValidationDiagram getActiveDiagram() {
		return activeDiagram;
	}

	@Override
	public String getSelectedNodeID() {
		return selectedNodeID;
	}
	
	public void setSelectedNodeID(String id){
		this.selectedNodeID = id;
		pcs.firePropertyChange(PROP_SELECTED_ID, this.selectedNodeID, this.selectedNodeID = id);	
	}
	
	@Override
	public String getSelectedSectionID() {
		return selectedSectionID;
	}
	
	public void setSelectedSectionID(String id){
		this.selectedNodeID = id;
		pcs.firePropertyChange(PROP_SELECTED_ID, this.selectedSectionID, this.selectedSectionID = id);	
	}
	
	@Override
	public IProject getActiveProject() {
		if(activeDiagram == null){
			return null;
		}
		
		return activeDiagram.getProject();
	}
	
	@Override
	public IValidationStatus getActiveStatus(String id) {
		
		if(activeDiagram == null){
			return null;
		}
		
		return activeDiagram.getActiveStatus(id);
	}

	@Override
	public IValidationStatusProvider getActiveStatusProvider(String id) {
		
		if(activeDiagram == null){
			return null;
		}
		
		return activeDiagram.getActiveStatusProvider(id);
	}
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener){
		pcs.removePropertyChangeListener(listener);
	}
	
}
