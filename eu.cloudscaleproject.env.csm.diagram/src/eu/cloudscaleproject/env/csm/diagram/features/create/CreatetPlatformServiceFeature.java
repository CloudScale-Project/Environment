package eu.cloudscaleproject.env.csm.diagram.features.create;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;

import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.core.impl.CoreFactoryImpl;
import eu.cloudscaleproject.env.csm.definition.Descriptor;

public abstract class CreatetPlatformServiceFeature extends AbstractCreateFeature implements
		ICreateFeature {
	
	protected final Descriptor descriptor;

	public CreatetPlatformServiceFeature(IFeatureProvider fp, Descriptor descriptor) {
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
			
			if(o instanceof CloudEnvironment)
			{
				if (descriptor.getProviderID() == null) return true;
				
				if (((CloudEnvironment)o).getCloudProviderDescriptor().getProviderID().equals(descriptor.getProviderID()))
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
		CloudEnvironment ce = (CloudEnvironment)o;
		
		PlatformService ps = createPlatformService(ce);
		
		//add empty operation interface
		if(ps instanceof OperationInterfaceContainer){
			OperationInterfaceContainer oic = (OperationInterfaceContainer)ps;
			if(oic.getProvidedInterfaces().isEmpty()){
				OperationInterface opInt = CoreFactoryImpl.eINSTANCE.createOperationInterface();
				opInt.setName("I_" + ps.getName());
				oic.getProvidedInterfaces().add(opInt);
			}
		}
		
		ce.getPlatformLayer().getServices().add(ps);
		
		ce.eResource().setModified(true);
		
		// activate direct editing after object creation
		// getFeatureProvider().getDirectEditingInfo().setActive(true);
		
		addGraphicalRepresentation(context, ps);
		return new Object[] { ps };
	}
	
	protected abstract PlatformService createPlatformService (CloudEnvironment ce);
}
