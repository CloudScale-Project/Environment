package eu.cloudscaleproject.env.method.viewer.diagram;

import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.StatusManager;

public class DiagramTypeProvider extends AbstractDiagramTypeProvider {

	@Optional @Inject
	StatusManager statusManager;
	
	private IToolBehaviorProvider[] toolBehaviorProviders;
	
	public DiagramTypeProvider() {
		super();
		
		CloudscaleContext.inject(this);
		setFeatureProvider(new PatternFeatureProvider(this));
	}
	
	@Override
	public void postInit() {
		super.postInit();

		// validate project resources
		if (statusManager != null) {
			IProject project = ExplorerProjectPaths
					.getProjectFromEmfResource(this.getDiagram().eResource());
			if (project != null) {
				statusManager.validateAllAsync(project);

			}
		}
		
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
