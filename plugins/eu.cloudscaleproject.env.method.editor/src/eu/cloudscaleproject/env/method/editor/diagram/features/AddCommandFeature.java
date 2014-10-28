package eu.cloudscaleproject.env.method.editor.diagram.features;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import eu.cloudscaleproject.env.method.common.method.Command;
import eu.cloudscaleproject.env.method.common.method.Container;
import eu.cloudscaleproject.env.method.common.method.Node;
import eu.cloudscaleproject.env.method.common.method.Section;
import eu.cloudscaleproject.env.method.common.util.MethodUtil;

public class AddCommandFeature extends AbstractCustomFeature{

	public AddCommandFeature(IFeatureProvider fp) {
		super(fp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Add command";
	}
	
	private Node getNode(ICustomContext context){
		EObject el = context.getInnerPictogramElement();
		if(el == null && context.getPictogramElements().length > 0){
			el = context.getPictogramElements()[0];
		}
		
		while(el != null){
			if(el instanceof PictogramElement){
				Object bo = getBusinessObjectForPictogramElement((PictogramElement)el);
				if(bo instanceof Section || bo instanceof Container){
					return (Node)bo;
				}
			}
			el = el.eContainer();
		}
		
		return null;
	}
	
	@Override
	public boolean canExecute(ICustomContext context) {
		if(getNode(context) != null){
			return true;
		}
		return false;
	}

	@Override
	public void execute(ICustomContext context) {
		
		Node node = getNode(context);
		Command command = MethodUtil.createCommand();
		
		if(node instanceof Container){
			Container c = (Container)node;
			c.getCommands().add(command);
		}
		else if(node instanceof Section){
			Section s = (Section)node;
			s.getCommands().add(command);
		}
	}

}
