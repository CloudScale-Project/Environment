package org.scaledl.overview.diagram.features.create;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.ProvidedService;
import org.scaledl.overview.architecture.ProvidedSoftwareService;
import org.scaledl.overview.deployment.Deployment;
import org.scaledl.overview.deployment.DeploymentFactory;
import org.scaledl.overview.deployment.GenericDeployment;
import org.scaledl.overview.specification.Descriptor;
import org.scaledl.overview.specification.ProvidedSoftwareServiceDescriptor;
import org.scaledl.overview.util.OverviewSpecificationUtil;
import org.scaledl.overview.util.OverviewUtil;

public class CreateProvidedSoftwareServiceFeature extends AbstractCreateFeature implements ICreateFeature {

	private final ProvidedSoftwareServiceDescriptor descriptor;
	
	public CreateProvidedSoftwareServiceFeature(IFeatureProvider fp, ProvidedSoftwareServiceDescriptor descriptor) {
		super(fp, descriptor.getName(), descriptor.getDescription());
		this.descriptor = descriptor;
	}
	
	public Descriptor getDescriptor(){
		return this.descriptor;
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		PictogramLink link = context.getTargetContainer().getLink();
		
		if(link != null){
			Object o = (link.getBusinessObjects().isEmpty()) ? null : link.getBusinessObjects().get(0);
			
			if(o instanceof CloudEnvironment)
			{
				if (descriptor.getProviderID() == null){
					return true;
				}
				if (((CloudEnvironment)o).getCloudEnvironmentDescriptor().getProviderID().equals(descriptor.getProviderID())){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Object[] create(ICreateContext context) {
		
		ProvidedSoftwareService service = ArchitectureFactory.eINSTANCE.createProvidedSoftwareService();

		
		PictogramLink link = context.getTargetContainer().getLink();
		Object o = (link.getBusinessObjects().isEmpty()) ? null : link.getBusinessObjects().get(0);
		
		if(o != null && o instanceof CloudEnvironment){
			CloudEnvironment ce = (CloudEnvironment)o;
			
			ProvidedSoftwareServiceDescriptor desc = 
					OverviewSpecificationUtil.getSystemDescriptor(ce, (ProvidedSoftwareServiceDescriptor)descriptor);
			
			service.setName(desc.getName());
			service.setDescription(desc.getDescription());
			service.setDescriptor(desc);
			
			//create generic deployment
			GenericDeployment gd = DeploymentFactory.eINSTANCE.createGenericDeployment();
			((ProvidedService)service).setDeployment(gd);
			Deployment deployment = OverviewUtil.getDeployment(ce);
			deployment.getServiceDeployments().add(gd);
			
			ce.getSoftwareLayer().getServices().add(service);
			
			addGraphicalRepresentation(context, service);
		}
		
		return new Object[]{service};
	}

}
