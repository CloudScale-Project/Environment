package org.scaledl.overview.properties.sections;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.architecture.ServiceProxy;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.architecture.UsageProxy;
import org.scaledl.overview.core.CorePackage;
import org.scaledl.overview.core.Entity;
import org.scaledl.overview.properties.components.ProvidedInterfaceComposite;
import org.scaledl.overview.properties.components.RequiredInterfaceComposite;

public class BasicSection extends GFPropertySection implements ITabbedPropertyConstants {

	private Text nameText;
	
	private DataBindingContext m_bindingContext;

	private Entity entity;
	private TransactionalEditingDomain editingDomain;
	
	private Section sectionReqInterfaces = null;
	private Section sectionProInterfaces = null;
	
	private RequiredInterfaceComposite reqInterfaceComposite = null;
	private ProvidedInterfaceComposite proInterfaceComposite = null;
	
	private Composite editorsComposite;
	
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
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void createControls(final Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		
		TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
		
		Composite composite = factory.createFlatFormComposite(parent);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		composite.setLayout(gridLayout);
		
		CLabel label = factory.createCLabel(composite, "Name:"); //$NON-NLS-1$
		label.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
		
		nameText = factory.createText(composite, ""); //$NON-NLS-1$
		nameText.setEditable(true);
		nameText.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		
		editorsComposite = factory.createComposite(composite);
		GridData gd = new GridData(GridData.FILL, GridData.FILL, true, true, 2, 1);
		editorsComposite.setLayoutData(gd);
		
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.HORIZONTAL;
		editorsComposite.setLayout(fillLayout);
		
	

		FormToolkit toolkit = new FormToolkit(parent.getDisplay());

		// Provided interfaces section
		{
			sectionProInterfaces = factory.createSection(editorsComposite, Section.DESCRIPTION | Section.TITLE_BAR);
			sectionProInterfaces.setText("Provided interfaces"); //$NON-NLS-1$
			sectionProInterfaces.setDescription("Provided OperationInterface operations.");
			sectionProInterfaces.marginHeight = 3;
			sectionProInterfaces.marginWidth = 3;

			// Composite for storing the data
			this.proInterfaceComposite = new ProvidedInterfaceComposite(sectionProInterfaces);
		    toolkit.paintBordersFor(this.proInterfaceComposite);
			sectionProInterfaces.setClient(proInterfaceComposite);
		}
		
		// Required interfaces section
		{
			sectionReqInterfaces = factory.createSection(editorsComposite, Section.DESCRIPTION | Section.TITLE_BAR);
			sectionReqInterfaces.setText("Required interfaces"); //$NON-NLS-1$
			sectionReqInterfaces.setDescription("Provided OperationInterface operations.");
			sectionReqInterfaces.marginHeight = 3;
			sectionReqInterfaces.marginWidth = 3;

			// Composite for storing the data
			this.reqInterfaceComposite = new RequiredInterfaceComposite(sectionReqInterfaces);
		    toolkit.paintBordersFor(this.reqInterfaceComposite);
			sectionReqInterfaces.setClient(reqInterfaceComposite);
		}
		
		composite.pack();
	}

	@Override
	public void refresh() {
		PictogramElement pe = getSelectedPictogramElement();
		if (pe != null) {
			Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
			
			if (bo == null){
				return;
			}
			
			if(bo instanceof Entity){
				this.entity = (Entity)bo;
				this.editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(this.entity.eResource().getResourceSet());
				
				if (m_bindingContext != null){
					m_bindingContext.dispose();
				}
				m_bindingContext = initDataBindings();
			}
			
			OperationInterfaceContainer oic = null;
			
			if(bo instanceof OperationInterfaceContainer){
				oic = (OperationInterfaceContainer)bo;
			}
			else if(bo instanceof ServiceProxy){
				ServiceProxy serviceProxy = (ServiceProxy)bo; 
				oic = serviceProxy.getSoftwareService();
			}
			
			
			if(oic != null){
				
				if (bo instanceof SoftwareService || bo instanceof UsageProxy)
				{
					this.reqInterfaceComposite.setInput(oic);
					this.sectionReqInterfaces.setVisible(true);
				}
				else
				{
					this.sectionReqInterfaces.setVisible(false);
				}
				
				this.proInterfaceComposite.setInput(oic);
				this.sectionProInterfaces.setVisible(true);
				
			}
			else{
				this.sectionProInterfaces.setVisible(false);
				this.sectionReqInterfaces.setVisible(false);
			}
			
			editorsComposite.layout();
		}
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		IObservableValue emfNameObservable = EMFEditObservables.observeValue(editingDomain, this.entity, CorePackage.Literals.ENTITY__NAME);
		IObservableValue localNameObservable = WidgetProperties.text(SWT.Modify).observe(nameText);
		
		bindingContext.bindValue(localNameObservable, emfNameObservable, null, null);
		
		return bindingContext;
	}
	
	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}
}
