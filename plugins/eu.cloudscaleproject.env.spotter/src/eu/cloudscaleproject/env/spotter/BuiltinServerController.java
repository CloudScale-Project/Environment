package eu.cloudscaleproject.env.spotter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Platform;

import eu.cloudscaleproject.env.common.Unzip;

public class BuiltinServerController
{
	private static HashMap<String, BuiltinServerController> mapControllers = new HashMap<>();
	public static BuiltinServerController getInstance(IProject project)
	{
		if (!mapControllers.containsKey(project.getName()))
		{
			BuiltinServerController controller = new BuiltinServerController(project);
			mapControllers.put(project.getName(), controller);
		}
		
		return mapControllers.get(project.getName());
	}


	private IProject project;

	private static String SERVER_ZIP_PACK = "resources/server/ds-server.zip";
	private static String SERVER_FOLDER = "ds-server";
	private static String SERVER_JAR = "ds-server.jar";

	private Logger logger = Logger.getLogger(BuiltinServerController.class.getName());

	private File serverJar;
	private Process serverProcess;

	private LinkedList<String> outputBuffer = new LinkedList<String>();
	private StreamRedirectThread streamRedirectThread;
	private Document logDocument = new PlainDocument();
	
	private int serverPort = 8080;

	private BuiltinServerController(IProject project)
	{
		this.project = project;

		logger.addHandler(new Handler()
		{
			@Override
			public void publish(LogRecord record)
			{
				serverLog(record.getLevel(), record.getMessage());
			}
			
			@Override public void flush() { }
			@Override public void close() throws SecurityException { }
		});
	}
	
	public IProject getProject()
	{
		return project;
	}

	public List<String> getServerOutput()
	{
		return Collections.unmodifiableList(this.outputBuffer);
	}

	public void initialize() throws IOException
	{
		if (serverJar != null)
		{
			return;
		}

		serverJar = getServerJar();

		if (serverJar == null)
		{
			logger.warning("Server initialization failed.");
			return;
		}
		
		logger.info("Builtin server persisted in :: "+serverJar.getPath());

		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			public void run()
			{
				if (isServerRunning())
					stopServer();
			}
		});
	}

	private File getServerJar()
	{
		File workspaceFolder = Platform.getLocation().toFile();
		File serverFolder = new File(workspaceFolder, SERVER_FOLDER);
		File serverJar = new File(serverFolder, SERVER_JAR);

		if (!serverJar.exists())
		{
			try
			{
				logger.info("Unpacking DynamicSpotter server to > " + serverFolder.getPath());
				// Unpack server
				InputStream is = getClass().getClassLoader().getResourceAsStream(SERVER_ZIP_PACK);
				Unzip.unZip(is, serverFolder.getPath());
			}
			catch (IOException e)
			{
				logger.severe("Failed to unpack DynamicSpotter server : " + e.getMessage());
				e.printStackTrace();
				return null;
			}
		}

		return serverJar;
	}
	
	public int getServerPort()
	{
		return serverPort;
	}

	public void setServerPort(int serverPort)
	{
		if (isServerRunning())
		{
			throw new IllegalStateException("Can't set server port - server is aalready running");
		}

		this.serverPort = serverPort;
	}

	public void stopServer()
	{
		logger.info("Stopping DynamicSpotter server...");
		if (this.serverProcess == null)
			return;

		if (isServerRunning())
		{
			this.serverProcess.destroy();
			this.serverProcess = null;
		}
	}

	public boolean isServerRunning()
	{
		if (serverProcess == null)
			return false;

		try
		{
			serverProcess.exitValue();
			return false;
		}
		catch (Exception e)
		{
			return true;
		}
	}
	
	public void startServer() throws IOException
	{
		startServer (8080);
		
	}
	public void startServer(int port) throws IOException
	{
		logger.info("Starting DynamicSpotter server (port="+port+")...");

		if (isServerRunning())
		{
			logger.info("DynamicSpotter Server is already running.");
			throw new IllegalStateException("Server is already running.");
		}

		if (serverJar == null)
		{
			initialize();
		}


		String command = String.format("java -jar %s start port=%s", this.serverJar.getName(), port);
		this.serverProcess = Runtime.getRuntime().exec(command, null, this.serverJar.getParentFile());
		this.serverPort = port;

		streamRedirectThread = new StreamRedirectThread(serverProcess.getInputStream(), logger);
		streamRedirectThread.start();
	}
	
	public Process getServerProcess()
	{
		return serverProcess;
	}
	
	public Document getDocument()
	{
		return logDocument;
	}
	
	private void serverLog (Level level, String log)
	{
		try
		{
			logDocument.insertString(logDocument.getLength(), log + "\n", null);
		}
		catch (BadLocationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private static class StreamRedirectThread extends Thread
	{
		private InputStream inputStream;
		private Logger logger;

		public StreamRedirectThread(InputStream is, Logger logger)
		{
			this.logger = logger;
			this.inputStream = is;
		}

		@Override
		public void run()
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			try
			{
				String line;
				while ((line = reader.readLine()) != null)
				{
					Level level = Level.INFO;
					if (line.contains(" WARN "))
						level = Level.WARNING;
					else if (line.contains(" SEVERE "))
						level = Level.SEVERE;
					
					logger.log(level, "[SERVER OUTPUT] " + line);
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}
}
