package eu.cloudscaleproject.env.analyser.gui;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class IntroPart {

	@Inject private ECommandService commandService;
	
	public IntroPart() {
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Button btnStartWorking = new Button(parent, SWT.NONE);
		btnStartWorking.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnStartWorking.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Command com = commandService.getCommand("eu.cludscale.environment.analyser.openPerspective");
				if(com != null && com.isDefined()){
					
						try {
							com.executeWithChecks(new ExecutionEvent(com, new HashMap<String,String>(), this, null));
						} catch (ExecutionException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NotDefinedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NotEnabledException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NotHandledException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
				}
			}
		});
		btnStartWorking.setText("Start working...");
		
		Browser browser = new Browser(parent, SWT.NONE);
		GridData gd_browser = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_browser.heightHint = 115;
		gd_browser.widthHint = 273;
		browser.setLayoutData(gd_browser);
		browser.setUrl("http://www.palladio-simulator.com");
	}

	@PreDestroy
	public void dispose() {
	}

	@Focus
	public void setFocus() {
		// TODO	Set the focus to control
	}

}
