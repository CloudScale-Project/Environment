package eu.cloudscaleproject.env.spotter.alternatives;

import java.io.FileOutputStream;
import java.io.InputStream;
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
import org.lpe.common.config.ConfigParameterDescription;
import org.spotter.eclipse.ui.Activator;
import org.spotter.eclipse.ui.UICoreException;
import org.spotter.eclipse.ui.editors.AbstractSpotterEditor;
import org.spotter.eclipse.ui.model.xml.MeasurementEnvironmentFactory;
import org.spotter.shared.configuration.ConfigKeys;
import org.spotter.shared.environment.model.XMeasurementEnvironment;
import org.spotter.shared.hierarchy.model.RawHierarchyFactory;
import org.spotter.shared.hierarchy.model.XPerformanceProblem;
import org.spotter.shared.util.JAXBUtil;

import eu.cloudscaleproject.env.spotter.CustomDynamicSpotterRunJob;
import eu.cloudscaleproject.env.spotter.ResourceUtils;
import eu.cloudscaleproject.env.spotter.Util;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractConfigAlternative;

public class ConfigAlternative extends AbstractConfigAlternative
{

	public static final String PLUGIN_FILE_ENVIRONMENT_CONFIG = "resources/alternative/mEnv.xml";
	public static final String PLUGIN_FILE_HIERARCHY_CONFIG = "resources/alternative/hierarchy.xml";
	public static final String PLUGIN_FILE_SPOTTER_CONFIG = "resources/alternative/spotter.conf";

	public static String KEY_ENVIRONMENT_CONFIG = "environment_config";
	public static String KEY_SPOTTER_CONFIG = "config";
	public static String KEY_HIERARCHY_CONFIG = "hierarchy";

	private List<AbstractSpotterEditor> editors = new LinkedList<AbstractSpotterEditor>();

	final IPropertyListener listener = new IPropertyListener()
	{
		@Override
		public void propertyChanged(Object source, int propId)
		{
			if (EditorPart.PROP_DIRTY == propId) {
				setDirty(true);
			}
		}
	};

	public ConfigAlternative(IProject project, IFolder folder)
	{
		super(project, folder, null, CSTool.SPOTTER_DYN);
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

		for (AbstractSpotterEditor editor : editors) {
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
		//initModels();

		try {
			IFile environment = getResource().getFile("mEnv.xml");
			if (!environment.exists()) {
				MeasurementEnvironmentFactory factory = MeasurementEnvironmentFactory.getInstance();
				XMeasurementEnvironment defaultEnvironment = factory.createMeasurementEnvironment();
				InputStream in = JAXBUtil.createInputStreamFromElement(defaultEnvironment);
				environment.create(in, false, null);
				in.close();
			}

			IFile hierarchy = getResource().getFile("hierarchy.xml");
			if (!hierarchy.exists()) {
				XPerformanceProblem defaultHierarchy = RawHierarchyFactory.getInstance().createProblemHierarchyRoot();
				InputStream in = JAXBUtil.createInputStreamFromElement(defaultHierarchy);
				hierarchy.create(in, false, null);
				in.close();
			}

			IFile spotter = getResource().getFile("spotter.conf");
			if (!spotter.exists()) {
				Properties confProp = new Properties();
				
				for (ConfigParameterDescription cpd : ConfigKeys.getSpotterConfigParamters())
				{
					if (cpd.isMandatory()) 
					{
						if (cpd.getDefaultValue() == null)
							confProp.put(cpd.getName(), "");
						else
							confProp.put(cpd.getName(), cpd.getDefaultValue());
					}
				}

				String envPath = environment.getLocation().toString();
				String hierarchyPath = hierarchy.getLocation().toString();

				confProp.setProperty("org.spotter.measurement.environmentDescriptionFile", envPath);
				confProp.setProperty("org.spotter.conf.problemHierarchyFile", hierarchyPath);
				confProp.setProperty("org.spotter.resultDir", getResultResourceProvider().getRootFolder().getLocation().toString());


				confProp.store(new FileOutputStream(spotter.getLocation().toFile()), "");
			}


			setSubResource(KEY_ENVIRONMENT_CONFIG, environment);
			setSubResource(KEY_HIERARCHY_CONFIG, hierarchy);
			setSubResource(KEY_SPOTTER_CONFIG, spotter);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected IStatus doRun(IProgressMonitor m) throws CoreException
	{
		InputAlternative selectedEditorInput = (InputAlternative) getInputAlternative();

		if (selectedEditorInput != null) {
			ResourceUtils.bindEditorInputs(selectedEditorInput, this);
			try {
				CustomDynamicSpotterRunJob job = Util.createJob(this);
				return job.run(m);
			} catch (UICoreException e) {
				e.printStackTrace();
				throw new CoreException(
						new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Error when preparign DS job.", e));
			}
		} else {
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Input not defined.");
		}
	}

}
