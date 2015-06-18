package eu.cloudscaleproject.env.overview.wizard.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.scaledl.overview.Overview;
import org.scaledl.overview.SpecificationProviderService;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.ExternalConnection;
import org.scaledl.overview.architecture.PlatformRuntimeService;
import org.scaledl.overview.architecture.PlatformService;
import org.scaledl.overview.architecture.ProvidedSoftwareService;
import org.scaledl.overview.architecture.UsageProxy;
import org.scaledl.overview.specification.CloudSpecification;
import org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.InfrastructureServiceDescriptor;
import org.scaledl.overview.specification.NetworkInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.PlatformRuntimeServiceDescriptor;
import org.scaledl.overview.specification.PlatformServiceDescriptor;
import org.scaledl.overview.specification.PlatformSupportServiceDescriptor;
import org.scaledl.overview.specification.ProvidedPlatformRuntimeServiceDescriptor;
import org.scaledl.overview.specification.ProvidedPlatformServiceDescriptor;
import org.scaledl.overview.specification.ProvidedPlatformSupportServiceDescriptor;
import org.scaledl.overview.specification.ProvidedServiceDescriptor;
import org.scaledl.overview.specification.ServiceDescriptor;
import org.scaledl.overview.specification.ServiceSpecification;
import org.scaledl.overview.specification.Specification;

public class OverviewHelper
{

	public static List<NetworkInfrastructureServiceDescriptor> getNetworkInfrastructureDescriptors(CloudSpecification specification)
	{
		LinkedList<NetworkInfrastructureServiceDescriptor> list = new LinkedList<NetworkInfrastructureServiceDescriptor>();
		for (InfrastructureServiceDescriptor isd : specification.getInfrastructureServiceDescriptors())
		{
			if (isd instanceof NetworkInfrastructureServiceDescriptor)
			{
				list.add((NetworkInfrastructureServiceDescriptor) isd);

			}
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public static List<ProvidedSoftwareService> getSaaSDescriptors(CloudSpecification specification)
	{
		LinkedList<ProvidedSoftwareService> list = new LinkedList<ProvidedSoftwareService>();
		list.addAll((Collection<? extends ProvidedSoftwareService>) specification.getSoftwareServiceDescriptors());
		return list;
	}

	public static List<ComputingInfrastructureServiceDescriptor> getIaaSRuntimeDescriptors(CloudSpecification specification)
	{
		LinkedList<ComputingInfrastructureServiceDescriptor> list = new LinkedList<ComputingInfrastructureServiceDescriptor>();

		if (specification == null)
			return list;

		for (InfrastructureServiceDescriptor isd : specification.getInfrastructureServiceDescriptors())
		{
			if (isd instanceof ComputingInfrastructureServiceDescriptor)
			{
				ComputingInfrastructureServiceDescriptor cisd = (ComputingInfrastructureServiceDescriptor) isd;
				if (cisd.isGeneralPurpose())
				{
					list.add(cisd);
				}
			}
		}

		return list;

	}

	@SuppressWarnings("unchecked")
	public static List<ProvidedPlatformRuntimeServiceDescriptor> getPaaSRuntimeDescriptors(CloudSpecification specification)
	{
		return (List<ProvidedPlatformRuntimeServiceDescriptor>) getPaaSDescriptors(specification, false, true);

	}

	@SuppressWarnings("unchecked")
	public static List<ProvidedPlatformSupportServiceDescriptor> getPaaSSuportDescriptors(CloudSpecification specification)
	{
		return (List<ProvidedPlatformSupportServiceDescriptor>) getPaaSDescriptors(specification, true, false);
	}

	public static List<? extends ProvidedPlatformServiceDescriptor> getPaaSDescriptors(CloudSpecification specification)
	{
		return getPaaSDescriptors(specification, true, true);
	}

	public static List<? extends PlatformServiceDescriptor> getPlatformDescriptors(ServiceSpecification specification, boolean support,
			boolean runtime)
	{
		LinkedList<PlatformServiceDescriptor> list = new LinkedList<PlatformServiceDescriptor>();
		for (ServiceDescriptor sd : specification.getServiceDescriptors())
		{
			if (runtime && sd instanceof PlatformRuntimeServiceDescriptor)
				list.add((PlatformRuntimeServiceDescriptor) sd);

			if (support && sd instanceof PlatformSupportServiceDescriptor)
				list.add((PlatformSupportServiceDescriptor) sd);
		}

		return list;
	}

	public static List<? extends ProvidedPlatformServiceDescriptor> getPaaSDescriptors(CloudSpecification specification, boolean support,
			boolean runtime)
	{
		LinkedList<ProvidedPlatformServiceDescriptor> list = new LinkedList<ProvidedPlatformServiceDescriptor>();

		if (specification == null)
			return list;

		for (ProvidedServiceDescriptor psd : specification.getPlatformServiceDescriptors())
		{

			if (runtime && psd instanceof ProvidedPlatformRuntimeServiceDescriptor)
			{
				list.add((ProvidedPlatformRuntimeServiceDescriptor) psd);
			}

			if (support && psd instanceof ProvidedPlatformSupportServiceDescriptor)
			{
				list.add((ProvidedPlatformServiceDescriptor) psd);
			}
		}

		return list;
	}

	public static List<CloudSpecification> getCloudSpecificationsIaaS()
	{
		List<CloudSpecification> list = new LinkedList<CloudSpecification>();
		for (CloudSpecification cs : SpecificationProviderService.getInstance().getCloudSpecifications())
		{
			for (InfrastructureServiceDescriptor isd : cs.getInfrastructureServiceDescriptors())
			{
				if (isd instanceof ComputingInfrastructureServiceDescriptor)
				{
					ComputingInfrastructureServiceDescriptor cids = (ComputingInfrastructureServiceDescriptor) isd;
					if (cids.isGeneralPurpose())
					{
						list.add(cs);
						break;
					}
				}
			}
		}

		return list;
	}

	public static List<CloudSpecification> getCloudSpecificationsPaaS()
	{
		List<CloudSpecification> list = new LinkedList<CloudSpecification>();
		for (CloudSpecification cs : SpecificationProviderService.getInstance().getCloudSpecifications())
		{
			for (ProvidedServiceDescriptor psd : cs.getPlatformServiceDescriptors())
			{
				if (psd instanceof ProvidedPlatformRuntimeServiceDescriptor)
				{
					list.add(cs);
					break;
				}
			}
		}

		return list;

	}

	public static List<CloudSpecification> getCloudSpecificationsPrivate()
	{
		List<CloudSpecification> list = new LinkedList<CloudSpecification>();
		for (CloudSpecification cs : SpecificationProviderService.getInstance().getCloudSpecifications())
		{
			for (InfrastructureServiceDescriptor isd : cs.getInfrastructureServiceDescriptors())
			{
				if (isd instanceof ComputingInfrastructureServiceDescriptor)
				{
					ComputingInfrastructureServiceDescriptor cids = (ComputingInfrastructureServiceDescriptor) isd;
					if (cids.isGeneralPurpose() && cids.getComputingResourceDescriptors().isEmpty())
					{
						list.add(cs);
						break;
					}
				}
			}
		}

		return list;
	}

	public static List<ServiceSpecification> getServiceSpecifications()
	{
		List<ServiceSpecification> l = new LinkedList<ServiceSpecification>();
		for (Specification specs : SpecificationProviderService.getInstance().getDefinitions())
		{
			if (specs instanceof ServiceSpecification)
			{
				l.add((ServiceSpecification) specs);
			}
		}

		return l;
	}

	public static List<PlatformRuntimeService> getPlatformRuntimeServices(Overview overview)
	{
		LinkedList<PlatformRuntimeService> l = new LinkedList<>();

		for (CloudEnvironment ce : overview.getArchitecture().getCloudEnvironments())
		{
			for (PlatformService ps : ce.getPlatformLayer().getServices())
			{
				if (ps instanceof PlatformRuntimeService)
				{
					l.add((PlatformRuntimeService) ps);
				}
			}
		}

		return l;
	}

	public static void mergeOverviewModel(Overview from, Overview to)
	{
		// // // // // // // // //
		// CloudEnvironment
		//
		CloudEnvironment ceToMerge = from.getArchitecture().getCloudEnvironments().get(0);

		if (to.getArchitecture().getCloudEnvironments().isEmpty())
		{
			to.getArchitecture().getCloudEnvironments().add(ceToMerge);
		}

		CloudEnvironment ce = to.getArchitecture().getCloudEnvironments().get(0);

		ce.getSoftwareLayer().getServices().addAll(ceToMerge.getSoftwareLayer().getServices());
		ce.getPlatformLayer().getServices().addAll(ceToMerge.getPlatformLayer().getServices());
		ce.getInfrastructureLayer().getServices().addAll(ceToMerge.getInfrastructureLayer().getServices());
		ce.getInternalConnections().addAll(ceToMerge.getInternalConnections());

		// // // // // // // // 
		// UsageProxy
		//
		UsageProxy usageProxyToMerge = (UsageProxy) from.getArchitecture().getProxies().get(0);
		if (!usageProxyToMerge.getRequiredInterfaces().isEmpty())
		{
			if (to.getArchitecture().getProxies().isEmpty())
			{
				to.getArchitecture().getProxies().add(usageProxyToMerge);
				to.getArchitecture().getUsageConnections().addAll(from.getArchitecture().getUsageConnections());
			} else
			{
				UsageProxy usageProxy = (UsageProxy) to.getArchitecture().getProxies().get(0);
				usageProxy.getRequiredInterfaces().addAll(usageProxyToMerge.getRequiredInterfaces());

				ExternalConnection externalConnection = from.getArchitecture().getUsageConnections().get(0);
				externalConnection.setSource(usageProxy);
				to.getArchitecture().getUsageConnections().add(externalConnection);
			}
		}

		// // // // // // // // //
		// Other stuff
		//
		to.getDataTypes().getTypes().addAll(from.getDataTypes().getTypes());
		to.getDefinition().getDescriptors().addAll(from.getDefinition().getDescriptors());
		to.getDeployment().getServiceDeployments().addAll(from.getDeployment().getServiceDeployments());

	}
}
