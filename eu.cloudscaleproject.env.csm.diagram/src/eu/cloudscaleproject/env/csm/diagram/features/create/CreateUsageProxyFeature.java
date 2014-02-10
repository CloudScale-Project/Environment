package eu.cloudscaleproject.env.csm.diagram.features.create;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.architecture.Proxy;
import eu.cloudscaleproject.env.csm.architecture.UsageProxy;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.diagram.util.CsmDiagramUtil;

public class CreateUsageProxyFeature extends AbstractCreateFeature implements ICreateFeature {

	public CreateUsageProxyFeature(IFeatureProvider fp) {
		super(fp, "Usage proxy", "Creates a new usage proxy.");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		
		// Allow only one UsageProxy
		Csm csm = CsmDiagramUtil.getCsmModel(getDiagram());
		for (Proxy proxy : csm.getArchitecture().getProxies()) {
			if (proxy instanceof UsageProxy) return false;
		}
		
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		
		Csm csm = CsmDiagramUtil.getCsmModel(getDiagram());
		
		UsageProxy usageProxy = ArchitectureFactoryImpl.eINSTANCE.createUsageProxy();
		usageProxy.setName("Usage proxy");
		csm.getArchitecture().getProxies().add(usageProxy);
		csm.getArchitecture().eResource().setModified(true);
		
		addGraphicalRepresentation(context, usageProxy);
		return new Object[] { usageProxy };
	}
}
