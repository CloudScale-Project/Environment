package eu.cloudscaleproject.env.staticspotter.editors.composites;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.staticspotter.alternatives.ConfigAlternative;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class ConfigSidebarComposite extends SidebarEditorComposite {
	
	private final String[] sections = new String[]{"Configurations:"};
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ConfigSidebarComposite(IProject project, Composite parent, int style) {
		super(parent, style);
		
		setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, CSTool.SPOTTER_STA_CONF));
		
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
				return new ConfigAlternativeComposite(parent, style, (ConfigAlternative)resource);
			}
		});
		
		//init();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}
}
