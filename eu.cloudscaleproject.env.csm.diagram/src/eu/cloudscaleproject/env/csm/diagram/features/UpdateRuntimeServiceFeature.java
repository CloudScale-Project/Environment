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
package eu.cloudscaleproject.env.csm.diagram.features;

import java.util.LinkedList;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.util.ColorConstant;

import eu.cloudscaleproject.env.csm.architecture.SoftwareService;
import eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer;

public class UpdateRuntimeServiceFeature extends AbstractUpdateFeature {

	public UpdateRuntimeServiceFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canUpdate(IUpdateContext context) {
		// return true, if linked business object is an EClass
		Object bo = getBusinessObjectForPictogramElement(context.getPictogramElement());
		return (bo instanceof SoftwareServiceContainer);
	}

	public IReason updateNeeded(IUpdateContext context) {
		// retrieve name from pictogram model
		String pictogramName = null;
		PictogramElement pictogramElement = context.getPictogramElement();
		
		SoftwareServiceContainer runtimeService = (SoftwareServiceContainer) getBusinessObjectForPictogramElement(pictogramElement);
		ContainerShape cs = (ContainerShape) pictogramElement;
		
		String businessName = runtimeService.getName();
		pictogramName = getTitleText(cs).getValue();
		
		// update needed, if names are different
		boolean doUpdate = ((pictogramName == null && businessName != null) || (pictogramName != null && !pictogramName .equals(businessName)));
		
		if (!doUpdate)
		{
			// Check software services
			for (SoftwareService ss : runtimeService.getSoftwareServices()) {
				Shape s = getSoftwareServiceShape(cs, ss);
				
				if (s == null) {
					doUpdate = true;
					break;
				}
			}
			
			for (Shape s : getRuntimeContainerShape(cs).getChildren())
			{
				SoftwareService ss = (SoftwareService) s.getLink().getBusinessObjects().get(0);
				
				if (!runtimeService.getSoftwareServices().contains(ss))
				{
					doUpdate = true;
					break;
				}
			}
		}
		
		
		if (doUpdate) {
			return Reason.createTrueReason("Name is out of date"); //$NON-NLS-1$
		} else {
			return Reason.createFalseReason();
		}
	}

	public boolean update(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		
		// Update title
		SoftwareServiceContainer runtimeService = (SoftwareServiceContainer) getBusinessObjectForPictogramElement(pictogramElement);
		ContainerShape cs = (ContainerShape) pictogramElement;
		ContainerShape runtimeContainer = getRuntimeContainerShape(cs);
		getTitleText(cs).setValue(runtimeService.getName());
		
		boolean layoutAndRefresh = false;
		// Update SoftwareService shapes -- add and remove
		for (SoftwareService ss : runtimeService.getSoftwareServices()) {
			Shape s = getSoftwareServiceShape(cs, ss);
			
			if (s == null) {
				
				// TODO: duplication in AddRuntimeService
				
				Shape moduleNameShape = Graphiti.getPeService().createShape(runtimeContainer, true);
				Text moduleNameTxt = Graphiti.getGaService().createText(moduleNameShape);
				moduleNameTxt.setForeground(manageColor(new ColorConstant("111111")));
				moduleNameTxt.setValue(ss.getName());
				moduleNameTxt.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
				moduleNameTxt.setFilled(true);
				moduleNameTxt.setBackground(manageColor(new ColorConstant("FFFFFF")));
				moduleNameTxt.setTransparency(0.1);
				link(moduleNameShape, ss);
				layoutAndRefresh = true;
				continue;
			}
		}
		
		for (Shape s : new LinkedList<Shape>(runtimeContainer.getChildren()))
		{
			SoftwareService ss = (SoftwareService) s.getLink().getBusinessObjects().get(0);
			
			if (!runtimeService.getSoftwareServices().contains(ss))
			{
				// ?! Some references are left in the model, and save does not work with
				// only removing shape from children. s and ss must be deleted. Standard EMF stuff.
				// runtimeContainer.getChildren().remove(s);
				EcoreUtil.delete(s, true);
				EcoreUtil.delete(ss);
				
				//s.setLink(null);
				layoutAndRefresh = true;
			}
		}
		
		if (layoutAndRefresh)
		{
			layoutPictogramElement(pictogramElement);
			getDiagramEditor().refresh();
		}
		
		return true;
	}
	
	private Text getTitleText (ContainerShape cs)
	{
		// Find title 
		for (Shape shape : cs.getChildren()) {
			if (shape.getGraphicsAlgorithm() instanceof Text) {
				return (Text) shape.getGraphicsAlgorithm();
			}
		}
		
		return null;
	}
	
	private ContainerShape getRuntimeContainerShape (ContainerShape cs)
	{
		for (Shape shape : cs.getChildren())
		{
			if (shape instanceof ContainerShape) return (ContainerShape)shape;
		}
		
		return null;
	}
	
	private Shape getSoftwareServiceShape (ContainerShape cs, SoftwareService ss)
	{
		for (Shape s : getRuntimeContainerShape(cs).getChildren())
		{
			if (getBusinessObjectForPictogramElement(s) == ss)
			{
				return s;
			}
		}
		
		return null;
	}
}
