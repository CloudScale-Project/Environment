package eu.cloudscaleproject.env.spotter;

import java.io.IOException;

import org.eclipse.e4.core.di.annotations.Execute;

public class ServerHandler
{
	@Execute
	public void execute() throws IOException
	{
		if (ServerController.getInstance().isServerRunning())
			ServerController.getInstance().stopServer();
		else
			ServerController.getInstance().startServer();
	}

}