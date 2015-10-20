package eu.cloudscaleproject.env.spotter.editors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.resources.IResource;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.spotter.editors.composite.ServerClientComposite;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ServerEditor {

	@SuppressWarnings("unused")
	private Composite composite;
	
	@Inject
	@Optional
	@PostConstruct
	public void postConstruct(Composite parent, IResource resource){
		composite = new ServerClientComposite(resource.getProject(), parent, SWT.NONE);
		parent.layout();
		parent.redraw();
	}
	
}
