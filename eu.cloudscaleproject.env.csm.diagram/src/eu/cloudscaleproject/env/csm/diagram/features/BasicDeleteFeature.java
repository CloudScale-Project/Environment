package eu.cloudscaleproject.env.csm.diagram.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;

import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService;
import eu.cloudscaleproject.env.csm.architecture.ExternalConnection;
import eu.cloudscaleproject.env.csm.architecture.HybridConnection;
import eu.cloudscaleproject.env.csm.architecture.InternalConnection;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.architecture.Proxy;
import eu.cloudscaleproject.env.csm.architecture.ServiceProxy;
import eu.cloudscaleproject.env.csm.architecture.SoftwareLayer;
import eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer;
import eu.cloudscaleproject.env.csm.architecture.UsageProxy;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public class BasicDeleteFeature extends DefaultDeleteFeature{

	public BasicDeleteFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public void preDelete(IDeleteContext context) {
		// TODO Auto-generated method stub
		PictogramLink link = context.getPictogramElement().getLink();
		
		//remove connections
		for(Object o : link.getBusinessObjects()){
			if(o instanceof InternalConnection){
				System.out.println("Deleting INTERNAL connection!");
				InternalConnection connection = (InternalConnection)o;
				preDeleteInternalConnection(connection);
			}
			else if (o instanceof HybridConnection)
			{
				System.out.println("Deleting INTERNAL connection!");
				HybridConnection connection = (HybridConnection)o;
				preDeleteHybridConnection(connection);
			}
			else if(o instanceof ExternalConnection){
				System.out.println("Deleting EXTERNAL connection!");
				ExternalConnection connection = (ExternalConnection)o;
				preDeleteExternalConnection(connection);
			}
		}
		
		//remove objects
		for(Object o : link.getBusinessObjects()){
			if (o instanceof SoftwareServiceContainer){
				SoftwareServiceContainer ssc = (SoftwareServiceContainer)o;
				if(!ssc.getSoftwareServices().isEmpty()){
					SoftwareLayer sl = ssc.getSoftwareServices().get(0).getSoftwareLayer();
					sl.getServices().removeAll(ssc.getSoftwareServices());
				}
			}

			if(o instanceof PlatformService){
				PlatformService ps = (PlatformService)o;
				
				//remove connection
				deleteConnections(ps);
				
				if (o instanceof DeployablePlatformService){
					DeployablePlatformService dpd = (DeployablePlatformService)o;
				
					//remove computing infrastructure service if not used anywhere else.
					ComputingInfrastructureService cis = dpd.getComputingInfrastructureService();
					if(cis != null && cis.getPlatformServices().size() <= 1){
						cis.getInfrastructureLayer().getServices().remove(cis);
					}
				}
			}
  
		}
		
		super.preDelete(context);
	}
	
	private void deleteConnections(PlatformService ps){
		for(InternalConnection con : ps.getPlatformLayer().getCloudEnvironment().getInternalConnections()){
			if(con.getSourcePlatformService() == ps || con.getDestinationPlatformService() == ps){
				preDeleteInternalConnection(con);
			}
		}
		for(HybridConnection con : ps.getPlatformLayer().getCloudEnvironment().getArchitecture().getHybridConnections()){
			if(con.getSourcePlatformService() == ps || con.getTargetPlatformService() == ps){
				preDeleteHybridConnection(con);
			}
		}
		for(ExternalConnection con : ps.getPlatformLayer().getCloudEnvironment().getArchitecture().getExternalConnections()){
			if(con.getPlatformService() == ps){
				preDeleteExternalConnection(con);
			}
		}
	}
	
	private void preDeleteInternalConnection(InternalConnection connection){
		System.out.println("Deleting INTERNAL connection!");
		CsmUtil.removeRequiredInterfaces(connection.getDestinationPlatformService(),
									  CsmUtil.collectProvidedInterfaces(connection.getSourcePlatformService()));
	}
	
	private void preDeleteHybridConnection(HybridConnection connection){
		CsmUtil.removeRequiredInterfaces(connection.getTargetPlatformService(),
				  CsmUtil.collectProvidedInterfaces(connection.getSourcePlatformService()));
		
		CsmUtil.removeSystemDescriptor(connection, connection.getDescriptor());
	}

	private void preDeleteExternalConnection(ExternalConnection connection){
		Proxy c = connection.getProxy();
		if (connection.getIsOutbound() == Boolean.TRUE)
		{
			if(c instanceof UsageProxy){
				UsageProxy uc = (UsageProxy)c;
				uc.getRequiredInterfaces().remove(CsmUtil.collectProvidedInterfaces(connection.getPlatformService()));
			}
		}
		else
		{
			if(c instanceof ServiceProxy){
				ServiceProxy sc = (ServiceProxy)c;
				CsmUtil.removeRequiredInterfaces(connection.getPlatformService(), 
						sc.getSoftwareService().getProvidedInterfaces());
			}
		}
		CsmUtil.removeSystemDescriptor(connection, connection.getDescriptor());
	}

}
