package eu.cloudscaleproject.env.csm.diagram.features.create;

import org.eclipse.graphiti.features.IFeatureProvider;

import eu.cloudscaleproject.env.csm.architecture.ApplicationServer;
import eu.cloudscaleproject.env.csm.architecture.CloudProvider;
import eu.cloudscaleproject.env.csm.architecture.Component;
import eu.cloudscaleproject.env.csm.architecture.Instance;
import eu.cloudscaleproject.env.csm.architecture.Module;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.core.impl.CoreFactoryImpl;
import eu.cloudscaleproject.env.csm.definition.ApplicationServerDescriptor;
import eu.cloudscaleproject.env.csm.diagram.util.CsmUtil;

public class CreateApplicationServerFeature extends CreateComponentFeature {

	
	public CreateApplicationServerFeature (IFeatureProvider fp, ApplicationServerDescriptor descriptor)
	{
		super (fp, descriptor);
	}
	
	@Override
	protected Component createComponent(CloudProvider cp) {
		ApplicationServer applicationServer = ArchitectureFactoryImpl.eINSTANCE.createApplicationServer();
		//RuntimeContainer runtimeContainer = CoreFactoryImpl.eINSTANCE.createRuntimeContainer();
		//applicationServer.setRuntimeContainer(runtimeContainer);
		
		Module module = ArchitectureFactoryImpl.eINSTANCE.createModule();
		module.setName("My app module");
		
		OperationInterface oi = CoreFactoryImpl.eINSTANCE.createOperationInterface();
		oi.setName("OperationInterface : "+applicationServer.getName());
		module.getProvidedInterfaces().add(oi);

		applicationServer.getModules().add(module);
		
		ApplicationServerDescriptor asd =  CsmUtil.getSystemDescriptor(cp, (ApplicationServerDescriptor)descriptor);
		applicationServer.setApplicationServerDescriptor(asd);
		applicationServer.setName(descriptor.getName());
		
		
		Instance instance = CsmUtil.createDefaultInstance(cp);
		applicationServer.setInstance(instance);
		
		return applicationServer;
	}

}
