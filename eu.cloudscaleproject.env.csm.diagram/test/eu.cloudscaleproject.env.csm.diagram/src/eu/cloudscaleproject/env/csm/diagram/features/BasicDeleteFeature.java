package eu.cloudscaleproject.env.csm.diagram.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;

import eu.cloudscaleproject.env.csm.architecture.Connector;
import eu.cloudscaleproject.env.csm.architecture.ExternalConnection;
import eu.cloudscaleproject.env.csm.architecture.HybridConnection;
import eu.cloudscaleproject.env.csm.architecture.InternalConnection;
import eu.cloudscaleproject.env.csm.architecture.ServiceConnector;
import eu.cloudscaleproject.env.csm.architecture.UsageConnector;
import eu.cloudscaleproject.env.csm.diagram.util.CsmUtil;

public class BasicDeleteFeature extends DefaultDeleteFeature{

	public BasicDeleteFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public void preDelete(IDeleteContext context) {
		// TODO Auto-generated method stub
		PictogramLink link = context.getPictogramElement().getLink();
		
		for(Object o : link.getBusinessObjects()){
			if(o instanceof InternalConnection){
				System.out.println("Deleting INTERNAL connection!");
				InternalConnection connection = (InternalConnection)o;
				
				CsmUtil.removeRequiredInterfaces(connection.getDestinationPlatformService(),
											  CsmUtil.collectProvidedInterfaces(connection.getSourcePlatformService()));
			}
			else if (o instanceof HybridConnection)
			{
				System.out.println("Deleting INTERNAL connection!");
				HybridConnection connection = (HybridConnection)o;
				
				CsmUtil.removeRequiredInterfaces(connection.getTargetPlatformService(),
						  CsmUtil.collectProvidedInterfaces(connection.getSourcePlatformService()));
			}
			else if(o instanceof ExternalConnection){
				System.out.println("Deleting EXTERNAL connection!");
				ExternalConnection connection = (ExternalConnection)o;
				
				Connector c = connection.getConnector();
				if (connection.getIsOutbound() == Boolean.TRUE)
				{
					if(c instanceof UsageConnector){
						UsageConnector uc = (UsageConnector)c;
						uc.getExposedInterfaces().remove(CsmUtil.collectProvidedInterfaces(connection.getPlatformService()));
					}
				}
				else
				{
					if(c instanceof ServiceConnector){
						ServiceConnector sc = (ServiceConnector)c;
						CsmUtil.removeRequiredInterfaces(connection.getPlatformService(), 
								sc.getApplicationService().getProvidedInterfaces());
					}
				}
			}
		}
		
		super.preDelete(context);
	}

}
