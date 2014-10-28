package eu.cloudscaleproject.env.extractor.wizard.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.scaledl.overview.SpecificationProviderService;
import org.scaledl.overview.architecture.ProvidedSoftwareService;
import org.scaledl.overview.specification.CloudSpecification;
import org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.InfrastructureServiceDescriptor;
import org.scaledl.overview.specification.NetworkInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.ProvidedPlatformRuntimeServiceDescriptor;
import org.scaledl.overview.specification.ProvidedPlatformServiceDescriptor;
import org.scaledl.overview.specification.ProvidedPlatformSupportServiceDescriptor;
import org.scaledl.overview.specification.ProvidedServiceDescriptor;

public class OverviewHelper {
	
	public static List<NetworkInfrastructureServiceDescriptor> getNetworkInfrastructureDescriptors (CloudSpecification specification)
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
	public static List<ProvidedSoftwareService> getSaaSDescriptors (CloudSpecification specification)
	{
		LinkedList<ProvidedSoftwareService> list = new LinkedList<ProvidedSoftwareService>();
		list.addAll((Collection<? extends ProvidedSoftwareService>) specification.getSoftwareServiceDescriptors());
		return list;
	}

	public static List<ComputingInfrastructureServiceDescriptor> getIaaSRuntimeDescriptors (CloudSpecification specification)
	{
		LinkedList<ComputingInfrastructureServiceDescriptor> list = new LinkedList<ComputingInfrastructureServiceDescriptor>();

		if (specification == null) return list;
		
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
	public static List<ProvidedPlatformRuntimeServiceDescriptor> getPaaSRuntimeDescriptors (CloudSpecification specification)
	{
		return (List<ProvidedPlatformRuntimeServiceDescriptor>) getPaaSDescriptors(specification, false, true);
		
	}
	@SuppressWarnings("unchecked")
	public static List<ProvidedPlatformSupportServiceDescriptor> getPaaSSuportDescriptors (CloudSpecification specification)
	{
		return (List<ProvidedPlatformSupportServiceDescriptor>)getPaaSDescriptors(specification, true, false);
	}


	public static List<? extends ProvidedPlatformServiceDescriptor> getPaaSDescriptors (CloudSpecification specification)
	{
		return getPaaSDescriptors(specification, true, true);
	}

	public static List<? extends ProvidedPlatformServiceDescriptor> getPaaSDescriptors (CloudSpecification specification, boolean support, boolean runtime)
	{
		LinkedList<ProvidedPlatformServiceDescriptor> list = new LinkedList<ProvidedPlatformServiceDescriptor>();

		if (specification == null) return list;
		
		for (ProvidedServiceDescriptor psd : specification.getPlatformServiceDescriptors())
		{

			if (runtime && psd instanceof ProvidedPlatformRuntimeServiceDescriptor)
			{
				list.add((ProvidedPlatformRuntimeServiceDescriptor)psd);
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
}
