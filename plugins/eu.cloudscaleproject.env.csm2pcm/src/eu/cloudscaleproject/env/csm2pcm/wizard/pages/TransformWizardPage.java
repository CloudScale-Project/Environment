package eu.cloudscaleproject.env.csm2pcm.wizard.pages;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;

import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.csm2pcm.handlers.TransformHandler;
import eu.cloudscaleproject.env.overview.OverviewAlternative;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class TransformWizardPage extends WizardPage{
	
	private static final String DEFAULT_TITLE = "Overview model transformation";
	private static final String DEFAULT_DESCRIPTION = "Transform Overview model into PCM. PCM model can then be used as an input for the Analyser tool.";
	
	private final IProject project;
	private final IFolder outputFolder;

	private OverviewAlternative overviewAlternative;
	private IFolder transformedPCMModelFolder = null;
	
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	
	private final ResourceProvider resourceProvider;
	
	private ListViewer listViewer;
	private Button btnTransform;
	
	private ProgressBar progressBar;
	private Label lblStatus;
	
	public TransformWizardPage(IProject project ,IFolder outputFolder) {
		super("Overview model transformation");

		this.project = project;
		this.outputFolder = outputFolder;
		this.resourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSTool.OVERVIEW);
		
		setTitle(DEFAULT_TITLE);
		setDescription(DEFAULT_DESCRIPTION);
		
		//page can be skipped
		setPageComplete(false);
	}
	
	public IFolder getTransformedPCMModelFolder(){
		return this.transformedPCMModelFolder;
	}

	@Override
	public void createControl(Composite parent) {
		
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		
		Label lblListName = new Label(composite, SWT.NONE);
		lblListName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		lblListName.setText("Please select Overview model:");
		
		listViewer = new ListViewer(composite, SWT.BORDER | SWT.V_SCROLL);
		listViewer.getList().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		listViewer.addSelectionChangedListener(new ISelectionChangedListener()
		{
			@Override
			public void selectionChanged(SelectionChangedEvent event)
			{
				IEditorInputResource alternative = getSelectedAlternative();
				if(alternative instanceof OverviewAlternative){
					overviewAlternative = (OverviewAlternative)alternative;					
					overviewAlternative.validate();
					ValidationDiagramService.showDiagram(project);
					ValidationDiagramService.showStatus(project, overviewAlternative);
					
					if(overviewAlternative.getSelfStatus().isDone()){
						btnTransform.setEnabled(true);
						lblStatus.setText("");
					}
					else{
						btnTransform.setEnabled(false);
						lblStatus.setText("Overview model is incomplete or contains errors!");
					}
				}
			}
		});
		
		final Composite status = new Composite(composite, SWT.NONE);
		status.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		final StackLayout stackStatus = new StackLayout();
		status.setLayout(stackStatus);
		
		progressBar = new ProgressBar(status, SWT.HORIZONTAL | SWT.INDETERMINATE);
		progressBar.setLayoutData(new FillLayout());
		progressBar.setVisible(false);
		
		lblStatus = new Label(status, SWT.NONE);
		lblStatus.setLayoutData(new FillLayout());
		lblStatus.setText("");
		
		stackStatus.topControl = lblStatus;
		
		btnTransform = new Button(composite, SWT.NONE);
		btnTransform.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		btnTransform.setEnabled(false);
		btnTransform.setText("Transform Overview model...");
		
		btnTransform.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				//execute transformation
				btnTransform.setEnabled(false);
				stackStatus.topControl = progressBar;
				status.layout();
				
				TransformHandler runHandler = new TransformHandler();
				IFile file = (IFile)overviewAlternative.getSubResource(ToolchainUtils.KEY_FILE_OVERVIEW);
				
				runHandler.execute(file, outputFolder, new BasicCallback<IFolder>() {
					
					@Override
					public void handle(final IFolder outputFolder) {
						transformedPCMModelFolder = outputFolder;
						
						Display.getDefault().syncExec(new Runnable() {
							
							@Override
							public void run() {
								lblStatus.setText("Transformation Done");
								composite.layout();
								composite.redraw();
								
								btnTransform.setEnabled(true);
								stackStatus.topControl = lblStatus;
								status.layout();
								
								setPageComplete(true);
							}
						});
					}
				});
			}
		});
		
		m_bindingContext = initDataBindings();
		
		setControl(composite);
	}
	
	public IEditorInputResource getSelectedAlternative(){
		return (IEditorInputResource) ((StructuredSelection)listViewer.getSelection()).getFirstElement();
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap observeMap = BeansObservables.observeMap(listContentProvider.getKnownElements(), IEditorInputResource.class, "name");
		listViewer.setLabelProvider(new ObservableMapLabelProvider(observeMap));
		listViewer.setContentProvider(listContentProvider);
		//
		IObservableList resourcesResourceProviderObserveList = PojoProperties.list("resources").observe(resourceProvider);
		listViewer.setInput(resourcesResourceProviderObserveList);
		//
		return bindingContext;
	}
}