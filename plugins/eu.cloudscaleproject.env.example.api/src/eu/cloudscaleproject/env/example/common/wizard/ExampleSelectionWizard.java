package eu.cloudscaleproject.env.example.common.wizard;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import eu.cloudscaleproject.env.example.common.Example;
import eu.cloudscaleproject.env.example.common.ExampleService;
import eu.cloudscaleproject.env.example.common.wizard.ExampleSelectionPage.ExampleWizardNode;

public class ExampleSelectionWizard extends Wizard implements INewWizard, IExecutableExtension
{
	public ExampleSelectionWizard()
	{
		setWindowTitle("CloudScale Examples");
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
		
		for (Example example :ExampleService.getInstance().getExamples())
		{
			wizards.add(new ExampleWizardNode(new ExampleProjectWizard(example)));
		}
		
		return wizards;
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException
	{
	}
}
