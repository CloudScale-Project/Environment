package eu.cloudscaleproject.env.method.common.diagram.patterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeService;

import eu.cloudscaleproject.env.method.common.method.Command;
import eu.cloudscaleproject.env.method.common.method.Container;
import eu.cloudscaleproject.env.method.common.method.Method;
import eu.cloudscaleproject.env.method.common.method.Requirement;
import eu.cloudscaleproject.env.method.common.method.Section;
import eu.cloudscaleproject.env.method.common.util.MethodUtil;

public class SectionPattern extends NodePattern {
	
	public String getCreateName() {
		return "Section";
	}

	public boolean isMainBusinessObjectApplicable(Object mainBusinessObject) {
		return mainBusinessObject instanceof Section;
	}

	protected boolean isPatternControlled(PictogramElement pictogramElement) {
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
		return isMainBusinessObjectApplicable(domainObject);
	}

	protected boolean isPatternRoot(PictogramElement pictogramElement) {
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
		return isMainBusinessObjectApplicable(domainObject);
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		PictogramLink link = context.getTargetContainer().getLink();
		if(link == null){
			return true;
		}
		Object o = link.getBusinessObjects().get(0);
		if(o instanceof Container){
			return true;
		}
		return false;
	}

	@Override
	public Object[] create(ICreateContext context) {
		
		Method method = MethodUtil.getMethodModel(getDiagram());
		Section s = MethodUtil.createSection();
		
		PictogramLink link = context.getTargetContainer().getLink();
		
		if(link != null){
			Container c = (Container)link.getBusinessObjects().get(0);
			c.getChildren().add(s);
		}
		else{
			method.getNodes().add(s);
		}
		
		addGraphicalRepresentation(context, s);
		return new Object[]{s};
	}
	
	@Override
	public PictogramElement add(IAddContext context) {
		
		ContainerShape root = (ContainerShape)super.add(context);
		PatternUtil.addShapeID(root, "section");
		
		// Get Graphiti services for easier access
		IPeService peService = Graphiti.getPeService();
		
		// Get information from context
		Section section = (Section) context.getNewObject();

		for(Command command : section.getCommands()){
			addCommand(root, command, 5, 5, 15, 15);
		}
		
		for(Requirement requirement : section.getRequirements()){
			addRequirement(root, requirement, 5, 20, 100, 20);
		}
		
		link(root, section);
		peService.createChopboxAnchor(root);

		return root;
	}
	
	private void addCommand(ContainerShape root, Command command, int x, int y, int w, int h){
		AddContext context = new AddContext();
		context.setLocation(x, y);
		context.setSize(w, h);
		context.setNewObject(command);
		context.setTargetContainer(root);
		IAddFeature addFeature = getFeatureProvider().getAddFeature(context);
		addFeature.add(context);
	}
	
	private Shape getCommand(ContainerShape root, Command command){
		for(Shape shape : root.getChildren()){
			Object bo = getBusinessObjectForPictogramElement(shape);
			if(bo instanceof Command && ((Command)bo).getId().equals(command.getId())){
				return shape;
			}
		}
		return null;
	}
	
	private void addRequirement(ContainerShape root, Requirement command, int x, int y, int w, int h){
		AddContext context = new AddContext();
		context.setLocation(x, y);
		context.setSize(w, h);
		context.setNewObject(command);
		context.setTargetContainer(root);
		IAddFeature addFeature = getFeatureProvider().getAddFeature(context);
		addFeature.add(context);
	}
	
	private Shape getRequirement(ContainerShape root, Requirement requirement){
		for(Shape shape : root.getChildren()){
			Object bo = getBusinessObjectForPictogramElement(shape);
			if(bo instanceof Requirement && ((Requirement)bo).getId().equals(requirement.getId())){
				return shape;
			}
		}
		return null;
	}
	
	@Override
	public boolean layout(ILayoutContext context) {
		
		super.layout(context);
		
		ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
		IGaService gaService = Graphiti.getGaService();
		
		int yPosition = 25;
		int xPosition = 5;

		//position commands
		{
			int xPadding = 2;
			int yPadding = 2;
			int rowHeight = 20;
			
			int posx = xPosition;
			int posy = yPosition;
			
			//sort commands
			List<Shape> sortedCommandShapes = new ArrayList<Shape>();
			for(Shape shape : containerShape.getChildren()){
				Object bo = getBusinessObjectForPictogramElement(shape);
				if(bo instanceof Command){
					sortedCommandShapes.add(shape);
				}
			}
			Collections.sort(sortedCommandShapes, new Comparator<Shape>() {

				@Override
				public int compare(Shape arg0, Shape arg1) {
					Command c1 = (Command)getBusinessObjectForPictogramElement(arg0);
					Command c2 = (Command)getBusinessObjectForPictogramElement(arg1);
					
					return c1.getPosition() - c2.getPosition();
				}
			});
			//
			
			for(Shape shape : sortedCommandShapes){
				Object bo = getBusinessObjectForPictogramElement(shape);
				
				if(bo instanceof Command){
					
					Command command = (Command)bo;
					int width = (command.getName().length()+2) * 7;
					width = width < 20 ? 20 : width;
					
					if(containerShape.getGraphicsAlgorithm().getWidth() < posx + width){
						posy += rowHeight + yPadding;
						posx = xPadding;
					}
					
					gaService.setLocationAndSize(shape.getGraphicsAlgorithm(), posx, posy, width, rowHeight);
					posx += xPadding + width;
					
					ILayoutContext c = new LayoutContext(shape);
					getFeatureProvider().getLayoutFeature(c).execute(c);
				}
			}
			
			yPosition = posy + rowHeight;
		}
		
		yPosition += 8;
		
		//position requirements
		{
			int yPadding = 2;
			int yStart = yPosition;
			int rowHeight = 20;
			
			int posx = xPosition;
			int posy = yStart;
			
			//sort requirements
			List<Shape> sortedRequShapes = new ArrayList<Shape>();
			for(Shape shape : containerShape.getChildren()){
				Object bo = getBusinessObjectForPictogramElement(shape);
				if(bo instanceof Requirement){
					sortedRequShapes.add(shape);
				}
			}
			Collections.sort(sortedRequShapes, new Comparator<Shape>() {

				@Override
				public int compare(Shape arg0, Shape arg1) {
					Requirement c1 = (Requirement)getBusinessObjectForPictogramElement(arg0);
					Requirement c2 = (Requirement)getBusinessObjectForPictogramElement(arg1);
					
					return c1.getPosition() - c2.getPosition();
				}
			});
			//
			
			for(Shape shape : sortedRequShapes){
				Object bo = getBusinessObjectForPictogramElement(shape);
				
				if(bo instanceof Requirement){
					int width = containerShape.getGraphicsAlgorithm().getWidth() - 2*xPosition;
					gaService.setLocationAndSize(shape.getGraphicsAlgorithm(), posx, posy, width, rowHeight);
					posy += yPadding + rowHeight;
					
					ILayoutContext c = new LayoutContext(shape);
					getFeatureProvider().getLayoutFeature(c).execute(c);
				}
			}
			yPosition += yPadding + rowHeight + 4;
		}
		
		return true;
	}
	
	@Override
	public IReason updateNeeded(IUpdateContext context) {
		
		PictogramElement pictogramElement = context.getPictogramElement();
        if (pictogramElement instanceof ContainerShape) {
            ContainerShape cs = (ContainerShape) pictogramElement;
            
            Section section = (Section)getBusinessObjectForPictogramElement(cs);
            
            //check new commands
            for(Command command : section.getCommands()){
            	Shape childShape = getCommand(cs, command);
            	if(childShape == null){
            		return Reason.createTrueReason("New command node: " + command.getName());
            	}
            }
            
            //check new requirements
            for(Requirement requirement : section.getRequirements()){
            	Shape childShape = getRequirement(cs, requirement);
            	if(childShape == null){
            		return Reason.createTrueReason("New requirement node: " + requirement.getName());
            	}
            }
            
            //check removed commands & requirements
            for(Shape shape : cs.getChildren()){
            	if(PatternUtil.hasShapeID(shape, "node")){
            		Object bo = getBusinessObjectForPictogramElement(shape);
            		if(bo == null){
                		return Reason.createTrueReason("Node removed");
            		}
            	}
            }
        }
        
		return super.updateNeeded(context);
	}
	
	@Override
	public boolean update(IUpdateContext context) {
		
		super.update(context);
		
		PictogramElement pictogramElement = context.getPictogramElement();
        if (pictogramElement instanceof ContainerShape) {
            ContainerShape root = (ContainerShape) pictogramElement;
            
            Section section = (Section)getBusinessObjectForPictogramElement(root);
            
            //check new commands
            for(Command command : section.getCommands()){
            	Shape childShape = getCommand(root, command);
            	if(childShape == null){
            		addCommand(root, command, 5, 5, 15, 15);
            	}
            }
            
            //check new requirements
            for(Requirement requirement : section.getRequirements()){
            	Shape childShape = getRequirement(root, requirement);
            	if(childShape == null){
            		addRequirement(root, requirement, 5, 5, 100, 15);
            	}
            }
            
            //check removed commands & requirements
            for(Shape shape : root.getChildren()){
            	if(PatternUtil.hasShapeID(shape, "node")){
            		Object bo = getBusinessObjectForPictogramElement(shape);
            		if(bo == null){
            			IRemoveContext rc = new RemoveContext(shape);
                		getFeatureProvider().getRemoveFeature(rc);
            		}
            	}
            }
            
            ILayoutContext c = new LayoutContext(root);
			ILayoutFeature layoutFeature = getFeatureProvider().getLayoutFeature(c);
			if(layoutFeature != null && layoutFeature.canExecute(c)){
				layoutFeature.execute(c);
			}
            
            return true;
        }
        
        return false;
	}
}
