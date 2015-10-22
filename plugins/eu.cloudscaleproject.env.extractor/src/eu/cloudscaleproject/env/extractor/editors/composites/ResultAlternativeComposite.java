package eu.cloudscaleproject.env.extractor.editors.composites;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.common.ui.SplitComposite;
import eu.cloudscaleproject.env.extractor.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.toolchain.IPropertySheetPageProvider;
import eu.cloudscaleproject.env.toolchain.ui.ResultEditorView;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;
import eu.cloudscaleproject.env.toolchain.util.PropertyPageComposite;

public class ResultAlternativeComposite extends ResultEditorView implements IPropertySheetPageProvider{


	private ResultAlternative resultPersistenceFolder;
	private EMFEditableTreeviewComposite treeViewComposite;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ResultAlternativeComposite(Composite parent, int style, ResultAlternative rif) {
		super(parent, style, rif);
		
		this.resultPersistenceFolder = rif;

		getContainer().setLayout(new GridLayout(1, false));
		
		//
		// Treeview
		//
		Group containerEditor = new Group(getContainer(), SWT.NONE);
		containerEditor.setText("Extracted internal architecture model");
		containerEditor.setLayout(new FillLayout());
		containerEditor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		SplitComposite splitComposite = new SplitComposite(containerEditor, SWT.NONE);

		//top
		this.treeViewComposite = new EMFEditableTreeviewComposite(resultPersistenceFolder, splitComposite, SWT.NONE);
		splitComposite.setTopControl(treeViewComposite);
		
		//bottom
		PropertyPageComposite propertyComposite = new PropertyPageComposite(splitComposite, SWT.BORDER, this.treeViewComposite.getPropertySheetPage());
		splitComposite.setBottomControl(propertyComposite);
	}
	
	
	@Override
	public IPropertySheetPage getPropertySheetPage()
	{
		return treeViewComposite.getPropertySheetPage();
	}
}
