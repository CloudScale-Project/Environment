/**
 */
package org.scaledl.overview.application;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.scaledl.overview.core.CorePackage;

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
 * @see org.scaledl.overview.application.ApplicationFactory
 * @model kind="package"
 * @generated
 */
public interface ApplicationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "application";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org.scaledl.overview/ScaleDLOverviewComponentModel/Application/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "application";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ApplicationPackage eINSTANCE = org.scaledl.overview.application.impl.ApplicationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.scaledl.overview.application.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.application.impl.OperationImpl
	 * @see org.scaledl.overview.application.impl.ApplicationPackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Return Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__RETURN_PARAMETER = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__PARAMETERS = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.application.OperationInterfaceContainer <em>Operation Interface Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.application.OperationInterfaceContainer
	 * @see org.scaledl.overview.application.impl.ApplicationPackageImpl#getOperationInterfaceContainer()
	 * @generated
	 */
	int OPERATION_INTERFACE_CONTAINER = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE_CONTAINER__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE_CONTAINER__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE_CONTAINER__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE_CONTAINER__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE_CONTAINER__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Operation Interface Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE_CONTAINER_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.application.impl.OperationInterfaceImpl <em>Operation Interface</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.application.impl.OperationInterfaceImpl
	 * @see org.scaledl.overview.application.impl.ApplicationPackageImpl#getOperationInterface()
	 * @generated
	 */
	int OPERATION_INTERFACE = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE__OPERATIONS = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Providing Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE__PROVIDING_CONTAINER = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Requiring Container</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE__REQUIRING_CONTAINER = CorePackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Operation Interface</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 3;


	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.application.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see org.scaledl.overview.application.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.application.Operation#getReturnParameter <em>Return Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Return Parameter</em>'.
	 * @see org.scaledl.overview.application.Operation#getReturnParameter()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_ReturnParameter();

	/**
	 * Returns the meta object for the reference list '{@link org.scaledl.overview.application.Operation#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parameters</em>'.
	 * @see org.scaledl.overview.application.Operation#getParameters()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Parameters();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.application.OperationInterfaceContainer <em>Operation Interface Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Interface Container</em>'.
	 * @see org.scaledl.overview.application.OperationInterfaceContainer
	 * @generated
	 */
	EClass getOperationInterfaceContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.application.OperationInterfaceContainer#getProvidedInterfaces <em>Provided Interfaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Provided Interfaces</em>'.
	 * @see org.scaledl.overview.application.OperationInterfaceContainer#getProvidedInterfaces()
	 * @see #getOperationInterfaceContainer()
	 * @generated
	 */
	EReference getOperationInterfaceContainer_ProvidedInterfaces();

	/**
	 * Returns the meta object for the reference list '{@link org.scaledl.overview.application.OperationInterfaceContainer#getRequiredInterfaces <em>Required Interfaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Required Interfaces</em>'.
	 * @see org.scaledl.overview.application.OperationInterfaceContainer#getRequiredInterfaces()
	 * @see #getOperationInterfaceContainer()
	 * @generated
	 */
	EReference getOperationInterfaceContainer_RequiredInterfaces();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.application.OperationInterface <em>Operation Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Interface</em>'.
	 * @see org.scaledl.overview.application.OperationInterface
	 * @generated
	 */
	EClass getOperationInterface();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.application.OperationInterface#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see org.scaledl.overview.application.OperationInterface#getOperations()
	 * @see #getOperationInterface()
	 * @generated
	 */
	EReference getOperationInterface_Operations();

	/**
	 * Returns the meta object for the container reference '{@link org.scaledl.overview.application.OperationInterface#getProvidingContainer <em>Providing Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Providing Container</em>'.
	 * @see org.scaledl.overview.application.OperationInterface#getProvidingContainer()
	 * @see #getOperationInterface()
	 * @generated
	 */
	EReference getOperationInterface_ProvidingContainer();

	/**
	 * Returns the meta object for the reference list '{@link org.scaledl.overview.application.OperationInterface#getRequiringContainer <em>Requiring Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Requiring Container</em>'.
	 * @see org.scaledl.overview.application.OperationInterface#getRequiringContainer()
	 * @see #getOperationInterface()
	 * @generated
	 */
	EReference getOperationInterface_RequiringContainer();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ApplicationFactory getApplicationFactory();

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
		 * The meta object literal for the '{@link org.scaledl.overview.application.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.application.impl.OperationImpl
		 * @see org.scaledl.overview.application.impl.ApplicationPackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em><b>Return Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__RETURN_PARAMETER = eINSTANCE.getOperation_ReturnParameter();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__PARAMETERS = eINSTANCE.getOperation_Parameters();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.application.OperationInterfaceContainer <em>Operation Interface Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.application.OperationInterfaceContainer
		 * @see org.scaledl.overview.application.impl.ApplicationPackageImpl#getOperationInterfaceContainer()
		 * @generated
		 */
		EClass OPERATION_INTERFACE_CONTAINER = eINSTANCE.getOperationInterfaceContainer();

		/**
		 * The meta object literal for the '<em><b>Provided Interfaces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES = eINSTANCE.getOperationInterfaceContainer_ProvidedInterfaces();

		/**
		 * The meta object literal for the '<em><b>Required Interfaces</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES = eINSTANCE.getOperationInterfaceContainer_RequiredInterfaces();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.application.impl.OperationInterfaceImpl <em>Operation Interface</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.application.impl.OperationInterfaceImpl
		 * @see org.scaledl.overview.application.impl.ApplicationPackageImpl#getOperationInterface()
		 * @generated
		 */
		EClass OPERATION_INTERFACE = eINSTANCE.getOperationInterface();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_INTERFACE__OPERATIONS = eINSTANCE.getOperationInterface_Operations();

		/**
		 * The meta object literal for the '<em><b>Providing Container</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_INTERFACE__PROVIDING_CONTAINER = eINSTANCE.getOperationInterface_ProvidingContainer();

		/**
		 * The meta object literal for the '<em><b>Requiring Container</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_INTERFACE__REQUIRING_CONTAINER = eINSTANCE.getOperationInterface_RequiringContainer();

	}

} //ApplicationPackage
