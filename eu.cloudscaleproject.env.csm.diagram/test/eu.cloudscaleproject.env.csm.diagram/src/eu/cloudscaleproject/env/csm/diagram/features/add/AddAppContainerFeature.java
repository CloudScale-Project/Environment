package eu.cloudscaleproject.env.csm.diagram.features.add;


import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.util.ColorConstant;

import eu.cloudscaleproject.env.csm.architecture.ApplicationServer;
import eu.cloudscaleproject.env.csm.architecture.Module;

public class AddAppContainerFeature extends AbstractAddFeature{
	
	public static final ColorConstant BACKGROUND_CONTAINER = new ColorConstant("87afcb") ;
	public static final ColorConstant BACKGROUND_APPSERVER = new ColorConstant("456890") ;
	
	public static final ColorConstant TEXT_APPSERVER = new ColorConstant("FFFFFF") ;
	public static final ColorConstant TEXT_CONTAINER = new ColorConstant("111111") ;
	

	public AddAppContainerFeature(IFeatureProvider fp) {
		super(fp);
	}

//	@Override
	public boolean canAdd(IAddContext context) {
		// This feature supports to add a library object directly into the diagram
		if (context.getNewObject() instanceof ApplicationServer) {
			return true;
		}
		return false;
	}
	
//	@Override
	public PictogramElement add(IAddContext context) {

		// Get information from context
		ApplicationServer appServer = (ApplicationServer) context.getNewObject();
		ContainerShape parentShape = context.getTargetContainer();

		// Get Graphiti services for easier access
		IPeService peService = Graphiti.getPeService();
		IGaService gaService = Graphiti.getGaService();

		// Create a application server/service
		ContainerShape containerShape = peService.createContainerShape(parentShape, true);
		RoundedRectangle rectangle = gaService.createRoundedRectangle(containerShape, 5,5);
		gaService.setLocationAndSize(rectangle, context.getX(), context.getY(), context.getWidth(), context.getHeight());
		rectangle.setBackground(manageColor(BACKGROUND_APPSERVER));
		peService.createChopboxAnchor(containerShape);
		link(containerShape, appServer);

		// Add the name on top
		Shape containerName = peService.createShape(containerShape, false);
		Text libraryNameText = gaService.createText(containerName);
		libraryNameText.setForeground(manageColor(TEXT_APPSERVER));
		libraryNameText.setValue(appServer.getName());
		
		// RuntimeContainer
		ContainerShape runtimeContainer = peService.createContainerShape(containerShape, false);
		Rectangle rowRectangle = gaService.createRectangle(runtimeContainer);
		rowRectangle.setBackground(manageColor(BACKGROUND_CONTAINER));
		//link (runtimeContainer, appServer.getRuntimeContainer());
		
		// Modules
		for (Module module : appServer.getModules()) {
			Shape moduleNameShape = peService.createShape(runtimeContainer, false);
			Text moduleNameTxt = gaService.createText(moduleNameShape);
			moduleNameTxt.setForeground(manageColor(TEXT_CONTAINER));
			moduleNameTxt.setValue(module.getName());
		}
		
		// Using TITLE-CONTAINER(grid layout) layout
		layoutPictogramElement(containerShape);
		
		return containerShape;
	}
}
