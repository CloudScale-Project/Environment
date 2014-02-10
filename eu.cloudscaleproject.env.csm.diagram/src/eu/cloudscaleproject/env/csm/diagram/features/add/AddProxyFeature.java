package eu.cloudscaleproject.env.csm.diagram.features.add;


import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;

import eu.cloudscaleproject.env.csm.architecture.Proxy;
import eu.cloudscaleproject.env.csm.architecture.ServiceProxy;
import eu.cloudscaleproject.env.csm.diagram.DiagramImageProvider;

public class AddProxyFeature extends AbstractAddFeature{

	public AddProxyFeature(IFeatureProvider fp) {
		super(fp);
	}

//	@Override
	public boolean canAdd(IAddContext context) {
		// TODO: check for right domain object instance below
		return context.getTargetContainer() instanceof ContainerShape;
	}
	
	public static final ColorConstant TEXT = new ColorConstant("111111") ;
	    
	private int IMG_SIZE=64;
	private int WIDTH = 80;
	private int HEIGHT = 90;
	
	
//	@Override
	public PictogramElement add(IAddContext context) {

		Proxy connector = (Proxy)context.getNewObject();
		ContainerShape parentShape = context.getTargetContainer();
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();

		ContainerShape containerShape = peCreateService.createContainerShape(parentShape, true);
		
		if (connector instanceof ServiceProxy)
		{
			
			RoundedRectangle roundedRectangle; 
	        {
	            // create and set graphics algorithm
	            roundedRectangle =
	                gaService.createRoundedRectangle(containerShape, 5, 5);
	            roundedRectangle.setTransparency(1d);
	            gaService.setLocationAndSize(roundedRectangle,
	                context.getX(), context.getY(), WIDTH, HEIGHT);
	        }
	        
	        // Image - create internal container and add Image -- Needed for layout otherwise image is centrally aligned
	        {
				ContainerShape internalContainer = Graphiti.getPeService().createContainerShape(containerShape, false);
			    Image image = gaService.createImage(internalContainer, DiagramImageProvider.IMG_SERVICE_PROXY);
				gaService.setLocationAndSize(image, (WIDTH-IMG_SIZE)/2, 5 , IMG_SIZE, IMG_SIZE);
	        }
			
	        // Text - for name
			{
				ServiceProxy serviceProxy = (ServiceProxy) connector;
				
	            Text text = gaService.createText(roundedRectangle, serviceProxy.getName());
	            text.setForeground(manageColor(TEXT));
	            text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER ); 
	            text.setVerticalAlignment(Orientation.ALIGNMENT_TOP);
	            text.setFont(gaService.manageDefaultFont(getDiagram(), false, true));
	            gaService.setLocationAndSize(text, 0, IMG_SIZE+5, WIDTH, 30);
			}
			
		}
		else
		{
		    Image image = gaService.createImage(containerShape, DiagramImageProvider.IMG_USAGE_PROXY);
			gaService.setLocationAndSize(image, context.getX(), context.getY(), 64+4, 64+4);
		}
		
		peCreateService.createChopboxAnchor(containerShape);
		link(containerShape, connector);

		return containerShape;
	}
}
