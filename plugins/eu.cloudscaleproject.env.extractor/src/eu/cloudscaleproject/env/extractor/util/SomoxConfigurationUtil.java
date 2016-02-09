package eu.cloudscaleproject.env.extractor.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.somox.common.MetricsDetails;
import org.somox.common.MetricsDetails.GroupID;
import org.somox.common.SoMoXProjectPreferences;
import org.somox.configuration.SoMoXConfiguration;

import eu.cloudscaleproject.env.extractor.alternatives.ConfingAlternative;

public class SomoxConfigurationUtil
{
	private static String DEFAULT_BLACKLIST ="java.*§javax.*§"; 
	private static SoMoXProjectPreferences somoxPreferences = new SoMoXProjectPreferences();
	private static Map<String, MetricsDetails> mapMetrics = new LinkedHashMap<String, MetricsDetails>();
	static 
	{
		for (MetricsDetails md : somoxPreferences.orderedMetricDetails)
		{
			mapMetrics.put(md.metricWeightPeferenceName, md);
		}
	}
	
	
	public static SoMoXConfiguration createDefaultSomoxConfiguration ()
	{
		SoMoXConfiguration somoxConfiguration = new SoMoXConfiguration();

		somoxConfiguration.setDefaults();

		//
		// Other configs
		//
		somoxConfiguration.setExcludedSuffixesForNameResemblance("");
		somoxConfiguration.setExcludedPrefixesForNameResemblance("");
		somoxConfiguration.setWildcardKey(DEFAULT_BLACKLIST, "");
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
	
	public static MetricsDetails getMetricDescription (String key)
	{
		return mapMetrics.get(key);
	}
	
	public static List<MetricsDetails> getMetricsDetails()
	{
		return new LinkedList<>(mapMetrics.values());
	}

	public static List<MetricsDetails> getMetricsDetails(GroupID group)
	{
		LinkedList<MetricsDetails> l = new LinkedList<MetricsDetails>();
		for (MetricsDetails md : mapMetrics.values())
		{
			if (md.group == group)
			{
				l.add(md);
			}
		}
		
		return l;
	}

	public static List<String> getMetricsKeys()
	{
		return new LinkedList<>(mapMetrics.keySet());
	}
	
	
	
	public static SoMoXConfiguration loadSomoxConfiguration (ConfingAlternative r)
	{
		SoMoXConfiguration somoxConfiguration = createDefaultSomoxConfiguration();
		for(String metric : mapMetrics.keySet())
		{
			try
			{
				double value = Double.parseDouble(r.getProperty(metric));
				setMetricValueByKey(metric, value, somoxConfiguration);
			}
			catch(NullPointerException e){} // Expected
			catch(NumberFormatException e){
				Logger.getLogger(SomoxConfigurationUtil.class).warn(
						"Unable to parse SomoxConfiguration metric value: "+metric);
			} 
		}
		
		String blacklist = getBlacklist(r);
		somoxConfiguration.setWildcardKey(blacklist, "");
		
		return somoxConfiguration;
	}
	
	public static String getBlacklist (ConfingAlternative alternative)
	{
		String blacklist = alternative.getProperty("blacklist");
		return blacklist == null ? DEFAULT_BLACKLIST : blacklist;
	}

	public static void setBlacklist (String blacklist, ConfingAlternative alternative)
	{
		alternative.setProperty("blacklist", blacklist);
		alternative.getSomoxConfiguration().setWildcardKey(blacklist, "");
	}
	

	public static void persistMetricValue (String key, double value, ConfingAlternative alternative)
	{
		alternative.setProperty(key, ""+value);
	}
	
	public static void setMetricValueByKey (String key, double value, SoMoXConfiguration conf)
	{
		switch (key)
		{
		    // Clustering
			case SoMoXProjectPreferences.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_COMPOSE:
				conf.getClusteringConfig().setClusteringComposeThresholdDecrement(value); break;
			case SoMoXProjectPreferences.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_MERGE:
				conf.getClusteringConfig().setClusteringMergeThresholdDecrement(value); break;
			case SoMoXProjectPreferences.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_COMPOSE:
				conf.getClusteringConfig().setMaxComposeClusteringThreshold(value); break;
			case SoMoXProjectPreferences.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_MERGE:
				conf.getClusteringConfig().setMaxMergeClusteringThreshold(value); break;
			case SoMoXProjectPreferences.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_COMPOSE:
				conf.getClusteringConfig().setMinComposeClusteringThreshold(value); break;
			case SoMoXProjectPreferences.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_MERGE:
				conf.getClusteringConfig().setMinMergeClusteringThreshold(value); break;

			case SoMoXProjectPreferences.SOMOX_WEIGHT_DIRECTORY_MAPPING:
				conf.setWeightDirectoryMapping(value); break;
			case SoMoXProjectPreferences.SOMOX_WEIGHT_DMS:
				conf.setWeightDMS(value); break;
			case SoMoXProjectPreferences.SOMOX_WEIGHT_HIGH_COUPLING:
				conf.setWeightHighCoupling(value); break;
			case SoMoXProjectPreferences.SOMOX_WEIGHT_LOW_COUPLING:
				conf.setWeightLowCoupling(value); break;

			case SoMoXProjectPreferences.SOMOX_WEIGHT_HIGH_SLAQ:
				conf.setWeightHighSLAQ(value); break;
			case SoMoXProjectPreferences.SOMOX_WEIGHT_LOW_SLAQ:
				conf.setWeightLowSLAQ(value); break;

			case SoMoXProjectPreferences.SOMOX_WEIGHT_HIGH_NAME_RESEMBLANCE:
				conf.setWeightHighNameResemblance(value); break;
			case SoMoXProjectPreferences.SOMOX_WEIGHT_HIGHEST_NAME_RESEMBLANCE:
				conf.setWeightHighestNameResemblance(value); break;
			case SoMoXProjectPreferences.SOMOX_WEIGHT_MID_NAME_RESEMBLANCE:
				conf.setWeightMidNameResemblance(value); break;
			case SoMoXProjectPreferences.SOMOX_WEIGHT_LOW_NAME_RESEMBLANCE:
				conf.setWeightLowNameResemblance(value); break;

			case SoMoXProjectPreferences.SOMOX_WEIGHT_INTERFACE_VIOLATION_IRRELEVANT:
				conf.setWeightInterfaceViolationIrrelevant(value); break;
			case SoMoXProjectPreferences.SOMOX_WEIGHT_INTERFACE_VIOLATION_RELEVANT:
				conf.setWeightInterfaceViolationRelevant(value); break;
			case SoMoXProjectPreferences.SOMOX_WEIGHT_PACKAGE_MAPPING:
				conf.setWeightPackageMapping(value); break;
			
			default: throw new IllegalStateException();
		}
		
	}

	public static double getMetricValueByKey (String key, SoMoXConfiguration conf)
	{
		switch (key)
		{
		    // Clustering
			case SoMoXProjectPreferences.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_COMPOSE:
				return conf.getClusteringConfig().getClusteringComposeThresholdDecrement();
			case SoMoXProjectPreferences.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_MERGE:
				return conf.getClusteringConfig().getClusteringMergeThresholdDecrement();
			case SoMoXProjectPreferences.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_COMPOSE:
				return conf.getClusteringConfig().getMaxComposeClusteringThreshold();
			case SoMoXProjectPreferences.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_MERGE:
				return conf.getClusteringConfig().getMaxMergeClusteringThreshold();
			case SoMoXProjectPreferences.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_COMPOSE:
				return conf.getClusteringConfig().getMinComposeClusteringThreshold();
			case SoMoXProjectPreferences.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_MERGE:
				return conf.getClusteringConfig().getMinMergeClusteringThreshold();

			case SoMoXProjectPreferences.SOMOX_WEIGHT_DIRECTORY_MAPPING:
				return conf.getWeightDirectoryMapping();
			case SoMoXProjectPreferences.SOMOX_WEIGHT_DMS:
				return conf.getWeightDMS();
			case SoMoXProjectPreferences.SOMOX_WEIGHT_HIGH_COUPLING:
				return conf.getWeightHighCoupling();
			case SoMoXProjectPreferences.SOMOX_WEIGHT_LOW_COUPLING:
				return conf.getWeightLowCoupling();

			case SoMoXProjectPreferences.SOMOX_WEIGHT_HIGH_SLAQ:
				return conf.getWeightHighSLAQ();
			case SoMoXProjectPreferences.SOMOX_WEIGHT_LOW_SLAQ:
				return conf.getWeightLowSLAQ();

			case SoMoXProjectPreferences.SOMOX_WEIGHT_HIGH_NAME_RESEMBLANCE:
				return conf.getWeightHighNameResemblance();
			case SoMoXProjectPreferences.SOMOX_WEIGHT_HIGHEST_NAME_RESEMBLANCE:
				return conf.getWeightHighestNameResemblance();
			case SoMoXProjectPreferences.SOMOX_WEIGHT_MID_NAME_RESEMBLANCE:
				return conf.getWeightMidNameResemblance();
			case SoMoXProjectPreferences.SOMOX_WEIGHT_LOW_NAME_RESEMBLANCE:
				return conf.getWeightLowNameResemblance();

			case SoMoXProjectPreferences.SOMOX_WEIGHT_INTERFACE_VIOLATION_IRRELEVANT:
				return conf.getWeightInterfaceViolationIrrelevant();
			case SoMoXProjectPreferences.SOMOX_WEIGHT_INTERFACE_VIOLATION_RELEVANT:
				return conf.getWeightInterfaceViolationRelevant();
			case SoMoXProjectPreferences.SOMOX_WEIGHT_PACKAGE_MAPPING:
				return conf.getWeightPackageMapping();
			
			default: throw new IllegalStateException();
		}
	}

}
