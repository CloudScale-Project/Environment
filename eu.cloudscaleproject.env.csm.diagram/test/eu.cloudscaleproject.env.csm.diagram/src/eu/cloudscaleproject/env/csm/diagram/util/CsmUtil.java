/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package eu.cloudscaleproject.env.csm.diagram.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.DefinitionProviderService;
import eu.cloudscaleproject.env.csm.architecture.ApplicationServiceContainer;
import eu.cloudscaleproject.env.csm.architecture.Architecture;
import eu.cloudscaleproject.env.csm.architecture.ArchitectureFactory;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.DeployableApplicationService;
import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.definition.CloudDefinition;
import eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.DefinitionFactory;
import eu.cloudscaleproject.env.csm.definition.Descriptor;
import eu.cloudscaleproject.env.csm.definition.InfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.SystemDefinition;
import eu.cloudscaleproject.env.csm.impl.CsmFactoryImpl;

public class CsmUtil {

	public static void saveToModelFile(final EObject obj, final Diagram d) throws CoreException, IOException {
		URI uri = d.eResource().getURI();
		uri = uri.trimFragment();
		uri = uri.trimFileExtension();
		uri = uri.appendFileExtension("csm"); //$NON-NLS-1$
		
		ResourceSet rSet = d.eResource().getResourceSet();
		final IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IResource file = workspaceRoot.findMember(uri.toPlatformString(true));
		if (file == null || !file.exists()) {
			Resource createResource = rSet.createResource(uri);
			Csm csm = CsmFactoryImpl.eINSTANCE.createCsm();
			Architecture architecture = ArchitectureFactoryImpl.eINSTANCE.createArchitecture();
			SystemDefinition definition = DefinitionFactory.eINSTANCE.createSystemDefinition();
			
			csm.setArchitecture(architecture);
			csm.setDefinition(definition);
			
			createResource.getContents().add(csm);
			createResource.save(null);
			// createResource.setTrackingModification(true);
		}
		
		final Resource resource = rSet.getResource(uri, true);
		Csm csm = (Csm)resource.getContents().get(0);
		csm.getArchitecture().getCloudProviders().add((CloudEnvironment)obj);
		resource.save(null);
	}
	
	public static Csm getCsmModel (final Diagram d)
	{
		URI uri = d.eResource().getURI();
		uri = uri.trimFragment();
		uri = uri.trimFileExtension();
		uri = uri.appendFileExtension("csm"); 
		
		ResourceSet rSet = d.eResource().getResourceSet();
		final IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IResource file = workspaceRoot.findMember(uri.toPlatformString(true));
		
		// TODO : File creation should happen when diagram is created
		if (file == null || !file.exists()) {
			Resource createResource = rSet.createResource(uri);
			Csm csm = CsmFactoryImpl.eINSTANCE.createCsm();
			Architecture architecture = ArchitectureFactoryImpl.eINSTANCE.createArchitecture();
			SystemDefinition definition = DefinitionFactory.eINSTANCE.createSystemDefinition();
			
			csm.setArchitecture(architecture);
			csm.setDefinition(definition);
			
			createResource.getContents().add(csm);
			try
			{
				createResource.save(null);
			}
			catch (Exception e)
			{
				// FIXME : refactor
				throw new RuntimeException("Unable to save model! :: "+e.getMessage());
			}
		}
		
		final Resource resource = rSet.getResource(uri, true);
		Csm csm = (Csm)resource.getContents().get(0);
		
		return csm;
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
			cisd = CsmUtil.getSystemDescriptor(csm, cisdSet.get(0));
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
		
		/*for (Instance instance : csm.getDeployment().getInstances().toArray(new Instance[0])) {
			if (isOrphaned(instance))
			{
				csm.getDefinition().getDescriptors().remove(instance);
			}
		}*/
	}
	
	public static boolean isOrphaned (Entity entity)
	{
		return EcoreUtil.UsageCrossReferencer.find(entity, entity.eResource()).isEmpty();
	}
	
	
	public static HashSet<OperationInterface> collectProvidedInterfaces(PlatformService platformService){
		HashSet<OperationInterface> operationInterfaces = new HashSet<OperationInterface>();
		if(platformService instanceof OperationInterfaceContainer){
			OperationInterfaceContainer oic = (OperationInterfaceContainer)platformService;
			operationInterfaces.addAll(oic.getProvidedInterfaces());
		}
		else if(platformService instanceof ApplicationServiceContainer){
			ApplicationServiceContainer asc = (ApplicationServiceContainer)platformService;
			for(DeployableApplicationService as : asc.getApplicationServices()){
				operationInterfaces.addAll(as.getProvidedInterfaces());
			}
		}
		return operationInterfaces;
	}
	
	public static HashSet<OperationInterface> collectRequiredInterfaces(PlatformService platformService){
		HashSet<OperationInterface> operationInterfaces = new HashSet<OperationInterface>();
		if(platformService instanceof OperationInterfaceContainer){
			OperationInterfaceContainer oic = (OperationInterfaceContainer)platformService;
			operationInterfaces.addAll(oic.getRequiredInterfaces());
		}
		else if(platformService instanceof ApplicationServiceContainer){
			ApplicationServiceContainer asc = (ApplicationServiceContainer)platformService;
			for(DeployableApplicationService as : asc.getApplicationServices()){
				operationInterfaces.addAll(as.getRequiredInterfaces());
			}
		}
		return operationInterfaces;
	}
	
	public static void removeRequiredInterfaces(PlatformService platformService, 
												Collection<? extends OperationInterface> interfaces){
		if(platformService instanceof OperationInterfaceContainer){
			OperationInterfaceContainer oic = (OperationInterfaceContainer)platformService;
			oic.getRequiredInterfaces().removeAll(interfaces);
		}
		else if(platformService instanceof ApplicationServiceContainer){
			ApplicationServiceContainer asc = (ApplicationServiceContainer)platformService;
			for(DeployableApplicationService as : asc.getApplicationServices()){
				as.getRequiredInterfaces().removeAll(interfaces);
			}
		}
	}
	
	public static void removeProvidedInterfaces(PlatformService platformService, 
			Collection<? extends OperationInterface> interfaces){
		if(platformService instanceof OperationInterfaceContainer){
			OperationInterfaceContainer oic = (OperationInterfaceContainer)platformService;
			oic.getProvidedInterfaces().removeAll(interfaces);
		}
		else if(platformService instanceof ApplicationServiceContainer){
			ApplicationServiceContainer asc = (ApplicationServiceContainer)platformService;
			for(DeployableApplicationService as : asc.getApplicationServices()){
				as.getProvidedInterfaces().removeAll(interfaces);
			}
		}
	}
}
