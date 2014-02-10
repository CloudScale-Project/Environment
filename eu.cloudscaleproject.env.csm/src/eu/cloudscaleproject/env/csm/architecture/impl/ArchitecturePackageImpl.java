/**
 */
package eu.cloudscaleproject.env.csm.architecture.impl;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import eu.cloudscaleproject.env.csm.CsmPackage;
import eu.cloudscaleproject.env.csm.architecture.Architecture;
import eu.cloudscaleproject.env.csm.architecture.ArchitectureFactory;
import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.Connection;
import eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService;
import eu.cloudscaleproject.env.csm.architecture.DeployableRuntimeService;
import eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService;
import eu.cloudscaleproject.env.csm.architecture.DeployableSupportService;
import eu.cloudscaleproject.env.csm.architecture.ExternalConnection;
import eu.cloudscaleproject.env.csm.architecture.ExternalPlatformService;
import eu.cloudscaleproject.env.csm.architecture.ExternalRuntimeService;
import eu.cloudscaleproject.env.csm.architecture.ExternalSoftwareService;
import eu.cloudscaleproject.env.csm.architecture.ExternalSupportService;
import eu.cloudscaleproject.env.csm.architecture.HybridConnection;
import eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer;
import eu.cloudscaleproject.env.csm.architecture.InfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.InternalConnection;
import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;
import eu.cloudscaleproject.env.csm.architecture.PlatformLayer;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.architecture.Proxy;
import eu.cloudscaleproject.env.csm.architecture.Service;
import eu.cloudscaleproject.env.csm.architecture.ServiceProxy;
import eu.cloudscaleproject.env.csm.architecture.SoftwareLayer;
import eu.cloudscaleproject.env.csm.architecture.SoftwareService;
import eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer;
import eu.cloudscaleproject.env.csm.architecture.UsageProxy;
import eu.cloudscaleproject.env.csm.core.CorePackage;
import eu.cloudscaleproject.env.csm.core.impl.CorePackageImpl;
import eu.cloudscaleproject.env.csm.definition.DefinitionPackage;
import eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl;
import eu.cloudscaleproject.env.csm.impl.CsmPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class ArchitecturePackageImpl extends EPackageImpl implements
		ArchitecturePackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass architectureEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cloudEnvironmentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass infrastructureLayerEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass platformLayerEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass softwareLayerEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass infrastructureServiceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass platformServiceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass softwareServiceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deployablePlatformServiceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalPlatformServiceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deployableRuntimeServiceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deployableSupportServiceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalRuntimeServiceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalSupportServiceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalSoftwareServiceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deployableSoftwareServiceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass computingInfrastructureServiceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass softwareServiceContainerEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationInterfaceContainerEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass internalConnectionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hybridConnectionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalConnectionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass proxyEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceProxyEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass usageProxyEClass = null;

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
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ArchitecturePackageImpl() {
		super(eNS_URI, ArchitectureFactory.eINSTANCE);
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
	 * This method is used to initialize {@link ArchitecturePackage#eINSTANCE}
	 * when that field is accessed. Clients should not invoke it directly.
	 * Instead, they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ArchitecturePackage init() {
		if (isInited) return (ArchitecturePackage)EPackage.Registry.INSTANCE.getEPackage(ArchitecturePackage.eNS_URI);

		// Obtain or create and register package
		ArchitecturePackageImpl theArchitecturePackage = (ArchitecturePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ArchitecturePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ArchitecturePackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		CsmPackageImpl theCsmPackage = (CsmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CsmPackage.eNS_URI) instanceof CsmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CsmPackage.eNS_URI) : CsmPackage.eINSTANCE);
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);
		DefinitionPackageImpl theDefinitionPackage = (DefinitionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DefinitionPackage.eNS_URI) instanceof DefinitionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DefinitionPackage.eNS_URI) : DefinitionPackage.eINSTANCE);

		// Create package meta-data objects
		theArchitecturePackage.createPackageContents();
		theCsmPackage.createPackageContents();
		theCorePackage.createPackageContents();
		theDefinitionPackage.createPackageContents();

		// Initialize created meta-data
		theArchitecturePackage.initializePackageContents();
		theCsmPackage.initializePackageContents();
		theCorePackage.initializePackageContents();
		theDefinitionPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theArchitecturePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ArchitecturePackage.eNS_URI, theArchitecturePackage);
		return theArchitecturePackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArchitecture() {
		return architectureEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecture_CloudProviders() {
		return (EReference)architectureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecture_Proxies() {
		return (EReference)architectureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecture_ExternalConnections() {
		return (EReference)architectureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecture_HybridConnections() {
		return (EReference)architectureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecture_UnresolvedOperationInterfaces() {
		return (EReference)architectureEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCloudEnvironment() {
		return cloudEnvironmentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudEnvironment_InternalConnections() {
		return (EReference)cloudEnvironmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudEnvironment_CloudProviderDescriptor() {
		return (EReference)cloudEnvironmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudEnvironment_AvailabilityZoneDescriptor() {
		return (EReference)cloudEnvironmentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudEnvironment_Architecture() {
		return (EReference)cloudEnvironmentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudEnvironment_InfrastructureLayer() {
		return (EReference)cloudEnvironmentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudEnvironment_PlatformLayer() {
		return (EReference)cloudEnvironmentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudEnvironment_SoftwareLayer() {
		return (EReference)cloudEnvironmentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInfrastructureLayer() {
		return infrastructureLayerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInfrastructureLayer_Services() {
		return (EReference)infrastructureLayerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInfrastructureLayer_CloudProvider() {
		return (EReference)infrastructureLayerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPlatformLayer() {
		return platformLayerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPlatformLayer_Services() {
		return (EReference)platformLayerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPlatformLayer_CloudEnvironment() {
		return (EReference)platformLayerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSoftwareLayer() {
		return softwareLayerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSoftwareLayer_Services() {
		return (EReference)softwareLayerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSoftwareLayer_CloudProvider() {
		return (EReference)softwareLayerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getService() {
		return serviceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInfrastructureService() {
		return infrastructureServiceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInfrastructureService_InfrastructureLayer() {
		return (EReference)infrastructureServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPlatformService() {
		return platformServiceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPlatformService_PlatformLayer() {
		return (EReference)platformServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSoftwareService() {
		return softwareServiceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSoftwareService_SoftwareLayer() {
		return (EReference)softwareServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeployablePlatformService() {
		return deployablePlatformServiceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeployablePlatformService_ComputingInfrastructureService() {
		return (EReference)deployablePlatformServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeployablePlatformService_Descriptor() {
		return (EReference)deployablePlatformServiceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExternalPlatformService() {
		return externalPlatformServiceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExternalPlatformService_Descriptor() {
		return (EReference)externalPlatformServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeployableRuntimeService() {
		return deployableRuntimeServiceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeployableSupportService() {
		return deployableSupportServiceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExternalRuntimeService() {
		return externalRuntimeServiceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExternalSupportService() {
		return externalSupportServiceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExternalSoftwareService() {
		return externalSoftwareServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExternalSoftwareService_ServiceProxy() {
		return (EReference)externalSoftwareServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeployableSoftwareService() {
		return deployableSoftwareServiceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeployableSoftwareService_RuntimePlatformService() {
		return (EReference)deployableSoftwareServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComputingInfrastructureService() {
		return computingInfrastructureServiceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComputingInfrastructureService_Descriptor() {
		return (EReference)computingInfrastructureServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComputingInfrastructureService_PlatformServices() {
		return (EReference)computingInfrastructureServiceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSoftwareServiceContainer() {
		return softwareServiceContainerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSoftwareServiceContainer_SoftwareServices() {
		return (EReference)softwareServiceContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationInterfaceContainer() {
		return operationInterfaceContainerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationInterfaceContainer_ProvidedInterfaces() {
		return (EReference)operationInterfaceContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationInterfaceContainer_RequiredInterfaces() {
		return (EReference)operationInterfaceContainerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnection() {
		return connectionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInternalConnection() {
		return internalConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInternalConnection_SourcePlatformService() {
		return (EReference)internalConnectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInternalConnection_DestinationPlatformService() {
		return (EReference)internalConnectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHybridConnection() {
		return hybridConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHybridConnection_SourcePlatformService() {
		return (EReference)hybridConnectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHybridConnection_TargetPlatformService() {
		return (EReference)hybridConnectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHybridConnection_Descriptor() {
		return (EReference)hybridConnectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExternalConnection() {
		return externalConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExternalConnection_Proxy() {
		return (EReference)externalConnectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExternalConnection_PlatformService() {
		return (EReference)externalConnectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExternalConnection_IsOutbound() {
		return (EAttribute)externalConnectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExternalConnection_Descriptor() {
		return (EReference)externalConnectionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProxy() {
		return proxyEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServiceProxy() {
		return serviceProxyEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceProxy_SoftwareService() {
		return (EReference)serviceProxyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUsageProxy() {
		return usageProxyEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ArchitectureFactory getArchitectureFactory() {
		return (ArchitectureFactory)getEFactoryInstance();
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
		architectureEClass = createEClass(ARCHITECTURE);
		createEReference(architectureEClass, ARCHITECTURE__CLOUD_PROVIDERS);
		createEReference(architectureEClass, ARCHITECTURE__PROXIES);
		createEReference(architectureEClass, ARCHITECTURE__EXTERNAL_CONNECTIONS);
		createEReference(architectureEClass, ARCHITECTURE__HYBRID_CONNECTIONS);
		createEReference(architectureEClass, ARCHITECTURE__UNRESOLVED_OPERATION_INTERFACES);

		cloudEnvironmentEClass = createEClass(CLOUD_ENVIRONMENT);
		createEReference(cloudEnvironmentEClass, CLOUD_ENVIRONMENT__INTERNAL_CONNECTIONS);
		createEReference(cloudEnvironmentEClass, CLOUD_ENVIRONMENT__CLOUD_PROVIDER_DESCRIPTOR);
		createEReference(cloudEnvironmentEClass, CLOUD_ENVIRONMENT__AVAILABILITY_ZONE_DESCRIPTOR);
		createEReference(cloudEnvironmentEClass, CLOUD_ENVIRONMENT__ARCHITECTURE);
		createEReference(cloudEnvironmentEClass, CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER);
		createEReference(cloudEnvironmentEClass, CLOUD_ENVIRONMENT__PLATFORM_LAYER);
		createEReference(cloudEnvironmentEClass, CLOUD_ENVIRONMENT__SOFTWARE_LAYER);

		infrastructureLayerEClass = createEClass(INFRASTRUCTURE_LAYER);
		createEReference(infrastructureLayerEClass, INFRASTRUCTURE_LAYER__SERVICES);
		createEReference(infrastructureLayerEClass, INFRASTRUCTURE_LAYER__CLOUD_PROVIDER);

		platformLayerEClass = createEClass(PLATFORM_LAYER);
		createEReference(platformLayerEClass, PLATFORM_LAYER__SERVICES);
		createEReference(platformLayerEClass, PLATFORM_LAYER__CLOUD_ENVIRONMENT);

		softwareLayerEClass = createEClass(SOFTWARE_LAYER);
		createEReference(softwareLayerEClass, SOFTWARE_LAYER__SERVICES);
		createEReference(softwareLayerEClass, SOFTWARE_LAYER__CLOUD_PROVIDER);

		serviceEClass = createEClass(SERVICE);

		infrastructureServiceEClass = createEClass(INFRASTRUCTURE_SERVICE);
		createEReference(infrastructureServiceEClass, INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER);

		platformServiceEClass = createEClass(PLATFORM_SERVICE);
		createEReference(platformServiceEClass, PLATFORM_SERVICE__PLATFORM_LAYER);

		softwareServiceEClass = createEClass(SOFTWARE_SERVICE);
		createEReference(softwareServiceEClass, SOFTWARE_SERVICE__SOFTWARE_LAYER);

		deployablePlatformServiceEClass = createEClass(DEPLOYABLE_PLATFORM_SERVICE);
		createEReference(deployablePlatformServiceEClass, DEPLOYABLE_PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE);
		createEReference(deployablePlatformServiceEClass, DEPLOYABLE_PLATFORM_SERVICE__DESCRIPTOR);

		externalPlatformServiceEClass = createEClass(EXTERNAL_PLATFORM_SERVICE);
		createEReference(externalPlatformServiceEClass, EXTERNAL_PLATFORM_SERVICE__DESCRIPTOR);

		deployableRuntimeServiceEClass = createEClass(DEPLOYABLE_RUNTIME_SERVICE);

		deployableSupportServiceEClass = createEClass(DEPLOYABLE_SUPPORT_SERVICE);

		externalRuntimeServiceEClass = createEClass(EXTERNAL_RUNTIME_SERVICE);

		externalSupportServiceEClass = createEClass(EXTERNAL_SUPPORT_SERVICE);

		externalSoftwareServiceEClass = createEClass(EXTERNAL_SOFTWARE_SERVICE);
		createEReference(externalSoftwareServiceEClass, EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY);

		deployableSoftwareServiceEClass = createEClass(DEPLOYABLE_SOFTWARE_SERVICE);
		createEReference(deployableSoftwareServiceEClass, DEPLOYABLE_SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE);

		computingInfrastructureServiceEClass = createEClass(COMPUTING_INFRASTRUCTURE_SERVICE);
		createEReference(computingInfrastructureServiceEClass, COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTOR);
		createEReference(computingInfrastructureServiceEClass, COMPUTING_INFRASTRUCTURE_SERVICE__PLATFORM_SERVICES);

		softwareServiceContainerEClass = createEClass(SOFTWARE_SERVICE_CONTAINER);
		createEReference(softwareServiceContainerEClass, SOFTWARE_SERVICE_CONTAINER__SOFTWARE_SERVICES);

		operationInterfaceContainerEClass = createEClass(OPERATION_INTERFACE_CONTAINER);
		createEReference(operationInterfaceContainerEClass, OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES);
		createEReference(operationInterfaceContainerEClass, OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES);

		connectionEClass = createEClass(CONNECTION);

		internalConnectionEClass = createEClass(INTERNAL_CONNECTION);
		createEReference(internalConnectionEClass, INTERNAL_CONNECTION__SOURCE_PLATFORM_SERVICE);
		createEReference(internalConnectionEClass, INTERNAL_CONNECTION__DESTINATION_PLATFORM_SERVICE);

		hybridConnectionEClass = createEClass(HYBRID_CONNECTION);
		createEReference(hybridConnectionEClass, HYBRID_CONNECTION__SOURCE_PLATFORM_SERVICE);
		createEReference(hybridConnectionEClass, HYBRID_CONNECTION__TARGET_PLATFORM_SERVICE);
		createEReference(hybridConnectionEClass, HYBRID_CONNECTION__DESCRIPTOR);

		externalConnectionEClass = createEClass(EXTERNAL_CONNECTION);
		createEReference(externalConnectionEClass, EXTERNAL_CONNECTION__PROXY);
		createEReference(externalConnectionEClass, EXTERNAL_CONNECTION__PLATFORM_SERVICE);
		createEAttribute(externalConnectionEClass, EXTERNAL_CONNECTION__IS_OUTBOUND);
		createEReference(externalConnectionEClass, EXTERNAL_CONNECTION__DESCRIPTOR);

		proxyEClass = createEClass(PROXY);

		serviceProxyEClass = createEClass(SERVICE_PROXY);
		createEReference(serviceProxyEClass, SERVICE_PROXY__SOFTWARE_SERVICE);

		usageProxyEClass = createEClass(USAGE_PROXY);
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
		DefinitionPackage theDefinitionPackage = (DefinitionPackage)EPackage.Registry.INSTANCE.getEPackage(DefinitionPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		architectureEClass.getESuperTypes().add(theCorePackage.getEntity());
		cloudEnvironmentEClass.getESuperTypes().add(theCorePackage.getEntity());
		serviceEClass.getESuperTypes().add(theCorePackage.getEntity());
		infrastructureServiceEClass.getESuperTypes().add(this.getService());
		platformServiceEClass.getESuperTypes().add(this.getService());
		softwareServiceEClass.getESuperTypes().add(this.getService());
		softwareServiceEClass.getESuperTypes().add(this.getOperationInterfaceContainer());
		deployablePlatformServiceEClass.getESuperTypes().add(this.getPlatformService());
		externalPlatformServiceEClass.getESuperTypes().add(this.getPlatformService());
		deployableRuntimeServiceEClass.getESuperTypes().add(this.getDeployablePlatformService());
		deployableRuntimeServiceEClass.getESuperTypes().add(this.getSoftwareServiceContainer());
		deployableSupportServiceEClass.getESuperTypes().add(this.getDeployablePlatformService());
		deployableSupportServiceEClass.getESuperTypes().add(this.getOperationInterfaceContainer());
		externalRuntimeServiceEClass.getESuperTypes().add(this.getExternalPlatformService());
		externalRuntimeServiceEClass.getESuperTypes().add(this.getSoftwareServiceContainer());
		externalSupportServiceEClass.getESuperTypes().add(this.getExternalPlatformService());
		externalSupportServiceEClass.getESuperTypes().add(this.getOperationInterfaceContainer());
		externalSoftwareServiceEClass.getESuperTypes().add(this.getSoftwareService());
		deployableSoftwareServiceEClass.getESuperTypes().add(this.getSoftwareService());
		computingInfrastructureServiceEClass.getESuperTypes().add(this.getInfrastructureService());
		softwareServiceContainerEClass.getESuperTypes().add(theCorePackage.getEntity());
		operationInterfaceContainerEClass.getESuperTypes().add(theCorePackage.getEntity());
		connectionEClass.getESuperTypes().add(theCorePackage.getEntity());
		internalConnectionEClass.getESuperTypes().add(this.getConnection());
		hybridConnectionEClass.getESuperTypes().add(this.getConnection());
		externalConnectionEClass.getESuperTypes().add(this.getConnection());
		proxyEClass.getESuperTypes().add(theCorePackage.getEntity());
		serviceProxyEClass.getESuperTypes().add(this.getProxy());
		usageProxyEClass.getESuperTypes().add(this.getProxy());
		usageProxyEClass.getESuperTypes().add(this.getOperationInterfaceContainer());

		// Initialize classes and features; add operations and parameters
		initEClass(architectureEClass, Architecture.class, "Architecture", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArchitecture_CloudProviders(), this.getCloudEnvironment(), this.getCloudEnvironment_Architecture(), "cloudProviders", null, 0, -1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchitecture_Proxies(), this.getProxy(), null, "proxies", null, 0, -1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchitecture_ExternalConnections(), this.getExternalConnection(), null, "externalConnections", null, 0, -1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchitecture_HybridConnections(), this.getHybridConnection(), null, "hybridConnections", null, 0, -1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchitecture_UnresolvedOperationInterfaces(), theCorePackage.getOperationInterface(), null, "unresolvedOperationInterfaces", null, 0, -1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cloudEnvironmentEClass, CloudEnvironment.class, "CloudEnvironment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCloudEnvironment_InternalConnections(), this.getInternalConnection(), null, "internalConnections", null, 0, -1, CloudEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudEnvironment_CloudProviderDescriptor(), theDefinitionPackage.getCloudEnvironmentDescriptor(), null, "cloudProviderDescriptor", null, 1, 1, CloudEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudEnvironment_AvailabilityZoneDescriptor(), theDefinitionPackage.getAvailabilityZoneDescriptor(), null, "availabilityZoneDescriptor", null, 0, 1, CloudEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudEnvironment_Architecture(), this.getArchitecture(), this.getArchitecture_CloudProviders(), "architecture", null, 0, 1, CloudEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudEnvironment_InfrastructureLayer(), this.getInfrastructureLayer(), this.getInfrastructureLayer_CloudProvider(), "infrastructureLayer", null, 1, 1, CloudEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudEnvironment_PlatformLayer(), this.getPlatformLayer(), this.getPlatformLayer_CloudEnvironment(), "platformLayer", null, 1, 1, CloudEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudEnvironment_SoftwareLayer(), this.getSoftwareLayer(), this.getSoftwareLayer_CloudProvider(), "softwareLayer", null, 1, 1, CloudEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(infrastructureLayerEClass, InfrastructureLayer.class, "InfrastructureLayer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInfrastructureLayer_Services(), this.getInfrastructureService(), this.getInfrastructureService_InfrastructureLayer(), "services", null, 0, -1, InfrastructureLayer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInfrastructureLayer_CloudProvider(), this.getCloudEnvironment(), this.getCloudEnvironment_InfrastructureLayer(), "cloudProvider", null, 1, 1, InfrastructureLayer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(platformLayerEClass, PlatformLayer.class, "PlatformLayer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPlatformLayer_Services(), this.getPlatformService(), this.getPlatformService_PlatformLayer(), "services", null, 0, -1, PlatformLayer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlatformLayer_CloudEnvironment(), this.getCloudEnvironment(), this.getCloudEnvironment_PlatformLayer(), "cloudEnvironment", null, 1, 1, PlatformLayer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(softwareLayerEClass, SoftwareLayer.class, "SoftwareLayer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSoftwareLayer_Services(), this.getSoftwareService(), this.getSoftwareService_SoftwareLayer(), "services", null, 0, -1, SoftwareLayer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSoftwareLayer_CloudProvider(), this.getCloudEnvironment(), this.getCloudEnvironment_SoftwareLayer(), "cloudProvider", null, 1, 1, SoftwareLayer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceEClass, Service.class, "Service", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(infrastructureServiceEClass, InfrastructureService.class, "InfrastructureService", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInfrastructureService_InfrastructureLayer(), this.getInfrastructureLayer(), this.getInfrastructureLayer_Services(), "infrastructureLayer", null, 0, 1, InfrastructureService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(platformServiceEClass, PlatformService.class, "PlatformService", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPlatformService_PlatformLayer(), this.getPlatformLayer(), this.getPlatformLayer_Services(), "platformLayer", null, 0, 1, PlatformService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(softwareServiceEClass, SoftwareService.class, "SoftwareService", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSoftwareService_SoftwareLayer(), this.getSoftwareLayer(), this.getSoftwareLayer_Services(), "softwareLayer", null, 0, 1, SoftwareService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(deployablePlatformServiceEClass, DeployablePlatformService.class, "DeployablePlatformService", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDeployablePlatformService_ComputingInfrastructureService(), this.getComputingInfrastructureService(), this.getComputingInfrastructureService_PlatformServices(), "computingInfrastructureService", null, 0, 1, DeployablePlatformService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeployablePlatformService_Descriptor(), theDefinitionPackage.getDeployablePlatformServiceDescriptor(), null, "descriptor", null, 1, 1, DeployablePlatformService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(externalPlatformServiceEClass, ExternalPlatformService.class, "ExternalPlatformService", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExternalPlatformService_Descriptor(), theDefinitionPackage.getExternalPlatformServiceDescriptor(), null, "descriptor", null, 1, 1, ExternalPlatformService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(deployableRuntimeServiceEClass, DeployableRuntimeService.class, "DeployableRuntimeService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(deployableSupportServiceEClass, DeployableSupportService.class, "DeployableSupportService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(externalRuntimeServiceEClass, ExternalRuntimeService.class, "ExternalRuntimeService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(externalSupportServiceEClass, ExternalSupportService.class, "ExternalSupportService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(externalSoftwareServiceEClass, ExternalSoftwareService.class, "ExternalSoftwareService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExternalSoftwareService_ServiceProxy(), this.getServiceProxy(), this.getServiceProxy_SoftwareService(), "serviceProxy", null, 1, 1, ExternalSoftwareService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(deployableSoftwareServiceEClass, DeployableSoftwareService.class, "DeployableSoftwareService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDeployableSoftwareService_RuntimePlatformService(), this.getSoftwareServiceContainer(), this.getSoftwareServiceContainer_SoftwareServices(), "runtimePlatformService", null, 0, 1, DeployableSoftwareService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(computingInfrastructureServiceEClass, ComputingInfrastructureService.class, "ComputingInfrastructureService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComputingInfrastructureService_Descriptor(), theDefinitionPackage.getComputingInfrastructureServiceDescriptor(), null, "descriptor", null, 0, 1, ComputingInfrastructureService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComputingInfrastructureService_PlatformServices(), this.getDeployablePlatformService(), this.getDeployablePlatformService_ComputingInfrastructureService(), "platformServices", null, 0, -1, ComputingInfrastructureService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(softwareServiceContainerEClass, SoftwareServiceContainer.class, "SoftwareServiceContainer", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSoftwareServiceContainer_SoftwareServices(), this.getDeployableSoftwareService(), this.getDeployableSoftwareService_RuntimePlatformService(), "softwareServices", null, 0, -1, SoftwareServiceContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationInterfaceContainerEClass, OperationInterfaceContainer.class, "OperationInterfaceContainer", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationInterfaceContainer_ProvidedInterfaces(), theCorePackage.getOperationInterface(), theCorePackage.getOperationInterface_ProvidingContainer(), "providedInterfaces", null, 0, -1, OperationInterfaceContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationInterfaceContainer_RequiredInterfaces(), theCorePackage.getOperationInterface(), theCorePackage.getOperationInterface_RequiringContainer(), "requiredInterfaces", null, 0, -1, OperationInterfaceContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(connectionEClass, Connection.class, "Connection", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(internalConnectionEClass, InternalConnection.class, "InternalConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInternalConnection_SourcePlatformService(), this.getPlatformService(), null, "sourcePlatformService", null, 1, 1, InternalConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInternalConnection_DestinationPlatformService(), this.getPlatformService(), null, "destinationPlatformService", null, 1, 1, InternalConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(hybridConnectionEClass, HybridConnection.class, "HybridConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHybridConnection_SourcePlatformService(), this.getPlatformService(), null, "sourcePlatformService", null, 1, 1, HybridConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHybridConnection_TargetPlatformService(), this.getPlatformService(), null, "targetPlatformService", null, 1, 1, HybridConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHybridConnection_Descriptor(), theDefinitionPackage.getNetworkInfrastructureServiceDescriptor(), null, "descriptor", null, 1, 1, HybridConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(externalConnectionEClass, ExternalConnection.class, "ExternalConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExternalConnection_Proxy(), this.getProxy(), null, "proxy", null, 0, 1, ExternalConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExternalConnection_PlatformService(), this.getPlatformService(), null, "platformService", null, 0, 1, ExternalConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExternalConnection_IsOutbound(), ecorePackage.getEBooleanObject(), "isOutbound", null, 1, 1, ExternalConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExternalConnection_Descriptor(), theDefinitionPackage.getNetworkInfrastructureServiceDescriptor(), null, "descriptor", null, 1, 1, ExternalConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(proxyEClass, Proxy.class, "Proxy", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(serviceProxyEClass, ServiceProxy.class, "ServiceProxy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getServiceProxy_SoftwareService(), this.getExternalSoftwareService(), this.getExternalSoftwareService_ServiceProxy(), "softwareService", null, 0, 1, ServiceProxy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(usageProxyEClass, UsageProxy.class, "UsageProxy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
	}

} // ArchitecturePackageImpl
