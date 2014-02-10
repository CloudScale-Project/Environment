package eu.cloudscaleproject.env.csm.diagram.features.create;

import org.eclipse.graphiti.features.IFeatureProvider;

import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService;
import eu.cloudscaleproject.env.csm.architecture.ExternalRuntimeService;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.core.impl.CoreFactoryImpl;
import eu.cloudscaleproject.env.csm.definition.ExternalRuntimeServiceDescriptor;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public class CreateERPlatformServiceFeature extends CreatetPlatformServiceFeature {

	
	public CreateERPlatformServiceFeature (IFeatureProvider fp, ExternalRuntimeServiceDescriptor descriptor)
	{
		super (fp, descriptor);
	}
	
	@Override
	protected PlatformService createPlatformService(CloudEnvironment ce) {
		ExternalRuntimeService erService = ArchitectureFactoryImpl.eINSTANCE.createExternalRuntimeService();
		ExternalRuntimeServiceDescriptor erServiceDescriptor = CsmUtil.getSystemDescriptor(ce, (ExternalRuntimeServiceDescriptor)descriptor);
		
		DeployableSoftwareService applicationService = ArchitectureFactoryImpl.eINSTANCE.createDeployableSoftwareService();
		applicationService.getProvidedInterfaces().add(CoreFactoryImpl.eINSTANCE.createOperationInterface());
		applicationService.setName("App service");
		applicationService.setDescription("Deployable software service");

		erService.getSoftwareServices().add(applicationService);
		ce.getSoftwareLayer().getServices().add(applicationService);
		
		erService.setDescriptor(erServiceDescriptor);
		erService.setName(descriptor.getName());
		erService.setDescription("External runtime platform service: "+descriptor.getName());

		return erService;
	}

}
