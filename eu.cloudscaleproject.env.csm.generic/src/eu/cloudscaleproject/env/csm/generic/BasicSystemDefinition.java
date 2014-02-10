package eu.cloudscaleproject.env.csm.generic;

import eu.cloudscaleproject.env.csm.definition.DeployableRuntimeServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.DeployableSupportServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.impl.DefinitionFactoryImpl;
import eu.cloudscaleproject.env.csm.definition.impl.SoftwareDefinitionImpl;

public class BasicSystemDefinition extends SoftwareDefinitionImpl{

	public BasicSystemDefinition() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init ()
	{
		//
		// System definition
		//
		DeployableSupportServiceDescriptor mySql = DefinitionFactoryImpl.eINSTANCE.createDeployableSupportServiceDescriptor();
		mySql.setName("MySQL");
		mySql.setProviderID(null); // TODO : refactor
		
		DeployableSupportServiceDescriptor mongoDB = DefinitionFactoryImpl.eINSTANCE.createDeployableSupportServiceDescriptor();
		mongoDB.setName("MongoDB");
		mongoDB.setProviderID(null); // TODO : refactor
		
		DeployableSupportServiceDescriptor postgreSql = DefinitionFactoryImpl.eINSTANCE.createDeployableSupportServiceDescriptor();
		postgreSql.setName("PostgreSQL");
		postgreSql.setProviderID(null); // TODO : refactor
		
		DeployableRuntimeServiceDescriptor tomcat = DefinitionFactoryImpl.eINSTANCE.createDeployableRuntimeServiceDescriptor();
		tomcat.setName("Apache Tomcat");
		tomcat.setProviderID(null);
		
		DeployableRuntimeServiceDescriptor glassfish = DefinitionFactoryImpl.eINSTANCE.createDeployableRuntimeServiceDescriptor();
		glassfish.setName("GlassFish");
		glassfish.setProviderID(null);
		
		getDeployablePlatformServiceDescriptors().add(mySql);
		getDeployablePlatformServiceDescriptors().add(mongoDB);
		getDeployablePlatformServiceDescriptors().add(postgreSql);
		
		getDeployablePlatformServiceDescriptors().add(tomcat);
		getDeployablePlatformServiceDescriptors().add(glassfish);
	}
}
