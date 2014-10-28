package org.scaledl.overview.diagram.editor.system;

import org.scaledl.overview.diagram.util.ModelObject;

import org.scaledl.overview.architecture.CloudEnvironment;

public class CloudProviderModel extends ModelObject{

	private CloudEnvironment cloudEnvironment;
	
	public CloudEnvironment getCloudProvider() {
		return cloudEnvironment;
	}
	
	public void setCloudProvider(CloudEnvironment cloudProvider) {
		this.firePropertyChange("cloudProvider", this.cloudEnvironment, this.cloudEnvironment = cloudProvider);
	}
	
}
