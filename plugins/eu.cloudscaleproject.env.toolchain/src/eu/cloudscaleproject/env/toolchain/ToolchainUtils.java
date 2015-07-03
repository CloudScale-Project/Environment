package eu.cloudscaleproject.env.toolchain;

import java.util.logging.Logger;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ToolchainUtils {
	
	private static final Logger logger = Logger.getLogger(ToolchainUtils.class.getName());

	// tool folders
	@Deprecated
	public static final String EXTRACTOR_INPUT_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorInput";
	@Deprecated
	public static final String EXTRACTOR_CONF_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorConf";
	@Deprecated
	public static final String EXTRACTOR_RES_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes";
	
	@Deprecated
	public static final String ANALYSER_INPUT_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput";
	@Deprecated
	public static final String ANALYSER_CONF_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf";
	@Deprecated
	public static final String ANALYSER_RES_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserRes";

	@Deprecated
	public static final String SPOTTER_DYN_INPUT_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynInput";
	@Deprecated
	public static final String SPOTTER_DYN_CONF_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynConf";
	@Deprecated
	public static final String SPOTTER_DYN_RES_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynRes";
	
	@Deprecated
	public static final String SPOTTER_STA_INPUT_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaInput";
	@Deprecated
	public static final String SPOTTER_STA_CONF_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaConf";
	@Deprecated
	public static final String SPOTTER_STA_RES_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaRes";
	
	@Deprecated
	public static final String USAGEEVOLUTION_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.usageevolution";
	@Deprecated
	public static final String OVERVIEW_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.overview";
	@Deprecated
	public static final String ARCHITECTURAL_TEMPLATES_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.architecturaltemplates";
	
	// folder serources
	public static final String KEY_INPUT_ALTERNATIVE = "input_alternative";
	public static final String KEY_CONFIG_ALTERNATIVE = "config_alternative";
	public static final String KEY_RESULT_ALTERNATIVE = "result_alternative";

	public static final String KEY_FOLDER_USAGEEVOLUTION_ALT = "path_usageevolution_alt";
	
	// file resources
	public static final String KEY_FILE_OVERVIEW		= "eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_overview";
	public static final String KEY_FILE_OVERVIEW_DIAGRAM		= "eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_overview_diagram";
	
	// Analyser input & Extractor results (repository&system)
	public static final String KEY_FILE_REPOSITORY		= "eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_repository";
	public static final String KEY_FILE_RESOURCEENV		= "eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_resourceenvironment";
	public static final String KEY_FILE_SYSTEM			= "eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_system";
	public static final String KEY_FILE_USAGE			= "eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_usage";
	public static final String KEY_FILE_AT				= "eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_at";
	public static final String KEY_FILE_ALLOCATION		= "eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_allocation";
	
	// Analyser conf
	public static final String KEY_FILE_EXPERIMENTS 	= "eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_experiments";
	public static final String KEY_FILE_MONITOR 		= "eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_pms";
	public static final String KEY_FILE_VARIATIONS 		= "eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_variations";
	public static final String KEY_FILE_MESURPOINTS 	= "eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_pcmmeasuringpoint";
	public static final String KEY_FILE_SLO 			= "eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_slo";
	
	// Usage evolution
	public static final String KEY_FILE_USAGEEVOLUTION	= "eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_usageevolution";
	public static final String KEY_FILE_LIMBO 			= "eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_limbo_model";

	// Extractor results 
	public static final String KEY_FILE_SOURCEDECORATOR = "path_sourcedecorator";
	
	
	public static IEditorInputResource getToolSelectedResource(IProject project, String toolID){
		IEditorInputResource selectedResource = null;
		ResourceProvider resourceProvider = ResourceRegistry.getInstance().
				getResourceProvider(project, toolID);
		
		if(resourceProvider == null){
			logger.warning("getSelectedResource(): Resource provider does not exist. Tool id: " + toolID);
			return null;
		}
		
		selectedResource = resourceProvider.getTaggedResource(ResourceProvider.TAG_SELECTED);
		
		if(selectedResource == null){
			selectedResource = resourceProvider.getResources().isEmpty() ? 
					null : resourceProvider.getResources().get(0);
			resourceProvider.tagResource(ResourceProvider.TAG_SELECTED, selectedResource);
		}
		
		return selectedResource;
	}
	
	public static IFolder getToolFolder(IProject project, String id){
				
		String inputFolderName = ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_INPUT, "Input");
		String configFolderName = ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_CONFIGURATION, "Configuration");
		String resultFolderName = ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_RESULTS, "Result");
		String usageEvFolderName = ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_USAGE_EVOLUTION, "Usage evolution");
		String overviewFolderName = ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_OVERVIEW, "Overview");

		IFolder folder = null;
				
		//extractor
		if(EXTRACTOR_INPUT_ID.equals(id)){
			IFolder extractorFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_EXTRACTOR);
			if(extractorFolder != null)
				folder = extractorFolder.getFolder(inputFolderName);
		}
		else if(EXTRACTOR_CONF_ID.equals(id)){
			IFolder extractorFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_EXTRACTOR);
			if(extractorFolder != null)
				folder = extractorFolder.getFolder(configFolderName);
		}
		else if(EXTRACTOR_RES_ID.equals(id)){
			IFolder extractorFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_EXTRACTOR);
			if(extractorFolder != null)
				folder = extractorFolder.getFolder(resultFolderName);
		}
		
		//analyzer
		else if(ANALYSER_INPUT_ID.equals(id)){
			IFolder analyserFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_ANALYSER);
			if(analyserFolder != null)
				folder = analyserFolder.getFolder(inputFolderName);
		}
		else if(ANALYSER_CONF_ID.equals(id)){
			IFolder analyserFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_ANALYSER);
			if(analyserFolder != null)
				folder = analyserFolder.getFolder(configFolderName);
		}
		else if(ANALYSER_RES_ID.equals(id)){
			IFolder analyserFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_ANALYSER);
			if(analyserFolder != null)
				folder = analyserFolder.getFolder(resultFolderName);
		}
		
		//dynamic spotter
		else if(SPOTTER_DYN_INPUT_ID.equals(id)){
			IFolder spotterDynFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_DYNAMIC_SPOTTER);
			if(spotterDynFolder != null)
				folder = spotterDynFolder.getFolder(inputFolderName);
		}
		else if(SPOTTER_DYN_CONF_ID.equals(id)){
			IFolder spotterDynFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_DYNAMIC_SPOTTER);
			if(spotterDynFolder != null)
				folder = spotterDynFolder.getFolder(configFolderName);
		}
		else if(SPOTTER_DYN_RES_ID.equals(id)){
			IFolder spotterDynFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_DYNAMIC_SPOTTER);
			if(spotterDynFolder != null)
				folder = spotterDynFolder.getFolder(resultFolderName);
		}
		
		//static spotter
		else if(SPOTTER_STA_INPUT_ID.equals(id)){
			IFolder spotterStaFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_STATIC_SPOTTER);
			if(spotterStaFolder != null)
				folder = spotterStaFolder.getFolder(inputFolderName);
		}
		else if(SPOTTER_STA_CONF_ID.equals(id)){
			IFolder spotterStaFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_STATIC_SPOTTER);
			if(spotterStaFolder != null)
				folder = spotterStaFolder.getFolder(configFolderName);
		}
		else if(SPOTTER_STA_RES_ID.equals(id)){
			IFolder spotterStaFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_STATIC_SPOTTER);
			if(spotterStaFolder != null)
				folder = spotterStaFolder.getFolder(resultFolderName);
		}
		
		//usage evolution
		else if(USAGEEVOLUTION_ID.equals(id)){
			IFolder scaledlFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_SCALEDL);
			if(scaledlFolder != null)
				folder = scaledlFolder.getFolder(usageEvFolderName);
		}
		else if(OVERVIEW_ID.equals(id)){
			IFolder scaledlFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_SCALEDL);
			if(scaledlFolder != null)
				folder = scaledlFolder.getFolder(overviewFolderName);
		}
		else{
			throw new IllegalArgumentException("getToolchainFolder(project, id): ID is not valid: " + id);
		}
		
		if(folder != null && !folder.exists()){
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return folder;
	}
}
