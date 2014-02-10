package eu.cloudscaleproject.env.csm.diagram.editor.extractor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.EditorPart;

import eu.cloudscaleproject.env.csm.diagram.editor.application.SoftwareServiceEditorInput;

public class ExtractorEditor extends EditorPart {
	public ExtractorEditor() {
	}

	public static final String ID = "eu.cloudscaleproject.env.csm.diagram.ComponentEditor"; //$NON-NLS-1$
	private SoftwareServiceEditorInput input;


	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Analyser";
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// Initialize the editor part
			if (!(input instanceof SoftwareServiceEditorInput)) {
			      throw new RuntimeException("Wrong input");
		    }

		    this.input = (SoftwareServiceEditorInput) input;
		    setSite(site);
		    setInput(input);
		    setPartName("Service name: " + this.input.getSoftwareService().getName());
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
	    ScrolledForm form = toolkit.createScrolledForm(parent);
	    form.setText("Extractor");
	    form.getBody().setLayout(new FillLayout(SWT.HORIZONTAL));
	    // Creating the Screen
	    
	    // Basic composite
	    {
		    Section section = toolkit.createSection(form.getBody(), Section.DESCRIPTION
		        | Section.TITLE_BAR);
		    section.setText("Extracted models"); //$NON-NLS-1$
		    section.setDescription("Location of extracted PCM models used for this component.");
		    // Composite for storing the data
		    BasicExtractorComposite client = new BasicExtractorComposite(parent, this.input.getSoftwareService());
		    client.setParent(section);
		    toolkit.paintBordersFor(client);
		    section.setClient(client);
	    }
	    

	    // Lets make a layout for the first section of the screen
	    GridLayout layout = new GridLayout();
	    layout.numColumns = 1;
	    layout.marginWidth = 2;
	    layout.marginHeight = 2;
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
