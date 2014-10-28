package eu.cloudscaleproject.env.analyser.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.analyser.AnalyserUtil;
import eu.cloudscaleproject.env.analyser.InputAlternative;
import eu.cloudscaleproject.env.analyser.editors.composite.InputAlternativeEditComposite;
import eu.cloudscaleproject.env.analyser.editors.composite.InputAlternativeTreeviewComposite;
import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.AbstractSidebarEditorComposite;

public class InputComposite extends AbstractSidebarEditorComposite{
	
	private static final String SECTION_GEN = "Generated inputs:";
	private static final String SECTION_ALT = "Alternative inputs:";
	
	private final IProject project;
		
	public InputComposite(IProject project, Composite parent, int style) {
		super(parent, style);
		this.project = project;
		init();
	}
	
	private class RightPanelComposite extends Composite{
		
		private InputAlternativeEditComposite editComposite;
		private InputAlternativeTreeviewComposite treeviewComposite;

		public RightPanelComposite(IProject project, InputAlternative input, Composite parent, int style) {
			super(parent, style);
			
			GridLayout layout = new GridLayout(1, true);
			this.setLayout(layout);
			
			editComposite = new InputAlternativeEditComposite(project, input, this, SWT.NONE);
			GridData iac_gd = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
			editComposite.setLayoutData(iac_gd);
			editComposite.pack();
			
			treeviewComposite = new InputAlternativeTreeviewComposite(input, this, SWT.NONE);
			GridData iamc_gd = new GridData(SWT.FILL, SWT.FILL, true, true);
			treeviewComposite.setLayoutData(iamc_gd);
		}
		
		@Override
		public void update() {
			editComposite.update();
			treeviewComposite.update();
			super.update();
		}
	}

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
}
