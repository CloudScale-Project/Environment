package eu.cloudscaleproject.env.toolchain.services;

public interface IExplorerService {

	public void setSelection(Object data);
	public Object getSelection();
	public boolean isAvailable (Object data);
}
