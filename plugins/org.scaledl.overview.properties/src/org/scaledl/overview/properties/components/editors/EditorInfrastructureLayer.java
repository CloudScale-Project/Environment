package org.scaledl.overview.properties.components.editors;

import org.eclipse.swt.widgets.Composite;
import org.scaledl.overview.architecture.ComputingInfrastructureService;
import org.scaledl.overview.architecture.InfrastructureLayer;
import org.scaledl.overview.architecture.InfrastructureService;
import org.scaledl.overview.util.OverviewArchitectureUtil;
import org.scaledl.overview.util.OverviewUtil;

public class EditorInfrastructureLayer extends AbstractEditorList<InfrastructureService>{

	private final InfrastructureLayer layer;
	
	public EditorInfrastructureLayer(Composite c, InfrastructureLayer layer) {
		super(c, layer, layer.getServices());
		this.layer = layer;
		
		setName("Computing infrastructures.");
	}
	
	@Override
	public ComputingInfrastructureService createEntry() {
		return OverviewArchitectureUtil.createComputingInfrastructureService(OverviewUtil.getCloudEnvironment(layer),null);
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
