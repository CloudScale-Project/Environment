package eu.cloudscaleproject.env.product;

import java.util.HashMap;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import eu.cloudscaleproject.env.common.CloudScaleConstants;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

public class ExplorerDecorator extends LabelProvider implements
		ILabelDecorator {

	private static final String PLUGIN_ID = "eu.cloudscaleproject.env.product";
	private static final String ICON_PATH = "icons/explorer/";
	
	private static final String PROJECT_ICON = ICON_PATH + "project.png";
	private static final String GENERATED_ICON = ICON_PATH + "generated_models.png";
	private static final String SCALEDL_ICON = ICON_PATH + "scaledl_models.png";
	private static final String IMPORTED_ICON = ICON_PATH + "imported_models.png";

	private static final String INPUT_ICON = ICON_PATH + "input.png";
	private static final String CONFIGURATION_ICON = ICON_PATH + "configuration.png";
	private static final String RESULTS_ICON = ICON_PATH + "results.png";

	private static final String ANALYSER_ICON = ICON_PATH + "analyser.png";
	private static final String EXTRACTOR_ICON = ICON_PATH + "extractor.png";
	private static final String DYNAMIC_SPOTTER_ICON = ICON_PATH + "static-spotter.png";
	private static final String STATIC_SPOTTER_ICON = ICON_PATH + "dynamic-spotter.png";
	
	private static final String PROPERTIES_ICON = ICON_PATH + "projectProperties.png";

	
	@SuppressWarnings("unused") // How to detect if folder is expanded??
	private static final String FOLDER_CLOSE_ICON = ICON_PATH + "folder_close.png";
	private static final String FOLDER_OPEN_ICON = ICON_PATH + "folder_open.png";
	
	private HashMap<String, Properties> mapPropertiesFile = new HashMap<String, Properties>();
	
	@Override
	public Image decorateImage(Image image, Object element) {
		
		if (!(element instanceof IResource)) return image;
		
		IProject p = ((IResource)element).getProject();
		
		if (isCSEProject(p))
		{
			if (element instanceof IProject)
			{
				return getIcon(PROJECT_ICON);
			}
			if (element instanceof IFolder)
			{
				IFolder f = (IFolder) element;
				return getFolderImage(f);
			}
			if(element instanceof IFile){
				IFile f = (IFile) element;
				return getFileImage(f, image);
			}
		}
		
		return image;
	}
	
	@Override
	public String decorateText(String text, Object element) {
		if (element instanceof IProject)
		{
			if (isCSEProject((IProject) element))
			{
				return text+" [CloudScale Project]";
			}
		}
		
		return null;
	}
	
	private boolean isCSEProject (IProject p)
	{
		try {
			if(p.isOpen()){
				return p.getDescription().hasNature(CloudScaleConstants.PROJECT_NATURE_ID);
			}
			else{
				return false;
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private Image getFileImage(IFile f, Image image){
		if (f.getName().endsWith(".cse"))
		{
			return getIcon(PROPERTIES_ICON);
		}
		
		return image;
	}
	
	private Image getFolderImage (IFolder f)
	{
		Properties prop = getProjectProperties(f.getProject());
		if (prop == null) return null;
		
		if (f.getName().equals(prop.getProperty(ExplorerProjectPaths.KEY_FOLDER_GENERATED)))
		{
			return getIcon(GENERATED_ICON);
		}
		else if (f.getName().equals(prop.getProperty(ExplorerProjectPaths.KEY_FOLDER_IMPORT)))
		{
			return getIcon(IMPORTED_ICON);
		}

		else if (f.getName().equals(prop.getProperty(ExplorerProjectPaths.KEY_FOLDER_SCALEDL)))
		{
			return getIcon(SCALEDL_ICON);
		}

		else if (f.getName().equals(prop.getProperty(ExplorerProjectPaths.KEY_FOLDER_ANALYSER)))
		{
			return getIcon(ANALYSER_ICON);
		}
		else if (f.getName().equals(prop.getProperty(ExplorerProjectPaths.KEY_FOLDER_EXTRACTOR)))
		{
			return getIcon(EXTRACTOR_ICON);
		}
		else if (f.getName().equals(prop.getProperty(ExplorerProjectPaths.KEY_FOLDER_STATIC_SPOTTER)))
		{
			return getIcon(STATIC_SPOTTER_ICON);
		}
		else if (f.getName().equals(prop.getProperty(ExplorerProjectPaths.KEY_FOLDER_DYNAMIC_SPOTTER)))
		{
			return getIcon(DYNAMIC_SPOTTER_ICON);
		}

		else if (f.getName().equals(prop.getProperty(ExplorerProjectPaths.KEY_FOLDER_INPUT)))
		{
			return getIcon(INPUT_ICON);
		}
		else if (f.getName().equals(prop.getProperty(ExplorerProjectPaths.KEY_FOLDER_CONFIGURATION)))
		{
			return getIcon(CONFIGURATION_ICON);
		}
		else if (f.getName().equals(prop.getProperty(ExplorerProjectPaths.KEY_FOLDER_RESULTS)))
		{
			return getIcon(RESULTS_ICON);
		}

		else if (f.getName().endsWith(".cse"))
		{
			return getIcon(PROPERTIES_ICON);
		}
		else
		{
			return getIcon(FOLDER_OPEN_ICON);
		}
	}
	
	private Properties getProjectProperties (IProject p)
	{
		IFile f = p.getFile(ExplorerProjectPaths.FILE_PROJECT_PROPERTIES);
		
		if (!f.exists()) return null;
		
		if (mapPropertiesFile.containsKey(f.getFullPath().toString()))
			return mapPropertiesFile.get(f.getFullPath().toString());
		
		System.out.println(f.getFullPath().toString());
		Properties prop = new Properties();
		
			System.out.println("CREATE PROPS");
		try {
			prop.load(f.getContents());
			mapPropertiesFile.put(f.getFullPath().toString(), prop);
			return prop;
		} catch (Exception e) {
			return null;
		} 
	}
	
	private Image getIcon (String icon)
	{
		Image img = AbstractUIPlugin
				.imageDescriptorFromPlugin(PLUGIN_ID, icon)
				.createImage(); 
		return img;
	}

}
