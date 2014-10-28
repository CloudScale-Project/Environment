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
package org.scaledl.overview.diagram.features.layout;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.scaledl.overview.architecture.SoftwareServiceContainer;

public class LayoutTitleContainerFeature extends AbstractLayoutFeature {

	public LayoutTitleContainerFeature(IFeatureProvider fp) {
		super(fp);
	}
	

	public boolean canLayout(ILayoutContext context) {
		// return true, if pictogram element is linked to an EClass
		PictogramElement pe = context.getPictogramElement();
		if (!(pe instanceof ContainerShape))
			return false;
		EList<EObject> businessObjects = pe.getLink().getBusinessObjects();
		return businessObjects.size() == 1 && businessObjects.get(0) instanceof SoftwareServiceContainer;
	}

	public boolean layout(ILayoutContext context) {
		ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
		GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();
		ContainerShape internal = (ContainerShape) containerShape.getChildren().get(1);
		int numOfChilds = internal.getChildren().size();
		
		// Min size
		
		int minHeight = 50 + 10 + numOfChilds*(18+10);
		int width = (int) Math.max( minHeight*1.5, containerGa.getWidth());
		int height = Math.max( minHeight, containerGa.getHeight());
		
		Graphiti.getGaLayoutService().setSize(containerShape.getGraphicsAlgorithm(), width, height);
		
		int x = Math.max (containerGa.getX(), 5);
		int y = Math.max (containerGa.getY(), 5);
		Graphiti.getGaLayoutService().setLocation(containerShape.getGraphicsAlgorithm(), x,y);

		// TITLE  (10,10 h=20, w=W-20)
		
		// TODO
		Shape title = containerShape.getChildren().get(0);
		Graphiti.getGaLayoutService().setLocationAndSize(title.getGraphicsAlgorithm(), 10, 10, width-20, 20);
		
		//
		// CONTAINER - with grid layout of 1 column 
		//
		GraphicsAlgorithm rectInternal = internal.getGraphicsAlgorithm();
		
		
		Graphiti.getGaLayoutService().setLocation(rectInternal, 10, 40);
		Graphiti.getGaLayoutService().setSize(rectInternal, width-20, height-50);
		
		if (numOfChilds > 0)
		{
			int OFFSET = 10;
			int heightChild = (rectInternal.getHeight() - (1+numOfChilds)*OFFSET)/numOfChilds;
			heightChild = Math.max(18, heightChild);
			heightChild = Math.min(heightChild, 60);
			int widthChild = rectInternal.getWidth() - 2*OFFSET;
			
			int i =0;
			for (Shape shape : internal.getChildren()) {
				
				// +10 and +40 becaouse it is relative to first container - both are active
				Graphiti.getGaLayoutService().setLocationAndSize(shape.getGraphicsAlgorithm(), 
					OFFSET+10, i*(OFFSET+heightChild) + OFFSET+40, widthChild, heightChild);	
				
				++i;
			}
		}
		
		
		return true;
	}
}
