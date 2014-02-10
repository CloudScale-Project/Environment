package eu.cloudscaleproject.env.csm.generic;

import eu.cloudscaleproject.env.csm.definition.AvailabilityZoneDescriptor;
import eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor;
import eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.ExternalPlatformServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.ExternalSupportServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.impl.CloudDefinitionImpl;
import eu.cloudscaleproject.env.csm.definition.impl.DefinitionFactoryImpl;

public class SapHanaCloudDefinition extends CloudDefinitionImpl{

	private static final String ID_HANA = "hana";
	
	public SapHanaCloudDefinition() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init ()
	{
		//
		// Cloud definition - AWS
		//
		CloudEnvironmentDescriptor aws = DefinitionFactoryImpl.eINSTANCE.createCloudEnvironmentDescriptor();
		aws.setName("SAP HANA Cloud Platform");
		aws.setProviderID(ID_HANA);

		AvailabilityZoneDescriptor basicZone = DefinitionFactoryImpl.eINSTANCE.createAvailabilityZoneDescriptor();
		basicZone.setName("Amazon Web Services (AWS Zone)");
		basicZone.setProviderID(ID_HANA);

		NetworkInfrastructureServiceDescriptor internalConnection = DefinitionFactoryImpl.eINSTANCE.createNetworkInfrastructureServiceDescriptor();
		internalConnection.setBandwidth(1000);
		internalConnection.setLatency(50);
		internalConnection.setName("Internal connection");
		basicZone.setInternalConnectionDescriptor(internalConnection);
		
		aws.getAvailabilityZones().add(basicZone);
		
		ExternalPlatformServiceDescriptor imcp = DefinitionFactoryImpl.eINSTANCE.createExternalRuntimeServiceDescriptor();
		imcp.setName("Computing Platform");
		imcp.setProviderID(ID_HANA);
		
		ExternalSupportServiceDescriptor db = DefinitionFactoryImpl.eINSTANCE.createExternalSupportServiceDescriptor();
		db.setName("Persistence service");
		db.setProviderID(ID_HANA);
		
		ExternalSupportServiceDescriptor doc = DefinitionFactoryImpl.eINSTANCE.createExternalSupportServiceDescriptor();
		doc.setName("Document service");
		doc.setProviderID(ID_HANA);
		
		ExternalSupportServiceDescriptor id = DefinitionFactoryImpl.eINSTANCE.createExternalSupportServiceDescriptor();
		id.setName("Identity service");
		id.setProviderID(ID_HANA);
		
		ComputingInfrastructureServiceDescriptor instance_m1_large = DefinitionFactoryImpl.eINSTANCE.createComputingInfrastructureServiceDescriptor();
		instance_m1_large.setName("Hana Instance");
		instance_m1_large.setCpu(1200); // MHz
		instance_m1_large.setCpuUnits(4); // Units
		instance_m1_large.setMemory(7500); // MB
		instance_m1_large.setStorage(850); // GB
		instance_m1_large.setProviderID(ID_HANA);
		
		setDescriptor(aws);
		
		getPlatformDescriptors().add(imcp);
		getPlatformDescriptors().add(db);
		getPlatformDescriptors().add(doc);
		getPlatformDescriptors().add(id);
		getInfrastructureDescriptors().add(instance_m1_large);
	}
}
