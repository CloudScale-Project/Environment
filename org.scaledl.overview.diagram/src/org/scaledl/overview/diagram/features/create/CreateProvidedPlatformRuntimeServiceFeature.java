package org.scaledl.overview.diagram.features.create;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.PlatformService;
import org.scaledl.overview.architecture.ProvidedPlatformRuntimeService;
import org.scaledl.overview.specification.ProvidedPlatformRuntimeServiceDescriptor;
import org.scaledl.overview.util.OverviewSpecificationUtil;

public class CreateProvidedPlatformRuntimeServiceFeature extends CreatePlatformServiceFeature {

	
	public CreateProvidedPlatformRuntimeServiceFeature (IFeatureProvider fp, ProvidedPlatformRuntimeServiceDescriptor descriptor)
	{
		super (fp, descriptor);
	}
	
	@Override
	protected PlatformService createPlatformService(CloudEnvironment ce) {
		ProvidedPlatformRuntimeService prService = ArchitectureFactory.eINSTANCE.createProvidedPlatformRuntimeService();
		ProvidedPlatformRuntimeServiceDescriptor prServiceDescriptor 
			= OverviewSpecificationUtil.getSystemDescriptor(ce, (ProvidedPlatformRuntimeServiceDescriptor)descriptor);
				
		prService.setDescriptor(prServiceDescriptor);
		prService.setName(descriptor.getName());
		prService.setDescription("External runtime platform service: "+descriptor.getName());
		
		return prService;
	}

}
