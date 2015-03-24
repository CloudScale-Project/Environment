package eu.cloudscaleproject.env.analyser.editors.input;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.analyser.dialogs.ImportInputAlternativeDialog;
import eu.cloudscaleproject.env.analyser.dialogs.NewInputAlternativeDialog;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;

public class InputTreeViewComposite extends Composite implements IPropertySheetPageProvider{

	private Composite buttonsComposite;
	private EMFEditableTreeviewComposite treeviewComposite;
	
	public InputTreeViewComposite(InputAlternative input, Composite parent, int style) {
		super(parent, style);		
		final IProject project = input.getProject();
		
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
				NewInputAlternativeDialog dialog = new NewInputAlternativeDialog(project, Display.getDefault().getActiveShell());
				dialog.open();
				super.widgetSelected(e);
			}
		});
		Button btnImport = new Button(buttonsComposite, SWT.NONE);
		btnImport.setText("Import...");
		btnImport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ImportInputAlternativeDialog dialog = new ImportInputAlternativeDialog(Display.getDefault().getActiveShell());
				dialog.open();
				super.widgetSelected(e);
			}
		});
		Button btnDelete = new Button(buttonsComposite, SWT.NONE);
		btnDelete.setText("Delete...");
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//TODO: delete selected resource
				super.widgetSelected(e);
			}
		});
	}

	@Override
	public IPropertySheetPage getPropertySheetPage() {
		return treeviewComposite.getPropertySheetPage();
	}
	
}
