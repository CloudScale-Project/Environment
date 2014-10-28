package eu.cloudscaleproject.env.method.editor.diagram.properties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

import eu.cloudscaleproject.env.method.common.method.Method;

public class EditSectionFilter extends AbstractPropertySectionFilter {

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
