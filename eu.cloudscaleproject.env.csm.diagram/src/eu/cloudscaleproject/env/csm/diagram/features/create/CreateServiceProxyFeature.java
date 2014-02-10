package eu.cloudscaleproject.env.csm.diagram.features.create;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.architecture.ExternalSoftwareService;
import eu.cloudscaleproject.env.csm.architecture.ServiceProxy;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.diagram.util.CsmDiagramUtil;

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
		
		Csm csm = CsmDiagramUtil.getCsmModel(getDiagram());
		
		ServiceProxy serviceProxy = ArchitectureFactoryImpl.eINSTANCE.createServiceProxy();
		serviceProxy.setName("Service proxy");
		serviceProxy.setDescription("External service proxy");
		csm.getArchitecture().getProxies().add(serviceProxy);
		csm.getArchitecture().eResource().setModified(true);
		
		ExternalSoftwareService ess = ArchitectureFactoryImpl.eINSTANCE.createExternalSoftwareService();
		ess.setName("External software service");
		ess.setDescription("External software service : through Service Proxy");
		serviceProxy.setSoftwareService(ess);
		
		addGraphicalRepresentation(context, serviceProxy);
		return new Object[] { serviceProxy };
	}
}
