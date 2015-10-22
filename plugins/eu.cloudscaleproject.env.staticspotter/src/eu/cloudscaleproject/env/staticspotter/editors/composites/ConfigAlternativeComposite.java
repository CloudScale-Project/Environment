package eu.cloudscaleproject.env.staticspotter.editors.composites;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.ui.SplitComposite;
import eu.cloudscaleproject.env.staticspotter.alternatives.ConfigAlternative;
import eu.cloudscaleproject.env.toolchain.ui.ConfigEditorView;
import eu.cloudscaleproject.env.toolchain.util.EMFEditableTreeviewComposite;
import eu.cloudscaleproject.env.toolchain.util.PropertyPageComposite;

public class ConfigAlternativeComposite extends ConfigEditorView implements IRefreshable, ISelectable
{
	@Optional
	@Inject
	private CommandExecutor executor;

	private ConfigAlternative configAlternative;

	private EMFEditableTreeviewComposite treeViewComposite;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ConfigAlternativeComposite(Composite parent, int style, final ConfigAlternative configAlternative)
	{
		super(parent, style, configAlternative);
		CloudscaleContext.inject(this);

		this.configAlternative = configAlternative;
		getContainer().setLayout(new FillLayout());

		Group containerEditor = new Group(getContainer(), SWT.NONE);
		containerEditor.setText("Catalog and Engine models");
		containerEditor.setLayout(new FillLayout());

		SplitComposite splitComposite = new SplitComposite(containerEditor, SWT.NONE);
		
		this.treeViewComposite = new EMFEditableTreeviewComposite(this.configAlternative, splitComposite, SWT.NONE);
		splitComposite.setTopControl(treeViewComposite);
		
		PropertyPageComposite propertyComposite = new PropertyPageComposite(splitComposite, SWT.BORDER, this.treeViewComposite.getPropertySheetPage());
		splitComposite.setBottomControl(propertyComposite);
	}

	@Override
	public void refresh()
	{
	}
	
	@Override
	public void onSelect() {
	}

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}
}
