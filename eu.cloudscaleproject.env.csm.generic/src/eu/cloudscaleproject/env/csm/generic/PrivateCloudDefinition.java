package eu.cloudscaleproject.env.csm.generic;

import eu.cloudscaleproject.env.csm.definition.AvailabilityZoneDescriptor;
import eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor;
import eu.cloudscaleproject.env.csm.definition.ExternalPlatformServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.impl.CloudDefinitionImpl;
import eu.cloudscaleproject.env.csm.definition.impl.DefinitionFactoryImpl;

public class PrivateCloudDefinition extends CloudDefinitionImpl{

	private static final String ID_OPEN_STACK = "private";
	
	public PrivateCloudDefinition() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init ()
	{
		//
		// Cloud definition - private OpenStack
		//
		CloudEnvironmentDescriptor privateCloud = DefinitionFactoryImpl.eINSTANCE.createCloudEnvironmentDescriptor();
		privateCloud.setName("Private (OpenStack)");
		privateCloud.setProviderID(ID_OPEN_STACK);
		
		AvailabilityZoneDescriptor basicZone = DefinitionFactoryImpl.eINSTANCE.createAvailabilityZoneDescriptor();
		basicZone.setName("Private (OpenStack) zone");
		basicZone.setProviderID(ID_OPEN_STACK);

		NetworkInfrastructureServiceDescriptor internalConnection = DefinitionFactoryImpl.eINSTANCE.createNetworkInfrastructureServiceDescriptor();
		internalConnection.setBandwidth(1000);
		internalConnection.setLatency(50);
		internalConnection.setName("Internal connection");
		basicZone.setInternalConnectionDescriptor(internalConnection);
		
		privateCloud.getAvailabilityZones().add(basicZone);
		
		ExternalPlatformServiceDescriptor swift = DefinitionFactoryImpl.eINSTANCE.createExternalSupportServiceDescriptor();
		swift.setName("Swift");
		swift.setProviderID(ID_OPEN_STACK);
		
		ExternalPlatformServiceDescriptor ceph = DefinitionFactoryImpl.eINSTANCE.createExternalSupportServiceDescriptor();
		ceph.setName("Ceph");
		ceph.setProviderID(ID_OPEN_STACK);

		getPlatformDescriptors().add(swift);
		getPlatformDescriptors().add(ceph);
		setDescriptor(privateCloud);
	}
}
