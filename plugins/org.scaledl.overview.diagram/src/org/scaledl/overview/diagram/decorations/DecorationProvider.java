package org.scaledl.overview.diagram.decorations;

import java.util.Set;

import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.IDecorator;

public interface DecorationProvider{

	public Set<IDecorator> getDecorators(PictogramElement el);
	
}
