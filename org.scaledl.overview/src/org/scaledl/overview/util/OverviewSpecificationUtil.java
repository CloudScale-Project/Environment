package org.scaledl.overview.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.UsageCrossReferencer;
import org.scaledl.overview.Overview;
import org.scaledl.overview.SpecificationProviderService;
import org.scaledl.overview.specification.CloudSpecification;
import org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.Descriptor;
import org.scaledl.overview.specification.InfrastructureServiceDescriptor;

public class OverviewSpecificationUtil {
	
	private static Logger logger = Logger.getLogger(OverviewSpecificationUtil.class.getName());
	
	/**
	 * Collect <code>ComputingInfrastructureServiceDescriptor<code> instances from <code>CloudDefinition</code>.<br>
	 * Transform them with <code>CsmDefinitionUtil.getSystemDescriptor()</code> method before using them in <code>Csm<code> model.<br>
	 * <br>
	 * <br>
	 * @param providerID Cloud provider identification string.
	 * @return List of descriptors.
	 */
	public static synchronized List<ComputingInfrastructureServiceDescriptor> collectComputingInfrastructureServicesDescriptors(String providerID){
		
		List<ComputingInfrastructureServiceDescriptor> out = new ArrayList<ComputingInfrastructureServiceDescriptor>();
		CloudSpecification cloudDefinition = SpecificationProviderService.getInstance().getCloudDefinition(providerID);
		
		if(cloudDefinition == null){
			logger.severe("collectInstanceDescriptors(): Cloud definition with providerID:\""+providerID+"\" does not exist!");
			return out;
		}
		
		for(InfrastructureServiceDescriptor isd : cloudDefinition.getInfrastructureServiceDescriptors()){
			if(isd instanceof ComputingInfrastructureServiceDescriptor){
				out.add((ComputingInfrastructureServiceDescriptor)isd);
			}
		}
		
		return out;
	}
	
	/**
	 * 
	 * Collect all computing infrastructure service descriptors from <code>CloudSpecification</code>, <br>
	 * that represents IaaS and can be directly accessed and manipulated by the end user.
	 * 
	 * @param providerID Cloud provider identification string.
	 * @return List of descriptors.
	 */
	public static synchronized List<ComputingInfrastructureServiceDescriptor> collectGeneralPurposeComputingInfrastructureServicesDescriptors(String providerID){
		
		List<ComputingInfrastructureServiceDescriptor> out = new ArrayList<ComputingInfrastructureServiceDescriptor>();
		List<ComputingInfrastructureServiceDescriptor> isdList = collectComputingInfrastructureServicesDescriptors(providerID);
		
		for(ComputingInfrastructureServiceDescriptor cisd : isdList){
			//TODO: add this flag into ecore model
			if(cisd.isGeneralPurpose()){
				out.add(cisd);
			}
		}
		
		return out;
	}
	
	/**
	 * 
	 * Locate random computing infrastructure service descriptors from <code>CloudDefinition</code>, <br>
	 * that represents IaaS and can be directly accessed and manipulated by the end user.
	 * 
	 * @param providerID Cloud provider identification string.
	 * @return First descriptor that matches criteria.
	 */
	public static synchronized ComputingInfrastructureServiceDescriptor helperGetFistGeneralPurposeComputingInfrastructureServiceDescriptor(String providerID){
		List<ComputingInfrastructureServiceDescriptor> isdList = collectGeneralPurposeComputingInfrastructureServicesDescriptors(providerID);

		if(isdList.isEmpty()){
			return null;
		}
		
		return isdList.get(0);
	}
	
	/**
	 * Collect <code>InstanceDescriptor<code> instances from <code>CloudDefinition</code>.<br>
	 * Transform them with <code>CsmDefinitionUtil.getSystemDescriptor()</code> method before using them in <code>Csm<code> model.<br>
	 * <br>
	 * <br>
	 * @param providerID Provider identification string.
	 * @return List of descriptors.
	 */
	/*
	public static List<InstanceDescriptor> collectInstanceDescriptors(String providerID){
		
		List<InstanceDescriptor> out = new ArrayList<InstanceDescriptor>();
		
		CloudDefinition cloudDefinition = SpecificationProviderService.getInstance().getCloudDefinition(providerID);
		
		if(cloudDefinition == null){
			logger.severe("collectInstanceDescriptors(): Cloud definition with providerID:\""+providerID+"\" does not exist!");
			return out;
		}
	
		for(InfrastructureDescriptor id : cloudDefinition.getInfrastructureDescriptors()){
			if(id instanceof InstanceDescriptor){
				out.add((InstanceDescriptor)id);
			}
		}
		return out;
	}
	*/
	
	/**
	 * 
	 * Create or retrieve descriptor that is contained in the <code>SystemDefinition</code>,<br>
	 * based on specified descriptor that is contained in the <code>CloudDefinition</code>.<br>
	 * All descriptor class feature references are modified accordingly and copied directly into the <code>SystemDefinition</code>.<br>
	 * <br>
	 * If specified descriptor is already contained in <code>SystemDefinition</code>, same object will be returned.<br>
	 * <br>
	 * <code>CloudDefinition</code> holds all information (in a form of <code>Descriptor</code>) about specific cloud environment.<br>
	 * When cloud components are created, those descriptors should be copied into <code>SystemDefinition</code> and referenced from there.<br>
	 * <br>
	 * <br>
	 * @param obj : 		Any <code>EObject</code> that is contained in <code>Overview</code> object instance.<br>
	 * 			    		It is used to retrieve <code>Overview</code> instance.<br>
	 * @param descriptor :  Descriptor that is contained in <code>CloudDefinition</code><br>
	 * @return <code>Descriptor</code> that is contained in SystemDefinition.
	 */
	public static synchronized <T extends Descriptor> T getSystemDescriptor (EObject obj, T descriptor)
	{
		return getSystemDescriptor(obj, descriptor, false);
	}

	/**
	 * 
	 * Create or retrieve descriptor that is contained in the <code>SystemDefinition</code>,<br>
	 * based on specified descriptor that is contained in the <code>CloudDefinition</code>.<br>
	 * All descriptor class feature references are modified accordingly and copied directly into the <code>SystemDefinition</code>.<br>  
	 * <br>
	 * If specified descriptor is already contained in <code>SystemDefinition</code>, same object will be returned.<br>
	 * <br>
	 * <code>CloudDefinition</code> holds all information (in a form of <code>Descriptor</code>) about specific cloud environment.<br>
	 * When cloud components are created, those descriptors should be copied into <code>SystemDefinition</code> and referenced from there.<br>
	 * <br>
	 * <br>
	  * @param obj : 		Any <code>EObject</code> that is contained in <code>Overview</code> object instance.<br>
	 * 			    		It is used to retrieve <code>Overview</code> instance.<br>
	 * @param descriptor :  Descriptor that is contained in <code>CloudDefinition</code><br>
	 * @param forceCreateNew : Force creation of new <code>Descriptor</code> instance in <code>SystemDefinition</code>.<br>
	 * 						 It is useful when creating user defined cloud components, that are based on already defined descriptors.<br>
	 * @return <code>Descriptor</code> that is contained in SystemDefinition.
	 */
	@SuppressWarnings("unchecked")
	public static synchronized <T extends Descriptor> T getSystemDescriptor (EObject obj, T descriptor, boolean forceCreateNew)
	{
		Overview overview = (Overview)EcoreUtil.getRootContainer(obj);
		T systemDescriptor = null;
		
		if(EcoreUtil.getRootContainer(descriptor) instanceof Overview){
			//descriptor is already contained in system definition
			return descriptor;
		}

		if(!forceCreateNew){
			//find and retrieve system descriptor
			Class<T> tClass = (Class<T>) descriptor.getClass();

			for (Descriptor desc : overview.getDefinition().getDescriptors()) {
				if (tClass.isAssignableFrom(desc.getClass())) // Instanceof
				{
					if (desc.getName().equals(descriptor.getName()))
					{
						systemDescriptor = tClass.cast(desc);
						break;
					}
				}
			}
		}

		if (systemDescriptor == null) {
			
			systemDescriptor = EcoreUtil.copy(descriptor);	
			overview.getDefinition().getDescriptors().add(systemDescriptor);
			copyUncontainedDescriptors(systemDescriptor);
		}

		return systemDescriptor;
	}
	
	private static void copyUncontainedDescriptors(Descriptor descriptor){
		
		EClass eClass = descriptor.eClass();
		for (int i = 0, size = eClass.getFeatureCount(); i < size; ++i) {
			
			EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(i);
			
			if (eStructuralFeature.isChangeable() && !eStructuralFeature.isDerived()) {
				if (eStructuralFeature instanceof EReference) {
					
					EReference eReference = (EReference) eStructuralFeature;
					Object subObject = descriptor.eGet(eReference, true);
					
					
					if (subObject instanceof Descriptor) {
						
						if(eReference.isContainment()){
							copyUncontainedDescriptors((Descriptor)subObject);
							continue;
						}
						
						Descriptor newSystemDescriptor = getSystemDescriptor(descriptor, (Descriptor)subObject);
						descriptor.eSet(eReference, newSystemDescriptor);
						copyUncontainedDescriptors(newSystemDescriptor);
					}
					else if(subObject instanceof EList<?>){
						
						List<Object> tmpList = new ArrayList<Object>();
						for(Object o : (EList<?>)subObject){
							tmpList.add(o);
						}
						
						@SuppressWarnings("unchecked")
						EList<Object> ref = (EList<Object>)descriptor.eGet(eStructuralFeature, true);
						
						if(eReference.isContainment()){
							for(Object o : ref){
								if(o instanceof Descriptor){
									copyUncontainedDescriptors((Descriptor)o);
								}
							}
							continue;
						}
						
						ref.clear();
						for(Object o : tmpList){
							if(o instanceof Descriptor){
								Descriptor newSystemDescriptor = getSystemDescriptor(descriptor, (Descriptor)o);
								ref.add(newSystemDescriptor);
								copyUncontainedDescriptors(newSystemDescriptor);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * Remove specified <code>Descriptor</code> from <code>SystemDefinition</code>, only if it is not referenced by other descriptors.<br>
	 * <br>
	 * For more information read the description of <code>CsmDefinitionUtil.getSystemDescriptor()</code> method.<br>
	 * <br>
	 * <br>
	 * @param obj Any <code>EObject</code> that is contained in <code>Overview</code> object instance.
	 * 			  It is used to retrieve <code>Overview</code> instance.
	 * @param descriptor <code>Descriptor</code> to remove from <code>SystemDefinition</code>
	 */
	/*
	public static synchronized <T extends Descriptor> void removeSystemDescriptor(EObject obj, T descriptor){
		@SuppressWarnings("unchecked")
		Class<T> tClass = (Class<T>) descriptor.getClass();
		
		Overview csm = (Overview)EcoreUtil.getRootContainer(obj);
		
		for (Descriptor desc : csm.getDefinition().getDescriptors()) {
			if (tClass.isAssignableFrom(desc.getClass())) // Instanceof
			{
				if (desc.getName().equals(descriptor.getName()))
				{
					//remove descriptor only if it is not referenced elsewhere
					//TODO: check if it works as expected
					int descriptroRefCounter = 0;
					for(Object object : desc.eCrossReferences()){
						if(object instanceof Descriptor){
							descriptroRefCounter++;
						}
					}
					
					if(descriptroRefCounter > 0){
						continue;
					}
					
					//delete all children
					for(EObject child : desc.eContents()){
						EcoreUtil.delete(child);
					}
					
					//delete descriptor
					EcoreUtil.delete(desc, false);
					return;
				}
			}
		}
	}
	*/
	
	/**
	 * 
	 * Remove specified <code>Descriptor</code> from <code>SystemSpecification</code>, only if it is not referenced elsewhere.<br>
	 * <br>
	 * For more information read the description of <code>OverviewSpecificationUtil.getSystemDescriptor()</code> method.<br>
	 * <br>
	 * <br>
	 * @param obj Any <code>EObject</code> that is contained in <code>Overview</code> object instance.
	 * 			  It is used to retrieve <code>Overview</code> model instance.
	 * @param descriptor <code>Descriptor</code> to remove from <code>SystemSpecification</code>
	 */
	public static synchronized <T extends Descriptor> void releaseSystemDescriptor(EObject obj, T descriptor){
		
		if(descriptor == null){
			return;
		}
		
		@SuppressWarnings("unchecked")
		Class<T> tClass = (Class<T>) descriptor.getClass();
		
		EObject root = EcoreUtil.getRootContainer(obj);
		if(root == null || !(root instanceof Overview)){
			logger.severe("releaseSystemDescriptor(obj, descriptor): obj is not contained in 'Overview' model. "
					+ "Can't retrieve 'SystemSpecification', that is contained in Overview model, to release specified descriptor!");
			return;
		}
		
		List<Descriptor> descriptors = new ArrayList<Descriptor>(((Overview)root).getDefinition().getDescriptors());
		
		for (Descriptor desc : descriptors) {
			if (tClass.isAssignableFrom(desc.getClass())) // Instanceof
			{
				if (desc.getName().equals(descriptor.getName()))
				{
					if(!isDescriptorReferencedElsewhere(descriptor, descriptor)){
						EcoreUtil.delete(descriptor);
					}
				}
			}
		}
	}
	
	/**
	 *
	 * Recursively count all "external" references.
	 * 
	 * @param rootDesc Descriptor in question.
	 * @param desc Descriptor used for recursion. It must be equal to @rootDesc. 
	 */
	private static synchronized boolean isDescriptorReferencedElsewhere(Descriptor rootDesc, Descriptor desc){
		//remove descriptor only if it is not referenced elsewhere
		//TODO: check if it works as expected
		EObject root = EcoreUtil.getRootContainer(desc);
		
		int descriptroRefCounter = 0;
		for(Object object : UsageCrossReferencer.find(desc, root)){
			
			//check if reference is marked for release
			boolean isInsideRoot = false;
			if(object instanceof EObject){
				EObject ref = (EObject)object;
				while(ref != null){
					if(ref == rootDesc){
						isInsideRoot = true;
						break;
					}
					ref = ref.eContainer(); 
				}
			}
			
			if(!isInsideRoot){
				descriptroRefCounter++;
			}
		}
		
		if(descriptroRefCounter > 1){
			return true;
		}
		
		for(EObject child : desc.eContents()){
			if(child instanceof Descriptor){
				if(isDescriptorReferencedElsewhere(rootDesc, (Descriptor)child)){
					return true;
				}
			}
		}
		
		return false;
	}
}
