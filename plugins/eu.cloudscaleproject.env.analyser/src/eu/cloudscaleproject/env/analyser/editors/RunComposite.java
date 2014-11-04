package eu.cloudscaleproject.env.analyser.editors;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.analyser.ConfAlternative;
import eu.cloudscaleproject.env.analyser.ResourceUtils;
import eu.cloudscaleproject.env.analyser.editors.composite.ConfigAlternativeEditComposite;
import eu.cloudscaleproject.env.analyser.editors.composite.ConfigAlternativeTreeviewComposite;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class RunComposite extends SidebarEditorComposite{
	
	private static final String SECTION_DEFAULT = "Default measurements:";
	private static final String SECTION_ALT = "Alternative conf:";
	
	private final IProject project;
	
			
	public RunComposite(IProject project, Composite parent, int style) {
		super(parent, style);
		this.project = project;
		
		setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_CONF_ID));
		setContentProvider(new SidebarContentProvider() {
			
			@Override
			public String[] getSections() {
				return new String[]{SECTION_DEFAULT, SECTION_ALT};
			}
			
			@Override
			public String getSection(IEditorInputResource resource) {
				if(ResourceUtils.ANALYSER_CONF_CAPACITY_ANALYSES.equals(resource.getResource().getName())){
					return SECTION_DEFAULT;
				}
				return SECTION_ALT;
			}
			
			@Override
			public Composite createComposite(Composite parent, int style, IEditorInputResource resource) {
				return new RightPanelComposite(RunComposite.this.project, (ConfAlternative)resource, parent, SWT.NONE);
			}
		});
	}
	
	private class RightPanelComposite extends Composite implements IPropertySheetPageProvider{
		
		private ConfigAlternativeEditComposite editComposite;
		private ConfigAlternativeTreeviewComposite treeviewComposite;

		public RightPanelComposite(IProject project, ConfAlternative input, Composite parent, int style) {
			super(parent, style);
					
			GridLayout layout = new GridLayout(1, true);
			this.setLayout(layout);
			
			editComposite = new ConfigAlternativeEditComposite(project, input, this, SWT.NONE);
			GridData iac_gd = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
			editComposite.setLayoutData(iac_gd);
			editComposite.pack();
			
			treeviewComposite = new ConfigAlternativeTreeviewComposite(project, input, this, style);
			GridData iamc_gd = new GridData(SWT.FILL, SWT.FILL, true, true);
			treeviewComposite.setLayoutData(iamc_gd);
		}
		
		@Override
		public void update() {
			editComposite.update();
			treeviewComposite.update();
			super.update();
		}

		@Override
		public IPropertySheetPage getPropertySheetPage() {
			return null;
		}
	}

	/*
	
	@Override
	public Composite createInputComposite(IEditorInput input, Composite parent, int style) {
		if(input instanceof ConfAlternative){
			return new RightPanelComposite(project, (ConfAlternative)input, parent, style);
		}
		return null;
	}

	@Override
	public void handleNewInput(IEditorInput selected) {
		AnalyserUtil.createNewConfAlternative(project, "alternative."+AnalyserUtil.ALTERNATIVE_SUFIX, 
				new BasicCallback<ConfAlternative>() {
			
			@Override
			public void handle(ConfAlternative object) {
				addSidebarEditor(object, sections[1]);
			}
		});
	}

	@Override
	public void handleNewInputFrom(final IEditorInput selected) {
		
		if(!(selected instanceof IEditorInputResource)){
			return;
		}
		
		AnalyserUtil.createNewConfAlternative(project, "alternative."+AnalyserUtil.ALTERNATIVE_SUFIX, 
				new BasicCallback<ConfAlternative>() {
			
			@Override
			public void handle(ConfAlternative object) {
				String name = object.getName();
				object.copyFrom(((IEditorInputResource)selected).getResource());
				object.setName(name);
				object.save();
				addSidebarEditor(object, sections[1]);
			}
		});
	}

	@Override
	public void handleInputDelete(IEditorInput toDelete) {
		removeSidebarEditor(toDelete);

		if(!(toDelete instanceof IEditorInputResource)){
			return;
		}
		((IEditorInputResource)toDelete).delete();
	}

	@Override
	public List<IEditorInput> getInputs(String section) {
		List<IEditorInput> alt = new ArrayList<IEditorInput>();
		
		try{
			if(section.equals(sections[0])){
				alt.add(AnalyserUtil.retrieveCapacityExperiment(project, false));
			}
			if(section.equals(sections[1])){
				for(ConfAlternative ca : AnalyserUtil.getConfAlternatives(project)){
					if(!ca.getResource().equals(AnalyserUtil.retrieveCapacityExperiment(project, false).getResource())){
						alt.add(ca);
					}
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return alt;
	}

	@Override
	public String[] getSidebarSections() {
		return sections;
	}

	@Override
	public IResource[] getDependentRootResource() {
		IFolder analyserFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_ANALYSER);
		
		return new IResource[]{
				analyserFolder.getFolder(ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_CONFIGURATION)),
				analyserFolder.getFolder(ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_INPUT))
				};
	}
	
	*/
}
