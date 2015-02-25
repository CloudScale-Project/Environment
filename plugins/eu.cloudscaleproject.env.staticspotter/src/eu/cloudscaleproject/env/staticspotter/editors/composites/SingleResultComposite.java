package eu.cloudscaleproject.env.staticspotter.editors.composites;

import java.text.SimpleDateFormat;
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

import eu.cloudscaleproject.env.common.ui.TitleComposite;
import eu.cloudscaleproject.env.staticspotter.ResultPersistenceFolder;
import org.eclipse.swt.layout.FillLayout;



public class SingleResultComposite extends TitleComposite {


	private ResultPersistenceFolder resultPersistenceFolder;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SingleResultComposite(Composite parent, int style, ResultPersistenceFolder rif) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		
		init();
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yy hh:mm:ss");
	private void init()
	{
		try
		{
			long timestamp = Long.parseLong(resultPersistenceFolder.getProperty(ResultPersistenceFolder.KEY_TIMESTAMP));
			Date date = new Date(timestamp);
		}
		catch (Exception e)
		{
			//lblAlternativeName.setText("n/a");
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
