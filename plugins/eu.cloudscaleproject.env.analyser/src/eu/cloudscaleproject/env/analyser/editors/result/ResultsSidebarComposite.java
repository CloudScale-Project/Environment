package eu.cloudscaleproject.env.analyser.editors.result;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;

import eu.cloudscaleproject.env.analyser.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class ResultsSidebarComposite extends SidebarEditorComposite{
	
	private static final String[] sections = new String[]{"Results"};
	private final IProject project;

	public ResultsSidebarComposite(final IEditorPart editor, Composite parent, int style) {
		super(parent, style);
		this.project = ExplorerProjectPaths.getProject(editor);
		
		setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.ANALYSER_RES));
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
				
				return new ResultsComposite((ResultAlternative)resource, parent, SWT.NONE);
			}
		});
		
		//disable buttons for creating new alternatives
		setNewButtonEnabled(false);
		setNewFromButtonEnabled(false);
	}
}
