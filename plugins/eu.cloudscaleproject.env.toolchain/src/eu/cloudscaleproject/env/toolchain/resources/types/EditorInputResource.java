package eu.cloudscaleproject.env.toolchain.resources.types;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.ResourceValidationStatus;
import eu.cloudscaleproject.env.common.notification.StatusManager;

public abstract class EditorInputResource extends EditorInput implements IEditorInputResource{
	
	private static final Logger logger = Logger.getLogger(EditorInputResource.class.getName());
	private PropertyChangeListener listener;
	
	protected final Object saveLoadLock = new Object();
	protected final IResource resource;
	
	private boolean isDisposed = false;
	private boolean isLoaded = false;
	private boolean isDirty = false;
	
	protected boolean createInProgress = false;
	protected boolean saveInProgress = false;
	protected boolean loadInProgress = false;
	protected boolean deleteInProgress = false;
	
	private boolean jobInProgress = false;
	
	protected abstract void handleCreate();
	protected abstract void handleSave();
	protected abstract void handleLoad();
	protected abstract void handleDelete();
		
	public EditorInputResource(IResource resource){
		this(resource, null);
	}
	
	public EditorInputResource(IResource resource, String validatorID) {
		super("No name", validatorID);
		
		if(resource == null){
			throw new NullPointerException("Resource can not be NULL!");
		}
		
		this.resource = resource;
	}
	
	protected void initializeValidationListener()
	{
		if (listener == null)
		{
			listener = new PropertyChangeListener() {
				
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					
					if(jobInProgress){
						return;
					}
					
					if (EditorInputResource.this.getID() != null)
						StatusManager.getInstance().validateAsync(getProject(), EditorInputResource.this);
				}
			};
			
			this.addPropertyChangeListener(listener);
		}
	}
	
	@Override
	public IResource getResource(){
		return resource;
	}
	
	@Override
	public boolean isDisposed() {
		return isDisposed;
	}
	
	@Override
	public void dispose() {
		isDisposed = true;
		firePropertyChange(PROP_DISPOSED, this, null);
	}
	
	public void setJobInProgress(boolean enable){
		this.jobInProgress = enable;
	}
	
	@Override
	public boolean isJobInProgress(){
		return jobInProgress;
	}
	
	@Override
	public boolean isCreateInProgress() {
		return createInProgress;
	}
	@Override
	public boolean isSaveInProgress() {
		return saveInProgress;
	}
	@Override
	public boolean isLoadInProgress() {
		return loadInProgress;
	}
	@Override
	public boolean isDeleteInProgress() {
		return deleteInProgress;
	}
	
	@Override
	public void setDirty(boolean value) {
		firePropertyChange(PROP_DIRTY, this.isDirty, this.isDirty = value);
	}
	
	@Override
	public boolean isDirty() {
		return isDirty;
	}
	
	@Override
	public boolean isLoaded() {
		return isLoaded;
	}
	
	@Override
	public final void create() {
		
		synchronized (saveLoadLock) {
			try{
				createInProgress = true;
				logger.info("Creating resource: " + resource.getFullPath());
				handleCreate();
			}
			finally{
				createInProgress = false;
			}
			
			save();
			isLoaded = true;
		}
		
	}
	
	@Override
	public final void save() {
		
		synchronized (saveLoadLock) {
			if(createInProgress){
				return;
			}
			
			try{
				saveInProgress = true;
				logger.info("Saving resource: " + resource.getFullPath());
				handleSave();
				setDirty(false);
			}
			finally{
				saveInProgress = false;
			}
		}
		
		firePropertyChange(PROP_SAVED, false, true);
	}
	
	@Override
	public final void load() {
		long time = System.currentTimeMillis();
		
		synchronized (saveLoadLock) {
			
			//skip load operation if it is triggered from save or create
			if(createInProgress || saveInProgress || deleteInProgress){
				return;
			}
			
			if(!resource.exists()){
				return;
			}
			
			IResource res = getResource();
			if(res == null){
				throw new IllegalStateException("Can't load resource. Root folder is NULL!");
			}
			
			if(!res.exists()){
				throw new IllegalStateException("Can't load resource. Root folder does not exist: " + res.getLocation().toString());
			}
			

			if(isDirty){
				// FIXME: possible ThreadLock - save is triggered from UI and load from another thread
				//boolean load = DialogUtils.openConfirm("Reload confirmation dialog", 
				//		"Resource "+ getName() +" has been modified without beeing saved. Do you want to override changes?");
				//if(!load){
				//	return;
				//}
				
				if (Display.getDefault().getThread() != Thread.currentThread())
				{
	                logger.warning("FIXME: Load requested from NonGUI thread, while state was dirty -- possible ThreadLock. Thread="+Thread.currentThread().getName());
	                return;
				}
			}
					
			try {
				loadInProgress = true;
				logger.info("Loading resource: " + resource.getFullPath());
				handleLoad();
				setDirty(false);
				isLoaded = true;
			}
			finally{
				loadInProgress = false;
			}
		}
		
		firePropertyChange(PROP_LOADED, false, true);
		System.out.println("LOAD TIME : "+ (System.currentTimeMillis()-time));
	}
	
	@Override
	public final void delete() {
		
		synchronized (saveLoadLock) {
			if(!resource.exists()){
				return;
			}
			
			try{
				deleteInProgress = true;
				logger.info("Deleting resource: " + resource.getFullPath());
				handleDelete();
			}
			finally{
				deleteInProgress = false;
			}
		}
		
		firePropertyChange(PROP_DELETED, false, true);
	}
	
	@Override
	public final String getID() {
		return validatorID;
	}
	
	@Override
	public IProject getProject() {
		return getResource().getProject();
	}
	
	@Override
	protected IValidationStatus createSelfStatus() {
		return new ResourceValidationStatus(this, getID(), getResource());
	}
	
	@Override
	public void validate() {
		if(!isLoaded()){
			load();
		}
		StatusManager.getInstance().validate(getProject(), this);
	}
	
	@Override
	protected void firePropertyChange(String name, Object oldValue, Object newValue) {
		
		//batch changes
		if(jobInProgress){
			return;
		}
		
		super.firePropertyChange(name, oldValue, newValue);
	}
}
