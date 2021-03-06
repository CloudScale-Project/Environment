package eu.cloudscaleproject.env.common.notification;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobGroup;

import eu.cloudscaleproject.env.common.BatchExecutor;
import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.Extensions;

public class StatusManager {
	
	public static final String PROP_STATUS_PROVIDER_ADDED = "eu.cloudscaleproject.env.common.notification.StatusManager.providerAdded";
	public static final String PROP_STATUS_PROVIDER_REMOVED = "eu.cloudscaleproject.env.common.notification.StatusManager.providerRemoved";

	public static final String PROP_STATUS_ADDED = "eu.cloudscaleproject.env.common.notification.StatusManager.statusAdded";
	public static final String PROP_STATUS_REMOVED = "eu.cloudscaleproject.env.common.notification.StatusManager.statusRemoved";
	public static final String PROP_STATUS_CHANGED = "eu.cloudscaleproject.env.common.notification.StatusManager.statusChanged";

	public static final String PROP_VALIDATION_COMPLETED = "eu.cloudscaleproject.env.common.notification.StatusManager.validationCompleted";
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	//private final Object validationLock = new Object();

	private JobGroup jobGroup = new JobGroup("Validation group", 1, 1);
	
	//forward status provider changes to this prop. change support.
	private final PropertyChangeListener statusProviderListener = new PropertyChangeListener() {
		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			
			if(evt.getSource() instanceof IValidationStatusProvider){
				
				if(IValidationStatusProvider.PROP_STATUS_ADDED.equals(evt.getPropertyName())){
					pcs.firePropertyChange(PROP_STATUS_ADDED, evt.getOldValue(), evt.getNewValue());
				}
				if(IValidationStatusProvider.PROP_STATUS_REMOVED.equals(evt.getPropertyName())){
					pcs.firePropertyChange(PROP_STATUS_REMOVED, evt.getOldValue(), evt.getNewValue());
				}
				
			}
			if(evt.getSource() instanceof IValidationStatus){
				
				BatchExecutor.getInstance().addTask(this, evt.getSource(), new Runnable() {
					
					@Override
					public void run() {
						pcs.firePropertyChange(PROP_STATUS_CHANGED, null, evt.getSource());
					}
				});
			}
		}
	};
	
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

	private class ValidationJob extends Job
	{
		private IProject project;
		private IValidationStatusProvider statusProvider;

		public ValidationJob(String name, IProject project, IValidationStatusProvider statusProvider)
		{
			super(name);
			this.project = project;
			this.statusProvider = statusProvider;
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			
			monitor.beginTask("Validating...", IProgressMonitor.UNKNOWN);
			doValidate(project, statusProvider);
			monitor.done();
			
			return Status.OK_STATUS;
		}
	}
	
	private static final Logger logger = Logger.getLogger(StatusManager.class.getName());
	
	private long managerCreationTime = 0;
	private HashMap<IValidationStatusProvider, IProject> statusProviders = new HashMap<IValidationStatusProvider, IProject>();
		
	//private static StatusManager instance = null;
	public static StatusManager getInstance(){
		/*
		if(instance == null){
			instance = new StatusManager();
			CloudscaleContext.inject(instance);
		}
		return instance;
		*/
		
		return CloudscaleContext.getGlobalContext().get(StatusManager.class);
	}
	
	public StatusManager(){
		this.managerCreationTime = System.currentTimeMillis();
	}
	
	public synchronized void addStatusProvider(IProject project, IValidationStatusProvider statusProvider){
		statusProviders.put(statusProvider, project);
		statusProvider.addStatusChangeListener(statusProviderListener);
		pcs.firePropertyChange(PROP_STATUS_PROVIDER_ADDED, null, statusProvider);
	}
	
	public synchronized void removeStatusProvider(IValidationStatusProvider statusProvider){
		statusProviders.remove(statusProvider);
		statusProvider.removeStatusChangeListener(statusProviderListener);			
		pcs.firePropertyChange(PROP_STATUS_PROVIDER_REMOVED, statusProvider, null);
	}
	
	public synchronized List<IValidationStatusProvider> getStatusProviders(IProject project){
		List<IValidationStatusProvider> out = new ArrayList<IValidationStatusProvider>();
		for(Entry<IValidationStatusProvider, IProject> entry : statusProviders.entrySet()){
			try{
				if(project == null && entry.getValue() == null){
					IValidationStatusProvider sp = entry.getKey();
					if(sp != null){
						out.add(entry.getKey());
					}
				}
				if(project != null && project.equals(entry.getValue())){
					IValidationStatusProvider sp = entry.getKey();
					if(sp != null){
						out.add(entry.getKey());
					}
				}
			}
			catch(NullPointerException e){
				//skip
			}
		}
		return out;
	}
	
	public synchronized List<IValidationStatusProvider> getStatusProviders(IProject project, String id){
		List<IValidationStatusProvider> out = new ArrayList<IValidationStatusProvider>();
		for(Entry<IValidationStatusProvider, IProject> entry : statusProviders.entrySet()){
			try{
				if(project == null && entry.getValue() == null){
					IValidationStatusProvider sp = entry.getKey();
					if(sp != null){
						out.add(entry.getKey());
					}
				}
				if(project != null &&  project.equals(entry.getValue()) && id.equals(entry.getKey().getID())){
					IValidationStatusProvider sp = entry.getKey();
					if(sp != null){
						out.add(entry.getKey());
					}
				}
			}
			catch(NullPointerException e){
				//skip
			}
		}
		return out;
	}
	
	//property changes
	public void addPropertyChangeListener(String prop, PropertyChangeListener listener){
		pcs.addPropertyChangeListener(prop, listener);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(String prop, PropertyChangeListener listener){
		pcs.removePropertyChangeListener(prop, listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		pcs.removePropertyChangeListener(listener);
	}

	public boolean hasValidator(String id){
		boolean validatorFound = false;
		for (IResourceValidator v : Extensions.getInstance().getResourceValidators()) {
			if (v.getID().equals(id)) {
				validatorFound = true;
			}
		}
		
		return validatorFound;
	}
	
	public synchronized void validate(IProject project, IValidationStatusProvider statusProvider){
		
		if(statusProvider == null){
			logger.severe("Status provider is NULL! Can not validate!");
			return;
		}

		List<Job> jobs = jobGroup.getActiveJobs();
		for(Job job : jobs){
			if(job instanceof ValidationJob){
				ValidationJob vj = (ValidationJob)job;
				if (vj.statusProvider == statusProvider) return;
			}
		}
		
		//find out name
		String name = "Validating '" + project.getName() + "' project";
		IValidationStatus vs = statusProvider.getSelfStatus();
		if(vs != null){
			name = "Validating '" + vs.getName() + "' alternative";
		}
		
		ValidationJob job = new ValidationJob(name, project, statusProvider);
		job.setUser(false);
		job.setJobGroup(jobGroup);
		
		long startupDelay = 3000;
		
		if(System.currentTimeMillis() - managerCreationTime > startupDelay){
			job.schedule();
		}
		else{
			job.schedule(startupDelay - System.currentTimeMillis() + managerCreationTime);
		}
	}

	
	private void doValidate(IProject project, IValidationStatusProvider statusProvider) {
		try{
			boolean validatorFound = false;
			for (IResourceValidator v : Extensions.getInstance().getResourceValidators()) {				
				if (v.getID().equals(statusProvider.getID())) {
					validatorFound = true;
					
					//limit only one validation at a time
					v.validate(project, statusProvider);
					statusProvider.setValidated(true);
				}
			}
	
			if (!validatorFound) {
				logger.warning("Validator not found or it is not registered in extension point! ToolID: "
						+ statusProvider.getID());
			}
			else{
				pcs.firePropertyChange(PROP_VALIDATION_COMPLETED, null, statusProvider);
			}
		}
		catch(Exception e){
			//validation should not break any other functionality
			e.printStackTrace();
		}
	}
	
	public synchronized void validateAll(IProject project){
		for(Entry<IValidationStatusProvider, IProject> entry : statusProviders.entrySet()){
			try{
				// if global validator
				if(entry.getValue() == null){
					validate(null, entry.getKey());
				}
				// else
				else if(project != null && project.equals(entry.getValue())){
					validate(project, entry.getKey());
				}
			}
			catch(NullPointerException e){
				//skip
			}
		}
	}
	
}
