package org.scaledl.overview.diagram.features.layout;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;


public class LayoutBasicFeature extends AbstractLayoutFeature implements
		ILayoutFeature {

	private static final int MIN_HEIGHT = 40;
	private static final int MIN_WIDTH = 60;

	public LayoutBasicFeature(IFeatureProvider fp) {
		super(fp);
	}

//	@Override
	public boolean canLayout(ILayoutContext context) {
		// TODO: check for right domain object instances below
		return context.getPictogramElement() instanceof ContainerShape /* && getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof <DomainObject> */;
	}

//	@Override
	public boolean layout(ILayoutContext context) {
		ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
		GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();
		
		// Min size
		int width = Math.max( MIN_WIDTH, containerGa.getWidth());
		int height = Math.max( MIN_HEIGHT, containerGa.getHeight());
		Graphiti.getGaLayoutService().setSize(containerShape.getGraphicsAlgorithm(), width, height);
		
		// Center child : TODO: generalize
		GraphicsAlgorithm outerGraphicsAlgorithm = containerShape.getGraphicsAlgorithm();
		if (outerGraphicsAlgorithm instanceof RoundedRectangle) {
			RoundedRectangle roundedRectangle = (RoundedRectangle) outerGraphicsAlgorithm;
			EList<Shape> children = containerShape.getChildren();
			if (children.size() > 0) {
				Shape shape = children.get(0);
				GraphicsAlgorithm graphicsAlgorithm = shape.getGraphicsAlgorithm();
				if (graphicsAlgorithm instanceof Text) {
					Graphiti.getGaLayoutService().setLocationAndSize(graphicsAlgorithm, 0, 0, roundedRectangle.getWidth(), roundedRectangle.getHeight());
					return true;
				}
			}
		}
		return false;
	}
}
