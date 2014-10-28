package org.scaledl.overview.properties.sections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.scaledl.overview.core.Entity;
import org.scaledl.overview.diagram.util.OverviewDiagramUtil;
import org.scaledl.overview.properties.HighlightDecorationProvider;
import org.scaledl.overview.properties.components.CsmTreeViewEditor;

public class TreeViewSection extends GFPropertySection implements ITabbedPropertyConstants{

	private CsmTreeViewEditor csmTreeEditor;
	private HighlightDecorationProvider highlightProvider;
	
	public static class SectionFilter extends AbstractPropertySectionFilter{
		@Override
		protected boolean accept(PictogramElement pictogramElement) {
			EObject bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pictogramElement);
			if (bo instanceof Entity) {
				return true;
			}
			return false;
		}
	}
	
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		
		TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
		final Composite composite = factory.createFlatFormComposite(parent);
		FillLayout compositeLayout = new FillLayout(SWT.VERTICAL | SWT.HORIZONTAL);
	    compositeLayout.spacing = 3;
	    composite.setLayout(compositeLayout);
		
	    this.csmTreeEditor = new CsmTreeViewEditor(composite, SWT.NONE);
	    
	    if(highlightProvider != null){
	    	highlightProvider.dispose();
	    }
	    highlightProvider = new HighlightDecorationProvider(this.csmTreeEditor.treeViewer);
	}
	
	@Override
	public void refresh() {
		super.refresh();
		
		Object bo = OverviewDiagramUtil.getBusinessObject(getSelectedPictogramElement());
		
		if(bo != null && bo instanceof EObject){
			EObject input = (EObject)bo;
			this.csmTreeEditor.setInput(input);
	    	this.csmTreeEditor.redraw();
	    	this.highlightProvider.setDiagram(getDiagram(), getDiagramContainer().getDiagramBehavior());
		}
	}
	
	@Override
	public void aboutToBeHidden() {
		super.aboutToBeHidden();
		if(highlightProvider != null){
			highlightProvider.clear();
		}
	}
	
	@Override
	public void dispose() {
		super.dispose();
		if(highlightProvider != null){
			highlightProvider.dispose();
		}
	}

	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}
}
