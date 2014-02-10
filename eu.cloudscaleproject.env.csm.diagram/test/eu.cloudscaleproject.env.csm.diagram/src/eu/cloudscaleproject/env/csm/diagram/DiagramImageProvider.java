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
package eu.cloudscaleproject.env.csm.diagram;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class DiagramImageProvider extends AbstractImageProvider {

	// The prefix for all identifiers of this image provider
	protected static final String PREFIX = "eu.cloudscaleproject.env.csm.diagram."; //$NON-NLS-1$

	// The image identifier for an EReference.
	public static final String IMG_EDITOR = PREFIX + "editor"; //$NON-NLS-1$

	@Override
	protected void addAvailableImages() {
		// register the path for each image identifier
		addImageFilePath(IMG_EDITOR, "icons/editor.gif"); //$NON-NLS-1$
	}
}
