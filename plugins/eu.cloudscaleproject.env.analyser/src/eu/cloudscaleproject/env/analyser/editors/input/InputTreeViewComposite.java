package eu.cloudscaleproject.env.analyser.editors.input;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
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
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;
import edu.kit.ipd.sdq.mdsd.profiles.ui.menu.ApplicableStereotypesSubmenu;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.analyser.wizard.NewPCMModelWizard;
import eu.cloudscaleproject.env.analyser.wizard.ImportPCMModelWizard;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;

public class InputTreeViewComposite extends Composite implements IPropertySheetPageProvider{

	private Composite buttonsComposite;
	private EMFEditableTreeviewComposite treeviewComposite;
	private final Button btnDelete;
	
	ISelectionChangedListener treeViewSelectionListener = new ISelectionChangedListener() {
		
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			ISelection selection = event.getSelection();
			
			if(btnDelete == null || btnDelete.isDisposed()){
				return;
			}
			
			if(selection.isEmpty()){
				btnDelete.setEnabled(false);
			}
			else{
				btnDelete.setEnabled(true);
			}
			
		}
	};
	
	public InputTreeViewComposite(final InputAlternative input, Composite parent, int style) {
		super(parent, style);		
		
		setLayout(new GridLayout(2, false));
		
		treeviewComposite = new EMFEditableTreeviewComposite(input, this, SWT.NONE){
			
			@Override
			protected void menuAboutToShow(IMenuManager menuManager, EObject selectedElement) {
				
				EObject root = EcoreUtil.getRootContainer(selectedElement);
				if(root instanceof System || root instanceof ResourceEnvironment){
					
					MenuManager applyStereotypeMenu = new MenuManager("Apply Architecture Template");
					
					ApplicableStereotypesSubmenu applyStereotypeContribution = new ApplicableStereotypesSubmenu();
					applyStereotypeContribution.initialize(PlatformUI.getWorkbench());
					applyStereotypeMenu.add(applyStereotypeContribution);
					
					menuManager.add(applyStereotypeMenu);
				}
			}
		};
		
		treeviewComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		buttonsComposite = new Composite(this, SWT.NONE);
		buttonsComposite.setLayout(new GridLayout(1, true));
		buttonsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
		
		Button btnCreate = new Button(buttonsComposite, SWT.NONE);
		btnCreate.setText("Create...");
		btnCreate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//NewInputAlternativeDialog dialog = new NewInputAlternativeDialog(project, Display.getDefault().getActiveShell());
				//dialog.open();
				
				ModelType[] types = new ModelType[]{
						ModelType.REPOSITORY,
						ModelType.SYSTEM,
						ModelType.RESOURCE,
						ModelType.ALLOCATION,
						ModelType.USAGE
				};
				
				NewPCMModelWizard createEmptyModelWizard = new NewPCMModelWizard(input, types);
				WizardDialog wizardDialog = new WizardDialog(InputTreeViewComposite.this.getShell(), createEmptyModelWizard);
				wizardDialog.open();
				
				super.widgetSelected(e);
			}
		});
		Button btnImport = new Button(buttonsComposite, SWT.NONE);
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
	
	

	@Override
	public IPropertySheetPage getPropertySheetPage() {
		return treeviewComposite.getPropertySheetPage();
	}
	
}
