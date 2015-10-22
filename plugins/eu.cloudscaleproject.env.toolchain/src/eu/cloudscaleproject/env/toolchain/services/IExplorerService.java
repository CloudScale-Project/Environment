package eu.cloudscaleproject.env.toolchain.services;

public interface IExplorerService {

	public void setSelection(Object data);
	
	@Deprecated
	public void setSelection(String id, int index);
	
}
