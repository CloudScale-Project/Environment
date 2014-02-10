/**
 */
package eu.cloudscaleproject.env.csm.core.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import eu.cloudscaleproject.env.csm.CsmPackage;
import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl;
import eu.cloudscaleproject.env.csm.core.CoreFactory;
import eu.cloudscaleproject.env.csm.core.CorePackage;
import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.core.Operation;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.core.TypeEnum;
import eu.cloudscaleproject.env.csm.definition.DefinitionPackage;
import eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl;
import eu.cloudscaleproject.env.csm.impl.CsmPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class CorePackageImpl extends EPackageImpl implements CorePackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass avEntryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aeEntryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entityEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationInterfaceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum typeEnumEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
	 * package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory
	 * method {@link #init init()}, which also performs initialization of the
	 * package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see eu.cloudscaleproject.env.csm.core.CorePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CorePackageImpl() {
		super(eNS_URI, CoreFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model,
	 * and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link CorePackage#eINSTANCE} when that
	 * field is accessed. Clients should not invoke it directly. Instead, they
	 * should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CorePackage init() {
		if (isInited) return (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

		// Obtain or create and register package
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CorePackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		CsmPackageImpl theCsmPackage = (CsmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CsmPackage.eNS_URI) instanceof CsmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CsmPackage.eNS_URI) : CsmPackage.eINSTANCE);
		ArchitecturePackageImpl theArchitecturePackage = (ArchitecturePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ArchitecturePackage.eNS_URI) instanceof ArchitecturePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ArchitecturePackage.eNS_URI) : ArchitecturePackage.eINSTANCE);
		DefinitionPackageImpl theDefinitionPackage = (DefinitionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DefinitionPackage.eNS_URI) instanceof DefinitionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DefinitionPackage.eNS_URI) : DefinitionPackage.eINSTANCE);

		// Create package meta-data objects
		theCorePackage.createPackageContents();
		theCsmPackage.createPackageContents();
		theArchitecturePackage.createPackageContents();
		theDefinitionPackage.createPackageContents();

		// Initialize created meta-data
		theCorePackage.initializePackageContents();
		theCsmPackage.initializePackageContents();
		theArchitecturePackage.initializePackageContents();
		theDefinitionPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCorePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CorePackage.eNS_URI, theCorePackage);
		return theCorePackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAVEntry() {
		return avEntryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAVEntry_Key() {
		return (EAttribute)avEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAVEntry_Value() {
		return (EAttribute)avEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAEEntry() {
		return aeEntryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAEEntry_Key() {
		return (EAttribute)aeEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAEEntry_Value() {
		return (EReference)aeEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntity() {
		return entityEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntity_Id() {
		return (EAttribute)entityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntity_Name() {
		return (EAttribute)entityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntity_Description() {
		return (EAttribute)entityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntity_AvMap() {
		return (EReference)entityEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntity_AeMap() {
		return (EReference)entityEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperation() {
		return operationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperation_MethodName() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperation_ReturnValue() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperation_Parameters() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationInterface() {
		return operationInterfaceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationInterface_Operations() {
		return (EReference)operationInterfaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationInterface_ProvidingContainer() {
		return (EReference)operationInterfaceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationInterface_RequiringContainer() {
		return (EReference)operationInterfaceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTypeEnum() {
		return typeEnumEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CoreFactory getCoreFactory() {
		return (CoreFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		avEntryEClass = createEClass(AV_ENTRY);
		createEAttribute(avEntryEClass, AV_ENTRY__KEY);
		createEAttribute(avEntryEClass, AV_ENTRY__VALUE);

		aeEntryEClass = createEClass(AE_ENTRY);
		createEAttribute(aeEntryEClass, AE_ENTRY__KEY);
		createEReference(aeEntryEClass, AE_ENTRY__VALUE);

		entityEClass = createEClass(ENTITY);
		createEAttribute(entityEClass, ENTITY__ID);
		createEAttribute(entityEClass, ENTITY__NAME);
		createEAttribute(entityEClass, ENTITY__DESCRIPTION);
		createEReference(entityEClass, ENTITY__AV_MAP);
		createEReference(entityEClass, ENTITY__AE_MAP);

		operationEClass = createEClass(OPERATION);
		createEAttribute(operationEClass, OPERATION__METHOD_NAME);
		createEAttribute(operationEClass, OPERATION__RETURN_VALUE);
		createEAttribute(operationEClass, OPERATION__PARAMETERS);

		operationInterfaceEClass = createEClass(OPERATION_INTERFACE);
		createEReference(operationInterfaceEClass, OPERATION_INTERFACE__OPERATIONS);
		createEReference(operationInterfaceEClass, OPERATION_INTERFACE__PROVIDING_CONTAINER);
		createEReference(operationInterfaceEClass, OPERATION_INTERFACE__REQUIRING_CONTAINER);

		// Create enums
		typeEnumEEnum = createEEnum(TYPE_ENUM);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This
	 * method is guarded to have no affect on any invocation but its first. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		ArchitecturePackage theArchitecturePackage = (ArchitecturePackage)EPackage.Registry.INSTANCE.getEPackage(ArchitecturePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		operationEClass.getESuperTypes().add(this.getEntity());
		operationInterfaceEClass.getESuperTypes().add(this.getEntity());

		// Initialize classes and features; add operations and parameters
		initEClass(avEntryEClass, Map.Entry.class, "AVEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAVEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAVEntry_Value(), ecorePackage.getEJavaObject(), "value", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aeEntryEClass, Map.Entry.class, "AEEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAEEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAEEntry_Value(), ecorePackage.getEObject(), null, "value", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(entityEClass, Entity.class, "Entity", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEntity_Id(), ecorePackage.getEString(), "id", "", 0, 1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntity_Name(), ecorePackage.getEString(), "name", "", 0, 1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntity_Description(), ecorePackage.getEString(), "description", "", 0, 1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntity_AvMap(), this.getAVEntry(), null, "avMap", null, 0, -1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntity_AeMap(), this.getAEEntry(), null, "aeMap", null, 0, -1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationEClass, Operation.class, "Operation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOperation_MethodName(), ecorePackage.getEString(), "methodName", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_ReturnValue(), this.getTypeEnum(), "returnValue", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_Parameters(), this.getTypeEnum(), "parameters", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationInterfaceEClass, OperationInterface.class, "OperationInterface", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationInterface_Operations(), this.getOperation(), null, "operations", null, 0, -1, OperationInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationInterface_ProvidingContainer(), theArchitecturePackage.getOperationInterfaceContainer(), theArchitecturePackage.getOperationInterfaceContainer_ProvidedInterfaces(), "providingContainer", null, 0, 1, OperationInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationInterface_RequiringContainer(), theArchitecturePackage.getOperationInterfaceContainer(), theArchitecturePackage.getOperationInterfaceContainer_RequiredInterfaces(), "requiringContainer", null, 0, -1, OperationInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(typeEnumEEnum, TypeEnum.class, "TypeEnum");
		addEEnumLiteral(typeEnumEEnum, TypeEnum.INT);
		addEEnumLiteral(typeEnumEEnum, TypeEnum.STRING);
		addEEnumLiteral(typeEnumEEnum, TypeEnum.BOOL);
		addEEnumLiteral(typeEnumEEnum, TypeEnum.DOUBLE);
	}

} // CorePackageImpl
