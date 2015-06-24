/**
 */
package org.scaledl.overview.core.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.scaledl.overview.OverviewPackage;
import org.scaledl.overview.application.ApplicationPackage;
import org.scaledl.overview.application.impl.ApplicationPackageImpl;
import org.scaledl.overview.architecture.ArchitecturePackage;
import org.scaledl.overview.architecture.impl.ArchitecturePackageImpl;
import org.scaledl.overview.core.CoreFactory;
import org.scaledl.overview.core.CorePackage;
import org.scaledl.overview.core.Entity;
import org.scaledl.overview.core.MeasurementMetric_NOTUSED;
import org.scaledl.overview.core.StatisticType_NOTUSED;
import org.scaledl.overview.deployment.DeploymentPackage;
import org.scaledl.overview.deployment.impl.DeploymentPackageImpl;
import org.scaledl.overview.impl.OverviewPackageImpl;
import org.scaledl.overview.parametertype.ParametertypePackage;
import org.scaledl.overview.parametertype.impl.ParametertypePackageImpl;
import org.scaledl.overview.specification.SpecificationPackage;
import org.scaledl.overview.specification.impl.SpecificationPackageImpl;
import org.scaledl.overview.specification.sla.SlaPackage;
import org.scaledl.overview.specification.sla.impl.SlaPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CorePackageImpl extends EPackageImpl implements CorePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass avEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aeEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum measurementMetric_NOTUSEDEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum statisticType_NOTUSEDEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.scaledl.overview.core.CorePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CorePackageImpl() {
		super(eNS_URI, CoreFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link CorePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
		OverviewPackageImpl theOverviewPackage = (OverviewPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OverviewPackage.eNS_URI) instanceof OverviewPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OverviewPackage.eNS_URI) : OverviewPackage.eINSTANCE);
		ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) instanceof ApplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) : ApplicationPackage.eINSTANCE);
		ArchitecturePackageImpl theArchitecturePackage = (ArchitecturePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ArchitecturePackage.eNS_URI) instanceof ArchitecturePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ArchitecturePackage.eNS_URI) : ArchitecturePackage.eINSTANCE);
		DeploymentPackageImpl theDeploymentPackage = (DeploymentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI) instanceof DeploymentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI) : DeploymentPackage.eINSTANCE);
		SpecificationPackageImpl theSpecificationPackage = (SpecificationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI) instanceof SpecificationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI) : SpecificationPackage.eINSTANCE);
		SlaPackageImpl theSlaPackage = (SlaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SlaPackage.eNS_URI) instanceof SlaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SlaPackage.eNS_URI) : SlaPackage.eINSTANCE);
		ParametertypePackageImpl theParametertypePackage = (ParametertypePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ParametertypePackage.eNS_URI) instanceof ParametertypePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ParametertypePackage.eNS_URI) : ParametertypePackage.eINSTANCE);

		// Create package meta-data objects
		theCorePackage.createPackageContents();
		theOverviewPackage.createPackageContents();
		theApplicationPackage.createPackageContents();
		theArchitecturePackage.createPackageContents();
		theDeploymentPackage.createPackageContents();
		theSpecificationPackage.createPackageContents();
		theSlaPackage.createPackageContents();
		theParametertypePackage.createPackageContents();

		// Initialize created meta-data
		theCorePackage.initializePackageContents();
		theOverviewPackage.initializePackageContents();
		theApplicationPackage.initializePackageContents();
		theArchitecturePackage.initializePackageContents();
		theDeploymentPackage.initializePackageContents();
		theSpecificationPackage.initializePackageContents();
		theSlaPackage.initializePackageContents();
		theParametertypePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCorePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CorePackage.eNS_URI, theCorePackage);
		return theCorePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAVEntry() {
		return avEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAVEntry_Key() {
		return (EAttribute)avEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAVEntry_Value() {
		return (EAttribute)avEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAEEntry() {
		return aeEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAEEntry_Key() {
		return (EAttribute)aeEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAEEntry_Value() {
		return (EReference)aeEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntity() {
		return entityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntity_Id() {
		return (EAttribute)entityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntity_Name() {
		return (EAttribute)entityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntity_Description() {
		return (EAttribute)entityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntity_AvMap() {
		return (EReference)entityEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntity_AeMap() {
		return (EReference)entityEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getMeasurementMetric_NOTUSED() {
		return measurementMetric_NOTUSEDEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getStatisticType_NOTUSED() {
		return statisticType_NOTUSEDEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreFactory getCoreFactory() {
		return (CoreFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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

		// Create enums
		measurementMetric_NOTUSEDEEnum = createEEnum(MEASUREMENT_METRIC_NOTUSED);
		statisticType_NOTUSEDEEnum = createEEnum(STATISTIC_TYPE_NOTUSED);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(avEntryEClass, Map.Entry.class, "AVEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAVEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAVEntry_Value(), ecorePackage.getEJavaObject(), "value", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aeEntryEClass, Map.Entry.class, "AEEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAEEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAEEntry_Value(), ecorePackage.getEObject(), null, "value", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(entityEClass, Entity.class, "Entity", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEntity_Id(), ecorePackage.getEString(), "id", "", 0, 1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntity_Name(), ecorePackage.getEString(), "name", "", 0, 1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntity_Description(), ecorePackage.getEString(), "description", "", 0, 1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntity_AvMap(), this.getAVEntry(), null, "avMap", null, 0, -1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntity_AeMap(), this.getAEEntry(), null, "aeMap", null, 0, -1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(measurementMetric_NOTUSEDEEnum, MeasurementMetric_NOTUSED.class, "MeasurementMetric_NOTUSED");
		addEEnumLiteral(measurementMetric_NOTUSEDEEnum, MeasurementMetric_NOTUSED.CPU_UTILIZATION);
		addEEnumLiteral(measurementMetric_NOTUSEDEEnum, MeasurementMetric_NOTUSED.DISK_WRITE_OPS);
		addEEnumLiteral(measurementMetric_NOTUSEDEEnum, MeasurementMetric_NOTUSED.DISK_WRITE_BYTES);
		addEEnumLiteral(measurementMetric_NOTUSEDEEnum, MeasurementMetric_NOTUSED.DISK_READ_OPS);
		addEEnumLiteral(measurementMetric_NOTUSEDEEnum, MeasurementMetric_NOTUSED.DISK_READ_BYTES);
		addEEnumLiteral(measurementMetric_NOTUSEDEEnum, MeasurementMetric_NOTUSED.LATENCY);
		addEEnumLiteral(measurementMetric_NOTUSEDEEnum, MeasurementMetric_NOTUSED.REQUEST_COUNT);
		addEEnumLiteral(measurementMetric_NOTUSEDEEnum, MeasurementMetric_NOTUSED.NETWORK_OUT);
		addEEnumLiteral(measurementMetric_NOTUSEDEEnum, MeasurementMetric_NOTUSED.NETWORK_IN);

		initEEnum(statisticType_NOTUSEDEEnum, StatisticType_NOTUSED.class, "StatisticType_NOTUSED");
		addEEnumLiteral(statisticType_NOTUSEDEEnum, StatisticType_NOTUSED.AVERAGE);
		addEEnumLiteral(statisticType_NOTUSEDEEnum, StatisticType_NOTUSED.MIN);
		addEEnumLiteral(statisticType_NOTUSEDEEnum, StatisticType_NOTUSED.MAX);
		addEEnumLiteral(statisticType_NOTUSEDEEnum, StatisticType_NOTUSED.SUM);
	}

} //CorePackageImpl
