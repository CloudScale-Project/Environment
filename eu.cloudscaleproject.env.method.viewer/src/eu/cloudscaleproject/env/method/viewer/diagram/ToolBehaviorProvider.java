package eu.cloudscaleproject.env.method.viewer.diagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IPlatformImageConstants;
import org.eclipse.graphiti.tb.ColorDecorator;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;
import org.eclipse.graphiti.util.IColorConstant;

import eu.cloudscaleproject.env.method.common.method.LinkedNode;
import eu.cloudscaleproject.env.method.common.method.Node;
import eu.cloudscaleproject.env.method.common.method.Requirement;
import eu.cloudscaleproject.env.method.common.method.SectionConnector;
import eu.cloudscaleproject.env.method.common.method.StatusNode;
import eu.cloudscaleproject.env.method.common.method.Warning;
import eu.cloudscaleproject.env.method.viewer.ToolStatusImpl;
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
		
		if (bo instanceof StatusNode) {
			StatusNode statusNode = (StatusNode) bo;			
			ToolStatusImpl status = new ToolStatusImpl();
			status.setStatusNode(statusNode);

			if(!statusNode.getWarnings().isEmpty()){
				for(Warning w : statusNode.getWarnings()) {
					ImageDecorator imageRenderingDecorator = new ImageDecorator(IPlatformImageConstants.IMG_ECLIPSE_WARNING_TSK);
					imageRenderingDecorator.setMessage(w.getMessage());
					imageRenderingDecorator.setX(pe.getGraphicsAlgorithm().getWidth() - 20);
					imageRenderingDecorator.setY(5);
					decorators.add(imageRenderingDecorator);
				}
				
				ColorDecorator cd = new ColorDecorator();
				cd.setBackgroundColor(IColorConstant.LIGHT_ORANGE);
				cd.setMessage("Section contains errors. Resove them before continue!");
				decorators.add(cd);
			}
			else if(!status.hasMetRequirements()){
				ColorDecorator cd = new ColorDecorator();
				cd.setBackgroundColor(IColorConstant.LIGHT_GRAY);
				cd.setMessage("Requirements has not been met.");
				decorators.add(cd);
			}
			else if(status.isInProgress()){
				ImageDecorator imageRenderingDecorator = new ImageDecorator(IPlatformImageConstants.IMG_ECLIPSE_QUICKASSIST);
				imageRenderingDecorator.setMessage("Work still has to be done on this node!");
				imageRenderingDecorator.setX(pe.getGraphicsAlgorithm().getWidth() - 20);
				imageRenderingDecorator.setY(5);
				decorators.add(imageRenderingDecorator);
			}
			else if(status.isDone()){
				ColorDecorator cd = new ColorDecorator();
				cd.setBackgroundColor(IColorConstant.LIGHT_GREEN);
				cd.setMessage("Section successfully compleated.");
				decorators.add(cd);
			}
			
			if(status.isDirty()){
				ImageDecorator imageRenderingDecorator = new ImageDecorator(IPlatformImageConstants.IMG_EDIT_REFRESH);
				imageRenderingDecorator.setMessage("Current work on this node is out of sync, based on it's required nodes!");
				imageRenderingDecorator.setX(pe.getGraphicsAlgorithm().getWidth() - 20);
				imageRenderingDecorator.setY(5);
				decorators.add(imageRenderingDecorator);
			}
			
			status.dispose();
						
		}
		else if(bo instanceof Requirement){
			Requirement r = (Requirement)bo;
			if(r.isDone()){
				ColorDecorator cd = new ColorDecorator();
				cd.setBackgroundColor(IColorConstant.LIGHT_GREEN);
				cd.setMessage("Section successfully compleated.");
				decorators.add(cd);
			}
			else{
				ColorDecorator cd = new ColorDecorator();
				cd.setBackgroundColor(IColorConstant.LIGHT_ORANGE);
				cd.setMessage("Section contains errors. Resove them before continue!");
				decorators.add(cd);
			}
		}
		
		return decorators.toArray(new IDecorator[decorators.size()]);
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
				if(node instanceof LinkedNode){
					StringBuilder sb1 = new StringBuilder();
					sb1.append("Required: ");
					
					LinkedNode ln = (LinkedNode)node;
					for (SectionConnector sc : ln.getPrevious()) {
						if (sc.isRequired() && sc.getStart() instanceof Node) {
							sb1.append(((Node)sc.getStart()).getName());
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
