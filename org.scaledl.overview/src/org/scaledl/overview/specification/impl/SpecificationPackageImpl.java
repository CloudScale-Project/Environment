/**
 */
package org.scaledl.overview.specification.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.scaledl.overview.OverviewPackage;
import org.scaledl.overview.application.ApplicationPackage;
import org.scaledl.overview.application.impl.ApplicationPackageImpl;
import org.scaledl.overview.architecture.ArchitecturePackage;
import org.scaledl.overview.architecture.impl.ArchitecturePackageImpl;
import org.scaledl.overview.core.CorePackage;
import org.scaledl.overview.core.impl.CorePackageImpl;
import org.scaledl.overview.deployment.DeploymentPackage;
import org.scaledl.overview.deployment.impl.DeploymentPackageImpl;
import org.scaledl.overview.impl.OverviewPackageImpl;
import org.scaledl.overview.parametertype.ParametertypePackage;
import org.scaledl.overview.parametertype.impl.ParametertypePackageImpl;
import org.scaledl.overview.specification.AvailabilityZoneDescriptor;
import org.scaledl.overview.specification.CloudEnvironmentDescriptor;
import org.scaledl.overview.specification.CloudSpecification;
import org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.ComputingResourceDescriptor;
import org.scaledl.overview.specification.ExternalSoftwareServiceDescriptor;
import org.scaledl.overview.specification.InfrastructureServiceDescriptor;
import org.scaledl.overview.specification.NetworkInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.PlatformRuntimeServiceDescriptor;
import org.scaledl.overview.specification.PlatformServiceDescriptor;
import org.scaledl.overview.specification.PlatformSupportServiceDescriptor;
import org.scaledl.overview.specification.ProvidedPlatformRuntimeServiceDescriptor;
import org.scaledl.overview.specification.ProvidedPlatformServiceDescriptor;
import org.scaledl.overview.specification.ProvidedPlatformSupportServiceDescriptor;
import org.scaledl.overview.specification.ProvidedServiceDescriptor;
import org.scaledl.overview.specification.ProvidedSoftwareServiceDescriptor;
import org.scaledl.overview.specification.RegionDescriptor;
import org.scaledl.overview.specification.ServiceDescriptor;
import org.scaledl.overview.specification.ServiceSpecification;
import org.scaledl.overview.specification.SoftwareServiceDescriptor;
import org.scaledl.overview.specification.Specification;
import org.scaledl.overview.specification.SpecificationFactory;
import org.scaledl.overview.specification.SpecificationPackage;
import org.scaledl.overview.specification.SystemSpecification;
import org.scaledl.overview.specification.sla.SlaPackage;
import org.scaledl.overview.specification.sla.impl.SlaPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SpecificationPackageImpl extends EPackageImpl implements SpecificationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass systemSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cloudSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass descriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cloudEnvironmentDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass regionDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass availabilityZoneDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass providedServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass infrastructureServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass networkInfrastructureServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass computingInfrastructureServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass computingResourceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass platformServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass platformRuntimeServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass platformSupportServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass providedPlatformServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass providedPlatformRuntimeServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass providedPlatformSupportServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass softwareServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass providedSoftwareServiceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalSoftwareServiceDescriptorEClass = null;

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
	 * @see org.scaledl.overview.specification.SpecificationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SpecificationPackageImpl() {
		super(eNS_URI, SpecificationFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link SpecificationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SpecificationPackage init() {
		if (isInited) return (SpecificationPackage)EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI);

		// Obtain or create and register package
		SpecificationPackageImpl theSpecificationPackage = (SpecificationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SpecificationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SpecificationPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		OverviewPackageImpl theOverviewPackage = (OverviewPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OverviewPackage.eNS_URI) instanceof OverviewPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OverviewPackage.eNS_URI) : OverviewPackage.eINSTANCE);
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);
		ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) instanceof ApplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) : ApplicationPackage.eINSTANCE);
		ArchitecturePackageImpl theArchitecturePackage = (ArchitecturePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ArchitecturePackage.eNS_URI) instanceof ArchitecturePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ArchitecturePackage.eNS_URI) : ArchitecturePackage.eINSTANCE);
		DeploymentPackageImpl theDeploymentPackage = (DeploymentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI) instanceof DeploymentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI) : DeploymentPackage.eINSTANCE);
		SlaPackageImpl theSlaPackage = (SlaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SlaPackage.eNS_URI) instanceof SlaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SlaPackage.eNS_URI) : SlaPackage.eINSTANCE);
		ParametertypePackageImpl theParametertypePackage = (ParametertypePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ParametertypePackage.eNS_URI) instanceof ParametertypePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ParametertypePackage.eNS_URI) : ParametertypePackage.eINSTANCE);

		// Create package meta-data objects
		theSpecificationPackage.createPackageContents();
		theOverviewPackage.createPackageContents();
		theCorePackage.createPackageContents();
		theApplicationPackage.createPackageContents();
		theArchitecturePackage.createPackageContents();
		theDeploymentPackage.createPackageContents();
		theSlaPackage.createPackageContents();
		theParametertypePackage.createPackageContents();

		// Initialize created meta-data
		theSpecificationPackage.initializePackageContents();
		theOverviewPackage.initializePackageContents();
		theCorePackage.initializePackageContents();
		theApplicationPackage.initializePackageContents();
		theArchitecturePackage.initializePackageContents();
		theDeploymentPackage.initializePackageContents();
		theSlaPackage.initializePackageContents();
		theParametertypePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSpecificationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SpecificationPackage.eNS_URI, theSpecificationPackage);
		return theSpecificationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpecification() {
		return specificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemSpecification() {
		return systemSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemSpecification_Descriptors() {
		return (EReference)systemSpecificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCloudSpecification() {
		return cloudSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudSpecification_Descriptor() {
		return (EReference)cloudSpecificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudSpecification_InfrastructureServiceDescriptors() {
		return (EReference)cloudSpecificationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudSpecification_PlatformServiceDescriptors() {
		return (EReference)cloudSpecificationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudSpecification_SoftwareServiceDescriptors() {
		return (EReference)cloudSpecificationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServiceSpecification() {
		return serviceSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceSpecification_ServiceDescriptors() {
		return (EReference)serviceSpecificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDescriptor() {
		return descriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescriptor_ProviderID() {
		return (EAttribute)descriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCloudEnvironmentDescriptor() {
		return cloudEnvironmentDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudEnvironmentDescriptor_AvailabilityZones() {
		return (EReference)cloudEnvironmentDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudEnvironmentDescriptor_Regions() {
		return (EReference)cloudEnvironmentDescriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRegionDescriptor() {
		return regionDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAvailabilityZoneDescriptor() {
		return availabilityZoneDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAvailabilityZoneDescriptor_NetworkInfrastructureServiceDescriptor() {
		return (EReference)availabilityZoneDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServiceDescriptor() {
		return serviceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvidedServiceDescriptor() {
		return providedServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProvidedServiceDescriptor_Sla() {
		return (EReference)providedServiceDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInfrastructureServiceDescriptor() {
		return infrastructureServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNetworkInfrastructureServiceDescriptor() {
		return networkInfrastructureServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNetworkInfrastructureServiceDescriptor_Bandwidth() {
		return (EAttribute)networkInfrastructureServiceDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNetworkInfrastructureServiceDescriptor_Latency() {
		return (EAttribute)networkInfrastructureServiceDescriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComputingInfrastructureServiceDescriptor() {
		return computingInfrastructureServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComputingInfrastructureServiceDescriptor_ComputingResourceDescriptors() {
		return (EReference)computingInfrastructureServiceDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComputingInfrastructureServiceDescriptor_GeneralPurpose() {
		return (EAttribute)computingInfrastructureServiceDescriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComputingResourceDescriptor() {
		return computingResourceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComputingResourceDescriptor_Editable() {
		return (EAttribute)computingResourceDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComputingResourceDescriptor_Memory() {
		return (EAttribute)computingResourceDescriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComputingResourceDescriptor_Cpu() {
		return (EAttribute)computingResourceDescriptorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComputingResourceDescriptor_CpuUnits() {
		return (EAttribute)computingResourceDescriptorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComputingResourceDescriptor_Storage() {
		return (EAttribute)computingResourceDescriptorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPlatformServiceDescriptor() {
		return platformServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPlatformRuntimeServiceDescriptor() {
		return platformRuntimeServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPlatformSupportServiceDescriptor() {
		return platformSupportServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvidedPlatformServiceDescriptor() {
		return providedPlatformServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProvidedPlatformServiceDescriptor_InfrastructureServiceDescriptor() {
		return (EReference)providedPlatformServiceDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvidedPlatformRuntimeServiceDescriptor() {
		return providedPlatformRuntimeServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvidedPlatformSupportServiceDescriptor() {
		return providedPlatformSupportServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSoftwareServiceDescriptor() {
		return softwareServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvidedSoftwareServiceDescriptor() {
		return providedSoftwareServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProvidedSoftwareServiceDescriptor_Operations() {
		return (EReference)providedSoftwareServiceDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExternalSoftwareServiceDescriptor() {
		return externalSoftwareServiceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecificationFactory getSpecificationFactory() {
		return (SpecificationFactory)getEFactoryInstance();
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
		specificationEClass = createEClass(SPECIFICATION);

		systemSpecificationEClass = createEClass(SYSTEM_SPECIFICATION);
		createEReference(systemSpecificationEClass, SYSTEM_SPECIFICATION__DESCRIPTORS);

		cloudSpecificationEClass = createEClass(CLOUD_SPECIFICATION);
		createEReference(cloudSpecificationEClass, CLOUD_SPECIFICATION__DESCRIPTOR);
		createEReference(cloudSpecificationEClass, CLOUD_SPECIFICATION__INFRASTRUCTURE_SERVICE_DESCRIPTORS);
		createEReference(cloudSpecificationEClass, CLOUD_SPECIFICATION__PLATFORM_SERVICE_DESCRIPTORS);
		createEReference(cloudSpecificationEClass, CLOUD_SPECIFICATION__SOFTWARE_SERVICE_DESCRIPTORS);

		serviceSpecificationEClass = createEClass(SERVICE_SPECIFICATION);
		createEReference(serviceSpecificationEClass, SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS);

		descriptorEClass = createEClass(DESCRIPTOR);
		createEAttribute(descriptorEClass, DESCRIPTOR__PROVIDER_ID);

		cloudEnvironmentDescriptorEClass = createEClass(CLOUD_ENVIRONMENT_DESCRIPTOR);
		createEReference(cloudEnvironmentDescriptorEClass, CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES);
		createEReference(cloudEnvironmentDescriptorEClass, CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS);

		regionDescriptorEClass = createEClass(REGION_DESCRIPTOR);

		availabilityZoneDescriptorEClass = createEClass(AVAILABILITY_ZONE_DESCRIPTOR);
		createEReference(availabilityZoneDescriptorEClass, AVAILABILITY_ZONE_DESCRIPTOR__NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR);

		serviceDescriptorEClass = createEClass(SERVICE_DESCRIPTOR);

		providedServiceDescriptorEClass = createEClass(PROVIDED_SERVICE_DESCRIPTOR);
		createEReference(providedServiceDescriptorEClass, PROVIDED_SERVICE_DESCRIPTOR__SLA);

		infrastructureServiceDescriptorEClass = createEClass(INFRASTRUCTURE_SERVICE_DESCRIPTOR);

		networkInfrastructureServiceDescriptorEClass = createEClass(NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR);
		createEAttribute(networkInfrastructureServiceDescriptorEClass, NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH);
		createEAttribute(networkInfrastructureServiceDescriptorEClass, NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY);

		computingInfrastructureServiceDescriptorEClass = createEClass(COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR);
		createEReference(computingInfrastructureServiceDescriptorEClass, COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__COMPUTING_RESOURCE_DESCRIPTORS);
		createEAttribute(computingInfrastructureServiceDescriptorEClass, COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__GENERAL_PURPOSE);

		computingResourceDescriptorEClass = createEClass(COMPUTING_RESOURCE_DESCRIPTOR);
		createEAttribute(computingResourceDescriptorEClass, COMPUTING_RESOURCE_DESCRIPTOR__EDITABLE);
		createEAttribute(computingResourceDescriptorEClass, COMPUTING_RESOURCE_DESCRIPTOR__MEMORY);
		createEAttribute(computingResourceDescriptorEClass, COMPUTING_RESOURCE_DESCRIPTOR__CPU);
		createEAttribute(computingResourceDescriptorEClass, COMPUTING_RESOURCE_DESCRIPTOR__CPU_UNITS);
		createEAttribute(computingResourceDescriptorEClass, COMPUTING_RESOURCE_DESCRIPTOR__STORAGE);

		platformServiceDescriptorEClass = createEClass(PLATFORM_SERVICE_DESCRIPTOR);

		platformRuntimeServiceDescriptorEClass = createEClass(PLATFORM_RUNTIME_SERVICE_DESCRIPTOR);

		platformSupportServiceDescriptorEClass = createEClass(PLATFORM_SUPPORT_SERVICE_DESCRIPTOR);

		providedPlatformServiceDescriptorEClass = createEClass(PROVIDED_PLATFORM_SERVICE_DESCRIPTOR);
		createEReference(providedPlatformServiceDescriptorEClass, PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__INFRASTRUCTURE_SERVICE_DESCRIPTOR);

		providedPlatformRuntimeServiceDescriptorEClass = createEClass(PROVIDED_PLATFORM_RUNTIME_SERVICE_DESCRIPTOR);

		providedPlatformSupportServiceDescriptorEClass = createEClass(PROVIDED_PLATFORM_SUPPORT_SERVICE_DESCRIPTOR);

		softwareServiceDescriptorEClass = createEClass(SOFTWARE_SERVICE_DESCRIPTOR);

		providedSoftwareServiceDescriptorEClass = createEClass(PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR);
		createEReference(providedSoftwareServiceDescriptorEClass, PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__OPERATIONS);

		externalSoftwareServiceDescriptorEClass = createEClass(EXTERNAL_SOFTWARE_SERVICE_DESCRIPTOR);
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
		SlaPackage theSlaPackage = (SlaPackage)EPackage.Registry.INSTANCE.getEPackage(SlaPackage.eNS_URI);
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);
		ApplicationPackage theApplicationPackage = (ApplicationPackage)EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theSlaPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		systemSpecificationEClass.getESuperTypes().add(this.getSpecification());
		cloudSpecificationEClass.getESuperTypes().add(this.getSpecification());
		serviceSpecificationEClass.getESuperTypes().add(this.getSpecification());
		descriptorEClass.getESuperTypes().add(theCorePackage.getEntity());
		cloudEnvironmentDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		regionDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		availabilityZoneDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		serviceDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		providedServiceDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		infrastructureServiceDescriptorEClass.getESuperTypes().add(this.getProvidedServiceDescriptor());
		networkInfrastructureServiceDescriptorEClass.getESuperTypes().add(this.getInfrastructureServiceDescriptor());
		computingInfrastructureServiceDescriptorEClass.getESuperTypes().add(this.getInfrastructureServiceDescriptor());
		computingResourceDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		platformServiceDescriptorEClass.getESuperTypes().add(this.getServiceDescriptor());
		platformRuntimeServiceDescriptorEClass.getESuperTypes().add(this.getPlatformServiceDescriptor());
		platformSupportServiceDescriptorEClass.getESuperTypes().add(this.getPlatformServiceDescriptor());
		providedPlatformServiceDescriptorEClass.getESuperTypes().add(this.getPlatformServiceDescriptor());
		providedPlatformServiceDescriptorEClass.getESuperTypes().add(this.getProvidedServiceDescriptor());
		providedPlatformRuntimeServiceDescriptorEClass.getESuperTypes().add(this.getProvidedPlatformServiceDescriptor());
		providedPlatformSupportServiceDescriptorEClass.getESuperTypes().add(this.getProvidedPlatformServiceDescriptor());
		softwareServiceDescriptorEClass.getESuperTypes().add(this.getServiceDescriptor());
		providedSoftwareServiceDescriptorEClass.getESuperTypes().add(this.getSoftwareServiceDescriptor());
		providedSoftwareServiceDescriptorEClass.getESuperTypes().add(this.getProvidedServiceDescriptor());
		externalSoftwareServiceDescriptorEClass.getESuperTypes().add(this.getProvidedSoftwareServiceDescriptor());

		// Initialize classes and features; add operations and parameters
		initEClass(specificationEClass, Specification.class, "Specification", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(systemSpecificationEClass, SystemSpecification.class, "SystemSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSystemSpecification_Descriptors(), this.getDescriptor(), null, "descriptors", null, 0, -1, SystemSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cloudSpecificationEClass, CloudSpecification.class, "CloudSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCloudSpecification_Descriptor(), this.getCloudEnvironmentDescriptor(), null, "descriptor", null, 1, 1, CloudSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudSpecification_InfrastructureServiceDescriptors(), this.getInfrastructureServiceDescriptor(), null, "infrastructureServiceDescriptors", null, 0, -1, CloudSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudSpecification_PlatformServiceDescriptors(), this.getProvidedServiceDescriptor(), null, "platformServiceDescriptors", null, 0, -1, CloudSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudSpecification_SoftwareServiceDescriptors(), this.getProvidedServiceDescriptor(), null, "softwareServiceDescriptors", null, 0, -1, CloudSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceSpecificationEClass, ServiceSpecification.class, "ServiceSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getServiceSpecification_ServiceDescriptors(), this.getServiceDescriptor(), null, "serviceDescriptors", null, 0, -1, ServiceSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(descriptorEClass, org.scaledl.overview.specification.Descriptor.class, "Descriptor", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDescriptor_ProviderID(), ecorePackage.getEString(), "providerID", null, 0, 1, org.scaledl.overview.specification.Descriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cloudEnvironmentDescriptorEClass, CloudEnvironmentDescriptor.class, "CloudEnvironmentDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCloudEnvironmentDescriptor_AvailabilityZones(), this.getAvailabilityZoneDescriptor(), null, "availabilityZones", null, 0, -1, CloudEnvironmentDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudEnvironmentDescriptor_Regions(), this.getRegionDescriptor(), null, "regions", null, 0, -1, CloudEnvironmentDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(regionDescriptorEClass, RegionDescriptor.class, "RegionDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(availabilityZoneDescriptorEClass, AvailabilityZoneDescriptor.class, "AvailabilityZoneDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAvailabilityZoneDescriptor_NetworkInfrastructureServiceDescriptor(), this.getNetworkInfrastructureServiceDescriptor(), null, "networkInfrastructureServiceDescriptor", null, 1, 1, AvailabilityZoneDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceDescriptorEClass, ServiceDescriptor.class, "ServiceDescriptor", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(providedServiceDescriptorEClass, ProvidedServiceDescriptor.class, "ProvidedServiceDescriptor", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProvidedServiceDescriptor_Sla(), theSlaPackage.getSla(), null, "sla", null, 0, 1, ProvidedServiceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(infrastructureServiceDescriptorEClass, InfrastructureServiceDescriptor.class, "InfrastructureServiceDescriptor", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(networkInfrastructureServiceDescriptorEClass, NetworkInfrastructureServiceDescriptor.class, "NetworkInfrastructureServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNetworkInfrastructureServiceDescriptor_Bandwidth(), ecorePackage.getEInt(), "bandwidth", null, 0, 1, NetworkInfrastructureServiceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNetworkInfrastructureServiceDescriptor_Latency(), ecorePackage.getEInt(), "latency", null, 0, 1, NetworkInfrastructureServiceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(computingInfrastructureServiceDescriptorEClass, ComputingInfrastructureServiceDescriptor.class, "ComputingInfrastructureServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComputingInfrastructureServiceDescriptor_ComputingResourceDescriptors(), this.getComputingResourceDescriptor(), null, "computingResourceDescriptors", null, 0, -1, ComputingInfrastructureServiceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComputingInfrastructureServiceDescriptor_GeneralPurpose(), ecorePackage.getEBoolean(), "generalPurpose", null, 0, 1, ComputingInfrastructureServiceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(computingResourceDescriptorEClass, ComputingResourceDescriptor.class, "ComputingResourceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComputingResourceDescriptor_Editable(), ecorePackage.getEBoolean(), "editable", null, 0, 1, ComputingResourceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComputingResourceDescriptor_Memory(), ecorePackage.getEInt(), "memory", null, 0, 1, ComputingResourceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComputingResourceDescriptor_Cpu(), ecorePackage.getEInt(), "cpu", null, 0, 1, ComputingResourceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComputingResourceDescriptor_CpuUnits(), ecorePackage.getEInt(), "cpuUnits", null, 0, 1, ComputingResourceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComputingResourceDescriptor_Storage(), ecorePackage.getEInt(), "storage", null, 0, 1, ComputingResourceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(platformServiceDescriptorEClass, PlatformServiceDescriptor.class, "PlatformServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(platformRuntimeServiceDescriptorEClass, PlatformRuntimeServiceDescriptor.class, "PlatformRuntimeServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(platformSupportServiceDescriptorEClass, PlatformSupportServiceDescriptor.class, "PlatformSupportServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(providedPlatformServiceDescriptorEClass, ProvidedPlatformServiceDescriptor.class, "ProvidedPlatformServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProvidedPlatformServiceDescriptor_InfrastructureServiceDescriptor(), this.getComputingInfrastructureServiceDescriptor(), null, "infrastructureServiceDescriptor", null, 0, 1, ProvidedPlatformServiceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(providedPlatformRuntimeServiceDescriptorEClass, ProvidedPlatformRuntimeServiceDescriptor.class, "ProvidedPlatformRuntimeServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(providedPlatformSupportServiceDescriptorEClass, ProvidedPlatformSupportServiceDescriptor.class, "ProvidedPlatformSupportServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(softwareServiceDescriptorEClass, SoftwareServiceDescriptor.class, "SoftwareServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(providedSoftwareServiceDescriptorEClass, ProvidedSoftwareServiceDescriptor.class, "ProvidedSoftwareServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProvidedSoftwareServiceDescriptor_Operations(), theApplicationPackage.getOperation(), null, "operations", null, 1, -1, ProvidedSoftwareServiceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(externalSoftwareServiceDescriptorEClass, ExternalSoftwareServiceDescriptor.class, "ExternalSoftwareServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
	}

} //SpecificationPackageImpl
