package org.scaledl.overview.diagram.features;

import java.util.Iterator;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.ComputingInfrastructureService;
import org.scaledl.overview.architecture.ExternalConnection;
import org.scaledl.overview.architecture.InternalConnection;
import org.scaledl.overview.architecture.PlatformService;
import org.scaledl.overview.architecture.ProvidedService;
import org.scaledl.overview.architecture.Service;
import org.scaledl.overview.architecture.SoftwareLayer;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.architecture.SoftwareServiceContainer;
import org.scaledl.overview.deployment.Deployment;
import org.scaledl.overview.deployment.ServiceDeployment;
import org.scaledl.overview.util.OverviewArchitectureUtil;
import org.scaledl.overview.util.OverviewDeploymentUtil;
import org.scaledl.overview.util.OverviewSpecificationUtil;
import org.scaledl.overview.util.OverviewUtil;

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
				System.out.println("Deleting infrastructure connection!");
				InternalConnection connection = (InternalConnection)o;
				preDeleteInternalConnection(connection);
			}
			else if (o instanceof ExternalConnection)
			{
				System.out.println("Deleting hybrid connection!");
				ExternalConnection connection = (ExternalConnection)o;
				preDeleteExternalConnection(connection);
			}
		}
		
		//remove objects
		for(Object o : link.getBusinessObjects()){
			if (o instanceof SoftwareServiceContainer){
				SoftwareServiceContainer ssc = (SoftwareServiceContainer)o;
				if(!ssc.getSoftwareServices().isEmpty()){
					
					Iterator<SoftwareService> iter = ssc.getSoftwareServices().iterator();
					while(iter.hasNext()){
						SoftwareService dss = iter.next();
						dss.getProvidedInterfaces().clear();
						dss.getRequiredInterfaces().clear();
						
						iter.remove();
					}
					
					SoftwareLayer sl = OverviewUtil.getCloudEnvironment(ssc).getSoftwareLayer();
					sl.getServices().removeAll(ssc.getSoftwareServices());
				}
			}

			if(o instanceof PlatformService){
				PlatformService ps = (PlatformService)o;
				
				//remove connection
				deleteConnections(ps);
				
				//release descriptor
				OverviewSpecificationUtil.releaseSystemDescriptor(ps, ps.getDescriptor());
				
				//remove infrastructure service
				if(ps.getComputingInfrastructureService() != null){
					ComputingInfrastructureService cis = ps.getComputingInfrastructureService();
					OverviewArchitectureUtil.deleteComputingInfrastructureService(cis);
				}
				
				//remove service deployment
				if (ps instanceof ProvidedService){
					Deployment deployment = OverviewUtil.getDeployment(ps);
					ServiceDeployment sd = ((ProvidedService)ps).getDeployment();
					OverviewDeploymentUtil.deleteServiceDeployment(deployment, sd);
				}
			}
  
		}
		
		super.preDelete(context);
	}
	
	private void deleteConnections(Service service){
		
		CloudEnvironment ce = OverviewUtil.getCloudEnvironment(service);
		
		{
			Iterator<InternalConnection> iter = ce.getInternalConnections().iterator();
			while(iter.hasNext()){
				InternalConnection con = iter.next();
				if(con.getSource() == service || con.getTarget() == service){
					preDeleteInternalConnection(con);
				
					//delete internal connection
					iter.remove();
				}
			}
		}
		{
			Iterator<ExternalConnection> iter = ce.getArchitecture().getExternalConnections().iterator();
			while(iter.hasNext()){
				ExternalConnection con = iter.next();
				if(con.getSource() == service || con.getTarget() == service){
					preDeleteExternalConnection(con);
					
					//delete internal connection
					iter.remove();
				}
			}
		}
	}
	
	private void preDeleteInternalConnection(InternalConnection connection){
		System.out.println("Deleting INTERNAL connection!");
		OverviewArchitectureUtil.removeRequiredInterfaces(connection.getTarget(),
									  OverviewArchitectureUtil.collectProvidedInterfaces(connection.getSource()));
	}

	private void preDeleteExternalConnection(ExternalConnection connection){
		
		OverviewArchitectureUtil.removeRequiredInterfaces(connection.getTarget(),
				OverviewArchitectureUtil.collectProvidedInterfaces(connection.getSource()));		
	}

}
