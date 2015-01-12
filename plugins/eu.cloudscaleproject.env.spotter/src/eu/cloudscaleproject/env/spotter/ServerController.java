package eu.cloudscaleproject.env.spotter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import org.eclipse.core.runtime.Platform;

import eu.cloudscaleproject.env.common.Unzip;

public class ServerController
{
	private static ServerController INSTANCE;

	public static ServerController getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new ServerController();
		}

		return INSTANCE;
	}

	private static String SERVER_ZIP_PACK = "resources/server/ds-server.zip";
	private static String SERVER_FOLDER = "ds-server";
	private static String SERVER_JAR = "ds-server.jar";

	private static Logger logger = Logger.getLogger(ServerHandler.class.getName());

	private File serverJar;
	private Process serverProcess;

	private LinkedList<String> outputBuffer = new LinkedList<String>();
	private StreamRedirectThread streamRedirectThread;

	private ServerController()
	{

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
		logger.info("Starting DynamicSpotter server...");

		if (serverJar == null)
		{
			initialize();
		}

		if (isServerRunning())
		{
			logger.info("DynamicSpotter Server is already running.");
			return;
		}

		String command = String.format("java -jar %s start", this.serverJar.getName());
		this.serverProcess = Runtime.getRuntime().exec(command, null, this.serverJar.getParentFile());

		streamRedirectThread = new StreamRedirectThread(serverProcess.getInputStream());
		streamRedirectThread.start();
	}
	
	public Process getServerProcess()
	{
		return serverProcess;
	}
	
	public Document getDocument()
	{
		if (streamRedirectThread != null)
			return streamRedirectThread.getDocument();
		
		return null;
	}

	private static class StreamRedirectThread extends Thread
	{
		private Document document = new PlainDocument();
		private InputStream inputStream;

		public StreamRedirectThread(InputStream is)
		{
			this.inputStream = is;
		}
		
		public Document getDocument()
		{
			return document;
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
					logger.info("[SERVER OUTPUT] " + line);
					try
					{
						document.insertString(document.getLength()-1, line, null);
					}
					catch (BadLocationException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}
}
