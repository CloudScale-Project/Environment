package org.scaledl.overview.util;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.scaledl.overview.Overview;
import org.scaledl.overview.OverviewFactory;
import org.scaledl.overview.application.Operation;
import org.scaledl.overview.architecture.Architecture;
import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.converter.IOverviewConverter;
import org.scaledl.overview.core.Entity;
import org.scaledl.overview.deployment.Deployment;
import org.scaledl.overview.deployment.DeploymentFactory;
import org.scaledl.overview.parametertype.Parameter;
import org.scaledl.overview.parametertype.ParameterType;
import org.scaledl.overview.parametertype.ParametertypeFactory;
import org.scaledl.overview.parametertype.PrimitiveParameter;
import org.scaledl.overview.parametertype.TypeEnum;
import org.scaledl.overview.specification.Descriptor;
import org.scaledl.overview.specification.Specification;
import org.scaledl.overview.specification.SpecificationFactory;
import org.scaledl.overview.specification.SystemSpecification;

public class OverviewUtil {
	
	
	public static Overview createOverviewModel() {
		Overview csm = OverviewFactory.eINSTANCE.createOverview();
		
		Architecture architecture = ArchitectureFactory.eINSTANCE.createArchitecture();
		Deployment deployment = DeploymentFactory.eINSTANCE.createDeployment();
		SystemSpecification definition = SpecificationFactory.eINSTANCE.createSystemSpecification();

		
		//TODO: configure this by using specification?
		//create primitive dataTypes
		ParameterType dataType = ParametertypeFactory.eINSTANCE.createParameterType();
		for(TypeEnum e : TypeEnum.values()){
			PrimitiveParameter param = ParametertypeFactory.eINSTANCE.createPrimitiveParameter();
			param.setName(e.getLiteral());
			param.setDescription("Default primitive data type");
			param.setType(e);
			dataType.getTypes().add(param);
		}
		
		csm.setArchitecture(architecture);
		csm.setDeployment(deployment);
		csm.setDefinition(definition);
		csm.setDataTypes(dataType);

		return csm;
	}
	/**
	 * 
	 * Returns <code>Architecture</code> using a arbitrary <code>EObject</code>, that is contained in the Overview model.
	 * 
	 * @param eobject Arbitrary <code>EObject</code> contained in Overview model.
	 * @return <code>Architecture</code> which contains services from different cloud providers and connections between them.
	 */
	public static Architecture getArchitecture(EObject eobject){
		EObject root = EcoreUtil.getRootContainer(eobject);
		
		if(root instanceof Overview){
			Overview csm = (Overview)root;
			return csm.getArchitecture();
		}
		return null;
	}
	
	/**
	 * 
	 * Returns <code>Deployment</code> using a arbitrary <code>EObject</code>, that is contained in the Overview model.
	 * 
	 * @param eobject Arbitrary <code>EObject</code> contained in Overview model.
	 * @return <code>Deployment</code> which contains hardware components, provided by different cloud providers.
	 */
	public static Deployment getDeployment(EObject eobject){
		EObject root = EcoreUtil.getRootContainer(eobject);
		
		if(root instanceof Overview){
			Overview overview = (Overview)root;
			return overview.getDeployment();
		}
		return null;
	}
	
	/**
	 * 
	 * Returns <code>Specification</code> using a arbitrary <code>EObject</code>, that is contained in the Overview model.
	 * 
	 * @param eobject Arbitrary <code>EObject</code> contained in Overview model.
	 * @return <code>Definition</code> which contains descriptors, that are currently<br>
	 * 		   used in <code>Architecture</code> or <code>Infrastructure</code> Overview model package.
	 */
	public static Specification getSpecification(EObject eobject){
		EObject root = EcoreUtil.getRootContainer(eobject);
		
		if(root instanceof Overview){
			Overview overview = (Overview)root;
			return overview.getDefinition();
		}
		
		return null;
	}
	
	/**
	 * 
	 * Returns <code>CloudEnvironment</code> using a arbitrary <code>EObject</code>, that is contained in the <code>CloudEnvironment</code>.
	 * 
	 * @param eobject Arbitrary <code>EObject</code> contained in <code>CloudEnvironment</code>.
	 * @return <code>CloudEnvironemnt</code> which contains services, provided by specific cloud provider, region or availability zone, and connections between them.
	 */
	public static CloudEnvironment getCloudEnvironment(EObject eobject){
		EObject current = eobject;
		while(current != null){
			if(current instanceof CloudEnvironment){
				return (CloudEnvironment)current;
			}
			current = current.eContainer();
		}
		return null;
	}
	
	/**
	 * 
	 * Returns <code>DataType</code> using a arbitrary <code>EObject</code>, that is contained in the <code>Overview</code> model.
	 * 
	 * @param eobject Arbitrary <code>EObject</code> contained in <code>Overview</code> model.
	 * @return <code>DataType</code> which contains different data types used throughout <code>Overview</code> model.
	 */
	public static ParameterType getDataTypes(EObject eobject){
		EObject current = eobject;
		while(current != null){
			if(current instanceof Overview){
				return ((Overview)current).getDataTypes();
			}
			current = current.eContainer();
		}
		return null;
	}
	
	/**
	 * 
	 * Returns <code>CloudEnvironemnt</code> from collection of cloud environments<br>,
	 * which is contained in the specified <code>Architecture</code>.
	 * 
	 * @param architecture <code>Architecture</code> object from which to retrieve <code>CloudEnvironment</code>
	 * @param providerID String that uniquely identifies specific cloud provider.
	 * @return <code>CloudEnvironment</code>
	 */
	public static CloudEnvironment getCloudEnvironment(Architecture architecture, String providerID){
		CloudEnvironment ce = null;
		
		for(CloudEnvironment env : architecture.getCloudEnvironments()){
			if(env.getCloudEnvironmentDescriptor().getProviderID() == providerID){
				ce = env;
				break;
			}
		}
		
		return ce;
	}
	
	/**
	 * 
	 * Checks if specified <code>Entity</code> has an external PCM model reference in <code>Entity.aemap()</code>.
	 * This method looks for:<br>
	 * <code>ICsmConverter.KEY_PCM_REPOSITORY</code> or<br>
	 * <code>ICsmConverter.KEY_PCM_SYSTEM</code> or<br>
	 * <code>ICsmConverter.KEY_PCM_INTERFACE</code><br>
	 * key value in <code>Entity.aemap()</code>.
	 * 
	 * @param e <code>Entity</code> to check.
	 * @return TRUE if <code>Entity</code> contains PCM model reference.
	 */
	public static boolean hasExternalModel(Entity e){
		if(e.getAeMap().containsKey(IOverviewConverter.KEY_PCM_REPOSITORY)
				|| e.getAeMap().containsKey(IOverviewConverter.KEY_PCM_SYSTEM)
				|| e.getAeMap().containsKey(IOverviewConverter.KEY_PCM_INTERFACE)){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * Removes all unused descriptors from Overview model definition. 
	 * 
	 * @param overview Overview model on which to run "garbage collector".
	 */
	public static void gcOrphanedObjects (Overview overview)
	{
		for (Descriptor desc : overview.getDefinition().getDescriptors().toArray(new Descriptor[0])) {
			if (isOrphaned(desc))
			{
				overview.getDefinition().getDescriptors().remove(desc);
			}
		}
	}
	
	/**
	 * 
	 * Checks if specified <code>Entity<code> has any usage references in specified entity <code>Resource</code> (<code>entity.eResource()</code>). 
	 * Entity has to be contained in <code>Resource</code>, otherwise null pointer exception will be thrown!
	 * 
	 * @param entity <Entity> in question.
	 * @return TRUE if specified entity has no usage references.
	 */
	public static boolean isOrphaned (Entity entity)
	{
		return EcoreUtil.UsageCrossReferencer.find(entity, entity.eResource()).isEmpty();
	}
	
	/**
	 * 
	 * Provides alternative <code>String</code> representation for some objects declared in Overview meta-model.
	 
	 * This method handles:
	 * 
	 * java: Array - recursion,
	 * java: List - recursion,
	 * Overview meta-model: <code>Operation</code>,
	 * Overview meta-model: <code>Entity</code>.
	 * 
	 * For other object types this method returns <code>object.toString()</code>
	 * 
	 * @param object from which to retrieve string representation.
	 * @return <code>String</code>
	 */
	public static String getTextRepresentation(Object object) {

		if (object instanceof Object[]) {
			String out = "List{";
			Object[] list = (Object[]) object;

			for (Object obj : list) {
				out += getTextRepresentation(obj);
				out += ", ";
			}
			if (list.length > 0) {
				out = out.substring(0, Math.max(out.length() - 2, 0));
			}

			out += "}";
			return out + "}";
		} else if (object instanceof List<?>) {
			String out = "List{";
			List<?> list = (List<?>) object;

			for (Object obj : list) {
				out += getTextRepresentation(obj);
				out += ", ";
			}
			if (list.size() > 0) {
				out = out.substring(0, Math.max(out.length() - 2, 0));
			}
			out += "}";

			return out;
		} else if (object instanceof String) {
			return (String) object;
		} else if (object instanceof Operation) {
			Operation op = (Operation) object;

			String out = getTextRepresentation(op.getReturnParameter()) + " "
					+ op.getName();
			out += " (";
			for (Parameter p : op.getParameters()) {
				if (p != null) {
					out += getTextRepresentation(p) + ", ";
				}
			}
			if (!op.getParameters().isEmpty()) {
				out = out.substring(0, out.length() - 2);
			}
			out += ")";

			return out;
		}
		else if (object instanceof Entity) {
			Entity e = (Entity) object;
			return e.getName();
		} else {
			return String.valueOf(object);
		}
	}
	
}
