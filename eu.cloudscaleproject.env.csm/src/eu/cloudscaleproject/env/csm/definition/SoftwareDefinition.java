/**
 */
package eu.cloudscaleproject.env.csm.definition;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Software Definition</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The SoftwareDefinition model specifies the deployable 3rd party software that can be used to specify node components (see Node) or used operating systems on the instances (see Instance).
 * This model is not meant to be included into CSM model, but it provides ability for specifying different software bundles, which descriptors can be assigned to CSM model entities.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.SoftwareDefinition#getDeployablePlatformServiceDescriptors <em>Deployable Platform Service Descriptors</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#getSoftwareDefinition()
 * @model
 * @generated
 */
public interface SoftwareDefinition extends Definition {
	/**
	 * Returns the value of the '
	 * <em><b>Deployable Platform Service Descriptors</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link eu.cloudscaleproject.env.csm.definition.DeployablePlatformServiceDescriptor}
	 * . <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '
	 *         <em>Deployable Platform Service Descriptors</em>' containment
	 *         reference list.
	 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#getSoftwareDefinition_DeployablePlatformServiceDescriptors()
	 * @model containment="true"
	 * @generated
	 */
	EList<DeployablePlatformServiceDescriptor> getDeployablePlatformServiceDescriptors();

} // SoftwareDefinition
