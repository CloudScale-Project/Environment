package eu.cloudscaleproject.env.method.viewer.diagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
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

import eu.cloudscaleproject.env.method.common.method.Link;
import eu.cloudscaleproject.env.method.common.method.LinkedObject;
import eu.cloudscaleproject.env.method.common.method.Node;
import eu.cloudscaleproject.env.method.common.method.Requirement;
import eu.cloudscaleproject.env.method.common.method.Section;
import eu.cloudscaleproject.env.method.common.method.StatusNode;
import eu.cloudscaleproject.env.method.common.method.Warning;
import eu.cloudscaleproject.env.method.viewer.StatusServiceImpl;
import eu.cloudscaleproject.env.method.viewer.ToolStatusImpl;
import eu.cloudscaleproject.env.method.viewer.diagram.features.CommandFeature;

public class ToolBehaviorProvider extends DefaultToolBehaviorProvider{
			
	public ToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}
	
	public IProject getProject(StatusNode statusNode){
		URI uri = statusNode.eResource().getURI();
		String platformString = uri.toPlatformString(true);
		Path path = new Path(platformString);
		
		return ResourcesPlugin.getWorkspace().getRoot().getFile(path).getProject();
	}
	
	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		IFeatureProvider featureProvider = getFeatureProvider();		
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		
		List<IDecorator> decorators = new ArrayList<IDecorator>();
		
		if(!(bo instanceof StatusNode)){
			return decorators.toArray(new IDecorator[decorators.size()]);
		}
		
		//retrieve chached instance in status service (workaround to obtain same object instance)
		String id = ((StatusNode) bo).getId();
		IProject project = getProject((StatusNode) bo);
		
		ToolStatusImpl status = (ToolStatusImpl)StatusServiceImpl.getProjectStatusSrvice(project).getToolStatus(id);
		if(status == null){
			return decorators.toArray(new IDecorator[decorators.size()]);
		}
		StatusNode statusNode = status.getStatusNode();
		
		//handle requirements
		if(statusNode instanceof Requirement){
			Requirement r = (Requirement)statusNode;
			
			boolean sectionInProgress = true;
			boolean sectionDone = true;
			
			if(r.eContainer() instanceof Section){
				Section section = (Section)r.eContainer();
				sectionInProgress = section.isInProgress();
				sectionDone = section.isDone();
			}
			
			if(sectionInProgress || sectionDone){
				if(r.isDone()){
					
					ImageDecorator imageRenderingDecorator = new ImageDecorator(IPlatformImageConstants.IMG_TOGGLE_PAD);
					imageRenderingDecorator.setMessage("Task is done.");
					imageRenderingDecorator.setX(pe.getGraphicsAlgorithm().getWidth() - 20);
					imageRenderingDecorator.setY(5);
					decorators.add(imageRenderingDecorator);
					
					ColorDecorator cd = new ColorDecorator();
					cd.setBackgroundColor(IColorConstant.LIGHT_GREEN);
					cd.setMessage("Section successfully compleated.");
					decorators.add(cd);
				}
				else if(!r.getWarnings().isEmpty()){
					for(Warning w : r.getWarnings()) {
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
				else{
					
					ImageDecorator imageRenderingDecorator = new ImageDecorator(IPlatformImageConstants.IMG_MISSING);
					imageRenderingDecorator.setMessage("Requirement is missing!");
					imageRenderingDecorator.setX(pe.getGraphicsAlgorithm().getWidth() - 20);
					imageRenderingDecorator.setY(5);
					decorators.add(imageRenderingDecorator);
					
					ColorDecorator cd = new ColorDecorator();
					cd.setBackgroundColor(IColorConstant.LIGHT_ORANGE);
					cd.setMessage("Requirement missing!");
					decorators.add(cd);
				}
			}
		}
		else{
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
				
				ImageDecorator imageRenderingDecorator = new ImageDecorator(IPlatformImageConstants.IMG_TOGGLE_PAD);
				imageRenderingDecorator.setMessage("Task is done.");
				imageRenderingDecorator.setX(pe.getGraphicsAlgorithm().getWidth() - 20);
				imageRenderingDecorator.setY(5);
				decorators.add(imageRenderingDecorator);
				
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
