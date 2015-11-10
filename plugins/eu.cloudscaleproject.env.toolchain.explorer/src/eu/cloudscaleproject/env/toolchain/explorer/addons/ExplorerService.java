package eu.cloudscaleproject.env.toolchain.explorer.addons;

import eu.cloudscaleproject.env.toolchain.explorer.Explorer;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.services.IExplorerService;

public class ExplorerService implements IExplorerService{

	@Override
	public void setSelection(final Object data) {
		IExplorerNode node = Explorer.getInstance().findNode(data);
		Explorer.getInstance().setSelection(node);		
	}
	
	@Override
	public Object getSelection()
	{
		throw new IllegalStateException();
	}
	
	@Override
	public boolean isAvailable(Object data)
	{
		IExplorerNode node = Explorer.getInstance().findNode(data);
		return node != null;
	}

}
