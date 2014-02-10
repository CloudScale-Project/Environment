package eu.cloudscaleproject.env.csm.diagram.editor.component;

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

import eu.cloudscaleproject.env.csm.DefinitionProviderService;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService;
import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.definition.CloudDefinition;

public class MainEditor extends EditorPart {
	public MainEditor() {
	}

	public static final String ID = "eu.cloudscaleproject.env.csm.diagram.MainEditor"; //$NON-NLS-1$
	
	private ComponentEditorInput input;

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Main editor";
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// Initialize the editor part
			if (!(input instanceof ComponentEditorInput)) {
			      throw new RuntimeException("Wrong input");
		    }

		    this.input = (ComponentEditorInput) input;
		    setSite(site);
		    setInput(input);
		    setPartName("Component name: " + this.input.getEntity().getName());
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
	    
	    
	    //
	    // SECTIONS
	    //
	    
	    String cloudProivderId = ((CloudEnvironment)input.getEntity().eContainer().eContainer())
	    		.getCloudProviderDescriptor().getProviderID();
	    CloudDefinition cloudDefinition = DefinitionProviderService.getInstance().getCloudDefinition(cloudProivderId);
	    
	   // Entity section
	    {
		    Section section = toolkit.createSection(leftComposite, Section.DESCRIPTION | Section.TITLE_BAR);
		    section.setText("General information"); //$NON-NLS-1$
		    section.setDescription("General information description .... [TODO]");
		    // Composite for storing the data
		    NameComposite nameComposite = new NameComposite(section, input.getEntity());
		    toolkit.paintBordersFor(nameComposite);
		    section.setClient(nameComposite); 
	    } 
	    
	    // Interfaces section
	    if (input.getEntity() instanceof OperationInterfaceContainer)
	    {
		    Section section = toolkit.createSection(leftComposite, Section.DESCRIPTION | Section.TITLE_BAR);
		    section.setText("Interfaces"); //$NON-NLS-1$
		    section.setDescription("Interfaces description .... [TODO]");
		    
		    // Composite for storing the data
		    IterfaceComposite composite = new IterfaceComposite(section, (PlatformService) input.getEntity());
		    toolkit.paintBordersFor(composite);
		    section.setClient(composite);
	    	
	    }
	    
	    if (input.getEntity() instanceof DeployablePlatformService)
	    {
		    Section instanceSection = toolkit.createSection(rightComposite, Section.DESCRIPTION
		        | Section.TITLE_BAR);
		    
		    instanceSection.setText("Node information"); //$NON-NLS-1$
		    instanceSection.setDescription("General information description .... [TODO]");
		    
		    NodeComposite nodeComposite = new NodeComposite(instanceSection, (DeployablePlatformService)(input.getEntity()), cloudDefinition);
		    toolkit.paintBordersFor(nodeComposite);
		    instanceSection.setClient(nodeComposite);
		    
	    }
	    // Lets make a layout for the first section of the screen
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
