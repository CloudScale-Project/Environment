package org.scaledl.overview.diagram.editor.system;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.EditorPart;

public class OverviewEditor extends EditorPart {
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	public OverviewEditor() {
	}

	private SystemEditorInput input;


	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Overview";
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// Initialize the editor part
			if (!(input instanceof SystemEditorInput)) {
			      throw new RuntimeException("Wrong input");
		    }

		    this.input = (SystemEditorInput) input;
		    setSite(site);
		    setInput(input);
		    setPartName("Csm name: " + this.input.getOverview().toString());
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
	    ScrolledForm form = toolkit.createScrolledForm(parent);
	    form.setText("Main");
	    FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL);
	    fillLayout.spacing = 15;
	    form.getBody().setLayout(fillLayout);
	    
	    Composite leftComposite = toolkit.createComposite(form.getBody(), SWT.NONE);
	    toolkit.paintBordersFor(leftComposite);
	    FillLayout fl_leftComposite = new FillLayout(SWT.VERTICAL);
	    fl_leftComposite.spacing = 10;
	    leftComposite.setLayout(fl_leftComposite);
	    
	    Composite rightComposite = new Composite(form.getBody(), SWT.NONE);
	    toolkit.adapt(rightComposite);
	    toolkit.paintBordersFor(rightComposite);
	    FillLayout fl_rightComposite = new FillLayout(SWT.VERTICAL);
	    fl_rightComposite.spacing = 10;
	    rightComposite.setLayout(fl_rightComposite);
	    
	}
	
	
	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
}
