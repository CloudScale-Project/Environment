/**
 */
package org.scaledl.overview.specification.sla;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sla</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.sla.Sla#getPriceModel <em>Price Model</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.sla.Sla#getSlo <em>Slo</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.specification.sla.SlaPackage#getSla()
 * @model
 * @generated
 */
public interface Sla extends EObject {
	/**
	 * Returns the value of the '<em><b>Price Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Price Model</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Price Model</em>' containment reference.
	 * @see #setPriceModel(PriceModel)
	 * @see org.scaledl.overview.specification.sla.SlaPackage#getSla_PriceModel()
	 * @model containment="true"
	 * @generated
	 */
	PriceModel getPriceModel();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.specification.sla.Sla#getPriceModel <em>Price Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Price Model</em>' containment reference.
	 * @see #getPriceModel()
	 * @generated
	 */
	void setPriceModel(PriceModel value);

	/**
	 * Returns the value of the '<em><b>Slo</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Slo</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Slo</em>' containment reference.
	 * @see #setSlo(Slo)
	 * @see org.scaledl.overview.specification.sla.SlaPackage#getSla_Slo()
	 * @model containment="true"
	 * @generated
	 */
	Slo getSlo();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.specification.sla.Sla#getSlo <em>Slo</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Slo</em>' containment reference.
	 * @see #getSlo()
	 * @generated
	 */
	void setSlo(Slo value);

} // Sla
