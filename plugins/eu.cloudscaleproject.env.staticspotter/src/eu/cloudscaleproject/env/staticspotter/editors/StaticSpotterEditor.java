package eu.cloudscaleproject.env.staticspotter.editors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

public class StaticSpotterEditor extends Composite {

	private final Composite compositeView;
	private final StackLayout stackLayout;
	
	private final IntroComposite introComposite;
	private InputComposite inputComposite;
	private RunComposite runComposite;
	private ResultsComposite resultsComposite;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public StaticSpotterEditor(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));
		
		stackLayout = new StackLayout();
		Composite compositeButtons = new Composite(this, SWT.NONE);
		compositeButtons.setBackground(SWTResourceManager.getColor(84,118,147));
		compositeButtons.setLayout(new GridLayout(1, false));
		GridData gd_compositeButtons = new GridData(SWT.CENTER, SWT.FILL, false, false, 1, 1);
		gd_compositeButtons.widthHint = 160;
		gd_compositeButtons.minimumWidth = 120;
		compositeButtons.setLayoutData(gd_compositeButtons);
		
		compositeView = new Composite(this, SWT.NONE);
		compositeView.setLayout(stackLayout);
		compositeView.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeView.setBounds(0, 0, 64, 64);
		
		final Button btnIntro = new Button(compositeButtons, SWT.NONE);
		btnIntro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnIntro.setText("Intro");
		
		final Button btnInput = new Button(compositeButtons, SWT.NONE);
		btnInput.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnInput.setText("Input");

		final Button btnRun = new Button(compositeButtons, SWT.NONE);
		btnRun.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnRun.setText("Run");

		final Button btnResults = new Button(compositeButtons, SWT.NONE);
		btnResults.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnResults.setText("Results");
		
		
		// Composites
		introComposite = new IntroComposite(compositeView, SWT.NONE);
		introComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		inputComposite = new InputComposite(compositeView, style);
		inputComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		runComposite = new RunComposite(compositeView, style);
		runComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		resultsComposite = new ResultsComposite(compositeView, style);
		resultsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		
		SelectionListener listener = new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (e.widget == btnIntro)
				{
                    stackLayout.topControl = introComposite;
				}
				else if (e.widget == btnInput)
				{
                    stackLayout.topControl = inputComposite;
				}
				else if (e.widget == btnRun)
				{
                    stackLayout.topControl = runComposite;
				}
				else if (e.widget == btnResults)
				{
                    stackLayout.topControl = resultsComposite;
				}

				compositeView.layout();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		btnIntro.addSelectionListener(listener);
		btnInput.addSelectionListener(listener);
		btnRun.addSelectionListener(listener);
		btnResults.addSelectionListener(listener);
		
		stackLayout.topControl = introComposite;
		compositeView.layout();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
