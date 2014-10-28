package org.scaledl.overview.diagram.features.create;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.scaledl.overview.Overview;
import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.ProvidedSoftwareService;
import org.scaledl.overview.architecture.ServiceProxy;
import org.scaledl.overview.diagram.util.OverviewDiagramUtil;

public class CreateServiceProxyFeature extends AbstractCreateFeature implements ICreateFeature {

	public CreateServiceProxyFeature(IFeatureProvider fp) {
		super(fp, "Service proxy", "Creates a new service proxy.");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		
		return context.getTargetContainer() instanceof Diagram;
		
	}

	@Override
	public Object[] create(ICreateContext context) {
		
		Overview overview = OverviewDiagramUtil.getOverviewModel(getDiagram());
		
		ServiceProxy serviceProxy = ArchitectureFactory.eINSTANCE.createServiceProxy();
		serviceProxy.setName("Service proxy");
		serviceProxy.setDescription("External service proxy");
		overview.getArchitecture().getProxies().add(serviceProxy);
		overview.getArchitecture().eResource().setModified(true);
		
		ProvidedSoftwareService ess = ArchitectureFactory.eINSTANCE.createExternalSoftwareService();
		ess.setName("External software service");
		ess.setDescription("External software service : through Service Proxy");
		
		
		serviceProxy.setSoftwareService(ess);
		
		addGraphicalRepresentation(context, serviceProxy);
		return new Object[] { serviceProxy };
	}
}
