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
package org.scaledl.overview.diagram;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;
import org.scaledl.overview.core.Entity;
import org.scaledl.overview.specification.CloudEnvironmentDescriptor;

public class DiagramImageProvider extends AbstractImageProvider {

	// The prefix for all identifiers of this image provider
	protected static final String PREFIX = "org.scaledl.overview.diagram."; //$NON-NLS-1$

	// The image identifier for an EReference.
	public static final String IMG_FOLDER = PREFIX + "folder"; //$NON-NLS-1$
	public static final String IMG_EDITOR = PREFIX + "editor"; //$NON-NLS-1$
	public static final String IMG_USAGE_PROXY = PREFIX + "usageproxy"; //$NON-NLS-1$
	public static final String IMG_USAGE_PROXY_SMALL = PREFIX + "usageproxy_small"; //$NON-NLS-1$
	public static final String IMG_SERVICE_PROXY = PREFIX + "serviceproxy";
	public static final String IMG_SERVICE_PROXY_SMALL = PREFIX + "serviceproxy_small";
	
	public static final String IMG_CONNECTION = PREFIX + "connection"; //$NON-NLS-1$
	public static final String IMG_CLOUD_ENVIRONMENT = PREFIX + "ce"; //$NON-NLS-1$
	public static final String IMG_DEPLOYABLE_SERVICES = PREFIX + "deployableservices"; //$NON-NLS-1$
	
	public static final String IMG_AWS = PREFIX + "aws"; //$NON-NLS-1$
	public static final String IMG_SAP = PREFIX + "sap"; //$NON-NLS-1$
	public static final String IMG_OPENSTACK = PREFIX + "openstack"; //$NON-NLS-1$

	

	@Override
	protected void addAvailableImages() {
		// register the path for each image identifier
		addImageFilePath(IMG_FOLDER, "icons/explorer/folder_open.png"); //$NON-NLS-1$
		addImageFilePath(IMG_EDITOR, "icons/editor.gif"); //$NON-NLS-1$
		
		addImageFilePath(IMG_USAGE_PROXY, "icons/diagram/usage_proxy2-64.png"); //$NON-NLS-1$
		addImageFilePath(IMG_USAGE_PROXY_SMALL, "icons/diagram/usage_proxy2-16.png"); //$NON-NLS-1$
		
		addImageFilePath(IMG_SERVICE_PROXY, "icons/diagram/service_proxy2-64.png"); //$NON-NLS-1$
		addImageFilePath(IMG_SERVICE_PROXY_SMALL, "icons/diagram/service_proxy2-16.png"); //$NON-NLS-1$
		
		addImageFilePath(IMG_CONNECTION, "icons/diagram/connection.png"); //$NON-NLS-1$
		addImageFilePath(IMG_CLOUD_ENVIRONMENT, "icons/diagram/cloud_environment-16.png"); //$NON-NLS-1$
		addImageFilePath(IMG_DEPLOYABLE_SERVICES, "icons/diagram/deployable_services-16.png"); //$NON-NLS-1$
		addImageFilePath(IMG_AWS, "icons/diagram/aws-16.png"); //$NON-NLS-1$
		addImageFilePath(IMG_SAP, "icons/diagram/sap-16.png"); //$NON-NLS-1$
		addImageFilePath(IMG_OPENSTACK, "icons/diagram/openstack-16.png"); //$NON-NLS-1$
	}
	
	public static String getImage (Entity e)
	{
		// TODO: Retrieve icons from Cloud definition providers
		if (e instanceof CloudEnvironmentDescriptor)
		{
			CloudEnvironmentDescriptor ced = (CloudEnvironmentDescriptor) e;
			
			if (ced.getProviderID().equals("aws"))
			{
				return IMG_AWS;
			}
			
			if (ced.getProviderID().equals("openstack") || ced.getProviderID().equals("private"))
			{
				return IMG_OPENSTACK;
			}
			
			if (ced.getProviderID().equals("hana"))
			{
				return IMG_SAP;
			}
		}
		
		return null;
	}
	
	
}
