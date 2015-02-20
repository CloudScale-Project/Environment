package eu.cloudscaleproject.env.toolchain.util;

public interface ISaveableComposite {
	
	public void save();
	public void load();
	public boolean isDirty();

}
