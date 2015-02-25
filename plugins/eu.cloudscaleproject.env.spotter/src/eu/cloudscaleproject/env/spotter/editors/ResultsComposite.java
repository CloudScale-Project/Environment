package eu.cloudscaleproject.env.spotter.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.spotter.editors.composite.ResultDataComposite;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.AbstractSidebarEditor;
import eu.cloudscaleproject.env.toolchain.util.ISidebarEditor;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditor;

public class ResultsComposite extends Composite{

	private final String[] sectionsAlt = new String[]{"Results from alternative:"};
	private final String[] sectionsRes = new String[]{"Results:"};

	private final IProject project;
	private ISidebarEditor alternativesEditor;	
	private final Composite compositeArea;
		
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ResultsComposite(IProject project, Composite parent, int style) {
		super(parent, style);
		
		this.project = project;
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.horizontalSpacing = 0;
		gridLayout.verticalSpacing = 0;
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		setLayout(gridLayout);
		
		Composite compositeAltSidebar = new Composite(this, SWT.NONE);
		compositeAltSidebar.setLayout(null);
		GridData gd_compositeAltSidebar = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_compositeAltSidebar.minimumWidth = 120;
		compositeAltSidebar.setLayoutData(gd_compositeAltSidebar);
		
		compositeArea = new Composite(this, SWT.NONE);
		compositeArea.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		
		Composite compositeResSidebar = new Composite(this, SWT.NONE);
		compositeResSidebar.setLayout(null);
		GridData gd_compositeResSidebar = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_compositeResSidebar.minimumWidth = 120;
		compositeResSidebar.setLayoutData(gd_compositeResSidebar);
		
		if(alternativesEditor != null){
			alternativesEditor.dispose();
		}
		

		createAlternativeEditor(compositeAltSidebar, compositeResSidebar);
	}
	
	private ISidebarEditor createAlternativeEditor(final Composite sidebar, 
												   final Composite area){
		
		SidebarEditor editor = new SidebarEditor(sidebar, area);
		
		editor.setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.SPOTTER_DYN_RES_ID));
		editor.setContentProvider(new SidebarContentProvider() {
			
			@Override
			public String[] getSections() {
				return sectionsAlt;
			}
			
			@Override
			public String getSection(IEditorInputResource resource) {
				return sectionsAlt[0];
			}
			
			@Override
			public Composite createComposite(Composite parent, int style,
					IEditorInputResource resource) {
								
				Composite sidebar = new Composite(parent, SWT.NONE);
				if(resource instanceof EditorInputFolder){
					createResultEditor((EditorInputFolder)resource, sidebar, compositeArea);
				}
				return sidebar;
			}
		});
		
		editor.setNewButtonEnabled(false);
		editor.setNewFromButtonEnabled(false);
		
		return editor;
	}
	
	private ISidebarEditor createResultEditor(final EditorInputFolder editorInput, 
													Composite sidebar, 
													Composite area){
		
		ISidebarEditor editor = new AbstractSidebarEditor(sidebar, area) {

			@Override
			public Composite createInputComposite(IEditorInput input,
					Composite parent, int style) {
				
				//TODO: test is this is correct! Wait until DynamicSpotter bug is fixed!
				ResultDataComposite rc = new ResultDataComposite(parent, style);
				rc.setResult((EditorInputFolder)input);
				return rc;
				//return new ResultComposite(parent, style, selectedInputName, editorInput.getResource().getFolder(input.getName()));
			}

			@Override
			public List<IEditorInput> getInputs(String section) {
				List<IEditorInput> out = new ArrayList<IEditorInput>();
				
				if(ResultsComposite.this.validateResults(editorInput)){
					return out;
				}
				
				if(section.equals(sectionsRes[0])){
					try {
						for(IResource res : editorInput.getResource().members()){
							if(res instanceof IFolder){
								EditorInputFolder ei = new EditorInputFolder(res.getProject(), (IFolder)res);
								out.add(ei);
							}
						}
					} catch (CoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return out;
			}

			@Override
			public String[] getSidebarSections() {
				return sectionsRes;
			}

		};
		
		editor.setNewButtonEnabled(false);
		editor.setNewFromButtonEnabled(false);
		
		editor.init();
		return editor;
	}
	
	private final boolean validateResults(EditorInputFolder editorInput){
		
		if(editorInput == null){
			return false;
		}
		
		if(!editorInput.getResource().exists()){
			return false;
		}
		
		if(!editorInput.getResource().getFile("results.ser").exists()){
			return false;
		}
		
		if(!editorInput.getResource().getFile("SpotterReport.txt").exists()){
			return false;
		}
		
		return true;
	}

	@Override
	protected void checkSubclass() {
		
	}
}
