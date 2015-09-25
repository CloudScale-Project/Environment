package eu.cloudscaleproject.env.staticspotter.editors.composites;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.staticspotter.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ui.InputEditorView;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;
import eu.cloudscaleproject.env.toolchain.util.PropertyPageComposite;

public class InputAlternativeComposite extends InputEditorView implements IRefreshable, ISelectable {

	private InputAlternative inputAlternative;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InputAlternativeComposite(Composite parent, int style, final InputAlternative inputAlternative) {
		super(parent, SWT.NONE, inputAlternative);

		getContainer().setLayout(new GridLayout());
		
		this.inputAlternative = inputAlternative;

		Group containerEditor = new Group(getContainer(), SWT.NONE);
		containerEditor.setText("Source Code Decorator");
		containerEditor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		containerEditor.setLayout(new FillLayout(SWT.HORIZONTAL));

		EMFEditableTreeviewComposite treeViewComposite = new EMFEditableTreeviewComposite(inputAlternative, containerEditor, SWT.NONE);

		PropertyPageComposite propertyComposite = new PropertyPageComposite(getContainer(), SWT.NONE, treeViewComposite.getPropertySheetPage());
		propertyComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}
	
	private void load ()
	{
		//String url = this.editorInput.getProperty(GlobalInputAlternative.KEY_PROJECT_URL);
		//txtInput.setText(url == null ? "" : url);
	}
	
	@Override
	public void refresh() {
		load();
	}

	@Override
	public void onSelect()
	{
		IProject project = inputAlternative.getProject();
		
		ValidationDiagramService.showStatus(project, CSTool.SPOTTER_STA_INPUT.getID(), this.inputAlternative);
		ValidationDiagramService.showStatus(project, CSTool.SPOTTER_STA_CONF.getID(), null);
		ValidationDiagramService.showStatus(project, CSTool.SPOTTER_STA_RES.getID(), null);
	}
}
