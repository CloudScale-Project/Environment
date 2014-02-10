package eu.cloudscaleproject.env.csm.diagram.features.create;

import org.eclipse.graphiti.features.IFeatureProvider;

import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.DeployableSupportService;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.core.impl.CoreFactoryImpl;
import eu.cloudscaleproject.env.csm.definition.DeployableSupportServiceDescriptor;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public class CreateDSPlatformServiceFeature extends CreatetPlatformServiceFeature {

	public CreateDSPlatformServiceFeature (IFeatureProvider fp, DeployableSupportServiceDescriptor descriptor)
	{
		super (fp, descriptor);
	}

	@Override
	protected PlatformService createPlatformService(CloudEnvironment ce) {
		DeployableSupportService dsService = ArchitectureFactoryImpl.eINSTANCE.createDeployableSupportService();
		DeployableSupportServiceDescriptor dsServiceDescriptor = CsmUtil.getSystemDescriptor(ce, (DeployableSupportServiceDescriptor)descriptor);
		dsService.setDescriptor(dsServiceDescriptor);
		dsService.setName(descriptor.getName());
		dsService.setDescription("Deployable platform support service: "+descriptor.getName());
		
		OperationInterface oi = CoreFactoryImpl.eINSTANCE.createOperationInterface();
		oi.setName("I_" + dsService.getName());
		dsService.getProvidedInterfaces().add(oi);
		
		ComputingInfrastructureService ciService = CsmUtil.createDefaultComputingInfrastructureService(ce);
		dsService.setComputingInfrastructureService(ciService);
		
		return dsService;
	}
}
