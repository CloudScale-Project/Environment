package eu.cloudscaleproject.env.example.common;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class Example
{
	private String name;
	private String description;
	private List<Resource> resoruces;
	private URL readme;

	public Example(IConfigurationElement example)
	{
		load(example);
	}

	private void load(IConfigurationElement el)
	{
		this.resoruces = new LinkedList<>();
		this.name = el.getAttribute("name");
		this.description = el.getAttribute("description");
		this.readme = convertAttributeResource("readme", el);

		for (IConfigurationElement resourceElement : el.getChildren())
		{
			Resource resource;
			try
			{
				resource = new Resource(this, resourceElement);
				this.resoruces.add(resource);
			} catch (IOException e)
			{
				Logger.getLogger(Example.class.getName()).warning("Unable to load resource for example : " + name);
				e.printStackTrace();
			}
		}
	}

	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public URL getReadme()
	{
		return readme;
	}

	public List<Resource> getResoruces()
	{
		return resoruces;
	}

	public static class Resource
	{
		public static enum Type {
			ENVIRONMENT, ANALYSER, EXTRACTOR, STATIC_SPOTTER, DYNAMIC_SPOTTER, SOURCE
		};

		private String name;
		private String description;
		private URL archive;
		private Type type;
		private Example example;
		private URL readme;

		public Resource(Example e, IConfigurationElement resourceElement) throws IOException
		{
			this.example = e;
			load(resourceElement);
		}

		private void load(IConfigurationElement el) throws IOException
		{
			this.name = el.getAttribute("name");
			this.description = el.getAttribute("description");
			this.type = convertType(el);
			this.archive = convertAttributeResource("bundle", el);
			this.readme = convertAttributeResource("readme", el);
		}

		private Type convertType(IConfigurationElement el)
		{
			switch (el.getAttribute("type"))
			{
			case "environment":
				return Type.ENVIRONMENT;
			case "analyser":
				return Type.ANALYSER;
			case "extractor":
				return Type.EXTRACTOR;
			case "dynamicspotter":
				return Type.DYNAMIC_SPOTTER;
			case "staticspotter":
				return Type.STATIC_SPOTTER;
			case "source":
				return Type.SOURCE;
			default:
				return Type.ENVIRONMENT;
			}
		}

		public Example getExample()
		{
			return example;
		}

		public String getName()
		{
			return name;
		}

		public String getDescription()
		{
			return description;
		}

		public Type getType()
		{
			return type;
		}

		public URL getArchive()
		{
			return archive;
		}

		public URL getReadme()
		{
			return readme;
		}

	}

	private static URL convertAttributeResource(String attr, IConfigurationElement el) 
	{
		IContributor contributor = el.getDeclaringExtension().getContributor();
		Bundle bundle = Platform.getBundle(contributor.getName());
		URL u = bundle.getEntry(el.getAttribute(attr));
		try
		{
			return FileLocator.toFileURL(u);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
}
