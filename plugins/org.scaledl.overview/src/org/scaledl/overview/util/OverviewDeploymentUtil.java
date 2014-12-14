package org.scaledl.overview.util;

import org.scaledl.overview.deployment.ComputingEnvironment;
import org.scaledl.overview.deployment.Deployment;
import org.scaledl.overview.deployment.GenericDeployment;
import org.scaledl.overview.deployment.RuntimeDeployment;
import org.scaledl.overview.deployment.ServiceDeployment;
import org.scaledl.overview.deployment.impl.DeploymentFactoryImpl;
import org.scaledl.overview.specification.ComputingResourceDescriptor;

public class OverviewDeploymentUtil {
	
	//private static Logger logger = Logger.getLogger(OverviewDeploymentUtil.class.getName());
	
	public static RuntimeDeployment createRuntimeDeployment(Deployment deployment, ComputingResourceDescriptor crd){
		
		RuntimeDeployment rd = DeploymentFactoryImpl.eINSTANCE.createRuntimeDeployment();
		
		ComputingEnvironment ce = DeploymentFactoryImpl.eINSTANCE.createComputingEnvironment();
		ce.setName("Single instance computing environment");
		ce.setInstanceDescriptor(crd);
		
		rd.setComputingEnvironment(ce);
		
		
		deployment.getServiceDeployments().add(rd);
		
		return rd;
	}
	
	public static GenericDeployment createGenericDeployment(Deployment deployment){
		
		GenericDeployment gd = DeploymentFactoryImpl.eINSTANCE.createGenericDeployment();
		deployment.getServiceDeployments().add(gd);
		
		return gd;
	}
	
	public static void deleteServiceDeployment(Deployment deployment, ServiceDeployment sd){
		deployment.getServiceDeployments().remove(sd);
	}
	
}
