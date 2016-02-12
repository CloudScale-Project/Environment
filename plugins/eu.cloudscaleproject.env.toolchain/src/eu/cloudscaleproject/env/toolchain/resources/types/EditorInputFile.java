package eu.cloudscaleproject.env.toolchain.resources.types;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import eu.cloudscaleproject.env.common.dialogs.DialogUtils;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;

public class EditorInputFile extends EditorInputResource{
	
	private static final Logger logger = Logger.getLogger(EditorInputFile.class.getName());
		
	protected Properties source = new Properties();
	protected final IProject project;
	protected final IFile file;
			
	public EditorInputFile(IProject project, IFile file) {
		this(project, file, null);
		
		//preload
		if(file.exists()){
			try (InputStream is = file.getContents(true)) {
				source.loadFromXML(is);
			} catch (InvalidPropertiesFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (CoreException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public EditorInputFile(IProject project, IFile file, String validationID) {
		
		super(file, validationID);
		
		this.project = project;
		this.file = file;
		
		initializeValidationListener();
	}
	
	@Override
	public String getType() {
		return getProperty(ResourceProvider.PROP_TYPE);
	}
	
	@Override
	public IFile getResource(){
		return file;
	}
	
	@Override
	public IProject getProject(){
		return this.project;
	}
	
	public String getProperty(String key){
		synchronized (source) {
			return source.getProperty(key);
		}
	}
	
	public String[] getProperties(String key){
		synchronized (source) {
			String prop = source.getProperty(key);
			if(prop != null){
				return prop.split(",");
			}
			else{
				return new String[]{};
			}
		}
	}
	
	public String[] getKeys(){
		synchronized (source) {
			Set<Object> keys = source.keySet();
			return keys.toArray(new String[keys.size()]);
		}
	}
	
	/**
	 * Set property values under specified key, trigger the dirty state and notify listeners.
	 * Properties are persisted, when the resource is saved.
	 * 
	 * @param key Key that identifies the properties. 
	 * @param values Array of strings to store.
	 */
	public void setProperties(String key, String[] values){
		String[] old = null;
		synchronized (source) {
			old = getProperties(key);
			doSetProperties(key, values);
		}
		setDirty(true);
		firePropertyChange(key, old, values);
	}
	
	/**
	 * Set property values under specified key, without notifying listeners and triggering the dirty state.
	 * Properties are persisted, when the resource is saved.
	 * 
	 * @param key Key that identifies the properties. 
	 * @param values Array of strings to store.
	 */
	protected void doSetProperties(String key, String[] values){
		
		if(values == null){
			doSetProperty(key, null);
		}
		
		StringBuilder value = new StringBuilder();
		for(int i=0; i<values.length; i++){
			value.append(values[i]);
			if(i != values.length -1){
				value.append(",");
			}
		}
		
		doSetProperty(key, value.toString());
	}
	
	/**
	 * Set property value under specified key, trigger the dirty state and notify listeners.
	 * Property is persisted, when the resource is saved.
	 * 
	 * @param key Key that identifies the property. 
	 * @param value String value to store.
	 */
	public void setProperty(String key, String value){
		String old = null;
		synchronized (source) {
			old = getProperty(key);
			doSetProperty(key, value);
		}
		setDirty(true);
		firePropertyChange(key, old, value);
	}
	
	/**
	 * Set property value under specified key, without notifying listeners and triggering the dirty state.
	 * Property is persisted, when the resource is saved.
	 * 
	 * @param key Key that identifies the properties.
	 * @param values String value to store.
	 */
	protected void doSetProperty(String key, String value){
		synchronized (source) {
			if(value == null){
				source.remove(key);
			}
			else{
				source.setProperty(key, value);
			}
		}
	}
	
	/**
	 * Remove properties stored under specified key, trigger the dirty state and notify listeners.
	 * Properties are removed from the file system, when the resource is saved.
	 * 
	 * @param key Key that identifies the properties.
	 */
	public void removeProperty(String key){
		String old = null;
		synchronized (source) {
			old = source.getProperty(key);
			doRemoveProperty(key);
		}
		setDirty(true);
		firePropertyChange(key, old, null);
	}
	
	/**
	 * Remove properties stored under specified key, without notifying listeners and triggering the dirty state.
	 * Properties are removed from the file system, when the resource is saved.
	 * 
	 * @param key Key that identifies the properties.
	 */
	protected void doRemoveProperty(String key){
		synchronized (source) {
			source.remove(key);
		}
	}
	
	@Override
	public String getName(){
		String prop = getProperty(KEY_NAME);
		if(prop == null){
			return file.getName();
		}
		return prop;
	}
	
	@Override
	public void setName(String name){
		String old = getName();
		doSetProperty(KEY_NAME, name);
		
		setDirty(true);
		firePropertyChange(PROP_NAME, old, name);
	}
	
	@Override
	public String getPersistedStatus() {
		return getProperty(KEY_STATUS);
	}
	
	@Override
	protected void handleCreate(IProgressMonitor monitor) {
		
		if (!file.exists()) {
			doSetProperty(KEY_TIMESTAMP_CREATED, ""+System.currentTimeMillis());
			try (InputStream is = new ByteArrayInputStream(new byte[0])) {
				file.create(is, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	protected void handleSaveStatus(){
		if(hasStatusEntry(IValidationStatus.SEVERITY_ERROR)){
			doSetProperty(KEY_STATUS, VALUE_STATUS_ERROR);
		}
		else if(hasStatusEntry(IValidationStatus.SEVERITY_WARNING)){
			doSetProperty(KEY_STATUS, VALUE_STATUS_WARNING);
		}
		else{
			doRemoveProperty(KEY_STATUS);
		}
	}
	
	@Override
	protected void handleSave(IProgressMonitor monitor) {
		
		handleSaveStatus();
		
		try (OutputStream os = new FileOutputStream(new File(
				file.getLocationURI()))) {
			doSetProperty(KEY_TIMESTAMP_MODIFIED, ""+System.currentTimeMillis());
			synchronized (source) {
				source.storeToXML(os, "");
			}
			setDirty(false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			file.refreshLocal(IFile.DEPTH_ZERO, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void handleLoad(IProgressMonitor monitor) {

		try (InputStream is = file.getContents(true)) {
			synchronized (source) {
				source.loadFromXML(is);
			}
			setDirty(false);
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CoreException e1) {
			e1.printStackTrace();
		}		
	}
	
	@Override
	protected void handleDelete(IProgressMonitor monitor) {
		if (file.exists()) {
			try {
				file.delete(true, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public synchronized final void copyFrom(IResource file) {
		copyFrom(file, null);
	}

	public synchronized final void copyFrom(IResource file, IProgressMonitor monitor) {
		
		if(isDirty()){
			boolean load = DialogUtils.openConfirm("Reload confirmation dialog", 
					"Resource "+ getName() +" has been modified without beeing saved. Do you want to override changes?");
			if(!load){
				return;
			}
		}
		
		if(!(file instanceof IFile)){
			logger.severe("copyFrom("+ file.toString() +"): input attribute must be instance of IFile!");
			return;
		}
		
		if (!file.exists()) {
			return;
		}

		try (InputStream is = ((IFile)file).getContents(true)) {
			synchronized (source) {
				source.loadFromXML(is);
			}
			setDirty(false);

		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CoreException e1) {
			e1.printStackTrace();
		}
	}

}
