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
package eu.cloudscaleproject.env.csm.diagram.util;

import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * Styles are created here with "plain"-methods, i.e. all values have to be set
 * explicitly.
 * 
 * See chapters "Using Styles" and "Color Schemas" in tutorial for deatails.
 */
public class StyleUtil {

	private static final IColorConstant BASIC_TEXT_FOREGROUND = new ColorConstant(0, 0, 0);
	private static final IColorConstant CLOUDPROVIDER_FOREGROUND = new ColorConstant(98, 131, 167);
	
	private static final IColorConstant NODE_FOREGROUND = new ColorConstant(66,0,66);
	private static final IColorConstant SERVICE_FOREGROUND = new ColorConstant(66,66,0);

	public static Style getStyleForCommonValues(Diagram diagram) {
		final String styleId = "COMMON-VALUES"; //$NON-NLS-1$
		IGaService gaService = Graphiti.getGaService();

		// Is style already persisted?
		Style style = gaService.findStyle(diagram, styleId);

		if (style == null) { // style not found - create new style
			style = gaService.createPlainStyle(diagram, styleId);
			setCommonValues(style);
		}
		return style;
	}

	public static Style getStyleForService(Diagram diagram) {
		
		final String styleId = "SERVICE";
		IGaService gaService = Graphiti.getGaService();

		// this is a child style of the common-values-style
		Style parentStyle = getStyleForCommonValues(diagram);
		Style style = gaService.findStyle(parentStyle, styleId);

		if (style == null) { // style not found - create new style
			style = gaService.createPlainStyle(parentStyle, styleId);
			style.setFilled(true);
			style.setForeground(gaService.manageColor(diagram, SERVICE_FOREGROUND));
			gaService.setRenderingStyle(style, ServiceColoredAreas.getServiceAdaptions());
		}
		return style;
	}
	
	public static Style getStyleForNode(Diagram diagram) {
		
		final String styleId = "NODE";
		IGaService gaService = Graphiti.getGaService();

		// this is a child style of the common-values-style
		Style parentStyle = getStyleForCommonValues(diagram);
		Style style = gaService.findStyle(parentStyle, styleId);

		if (style == null) { // style not found - create new style
			style = gaService.createPlainStyle(parentStyle, styleId);
			style.setFilled(true);
			style.setForeground(gaService.manageColor(diagram, NODE_FOREGROUND));
			gaService.setRenderingStyle(style, NodeColoredAreas.getNodeAdaptions());
		}
		return style;
	}
	
	public static Style getStyleForCloudProvider(Diagram diagram) {
		final String styleId = "CLOUDPROVIDER"; //$NON-NLS-1$
		IGaService gaService = Graphiti.getGaService();

		// this is a child style of the common-values-style
		Style parentStyle = getStyleForCommonValues(diagram);
		Style style = gaService.findStyle(parentStyle, styleId);

		if (style == null) { // style not found - create new style
			style = gaService.createPlainStyle(parentStyle, styleId);
			style.setFilled(true);
			style.setForeground(gaService.manageColor(diagram, CLOUDPROVIDER_FOREGROUND));

			gaService.setRenderingStyle(style, CloudProviderColoredAreas.getCloudProviderAdaptions());
		}
		return style;
	}
	
	public static Style getStyleForConnector (Diagram diagram) {
		final String styleId = "CONNECTOR"; //$NON-NLS-1$
		IGaService gaService = Graphiti.getGaService();

		// this is a child style of the common-values-style
		Style parentStyle = getStyleForCommonValues(diagram);
		Style style = gaService.findStyle(parentStyle, styleId);

		if (style == null) { // style not found - create new style
			style = gaService.createPlainStyle(parentStyle, styleId);
			style.setFilled(true);
			style.setForeground(gaService.manageColor(diagram, CLOUDPROVIDER_FOREGROUND));

			gaService.setRenderingStyle(style, CloudProviderColoredAreas.getLightYellowAdaptions());
		}
		return style;
	}

	public static Style getStyleForCloudProviderText(Diagram diagram) {
		final String styleId = "CLOUDPROVIDER-TEXT"; //$NON-NLS-1$
		IGaService gaService = Graphiti.getGaService();

		// this is a child style of the common-values-style
		Style parentStyle = getStyleForCommonValues(diagram);
		Style style = gaService.findStyle(parentStyle, styleId);

		if (style == null) { // style not found - create new style
			style = gaService.createPlainStyle(parentStyle, styleId);
			setCommonTextValues(diagram, gaService, style);
			style.setFont(gaService.manageDefaultFont(diagram, false, true));
		}
		return style;
	}
	
	public static Style getStyleForComponentText(Diagram diagram) {
		final String styleId = "COMPONENT-TEXT"; //$NON-NLS-1$
		IGaService gaService = Graphiti.getGaService();

		// this is a child style of the common-values-style
		Style parentStyle = getStyleForCommonValues(diagram);
		Style style = gaService.findStyle(parentStyle, styleId);

		if (style == null) { // style not found - create new style
			style = gaService.createPlainStyle(parentStyle, styleId);
			setCommonTextValues(diagram, gaService, style);
			style.setFont(gaService.manageDefaultFont(diagram, false, true));
		}
		return style;
	}

	public static Style getStyleForTextDecorator(Diagram diagram) {
		final String styleId = "TEXT-DECORATOR-TEXT"; //$NON-NLS-1$
		IGaService gaService = Graphiti.getGaService();

		// this is a child style of the common-values-style
		Style parentStyle = getStyleForCommonValues(diagram);
		Style style = gaService.findStyle(parentStyle, styleId);

		if (style == null) { // style not found - create new style
			style = gaService.createPlainStyle(parentStyle, styleId);
			setCommonTextValues(diagram, gaService, style);
			style.setFont(gaService.manageDefaultFont(diagram, true, false));
		}
		return style;
	}

	private static void setCommonTextValues(Diagram diagram, IGaService gaService, Style style) {
		style.setFilled(false);
		style.setAngle(0);
		style.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		style.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
		style.setForeground(gaService.manageColor(diagram, BASIC_TEXT_FOREGROUND));
	}

	private static void setCommonValues(Style style) {
		style.setLineStyle(LineStyle.SOLID);
		style.setLineVisible(true);
		style.setLineWidth(2);
		style.setTransparency(0.0);
	}
}
