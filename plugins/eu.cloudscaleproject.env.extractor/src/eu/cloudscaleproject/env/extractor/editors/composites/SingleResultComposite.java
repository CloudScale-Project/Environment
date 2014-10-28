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

import eu.cloudscaleproject.env.extractor.ResultPersistenceFolder;

public class SingleResultComposite extends Composite {


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

		this.setLayout(new GridLayout(3, false));
		
		Label lblTitle = new Label(this, SWT.NONE);
		lblTitle.setFont(SWTResourceManager.getFont("Sans", 14, SWT.NORMAL));
		lblTitle.setText("Result");
		
		lblAlternativeName = new Label(this, SWT.NONE);
		lblAlternativeName.setFont(SWTResourceManager.getFont("Sans", 11, SWT.NORMAL));

		new Label(this, SWT.NONE);
		
		Label label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		GridData gd_label = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		gd_label.widthHint = 167;
		label.setLayoutData(gd_label);
		
		
		Label lblRepositoryTitle = new Label(this, SWT.NONE);
		lblRepositoryTitle.setFont(SWTResourceManager.getFont("Sans", 11, SWT.NORMAL));
		lblRepositoryTitle.setText("Repository:");
		
		Label lblRepositoryModel = new Label(this, SWT.NONE);
		lblRepositoryModel.setText(ResultPersistenceFolder.RESULT_REPOSITORY);
		
		Button btnViewRepository = new Button(this, SWT.NONE);
		btnViewRepository.setText("View");
		
		Label lblSystemTitle = new Label(this, SWT.NONE);
		lblSystemTitle.setFont(SWTResourceManager.getFont("Sans", 11, SWT.NORMAL));
		lblSystemTitle.setText("System:");
		
		Label lblSystemModel = new Label(this, SWT.NONE);
		lblSystemModel.setText(ResultPersistenceFolder.RESULT_SYSTEM);
		
		Button btnViewSystem = new Button(this, SWT.NONE);
		btnViewSystem.setText("View");
		
		
		/////
		
		
		btnViewRepository.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					IFile diagramFile = resultPersistenceFolder.getFileResource(ResultPersistenceFolder.KEY_REPOSITORY_DIAGRAM);
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
					IFile diagramFile = resultPersistenceFolder.getFileResource(ResultPersistenceFolder.KEY_SYSTEM_DIAGRAM);
					IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), diagramFile);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		
		
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		init();
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yy hh:mm:ss");
	private void init()
	{
		try
		{
			long timestamp = Long.parseLong(resultPersistenceFolder.getProperty(ResultPersistenceFolder.KEY_TIMESTAMP));
			Date date = new Date(timestamp);
			lblAlternativeName.setText(resultPersistenceFolder.getName() +"  --  "+sdf.format(date));
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
