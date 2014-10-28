package org.scaledl.overview.diagram.features.create;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.PlatformService;
import org.scaledl.overview.architecture.ProvidedPlatformSupportService;
import org.scaledl.overview.specification.ProvidedPlatformSupportServiceDescriptor;
import org.scaledl.overview.util.OverviewSpecificationUtil;

public class CreateProvidedPlatformSupportServiceFeature extends CreatePlatformServiceFeature {

	
	public CreateProvidedPlatformSupportServiceFeature (IFeatureProvider fp, ProvidedPlatformSupportServiceDescriptor descriptor)
	{
		super (fp, descriptor);
	}
	
	@Override
	protected PlatformService createPlatformService(CloudEnvironment ce) {
		ProvidedPlatformSupportService service = ArchitectureFactory.eINSTANCE.createProvidedPlatformSupportService();
		ProvidedPlatformSupportServiceDescriptor serviceDes = OverviewSpecificationUtil.getSystemDescriptor(ce, (ProvidedPlatformSupportServiceDescriptor)descriptor);
		
		service.setDescriptor(serviceDes);
		service.setName(descriptor.getName());
		service.setDescription("External support platform service: "+descriptor.getName());
		
		return service;
	}

}
