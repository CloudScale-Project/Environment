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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.scaledl.overview.architecture.ServiceProxy;

public class UpdateServiceProxyFeature extends AbstractUpdateFeature {

	public UpdateServiceProxyFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canUpdate(IUpdateContext context) {
		// return true, if linked business object is an EClass
		Object bo = getBusinessObjectForPictogramElement(context.getPictogramElement());
		return (bo instanceof ServiceProxy);
	}

	public IReason updateNeeded(IUpdateContext context) {
		
		PictogramElement pictogramElement = context.getPictogramElement();
		Text t = getText(pictogramElement);
		ServiceProxy bo = (ServiceProxy) getBusinessObjectForPictogramElement(context.getPictogramElement());
		
		boolean updateNameNeeded = !(t == null || t.getValue().equals(bo.getName())); 
		if (updateNameNeeded) {
			return Reason.createTrueReason("Name is out of date"); //$NON-NLS-1$
		} else {
			return Reason.createFalseReason();
		}
	}

	public boolean update(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Text t = getText(pictogramElement);
		ServiceProxy bo = (ServiceProxy) getBusinessObjectForPictogramElement(context.getPictogramElement());
		
		t.setValue(bo.getName());
		
		return true;
	}
	
	private Text getText (PictogramElement e)
	{
		
		if (!(e instanceof ContainerShape)) return null;
		
		ContainerShape container = (ContainerShape) e;
		
		if (container.getGraphicsAlgorithm() instanceof RoundedRectangle)
		{
			RoundedRectangle rr =  (RoundedRectangle) container.getGraphicsAlgorithm();
			
			for (GraphicsAlgorithm child : rr.getGraphicsAlgorithmChildren()) {
				
				if (child instanceof Text)
				 return (Text) child;
			}
			
		}
		
		return null;
	}
}
