package org.scaledl.overview.diagram.features.create;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.PlatformRuntimeService;
import org.scaledl.overview.architecture.PlatformService;
import org.scaledl.overview.architecture.impl.ArchitectureFactoryImpl;
import org.scaledl.overview.specification.PlatformRuntimeServiceDescriptor;
import org.scaledl.overview.util.OverviewSpecificationUtil;

public class CreatePlatformRuntimeServiceFeature extends CreatePlatformServiceFeature {

	public CreatePlatformRuntimeServiceFeature (IFeatureProvider fp, PlatformRuntimeServiceDescriptor descriptor)
	{
		super (fp, descriptor);
	}
	
	@Override
	protected PlatformService createPlatformService(CloudEnvironment ce) {
		PlatformRuntimeService drService = ArchitectureFactoryImpl.eINSTANCE.createPlatformRuntimeService();
		PlatformRuntimeServiceDescriptor drServiceDescriptor =  OverviewSpecificationUtil.getSystemDescriptor(ce, (PlatformRuntimeServiceDescriptor)descriptor);
		drService.setDescriptor(drServiceDescriptor);
		drService.setName(descriptor.getName());
		drService.setDescription("Runtime platform service: " + descriptor.getName());
				
		return drService;
	}

}
