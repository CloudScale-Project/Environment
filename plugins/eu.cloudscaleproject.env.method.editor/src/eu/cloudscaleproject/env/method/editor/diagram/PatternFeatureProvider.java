package eu.cloudscaleproject.env.method.editor.diagram;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.pattern.DefaultFeatureProviderWithPatterns;

import eu.cloudscaleproject.env.method.common.diagram.patterns.ActionPattern;
import eu.cloudscaleproject.env.method.common.diagram.patterns.CommandPattern;
import eu.cloudscaleproject.env.method.common.diagram.patterns.ConnectorPattern;
import eu.cloudscaleproject.env.method.common.diagram.patterns.ContainerPattern;
import eu.cloudscaleproject.env.method.common.diagram.patterns.NodePattern;
import eu.cloudscaleproject.env.method.common.diagram.patterns.RequirementPattern;
import eu.cloudscaleproject.env.method.common.diagram.patterns.SectionPattern;
import eu.cloudscaleproject.env.method.editor.diagram.features.AddCommandFeature;
import eu.cloudscaleproject.env.method.editor.diagram.features.AddRequirementFeature;
import eu.cloudscaleproject.env.method.editor.diagram.features.DeleteFeature;
import eu.cloudscaleproject.env.method.editor.diagram.features.LayoutFeature;

public class PatternFeatureProvider extends DefaultFeatureProviderWithPatterns {

	public PatternFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
				
		//Temporary hack 
		//TODO: dependency to eu.cloudscaleproject.env.common should be removed!
		/*
		for(StatusManager.Tool t : StatusManager.Tool.values()){
			addPattern(new SectionPattern(t.getDefaultName(), t.getID()));
		}
		*/
		//
		
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
		return new ICustomFeature[]{new LayoutFeature(this),
									new AddCommandFeature(this), 
									new AddRequirementFeature(this)};
	}
		
	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		return new DeleteFeature(this);
	}
}
