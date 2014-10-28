package eu.cloudscaleproject.env.extractor.editors;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.extractor.ConfigPersistenceFolder;
import eu.cloudscaleproject.env.extractor.editors.composites.ConfigAlternativeComposite;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class ConfigComposite extends SidebarEditorComposite {
	
	private final IProject project;
	private final String[] sections = new String[]{"Configurations:"};
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ConfigComposite(IProject project, Composite parent, int style) {
		super(parent, style);
		
		this.project = project;
		
		IFolder extractorFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_EXTRACTOR);
		IFolder extractorConfigurationFolder = extractorFolder.getFolder(
				ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_CONFIGURATION));
		
		setResourceProvider(new ResourceProvider(project, extractorConfigurationFolder, "Alternative") {
			
			@Override
			public boolean validateResource(IResource res) {
				if(res instanceof IFolder){
					return true;
				}
				return false;
			}

			@Override
			public IEditorInputResource loadResource(IResource res) {
				// TODO Auto-generated method stub
				ConfigPersistenceFolder cif = new ConfigPersistenceFolder(ConfigComposite.this.project, (IFolder)res);
				return cif;
			}
			
			@Override
			public IResource createResource(String name) {

				IFolder folder = getRootFolder().getFolder(name); 
				ConfigPersistenceFolder cif = new ConfigPersistenceFolder(ConfigComposite.this.project, folder);
				cif.create();
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
				return new ConfigAlternativeComposite(parent, style, (ConfigPersistenceFolder)resource);
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
