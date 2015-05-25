package eu.cloudscaleproject.env.usageevolution.editors.composite;

import org.eclipse.core.resources.IProject;
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
import eu.cloudscaleproject.env.toolchain.ui.InputEditorView;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;

public class UsageEvolutionEditorComposite extends InputEditorView implements IPropertySheetPageProvider, ISelectable, IRefreshable{

	//private static final String PLOTVIEWID = "tools.descartes.dlim.generator.editor.views.PlotView";
	
	private final IProject project;
	private final UsageEvolutionAlternative alternative;
	
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
