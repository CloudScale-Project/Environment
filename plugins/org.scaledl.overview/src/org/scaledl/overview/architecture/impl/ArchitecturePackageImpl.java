/**
 */
package org.scaledl.overview.architecture.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.scaledl.overview.OverviewPackage;
import org.scaledl.overview.application.ApplicationPackage;
import org.scaledl.overview.application.impl.ApplicationPackageImpl;
import org.scaledl.overview.architecture.Architecture;
import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.ArchitecturePackage;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.ComputingInfrastructureService;
import org.scaledl.overview.architecture.Connection;
import org.scaledl.overview.architecture.ExternalConnection;
import org.scaledl.overview.architecture.ExternalSoftwareService;
import org.scaledl.overview.architecture.InfrastructureLayer;
import org.scaledl.overview.architecture.InfrastructureService;
import org.scaledl.overview.architecture.InternalConnection;
import org.scaledl.overview.architecture.PlatformLayer;
import org.scaledl.overview.architecture.PlatformRuntimeService;
import org.scaledl.overview.architecture.PlatformService;
import org.scaledl.overview.architecture.PlatformSupportService;
import org.scaledl.overview.architecture.ProvidedPlatformRuntimeService;
import org.scaledl.overview.architecture.ProvidedPlatformSupportService;
import org.scaledl.overview.architecture.ProvidedService;
import org.scaledl.overview.architecture.ProvidedSoftwareService;
import org.scaledl.overview.architecture.Proxy;
import org.scaledl.overview.architecture.Service;
import org.scaledl.overview.architecture.ServiceProxy;
import org.scaledl.overview.architecture.SoftwareLayer;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.architecture.SoftwareServiceContainer;
import org.scaledl.overview.architecture.UsageProxy;
import org.scaledl.overview.core.CorePackage;
import org.scaledl.overview.core.impl.CorePackageImpl;
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
public class ArchitecturePackageImpl extends EPackageImpl implements ArchitecturePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass architectureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cloudEnvironmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass infrastructureLayerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass platformLayerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass softwareLayerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass providedServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass infrastructureServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass computingInfrastructureServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass platformServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass platformRuntimeServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass platformSupportServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass providedPlatformRuntimeServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass providedPlatformSupportServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass softwareServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass providedSoftwareServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalSoftwareServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass softwareServiceContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass internalConnectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalConnectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass proxyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceProxyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass usageProxyEClass = null;

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
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ArchitecturePackageImpl() {
		super(eNS_URI, ArchitectureFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ArchitecturePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
		OverviewPackageImpl theOverviewPackage = (OverviewPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OverviewPackage.eNS_URI) instanceof OverviewPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OverviewPackage.eNS_URI) : OverviewPackage.eINSTANCE);
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);
		ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) instanceof ApplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) : ApplicationPackage.eINSTANCE);
		DeploymentPackageImpl theDeploymentPackage = (DeploymentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI) instanceof DeploymentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI) : DeploymentPackage.eINSTANCE);
		SpecificationPackageImpl theSpecificationPackage = (SpecificationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI) instanceof SpecificationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI) : SpecificationPackage.eINSTANCE);
		SlaPackageImpl theSlaPackage = (SlaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SlaPackage.eNS_URI) instanceof SlaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SlaPackage.eNS_URI) : SlaPackage.eINSTANCE);
		ParametertypePackageImpl theParametertypePackage = (ParametertypePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ParametertypePackage.eNS_URI) instanceof ParametertypePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ParametertypePackage.eNS_URI) : ParametertypePackage.eINSTANCE);

		// Create package meta-data objects
		theArchitecturePackage.createPackageContents();
		theOverviewPackage.createPackageContents();
		theCorePackage.createPackageContents();
		theApplicationPackage.createPackageContents();
		theDeploymentPackage.createPackageContents();
		theSpecificationPackage.createPackageContents();
		theSlaPackage.createPackageContents();
		theParametertypePackage.createPackageContents();

		// Initialize created meta-data
		theArchitecturePackage.initializePackageContents();
		theOverviewPackage.initializePackageContents();
		theCorePackage.initializePackageContents();
		theApplicationPackage.initializePackageContents();
		theDeploymentPackage.initializePackageContents();
		theSpecificationPackage.initializePackageContents();
		theSlaPackage.initializePackageContents();
		theParametertypePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theArchitecturePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ArchitecturePackage.eNS_URI, theArchitecturePackage);
		return theArchitecturePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArchitecture() {
		return architectureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecture_CloudEnvironments() {
		return (EReference)architectureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecture_Proxies() {
		return (EReference)architectureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecture_UsageConnections() {
		return (EReference)architectureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecture_ExternalConnections() {
		return (EReference)architectureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecture_UnresolvedOperationInterfaces() {
		return (EReference)architectureEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCloudEnvironment() {
		return cloudEnvironmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudEnvironment_Architecture() {
		return (EReference)cloudEnvironmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudEnvironment_InternalConnections() {
		return (EReference)cloudEnvironmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudEnvironment_CloudEnvironmentDescriptor() {
		return (EReference)cloudEnvironmentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudEnvironment_AvailabilityZoneDescriptor() {
		return (EReference)cloudEnvironmentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudEnvironment_InfrastructureLayer() {
		return (EReference)cloudEnvironmentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudEnvironment_PlatformLayer() {
		return (EReference)cloudEnvironmentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCloudEnvironment_SoftwareLayer() {
		return (EReference)cloudEnvironmentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInfrastructureLayer() {
		return infrastructureLayerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInfrastructureLayer_Services() {
		return (EReference)infrastructureLayerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInfrastructureLayer_CloudEnvironment() {
		return (EReference)infrastructureLayerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPlatformLayer() {
		return platformLayerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPlatformLayer_Services() {
		return (EReference)platformLayerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPlatformLayer_CloudEnvironment() {
		return (EReference)platformLayerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSoftwareLayer() {
		return softwareLayerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSoftwareLayer_Services() {
		return (EReference)softwareLayerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSoftwareLayer_CloudEnvironment() {
		return (EReference)softwareLayerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getService() {
		return serviceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvidedService() {
		return providedServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProvidedService_Deployment() {
		return (EReference)providedServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInfrastructureService() {
		return infrastructureServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInfrastructureService_InfrastructureLayer() {
		return (EReference)infrastructureServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComputingInfrastructureService() {
		return computingInfrastructureServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComputingInfrastructureService_Descriptor() {
		return (EReference)computingInfrastructureServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComputingInfrastructureService_PlatformServices() {
		return (EReference)computingInfrastructureServiceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPlatformService() {
		return platformServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPlatformService_Descriptor() {
		return (EReference)platformServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPlatformService_ComputingInfrastructureService() {
		return (EReference)platformServiceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPlatformService_PlatformLayer() {
		return (EReference)platformServiceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPlatformRuntimeService() {
		return platformRuntimeServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPlatformSupportService() {
		return platformSupportServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvidedPlatformRuntimeService() {
		return providedPlatformRuntimeServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvidedPlatformSupportService() {
		return providedPlatformSupportServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSoftwareService() {
		return softwareServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSoftwareService_RuntimePlatformService() {
		return (EReference)softwareServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSoftwareService_SoftwareLayer() {
		return (EReference)softwareServiceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvidedSoftwareService() {
		return providedSoftwareServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProvidedSoftwareService_Descriptor() {
		return (EReference)providedSoftwareServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSoftwareServiceContainer() {
		return softwareServiceContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSoftwareServiceContainer_SoftwareServices() {
		return (EReference)softwareServiceContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnection() {
		return connectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInternalConnection() {
		return internalConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInternalConnection_Descriptor() {
		return (EReference)internalConnectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInternalConnection_Source() {
		return (EReference)internalConnectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInternalConnection_Target() {
		return (EReference)internalConnectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExternalConnection() {
		return externalConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExternalConnection_Source() {
		return (EReference)externalConnectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExternalConnection_Target() {
		return (EReference)externalConnectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExternalConnection_Bandwidth() {
		return (EAttribute)externalConnectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExternalConnection_Latency() {
		return (EAttribute)externalConnectionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProxy() {
		return proxyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServiceProxy() {
		return serviceProxyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceProxy_SoftwareService() {
		return (EReference)serviceProxyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUsageProxy() {
		return usageProxyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchitectureFactory getArchitectureFactory() {
		return (ArchitectureFactory)getEFactoryInstance();
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
		architectureEClass = createEClass(ARCHITECTURE);
		createEReference(architectureEClass, ARCHITECTURE__CLOUD_ENVIRONMENTS);
		createEReference(architectureEClass, ARCHITECTURE__PROXIES);
		createEReference(architectureEClass, ARCHITECTURE__USAGE_CONNECTIONS);
		createEReference(architectureEClass, ARCHITECTURE__EXTERNAL_CONNECTIONS);
		createEReference(architectureEClass, ARCHITECTURE__UNRESOLVED_OPERATION_INTERFACES);

		cloudEnvironmentEClass = createEClass(CLOUD_ENVIRONMENT);
		createEReference(cloudEnvironmentEClass, CLOUD_ENVIRONMENT__ARCHITECTURE);
		createEReference(cloudEnvironmentEClass, CLOUD_ENVIRONMENT__INTERNAL_CONNECTIONS);
		createEReference(cloudEnvironmentEClass, CLOUD_ENVIRONMENT__CLOUD_ENVIRONMENT_DESCRIPTOR);
		createEReference(cloudEnvironmentEClass, CLOUD_ENVIRONMENT__AVAILABILITY_ZONE_DESCRIPTOR);
		createEReference(cloudEnvironmentEClass, CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER);
		createEReference(cloudEnvironmentEClass, CLOUD_ENVIRONMENT__PLATFORM_LAYER);
		createEReference(cloudEnvironmentEClass, CLOUD_ENVIRONMENT__SOFTWARE_LAYER);

		infrastructureLayerEClass = createEClass(INFRASTRUCTURE_LAYER);
		createEReference(infrastructureLayerEClass, INFRASTRUCTURE_LAYER__SERVICES);
		createEReference(infrastructureLayerEClass, INFRASTRUCTURE_LAYER__CLOUD_ENVIRONMENT);

		platformLayerEClass = createEClass(PLATFORM_LAYER);
		createEReference(platformLayerEClass, PLATFORM_LAYER__SERVICES);
		createEReference(platformLayerEClass, PLATFORM_LAYER__CLOUD_ENVIRONMENT);

		softwareLayerEClass = createEClass(SOFTWARE_LAYER);
		createEReference(softwareLayerEClass, SOFTWARE_LAYER__SERVICES);
		createEReference(softwareLayerEClass, SOFTWARE_LAYER__CLOUD_ENVIRONMENT);

		serviceEClass = createEClass(SERVICE);

		providedServiceEClass = createEClass(PROVIDED_SERVICE);
		createEReference(providedServiceEClass, PROVIDED_SERVICE__DEPLOYMENT);

		infrastructureServiceEClass = createEClass(INFRASTRUCTURE_SERVICE);
		createEReference(infrastructureServiceEClass, INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER);

		computingInfrastructureServiceEClass = createEClass(COMPUTING_INFRASTRUCTURE_SERVICE);
		createEReference(computingInfrastructureServiceEClass, COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTOR);
		createEReference(computingInfrastructureServiceEClass, COMPUTING_INFRASTRUCTURE_SERVICE__PLATFORM_SERVICES);

		platformServiceEClass = createEClass(PLATFORM_SERVICE);
		createEReference(platformServiceEClass, PLATFORM_SERVICE__DESCRIPTOR);
		createEReference(platformServiceEClass, PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE);
		createEReference(platformServiceEClass, PLATFORM_SERVICE__PLATFORM_LAYER);

		platformRuntimeServiceEClass = createEClass(PLATFORM_RUNTIME_SERVICE);

		platformSupportServiceEClass = createEClass(PLATFORM_SUPPORT_SERVICE);

		providedPlatformRuntimeServiceEClass = createEClass(PROVIDED_PLATFORM_RUNTIME_SERVICE);

		providedPlatformSupportServiceEClass = createEClass(PROVIDED_PLATFORM_SUPPORT_SERVICE);

		softwareServiceEClass = createEClass(SOFTWARE_SERVICE);
		createEReference(softwareServiceEClass, SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE);
		createEReference(softwareServiceEClass, SOFTWARE_SERVICE__SOFTWARE_LAYER);

		providedSoftwareServiceEClass = createEClass(PROVIDED_SOFTWARE_SERVICE);
		createEReference(providedSoftwareServiceEClass, PROVIDED_SOFTWARE_SERVICE__DESCRIPTOR);

		externalSoftwareServiceEClass = createEClass(EXTERNAL_SOFTWARE_SERVICE);
		createEReference(externalSoftwareServiceEClass, EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY);

		softwareServiceContainerEClass = createEClass(SOFTWARE_SERVICE_CONTAINER);
		createEReference(softwareServiceContainerEClass, SOFTWARE_SERVICE_CONTAINER__SOFTWARE_SERVICES);

		connectionEClass = createEClass(CONNECTION);

		internalConnectionEClass = createEClass(INTERNAL_CONNECTION);
		createEReference(internalConnectionEClass, INTERNAL_CONNECTION__DESCRIPTOR);
		createEReference(internalConnectionEClass, INTERNAL_CONNECTION__SOURCE);
		createEReference(internalConnectionEClass, INTERNAL_CONNECTION__TARGET);

		externalConnectionEClass = createEClass(EXTERNAL_CONNECTION);
		createEReference(externalConnectionEClass, EXTERNAL_CONNECTION__SOURCE);
		createEReference(externalConnectionEClass, EXTERNAL_CONNECTION__TARGET);
		createEAttribute(externalConnectionEClass, EXTERNAL_CONNECTION__BANDWIDTH);
		createEAttribute(externalConnectionEClass, EXTERNAL_CONNECTION__LATENCY);

		proxyEClass = createEClass(PROXY);

		serviceProxyEClass = createEClass(SERVICE_PROXY);
		createEReference(serviceProxyEClass, SERVICE_PROXY__SOFTWARE_SERVICE);

		usageProxyEClass = createEClass(USAGE_PROXY);
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
		SpecificationPackage theSpecificationPackage = (SpecificationPackage)EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI);
		DeploymentPackage theDeploymentPackage = (DeploymentPackage)EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		architectureEClass.getESuperTypes().add(theCorePackage.getEntity());
		cloudEnvironmentEClass.getESuperTypes().add(theCorePackage.getEntity());
		serviceEClass.getESuperTypes().add(theCorePackage.getEntity());
		providedServiceEClass.getESuperTypes().add(this.getService());
		infrastructureServiceEClass.getESuperTypes().add(this.getProvidedService());
		computingInfrastructureServiceEClass.getESuperTypes().add(this.getInfrastructureService());
		platformServiceEClass.getESuperTypes().add(this.getService());
		platformRuntimeServiceEClass.getESuperTypes().add(this.getSoftwareServiceContainer());
		platformRuntimeServiceEClass.getESuperTypes().add(this.getPlatformService());
		platformSupportServiceEClass.getESuperTypes().add(theApplicationPackage.getOperationInterfaceContainer());
		platformSupportServiceEClass.getESuperTypes().add(this.getPlatformService());
		providedPlatformRuntimeServiceEClass.getESuperTypes().add(this.getPlatformRuntimeService());
		providedPlatformRuntimeServiceEClass.getESuperTypes().add(this.getProvidedService());
		providedPlatformSupportServiceEClass.getESuperTypes().add(this.getPlatformSupportService());
		providedPlatformSupportServiceEClass.getESuperTypes().add(this.getProvidedService());
		softwareServiceEClass.getESuperTypes().add(this.getService());
		softwareServiceEClass.getESuperTypes().add(theApplicationPackage.getOperationInterfaceContainer());
		providedSoftwareServiceEClass.getESuperTypes().add(this.getSoftwareService());
		providedSoftwareServiceEClass.getESuperTypes().add(this.getProvidedService());
		externalSoftwareServiceEClass.getESuperTypes().add(this.getProvidedSoftwareService());
		softwareServiceContainerEClass.getESuperTypes().add(theCorePackage.getEntity());
		connectionEClass.getESuperTypes().add(theCorePackage.getEntity());
		internalConnectionEClass.getESuperTypes().add(this.getConnection());
		externalConnectionEClass.getESuperTypes().add(this.getConnection());
		proxyEClass.getESuperTypes().add(theCorePackage.getEntity());
		serviceProxyEClass.getESuperTypes().add(this.getProxy());
		usageProxyEClass.getESuperTypes().add(this.getProxy());
		usageProxyEClass.getESuperTypes().add(theApplicationPackage.getOperationInterfaceContainer());

		// Initialize classes and features; add operations and parameters
		initEClass(architectureEClass, Architecture.class, "Architecture", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArchitecture_CloudEnvironments(), this.getCloudEnvironment(), this.getCloudEnvironment_Architecture(), "cloudEnvironments", null, 0, -1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchitecture_Proxies(), this.getProxy(), null, "proxies", null, 0, -1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchitecture_UsageConnections(), this.getExternalConnection(), null, "usageConnections", null, 0, -1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchitecture_ExternalConnections(), this.getExternalConnection(), null, "externalConnections", null, 0, -1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchitecture_UnresolvedOperationInterfaces(), theApplicationPackage.getOperationInterface(), null, "unresolvedOperationInterfaces", null, 0, -1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cloudEnvironmentEClass, CloudEnvironment.class, "CloudEnvironment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCloudEnvironment_Architecture(), this.getArchitecture(), this.getArchitecture_CloudEnvironments(), "architecture", null, 0, 1, CloudEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudEnvironment_InternalConnections(), this.getInternalConnection(), null, "internalConnections", null, 0, -1, CloudEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudEnvironment_CloudEnvironmentDescriptor(), theSpecificationPackage.getCloudEnvironmentDescriptor(), null, "cloudEnvironmentDescriptor", null, 1, 1, CloudEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudEnvironment_AvailabilityZoneDescriptor(), theSpecificationPackage.getAvailabilityZoneDescriptor(), null, "availabilityZoneDescriptor", null, 0, 1, CloudEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudEnvironment_InfrastructureLayer(), this.getInfrastructureLayer(), this.getInfrastructureLayer_CloudEnvironment(), "infrastructureLayer", null, 1, 1, CloudEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudEnvironment_PlatformLayer(), this.getPlatformLayer(), this.getPlatformLayer_CloudEnvironment(), "platformLayer", null, 1, 1, CloudEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCloudEnvironment_SoftwareLayer(), this.getSoftwareLayer(), this.getSoftwareLayer_CloudEnvironment(), "softwareLayer", null, 1, 1, CloudEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(infrastructureLayerEClass, InfrastructureLayer.class, "InfrastructureLayer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInfrastructureLayer_Services(), this.getInfrastructureService(), this.getInfrastructureService_InfrastructureLayer(), "services", null, 0, -1, InfrastructureLayer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInfrastructureLayer_CloudEnvironment(), this.getCloudEnvironment(), this.getCloudEnvironment_InfrastructureLayer(), "cloudEnvironment", null, 1, 1, InfrastructureLayer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(platformLayerEClass, PlatformLayer.class, "PlatformLayer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPlatformLayer_Services(), this.getPlatformService(), this.getPlatformService_PlatformLayer(), "services", null, 0, -1, PlatformLayer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlatformLayer_CloudEnvironment(), this.getCloudEnvironment(), this.getCloudEnvironment_PlatformLayer(), "cloudEnvironment", null, 1, 1, PlatformLayer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(softwareLayerEClass, SoftwareLayer.class, "SoftwareLayer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSoftwareLayer_Services(), this.getSoftwareService(), this.getSoftwareService_SoftwareLayer(), "services", null, 0, -1, SoftwareLayer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSoftwareLayer_CloudEnvironment(), this.getCloudEnvironment(), this.getCloudEnvironment_SoftwareLayer(), "cloudEnvironment", null, 1, 1, SoftwareLayer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceEClass, Service.class, "Service", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(providedServiceEClass, ProvidedService.class, "ProvidedService", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProvidedService_Deployment(), theDeploymentPackage.getServiceDeployment(), null, "deployment", null, 0, 1, ProvidedService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(infrastructureServiceEClass, InfrastructureService.class, "InfrastructureService", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInfrastructureService_InfrastructureLayer(), this.getInfrastructureLayer(), this.getInfrastructureLayer_Services(), "infrastructureLayer", null, 0, 1, InfrastructureService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(computingInfrastructureServiceEClass, ComputingInfrastructureService.class, "ComputingInfrastructureService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComputingInfrastructureService_Descriptor(), theSpecificationPackage.getComputingInfrastructureServiceDescriptor(), null, "descriptor", null, 1, 1, ComputingInfrastructureService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComputingInfrastructureService_PlatformServices(), this.getPlatformService(), this.getPlatformService_ComputingInfrastructureService(), "platformServices", null, 0, -1, ComputingInfrastructureService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(platformServiceEClass, PlatformService.class, "PlatformService", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPlatformService_Descriptor(), theSpecificationPackage.getPlatformServiceDescriptor(), null, "descriptor", null, 1, 1, PlatformService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlatformService_ComputingInfrastructureService(), this.getComputingInfrastructureService(), this.getComputingInfrastructureService_PlatformServices(), "computingInfrastructureService", null, 0, 1, PlatformService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlatformService_PlatformLayer(), this.getPlatformLayer(), this.getPlatformLayer_Services(), "platformLayer", null, 0, 1, PlatformService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(platformRuntimeServiceEClass, PlatformRuntimeService.class, "PlatformRuntimeService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(platformSupportServiceEClass, PlatformSupportService.class, "PlatformSupportService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(providedPlatformRuntimeServiceEClass, ProvidedPlatformRuntimeService.class, "ProvidedPlatformRuntimeService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(providedPlatformSupportServiceEClass, ProvidedPlatformSupportService.class, "ProvidedPlatformSupportService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(softwareServiceEClass, SoftwareService.class, "SoftwareService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSoftwareService_RuntimePlatformService(), this.getSoftwareServiceContainer(), this.getSoftwareServiceContainer_SoftwareServices(), "runtimePlatformService", null, 0, 1, SoftwareService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSoftwareService_SoftwareLayer(), this.getSoftwareLayer(), this.getSoftwareLayer_Services(), "softwareLayer", null, 0, 1, SoftwareService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(providedSoftwareServiceEClass, ProvidedSoftwareService.class, "ProvidedSoftwareService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProvidedSoftwareService_Descriptor(), theSpecificationPackage.getProvidedSoftwareServiceDescriptor(), null, "descriptor", null, 1, 1, ProvidedSoftwareService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(externalSoftwareServiceEClass, ExternalSoftwareService.class, "ExternalSoftwareService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExternalSoftwareService_ServiceProxy(), this.getServiceProxy(), null, "serviceProxy", null, 1, 1, ExternalSoftwareService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(softwareServiceContainerEClass, SoftwareServiceContainer.class, "SoftwareServiceContainer", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSoftwareServiceContainer_SoftwareServices(), this.getSoftwareService(), this.getSoftwareService_RuntimePlatformService(), "softwareServices", null, 0, -1, SoftwareServiceContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(connectionEClass, Connection.class, "Connection", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(internalConnectionEClass, InternalConnection.class, "InternalConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInternalConnection_Descriptor(), theSpecificationPackage.getNetworkInfrastructureServiceDescriptor(), null, "descriptor", null, 1, 1, InternalConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInternalConnection_Source(), theApplicationPackage.getOperationInterfaceContainer(), null, "source", null, 1, 1, InternalConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInternalConnection_Target(), theApplicationPackage.getOperationInterfaceContainer(), null, "target", null, 1, 1, InternalConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(externalConnectionEClass, ExternalConnection.class, "ExternalConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExternalConnection_Source(), theApplicationPackage.getOperationInterfaceContainer(), null, "source", null, 1, 1, ExternalConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExternalConnection_Target(), theApplicationPackage.getOperationInterfaceContainer(), null, "target", null, 1, 1, ExternalConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExternalConnection_Bandwidth(), ecorePackage.getEInt(), "bandwidth", null, 1, 1, ExternalConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExternalConnection_Latency(), ecorePackage.getEInt(), "latency", null, 1, 1, ExternalConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(proxyEClass, Proxy.class, "Proxy", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(serviceProxyEClass, ServiceProxy.class, "ServiceProxy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getServiceProxy_SoftwareService(), this.getSoftwareService(), null, "softwareService", null, 0, 1, ServiceProxy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(usageProxyEClass, UsageProxy.class, "UsageProxy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
	}

} //ArchitecturePackageImpl
