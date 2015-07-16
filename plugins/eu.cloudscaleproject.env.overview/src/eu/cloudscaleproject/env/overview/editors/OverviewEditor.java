package eu.cloudscaleproject.env.overview.editors;


import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.overview.OverviewAlternative;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ui.InputEditorView;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class OverviewEditor extends InputEditorView implements ISelectable, IPropertySheetPageProvider{

	private final IProject project;
	private final OverviewAlternative alternative;

	private EMFEditableTreeviewComposite treeviewComposite;

	
	public OverviewEditor(OverviewAlternative input, Composite parent, int style) {
		super(parent, style, input);
		
		this.project = input.getProject();
		this.alternative = input;
		
		Composite mainContainer = new Composite(getContainer(), SWT.NONE);
		mainContainer.setLayout(new GridLayout());
		
		this.treeviewComposite = new EMFEditableTreeviewComposite(input, mainContainer, SWT.NONE);
		this.treeviewComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}

	@Override
	public IPropertySheetPage getPropertySheetPage() {
		if(treeviewComposite != null && !treeviewComposite.isDisposed()){
			return treeviewComposite.getPropertySheetPage();
		}
		return null;
	}
	
	@Override
	public void onSelect() {
		ValidationDiagramService.showStatus(project, CSTool.OVERVIEW.getID(), alternative);
	}
}