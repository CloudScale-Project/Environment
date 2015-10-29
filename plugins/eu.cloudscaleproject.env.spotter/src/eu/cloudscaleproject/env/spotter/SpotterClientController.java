package eu.cloudscaleproject.env.spotter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
	
	public boolean connect (String hostname, String port, boolean showErrorDialog)
	{
        getClient().updateUrl(hostname, port);

        boolean old = isConnected;
        this.isConnected = getClient().testConnection(showErrorDialog);
        
        this.pcs.firePropertyChange(PROP_CONNECTION, old, isConnected);
        
        return this.isConnected;
	}

	public boolean connect (String hostname, String port)
	{
		return connect(hostname, port, true);
	}

	public void disconnect ()
	{
		getClient().updateUrl("", "");
		this.isConnected = false;
        this.pcs.firePropertyChange(PROP_CONNECTION, !isConnected, isConnected);
	}

	PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	public static final String PROP_CONNECTION = "prop.connection";
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

	public void addPropertyChangeListener(String prop, PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(prop, listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(String prop, PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(prop, listener);
    }
}
