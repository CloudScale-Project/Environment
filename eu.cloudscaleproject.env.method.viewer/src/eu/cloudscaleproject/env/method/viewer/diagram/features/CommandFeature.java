package eu.cloudscaleproject.env.method.viewer.diagram.features;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.method.common.method.Node;


public class CommandFeature extends AbstractCustomFeature{
	
	@Optional @Inject
	private CommandExecutor executor;

	public CommandFeature(IFeatureProvider fp) {
		super(fp);
		CloudscaleContext.inject(this);
	}
	
	@Override
	public String getName() {
		return "Run...";
	}
	
	private Object findBusinessObject(ICustomContext context){
		EObject el = context.getInnerPictogramElement();
		if(el == null){
			el = context.getPictogramElements()[0];
		}
						
		while(el != null){
			if(el instanceof PictogramElement){
				Object object = getBusinessObjectForPictogramElement((PictogramElement)el);
				if(object != null){
					return object;
				}
			}
			el = el.eContainer();
		}
		
		return null;
	}
	
	@Override
	public boolean hasDoneChanges() {
		return false;
	}
	
	@Override
	public boolean canExecute(ICustomContext context) {
		
		Object bo = findBusinessObject(context);
		
		if(bo instanceof Node){
			Node node = (Node)bo;
			if(node.getCommandId() != null && !node.getCommandId().isEmpty()){
				return true;
			}
		}
	
		return false;
	}

	@Override
	public void execute(final ICustomContext context) {

		final Object bo = findBusinessObject(context);

		if (bo instanceof Node) {
			Node node = (Node) bo;
			if (executor == null) {
				return;
			}

			if (node.getCommandParam().isEmpty()) {
				executor.execute(node.getCommandId());
			} else {
				executor.execute(node.getCommandId(), node.getCommandParam()
						.toArray(new String[node.getCommandParam().size()]));
			}
		}
	}
}
