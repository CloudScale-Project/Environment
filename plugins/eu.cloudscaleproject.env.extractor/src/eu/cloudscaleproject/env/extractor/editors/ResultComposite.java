package eu.cloudscaleproject.env.extractor.editors;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.extractor.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.extractor.editors.composites.SingleResultComposite;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class ResultComposite extends SidebarEditorComposite {
	
	private final String[] sections = new String[]{"Results:"};
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ResultComposite(IProject project, Composite parent, int style) {
		super(parent, style);
		
		
		setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.EXTRACTOR_RES_ID));
		
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

				return new SingleResultComposite(parent, style, (ResultAlternative)resource);
			}
		});
		
	}
	
	@Override
	public void update() {
		super.update();
	}
}
