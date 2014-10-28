package org.scaledl.overview.diagram.features.add;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.util.ColorConstant;
import org.scaledl.overview.architecture.PlatformRuntimeService;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.architecture.SoftwareServiceContainer;

public class AddRuntimeServiceFeature extends AbstractAddFeature{
	
	public static final ColorConstant BACKGROUND_CONTAINER = new ColorConstant("87afcb") ;
	public static final ColorConstant BACKGROUND_APPSERVER = new ColorConstant("456890") ;
	
	public static final ColorConstant TEXT_APPSERVER = new ColorConstant("FFFFFF") ;
	public static final ColorConstant TEXT_CONTAINER = new ColorConstant("111111") ;
	
	public static final ColorConstant BACKGROUND_DR_PLATFORM_SERVICE = new ColorConstant("456890") ;
	public static final ColorConstant BACKGROUND_ER_PLATFORM_SERVICE = new ColorConstant("afcfdb") ;

	public AddRuntimeServiceFeature(IFeatureProvider fp) {
		super(fp);
	}

//	@Override
	public boolean canAdd(IAddContext context) {
		// This feature supports to add a library object directly into the diagram
		if (context.getNewObject() instanceof SoftwareServiceContainer) {
			return true;
		}
		return false;
	}
	
//	@Override
	public PictogramElement add(IAddContext context) {

		// Get information from context
		SoftwareServiceContainer softwareServiceContainer = (SoftwareServiceContainer) context.getNewObject();
		ContainerShape parentShape = context.getTargetContainer();
		Diagram diagram = Graphiti.getPeService().getDiagramForPictogramElement(parentShape);

		// Get Graphiti services for easier access
		IPeService peService = Graphiti.getPeService();
		IGaService gaService = Graphiti.getGaService();

		// Create a application server/service
		ContainerShape containerShape = peService.createContainerShape(parentShape, true);
		RoundedRectangle rectangle = gaService.createRoundedRectangle(containerShape, 5,5);
		gaService.setLocationAndSize(rectangle, context.getX(), context.getY(), context.getWidth(), context.getHeight());
		rectangle.setBackground(manageColor(BACKGROUND_APPSERVER));
		
		if (softwareServiceContainer instanceof PlatformRuntimeService){
			rectangle.setBackground(gaService.manageColor(diagram, BACKGROUND_DR_PLATFORM_SERVICE));
		}
		else {
			rectangle.setBackground(gaService.manageColor(diagram, BACKGROUND_ER_PLATFORM_SERVICE));
		}
		
		peService.createChopboxAnchor(containerShape);
		link(containerShape, softwareServiceContainer);

		// Add the name on top
		Shape containerName = peService.createShape(containerShape, false);
		Text libraryNameText = gaService.createText(containerName);
		libraryNameText.setForeground(manageColor(TEXT_APPSERVER));
		libraryNameText.setValue(softwareServiceContainer.getName());
		
		// RuntimeContainer
		ContainerShape runtimeContainer = peService.createContainerShape(containerShape, false);
		Rectangle rowRectangle = gaService.createRectangle(runtimeContainer);
		rowRectangle.setBackground(manageColor(BACKGROUND_CONTAINER));
		//link (runtimeContainer, appServer.getRuntimeContainer());
		
		// Modules
		for (SoftwareService dAppService : softwareServiceContainer.getSoftwareServices()) {
			Shape moduleNameShape = peService.createShape(runtimeContainer, true);
			Rectangle r = Graphiti.getGaService().createRectangle(moduleNameShape);
			r.setBackground(manageColor(BACKGROUND_APPSERVER));
			
			Text moduleNameTxt = gaService.createText(moduleNameShape);
			moduleNameTxt.setForeground(manageColor(TEXT_CONTAINER));
			moduleNameTxt.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
			moduleNameTxt.setLineVisible(true);
			moduleNameTxt.setValue(dAppService.getName());
			moduleNameTxt.setFilled(true);
			moduleNameTxt.setBackground(manageColor(new ColorConstant("FFFFFF")));
			moduleNameTxt.setTransparency(0.1);

			peService.createChopboxAnchor(moduleNameShape);
			
			link(moduleNameShape, dAppService);
			
		}
		
		// Using TITLE-CONTAINER(grid layout) layout
		layoutPictogramElement(containerShape);
		
		return containerShape;
	}
}
