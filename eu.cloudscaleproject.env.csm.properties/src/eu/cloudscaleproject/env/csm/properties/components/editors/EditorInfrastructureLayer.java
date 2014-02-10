package eu.cloudscaleproject.env.csm.properties.components.editors;

import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer;
import eu.cloudscaleproject.env.csm.architecture.InfrastructureService;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public class EditorInfrastructureLayer extends AbstractEditorList<InfrastructureService>{

	private final InfrastructureLayer layer;
	
	public EditorInfrastructureLayer(Composite c, InfrastructureLayer layer) {
		super(c, layer, layer.getServices());
		this.layer = layer;
		
		setName("Computing infrastructures.");
	}
	
	@Override
	public ComputingInfrastructureService createEntry() {
		return CsmUtil.createDefaultComputingInfrastructureService(CsmUtil.getCloudEnvironment(layer));
	}
	
	@Override
	public void deleteEntry(Object o) {
		layer.getServices().remove(o);
	}

	@Override
	public boolean canCreateDeleteEntry() {
		return true;
	}

}
