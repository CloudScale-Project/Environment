package eu.cloudscaleproject.env.toolchain.editors;

import javax.inject.Inject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.MultiPageEditorPart;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.toolchain.ProjectEditorExtension;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 * 
 * TODO:
 * This class is not used! Migrate ProjectEditor class to this?
 *
 */
public class MultipageProjectEditor extends MultiPageEditorPart{
	
	@Inject
	private ProjectEditorProvider editorProvider;
	
	public MultipageProjectEditor() {
		CloudscaleContext.inject(this);
	}

	@Override
	protected void createPages() {
		
		for(ProjectEditorExtension pee : editorProvider.getToolExtensions()){
			if(pee instanceof EditorPart){
				//EditorPart ep = (EditorPart)pee;
				//addPage(ep, ep.)
			}
		}		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSaveAs() {
		//this is not allowed
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

}
