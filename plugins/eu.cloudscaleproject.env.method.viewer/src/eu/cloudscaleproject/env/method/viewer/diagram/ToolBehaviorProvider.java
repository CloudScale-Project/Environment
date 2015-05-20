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
	
	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		IFeatureProvider featureProvider = getFeatureProvider();		
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		
		List<IDecorator> decorators = new ArrayList<IDecorator>();
		
		if(!(bo instanceof StatusNode)){
			return decorators.toArray(new IDecorator[decorators.size()]);
		}
		
		StatusNode statusNode = (StatusNode)bo;
		Section section = null;
		if(bo instanceof Section){
			section = (Section)bo;
		}
				
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
				
				ImageDecorator imageRenderingDecorator = new ImageDecorator(DiagramImageProvider.IMG_DONE_SMALL);
				imageRenderingDecorator.setMessage("Task is done.");
				imageRenderingDecorator.setX(pe.getGraphicsAlgorithm().getWidth() - 20);
				imageRenderingDecorator.setY(5);
				decorators.add(imageRenderingDecorator);
				
				ColorDecorator cd = new ColorDecorator();
				cd.setBackgroundColor(new ColorConstant(MethodColorConstants.REQUIREMENT_DONE));
				cd.setMessage("Section successfully compleated.");
				decorators.add(cd);
			}
			else if(!r.getWarnings().isEmpty()){
				for(Warning w : r.getWarnings()) {
					ImageDecorator imageRenderingDecorator = new ImageDecorator(DiagramImageProvider.IMG_WARNING_SMALL);
					imageRenderingDecorator.setMessage(w.getMessage());
					imageRenderingDecorator.setX(pe.getGraphicsAlgorithm().getWidth() - 20);
					imageRenderingDecorator.setY(5);
					decorators.add(imageRenderingDecorator);
				}
				
				ColorDecorator cd = new ColorDecorator();
				cd.setBackgroundColor(new ColorConstant(MethodColorConstants.REQUIREMENT_WARNING));
				cd.setMessage("Section contains errors. Resove them before continue!");
				decorators.add(cd);
			}
			else if(parent != null && parent.isInProgress()){
				ImageDecorator imageRenderingDecorator = new ImageDecorator(DiagramImageProvider.IMG_MISSING_SMALL);
				imageRenderingDecorator.setMessage("Requirement is missing!");
				imageRenderingDecorator.setX(pe.getGraphicsAlgorithm().getWidth() - 20);
				imageRenderingDecorator.setY(5);
				decorators.add(imageRenderingDecorator);
				
				ColorDecorator cd = new ColorDecorator();
				cd.setBackgroundColor(new ColorConstant(MethodColorConstants.REQUIREMENT_WARNING));
				cd.setMessage("Requirement missing!");
				decorators.add(cd);
			}
		}
		else{
			if(!statusNode.getWarnings().isEmpty()){
				for(Warning w : statusNode.getWarnings()) {
					ImageDecorator imageRenderingDecorator = new ImageDecorator(DiagramImageProvider.IMG_WARNING);
					imageRenderingDecorator.setMessage(w.getMessage());
					imageRenderingDecorator.setX(pe.getGraphicsAlgorithm().getWidth() - 20);
					imageRenderingDecorator.setY(5);
					decorators.add(imageRenderingDecorator);
				}
				
				ColorDecorator cd = new ColorDecorator();
				cd.setBackgroundColor(new ColorConstant(MethodColorConstants.STATUS_NODE_WARNING));
				cd.setMessage("Section contains errors. Resove them before continue!");
				decorators.add(cd);
			}
			else if(section != null && !calcHasMetRequirements(section)){
				ColorDecorator cd = new ColorDecorator();
				cd.setBackgroundColor(new ColorConstant(MethodColorConstants.STATUS_NODE_DISABLED));
				cd.setMessage("Requirements has not been met.");
				decorators.add(cd);
			}
			else if(statusNode.isDirty()){
				ImageDecorator imageRenderingDecorator = new ImageDecorator(DiagramImageProvider.IMG_REFRESH);
				imageRenderingDecorator.setMessage("Current work on this node is out of sync. Please save resources!");
				imageRenderingDecorator.setX(pe.getGraphicsAlgorithm().getWidth() - 20);
				imageRenderingDecorator.setY(5);
				decorators.add(imageRenderingDecorator);
			}
			else if(statusNode.isDone()){
				ImageDecorator imageRenderingDecorator = new ImageDecorator(DiagramImageProvider.IMG_DONE);
				imageRenderingDecorator.setMessage("Task is done.");
				imageRenderingDecorator.setX(pe.getGraphicsAlgorithm().getWidth() - 20);
				imageRenderingDecorator.setY(5);
				decorators.add(imageRenderingDecorator);
				
				ColorDecorator cd = new ColorDecorator();
				cd.setBackgroundColor(new ColorConstant(MethodColorConstants.STATUS_NODE_DONE));
				cd.setMessage("Section successfully compleated.");
				decorators.add(cd);
			}
			else if(section != null && section.isInProgress()){
				ImageDecorator imageRenderingDecorator = new ImageDecorator(DiagramImageProvider.IMG_NOT_DONE);
				imageRenderingDecorator.setMessage("Work still has to be done on this node!");
				imageRenderingDecorator.setX(pe.getGraphicsAlgorithm().getWidth() - 20);
				imageRenderingDecorator.setY(5);
				decorators.add(imageRenderingDecorator);
			}					
		}
		
		return decorators.toArray(new IDecorator[decorators.size()]);
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
