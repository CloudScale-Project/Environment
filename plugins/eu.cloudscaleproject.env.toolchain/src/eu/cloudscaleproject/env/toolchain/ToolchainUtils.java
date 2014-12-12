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
	public static final String EXTRACTOR_INPUT_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorInput";
	public static final String EXTRACTOR_CONF_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorConf";
	public static final String EXTRACTOR_RES_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes";

	public static final String ANALYSER_INPUT_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput";
	public static final String ANALYSER_CONF_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf";
	public static final String ANALYSER_RES_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserRes";

	public static final String SPOTTER_DYN_INPUT_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynInput";
	public static final String SPOTTER_DYN_CONF_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynConf";
	public static final String SPOTTER_DYN_RES_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynRes";
	
	public static final String SPOTTER_STA_INPUT_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaInput";
	public static final String SPOTTER_STA_CONF_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaConf";
	public static final String SPOTTER_STA_RES_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaRes";
	
	public static final String USAGEEVOLUTION_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.usageevolution";
	public static final String OVERVIEW_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.overview";
	public static final String ARCHITECTURAL_TEMPLATES_ID = "eu.cloudscaleproject.env.toolchain.ToolchainUtils.architecturaltemplates";
	
	// folder serources
	public static final String KEY_FOLDER_ANALYSER_INPUT_ALT = "path_analyser_alt";
	public static final String KEY_FOLDER_USAGEEVOLUTION_ALT = "path_usageevolution_alt";
	
	// file resources
	
	// Analyser input
	public static final String KEY_FILE_REPOSITORY		= "path_repository";
	public static final String KEY_FILE_RESOURCEENV		= "path_resourceenvironment";
	public static final String KEY_FILE_SYSTEM			= "path_system";
	public static final String KEY_FILE_USAGE			= "path_usage";
	public static final String KEY_FILE_ALLOCATION		= "path_allocation";
	
	// Analyser conf
	public static final String KEY_FILE_EXPERIMENTS 	= "path_experiments";
	public static final String KEY_FILE_PMS 			= "path_pms";
	public static final String KEY_FILE_VARIATIONS 		= "path_variations";
	public static final String KEY_FILE_MESURPOINTS 	= "path_pcmmeasuringpoint";
	public static final String KEY_FILE_SLO 			= "path_slo";
	
	// Usage evolution
	public static final String KEY_FILE_USAGEEVOLUTION	= "path_usageevolution";
	public static final String KEY_FILE_LIMBO 			= "path_limbo_model";
	
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
		IFolder extractorFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_EXTRACTOR);
		IFolder analyserFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_ANALYSER);
		IFolder spotterDynFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_DYNAMIC_SPOTTER);
		IFolder spotterStaFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_STATIC_SPOTTER);
		IFolder scaledlFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_SCALEDL);


		IFolder folder = null;
		
		//extractor
		if(EXTRACTOR_INPUT_ID.equals(id)){
			folder = extractorFolder.getFolder(
					ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_INPUT));
		}
		else if(EXTRACTOR_CONF_ID.equals(id)){
			folder = extractorFolder.getFolder(
					ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_CONFIGURATION));
		}
		else if(EXTRACTOR_RES_ID.equals(id)){
			folder = extractorFolder.getFolder(
					ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_RESULTS));
		}
		
		//analyzer
		else if(ANALYSER_INPUT_ID.equals(id)){
			folder = analyserFolder.getFolder(
					ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_INPUT));
		}
		else if(ANALYSER_CONF_ID.equals(id)){
			folder = analyserFolder.getFolder(
					ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_CONFIGURATION));
		}
		else if(ANALYSER_RES_ID.equals(id)){
			folder = analyserFolder.getFolder(
					ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_RESULTS));
		}
		
		//dynamic spotter
		else if(SPOTTER_DYN_INPUT_ID.equals(id)){
			folder = spotterDynFolder.getFolder(
					ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_INPUT));
		}
		else if(SPOTTER_DYN_CONF_ID.equals(id)){
			folder = spotterDynFolder.getFolder(
					ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_CONFIGURATION));
		}
		else if(SPOTTER_DYN_RES_ID.equals(id)){
			folder = spotterDynFolder.getFolder(
					ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_RESULTS));
		}
		
		//static spotter
		else if(SPOTTER_STA_INPUT_ID.equals(id)){
			folder = spotterStaFolder.getFolder(
					ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_INPUT));
		}
		else if(SPOTTER_STA_CONF_ID.equals(id)){
			folder = spotterStaFolder.getFolder(
					ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_CONFIGURATION));
		}
		else if(SPOTTER_STA_RES_ID.equals(id)){
			folder = spotterStaFolder.getFolder(
					ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_RESULTS));
		}
		
		//usage evolution
		else if(USAGEEVOLUTION_ID.equals(id)){
			folder = scaledlFolder.getFolder(
					ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_USAGE_EVOLUTION));
		}
		
		
		
		if(folder == null){
			throw new IllegalArgumentException("getToolchainFolder(project, id): ID is not valid: " + id);	
		}
		
		if(!folder.exists()){
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
