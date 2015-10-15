package eu.cloudscaleproject.env.extractor.editors.composites;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.extractor.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class InputSidebarComposite extends SidebarEditorComposite {
	
	private final String[] sections = new String[]{"Inputs:"};
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InputSidebarComposite(IProject project, Composite parent, int style) {
		super(parent, style);
		
		setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, CSTool.EXTRACTOR_INPUT.getID()));
		
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
				return new InputAlternativeComposite(parent, style, (InputAlternative)resource);
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
