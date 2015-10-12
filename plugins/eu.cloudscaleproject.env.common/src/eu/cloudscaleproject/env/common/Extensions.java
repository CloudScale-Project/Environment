package eu.cloudscaleproject.env.common;

import java.util.ArrayList;
import java.util.List;

import eu.cloudscaleproject.env.common.notification.IResourceValidator;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class Extensions {
	
	private static Extensions instance = null;
	public static Extensions getInstance(){
		if(instance == null){
			instance = new Extensions();
		}
		return instance;
	}
	
	private final ExtensionRetriever extensionRetriever = new ExtensionRetriever();
	
	private List<IResourceValidator> resourceValidators = new ArrayList<>();
	
	public void retrieveExtensions(){
		
		//retrieve validators
		this.resourceValidators = extensionRetriever.retrieveExtensionObjects(
						"eu.cloudscaleproject.env.common.notification.validator",
						"class", IResourceValidator.class);
	}
	
	public List<IResourceValidator> getResourceValidators(){
		return this.resourceValidators;
	}

}
