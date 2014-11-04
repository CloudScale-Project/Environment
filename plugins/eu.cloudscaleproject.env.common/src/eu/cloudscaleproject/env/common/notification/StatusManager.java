package eu.cloudscaleproject.env.common.notification;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.Creatable;

import eu.cloudscaleproject.env.common.ExtensionRetriever;

@Creatable
@Singleton
public class StatusManager {
	
	public enum Tool{
				
		EXTRACTOR_INPUT("ext_input"),
		EXTRACTOR("ext_tool"),
		EXTRACTOR_RESULTS("ext_res"),
		
		ANALYSER_INPUT("analyser_input"),
		ANALYSER("analyser_tool"),
		ANALYSER_RESULTS("analyser_res"),
				
		DYNAMIC_SPOTTER_INPUT("dynamic_spotter_input"),
		DYNAMIC_SPOTTER("dynamic_spotter_tool"),
		DYNAMIC_SPOTTER_RESULTS("dynamic_spotter_res"),
		
		STATIC_SPOTTER_INPUT("static_spotter_input"),
		STATIC_SPOTTER("static_spotter_tool"),
		STATIC_SPOTTER_RESULTS("static_spotter_res"),
								
		OVERVIEW("overview"),
		ARCHITECTURE_TEMPLATES("architectureTemplates"),
		USAGE_EVOLUTION("usageEvolution"),
		
		//actions
		TRANSFORM_OVERVIEW("action_transform_overview_analyser"),
		IMPORT_EXT_ANALYSER("action_import_ext_analyser"),
		IMPORT_EXT_SS("action_import_ext_ss"),
		USE_MEASUREMENTS("action_useMeasurements"),
		IMPORT_EXT_OVERVIEW("action_import_ext_overview");

		
		private final String id;
		
		Tool(String id){
			this.id = id;
		}
		
		public String getID(){
			return id;
		}
	}
	
	private static final Logger logger = Logger.getLogger(StatusManager.class.getName());
	
	private final Object statusLock = new Object();
	private final Object validationLock = new Object();
	
	private List<ToolValidator> validators = null;
	private List<IStatusService> services = null;
	
	@Inject
	private ExtensionRetriever er;
	
	public List<ToolValidator> getValidators(){
		if(validators == null){
			validators = er.retrieveExtensionObjects(
					"eu.cloudscaleproject.env.common.notification.validator",
					"class", ToolValidator.class);
		}
		return validators;
	}
	
	public List<IStatusService> getServices(){
		if(this.services == null){
			services = er.retrieveExtensionObjects(
					"eu.cloudscaleproject.env.common.notification.status",
					"class", IStatusService.class);
		}
		return services;
	}
	
	public boolean hasValidator(String toolID){
		boolean validatorFound = false;
		for (ToolValidator v : getValidators()) {
			if (v.getToolID().equals(toolID)) {
				validatorFound = true;
			}
		}
		
		return validatorFound;
	}
	
	public boolean validate(IProject project, String toolID) {

		boolean isValid = true;

		synchronized (validationLock) {
			boolean validatorFound = false;
			for (ToolValidator v : getValidators()) {				
				if (v.getToolID().equals(toolID)) {
					validatorFound = true;
					isValid &= v.validate(project);
				}
			}

			if (!validatorFound) {
				logger.warning("Validator not found or it is not registered in extension point! +"
						+ "Method \"validate()\" is returning TRUE for the ToolID: "
						+ toolID);
			}
		}

		return isValid;
	}
	
	public boolean validateAll(IProject project) {

		boolean isValid = true;
		synchronized (validationLock) {
			for (ToolValidator v : getValidators()) {
				isValid &= v.validate(project);
			}
		}
		return isValid;
	}

	public IToolStatus getStatus(IProject project, String toolID) {

		synchronized (statusLock) {
			
			if(getServices().isEmpty()){
				logger.warning("Notification system implementation plugin or extension point NOT FOUND!");
				logger.warning("Returning \"dummy\" IToolStatus object for toolID: "+ toolID); 
				//dummy implementations - notification service should not produce null pointer exceptions! 
				return new DummyToolStatus(); 
			}
			
			IToolStatus status = getServices().get(0).getToolStatus(project, toolID);

			if(status == null){
				logger.warning("Notification system for specified toolID NOT FOUND!");
				logger.warning("Returning \"dummy\" IToolStatus object for: " + toolID);
				//dummy implementations - notification service should not produce null pointer exceptions!
				return new DummyToolStatus();
			}

			return status;
		}
	}
}
