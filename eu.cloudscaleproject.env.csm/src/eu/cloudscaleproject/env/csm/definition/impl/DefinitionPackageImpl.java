/**
 */
package eu.cloudscaleproject.env.csm.definition.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import eu.cloudscaleproject.env.csm.CsmPackage;
import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl;
import eu.cloudscaleproject.env.csm.core.CorePackage;
import eu.cloudscaleproject.env.csm.core.impl.CorePackageImpl;
import eu.cloudscaleproject.env.csm.definition.AvailabilityZoneDescriptor;
import eu.cloudscaleproject.env.csm.definition.CloudDefinition;
import eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor;
import eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.Definition;
import eu.cloudscaleproject.env.csm.definition.DefinitionFactory;
import eu.cloudscaleproject.env.csm.definition.DefinitionPackage;
import eu.cloudscaleproject.env.csm.definition.DeployablePlatformServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.DeployableRuntimeServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.DeployableSupportServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.ExternalPlatformServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.ExternalRuntimeServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.ExternalSupportServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.InfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.RegionDescriptor;
import eu.cloudscaleproject.env.csm.definition.SoftwareDefinition;
import eu.cloudscaleproject.env.csm.definition.SystemDefinition;
import eu.cloudscaleproject.env.csm.impl.CsmPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class DefinitionPackageImpl extends EPackageImpl implements
		DefinitionPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass definitionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass systemDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cloudDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass softwareDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass descriptorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cloudEnvironmentDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass regionDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass availabilityZoneDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass infrastructureServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass networkInfrastructureServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass computingInfrastructureServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deployablePlatformServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalPlatformServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deployableRuntimeServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalRuntimeServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deployableSupportServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalSupportServiceDescriptorEClass = null;

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
	 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DefinitionPackageImpl() {
		super(eNS_URI, DefinitionFactory.eINSTANCE);
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
	 * This method is used to initialize {@link DefinitionPackage#eINSTANCE}
	 * when that field is accessed. Clients should not invoke it directly.
	 * Instead, they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DefinitionPackage init() {
		if (isInited) return (DefinitionPackage)EPackage.Registry.INSTANCE.getEPackage(DefinitionPackage.eNS_URI);

		// Obtain or create and register package
		DefinitionPackageImpl theDefinitionPackage = (DefinitionPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DefinitionPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DefinitionPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		CsmPackageImpl theCsmPackage = (CsmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CsmPackage.eNS_URI) instanceof CsmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CsmPackage.eNS_URI) : CsmPackage.eINSTANCE);
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);
		ArchitecturePackageImpl theArchitecturePackage = (ArchitecturePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ArchitecturePackage.eNS_URI) instanceof ArchitecturePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ArchitecturePackage.eNS_URI) : ArchitecturePackage.eINSTANCE);

		// Create package meta-data objects
		theDefinitionPackage.createPackageContents();
		theCsmPackage.createPackageContents();
		theCorePackage.createPackageContents();
		theArchitecturePackage.createPackageContents();

		// Initialize created meta-data
		theDefinitionPackage.initializePackageContents();
		theCsmPackage.initializePackageContents();
		theCorePackage.initializePackageContents();
		theArchitecturePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDefinitionPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DefinitionPackage.eNS_URI, theDefinitionPackage);
		return theDefinitionPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDefinition() {
		return definitionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemDefinition() {
		return systemDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemDefinition_Descriptors() {
		return (EReference)systemDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCloudDefinition() {
		return cloudDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudDefinition_Descriptor() {
		return (EReference)cloudDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudDefinition_InfrastructureDescriptors() {
		return (EReference)cloudDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudDefinition_PlatformDescriptors() {
		return (EReference)cloudDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSoftwareDefinition() {
		return softwareDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSoftwareDefinition_DeployablePlatformServiceDescriptors() {
		return (EReference)softwareDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDescriptor() {
		return descriptorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescriptor_ProviderID() {
		return (EAttribute)descriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCloudEnvironmentDescriptor() {
		return cloudEnvironmentDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudEnvironmentDescriptor_AvailabilityZones() {
		return (EReference)cloudEnvironmentDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudEnvironmentDescriptor_Regions() {
		return (EReference)cloudEnvironmentDescriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRegionDescriptor() {
		return regionDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAvailabilityZoneDescriptor() {
		return availabilityZoneDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAvailabilityZoneDescriptor_InternalConnectionDescriptor() {
		return (EReference)availabilityZoneDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInfrastructureServiceDescriptor() {
		return infrastructureServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNetworkInfrastructureServiceDescriptor() {
		return networkInfrastructureServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNetworkInfrastructureServiceDescriptor_Bandwidth() {
		return (EAttribute)networkInfrastructureServiceDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNetworkInfrastructureServiceDescriptor_Latency() {
		return (EAttribute)networkInfrastructureServiceDescriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComputingInfrastructureServiceDescriptor() {
		return computingInfrastructureServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComputingInfrastructureServiceDescriptor_Memory() {
		return (EAttribute)computingInfrastructureServiceDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComputingInfrastructureServiceDescriptor_Cpu() {
		return (EAttribute)computingInfrastructureServiceDescriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComputingInfrastructureServiceDescriptor_CpuUnits() {
		return (EAttribute)computingInfrastructureServiceDescriptorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComputingInfrastructureServiceDescriptor_Storage() {
		return (EAttribute)computingInfrastructureServiceDescriptorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeployablePlatformServiceDescriptor() {
		return deployablePlatformServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExternalPlatformServiceDescriptor() {
		return externalPlatformServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeployableRuntimeServiceDescriptor() {
		return deployableRuntimeServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExternalRuntimeServiceDescriptor() {
		return externalRuntimeServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeployableSupportServiceDescriptor() {
		return deployableSupportServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExternalSupportServiceDescriptor() {
		return externalSupportServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public DefinitionFactory getDefinitionFactory() {
		return (DefinitionFactory)getEFactoryInstance();
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
		definitionEClass = createEClass(DEFINITION);

		systemDefinitionEClass = createEClass(SYSTEM_DEFINITION);
		createEReference(systemDefinitionEClass, SYSTEM_DEFINITION__DESCRIPTORS);

		cloudDefinitionEClass = createEClass(CLOUD_DEFINITION);
		createEReference(cloudDefinitionEClass, CLOUD_DEFINITION__DESCRIPTOR);
		createEReference(cloudDefinitionEClass, CLOUD_DEFINITION__INFRASTRUCTURE_DESCRIPTORS);
		createEReference(cloudDefinitionEClass, CLOUD_DEFINITION__PLATFORM_DESCRIPTORS);

		softwareDefinitionEClass = createEClass(SOFTWARE_DEFINITION);
		createEReference(softwareDefinitionEClass, SOFTWARE_DEFINITION__DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTORS);

		descriptorEClass = createEClass(DESCRIPTOR);
		createEAttribute(descriptorEClass, DESCRIPTOR__PROVIDER_ID);

		cloudEnvironmentDescriptorEClass = createEClass(CLOUD_ENVIRONMENT_DESCRIPTOR);
		createEReference(cloudEnvironmentDescriptorEClass, CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES);
		createEReference(cloudEnvironmentDescriptorEClass, CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS);

		regionDescriptorEClass = createEClass(REGION_DESCRIPTOR);

		availabilityZoneDescriptorEClass = createEClass(AVAILABILITY_ZONE_DESCRIPTOR);
		createEReference(availabilityZoneDescriptorEClass, AVAILABILITY_ZONE_DESCRIPTOR__INTERNAL_CONNECTION_DESCRIPTOR);

		infrastructureServiceDescriptorEClass = createEClass(INFRASTRUCTURE_SERVICE_DESCRIPTOR);

		networkInfrastructureServiceDescriptorEClass = createEClass(NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR);
		createEAttribute(networkInfrastructureServiceDescriptorEClass, NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH);
		createEAttribute(networkInfrastructureServiceDescriptorEClass, NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY);

		computingInfrastructureServiceDescriptorEClass = createEClass(COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR);
		createEAttribute(computingInfrastructureServiceDescriptorEClass, COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__MEMORY);
		createEAttribute(computingInfrastructureServiceDescriptorEClass, COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU);
		createEAttribute(computingInfrastructureServiceDescriptorEClass, COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU_UNITS);
		createEAttribute(computingInfrastructureServiceDescriptorEClass, COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__STORAGE);

		deployablePlatformServiceDescriptorEClass = createEClass(DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR);

		externalPlatformServiceDescriptorEClass = createEClass(EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR);

		deployableRuntimeServiceDescriptorEClass = createEClass(DEPLOYABLE_RUNTIME_SERVICE_DESCRIPTOR);

		externalRuntimeServiceDescriptorEClass = createEClass(EXTERNAL_RUNTIME_SERVICE_DESCRIPTOR);

		deployableSupportServiceDescriptorEClass = createEClass(DEPLOYABLE_SUPPORT_SERVICE_DESCRIPTOR);

		externalSupportServiceDescriptorEClass = createEClass(EXTERNAL_SUPPORT_SERVICE_DESCRIPTOR);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		systemDefinitionEClass.getESuperTypes().add(this.getDefinition());
		cloudDefinitionEClass.getESuperTypes().add(this.getDefinition());
		softwareDefinitionEClass.getESuperTypes().add(this.getDefinition());
		descriptorEClass.getESuperTypes().add(theCorePackage.getEntity());
		cloudEnvironmentDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		regionDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		availabilityZoneDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		infrastructureServiceDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		networkInfrastructureServiceDescriptorEClass.getESuperTypes().add(this.getInfrastructureServiceDescriptor());
		computingInfrastructureServiceDescriptorEClass.getESuperTypes().add(this.getInfrastructureServiceDescriptor());
		deployablePlatformServiceDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		externalPlatformServiceDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		deployableRuntimeServiceDescriptorEClass.getESuperTypes().add(this.getDeployablePlatformServiceDescriptor());
		externalRuntimeServiceDescriptorEClass.getESuperTypes().add(this.getExternalPlatformServiceDescriptor());
		deployableSupportServiceDescriptorEClass.getESuperTypes().add(this.getDeployablePlatformServiceDescriptor());
		externalSupportServiceDescriptorEClass.getESuperTypes().add(this.getExternalPlatformServiceDescriptor());

		// Initialize classes and features; add operations and parameters
		initEClass(definitionEClass, Definition.class, "Definition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(systemDefinitionEClass, SystemDefinition.class, "SystemDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSystemDefinition_Descriptors(), this.getDescriptor(), null, "descriptors", null, 0, -1, SystemDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cloudDefinitionEClass, CloudDefinition.class, "CloudDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCloudDefinition_Descriptor(), this.getCloudEnvironmentDescriptor(), null, "descriptor", null, 1, 1, CloudDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudDefinition_InfrastructureDescriptors(), this.getInfrastructureServiceDescriptor(), null, "infrastructureDescriptors", null, 0, -1, CloudDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudDefinition_PlatformDescriptors(), this.getExternalPlatformServiceDescriptor(), null, "platformDescriptors", null, 0, -1, CloudDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(softwareDefinitionEClass, SoftwareDefinition.class, "SoftwareDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSoftwareDefinition_DeployablePlatformServiceDescriptors(), this.getDeployablePlatformServiceDescriptor(), null, "deployablePlatformServiceDescriptors", null, 0, -1, SoftwareDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(descriptorEClass, eu.cloudscaleproject.env.csm.definition.Descriptor.class, "Descriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDescriptor_ProviderID(), ecorePackage.getEString(), "providerID", null, 0, 1, eu.cloudscaleproject.env.csm.definition.Descriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cloudEnvironmentDescriptorEClass, CloudEnvironmentDescriptor.class, "CloudEnvironmentDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCloudEnvironmentDescriptor_AvailabilityZones(), this.getAvailabilityZoneDescriptor(), null, "availabilityZones", null, 0, -1, CloudEnvironmentDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudEnvironmentDescriptor_Regions(), this.getRegionDescriptor(), null, "regions", null, 0, -1, CloudEnvironmentDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(regionDescriptorEClass, RegionDescriptor.class, "RegionDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(availabilityZoneDescriptorEClass, AvailabilityZoneDescriptor.class, "AvailabilityZoneDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAvailabilityZoneDescriptor_InternalConnectionDescriptor(), this.getNetworkInfrastructureServiceDescriptor(), null, "internalConnectionDescriptor", null, 1, 1, AvailabilityZoneDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(infrastructureServiceDescriptorEClass, InfrastructureServiceDescriptor.class, "InfrastructureServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(networkInfrastructureServiceDescriptorEClass, NetworkInfrastructureServiceDescriptor.class, "NetworkInfrastructureServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNetworkInfrastructureServiceDescriptor_Bandwidth(), ecorePackage.getEInt(), "bandwidth", null, 0, 1, NetworkInfrastructureServiceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNetworkInfrastructureServiceDescriptor_Latency(), ecorePackage.getEInt(), "latency", null, 0, 1, NetworkInfrastructureServiceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(computingInfrastructureServiceDescriptorEClass, ComputingInfrastructureServiceDescriptor.class, "ComputingInfrastructureServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComputingInfrastructureServiceDescriptor_Memory(), ecorePackage.getEInt(), "memory", null, 0, 1, ComputingInfrastructureServiceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComputingInfrastructureServiceDescriptor_Cpu(), ecorePackage.getEInt(), "cpu", null, 0, 1, ComputingInfrastructureServiceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComputingInfrastructureServiceDescriptor_CpuUnits(), ecorePackage.getEInt(), "cpuUnits", null, 0, 1, ComputingInfrastructureServiceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComputingInfrastructureServiceDescriptor_Storage(), ecorePackage.getEInt(), "storage", null, 0, 1, ComputingInfrastructureServiceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(deployablePlatformServiceDescriptorEClass, DeployablePlatformServiceDescriptor.class, "DeployablePlatformServiceDescriptor", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(externalPlatformServiceDescriptorEClass, ExternalPlatformServiceDescriptor.class, "ExternalPlatformServiceDescriptor", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(deployableRuntimeServiceDescriptorEClass, DeployableRuntimeServiceDescriptor.class, "DeployableRuntimeServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(externalRuntimeServiceDescriptorEClass, ExternalRuntimeServiceDescriptor.class, "ExternalRuntimeServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(deployableSupportServiceDescriptorEClass, DeployableSupportServiceDescriptor.class, "DeployableSupportServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(externalSupportServiceDescriptorEClass, ExternalSupportServiceDescriptor.class, "ExternalSupportServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
	}

} // DefinitionPackageImpl
