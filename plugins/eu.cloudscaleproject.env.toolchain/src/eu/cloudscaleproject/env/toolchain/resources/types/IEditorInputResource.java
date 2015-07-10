package eu.cloudscaleproject.env.toolchain.resources.types;

import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.common.interfaces.IProjectProvider;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;

public interface IEditorInputResource extends IEditorInput, IProjectProvider, IValidationStatusProvider{

	public final String KEY_NAME = "name";
	public final String KEY_TIMESTAMP_CREATED = "created";
	public final String KEY_TIMESTAMP_MODIFIED = "modified";
	
	public static final String PROP_SAVED = "eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource.propSaved";
	public static final String PROP_LOADED = "eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource.propLoaded";
	public static final String PROP_DELETED = "eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource.propDeleted";
	public static final String PROP_DIRTY = "eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource.propDirty";
	
	public String getName();
	public IResource getResource();
	
	public String getType();
	
	public void create();
	public void save();
	public void load();
	public void delete();
	
	public boolean isCreateInProgress();
	public boolean isSaveInProgress();
	public boolean isLoadInProgress();
	public boolean isDeleteInProgress();
	
	public boolean isJobInProgress();
	
	public boolean isLoaded();
	
	public boolean isDirty();
	public void setDirty(boolean value);
		
	public String getProperty(String key);
	public void setProperty(String key, String value);

	public void copyFrom(IResource file);
}
