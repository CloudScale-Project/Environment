package eu.cloudscaleproject.env.toolchain.explorer;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerUtils {
	
	public static ExplorerResourceNode getProjectNode(IExplorerNode node){
		while(node != null){
			if(node instanceof ExplorerResourceNode){
				ExplorerResourceNode resNode = (ExplorerResourceNode)node;
				if(resNode.getResource() instanceof IProject){
					return (ExplorerResourceNode)node;
				}
			}
			node = node.getParent();
		}
		return null;
	}
	
	public static Image createImage(IConfigurationElement element, String attribute){
		
		String iconPath = element.getAttribute(attribute);
		
		Image image = null;
		if(iconPath != null && !iconPath.isEmpty()){
			
			Bundle bundle = Platform.getBundle(element.getContributor().getName());
			URL url = bundle.getResource(iconPath);
			
			try {
				image = new Image(Display.getDefault(), url.openStream());
				return image;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
