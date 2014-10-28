package org.scaledl.overview.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.scaledl.overview.Overview;
import org.scaledl.overview.application.OperationInterface;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.architecture.Architecture;
import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.ComputingInfrastructureService;
import org.scaledl.overview.architecture.Connection;
import org.scaledl.overview.architecture.ExternalConnection;
import org.scaledl.overview.architecture.InfrastructureService;
import org.scaledl.overview.architecture.InternalConnection;
import org.scaledl.overview.architecture.PlatformService;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.architecture.SoftwareServiceContainer;
import org.scaledl.overview.core.Entity;
import org.scaledl.overview.deployment.Deployment;
import org.scaledl.overview.deployment.RuntimeDeployment;
import org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.ComputingResourceDescriptor;
import org.scaledl.overview.specification.SpecificationFactory;

public class OverviewArchitectureUtil {
	
	private static Logger logger = Logger.getLogger(OverviewArchitectureUtil.class.getName());
	
	/**
	 * Collect all computing infrastructure services in the cloud environment with specified descriptor.
	 * Specified descriptors must be contained in system definition and used by computing infrastructure services,<br>
	 * because the method uses direct java object comparison.
	 * 
	 * @param ce Cloud environment that contains (or not) desired computing infrastructure services.
	 * @param cisdList List of computing infrastructure descriptors from system definition.
	 * @return List of infrastructure services.
	 */
	public static List<ComputingInfrastructureService> collectComputingInfrastructureServices(CloudEnvironment ce, 
																							  List<ComputingInfrastructureServiceDescriptor> cisdList){
		List<ComputingInfrastructureService> out = new ArrayList<ComputingInfrastructureService>();
		
		for(InfrastructureService is : ce.getInfrastructureLayer().getServices()){
			if(is instanceof ComputingInfrastructureService){
				ComputingInfrastructureService cis = (ComputingInfrastructureService)is;
				if(cisdList.contains(cis.getDescriptor())){
					out.add(cis);
				}
			}
		}
		
		return out;
	}
	
	/**
	 * Create and return new computing infrastructure service based on specified descriptor.<br>
	 * Specified descriptor doesen't have to be contained in resource or EMF model, because the method creates new<br>
	 * descriptor instance and adds it to <code>SystemDefinition</code>.
	 * 
	 * If the specified descriptor is NULL, the method tries to locate first accessible<br>
	 * computing infrastructure service descriptor from <code>SystemDefinition</code> or creates<br>
	 * new editable and accessible computing infrastructure descriptor and adds it to <code>SystemDefinition</code> 
	 * 
	 * @param ce Cloud environment.
	 * @param descriptor Computing infrastructure service descriptor that specifies type and parameters of newly created service. It can be NULL.
	 * @return Computing infrastructure service based on specified descriptor.
	 */
	public static ComputingInfrastructureService createComputingInfrastructureService (CloudEnvironment ce, ComputingInfrastructureServiceDescriptor descriptor)
	{
		Overview overview = (Overview)EcoreUtil.getRootContainer(ce);
		
		//validate and supplement computing infrastructure service descriptor
		if (descriptor == null)
		{
			descriptor = OverviewSpecificationUtil.helperGetFistGeneralPurposeComputingInfrastructureServiceDescriptor(ce.getCloudEnvironmentDescriptor().getProviderID());
		}
		
		if(descriptor == null){
			
			logger.warning("createComputingInfrastructureService(): "
					+ "Descriptor is not specified and can not be retrived from \"CloudDefinition\"."
					+ "Using buildin generic editable computing infrastructure descriptor!");
			
			//create custom computing infrastructure service if predefined can not be retrieved
			descriptor = SpecificationFactory.eINSTANCE.createComputingInfrastructureServiceDescriptor();
			descriptor.setName("Custom computing Infrastructure Service");
			descriptor.setProviderID(ce.getCloudEnvironmentDescriptor().getProviderID());			
		}
		
		//copy descriptor into system definition 
		descriptor = OverviewSpecificationUtil.getSystemDescriptor(overview, descriptor);
		
		//create computing infrastructure service
		ComputingInfrastructureService cis  = ArchitectureFactory.eINSTANCE.createComputingInfrastructureService();
		
		ce.getInfrastructureLayer().getServices().add(cis);
		cis.setName(descriptor.getName());
		cis.setDescription(descriptor.getDescription());
		cis.setDescriptor(descriptor);
		
		
		Deployment deployment = OverviewUtil.getDeployment(ce);

		ComputingResourceDescriptor crd = null;
		if (descriptor.getComputingResourceDescriptors().isEmpty())
			crd = SpecificationFactory.eINSTANCE.createComputingResourceDescriptor();
		else
			crd = descriptor.getComputingResourceDescriptors().get(0);
		
		RuntimeDeployment rd = OverviewDeploymentUtil.createRuntimeDeployment(deployment, crd);
		cis.setDeployment(rd);
		
		return cis;
	}
	
	/**
	 * 
	 * Delete computing infrastructure service and corresponding components if they are not used<br>
	 * or referenced by any upper layer service (platform service).
	 * 
	 * @param cis Computing infrastructure service to delete.
	 */
	public static void deleteComputingInfrastructureService(ComputingInfrastructureService cis){
		
		//TODO: delete descriptor if not used elsewhere
		if(cis != null && cis.getPlatformServices().size() <= 1){
			
			Deployment deployment = OverviewUtil.getDeployment(cis);
			OverviewDeploymentUtil.deleteServiceDeployment(deployment, cis.getDeployment());			
			
			//release descriptor
			OverviewSpecificationUtil.releaseSystemDescriptor(cis, cis.getDescriptor());
			
			cis.getInfrastructureLayer().getServices().remove(cis);

		}
	}

	/**
	 * 
	 * Collect all <code>OperationInterface</code> objects, provided by specified <code>PlatformService</code>.
	 * 
	 * @param platformService <code>PlatformService</code> in question.
	 * @return <code>HashSet</code> of provided operation interfaces.
	 */
	public static Set<OperationInterface> collectProvidedInterfaces(Entity entity){
		
		HashSet<OperationInterface> operationInterfaces = new HashSet<OperationInterface>();

		TreeIterator<EObject> iter = EcoreUtil.getAllContents(entity, true);		
		while(iter.hasNext()){
			EObject item = iter.next();
			if(item instanceof OperationInterfaceContainer){
				OperationInterfaceContainer oic = (OperationInterfaceContainer)item;
				operationInterfaces.addAll(oic.getProvidedInterfaces());
			}
		}
		
		return operationInterfaces;
	}
	
	/**
	 * 
	 * Collect all <code>OperationInterface</code> objects, required by specified <code>PlatformService</code>.
	 * 
	 * @param platformService <code>PlatformService</code> in question.
	 * @return <code>HashSet</code> of required operation interfaces.
	 */
	public static Set<OperationInterface> collectRequiredInterfaces(PlatformService platformService){
		HashSet<OperationInterface> operationInterfaces = new HashSet<OperationInterface>();
		if(platformService instanceof OperationInterfaceContainer){
			OperationInterfaceContainer oic = (OperationInterfaceContainer)platformService;
			operationInterfaces.addAll(oic.getRequiredInterfaces());
		}
		else if(platformService instanceof SoftwareServiceContainer){
			SoftwareServiceContainer asc = (SoftwareServiceContainer)platformService;
			for(SoftwareService as : asc.getSoftwareServices()){
				operationInterfaces.addAll(as.getRequiredInterfaces());
			}
		}
		return operationInterfaces;
	}
	
	
	/**
	 * 
	 * Collect all <code>OperationInterfaceContainer</code>'s connected with the specified operation interface container.<br>
	 * Resulting set of operation interface containers are in position to provide operation interfaces, required by the specified container.<br> 
	 * <br>
	 * @param opInterfaceContainer Operation interface container for which the method collects potential containers.
	 * @return Set of Operation interface containers that can require the operation interface provided by the specified container of the same type.
	 */
	public static Set<OperationInterfaceContainer> collectPotentialyResolveConteiners (OperationInterfaceContainer opInterfaceContainer)
	{
		
	    Collection<Setting> find = EcoreUtil.UsageCrossReferencer.find(opInterfaceContainer, opInterfaceContainer.eResource());
	  
	    Set<OperationInterfaceContainer> potentialContainers = new HashSet<OperationInterfaceContainer>();
	    
	    for (Setting setting : find) {
			
	    	EObject obj = setting.getEObject();
	    	if (obj instanceof Connection)
	    	{
	    		Connection c = (Connection) obj;
	    		
	    		if (c instanceof InternalConnection)
	    		{
	    			InternalConnection ic = (InternalConnection) c;
	    			
	    			if (ic.getTarget() == opInterfaceContainer)
	    			{
	    				potentialContainers.add((OperationInterfaceContainer)ic.getSource());
	    			}
	    		}
	    		else if (c instanceof ExternalConnection)
	    		{
	    			ExternalConnection ec = (ExternalConnection) c;
	    			
	    			if (ec.getTarget() == opInterfaceContainer)
	    			{
	    				potentialContainers.add((OperationInterfaceContainer)ec.getSource());
	    			}
	    		}
	    	}
		}
	    
	   return potentialContainers;
	}
	
	/**
	 *
	 * Collect all operation interfaces that could be required by specified <code>OperationInterfaceContainer</code>.
	 * 
	 * Operation interface can be required by <code>OperationInterfaceContainer</code> if it's container<br>
	 * have <code>Connection</code> to the specified <code>OperationInterfaceContainer</code>.
	 * 
	 * @param opInterfaceContainer Operation interface container for which the method collects potentially required operation interfaces.
	 * @return Set of operation interfaces that could be required by the specified <code>OperationInterfaceContainer</code>.
	 */
	public static Set<OperationInterface> collectPotentialyRequiredInterfaces(OperationInterfaceContainer opInterfaceContainer){
		
	    Collection<Setting> find = EcoreUtil.UsageCrossReferencer.find(opInterfaceContainer, opInterfaceContainer.eResource());

	    Set<OperationInterface> interfaces = new HashSet<OperationInterface>();
	    Set<OperationInterfaceContainer> providerServices = new HashSet<OperationInterfaceContainer>();
	    
	    for (Setting setting : find) {
			
	    	EObject obj = setting.getEObject();
	    	if (obj instanceof Connection)
	    	{
	    		Connection c = (Connection) obj;
	    		
	    		if (c instanceof InternalConnection)
	    		{
	    			InternalConnection ic = (InternalConnection) c;
	    			if(ic.getSource() == opInterfaceContainer){
	    				providerServices.add(ic.getTarget());
	    			}
	    			
	    		}
	    		else if (c instanceof ExternalConnection)
	    		{
	    			ExternalConnection ec = (ExternalConnection) c;
	    			if(ec.getSource() == opInterfaceContainer){
	    				providerServices.add(ec.getTarget());
	    			}
	    		}
	    	}
		}
	    
	    for (OperationInterfaceContainer provider : providerServices) {
	    	
	    	interfaces.addAll(provider.getProvidedInterfaces());
		}
	    
	   return interfaces;
	}
	
	/**
	 * 
	 * Remove all required operation interface references from specified <code>Entity</code><br>
	 * This operation recursively removes all interfaces.
	 * 
	 * @param platformService Platform service from which method removes all required <code>OperationInterfaces</code>.
	 * @param interfaces Operation interfaces to remove.
	 */
	public static void removeRequiredInterfaces(Entity entity, 
												Collection<? extends OperationInterface> interfaces){
		
		TreeIterator<EObject> iter = EcoreUtil.getAllContents(entity, true);
		
		while(iter.hasNext()){
			EObject item = iter.next();
			if(item instanceof OperationInterfaceContainer){
				OperationInterfaceContainer oic = (OperationInterfaceContainer)item;
				oic.getRequiredInterfaces().removeAll(interfaces);
			}
		}
	}
	
	/**
	 * 
	 * This method removes all cross-references and clears containment reference <br>
	 * from the EMF model, which points to operation interface specified in interfaces collection.<br>
	 * 
	 * @param interfaces Collection of operation interfaces to remove from EMF model.
	 */
	public static void deleteInterfaces(Collection<? extends OperationInterface> interfaces){
		List<OperationInterface> tmp = new ArrayList<OperationInterface>();
		tmp.addAll(interfaces);
		
		for(OperationInterface opInterface : tmp){
			deleteInterface(opInterface);
		}
	}

	/**
	 * 
	 * This method removes all cross-references and clears containment reference,<br>
	 * of specified <core>OperationInterface</core>, from EMF model.
	 * 
	 * @param interfaces Operation interface to remove with all of it's cross-references from EMF model.
	 */
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
	
}
