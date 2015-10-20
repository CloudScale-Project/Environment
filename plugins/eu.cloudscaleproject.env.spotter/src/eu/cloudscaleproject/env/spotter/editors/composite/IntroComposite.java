package eu.cloudscaleproject.env.spotter.editors.composite;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.Bundle;

import eu.cloudscaleproject.env.spotter.Activator;

public class IntroComposite extends Composite {

	private URL introUrl;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public IntroComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Bundle plugin = Activator.getDefault().getBundle();
		
		try {
			// Cache entire folder
			// (needed in product export, to cache files, so it is possible to
			// reference them)
			URL folder = FileLocator.find(plugin, new Path("resources/html/"), null);
			FileLocator.toFileURL(folder);
			
			IPath relativePagePath = new Path("resources/html/intro.html");
			URL fileInPlugin = FileLocator.find(plugin, relativePagePath, null);
			introUrl = FileLocator.toFileURL(fileInPlugin);
			Browser browser = new Browser(this, SWT.NONE);
			browser.setUrl(introUrl.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
