/**
 */
package eu.cloudscaleproject.env.csm.definition;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Availability Zone Descriptor</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The AvaliabilityZoneDescriptor specify the properties of the cloud availability zones (see CloudProvider). 
 * In the current version only Entity properties are available; name and description.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.AvailabilityZoneDescriptor#getInternalConnectionDescriptor <em>Internal Connection Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#getAvailabilityZoneDescriptor()
 * @model
 * @generated
 */
public interface AvailabilityZoneDescriptor extends Descriptor {
	/**
	 * Returns the value of the '<em><b>Internal Connection Descriptor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Internal Connection Descriptor</em>'
	 * containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Internal Connection Descriptor</em>' containment reference.
	 * @see #setInternalConnectionDescriptor(NetworkInfrastructureServiceDescriptor)
	 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#getAvailabilityZoneDescriptor_InternalConnectionDescriptor()
	 * @model containment="true" required="true"
	 * @generated
	 */
	NetworkInfrastructureServiceDescriptor getInternalConnectionDescriptor();

	/**
	 * Sets the value of the '
	 * {@link eu.cloudscaleproject.env.csm.definition.AvailabilityZoneDescriptor#getInternalConnectionDescriptor
	 * <em>Internal Connection Descriptor</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Internal Connection Descriptor</em>'
	 *            containment reference.
	 * @see #getInternalConnectionDescriptor()
	 * @generated
	 */
	void setInternalConnectionDescriptor(
			NetworkInfrastructureServiceDescriptor value);

} // AvailabilityZoneDescriptor
