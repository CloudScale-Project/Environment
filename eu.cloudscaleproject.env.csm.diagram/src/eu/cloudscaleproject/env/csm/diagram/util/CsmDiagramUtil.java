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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.services.Graphiti;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.architecture.Architecture;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.definition.DefinitionFactory;
import eu.cloudscaleproject.env.csm.definition.SystemDefinition;
import eu.cloudscaleproject.env.csm.impl.CsmFactoryImpl;

public class CsmDiagramUtil {

	public static void saveToModelFile(final EObject obj, final Diagram d) throws CoreException, IOException {
		URI uri = d.eResource().getURI();
		uri = uri.trimFragment();
		uri = uri.trimFileExtension();
		uri = uri.appendFileExtension("sdlo"); //$NON-NLS-1$
		
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
		uri = uri.appendFileExtension("sdlo"); 
		
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
	
	
	public static Set<PictogramElement> getPictogramElementsFromBusinessObjects(Diagram diagram, Object businessObject){
		
		if(businessObject instanceof List<?>){
			return getPictogramElementsFromBusinessObjectsList(diagram, (List<?>)businessObject);
		}
		
		Set<PictogramElement> out = new HashSet<PictogramElement>();
		
		List<PictogramLink> links = diagram.getPictogramLinks();
		for(PictogramLink link : links){
			if(link.getBusinessObjects().contains(businessObject)){
				out.add(link.getPictogramElement());
			}
		}
		
		return out;
	}
	
	public static Set<PictogramElement> getPictogramElementsFromBusinessObjectsList(Diagram diagram, List<?> businessObjects){
		Set<PictogramElement> out = new HashSet<PictogramElement>();
		
		List<PictogramLink> links = diagram.getPictogramLinks();
		for(PictogramLink link : links){
			for(Object o : businessObjects){
				if(link.getBusinessObjects().contains(o)){
					out.add(link.getPictogramElement());
				}
			}
		}
		
		return out;
	}

	public static Set<PictogramElement> getPictogramElementsFromBusinessObjectsInv(Diagram diagram, List<?> businessObjects){
		Set<PictogramElement> out = new HashSet<PictogramElement>();
		
		List<PictogramLink> links = diagram.getPictogramLinks();
		for(PictogramLink link : links){
			boolean contains = false;
			for(Object o : businessObjects){
				if(link.getBusinessObjects().contains(o)){
					contains = true;
				}
			}
			if(!contains){
				out.add(link.getPictogramElement());
			}
		}
		
		return out;
	}
	
	public static EObject getBusinessObject(PictogramElement element){
		return Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(element);
	}
	
	public static CloudEnvironment getCloudEnvironment(PictogramElement element){
		EObject e = element;
		
		while(e != null && e instanceof PictogramElement){
			EObject b = getBusinessObject((PictogramElement)e);
			if(b instanceof CloudEnvironment){
				return (CloudEnvironment)b;
			}
			e = e.eContainer();
		}
		
		return null;
	}
}
