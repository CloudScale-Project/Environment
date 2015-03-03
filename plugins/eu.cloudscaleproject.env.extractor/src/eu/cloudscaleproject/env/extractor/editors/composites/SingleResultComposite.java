package eu.cloudscaleproject.env.extractor.editors.composites;

import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ibm.icu.text.SimpleDateFormat;

import eu.cloudscaleproject.env.common.ui.TitleComposite;
import eu.cloudscaleproject.env.extractor.ResultPersistenceFolder;

public class SingleResultComposite extends TitleComposite {


	private ResultPersistenceFolder resultPersistenceFolder;
	private Label lblAlternativeName;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SingleResultComposite(Composite parent, int style, ResultPersistenceFolder rif) {
		super(parent, style);
		
		this.resultPersistenceFolder = rif;

		getContainer().setLayout(new GridLayout(3, false));
		
		Label lblRepositoryTitle = new Label(getContainer(), SWT.NONE);
		lblRepositoryTitle.setFont(SWTResourceManager.getFont("Sans", 11, SWT.NORMAL));
		lblRepositoryTitle.setText("Repository:");
		
		Label lblRepositoryModel = new Label(getContainer(), SWT.NONE);
		lblRepositoryModel.setText(ResultPersistenceFolder.RESULT_REPOSITORY);
		
		Button btnViewRepository = new Button(getContainer(), SWT.NONE);
		btnViewRepository.setText("View");
		
		Label lblSystemTitle = new Label(getContainer(), SWT.NONE);
		lblSystemTitle.setFont(SWTResourceManager.getFont("Sans", 11, SWT.NORMAL));
		lblSystemTitle.setText("System:");
		
		Label lblSystemModel = new Label(getContainer(), SWT.NONE);
		lblSystemModel.setText(ResultPersistenceFolder.RESULT_SYSTEM);
		
		Button btnViewSystem = new Button(getContainer(), SWT.NONE);
		btnViewSystem.setText("View");
		
		
		/////
		
		
		btnViewRepository.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					IFile diagramFile = (IFile)resultPersistenceFolder.getSubResource(ResultPersistenceFolder.KEY_REPOSITORY_DIAGRAM);
					IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), diagramFile);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		btnViewSystem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					IFile diagramFile = (IFile)resultPersistenceFolder.getSubResource(ResultPersistenceFolder.KEY_SYSTEM_DIAGRAM);
					IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), diagramFile);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		init();
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yy hh:mm:ss");
	private void init()
	{
		try
		{
			setTitle(this.resultPersistenceFolder.getName());
			layout();
		}
		catch (Exception e)
		{
			lblAlternativeName.setText("n/a");
		}
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		resultPersistenceFolder.load();
		init();
		super.update();
	}
}
