package org.scaledl.overview.properties.components.editors;

import org.eclipse.swt.widgets.Composite;
import org.scaledl.overview.deployment.DeploymentFactory;
import org.scaledl.overview.deployment.ScalingManager;
import org.scaledl.overview.deployment.ScalingPolicy;

public class EditorScalingManager extends AbstractEditorList<ScalingPolicy>{

	private final ScalingManager scalingManager;
	
	public EditorScalingManager(Composite c, ScalingManager sm){
		super(c, sm, sm.getScalingPolicy());
		this.scalingManager = sm;
		
		setName("Scaling policies:");
	}

	@Override
	public ScalingPolicy createEntry() {
		ScalingPolicy sp = DeploymentFactory.eINSTANCE.createScalingPolicy();
		scalingManager.getScalingPolicy().add(sp);
		return sp;
	}
	
	@Override
	public void deleteEntry(Object o) {
		scalingManager.getScalingPolicy().remove(o);
	}

	@Override
	public boolean canCreateDeleteEntry() {
		return true;
	}
}
