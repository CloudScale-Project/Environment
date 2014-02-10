package eu.cloudscaleproject.env.csm.diagram.diagram;

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

import eu.cloudscaleproject.env.csm.DefinitionProviderService;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.Connection;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.architecture.Proxy;
import eu.cloudscaleproject.env.csm.architecture.Service;
import eu.cloudscaleproject.env.csm.architecture.ServiceProxy;
import eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer;
import eu.cloudscaleproject.env.csm.definition.CloudDefinition;
import eu.cloudscaleproject.env.csm.definition.Definition;
import eu.cloudscaleproject.env.csm.definition.DeployablePlatformServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.DeployableRuntimeServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.DeployableSupportServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.ExternalPlatformServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.ExternalRuntimeServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.ExternalSupportServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.SoftwareDefinition;
import eu.cloudscaleproject.env.csm.diagram.features.BasicDeleteFeature;
import eu.cloudscaleproject.env.csm.diagram.features.BasicMoveFeature;
import eu.cloudscaleproject.env.csm.diagram.features.BasicResizeFeature;
import eu.cloudscaleproject.env.csm.diagram.features.DirectEditingFeature;
import eu.cloudscaleproject.env.csm.diagram.features.EditorFeature;
import eu.cloudscaleproject.env.csm.diagram.features.RenameComponentFeature;
import eu.cloudscaleproject.env.csm.diagram.features.UpdateConnectionService;
import eu.cloudscaleproject.env.csm.diagram.features.UpdateEnvironmentFeature;
import eu.cloudscaleproject.env.csm.diagram.features.UpdateRuntimeServiceFeature;
import eu.cloudscaleproject.env.csm.diagram.features.UpdateServiceFeature;
import eu.cloudscaleproject.env.csm.diagram.features.UpdateServiceProxyFeature;
import eu.cloudscaleproject.env.csm.diagram.features.add.AddCloudEnvironmentFeature;
import eu.cloudscaleproject.env.csm.diagram.features.add.AddConnectionFeature;
import eu.cloudscaleproject.env.csm.diagram.features.add.AddPlatformServiceFeature;
import eu.cloudscaleproject.env.csm.diagram.features.add.AddProxyFeature;
import eu.cloudscaleproject.env.csm.diagram.features.add.AddRuntimeServiceFeature;
import eu.cloudscaleproject.env.csm.diagram.features.create.CreateCloudEnvironmentFeature;
import eu.cloudscaleproject.env.csm.diagram.features.create.CreateConnectionFeature;
import eu.cloudscaleproject.env.csm.diagram.features.create.CreateDRPlatformServiceFeature;
import eu.cloudscaleproject.env.csm.diagram.features.create.CreateDSPlatformServiceFeature;
import eu.cloudscaleproject.env.csm.diagram.features.create.CreateERPlatformServiceFeature;
import eu.cloudscaleproject.env.csm.diagram.features.create.CreateESPlatformServiceFeature;
import eu.cloudscaleproject.env.csm.diagram.features.create.CreateServiceProxyFeature;
import eu.cloudscaleproject.env.csm.diagram.features.create.CreateUsageProxyFeature;
import eu.cloudscaleproject.env.csm.diagram.features.layout.LayoutBasicFeature;
import eu.cloudscaleproject.env.csm.diagram.features.layout.LayoutProviderFeature;
import eu.cloudscaleproject.env.csm.diagram.features.layout.LayoutTitleContainerFeature;

public class DiagramFeatureProvider extends DefaultFeatureProvider {

	public DiagramFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}
	
	@Override
	public ICreateFeature[] getCreateFeatures() {

		List<ICreateFeature> features = new ArrayList<ICreateFeature>();
		
		for (Definition definition : DefinitionProviderService.getInstance().getDefinitions())
		{
			if (definition instanceof SoftwareDefinition)
			{
				SoftwareDefinition sd = (SoftwareDefinition)definition;
				for (DeployablePlatformServiceDescriptor d : sd.getDeployablePlatformServiceDescriptors()) {
					if(d instanceof DeployableRuntimeServiceDescriptor){
						features.add(new CreateDRPlatformServiceFeature(this, (DeployableRuntimeServiceDescriptor)d));
					}
					else if(d instanceof DeployableSupportServiceDescriptor){
						features.add(new CreateDSPlatformServiceFeature(this, (DeployableSupportServiceDescriptor)d));
					}
				}
			}
			else if (definition instanceof CloudDefinition)
			{
				CloudDefinition cd = (CloudDefinition)definition;
				features.add(new CreateCloudEnvironmentFeature(this, cd.getDescriptor()));
				
				for (ExternalPlatformServiceDescriptor d : cd.getPlatformDescriptors()) {
					if(d instanceof ExternalRuntimeServiceDescriptor){
						features.add(new CreateERPlatformServiceFeature(this, (ExternalRuntimeServiceDescriptor)d));
					}
					else if(d instanceof ExternalSupportServiceDescriptor){
						features.add(new CreateESPlatformServiceFeature(this, (ExternalSupportServiceDescriptor)d));
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
		else if (context instanceof IAddContext && context.getNewObject() instanceof SoftwareServiceContainer ) {
			return new AddRuntimeServiceFeature(this);
		}
		else if (context instanceof IAddContext && context.getNewObject() instanceof PlatformService ) {
			return new AddPlatformServiceFeature(this);
		}
		else if (context instanceof IAddContext && context.getNewObject() instanceof Proxy ) {
			return new AddProxyFeature(this);
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
