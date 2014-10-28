/**
 */
package org.scaledl.overview.architecture;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.scaledl.overview.application.ApplicationPackage;
import org.scaledl.overview.core.CorePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The package contains base classes for building cloud based systems. The classes are abstract representation of the cloud system architecture, while the exact specification is referenced through descriptors defined in the Definition package.
 * <!-- end-model-doc -->
 * @see org.scaledl.overview.architecture.ArchitectureFactory
 * @model kind="package"
 * @generated
 */
public interface ArchitecturePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "architecture";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org.scaledl.overview/ScaleDLOverviewComponentModel/Architecture/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "architecture";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ArchitecturePackage eINSTANCE = org.scaledl.overview.architecture.impl.ArchitecturePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.ArchitectureImpl <em>Architecture</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.ArchitectureImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getArchitecture()
	 * @generated
	 */
	int ARCHITECTURE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Cloud Environments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__CLOUD_ENVIRONMENTS = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Proxies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__PROXIES = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Usage Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__USAGE_CONNECTIONS = CorePackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>External Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__EXTERNAL_CONNECTIONS = CorePackage.ENTITY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Unresolved Operation Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__UNRESOLVED_OPERATION_INTERFACES = CorePackage.ENTITY_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Architecture</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.CloudEnvironmentImpl <em>Cloud Environment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.CloudEnvironmentImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getCloudEnvironment()
	 * @generated
	 */
	int CLOUD_ENVIRONMENT = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Architecture</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__ARCHITECTURE = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Internal Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__INTERNAL_CONNECTIONS = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Cloud Environment Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__CLOUD_ENVIRONMENT_DESCRIPTOR = CorePackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Availability Zone Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__AVAILABILITY_ZONE_DESCRIPTOR = CorePackage.ENTITY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Infrastructure Layer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER = CorePackage.ENTITY_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Platform Layer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__PLATFORM_LAYER = CorePackage.ENTITY_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Software Layer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__SOFTWARE_LAYER = CorePackage.ENTITY_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Cloud Environment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.InfrastructureLayerImpl <em>Infrastructure Layer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.InfrastructureLayerImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getInfrastructureLayer()
	 * @generated
	 */
	int INFRASTRUCTURE_LAYER = 2;

	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_LAYER__SERVICES = 0;

	/**
	 * The feature id for the '<em><b>Cloud Environment</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_LAYER__CLOUD_ENVIRONMENT = 1;

	/**
	 * The number of structural features of the '<em>Infrastructure Layer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_LAYER_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.PlatformLayerImpl <em>Platform Layer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.PlatformLayerImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getPlatformLayer()
	 * @generated
	 */
	int PLATFORM_LAYER = 3;

	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_LAYER__SERVICES = 0;

	/**
	 * The feature id for the '<em><b>Cloud Environment</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_LAYER__CLOUD_ENVIRONMENT = 1;

	/**
	 * The number of structural features of the '<em>Platform Layer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_LAYER_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.SoftwareLayerImpl <em>Software Layer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.SoftwareLayerImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getSoftwareLayer()
	 * @generated
	 */
	int SOFTWARE_LAYER = 4;

	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_LAYER__SERVICES = 0;

	/**
	 * The feature id for the '<em><b>Cloud Environment</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_LAYER__CLOUD_ENVIRONMENT = 1;

	/**
	 * The number of structural features of the '<em>Software Layer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_LAYER_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.Service <em>Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.Service
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getService()
	 * @generated
	 */
	int SERVICE = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The number of structural features of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.ProvidedServiceImpl <em>Provided Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.ProvidedServiceImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getProvidedService()
	 * @generated
	 */
	int PROVIDED_SERVICE = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SERVICE__ID = SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SERVICE__NAME = SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SERVICE__DESCRIPTION = SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SERVICE__AV_MAP = SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SERVICE__AE_MAP = SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Deployment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SERVICE__DEPLOYMENT = SERVICE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Provided Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SERVICE_FEATURE_COUNT = SERVICE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.InfrastructureServiceImpl <em>Infrastructure Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.InfrastructureServiceImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getInfrastructureService()
	 * @generated
	 */
	int INFRASTRUCTURE_SERVICE = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE__ID = PROVIDED_SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE__NAME = PROVIDED_SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE__DESCRIPTION = PROVIDED_SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE__AV_MAP = PROVIDED_SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE__AE_MAP = PROVIDED_SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Deployment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE__DEPLOYMENT = PROVIDED_SERVICE__DEPLOYMENT;

	/**
	 * The feature id for the '<em><b>Infrastructure Layer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER = PROVIDED_SERVICE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Infrastructure Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE_FEATURE_COUNT = PROVIDED_SERVICE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.ComputingInfrastructureServiceImpl <em>Computing Infrastructure Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.ComputingInfrastructureServiceImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getComputingInfrastructureService()
	 * @generated
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE__ID = INFRASTRUCTURE_SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE__NAME = INFRASTRUCTURE_SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTION = INFRASTRUCTURE_SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE__AV_MAP = INFRASTRUCTURE_SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE__AE_MAP = INFRASTRUCTURE_SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Deployment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE__DEPLOYMENT = INFRASTRUCTURE_SERVICE__DEPLOYMENT;

	/**
	 * The feature id for the '<em><b>Infrastructure Layer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER = INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTOR = INFRASTRUCTURE_SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Platform Services</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE__PLATFORM_SERVICES = INFRASTRUCTURE_SERVICE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Computing Infrastructure Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_FEATURE_COUNT = INFRASTRUCTURE_SERVICE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.PlatformServiceImpl <em>Platform Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.PlatformServiceImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getPlatformService()
	 * @generated
	 */
	int PLATFORM_SERVICE = 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE__ID = SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE__NAME = SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE__DESCRIPTION = SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE__AV_MAP = SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE__AE_MAP = SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE__DESCRIPTOR = SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Computing Infrastructure Service</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE = SERVICE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Platform Layer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE__PLATFORM_LAYER = SERVICE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Platform Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE_FEATURE_COUNT = SERVICE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.SoftwareServiceContainer <em>Software Service Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.SoftwareServiceContainer
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getSoftwareServiceContainer()
	 * @generated
	 */
	int SOFTWARE_SERVICE_CONTAINER = 17;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_CONTAINER__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_CONTAINER__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_CONTAINER__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_CONTAINER__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_CONTAINER__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Software Services</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_CONTAINER__SOFTWARE_SERVICES = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Software Service Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_CONTAINER_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.PlatformRuntimeServiceImpl <em>Platform Runtime Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.PlatformRuntimeServiceImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getPlatformRuntimeService()
	 * @generated
	 */
	int PLATFORM_RUNTIME_SERVICE = 10;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_RUNTIME_SERVICE__ID = SOFTWARE_SERVICE_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_RUNTIME_SERVICE__NAME = SOFTWARE_SERVICE_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_RUNTIME_SERVICE__DESCRIPTION = SOFTWARE_SERVICE_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_RUNTIME_SERVICE__AV_MAP = SOFTWARE_SERVICE_CONTAINER__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_RUNTIME_SERVICE__AE_MAP = SOFTWARE_SERVICE_CONTAINER__AE_MAP;

	/**
	 * The feature id for the '<em><b>Software Services</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_RUNTIME_SERVICE__SOFTWARE_SERVICES = SOFTWARE_SERVICE_CONTAINER__SOFTWARE_SERVICES;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_RUNTIME_SERVICE__DESCRIPTOR = SOFTWARE_SERVICE_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Computing Infrastructure Service</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_RUNTIME_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE = SOFTWARE_SERVICE_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Platform Layer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_RUNTIME_SERVICE__PLATFORM_LAYER = SOFTWARE_SERVICE_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Platform Runtime Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_RUNTIME_SERVICE_FEATURE_COUNT = SOFTWARE_SERVICE_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.PlatformSupportServiceImpl <em>Platform Support Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.PlatformSupportServiceImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getPlatformSupportService()
	 * @generated
	 */
	int PLATFORM_SUPPORT_SERVICE = 11;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SUPPORT_SERVICE__ID = ApplicationPackage.OPERATION_INTERFACE_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SUPPORT_SERVICE__NAME = ApplicationPackage.OPERATION_INTERFACE_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SUPPORT_SERVICE__DESCRIPTION = ApplicationPackage.OPERATION_INTERFACE_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SUPPORT_SERVICE__AV_MAP = ApplicationPackage.OPERATION_INTERFACE_CONTAINER__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SUPPORT_SERVICE__AE_MAP = ApplicationPackage.OPERATION_INTERFACE_CONTAINER__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SUPPORT_SERVICE__PROVIDED_INTERFACES = ApplicationPackage.OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SUPPORT_SERVICE__REQUIRED_INTERFACES = ApplicationPackage.OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SUPPORT_SERVICE__DESCRIPTOR = ApplicationPackage.OPERATION_INTERFACE_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Computing Infrastructure Service</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SUPPORT_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE = ApplicationPackage.OPERATION_INTERFACE_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Platform Layer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SUPPORT_SERVICE__PLATFORM_LAYER = ApplicationPackage.OPERATION_INTERFACE_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Platform Support Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SUPPORT_SERVICE_FEATURE_COUNT = ApplicationPackage.OPERATION_INTERFACE_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.ProvidedPlatformRuntimeServiceImpl <em>Provided Platform Runtime Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.ProvidedPlatformRuntimeServiceImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getProvidedPlatformRuntimeService()
	 * @generated
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE = 12;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE__ID = PLATFORM_RUNTIME_SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE__NAME = PLATFORM_RUNTIME_SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE__DESCRIPTION = PLATFORM_RUNTIME_SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE__AV_MAP = PLATFORM_RUNTIME_SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE__AE_MAP = PLATFORM_RUNTIME_SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Software Services</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE__SOFTWARE_SERVICES = PLATFORM_RUNTIME_SERVICE__SOFTWARE_SERVICES;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE__DESCRIPTOR = PLATFORM_RUNTIME_SERVICE__DESCRIPTOR;

	/**
	 * The feature id for the '<em><b>Computing Infrastructure Service</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE = PLATFORM_RUNTIME_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE;

	/**
	 * The feature id for the '<em><b>Platform Layer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE__PLATFORM_LAYER = PLATFORM_RUNTIME_SERVICE__PLATFORM_LAYER;

	/**
	 * The feature id for the '<em><b>Deployment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE__DEPLOYMENT = PLATFORM_RUNTIME_SERVICE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Provided Platform Runtime Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE_FEATURE_COUNT = PLATFORM_RUNTIME_SERVICE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.ProvidedPlatformSupportServiceImpl <em>Provided Platform Support Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.ProvidedPlatformSupportServiceImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getProvidedPlatformSupportService()
	 * @generated
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE = 13;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE__ID = PLATFORM_SUPPORT_SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE__NAME = PLATFORM_SUPPORT_SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE__DESCRIPTION = PLATFORM_SUPPORT_SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE__AV_MAP = PLATFORM_SUPPORT_SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE__AE_MAP = PLATFORM_SUPPORT_SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE__PROVIDED_INTERFACES = PLATFORM_SUPPORT_SERVICE__PROVIDED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE__REQUIRED_INTERFACES = PLATFORM_SUPPORT_SERVICE__REQUIRED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE__DESCRIPTOR = PLATFORM_SUPPORT_SERVICE__DESCRIPTOR;

	/**
	 * The feature id for the '<em><b>Computing Infrastructure Service</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE = PLATFORM_SUPPORT_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE;

	/**
	 * The feature id for the '<em><b>Platform Layer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE__PLATFORM_LAYER = PLATFORM_SUPPORT_SERVICE__PLATFORM_LAYER;

	/**
	 * The feature id for the '<em><b>Deployment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE__DEPLOYMENT = PLATFORM_SUPPORT_SERVICE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Provided Platform Support Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE_FEATURE_COUNT = PLATFORM_SUPPORT_SERVICE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.SoftwareServiceImpl <em>Software Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.SoftwareServiceImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getSoftwareService()
	 * @generated
	 */
	int SOFTWARE_SERVICE = 14;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE__ID = SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE__NAME = SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE__DESCRIPTION = SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE__AV_MAP = SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE__AE_MAP = SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE__PROVIDED_INTERFACES = SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE__REQUIRED_INTERFACES = SERVICE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Runtime Platform Service</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE = SERVICE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Software Layer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE__SOFTWARE_LAYER = SERVICE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Software Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_FEATURE_COUNT = SERVICE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.ProvidedSoftwareServiceImpl <em>Provided Software Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.ProvidedSoftwareServiceImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getProvidedSoftwareService()
	 * @generated
	 */
	int PROVIDED_SOFTWARE_SERVICE = 15;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE__ID = SOFTWARE_SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE__NAME = SOFTWARE_SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE__DESCRIPTION = SOFTWARE_SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE__AV_MAP = SOFTWARE_SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE__AE_MAP = SOFTWARE_SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE__PROVIDED_INTERFACES = SOFTWARE_SERVICE__PROVIDED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE__REQUIRED_INTERFACES = SOFTWARE_SERVICE__REQUIRED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Runtime Platform Service</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE = SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE;

	/**
	 * The feature id for the '<em><b>Software Layer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE__SOFTWARE_LAYER = SOFTWARE_SERVICE__SOFTWARE_LAYER;

	/**
	 * The feature id for the '<em><b>Deployment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE__DEPLOYMENT = SOFTWARE_SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE__DESCRIPTOR = SOFTWARE_SERVICE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Provided Software Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE_FEATURE_COUNT = SOFTWARE_SERVICE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.ExternalSoftwareServiceImpl <em>External Software Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.ExternalSoftwareServiceImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getExternalSoftwareService()
	 * @generated
	 */
	int EXTERNAL_SOFTWARE_SERVICE = 16;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__ID = PROVIDED_SOFTWARE_SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__NAME = PROVIDED_SOFTWARE_SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__DESCRIPTION = PROVIDED_SOFTWARE_SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__AV_MAP = PROVIDED_SOFTWARE_SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__AE_MAP = PROVIDED_SOFTWARE_SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__PROVIDED_INTERFACES = PROVIDED_SOFTWARE_SERVICE__PROVIDED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__REQUIRED_INTERFACES = PROVIDED_SOFTWARE_SERVICE__REQUIRED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Runtime Platform Service</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE = PROVIDED_SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE;

	/**
	 * The feature id for the '<em><b>Software Layer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__SOFTWARE_LAYER = PROVIDED_SOFTWARE_SERVICE__SOFTWARE_LAYER;

	/**
	 * The feature id for the '<em><b>Deployment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__DEPLOYMENT = PROVIDED_SOFTWARE_SERVICE__DEPLOYMENT;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__DESCRIPTOR = PROVIDED_SOFTWARE_SERVICE__DESCRIPTOR;

	/**
	 * The feature id for the '<em><b>Service Proxy</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY = PROVIDED_SOFTWARE_SERVICE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>External Software Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE_FEATURE_COUNT = PROVIDED_SOFTWARE_SERVICE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.ConnectionImpl <em>Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.ConnectionImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getConnection()
	 * @generated
	 */
	int CONNECTION = 18;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The number of structural features of the '<em>Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.InternalConnectionImpl <em>Internal Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.InternalConnectionImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getInternalConnection()
	 * @generated
	 */
	int INTERNAL_CONNECTION = 19;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_CONNECTION__ID = CONNECTION__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_CONNECTION__NAME = CONNECTION__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_CONNECTION__DESCRIPTION = CONNECTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_CONNECTION__AV_MAP = CONNECTION__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_CONNECTION__AE_MAP = CONNECTION__AE_MAP;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_CONNECTION__DESCRIPTOR = CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_CONNECTION__SOURCE = CONNECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_CONNECTION__TARGET = CONNECTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Internal Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.ExternalConnectionImpl <em>External Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.ExternalConnectionImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getExternalConnection()
	 * @generated
	 */
	int EXTERNAL_CONNECTION = 20;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION__ID = CONNECTION__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION__NAME = CONNECTION__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION__DESCRIPTION = CONNECTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION__AV_MAP = CONNECTION__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION__AE_MAP = CONNECTION__AE_MAP;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION__SOURCE = CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION__TARGET = CONNECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Bandwidth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION__BANDWIDTH = CONNECTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Latency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION__LATENCY = CONNECTION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>External Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.ProxyImpl <em>Proxy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.ProxyImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getProxy()
	 * @generated
	 */
	int PROXY = 21;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROXY__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROXY__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROXY__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROXY__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROXY__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The number of structural features of the '<em>Proxy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROXY_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.ServiceProxyImpl <em>Service Proxy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.ServiceProxyImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getServiceProxy()
	 * @generated
	 */
	int SERVICE_PROXY = 22;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROXY__ID = PROXY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROXY__NAME = PROXY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROXY__DESCRIPTION = PROXY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROXY__AV_MAP = PROXY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROXY__AE_MAP = PROXY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Software Service</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROXY__SOFTWARE_SERVICE = PROXY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Service Proxy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROXY_FEATURE_COUNT = PROXY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.architecture.impl.UsageProxyImpl <em>Usage Proxy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.architecture.impl.UsageProxyImpl
	 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getUsageProxy()
	 * @generated
	 */
	int USAGE_PROXY = 23;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USAGE_PROXY__ID = PROXY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USAGE_PROXY__NAME = PROXY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USAGE_PROXY__DESCRIPTION = PROXY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USAGE_PROXY__AV_MAP = PROXY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USAGE_PROXY__AE_MAP = PROXY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USAGE_PROXY__PROVIDED_INTERFACES = PROXY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USAGE_PROXY__REQUIRED_INTERFACES = PROXY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Usage Proxy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USAGE_PROXY_FEATURE_COUNT = PROXY_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.Architecture <em>Architecture</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Architecture</em>'.
	 * @see org.scaledl.overview.architecture.Architecture
	 * @generated
	 */
	EClass getArchitecture();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.architecture.Architecture#getCloudEnvironments <em>Cloud Environments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cloud Environments</em>'.
	 * @see org.scaledl.overview.architecture.Architecture#getCloudEnvironments()
	 * @see #getArchitecture()
	 * @generated
	 */
	EReference getArchitecture_CloudEnvironments();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.architecture.Architecture#getProxies <em>Proxies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Proxies</em>'.
	 * @see org.scaledl.overview.architecture.Architecture#getProxies()
	 * @see #getArchitecture()
	 * @generated
	 */
	EReference getArchitecture_Proxies();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.architecture.Architecture#getUsageConnections <em>Usage Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Usage Connections</em>'.
	 * @see org.scaledl.overview.architecture.Architecture#getUsageConnections()
	 * @see #getArchitecture()
	 * @generated
	 */
	EReference getArchitecture_UsageConnections();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.architecture.Architecture#getExternalConnections <em>External Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>External Connections</em>'.
	 * @see org.scaledl.overview.architecture.Architecture#getExternalConnections()
	 * @see #getArchitecture()
	 * @generated
	 */
	EReference getArchitecture_ExternalConnections();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.architecture.Architecture#getUnresolvedOperationInterfaces <em>Unresolved Operation Interfaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Unresolved Operation Interfaces</em>'.
	 * @see org.scaledl.overview.architecture.Architecture#getUnresolvedOperationInterfaces()
	 * @see #getArchitecture()
	 * @generated
	 */
	EReference getArchitecture_UnresolvedOperationInterfaces();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.CloudEnvironment <em>Cloud Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cloud Environment</em>'.
	 * @see org.scaledl.overview.architecture.CloudEnvironment
	 * @generated
	 */
	EClass getCloudEnvironment();

	/**
	 * Returns the meta object for the container reference '{@link org.scaledl.overview.architecture.CloudEnvironment#getArchitecture <em>Architecture</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Architecture</em>'.
	 * @see org.scaledl.overview.architecture.CloudEnvironment#getArchitecture()
	 * @see #getCloudEnvironment()
	 * @generated
	 */
	EReference getCloudEnvironment_Architecture();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.architecture.CloudEnvironment#getInternalConnections <em>Internal Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Internal Connections</em>'.
	 * @see org.scaledl.overview.architecture.CloudEnvironment#getInternalConnections()
	 * @see #getCloudEnvironment()
	 * @generated
	 */
	EReference getCloudEnvironment_InternalConnections();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.architecture.CloudEnvironment#getCloudEnvironmentDescriptor <em>Cloud Environment Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Cloud Environment Descriptor</em>'.
	 * @see org.scaledl.overview.architecture.CloudEnvironment#getCloudEnvironmentDescriptor()
	 * @see #getCloudEnvironment()
	 * @generated
	 */
	EReference getCloudEnvironment_CloudEnvironmentDescriptor();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.architecture.CloudEnvironment#getAvailabilityZoneDescriptor <em>Availability Zone Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Availability Zone Descriptor</em>'.
	 * @see org.scaledl.overview.architecture.CloudEnvironment#getAvailabilityZoneDescriptor()
	 * @see #getCloudEnvironment()
	 * @generated
	 */
	EReference getCloudEnvironment_AvailabilityZoneDescriptor();

	/**
	 * Returns the meta object for the containment reference '{@link org.scaledl.overview.architecture.CloudEnvironment#getInfrastructureLayer <em>Infrastructure Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Infrastructure Layer</em>'.
	 * @see org.scaledl.overview.architecture.CloudEnvironment#getInfrastructureLayer()
	 * @see #getCloudEnvironment()
	 * @generated
	 */
	EReference getCloudEnvironment_InfrastructureLayer();

	/**
	 * Returns the meta object for the containment reference '{@link org.scaledl.overview.architecture.CloudEnvironment#getPlatformLayer <em>Platform Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Platform Layer</em>'.
	 * @see org.scaledl.overview.architecture.CloudEnvironment#getPlatformLayer()
	 * @see #getCloudEnvironment()
	 * @generated
	 */
	EReference getCloudEnvironment_PlatformLayer();

	/**
	 * Returns the meta object for the containment reference '{@link org.scaledl.overview.architecture.CloudEnvironment#getSoftwareLayer <em>Software Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Software Layer</em>'.
	 * @see org.scaledl.overview.architecture.CloudEnvironment#getSoftwareLayer()
	 * @see #getCloudEnvironment()
	 * @generated
	 */
	EReference getCloudEnvironment_SoftwareLayer();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.InfrastructureLayer <em>Infrastructure Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Infrastructure Layer</em>'.
	 * @see org.scaledl.overview.architecture.InfrastructureLayer
	 * @generated
	 */
	EClass getInfrastructureLayer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.architecture.InfrastructureLayer#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Services</em>'.
	 * @see org.scaledl.overview.architecture.InfrastructureLayer#getServices()
	 * @see #getInfrastructureLayer()
	 * @generated
	 */
	EReference getInfrastructureLayer_Services();

	/**
	 * Returns the meta object for the container reference '{@link org.scaledl.overview.architecture.InfrastructureLayer#getCloudEnvironment <em>Cloud Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Cloud Environment</em>'.
	 * @see org.scaledl.overview.architecture.InfrastructureLayer#getCloudEnvironment()
	 * @see #getInfrastructureLayer()
	 * @generated
	 */
	EReference getInfrastructureLayer_CloudEnvironment();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.PlatformLayer <em>Platform Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Platform Layer</em>'.
	 * @see org.scaledl.overview.architecture.PlatformLayer
	 * @generated
	 */
	EClass getPlatformLayer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.architecture.PlatformLayer#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Services</em>'.
	 * @see org.scaledl.overview.architecture.PlatformLayer#getServices()
	 * @see #getPlatformLayer()
	 * @generated
	 */
	EReference getPlatformLayer_Services();

	/**
	 * Returns the meta object for the container reference '{@link org.scaledl.overview.architecture.PlatformLayer#getCloudEnvironment <em>Cloud Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Cloud Environment</em>'.
	 * @see org.scaledl.overview.architecture.PlatformLayer#getCloudEnvironment()
	 * @see #getPlatformLayer()
	 * @generated
	 */
	EReference getPlatformLayer_CloudEnvironment();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.SoftwareLayer <em>Software Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Software Layer</em>'.
	 * @see org.scaledl.overview.architecture.SoftwareLayer
	 * @generated
	 */
	EClass getSoftwareLayer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.architecture.SoftwareLayer#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Services</em>'.
	 * @see org.scaledl.overview.architecture.SoftwareLayer#getServices()
	 * @see #getSoftwareLayer()
	 * @generated
	 */
	EReference getSoftwareLayer_Services();

	/**
	 * Returns the meta object for the container reference '{@link org.scaledl.overview.architecture.SoftwareLayer#getCloudEnvironment <em>Cloud Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Cloud Environment</em>'.
	 * @see org.scaledl.overview.architecture.SoftwareLayer#getCloudEnvironment()
	 * @see #getSoftwareLayer()
	 * @generated
	 */
	EReference getSoftwareLayer_CloudEnvironment();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.Service <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service</em>'.
	 * @see org.scaledl.overview.architecture.Service
	 * @generated
	 */
	EClass getService();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.ProvidedService <em>Provided Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provided Service</em>'.
	 * @see org.scaledl.overview.architecture.ProvidedService
	 * @generated
	 */
	EClass getProvidedService();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.architecture.ProvidedService#getDeployment <em>Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Deployment</em>'.
	 * @see org.scaledl.overview.architecture.ProvidedService#getDeployment()
	 * @see #getProvidedService()
	 * @generated
	 */
	EReference getProvidedService_Deployment();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.InfrastructureService <em>Infrastructure Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Infrastructure Service</em>'.
	 * @see org.scaledl.overview.architecture.InfrastructureService
	 * @generated
	 */
	EClass getInfrastructureService();

	/**
	 * Returns the meta object for the container reference '{@link org.scaledl.overview.architecture.InfrastructureService#getInfrastructureLayer <em>Infrastructure Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Infrastructure Layer</em>'.
	 * @see org.scaledl.overview.architecture.InfrastructureService#getInfrastructureLayer()
	 * @see #getInfrastructureService()
	 * @generated
	 */
	EReference getInfrastructureService_InfrastructureLayer();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.ComputingInfrastructureService <em>Computing Infrastructure Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Computing Infrastructure Service</em>'.
	 * @see org.scaledl.overview.architecture.ComputingInfrastructureService
	 * @generated
	 */
	EClass getComputingInfrastructureService();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.architecture.ComputingInfrastructureService#getDescriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Descriptor</em>'.
	 * @see org.scaledl.overview.architecture.ComputingInfrastructureService#getDescriptor()
	 * @see #getComputingInfrastructureService()
	 * @generated
	 */
	EReference getComputingInfrastructureService_Descriptor();

	/**
	 * Returns the meta object for the reference list '{@link org.scaledl.overview.architecture.ComputingInfrastructureService#getPlatformServices <em>Platform Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Platform Services</em>'.
	 * @see org.scaledl.overview.architecture.ComputingInfrastructureService#getPlatformServices()
	 * @see #getComputingInfrastructureService()
	 * @generated
	 */
	EReference getComputingInfrastructureService_PlatformServices();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.PlatformService <em>Platform Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Platform Service</em>'.
	 * @see org.scaledl.overview.architecture.PlatformService
	 * @generated
	 */
	EClass getPlatformService();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.architecture.PlatformService#getDescriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Descriptor</em>'.
	 * @see org.scaledl.overview.architecture.PlatformService#getDescriptor()
	 * @see #getPlatformService()
	 * @generated
	 */
	EReference getPlatformService_Descriptor();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.architecture.PlatformService#getComputingInfrastructureService <em>Computing Infrastructure Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Computing Infrastructure Service</em>'.
	 * @see org.scaledl.overview.architecture.PlatformService#getComputingInfrastructureService()
	 * @see #getPlatformService()
	 * @generated
	 */
	EReference getPlatformService_ComputingInfrastructureService();

	/**
	 * Returns the meta object for the container reference '{@link org.scaledl.overview.architecture.PlatformService#getPlatformLayer <em>Platform Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Platform Layer</em>'.
	 * @see org.scaledl.overview.architecture.PlatformService#getPlatformLayer()
	 * @see #getPlatformService()
	 * @generated
	 */
	EReference getPlatformService_PlatformLayer();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.PlatformRuntimeService <em>Platform Runtime Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Platform Runtime Service</em>'.
	 * @see org.scaledl.overview.architecture.PlatformRuntimeService
	 * @generated
	 */
	EClass getPlatformRuntimeService();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.PlatformSupportService <em>Platform Support Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Platform Support Service</em>'.
	 * @see org.scaledl.overview.architecture.PlatformSupportService
	 * @generated
	 */
	EClass getPlatformSupportService();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.ProvidedPlatformRuntimeService <em>Provided Platform Runtime Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provided Platform Runtime Service</em>'.
	 * @see org.scaledl.overview.architecture.ProvidedPlatformRuntimeService
	 * @generated
	 */
	EClass getProvidedPlatformRuntimeService();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.ProvidedPlatformSupportService <em>Provided Platform Support Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provided Platform Support Service</em>'.
	 * @see org.scaledl.overview.architecture.ProvidedPlatformSupportService
	 * @generated
	 */
	EClass getProvidedPlatformSupportService();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.SoftwareService <em>Software Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Software Service</em>'.
	 * @see org.scaledl.overview.architecture.SoftwareService
	 * @generated
	 */
	EClass getSoftwareService();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.architecture.SoftwareService#getRuntimePlatformService <em>Runtime Platform Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Runtime Platform Service</em>'.
	 * @see org.scaledl.overview.architecture.SoftwareService#getRuntimePlatformService()
	 * @see #getSoftwareService()
	 * @generated
	 */
	EReference getSoftwareService_RuntimePlatformService();

	/**
	 * Returns the meta object for the container reference '{@link org.scaledl.overview.architecture.SoftwareService#getSoftwareLayer <em>Software Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Software Layer</em>'.
	 * @see org.scaledl.overview.architecture.SoftwareService#getSoftwareLayer()
	 * @see #getSoftwareService()
	 * @generated
	 */
	EReference getSoftwareService_SoftwareLayer();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.ProvidedSoftwareService <em>Provided Software Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provided Software Service</em>'.
	 * @see org.scaledl.overview.architecture.ProvidedSoftwareService
	 * @generated
	 */
	EClass getProvidedSoftwareService();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.architecture.ProvidedSoftwareService#getDescriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Descriptor</em>'.
	 * @see org.scaledl.overview.architecture.ProvidedSoftwareService#getDescriptor()
	 * @see #getProvidedSoftwareService()
	 * @generated
	 */
	EReference getProvidedSoftwareService_Descriptor();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.ExternalSoftwareService <em>External Software Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Software Service</em>'.
	 * @see org.scaledl.overview.architecture.ExternalSoftwareService
	 * @generated
	 */
	EClass getExternalSoftwareService();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.architecture.ExternalSoftwareService#getServiceProxy <em>Service Proxy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Service Proxy</em>'.
	 * @see org.scaledl.overview.architecture.ExternalSoftwareService#getServiceProxy()
	 * @see #getExternalSoftwareService()
	 * @generated
	 */
	EReference getExternalSoftwareService_ServiceProxy();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.SoftwareServiceContainer <em>Software Service Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Software Service Container</em>'.
	 * @see org.scaledl.overview.architecture.SoftwareServiceContainer
	 * @generated
	 */
	EClass getSoftwareServiceContainer();

	/**
	 * Returns the meta object for the reference list '{@link org.scaledl.overview.architecture.SoftwareServiceContainer#getSoftwareServices <em>Software Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Software Services</em>'.
	 * @see org.scaledl.overview.architecture.SoftwareServiceContainer#getSoftwareServices()
	 * @see #getSoftwareServiceContainer()
	 * @generated
	 */
	EReference getSoftwareServiceContainer_SoftwareServices();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection</em>'.
	 * @see org.scaledl.overview.architecture.Connection
	 * @generated
	 */
	EClass getConnection();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.InternalConnection <em>Internal Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Internal Connection</em>'.
	 * @see org.scaledl.overview.architecture.InternalConnection
	 * @generated
	 */
	EClass getInternalConnection();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.architecture.InternalConnection#getDescriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Descriptor</em>'.
	 * @see org.scaledl.overview.architecture.InternalConnection#getDescriptor()
	 * @see #getInternalConnection()
	 * @generated
	 */
	EReference getInternalConnection_Descriptor();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.architecture.InternalConnection#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.scaledl.overview.architecture.InternalConnection#getSource()
	 * @see #getInternalConnection()
	 * @generated
	 */
	EReference getInternalConnection_Source();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.architecture.InternalConnection#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.scaledl.overview.architecture.InternalConnection#getTarget()
	 * @see #getInternalConnection()
	 * @generated
	 */
	EReference getInternalConnection_Target();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.ExternalConnection <em>External Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Connection</em>'.
	 * @see org.scaledl.overview.architecture.ExternalConnection
	 * @generated
	 */
	EClass getExternalConnection();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.architecture.ExternalConnection#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.scaledl.overview.architecture.ExternalConnection#getSource()
	 * @see #getExternalConnection()
	 * @generated
	 */
	EReference getExternalConnection_Source();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.architecture.ExternalConnection#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.scaledl.overview.architecture.ExternalConnection#getTarget()
	 * @see #getExternalConnection()
	 * @generated
	 */
	EReference getExternalConnection_Target();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.architecture.ExternalConnection#getBandwidth <em>Bandwidth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bandwidth</em>'.
	 * @see org.scaledl.overview.architecture.ExternalConnection#getBandwidth()
	 * @see #getExternalConnection()
	 * @generated
	 */
	EAttribute getExternalConnection_Bandwidth();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.architecture.ExternalConnection#getLatency <em>Latency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Latency</em>'.
	 * @see org.scaledl.overview.architecture.ExternalConnection#getLatency()
	 * @see #getExternalConnection()
	 * @generated
	 */
	EAttribute getExternalConnection_Latency();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.Proxy <em>Proxy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Proxy</em>'.
	 * @see org.scaledl.overview.architecture.Proxy
	 * @generated
	 */
	EClass getProxy();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.ServiceProxy <em>Service Proxy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Proxy</em>'.
	 * @see org.scaledl.overview.architecture.ServiceProxy
	 * @generated
	 */
	EClass getServiceProxy();

	/**
	 * Returns the meta object for the containment reference '{@link org.scaledl.overview.architecture.ServiceProxy#getSoftwareService <em>Software Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Software Service</em>'.
	 * @see org.scaledl.overview.architecture.ServiceProxy#getSoftwareService()
	 * @see #getServiceProxy()
	 * @generated
	 */
	EReference getServiceProxy_SoftwareService();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.architecture.UsageProxy <em>Usage Proxy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Usage Proxy</em>'.
	 * @see org.scaledl.overview.architecture.UsageProxy
	 * @generated
	 */
	EClass getUsageProxy();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ArchitectureFactory getArchitectureFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.ArchitectureImpl <em>Architecture</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.ArchitectureImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getArchitecture()
		 * @generated
		 */
		EClass ARCHITECTURE = eINSTANCE.getArchitecture();

		/**
		 * The meta object literal for the '<em><b>Cloud Environments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHITECTURE__CLOUD_ENVIRONMENTS = eINSTANCE.getArchitecture_CloudEnvironments();

		/**
		 * The meta object literal for the '<em><b>Proxies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHITECTURE__PROXIES = eINSTANCE.getArchitecture_Proxies();

		/**
		 * The meta object literal for the '<em><b>Usage Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHITECTURE__USAGE_CONNECTIONS = eINSTANCE.getArchitecture_UsageConnections();

		/**
		 * The meta object literal for the '<em><b>External Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHITECTURE__EXTERNAL_CONNECTIONS = eINSTANCE.getArchitecture_ExternalConnections();

		/**
		 * The meta object literal for the '<em><b>Unresolved Operation Interfaces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHITECTURE__UNRESOLVED_OPERATION_INTERFACES = eINSTANCE.getArchitecture_UnresolvedOperationInterfaces();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.CloudEnvironmentImpl <em>Cloud Environment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.CloudEnvironmentImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getCloudEnvironment()
		 * @generated
		 */
		EClass CLOUD_ENVIRONMENT = eINSTANCE.getCloudEnvironment();

		/**
		 * The meta object literal for the '<em><b>Architecture</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_ENVIRONMENT__ARCHITECTURE = eINSTANCE.getCloudEnvironment_Architecture();

		/**
		 * The meta object literal for the '<em><b>Internal Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_ENVIRONMENT__INTERNAL_CONNECTIONS = eINSTANCE.getCloudEnvironment_InternalConnections();

		/**
		 * The meta object literal for the '<em><b>Cloud Environment Descriptor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_ENVIRONMENT__CLOUD_ENVIRONMENT_DESCRIPTOR = eINSTANCE.getCloudEnvironment_CloudEnvironmentDescriptor();

		/**
		 * The meta object literal for the '<em><b>Availability Zone Descriptor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_ENVIRONMENT__AVAILABILITY_ZONE_DESCRIPTOR = eINSTANCE.getCloudEnvironment_AvailabilityZoneDescriptor();

		/**
		 * The meta object literal for the '<em><b>Infrastructure Layer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER = eINSTANCE.getCloudEnvironment_InfrastructureLayer();

		/**
		 * The meta object literal for the '<em><b>Platform Layer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_ENVIRONMENT__PLATFORM_LAYER = eINSTANCE.getCloudEnvironment_PlatformLayer();

		/**
		 * The meta object literal for the '<em><b>Software Layer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_ENVIRONMENT__SOFTWARE_LAYER = eINSTANCE.getCloudEnvironment_SoftwareLayer();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.InfrastructureLayerImpl <em>Infrastructure Layer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.InfrastructureLayerImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getInfrastructureLayer()
		 * @generated
		 */
		EClass INFRASTRUCTURE_LAYER = eINSTANCE.getInfrastructureLayer();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFRASTRUCTURE_LAYER__SERVICES = eINSTANCE.getInfrastructureLayer_Services();

		/**
		 * The meta object literal for the '<em><b>Cloud Environment</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFRASTRUCTURE_LAYER__CLOUD_ENVIRONMENT = eINSTANCE.getInfrastructureLayer_CloudEnvironment();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.PlatformLayerImpl <em>Platform Layer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.PlatformLayerImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getPlatformLayer()
		 * @generated
		 */
		EClass PLATFORM_LAYER = eINSTANCE.getPlatformLayer();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLATFORM_LAYER__SERVICES = eINSTANCE.getPlatformLayer_Services();

		/**
		 * The meta object literal for the '<em><b>Cloud Environment</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLATFORM_LAYER__CLOUD_ENVIRONMENT = eINSTANCE.getPlatformLayer_CloudEnvironment();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.SoftwareLayerImpl <em>Software Layer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.SoftwareLayerImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getSoftwareLayer()
		 * @generated
		 */
		EClass SOFTWARE_LAYER = eINSTANCE.getSoftwareLayer();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOFTWARE_LAYER__SERVICES = eINSTANCE.getSoftwareLayer_Services();

		/**
		 * The meta object literal for the '<em><b>Cloud Environment</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOFTWARE_LAYER__CLOUD_ENVIRONMENT = eINSTANCE.getSoftwareLayer_CloudEnvironment();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.Service <em>Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.Service
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getService()
		 * @generated
		 */
		EClass SERVICE = eINSTANCE.getService();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.ProvidedServiceImpl <em>Provided Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.ProvidedServiceImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getProvidedService()
		 * @generated
		 */
		EClass PROVIDED_SERVICE = eINSTANCE.getProvidedService();

		/**
		 * The meta object literal for the '<em><b>Deployment</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROVIDED_SERVICE__DEPLOYMENT = eINSTANCE.getProvidedService_Deployment();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.InfrastructureServiceImpl <em>Infrastructure Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.InfrastructureServiceImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getInfrastructureService()
		 * @generated
		 */
		EClass INFRASTRUCTURE_SERVICE = eINSTANCE.getInfrastructureService();

		/**
		 * The meta object literal for the '<em><b>Infrastructure Layer</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER = eINSTANCE.getInfrastructureService_InfrastructureLayer();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.ComputingInfrastructureServiceImpl <em>Computing Infrastructure Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.ComputingInfrastructureServiceImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getComputingInfrastructureService()
		 * @generated
		 */
		EClass COMPUTING_INFRASTRUCTURE_SERVICE = eINSTANCE.getComputingInfrastructureService();

		/**
		 * The meta object literal for the '<em><b>Descriptor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTOR = eINSTANCE.getComputingInfrastructureService_Descriptor();

		/**
		 * The meta object literal for the '<em><b>Platform Services</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPUTING_INFRASTRUCTURE_SERVICE__PLATFORM_SERVICES = eINSTANCE.getComputingInfrastructureService_PlatformServices();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.PlatformServiceImpl <em>Platform Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.PlatformServiceImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getPlatformService()
		 * @generated
		 */
		EClass PLATFORM_SERVICE = eINSTANCE.getPlatformService();

		/**
		 * The meta object literal for the '<em><b>Descriptor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLATFORM_SERVICE__DESCRIPTOR = eINSTANCE.getPlatformService_Descriptor();

		/**
		 * The meta object literal for the '<em><b>Computing Infrastructure Service</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE = eINSTANCE.getPlatformService_ComputingInfrastructureService();

		/**
		 * The meta object literal for the '<em><b>Platform Layer</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLATFORM_SERVICE__PLATFORM_LAYER = eINSTANCE.getPlatformService_PlatformLayer();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.PlatformRuntimeServiceImpl <em>Platform Runtime Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.PlatformRuntimeServiceImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getPlatformRuntimeService()
		 * @generated
		 */
		EClass PLATFORM_RUNTIME_SERVICE = eINSTANCE.getPlatformRuntimeService();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.PlatformSupportServiceImpl <em>Platform Support Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.PlatformSupportServiceImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getPlatformSupportService()
		 * @generated
		 */
		EClass PLATFORM_SUPPORT_SERVICE = eINSTANCE.getPlatformSupportService();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.ProvidedPlatformRuntimeServiceImpl <em>Provided Platform Runtime Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.ProvidedPlatformRuntimeServiceImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getProvidedPlatformRuntimeService()
		 * @generated
		 */
		EClass PROVIDED_PLATFORM_RUNTIME_SERVICE = eINSTANCE.getProvidedPlatformRuntimeService();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.ProvidedPlatformSupportServiceImpl <em>Provided Platform Support Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.ProvidedPlatformSupportServiceImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getProvidedPlatformSupportService()
		 * @generated
		 */
		EClass PROVIDED_PLATFORM_SUPPORT_SERVICE = eINSTANCE.getProvidedPlatformSupportService();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.SoftwareServiceImpl <em>Software Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.SoftwareServiceImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getSoftwareService()
		 * @generated
		 */
		EClass SOFTWARE_SERVICE = eINSTANCE.getSoftwareService();

		/**
		 * The meta object literal for the '<em><b>Runtime Platform Service</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE = eINSTANCE.getSoftwareService_RuntimePlatformService();

		/**
		 * The meta object literal for the '<em><b>Software Layer</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOFTWARE_SERVICE__SOFTWARE_LAYER = eINSTANCE.getSoftwareService_SoftwareLayer();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.ProvidedSoftwareServiceImpl <em>Provided Software Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.ProvidedSoftwareServiceImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getProvidedSoftwareService()
		 * @generated
		 */
		EClass PROVIDED_SOFTWARE_SERVICE = eINSTANCE.getProvidedSoftwareService();

		/**
		 * The meta object literal for the '<em><b>Descriptor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROVIDED_SOFTWARE_SERVICE__DESCRIPTOR = eINSTANCE.getProvidedSoftwareService_Descriptor();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.ExternalSoftwareServiceImpl <em>External Software Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.ExternalSoftwareServiceImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getExternalSoftwareService()
		 * @generated
		 */
		EClass EXTERNAL_SOFTWARE_SERVICE = eINSTANCE.getExternalSoftwareService();

		/**
		 * The meta object literal for the '<em><b>Service Proxy</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY = eINSTANCE.getExternalSoftwareService_ServiceProxy();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.SoftwareServiceContainer <em>Software Service Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.SoftwareServiceContainer
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getSoftwareServiceContainer()
		 * @generated
		 */
		EClass SOFTWARE_SERVICE_CONTAINER = eINSTANCE.getSoftwareServiceContainer();

		/**
		 * The meta object literal for the '<em><b>Software Services</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOFTWARE_SERVICE_CONTAINER__SOFTWARE_SERVICES = eINSTANCE.getSoftwareServiceContainer_SoftwareServices();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.ConnectionImpl <em>Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.ConnectionImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getConnection()
		 * @generated
		 */
		EClass CONNECTION = eINSTANCE.getConnection();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.InternalConnectionImpl <em>Internal Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.InternalConnectionImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getInternalConnection()
		 * @generated
		 */
		EClass INTERNAL_CONNECTION = eINSTANCE.getInternalConnection();

		/**
		 * The meta object literal for the '<em><b>Descriptor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERNAL_CONNECTION__DESCRIPTOR = eINSTANCE.getInternalConnection_Descriptor();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERNAL_CONNECTION__SOURCE = eINSTANCE.getInternalConnection_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERNAL_CONNECTION__TARGET = eINSTANCE.getInternalConnection_Target();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.ExternalConnectionImpl <em>External Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.ExternalConnectionImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getExternalConnection()
		 * @generated
		 */
		EClass EXTERNAL_CONNECTION = eINSTANCE.getExternalConnection();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERNAL_CONNECTION__SOURCE = eINSTANCE.getExternalConnection_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERNAL_CONNECTION__TARGET = eINSTANCE.getExternalConnection_Target();

		/**
		 * The meta object literal for the '<em><b>Bandwidth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_CONNECTION__BANDWIDTH = eINSTANCE.getExternalConnection_Bandwidth();

		/**
		 * The meta object literal for the '<em><b>Latency</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_CONNECTION__LATENCY = eINSTANCE.getExternalConnection_Latency();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.ProxyImpl <em>Proxy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.ProxyImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getProxy()
		 * @generated
		 */
		EClass PROXY = eINSTANCE.getProxy();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.ServiceProxyImpl <em>Service Proxy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.ServiceProxyImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getServiceProxy()
		 * @generated
		 */
		EClass SERVICE_PROXY = eINSTANCE.getServiceProxy();

		/**
		 * The meta object literal for the '<em><b>Software Service</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE_PROXY__SOFTWARE_SERVICE = eINSTANCE.getServiceProxy_SoftwareService();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.architecture.impl.UsageProxyImpl <em>Usage Proxy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.architecture.impl.UsageProxyImpl
		 * @see org.scaledl.overview.architecture.impl.ArchitecturePackageImpl#getUsageProxy()
		 * @generated
		 */
		EClass USAGE_PROXY = eINSTANCE.getUsageProxy();

	}

} //ArchitecturePackage
