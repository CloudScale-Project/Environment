/**
 */
package org.scaledl.overview.specification;

import org.scaledl.overview.core.Entity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The Descriptor model is used to specify the behaviour of the CSM architecture elements (e.g. components, connectors, cloud providers, …). For the descriptors that are available in the specific cloud provider, the provider id is defined (e.g. specification of services provided by specific PaaS provider). 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.Descriptor#getProviderID <em>Provider ID</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.specification.SpecificationPackage#getDescriptor()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Descriptor extends Entity {
	/**
	 * Returns the value of the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Provider ID</em>' attribute.
	 * @see #setProviderID(String)
	 * @see org.scaledl.overview.specification.SpecificationPackage#getDescriptor_ProviderID()
	 * @model
	 * @generated
	 */
	String getProviderID();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.specification.Descriptor#getProviderID <em>Provider ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Provider ID</em>' attribute.
	 * @see #getProviderID()
	 * @generated
	 */
	void setProviderID(String value);

} // Descriptor
