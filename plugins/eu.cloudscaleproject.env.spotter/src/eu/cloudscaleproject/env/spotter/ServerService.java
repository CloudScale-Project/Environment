package eu.cloudscaleproject.env.spotter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.lpe.common.util.LpeFileUtils;
import org.lpe.common.util.LpeStreamUtils;
import org.spotter.eclipse.ui.Activator;
import org.spotter.eclipse.ui.ServiceClientWrapper;
import org.spotter.eclipse.ui.UICoreException;
import org.spotter.eclipse.ui.jobs.DynamicSpotterRunJob;
import org.spotter.eclipse.ui.model.xml.HierarchyFactory;
import org.spotter.eclipse.ui.model.xml.MeasurementEnvironmentFactory;
import org.spotter.eclipse.ui.util.DialogUtils;
import org.spotter.eclipse.ui.util.SpotterProjectSupport;
import org.spotter.shared.configuration.FileManager;
import org.spotter.shared.configuration.JobDescription;
import org.spotter.shared.environment.model.XMeasurementEnvironment;
import org.spotter.shared.hierarchy.model.XPerformanceProblem;

import eu.cloudscaleproject.env.spotter.alternatives.ConfigAlternative;
import eu.cloudscaleproject.env.spotter.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class ServerService
{

	private static final Logger logger = Logger.getLogger(ServerService.class.getName());

	private static ServerService instance = null;

	public static ServerService getInstance()
	{
		if (instance == null)
		{
			instance = new ServerService();
		}
		return instance;
	}

	private static SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");

	public IStatus runSimulation(ConfigAlternative confAlternative)
	{

		// InputAlternative inputAlternative = (InputAlternative)
		// confAlternative.getInputAlternative();

		// prepare configuration for run
		/*
		 * IFile fileConf = (IFile)
		 * confAlternative.getSubResource(ConfigAlternative.KEY_SPOTTER_CONFIG);
		 * if(fileConf.exists()){ Properties confProp = new Properties();
		 * 
		 * try { confProp.load(fileConf.getContents());
		 * 
		 * IPath confEditorInputLocation =
		 * confAlternative.getResource().getLocation();
		 * 
		 * String hierarchyPath =
		 * confEditorInputLocation.append(FileManager.HIERARCHY_FILENAME
		 * ).toString(); String envPath =
		 * confEditorInputLocation.append(FileManager
		 * .ENVIRONMENT_FILENAME).toString();
		 * 
		 * ResourceProvider resultResourceProvider =
		 * ResourceRegistry.getInstance().getResourceProvider( project,
		 * ToolchainUtils.SPOTTER_DYN_RES_ID);
		 * 
		 * //ResultAlternative rif = (ResultAlternative)resultResourceProvider
		 * //.createNewResource(confAlternative.getName() + " " + sdf.format(new
		 * Date()), "");
		 * 
		 * //confProp.setProperty("org.spotter.conf.problemHierarchyFile",
		 * hierarchyPath); //confProp.setProperty(
		 * "org.spotter.measurement.environmentDescriptionFile", envPath);
		 * //confProp.setProperty("org.spotter.resultDir",
		 * rif.getResource().getLocation().toString());
		 * //confProp.setProperty("org.spotter.resultDir",
		 * resultResourceProvider.getRootFolder().getLocation().toString());
		 * 
		 * File f = fileConf.getLocation().toFile(); try(OutputStream os = new
		 * FileOutputStream(f)){ confProp.store(os, ""); }
		 * fileConf.refreshLocal(IResource.DEPTH_ZERO, null);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } }
		 */

		ServiceClientWrapper client = SpotterClientController.getController(confAlternative.getProject()).getClient();

		try
		{
			JobDescription jobDescription = createJobDescription(confAlternative);
			Long jobId = client.startDiagnosis(jobDescription);

			if (jobId != null && jobId != 0)
			{
				EnvironmentDSRunJob job = new EnvironmentDSRunJob(confAlternative, jobId);
				job.run(new NullProgressMonitor());
				return job.getResult();

			} else
			{
				String msg = String.format("Error occured during diagnosis: %s", "Could not retrieve a valid job id!");
				logger.warning(msg);
				return new Status(IStatus.ERROR, Activator.PLUGIN_ID, msg);
			}

		} catch (UICoreException e)
		{
			String message = "Unable to read and parse all configuration files!";
			DialogUtils.handleError(message, e);
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Error in run.", e);
		}
	}

	public JobDescription createJobDescription(EditorInputFolder confEditorInput) throws UICoreException
	{
		JobDescription jobDescription = new JobDescription();

		IFile spotterFile = confEditorInput.getResource().getFile(FileManager.SPOTTER_CONFIG_FILENAME);
		Properties dynamicSpotterConfig = SpotterProjectSupport.getSpotterConfig(spotterFile);
		jobDescription.setDynamicSpotterConfig(dynamicSpotterConfig);

		MeasurementEnvironmentFactory envFactory = MeasurementEnvironmentFactory.getInstance();
		String envFile = confEditorInput.getResource().getFile(FileManager.ENVIRONMENT_FILENAME).getLocation().toString();
		XMeasurementEnvironment measurementEnvironment = envFactory.parseXMLFile(envFile);
		jobDescription.setMeasurementEnvironment(measurementEnvironment);

		HierarchyFactory hierFactory = HierarchyFactory.getInstance();
		String hierFile = confEditorInput.getResource().getFile(FileManager.HIERARCHY_FILENAME).getLocation().toString();
		XPerformanceProblem hierarchy = hierFactory.parseHierarchyFile(hierFile);
		jobDescription.setHierarchy(hierarchy);

		return jobDescription;
	}

	private void fetchResults(ResultAlternative alternative, Long jobId)
	{

		ServiceClientWrapper client = SpotterClientController.getController(alternative.getProject()).getClient();

		FileOutputStream fos;
		try
		{
			InputStream resultsZipStream = client.requestResults(jobId.toString());
			if (resultsZipStream == null || resultsZipStream.available() == 0)
			{
				String msg = "Received empty input stream for job " + jobId + ", skipping job!";
				DialogUtils.openWarning(msg);
			}

			File zipFile = alternative.getResource().getFile("temp.zip").getLocation().toFile();
			zipFile.createNewFile();
			fos = new FileOutputStream(zipFile);
			LpeStreamUtils.pipe(resultsZipStream, fos);
			resultsZipStream.close();

			LpeFileUtils.unzip(zipFile, alternative.getResource().getLocation().toFile());
			zipFile.delete();

		} catch (Exception e)
		{
			String message = "Error while saving fetched results for job " + jobId + "!";
			DialogUtils.handleError(message, e);
			logger.severe(message);
			e.printStackTrace();
		}
	}

	private class EnvironmentDSRunJob extends DynamicSpotterRunJob
	{
		private ConfigAlternative alternative;
		private long jobId;
		private long timestamp;

		public EnvironmentDSRunJob(ConfigAlternative alternative, long jobId)
		{
			super(alternative.getProject(), jobId, System.currentTimeMillis());

			this.alternative = alternative;
			this.jobId = jobId;
			this.timestamp = System.currentTimeMillis();
			// TODO Auto-generated constructor stub
		}

		@Override
		public IStatus run(IProgressMonitor monitor)
		{
			IStatus status = super.run(monitor);

			if (status.isOK())
			{

				// Prepare result folder
				ResourceProvider resultResourceProvider = ResourceRegistry.getInstance().getResourceProvider(alternative.getProject(),
						ToolchainUtils.SPOTTER_DYN_RES_ID);
				
				ResultAlternative resultAlternative = (ResultAlternative) resultResourceProvider.createNewResource(alternative.getName()
						+ " " + sdf.format(new Date(timestamp)), "");
				
				System.out.println(resultAlternative.getName());

				// Fetch Spotter results
				fetchResults(resultAlternative, jobId);
			}
			return status;
		}

	}

}
