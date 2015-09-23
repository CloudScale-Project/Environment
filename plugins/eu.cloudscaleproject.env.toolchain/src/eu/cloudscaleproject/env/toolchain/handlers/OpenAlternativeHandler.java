package eu.cloudscaleproject.env.toolchain.handlers;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.MethodStatusContext;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.editors.ProjectEditor;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;

@Deprecated
public class OpenAlternativeHandler
{
	private static final String EXTRACTOR_ID="eu.cloudscaleproject.env.extractor.tabitemextension";
	private static final String ANALYSER_ID="eu.cloudscaleproject.env.analyser.tabitemextension";
	private static final String STA_SPOTTER_ID="eu.cloudscaleproject.env.spotter.tabitemextension";
	private static final String DYN_SPOTTER_ID="eu.cloudscaleproject.env.staticspotter.tabitemextension";

	public static final String ACTION_OPEN_INTRO = "openIntro";
	public static final String ACTION_OPEN_INPUT = "openInput";
	public static final String ACTION_OPEN_RUN = "openRun";
	public static final String ACTION_OPEN_RESULTS = "openResults";

	private static final String USAGEEVOLUTION_ID="eu.cloudscaleproject.env.usageevolution.tabitemextension";
	private static final String OVERVIEW_ID="eu.cloudscaleproject.env.overview.tabitemextension";


	private enum WorkaroundMap 
	{
	EXTRACTOR_INPUT(CSTool.EXTRACTOR_INPUT, EXTRACTOR_ID, ACTION_OPEN_INPUT),
	EXTRACTOR_CONF(CSTool.EXTRACTOR_CONF, EXTRACTOR_ID, ACTION_OPEN_RUN),
	EXTRACTOR_RES(CSTool.EXTRACTOR_RES, EXTRACTOR_ID, ACTION_OPEN_RESULTS),

	ANALYSER_INPUT(CSTool.ANALYSER_INPUT, ANALYSER_ID, ACTION_OPEN_INPUT),
	ANALYSER_CONF(CSTool.ANALYSER_CONF, ANALYSER_ID, ACTION_OPEN_RUN),
	ANALYSER_RES(CSTool.ANALYSER_RES, ANALYSER_ID, ACTION_OPEN_RESULTS),

	SPOTTER_DYN_INPUT(CSTool.SPOTTER_DYN_INPUT, DYN_SPOTTER_ID, ACTION_OPEN_INPUT),
	SPOTTER_DYN_CONF(CSTool.SPOTTER_DYN_CONF, DYN_SPOTTER_ID, ACTION_OPEN_RUN),
	SPOTTER_DYN_RES(CSTool.SPOTTER_DYN_RES, DYN_SPOTTER_ID, ACTION_OPEN_RESULTS),
	
	SPOTTER_STA_INPUT(CSTool.SPOTTER_STA_INPUT, STA_SPOTTER_ID, ACTION_OPEN_INPUT),
	SPOTTER_STA_CONF(CSTool.SPOTTER_STA_CONF, STA_SPOTTER_ID, ACTION_OPEN_RUN),
	SPOTTER_STA_RES(CSTool.SPOTTER_STA_RES, STA_SPOTTER_ID, ACTION_OPEN_RESULTS),
	
	USAGEEVOLUTION(CSTool.USAGEEVOLUTION, USAGEEVOLUTION_ID, null),
	OVERVIEW(CSTool.OVERVIEW, OVERVIEW_ID, null),
	ARCHITECTURAL_TEMPLATES(CSTool.ARCHITECTURAL_TEMPLATES, null, null);
	
	
		private String extension, action;
		private CSTool tool;
		private WorkaroundMap(CSTool tool, String extension, String action)
		{
			this.tool = tool;
			this.extension = extension;
			this.action = action;
		}
		
		private static WorkaroundMap getById (String id)
		{
			for (WorkaroundMap wm : values())
			{
				if (wm.tool.getID().equals(id))
				{
					return wm;
				}
			}

			return null;
		}
	}

	@Execute
	public void execute(@Active IProject project, @Active MethodStatusContext context)
	{
		// TODO Your code goes here

		if (context.getValidationStatus() != null)
		{
			IValidationStatusProvider statusProvider = context.getValidationStatus().getProvider();

			if (statusProvider instanceof IEditorInputResource)
			{
				IEditorInputResource eir = (IEditorInputResource) statusProvider;
				OpenAlternativeUtil.openAlternative(eir);
			}
		} else
		{
			IFile file = ExplorerProjectPaths.getPropertyFile(project);

			IEditorInput editorInput = new FileEditorInput(file);
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

			try
			{
				IEditorPart part = IDE.openEditor(page, editorInput, "eu.cloudscaleproject.env.toolchain.tooleditor");

				if (part instanceof ProjectEditor)
				{
					WorkaroundMap wm = WorkaroundMap.getById(context.getId());
					ProjectEditor pe = (ProjectEditor) part;
					pe.openTab(wm.extension, wm.action);
				}
			} catch (PartInitException e)
			{
				e.printStackTrace();
			}
		}
	}

}