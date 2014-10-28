package eu.cloudscaleproject.env.method.common.diagram.patterns;

import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.algorithms.styles.impl.StylesFactoryImpl;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeService;

import eu.cloudscaleproject.env.method.common.method.Action;
import eu.cloudscaleproject.env.method.common.method.Container;
import eu.cloudscaleproject.env.method.common.method.Method;
import eu.cloudscaleproject.env.method.common.method.Node;
import eu.cloudscaleproject.env.method.common.util.MethodUtil;

public class ActionPattern extends NodePattern{

	@Override
	public String getCreateName() {
		return "Action";
	}

	public boolean isMainBusinessObjectApplicable(Object mainBusinessObject) {
		return (mainBusinessObject instanceof Action);
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
		Action action = MethodUtil.createAction();
		
		PictogramLink link = context.getTargetContainer().getLink();
		if(link != null){
			Container container = (Container)link.getBusinessObjects().get(0);
			container.getChildren().add(action);
		}
		else{
			Method method = MethodUtil.getMethodModel(getDiagram());
			method.getNodes().add(action);
		}
		
		addGraphicalRepresentation(context, action);
		return new Object[]{action};
	}
	
	@Override
	public PictogramElement add(IAddContext context) {
		PictogramElement root = super.add(context);
		PatternUtil.addShapeID(root, "action");
		
		// Get Graphiti services for easier access
		IPeService peService = Graphiti.getPeService();
		peService.createChopboxAnchor((ContainerShape)root);
		
		return root;
	}
	
	protected ContainerShape addNode(Node node, ContainerShape parent, int x, int y, int w, int h){
		// Get Graphiti services for easier access
		IPeService peService = Graphiti.getPeService();
		IGaService gaService = Graphiti.getGaService();
				
		ContainerShape containerShape = peService.createContainerShape(parent, true);
		PatternUtil.addShapeID(containerShape, "node");

		link(containerShape, node);

		Polygon polygon = gaService.createPolygon(containerShape, new int[]{0,0, w,0, w+30,h/2, w,h, 0,h});
				//createRoundedRectangle(containerShape, 15, 15);
		gaService.setLocationAndSize(polygon, x, y, w, h);
		polygon.setBackground(getColorBackground(node));
		polygon.setForeground(getColorForeground(node));
			
		addName(node, containerShape, 5, 0, w-5, h);
		
		return containerShape;
	}
	
	@Override
	protected void addName(Node node, ContainerShape root, int x, int y, int w, int h){
		// Get Graphiti services for easier access
		IPeService peService = Graphiti.getPeService();
		IGaService gaService = Graphiti.getGaService();
		
		Shape shape = peService.createShape(root, false);
		PatternUtil.addShapeID(shape, "node_name");
		
		Text textName = gaService.createText(shape, node.getName());
		textName.setForeground(getColorText(node));
		textName.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
		textName.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
		gaService.setLocationAndSize(textName, x, y, w, h);
	}
	
	@Override
	public boolean layout(ILayoutContext context) {
		ContainerShape root = (ContainerShape)context.getPictogramElement();
		Shape nameShape = getName((ContainerShape)context.getPictogramElement());

		//int x = root.getGraphicsAlgorithm().getX();
		//int y = root.getGraphicsAlgorithm().getY();
		int x = 0;
		int y = 0;
		int w = root.getGraphicsAlgorithm().getWidth();
		int h = root.getGraphicsAlgorithm().getHeight();
		
		Polyline p = (Polyline)root.getGraphicsAlgorithm();
		p.getPoints().clear();

		Point p1 = StylesFactoryImpl.eINSTANCE.createPoint();
		p1.setX(x);
		p1.setY(y);
		p.getPoints().add(p1);
		Point p2 = StylesFactoryImpl.eINSTANCE.createPoint();
		p2.setX(x+w-w/4);
		p2.setY(y);
		p.getPoints().add(p2);
		Point p3 = StylesFactoryImpl.eINSTANCE.createPoint();
		p3.setX(x+w);
		p3.setY(y+h/2);
		p.getPoints().add(p3);
		Point p4 = StylesFactoryImpl.eINSTANCE.createPoint();
		p4.setX(x+w-w/4);
		p4.setY(y+h);
		p.getPoints().add(p4);
		Point p5 = StylesFactoryImpl.eINSTANCE.createPoint();
		p5.setX(x);
		p5.setY(y+h);
		p.getPoints().add(p5);
		
		Text text = (Text)nameShape.getGraphicsAlgorithm();
		text.setX(5);
		text.setY(0);
		text.setWidth(root.getGraphicsAlgorithm().getWidth()-5);
		text.setHeight(root.getGraphicsAlgorithm().getHeight());

		text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
		text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
		return true;
	}
	
}
