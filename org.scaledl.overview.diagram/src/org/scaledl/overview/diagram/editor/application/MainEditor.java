package org.scaledl.overview.diagram.editor.application;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.EditorPart;
import org.scaledl.overview.diagram.editor.basic.IterfaceComposite;
import org.scaledl.overview.diagram.editor.basic.NameComposite;
import org.scaledl.overview.diagram.editor.basic.RequiredInterfaceComposite;
import org.scaledl.overview.diagram.editor.extractor.BasicExtractorComposite;

public class MainEditor extends EditorPart {
	public MainEditor() {
	}

	public static final String ID = "org.scaledl.overview.diagram.MainEditor"; //$NON-NLS-1$
	
	private SoftwareServiceEditorInput input;

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Application service editor";
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
		    setPartName("Component name: " + this.input.getSoftwareService().getName());
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
	    FillLayout fl_rightComposite = new FillLayout(SWT.VERTICAL); fl_rightComposite.spacing = 10;
	    rightComposite.setLayout(fl_rightComposite);
	    
	    
	    //
	    // SECTIONS
	    //
	    
	   // Entity section
	    {
		    Section section = toolkit.createSection(leftComposite, Section.DESCRIPTION | Section.TITLE_BAR);
		    section.setText("General information"); //$NON-NLS-1$
		    section.setDescription("General information description .... [TODO]");
		    // Composite for storing the data
		    NameComposite nameComposite = new NameComposite(section, input.getSoftwareService());
		    toolkit.paintBordersFor(nameComposite);
		    section.setClient(nameComposite); 
	    } 
	    
	    // Interfaces section
	    {
		    Section section = toolkit.createSection(leftComposite, Section.DESCRIPTION | Section.TITLE_BAR);
		    section.setText("Provided interface"); //$NON-NLS-1$
		    section.setDescription("Provided OperationInterface operations.");
		    
		    // Composite for storing the data
		    IterfaceComposite composite = new IterfaceComposite(section, input.getSoftwareService());
		    toolkit.paintBordersFor(composite);
		    section.setClient(composite);
	    }
	    
	    
	    // Extractor seciton
	    {
		    Section section = toolkit.createSection(rightComposite, Section.DESCRIPTION
		        | Section.TITLE_BAR);
		    section.setText("Extracted models"); //$NON-NLS-1$
		    section.setDescription("Location of extracted PCM models used for this component.");
		    // Composite for storing the data
		    BasicExtractorComposite client = new BasicExtractorComposite(parent, this.input.getSoftwareService());
		    client.setParent(section);
		    toolkit.paintBordersFor(client);
		    section.setClient(client);
	    }
	    
	    // Interfaces section
	    {
		    Section section = toolkit.createSection(rightComposite, Section.DESCRIPTION | Section.TITLE_BAR);
		    section.setText("Required interfaces"); //$NON-NLS-1$
		    section.setDescription("Provided OperationInterface operations.");
		    
		    // Composite for storing the data
		    RequiredInterfaceComposite composite = new RequiredInterfaceComposite(section, input.getSoftwareService());
		    toolkit.paintBordersFor(composite);
		    section.setClient(composite);
	    }
	    
	    
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		throw new IllegalStateException("Should not be called!");
		
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
