package eu.cloudscaleproject.env.toolchain.util;

public interface ISaveable {
	
	public void save();
	public void load(boolean force);
	public boolean isDirty();

}
