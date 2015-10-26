package eu.cloudscaleproject.env.spotter.editors.composite;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.spotter.alternatives.ConfigAlternative;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class ConfSidebarComposite extends SidebarEditorComposite {

	private final String[] sections = new String[]{"Run configurations:"};
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ConfSidebarComposite(final IProject project, Composite parent, int style) {
		super(parent, style);
		
		setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.SPOTTER_DYN_CONF));
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
				
				return new ConfigAlternativeComposite(project, parent, style, (ConfigAlternative)resource);
			}
		});
	}

	@Override
	protected void checkSubclass() {
	}
}
