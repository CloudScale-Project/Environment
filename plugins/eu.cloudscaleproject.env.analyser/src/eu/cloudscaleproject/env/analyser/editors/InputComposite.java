package eu.cloudscaleproject.env.analyser.editors;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.analyser.ResourceUtils;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.analyser.dialogs.NewInputAlternativeDialog;
import eu.cloudscaleproject.env.analyser.editors.input.InputTreeViewComposite;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.ui.TitleComposite;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class InputComposite extends SidebarEditorComposite{
	
	private static final String SECTION_GEN = "Generated inputs:";
	private static final String SECTION_ALT = "Alternative inputs:";
	
	private final IProject project;
		
	public InputComposite(final IEditorPart editor, Composite parent, int style) {
		super(parent, style);
		
		this.project = ExplorerProjectPaths.getProject(editor);
		
		setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID));
		setContentProvider(new SidebarContentProvider() {
			
			@Override
			public String[] getSections() {
				return new String[]{SECTION_GEN, SECTION_ALT};
			}
			
			@Override
			public String getSection(IEditorInputResource resource) {
				if(ResourceUtils.ANALYSER_INPUT_GENERATED_RES_NAME.equals(resource.getResource().getName())){
					return SECTION_GEN;
				}
				else{
					return SECTION_ALT;
				}
			}
			
			@Override
			public Composite createComposite(Composite parent, int style, IEditorInputResource resource) {
				return new RightPanelComposite(editor, (InputAlternative)resource, parent, SWT.NONE);
			}
		});
	}
	
	private class RightPanelComposite extends TitleComposite implements IPropertySheetPageProvider{
		
		private InputTreeViewComposite treeviewComposite;

		public RightPanelComposite(IEditorPart editor, InputAlternative input, Composite parent, int style) {
			super(parent, style);
			getContainer().setLayout(new FillLayout());
			setTitle(input.getName());
			treeviewComposite = new InputTreeViewComposite(input, getContainer(), SWT.NONE);
		}

		@Override
		public IPropertySheetPage getPropertySheetPage() {
			if(treeviewComposite != null && !treeviewComposite.isDisposed()){
				return treeviewComposite.getPropertySheetPage();
			}
			return null;
		}
	}

	@Override
	public void handleNewInput(IEditorInput selected) {
		NewInputAlternativeDialog dialog = new NewInputAlternativeDialog(project, getShell());
		dialog.open();
	}
	
	/*
	@Override
	public Composite createInputComposite(IEditorInput input, Composite parent, int style) {
		if(input instanceof InputAlternative){
			return new RightPanelComposite(project, (InputAlternative)input, parent, style);
		}
		return null;
	}

	@Override
	public void handleNewInput(IEditorInput selected) {
		AnalyserUtil.createNewInputAlternative(project, "alternative."+AnalyserUtil.ALTERNATIVE_SUFIX, 
				new BasicCallback<InputAlternative>() {
			
			@Override
			public void handle(InputAlternative object) {
				addSidebarEditor(object, SECTION_ALT);
			}
		});
	}

	@Override
	public void handleNewInputFrom(final IEditorInput selected) {
		
		if(!(selected instanceof IEditorInputResource)){
			return;
		}
		
		AnalyserUtil.createNewInputAlternative(project, "alternative."+AnalyserUtil.ALTERNATIVE_SUFIX, new BasicCallback<InputAlternative>() {
			@Override
			public void handle(InputAlternative object) {
				String name = object.getName();
				object.copyFrom(((IEditorInputResource)selected).getResource());
				object.setName(name);
				addSidebarEditor(object, SECTION_ALT);
			}
		});	
	}

	@Override
	public void handleInputDelete(final IEditorInput toDelete) {
		
		if(!(toDelete instanceof IEditorInputResource)){
			return;
		}
		
		removeSidebarEditor(toDelete);
		((IEditorInputResource)toDelete).delete();
	}

	@Override
	public List<IEditorInput> getInputs(final String section) {
		
		List<IEditorInput> alternatives = new ArrayList<IEditorInput>();
		
		String transformPath = AnalyserUtil.getTransformInputAlternative(project).getProjectPath();
		
		if(SECTION_ALT.equals(section)){
			for(InputAlternative ia : AnalyserUtil.getInputAlternatives(project)){
				if(transformPath == null || !transformPath.equals(ia.getProjectPath())){
					alternatives.add(ia);
				}
			}
		}
		if(SECTION_GEN.equals(section)){
			alternatives.add(AnalyserUtil.getTransformInputAlternative(project));
		}
		
		return alternatives;
	}

	@Override 
	public String[] getSidebarSections() {
		return new String[]{SECTION_GEN, SECTION_ALT};
	}

	@Override
	public IResource[] getDependentRootResource() {
		
		IFolder analyserFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_ANALYSER);
		return new IResource[]{analyserFolder.getFolder(
				ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_INPUT))};
	}
	*/
}
