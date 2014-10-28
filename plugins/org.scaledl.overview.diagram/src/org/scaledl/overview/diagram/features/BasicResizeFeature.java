package org.scaledl.overview.diagram.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.impl.DefaultResizeShapeFeature;
import org.scaledl.overview.architecture.ProvidedSoftwareService;
import org.scaledl.overview.architecture.Proxy;
import org.scaledl.overview.architecture.SoftwareService;

public class BasicResizeFeature extends DefaultResizeShapeFeature{

	public BasicResizeFeature(IFeatureProvider fp) {
		super (fp);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean canResizeShape(IResizeShapeContext context) {
		
		// Don't allow Proxy to be resized -- Anti-aliasing feature missing
		Object o = getBusinessObjectForPictogramElement(context.getShape());
		
		if(o instanceof ProvidedSoftwareService){
			return super.canResizeShape(context);
		}
		
		if (o instanceof Proxy || o instanceof SoftwareService)
			return false;
		
		return super.canResizeShape(context);
	}
	
}
