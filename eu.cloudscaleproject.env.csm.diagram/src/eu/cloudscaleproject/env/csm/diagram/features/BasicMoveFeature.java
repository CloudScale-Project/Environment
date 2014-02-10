package eu.cloudscaleproject.env.csm.diagram.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.impl.MoveShapeContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.architecture.SoftwareService;

public class BasicMoveFeature extends DefaultMoveShapeFeature{

	public BasicMoveFeature(IFeatureProvider fp) {
		super (fp);
	}
	
	@Override
	public boolean canMoveShape(IMoveShapeContext context) {
		
		Object o = getBusinessObjectForPictogramElement(context.getShape());
		if (o instanceof SoftwareService)
			return false;
		return super.canMoveShape(context);
	}
	
	@Override
	public void moveShape(IMoveShapeContext context) {
		// TODO Auto-generated method stub
		ContainerShape shape = (ContainerShape) context.getPictogramElement();
		Object obj = getBusinessObjectForPictogramElement(shape);
		
		if (obj instanceof PlatformService)
		{
			ContainerShape container = shape.getContainer(); 
			
			int w = container.getGraphicsAlgorithm().getWidth();
			int h = container.getGraphicsAlgorithm().getHeight();
			
			int x = Math.max (context.getX(), 5);
			int y = Math.max (context.getY(), 5);
			
			x = Math.min(w-5-shape.getGraphicsAlgorithm().getWidth(), x);
			y = Math.min(h-5-shape.getGraphicsAlgorithm().getHeight(), y);
			
			
			((MoveShapeContext)context).setX(x);
			((MoveShapeContext)context).setY(y);
			
		}
		
		
		
		super.moveShape(context);
	}
}
