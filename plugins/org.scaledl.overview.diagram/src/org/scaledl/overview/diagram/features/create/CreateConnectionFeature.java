package org.scaledl.overview.diagram.features.create;

import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.architecture.Architecture;
import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.ExternalConnection;
import org.scaledl.overview.architecture.InternalConnection;
import org.scaledl.overview.architecture.Proxy;
import org.scaledl.overview.core.Entity;
import org.scaledl.overview.specification.NetworkInfrastructureServiceDescriptor;
import org.scaledl.overview.util.OverviewUtil;

public class CreateConnectionFeature extends AbstractCreateConnectionFeature
		implements ICreateConnectionFeature {

	public CreateConnectionFeature(IFeatureProvider fp) {
		super(fp, "Connection", "Creates a new internal connection between two components");
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		Object source = getBusinessObjectForPictogramElement(context.getSourcePictogramElement());
		return (source instanceof OperationInterfaceContainer);
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		Object source = getBusinessObjectForPictogramElement(context.getSourcePictogramElement());
		Object target = getBusinessObjectForPictogramElement(context.getTargetPictogramElement());
		
		if (target instanceof OperationInterfaceContainer)
		{
			if (source instanceof OperationInterfaceContainer) return true;
		}
		
		return false;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
				
		Connection connectionPictogram = null;
		org.scaledl.overview.architecture.Connection connection = null;
		
		Entity source = (Entity)getBusinessObjectForPictogramElement(context.getSourcePictogramElement());
		Entity target = (Entity)getBusinessObjectForPictogramElement(context.getTargetPictogramElement());
		
		CloudEnvironment sourceCloudEnvironment = OverviewUtil.getCloudEnvironment(source);
		CloudEnvironment targetCloudEnvironment = OverviewUtil.getCloudEnvironment(target);
		CloudEnvironment cloudEnvironment = sourceCloudEnvironment != null ? sourceCloudEnvironment : targetCloudEnvironment;
		
		/*
		NetworkInfrastructureServiceDescriptor desc riptor = SpecificationFactory.eINSTANCE.createNetworkInfrastructureServiceDescriptor();
		descriptor.setBandwidth(1000);
		descriptor.setLatency(50);
		descriptor.setName(target.getName()+"->"+source.getName());
		*/
		
		if (target instanceof Proxy || source instanceof Proxy)
		{
			// usage connection
			ExternalConnection ec = ArchitectureFactory.eINSTANCE.createExternalConnection();
			ec.setName("External service connection");
			
			ec.setSource((OperationInterfaceContainer)source);
			ec.setTarget((OperationInterfaceContainer)target);
			
			((Architecture)cloudEnvironment.eContainer()).getUsageConnections().add(ec);
			connection = ec;
		}
		else if(source instanceof OperationInterfaceContainer
				&& target instanceof OperationInterfaceContainer){
			
			if(sourceCloudEnvironment != targetCloudEnvironment){
				//connection is external
				ExternalConnection ec = ArchitectureFactory.eINSTANCE.createExternalConnection();
				ec.setName("External service connection");
				
				ec.setSource((OperationInterfaceContainer)source);
				ec.setTarget((OperationInterfaceContainer)target);
				
				((Architecture)cloudEnvironment.eContainer()).getExternalConnections().add(ec);
				connection = ec;
			}
			else{
				//connection is internal
				InternalConnection ic = ArchitectureFactory.eINSTANCE.createInternalConnection();
				NetworkInfrastructureServiceDescriptor descriptor 
					= OverviewUtil.getCloudEnvironment(source).getAvailabilityZoneDescriptor().getNetworkInfrastructureServiceDescriptor();
				ic.setDescriptor(descriptor);
				ic.setName("Internal service connection");
				
				ic.setSource((OperationInterfaceContainer)source);
				ic.setTarget((OperationInterfaceContainer)target);
				
				cloudEnvironment.getInternalConnections().add(ic);
				connection = ic;
			}
		}
		else{
			throw new IllegalArgumentException("Pictogram connection connect incompatible EMF class elements!");
		}
		//
		
		cloudEnvironment.eResource().setModified(true);

		AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		addContext.setNewObject(connection);
		connectionPictogram = (Connection) getFeatureProvider().addIfPossible(addContext);

		return connectionPictogram;
	}
}
