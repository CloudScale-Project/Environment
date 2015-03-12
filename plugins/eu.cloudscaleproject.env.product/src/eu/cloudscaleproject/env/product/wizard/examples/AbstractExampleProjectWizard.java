package eu.cloudscaleproject.env.product.wizard.examples;

import java.net.URI;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

import eu.cloudscaleproject.env.product.wizard.CloudScaleProjectSupport;

public abstract class AbstractExampleProjectWizard extends Wizard implements INewWizard, IExecutableExtension {

	public static enum WizardGroup  { CLOUDSCALE, ANALYSER, EXTRACTOR, STATIC_SPOTTER, DYNAMIC_SPOTTER };
    private WizardNewProjectCreationPage _pageOne;
    private IConfigurationElement _configurationElement;

    public AbstractExampleProjectWizard() {
    }

    protected abstract String getArchivePath ();
    public abstract String getName ();
    public abstract String getDescription ();
    public abstract WizardGroup getGroup ();
    
    
    
    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
    }

    @Override
    public boolean performFinish() {
        String name = _pageOne.getProjectName();
        URI location = null;
        if (!_pageOne.useDefaults()) {
            location = _pageOne.getLocationURI();
        }

        IProject project = CloudScaleProjectSupport.createBaseProject(name, location);
        CloudScaleProjectSupport.createExampleProject(project, getArchivePath());

        BasicNewProjectResourceWizard.updatePerspective(_configurationElement);

        return true;
    }
    

    @Override
    public void addPages() {
        super.addPages();

        _pageOne = new WizardNewProjectCreationPage("CloudScale Example");
        _pageOne.setTitle("CloudScale Example project - "+getName());
        _pageOne.setDescription(getDescription());

        addPage(_pageOne);
    }

    @Override
    public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
            throws CoreException {
        _configurationElement = config;

    }

}
