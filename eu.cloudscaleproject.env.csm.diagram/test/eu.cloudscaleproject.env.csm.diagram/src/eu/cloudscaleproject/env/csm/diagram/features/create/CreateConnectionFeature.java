package eu.cloudscaleproject.env.csm.diagram.features.create;

import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;

import eu.cloudscaleproject.env.csm.architecture.Architecture;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.Connector;
import eu.cloudscaleproject.env.csm.architecture.ExternalConnection;
import eu.cloudscaleproject.env.csm.architecture.HybridConnection;
import eu.cloudscaleproject.env.csm.architecture.InternalConnection;
import eu.cloudscaleproject.env.csm.architecture.PlatformLayer;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.core.Entity;

public class CreateConnectionFeature extends AbstractCreateConnectionFeature
		implements ICreateConnectionFeature {

	public CreateConnectionFeature(IFeatureProvider fp) {
		super(fp, "Connection", "Creates a new internal connection between two components");
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		return getBusinessObjectForPictogramElement(context.getSourcePictogramElement()) instanceof PlatformService;
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		Entity source = (Entity) getBusinessObjectForPictogramElement(context.getSourcePictogramElement());
		Entity target = (Entity) getBusinessObjectForPictogramElement(context.getTargetPictogramElement());
		
		// Component -> Component
		// Component -> Connector
		if (source instanceof PlatformService)
		{
			if (target instanceof Connector) return true;
			if (target instanceof PlatformService) return true;
		}
		
		return false;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection connectionPictogram = null;
		eu.cloudscaleproject.env.csm.architecture.Connection connection = null;
		
		PlatformService source = (PlatformService)getBusinessObjectForPictogramElement(context.getSourcePictogramElement());
		Entity target = (Entity)getBusinessObjectForPictogramElement(context.getTargetPictogramElement());
		
		PlatformLayer sourcePlatformLayer = (PlatformLayer)source.eContainer();
		CloudEnvironment sourceCloud = (CloudEnvironment) sourcePlatformLayer.getCloudProvider();
		
		// FIXME
		//if(source.getProvidedInterface() == null){
		//	OperationInterface oi = CoreFactoryImpl.eINSTANCE.createOperationInterface();
		//	source.setProvidedInterface(oi);
		//}
		
		if (target instanceof Connector)
		{
			// External connection
			ExternalConnection ec =  ArchitectureFactoryImpl.eINSTANCE.createExternalConnection();
			ec.setPlatformService(source);
			ec.setConnector((Connector)target);
			
			((Architecture)sourceCloud.eContainer()).getExternalConnections().add(ec);
			
			connection = ec;
		}
		else if (target instanceof PlatformService)
		{
			PlatformLayer targetPlatformLayer = (PlatformLayer)target.eContainer();
			CloudEnvironment targetCloud = (CloudEnvironment) targetPlatformLayer.getCloudProvider();
			//((Component)target).getRequiredInterfaces().add(source.getProvidedInterface());
			
			if (sourceCloud == targetCloud)
			{
				// Internal connection
				InternalConnection ic =  ArchitectureFactoryImpl.eINSTANCE.createInternalConnection();
				ic.setSourcePlatformService(source);
				ic.setDestinationPlatformService((PlatformService)target);
				sourceCloud.getInternalConnections().add(ic);
				
				connection = ic;
			}
			else
			{
				// Hybrid connection
				HybridConnection hc =  ArchitectureFactoryImpl.eINSTANCE.createHybridConnection();
				hc.setSourcePlatformService(source);
				hc.setTargetPlatformService((PlatformService)target);
				connection = hc;
				
			    Architecture architecture = (Architecture)source.getPlatformLayer().getCloudProvider().eContainer();
				architecture.getHybridConnections().add(hc);
				
				connection = hc;
			}
		}
		
		sourceCloud.eResource().setModified(true);

		AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		addContext.setNewObject(connection);
		connectionPictogram = (Connection) getFeatureProvider().addIfPossible(addContext);

		return connectionPictogram;
	}
}
