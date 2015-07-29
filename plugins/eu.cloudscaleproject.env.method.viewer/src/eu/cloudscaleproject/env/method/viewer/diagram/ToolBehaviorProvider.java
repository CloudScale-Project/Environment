package eu.cloudscaleproject.env.method.viewer.diagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.ColorDecorator;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;
import org.eclipse.graphiti.util.ColorConstant;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.method.common.method.Link;
import eu.cloudscaleproject.env.method.common.method.LinkedObject;
import eu.cloudscaleproject.env.method.common.method.Node;
import eu.cloudscaleproject.env.method.common.method.Requirement;
import eu.cloudscaleproject.env.method.common.method.Section;
import eu.cloudscaleproject.env.method.common.method.StatusNode;
import eu.cloudscaleproject.env.method.common.method.Warning;
import eu.cloudscaleproject.env.method.common.util.MethodColorConstants;
import eu.cloudscaleproject.env.method.viewer.diagram.features.CommandFeature;

public class ToolBehaviorProvider extends DefaultToolBehaviorProvider{
			
	public ToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}
	
	private void advancePosition(int[] pos){
		pos[0] = pos[0]-18;
		pos[1] = pos[1];
	}
	
	@SuppressWarnings("unused")
	private void advancePositionMin(int[] pos){
		pos[0] = pos[0]-4;
		pos[1] = pos[1];
	}
	
	private void advancePositionRequ(int[] pos){
		pos[0] = pos[0]-10;
		pos[1] = pos[1];
	}
		
	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		IFeatureProvider featureProvider = getFeatureProvider();		
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		
		List<IDecorator> decorators = new ArrayList<IDecorator>();
		
		if(!(bo instanceof StatusNode)){
			return decorators.toArray(new IDecorator[decorators.size()]);
		}
		
		StatusNode statusNode = (StatusNode)bo;
		synchronized (statusNode) {
			collectDecorators(pe, statusNode, decorators);
		}
		
		return decorators.toArray(new IDecorator[decorators.size()]);
	}
	
	private void collectDecorators(PictogramElement pe, StatusNode statusNode, List<IDecorator> decorators){
		
		Section section = null;
		if(statusNode instanceof Section){
			section = (Section)statusNode;
		}
		
		//sort warnings by severity
		List<Warning> errors = new ArrayList<Warning>();
		List<Warning> warnings = new ArrayList<Warning>();
		List<Warning> infos = new ArrayList<Warning>();
		
		for(Warning w : statusNode.getWarnings()){
			if(IValidationStatus.SEVERITY_INFO == w.getSeverity()){
				infos.add(w);
			}
			else if(IValidationStatus.SEVERITY_WARNING == w.getSeverity()){
				warnings.add(w);
			}
			else{
				errors.add(w);
			}
		}
		
		//image decorations position (x,y)
		int[] position = new int[]{
				pe.getGraphicsAlgorithm().getWidth() - 5,
				5
		};
				
		//handle requirements
		if(statusNode instanceof Requirement){
						
			Requirement r = (Requirement)statusNode;
			Section parent = null;
			
			{
				EObject reqContainer = r.eContainer();
				if(reqContainer instanceof Section){
					parent = (Section)reqContainer;
				}
			}
			
			if(parent != null && !parent.isInProgress()){
				//do not draw decorations
			}
			else if(parent != null && !calcHasMetRequirements(parent)){
				//do not draw decorations
			}
			else if(r.isDone()){
				
				advancePositionRequ(position);
				
				ImageDecorator imageRenderingDecorator = new ImageDecorator(DiagramImageProvider.IMG_DONE_SMALL);
				imageRenderingDecorator.setMessage("Task is done.");
				imageRenderingDecorator.setX(position[0]);
				imageRenderingDecorator.setY(position[1]);
				decorators.add(imageRenderingDecorator);
				
				ColorDecorator cd = new ColorDecorator();
				cd.setBackgroundColor(new ColorConstant(MethodColorConstants.REQUIREMENT_DONE));
				cd.setMessage("Section successfully compleated.");
				decorators.add(cd);
			}
			else if(!errors.isEmpty()){
				
				advancePositionRequ(position);
				
				for(Warning w : errors) {
					ImageDecorator imageRenderingDecorator = new ImageDecorator(DiagramImageProvider.IMG_WARNING_SMALL);
					imageRenderingDecorator.setMessage(w.getMessage());
					imageRenderingDecorator.setX(position[0]);
					imageRenderingDecorator.setY(position[1]);
					decorators.add(imageRenderingDecorator);
					
					//show only first
					//advancePositionRequ(position);
					break;
				}
				
				ColorDecorator cd = new ColorDecorator();
				cd.setBackgroundColor(new ColorConstant(MethodColorConstants.REQUIREMENT_ERROR));
				cd.setMessage("Section contains errors. Resove them before continue!");
				decorators.add(cd);
			}
			else if(parent != null && parent.isInProgress()){
				
				advancePositionRequ(position);
				
				ImageDecorator imageRenderingDecorator = new ImageDecorator(DiagramImageProvider.IMG_NOT_DONE);
				imageRenderingDecorator.setMessage("Requirement is missing!");
				imageRenderingDecorator.setX(position[0]);
				imageRenderingDecorator.setY(position[1]);
				decorators.add(imageRenderingDecorator);
				
				/*
				ColorDecorator cd = new ColorDecorator();
				cd.setBackgroundColor(new ColorConstant(MethodColorConstants.REQUIREMENT_WARNING));
				cd.setMessage("Requirement missing!");
				decorators.add(cd);
				*/
			}
		}
		else{
			
			if(!errors.isEmpty()){
				
				advancePosition(position);
				
				for(Warning w : errors) {
					ImageDecorator imageRenderingDecorator = new ImageDecorator(DiagramImageProvider.IMG_WARNING);
					imageRenderingDecorator.setMessage(w.getMessage());
					imageRenderingDecorator.setX(position[0]);
					imageRenderingDecorator.setY(position[1]);
					decorators.add(imageRenderingDecorator);
					
					//show only first
					//advancePositionRequ(position);
					break;
				}
				
				ColorDecorator cd = new ColorDecorator();
				cd.setBackgroundColor(new ColorConstant(MethodColorConstants.STATUS_NODE_ERROR));
				cd.setMessage("Section contains errors. Resove them before continue!");
				decorators.add(cd);
			}
			else if(section != null && !calcHasMetRequirements(section)){
				ColorDecorator cd = new ColorDecorator();
				cd.setBackgroundColor(new ColorConstant(MethodColorConstants.STATUS_NODE_DISABLED));
				cd.setMessage("Requirements has not been met.");
				decorators.add(cd);
			}
			else if(statusNode.isDone()){
				
				advancePosition(position);
				
				ImageDecorator imageRenderingDecorator = new ImageDecorator(DiagramImageProvider.IMG_DONE);
				imageRenderingDecorator.setMessage("Task is done.");
				imageRenderingDecorator.setX(position[0]);
				imageRenderingDecorator.setY(position[1]);
				decorators.add(imageRenderingDecorator);
				
				ColorDecorator cd = new ColorDecorator();
				cd.setBackgroundColor(new ColorConstant(MethodColorConstants.STATUS_NODE_DONE));
				cd.setMessage("Section successfully compleated.");
				decorators.add(cd);
			}
			else if(section != null && section.isInProgress()){
				
				advancePosition(position);
				
				ImageDecorator imageRenderingDecorator = new ImageDecorator(DiagramImageProvider.IMG_NOT_DONE);
				imageRenderingDecorator.setMessage("Work still has to be done on this node!");
				imageRenderingDecorator.setX(position[0]);
				imageRenderingDecorator.setY(position[1]);
				decorators.add(imageRenderingDecorator);
			}
			
			if(statusNode.isDirty()){
				
				advancePosition(position);
				
				ImageDecorator imageRenderingDecorator = new ImageDecorator(DiagramImageProvider.IMG_REFRESH);
				imageRenderingDecorator.setMessage("Current work on this node is out of sync. Please save resources!");
				imageRenderingDecorator.setX(position[0]);
				imageRenderingDecorator.setY(position[1]);
				decorators.add(imageRenderingDecorator);
			}
		}
		
		//add warning and info decorators		
		if(!warnings.isEmpty()){
			
			advancePosition(position);
			
			for(Warning w : warnings) {
				ImageDecorator imageRenderingDecorator = new ImageDecorator(DiagramImageProvider.IMG_WARNING);
				imageRenderingDecorator.setMessage(w.getMessage());
				imageRenderingDecorator.setX(position[0]);
				imageRenderingDecorator.setY(position[1]);
				decorators.add(imageRenderingDecorator);
				
				if(errors.isEmpty()){
					ColorDecorator cd = new ColorDecorator();
					cd.setBackgroundColor(new ColorConstant(MethodColorConstants.STATUS_NODE_WARNING));
					cd.setMessage("Section contains errors. Resove them before continue!");
					decorators.add(cd);
				}
				
				//show only first
				//advancePositionRequ(position);
				break;
			}
		}
		
		if(!infos.isEmpty()){
			
			advancePosition(position);
			
			for(Warning w : infos) {
				ImageDecorator imageRenderingDecorator = new ImageDecorator(DiagramImageProvider.IMG_WARNING);
				imageRenderingDecorator.setMessage(w.getMessage());
				imageRenderingDecorator.setX(position[0]);
				imageRenderingDecorator.setY(position[1]);
				decorators.add(imageRenderingDecorator);
				
				//show only first
				//advancePositionRequ(position);
				break;
			}
		}
	}
	
	public boolean calcHasMetRequirements(Section section){
		for(Link link : section.getPrevious()){
			if(link.isRequired()){
				LinkedObject lo = link.getStart();
				if(lo instanceof StatusNode){
					StatusNode requiredStatusNode = (StatusNode)lo;
					if(!requiredStatusNode.isDone()){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public Object getToolTip(GraphicsAlgorithm ga) {
		PictogramElement pe = ga.getPictogramElement();
		Object bo = getFeatureProvider().getBusinessObjectForPictogramElement(pe);
		if (bo instanceof Node) {
			Node node = (Node)bo;
			
			StringBuilder sb = new StringBuilder();
			sb.append("Node: ").append(node.getName()).append("\n");
			if(!node.getTooltip().isEmpty()){
				sb.append(node.getTooltip()).append("\n");
				sb.append("\n");
			}
			sb.append("Description: ").append(node.getDescription()).append("\n");

			{

				boolean hasRequired = false;
				if(node instanceof LinkedObject){
					StringBuilder sb1 = new StringBuilder();
					sb1.append("Required: ");
					
					LinkedObject linkedObject = (LinkedObject)node;
					for (Link link : linkedObject.getPrevious()) {
						if (link.isRequired() && link.getStart() instanceof Node) {
							sb1.append(((Node)link.getStart()).getName());
							sb1.append(", ");
							hasRequired = true;
						}
					}
					if (hasRequired) {
						sb1.deleteCharAt(sb1.lastIndexOf(", "));
						sb1.append("\n");
						sb.append(sb1.toString());
					}
				}
			}
		
			return sb.toString();
		}
		return super.getToolTip(ga);
	}
	
	@Override
	public ICustomFeature getDoubleClickFeature(IDoubleClickContext context) {
		return new CommandFeature(getFeatureProvider());
	}
	
	@Override
	public IContextButtonPadData getContextButtonPad(
			IPictogramElementContext context) {
		return null;
	}
	
	@Override
	public boolean isShowFlyoutPalette() {
		return false;
	}
}
