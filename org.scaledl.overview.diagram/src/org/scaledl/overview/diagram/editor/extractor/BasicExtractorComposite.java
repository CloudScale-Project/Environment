package org.scaledl.overview.diagram.editor.extractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
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
import org.scaledl.overview.converter.ConverterService;
import org.scaledl.overview.converter.IOverviewConverter;
import org.scaledl.overview.converter.IOverviewConverterCallback;
import org.scaledl.overview.diagram.SWTResourceManager;
import org.scaledl.overview.diagram.editor.extractor.wizard.ImportModelWizard;

import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.core.Entity;

public class BasicExtractorComposite extends Composite {
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	private SoftwareService applicationService;
	
	private Label lblSystemModelLoc;
	private Label lblRepositoryModelLoc;
	
	private Button btnImport;
	private Button btnRemove;
	
	private static final String EMPTY_LOC_STRING = "No model selected ...";
	
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BasicExtractorComposite(Composite parent, Entity entity) {
		super(parent, SWT.NONE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		this.applicationService = (SoftwareService)entity;
		
		setLayout(new FormLayout());
		
		Label lblRepositoryModel = new Label(this, SWT.NONE);
		lblRepositoryModel.setText("Repository model:");
		lblRepositoryModel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblRepositoryModel = new FormData();
		fd_lblRepositoryModel.top = new FormAttachment(0, 12);
		fd_lblRepositoryModel.left = new FormAttachment(0, 12);
		lblRepositoryModel.setLayoutData(fd_lblRepositoryModel);
		
		this.lblRepositoryModelLoc = formToolkit.createLabel(this, EMPTY_LOC_STRING, SWT.NONE);
		this.lblRepositoryModelLoc.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		FormData fd_lblRepositoryModelLoc = new FormData();
		fd_lblRepositoryModelLoc.top = new FormAttachment(0, 12);
		fd_lblRepositoryModelLoc.left = new FormAttachment(lblRepositoryModel, 6);
		this.lblRepositoryModelLoc.setLayoutData(fd_lblRepositoryModelLoc);
		
		Label lblSystemModel = new Label(this, SWT.NONE);
		lblSystemModel.setText("System model:");
		lblSystemModel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblSystemModel = new FormData();
		fd_lblSystemModel.top = new FormAttachment(lblRepositoryModel, 6);
		fd_lblSystemModel.left = new FormAttachment(0, 12);
		lblSystemModel.setLayoutData(fd_lblSystemModel);
		
		this.lblSystemModelLoc = formToolkit.createLabel(this, EMPTY_LOC_STRING, SWT.NONE);
		this.lblSystemModelLoc.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		FormData fd_lblSystemModelLoc = new FormData();
		fd_lblSystemModelLoc.top = new FormAttachment(this.lblRepositoryModelLoc, 6);
		fd_lblSystemModelLoc.left = new FormAttachment(lblSystemModel, 6);
		this.lblSystemModelLoc.setLayoutData(fd_lblSystemModelLoc);
		
		btnImport = formToolkit.createButton(this, "Import PCM model", SWT.NONE);
		FormData fd_btnImportPcmModel = new FormData();
		fd_btnImportPcmModel.top = new FormAttachment(lblSystemModel, 22);
		btnImport.setLayoutData(fd_btnImportPcmModel);
		
		this.btnRemove = new Button(this, SWT.NONE);
		FormData fd_btnRemove = new FormData();
		fd_btnRemove.top = new FormAttachment(lblSystemModel, 22);
		fd_btnRemove.left = new FormAttachment(btnImport, 3);
		btnRemove.setLayoutData(fd_btnRemove);
		formToolkit.adapt(btnRemove, true, true);
		btnRemove.setText("Remove ");
		
		init();
		
		btnImport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				importSoftwareService();
			}
		});
		
		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeSoftwareService();
			}
		});
	}
	
	
	private void init ()
	{
		EObject system = applicationService.getAeMap().get(IOverviewConverter.KEY_PCM_SYSTEM);
		EObject repository = applicationService.getAeMap().get(IOverviewConverter.KEY_PCM_REPOSITORY);
		
		if (system == null || repository == null)
		{
			lblSystemModelLoc.setText(EMPTY_LOC_STRING);
			lblRepositoryModelLoc.setText(EMPTY_LOC_STRING);
			this.btnRemove.setEnabled(false);
		}
		else{
			lblSystemModelLoc.setText(system.eResource().getURI().toString());
			lblRepositoryModelLoc.setText(repository.eResource().getURI().toString());
			this.btnRemove.setEnabled(true);
		}
	}
	
	private void importSoftwareService(){
		ImportModelWizard wizard = new ImportModelWizard((SoftwareService)applicationService);
		
		WizardDialog dialog = new WizardDialog(getShell(), wizard);
		
		if (dialog.open() == Window.OK)
		{
			init();
		}
	}
	
	private void removeSoftwareService ()
	{
		final EObject system = applicationService.getAeMap().get(IOverviewConverter.KEY_PCM_SYSTEM);
		final EObject repository = applicationService.getAeMap().get(IOverviewConverter.KEY_PCM_REPOSITORY);

		if(system == null) return;
			
		List<EObject> toRemove = new ArrayList<EObject>();
		toRemove.add(repository);
		toRemove.add(system);
		
		
		ConverterService.getInstance().removeExternalModel(applicationService, toRemove, new IOverviewConverterCallback() {
			@Override
			public void callback() {
				 IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(
							 new Path(system.eResource().getURI().trimSegments(1).toPlatformString(true)));
				 
					try {
						system.eResource().delete(null);
						repository.eResource().delete(null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//delete folder
					try {
						folder.delete(true, new NullProgressMonitor());
					} catch (CoreException e) {
						e.printStackTrace();
					}
					
					TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(applicationService.eResource().getResourceSet());
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							applicationService.getAeMap().removeKey(IOverviewConverter.KEY_PCM_SYSTEM);
							applicationService.getAeMap().removeKey(IOverviewConverter.KEY_PCM_REPOSITORY);
						}
					});
						
					init();
			}
		});
	}
}
