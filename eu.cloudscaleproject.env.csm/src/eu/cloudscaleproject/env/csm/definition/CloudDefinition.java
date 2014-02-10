/**
 */
package eu.cloudscaleproject.env.csm.definition;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Cloud Definition</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The CloudDefinition model specifies all the details of the cloud provider (see CloudProvider). It consists of cloud provider descriptor, instance descriptors (in case the IaaS clouds), service descriptors (in case of PaaS clouds) and connection descriptors, specifying internal connections (e.g. bandwidth and latency). 
 * This model is not meant to be included into CSM model, but it provides ability for specifying different cloud environments, which descriptors can be assigned to CSM model entities.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.CloudDefinition#getDescriptor <em>Descriptor</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.CloudDefinition#getInfrastructureDescriptors <em>Infrastructure Descriptors</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.CloudDefinition#getPlatformDescriptors <em>Platform Descriptors</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#getCloudDefinition()
 * @model
 * @generated
 */
public interface CloudDefinition extends Definition {
	/**
	 * Returns the value of the '<em><b>Descriptor</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Descriptor</em>' containment reference.
	 * @see #setDescriptor(CloudEnvironmentDescriptor)
	 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#getCloudDefinition_Descriptor()
	 * @model containment="true" required="true"
	 * @generated
	 */
	CloudEnvironmentDescriptor getDescriptor();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.definition.CloudDefinition#getDescriptor <em>Descriptor</em>}' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Descriptor</em>' containment reference.
	 * @see #getDescriptor()
	 * @generated
	 */
	void setDescriptor(CloudEnvironmentDescriptor value);

	/**
	 * Returns the value of the '<em><b>Infrastructure Descriptors</b></em>'
	 * containment reference list. The list contents are of type
	 * {@link eu.cloudscaleproject.env.csm.definition.InfrastructureServiceDescriptor}
	 * . <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Infrastructure Descriptors</em>'
	 *         containment reference list.
	 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#getCloudDefinition_InfrastructureDescriptors()
	 * @model containment="true"
	 * @generated
	 */
	EList<InfrastructureServiceDescriptor> getInfrastructureDescriptors();

	/**
	 * Returns the value of the '<em><b>Platform Descriptors</b></em>'
	 * containment reference list. The list contents are of type
	 * {@link eu.cloudscaleproject.env.csm.definition.ExternalPlatformServiceDescriptor}
	 * . <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Platform Descriptors</em>' containment
	 *         reference list.
	 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#getCloudDefinition_PlatformDescriptors()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExternalPlatformServiceDescriptor> getPlatformDescriptors();

} // CloudDefinition
