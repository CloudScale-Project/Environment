package eu.cloudscaleproject.env.csm.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.DefinitionProviderService;
import eu.cloudscaleproject.env.csm.architecture.Architecture;
import eu.cloudscaleproject.env.csm.architecture.ArchitectureFactory;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.Connection;
import eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService;
import eu.cloudscaleproject.env.csm.architecture.ExternalConnection;
import eu.cloudscaleproject.env.csm.architecture.ExternalSoftwareService;
import eu.cloudscaleproject.env.csm.architecture.HybridConnection;
import eu.cloudscaleproject.env.csm.architecture.InternalConnection;
import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.architecture.ServiceProxy;
import eu.cloudscaleproject.env.csm.architecture.SoftwareService;
import eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer;
import eu.cloudscaleproject.env.csm.architecture.UsageProxy;
import eu.cloudscaleproject.env.csm.converter.ICsmConverter;
import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.core.Operation;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.core.TypeEnum;
import eu.cloudscaleproject.env.csm.definition.CloudDefinition;
import eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.DefinitionFactory;
import eu.cloudscaleproject.env.csm.definition.Descriptor;
import eu.cloudscaleproject.env.csm.definition.InfrastructureServiceDescriptor;

public class CsmUtil {

	public static CloudEnvironment getCloudEnvironment(EObject eobject){
		EObject current = eobject;
		while(current != null){
			if(current instanceof CloudEnvironment){
				return (CloudEnvironment)current;
			}
			current = current.eContainer();
		}
		return null;
	}

	public static <T extends Descriptor> T getSystemDescriptor (EObject obj, T descriptor)
	{
			T systemDescriptor = null;
			@SuppressWarnings("unchecked")
			Class<T> tClass = (Class<T>) descriptor.getClass();
			
			Csm csm = (Csm)EcoreUtil.getRootContainer(obj);
			
			for (Descriptor desc : csm.getDefinition().getDescriptors()) {
				if (tClass.isAssignableFrom(desc.getClass())) // Instanceof
				{
					if (desc.getName().equals(descriptor.getName()))
					{
						systemDescriptor = tClass.cast(desc);
						break;
					}
				}
			}
			
			if (systemDescriptor == null)
			{
				systemDescriptor = EcoreUtil.copy(descriptor);
				csm.getDefinition().getDescriptors().add(systemDescriptor);
			}
			
			return systemDescriptor;
	}
	
	public static <T extends Descriptor> void removeSystemDescriptor(EObject obj, T descriptor){
		@SuppressWarnings("unchecked")
		Class<T> tClass = (Class<T>) descriptor.getClass();
		
		Csm csm = (Csm)EcoreUtil.getRootContainer(obj);
		
		for (Descriptor desc : csm.getDefinition().getDescriptors()) {
			if (tClass.isAssignableFrom(desc.getClass())) // Instanceof
			{
				if (desc.getName().equals(descriptor.getName()))
				{
					EcoreUtil.delete(descriptor, false);
					return;
				}
			}
		}
	}
	
	public static boolean hasExternalModel(Entity e){
		if(e.getAeMap().containsKey(ICsmConverter.KEY_PCM_REPOSITORY)
				|| e.getAeMap().containsKey(ICsmConverter.KEY_PCM_SYSTEM)
				|| e.getAeMap().containsKey(ICsmConverter.KEY_PCM_INTERFACE)){
			return true;
		}
		return false;
	}

	public static List<ComputingInfrastructureServiceDescriptor> getComputingInfrastructureServicesDescriptors(CloudDefinition cd){
		
		List<ComputingInfrastructureServiceDescriptor> out = new ArrayList<ComputingInfrastructureServiceDescriptor>();
		for(InfrastructureServiceDescriptor isd : cd.getInfrastructureDescriptors()){
			if(isd instanceof ComputingInfrastructureServiceDescriptor){
				out.add((ComputingInfrastructureServiceDescriptor)isd);
			}
		}
		return out;
	}
	
	public static List<ComputingInfrastructureServiceDescriptor> getComputingInfrastructureServicesDescriptors(CloudEnvironment ce){
		
		List<ComputingInfrastructureServiceDescriptor> out = new ArrayList<ComputingInfrastructureServiceDescriptor>();
		
		CloudDefinition cloudDefinition = DefinitionProviderService.getInstance().
											getCloudDefinition(ce.getCloudProviderDescriptor().getProviderID());
		for(InfrastructureServiceDescriptor isd : cloudDefinition.getInfrastructureDescriptors()){
			if(isd instanceof ComputingInfrastructureServiceDescriptor){
				out.add((ComputingInfrastructureServiceDescriptor)isd);
			}
		}
		return out;
	}
	
	public static ComputingInfrastructureService createDefaultComputingInfrastructureService (CloudEnvironment ce)
	{
		Csm csm = (Csm) EcoreUtil.getRootContainer(ce);
		ComputingInfrastructureService cis  = ArchitectureFactory.eINSTANCE.createComputingInfrastructureService();
		
		List<ComputingInfrastructureServiceDescriptor> cisdSet = getComputingInfrastructureServicesDescriptors(ce);
		ComputingInfrastructureServiceDescriptor cisd = null;
		
		if (cisdSet.isEmpty())
		{
			cisd = DefinitionFactory.eINSTANCE.createComputingInfrastructureServiceDescriptor();
			cisd.setName("Custom instance");
			cisd.setProviderID(ce.getCloudProviderDescriptor().getProviderID());
			csm.getDefinition().getDescriptors().add(cisd);
		}
		else
		{
			cisd = getSystemDescriptor(csm, cisdSet.get(0));
		}
		
		ce.getInfrastructureLayer().getServices().add(cis);
		cis.setName("Instance "+ce.getInfrastructureLayer().getServices().size());
		cis.setDescriptor(cisd);
		return cis;
	}
	
	public static void gcOrphanedObjects (Csm csm)
	{
		for (Descriptor desc : csm.getDefinition().getDescriptors().toArray(new Descriptor[0])) {
			if (isOrphaned(desc))
			{
				csm.getDefinition().getDescriptors().remove(desc);
			}
		}
	}
	
	public static boolean isOrphaned (Entity entity)
	{
		return EcoreUtil.UsageCrossReferencer.find(entity, entity.eResource()).isEmpty();
	}

	public static Set<OperationInterface> collectProvidedInterfaces(PlatformService platformService){
		HashSet<OperationInterface> operationInterfaces = new HashSet<OperationInterface>();
		if(platformService instanceof OperationInterfaceContainer){
			OperationInterfaceContainer oic = (OperationInterfaceContainer)platformService;
			operationInterfaces.addAll(oic.getProvidedInterfaces());
		}
		else if(platformService instanceof SoftwareServiceContainer){
			SoftwareServiceContainer asc = (SoftwareServiceContainer)platformService;
			for(DeployableSoftwareService as : asc.getSoftwareServices()){
				operationInterfaces.addAll(as.getProvidedInterfaces());
			}
		}
		return operationInterfaces;
	}
	
	public static Set<OperationInterface> collectRequiredInterfaces(PlatformService platformService){
		HashSet<OperationInterface> operationInterfaces = new HashSet<OperationInterface>();
		if(platformService instanceof OperationInterfaceContainer){
			OperationInterfaceContainer oic = (OperationInterfaceContainer)platformService;
			operationInterfaces.addAll(oic.getRequiredInterfaces());
		}
		else if(platformService instanceof SoftwareServiceContainer){
			SoftwareServiceContainer asc = (SoftwareServiceContainer)platformService;
			for(DeployableSoftwareService as : asc.getSoftwareServices()){
				operationInterfaces.addAll(as.getRequiredInterfaces());
			}
		}
		return operationInterfaces;
	}
	
	public static Set<OperationInterfaceContainer> collectPotentialyResolveConteiners (OperationInterfaceContainer opInterfaceContainer)
	{
		EObject connectedService = null;
		if (opInterfaceContainer instanceof DeployableSoftwareService)
		{
			connectedService = (PlatformService)((DeployableSoftwareService)opInterfaceContainer).getRuntimePlatformService();
		}
		else if(opInterfaceContainer instanceof PlatformService){
			connectedService = (PlatformService)opInterfaceContainer;
		}
		else if(opInterfaceContainer instanceof UsageProxy){
			connectedService = opInterfaceContainer;
		}
		else if(opInterfaceContainer instanceof ExternalSoftwareService){
			ExternalSoftwareService ess = (ExternalSoftwareService)opInterfaceContainer;
			connectedService = ess.getServiceProxy();
		}
		else 
		{
			throw new UnsupportedOperationException();
		}
		
	    Collection<Setting> find = EcoreUtil.UsageCrossReferencer.find(connectedService, connectedService.eResource());
	  
	    Set<OperationInterfaceContainer> potentialContainers = new HashSet<OperationInterfaceContainer>();
	    
	    for (Setting setting : find) {
			
	    	EObject obj = setting.getEObject();
	    	if (obj instanceof Connection)
	    	{
	    		Connection c = (Connection) obj;
	    		
	    		if (c instanceof InternalConnection)
	    		{
	    			InternalConnection ic = (InternalConnection) c;
	    			
	    			if (ic.getDestinationPlatformService() == connectedService 
	    					&& ic.getSourcePlatformService() instanceof OperationInterfaceContainer)
	    			{
	    				potentialContainers.add((OperationInterfaceContainer)ic.getSourcePlatformService());
	    			}
	    		}
	    		else if (c instanceof HybridConnection)
	    		{
	    			HybridConnection hc = (HybridConnection) c;
	    			
	    			if (hc.getTargetPlatformService() == connectedService)
	    			{
		    			if (hc.getSourcePlatformService() instanceof OperationInterfaceContainer)
		    			{
		    				potentialContainers.add((OperationInterfaceContainer)hc.getSourcePlatformService());
		    			}
		    			else if (hc.getSourcePlatformService() instanceof SoftwareServiceContainer)
		    			{
		    				SoftwareServiceContainer ssc = (SoftwareServiceContainer) hc.getSourcePlatformService();
		    				potentialContainers.addAll(ssc.getSoftwareServices());
		    			}
	    			}
	    			
	    		}
	    		else if(c instanceof ExternalConnection){
	    			ExternalConnection ec = (ExternalConnection)c;
	    			
	    			
	    				if(ec.getPlatformService() == connectedService && ec.getProxy() instanceof ServiceProxy){
	    					SoftwareService ss = ((ServiceProxy)ec.getProxy()).getSoftwareService();
	    					potentialContainers.add(ss);
	    				}
	    		}
	    	}
		}
	    
	   return potentialContainers;
	}
	
	public static Set<OperationInterface> collectPotentialyRequiredInterfaces(OperationInterfaceContainer opInterfaceContainer){
		
		EObject connectedService = null;
		if (opInterfaceContainer instanceof DeployableSoftwareService)
		{
			connectedService = (PlatformService)((DeployableSoftwareService)opInterfaceContainer).getRuntimePlatformService();
		}
		else if(opInterfaceContainer instanceof PlatformService){
			connectedService = (PlatformService)opInterfaceContainer;
		}
		else if(opInterfaceContainer instanceof UsageProxy){
			connectedService = opInterfaceContainer;
		}
		else if(opInterfaceContainer instanceof ExternalSoftwareService){
			ExternalSoftwareService ess = (ExternalSoftwareService)opInterfaceContainer;
			connectedService = ess.getServiceProxy();
		}
		else 
		{
			throw new UnsupportedOperationException();
		}
		
	    Collection<Setting> find = EcoreUtil.UsageCrossReferencer.find(connectedService, connectedService.eResource());
	    
	    //LinkedList<OperationInterface> interfaces = new LinkedList<OperationInterface>();
	    //LinkedList<PlatformService> providerServices = new LinkedList<PlatformService>();

	    Set<OperationInterface> interfaces = new HashSet<OperationInterface>();
	    Set<EObject> providerServices = new HashSet<EObject>();
	    
	    for (Setting setting : find) {
			
	    	EObject obj = setting.getEObject();
	    	if (obj instanceof Connection)
	    	{
	    		Connection c = (Connection) obj;
	    		
	    		if (c instanceof InternalConnection)
	    		{
	    			InternalConnection ic = (InternalConnection) c;
	    			
	    			if (ic.getDestinationPlatformService() == connectedService)
	    			{
	    				providerServices.add(ic.getSourcePlatformService());
	    			}
	    		}
	    		else if (c instanceof HybridConnection)
	    		{
	    			HybridConnection hc = (HybridConnection) c;
	    			
	    			if (hc.getTargetPlatformService() == connectedService)
	    			{
	    				providerServices.add(hc.getSourcePlatformService());
	    			}
	    		}
	    		else if(c instanceof ExternalConnection){
	    			ExternalConnection ec = (ExternalConnection)c;
	    			
	    			if(ec.getIsOutbound()){
	    				if(ec.getProxy() == connectedService){
	    					providerServices.add(ec.getPlatformService());
	    				}
	    			}
	    			else{
	    				if(ec.getPlatformService() == connectedService){
	    					providerServices.add(ec.getProxy());
	    				}
	    			}
	    		}
	    	}
		}
	    
	    for (EObject provider : providerServices) {
	    	if (provider instanceof SoftwareServiceContainer)
	    	{
	    		for (SoftwareService as : ((SoftwareServiceContainer)provider).getSoftwareServices()) {
	    			interfaces.addAll(as.getProvidedInterfaces());
				}
	    	}
	    	else if (provider instanceof ServiceProxy){
	    		ServiceProxy sp = (ServiceProxy)provider;
	    		if(sp.getSoftwareService() != null){
	    			interfaces.addAll(sp.getSoftwareService().getProvidedInterfaces());
	    		}
	    	}
	    	else if (provider instanceof OperationInterfaceContainer)
	    	{
	    		interfaces.addAll( ((OperationInterfaceContainer)provider).getProvidedInterfaces());
	    	}
		}
	    
	   return interfaces;
	}
	
	public static void removeRequiredInterfaces(PlatformService platformService, 
												Collection<? extends OperationInterface> interfaces){
		if(platformService instanceof OperationInterfaceContainer){
			OperationInterfaceContainer oic = (OperationInterfaceContainer)platformService;
			oic.getRequiredInterfaces().removeAll(interfaces);
		}
		else if(platformService instanceof SoftwareServiceContainer){
			SoftwareServiceContainer asc = (SoftwareServiceContainer)platformService;
			for(DeployableSoftwareService as : asc.getSoftwareServices()){
				as.getRequiredInterfaces().removeAll(interfaces);
			}
		}
	}
	
	public static void deleteInterfaces(Collection<? extends OperationInterface> interfaces){
		List<OperationInterface> tmp = new ArrayList<OperationInterface>();
		tmp.addAll(interfaces);
		
		for(OperationInterface opInterface : tmp){
			deleteInterface(opInterface);
		}
	}

	public static void deleteInterface(OperationInterface opInterface) {
		// remove references
		for (EObject object : opInterface.eCrossReferences()) {
			if (object instanceof OperationInterfaceContainer) {
				OperationInterfaceContainer oic = (OperationInterfaceContainer) object;
				oic.getRequiredInterfaces().remove(opInterface);
			} else if (object instanceof Architecture) {
				Architecture arch = (Architecture) object;
				arch.getUnresolvedOperationInterfaces().remove(opInterface);
			} else {
				throw new UnsupportedOperationException(
						"Can't remove reference! Operation interface reference is contained in unsupported object type!");
			}
		}

		// remove containment reference
		EObject container = opInterface.eContainer();
		if (container == null) {
			return;
		} else if (container instanceof OperationInterfaceContainer) {
			((OperationInterfaceContainer) container).getProvidedInterfaces()
					.remove(opInterface);
		} else if (container instanceof Architecture) {
			((Architecture) container).getUnresolvedOperationInterfaces()
					.remove(opInterface);
		} else {
			throw new UnsupportedOperationException(
					"Can't remove reference! Operation interface reference is contained in unsupported container!");
		}
	}
	
	public static String getTextRepresentation(Object object) {

		if (object instanceof Object[]) {
			String out = "List{";
			Object[] list = (Object[]) object;

			for (Object obj : list) {
				out += getTextRepresentation(obj);
				out += ", ";
			}
			if (list.length > 0) {
				out = out.substring(0, Math.max(out.length() - 2, 0));
			}

			out += "}";
			return out + "}";
		} else if (object instanceof List<?>) {
			String out = "List{";
			List<?> list = (List<?>) object;

			for (Object obj : list) {
				out += getTextRepresentation(obj);
				out += ", ";
			}
			if (list.size() > 0) {
				out = out.substring(0, Math.max(out.length() - 2, 0));
			}
			out += "}";

			return out;
		} else if (object instanceof String) {
			return (String) object;
		} else if (object instanceof Operation) {
			Operation op = (Operation) object;

			String out = op.getReturnValue().getLiteral() + " "
					+ op.getMethodName();
			out += " (";
			for (TypeEnum e : op.getParameters()) {
				if (e != null) {
					out += e.getLiteral() + ", ";
				}
			}
			if (!op.getParameters().isEmpty()) {
				out = out.substring(0, out.length() - 2);
			}
			out += ")";

			return out;
		} else if (object instanceof Entity) {
			Entity e = (Entity) object;
			return e.getName();
		} else {
			return String.valueOf(object);
		}
	}
	
}
