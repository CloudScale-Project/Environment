/**
 */
package eu.cloudscaleproject.env.csm.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.CsmFactory;
import eu.cloudscaleproject.env.csm.CsmPackage;
import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl;
import eu.cloudscaleproject.env.csm.core.CorePackage;
import eu.cloudscaleproject.env.csm.core.impl.CorePackageImpl;
import eu.cloudscaleproject.env.csm.definition.DefinitionPackage;
import eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class CsmPackageImpl extends EPackageImpl implements CsmPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass csmEClass = null;

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
	 * @see eu.cloudscaleproject.env.csm.CsmPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CsmPackageImpl() {
		super(eNS_URI, CsmFactory.eINSTANCE);
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
	 * This method is used to initialize {@link CsmPackage#eINSTANCE} when that
	 * field is accessed. Clients should not invoke it directly. Instead, they
	 * should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CsmPackage init() {
		if (isInited) return (CsmPackage)EPackage.Registry.INSTANCE.getEPackage(CsmPackage.eNS_URI);

		// Obtain or create and register package
		CsmPackageImpl theCsmPackage = (CsmPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CsmPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CsmPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);
		ArchitecturePackageImpl theArchitecturePackage = (ArchitecturePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ArchitecturePackage.eNS_URI) instanceof ArchitecturePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ArchitecturePackage.eNS_URI) : ArchitecturePackage.eINSTANCE);
		DefinitionPackageImpl theDefinitionPackage = (DefinitionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DefinitionPackage.eNS_URI) instanceof DefinitionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DefinitionPackage.eNS_URI) : DefinitionPackage.eINSTANCE);

		// Create package meta-data objects
		theCsmPackage.createPackageContents();
		theCorePackage.createPackageContents();
		theArchitecturePackage.createPackageContents();
		theDefinitionPackage.createPackageContents();

		// Initialize created meta-data
		theCsmPackage.initializePackageContents();
		theCorePackage.initializePackageContents();
		theArchitecturePackage.initializePackageContents();
		theDefinitionPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCsmPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CsmPackage.eNS_URI, theCsmPackage);
		return theCsmPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCsm() {
		return csmEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCsm_Architecture() {
		return (EReference)csmEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCsm_Definition() {
		return (EReference)csmEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CsmFactory getCsmFactory() {
		return (CsmFactory)getEFactoryInstance();
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
		csmEClass = createEClass(CSM);
		createEReference(csmEClass, CSM__ARCHITECTURE);
		createEReference(csmEClass, CSM__DEFINITION);
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
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);
		ArchitecturePackage theArchitecturePackage = (ArchitecturePackage)EPackage.Registry.INSTANCE.getEPackage(ArchitecturePackage.eNS_URI);
		DefinitionPackage theDefinitionPackage = (DefinitionPackage)EPackage.Registry.INSTANCE.getEPackage(DefinitionPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theCorePackage);
		getESubpackages().add(theArchitecturePackage);
		getESubpackages().add(theDefinitionPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(csmEClass, Csm.class, "Csm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCsm_Architecture(), theArchitecturePackage.getArchitecture(), null, "architecture", null, 0, 1, Csm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCsm_Definition(), theDefinitionPackage.getSystemDefinition(), null, "definition", null, 0, 1, Csm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} // CsmPackageImpl
