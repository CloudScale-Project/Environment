package eu.cloudscaleproject.env.csm.diagram.features.create;

import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;

import eu.cloudscaleproject.env.csm.architecture.Architecture;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.ExternalConnection;
import eu.cloudscaleproject.env.csm.architecture.HybridConnection;
import eu.cloudscaleproject.env.csm.architecture.InternalConnection;
import eu.cloudscaleproject.env.csm.architecture.PlatformLayer;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.architecture.Proxy;
import eu.cloudscaleproject.env.csm.architecture.ServiceProxy;
import eu.cloudscaleproject.env.csm.architecture.UsageProxy;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.impl.DefinitionFactoryImpl;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public class CreateConnectionFeature extends AbstractCreateConnectionFeature
		implements ICreateConnectionFeature {

	public CreateConnectionFeature(IFeatureProvider fp) {
		super(fp, "Connection", "Creates a new internal connection between two components");
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		Object source = getBusinessObjectForPictogramElement(context.getSourcePictogramElement());
		return (source instanceof PlatformService || source instanceof Proxy);
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		Object source = getBusinessObjectForPictogramElement(context.getSourcePictogramElement());
		Object target = getBusinessObjectForPictogramElement(context.getTargetPictogramElement());
		
		if (target instanceof PlatformService)
		{
			if (source instanceof PlatformService) return true;
		}
		if (source instanceof UsageProxy)
		{
			if (target instanceof PlatformService) return true;
		}
		if (target instanceof ServiceProxy)
		{
			if (source instanceof PlatformService) return true;
		}
		
		return false;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		
		// TODO: REFACTOR!!!
		
		Connection connectionPictogram = null;
		eu.cloudscaleproject.env.csm.architecture.Connection connection = null;
		
		Entity source = (Entity)getBusinessObjectForPictogramElement(context.getSourcePictogramElement());
		Entity target = (Entity)getBusinessObjectForPictogramElement(context.getTargetPictogramElement());
		
		
		PlatformLayer platformLayer;
		if (source instanceof PlatformService)
			platformLayer = ((PlatformService)source).getPlatformLayer();
		else
			platformLayer = ((PlatformService)target).getPlatformLayer();
		
		CloudEnvironment cloudEnvironment = (CloudEnvironment) platformLayer.getCloudEnvironment();
		
		NetworkInfrastructureServiceDescriptor descriptor = DefinitionFactoryImpl.eINSTANCE.createNetworkInfrastructureServiceDescriptor();
		descriptor.setBandwidth(1000);
		descriptor.setLatency(50);
		descriptor.setName(target.getName()+"->"+source.getName());
		
		if (target instanceof ServiceProxy)
		{
			// External connection
			ExternalConnection ec =  ArchitectureFactoryImpl.eINSTANCE.createExternalConnection();
			ec.setName("External Service connection");
			ec.setPlatformService((PlatformService)source);
			ec.setProxy((Proxy)target);
			ec.setDescriptor(CsmUtil.getSystemDescriptor(target, descriptor));
			ec.setIsOutbound(false);
			((Architecture)cloudEnvironment.eContainer()).getExternalConnections().add(ec);
			
			connection = ec;
		}
		else if (source instanceof UsageProxy)
		{
			// External connection
			ExternalConnection ec =  ArchitectureFactoryImpl.eINSTANCE.createExternalConnection();
			ec.setName("External Usage connection");
			ec.setPlatformService((PlatformService)target);
			ec.setProxy((Proxy)source);
			ec.setDescriptor(CsmUtil.getSystemDescriptor(target, descriptor));
			ec.setIsOutbound(true);
			((Architecture)cloudEnvironment.eContainer()).getExternalConnections().add(ec);
			
			connection = ec;
		}
		else if (target instanceof PlatformService && source instanceof PlatformService)
		{
			PlatformLayer targetPlatformLayer = (PlatformLayer)target.eContainer();
			CloudEnvironment targetCloud = (CloudEnvironment) targetPlatformLayer.getCloudEnvironment();
			//((Component)target).getRequiredInterfaces().add(source.getProvidedInterface());
			
			if (cloudEnvironment == targetCloud)
			{
				// Internal connection
				InternalConnection ic =  ArchitectureFactoryImpl.eINSTANCE.createInternalConnection();
				ic.setName("Internal connection");
				ic.setSourcePlatformService((PlatformService)target);
				ic.setDestinationPlatformService((PlatformService)source);
				cloudEnvironment.getInternalConnections().add(ic);
				
				connection = ic;
			}
			else
			{
				// Hybrid connection
				HybridConnection hc =  ArchitectureFactoryImpl.eINSTANCE.createHybridConnection();
				hc.setName("Hybrid connection");
				hc.setSourcePlatformService((PlatformService)target);
				hc.setTargetPlatformService((PlatformService)source);
				hc.setDescriptor(CsmUtil.getSystemDescriptor(source, descriptor));
				connection = hc;
				
			    Architecture architecture = (Architecture)(((PlatformService)source).getPlatformLayer()).getCloudEnvironment().eContainer();
				architecture.getHybridConnections().add(hc);
				
				connection = hc;
			}
		}
		cloudEnvironment.eResource().setModified(true);

		AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		addContext.setNewObject(connection);
		connectionPictogram = (Connection) getFeatureProvider().addIfPossible(addContext);

		return connectionPictogram;
	}
}
