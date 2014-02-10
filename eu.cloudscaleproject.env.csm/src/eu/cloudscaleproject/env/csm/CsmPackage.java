/**
 */
package eu.cloudscaleproject.env.csm;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc --> <!-- begin-model-doc --> This package is the root
 * package of all packages of the CloudScale Model (CSM). <!-- end-model-doc -->
 * 
 * @see eu.cloudscaleproject.env.csm.CsmFactory
 * @model kind="package"
 * @generated
 */
public interface CsmPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "csm";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://eu.cloudscaleproject.env/CloudscaleComponentModel/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "csm";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	CsmPackage eINSTANCE = eu.cloudscaleproject.env.csm.impl.CsmPackageImpl.init();

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.impl.CsmImpl <em>Csm</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.impl.CsmImpl
	 * @see eu.cloudscaleproject.env.csm.impl.CsmPackageImpl#getCsm()
	 * @generated
	 */
	int CSM = 0;

	/**
	 * The feature id for the '<em><b>Architecture</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CSM__ARCHITECTURE = 0;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CSM__DEFINITION = 1;

	/**
	 * The number of structural features of the '<em>Csm</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSM_FEATURE_COUNT = 2;

	/**
	 * Returns the meta object for class '
	 * {@link eu.cloudscaleproject.env.csm.Csm <em>Csm</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Csm</em>'.
	 * @see eu.cloudscaleproject.env.csm.Csm
	 * @generated
	 */
	EClass getCsm();

	/**
	 * Returns the meta object for the containment reference '{@link eu.cloudscaleproject.env.csm.Csm#getArchitecture <em>Architecture</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Architecture</em>'.
	 * @see eu.cloudscaleproject.env.csm.Csm#getArchitecture()
	 * @see #getCsm()
	 * @generated
	 */
	EReference getCsm_Architecture();

	/**
	 * Returns the meta object for the containment reference '{@link eu.cloudscaleproject.env.csm.Csm#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Definition</em>'.
	 * @see eu.cloudscaleproject.env.csm.Csm#getDefinition()
	 * @see #getCsm()
	 * @generated
	 */
	EReference getCsm_Definition();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CsmFactory getCsmFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that
	 * represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.impl.CsmImpl <em>Csm</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.impl.CsmImpl
		 * @see eu.cloudscaleproject.env.csm.impl.CsmPackageImpl#getCsm()
		 * @generated
		 */
		EClass CSM = eINSTANCE.getCsm();

		/**
		 * The meta object literal for the '<em><b>Architecture</b></em>' containment reference feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference CSM__ARCHITECTURE = eINSTANCE.getCsm_Architecture();

		/**
		 * The meta object literal for the '<em><b>Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference CSM__DEFINITION = eINSTANCE.getCsm_Definition();

	}

} // CsmPackage
