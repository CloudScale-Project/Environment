package eu.cloudscaleproject.env.overview.wizard.pages;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.scaledl.overview.SpecificationProviderService;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.PlatformRuntimeService;
import org.scaledl.overview.specification.CloudEnvironmentDescriptor;
import org.scaledl.overview.specification.CloudSpecification;

import eu.cloudscaleproject.env.overview.wizard.composites.DeploymentComposite;
import eu.cloudscaleproject.env.overview.wizard.composites.RuntimeServiceSelectionComposite;
import eu.cloudscaleproject.env.overview.wizard.util.IWizardPageControll;
import eu.cloudscaleproject.env.overview.wizard.util.SwtUtil;
import eu.cloudscaleproject.env.overview.wizard.util.WizardData;

public class DeploymentWizardPage extends WizardPage implements IWizardPageControll {

	private WizardData data;

	private Composite stackedContainer;
	private RuntimeServiceSelectionComposite selectionComposite;
	private DeploymentComposite deploymentComposite;

	private Button rbCreate;
	private Button rbExisting;

	/**
	 * Create the wizard.
	 */
	public DeploymentWizardPage(WizardData data) {
		super("wizardPage");
		setTitle("Basic deployment configuraiton");
		setDescription("Select cloud enviornment and software deployment.");
		
		this.data = data;
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout(1, false));
		setControl(container);
		
		Composite composite_1 = new Composite(container, SWT.BORDER);
		GridLayout gl_composite_1 = new GridLayout(2, false);
		gl_composite_1.marginLeft = 5;
		composite_1.setLayout(gl_composite_1);
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_composite_1.heightHint = 48;
		composite_1.setLayoutData(gd_composite_1);
		
		Label lblRuntimePlatformService = new Label(composite_1, SWT.NONE);
		lblRuntimePlatformService.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblRuntimePlatformService.setText("Runtime Platform Service : ");
		
		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		composite_2.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		rbCreate = new Button(composite_2, SWT.RADIO);
		rbCreate.setText("Create new");
		rbCreate.setSelection(true);
		
		rbExisting = new Button(composite_2, SWT.RADIO);
		rbExisting.setText("Select existing");


		stackedContainer = new Composite(container, SWT.NONE);
		stackedContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		StackLayout sl_stackedContainer = new StackLayout();
		sl_stackedContainer.marginWidth = 6;
		stackedContainer.setLayout(sl_stackedContainer);

		selectionComposite = new RuntimeServiceSelectionComposite(stackedContainer, SWT.NULL);
		deploymentComposite = new DeploymentComposite(stackedContainer, SWT.NULL);
		
		
		updateStackedComposite();
		SelectionListener l = new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				updateStackedComposite();
			}
		};

		rbCreate.addSelectionListener(l);
		rbExisting.addSelectionListener(l);

		SwtUtil.addListenerRecursive(getControl(), new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				checkComplete();
			}
		});
		
		checkComplete();
	}
	
	private void updateStackedComposite ()
	{
		if (rbCreate.getSelection())
		{
			((StackLayout)stackedContainer.getLayout()).topControl = deploymentComposite;
		}
		else
		{
			((StackLayout)stackedContainer.getLayout()).topControl = selectionComposite;
		}
		
		stackedContainer.layout();
		stackedContainer.redraw();
	}
	
	
	@Override
	public void performNext() {

		CloudEnvironment ce = data.getOverviewModel().getArchitecture().getCloudEnvironments().get(0);

		// Clear previous state
		ce.getPlatformLayer().getServices().clear();
		ce.getInfrastructureLayer().getServices().clear();
		data.getOverviewModel().getDeployment().getServiceDeployments().clear();
		data.getOverviewModel().getDefinition().getDescriptors().clear();

		data.setPlatformRuntimeService (null);

		PlatformRuntimeService prs;
		if (rbCreate.getSelection())
		{
			prs = deploymentComposite.createRuntimeService(data.getOverviewModel());
		}
		else
		{
			prs = selectionComposite.getPlatformRuntimeService(); 
            CloudEnvironmentDescriptor targetCEDescriptor = prs.getPlatformLayer().getCloudEnvironment().getCloudEnvironmentDescriptor();
			ce.setCloudEnvironmentDescriptor(targetCEDescriptor);
		}

		data.setPlatformRuntimeService (prs);
		CloudSpecification cloudSpecification = SpecificationProviderService.getInstance().getCloudDefinition(ce.getCloudEnvironmentDescriptor().getProviderID());
		data.setCloudSpecification(cloudSpecification);
	}

	@Override
	public void performBack() {
	}
	
	@Override
	public void performUpdate() {
		if (this.data.getTargetOverview() != null)
		{
			selectionComposite.setOverviewModel(this.data.getTargetOverview());
			rbExisting.setEnabled(true);
		}
		else
		{
			rbExisting.setEnabled(false);
			rbCreate.setSelection(true);
			updateStackedComposite();
		}

		checkComplete();
	}
	
	
	private void checkComplete ()
	{
		boolean isComplete = false;
		if (rbCreate.getSelection())
		{
			isComplete = deploymentComposite.isComplete();
		}
		else
		{
			isComplete = selectionComposite.isComplete();
		}

        setPageComplete(isComplete);
	}

}
