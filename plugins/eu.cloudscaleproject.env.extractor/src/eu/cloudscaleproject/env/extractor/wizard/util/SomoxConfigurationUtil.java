package eu.cloudscaleproject.env.extractor.wizard.util;

import org.somox.configuration.SoMoXConfiguration;

public class SomoxConfigurationUtil
{
	
	public static SoMoXConfiguration createDefaultSomoxConfiguration ()
	{
		SoMoXConfiguration somoxConfiguration = new SoMoXConfiguration();

		somoxConfiguration.setDefaults();

		//
		// Other configs
		//
		somoxConfiguration.setExcludedSuffixesForNameResemblance("");
		somoxConfiguration.setExcludedPrefixesForNameResemblance("");
		somoxConfiguration.setWildcardKey("§javax.swing.border§rbe.util§java.util§java.net§java.text§(default package)§java.lang.reflect§javax.swing§javax§java.io§rbe.args§java§java.util.regex§rbe§java.lang§java.sql§", "");
		somoxConfiguration.setReverseEngineerInterfacesNotAssignedToComponent(false);
		
		//
		// Clustering
		//
		somoxConfiguration.getClusteringConfig().setMinMergeClusteringThreshold(45/100d);
		somoxConfiguration.getClusteringConfig().setMaxMergeClusteringThreshold(100/100d);
		somoxConfiguration.getClusteringConfig().setClusteringMergeThresholdDecrement(10/100d);

		somoxConfiguration.getClusteringConfig().setMinComposeClusteringThreshold(25/100d);
		somoxConfiguration.getClusteringConfig().setMaxComposeClusteringThreshold(100/100d);
		somoxConfiguration.getClusteringConfig().setClusteringComposeThresholdDecrement(10/100d);
		

		//
		// Weights
		//
		// Dirctory & Package
		somoxConfiguration.setWeightDirectoryMapping(.70);
		somoxConfiguration.setWeightPackageMapping(.70);

		// Interface Violation
		somoxConfiguration.setWeightInterfaceViolationIrrelevant(.10);
		somoxConfiguration.setWeightInterfaceViolationRelevant(0.40);

		// Coupling
		somoxConfiguration.setWeightHighCoupling(.015);
		somoxConfiguration.setWeightLowCoupling(0);

		// Resemblance
		somoxConfiguration.setWeightHighestNameResemblance(.45);
		somoxConfiguration.setWeightHighNameResemblance(0.30);
		somoxConfiguration.setWeightMidNameResemblance(0.15);
		somoxConfiguration.setWeightLowNameResemblance(0.05);

		// SLAQ (Slice Layer Architecture Quality) & DMS (Distance from the Main sequence)
		somoxConfiguration.setWeightHighSLAQ(0.15);
		somoxConfiguration.setWeightLowSLAQ(0.05);
		somoxConfiguration.setWeightDMS(.05);
		

		return somoxConfiguration;
	}

}
