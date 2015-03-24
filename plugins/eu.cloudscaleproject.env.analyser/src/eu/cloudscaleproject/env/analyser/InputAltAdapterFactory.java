package eu.cloudscaleproject.env.analyser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemStyledLabelProvider;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceSetItemProvider;
import org.eclipse.jface.viewers.StyledString;

import de.uka.ipd.sdq.pcm.allocation.provider.AllocationItemProvider;
import de.uka.ipd.sdq.pcm.allocation.provider.AllocationItemProviderAdapterFactory;
import de.uka.ipd.sdq.pcm.core.composition.provider.CompositionItemProviderAdapterFactory;
import de.uka.ipd.sdq.pcm.core.entity.NamedElement;
import de.uka.ipd.sdq.pcm.repository.provider.RepositoryItemProvider;
import de.uka.ipd.sdq.pcm.repository.provider.RepositoryItemProviderAdapterFactory;
import de.uka.ipd.sdq.pcm.resourceenvironment.provider.ResourceEnvironmentItemProvider;
import de.uka.ipd.sdq.pcm.resourceenvironment.provider.ResourceenvironmentItemProviderAdapterFactory;
import de.uka.ipd.sdq.pcm.seff.util.SeffAdapterFactory;
import de.uka.ipd.sdq.pcm.system.provider.SystemItemProvider;
import de.uka.ipd.sdq.pcm.system.provider.SystemItemProviderAdapterFactory;
import de.uka.ipd.sdq.pcm.usagemodel.provider.UsageModelItemProvider;
import de.uka.ipd.sdq.pcm.usagemodel.provider.UsagemodelItemProviderAdapterFactory;

public class InputAltAdapterFactory extends ComposedAdapterFactory{
	
	public InputAltAdapterFactory() {
		super(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		
		addAdapterFactory(new ResourceSetAdapterFactory());
		addAdapterFactory(new RepositoryAdapterFactory());
		addAdapterFactory(new SystemAdapterFactory());
		addAdapterFactory(new CompositionItemProviderAdapterFactory());
		addAdapterFactory(new SeffAdapterFactory());
		addAdapterFactory(new ResourceAdapterFactory());
		addAdapterFactory(new AllocationAdapterFactory());
		addAdapterFactory(new UsageAdapterFactory());
	}
	
	private class ResourceSetAdapterFactory extends ResourceItemProviderAdapterFactory{
		
		private ResourceSetProvider adapter = new ResourceSetProvider(this);
		
		@Override
		public Adapter createResourceSetAdapter() {
			return adapter;
		}
	}
	
	private class RepositoryAdapterFactory extends RepositoryItemProviderAdapterFactory{
		
		private RepositoryProvider adapter = new RepositoryProvider(this);
		public RepositoryAdapterFactory() {
			super();
			supportedTypes.add(IItemStyledLabelProvider.class);
		}
		
		@Override
		public Adapter createRepositoryAdapter() {
			return adapter; 
		}
	}
	
	private class SystemAdapterFactory extends SystemItemProviderAdapterFactory{
		private SystemProvider adapter = new SystemProvider(this);
		public SystemAdapterFactory() {
			super();
			supportedTypes.add(IItemStyledLabelProvider.class);
		}
		
		@Override
		public Adapter createSystemAdapter() {
			return adapter; 
		}
	}
	
	private class ResourceAdapterFactory extends ResourceenvironmentItemProviderAdapterFactory{
		private ResourceProvider adapter = new ResourceProvider(this);
		public ResourceAdapterFactory() {
			super();
			supportedTypes.add(IItemStyledLabelProvider.class);
		}
		
		@Override
		public Adapter createResourceEnvironmentAdapter() {
			return adapter;
		}
	}
	
	private class AllocationAdapterFactory extends AllocationItemProviderAdapterFactory{
		private AllocationProvider adapter = new AllocationProvider(this);
		public AllocationAdapterFactory() {
			super();
			supportedTypes.add(IItemStyledLabelProvider.class);
		}
		
		@Override
		public Adapter createAllocationAdapter() {
			return adapter; 
		}
	}
	
	private class UsageAdapterFactory extends UsagemodelItemProviderAdapterFactory{
		private UsageProvider adapter = new UsageProvider(this);
		public UsageAdapterFactory() {
			super();
			supportedTypes.add(IItemStyledLabelProvider.class);
		}
		
		@Override
		public Adapter createUsageModelAdapter() {
			return adapter; 
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	// Item providers
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private class ResourceSetProvider extends ResourceSetItemProvider{

		public ResourceSetProvider(AdapterFactory adapterFactory) {
			super(adapterFactory);
		}
		
		@Override
		public Collection<?> getChildren(Object object) {
			ResourceSet resSet = (ResourceSet)object;
			
			List<EObject> children = new ArrayList<EObject>();
			for(Resource r : resSet.getResources()){
				if(!r.getContents().isEmpty()){
					children.add(r.getContents().get(0));
				}
			}
			
			return children;
		}
	}
	
	private class RepositoryProvider extends RepositoryItemProvider implements IItemStyledLabelProvider{

		public RepositoryProvider(AdapterFactory adapterFactory) {
			super(adapterFactory);
		}
		
		@Override
		public Object getStyledText(Object object) {
			NamedElement nm = (NamedElement)object;
			return composeStyledString("Repository Model", nm.getEntityName(), ((EObject)object).eResource().getURI().lastSegment());
		}
	}
	
	private class SystemProvider extends SystemItemProvider implements IItemStyledLabelProvider{

		public SystemProvider(AdapterFactory adapterFactory) {
			super(adapterFactory);
		}
		
		@Override
		public Object getStyledText(Object object) {
			NamedElement nm = (NamedElement)object;
			return composeStyledString("System Model", nm.getEntityName(), ((EObject)object).eResource().getURI().lastSegment());
		}
	}
	
	private class ResourceProvider extends ResourceEnvironmentItemProvider implements IItemStyledLabelProvider{

		public ResourceProvider(AdapterFactory adapterFactory) {
			super(adapterFactory);
		}
		
		@Override
		public Object getStyledText(Object object) {
			NamedElement nm = (NamedElement)object;
			return composeStyledString("Resource Model", nm.getEntityName(), ((EObject)object).eResource().getURI().lastSegment());
		}
	}
	
	private class AllocationProvider extends AllocationItemProvider implements IItemStyledLabelProvider{

		public AllocationProvider(AdapterFactory adapterFactory) {
			super(adapterFactory);
		}
		
		@Override
		public Object getStyledText(Object object) {
			NamedElement nm = (NamedElement)object;
			return composeStyledString("Allocation Model", nm.getEntityName(), ((EObject)object).eResource().getURI().lastSegment());
		}
	}
	
	private class UsageProvider extends UsageModelItemProvider implements IItemStyledLabelProvider{

		public UsageProvider(AdapterFactory adapterFactory) {
			super(adapterFactory);
		}
		
		@Override
		public Object getStyledText(Object object) {
			return composeStyledString("Usage Model", "usage model", ((EObject)object).eResource().getURI().lastSegment());
		}
	}
	
	private static StyledString composeStyledString (String type, String name, String resourceName)
	{
			StyledString out = new StyledString();
			out.append(String.format("%-25s", type));
			out.append(name);
			out.append(" [ Resource: " + resourceName + " ] ", StyledString.COUNTER_STYLER);
			return out;
	}

}
