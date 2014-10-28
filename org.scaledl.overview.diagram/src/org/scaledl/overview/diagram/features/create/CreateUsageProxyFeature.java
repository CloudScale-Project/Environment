package org.scaledl.overview.diagram.features.create;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.scaledl.overview.Overview;
import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.Proxy;
import org.scaledl.overview.architecture.UsageProxy;
import org.scaledl.overview.diagram.util.OverviewDiagramUtil;

public class CreateUsageProxyFeature extends AbstractCreateFeature implements ICreateFeature {

	public CreateUsageProxyFeature(IFeatureProvider fp) {
		super(fp, "Usage proxy", "Creates a new usage proxy.");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		
		// Allow only one UsageProxy
		Overview overview = OverviewDiagramUtil.getOverviewModel(getDiagram());
		for (Proxy proxy : overview.getArchitecture().getProxies()) {
			if (proxy instanceof UsageProxy) return false;
		}
		
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		
		Overview overview = OverviewDiagramUtil.getOverviewModel(getDiagram());
		
		org.scaledl.overview.architecture.UsageProxy usageProxy = ArchitectureFactory.eINSTANCE.createUsageProxy();
		usageProxy.setName("Usage proxy");
		overview.getArchitecture().getProxies().add(usageProxy);
		overview.getArchitecture().eResource().setModified(true);
		
		addGraphicalRepresentation(context, usageProxy);
		return new Object[] { usageProxy };
	}
}
