package eu.cloudscaleproject.env.csm.properties.sections.filters;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

import eu.cloudscaleproject.env.csm.architecture.PlatformService;

public class SectionFilterPlatformService extends AbstractPropertySectionFilter {
	
	@Override
	protected boolean accept(PictogramElement pictogramElement) {
		EObject bo = Graphiti.getLinkService()
				.getBusinessObjectForLinkedPictogramElement(pictogramElement);
		if (bo instanceof PlatformService) {
			return true;
		}
		return false;
	}
}