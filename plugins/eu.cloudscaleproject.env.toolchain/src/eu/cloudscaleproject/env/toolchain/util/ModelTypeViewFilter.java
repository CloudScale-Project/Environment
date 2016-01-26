package eu.cloudscaleproject.env.toolchain.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import eu.cloudscaleproject.env.toolchain.ModelType;

public class ModelTypeViewFilter extends ViewerFilter{

	private final ModelType[] modelTypes;
	private final boolean filterPlatformResources;
	
	public ModelTypeViewFilter(ModelType[] modelTypes, boolean filterPlatformResources) {
		this.modelTypes = modelTypes;
		this.filterPlatformResources = filterPlatformResources;
	}
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		
		if(parentElement instanceof ResourceSet || parentElement == null){
			if(element instanceof EObject){
				
				EObject eo = (EObject)element;
				Resource res = (Resource)eo.eResource();
				
				if(res != null && filterPlatformResources){
					if(!res.getURI().isPlatformResource()){
						return false;
					}
				}
				
				ModelType type = ModelType.filterModelType(modelTypes, eo.eResource());
				if(type == null){
					return false;
				}
				
			}
		}
		return true;
	}
	
}
