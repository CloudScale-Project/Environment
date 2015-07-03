package eu.cloudscaleproject.env.spotter.alternatives;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.part.EditorPart;
import org.spotter.eclipse.ui.editors.AbstractSpotterEditor;

import eu.cloudscaleproject.env.spotter.ResourceUtils;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;

public class InputAlternative extends EditorInputEMF
{
	public static String KEY_ENVIRONMENT_CONFIG = "environment_config";
	
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

	public InputAlternative(IProject project, IFolder folder)
	{
		super(project, folder, null, ToolchainUtils.SPOTTER_DYN_INPUT_ID);
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
	protected void doSave()
	{
		super.doSave();
		
		for (AbstractSpotterEditor editor : editors)
		{
			editor.doSave(null);
		}
	}

	@Override
	protected void doCreate()
	{
		super.doCreate();
		initModels();
	}

	private void initModels()
	{
		if (getSubResource(KEY_ENVIRONMENT_CONFIG) == null)
		{
			IFile file = getResource().getFile("mEnv.xml");
			ResourceUtils.createDefaultFile(file, "" + "<measurementEnvironment xmlns=\"org.spotter.shared.environment.model\"/>");

			setSubResource(KEY_ENVIRONMENT_CONFIG, file);
		}
	}

}
