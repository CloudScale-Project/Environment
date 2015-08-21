package eu.cloudscaleproject.env.toolchain.explorer;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;

import eu.cloudscaleproject.env.common.ui.util.ImageHelper;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerResources {

	public static Image PROJECT_16;
	public static Image DELETE_16;
	public static Image LINK_TO_EDITOR_16;
	public static Image ALTERNATIVE_16;
	
	public static Image getImage(IConfigurationElement element, String attribute, int width, int height)
	{
		String filepath = element.getAttribute(attribute);
		Bundle bundle = Platform.getBundle(element.getContributor().getName());

		if(bundle == null || filepath == null || filepath.isEmpty()){
			return null;
		}
		
		Key key = new Key(bundle, filepath, width, height);
		if(images.containsKey(key)){
			return images.get(key);
		}
		
		Image image = loadImage(bundle, filepath);
		image = ImageHelper.resize(image, width, height);
		if(image != null){
			images.put(key, image);
			return image;
		}
		
		return null;
	}
	
	static
	{
		PROJECT_16 = loadImage("icons/project_16.png");
		DELETE_16 = loadImage("icons/delete_16.png");
		LINK_TO_EDITOR_16 = loadImage("icons/link_to_editor_16.gif");
		ALTERNATIVE_16 = loadImage("icons/alternative_16.png");
	}
	
	private static HashMap<Key, Image> images = new HashMap<Key, Image>();

	private static Image loadImage(String filepath)
	{
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, filepath).createImage();
	}
	
	private static Image loadImage(Bundle bundle, String filepath)
	{		
		URL url = bundle.getResource(filepath);
		try {
			return new Image(Display.getDefault(), url.openStream());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static class Key{
		
		public final Bundle bundle;
		public final String filepath;
		
		public final int width;
		public final int height;
		
		public Key(Bundle bundle, String filepath, int width, int height) {
			this.bundle = bundle;
			this.filepath = filepath;
			this.width = width;
			this.height = height;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((bundle == null) ? 0 : bundle.hashCode());
			result = prime * result
					+ ((filepath == null) ? 0 : filepath.hashCode());
			result = prime * result + height;
			result = prime * result + width;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Key other = (Key) obj;
			if (bundle == null) {
				if (other.bundle != null)
					return false;
			} else if (!bundle.equals(other.bundle))
				return false;
			if (filepath == null) {
				if (other.filepath != null)
					return false;
			} else if (!filepath.equals(other.filepath))
				return false;
			if (height != other.height)
				return false;
			if (width != other.width)
				return false;
			return true;
		}
		
	}
}
