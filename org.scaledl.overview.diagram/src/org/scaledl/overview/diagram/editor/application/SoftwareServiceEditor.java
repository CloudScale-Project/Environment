package org.scaledl.overview.diagram.editor.application;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;

public class SoftwareServiceEditor extends MultiPageEditorPart {

	public static final String ID = "org.scaledl.overview.diagram.editor.application.ApplicationServiceEditor"; //$NON-NLS-1$
	private SoftwareServiceEditorInput input;
	@SuppressWarnings("unused")
	private TransactionalEditingDomain editingDomain;

	public SoftwareServiceEditor() {
	}
	
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
		
		this.input = (SoftwareServiceEditorInput)input;
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
			setPageText(pageIndex, "Application Service Editor");
			pageIndex = addPage(new AnalyserEditor(), getEditorInput());
			setPageText(pageIndex, "Analyser");
		} catch (PartInitException e) {
			
		}
	}
	
	@Override
	public boolean isDirty() {
		// TODO: if needed
		// Partial solution removed in r715
		
		return false;
	}
	
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
	
	

	@Override
	public void doSave(final IProgressMonitor monitor) {
		// TODO: no used - check isDirty
	}
	
	@Override
	public void doSaveAs() {
		// TODO: no used - check isDirty
	}
}
