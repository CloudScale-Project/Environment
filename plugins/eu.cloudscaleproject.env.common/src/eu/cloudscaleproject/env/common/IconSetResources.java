package eu.cloudscaleproject.env.common;

import java.util.HashMap;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class IconSetResources
{
	private static HashMap<String, Image> cache = new HashMap<>();
	
	public enum COLOR {GREEN, ORANGE, BLUE, RED};
	public enum SIZE {
		SIZE_8(8), SIZE_16(16), SIZE_24(24), SIZE_32(32), SIZE_48(48);

		private int size;
		SIZE(int size){ this.size = size; }
	}
		

	public static class IconDescriptor {
		public final String name;
		public final COLOR color;
		public final SIZE size;
		
		public IconDescriptor(String name, COLOR color, SIZE size) {
			this.name = name;
			this.color = color;
			this.size = size;
		}
		
		public IconDescriptor withSize (SIZE size)
		{
			return new IconDescriptor(name, color, size);
		}

		public IconDescriptor withColor (COLOR color)
		{
			return new IconDescriptor(name, color, size);
		}
	}

	public static final IconDescriptor OK = new IconDescriptor("ok", COLOR.GREEN, SIZE.SIZE_32);
	public static final IconDescriptor WARNING = new IconDescriptor("warning", COLOR.ORANGE, SIZE.SIZE_32);
	public static final IconDescriptor ERROR = new IconDescriptor("cancel", COLOR.RED, SIZE.SIZE_32);
	public static final IconDescriptor THUMB_UP = new IconDescriptor("thumb_up", COLOR.GREEN, SIZE.SIZE_32);

	public static Image getImage (IconDescriptor descriptor)
	{
		return getImage(descriptor.name, descriptor.color, descriptor.size);
	}

	public static Image getImage(String name, COLOR color, SIZE size)
	{
		String plugin = "eu.cloudscaleproject.env.common";
		String container = "icons/sets/"+color.toString().toLowerCase()+"/"+size.size+"/";
		String imgFilePath = container+name+".png";
		
		if (!cache.containsKey(imgFilePath))
		{
			Image img = AbstractUIPlugin.imageDescriptorFromPlugin(plugin, imgFilePath).createImage();
			cache.put(imgFilePath, img);
		}
		
		return cache.get(imgFilePath);
	}
}
