/**
 */
package org.scaledl.overview.architecture.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.scaledl.overview.architecture.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ArchitectureFactoryImpl extends EFactoryImpl implements ArchitectureFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ArchitectureFactory init() {
		try {
			ArchitectureFactory theArchitectureFactory = (ArchitectureFactory)EPackage.Registry.INSTANCE.getEFactory(ArchitecturePackage.eNS_URI);
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchitectureFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
			case ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE: return createComputingInfrastructureService();
			case ArchitecturePackage.PLATFORM_RUNTIME_SERVICE: return createPlatformRuntimeService();
			case ArchitecturePackage.PLATFORM_SUPPORT_SERVICE: return createPlatformSupportService();
			case ArchitecturePackage.PROVIDED_PLATFORM_RUNTIME_SERVICE: return createProvidedPlatformRuntimeService();
			case ArchitecturePackage.PROVIDED_PLATFORM_SUPPORT_SERVICE: return createProvidedPlatformSupportService();
			case ArchitecturePackage.SOFTWARE_SERVICE: return createSoftwareService();
			case ArchitecturePackage.PROVIDED_SOFTWARE_SERVICE: return createProvidedSoftwareService();
			case ArchitecturePackage.EXTERNAL_SOFTWARE_SERVICE: return createExternalSoftwareService();
			case ArchitecturePackage.INTERNAL_CONNECTION: return createInternalConnection();
			case ArchitecturePackage.EXTERNAL_CONNECTION: return createExternalConnection();
			case ArchitecturePackage.SERVICE_PROXY: return createServiceProxy();
			case ArchitecturePackage.USAGE_PROXY: return createUsageProxy();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Architecture createArchitecture() {
		ArchitectureImpl architecture = new ArchitectureImpl();
		return architecture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CloudEnvironment createCloudEnvironment() {
		CloudEnvironmentImpl cloudEnvironment = new CloudEnvironmentImpl();
		return cloudEnvironment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InfrastructureLayer createInfrastructureLayer() {
		InfrastructureLayerImpl infrastructureLayer = new InfrastructureLayerImpl();
		return infrastructureLayer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformLayer createPlatformLayer() {
		PlatformLayerImpl platformLayer = new PlatformLayerImpl();
		return platformLayer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareLayer createSoftwareLayer() {
		SoftwareLayerImpl softwareLayer = new SoftwareLayerImpl();
		return softwareLayer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComputingInfrastructureService createComputingInfrastructureService() {
		ComputingInfrastructureServiceImpl computingInfrastructureService = new ComputingInfrastructureServiceImpl();
		return computingInfrastructureService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformRuntimeService createPlatformRuntimeService() {
		PlatformRuntimeServiceImpl platformRuntimeService = new PlatformRuntimeServiceImpl();
		return platformRuntimeService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformSupportService createPlatformSupportService() {
		PlatformSupportServiceImpl platformSupportService = new PlatformSupportServiceImpl();
		return platformSupportService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvidedPlatformRuntimeService createProvidedPlatformRuntimeService() {
		ProvidedPlatformRuntimeServiceImpl providedPlatformRuntimeService = new ProvidedPlatformRuntimeServiceImpl();
		return providedPlatformRuntimeService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvidedPlatformSupportService createProvidedPlatformSupportService() {
		ProvidedPlatformSupportServiceImpl providedPlatformSupportService = new ProvidedPlatformSupportServiceImpl();
		return providedPlatformSupportService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareService createSoftwareService() {
		SoftwareServiceImpl softwareService = new SoftwareServiceImpl();
		return softwareService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvidedSoftwareService createProvidedSoftwareService() {
		ProvidedSoftwareServiceImpl providedSoftwareService = new ProvidedSoftwareServiceImpl();
		return providedSoftwareService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalSoftwareService createExternalSoftwareService() {
		ExternalSoftwareServiceImpl externalSoftwareService = new ExternalSoftwareServiceImpl();
		return externalSoftwareService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InternalConnection createInternalConnection() {
		InternalConnectionImpl internalConnection = new InternalConnectionImpl();
		return internalConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalConnection createExternalConnection() {
		ExternalConnectionImpl externalConnection = new ExternalConnectionImpl();
		return externalConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceProxy createServiceProxy() {
		ServiceProxyImpl serviceProxy = new ServiceProxyImpl();
		return serviceProxy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UsageProxy createUsageProxy() {
		UsageProxyImpl usageProxy = new UsageProxyImpl();
		return usageProxy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchitecturePackage getArchitecturePackage() {
		return (ArchitecturePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ArchitecturePackage getPackage() {
		return ArchitecturePackage.eINSTANCE;
	}

} //ArchitectureFactoryImpl
