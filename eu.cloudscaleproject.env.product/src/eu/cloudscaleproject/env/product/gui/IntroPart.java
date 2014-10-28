package eu.cloudscaleproject.env.product.gui;

import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.osgi.framework.Bundle;

import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.common.CommonResources;
import eu.cloudscaleproject.env.common.ui.resources.ResourceManager;
import eu.cloudscaleproject.env.product.Activator;

public class IntroPart {

	@Inject private CommandExecutor commandExecutor;
	
	private URL introUrl;
	
	public IntroPart() {
		try {
			Bundle plugin = Activator.getDefault().getBundle();
			
			
			// Cache entire folder 
			//(needed in product export, to cache files, so it is possible to reference them)
			URL folder = FileLocator.find(plugin, new Path("html/"), null);
			FileLocator.toFileURL(folder);
			
			IPath relativePagePath = new Path("html/intro.html");
			URL fileInPlugin = FileLocator.find(plugin, relativePagePath, null);
			introUrl = FileLocator.toFileURL(fileInPlugin);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {
		GridLayout gl_parent = new GridLayout(1, false);
		gl_parent.verticalSpacing = 0;
		parent.setLayout(gl_parent);
		
		Composite header = new Composite(parent, SWT.NONE);
		header.setBackground(CommonResources.getColor(80, 114, 149));
		header.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		header.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite c_header = new Composite(header, SWT.NONE);
		GridLayout gl_c_header = new GridLayout(7, false);
		gl_c_header.horizontalSpacing = 0;
		gl_c_header.verticalSpacing = 0;
		gl_c_header.marginWidth = 0;
		gl_c_header.marginHeight = 0;
		c_header.setLayout(gl_c_header);
		
		Composite c_image = new Composite(c_header, SWT.NONE);
		GridData gd_c_image = new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1);
		gd_c_image.widthHint = 290;
		gd_c_image.heightHint = 90;
		c_image.setLayoutData(gd_c_image);
		
		Composite imageLogo = new Composite(c_image, SWT.NONE);
		imageLogo.setBackgroundImage(ResourceManager.getPluginImage("eu.cloudscaleproject.env.product", "html/img-logo.png"));
		imageLogo.setBounds(0, 0, 241, 50);
		imageLogo.setLocation(40, 20);
		
		Composite composite_1 = new Composite(c_header, SWT.NONE);
		GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.FILL, true, true, 1, 1);
		gd_composite_1.widthHint = 30;
		gd_composite_1.heightHint = 90;
		composite_1.setLayoutData(gd_composite_1);
		
		ButtonImg back = new ButtonImg(c_header, 
				ResourceManager.getPluginImage("eu.cloudscaleproject.env.product", "html/ic-back.png"),
				60, 90);
		
		ButtonImg forward = new ButtonImg(c_header, 
				ResourceManager.getPluginImage("eu.cloudscaleproject.env.product", "html/ic-next.png"),
				60, 90);
		
		ButtonImg home = new ButtonImg(c_header, 
				ResourceManager.getPluginImage("eu.cloudscaleproject.env.product", "html/ic-home.png"),
				60, 90);
		
		ButtonImg newProject = new ButtonImg(c_header, 
				ResourceManager.getPluginImage("eu.cloudscaleproject.env.product", "html/ic-newProject.png"),
				60, 90);
		
		ButtonImg workspace = new ButtonImg(c_header, 
				ResourceManager.getPluginImage("eu.cloudscaleproject.env.product", "html/button_enter.png"),
				75, 90);
		new Label(c_header, SWT.NONE);
		new Label(c_header, SWT.NONE);
		new Label(c_header, SWT.NONE);
		new Label(c_header, SWT.NONE);
		
		Composite c_body = new Composite(parent, SWT.NONE);
		c_body.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		c_body.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		final Browser browser = new Browser(c_body, SWT.NONE);
		browser.setUrl(introUrl.toString());
		
		back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				browser.back();
			}
		});
		
		forward.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				browser.forward();
			}
		});
		
		newProject.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				commandExecutor.execute("eu.cloudscaleproject.env.product.command.newProject");
			}
		});
		
		workspace.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				commandExecutor.execute("eu.cloudscaleproject.env.product.command.openCsPerspectiv");
			}
		});
		
		home.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				browser.setUrl(introUrl.toString());
			}
		});
	}

	@PreDestroy
	public void dispose() {
	}
}
