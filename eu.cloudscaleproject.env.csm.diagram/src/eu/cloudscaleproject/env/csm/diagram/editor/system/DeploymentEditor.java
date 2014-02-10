package eu.cloudscaleproject.env.csm.diagram.editor.system;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.EditorPart;

import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer;

public class DeploymentEditor extends EditorPart {
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	public DeploymentEditor() {
	}

	private SystemEditorInput input;
	private CloudProviderListComposite cloudComposite;
	private ComputingInfrastructureListComposite instancesComposite;
	private CloudProviderModel cloudProviderModel;
	private ComputingInfrastructureServiceComposite instanceDescriptionComposite;


	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Deployment";
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
		    setPartName("Csm name: " + this.input.getCsm().toString());
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
	    fillLayout.marginWidth = 5;
	    fillLayout.marginHeight = 5;
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
	    
	    this.cloudProviderModel = new CloudProviderModel();
	    
	   // CloudProvider list section
	    {
		    Section section = toolkit.createSection(leftComposite, Section.DESCRIPTION | Section.TITLE_BAR);
		    section.setText("Cloud environments"); //$NON-NLS-1$
		    section.setDescription("List displays all used cloud environments. Select one, to get list of available computing infrastructure services.");
		    // Composite for storing the data
		    this.cloudComposite = new CloudProviderListComposite(section, SWT.NONE);
		    this.cloudComposite.setCloudProviderModel(this.cloudProviderModel);
	        cloudComposite.setCsm(this.input.getCsm());
	        
		    toolkit.paintBordersFor(cloudComposite);
		    section.setClient(cloudComposite); 
	    } 
	    
	    // Instances list section
	    {
		    Section section = toolkit.createSection(leftComposite, Section.DESCRIPTION | Section.TITLE_BAR);
		    section.setText("Computing Infrastructure Services"); //$NON-NLS-1$
		    section.setDescription("List displays all computing infrastructure services in chosen cloud environment. Select one, to get service details.");
		    // Composite for storing the data
		    this.instancesComposite = new ComputingInfrastructureListComposite(section, SWT.NONE);
		    
		    toolkit.paintBordersFor(instancesComposite);
		    section.setClient(instancesComposite); 
	    } 
	    
	   // Instance section
	    {
		    Section section = toolkit.createSection(rightComposite, Section.DESCRIPTION | Section.TITLE_BAR);
		    section.setText("Computing Infrastructure Service Details"); //$NON-NLS-1$
		    section.setDescription("Details of the chosen computing infrastructure service. Desriptor and deployed platform servcies.");
		    // Composite for storing the data
		    this.instanceDescriptionComposite = new ComputingInfrastructureServiceComposite(section, SWT.NONE);
		    
		    toolkit.paintBordersFor(instanceDescriptionComposite);
		    section.setClient(instanceDescriptionComposite); 
	    } 
	    m_bindingContext = initDataBindings();
	}
	
	public void setSelection (final ComputingInfrastructureService cis)
	{
    	Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				InfrastructureLayer infrastructureLayer = (InfrastructureLayer)cis.eContainer();
				CloudEnvironment cloudEnvironment = (CloudEnvironment)infrastructureLayer.eContainer();
				instancesComposite.setCloudProvider(cloudEnvironment);
				instanceDescriptionComposite.setComputingInfrastructureService(cis);
			}
		});
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
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		
		IObservableValue observeSingleSelectionTableViewer = ViewerProperties.singleSelection().observe(this.cloudComposite.getTableViewer());
		IObservableValue cloudProviderCloudProviderModelObserveValue = BeanProperties.value("cloudProvider").observe(instancesComposite);
		bindingContext.bindValue(observeSingleSelectionTableViewer, cloudProviderCloudProviderModelObserveValue, null, null);
		
		IObservableValue observeInstanceSelectionTableViewer = ViewerProperties.singleSelection().observe(this.instancesComposite.getTableViewer());
		IObservableValue instanceObvservableValue = BeanProperties.value("computingInfrastructureService").observe(this.instanceDescriptionComposite);
		bindingContext.bindValue(observeInstanceSelectionTableViewer, instanceObvservableValue, null, null);
		
		return bindingContext;
	}
}
