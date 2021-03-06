package eu.cloudscaleproject.env.spotter.editors.composite;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.spotter.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class ResultsSidebarComposite extends SidebarEditorComposite{

	private final String[] sections = new String[]{"Results:"};
		
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ResultsSidebarComposite(final IProject project, Composite parent, int style) {
		super(parent, style);
		
		setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.SPOTTER_DYN_RES));
		setContentProvider(new SidebarContentProvider() {
			
			@Override
			public String[] getSections() {
				return sections;
			}
			
			@Override
			public String getSection(IEditorInputResource resource) {
				return sections[0];
			}
			
			@Override
			public Composite createComposite(Composite parent, int style,
					IEditorInputResource resource) {
				
				ResultAlternativeComposite rc = new ResultAlternativeComposite(parent, SWT.NONE, (ResultAlternative)resource);
				return rc;
			}
		});

		setNewButtonEnabled(false);
		setNewFromButtonEnabled(false);
	}

	@Override
	protected void checkSubclass() {
	}
}
