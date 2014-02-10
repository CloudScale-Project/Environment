package eu.cloudscaleproject.env.csm.diagram.features.add;

import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;

import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;

public class AddCloudEnvironmentFeature extends AbstractAddFeature implements IAddFeature {
	
	public static final ColorConstant BACKGROUND = new ColorConstant("eeeeee") ;
	public static final ColorConstant TEXT = new ColorConstant("000000") ;
	
	public AddCloudEnvironmentFeature(IFeatureProvider fp) {
		super(fp);
	}

	//@Override
	public boolean canAdd(IAddContext context) {
		// TODO: check for right domain object instance below
		return /* context.getNewObject() instanceof DomainObject && */ context.getTargetContainer() instanceof Diagram;
	}

	//@Override
	public PictogramElement add(IAddContext context) {

		CloudEnvironment provider = (CloudEnvironment)context.getNewObject();

		Diagram targetDiagram = (Diagram) context.getTargetContainer();
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();

		ContainerShape containerShape = peCreateService.createContainerShape(targetDiagram, true);
		
		RoundedRectangle roundedRectangle = gaService.createRoundedRectangle(containerShape, 5, 5);
		gaService.setLocationAndSize(roundedRectangle, context.getX(), context.getY(), context.getWidth(), context.getHeight());
		
		roundedRectangle.setBackground(gaService.manageColor(targetDiagram, BACKGROUND));
		// roundedRectangle.setStyle(StyleUtil.getStyleForCloudProvider(targetDiagram));
		  
		Shape shape = peCreateService.createShape(containerShape, false);
		Text text = gaService.createText(shape, provider.getName()==null ? "Name missing":provider.getName());
		text.setForeground(gaService.manageColor(targetDiagram, TEXT));
		text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
		gaService.setLocationAndSize(text, 5, 5, context.getWidth()-10, 20);

		peCreateService.createChopboxAnchor(containerShape);

		// TODO: enable the link to the domain object
		link(containerShape, provider);
		
		// Using PROVIDER layout
		layoutPictogramElement(containerShape);

		return containerShape;
	}
}
