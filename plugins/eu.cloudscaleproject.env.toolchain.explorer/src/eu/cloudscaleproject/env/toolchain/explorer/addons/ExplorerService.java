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
	public void setSelection(String id, int index) {
		// TODO Auto-generated method stub
	}

}
