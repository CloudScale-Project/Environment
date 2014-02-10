package eu.cloudscaleproject.env.csm.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.core.Entity;

public class ConverterService {

	private static ConverterService instance = null;

	private final Map<IConfigurationElement, ICsmConverter> converters = new HashMap<IConfigurationElement, ICsmConverter>();

	public static ConverterService getInstance() {
		if (instance == null) {
			instance = new ConverterService();
			instance.retrieveConverters();
		}
		return instance;
	}

	public void convert(Csm csm) {
		this.convert(csm.eResource());
	}

	public void convert(Resource inResource) {
		retrieveConverters();

		if (inResource == null) {
			// TODO : log
			return;
		}

		for (ICsmConverter converter : converters.values()) {
			if (converter.canTransform(inResource)) {
				converter.transform(inResource);
			}
		}
	}

	public boolean addExternalModel(Entity object, List<EObject> external,
			ICSMConverterCallback callback) {
		if (object == null) {
			return false;
		}

		for (ICsmConverter converter : converters.values()) {
			if (converter.addExternalModel(object, external, callback)) {
				return true;
			}
		}
		return false;
	}

	public boolean removeExternalModel(Entity object, List<EObject> external,
			ICSMConverterCallback callback) {
		if (object == null) {
			return false;
		}

		for (ICsmConverter converter : converters.values()) {
			if (converter.removeExternalModel(object, external, callback)) {
				return true;
			}
		}
		return false;
	}

	private void retrieveConverters() {
		Map<IConfigurationElement, ICsmConverter> lastConverters = new HashMap<IConfigurationElement, ICsmConverter>();
		lastConverters.putAll(converters);
		converters.clear();

		IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(
						"eu.cloudscaleproject.env.csm.CsmConverter");
		for (IConfigurationElement el : elements) {
			try {
				if (!el.isValid()) {
					continue;
				}

				if (!lastConverters.containsKey(el)) {
					Object object = el.createExecutableExtension("class");
					if (object instanceof ICsmConverter) {
						converters.put(el, (ICsmConverter) object);
					}
				} else {
					converters.put(el, lastConverters.get(el));
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

}
