package eu.cloudscaleproject.env.csm.properties.components.editors;

import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService;
import eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public class EditorSoftwareServices extends AbstractEditorList<DeployableSoftwareService>{

	private final SoftwareServiceContainer ssContainer;
	
	public EditorSoftwareServices(Composite c, SoftwareServiceContainer ssContainer){
		super(c, ssContainer, ssContainer.getSoftwareServices());
		this.ssContainer = ssContainer;
		
		setName("Edit software services.");
	}

	@Override
	public DeployableSoftwareService createEntry() {
		DeployableSoftwareService dss = ArchitectureFactoryImpl.eINSTANCE.createDeployableSoftwareService();
		dss.setName("NoName");
		dss.setRuntimePlatformService(ssContainer);
		
		CloudEnvironment ce = CsmUtil.getCloudEnvironment(ssContainer);
		if(ce == null){
			throw new UnsupportedOperationException("Software service container is not contained in CloudEnvironment");
		}
		
		dss.setSoftwareLayer(ce.getSoftwareLayer());
		return dss;
	}
	
	public void deleteEntry(Object o){
		removeEntry(o);
		
		if(o instanceof DeployableSoftwareService){
			DeployableSoftwareService dss = (DeployableSoftwareService)o;
			CsmUtil.deleteInterfaces(dss.getProvidedInterfaces());
			ssContainer.getSoftwareServices().remove(dss);
		}
	}
	
	@Override
	public boolean canCreateDeleteEntry() {
		return true;
	}
}
