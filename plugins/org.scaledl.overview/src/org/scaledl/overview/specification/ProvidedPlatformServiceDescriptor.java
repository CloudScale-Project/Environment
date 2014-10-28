/**
 */
package org.scaledl.overview.specification;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provided Platform Service Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.ProvidedPlatformServiceDescriptor#getInfrastructureServiceDescriptor <em>Infrastructure Service Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.specification.SpecificationPackage#getProvidedPlatformServiceDescriptor()
 * @model
 * @generated
 */
public interface ProvidedPlatformServiceDescriptor extends PlatformServiceDescriptor, ProvidedServiceDescriptor {
	/**
	 * Returns the value of the '<em><b>Infrastructure Service Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Infrastructure Service Descriptor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Infrastructure Service Descriptor</em>' reference.
	 * @see #setInfrastructureServiceDescriptor(ComputingInfrastructureServiceDescriptor)
	 * @see org.scaledl.overview.specification.SpecificationPackage#getProvidedPlatformServiceDescriptor_InfrastructureServiceDescriptor()
	 * @model
	 * @generated
	 */
	ComputingInfrastructureServiceDescriptor getInfrastructureServiceDescriptor();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.specification.ProvidedPlatformServiceDescriptor#getInfrastructureServiceDescriptor <em>Infrastructure Service Descriptor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Infrastructure Service Descriptor</em>' reference.
	 * @see #getInfrastructureServiceDescriptor()
	 * @generated
	 */
	void setInfrastructureServiceDescriptor(ComputingInfrastructureServiceDescriptor value);

} // ProvidedPlatformServiceDescriptor
