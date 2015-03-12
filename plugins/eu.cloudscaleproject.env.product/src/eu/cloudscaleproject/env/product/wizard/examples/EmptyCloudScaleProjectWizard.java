package eu.cloudscaleproject.env.product.wizard.examples;


public class EmptyCloudScaleProjectWizard extends AbstractExampleProjectWizard {

    public EmptyCloudScaleProjectWizard() {
    }

    private static final String NAME = "Empty CloudScale Project";
    private static final String DESCRIPTION = "This is empty CloudScale Project. Nothing inside...";
    private static final String ARCHIVE_PATH = "/resources/examples/empty-project.zip";

    @Override
    protected String getArchivePath()
    {
    	return ARCHIVE_PATH;
    }
    
    @Override
    public WizardGroup getGroup()
    {
    	return WizardGroup.CLOUDSCALE;
    }
    
    @Override
    public String getName()
    {
    	return NAME;
    }

    @Override
    public String getDescription()
    {
    	return DESCRIPTION;
    }

}
