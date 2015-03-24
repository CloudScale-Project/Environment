package eu.cloudscaleproject.env.staticspotter.editors;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.staticspotter.ResultPersistenceFolder;
import eu.cloudscaleproject.env.staticspotter.editors.composites.SingleResultComposite;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class ResultComposite extends SidebarEditorComposite {
	
	private final IProject project;
	private final String[] sections = new String[]{"Results:"};
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ResultComposite(IProject project, Composite parent, int style) {
		super(parent, style);
		
		this.project = project;
		
		IFolder extractorFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_STATIC_SPOTTER);
		IFolder extractorConfigurationFolder = extractorFolder.getFolder(
				ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_RESULTS));
		
		setResourceProvider(new ResourceProvider(extractorConfigurationFolder, "Result") {
			
			@Override
			public boolean validateResource(IResource res) {
				if(res instanceof IFolder){
					return true;
				}
				return false;
			}

			@Override
			public IEditorInputResource loadResource(IResource res, String type) {
				// TODO Auto-generated method stub
				ResultPersistenceFolder rif = new ResultPersistenceFolder(ResultComposite.this.project, (IFolder)res);
				rif.load();
				return rif;
			}
			
			@Override
			public IResource createResource(String name) {

				IFolder folder = getRootFolder().getFolder(name); 
				ResultPersistenceFolder rif = new ResultPersistenceFolder(ResultComposite.this.project, folder);
				rif.create();
				rif.save();
				return folder;
			}
			
		});
		
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

				return new SingleResultComposite(parent, style, (ResultPersistenceFolder)resource);
			}
		});
		
	}
	
	@Override
	public void update() {
		super.update();
	}
}
