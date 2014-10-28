/**
 */
package org.scaledl.overview.architecture;

import org.scaledl.overview.specification.ProvidedSoftwareServiceDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provided Software Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.architecture.ProvidedSoftwareService#getDescriptor <em>Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.architecture.ArchitecturePackage#getProvidedSoftwareService()
 * @model
 * @generated
 */
public interface ProvidedSoftwareService extends SoftwareService, ProvidedService {
	/**
	 * Returns the value of the '<em><b>Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptor</em>' reference.
	 * @see #setDescriptor(ProvidedSoftwareServiceDescriptor)
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getProvidedSoftwareService_Descriptor()
	 * @model required="true"
	 * @generated
	 */
	ProvidedSoftwareServiceDescriptor getDescriptor();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.architecture.ProvidedSoftwareService#getDescriptor <em>Descriptor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Descriptor</em>' reference.
	 * @see #getDescriptor()
	 * @generated
	 */
	void setDescriptor(ProvidedSoftwareServiceDescriptor value);

} // ProvidedSoftwareService
