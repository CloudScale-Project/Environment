package eu.cloudscaleproject.env.csm;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import eu.cloudscaleproject.env.csm.definition.CloudDefinition;
import eu.cloudscaleproject.env.csm.definition.Definition;

public class DefinitionProviderService {

	private static DefinitionProviderService instance = null;

	public static DefinitionProviderService getInstance() {
		if (instance == null) {
			instance = new DefinitionProviderService();
			init();
		}
		return instance;
	}

	private static void init() {
		IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(
						"eu.cloudscaleproject.env.csm.CsmDefinition");

		for (IConfigurationElement el : elements) {
			try {
				Object object = el.createExecutableExtension("class");
				if (object instanceof Definition) {
					//
					// instance.addCloudProvider((ICloudProviderExtension)object);
					System.out.println("Csm Definition : " + object.getClass());
					instance.addDefinition((Definition) object);
				}
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private List<Definition> definitions = new LinkedList<Definition>();
	private Map<String, CloudDefinition> mapCloudDefinitions = new HashMap<String, CloudDefinition>();

	private void addDefinition(Definition d) {
		definitions.add(d);
		if (d instanceof CloudDefinition) {
			CloudDefinition cd = (CloudDefinition) d;
			mapCloudDefinitions.put(cd.getDescriptor().getProviderID(), cd);
		}
	}

	public List<Definition> getDefinitions() {
		return Collections.unmodifiableList(definitions);
	}

	public CloudDefinition getCloudDefinition(String id) {
		return mapCloudDefinitions.get(id);
	}
}
