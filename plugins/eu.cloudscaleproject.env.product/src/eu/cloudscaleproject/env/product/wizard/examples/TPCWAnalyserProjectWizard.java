package eu.cloudscaleproject.env.product.wizard.examples;


public class TPCWAnalyserProjectWizard extends AbstractExampleProjectWizard {

    public TPCWAnalyserProjectWizard() {
    }

    private static final String NAME = "TPC-W Analyser Project";
    private static final String DESCRIPTION = "This is TPC-W project.";
    private static final String ARCHIVE_PATH = "/resources/examples/tpcw-project.zip";

    @Override
    protected String getArchivePath()
    {
    	return ARCHIVE_PATH;
    }
    
    @Override
    public WizardGroup getGroup()
    {
    	return WizardGroup.ANALYSER;
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
