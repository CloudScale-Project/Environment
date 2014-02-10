/**
 */
package eu.cloudscaleproject.env.csm.architecture.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import eu.cloudscaleproject.env.csm.architecture.Architecture;
import eu.cloudscaleproject.env.csm.architecture.ArchitectureFactory;
import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.DeployableRuntimeService;
import eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService;
import eu.cloudscaleproject.env.csm.architecture.DeployableSupportService;
import eu.cloudscaleproject.env.csm.architecture.ExternalConnection;
import eu.cloudscaleproject.env.csm.architecture.ExternalRuntimeService;
import eu.cloudscaleproject.env.csm.architecture.ExternalSoftwareService;
import eu.cloudscaleproject.env.csm.architecture.ExternalSupportService;
import eu.cloudscaleproject.env.csm.architecture.HybridConnection;
import eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer;
import eu.cloudscaleproject.env.csm.architecture.InternalConnection;
import eu.cloudscaleproject.env.csm.architecture.PlatformLayer;
import eu.cloudscaleproject.env.csm.architecture.ServiceProxy;
import eu.cloudscaleproject.env.csm.architecture.SoftwareLayer;
import eu.cloudscaleproject.env.csm.architecture.UsageProxy;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class ArchitectureFactoryImpl extends EFactoryImpl implements
		ArchitectureFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static ArchitectureFactory init() {
		try {
			ArchitectureFactory theArchitectureFactory = (ArchitectureFactory)EPackage.Registry.INSTANCE.getEFactory("http://eu.cloudscaleproject.env/CloudscaleComponentModel/Architecture/1.0"); 
			if (theArchitectureFactory != null) {
				return theArchitectureFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ArchitectureFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public ArchitectureFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ArchitecturePackage.ARCHITECTURE: return createArchitecture();
			case ArchitecturePackage.CLOUD_ENVIRONMENT: return createCloudEnvironment();
			case ArchitecturePackage.INFRASTRUCTURE_LAYER: return createInfrastructureLayer();
			case ArchitecturePackage.PLATFORM_LAYER: return createPlatformLayer();
			case ArchitecturePackage.SOFTWARE_LAYER: return createSoftwareLayer();
			case ArchitecturePackage.DEPLOYABLE_RUNTIME_SERVICE: return createDeployableRuntimeService();
			case ArchitecturePackage.DEPLOYABLE_SUPPORT_SERVICE: return createDeployableSupportService();
			case ArchitecturePackage.EXTERNAL_RUNTIME_SERVICE: return createExternalRuntimeService();
			case ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE: return createExternalSupportService();
			case ArchitecturePackage.EXTERNAL_SOFTWARE_SERVICE: return createExternalSoftwareService();
			case ArchitecturePackage.DEPLOYABLE_SOFTWARE_SERVICE: return createDeployableSoftwareService();
			case ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE: return createComputingInfrastructureService();
			case ArchitecturePackage.INTERNAL_CONNECTION: return createInternalConnection();
			case ArchitecturePackage.HYBRID_CONNECTION: return createHybridConnection();
			case ArchitecturePackage.EXTERNAL_CONNECTION: return createExternalConnection();
			case ArchitecturePackage.SERVICE_PROXY: return createServiceProxy();
			case ArchitecturePackage.USAGE_PROXY: return createUsageProxy();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Architecture createArchitecture() {
		ArchitectureImpl architecture = new ArchitectureImpl();
		return architecture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CloudEnvironment createCloudEnvironment() {
		CloudEnvironmentImpl cloudEnvironment = new CloudEnvironmentImpl();
		return cloudEnvironment;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public InfrastructureLayer createInfrastructureLayer() {
		InfrastructureLayerImpl infrastructureLayer = new InfrastructureLayerImpl();
		return infrastructureLayer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformLayer createPlatformLayer() {
		PlatformLayerImpl platformLayer = new PlatformLayerImpl();
		return platformLayer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareLayer createSoftwareLayer() {
		SoftwareLayerImpl softwareLayer = new SoftwareLayerImpl();
		return softwareLayer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public DeployableRuntimeService createDeployableRuntimeService() {
		DeployableRuntimeServiceImpl deployableRuntimeService = new DeployableRuntimeServiceImpl();
		return deployableRuntimeService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public DeployableSupportService createDeployableSupportService() {
		DeployableSupportServiceImpl deployableSupportService = new DeployableSupportServiceImpl();
		return deployableSupportService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalRuntimeService createExternalRuntimeService() {
		ExternalRuntimeServiceImpl externalRuntimeService = new ExternalRuntimeServiceImpl();
		return externalRuntimeService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalSupportService createExternalSupportService() {
		ExternalSupportServiceImpl externalSupportService = new ExternalSupportServiceImpl();
		return externalSupportService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalSoftwareService createExternalSoftwareService() {
		ExternalSoftwareServiceImpl externalSoftwareService = new ExternalSoftwareServiceImpl();
		return externalSoftwareService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public DeployableSoftwareService createDeployableSoftwareService() {
		DeployableSoftwareServiceImpl deployableSoftwareService = new DeployableSoftwareServiceImpl();
		return deployableSoftwareService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ComputingInfrastructureService createComputingInfrastructureService() {
		ComputingInfrastructureServiceImpl computingInfrastructureService = new ComputingInfrastructureServiceImpl();
		return computingInfrastructureService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public InternalConnection createInternalConnection() {
		InternalConnectionImpl internalConnection = new InternalConnectionImpl();
		return internalConnection;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public HybridConnection createHybridConnection() {
		HybridConnectionImpl hybridConnection = new HybridConnectionImpl();
		return hybridConnection;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalConnection createExternalConnection() {
		ExternalConnectionImpl externalConnection = new ExternalConnectionImpl();
		return externalConnection;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceProxy createServiceProxy() {
		ServiceProxyImpl serviceProxy = new ServiceProxyImpl();
		return serviceProxy;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public UsageProxy createUsageProxy() {
		UsageProxyImpl usageProxy = new UsageProxyImpl();
		return usageProxy;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ArchitecturePackage getArchitecturePackage() {
		return (ArchitecturePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ArchitecturePackage getPackage() {
		return ArchitecturePackage.eINSTANCE;
	}

} // ArchitectureFactoryImpl
