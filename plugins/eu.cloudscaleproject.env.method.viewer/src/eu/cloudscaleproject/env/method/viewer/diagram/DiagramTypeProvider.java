package eu.cloudscaleproject.env.method.viewer.diagram;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

public class DiagramTypeProvider extends AbstractDiagramTypeProvider {
	
	private IToolBehaviorProvider[] toolBehaviorProviders;
	
	public DiagramTypeProvider() {
		super();		
		setFeatureProvider(new PatternFeatureProvider(this));
	}
	
	@Override
	public void postInit() {
		super.postInit();
				
		/*
		 * TODO: FIX - Illegal state exception
		if(getDiagramBehavior() instanceof CustomDiagramBehavior){
			getDiagram().setGridUnit(0);
		}
		else{
			getDiagram().setGridUnit(5);
		}
		*/
	}

	@Override
	public boolean isAutoUpdateAtReset() {
		return true;
	}

	@Override
	public boolean isAutoUpdateAtRuntime() {
		return true;
	}

	@Override
	public boolean isAutoUpdateAtStartup() {
		return true;
	}

	@Override
	public boolean isAutoUpdateAtRuntimeWhenEditorIsSaved() {
		return true;
	}
		
	@Override
	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		if (toolBehaviorProviders == null) {
			toolBehaviorProviders = new IToolBehaviorProvider[] { 
					new ToolBehaviorProvider(this) 
					};
		}
		return toolBehaviorProviders;
	}
}
