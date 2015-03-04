package eu.cloudscaleproject.env.extractor.editors.composites;

import java.util.HashMap;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.wb.swt.SWTResourceManager;
import org.somox.common.MetricsDetails;
import org.somox.common.MetricsDetails.GroupID;
import org.somox.configuration.SoMoXConfiguration;

import eu.cloudscaleproject.env.extractor.wizard.util.SomoxConfigurationUtil;

public class SomoxConfigurationComposite extends Composite
{
	private HashMap<String, Scale> mapScales = new HashMap<>();
	private SoMoXConfiguration conf;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SomoxConfigurationComposite(SoMoXConfiguration conf, Composite parent, int style)
	{
		super(parent, style);

		this.conf = conf;
		setLayout(new GridLayout(1, false));
//		{
//			Label lblSomoxConfiguration = new Label(this, SWT.NONE);
//			lblSomoxConfiguration.setFont(SWTResourceManager.getFont("Sans", 11, SWT.NORMAL));
//			lblSomoxConfiguration.setText("SoMoX Configuration");
//		}
//		{
//			Label label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
//			label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
//			label.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
//		}
//		
		
		CTabFolder tabFolder = new CTabFolder(this, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setTabHeight(32);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		
		{
			Composite composite = createGroupComposite(tabFolder, GroupID.GROUP_CLUSTERING, GroupID.GROUP_MERGING);
			CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setControl(composite);
			tabItem.setText("Clustering and Merging metrics");
			tabFolder.setSelection(tabItem);
		}
		{
			Composite composite = createGroupComposite(tabFolder, GroupID.GROUP_METRICS);
			CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setControl(composite);
			tabItem.setText("Other metrics");
		}

		load();
	}
	
	DataBindingContext bindingContext = new DataBindingContext();
	
	private Composite createGroupComposite (Composite parent, GroupID ... groups)
	{
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.horizontalSpacing = 15;
		composite.setLayout(gl_composite);

		

		for (int i = 0; i<groups.length; ++i)
		{
			GroupID groupId = groups[i];

			if (groups.length > 1)
			{
				if (i>0)
				{
					Label empty = new Label (composite, SWT.NONE);
					empty.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
				}

				Label lblGroupName = new Label (composite, SWT.NONE);
				lblGroupName.setFont(SWTResourceManager.getFont("Sans", 10, SWT.ITALIC));
				GridData gd_lblGroup = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
				gd_lblGroup.heightHint = 20;
				lblGroupName.setLayoutData(gd_lblGroup);
				lblGroupName.setText(groupId.name().replaceAll("GROUP_", ""));
			}

			for (MetricsDetails md : SomoxConfigurationUtil.getMetricsDetails(groupId))
			{
				createSlider(md.metricWeightPeferenceName, composite);
			}
		}
		
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		return scrolledComposite;
	}

	private void createSlider (final String metricKey, Composite parent)
	{
		MetricsDetails md = SomoxConfigurationUtil.getMetricDescription(metricKey);

		Label lblName = new Label(parent, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		lblName.setLayoutData(gd_lblNewLabel);
		lblName.setText(md.metricLabel);
		
		Label lblValue = new Label(parent, SWT.BORDER);
		lblValue.setAlignment(SWT.CENTER);
		GridData gd_lblNewLabel_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_1.widthHint = 30;
		gd_lblNewLabel_1.minimumWidth = 50;
		lblValue.setLayoutData(gd_lblNewLabel_1);
		
		final Scale scale = new Scale(parent, SWT.NONE);
		GridData gd_scale = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_scale.widthHint = 120;
		scale.setLayoutData(gd_scale);
		
		bindingContext.bindValue(
				WidgetProperties.text().observe(lblValue), 
				WidgetProperties.selection().observe(scale), 
				null, null);

	    scale.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event event) {
	        	SomoxConfigurationUtil.setValueByKey(metricKey, scale.getSelection()/100d, conf);
	        }
	      });
	    

		lblName.setToolTipText(md.metricExplanantion);
		lblValue.setToolTipText(md.metricExplanantion);
		scale.setToolTipText(md.metricExplanantion);

	    mapScales.put(metricKey, scale);
	}
	
	public void load()
	{
		for (String key : mapScales.keySet())
		{
			Scale scale = mapScales.get(key);
			int value = (int)(100*SomoxConfigurationUtil.getValueByKey(key, conf));
			scale.setSelection(value);
		}

		bindingContext.updateTargets();
	}
	

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}
}
