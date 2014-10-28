package eu.cloudscaleproject.env.extractor.editors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.extractor.InputPersitenceFile;
import eu.cloudscaleproject.env.extractor.editors.composites.InputAlternativeComposite;
import eu.cloudscaleproject.env.extractor.wizard.util.Util;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class InputComposite extends SidebarEditorComposite {
	
	private final IProject project;
	private final String[] sections = new String[]{"Inputs:"};
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InputComposite(IProject project, Composite parent, int style) {
		super(parent, style);
		
		this.project = project;
		
		IFolder extractorInputFolder = Util.getInputFolder(project);
		
		setResourceProvider(new ResourceProvider(project, extractorInputFolder, "Alternative.alt") {
			
			@Override
			public boolean validateResource(IResource res) {
				if(res instanceof IFile){
					return true;
				}
				return false;
			}
			
			@Override
			public IEditorInputResource loadResource(IResource resource) {

				InputPersitenceFile eif = new InputPersitenceFile(InputComposite.this.project, (IFile)resource);
				if (!resource.exists())
					eif.save();
				return eif;
			}
			
			@Override
			public IResource createResource(String resourceName) {
				IFile file = getRootFolder().getFile(resourceName);
				InputPersitenceFile ipf = new InputPersitenceFile(InputComposite.this.project, file);
				ipf.save();
				return file;
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
				return new InputAlternativeComposite(parent, style, (InputPersitenceFile)resource);
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
