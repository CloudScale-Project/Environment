package eu.cloudscaleproject.env.csm.generic;

import eu.cloudscaleproject.env.csm.definition.AvailabilityZoneDescriptor;
import eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor;
import eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.ExternalPlatformServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.ExternalSupportServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.impl.CloudDefinitionImpl;
import eu.cloudscaleproject.env.csm.definition.impl.DefinitionFactoryImpl;

public class AwsCloudDefinition extends CloudDefinitionImpl{

	private static final String ID_AWS = "aws";
	
	public AwsCloudDefinition() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init ()
	{
		//
		// Cloud definition - AWS
		//
		CloudEnvironmentDescriptor aws = DefinitionFactoryImpl.eINSTANCE.createCloudEnvironmentDescriptor();
		aws.setName("Amazon Web Services (AWS)");
		aws.setProviderID(ID_AWS);

		AvailabilityZoneDescriptor basicZone = DefinitionFactoryImpl.eINSTANCE.createAvailabilityZoneDescriptor();
		basicZone.setName("Amazon Web Services (AWS Zone)");
		basicZone.setProviderID(ID_AWS);

		NetworkInfrastructureServiceDescriptor internalConnection = DefinitionFactoryImpl.eINSTANCE.createNetworkInfrastructureServiceDescriptor();
		internalConnection.setBandwidth(1000);
		internalConnection.setLatency(50);
		internalConnection.setName("Internal connection");
		basicZone.setInternalConnectionDescriptor(internalConnection);
		
		aws.getAvailabilityZones().add(basicZone);
		
		ExternalPlatformServiceDescriptor elb = DefinitionFactoryImpl.eINSTANCE.createExternalSupportServiceDescriptor();
		elb.setName("Elastic Load Balancer");
		elb.setProviderID(ID_AWS);
		
		ExternalSupportServiceDescriptor dynamoDB = DefinitionFactoryImpl.eINSTANCE.createExternalSupportServiceDescriptor();
		dynamoDB.setName("Amazon DynamoDB");
		dynamoDB.setProviderID(ID_AWS);
		
		ExternalPlatformServiceDescriptor elastiCache = DefinitionFactoryImpl.eINSTANCE.createExternalSupportServiceDescriptor();
		elastiCache.setName("Amazon ElastiCache");
		elastiCache.setProviderID(ID_AWS);
		
		ExternalPlatformServiceDescriptor redshift = DefinitionFactoryImpl.eINSTANCE.createExternalSupportServiceDescriptor();
		redshift.setName("Amazon Redshift");
		redshift.setProviderID(ID_AWS);
		
		ExternalPlatformServiceDescriptor s3 = DefinitionFactoryImpl.eINSTANCE.createExternalSupportServiceDescriptor();
		s3.setName("Amazon S3");
		s3.setProviderID(ID_AWS);
		
		ExternalPlatformServiceDescriptor rds = DefinitionFactoryImpl.eINSTANCE.createExternalSupportServiceDescriptor();
		rds.setName("Amazon RDS");
		rds.setProviderID(ID_AWS);
		
		ExternalPlatformServiceDescriptor cloudFront = DefinitionFactoryImpl.eINSTANCE.createExternalSupportServiceDescriptor();
		cloudFront.setName("Amazon CloudFront");
		cloudFront.setProviderID(ID_AWS);
		
		ExternalPlatformServiceDescriptor beanstalk = DefinitionFactoryImpl.eINSTANCE.createExternalRuntimeServiceDescriptor();
		beanstalk.setName("Elastic Beanstalk");
		beanstalk.setProviderID(ID_AWS);
		
		
		ComputingInfrastructureServiceDescriptor instance_m1_small = DefinitionFactoryImpl.eINSTANCE.createComputingInfrastructureServiceDescriptor();
		instance_m1_small.setName("M1 Small Instance");
		instance_m1_small.setCpu(1200); // MHz
		instance_m1_small.setCpuUnits(1); // Units
		instance_m1_small.setMemory(1700); // MB
		instance_m1_small.setStorage(160); // GB
		instance_m1_small.setProviderID(ID_AWS);
		
		ComputingInfrastructureServiceDescriptor instance_m1_medium = DefinitionFactoryImpl.eINSTANCE.createComputingInfrastructureServiceDescriptor();
		instance_m1_medium.setName("M1 Medium Instance");
		instance_m1_medium.setCpu(1200); // MHz
		instance_m1_medium.setCpuUnits(2); // Units
		instance_m1_medium.setMemory(3750); // MB
		instance_m1_medium.setStorage(375); // GB
		instance_m1_medium.setProviderID(ID_AWS);
		
		ComputingInfrastructureServiceDescriptor instance_m1_large = DefinitionFactoryImpl.eINSTANCE.createComputingInfrastructureServiceDescriptor();
		instance_m1_large.setName("M1 Large Instance");
		instance_m1_large.setCpu(1200); // MHz
		instance_m1_large.setCpuUnits(4); // Units
		instance_m1_large.setMemory(7500); // MB
		instance_m1_large.setStorage(850); // GB
		instance_m1_large.setProviderID(ID_AWS);
		
		setDescriptor(aws);
		
		getPlatformDescriptors().add(beanstalk);
		getPlatformDescriptors().add(elb);
		getPlatformDescriptors().add(dynamoDB);
		getPlatformDescriptors().add(rds);
		getPlatformDescriptors().add(elastiCache);
		getPlatformDescriptors().add(redshift);
		getPlatformDescriptors().add(s3);
		getPlatformDescriptors().add(cloudFront);
		
		getInfrastructureDescriptors().add(instance_m1_large);
		getInfrastructureDescriptors().add(instance_m1_medium);
		getInfrastructureDescriptors().add(instance_m1_small);
	}
}
