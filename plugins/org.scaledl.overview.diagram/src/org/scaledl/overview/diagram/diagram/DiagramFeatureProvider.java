package org.scaledl.overview.diagram.diagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;
import org.scaledl.overview.Overview;
import org.scaledl.overview.SpecificationProviderService;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.Connection;
import org.scaledl.overview.architecture.ExternalSoftwareService;
import org.scaledl.overview.architecture.PlatformService;
import org.scaledl.overview.architecture.Proxy;
import org.scaledl.overview.architecture.Service;
import org.scaledl.overview.architecture.ServiceProxy;
import org.scaledl.overview.architecture.SoftwareServiceContainer;
import org.scaledl.overview.diagram.features.BasicDeleteFeature;
import org.scaledl.overview.diagram.features.BasicMoveFeature;
import org.scaledl.overview.diagram.features.BasicResizeFeature;
import org.scaledl.overview.diagram.features.DirectEditingFeature;
import org.scaledl.overview.diagram.features.EditorFeature;
import org.scaledl.overview.diagram.features.RenameComponentFeature;
import org.scaledl.overview.diagram.features.UpdateConnectionService;
import org.scaledl.overview.diagram.features.UpdateDiagramFeature;
import org.scaledl.overview.diagram.features.UpdateEnvironmentFeature;
import org.scaledl.overview.diagram.features.UpdateRuntimeServiceFeature;
import org.scaledl.overview.diagram.features.UpdateServiceFeature;
import org.scaledl.overview.diagram.features.UpdateServiceProxyFeature;
import org.scaledl.overview.diagram.features.add.AddCloudEnvironmentFeature;
import org.scaledl.overview.diagram.features.add.AddConnectionFeature;
import org.scaledl.overview.diagram.features.add.AddProxyFeature;
import org.scaledl.overview.diagram.features.add.AddRuntimeServiceFeature;
import org.scaledl.overview.diagram.features.add.AddSupportServiceFeature;
import org.scaledl.overview.diagram.features.create.CreateCloudEnvironmentFeature;
import org.scaledl.overview.diagram.features.create.CreateConnectionFeature;
import org.scaledl.overview.diagram.features.create.CreatePlatformRuntimeServiceFeature;
import org.scaledl.overview.diagram.features.create.CreatePlatformSupportServiceFeature;
import org.scaledl.overview.diagram.features.create.CreateProvidedPlatformRuntimeServiceFeature;
import org.scaledl.overview.diagram.features.create.CreateProvidedPlatformSupportServiceFeature;
import org.scaledl.overview.diagram.features.create.CreateProvidedSoftwareServiceFeature;
import org.scaledl.overview.diagram.features.create.CreateServiceProxyFeature;
import org.scaledl.overview.diagram.features.create.CreateUsageProxyFeature;
import org.scaledl.overview.diagram.features.layout.LayoutBasicFeature;
import org.scaledl.overview.diagram.features.layout.LayoutProviderFeature;
import org.scaledl.overview.diagram.features.layout.LayoutTitleContainerFeature;
import org.scaledl.overview.specification.CloudSpecification;
import org.scaledl.overview.specification.PlatformRuntimeServiceDescriptor;
import org.scaledl.overview.specification.PlatformSupportServiceDescriptor;
import org.scaledl.overview.specification.ProvidedPlatformRuntimeServiceDescriptor;
import org.scaledl.overview.specification.ProvidedPlatformSupportServiceDescriptor;
import org.scaledl.overview.specification.ProvidedServiceDescriptor;
import org.scaledl.overview.specification.ProvidedSoftwareServiceDescriptor;
import org.scaledl.overview.specification.ServiceDescriptor;
import org.scaledl.overview.specification.ServiceSpecification;
import org.scaledl.overview.specification.Specification;

public class DiagramFeatureProvider extends DefaultFeatureProvider {

	public DiagramFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}
	
	@Override
	public ICreateFeature[] getCreateFeatures() {

		List<ICreateFeature> features = new ArrayList<ICreateFeature>();
		
		for (Specification definition : SpecificationProviderService.getInstance().getDefinitions())
		{
			if (definition instanceof ServiceSpecification)
			{
				//create universal cloud-deployable services
				ServiceSpecification ss = (ServiceSpecification)definition;
				for (ServiceDescriptor d : ss.getServiceDescriptors()) {
					if(d instanceof PlatformRuntimeServiceDescriptor){
						features.add(new CreatePlatformRuntimeServiceFeature(this, (PlatformRuntimeServiceDescriptor)d));
					}
					else if(d instanceof PlatformSupportServiceDescriptor){
						features.add(new CreatePlatformSupportServiceFeature(this, (PlatformSupportServiceDescriptor)d));
					}
					else if(d instanceof ExternalSoftwareService){
						//TODO: implement this
					}
				}
			}
			else if (definition instanceof CloudSpecification)
			{
				CloudSpecification cs = (CloudSpecification)definition;
				features.add(new CreateCloudEnvironmentFeature(this, cs));
				
				//create cloud provided platform services
				for (ProvidedServiceDescriptor d : cs.getPlatformServiceDescriptors()) {
					if(d instanceof ProvidedPlatformRuntimeServiceDescriptor){
						features.add(new CreateProvidedPlatformRuntimeServiceFeature(this, (ProvidedPlatformRuntimeServiceDescriptor)d));
					}
					else if(d instanceof ProvidedPlatformSupportServiceDescriptor){
						features.add(new CreateProvidedPlatformSupportServiceFeature(this, (ProvidedPlatformSupportServiceDescriptor)d));
					}
					else if(d instanceof PlatformRuntimeServiceDescriptor){
						features.add(new CreatePlatformRuntimeServiceFeature(this, (PlatformRuntimeServiceDescriptor)d));
					}
					else if(d instanceof PlatformSupportServiceDescriptor){
						features.add(new CreatePlatformSupportServiceFeature(this, (PlatformSupportServiceDescriptor)d));
					}
					
				}
				for (ProvidedServiceDescriptor d : cs.getSoftwareServiceDescriptors()) {
					if(d instanceof ProvidedSoftwareServiceDescriptor){
						features.add(new CreateProvidedSoftwareServiceFeature(this, (ProvidedSoftwareServiceDescriptor)d));
					}
				}
			}
		}
		
		// FIXME : add descriptor
		features.add(new CreateUsageProxyFeature(this));
		features.add(new CreateServiceProxyFeature(this));
		
		ICreateFeature[] featuresArray = new ICreateFeature[features.size()];
		featuresArray = features.toArray(featuresArray);
		
		return featuresArray;
		//return new ICreateFeature[] {new CreateDomainObjectFeature(this, null)};
	}
	
	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] {
				new CreateConnectionFeature(this)
			};
	}
	
	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		// TODO Auto-generated method stub
		return new BasicDeleteFeature(this);
	}
	
	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		// TODO: check for right domain object instances below
		
		if (context instanceof IAddConnectionContext /* && context.getNewObject() instanceof <DomainObject> */) {
			return new AddConnectionFeature(this);
		}
		else if (context instanceof IAddContext && context.getNewObject() instanceof CloudEnvironment ) {
			return new AddCloudEnvironmentFeature(this);
		}
		else if (context instanceof IAddContext && context.getNewObject() instanceof Proxy ) {
			return new AddProxyFeature(this);
		}
		else if (context instanceof IAddContext && context.getNewObject() instanceof SoftwareServiceContainer ) {
			return new AddRuntimeServiceFeature(this);
		}
		else if (context instanceof IAddContext && context.getNewObject() instanceof OperationInterfaceContainer ) {
			return new AddSupportServiceFeature(this);
		}

		return super.getAddFeature(context);
	}
	
	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof CloudEnvironment) {
			return new LayoutProviderFeature(this);
		}
		else if (bo instanceof SoftwareServiceContainer)
		{
			return new LayoutTitleContainerFeature(this);
		}
		else if (context.getPictogramElement() instanceof ContainerShape /* && getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof <DomainObject> */) {
			return  new LayoutBasicFeature(this);
		}
	
		return super.getLayoutFeature(context);
	}
	
	@Override
	public ICustomFeature[] getCustomFeatures(ICustomContext context) {
		return new ICustomFeature[]{ new RenameComponentFeature(this), new EditorFeature(this)};
		
	};
	
	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
	   PictogramElement pictogramElement = context.getPictogramElement();
       Object bo = getBusinessObjectForPictogramElement(pictogramElement);
       
       if (bo instanceof CloudEnvironment) {
           return new UpdateEnvironmentFeature(this);
       }
       
       if (bo instanceof SoftwareServiceContainer)
       {
           return new UpdateRuntimeServiceFeature(this);
       }
       
       if (bo instanceof Service) {
           return new UpdateServiceFeature(this);
       }
       
       if (bo instanceof Connection)
       {
    	   return new UpdateConnectionService(this);
       }
       if (bo instanceof ServiceProxy)
       {
    	   return new UpdateServiceProxyFeature(this);
       }
       if (bo instanceof Overview)
       {
    	   return new UpdateDiagramFeature(this);
       }
    	   
       
	   return super.getUpdateFeature(context);
	}
	
	@Override
	public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context) {
		PictogramElement pe = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pe);
		
		if (bo instanceof PlatformService) {
			return new DirectEditingFeature(this);
		}
		
		return super.getDirectEditingFeature(context);
	}
	
	@Override
	public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context) {
		return new BasicResizeFeature(this);
	}
	
	@Override
	public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context) {
		return new BasicMoveFeature(this);
	}
	
	
}
