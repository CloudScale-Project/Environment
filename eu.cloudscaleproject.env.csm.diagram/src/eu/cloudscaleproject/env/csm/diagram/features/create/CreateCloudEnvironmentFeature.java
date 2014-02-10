package eu.cloudscaleproject.env.csm.diagram.features.create;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor;
import eu.cloudscaleproject.env.csm.diagram.util.CsmDiagramUtil;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public class CreateCloudEnvironmentFeature extends AbstractCreateFeature implements ICreateFeature{

	private final CloudEnvironmentDescriptor descriptor;

	public CreateCloudEnvironmentFeature(IFeatureProvider fp, CloudEnvironmentDescriptor descriptor) {
		super(fp, descriptor.getName(), "Creates a new DomainObject");
		this.descriptor = descriptor;
	}
	
	public CloudEnvironmentDescriptor getDescriptor() {
		return descriptor;
	}

//	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

//	@Override
	public Object[] create(ICreateContext context) {
		
		CloudEnvironment environment = ArchitectureFactoryImpl.eINSTANCE.createCloudEnvironment();
		
		Csm csm = CsmDiagramUtil.getCsmModel(getDiagram());
		csm.getArchitecture().getCloudProviders().add(environment);
		csm.eResource().setModified(true);
		
		environment.setCloudProviderDescriptor(CsmUtil.getSystemDescriptor(environment,descriptor));
		environment.setName("My "+environment.getCloudProviderDescriptor().getName());
		environment.setDescription("Cloud environment: "+environment.getCloudProviderDescriptor().getName());
		
		if(!environment.getCloudProviderDescriptor().getAvailabilityZones().isEmpty()){
			//set default availability zone
			environment.setAvailabilityZoneDescriptor(environment.getCloudProviderDescriptor().getAvailabilityZones().get(0));
		}
		
		environment.setInfrastructureLayer(ArchitectureFactoryImpl.eINSTANCE.createInfrastructureLayer());
		environment.setPlatformLayer(ArchitectureFactoryImpl.eINSTANCE.createPlatformLayer());
		environment.setSoftwareLayer(ArchitectureFactoryImpl.eINSTANCE.createSoftwareLayer());
		
		addGraphicalRepresentation(context, environment);
		return new Object[] { environment };
	}
}
