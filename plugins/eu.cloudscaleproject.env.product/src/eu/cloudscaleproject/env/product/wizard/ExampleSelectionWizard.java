package eu.cloudscaleproject.env.product.wizard;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import eu.cloudscaleproject.env.product.wizard.ExampleSelectionPage.ExampleWizardNode;
import eu.cloudscaleproject.env.product.wizard.examples.EmptyCloudScaleProjectWizard;
import eu.cloudscaleproject.env.product.wizard.examples.TPCWAnalyserProjectWizard;

public class ExampleSelectionWizard extends Wizard implements INewWizard, IExecutableExtension
{
	public ExampleSelectionWizard()
	{
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection)
	{
	}

	@Override
	public boolean performFinish()
	{
		return true;
	}

	@Override
	public void addPages()
	{
		addPage(new ExampleSelectionPage(getWizardNodes()));
		setForcePreviousAndNextButtons(true);
	}
	
	private List<ExampleWizardNode> getWizardNodes()
	{
		LinkedList<ExampleWizardNode> wizards = new LinkedList<>();
		
		wizards.add(new ExampleWizardNode(new EmptyCloudScaleProjectWizard()));
		wizards.add(new ExampleWizardNode(new TPCWAnalyserProjectWizard()));
		
		return wizards;
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException
	{
	}
}
