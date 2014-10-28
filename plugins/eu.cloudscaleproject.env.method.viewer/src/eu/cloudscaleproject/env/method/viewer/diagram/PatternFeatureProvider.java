package eu.cloudscaleproject.env.method.viewer.diagram;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.IMoveAnchorFeature;
import org.eclipse.graphiti.features.IMoveBendpointFeature;
import org.eclipse.graphiti.features.IMoveConnectionDecoratorFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.IRemoveBendpointFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.IMoveAnchorContext;
import org.eclipse.graphiti.features.context.IMoveBendpointContext;
import org.eclipse.graphiti.features.context.IMoveConnectionDecoratorContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.IRemoveBendpointContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.pattern.DefaultFeatureProviderWithPatterns;

import eu.cloudscaleproject.env.method.common.diagram.patterns.ActionPattern;
import eu.cloudscaleproject.env.method.common.diagram.patterns.CommandPattern;
import eu.cloudscaleproject.env.method.common.diagram.patterns.ConnectorPattern;
import eu.cloudscaleproject.env.method.common.diagram.patterns.ContainerPattern;
import eu.cloudscaleproject.env.method.common.diagram.patterns.NodePattern;
import eu.cloudscaleproject.env.method.common.diagram.patterns.RequirementPattern;
import eu.cloudscaleproject.env.method.common.diagram.patterns.SectionPattern;
import eu.cloudscaleproject.env.method.viewer.diagram.features.CommandFeature;


public class PatternFeatureProvider extends DefaultFeatureProviderWithPatterns {

	public PatternFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
		
		addPattern(new CommandPattern());
		addPattern(new RequirementPattern());
		addPattern(new ActionPattern());
		addPattern(new SectionPattern());
		addPattern(new ContainerPattern());
		addPattern(new NodePattern());
		
		addConnectionPattern(new ConnectorPattern(true));
		addConnectionPattern(new ConnectorPattern(false));
	}
	
	@Override
	public ICustomFeature[] getCustomFeatures(ICustomContext context) {
		return new ICustomFeature[]{new CommandFeature(this)};
	}
		
	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		return null;
	}
	
	@Override
	public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context) {
		return null;
	}
	
	@Override
	public IMoveAnchorFeature getMoveAnchorFeature(IMoveAnchorContext context) {
		return null;
	}
	
	@Override
	public IMoveBendpointFeature getMoveBendpointFeature(
			IMoveBendpointContext context) {
		return null;
	}
	
	@Override
	public IMoveConnectionDecoratorFeature getMoveConnectionDecoratorFeature(
			IMoveConnectionDecoratorContext context) {
		return null;
	}
	
	@Override
	public IDirectEditingFeature getDirectEditingFeature(
			IDirectEditingContext context) {
		return super.getDirectEditingFeature(context);
	}
	
	@Override
	protected IDirectEditingFeature getDirectEditingFeatureAdditional(
			IDirectEditingContext context) {
		return super.getDirectEditingFeatureAdditional(context);
	}
	
	@Override
	public IRemoveBendpointFeature getRemoveBendpointFeature(
			IRemoveBendpointContext context) {
		return null;
	}
	
	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		return null;
	}
	
	@Override
	public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context) {
		return null;
	}
	
	@Override
	protected IResizeShapeFeature getResizeShapeFeatureAdditional(
			IResizeShapeContext context) {
		return null;
	}
	
	@Override
	protected IRemoveFeature getRemoveFeatureAdditional(IRemoveContext context) {
		return null;
	}
	
	@Override
	public IReconnectionFeature getReconnectionFeature(
			IReconnectionContext context) {
		return null;
	}
}
