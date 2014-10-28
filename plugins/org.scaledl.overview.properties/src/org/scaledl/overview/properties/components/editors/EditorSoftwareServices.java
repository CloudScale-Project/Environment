package org.scaledl.overview.properties.components.editors;

import org.eclipse.swt.widgets.Composite;
import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.architecture.SoftwareServiceContainer;
import org.scaledl.overview.util.OverviewArchitectureUtil;
import org.scaledl.overview.util.OverviewUtil;

public class EditorSoftwareServices extends AbstractEditorList<SoftwareService>{

	private final SoftwareServiceContainer ssContainer;
	
	public EditorSoftwareServices(Composite c, SoftwareServiceContainer ssContainer){
		super(c, ssContainer, ssContainer.getSoftwareServices());
		this.ssContainer = ssContainer;
		
		setName("Edit software services.");
	}

	@Override
	public SoftwareService createEntry() {
		SoftwareService dss = ArchitectureFactory.eINSTANCE.createSoftwareService();
		dss.setName("NoName");
		dss.setRuntimePlatformService(ssContainer);
		
		CloudEnvironment ce = OverviewUtil.getCloudEnvironment(ssContainer);
		if(ce == null){
			throw new UnsupportedOperationException("Software service container is not contained in CloudEnvironment");
		}
		
		dss.setSoftwareLayer(ce.getSoftwareLayer());
		return dss;
	}
	
	public void deleteEntry(Object o){
		removeEntry(o);
		
		if(o instanceof SoftwareService){
			SoftwareService dss = (SoftwareService)o;
			OverviewArchitectureUtil.deleteInterfaces(dss.getProvidedInterfaces());
			ssContainer.getSoftwareServices().remove(dss);
		}
	}
	
	@Override
	public boolean canCreateDeleteEntry() {
		return true;
	}
}
