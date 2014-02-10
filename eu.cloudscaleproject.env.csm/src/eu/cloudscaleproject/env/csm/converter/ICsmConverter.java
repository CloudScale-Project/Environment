package eu.cloudscaleproject.env.csm.converter;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import eu.cloudscaleproject.env.csm.core.Entity;

public interface ICsmConverter {

	public final static String KEY_PCM_SYSTEM = "PCM_SYSTEM";
	public final static String KEY_PCM_REPOSITORY = "PCM_REPOSITORY";
	public final static String KEY_PCM_INTERFACE = "PCM_INTERFACE";

	public boolean addExternalModel(Entity object, List<EObject> external,
			ICSMConverterCallback callback);

	public boolean removeExternalModel(Entity object, List<EObject> external,
			ICSMConverterCallback callback);

	public void transform(Resource resource);

	public boolean canTransform(Resource resource);

}
