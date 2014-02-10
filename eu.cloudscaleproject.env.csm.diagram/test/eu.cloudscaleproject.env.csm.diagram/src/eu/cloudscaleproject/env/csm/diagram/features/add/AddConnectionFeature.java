/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2011 SAP AG.
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
package eu.cloudscaleproject.env.csm.diagram.features.add;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

import eu.cloudscaleproject.env.csm.architecture.ExternalConnection;
import eu.cloudscaleproject.env.csm.architecture.HybridConnection;
import eu.cloudscaleproject.env.csm.diagram.util.StyleUtil;


public class AddConnectionFeature extends AbstractAddFeature {

	public AddConnectionFeature(IFeatureProvider fp) {
		super(fp);
	}

	public PictogramElement add(IAddContext context) {
		IAddConnectionContext addConContext = (IAddConnectionContext) context;
		eu.cloudscaleproject.env.csm.architecture.Connection addedConnection = 
				(eu.cloudscaleproject.env.csm.architecture.Connection) context.getNewObject();
		
		IPeCreateService peCreateService = Graphiti.getPeCreateService();

		// CONNECTION WITH POLYLINE
		Connection connection = peCreateService.createFreeFormConnection(getDiagram());
		connection.setStart(addConContext.getSourceAnchor());
		connection.setEnd(addConContext.getTargetAnchor());

		IGaService gaService = Graphiti.getGaService();
		Polyline polyline = gaService.createPlainPolyline(connection);
		polyline.setStyle(StyleUtil.getStyleForCloudProvider(getDiagram()));
		
		if (addedConnection instanceof HybridConnection)
		{
			polyline.setLineStyle(LineStyle.DASH);
		}
		else if (addedConnection instanceof ExternalConnection)
		{
			polyline.setLineStyle(LineStyle.DASHDOTDOT);
		}
			

		// create link and wire it
		link(connection, addedConnection);

		// add dynamic text decorator for the reference name
		ConnectionDecorator textDecorator = peCreateService.createConnectionDecorator(connection, true, 0.5, true);
		Text text = gaService.createPlainText(textDecorator);
		text.setStyle(StyleUtil.getStyleForTextDecorator((getDiagram())));
		gaService.setLocation(text, 10, 0);

		// set reference name in the text decorator
		text.setValue(addedConnection.getName() == null ? "" : addedConnection.getName());

		// add static graphical decorators (composition and navigable)
		ConnectionDecorator cd;
		cd = peCreateService.createConnectionDecorator(connection, false, 1.0, true);
		createArrow(cd);
		return connection;
	}

	public boolean canAdd(IAddContext context) {
		// return true if given business object is an EReference
		// note, that the context must be an instance of IAddConnectionContext
		if (context instanceof IAddConnectionContext && context.getNewObject() instanceof eu.cloudscaleproject.env.csm.architecture.Connection) 
		{
			return true;
		}
		return false;
	}

	private Polyline createArrow(GraphicsAlgorithmContainer gaContainer) {
		Polyline polyline = Graphiti.getGaCreateService().createPlainPolyline(gaContainer,
				new int[] { -10, 8, 0, 0,0,-2,  -10, -8 });
		polyline.setStyle(StyleUtil.getStyleForCloudProvider(getDiagram()));
		return polyline;
	}
}
