package eu.cloudscaleproject.env.spotter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.lpe.common.util.LpeFileUtils;
import org.lpe.common.util.LpeStreamUtils;
import org.spotter.eclipse.ui.ServiceClientWrapper;
import org.spotter.eclipse.ui.UICoreException;
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
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class Util
{
	private static final Logger logger = Logger.getLogger(Util.class.getName());
	
	public static CustomDynamicSpotterRunJob createJob(ConfigAlternative confAlternative) throws UICoreException
	{
		ServiceClientWrapper client = SpotterClientController.getController(confAlternative.getProject()).getClient();

		JobDescription jobDescription;
		jobDescription = createJobDescription(confAlternative);
		Long jobId = client.startDiagnosis(jobDescription);

		return new CustomDynamicSpotterRunJob(confAlternative, jobId);
	}

	public static JobDescription createJobDescription(EditorInputFolder confEditorInput) throws UICoreException
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

	public static void fetchResults(ResultAlternative alternative, Long jobId)
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
			alternative.getResource().refreshLocal(IResource.DEPTH_INFINITE, null);

		} catch (Exception e)
		{
			String message = "Error while saving fetched results for job " + jobId + "!";
			DialogUtils.handleError(message, e);
			logger.severe(message);
			e.printStackTrace();
		}
	}
}
