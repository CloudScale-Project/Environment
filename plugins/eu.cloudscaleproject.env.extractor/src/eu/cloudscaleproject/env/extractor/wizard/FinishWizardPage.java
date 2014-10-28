package eu.cloudscaleproject.env.extractor.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.scaledl.overview.Overview;
import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.ExternalConnection;
import org.scaledl.overview.architecture.UsageProxy;

import eu.cloudscaleproject.env.extractor.wizard.util.IWizardPageControll;
import eu.cloudscaleproject.env.extractor.wizard.util.WizardData;

public class FinishWizardPage extends WizardPage implements IWizardPageControll {

	private WizardData data;

	/**
	 * Create the wizard.
	 */
	public FinishWizardPage(WizardData data) {
		super("wizardPage");
		
		setTitle("Final page");
		setDescription("");

		this.data = data;
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
	}

	@Override
	public void performNext() {
		// TODO Auto-generated method stub
		
		// Lastly create UsageProxy and connect imported app service
		UsageProxy usageProxy = ArchitectureFactory.eINSTANCE.createUsageProxy();
		Overview overview = this.data.getOverviewModel();

		overview.getArchitecture().getProxies().add(usageProxy);
		ExternalConnection connection = ArchitectureFactory.eINSTANCE.createExternalConnection();
		connection.setTarget(this.data.getSoftwareService());
		connection.setSource(usageProxy);
		usageProxy.getRequiredInterfaces().addAll(this.data.getSoftwareService().getProvidedInterfaces());
		overview.getArchitecture().getExternalConnections().add(connection);
	}
	
	@Override
	public void performBack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void performUpdate() {
		// TODO Auto-generated method stub
		
		Overview overviewModel = this.data.getOverviewModel();
		overviewModel.getArchitecture().getCloudEnvironments().get(0);
		
	}
}
