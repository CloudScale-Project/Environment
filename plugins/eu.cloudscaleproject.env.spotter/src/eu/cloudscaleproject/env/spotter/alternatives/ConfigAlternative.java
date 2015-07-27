package eu.cloudscaleproject.env.spotter.alternatives;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.part.EditorPart;
import org.spotter.eclipse.ui.Activator;
import org.spotter.eclipse.ui.UICoreException;
import org.spotter.eclipse.ui.editors.AbstractSpotterEditor;

import eu.cloudscaleproject.env.spotter.CustomDynamicSpotterRunJob;
import eu.cloudscaleproject.env.spotter.ResourceUtils;
import eu.cloudscaleproject.env.spotter.Util;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractConfigAlternative;

public class ConfigAlternative extends AbstractConfigAlternative
{

	public static String KEY_ENVIRONMENT_CONFIG = "environment_config";
	public static String KEY_SPOTTER_CONFIG = "config";
	public static String KEY_HIERARCHY_CONFIG = "hierarchy";

	private List<AbstractSpotterEditor> editors = new LinkedList<AbstractSpotterEditor>();
	
	final IPropertyListener listener = new IPropertyListener()
	{
		@Override
		public void propertyChanged(Object source, int propId)
		{
			if (EditorPart.PROP_DIRTY == propId)
			{
				setDirty(true);
			}
		}
	};

	public ConfigAlternative(IProject project, IFolder folder)
	{
		super(project, folder, null, CSTool.SPOTTER_DYN_CONF.getID(), ResourceRegistry.getInstance().getResourceProvider(project,
				CSTool.SPOTTER_DYN_INPUT.getID()), ResourceRegistry.getInstance().getResourceProvider(project,
				CSTool.SPOTTER_DYN_RES.getID()));
	}

	public void registerSpotterEditor(final AbstractSpotterEditor editor)
	{
		editor.addPropertyListener(listener);
		editors.add(editor);
	}

	public void unRegisterSpotterEditor(final AbstractSpotterEditor editor)
	{
		editors.remove(editor);
		editor.removePropertyListener(listener);
	}

	@Override
	protected void doSave(IProgressMonitor monitor)
	{
		super.doSave(monitor);
		
		for (AbstractSpotterEditor editor : editors)
		{
			editor.doSave(monitor);
		}
	}

	@Override
	protected void doLoad(IProgressMonitor monitor)
	{
		super.doLoad(monitor);
	}
	
	@Override
	protected void doCreate(IProgressMonitor monitor)
	{
		super.doCreate(monitor);
		initModels();
	}
	
	private void initModels ()
	{
		if (getSubResource(KEY_ENVIRONMENT_CONFIG) == null)
		{
			IFile file = getResource().getFile("mEnv.xml");
			ResourceUtils.createDefaultFile(file, ""
					+ "<measurementEnvironment xmlns=\"org.spotter.shared.environment.model\">" + "<workloadAdapter>"
					+ "<extensionName>workload.satellite.adapter.customized</extensionName>"
					+ "<config key=\"org.spotter.satellite.adapter.name\" value=\"Customized Workload Satellite Adapter\"/>"
					+ "<config key=\"org.spotter.workload.simple.userScriptClassName\" value=\"\"/>"
					+ "<config key=\"org.spotter.workload.simple.userScriptPath\" value=\"\"/>" + "</workloadAdapter>"
					+ "</measurementEnvironment>");

			setSubResource(KEY_ENVIRONMENT_CONFIG, file);
		}

		if (getSubResource(KEY_HIERARCHY_CONFIG) == null)
		{
			IFile file = getResource().getFile("hierarchy.xml");
			ResourceUtils.createDefaultFile(file, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
					+ "<root xmlns=\"http://www.sopeco.org/PerformanceProblemHierarchySchema\">" 
				    + "<uniqueId>3a105ebc-9390-4176-ba51-ce2f092092fb</uniqueId>"
				    + "<config key=\"org.spotter.detection.detectable\" value=\"false\"/>"
                    + "</root>");

			setSubResource(KEY_HIERARCHY_CONFIG, file);
		}

		if (getSubResource(KEY_SPOTTER_CONFIG) == null)
		{
				IFile file = getResource().getFile("spotter.conf");
				Properties confProp = new Properties();

				String envPath = getSubResource(KEY_ENVIRONMENT_CONFIG).getLocation().toString();
				String hierarchyPath = getSubResource(KEY_HIERARCHY_CONFIG).getLocation().toString();

				confProp.setProperty("org.spotter.measurement.environmentDescriptionFile", envPath);
				confProp.setProperty("org.spotter.conf.problemHierarchyFile", hierarchyPath);
				confProp.setProperty("org.spotter.resultDir", resultsResourceProvider.getRootFolder().getLocation().toString());

				confProp.setProperty("org.spotter.workload.maxusers", "10");
				confProp.setProperty("org.spotter.workload.experiment.duration", "180");
				confProp.setProperty("org.spotter.prewarmup.duration", "1");
				confProp.setProperty("org.spotter.workload.experiment.rampup.intervalLength", "1");
				confProp.setProperty("org.spotter.workload.experiment.rampup.numUsersPerInterval", "5");
				confProp.setProperty("org.spotter.workload.experiment.cooldown.intervalLength", "1");
				confProp.setProperty("org.spotter.workload.experiment.cooldown.numUsersPerInterval", "2");
				
				try
				{
					confProp.store(new FileOutputStream(file.getLocation().toFile()), hierarchyPath);
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				setSubResource(KEY_SPOTTER_CONFIG, file);
				
				save();
		}
	}

	@Override
	protected IStatus doRun(IProgressMonitor m) throws CoreException
	{
		InputAlternative selectedEditorInput = (InputAlternative) getInputAlternative();

		if (selectedEditorInput != null)
		{
			ResourceUtils.bindEditorInputs(selectedEditorInput, this);
			try
			{
				CustomDynamicSpotterRunJob job = Util.createJob(this);
				return job.run(m);
			} catch (UICoreException e)
			{
				e.printStackTrace();
				throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Error when preparign DS job.", e));
			}
		} else
		{
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Input not defined.");
		}
	}

}
