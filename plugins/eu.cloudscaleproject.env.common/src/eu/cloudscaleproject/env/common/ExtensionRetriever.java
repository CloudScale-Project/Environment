package eu.cloudscaleproject.env.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Singleton;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.di.annotations.Creatable;

@Creatable
@Singleton
public class ExtensionRetriever {
	
	private static final Logger logger = Logger.getLogger(ExtensionRetriever.class.getName());

	@SuppressWarnings("unchecked")
	public <T extends Object> List<T> retrieveExtensionObjects(String extensionPointId, String attrName,  Class<T> clazz) {

		logger.info("Retrieving extensions for: " + extensionPointId);

		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] config = registry.getConfigurationElementsFor(extensionPointId);
		
		final HashMap<T, Integer> mapPositions = new HashMap<T, Integer>();
		List<T> out = new ArrayList<T>();
		
		try {
			for (IConfigurationElement e : config) {
				final Object o = e.createExecutableExtension(attrName);
								
				if(clazz.isAssignableFrom(o.getClass())){
					out.add((T)o);
					try
					{
						String pos = e.getAttribute("position");
						mapPositions.put((T)o, Integer.parseInt(pos));
					}
					catch (Exception ex)
					{
						mapPositions.put((T)o, Integer.MAX_VALUE);
					}
				}
			}
		} catch (CoreException ex) {
			logger.severe(ex.getMessage());
			return out;
		}
		
		Collections.sort(out, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                    return mapPositions.get(o1).compareTo(mapPositions.get(o2));
            }
		});
		
		return out;
	}
	
	public Object retrieveExtensionObject(String extensionPointId, String extensionId, String attrName){
	
		logger.info("Retrieving extension: " + extensionPointId + " with id: " + extensionId);
		
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] config = registry.getConfigurationElementsFor(extensionPointId);

	
		Object out = null;
		try {
			for (IConfigurationElement e : config) {
                // Workaround (for extractor - simpleIdentifier)
				// I don't know why only extractor ext. id is composed from namespace and extension id
				// which should be normal practice regarding the getUniqueIdentifier documentation
				// Analyser and Spotter (defined same as Extractor) extension are working ok
				if(extensionId.equals(e.getDeclaringExtension().getUniqueIdentifier()) ||
						extensionId.equals(e.getDeclaringExtension().getSimpleIdentifier())){ 
					out = e.createExecutableExtension(attrName);
				}
			}
		} catch (CoreException ex) {
			logger.severe(ex.getMessage());
			return out;
		}
		
		if(out == null){
			logger.info("Retrieving extension: " + extensionPointId + " with id: " + extensionId + " Extension not found!");
		}
		
		return out;
	}

}
