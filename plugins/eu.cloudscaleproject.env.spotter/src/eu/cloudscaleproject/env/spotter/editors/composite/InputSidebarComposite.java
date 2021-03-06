package eu.cloudscaleproject.env.spotter.editors.composite;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.spotter.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class InputSidebarComposite extends SidebarEditorComposite{
	
	private final String[] sections = new String[]{"Inputs:"};
	private final ResourceProvider resourceProvider;
		
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InputSidebarComposite(final IProject project, Composite parent, int style) {
		super(parent, style);
		
		this.resourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.SPOTTER_DYN_INPUT);
		
		setResourceProvider(resourceProvider);
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
				
				return new InputAlternativeComposite(project, parent, style, (InputAlternative)resource);
			}
		});
	}
}
