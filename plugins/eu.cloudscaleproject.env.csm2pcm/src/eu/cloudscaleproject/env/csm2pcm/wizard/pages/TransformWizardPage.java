package eu.cloudscaleproject.env.csm2pcm.wizard.pages;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class TransformWizardPage extends WizardPage{
	
	private final IProject project;
	private final IFolder outputFolder;

	private IFolder transformedPCMModelFolder = null;
	
	public TransformWizardPage(IProject project, IFolder outputFolder) {
		super("Overview model transformation");

		this.project = project;
		this.outputFolder = outputFolder;
		
		setTitle("Overview model transformation");
		setDescription("Transform Overview model into PCM. PCM model can then be used as an input for the Analyser tool.");
		
		setPageComplete(false);
	}
	
	public IFolder getTransformedPCMModelFolder(){
		return this.transformedPCMModelFolder;
	}

	@Override
	public void createControl(Composite parent) {
		
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, true));
		
		final Button btnTransform = new Button(composite, SWT.NONE);
		btnTransform.setText("Transform Overview model...");
		
		final ProgressBar progressBar = new ProgressBar(composite, SWT.HORIZONTAL | SWT.INDETERMINATE);
		progressBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		progressBar.setVisible(false);
		
		final Label lblStatus = new Label(composite, SWT.NONE);
		lblStatus.setText("");
		
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.OVERVIEW_ID);
		IEditorInputResource overviewResource = rp.getResources().get(0);
		overviewResource.validate();
		
		ValidationDiagramService.showDiagram(project);
		ValidationDiagramService.showStatus(project, overviewResource);
		
		final IFile file = (IFile)overviewResource.getResource();
		
		btnTransform.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				//execute transformation
				progressBar.setVisible(true);
				
				TransformHandler runHandler = new TransformHandler();
				runHandler.execute(file, outputFolder, new BasicCallback<IFolder>() {
					
					@Override
					public void handle(final IFolder outputFolder) {
						transformedPCMModelFolder = outputFolder;
						
						Display.getDefault().syncExec(new Runnable() {
							
							@Override
							public void run() {
								btnTransform.setEnabled(false);
								lblStatus.setText("New PCM model has been created in: " + outputFolder.getFullPath());
								composite.layout();
								composite.redraw();
								
								progressBar.setVisible(false);
								setPageComplete(true);
							}
						});
					}
				});
			}
		});
		
		if(!overviewResource.getSelfStatus().isDone()){
			btnTransform.setEnabled(false);
			lblStatus.setText("Overview model is incomplete or contains errors!");
		}
		
		setControl(composite);
	}
}