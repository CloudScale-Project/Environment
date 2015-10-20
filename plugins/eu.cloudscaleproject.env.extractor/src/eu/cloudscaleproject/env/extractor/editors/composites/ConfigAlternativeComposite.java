package eu.cloudscaleproject.env.extractor.editors.composites;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.somox.common.MetricsDetails.GroupID;

import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.extractor.alternatives.ConfingAlternative;
import eu.cloudscaleproject.env.toolchain.ui.ConfigEditorView;

public class ConfigAlternativeComposite extends ConfigEditorView implements IRefreshable, ISelectable
{
	private ConfingAlternative configAlternative;
	
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ConfigAlternativeComposite(Composite parent, int style, ConfingAlternative cif)
	{
		super(parent, style, cif);
		//((GridData) getContainer().getLayoutData()).horizontalIndent = 1;

		this.configAlternative = cif;

		// this.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
		// 1));
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		getContainer().setLayout(gridLayout);

		CTabFolder tabFolder = new CTabFolder(getContainer(), SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		tabFolder.setTabHeight(32);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		{
			SomoxConfigurationComposite composite = new SomoxConfigurationComposite(tabFolder, SWT.NONE, 
					configAlternative, GroupID.GROUP_CLUSTERING, GroupID.GROUP_MERGING);

			CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setControl(composite);
			tabItem.setText("Clustering / Merging");
			tabFolder.setSelection(tabItem);
		}
		{
			SomoxConfigurationComposite composite = new SomoxConfigurationComposite(tabFolder, SWT.NONE, 
					configAlternative, GroupID.GROUP_METRICS);

			CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setControl(composite);
			tabItem.setText("Advanced metrics");
			tabFolder.setSelection(tabItem);
		}

		tabFolder.setSelection(0);
	}
	
	@Override
	public void refresh()
	{
	}
	
	
	
	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}


	@Override
	public void onSelect() {
	}
}
