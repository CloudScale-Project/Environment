/**
 */
package org.scaledl.overview.specification.sla;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.scaledl.overview.specification.sla.SlaPackage
 * @generated
 */
public interface SlaFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SlaFactory eINSTANCE = org.scaledl.overview.specification.sla.impl.SlaFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Sla</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sla</em>'.
	 * @generated
	 */
	Sla createSla();

	/**
	 * Returns a new object of class '<em>Price Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Price Model</em>'.
	 * @generated
	 */
	PriceModel createPriceModel();

	/**
	 * Returns a new object of class '<em>Slo</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Slo</em>'.
	 * @generated
	 */
	Slo createSlo();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SlaPackage getSlaPackage();

} //SlaFactory
