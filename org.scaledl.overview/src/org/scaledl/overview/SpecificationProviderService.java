package org.scaledl.overview;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.osgi.framework.Bundle;
import org.scaledl.overview.specification.CloudSpecification;
import org.scaledl.overview.specification.Specification;

public class SpecificationProviderService {

	private static SpecificationProviderService instance = null;

	public static SpecificationProviderService getInstance() {
		if (instance == null) {
			instance = new SpecificationProviderService();
			init();
		}
		return instance;
	}
	
	private static void init() {
		IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(
						"org.scaledl.overview.Specification");

		for (IConfigurationElement el : elements) {
			try {
				IExtension declaringExtension = el.getDeclaringExtension();
				IContributor contributor = declaringExtension.getContributor();
				Bundle bundle = Platform.getBundle(contributor.getName());

				// Specification model URL -- this is correct way to get URI in product
				URL fileURL = bundle.getEntry(el.getAttribute("model"));
				URL resolvedFileURL = FileLocator.toFileURL(fileURL);
				URI resolvedURI = new URI (resolvedFileURL.getProtocol(), resolvedFileURL.getPath(), null);
				File file = new File(resolvedURI);

				// Load specification resource and add specification to registry
				XMIResourceImpl resource = new XMIResourceImpl();
				//File file = new File(FileLocator.resolve(fileURL).toURI());
				resource.load(new FileInputStream(file), new HashMap<Object,Object>());
				Specification specification = (Specification)resource.getContents().get(0);  
				instance.addSpecification(specification);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private List<Specification> definitions = new LinkedList<Specification>();
	private Map<String, CloudSpecification> mapCloudDefinitions = new HashMap<String, CloudSpecification>();

	private void addSpecification(Specification d) {
		definitions.add(d);
		if (d instanceof CloudSpecification) {
			CloudSpecification cd = (CloudSpecification) d;
			mapCloudDefinitions.put(cd.getDescriptor().getProviderID(), cd);
		}
	}

	public List<Specification> getDefinitions() {
		return Collections.unmodifiableList(definitions);
	}
	
	public List<CloudSpecification> getCloudSpecifications()
	{
		return Collections.unmodifiableList(new LinkedList<CloudSpecification>(mapCloudDefinitions.values()));
	}
	

	public CloudSpecification getCloudDefinition(String id) {
		return mapCloudDefinitions.get(id);
	}
}
