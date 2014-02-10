package eu.cloudscaleproject.env.csm.diagram.editor.system;

import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.diagram.util.ModelObject;

public class CloudProviderModel extends ModelObject{

	private CloudEnvironment cloudEnvironment;
	
	public CloudEnvironment getCloudProvider() {
		return cloudEnvironment;
	}
	
	public void setCloudProvider(CloudEnvironment cloudProvider) {
		this.firePropertyChange("cloudProvider", this.cloudEnvironment, this.cloudEnvironment = cloudProvider);
	}
	
}
