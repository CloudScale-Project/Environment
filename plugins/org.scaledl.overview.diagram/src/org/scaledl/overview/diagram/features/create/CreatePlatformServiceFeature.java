package org.scaledl.overview.diagram.features.create;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.scaledl.overview.application.ApplicationFactory;
import org.scaledl.overview.application.OperationInterface;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.ComputingInfrastructureService;
import org.scaledl.overview.architecture.PlatformService;
import org.scaledl.overview.architecture.ProvidedService;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.architecture.SoftwareServiceContainer;
import org.scaledl.overview.deployment.ComputingEnvironment;
import org.scaledl.overview.deployment.Deployment;
import org.scaledl.overview.deployment.DeploymentFactory;
import org.scaledl.overview.deployment.GenericDeployment;
import org.scaledl.overview.deployment.RuntimeDeployment;
import org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.Descriptor;
import org.scaledl.overview.specification.ProvidedPlatformServiceDescriptor;
import org.scaledl.overview.util.OverviewArchitectureUtil;
import org.scaledl.overview.util.OverviewSpecificationUtil;
import org.scaledl.overview.util.OverviewUtil;

public abstract class CreatePlatformServiceFeature extends AbstractCreateFeature implements
		ICreateFeature {
	
	protected final Descriptor descriptor;

	public CreatePlatformServiceFeature(IFeatureProvider fp, Descriptor descriptor) {
		super(fp, descriptor.getName(), "Creates a new DomainObject");
		this.descriptor = descriptor;
	}
	
	public Descriptor getDescriptor() {
		return descriptor;
	}

//	@Override
	public boolean canCreate(ICreateContext context) {
		PictogramLink link = context.getTargetContainer().getLink();
		
		if(link != null){
			Object o = (link.getBusinessObjects().isEmpty()) ? null : link.getBusinessObjects().get(0);
			
			if(o instanceof CloudEnvironment)
			{
				if (descriptor.getProviderID() == null) return true;
				
				if (((CloudEnvironment)o).getCloudEnvironmentDescriptor().getProviderID().equals(descriptor.getProviderID()))
				return true;
			}
		}
		return false;
	}

//	@Override
	public Object[] create(ICreateContext context) {
		// TODO: in case of an EMF object add the new object to a suitable resource
		PictogramLink link = context.getTargetContainer().getLink();
		Object o = (link.getBusinessObjects().isEmpty()) ? null : link.getBusinessObjects().get(0);
		CloudEnvironment ce = (CloudEnvironment)o;
		
		PlatformService ps = createPlatformService(ce);
		
		if(ps.getDescriptor() instanceof ProvidedPlatformServiceDescriptor){
			//create deployment			
			
			ComputingInfrastructureServiceDescriptor cisd = 
					((ProvidedPlatformServiceDescriptor)ps.getDescriptor()).getInfrastructureServiceDescriptor();
			if(cisd != null){
				
				RuntimeDeployment rd = DeploymentFactory.eINSTANCE.createRuntimeDeployment();
				ComputingEnvironment compEnv = DeploymentFactory.eINSTANCE.createComputingEnvironment();
				compEnv.setInstanceDescriptor(cisd.getComputingResourceDescriptors().get(0));
				rd.setComputingEnvironment(compEnv);
				
				((ProvidedService)ps).setDeployment(rd);
				
				Deployment deployment = OverviewUtil.getDeployment(ce);
				deployment.getServiceDeployments().add(rd);
			}
			else{
				GenericDeployment gd = DeploymentFactory.eINSTANCE.createGenericDeployment();
				
				((ProvidedService)ps).setDeployment(gd);
				
				Deployment deployment = OverviewUtil.getDeployment(ce);
				deployment.getServiceDeployments().add(gd);
			}
		}
		else{
			ComputingInfrastructureServiceDescriptor cisd = 
					OverviewSpecificationUtil.helperGetFistGeneralPurposeComputingInfrastructureServiceDescriptor(ce.getCloudEnvironmentDescriptor().getProviderID());
			ComputingInfrastructureService cis = OverviewArchitectureUtil.createComputingInfrastructureService(ce, cisd);
			cis.getPlatformServices().add(ps);			
			ce.getInfrastructureLayer().getServices().add(cis);
		}
		
		//add empty operation interface
		if(ps instanceof OperationInterfaceContainer){
			OperationInterfaceContainer oic = (OperationInterfaceContainer)ps;
			if(oic.getProvidedInterfaces().isEmpty()){
				OperationInterface opInt = ApplicationFactory.eINSTANCE.createOperationInterface();
				opInt.setName("I_" + ps.getName());
				oic.getProvidedInterfaces().add(opInt);
			}
		}
		else if(ps instanceof SoftwareServiceContainer){
			SoftwareServiceContainer ssc = (SoftwareServiceContainer)ps;
			
			SoftwareService applicationService = ArchitectureFactory.eINSTANCE.createSoftwareService();
			
			OperationInterface operationInterface = ApplicationFactory.eINSTANCE.createOperationInterface();
			operationInterface.setName("I_AppService_1");
			applicationService.getProvidedInterfaces().add(operationInterface);
			
			applicationService.setName("App service");
			applicationService.setDescription("Software service");

			ssc.getSoftwareServices().add(applicationService);
			ce.getSoftwareLayer().getServices().add(applicationService);
		}
		
		ce.getPlatformLayer().getServices().add(ps);
		ce.eResource().setModified(true);
		
		// activate direct editing after object creation
		// getFeatureProvider().getDirectEditingInfo().setActive(true);
		
		addGraphicalRepresentation(context, ps);
		return new Object[] { ps };
	}
	
	protected abstract PlatformService createPlatformService (CloudEnvironment ce);
}
