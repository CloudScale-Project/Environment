package eu.cloudscaleproject.env.extractor.editors.composites;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.extractor.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ui.TitleComposite;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;

public class SingleResultComposite extends TitleComposite implements IPropertySheetPageProvider, IRefreshable{


	private ResultAlternative resultPersistenceFolder;
	private EMFEditableTreeviewComposite treeViewComposite;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SingleResultComposite(Composite parent, int style, ResultAlternative rif) {
		super(parent, style);
		
		this.resultPersistenceFolder = rif;

		getContainer().setLayout(new GridLayout(3, false));
		
		//
		// Treeview
		//
		Group containerEditor = new Group(getContainer(), SWT.NONE);
		containerEditor.setText("Extracted internal architecture model");
		containerEditor.setLayout(new FillLayout(SWT.HORIZONTAL));
		containerEditor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

		this.treeViewComposite = new EMFEditableTreeviewComposite(resultPersistenceFolder, containerEditor, SWT.NONE);
		
		init();
	}
	
	private void init()
	{
		setTitle(this.resultPersistenceFolder.getName());
		layout();
	}
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		resultPersistenceFolder.load();
		init();
	}

	@Override
	public IPropertySheetPage getPropertySheetPage()
	{
		return treeViewComposite.getPropertySheetPage();
	}
}
