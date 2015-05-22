package eu.cloudscaleproject.env.usageevolution.editors.composite;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ProjectEditorSelectionService;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;

public class UsageEvolutionEditorComposite extends Composite implements IPropertySheetPageProvider, ISelectable, IRefreshable{

	private static final String PLOTVIEWID = "tools.descartes.dlim.generator.editor.views.PlotView";
	
	private final IProject project;
	private final UsageEvolutionAlternative alternative;
	
	private final EMFEditableTreeviewComposite treeviewEditor;
	
	private final ISelectionChangedListener changeListener = new ISelectionChangedListener() {
		
		public void selectionChanged(SelectionChangedEvent selectionChangedEvent) {
			//TODO: set arrival rate plot, when the packages will be exposed...
			/*
			IViewReference[] references = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences();
			for (int i = 0; i < references.length; i++) {
				if (references[i].getId().equals(PLOTVIEWID)) {
					PlotView view = (PlotView) (references[i].getView(true));
					view.updatePlot(alternative.getEditingDomain().getResourceSet()
							.getResources().get(0).getContents()
							.get(0));
				}
			}
			*/
		}
	};
	
	public UsageEvolutionEditorComposite(final UsageEvolutionAlternative alt, Composite parent, int style) {
		super(parent, style);
				
		this.project = alt.getProject();
		this.alternative = alt;
		
		setLayout(new GridLayout(1, false));
		
		Label lblTitle = new Label(this, SWT.NONE);
		lblTitle.setText("Usage evolution arrival rate editor:");
		lblTitle.setLayoutData(new GridData());
		
		treeviewEditor = new EMFEditableTreeviewComposite(alt, this, SWT.NONE);
		treeviewEditor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
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
