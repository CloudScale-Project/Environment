package eu.cloudscaleproject.env.toolchain;

import java.nio.channels.IllegalSelectorException;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MStackElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ToolchainUtils {
	
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ToolchainUtils.class.getName());
	
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
	public static final String KEY_FILE_VARIATION 			= "eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_variation";

	// Usage evolution
	public static final String KEY_FILE_USAGEEVOLUTION	= "eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_usageevolution";
	public static final String KEY_FILE_LIMBO 			= "eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_limbo_model";

	// Extractor results 
	public static final String KEY_FILE_SOURCEDECORATOR = "path_sourcedecorator";
		
	public static HashSet<IEditorInputResource> getOpenedAlternatives(){
		
		EModelService modelService = CloudscaleContext.getGlobalContext().get(EModelService.class);
		MApplication app = CloudscaleContext.getGlobalContext().get(MApplication.class);

		if(modelService == null){
			throw new IllegalSelectorException();
		}
		if(app == null){
			throw new IllegalSelectorException();
		}
		
		HashSet<IEditorInputResource> out = new HashSet<IEditorInputResource>();
		MPartStack stack = (MPartStack)modelService.find("org.eclipse.e4.primaryDataStack", app);
		
		for(MStackElement el : stack.getChildren()){
			if(el instanceof MPart){
				MPart part = (MPart)el;
				if(part.getContext() != null){
					IEditorInputResource alternative = part.getContext().get(IEditorInputResource.class);
					if(alternative != null){
						out.add(alternative);
					}
				}
			}
		}
		return out;
	}
	
	public static IFile getEMFDiagramFileFromModel(IFile modelFile){
		
		String fileName = ExplorerProjectPaths.removeFileExtension(modelFile.getName());
		String fileExtension = modelFile.getFileExtension() + "_diagram";

		List<IFile> files = ExplorerProjectPaths.findFiles(modelFile.getParent(), fileName, fileExtension, true);
		IFile diagramFile = files.isEmpty() ? null : files.get(0);
		return diagramFile;
	}
	
	public static IFolder getToolFolder(IProject project, String id){
		
		IConfigurationElement toolElement = ToolchainExtensions.getInstance().getToolElement(id);
		String toolName = toolElement.getAttribute("name");

		toolName = ExplorerProjectPaths.getProjectProperty(project, id, toolName);
		
		IFolder folder = project.getFolder(toolName);
		
		if(!folder.exists()){
			try {
				ExplorerProjectPaths.prepareFolder(folder);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		return folder;
	}
	
	public static IFolder getResourceProviderFolder(IProject project, String id){
		
		IConfigurationElement resourceElement = ToolchainExtensions.getInstance().getToolChildElement(id);
		String resourceName = resourceElement.getAttribute("name");
		
		if(resourceName == null || resourceName.isEmpty()){
			throw new IllegalArgumentException(
					"Tool resource extension point name is empty! Tool resource extension ID: " + resourceElement.getAttribute("id")); 
		}
		
		resourceName = ExplorerProjectPaths.getProjectProperty(project, id, resourceName);

		IFolder toolFolder = getToolFolder(project, ((IConfigurationElement)resourceElement.getParent()).getAttribute("id"));
		IFolder folder = toolFolder.getFolder(resourceName);
		
		if(!folder.exists()){
			try {
				ExplorerProjectPaths.prepareFolder(folder);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		return folder;
	}
	
}
