package org.scaledl.overview.diagram.features.add;

import org.eclipse.graphiti.features.IDirectEditingInfo;
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
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.architecture.ProvidedService;

public class AddSupportServiceFeature extends AbstractAddFeature{
	
	public static final ColorConstant BACKGROUND_DS_PLATFORM_SERVICE = new ColorConstant("87afcb") ;
	public static final ColorConstant BACKGROUND_ES_PLATFORM_SERVICE = new ColorConstant("afcfdb") ;
	
	public static final ColorConstant TEXT_DR_PLATFORM_SERVICE = new ColorConstant("FFFFFF") ;
	public static final ColorConstant TEXT_DS_PLATFORM_SERVICE = new ColorConstant("111111") ;
	public static final ColorConstant TEXT_ER_PLATFORM_SERVICE = new ColorConstant("111111") ;
	public static final ColorConstant TEXT_ES_PLATFORM_SERVICE = new ColorConstant("111111") ;
	

	public AddSupportServiceFeature(IFeatureProvider fp) {
		super(fp);
	}

//	@Override
	public boolean canAdd(IAddContext context) {
		// TODO: check for right domain object instance below
		return context.getTargetContainer() instanceof ContainerShape;
	}
	
//	@Override
	public PictogramElement add(IAddContext context) {

		OperationInterfaceContainer oic = (OperationInterfaceContainer)context.getNewObject();
		ContainerShape parentShape = context.getTargetContainer();
		Diagram diagram = Graphiti.getPeService().getDiagramForPictogramElement(parentShape);
		
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();

		ContainerShape containerShape = peCreateService.createContainerShape(parentShape, true);
		RoundedRectangle roundedRectangle = gaService.createRoundedRectangle(containerShape, 5, 5);
		gaService.setLocationAndSize(roundedRectangle, context.getX(), context.getY(), 
				Math.max(context.getWidth(),40), Math.max(context.getHeight(),20));
		
		if (oic instanceof ProvidedService){
			roundedRectangle.setBackground(gaService.manageColor(diagram, BACKGROUND_DS_PLATFORM_SERVICE));
		}
		else {
			roundedRectangle.setBackground(gaService.manageColor(diagram, BACKGROUND_ES_PLATFORM_SERVICE));
		}
		
		roundedRectangle.setLineVisible(true);
		roundedRectangle.setLineWidth(1);
		roundedRectangle.setForeground(gaService.manageColor(diagram, new ColorConstant("000000")));
		
		// TEXT
		{
			Shape shape = peCreateService.createShape(containerShape, false);
			Text text = gaService.createText(shape, oic.getName());
			text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
			text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
			
			if(oic instanceof ProvidedService){
				text.setForeground(gaService.manageColor(diagram, TEXT_ES_PLATFORM_SERVICE));
			}
			else{
				text.setForeground(gaService.manageColor(diagram, TEXT_DS_PLATFORM_SERVICE));
			}
			
			/*
			if (platformService instanceof ProvidedPlatformRuntimeService){
				text.setForeground(gaService.manageColor(diagram, TEXT_ER_PLATFORM_SERVICE));
			}
			else if (platformService instanceof ProvidedPlatformSupportService){
				text.setForeground(gaService.manageColor(diagram, TEXT_ES_PLATFORM_SERVICE));
			}
			else if (platformService instanceof PlatformRuntimeService){
				text.setForeground(gaService.manageColor(diagram, TEXT_DR_PLATFORM_SERVICE));
			}
			else if (platformService instanceof PlatformSupportService){
				text.setForeground(gaService.manageColor(diagram, TEXT_DS_PLATFORM_SERVICE));
			}
			*/
			
			gaService.setLocationAndSize(text, 0, 0, context.getWidth(), context.getHeight());
			
			// create link and wire it
			// link(shape, oic);
			
			// provide information to support direct-editing directly
			// after object creation (must be activated additionally)
			final IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();
			// set container shape for direct editing after object creation
			directEditingInfo.setMainPictogramElement(containerShape);
			// set shape and graphics algorithm where the editor for
			// direct editing shall be opened after object creation
			directEditingInfo.setPictogramElement(shape);
			directEditingInfo.setGraphicsAlgorithm(text);
		}

		peCreateService.createChopboxAnchor(containerShape);

		// TODO: enable the link to the domain object
		link(containerShape, oic);
		layoutPictogramElement(containerShape);

		return containerShape;
	}
}
