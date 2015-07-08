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

import eu.cloudscaleproject.env.common.dialogs.DialogUtils;
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
	
	public String getProperty(String key){
		synchronized (source) {
			return source.getProperty(key);
		}
	}
	
	public String[] getKeys(){
		synchronized (source) {
			Set<Object> keys = source.keySet();
			return keys.toArray(new String[keys.size()]);
		}
	}
	
	public void setProperty(String key, String value){
		String old = null;
		synchronized (source) {
			old = getProperty(key);
			if (value == null) 
			{
				source.remove(key);
			}
			else
			{
				source.setProperty(key, value);
			}
		}
		
		setDirty(true);
		pcs.firePropertyChange(key, old, value);
	}
	
	public void removeProperty(String key){
		String old = source.getProperty(key);
		source.remove(key);
		setDirty(true);
		pcs.firePropertyChange(key, old, null);
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
		setProperty(KEY_NAME, name);
		
		setDirty(true);
		pcs.firePropertyChange(PROP_NAME, old, name);
	}
	
	protected void handleCreate() {
		
		if (!file.exists()) {
			setProperty(KEY_TIMESTAMP_CREATED, ""+System.currentTimeMillis());
			try (InputStream is = new ByteArrayInputStream(new byte[0])) {
				file.create(is, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	protected void handleSave() {
		
		try (OutputStream os = new FileOutputStream(new File(
				file.getLocationURI()))) {
			setProperty(KEY_TIMESTAMP_MODIFIED, ""+System.currentTimeMillis());
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
	
	protected void handleLoad() {

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
	
	protected void handleDelete() {
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
