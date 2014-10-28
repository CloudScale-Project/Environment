package eu.cloudscaleproject.env.method.common.diagram.patterns;

import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.pattern.AbstractConnectionPattern;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

import eu.cloudscaleproject.env.method.common.method.LinkedNode;
import eu.cloudscaleproject.env.method.common.method.Method;
import eu.cloudscaleproject.env.method.common.method.MethodFactory;
import eu.cloudscaleproject.env.method.common.method.SectionConnector;
import eu.cloudscaleproject.env.method.common.util.MethodUtil;

public class ConnectorPattern extends AbstractConnectionPattern{

	private final boolean required;
	
	public ConnectorPattern(boolean required){
		super();
		this.required = required;
	}
	
	@Override
	public String getCreateName() {
		return required ? "Required connector" : "Connector";
	}
	
	public boolean canCreate(ICreateConnectionContext context) {
		Object source = getBusinessObjectForPictogramElement(context.getSourcePictogramElement());
		Object target = getBusinessObjectForPictogramElement(context.getTargetPictogramElement());
		
		if(source instanceof LinkedNode && target instanceof LinkedNode){
			return true;
		}
		
		return false;
	}

	public boolean canStartConnection(ICreateConnectionContext context) {
		Object source = getBusinessObjectForPictogramElement(context.getSourcePictogramElement());
		
		if(source instanceof LinkedNode){
			return true;
		}
		
		return false;
	}
	
	@Override
	public Connection create(ICreateConnectionContext context) {
		
		Method method = MethodUtil.getMethodModel(getDiagram());
		Connection connectionPictogram = null;
		
		LinkedNode source = (LinkedNode)getBusinessObjectForPictogramElement(context.getSourcePictogramElement());
		LinkedNode target = (LinkedNode)getBusinessObjectForPictogramElement(context.getTargetPictogramElement());
		
		SectionConnector connector = MethodFactory.eINSTANCE.createSectionConnector();
		connector.setStart(source);
		connector.setEnd(target);
		connector.setRequired(required);
		method.getSectionConnectors().add(connector);
		
		source.getNext().add(connector);
		target.getPrevious().add(connector);
		
		AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		addContext.setNewObject(connector);
		connectionPictogram = (Connection) getFeatureProvider().addIfPossible(addContext);
		
		return connectionPictogram;
	}
	
	public boolean canAdd(IAddContext context) {
		Object bo = context.getNewObject();
		if(bo instanceof SectionConnector){
			return true;
		}
		return false;
	}
	
	@Override
	public PictogramElement add(IAddContext context) {
		IAddConnectionContext addConContext = (IAddConnectionContext) context;
		
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		SectionConnector connector = (SectionConnector) context.getNewObject();

		// CONNECTION WITH POLYLINE
		Connection connection = peCreateService.createFreeFormConnection(getDiagram());
		connection.setStart(addConContext.getSourceAnchor());
		connection.setEnd(addConContext.getTargetAnchor());
		
		IGaService gaService = Graphiti.getGaService();
		Polyline line = gaService.createPolyline(connection);
		line.setLineWidth(2);
				
		if(connector.isRequired()){
			line.setLineStyle(LineStyle.SOLID);
		} 
		else{
			line.setLineStyle(LineStyle.DASH);
		};
		
		ConnectionDecorator cd;
		cd = peCreateService.createConnectionDecorator(connection, false, 1.0, true);
		createArrow(cd);

		link(connection, connector);
		
		return connection;
	}
	
	private Polyline createArrow(GraphicsAlgorithmContainer gaContainer) {
		Polyline polyline = Graphiti.getGaCreateService().createPlainPolyline(gaContainer,
				new int[] { -10, 8, 0, 0,0,-2,  -10, -8 });
		polyline.setLineStyle(LineStyle.SOLID);
		polyline.setLineWidth(2);
		return polyline;
	}
}
