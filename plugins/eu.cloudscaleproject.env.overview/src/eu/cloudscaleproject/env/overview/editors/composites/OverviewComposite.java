package eu.cloudscaleproject.env.overview.editors.composites;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.overview.OverviewAlternative;
import eu.cloudscaleproject.env.toolchain.ui.AbstractEditorView;
import eu.cloudscaleproject.env.toolchain.ui.widgets.TitleWidget;
import eu.cloudscaleproject.env.toolchain.ui.widgets.ValidationWidget;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;
import eu.cloudscaleproject.env.toolchain.util.PropertyPageComposite;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class OverviewComposite extends AbstractEditorView implements ISelectable{

	private EMFEditableTreeviewComposite treeviewComposite;
	
	public OverviewComposite(Composite parent, int style, OverviewAlternative input) {
		super(parent, style, input);
		
		new TitleWidget(getHeader(), style, input);
		Composite mainContainer = new Composite(getContainer(), SWT.NONE);
		mainContainer.setLayout(new GridLayout());
		new ValidationWidget(getFooter(), style, input);
		
		//tree view
		this.treeviewComposite = new EMFEditableTreeviewComposite(input, mainContainer, SWT.NONE);
		this.treeviewComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		//property sheet page
		PropertyPageComposite pageSheet = new PropertyPageComposite(
				mainContainer, SWT.BORDER, treeviewComposite.getPropertySheetPage());
		pageSheet.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}
	
	@Override
	public void onSelect() {
	}
}