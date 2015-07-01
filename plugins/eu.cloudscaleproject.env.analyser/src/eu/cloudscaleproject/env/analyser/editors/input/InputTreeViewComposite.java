package eu.cloudscaleproject.env.analyser.editors.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISources;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.services.IServiceLocator;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.analyser.wizard.ImportPCMModelWizard;
import eu.cloudscaleproject.env.analyser.wizard.NewPCMModelWizard;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;

public class InputTreeViewComposite extends Composite implements IPropertySheetPageProvider{

	private Composite buttonsComposite;
	private EMFEditableTreeviewComposite treeviewComposite;
	
	private final Button btnCreate;
	private final Button btnImport;
	private final Button btnDelete;
	
	private final InputAlternative alternative;
	
	ISelectionChangedListener treeViewSelectionListener = new ISelectionChangedListener() {
		
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			IStructuredSelection selection = (IStructuredSelection)event.getSelection();
			Object selected = selection.getFirstElement();
			
			if(btnDelete == null || btnDelete.isDisposed()){
				return;
			}
			
			if(selection.isEmpty()){
				btnDelete.setEnabled(false);
				return;
			}
			
			if(selected instanceof EObject 
					&& EcoreUtil.getRootContainer((EObject)selected) == selected){
				btnDelete.setEnabled(true);
			}
			else{
				btnDelete.setEnabled(false);
			}
			
		}
	};
	
	public InputTreeViewComposite(final IEditorPart editor, final InputAlternative input, Composite parent, int style) {
		super(parent, style);
		
		alternative = input;
		setLayout(new GridLayout(2, false));
		
		
		treeviewComposite = new EMFEditableTreeviewComposite(input, editor.getEditorSite(), this, SWT.NONE){
			
			@Override
			protected void menuAboutToShow(IMenuManager menuManager, EObject selectedElement) {
				// TODO: AT branding
				menuManager.add(createMDSDProfilesMenu());
			}
		};
		
		treeviewComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		buttonsComposite = new Composite(this, SWT.NONE);
		buttonsComposite.setLayout(new GridLayout(1, true));
		buttonsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
		
		btnCreate = new Button(buttonsComposite, SWT.NONE);
		btnCreate.setText("Create...");
		btnCreate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				List<ModelType> avaiableModelTypes = getAvaiableModelTypes();
				
				NewPCMModelWizard createEmptyModelWizard = new NewPCMModelWizard(
						alternative, 
						avaiableModelTypes.toArray(new ModelType[avaiableModelTypes.size()]));
						
				WizardDialog wizardDialog = new WizardDialog(InputTreeViewComposite.this.getShell(), createEmptyModelWizard);
				wizardDialog.open();
				
				refreshButtonStates();
				super.widgetSelected(e);
			}
		});
		btnImport = new Button(buttonsComposite, SWT.NONE);
		btnImport.setText("Import...");
		btnImport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//ImportInputAlternativeDialog dialog = new ImportInputAlternativeDialog(Display.getDefault().getActiveShell());
				//dialog.open();
				
				ImportPCMModelWizard createEmptyModelWizard = new ImportPCMModelWizard(input);
				WizardDialog wizardDialog = new WizardDialog(InputTreeViewComposite.this.getShell(), createEmptyModelWizard);
				wizardDialog.setTitle("asdf");
				wizardDialog.open();
				
				refreshButtonStates();
				super.widgetSelected(e);
			}
		});
		btnDelete = new Button(buttonsComposite, SWT.NONE);
		btnDelete.setText("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IFile file = treeviewComposite.getSelectedModelFile();
				if(file != null){
					boolean isInternal = input.isResourceInternal(file);
					input.removeSubResourceModel(file);
					
					if(isInternal){
						try {
							file.delete(true, null);
						} catch (CoreException e1) {
							e1.printStackTrace();
						}
					}
				}
				
				refreshButtonStates();
			}
		});
		
		treeviewComposite.getTreeViewer().addSelectionChangedListener(treeViewSelectionListener);
		addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				treeviewComposite.getTreeViewer().removeSelectionChangedListener(treeViewSelectionListener);
			}
		});
	}
	
	private MenuManager createMDSDProfilesMenu ()
	{
		final String cmdProfiles = "org.palladiosimulator.mdsdprofiles.ui.commands.ApplyUnapplyProfiles";
		final String cmdStereotypes = "org.palladiosimulator.mdsdprofiles.ui.commands.ApplyUnapplyStereotypes";
		MenuManager mm = new MenuManager("MDSD Profiles...");

		mm.add(new Action("Apply/Unapply Profiles")
		{
			@Override
			public void run()
			{
				executeCommand(cmdProfiles,treeviewComposite.getTreeViewer().getSelection());
			}
		});

		mm.add(new Action("Apply/Unapply Stereotype")
		{
			@Override
			public void run()
			{
				executeCommand(cmdStereotypes,treeviewComposite.getTreeViewer().getSelection());
			}
			
		});

		return mm;
	}
	
	private void executeCommand (String cmdId, ISelection selection)
	{

		try  {
			// TODO: move to common helpers
			IServiceLocator serviceLocator = PlatformUI.getWorkbench();
			ICommandService commandService = (ICommandService) serviceLocator.getService(ICommandService.class);

		    Command command = commandService.getCommand(cmdId);
		    
		    EvaluationContext c = new EvaluationContext(null, "non null value");
		    c.addVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME, selection);
		    c.addVariable(ISources.ACTIVE_SHELL_NAME, Display.getDefault().getActiveShell());

		    ExecutionEvent e = new ExecutionEvent(command, new HashMap<Object,Object>(), this, c);
		    command.executeWithChecks(e);

		} catch (Exception e) {
		    
		    e.printStackTrace();
		}
	}
	
	private void refreshButtonStates(){
		List<ModelType> avaiableModelTypes = getAvaiableModelTypes();
		if(avaiableModelTypes.isEmpty()){
			btnCreate.setEnabled(false);
			btnImport.setEnabled(false);
		}
		else{
			btnCreate.setEnabled(true);
			btnImport.setEnabled(true);
		}
	}
	
	private List<ModelType> getAvaiableModelTypes(){
		ModelType[] types = new ModelType[]{
				ModelType.REPOSITORY,
				ModelType.SYSTEM,
				ModelType.RESOURCE,
				ModelType.ALLOCATION,
				ModelType.USAGE
		};
		
		List<ModelType> modelTypes = new ArrayList<ModelType>();
		for(ModelType mt : types){
			
			//allow multiple repository models
			if(mt == ModelType.REPOSITORY){
				modelTypes.add(mt);
				continue;
			}
			
			if(alternative.getModelRoot(mt.getToolchainFileID()).isEmpty()){
				modelTypes.add(mt);
			}
		}
		
		return modelTypes;
	}

	@Override
	public IPropertySheetPage getPropertySheetPage() {
		return treeviewComposite.getPropertySheetPage();
	}
	
	
	
}
