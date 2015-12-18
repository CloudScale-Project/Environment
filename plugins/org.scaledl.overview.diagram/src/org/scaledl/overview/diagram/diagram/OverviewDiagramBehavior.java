package org.scaledl.overview.diagram.diagram;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.ui.editor.DefaultUpdateBehavior;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.graphiti.ui.editor.IDiagramEditorInput;

import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class OverviewDiagramBehavior extends DiagramBehavior {

	public OverviewDiagramBehavior(IDiagramContainerUI diagramContainer) {
		super(diagramContainer);
	}
	
	@Override
	protected DefaultUpdateBehavior createUpdateBehavior() {
		return new UpdateBehavior(this);
	}
	
	private class UpdateBehavior extends DefaultUpdateBehavior{
		
		private boolean usingSharedEditingDomain = false;

		public UpdateBehavior(DiagramBehavior diagramBehavior) {
			super(diagramBehavior);
		}
		
		@Override
		protected void createEditingDomain(IDiagramEditorInput input) {
			
			IEditorInputResource eir = ResourceRegistry.getInstance().findResource(input.getUri().toPlatformString(true));
			if(eir instanceof EditorInputEMF){
				
				EditorInputEMF eie = (EditorInputEMF)eir;
				TransactionalEditingDomain ed = eie.getEditingDomain();
				
				usingSharedEditingDomain = true;
				initializeEditingDomain(ed);
			}
			else{
				usingSharedEditingDomain = false;
				super.createEditingDomain(input);
			}
		}
		
		@Override
		protected void disposeEditingDomain() {
			if(!usingSharedEditingDomain){
				super.disposeEditingDomain();
			}
		}
		
	}

}
