package eu.cloudscaleproject.env.method.editor.diagram;

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
