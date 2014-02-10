package eu.cloudscaleproject.env.csm.diagram.features.create;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.architecture.CloudProvider;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.definition.CloudProviderDescriptor;
import eu.cloudscaleproject.env.csm.diagram.util.CsmUtil;

public class CreateCloudProviderFeature extends AbstractCreateFeature implements ICreateFeature{

	private final CloudProviderDescriptor descriptor;

	public CreateCloudProviderFeature(IFeatureProvider fp, CloudProviderDescriptor descriptor) {
		super(fp, descriptor.getName(), "Creates a new DomainObject");
		this.descriptor = descriptor;
	}
	
	public CloudProviderDescriptor getDescriptor() {
		return descriptor;
	}

//	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

//	@Override
	public Object[] create(ICreateContext context) {
		
		CloudProvider provider = ArchitectureFactoryImpl.eINSTANCE.createCloudProvider();
		
		Csm csm = CsmUtil.getCsmModel(getDiagram());
		csm.getArchitecture().getCloudProviders().add(provider);
		csm.eResource().setModified(true);
		
		provider.setCloudProviderDescriptor(CsmUtil.getSystemDescriptor(provider,descriptor));
		provider.setName("My "+provider.getCloudProviderDescriptor().getName());
		
		addGraphicalRepresentation(context, provider);
		return new Object[] { provider };
	}
}
