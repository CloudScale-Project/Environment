package eu.cloudscaleproject.env.analyser.editors.input;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.analyser.PCMModelType;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.analyser.wizard.CreatePCMModelWizard;
import eu.cloudscaleproject.env.analyser.wizard.ImportPCMModelWizard;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;

public class InputTreeViewComposite extends Composite implements IPropertySheetPageProvider{

	private Composite buttonsComposite;
	private EMFEditableTreeviewComposite treeviewComposite;
	
	public InputTreeViewComposite(final InputAlternative input, Composite parent, int style) {
		super(parent, style);		
		
		setLayout(new GridLayout(2, false));
		
		treeviewComposite = new EMFEditableTreeviewComposite(input, this, SWT.NONE);
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
				
				PCMModelType[] types = new PCMModelType[]{
						PCMModelType.REPOSITORY,
						PCMModelType.SYSTEM,
						PCMModelType.RESOURCE,
						PCMModelType.ALLOCATION,
						PCMModelType.USAGE
				};
				
				CreatePCMModelWizard createEmptyModelWizard = new CreatePCMModelWizard(input, types);
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
		Button btnDelete = new Button(buttonsComposite, SWT.NONE);
		btnDelete.setText("Delete...");
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IFile file = treeviewComposite.getSelectedModelFile();
				input.removeSubResourceModel(file);
				try {
					file.delete(true, null);
				} catch (CoreException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	@Override
	public IPropertySheetPage getPropertySheetPage() {
		return treeviewComposite.getPropertySheetPage();
	}
	
}
