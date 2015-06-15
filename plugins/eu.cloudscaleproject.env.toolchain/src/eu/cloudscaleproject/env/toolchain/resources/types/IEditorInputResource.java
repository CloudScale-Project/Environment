package eu.cloudscaleproject.env.toolchain.resources.types;

import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.common.interfaces.IProjectProvider;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;

public interface IEditorInputResource extends IEditorInput, IProjectProvider, IValidationStatusProvider{

	public final String KEY_NAME = "name";
	public final String KEY_TIMESTAMP_CREATED = "created";
	public final String KEY_TIMESTAMP_MODIFIED = "modified";

	public String getName();
	public IResource getResource();
	
	public String getType();
	
	public void create();
	
	public void save();
	public void load();
	public void delete();
	
	public boolean isLoaded();
	public boolean isDirty();
		
	public String getProperty(String key);
	public void setProperty(String key, String value);

	public void copyFrom(IResource file);
}
