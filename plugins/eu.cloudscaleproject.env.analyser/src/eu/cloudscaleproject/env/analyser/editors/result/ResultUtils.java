package eu.cloudscaleproject.env.analyser.editors.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.palladiosimulator.edp2.datastream.IDataSource;
import org.palladiosimulator.edp2.datastream.chaindescription.ChainDescription;
import org.palladiosimulator.edp2.datastream.configurable.IPropertyConfigurable;
import org.palladiosimulator.edp2.datastream.filter.AbstractAdapter;
import org.palladiosimulator.edp2.visualization.AbstractVisualizationInput;

public class ResultUtils {

	private static final String DATASINK_EXTENSION_POINT_ID = "org.palladiosimulator.edp2.visualization.datasink";
	private static final String CHAIN_DESCRIPTION_EXTENSION_POINT_ID = "org.palladiosimulator.edp2.datastream.chainDescription";

	/**
	 * Attribute names and element IDs as used in extension points.
	 */
	private static final String ELEMENT_ID_DATASINK = "datasink";
	private static final String ELEMENT_ID_FILTER = "filter";
	private static final String ELEMENT_ID_PROPERTY = "property";
	private static final String PROPERTY_KEY_ATTRIBUTE = "key";
	private static final String PROPERTY_VALUE_ATTRIBUTE = "value";
	private static final String DATASINK_ID_ATTRIBUTE = "sinkID";
	private static final String FILTER_CLASS_ATTRIBUTE = "filterClass";

	private static final String CLASS_ATTRIBUTE = "class";
	private static final String ID_ATTRIBUTE = "id";
	private static final String NAME_ATTRIBUTE = "name";

	public static List<ChainDescription> getApplicableChainDescriptionsFromExtensions(IDataSource dataSource) {
		List<ChainDescription> result = new ArrayList<ChainDescription>();

		Map<String, AbstractVisualizationInput<?>> charts = getRegisteredVisualizations();
		IConfigurationElement[] chainDescriptionExtensions = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						CHAIN_DESCRIPTION_EXTENSION_POINT_ID);

		for (IConfigurationElement e : chainDescriptionExtensions) {

			IPropertyConfigurable visualization = null;
			IDataSource lastDataSource = null;
			boolean isApplicable = true;
			for (IConfigurationElement child : e.getChildren()) {
				IPropertyConfigurable configurable = createAndConfigureChainElement(
						charts, child);
				if (child.getName().equals(ELEMENT_ID_DATASINK)) {
					visualization = configurable;
				} else if (configurable instanceof AbstractAdapter) { // at
																		// least
																		// one
																		// filter
																		// in
																		// chain
					AbstractAdapter adapter = (AbstractAdapter) configurable;
					if (!dataSource.isCompatibleWith(adapter
							.getMetricDesciption())) {
						isApplicable = false; // filter is not applicable to
												// selected data source
						break;
					}
					if (lastDataSource != null) {
						adapter.setDataSource(lastDataSource);
					}
					lastDataSource = adapter;
				}
			}
			if (isApplicable) { // only if (optional) filters are applicable to
								// data source
				final ChainDescription newChainDescription = new ChainDescription(
						e.getAttribute(ID_ATTRIBUTE),
						e.getAttribute(NAME_ATTRIBUTE), lastDataSource,
						visualization);
				result.add(newChainDescription);
			}
		}
		return result;
	}

	private static IPropertyConfigurable createAndConfigureChainElement(
			final Map<String, AbstractVisualizationInput<?>> charts,
			final IConfigurationElement element) {
		final IPropertyConfigurable configurable = createChainElement(charts,
				element);
		final Map<String, Object> elementProperties = new HashMap<String, Object>(
				configurable.getProperties());
		for (final IConfigurationElement property : element
				.getChildren(ELEMENT_ID_PROPERTY)) {
			elementProperties.put(
					property.getAttribute(PROPERTY_KEY_ATTRIBUTE),
					property.getAttribute(PROPERTY_VALUE_ATTRIBUTE));
		}
		configurable.setProperties(elementProperties);
		return configurable;
	}

	private static IPropertyConfigurable createChainElement(
			final Map<String, AbstractVisualizationInput<?>> charts,
			final IConfigurationElement element) {
		try {
			if (element.getName().equals(ELEMENT_ID_DATASINK)) {
				return charts.get(element.getAttribute(DATASINK_ID_ATTRIBUTE));
			} else if (element.getName().equals(ELEMENT_ID_FILTER)) {
				return (IPropertyConfigurable) element
						.createExecutableExtension(FILTER_CLASS_ATTRIBUTE);
			}
		} catch (final CoreException e1) {
			throw new RuntimeException(e1.getMessage());
		}
		throw new IllegalArgumentException(
				"Configuration element found which is not supported");
	}

	private static Map<String, AbstractVisualizationInput<?>> getRegisteredVisualizations() {
		Map<String, AbstractVisualizationInput<?>> result = new HashMap<String, AbstractVisualizationInput<?>>();
		IConfigurationElement[] visualizationExtensions = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						DATASINK_EXTENSION_POINT_ID);
		for (IConfigurationElement e : visualizationExtensions) {
			try {
				String id = e.getAttribute(ID_ATTRIBUTE);
				AbstractVisualizationInput<?> visualization = (AbstractVisualizationInput<?>) e
						.createExecutableExtension(CLASS_ATTRIBUTE);
				result.put(id, visualization);
			} catch (CoreException e1) {
				throw new RuntimeException();
			}
		}
		return result;
	}
}
