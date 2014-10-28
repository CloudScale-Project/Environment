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

import eu.cloudscaleproject.env.method.common.method.Command;
import eu.cloudscaleproject.env.method.common.method.Container;
import eu.cloudscaleproject.env.method.common.method.Method;
import eu.cloudscaleproject.env.method.common.method.Node;
import eu.cloudscaleproject.env.method.common.util.MethodUtil;

public class ContainerPattern extends NodePattern {
	
	public String getCreateName() {
		return "Container";
	}

	public boolean isMainBusinessObjectApplicable(Object mainBusinessObject) {
		return (mainBusinessObject instanceof Container);
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
		Container c = MethodUtil.createContainer();
		
		PictogramLink link = context.getTargetContainer().getLink();
		if(link != null){
			Container parent = (Container)link.getBusinessObjects().get(0);
			parent.getChildren().add(c);
		}
		else{
			method.getNodes().add(c);
		}
		
		addGraphicalRepresentation(context, c);
		return new Object[]{c};
	}
	
	@Override
	public PictogramElement add(IAddContext context) {
		Container container = (Container) context.getNewObject();
		ContainerShape root = (ContainerShape)super.add(context);
		PatternUtil.addShapeID(root, "container");
	
		int x = 5;
		int y = 30;
		for(Node node : container.getChildren()){
			addChild(node, root, x, y, 100, 20);
			y += 23;
		}
		
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
	
	private void addChild(Node node, ContainerShape root, int x, int y, int w, int h){		
		AddContext context = new AddContext();
		context.setLocation(x, y);
		context.setSize(w, h);
		context.setNewObject(node);
		context.setTargetContainer(root);
		IAddFeature addFeature = getFeatureProvider().getAddFeature(context);
		addFeature.add(context);
	}
	
	private Shape getChild(ContainerShape root, Node node){
		for(Shape shape : root.getChildren()){
			Object bo = getBusinessObjectForPictogramElement(shape);
			if(bo == node){
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
		
		int yPosition = 5;
		int xPosition = containerShape.getGraphicsAlgorithm().getWidth() - 5;

		//position commands
		{
			int xPadding = 5;
			int yPadding = 5;
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
					
					if(80 > posx - width){
						posy += rowHeight + yPadding;
						posx = xPadding;
					}
					
					gaService.setLocationAndSize(shape.getGraphicsAlgorithm(), posx - width, posy, width, rowHeight);
					posx -= (xPadding + width);
					
					ILayoutContext c = new LayoutContext(shape);
					getFeatureProvider().getLayoutFeature(c).execute(c);
				}
			}
			
			yPosition = posy + rowHeight;
		}
		
		return true;
	}
	
	@Override
	public IReason updateNeeded(IUpdateContext context) {
		
		PictogramElement pictogramElement = context.getPictogramElement();
        if (pictogramElement instanceof ContainerShape) {
            ContainerShape cs = (ContainerShape) pictogramElement;
            
            Container container = (Container)getBusinessObjectForPictogramElement(cs);
            
            //check new children
            for(Node childNode : container.getChildren()){
            	Shape childShape = getChild(cs, childNode);
            	if(childShape == null){
            		return Reason.createTrueReason("New child node: " + childNode.getName());
            	}
            }
            
            //check new commands
            for(Command command : container.getCommands()){
            	Shape childShape = getCommand(cs, command);
            	if(childShape == null){
            		return Reason.createTrueReason("New command node: " + command.getName());
            	}
            }
            
            //check removed commands || children nodes
            for(Shape shape : cs.getChildren()){
            	if(PatternUtil.hasShapeID(shape, "node")){
            		Object bo = getBusinessObjectForPictogramElement(shape);
            		if(bo == null){
                		return Reason.createTrueReason("Child node removed");
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
            Container container = (Container)getBusinessObjectForPictogramElement(root);
            
            //check new commands
            for(Command command : container.getCommands()){
            	Shape childShape = getCommand(root, command);
            	if(childShape == null){
            		addCommand(root, command, 5, 5, 15, 15);
            	}
            }
            
            
            //add
            for(Node childNode : container.getChildren()){
            	Shape childShape = getChild(root, childNode);
            	if(childShape == null){
            		addChild(childNode, root, 5, 20, 100, 20);
            	}
            }
            
            //remove
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
