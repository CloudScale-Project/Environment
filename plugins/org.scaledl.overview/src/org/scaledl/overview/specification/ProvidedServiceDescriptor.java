/**
 */
package org.scaledl.overview.specification;

import org.scaledl.overview.specification.sla.Sla;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provided Service Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.ProvidedServiceDescriptor#getSla <em>Sla</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.specification.SpecificationPackage#getProvidedServiceDescriptor()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ProvidedServiceDescriptor extends Descriptor {
	/**
	 * Returns the value of the '<em><b>Sla</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sla</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sla</em>' containment reference.
	 * @see #setSla(Sla)
	 * @see org.scaledl.overview.specification.SpecificationPackage#getProvidedServiceDescriptor_Sla()
	 * @model containment="true"
	 * @generated
	 */
	Sla getSla();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.specification.ProvidedServiceDescriptor#getSla <em>Sla</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sla</em>' containment reference.
	 * @see #getSla()
	 * @generated
	 */
	void setSla(Sla value);

} // ProvidedServiceDescriptor
