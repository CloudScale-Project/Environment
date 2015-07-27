package eu.cloudscaleproject.env.toolchain.util;

import org.eclipse.core.runtime.IProgressMonitor;

public interface ISaveable {
	
	public void save(IProgressMonitor monitor);
	
	public void load(IProgressMonitor monitor, boolean force);
	public void unload(IProgressMonitor monitor);

	public boolean isDirty();

}
