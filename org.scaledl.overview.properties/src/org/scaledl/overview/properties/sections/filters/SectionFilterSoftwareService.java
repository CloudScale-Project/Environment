package org.scaledl.overview.properties.sections.filters;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;
import org.scaledl.overview.architecture.SoftwareService;

public class SectionFilterSoftwareService extends AbstractPropertySectionFilter {
	
	@Override
	protected boolean accept(PictogramElement pictogramElement) {
		EObject bo = Graphiti.getLinkService()
				.getBusinessObjectForLinkedPictogramElement(pictogramElement);
		if (bo instanceof SoftwareService) {
			return true;
		}
		return false;
	}
}