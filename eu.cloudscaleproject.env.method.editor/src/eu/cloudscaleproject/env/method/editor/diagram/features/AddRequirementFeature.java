package eu.cloudscaleproject.env.method.editor.diagram.features;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import eu.cloudscaleproject.env.method.common.method.Requirement;
import eu.cloudscaleproject.env.method.common.method.Section;
import eu.cloudscaleproject.env.method.common.util.MethodUtil;

public class AddRequirementFeature extends AbstractCustomFeature{

	public AddRequirementFeature(IFeatureProvider fp) {
		super(fp);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getName() {
		return "Add requirement";
	}
	
	private Section getSection(ICustomContext context){
		EObject el = context.getInnerPictogramElement();
		if(el == null && context.getPictogramElements().length > 0){
			el = context.getPictogramElements()[0];
		}
		
		while(el != null){
			if(el instanceof PictogramElement){
				Object bo = getBusinessObjectForPictogramElement((PictogramElement)el);
				if(bo instanceof Section){
					return (Section)bo;
				}
			}
			el = el.eContainer();
		}
		
		return null;
	}
	
	@Override
	public boolean canExecute(ICustomContext context) {
		if(getSection(context) != null){
			return true;
		}
		return false;
	}

	@Override
	public void execute(ICustomContext context) {
		Section section = getSection(context);
		Requirement r = MethodUtil.createRequirement();
		section.getRequirements().add(r);
	}

}
