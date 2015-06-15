package eu.cloudscaleproject.env.overview.editors;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.overview.OverviewResource;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class OverviewEditor extends Composite implements ISelectable, IPropertySheetPageProvider{

	private final IProject project;
	private final OverviewResource alternative;

	private final EMFEditableTreeviewComposite treeviewComposite;

	
	public OverviewEditor(OverviewResource input, Composite parent, int style) {
		super(parent, style);
		
		setLayout(new FillLayout());
		
		this.project = input.getProject();
		this.alternative = input;
		this.treeviewComposite = new EMFEditableTreeviewComposite(input, this, SWT.NONE);
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
		ValidationDiagramService.showStatus(project, alternative);
	}
}