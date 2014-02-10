package eu.cloudscaleproject.env.csm.properties;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;

public class PropertyAdapterFactory implements IAdapterFactory {

	public PropertyAdapterFactory() {
		super();
	}

	public Object getAdapter(Object adaptableObject, @SuppressWarnings("rawtypes") Class adapterType) {
		/*
		if (IPropertySource.class.equals(adapterType)) {
			if (adaptableObject instanceof GraphitiConnectionEditPart) {
				GraphitiConnectionEditPart editPart = (GraphitiConnectionEditPart) adaptableObject;
				PictogramElement pictogramElement = editPart.getPictogramElement();
				Object object = editPart.getFeatureProvider().getBusinessObjectForPictogramElement(pictogramElement);
				if (object instanceof EReference) {
					return new PropertyEReferenceSource((EReference) object);
				}
			}
		}
		*/
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Class[] getAdapterList() {
		return new Class[] { IPropertySource.class };
	}
}

