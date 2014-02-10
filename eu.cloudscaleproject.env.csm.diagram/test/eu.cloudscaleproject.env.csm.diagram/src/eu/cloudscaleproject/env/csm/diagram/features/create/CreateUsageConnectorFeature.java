package eu.cloudscaleproject.env.csm.diagram.features.create;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.architecture.UsageConnector;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.diagram.util.CsmUtil;

public class CreateUsageConnectorFeature extends AbstractCreateFeature implements ICreateFeature {

	public CreateUsageConnectorFeature(IFeatureProvider fp) {
		super(fp, "Usage connector", "Creates a new usage connector.");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		
		return context.getTargetContainer() instanceof Diagram;
		
	}

	@Override
	public Object[] create(ICreateContext context) {
		
		Csm csm = CsmUtil.getCsmModel(getDiagram());
		
		UsageConnector usageConnector = ArchitectureFactoryImpl.eINSTANCE.createUsageConnector();
		// usageConnector.setName("Usage connector for: "+.getName());
		csm.getArchitecture().getConnectors().add(usageConnector);
		csm.getArchitecture().eResource().setModified(true);
		
		addGraphicalRepresentation(context, usageConnector);
		return new Object[] { usageConnector };
	}
}
