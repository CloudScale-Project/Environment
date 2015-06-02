package eu.cloudscaleproject.env.usageevolution.editors.composite;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.generator.editor.views.PlotCanvas;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ProjectEditorSelectionService;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.ui.InputEditorView;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;

public class UsageEvolutionEditorComposite extends InputEditorView implements IPropertySheetPageProvider, ISelectable, IRefreshable{

	//private static final String PLOTVIEWID = "tools.descartes.dlim.generator.editor.views.PlotView";
	
	private final IProject project;
	private final UsageEvolutionAlternative alternative;
	
	private final PlotCanvas plotCanvas;
	private final EMFEditableTreeviewComposite treeviewEditor;
	
	public UsageEvolutionEditorComposite(final UsageEvolutionAlternative alt, Composite parent, int style) {
		super(parent, style, alt);
				
		this.project = alt.getProject();
		this.alternative = alt;
		
		getContainer().setLayout(new GridLayout(1, false));
		
		Label lblTitle = new Label(getContainer(), SWT.NONE);
		lblTitle.setText("Usage evolution arrival rate editor:");
		lblTitle.setLayoutData(new GridData());
		
		treeviewEditor = new EMFEditableTreeviewComposite(alt, getContainer(), SWT.NONE);
		GridData gd_tree = new GridData(SWT.FILL, SWT.FILL, true, false);
		gd_tree.heightHint = 120;
		gd_tree.minimumHeight = 120;
		treeviewEditor.setLayoutData(gd_tree);
		
		treeviewEditor.getTreeViewer().addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection ss = (StructuredSelection)event.getSelection();
				Object selection = ss.getFirstElement();
				if(selection instanceof EObject){
					EObject rootObject = EcoreUtil.getRootContainer((EObject)selection);
					if(rootObject instanceof Sequence){
						plotCanvas.setRootSequence((Sequence)rootObject);
						plotCanvas.redraw();
					}
				}
			}
		});
				
		plotCanvas = new PlotCanvas(getContainer(), SWT.NONE, true);
		plotCanvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		for(EObject eo : alt.getModelRoot(ToolchainUtils.KEY_FILE_LIMBO)){
			if(eo instanceof Sequence){
				plotCanvas.setRootSequence((Sequence)eo);
			}
		}
	}

	@Override
	public void onSelect() {
		ProjectEditorSelectionService.getInstance().reloadPropertySheetPage();
		ValidationDiagramService.showStatus(project, alternative);
		
		//TODO: show arrival rate
	}
	
	@Override
	public IPropertySheetPage getPropertySheetPage()
	{
		return treeviewEditor.getPropertySheetPage();
	}

	@Override
	public void refresh() {
		ProjectEditorSelectionService.getInstance().reloadPropertySheetPage();
	}
}
