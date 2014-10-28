package org.scaledl.overview.diagram.features.create;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.PlatformService;
import org.scaledl.overview.architecture.PlatformSupportService;
import org.scaledl.overview.specification.PlatformSupportServiceDescriptor;
import org.scaledl.overview.util.OverviewSpecificationUtil;

public class CreatePlatformSupportServiceFeature extends CreatePlatformServiceFeature {

	public CreatePlatformSupportServiceFeature (IFeatureProvider fp, PlatformSupportServiceDescriptor descriptor)
	{
		super (fp, descriptor);
	}

	@Override
	protected PlatformService createPlatformService(CloudEnvironment ce) {
		PlatformSupportService dsService = ArchitectureFactory.eINSTANCE.createPlatformSupportService();
		PlatformSupportServiceDescriptor dsServiceDescriptor = OverviewSpecificationUtil.getSystemDescriptor(ce, (PlatformSupportServiceDescriptor)descriptor);
		dsService.setDescriptor(dsServiceDescriptor);
		dsService.setName(descriptor.getName());
		dsService.setDescription("Platform support service: "+descriptor.getName());
		
		return dsService;
	}
}
