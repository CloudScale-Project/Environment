/**
 */
package org.scaledl.overview.specification;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Availability Zone Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The AvaliabilityZoneDescriptor specify the properties of the cloud availability zones (see CloudProvider). 
 * In the current version only Entity properties are available; name and description.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.AvailabilityZoneDescriptor#getNetworkInfrastructureServiceDescriptor <em>Network Infrastructure Service Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.specification.SpecificationPackage#getAvailabilityZoneDescriptor()
 * @model
 * @generated
 */
public interface AvailabilityZoneDescriptor extends Descriptor {
	/**
	 * Returns the value of the '<em><b>Network Infrastructure Service Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Network Infrastructure Service Descriptor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Network Infrastructure Service Descriptor</em>' reference.
	 * @see #setNetworkInfrastructureServiceDescriptor(NetworkInfrastructureServiceDescriptor)
	 * @see org.scaledl.overview.specification.SpecificationPackage#getAvailabilityZoneDescriptor_NetworkInfrastructureServiceDescriptor()
	 * @model required="true"
	 * @generated
	 */
	NetworkInfrastructureServiceDescriptor getNetworkInfrastructureServiceDescriptor();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.specification.AvailabilityZoneDescriptor#getNetworkInfrastructureServiceDescriptor <em>Network Infrastructure Service Descriptor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Network Infrastructure Service Descriptor</em>' reference.
	 * @see #getNetworkInfrastructureServiceDescriptor()
	 * @generated
	 */
	void setNetworkInfrastructureServiceDescriptor(NetworkInfrastructureServiceDescriptor value);

} // AvailabilityZoneDescriptor
