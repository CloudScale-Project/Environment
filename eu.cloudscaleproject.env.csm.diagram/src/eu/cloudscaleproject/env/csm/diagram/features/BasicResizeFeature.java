package eu.cloudscaleproject.env.csm.diagram.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.impl.DefaultResizeShapeFeature;

import eu.cloudscaleproject.env.csm.architecture.Proxy;
import eu.cloudscaleproject.env.csm.architecture.SoftwareService;

public class BasicResizeFeature extends DefaultResizeShapeFeature{

	public BasicResizeFeature(IFeatureProvider fp) {
		super (fp);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean canResizeShape(IResizeShapeContext context) {
		
		// Don't allow Proxy to be resized -- Anti-aliasing feature missing
		Object o = getBusinessObjectForPictogramElement(context.getShape());
		
		if (o instanceof Proxy || o instanceof SoftwareService)
			return false;
		
		return super.canResizeShape(context);
	}
	
}
