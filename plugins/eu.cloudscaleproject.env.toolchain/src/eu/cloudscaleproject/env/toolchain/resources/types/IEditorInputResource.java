package eu.cloudscaleproject.env.toolchain.resources.types;

import org.eclipse.core.resources.IResource;

public interface IEditorInputResource extends IEditorInput{

	public final String KEY_NAME = "name";
	public final String KEY_TIMESTAMP_CREATED = "created";
	public final String KEY_TIMESTAMP_MODIFIED = "modified";

	public IResource getResource();
	
	public String getType();
	
	public void save();
	public void load();
	public void delete();
	
	public boolean isDirty();
		
	public String getProperty(String key);
	public void setProperty(String key, String value);

	public void copyFrom(IResource file);
}
