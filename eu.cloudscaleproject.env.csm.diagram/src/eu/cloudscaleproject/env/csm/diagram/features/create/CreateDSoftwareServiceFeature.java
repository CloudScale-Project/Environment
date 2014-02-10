package eu.cloudscaleproject.env.csm.diagram.features.create;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;

import eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService;
import eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;

public class CreateDSoftwareServiceFeature extends AbstractCreateFeature implements ICreateFeature {

	public CreateDSoftwareServiceFeature(IFeatureProvider fp, String name, String description) {
		super(fp, name, description);
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		PictogramLink link = context.getTargetContainer().getLink();
		
		if(link != null){
			Object o = (link.getBusinessObjects().isEmpty()) ? null : link.getBusinessObjects().get(0);
			
			if(o instanceof SoftwareServiceContainer)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public Object[] create(ICreateContext context) {
		DeployableSoftwareService daService = ArchitectureFactoryImpl.eINSTANCE.createDeployableSoftwareService();
		
		PictogramLink link = context.getTargetContainer().getLink();
		Object o = (link.getBusinessObjects().isEmpty()) ? null : link.getBusinessObjects().get(0);
		SoftwareServiceContainer ps = (SoftwareServiceContainer)o;
		ps.getSoftwareServices().add(daService);
		
		return new Object[]{daService};
	}

}
