package eu.cloudscaleproject.env.csm;

import java.util.List;

import eu.cloudscaleproject.env.csm.definition.Descriptor;

public interface ICloudProviderExtension {

	public List<Descriptor> getDescriptors();

	public String getProviderName();
}
