package eu.cloudscaleproject.env.method.common.diagram.patterns;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.pattern.AbstractPattern;
import org.eclipse.graphiti.pattern.IPattern;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.util.ColorConstant;

import eu.cloudscaleproject.env.method.common.method.Container;
import eu.cloudscaleproject.env.method.common.method.Method;
import eu.cloudscaleproject.env.method.common.method.MethodFactory;
import eu.cloudscaleproject.env.method.common.method.Node;
import eu.cloudscaleproject.env.method.common.method.StatusNode;
import eu.cloudscaleproject.env.method.common.util.MethodUtil;

public class NodePattern extends AbstractPattern implements IPattern {
	
	public String getCreateName() {
		return "Status node";
	}
	
	public Color getColorBackground(Node node){
		return manageColor(new ColorConstant(node.getColorBackground()));
	}
	
	public Color getColorForeground(Node node){
		return manageColor(new ColorConstant(node.getColorForeground()));
	}
	
	public Color getColorText(Node node){
		return manageColor(new ColorConstant(node.getColorText()));
	}
	
	public boolean isMainBusinessObjectApplicable(Object mainBusinessObject) {
		return (mainBusinessObject instanceof StatusNode);
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
	public boolean canAdd(IAddContext context) {
		return isMainBusinessObjectApplicable(context.getNewObject());
	}
	
	@Override
	public boolean canUpdate(IUpdateContext context) {
		return isPatternControlled(context.getPictogramElement());
	}
	
	@Override
	public boolean canLayout(ILayoutContext context) {
		
		Object bo = getBusinessObjectForPictogramElement(context.getPictogramElement());
		if(bo instanceof Node){
			boolean canLayout = ((Node)bo).isLayout();
			return canLayout && isPatternControlled(context.getPictogramElement());
		}
		return isPatternControlled(context.getPictogramElement());
	}
	
	@Override
	public boolean canCreate(ICreateContext context) {
		PictogramLink link = context.getTargetContainer().getLink();
		if(link == null){
			return true;
		}
		Object o = link.getBusinessObjects().get(0);
		if(o instanceof Node){
			return true;
		}
		return false;
	}

	@Override
	public Object[] create(ICreateContext context) {
		
		Method method = MethodUtil.getMethodModel(getDiagram());
		Node n = MethodFactory.eINSTANCE.createStatusNode();
		
		PictogramLink link = context.getTargetContainer().getLink();
		if(link != null){
			Container parent = (Container)link.getBusinessObjects().get(0);
			parent.getChildren().add(n);
		}
		else{
			method.getNodes().add(n);
		}
		
		addGraphicalRepresentation(context, n);
		return new Object[]{n};
	}
	
	@Override
	public PictogramElement add(IAddContext context) {
		
		Node node = (Node) context.getNewObject();
		ContainerShape parentContainer = (ContainerShape) context.getTargetContainer();
		
		return addNode(node, parentContainer, context.getX(), 
											  context.getY(), 
											  context.getWidth(), 
											  context.getHeight());
		
	}
	
	protected ContainerShape addNode(Node node, ContainerShape parent, int x, int y, int w, int h){
		// Get Graphiti services for easier access
		IPeService peService = Graphiti.getPeService();
		IGaService gaService = Graphiti.getGaService();
				
		ContainerShape containerShape = peService.createContainerShape(parent, true);
		PatternUtil.addShapeID(containerShape, "node");

		link(containerShape, node);

		RoundedRectangle roundedRectangle = gaService.createRoundedRectangle(containerShape, 15, 15);
		gaService.setLocationAndSize(roundedRectangle, x, y, w, h);
		roundedRectangle.setBackground(getColorBackground(node));
		roundedRectangle.setForeground(getColorForeground(node));
			
		addName(node, containerShape, 5, 5, w-5, 20);
		
		return containerShape;
	}
	
	protected void addName(Node node, ContainerShape root, int x, int y, int w, int h){
		// Get Graphiti services for easier access
		IPeService peService = Graphiti.getPeService();
		IGaService gaService = Graphiti.getGaService();
		
		Shape shape = peService.createShape(root, false);
		PatternUtil.addShapeID(shape, "node_name");
		
		Text textName = gaService.createText(shape, node.getName());
		textName.setForeground(getColorText(node));
		textName.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
		gaService.setLocationAndSize(textName, x, y, w, h);
	}
	
	protected Shape getName(ContainerShape root){
		return PatternUtil.findChild(root, "node_name");
	}
	
	protected void setName(ContainerShape root, String name){
		Shape shape = getName(root);
		Text text = (Text)shape.getGraphicsAlgorithm();
		text.setValue(name);
		
		Node node = (Node)getBusinessObjectForPictogramElement(root);
		text.setForeground(manageColor(new ColorConstant(node.getColorForeground())));
	}
	
	@Override
	public void preDelete(IDeleteContext context) {
		PictogramLink link = context.getPictogramElement().getLink();
		EObject o = (link.getBusinessObjects().isEmpty()) ? null : link.getBusinessObjects().get(0);
		EcoreUtil.delete(o);
		super.preDelete(context);
	}
	
	@Override
	public boolean layout(ILayoutContext context) {
		ContainerShape containerShape = (ContainerShape) context.getPictogramElement();

		//position name
		GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();
		Graphiti.getGaLayoutService().setLocationAndSize(getName(containerShape).getGraphicsAlgorithm(),
				5, 5, containerGa.getWidth()-5, 20, true);
		return true;
	}
	
	@Override
	public IReason updateNeeded(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		if(pictogramElement instanceof ContainerShape){
			ContainerShape root = (ContainerShape)pictogramElement;
			Node node = (Node)getBusinessObjectForPictogramElement(pictogramElement);
			
			//check colors
			if(!PatternUtil.comapreColorEquals(root.getGraphicsAlgorithm().getBackground(), getColorBackground(node))){
				return Reason.createTrueReason("Color changed");
			}
			if(!PatternUtil.comapreColorEquals(root.getGraphicsAlgorithm().getForeground(), getColorForeground(node))){
				return Reason.createTrueReason("Color changed");
			}
			if(!PatternUtil.comapreColorEquals(getName(root).getGraphicsAlgorithm().getForeground(), getColorText(node))){
				return Reason.createTrueReason("Color changed");
			}
			
			//check name
			Shape shapeName = getName(root);
			Text text = (Text)shapeName.getGraphicsAlgorithm();
			if(!text.getValue().equals(node.getName())){
				return Reason.createTrueReason("Name is out of date");
			}
		}
		return Reason.createFalseReason();
	}
	
	@Override
	public boolean update(IUpdateContext context) {
		Node node = (Node)getBusinessObjectForPictogramElement(context.getPictogramElement());
		
		PictogramElement pictogramElement = context.getPictogramElement();
		
		//update colors
		pictogramElement.getGraphicsAlgorithm().setBackground(getColorBackground(node));
		pictogramElement.getGraphicsAlgorithm().setForeground(getColorForeground(node));
		
		//update name
		if(pictogramElement instanceof ContainerShape){
			setName((ContainerShape)pictogramElement, node.getName());
			//update text name color
			getName((ContainerShape)pictogramElement).getGraphicsAlgorithm().setForeground(getColorText(node));
			return true;
		}
		return false;
	}

}
