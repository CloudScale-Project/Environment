package org.scaledl.overview.properties.sections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.architecture.ServiceProxy;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.properties.components.ExtractorComposite;

public class ImportSection extends GFPropertySection implements ITabbedPropertyConstants {

	private Section sectionWizard = null;
	private ExtractorComposite extractorComposite;
	
	public static class SectionFilter extends AbstractPropertySectionFilter{
		@Override
		protected boolean accept(PictogramElement pictogramElement) {
			EObject bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pictogramElement);
			
			if (bo instanceof SoftwareService) {
				return true;
			}
			return false;
		}
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void createControls(final Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		
		TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
		
		Composite composite = factory.createFlatFormComposite(parent);
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.HORIZONTAL;
		composite.setLayout(fillLayout);
		
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());


	    // Extractor seciton
	    {
		    sectionWizard = toolkit.createSection(composite, Section.DESCRIPTION
		        | Section.TITLE_BAR);
		    sectionWizard.setText("Extracted models"); //$NON-NLS-1$
		    sectionWizard.setDescription("Location of extracted PCM models used for this component.");
		    // Composite for storing the data
		    extractorComposite = new ExtractorComposite(parent);
		    extractorComposite.setParent(sectionWizard);
		    toolkit.paintBordersFor(extractorComposite);
		    sectionWizard.setClient(extractorComposite);
	    }
		
		composite.pack();
	}

	@Override
	public void refresh() {
		PictogramElement pe = getSelectedPictogramElement();
		Object data = null;
		if (pe != null) {
			data = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
		}
		
		if(this.extractorComposite != null){
			if(data instanceof OperationInterfaceContainer){
				this.extractorComposite.setInput((OperationInterfaceContainer)data);
			}
			else if(data instanceof ServiceProxy){
				ServiceProxy sp = (ServiceProxy)data;
				this.extractorComposite.setInput(sp.getSoftwareService());
			}
		}
	}
	
	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}
}
