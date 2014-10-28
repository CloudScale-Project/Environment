/**
 */
package org.scaledl.overview.specification.sla;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.scaledl.overview.specification.sla.SlaFactory
 * @model kind="package"
 * @generated
 */
public interface SlaPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "sla";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://eu.cloudscaleproject.env/ScaleDLOverviewComponentModel/Specification/Sla/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "sla";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SlaPackage eINSTANCE = org.scaledl.overview.specification.sla.impl.SlaPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.sla.impl.SlaImpl <em>Sla</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.sla.impl.SlaImpl
	 * @see org.scaledl.overview.specification.sla.impl.SlaPackageImpl#getSla()
	 * @generated
	 */
	int SLA = 0;

	/**
	 * The feature id for the '<em><b>Price Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLA__PRICE_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Slo</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLA__SLO = 1;

	/**
	 * The number of structural features of the '<em>Sla</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLA_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.sla.impl.PriceModelImpl <em>Price Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.sla.impl.PriceModelImpl
	 * @see org.scaledl.overview.specification.sla.impl.SlaPackageImpl#getPriceModel()
	 * @generated
	 */
	int PRICE_MODEL = 1;

	/**
	 * The number of structural features of the '<em>Price Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRICE_MODEL_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.sla.impl.SloImpl <em>Slo</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.sla.impl.SloImpl
	 * @see org.scaledl.overview.specification.sla.impl.SlaPackageImpl#getSlo()
	 * @generated
	 */
	int SLO = 2;

	/**
	 * The number of structural features of the '<em>Slo</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLO_FEATURE_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.sla.Sla <em>Sla</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sla</em>'.
	 * @see org.scaledl.overview.specification.sla.Sla
	 * @generated
	 */
	EClass getSla();

	/**
	 * Returns the meta object for the containment reference '{@link org.scaledl.overview.specification.sla.Sla#getPriceModel <em>Price Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Price Model</em>'.
	 * @see org.scaledl.overview.specification.sla.Sla#getPriceModel()
	 * @see #getSla()
	 * @generated
	 */
	EReference getSla_PriceModel();

	/**
	 * Returns the meta object for the containment reference '{@link org.scaledl.overview.specification.sla.Sla#getSlo <em>Slo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Slo</em>'.
	 * @see org.scaledl.overview.specification.sla.Sla#getSlo()
	 * @see #getSla()
	 * @generated
	 */
	EReference getSla_Slo();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.sla.PriceModel <em>Price Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Price Model</em>'.
	 * @see org.scaledl.overview.specification.sla.PriceModel
	 * @generated
	 */
	EClass getPriceModel();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.sla.Slo <em>Slo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Slo</em>'.
	 * @see org.scaledl.overview.specification.sla.Slo
	 * @generated
	 */
	EClass getSlo();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SlaFactory getSlaFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.sla.impl.SlaImpl <em>Sla</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.sla.impl.SlaImpl
		 * @see org.scaledl.overview.specification.sla.impl.SlaPackageImpl#getSla()
		 * @generated
		 */
		EClass SLA = eINSTANCE.getSla();

		/**
		 * The meta object literal for the '<em><b>Price Model</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SLA__PRICE_MODEL = eINSTANCE.getSla_PriceModel();

		/**
		 * The meta object literal for the '<em><b>Slo</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SLA__SLO = eINSTANCE.getSla_Slo();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.sla.impl.PriceModelImpl <em>Price Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.sla.impl.PriceModelImpl
		 * @see org.scaledl.overview.specification.sla.impl.SlaPackageImpl#getPriceModel()
		 * @generated
		 */
		EClass PRICE_MODEL = eINSTANCE.getPriceModel();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.sla.impl.SloImpl <em>Slo</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.sla.impl.SloImpl
		 * @see org.scaledl.overview.specification.sla.impl.SlaPackageImpl#getSlo()
		 * @generated
		 */
		EClass SLO = eINSTANCE.getSlo();

	}

} //SlaPackage
