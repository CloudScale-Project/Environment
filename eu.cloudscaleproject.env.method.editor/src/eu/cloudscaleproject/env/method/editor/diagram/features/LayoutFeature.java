package eu.cloudscaleproject.env.method.editor.diagram.features;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

public class LayoutFeature extends AbstractCustomFeature{

	public LayoutFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getName() {
		return "Layout pictogram";
	}
	
	private PictogramElement getPictogram(ICustomContext context){
		EObject el = context.getInnerPictogramElement();
		if(el == null && context.getPictogramElements().length > 0){
			el = context.getPictogramElements()[0];
		}
		
		while(el != null){
			if(el instanceof PictogramElement){
				return (PictogramElement)el;
			}
			el = el.eContainer();
		}
		
		return null;
	}
	
	@Override
	public boolean canExecute(ICustomContext context) {
		if(getPictogram(context) != null){
			return true;
		}
		return false;
	}

	@Override
	public void execute(ICustomContext context) {
		
		ILayoutContext c = new LayoutContext(getPictogram(context));
		ILayoutFeature layoutFeature = getFeatureProvider().getLayoutFeature(c);
		if(layoutFeature != null && layoutFeature.canExecute(c)){
			layoutFeature.execute(c);
		}
	}
	
}
