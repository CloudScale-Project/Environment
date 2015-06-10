package eu.cloudscaleproject.env.method.viewer.diagram.properties;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

import eu.cloudscaleproject.env.method.common.method.Method;

public class PropertySectionFilter extends AbstractPropertySectionFilter implements IExecutableExtensionFactory{

	@Override
	public Object create() throws CoreException {
		return new PropertySectionFilter();
	}
	
	@Override
	protected boolean accept(PictogramElement pictogramElement) {
		EObject bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pictogramElement);
		EObject root = EcoreUtil.getRootContainer(bo);
		
		if(root instanceof Method){
			return true;
		}
		return false;
	}

}
