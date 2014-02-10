package eu.cloudscaleproject.env.csm.diagram.features.create;

import org.eclipse.graphiti.features.IFeatureProvider;

import eu.cloudscaleproject.env.csm.architecture.Appliance;
import eu.cloudscaleproject.env.csm.architecture.CloudProvider;
import eu.cloudscaleproject.env.csm.architecture.Component;
import eu.cloudscaleproject.env.csm.architecture.Instance;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.definition.ApplianceDescriptor;
import eu.cloudscaleproject.env.csm.diagram.util.CsmUtil;

public class CreateApplianceFeature extends CreateComponentFeature {

	
	public CreateApplianceFeature (IFeatureProvider fp, ApplianceDescriptor descriptor)
	{
		super (fp, descriptor);
	}
	
	@Override
	protected Component createComponent(CloudProvider cp) {
		Appliance appliance = ArchitectureFactoryImpl.eINSTANCE.createAppliance();
		ApplianceDescriptor ad = CsmUtil.getSystemDescriptor(cp, (ApplianceDescriptor)descriptor);
		appliance.setDescriptor(ad);
		appliance.setName(descriptor.getName());
		
		Instance instance = CsmUtil.createDefaultInstance(cp);
		appliance.setInstance(instance);
		
		return appliance;
	}
}
