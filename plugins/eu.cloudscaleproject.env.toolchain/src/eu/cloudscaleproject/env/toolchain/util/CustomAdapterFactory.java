package eu.cloudscaleproject.env.toolchain.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemStyledLabelProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.StyledString;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceSetItemProvider;

public class CustomAdapterFactory extends ComposedAdapterFactory
{
	private static final ComposedAdapterFactory HELPER_ADAPTER_FACTORY = new ComposedAdapterFactory(
			ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

	public CustomAdapterFactory()
	{
		super(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		addAdapterFactory(new ResourceSetAdapterFactory());
		addAdapterFactory(new CustomReflectiveAdapterFactory());
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////
	// Factories
	// //////////////////////////////////////////////////////////////////////////////////////////////////
	private class CustomReflectiveAdapterFactory extends ReflectiveItemProviderAdapterFactory
	{

		private final CustomReflectiveItemProvider adapter;

		public CustomReflectiveAdapterFactory()
		{
			supportedTypes.add(IItemStyledLabelProvider.class);
			supportedTypes.add(ITableItemLabelProvider.class);
			adapter = new CustomReflectiveItemProvider(this);
		}

		@Override
		public Adapter createAdapter(Notifier target)
		{
			return adapter;
		}

	}

	private class ResourceSetAdapterFactory extends ResourceItemProviderAdapterFactory
	{

		private ResourceSetProvider adapter = new ResourceSetProvider(this);

		@Override
		public Adapter createResourceSetAdapter()
		{
			return adapter;
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////
	// Item providers
	// //////////////////////////////////////////////////////////////////////////////////////////////////
	private class ResourceSetProvider extends ResourceSetItemProvider
	{

		public ResourceSetProvider(AdapterFactory adapterFactory)
		{
			super(adapterFactory);
		}

		@Override
		public Collection<?> getChildren(Object object)
		{
			ResourceSet resSet = (ResourceSet) object;

			List<Object> children = new ArrayList<>();
			for (Resource r : resSet.getResources())
			{
				if (!r.getContents().isEmpty())
				{
					children.add(r.getContents().get(0));
				}
			}

			return children;
		}
	}

	private class CustomReflectiveItemProvider extends ReflectiveItemProvider implements IItemStyledLabelProvider, ITableItemLabelProvider
	{
		public CustomReflectiveItemProvider(AdapterFactory factory)
		{
			super(factory);
		}

		@Override
		public String getColumnText(Object object, int columnIndex)
		{
			if (object instanceof EObject)
			{
				EObject eobj = (EObject) object;

				String model = eobj.eClass().getName();
				String name = getName(eobj);
				String resource = getResource(eobj);

				return composeString(model, name, resource, eobj.eContainer() == null);
			}

			return super.getColumnText(object, columnIndex);
		}

		@Override
		public Object getColumnImage(Object object, int columnIndex)
		{
			IItemLabelProvider itemLabelProvider = (IItemLabelProvider) HELPER_ADAPTER_FACTORY.adapt(object, IItemLabelProvider.class);
			return itemLabelProvider.getImage(object);
		}

		@Override
		public Object getStyledText(Object object)
		{
			if (object instanceof EObject)
			{
				EObject eobj = (EObject) object;

				String model = eobj.eClass().getName();
				String name = getName(eobj);
				String resource = getResource(eobj);

				return composeStyledString(model, name, resource, eobj.eContainer() == null);
			}

			return super.getStyledText(object);
		}

		private String getName(Object obj)
		{
			try{
				EObject eobj = (EObject) obj;
				EStructuralFeature nameFeature = getLabelFeature(eobj.eClass());
				if (nameFeature != null)
				{
					Object name = eobj.eGet(nameFeature);

					if (name != null)
						return name.toString();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			return null;
		}

		private String getResource(EObject eobj)
		{
			if (eobj.eContainer() == null && eobj.eResource() != null && eobj.eResource().getURI() != null)
			{
				return eobj.eResource().getURI().lastSegment();
			}

			return null;
		}

		@Override
		protected EStructuralFeature getLabelFeature(EClass eClass)
		{
			// Try to find attribute with name containing 'name' ignoring case
			// (ie. PCM entities - entityName)
			for (EAttribute eAttribute : eClass.getEAllAttributes())
			{
				if (!eAttribute.isMany() && eAttribute.getEType().getInstanceClass() != FeatureMap.Entry.class)
				{
					if (eAttribute.getName().toLowerCase().contains("name"))
						return eAttribute;
				}
			}
			return super.getLabelFeature(eClass);
		}
	}

	private static String composeString(String type, String name, String resourceName, boolean isContainer)
	{
		String composition = "";

		if (type != null)
		{
			if (isContainer)
				composition += "Model ";
			composition += type;
		}

		if (name != null)
			composition += String.format(" <%s>", name);

		if (resourceName != null && isContainer)
			composition += " [ Resource: " + resourceName + " ]";

		return composition;
	}

	private static StyledString composeStyledString(String type, String name, String resourceName, boolean isContainer)
	{
		StyledString out = new StyledString();

		if (type != null)
			if (isContainer)
				out.append(String.format("Model %s ", type));
			else
				out.append(String.format("%s ", type));

		if (name != null)
			out.append("  " + name + "  ", StyledString.Style.COUNTER_STYLER);

		if (resourceName != null && isContainer)
			out.append(" [ Resource: " + resourceName + " ]", StyledString.Style.QUALIFIER_STYLER);

		return out;
	}

}
