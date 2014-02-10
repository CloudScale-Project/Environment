package eu.cloudscaleproject.env.csm.diagram.editor.extractor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;

import eu.cloudscaleproject.env.csm.architecture.ApplicationServiceContainer;
import eu.cloudscaleproject.env.csm.architecture.DeployableApplicationService;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.converter.ConverterService;
import eu.cloudscaleproject.env.csm.converter.ICSMConverterCallback;
import eu.cloudscaleproject.env.csm.converter.ICsmConverter;
import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.diagram.editor.extractor.wizard.ImportModelWizard;

public class BasicComposite extends Composite {
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	private PlatformService platformService;
	private Label lblRepositoryModelLoc;
	private Label lblSystemModelLoc;
	private Button btnRemove;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BasicComposite(Composite parent, Entity entity) {
		super(parent, SWT.NONE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		this.platformService = (PlatformService)entity;
		
		setLayout(new FormLayout());
		
		Label lblRepositoryModel = new Label(this, SWT.NONE);
		lblRepositoryModel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblRepositoryModel = new FormData();
		fd_lblRepositoryModel.left = new FormAttachment(0, 12);
		fd_lblRepositoryModel.top = new FormAttachment(0, 10);
		lblRepositoryModel.setLayoutData(fd_lblRepositoryModel);
		lblRepositoryModel.setText("Repository model:");
		
		Label lblSystemModel = new Label(this, SWT.NONE);
		lblSystemModel.setText("System model:");
		lblSystemModel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblSystemModel = new FormData();
		fd_lblSystemModel.left = new FormAttachment(0, 12);
		lblSystemModel.setLayoutData(fd_lblSystemModel);
		
		this.lblRepositoryModelLoc = formToolkit.createLabel(this, "No model selected ...", SWT.NONE);
		lblRepositoryModelLoc.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		FormData fd_lblRepositoryModelLoc = new FormData();
		fd_lblRepositoryModelLoc.left = new FormAttachment(lblRepositoryModel, 6);
		fd_lblRepositoryModelLoc.top = new FormAttachment(0, 10);
		lblRepositoryModelLoc.setLayoutData(fd_lblRepositoryModelLoc);
		
		this.lblSystemModelLoc = formToolkit.createLabel(this, "No model selected ...", SWT.NONE);
		fd_lblSystemModel.bottom = new FormAttachment(lblSystemModelLoc, 0, SWT.BOTTOM);
		lblSystemModelLoc.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		FormData fd_lblSystemModelLoc = new FormData();
		fd_lblSystemModelLoc.top = new FormAttachment(lblRepositoryModelLoc, 6);
		fd_lblSystemModelLoc.left = new FormAttachment(lblRepositoryModelLoc, 0, SWT.LEFT);
		lblSystemModelLoc.setLayoutData(fd_lblSystemModelLoc);
		
		Button btnImportPcmModel = formToolkit.createButton(this, "Import PCM model", SWT.NONE);
		FormData fd_btnImportPcmModel = new FormData();
		fd_btnImportPcmModel.top = new FormAttachment(lblSystemModel, 22);
		fd_btnImportPcmModel.left = new FormAttachment(lblRepositoryModel, 0, SWT.LEFT);
		btnImportPcmModel.setLayoutData(fd_btnImportPcmModel);
		
		this.btnRemove = new Button(this, SWT.NONE);
		FormData fd_btnRemove = new FormData();
		fd_btnRemove.top = new FormAttachment(btnImportPcmModel, 0, SWT.TOP);
		fd_btnRemove.left = new FormAttachment(btnImportPcmModel, 6);
		btnRemove.setLayoutData(fd_btnRemove);
		formToolkit.adapt(btnRemove, true, true);
		btnRemove.setText("Remove ");
		
		init();
		
		if(this.platformService instanceof ApplicationServiceContainer){
			final ApplicationServiceContainer asContainer = (ApplicationServiceContainer)this.platformService;
			btnImportPcmModel.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ImportModelWizard wizard = new ImportModelWizard(asContainer.getApplicationServices().get(0));
					WizardDialog dialog = new WizardDialog(getShell(), wizard);
					
					if (dialog.open() == Window.OK)
					{
						importModels();
						init();
					}
					else
					{
						// TODO
					}
				}
			});
		}
		
		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeModels();
			}
		});
	}
	
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	
	private void init ()
	{
		if(platformService instanceof ApplicationServiceContainer){
			ApplicationServiceContainer asContainer = (ApplicationServiceContainer)platformService;
			
			DeployableApplicationService appService = asContainer.getApplicationServices().get(0);
			
			
			Object resResource = appService.getAvMap().get(ICsmConverter.KEY_RESOURCE_MODEL);
			Object resSystem = appService.getAvMap().get(ICsmConverter.KEY_SYSTEM_MODEL);
			
			boolean btnRemoveEnabled = true;
			if (resResource==null || resResource.toString().isEmpty())
			{
				resResource = "Model not selected ...";
				btnRemoveEnabled = false;
			}
			
			if (resSystem==null || resSystem.toString().isEmpty())
			{
				resSystem = "Model not selected ...";
				btnRemoveEnabled = false;
			}
			
			lblRepositoryModelLoc.setText(resResource.toString());
			lblSystemModelLoc.setText(resSystem.toString());
			
			this.btnRemove.setEnabled(btnRemoveEnabled);
		}
	}
	
	
	
	private void importModels ()
	{
		// TODO : Monitor - progress bar
		if(platformService instanceof ApplicationServiceContainer){
			ApplicationServiceContainer asContainer = (ApplicationServiceContainer)platformService;
			ConverterService.getInstance().addExternalModel(asContainer.getApplicationServices().get(0), null);
		}
	}
	
	private void removeModels ()
	{
		// TODO : Monitor - progress bar
		if(platformService instanceof ApplicationServiceContainer){
			ApplicationServiceContainer asContainer = (ApplicationServiceContainer)platformService;
			ConverterService.getInstance().removeExternalModel(asContainer.getApplicationServices().get(0), new ICSMConverterCallback() {
				@Override
				public void callback() {
					deleteModelFiles();
				}
			});
		}
	}
	
	private void deleteModelFiles ()
	{
		if(platformService instanceof ApplicationServiceContainer){
			ApplicationServiceContainer asContainer = (ApplicationServiceContainer)platformService;
			final DeployableApplicationService appService = asContainer.getApplicationServices().get(0);
			String resResource = appService.getAvMap().get(ICsmConverter.KEY_RESOURCE_MODEL).toString();
			String resSystem = appService.getAvMap().get(ICsmConverter.KEY_SYSTEM_MODEL).toString();
			
			// Remove model information from CSM 
			TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(appService.eResource().getResourceSet());
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					appService.getAvMap().remove(ICsmConverter.KEY_RESOURCE_MODEL);
					appService.getAvMap().remove(ICsmConverter.KEY_SYSTEM_MODEL);
				}
			});
			
			init();
			
			// Delete imported models
			IFile fResource = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(resResource));
			IFile fSystem = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(resSystem));
			
			try {
				fResource.delete(true, null);
				fSystem.delete(true, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
