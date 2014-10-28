package org.scaledl.overview.diagram.editor.platform;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;

public class PlatformServiceEditor extends MultiPageEditorPart {

	public static final String ID = "org.scaledl.overview.diagram.editor.component.ComponentEditor"; //$NON-NLS-1$
	private PlatformServiceEditorInput input;

	public PlatformServiceEditor() {
	}
	
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
		
		this.input = (PlatformServiceEditorInput)input;
		setPartName(this.input.getName());
	}
	
	@Override
	public void dispose() {
		
		super.dispose();
	}
	
	

	@Override
	protected void createPages() {
		try {
			int pageIndex = addPage(new MainEditor(), getEditorInput());
			setPageText(pageIndex, "Main");
		} catch (PartInitException e) {
		}
	}
	
	@Override
	public boolean isDirty() {
		
		return false;
		
		// TODO: if needed
		// Partial solution removed in r715
	}
	
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO: no used - check isDirty
	}

	@Override
	public void doSaveAs() {
		// TODO: no used - check isDirty
	}
}
