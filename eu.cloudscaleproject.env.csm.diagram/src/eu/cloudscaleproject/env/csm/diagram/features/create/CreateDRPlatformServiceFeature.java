package eu.cloudscaleproject.env.csm.diagram.features.create;

import org.eclipse.graphiti.features.IFeatureProvider;

import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.DeployableRuntimeService;
import eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.core.impl.CoreFactoryImpl;
import eu.cloudscaleproject.env.csm.definition.DeployableRuntimeServiceDescriptor;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public class CreateDRPlatformServiceFeature extends CreatetPlatformServiceFeature {

	public CreateDRPlatformServiceFeature (IFeatureProvider fp, DeployableRuntimeServiceDescriptor descriptor)
	{
		super (fp, descriptor);
	}
	
	@Override
	protected PlatformService createPlatformService(CloudEnvironment ce) {
		DeployableRuntimeService drService = ArchitectureFactoryImpl.eINSTANCE.createDeployableRuntimeService();
		DeployableRuntimeServiceDescriptor drServiceDescriptor =  CsmUtil.getSystemDescriptor(ce, (DeployableRuntimeServiceDescriptor)descriptor);
		drService.setDescriptor(drServiceDescriptor);
		drService.setName(descriptor.getName());
		drService.setDescription("Deployable runtime platform service: "+descriptor.getName());
		
		DeployableSoftwareService applicationService = ArchitectureFactoryImpl.eINSTANCE.createDeployableSoftwareService();
		applicationService.setName("App service");
		applicationService.setDescription("Deployable software service");

		
		OperationInterface operationInterface = CoreFactoryImpl.eINSTANCE.createOperationInterface();
		operationInterface.setName("I_AppService_1");
		applicationService.getProvidedInterfaces().add(operationInterface);
		
		drService.getSoftwareServices().add(applicationService);
		ce.getSoftwareLayer().getServices().add(applicationService);
		
		ComputingInfrastructureService ciService = CsmUtil.createDefaultComputingInfrastructureService(ce);
		drService.setComputingInfrastructureService(ciService);
		
		return drService;
	}

}
