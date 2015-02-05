package eu.cloudscaleproject.env.spotter;

import java.util.HashMap;

import org.eclipse.core.resources.IProject;
import org.spotter.eclipse.ui.ServiceClientWrapper;

public class SpotterClientController
{
	
	private static HashMap<String, SpotterClientController> mapControllers = new HashMap<>();

	public static SpotterClientController getController(IProject project)
	{
		if (!mapControllers.containsKey(project.getName()))
		{
			SpotterClientController controller = new SpotterClientController(project);
			mapControllers.put(project.getName(), controller);
		}
		
		return mapControllers.get(project.getName());
	}

	private IProject project;
	
	private SpotterClientController(IProject project)
	{
		this.project = project;
	}
	
	private ServiceClientWrapper client;
	private boolean isConnected;
	public ServiceClientWrapper getClient ()
	{
		if (client == null)
		{
			client = org.spotter.eclipse.ui.Activator.getDefault().getClient(project.getName());
		}

		return client;
	}

	
	public boolean isConnected ()
	{
		return isConnected;
	}
	
	public boolean connect (String hostname, String port)
	{
        getClient().updateUrl(hostname, port);

        this.isConnected = getClient().testConnection(true);
        
        return this.isConnected;
	}

	public void disconnect ()
	{
		getClient().updateUrl("", "");
		this.isConnected = false;
	}
}
