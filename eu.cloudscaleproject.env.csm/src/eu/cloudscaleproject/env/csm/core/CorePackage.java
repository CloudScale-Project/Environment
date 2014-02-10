/**
 */
package eu.cloudscaleproject.env.csm.core;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * <!-- end-user-doc --> <!-- begin-model-doc --> This package contains several
 * core classes that are used by classes from other two packages (i.e.
 * Architecture and Definition package). It provides abstract base class Entity,
 * used by all other classes, providing them id, name and description
 * attributes. Additionally, the package provides business logic related
 * classes; modules, operation interfaces and operations. <!-- end-model-doc -->
 * 
 * @see eu.cloudscaleproject.env.csm.core.CoreFactory
 * @model kind="package"
 * @generated
 */
public interface CorePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "core";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://eu.cloudscaleproject.env/CloudscaleComponentModel/Core/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "core";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	CorePackage eINSTANCE = eu.cloudscaleproject.env.csm.core.impl.CorePackageImpl.init();

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.core.impl.AEEntryImpl <em>AE Entry</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.core.impl.AEEntryImpl
	 * @see eu.cloudscaleproject.env.csm.core.impl.CorePackageImpl#getAEEntry()
	 * @generated
	 */
	int AE_ENTRY = 1;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.core.impl.AVEntryImpl <em>AV Entry</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.core.impl.AVEntryImpl
	 * @see eu.cloudscaleproject.env.csm.core.impl.CorePackageImpl#getAVEntry()
	 * @generated
	 */
	int AV_ENTRY = 0;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AV_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AV_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>AV Entry</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AV_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AE_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AE_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>AE Entry</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AE_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.core.impl.EntityImpl <em>Entity</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.core.impl.EntityImpl
	 * @see eu.cloudscaleproject.env.csm.core.impl.CorePackageImpl#getEntity()
	 * @generated
	 */
	int ENTITY = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ENTITY__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ENTITY__NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ENTITY__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__AV_MAP = 3;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__AE_MAP = 4;

	/**
	 * The number of structural features of the '<em>Entity</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ENTITY_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.core.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.core.impl.OperationImpl
	 * @see eu.cloudscaleproject.env.csm.core.impl.CorePackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OPERATION__ID = ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OPERATION__NAME = ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OPERATION__DESCRIPTION = ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__AV_MAP = ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__AE_MAP = ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Method Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OPERATION__METHOD_NAME = ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Return Value</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OPERATION__RETURN_VALUE = ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OPERATION__PARAMETERS = ENTITY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Operation</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = ENTITY_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.core.impl.OperationInterfaceImpl <em>Operation Interface</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.core.impl.OperationInterfaceImpl
	 * @see eu.cloudscaleproject.env.csm.core.impl.CorePackageImpl#getOperationInterface()
	 * @generated
	 */
	int OPERATION_INTERFACE = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE__ID = ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE__NAME = ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE__DESCRIPTION = ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE__AV_MAP = ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE__AE_MAP = ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE__OPERATIONS = ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Providing Container</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE__PROVIDING_CONTAINER = ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Requiring Container</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE__REQUIRING_CONTAINER = ENTITY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Operation Interface</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE_FEATURE_COUNT = ENTITY_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.core.TypeEnum <em>Type Enum</em>}' enum.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.core.TypeEnum
	 * @see eu.cloudscaleproject.env.csm.core.impl.CorePackageImpl#getTypeEnum()
	 * @generated
	 */
	int TYPE_ENUM = 5;

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>AE Entry</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>AE Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyId="true" keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueType="org.eclipse.emf.ecore.EObject" valueRequired="true"
	 * @generated
	 */
	EClass getAEEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAEEntry()
	 * @generated
	 */
	EAttribute getAEEntry_Key();

	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAEEntry()
	 * @generated
	 */
	EReference getAEEntry_Value();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>AV Entry</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>AV Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyId="true" keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueDataType="org.eclipse.emf.ecore.EJavaObject" valueRequired="true"
	 * @generated
	 */
	EClass getAVEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAVEntry()
	 * @generated
	 */
	EAttribute getAVEntry_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAVEntry()
	 * @generated
	 */
	EAttribute getAVEntry_Value();

	/**
	 * Returns the meta object for class '
	 * {@link eu.cloudscaleproject.env.csm.core.Entity <em>Entity</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Entity</em>'.
	 * @see eu.cloudscaleproject.env.csm.core.Entity
	 * @generated
	 */
	EClass getEntity();

	/**
	 * Returns the meta object for the attribute '
	 * {@link eu.cloudscaleproject.env.csm.core.Entity#getId <em>Id</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see eu.cloudscaleproject.env.csm.core.Entity#getId()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Id();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.csm.core.Entity#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.cloudscaleproject.env.csm.core.Entity#getName()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.csm.core.Entity#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see eu.cloudscaleproject.env.csm.core.Entity#getDescription()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Description();

	/**
	 * Returns the meta object for the map '{@link eu.cloudscaleproject.env.csm.core.Entity#getAvMap <em>Av Map</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Av Map</em>'.
	 * @see eu.cloudscaleproject.env.csm.core.Entity#getAvMap()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_AvMap();

	/**
	 * Returns the meta object for the map '{@link eu.cloudscaleproject.env.csm.core.Entity#getAeMap <em>Ae Map</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Ae Map</em>'.
	 * @see eu.cloudscaleproject.env.csm.core.Entity#getAeMap()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_AeMap();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.core.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see eu.cloudscaleproject.env.csm.core.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.csm.core.Operation#getMethodName <em>Method Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Method Name</em>'.
	 * @see eu.cloudscaleproject.env.csm.core.Operation#getMethodName()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_MethodName();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.csm.core.Operation#getReturnValue <em>Return Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Return Value</em>'.
	 * @see eu.cloudscaleproject.env.csm.core.Operation#getReturnValue()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_ReturnValue();

	/**
	 * Returns the meta object for the attribute list '{@link eu.cloudscaleproject.env.csm.core.Operation#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Parameters</em>'.
	 * @see eu.cloudscaleproject.env.csm.core.Operation#getParameters()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_Parameters();

	/**
	 * Returns the meta object for class '
	 * {@link eu.cloudscaleproject.env.csm.core.OperationInterface
	 * <em>Operation Interface</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Operation Interface</em>'.
	 * @see eu.cloudscaleproject.env.csm.core.OperationInterface
	 * @generated
	 */
	EClass getOperationInterface();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.csm.core.OperationInterface#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see eu.cloudscaleproject.env.csm.core.OperationInterface#getOperations()
	 * @see #getOperationInterface()
	 * @generated
	 */
	EReference getOperationInterface_Operations();

	/**
	 * Returns the meta object for the container reference '
	 * {@link eu.cloudscaleproject.env.csm.core.OperationInterface#getProvidingContainer
	 * <em>Providing Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the container reference '
	 *         <em>Providing Container</em>'.
	 * @see eu.cloudscaleproject.env.csm.core.OperationInterface#getProvidingContainer()
	 * @see #getOperationInterface()
	 * @generated
	 */
	EReference getOperationInterface_ProvidingContainer();

	/**
	 * Returns the meta object for the reference list '
	 * {@link eu.cloudscaleproject.env.csm.core.OperationInterface#getRequiringContainer
	 * <em>Requiring Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the reference list '
	 *         <em>Requiring Container</em>'.
	 * @see eu.cloudscaleproject.env.csm.core.OperationInterface#getRequiringContainer()
	 * @see #getOperationInterface()
	 * @generated
	 */
	EReference getOperationInterface_RequiringContainer();

	/**
	 * Returns the meta object for enum '{@link eu.cloudscaleproject.env.csm.core.TypeEnum <em>Type Enum</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Type Enum</em>'.
	 * @see eu.cloudscaleproject.env.csm.core.TypeEnum
	 * @generated
	 */
	EEnum getTypeEnum();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CoreFactory getCoreFactory();

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
		 * The meta object literal for the '
		 * {@link eu.cloudscaleproject.env.csm.core.impl.AEEntryImpl
		 * <em>AE Entry</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see eu.cloudscaleproject.env.csm.core.impl.AEEntryImpl
		 * @see eu.cloudscaleproject.env.csm.core.impl.CorePackageImpl#getAEEntry()
		 * @generated
		 */
		EClass AE_ENTRY = eINSTANCE.getAEEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AE_ENTRY__KEY = eINSTANCE.getAEEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference AE_ENTRY__VALUE = eINSTANCE.getAEEntry_Value();

		/**
		 * The meta object literal for the '
		 * {@link eu.cloudscaleproject.env.csm.core.impl.AVEntryImpl
		 * <em>AV Entry</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see eu.cloudscaleproject.env.csm.core.impl.AVEntryImpl
		 * @see eu.cloudscaleproject.env.csm.core.impl.CorePackageImpl#getAVEntry()
		 * @generated
		 */
		EClass AV_ENTRY = eINSTANCE.getAVEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AV_ENTRY__KEY = eINSTANCE.getAVEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AV_ENTRY__VALUE = eINSTANCE.getAVEntry_Value();

		/**
		 * The meta object literal for the '
		 * {@link eu.cloudscaleproject.env.csm.core.impl.EntityImpl
		 * <em>Entity</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see eu.cloudscaleproject.env.csm.core.impl.EntityImpl
		 * @see eu.cloudscaleproject.env.csm.core.impl.CorePackageImpl#getEntity()
		 * @generated
		 */
		EClass ENTITY = eINSTANCE.getEntity();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__ID = eINSTANCE.getEntity_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__NAME = eINSTANCE.getEntity_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__DESCRIPTION = eINSTANCE.getEntity_Description();

		/**
		 * The meta object literal for the '<em><b>Av Map</b></em>' map feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__AV_MAP = eINSTANCE.getEntity_AvMap();

		/**
		 * The meta object literal for the '<em><b>Ae Map</b></em>' map feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__AE_MAP = eINSTANCE.getEntity_AeMap();

		/**
		 * The meta object literal for the '
		 * {@link eu.cloudscaleproject.env.csm.core.impl.OperationImpl
		 * <em>Operation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see eu.cloudscaleproject.env.csm.core.impl.OperationImpl
		 * @see eu.cloudscaleproject.env.csm.core.impl.CorePackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em><b>Method Name</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__METHOD_NAME = eINSTANCE.getOperation_MethodName();

		/**
		 * The meta object literal for the '<em><b>Return Value</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__RETURN_VALUE = eINSTANCE.getOperation_ReturnValue();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' attribute list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__PARAMETERS = eINSTANCE.getOperation_Parameters();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.core.impl.OperationInterfaceImpl <em>Operation Interface</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.core.impl.OperationInterfaceImpl
		 * @see eu.cloudscaleproject.env.csm.core.impl.CorePackageImpl#getOperationInterface()
		 * @generated
		 */
		EClass OPERATION_INTERFACE = eINSTANCE.getOperationInterface();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_INTERFACE__OPERATIONS = eINSTANCE.getOperationInterface_Operations();

		/**
		 * The meta object literal for the '<em><b>Providing Container</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_INTERFACE__PROVIDING_CONTAINER = eINSTANCE.getOperationInterface_ProvidingContainer();

		/**
		 * The meta object literal for the '<em><b>Requiring Container</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_INTERFACE__REQUIRING_CONTAINER = eINSTANCE.getOperationInterface_RequiringContainer();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.core.TypeEnum <em>Type Enum</em>}' enum.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.core.TypeEnum
		 * @see eu.cloudscaleproject.env.csm.core.impl.CorePackageImpl#getTypeEnum()
		 * @generated
		 */
		EEnum TYPE_ENUM = eINSTANCE.getTypeEnum();

	}

} // CorePackage
