package org.scaledl.overview.diagram.features.create;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.scaledl.overview.Overview;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.impl.ArchitectureFactoryImpl;
import org.scaledl.overview.diagram.util.OverviewDiagramUtil;
import org.scaledl.overview.specification.CloudSpecification;
import org.scaledl.overview.util.OverviewSpecificationUtil;

public class CreateCloudEnvironmentFeature extends AbstractCreateFeature implements ICreateFeature{

	private final CloudSpecification cloudSpecification;

	public CreateCloudEnvironmentFeature(IFeatureProvider fp, CloudSpecification cloudSpecification) {
		super(fp, cloudSpecification.getDescriptor().getName(), "Creates a new DomainObject");
		this.cloudSpecification = cloudSpecification;
	}
	
	public CloudSpecification getCloudSpecification() {
		return cloudSpecification;
	}

//	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

//	@Override
	public Object[] create(ICreateContext context) {
		
		CloudEnvironment cloudEnvironment = ArchitectureFactoryImpl.eINSTANCE.createCloudEnvironment();
		
		Overview overview = OverviewDiagramUtil.getOverviewModel(getDiagram());
		
		overview.getArchitecture().getCloudEnvironments().add(cloudEnvironment);
		overview.eResource().setModified(true);
		
		cloudEnvironment.setCloudEnvironmentDescriptor(OverviewSpecificationUtil.getSystemDescriptor(cloudEnvironment,cloudSpecification.getDescriptor()));
		cloudEnvironment.setName("My " + cloudEnvironment.getCloudEnvironmentDescriptor().getName());
		cloudEnvironment.setDescription("Cloud environment: " + cloudEnvironment.getCloudEnvironmentDescriptor().getName());
		
		
		if(!cloudEnvironment.getCloudEnvironmentDescriptor().getAvailabilityZones().isEmpty()){
			//set default availability zone
			cloudEnvironment.setAvailabilityZoneDescriptor(cloudEnvironment.getCloudEnvironmentDescriptor().getAvailabilityZones().get(0));
		}
		
		cloudEnvironment.setInfrastructureLayer(ArchitectureFactoryImpl.eINSTANCE.createInfrastructureLayer());
		cloudEnvironment.setPlatformLayer(ArchitectureFactoryImpl.eINSTANCE.createPlatformLayer());
		cloudEnvironment.setSoftwareLayer(ArchitectureFactoryImpl.eINSTANCE.createSoftwareLayer());
		
		addGraphicalRepresentation(context, cloudEnvironment);
		return new Object[] { cloudEnvironment };
	}
}
