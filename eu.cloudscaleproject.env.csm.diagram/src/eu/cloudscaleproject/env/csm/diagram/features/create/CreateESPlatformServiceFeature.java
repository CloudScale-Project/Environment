package eu.cloudscaleproject.env.csm.diagram.features.create;

import org.eclipse.graphiti.features.IFeatureProvider;

import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.ExternalSupportService;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.definition.ExternalSupportServiceDescriptor;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public class CreateESPlatformServiceFeature extends CreatetPlatformServiceFeature {

	
	public CreateESPlatformServiceFeature (IFeatureProvider fp, ExternalSupportServiceDescriptor descriptor)
	{
		super (fp, descriptor);
	}
	
	@Override
	protected PlatformService createPlatformService(CloudEnvironment ce) {
		ExternalSupportService esService = ArchitectureFactoryImpl.eINSTANCE.createExternalSupportService();
		ExternalSupportServiceDescriptor esServiceDescriptor = CsmUtil.getSystemDescriptor(ce, (ExternalSupportServiceDescriptor)descriptor);
		
		esService.setDescriptor(esServiceDescriptor);
		esService.setName(descriptor.getName());
		esService.setDescription("External support platform service: "+descriptor.getName());

		return esService;
	}

}
