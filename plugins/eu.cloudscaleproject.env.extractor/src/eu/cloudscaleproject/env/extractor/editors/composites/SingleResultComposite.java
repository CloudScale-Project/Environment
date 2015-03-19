package eu.cloudscaleproject.env.extractor.editors.composites;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.wb.swt.SWTResourceManager;

import eu.cloudscaleproject.env.extractor.ResultPersistenceFolder;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.ui.TitleComposite;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;

public class SingleResultComposite extends TitleComposite implements IPropertySheetPageProvider{


	private ResultPersistenceFolder resultPersistenceFolder;
	private EMFEditableTreeviewComposite treeViewComposite;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SingleResultComposite(Composite parent, int style, ResultPersistenceFolder rif) {
		super(parent, style);
		
		this.resultPersistenceFolder = rif;

		getContainer().setLayout(new GridLayout(3, false));
		
		//
		// Repository
		// 
		Label lblRepositoryTitle = new Label(getContainer(), SWT.NONE);
		lblRepositoryTitle.setFont(SWTResourceManager.getFont("Sans", 11, SWT.NORMAL));
		lblRepositoryTitle.setText("Repository:");
		Button btnViewRepositoryEditor = new Button(getContainer(), SWT.NONE);
		btnViewRepositoryEditor.setText("Editor");
		Button btnViewRepositoryDiagram = new Button(getContainer(), SWT.NONE);
		btnViewRepositoryDiagram.setText("Diagram");
		
		//
		// System
		//
		Label lblSystemTitle = new Label(getContainer(), SWT.NONE);
		lblSystemTitle.setFont(SWTResourceManager.getFont("Sans", 11, SWT.NORMAL));
		lblSystemTitle.setText("System:");
		Button btnViewSystemEditor = new Button(getContainer(), SWT.NONE);
		btnViewSystemEditor.setText("Editor");
		Button btnViewSystemDiagram = new Button(getContainer(), SWT.NONE);
		btnViewSystemDiagram.setText("Diagram");
		
		//
		// Treeview
		//
		Group containerEditor = new Group(getContainer(), SWT.NONE);
		containerEditor.setText("Results preview");
		containerEditor.setLayout(new FillLayout(SWT.HORIZONTAL));
		containerEditor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

		this.treeViewComposite = new EMFEditableTreeviewComposite(resultPersistenceFolder, containerEditor, SWT.NONE);
		
        btnViewRepositoryEditor.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                        openResource(ToolchainUtils.KEY_FILE_REPOSITORY);
                }
        });
        btnViewRepositoryDiagram.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                        openResource(ResultPersistenceFolder.KEY_FILE_REPOSITORY_DIAGRAM);
                }
        });
        btnViewSystemEditor.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                        openResource(ToolchainUtils.KEY_FILE_SYSTEM);
                }
        });
        btnViewSystemDiagram.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                        openResource(ResultPersistenceFolder.KEY_FILE_SYSTEM_DIAGRAM);
                }
        });


		init();
	}
	
	private void openResource (String key)
	{
        try {
                IFile diagramFile = (IFile)resultPersistenceFolder.getSubResource(key);
                IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), diagramFile);
        } catch (Exception e1) {
                e1.printStackTrace();
        }
		
	}
	
	private void init()
	{
		setTitle(this.resultPersistenceFolder.getName());
		layout();
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		resultPersistenceFolder.load();
		init();
		super.update();
	}

	@Override
	public IPropertySheetPage getPropertySheetPage()
	{
		return treeViewComposite.getPropertySheetPage();
	}
}
