package eu.cloudscaleproject.env.csm.diagram.features.create;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;

import eu.cloudscaleproject.env.csm.architecture.CloudProvider;
import eu.cloudscaleproject.env.csm.architecture.Component;
import eu.cloudscaleproject.env.csm.architecture.Module;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.core.impl.CoreFactoryImpl;
import eu.cloudscaleproject.env.csm.definition.Descriptor;

public abstract class CreateComponentFeature extends AbstractCreateFeature implements
		ICreateFeature {
	
	protected final Descriptor descriptor;

	public CreateComponentFeature(IFeatureProvider fp, Descriptor descriptor) {
		super(fp, descriptor.getName(), "Creates a new DomainObject");
		this.descriptor = descriptor;
	}
	
	public Descriptor getDescriptor() {
		return descriptor;
	}

//	@Override
	public boolean canCreate(ICreateContext context) {
		PictogramLink link = context.getTargetContainer().getLink();
		
		if(link != null){
			Object o = (link.getBusinessObjects().isEmpty()) ? null : link.getBusinessObjects().get(0);
			
			if(o instanceof CloudProvider)
			{
				if (descriptor.getProviderID() == null) return true;
				
				if (((CloudProvider)o).getCloudProviderDescriptor().getProviderID().equals(descriptor.getProviderID()))
				return true;
			}
		}
		return false;
	}

//	@Override
	public Object[] create(ICreateContext context) {
		// TODO: in case of an EMF object add the new object to a suitable resource
		PictogramLink link = context.getTargetContainer().getLink();
		Object o = (link.getBusinessObjects().isEmpty()) ? null : link.getBusinessObjects().get(0);
		CloudProvider cp = (CloudProvider)o;
		
		Component c = createComponent(cp);
		if (c.getModules().isEmpty())
		{
			OperationInterface oi = CoreFactoryImpl.eINSTANCE.createOperationInterface();
			oi.setName("OperationInterface : "+c.getName());
			
			Module module = ArchitectureFactoryImpl.eINSTANCE.createModule();
			module.setName("Basic module");
			module.getProvidedInterfaces().add(oi);
			c.getModules().add(module);
		}
		
		cp.getComponents().add(c);
		cp.eResource().setModified(true);
		
		
		// activate direct editing after object creation
		// getFeatureProvider().getDirectEditingInfo().setActive(true);
		
		addGraphicalRepresentation(context, c);
		return new Object[] { c };
	}
	
	protected abstract Component createComponent (CloudProvider cp);
}
