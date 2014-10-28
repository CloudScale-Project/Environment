package org.scaledl.overview.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.scaledl.overview.Overview;
import org.scaledl.overview.core.Entity;

public class ConverterService {

	private static ConverterService instance = null;

	private final Map<IConfigurationElement, IOverviewConverter> converters = new HashMap<IConfigurationElement, IOverviewConverter>();

	public static ConverterService getInstance() {
		if (instance == null) {
			instance = new ConverterService();
			instance.retrieveConverters();
		}
		return instance;
	}

	public void convert(Overview csm) {
		this.convert(csm.eResource());
	}

	public void convert(Resource inResource) {
		retrieveConverters();

		if (inResource == null) {
			// TODO : log
			return;
		}

		for (IOverviewConverter converter : converters.values()) {
			if (converter.canTransform(inResource)) {
				converter.transform(inResource);
			}
		}
	}

	public boolean addExternalModel(Entity object, List<EObject> external,
			IOverviewConverterCallback callback) {
		if (object == null) {
			return false;
		}

		for (IOverviewConverter converter : converters.values()) {
			if (converter.addExternalModel(object, external, callback)) {
				return true;
			}
		}
		return false;
	}

	public boolean removeExternalModel(Entity object, List<EObject> external,
			IOverviewConverterCallback callback) {
		if (object == null) {
			return false;
		}

		for (IOverviewConverter converter : converters.values()) {
			if (converter.removeExternalModel(object, external, callback)) {
				return true;
			}
		}
		return false;
	}

	private void retrieveConverters() {
		Map<IConfigurationElement, IOverviewConverter> lastConverters = new HashMap<IConfigurationElement, IOverviewConverter>();
		lastConverters.putAll(converters);
		converters.clear();

		IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(
						"org.scaledl.overview.OverviewConverter");
		for (IConfigurationElement el : elements) {
			try {
				if (!el.isValid()) {
					continue;
				}

				if (!lastConverters.containsKey(el)) {
					Object object = el.createExecutableExtension("class");
					if (object instanceof IOverviewConverter) {
						converters.put(el, (IOverviewConverter) object);
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
