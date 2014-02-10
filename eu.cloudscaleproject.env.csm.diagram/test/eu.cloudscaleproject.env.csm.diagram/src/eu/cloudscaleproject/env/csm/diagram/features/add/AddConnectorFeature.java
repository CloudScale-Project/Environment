package eu.cloudscaleproject.env.csm.diagram.features.add;


import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

import eu.cloudscaleproject.env.csm.architecture.Connector;
import eu.cloudscaleproject.env.csm.diagram.util.StyleUtil;

public class AddConnectorFeature extends AbstractAddFeature{

	public AddConnectorFeature(IFeatureProvider fp) {
		super(fp);
	}

//	@Override
	public boolean canAdd(IAddContext context) {
		// TODO: check for right domain object instance below
		return context.getTargetContainer() instanceof ContainerShape;
	}
	
//	@Override
	public PictogramElement add(IAddContext context) {

		Connector connector = (Connector)context.getNewObject();
		
		ContainerShape parentShape = context.getTargetContainer();
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();

		ContainerShape containerShape = peCreateService.createContainerShape(parentShape, true);
		RoundedRectangle roundedRectangle = gaService.createRoundedRectangle(containerShape, 20, 20);
		gaService.setLocationAndSize(roundedRectangle, context.getX(), context.getY(), context.getWidth(), context.getHeight());
		roundedRectangle.setStyle(StyleUtil.getStyleForConnector(Graphiti.getPeService().getDiagramForPictogramElement(parentShape)));
		
		// Shape shape = peCreateService.createShape(containerShape, false);
		// Text text = gaService.createText(shape, component.getName());
		// text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		// text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
		// gaService.setLocationAndSize(text, 0, 0, context.getWidth(), context.getHeight());

		peCreateService.createChopboxAnchor(containerShape);

		// TODO: enable the link to the domain object
		link(containerShape, connector);

		return containerShape;
	}
}
