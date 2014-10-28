package eu.cloudscaleproject.env.toolchain.resources.types;

import org.eclipse.core.resources.IResource;

public interface IEditorInputResource extends IEditorInput{

	public IResource getResource();
	
	public void save();
	public void load();
	public void delete();
	
	public String getProperty(String key);
	public void setProperty(String key, String value);

	public void copyFrom(IResource file);
}
