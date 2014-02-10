package eu.cloudscaleproject.env.csm.diagram.features.create;

import org.eclipse.graphiti.features.IFeatureProvider;

import eu.cloudscaleproject.env.csm.architecture.CloudProvider;
import eu.cloudscaleproject.env.csm.architecture.Component;
import eu.cloudscaleproject.env.csm.architecture.Service;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.definition.ServiceDescriptor;
import eu.cloudscaleproject.env.csm.diagram.util.CsmUtil;

public class CreateServiceFeature extends CreateComponentFeature {

	
	public CreateServiceFeature (IFeatureProvider fp, ServiceDescriptor descriptor)
	{
		super (fp, descriptor);
	}
	
	@Override
	protected Component createComponent(CloudProvider cp) {
		Service service = ArchitectureFactoryImpl.eINSTANCE.createService();
		ServiceDescriptor sd = CsmUtil.getSystemDescriptor(cp, (ServiceDescriptor)descriptor);
		service.setDescriptor(sd);
		service.setName("My "+descriptor.getName());
		
		return service;
	}

}
