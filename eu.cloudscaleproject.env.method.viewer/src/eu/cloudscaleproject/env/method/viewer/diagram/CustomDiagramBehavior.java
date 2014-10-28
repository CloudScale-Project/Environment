package eu.cloudscaleproject.env.method.viewer.diagram;

import org.eclipse.graphiti.ui.editor.DefaultRefreshBehavior;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;

public class CustomDiagramBehavior extends DiagramBehavior{
	
	public CustomDiagramBehavior(IDiagramContainerUI container){
		super(container);
	}

	@Override
	protected DefaultRefreshBehavior createRefreshBehavior() {
		return super.createRefreshBehavior();
	}
	
	/*
	@Override
	protected boolean isDirty() {
		return super.isDirty();
	}
	
	@Override
	public void refresh() {
		super.refresh();
	}
	*/
	
	@Override
	public void refreshContent() {
		/*
		for (Resource resource : getResourceSet().getResources()) {
			resource.unload();
		}
		*/
		super.refreshContent();
	}
}
