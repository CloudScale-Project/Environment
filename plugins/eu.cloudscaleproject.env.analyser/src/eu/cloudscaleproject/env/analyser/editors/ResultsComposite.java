package eu.cloudscaleproject.env.analyser.editors;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.analyser.editors.result.AdvancedResultComposite;
import eu.cloudscaleproject.env.analyser.editors.result.CapacityResultComposite;
import eu.cloudscaleproject.env.analyser.editors.result.MonitorResultsComposite;
import eu.cloudscaleproject.env.analyser.editors.result.ScalabilityResultComposite;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.ui.IRefreshable;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.SidebarContentProvider;
import eu.cloudscaleproject.env.toolchain.util.SidebarEditorComposite;

public class ResultsComposite extends SidebarEditorComposite{
	
	private static final String[] sections = new String[]{"Results"};
	private final IProject project;

	public ResultsComposite(final IEditorPart editor, Composite parent, int style) {
		super(parent, style);
		this.project = ExplorerProjectPaths.getProject(editor);
		
		setResourceProvider(ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_RES_ID));
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
				
				return new RightPanelComposite((ResultAlternative)resource, parent, SWT.NONE);
			}
		});
		
		//disable buttons for creating new alternatives
		setNewButtonEnabled(false);
		setNewFromButtonEnabled(false);
	}

	
	private static class RightPanelComposite extends Composite implements IRefreshable{

		private CTabFolder tabFolder;
		
		private Composite basicResultComposite;
		private AdvancedResultComposite advancedResultComposite;
		
		public RightPanelComposite(ResultAlternative alternative, Composite parent, int style) {
			super(parent, style);
			
			setLayout(new FillLayout());
			
			this.tabFolder = new CTabFolder(this, SWT.BOTTOM);
			
			CTabItem tabBasicResults = new CTabItem(tabFolder, SWT.NONE);
			if(alternative.getTypeEnum().equals(ConfAlternative.Type.NORMAL)){
				basicResultComposite = new MonitorResultsComposite(alternative, tabFolder, SWT.NONE);
			}
			else if(alternative.getTypeEnum().equals(ConfAlternative.Type.CAPACITY)){
				basicResultComposite = new CapacityResultComposite(alternative, tabFolder, SWT.NONE);
			}
			else if(alternative.getTypeEnum().equals(ConfAlternative.Type.SCALABILITY)){
				basicResultComposite = new ScalabilityResultComposite(alternative, tabFolder, SWT.NONE);
			}
			tabBasicResults.setText("Basic result viewer");
			tabBasicResults.setControl(basicResultComposite);
			
			CTabItem tabAdvancedResults = new CTabItem(tabFolder, SWT.NONE);
			advancedResultComposite = new AdvancedResultComposite(alternative, tabFolder, SWT.NONE);
			tabAdvancedResults.setText("Advanced result viewer");
			tabAdvancedResults.setControl(advancedResultComposite);
			
			tabFolder.setSelection(tabBasicResults);
			
			refresh();
			layout();
		}
		
		@Override
		public void refresh() {
			if(basicResultComposite instanceof IRefreshable){
				((IRefreshable)basicResultComposite).refresh();
			}
			advancedResultComposite.refresh();
		}
		
	}
}
