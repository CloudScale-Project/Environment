package eu.cloudscaleproject.env.toolchain.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IResultAlternative;
import eu.cloudscaleproject.env.toolchain.ui.widgets.TitleWidget;

public class ResultEditorView extends AbstractEditorView
{
	private Group metaContainer;
	private Label lblInputValue;
	private CLabel lblConfigurationValue;
	private CLabel lblCreatedValue;
	private CLabel lblLastChangeValue;
	private Composite configContainer;
	private IResultAlternative result;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ResultEditorView(Composite parent, int style, final IResultAlternative result)
	{
		super(parent, style, result);
		
		this.result = result;
		
		new TitleWidget(getHeader(), SWT.NONE, result){
			@Override
			protected void initButtons() {
				CLabel lblUp = createContextButton("Input", 
						loadImage("resources/icons/ic-input-white-24.png"));
				CLabel lblConfiguration = createContextButton("Configuration", 
						loadImage("resources/icons/ic-configurations-white-24.png"));
				createSeparator();

				lblUp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						ResourceRegistry.getInstance().openResourceEditor(((IResultAlternative)result).getInputAlternative());
					}
				});
				lblConfiguration.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						ResourceRegistry.getInstance().openResourceEditor(((IResultAlternative)result).getConfigAlternative());
					}
				});
				super.initButtons();
			}
		};
		
		initContainer();
		getFooter().dispose();
	}

	private void initContainer()
	{
		Composite mainContainer = super.getContainer();
		GridLayout layout = new GridLayout(1, false);
		layout.verticalSpacing = 10;
		mainContainer.setLayout(layout);

		metaContainer = new Group(mainContainer, SWT.NONE);
		metaContainer.setLayout(new GridLayout(4, false));
		GridData gd_grpDetails = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_grpDetails.heightHint = 60;
		metaContainer.setLayoutData(gd_grpDetails);
		metaContainer.setText("Details");

		CLabel lblInputTitle = new CLabel(metaContainer, SWT.NONE);
		lblInputTitle.setText("Input:");
		lblInputValue = new Label(metaContainer, SWT.NONE);
		lblInputValue.setCursor(new Cursor(getDisplay(), SWT.CURSOR_HAND));

		CLabel lblConfigurationTitle = new CLabel(metaContainer, SWT.NONE);
		lblConfigurationTitle.setText("  Configuration:");
		lblConfigurationValue = new CLabel(metaContainer, SWT.NONE);
		lblConfigurationValue.setCursor(new Cursor(getDisplay(), SWT.CURSOR_HAND));

		CLabel lblCreatedTitle = new CLabel(metaContainer, SWT.NONE);
		lblCreatedTitle.setText("Created on:");
		lblCreatedValue = new CLabel(metaContainer, SWT.NONE);

		CLabel lblLastChangeTitle = new CLabel(metaContainer, SWT.NONE);
		lblLastChangeTitle.setText("  Last change:");

		lblLastChangeValue = new CLabel(metaContainer, SWT.NONE);

		this.configContainer = new Composite(mainContainer, SWT.NONE);
		configContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		lblInputValue.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				ResourceRegistry.getInstance().openResourceEditor(result.getInputAlternative());
			}
		});
		
		lblConfigurationValue.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				ResourceRegistry.getInstance().openResourceEditor(result.getConfigAlternative());
			}
		});

		updateMetaData();
	}

	private void updateMetaData()
	{
		if(result.getInputAlternative() != null){
			lblInputValue.setText(this.result.getInputAlternative().getName());
		}

		if(result.getConfigAlternative() != null){
			lblConfigurationValue.setText(this.result.getConfigAlternative().getName());
		}
		
		lblCreatedValue.setText(getDateString(result.getProperty("created")));
		lblLastChangeValue.setText(getDateString(result.getProperty("modified")));
		metaContainer.layout();
	}
	
	private SimpleDateFormat sdf_name = new SimpleDateFormat("d/MM, hh:mm:ss");
	private String getDateString (String time)
	{
		try {
			Date date = new Date(Long.parseLong(time));
			return sdf_name.format(date);
		} catch (Exception e) {
			return "n/a";
		}
	}

	@Override
	protected Composite getContainer()
	{
		return this.configContainer;
	}
}
