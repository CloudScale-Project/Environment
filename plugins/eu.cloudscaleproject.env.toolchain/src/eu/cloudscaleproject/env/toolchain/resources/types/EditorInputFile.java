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

import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;

public class EditorInputFile extends EditorInputResource{
	
	private static final Logger logger = Logger.getLogger(EditorInputFile.class.getName());
		
	protected Properties source = new Properties();
	protected final IProject project;
	protected final IFile file;
	
	private final String validationID;
	
	private boolean isLoaded = false;
	private boolean isDirty = false;
	
	public EditorInputFile(IProject project, IFile file) {
		this(project, file, null);
	}
	
	public EditorInputFile(IProject project, IFile file, String validationID) {
		this.project = project;
		this.file = file;
		this.validationID = validationID;
		
		if(file.exists()){
			load();
		}
	}
	
	@Override
	public String getID() {
		return validationID;
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
	public synchronized  String getName(){
		String prop = source.getProperty(KEY_NAME);
		if(prop == null){
			return file.getName();
		}
		return prop;
	}
	
	@Override
	public synchronized  void setName(String name){
		String old = getName();
		source.setProperty(KEY_NAME, name);
		isDirty = true;
		pcs.firePropertyChange(KEY_NAME, old, name);
	}
	
	public synchronized String getProperty(String key){
		return source.getProperty(key);
	}
	
	public synchronized String[] getKeys(){
		Set<Object> keys = source.keySet();
		return keys.toArray(new String[keys.size()]);
	}
	
	public synchronized  void setProperty(String key, String value){
		String old = getProperty(key);
		if (value == null) 
		{
			source.remove(key);
		}
		else
		{
			source.setProperty(key, value);
		}
		
		isDirty = true;
		pcs.firePropertyChange(key, old, value);
	}
	
	@Override
	public synchronized final void save() {
		
		if(!isDirty){
			return;
		}

		if (!file.exists()) {
			source.setProperty(KEY_TIMESTAMP_CREATED, ""+System.currentTimeMillis());
			try (InputStream is = new ByteArrayInputStream(new byte[0])) {
				file.create(is, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		try (OutputStream os = new FileOutputStream(new File(
				file.getLocationURI()))) {
			source.setProperty(KEY_TIMESTAMP_MODIFIED, ""+System.currentTimeMillis());
			source.storeToXML(os, "");
			isDirty = false;
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
	public synchronized  final void copyFrom(IResource file) {

		if(isDirty){
			logger.info("copyFrom(): EditorInputFile has unsaved changes! Resource: " + getResource().getFullPath());
		}
		
		if(!(file instanceof IFile)){
			logger.severe("copyFrom("+ file.toString() +"): input attribute must be instance of IFile!");
			return;
		}
		
		if (!file.exists()) {
			return;
		}

		try (InputStream is = ((IFile)file).getContents(true)) {
			source.loadFromXML(is);
			isDirty = false;

		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CoreException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public synchronized  final void load() {
		
		if(isDirty){
			logger.info("load(): EditorInputFile has unsaved changes! Resource: " + getResource().getFullPath());
		}

		if (!file.exists()) {
			logger.warning("File resource does not exist, Can not load properties!");
			return;
		}

		try (InputStream is = file.getContents(true)) {
			source.loadFromXML(is);
			isDirty = false;

		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CoreException e1) {
			e1.printStackTrace();
		}
		
		isLoaded = true;
	}
	
	@Override
	public boolean isLoaded() {
		return isLoaded;
	}
	
	@Override
	public boolean isDirty() {
		return isDirty;
	}

	@Override
	public synchronized  void delete() {
		if (file.exists()) {
			try {
				file.delete(true, null);
				isDirty = true;
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
