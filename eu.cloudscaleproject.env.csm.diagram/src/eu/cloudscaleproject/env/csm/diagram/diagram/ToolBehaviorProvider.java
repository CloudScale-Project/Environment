/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package eu.cloudscaleproject.env.csm.diagram.diagram;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.internal.Messages;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.palette.impl.ConnectionCreationToolEntry;
import org.eclipse.graphiti.palette.impl.ObjectCreationToolEntry;
import org.eclipse.graphiti.palette.impl.PaletteCompartmentEntry;
import org.eclipse.graphiti.platform.IPlatformImageConstants;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;

import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor;
import eu.cloudscaleproject.env.csm.definition.Descriptor;
import eu.cloudscaleproject.env.csm.diagram.DiagramImageProvider;
import eu.cloudscaleproject.env.csm.diagram.decorations.DecorationManager;
import eu.cloudscaleproject.env.csm.diagram.features.EditorFeature;
import eu.cloudscaleproject.env.csm.diagram.features.create.CreateCloudEnvironmentFeature;
import eu.cloudscaleproject.env.csm.diagram.features.create.CreateServiceProxyFeature;
import eu.cloudscaleproject.env.csm.diagram.features.create.CreateUsageProxyFeature;
import eu.cloudscaleproject.env.csm.diagram.features.create.CreatetPlatformServiceFeature;

public class ToolBehaviorProvider extends DefaultToolBehaviorProvider {
	
	private IValidator<EObject> validator = ModelValidationService.getInstance().newValidator(EvaluationMode.BATCH);

	public ToolBehaviorProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	@Override
	public IContextButtonPadData getContextButtonPad(IPictogramElementContext context) {
		IContextButtonPadData data = super.getContextButtonPad(context);
		PictogramElement pe = context.getPictogramElement();

		// 1. set the generic context buttons
		// note, that we do not add 'remove' (just as an example)
		setGenericContextButtons(data, pe, CONTEXT_BUTTON_DELETE | CONTEXT_BUTTON_UPDATE);

		// 2. set the editor button
		CustomContext cc = new CustomContext(new PictogramElement[] { pe });
		ICustomFeature[] cf = getFeatureProvider().getCustomFeatures(cc);
		for (int i = 0; i < cf.length; i++) {
			ICustomFeature iCustomFeature = cf[i];
			if (iCustomFeature instanceof EditorFeature) {
				IContextButtonEntry button = null;
				button = new ContextButtonEntry(iCustomFeature, cc);
				button.setText("Editor");
				button.setDescription("Open coresponding editor");
				button.setIconId(DiagramImageProvider.IMG_EDITOR);
				data.getGenericContextButtons().add(0, button);
				break;
			}
		}
		
		return data;
	}

	@Override
	public IPaletteCompartmentEntry[] getPalette() {
		List<IPaletteCompartmentEntry> ret = new ArrayList<IPaletteCompartmentEntry>();

		// add compartments from super class, skip objects compartment
		IPaletteCompartmentEntry[] superCompartments = super.getPalette();
		for (int i = 0; i < superCompartments.length; i++)
		{
			if (superCompartments[i].getLabel().equals(Messages.DefaultToolBehaviorProvider_1_xfld)) continue;
			if (superCompartments[i].getLabel().equals(Messages.DefaultToolBehaviorProvider_0_xfld))
			{
				continue;
			}
			ret.add(superCompartments[i]);
		}

		PaletteCompartmentEntry connCompartment = new PaletteCompartmentEntry("Connections & Proxies", DiagramImageProvider.IMG_FOLDER); //$NON-NLS-1$
		ret.add(connCompartment);
		
		// add all create-connection-features to the new stack-entry
		ICreateConnectionFeature[] createConnectionFeatures = getFeatureProvider().getCreateConnectionFeatures();
		for (ICreateConnectionFeature cf : createConnectionFeatures) {
			ConnectionCreationToolEntry connectionCreationToolEntry = new ConnectionCreationToolEntry(cf.getCreateName(),
					cf.getCreateDescription(), DiagramImageProvider.IMG_CONNECTION, cf.getCreateLargeImageId());
			connectionCreationToolEntry.addCreateConnectionFeature(cf);
			connCompartment.addToolEntry(connectionCreationToolEntry);
		}
		
		// FIXME : abstract class for connectors
		for (CreateUsageProxyFeature f : getCreateFeatures(CreateUsageProxyFeature.class))
		{
			connCompartment.addToolEntry(new ObjectCreationToolEntry(f.getName(), "", DiagramImageProvider.IMG_USAGE_PROXY_SMALL, null, f));
		}
		for (CreateServiceProxyFeature f : getCreateFeatures(CreateServiceProxyFeature.class))
		{
			connCompartment.addToolEntry(new ObjectCreationToolEntry(f.getName(), "", DiagramImageProvider.IMG_SERVICE_PROXY_SMALL, null, f));
		}
		
		
		List <CreateCloudEnvironmentFeature> cloudProviderFeatures = getCreateFeatures(CreateCloudEnvironmentFeature.class);
		List <CreatetPlatformServiceFeature> componentFeatures = getCreateFeatures(CreatetPlatformServiceFeature.class);
		
		// Compartment that holds all available cloud providers
		PaletteCompartmentEntry cloudProvidersCompartment = new PaletteCompartmentEntry("Cloud environments", DiagramImageProvider.IMG_CLOUD_ENVIRONMENT); //$NON-NLS-1$
		ret.add(cloudProvidersCompartment);
	
		for (CreateCloudEnvironmentFeature cloudFeature : cloudProviderFeatures) {
			
			CloudEnvironmentDescriptor descriptor = cloudFeature.getDescriptor();
			
			// Add cloud feature to CloudProviders compartment
			cloudProvidersCompartment.addToolEntry(new ObjectCreationToolEntry(
					descriptor.getName(), "", DiagramImageProvider.getImage(descriptor), null, cloudFeature));
			
			// Create cloud specifics compartment
			PaletteCompartmentEntry cloudCompartment = new PaletteCompartmentEntry(descriptor.getName(), 
					DiagramImageProvider.getImage(descriptor)); //$NON-NLS-1$
			
			ret.add(cloudCompartment);
			
			// Add all related components in this compartment
			for (CreatetPlatformServiceFeature componentFeature : componentFeatures) {
				
				Descriptor componentDescriptor = componentFeature.getDescriptor();
				
				if (descriptor.getProviderID().equals(componentDescriptor.getProviderID()))
				{
					cloudCompartment.addToolEntry(new ObjectCreationToolEntry(
							componentDescriptor.getName(), "", DiagramImageProvider.getImage(componentDescriptor), null, componentFeature));
				}
			}
		}
		
		// Create system compartment and insert all appliances and web servers into it
		PaletteCompartmentEntry systemCompartment = new PaletteCompartmentEntry("Deployable platform services", DiagramImageProvider.IMG_DEPLOYABLE_SERVICES); //$NON-NLS-1$
		ret.add(systemCompartment);
		
		for (CreatetPlatformServiceFeature componentFeature : componentFeatures) {
			Descriptor componentDescriptor = componentFeature.getDescriptor();
			if (componentFeature.getDescriptor().getProviderID() == null)
			{
					systemCompartment.addToolEntry(new ObjectCreationToolEntry(
							componentDescriptor.getName(), "", DiagramImageProvider.getImage(componentDescriptor), null, componentFeature));
				
			}
		}
		
		return ret.toArray(new IPaletteCompartmentEntry[ret.size()]);
	}
	
	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		
		Set<IDecorator> decorators = DecorationManager.getDecorations(pe);
		
		if(pe.getLink() != null && pe.getLink().getBusinessObjects() != null){
			Object o = pe.getLink().getBusinessObjects().get(0);
			if(o instanceof EObject){
				EObject eo = (EObject)o;
				if(!validator.validate(eo).isOK()){
					decorators.add(new ImageDecorator(IPlatformImageConstants.IMG_ECLIPSE_ERROR_TSK));
				}
			}
		}
		
		if(decorators.isEmpty()){
			return super.getDecorators(pe);
		}
		else{
			return decorators.toArray(new IDecorator[decorators.size()]);
		}
	}
	
	// Not really efficient, but works for now
	private <T> List<T> getCreateFeatures (Class<T> c)
	{
		LinkedList<T> features = new LinkedList<T>();
		
		IFeatureProvider featureProvider = getFeatureProvider();
		ICreateFeature[] createFeatures = featureProvider.getCreateFeatures();
		for (ICreateFeature cf : createFeatures) {
			if (c.isAssignableFrom(cf.getClass()))
			{
				features.add(c.cast(cf));
			}
		}
		
		return features;
	}

	@Override
	public ICustomFeature getDoubleClickFeature(IDoubleClickContext context) {
		ICustomFeature customFeature = new EditorFeature(getFeatureProvider());

		// canExecute() tests especially if the context contains a EClass
		if (customFeature.canExecute(context)) {
			return customFeature;
		}

		return super.getDoubleClickFeature(context);
	}

	@Override
	public String getToolTip(GraphicsAlgorithm ga) {
		PictogramElement pe = ga.getPictogramElement();
		Object bo = getFeatureProvider().getBusinessObjectForPictogramElement(pe);
		if (bo instanceof Entity) {
			String name = ((Entity) bo).getName();
			if (name != null && !(name.length() == 0)) {
				return name;
			}
		}
		return super.getToolTip(ga);
	}

}
