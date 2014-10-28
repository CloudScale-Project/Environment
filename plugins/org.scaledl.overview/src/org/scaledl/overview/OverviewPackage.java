/**
 */
package org.scaledl.overview;

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
 * <!-- begin-model-doc -->
 * This package is the root package of all packages of the CloudScale Model (CSM).
 * <!-- end-model-doc -->
 * @see org.scaledl.overview.OverviewFactory
 * @model kind="package"
 * @generated
 */
public interface OverviewPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "overview";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org.scaledl.overview/ScaleDLOverviewComponentModel/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "overview";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OverviewPackage eINSTANCE = org.scaledl.overview.impl.OverviewPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.scaledl.overview.impl.OverviewImpl <em>Overview</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.impl.OverviewImpl
	 * @see org.scaledl.overview.impl.OverviewPackageImpl#getOverview()
	 * @generated
	 */
	int OVERVIEW = 0;

	/**
	 * The feature id for the '<em><b>Architecture</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OVERVIEW__ARCHITECTURE = 0;

	/**
	 * The feature id for the '<em><b>Deployment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OVERVIEW__DEPLOYMENT = 1;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OVERVIEW__DEFINITION = 2;

	/**
	 * The feature id for the '<em><b>Data Types</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OVERVIEW__DATA_TYPES = 3;

	/**
	 * The number of structural features of the '<em>Overview</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OVERVIEW_FEATURE_COUNT = 4;


	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.Overview <em>Overview</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Overview</em>'.
	 * @see org.scaledl.overview.Overview
	 * @generated
	 */
	EClass getOverview();

	/**
	 * Returns the meta object for the containment reference '{@link org.scaledl.overview.Overview#getArchitecture <em>Architecture</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Architecture</em>'.
	 * @see org.scaledl.overview.Overview#getArchitecture()
	 * @see #getOverview()
	 * @generated
	 */
	EReference getOverview_Architecture();

	/**
	 * Returns the meta object for the containment reference '{@link org.scaledl.overview.Overview#getDeployment <em>Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Deployment</em>'.
	 * @see org.scaledl.overview.Overview#getDeployment()
	 * @see #getOverview()
	 * @generated
	 */
	EReference getOverview_Deployment();

	/**
	 * Returns the meta object for the containment reference '{@link org.scaledl.overview.Overview#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Definition</em>'.
	 * @see org.scaledl.overview.Overview#getDefinition()
	 * @see #getOverview()
	 * @generated
	 */
	EReference getOverview_Definition();

	/**
	 * Returns the meta object for the containment reference '{@link org.scaledl.overview.Overview#getDataTypes <em>Data Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Types</em>'.
	 * @see org.scaledl.overview.Overview#getDataTypes()
	 * @see #getOverview()
	 * @generated
	 */
	EReference getOverview_DataTypes();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OverviewFactory getOverviewFactory();

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
		 * The meta object literal for the '{@link org.scaledl.overview.impl.OverviewImpl <em>Overview</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.impl.OverviewImpl
		 * @see org.scaledl.overview.impl.OverviewPackageImpl#getOverview()
		 * @generated
		 */
		EClass OVERVIEW = eINSTANCE.getOverview();

		/**
		 * The meta object literal for the '<em><b>Architecture</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OVERVIEW__ARCHITECTURE = eINSTANCE.getOverview_Architecture();

		/**
		 * The meta object literal for the '<em><b>Deployment</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OVERVIEW__DEPLOYMENT = eINSTANCE.getOverview_Deployment();

		/**
		 * The meta object literal for the '<em><b>Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OVERVIEW__DEFINITION = eINSTANCE.getOverview_Definition();

		/**
		 * The meta object literal for the '<em><b>Data Types</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OVERVIEW__DATA_TYPES = eINSTANCE.getOverview_DataTypes();

	}

} //OverviewPackage
