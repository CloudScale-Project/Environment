package org.scaledl.overview;

import java.util.List;

import org.scaledl.overview.specification.Descriptor;


public interface ICloudProviderExtension {

	public List<Descriptor> getDescriptors();

	public String getProviderName();
}
