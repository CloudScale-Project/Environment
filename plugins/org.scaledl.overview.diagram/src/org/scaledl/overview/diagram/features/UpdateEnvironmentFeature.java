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
package org.scaledl.overview.diagram.features;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.ProvidedSoftwareService;
import org.scaledl.overview.architecture.Service;
import org.scaledl.overview.architecture.SoftwareService;

public class UpdateEnvironmentFeature extends AbstractUpdateFeature {

	public UpdateEnvironmentFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canUpdate(IUpdateContext context) {
		// return true, if linked business object is an EClass
		Object bo = getBusinessObjectForPictogramElement(context.getPictogramElement());
		return (bo instanceof CloudEnvironment);
	}

	public IReason updateNeeded(IUpdateContext context) {

		if (updateNeededName(context)) {
			return Reason.createTrueReason("Name is out of date"); //$NON-NLS-1$
		} 
		else if (updateNeededServices(context))
		{
			return Reason.createTrueReason("Services are out of date"); //$NON-NLS-1$
		}
		else {
			return Reason.createFalseReason(); }
	}

	public boolean update(IUpdateContext context) {
		// retrieve name from business model
		
		getNameText(context).setValue(getEnvironment(context).getName());
		updateServices(context);
		
		// Update context
		UpdateContext uc = new UpdateContext(getDiagram());
		IUpdateFeature updateFeature = getFeatureProvider().getUpdateFeature(uc);
		if (updateFeature.canExecute(uc))
			updateFeature.execute(uc);
		
		return true;
	}
	
	private void updateServices (IUpdateContext context)
	{
		// Remove deleted platform services
		Map<Service, Shape> psMap = getServiceContainers(context);

		List<Service> services = getServices(context);
		Set<Service> servicesDiagram = psMap.keySet();
		
		for (Service ps : servicesDiagram)
		{
			if (!services.contains(ps))
			{
				// delete pictogram
			}
		}
		
		for (Service s : services)
		{
			if (!servicesDiagram.contains(s))
			{
				addService(context, s);
			}
		}
	}
	
	
	private void addService (IUpdateContext context, Service ps)
	{
		AddContext ac = new AddContext();
		ac.setLocation(10 + (int)(Math.random()*50), 10 + (int)(Math.random()*50));
		ac.setSize(100, 100);
		ac.setNewObject(ps);
		ac.setTargetContainer((ContainerShape) context.getPictogramElement());
		IAddFeature addFeature = getFeatureProvider().getAddFeature(ac);
		
		if (addFeature.canExecute(ac))
			addFeature.execute(ac);
	}
	
	private Text getNameText (IUpdateContext context)
	{
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof ContainerShape) {
			ContainerShape cs = (ContainerShape) pictogramElement;
			for (Shape shape : cs.getChildren()) {
				if (shape.getGraphicsAlgorithm() instanceof Text) {
					return (Text) shape.getGraphicsAlgorithm();
				}
			}
		}
		
		return null;
	}
	
	private Map<Service, Shape> getServiceContainers (IUpdateContext context)
	{
		PictogramElement pictogramElement = context.getPictogramElement();
		ContainerShape cs = (ContainerShape) pictogramElement;
		
		HashMap<Service, Shape> psMap = new HashMap<Service, Shape>();

		for (Shape shape : cs.getChildren()) {
			if (shape.getLink() != null)
			{
				EList<EObject> businessObjects = shape.getLink().getBusinessObjects();
				
				if (!businessObjects.isEmpty())
				{
					if (businessObjects.get(0) instanceof Service)
					{
						Service ps = (Service) businessObjects.get(0);
						psMap.put(ps, shape);
					}
				}
			}
		}
		
		return psMap;
	}
	
	private List<Service> getServices (IUpdateContext context)
	{
		LinkedList<Service> list = new LinkedList<Service>();
		
		CloudEnvironment ce = getEnvironment(context);

		list.addAll(ce.getPlatformLayer().getServices());
		
		for (SoftwareService ss : ce.getSoftwareLayer().getServices())
		{
			if (ss instanceof ProvidedSoftwareService)
				list.add(ss);
		}
		
		return list;
	}
	
	private CloudEnvironment getEnvironment (IUpdateContext context)
	{
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		
		if (bo instanceof CloudEnvironment)
			return (CloudEnvironment)bo;
		else
			throw new IllegalStateException();
		
	}
	
	private boolean updateNeededServices (IUpdateContext context)
	{
		Map<Service, Shape> psMap = getServiceContainers(context);

		List<Service> services = getServices(context);
		Set<Service> servicesDiagram = psMap.keySet();
		
		for (Service ps : servicesDiagram)
		{
			if (!services.contains(ps))
			{
				return true;
			}
		}
		
		for (Service s : services)
		{
			if (!servicesDiagram.contains(s))
			{
				return true;
			}
		}
		
		return false;
	}
	
	private boolean updateNeededName (IUpdateContext context)
	{
		Text text = getNameText(context);
		CloudEnvironment ce = getEnvironment(context);

		// update needed, if names are different
		boolean updateNameNeeded = ((text.getValue() == null && ce.getName() != null) || (text.getValue() != null && !text.getValue()
				.equals(ce.getName())));
		
		return updateNameNeeded;
	}
	
}
