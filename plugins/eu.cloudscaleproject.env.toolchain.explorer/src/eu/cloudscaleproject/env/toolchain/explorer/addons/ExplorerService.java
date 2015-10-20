package eu.cloudscaleproject.env.toolchain.explorer.addons;

import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.BatchExecutor;
import eu.cloudscaleproject.env.toolchain.explorer.Explorer;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.services.IExplorerService;

public class ExplorerService implements IExplorerService{

	@Override
	public void setSelection(final Object data) {
		Explorer.getInstance().refreshAll();

		BatchExecutor.getInstance().addUITask(this, data, new Runnable()
		{
			@Override
			public void run()
			{
				
				Display.getDefault().asyncExec(new Runnable()
				{
					
					@Override
					public void run()
					{
				IExplorerNode node = Explorer.getInstance().findNode(data);
						// TODO Auto-generated method stub
				Explorer.getInstance().setSelection(node);
						
					}
				});
			}
		});
	}


	@Override
	public void setSelection(String id, int index) {
		// TODO Auto-generated method stub
	}

}
