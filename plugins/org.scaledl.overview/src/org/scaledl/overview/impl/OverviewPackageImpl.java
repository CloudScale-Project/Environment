/**
 */
package org.scaledl.overview.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.scaledl.overview.Overview;
import org.scaledl.overview.OverviewFactory;
import org.scaledl.overview.OverviewPackage;

import org.scaledl.overview.application.ApplicationPackage;

import org.scaledl.overview.application.impl.ApplicationPackageImpl;

import org.scaledl.overview.architecture.ArchitecturePackage;

import org.scaledl.overview.architecture.impl.ArchitecturePackageImpl;

import org.scaledl.overview.core.CorePackage;

import org.scaledl.overview.core.impl.CorePackageImpl;

import org.scaledl.overview.deployment.DeploymentPackage;

import org.scaledl.overview.deployment.impl.DeploymentPackageImpl;

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
public class OverviewPackageImpl extends EPackageImpl implements OverviewPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass overviewEClass = null;

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
	 * @see org.scaledl.overview.OverviewPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private OverviewPackageImpl() {
		super(eNS_URI, OverviewFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link OverviewPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static OverviewPackage init() {
		if (isInited) return (OverviewPackage)EPackage.Registry.INSTANCE.getEPackage(OverviewPackage.eNS_URI);

		// Obtain or create and register package
		OverviewPackageImpl theOverviewPackage = (OverviewPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof OverviewPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new OverviewPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);
		ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) instanceof ApplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) : ApplicationPackage.eINSTANCE);
		ArchitecturePackageImpl theArchitecturePackage = (ArchitecturePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ArchitecturePackage.eNS_URI) instanceof ArchitecturePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ArchitecturePackage.eNS_URI) : ArchitecturePackage.eINSTANCE);
		DeploymentPackageImpl theDeploymentPackage = (DeploymentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI) instanceof DeploymentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI) : DeploymentPackage.eINSTANCE);
		SpecificationPackageImpl theSpecificationPackage = (SpecificationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI) instanceof SpecificationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI) : SpecificationPackage.eINSTANCE);
		SlaPackageImpl theSlaPackage = (SlaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SlaPackage.eNS_URI) instanceof SlaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SlaPackage.eNS_URI) : SlaPackage.eINSTANCE);
		ParametertypePackageImpl theParametertypePackage = (ParametertypePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ParametertypePackage.eNS_URI) instanceof ParametertypePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ParametertypePackage.eNS_URI) : ParametertypePackage.eINSTANCE);

		// Create package meta-data objects
		theOverviewPackage.createPackageContents();
		theCorePackage.createPackageContents();
		theApplicationPackage.createPackageContents();
		theArchitecturePackage.createPackageContents();
		theDeploymentPackage.createPackageContents();
		theSpecificationPackage.createPackageContents();
		theSlaPackage.createPackageContents();
		theParametertypePackage.createPackageContents();

		// Initialize created meta-data
		theOverviewPackage.initializePackageContents();
		theCorePackage.initializePackageContents();
		theApplicationPackage.initializePackageContents();
		theArchitecturePackage.initializePackageContents();
		theDeploymentPackage.initializePackageContents();
		theSpecificationPackage.initializePackageContents();
		theSlaPackage.initializePackageContents();
		theParametertypePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theOverviewPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(OverviewPackage.eNS_URI, theOverviewPackage);
		return theOverviewPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOverview() {
		return overviewEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOverview_Architecture() {
		return (EReference)overviewEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOverview_Deployment() {
		return (EReference)overviewEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOverview_Definition() {
		return (EReference)overviewEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOverview_DataTypes() {
		return (EReference)overviewEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OverviewFactory getOverviewFactory() {
		return (OverviewFactory)getEFactoryInstance();
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
		overviewEClass = createEClass(OVERVIEW);
		createEReference(overviewEClass, OVERVIEW__ARCHITECTURE);
		createEReference(overviewEClass, OVERVIEW__DEPLOYMENT);
		createEReference(overviewEClass, OVERVIEW__DEFINITION);
		createEReference(overviewEClass, OVERVIEW__DATA_TYPES);
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

		// Obtain other dependent packages
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);
		ApplicationPackage theApplicationPackage = (ApplicationPackage)EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI);
		ArchitecturePackage theArchitecturePackage = (ArchitecturePackage)EPackage.Registry.INSTANCE.getEPackage(ArchitecturePackage.eNS_URI);
		DeploymentPackage theDeploymentPackage = (DeploymentPackage)EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI);
		SpecificationPackage theSpecificationPackage = (SpecificationPackage)EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI);
		ParametertypePackage theParametertypePackage = (ParametertypePackage)EPackage.Registry.INSTANCE.getEPackage(ParametertypePackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theCorePackage);
		getESubpackages().add(theApplicationPackage);
		getESubpackages().add(theArchitecturePackage);
		getESubpackages().add(theDeploymentPackage);
		getESubpackages().add(theSpecificationPackage);
		getESubpackages().add(theParametertypePackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(overviewEClass, Overview.class, "Overview", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOverview_Architecture(), theArchitecturePackage.getArchitecture(), null, "architecture", null, 0, 1, Overview.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOverview_Deployment(), theDeploymentPackage.getDeployment(), null, "deployment", null, 0, 1, Overview.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOverview_Definition(), theSpecificationPackage.getSystemSpecification(), null, "definition", null, 0, 1, Overview.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOverview_DataTypes(), theParametertypePackage.getParameterType(), null, "dataTypes", null, 0, 1, Overview.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //OverviewPackageImpl
