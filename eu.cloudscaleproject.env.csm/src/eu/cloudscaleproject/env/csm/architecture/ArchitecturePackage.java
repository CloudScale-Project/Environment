/**
 */
package eu.cloudscaleproject.env.csm.architecture;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import eu.cloudscaleproject.env.csm.core.CorePackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc --> <!-- begin-model-doc --> The package contains base
 * classes for building cloud based systems. The classes are abstract
 * representation of the cloud system architecture, while the exact
 * specification is referenced through descriptors defined in the Definition
 * package. <!-- end-model-doc -->
 * 
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitectureFactory
 * @model kind="package"
 * @generated
 */
public interface ArchitecturePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "architecture";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://eu.cloudscaleproject.env/CloudscaleComponentModel/Architecture/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "architecture";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	ArchitecturePackage eINSTANCE = eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl.init();

	/**
	 * The meta object id for the '
	 * {@link eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureImpl
	 * <em>Architecture</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getArchitecture()
	 * @generated
	 */
	int ARCHITECTURE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Cloud Providers</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__CLOUD_PROVIDERS = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Proxies</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__PROXIES = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>External Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__EXTERNAL_CONNECTIONS = CorePackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Hybrid Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__HYBRID_CONNECTIONS = CorePackage.ENTITY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Unresolved Operation Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__UNRESOLVED_OPERATION_INTERFACES = CorePackage.ENTITY_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Architecture</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.CloudEnvironmentImpl <em>Cloud Environment</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.CloudEnvironmentImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getCloudEnvironment()
	 * @generated
	 */
	int CLOUD_ENVIRONMENT = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Internal Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__INTERNAL_CONNECTIONS = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cloud Provider Descriptor</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__CLOUD_PROVIDER_DESCRIPTOR = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Availability Zone Descriptor</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__AVAILABILITY_ZONE_DESCRIPTOR = CorePackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Architecture</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__ARCHITECTURE = CorePackage.ENTITY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Infrastructure Layer</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER = CorePackage.ENTITY_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Platform Layer</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__PLATFORM_LAYER = CorePackage.ENTITY_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Software Layer</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT__SOFTWARE_LAYER = CorePackage.ENTITY_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Cloud Environment</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.InfrastructureLayerImpl <em>Infrastructure Layer</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.InfrastructureLayerImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getInfrastructureLayer()
	 * @generated
	 */
	int INFRASTRUCTURE_LAYER = 2;

	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_LAYER__SERVICES = 0;

	/**
	 * The feature id for the '<em><b>Cloud Provider</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_LAYER__CLOUD_PROVIDER = 1;

	/**
	 * The number of structural features of the '<em>Infrastructure Layer</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_LAYER_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.PlatformLayerImpl <em>Platform Layer</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.PlatformLayerImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getPlatformLayer()
	 * @generated
	 */
	int PLATFORM_LAYER = 3;

	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_LAYER__SERVICES = 0;

	/**
	 * The feature id for the '<em><b>Cloud Environment</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_LAYER__CLOUD_ENVIRONMENT = 1;

	/**
	 * The number of structural features of the '<em>Platform Layer</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_LAYER_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.SoftwareLayerImpl <em>Software Layer</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.SoftwareLayerImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getSoftwareLayer()
	 * @generated
	 */
	int SOFTWARE_LAYER = 4;

	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_LAYER__SERVICES = 0;

	/**
	 * The feature id for the '<em><b>Cloud Provider</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_LAYER__CLOUD_PROVIDER = 1;

	/**
	 * The number of structural features of the '<em>Software Layer</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_LAYER_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.ServiceImpl <em>Service</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ServiceImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getService()
	 * @generated
	 */
	int SERVICE = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SERVICE__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SERVICE__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SERVICE__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The number of structural features of the '<em>Service</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SERVICE_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.InfrastructureServiceImpl <em>Infrastructure Service</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.InfrastructureServiceImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getInfrastructureService()
	 * @generated
	 */
	int INFRASTRUCTURE_SERVICE = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE__ID = SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE__NAME = SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE__DESCRIPTION = SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE__AV_MAP = SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE__AE_MAP = SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Infrastructure Layer</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER = SERVICE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Infrastructure Service</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE_FEATURE_COUNT = SERVICE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.PlatformServiceImpl <em>Platform Service</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.PlatformServiceImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getPlatformService()
	 * @generated
	 */
	int PLATFORM_SERVICE = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE__ID = SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE__NAME = SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE__DESCRIPTION = SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE__AV_MAP = SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE__AE_MAP = SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Platform Layer</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE__PLATFORM_LAYER = SERVICE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Platform Service</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE_FEATURE_COUNT = SERVICE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.SoftwareServiceImpl <em>Software Service</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.SoftwareServiceImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getSoftwareService()
	 * @generated
	 */
	int SOFTWARE_SERVICE = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE__ID = SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE__NAME = SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE__DESCRIPTION = SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE__AV_MAP = SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE__AE_MAP = SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE__PROVIDED_INTERFACES = SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE__REQUIRED_INTERFACES = SERVICE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Software Layer</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE__SOFTWARE_LAYER = SERVICE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Software Service</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_FEATURE_COUNT = SERVICE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.DeployablePlatformServiceImpl <em>Deployable Platform Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.DeployablePlatformServiceImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getDeployablePlatformService()
	 * @generated
	 */
	int DEPLOYABLE_PLATFORM_SERVICE = 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_PLATFORM_SERVICE__ID = PLATFORM_SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_PLATFORM_SERVICE__NAME = PLATFORM_SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_PLATFORM_SERVICE__DESCRIPTION = PLATFORM_SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_PLATFORM_SERVICE__AV_MAP = PLATFORM_SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_PLATFORM_SERVICE__AE_MAP = PLATFORM_SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Platform Layer</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_PLATFORM_SERVICE__PLATFORM_LAYER = PLATFORM_SERVICE__PLATFORM_LAYER;

	/**
	 * The feature id for the '<em><b>Computing Infrastructure Service</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE = PLATFORM_SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_PLATFORM_SERVICE__DESCRIPTOR = PLATFORM_SERVICE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Deployable Platform Service</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_PLATFORM_SERVICE_FEATURE_COUNT = PLATFORM_SERVICE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.ExternalPlatformServiceImpl <em>External Platform Service</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ExternalPlatformServiceImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getExternalPlatformService()
	 * @generated
	 */
	int EXTERNAL_PLATFORM_SERVICE = 10;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PLATFORM_SERVICE__ID = PLATFORM_SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PLATFORM_SERVICE__NAME = PLATFORM_SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PLATFORM_SERVICE__DESCRIPTION = PLATFORM_SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PLATFORM_SERVICE__AV_MAP = PLATFORM_SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PLATFORM_SERVICE__AE_MAP = PLATFORM_SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Platform Layer</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PLATFORM_SERVICE__PLATFORM_LAYER = PLATFORM_SERVICE__PLATFORM_LAYER;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PLATFORM_SERVICE__DESCRIPTOR = PLATFORM_SERVICE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>External Platform Service</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PLATFORM_SERVICE_FEATURE_COUNT = PLATFORM_SERVICE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.DeployableRuntimeServiceImpl <em>Deployable Runtime Service</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.DeployableRuntimeServiceImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getDeployableRuntimeService()
	 * @generated
	 */
	int DEPLOYABLE_RUNTIME_SERVICE = 11;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_RUNTIME_SERVICE__ID = DEPLOYABLE_PLATFORM_SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_RUNTIME_SERVICE__NAME = DEPLOYABLE_PLATFORM_SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_RUNTIME_SERVICE__DESCRIPTION = DEPLOYABLE_PLATFORM_SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_RUNTIME_SERVICE__AV_MAP = DEPLOYABLE_PLATFORM_SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_RUNTIME_SERVICE__AE_MAP = DEPLOYABLE_PLATFORM_SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Platform Layer</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_RUNTIME_SERVICE__PLATFORM_LAYER = DEPLOYABLE_PLATFORM_SERVICE__PLATFORM_LAYER;

	/**
	 * The feature id for the '<em><b>Computing Infrastructure Service</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_RUNTIME_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE = DEPLOYABLE_PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_RUNTIME_SERVICE__DESCRIPTOR = DEPLOYABLE_PLATFORM_SERVICE__DESCRIPTOR;

	/**
	 * The feature id for the '<em><b>Software Services</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_RUNTIME_SERVICE__SOFTWARE_SERVICES = DEPLOYABLE_PLATFORM_SERVICE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Deployable Runtime Service</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_RUNTIME_SERVICE_FEATURE_COUNT = DEPLOYABLE_PLATFORM_SERVICE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.DeployableSupportServiceImpl <em>Deployable Support Service</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.DeployableSupportServiceImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getDeployableSupportService()
	 * @generated
	 */
	int DEPLOYABLE_SUPPORT_SERVICE = 12;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SUPPORT_SERVICE__ID = DEPLOYABLE_PLATFORM_SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SUPPORT_SERVICE__NAME = DEPLOYABLE_PLATFORM_SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SUPPORT_SERVICE__DESCRIPTION = DEPLOYABLE_PLATFORM_SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SUPPORT_SERVICE__AV_MAP = DEPLOYABLE_PLATFORM_SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SUPPORT_SERVICE__AE_MAP = DEPLOYABLE_PLATFORM_SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Platform Layer</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SUPPORT_SERVICE__PLATFORM_LAYER = DEPLOYABLE_PLATFORM_SERVICE__PLATFORM_LAYER;

	/**
	 * The feature id for the '<em><b>Computing Infrastructure Service</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SUPPORT_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE = DEPLOYABLE_PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SUPPORT_SERVICE__DESCRIPTOR = DEPLOYABLE_PLATFORM_SERVICE__DESCRIPTOR;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SUPPORT_SERVICE__PROVIDED_INTERFACES = DEPLOYABLE_PLATFORM_SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SUPPORT_SERVICE__REQUIRED_INTERFACES = DEPLOYABLE_PLATFORM_SERVICE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Deployable Support Service</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SUPPORT_SERVICE_FEATURE_COUNT = DEPLOYABLE_PLATFORM_SERVICE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.ExternalRuntimeServiceImpl <em>External Runtime Service</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ExternalRuntimeServiceImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getExternalRuntimeService()
	 * @generated
	 */
	int EXTERNAL_RUNTIME_SERVICE = 13;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RUNTIME_SERVICE__ID = EXTERNAL_PLATFORM_SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RUNTIME_SERVICE__NAME = EXTERNAL_PLATFORM_SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RUNTIME_SERVICE__DESCRIPTION = EXTERNAL_PLATFORM_SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RUNTIME_SERVICE__AV_MAP = EXTERNAL_PLATFORM_SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RUNTIME_SERVICE__AE_MAP = EXTERNAL_PLATFORM_SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Platform Layer</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RUNTIME_SERVICE__PLATFORM_LAYER = EXTERNAL_PLATFORM_SERVICE__PLATFORM_LAYER;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RUNTIME_SERVICE__DESCRIPTOR = EXTERNAL_PLATFORM_SERVICE__DESCRIPTOR;

	/**
	 * The feature id for the '<em><b>Software Services</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RUNTIME_SERVICE__SOFTWARE_SERVICES = EXTERNAL_PLATFORM_SERVICE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>External Runtime Service</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RUNTIME_SERVICE_FEATURE_COUNT = EXTERNAL_PLATFORM_SERVICE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.ExternalSupportServiceImpl <em>External Support Service</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ExternalSupportServiceImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getExternalSupportService()
	 * @generated
	 */
	int EXTERNAL_SUPPORT_SERVICE = 14;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SUPPORT_SERVICE__ID = EXTERNAL_PLATFORM_SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SUPPORT_SERVICE__NAME = EXTERNAL_PLATFORM_SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SUPPORT_SERVICE__DESCRIPTION = EXTERNAL_PLATFORM_SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SUPPORT_SERVICE__AV_MAP = EXTERNAL_PLATFORM_SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SUPPORT_SERVICE__AE_MAP = EXTERNAL_PLATFORM_SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Platform Layer</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SUPPORT_SERVICE__PLATFORM_LAYER = EXTERNAL_PLATFORM_SERVICE__PLATFORM_LAYER;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SUPPORT_SERVICE__DESCRIPTOR = EXTERNAL_PLATFORM_SERVICE__DESCRIPTOR;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SUPPORT_SERVICE__PROVIDED_INTERFACES = EXTERNAL_PLATFORM_SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SUPPORT_SERVICE__REQUIRED_INTERFACES = EXTERNAL_PLATFORM_SERVICE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>External Support Service</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SUPPORT_SERVICE_FEATURE_COUNT = EXTERNAL_PLATFORM_SERVICE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.ExternalSoftwareServiceImpl <em>External Software Service</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ExternalSoftwareServiceImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getExternalSoftwareService()
	 * @generated
	 */
	int EXTERNAL_SOFTWARE_SERVICE = 15;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__ID = SOFTWARE_SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__NAME = SOFTWARE_SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__DESCRIPTION = SOFTWARE_SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__AV_MAP = SOFTWARE_SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__AE_MAP = SOFTWARE_SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__PROVIDED_INTERFACES = SOFTWARE_SERVICE__PROVIDED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__REQUIRED_INTERFACES = SOFTWARE_SERVICE__REQUIRED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Software Layer</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__SOFTWARE_LAYER = SOFTWARE_SERVICE__SOFTWARE_LAYER;

	/**
	 * The feature id for the '<em><b>Service Proxy</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY = SOFTWARE_SERVICE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>External Software Service</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE_FEATURE_COUNT = SOFTWARE_SERVICE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.DeployableSoftwareServiceImpl <em>Deployable Software Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.DeployableSoftwareServiceImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getDeployableSoftwareService()
	 * @generated
	 */
	int DEPLOYABLE_SOFTWARE_SERVICE = 16;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SOFTWARE_SERVICE__ID = SOFTWARE_SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SOFTWARE_SERVICE__NAME = SOFTWARE_SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SOFTWARE_SERVICE__DESCRIPTION = SOFTWARE_SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SOFTWARE_SERVICE__AV_MAP = SOFTWARE_SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SOFTWARE_SERVICE__AE_MAP = SOFTWARE_SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SOFTWARE_SERVICE__PROVIDED_INTERFACES = SOFTWARE_SERVICE__PROVIDED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SOFTWARE_SERVICE__REQUIRED_INTERFACES = SOFTWARE_SERVICE__REQUIRED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Software Layer</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SOFTWARE_SERVICE__SOFTWARE_LAYER = SOFTWARE_SERVICE__SOFTWARE_LAYER;

	/**
	 * The feature id for the '<em><b>Runtime Platform Service</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE = SOFTWARE_SERVICE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Deployable Software Service</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SOFTWARE_SERVICE_FEATURE_COUNT = SOFTWARE_SERVICE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.ComputingInfrastructureServiceImpl <em>Computing Infrastructure Service</em>}' class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ComputingInfrastructureServiceImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getComputingInfrastructureService()
	 * @generated
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE = 17;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE__ID = INFRASTRUCTURE_SERVICE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE__NAME = INFRASTRUCTURE_SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTION = INFRASTRUCTURE_SERVICE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE__AV_MAP = INFRASTRUCTURE_SERVICE__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE__AE_MAP = INFRASTRUCTURE_SERVICE__AE_MAP;

	/**
	 * The feature id for the '<em><b>Infrastructure Layer</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER = INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTOR = INFRASTRUCTURE_SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Platform Services</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer <em>Software Service Container</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getSoftwareServiceContainer()
	 * @generated
	 */
	int SOFTWARE_SERVICE_CONTAINER = 18;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_CONTAINER__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_CONTAINER__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_CONTAINER__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_CONTAINER__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_CONTAINER__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Software Services</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_CONTAINER__SOFTWARE_SERVICES = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Software Service Container</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_CONTAINER_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer <em>Operation Interface Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getOperationInterfaceContainer()
	 * @generated
	 */
	int OPERATION_INTERFACE_CONTAINER = 19;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE_CONTAINER__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE_CONTAINER__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE_CONTAINER__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE_CONTAINER__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE_CONTAINER__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Operation Interface Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INTERFACE_CONTAINER_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '
	 * {@link eu.cloudscaleproject.env.csm.architecture.impl.ConnectionImpl
	 * <em>Connection</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ConnectionImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getConnection()
	 * @generated
	 */
	int CONNECTION = 20;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONNECTION__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONNECTION__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONNECTION__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The number of structural features of the '<em>Connection</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.InternalConnectionImpl <em>Internal Connection</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.InternalConnectionImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getInternalConnection()
	 * @generated
	 */
	int INTERNAL_CONNECTION = 21;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INTERNAL_CONNECTION__ID = CONNECTION__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INTERNAL_CONNECTION__NAME = CONNECTION__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INTERNAL_CONNECTION__DESCRIPTION = CONNECTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_CONNECTION__AV_MAP = CONNECTION__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_CONNECTION__AE_MAP = CONNECTION__AE_MAP;

	/**
	 * The feature id for the '<em><b>Source Platform Service</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_CONNECTION__SOURCE_PLATFORM_SERVICE = CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Destination Platform Service</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_CONNECTION__DESTINATION_PLATFORM_SERVICE = CONNECTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Internal Connection</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.HybridConnectionImpl <em>Hybrid Connection</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.HybridConnectionImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getHybridConnection()
	 * @generated
	 */
	int HYBRID_CONNECTION = 22;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int HYBRID_CONNECTION__ID = CONNECTION__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int HYBRID_CONNECTION__NAME = CONNECTION__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int HYBRID_CONNECTION__DESCRIPTION = CONNECTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HYBRID_CONNECTION__AV_MAP = CONNECTION__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HYBRID_CONNECTION__AE_MAP = CONNECTION__AE_MAP;

	/**
	 * The feature id for the '<em><b>Source Platform Service</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HYBRID_CONNECTION__SOURCE_PLATFORM_SERVICE = CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target Platform Service</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HYBRID_CONNECTION__TARGET_PLATFORM_SERVICE = CONNECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int HYBRID_CONNECTION__DESCRIPTOR = CONNECTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Hybrid Connection</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HYBRID_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.ExternalConnectionImpl <em>External Connection</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ExternalConnectionImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getExternalConnection()
	 * @generated
	 */
	int EXTERNAL_CONNECTION = 23;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION__ID = CONNECTION__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION__NAME = CONNECTION__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION__DESCRIPTION = CONNECTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION__AV_MAP = CONNECTION__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION__AE_MAP = CONNECTION__AE_MAP;

	/**
	 * The feature id for the '<em><b>Proxy</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION__PROXY = CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Platform Service</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION__PLATFORM_SERVICE = CONNECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Outbound</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION__IS_OUTBOUND = CONNECTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION__DESCRIPTOR = CONNECTION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>External Connection</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.ProxyImpl <em>Proxy</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ProxyImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getProxy()
	 * @generated
	 */
	int PROXY = 24;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROXY__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROXY__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROXY__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROXY__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROXY__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The number of structural features of the '<em>Proxy</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROXY_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '
	 * {@link eu.cloudscaleproject.env.csm.architecture.impl.ServiceProxyImpl
	 * <em>Service Proxy</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ServiceProxyImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getServiceProxy()
	 * @generated
	 */
	int SERVICE_PROXY = 25;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROXY__ID = PROXY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROXY__NAME = PROXY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROXY__DESCRIPTION = PROXY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROXY__AV_MAP = PROXY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROXY__AE_MAP = PROXY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Software Service</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROXY__SOFTWARE_SERVICE = PROXY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Service Proxy</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROXY_FEATURE_COUNT = PROXY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '
	 * {@link eu.cloudscaleproject.env.csm.architecture.impl.UsageProxyImpl
	 * <em>Usage Proxy</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.UsageProxyImpl
	 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getUsageProxy()
	 * @generated
	 */
	int USAGE_PROXY = 26;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int USAGE_PROXY__ID = PROXY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int USAGE_PROXY__NAME = PROXY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int USAGE_PROXY__DESCRIPTION = PROXY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USAGE_PROXY__AV_MAP = PROXY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USAGE_PROXY_FEATURE_COUNT = PROXY_FEATURE_COUNT + 2;

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.Architecture <em>Architecture</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Architecture</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.Architecture
	 * @generated
	 */
	EClass getArchitecture();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.csm.architecture.Architecture#getCloudProviders <em>Cloud Providers</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cloud Providers</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.Architecture#getCloudProviders()
	 * @see #getArchitecture()
	 * @generated
	 */
	EReference getArchitecture_CloudProviders();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.csm.architecture.Architecture#getProxies <em>Proxies</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Proxies</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.Architecture#getProxies()
	 * @see #getArchitecture()
	 * @generated
	 */
	EReference getArchitecture_Proxies();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.csm.architecture.Architecture#getExternalConnections <em>External Connections</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the containment reference list '<em>External Connections</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.Architecture#getExternalConnections()
	 * @see #getArchitecture()
	 * @generated
	 */
	EReference getArchitecture_ExternalConnections();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link eu.cloudscaleproject.env.csm.architecture.Architecture#getHybridConnections
	 * <em>Hybrid Connections</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Hybrid Connections</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.Architecture#getHybridConnections()
	 * @see #getArchitecture()
	 * @generated
	 */
	EReference getArchitecture_HybridConnections();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.csm.architecture.Architecture#getUnresolvedOperationInterfaces <em>Unresolved Operation Interfaces</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Unresolved Operation Interfaces</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.Architecture#getUnresolvedOperationInterfaces()
	 * @see #getArchitecture()
	 * @generated
	 */
	EReference getArchitecture_UnresolvedOperationInterfaces();

	/**
	 * Returns the meta object for class '
	 * {@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment
	 * <em>Cloud Environment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Cloud Environment</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.CloudEnvironment
	 * @generated
	 */
	EClass getCloudEnvironment();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getInternalConnections <em>Internal Connections</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Internal Connections</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getInternalConnections()
	 * @see #getCloudEnvironment()
	 * @generated
	 */
	EReference getCloudEnvironment_InternalConnections();

	/**
	 * Returns the meta object for the reference '{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getCloudProviderDescriptor <em>Cloud Provider Descriptor</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the reference '<em>Cloud Provider Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getCloudProviderDescriptor()
	 * @see #getCloudEnvironment()
	 * @generated
	 */
	EReference getCloudEnvironment_CloudProviderDescriptor();

	/**
	 * Returns the meta object for the reference '{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getAvailabilityZoneDescriptor <em>Availability Zone Descriptor</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the reference '<em>Availability Zone Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getAvailabilityZoneDescriptor()
	 * @see #getCloudEnvironment()
	 * @generated
	 */
	EReference getCloudEnvironment_AvailabilityZoneDescriptor();

	/**
	 * Returns the meta object for the container reference '{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getArchitecture <em>Architecture</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Architecture</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getArchitecture()
	 * @see #getCloudEnvironment()
	 * @generated
	 */
	EReference getCloudEnvironment_Architecture();

	/**
	 * Returns the meta object for the containment reference '{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getInfrastructureLayer <em>Infrastructure Layer</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the containment reference '<em>Infrastructure Layer</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getInfrastructureLayer()
	 * @see #getCloudEnvironment()
	 * @generated
	 */
	EReference getCloudEnvironment_InfrastructureLayer();

	/**
	 * Returns the meta object for the containment reference '{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getPlatformLayer <em>Platform Layer</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Platform Layer</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getPlatformLayer()
	 * @see #getCloudEnvironment()
	 * @generated
	 */
	EReference getCloudEnvironment_PlatformLayer();

	/**
	 * Returns the meta object for the containment reference '{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getSoftwareLayer <em>Software Layer</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Software Layer</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.CloudEnvironment#getSoftwareLayer()
	 * @see #getCloudEnvironment()
	 * @generated
	 */
	EReference getCloudEnvironment_SoftwareLayer();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer <em>Infrastructure Layer</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>Infrastructure Layer</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer
	 * @generated
	 */
	EClass getInfrastructureLayer();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Services</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer#getServices()
	 * @see #getInfrastructureLayer()
	 * @generated
	 */
	EReference getInfrastructureLayer_Services();

	/**
	 * Returns the meta object for the container reference '{@link eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer#getCloudProvider <em>Cloud Provider</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Cloud Provider</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer#getCloudProvider()
	 * @see #getInfrastructureLayer()
	 * @generated
	 */
	EReference getInfrastructureLayer_CloudProvider();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.PlatformLayer <em>Platform Layer</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Platform Layer</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.PlatformLayer
	 * @generated
	 */
	EClass getPlatformLayer();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.csm.architecture.PlatformLayer#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Services</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.PlatformLayer#getServices()
	 * @see #getPlatformLayer()
	 * @generated
	 */
	EReference getPlatformLayer_Services();

	/**
	 * Returns the meta object for the container reference '
	 * {@link eu.cloudscaleproject.env.csm.architecture.PlatformLayer#getCloudEnvironment
	 * <em>Cloud Environment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the container reference '
	 *         <em>Cloud Environment</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.PlatformLayer#getCloudEnvironment()
	 * @see #getPlatformLayer()
	 * @generated
	 */
	EReference getPlatformLayer_CloudEnvironment();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.SoftwareLayer <em>Software Layer</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Software Layer</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.SoftwareLayer
	 * @generated
	 */
	EClass getSoftwareLayer();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.csm.architecture.SoftwareLayer#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Services</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.SoftwareLayer#getServices()
	 * @see #getSoftwareLayer()
	 * @generated
	 */
	EReference getSoftwareLayer_Services();

	/**
	 * Returns the meta object for the container reference '{@link eu.cloudscaleproject.env.csm.architecture.SoftwareLayer#getCloudProvider <em>Cloud Provider</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Cloud Provider</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.SoftwareLayer#getCloudProvider()
	 * @see #getSoftwareLayer()
	 * @generated
	 */
	EReference getSoftwareLayer_CloudProvider();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.Service <em>Service</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.Service
	 * @generated
	 */
	EClass getService();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.InfrastructureService <em>Infrastructure Service</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>Infrastructure Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.InfrastructureService
	 * @generated
	 */
	EClass getInfrastructureService();

	/**
	 * Returns the meta object for the container reference '{@link eu.cloudscaleproject.env.csm.architecture.InfrastructureService#getInfrastructureLayer <em>Infrastructure Layer</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the container reference '<em>Infrastructure Layer</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.InfrastructureService#getInfrastructureLayer()
	 * @see #getInfrastructureService()
	 * @generated
	 */
	EReference getInfrastructureService_InfrastructureLayer();

	/**
	 * Returns the meta object for class '
	 * {@link eu.cloudscaleproject.env.csm.architecture.PlatformService
	 * <em>Platform Service</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Platform Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.PlatformService
	 * @generated
	 */
	EClass getPlatformService();

	/**
	 * Returns the meta object for the container reference '{@link eu.cloudscaleproject.env.csm.architecture.PlatformService#getPlatformLayer <em>Platform Layer</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Platform Layer</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.PlatformService#getPlatformLayer()
	 * @see #getPlatformService()
	 * @generated
	 */
	EReference getPlatformService_PlatformLayer();

	/**
	 * Returns the meta object for class '
	 * {@link eu.cloudscaleproject.env.csm.architecture.SoftwareService
	 * <em>Software Service</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Software Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.SoftwareService
	 * @generated
	 */
	EClass getSoftwareService();

	/**
	 * Returns the meta object for the container reference '{@link eu.cloudscaleproject.env.csm.architecture.SoftwareService#getSoftwareLayer <em>Software Layer</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Software Layer</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.SoftwareService#getSoftwareLayer()
	 * @see #getSoftwareService()
	 * @generated
	 */
	EReference getSoftwareService_SoftwareLayer();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService <em>Deployable Platform Service</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>Deployable Platform Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService
	 * @generated
	 */
	EClass getDeployablePlatformService();

	/**
	 * Returns the meta object for the reference '{@link eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService#getComputingInfrastructureService <em>Computing Infrastructure Service</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the reference '<em>Computing Infrastructure Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService#getComputingInfrastructureService()
	 * @see #getDeployablePlatformService()
	 * @generated
	 */
	EReference getDeployablePlatformService_ComputingInfrastructureService();

	/**
	 * Returns the meta object for the reference '{@link eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService#getDescriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService#getDescriptor()
	 * @see #getDeployablePlatformService()
	 * @generated
	 */
	EReference getDeployablePlatformService_Descriptor();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.ExternalPlatformService <em>External Platform Service</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>External Platform Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.ExternalPlatformService
	 * @generated
	 */
	EClass getExternalPlatformService();

	/**
	 * Returns the meta object for the reference '{@link eu.cloudscaleproject.env.csm.architecture.ExternalPlatformService#getDescriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.ExternalPlatformService#getDescriptor()
	 * @see #getExternalPlatformService()
	 * @generated
	 */
	EReference getExternalPlatformService_Descriptor();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.DeployableRuntimeService <em>Deployable Runtime Service</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>Deployable Runtime Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.DeployableRuntimeService
	 * @generated
	 */
	EClass getDeployableRuntimeService();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.DeployableSupportService <em>Deployable Support Service</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>Deployable Support Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.DeployableSupportService
	 * @generated
	 */
	EClass getDeployableSupportService();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.ExternalRuntimeService <em>External Runtime Service</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>External Runtime Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.ExternalRuntimeService
	 * @generated
	 */
	EClass getExternalRuntimeService();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.ExternalSupportService <em>External Support Service</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>External Support Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.ExternalSupportService
	 * @generated
	 */
	EClass getExternalSupportService();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.ExternalSoftwareService <em>External Software Service</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>External Software Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.ExternalSoftwareService
	 * @generated
	 */
	EClass getExternalSoftwareService();

	/**
	 * Returns the meta object for the container reference '{@link eu.cloudscaleproject.env.csm.architecture.ExternalSoftwareService#getServiceProxy <em>Service Proxy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Service Proxy</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.ExternalSoftwareService#getServiceProxy()
	 * @see #getExternalSoftwareService()
	 * @generated
	 */
	EReference getExternalSoftwareService_ServiceProxy();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService <em>Deployable Software Service</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>Deployable Software Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService
	 * @generated
	 */
	EClass getDeployableSoftwareService();

	/**
	 * Returns the meta object for the reference '{@link eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService#getRuntimePlatformService <em>Runtime Platform Service</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the reference '<em>Runtime Platform Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService#getRuntimePlatformService()
	 * @see #getDeployableSoftwareService()
	 * @generated
	 */
	EReference getDeployableSoftwareService_RuntimePlatformService();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService <em>Computing Infrastructure Service</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>Computing Infrastructure Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService
	 * @generated
	 */
	EClass getComputingInfrastructureService();

	/**
	 * Returns the meta object for the reference '{@link eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService#getDescriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService#getDescriptor()
	 * @see #getComputingInfrastructureService()
	 * @generated
	 */
	EReference getComputingInfrastructureService_Descriptor();

	/**
	 * Returns the meta object for the reference list '
	 * {@link eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService#getPlatformServices
	 * <em>Platform Services</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the reference list '
	 *         <em>Platform Services</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService#getPlatformServices()
	 * @see #getComputingInfrastructureService()
	 * @generated
	 */
	EReference getComputingInfrastructureService_PlatformServices();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer <em>Software Service Container</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>Software Service Container</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer
	 * @generated
	 */
	EClass getSoftwareServiceContainer();

	/**
	 * Returns the meta object for the reference list '
	 * {@link eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer#getSoftwareServices
	 * <em>Software Services</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the reference list '
	 *         <em>Software Services</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer#getSoftwareServices()
	 * @see #getSoftwareServiceContainer()
	 * @generated
	 */
	EReference getSoftwareServiceContainer_SoftwareServices();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer <em>Operation Interface Container</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>Operation Interface Container</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer
	 * @generated
	 */
	EClass getOperationInterfaceContainer();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer#getProvidedInterfaces
	 * <em>Provided Interfaces</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Provided Interfaces</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer#getProvidedInterfaces()
	 * @see #getOperationInterfaceContainer()
	 * @generated
	 */
	EReference getOperationInterfaceContainer_ProvidedInterfaces();

	/**
	 * Returns the meta object for the reference list '
	 * {@link eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer#getRequiredInterfaces
	 * <em>Required Interfaces</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the reference list '
	 *         <em>Required Interfaces</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer#getRequiredInterfaces()
	 * @see #getOperationInterfaceContainer()
	 * @generated
	 */
	EReference getOperationInterfaceContainer_RequiredInterfaces();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.Connection
	 * @generated
	 */
	EClass getConnection();

	/**
	 * Returns the meta object for class '
	 * {@link eu.cloudscaleproject.env.csm.architecture.InternalConnection
	 * <em>Internal Connection</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Internal Connection</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.InternalConnection
	 * @generated
	 */
	EClass getInternalConnection();

	/**
	 * Returns the meta object for the reference '{@link eu.cloudscaleproject.env.csm.architecture.InternalConnection#getSourcePlatformService <em>Source Platform Service</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the reference '<em>Source Platform Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.InternalConnection#getSourcePlatformService()
	 * @see #getInternalConnection()
	 * @generated
	 */
	EReference getInternalConnection_SourcePlatformService();

	/**
	 * Returns the meta object for the reference '{@link eu.cloudscaleproject.env.csm.architecture.InternalConnection#getDestinationPlatformService <em>Destination Platform Service</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the reference '<em>Destination Platform Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.InternalConnection#getDestinationPlatformService()
	 * @see #getInternalConnection()
	 * @generated
	 */
	EReference getInternalConnection_DestinationPlatformService();

	/**
	 * Returns the meta object for class '
	 * {@link eu.cloudscaleproject.env.csm.architecture.HybridConnection
	 * <em>Hybrid Connection</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Hybrid Connection</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.HybridConnection
	 * @generated
	 */
	EClass getHybridConnection();

	/**
	 * Returns the meta object for the reference '{@link eu.cloudscaleproject.env.csm.architecture.HybridConnection#getSourcePlatformService <em>Source Platform Service</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the reference '<em>Source Platform Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.HybridConnection#getSourcePlatformService()
	 * @see #getHybridConnection()
	 * @generated
	 */
	EReference getHybridConnection_SourcePlatformService();

	/**
	 * Returns the meta object for the reference '{@link eu.cloudscaleproject.env.csm.architecture.HybridConnection#getTargetPlatformService <em>Target Platform Service</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the reference '<em>Target Platform Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.HybridConnection#getTargetPlatformService()
	 * @see #getHybridConnection()
	 * @generated
	 */
	EReference getHybridConnection_TargetPlatformService();

	/**
	 * Returns the meta object for the reference '{@link eu.cloudscaleproject.env.csm.architecture.HybridConnection#getDescriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.HybridConnection#getDescriptor()
	 * @see #getHybridConnection()
	 * @generated
	 */
	EReference getHybridConnection_Descriptor();

	/**
	 * Returns the meta object for class '
	 * {@link eu.cloudscaleproject.env.csm.architecture.ExternalConnection
	 * <em>External Connection</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>External Connection</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.ExternalConnection
	 * @generated
	 */
	EClass getExternalConnection();

	/**
	 * Returns the meta object for the reference '{@link eu.cloudscaleproject.env.csm.architecture.ExternalConnection#getProxy <em>Proxy</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Proxy</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.ExternalConnection#getProxy()
	 * @see #getExternalConnection()
	 * @generated
	 */
	EReference getExternalConnection_Proxy();

	/**
	 * Returns the meta object for the reference '
	 * {@link eu.cloudscaleproject.env.csm.architecture.ExternalConnection#getPlatformService
	 * <em>Platform Service</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the reference '<em>Platform Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.ExternalConnection#getPlatformService()
	 * @see #getExternalConnection()
	 * @generated
	 */
	EReference getExternalConnection_PlatformService();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.csm.architecture.ExternalConnection#getIsOutbound <em>Is Outbound</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Outbound</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.ExternalConnection#getIsOutbound()
	 * @see #getExternalConnection()
	 * @generated
	 */
	EAttribute getExternalConnection_IsOutbound();

	/**
	 * Returns the meta object for the reference '{@link eu.cloudscaleproject.env.csm.architecture.ExternalConnection#getDescriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.ExternalConnection#getDescriptor()
	 * @see #getExternalConnection()
	 * @generated
	 */
	EReference getExternalConnection_Descriptor();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.Proxy <em>Proxy</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Proxy</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.Proxy
	 * @generated
	 */
	EClass getProxy();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.ServiceProxy <em>Service Proxy</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Proxy</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.ServiceProxy
	 * @generated
	 */
	EClass getServiceProxy();

	/**
	 * Returns the meta object for the reference '
	 * {@link eu.cloudscaleproject.env.csm.architecture.ServiceProxy#getSoftwareService
	 * <em>Software Service</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the reference '<em>Software Service</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.ServiceProxy#getSoftwareService()
	 * @see #getServiceProxy()
	 * @generated
	 */
	EReference getServiceProxy_SoftwareService();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.architecture.UsageProxy <em>Usage Proxy</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Usage Proxy</em>'.
	 * @see eu.cloudscaleproject.env.csm.architecture.UsageProxy
	 * @generated
	 */
	EClass getUsageProxy();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ArchitectureFactory getArchitectureFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that
	 * represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureImpl <em>Architecture</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getArchitecture()
		 * @generated
		 */
		EClass ARCHITECTURE = eINSTANCE.getArchitecture();

		/**
		 * The meta object literal for the '<em><b>Cloud Providers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference ARCHITECTURE__CLOUD_PROVIDERS = eINSTANCE.getArchitecture_CloudProviders();

		/**
		 * The meta object literal for the '<em><b>Proxies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHITECTURE__PROXIES = eINSTANCE.getArchitecture_Proxies();

		/**
		 * The meta object literal for the '<em><b>External Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference ARCHITECTURE__EXTERNAL_CONNECTIONS = eINSTANCE.getArchitecture_ExternalConnections();

		/**
		 * The meta object literal for the '<em><b>Hybrid Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference ARCHITECTURE__HYBRID_CONNECTIONS = eINSTANCE.getArchitecture_HybridConnections();

		/**
		 * The meta object literal for the '<em><b>Unresolved Operation Interfaces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHITECTURE__UNRESOLVED_OPERATION_INTERFACES = eINSTANCE.getArchitecture_UnresolvedOperationInterfaces();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.CloudEnvironmentImpl <em>Cloud Environment</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.CloudEnvironmentImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getCloudEnvironment()
		 * @generated
		 */
		EClass CLOUD_ENVIRONMENT = eINSTANCE.getCloudEnvironment();

		/**
		 * The meta object literal for the '<em><b>Internal Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_ENVIRONMENT__INTERNAL_CONNECTIONS = eINSTANCE.getCloudEnvironment_InternalConnections();

		/**
		 * The meta object literal for the '
		 * <em><b>Cloud Provider Descriptor</b></em>' reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CLOUD_ENVIRONMENT__CLOUD_PROVIDER_DESCRIPTOR = eINSTANCE.getCloudEnvironment_CloudProviderDescriptor();

		/**
		 * The meta object literal for the '
		 * <em><b>Availability Zone Descriptor</b></em>' reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CLOUD_ENVIRONMENT__AVAILABILITY_ZONE_DESCRIPTOR = eINSTANCE.getCloudEnvironment_AvailabilityZoneDescriptor();

		/**
		 * The meta object literal for the '<em><b>Architecture</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_ENVIRONMENT__ARCHITECTURE = eINSTANCE.getCloudEnvironment_Architecture();

		/**
		 * The meta object literal for the '<em><b>Infrastructure Layer</b></em>' containment reference feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER = eINSTANCE.getCloudEnvironment_InfrastructureLayer();

		/**
		 * The meta object literal for the '<em><b>Platform Layer</b></em>' containment reference feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_ENVIRONMENT__PLATFORM_LAYER = eINSTANCE.getCloudEnvironment_PlatformLayer();

		/**
		 * The meta object literal for the '<em><b>Software Layer</b></em>' containment reference feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_ENVIRONMENT__SOFTWARE_LAYER = eINSTANCE.getCloudEnvironment_SoftwareLayer();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.InfrastructureLayerImpl <em>Infrastructure Layer</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.InfrastructureLayerImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getInfrastructureLayer()
		 * @generated
		 */
		EClass INFRASTRUCTURE_LAYER = eINSTANCE.getInfrastructureLayer();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference INFRASTRUCTURE_LAYER__SERVICES = eINSTANCE.getInfrastructureLayer_Services();

		/**
		 * The meta object literal for the '<em><b>Cloud Provider</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference INFRASTRUCTURE_LAYER__CLOUD_PROVIDER = eINSTANCE.getInfrastructureLayer_CloudProvider();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.PlatformLayerImpl <em>Platform Layer</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.PlatformLayerImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getPlatformLayer()
		 * @generated
		 */
		EClass PLATFORM_LAYER = eINSTANCE.getPlatformLayer();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference PLATFORM_LAYER__SERVICES = eINSTANCE.getPlatformLayer_Services();

		/**
		 * The meta object literal for the '<em><b>Cloud Environment</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference PLATFORM_LAYER__CLOUD_ENVIRONMENT = eINSTANCE.getPlatformLayer_CloudEnvironment();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.SoftwareLayerImpl <em>Software Layer</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.SoftwareLayerImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getSoftwareLayer()
		 * @generated
		 */
		EClass SOFTWARE_LAYER = eINSTANCE.getSoftwareLayer();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference SOFTWARE_LAYER__SERVICES = eINSTANCE.getSoftwareLayer_Services();

		/**
		 * The meta object literal for the '<em><b>Cloud Provider</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference SOFTWARE_LAYER__CLOUD_PROVIDER = eINSTANCE.getSoftwareLayer_CloudProvider();

		/**
		 * The meta object literal for the '
		 * {@link eu.cloudscaleproject.env.csm.architecture.impl.ServiceImpl
		 * <em>Service</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ServiceImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getService()
		 * @generated
		 */
		EClass SERVICE = eINSTANCE.getService();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.InfrastructureServiceImpl <em>Infrastructure Service</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.InfrastructureServiceImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getInfrastructureService()
		 * @generated
		 */
		EClass INFRASTRUCTURE_SERVICE = eINSTANCE.getInfrastructureService();

		/**
		 * The meta object literal for the '<em><b>Infrastructure Layer</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER = eINSTANCE.getInfrastructureService_InfrastructureLayer();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.PlatformServiceImpl <em>Platform Service</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.PlatformServiceImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getPlatformService()
		 * @generated
		 */
		EClass PLATFORM_SERVICE = eINSTANCE.getPlatformService();

		/**
		 * The meta object literal for the '<em><b>Platform Layer</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference PLATFORM_SERVICE__PLATFORM_LAYER = eINSTANCE.getPlatformService_PlatformLayer();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.SoftwareServiceImpl <em>Software Service</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.SoftwareServiceImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getSoftwareService()
		 * @generated
		 */
		EClass SOFTWARE_SERVICE = eINSTANCE.getSoftwareService();

		/**
		 * The meta object literal for the '<em><b>Software Layer</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference SOFTWARE_SERVICE__SOFTWARE_LAYER = eINSTANCE.getSoftwareService_SoftwareLayer();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.DeployablePlatformServiceImpl <em>Deployable Platform Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.DeployablePlatformServiceImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getDeployablePlatformService()
		 * @generated
		 */
		EClass DEPLOYABLE_PLATFORM_SERVICE = eINSTANCE.getDeployablePlatformService();

		/**
		 * The meta object literal for the '<em><b>Computing Infrastructure Service</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYABLE_PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE = eINSTANCE.getDeployablePlatformService_ComputingInfrastructureService();

		/**
		 * The meta object literal for the '<em><b>Descriptor</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYABLE_PLATFORM_SERVICE__DESCRIPTOR = eINSTANCE.getDeployablePlatformService_Descriptor();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.ExternalPlatformServiceImpl <em>External Platform Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ExternalPlatformServiceImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getExternalPlatformService()
		 * @generated
		 */
		EClass EXTERNAL_PLATFORM_SERVICE = eINSTANCE.getExternalPlatformService();

		/**
		 * The meta object literal for the '<em><b>Descriptor</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERNAL_PLATFORM_SERVICE__DESCRIPTOR = eINSTANCE.getExternalPlatformService_Descriptor();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.DeployableRuntimeServiceImpl <em>Deployable Runtime Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.DeployableRuntimeServiceImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getDeployableRuntimeService()
		 * @generated
		 */
		EClass DEPLOYABLE_RUNTIME_SERVICE = eINSTANCE.getDeployableRuntimeService();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.DeployableSupportServiceImpl <em>Deployable Support Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.DeployableSupportServiceImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getDeployableSupportService()
		 * @generated
		 */
		EClass DEPLOYABLE_SUPPORT_SERVICE = eINSTANCE.getDeployableSupportService();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.ExternalRuntimeServiceImpl <em>External Runtime Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ExternalRuntimeServiceImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getExternalRuntimeService()
		 * @generated
		 */
		EClass EXTERNAL_RUNTIME_SERVICE = eINSTANCE.getExternalRuntimeService();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.ExternalSupportServiceImpl <em>External Support Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ExternalSupportServiceImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getExternalSupportService()
		 * @generated
		 */
		EClass EXTERNAL_SUPPORT_SERVICE = eINSTANCE.getExternalSupportService();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.ExternalSoftwareServiceImpl <em>External Software Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ExternalSoftwareServiceImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getExternalSoftwareService()
		 * @generated
		 */
		EClass EXTERNAL_SOFTWARE_SERVICE = eINSTANCE.getExternalSoftwareService();

		/**
		 * The meta object literal for the '<em><b>Service Proxy</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY = eINSTANCE.getExternalSoftwareService_ServiceProxy();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.DeployableSoftwareServiceImpl <em>Deployable Software Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.DeployableSoftwareServiceImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getDeployableSoftwareService()
		 * @generated
		 */
		EClass DEPLOYABLE_SOFTWARE_SERVICE = eINSTANCE.getDeployableSoftwareService();

		/**
		 * The meta object literal for the '
		 * <em><b>Runtime Platform Service</b></em>' reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DEPLOYABLE_SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE = eINSTANCE.getDeployableSoftwareService_RuntimePlatformService();

		/**
		 * The meta object literal for the '
		 * {@link eu.cloudscaleproject.env.csm.architecture.impl.ComputingInfrastructureServiceImpl
		 * <em>Computing Infrastructure Service</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ComputingInfrastructureServiceImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getComputingInfrastructureService()
		 * @generated
		 */
		EClass COMPUTING_INFRASTRUCTURE_SERVICE = eINSTANCE.getComputingInfrastructureService();

		/**
		 * The meta object literal for the '<em><b>Descriptor</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTOR = eINSTANCE.getComputingInfrastructureService_Descriptor();

		/**
		 * The meta object literal for the '<em><b>Platform Services</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPUTING_INFRASTRUCTURE_SERVICE__PLATFORM_SERVICES = eINSTANCE.getComputingInfrastructureService_PlatformServices();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer <em>Software Service Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getSoftwareServiceContainer()
		 * @generated
		 */
		EClass SOFTWARE_SERVICE_CONTAINER = eINSTANCE.getSoftwareServiceContainer();

		/**
		 * The meta object literal for the '<em><b>Software Services</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOFTWARE_SERVICE_CONTAINER__SOFTWARE_SERVICES = eINSTANCE.getSoftwareServiceContainer_SoftwareServices();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer <em>Operation Interface Container</em>}' class.
		 * <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getOperationInterfaceContainer()
		 * @generated
		 */
		EClass OPERATION_INTERFACE_CONTAINER = eINSTANCE.getOperationInterfaceContainer();

		/**
		 * The meta object literal for the '<em><b>Provided Interfaces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES = eINSTANCE.getOperationInterfaceContainer_ProvidedInterfaces();

		/**
		 * The meta object literal for the '<em><b>Required Interfaces</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES = eINSTANCE.getOperationInterfaceContainer_RequiredInterfaces();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.ConnectionImpl <em>Connection</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ConnectionImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getConnection()
		 * @generated
		 */
		EClass CONNECTION = eINSTANCE.getConnection();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.InternalConnectionImpl <em>Internal Connection</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.InternalConnectionImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getInternalConnection()
		 * @generated
		 */
		EClass INTERNAL_CONNECTION = eINSTANCE.getInternalConnection();

		/**
		 * The meta object literal for the '
		 * <em><b>Source Platform Service</b></em>' reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INTERNAL_CONNECTION__SOURCE_PLATFORM_SERVICE = eINSTANCE.getInternalConnection_SourcePlatformService();

		/**
		 * The meta object literal for the '
		 * <em><b>Destination Platform Service</b></em>' reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INTERNAL_CONNECTION__DESTINATION_PLATFORM_SERVICE = eINSTANCE.getInternalConnection_DestinationPlatformService();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.HybridConnectionImpl <em>Hybrid Connection</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.HybridConnectionImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getHybridConnection()
		 * @generated
		 */
		EClass HYBRID_CONNECTION = eINSTANCE.getHybridConnection();

		/**
		 * The meta object literal for the '
		 * <em><b>Source Platform Service</b></em>' reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference HYBRID_CONNECTION__SOURCE_PLATFORM_SERVICE = eINSTANCE.getHybridConnection_SourcePlatformService();

		/**
		 * The meta object literal for the '
		 * <em><b>Target Platform Service</b></em>' reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference HYBRID_CONNECTION__TARGET_PLATFORM_SERVICE = eINSTANCE.getHybridConnection_TargetPlatformService();

		/**
		 * The meta object literal for the '<em><b>Descriptor</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference HYBRID_CONNECTION__DESCRIPTOR = eINSTANCE.getHybridConnection_Descriptor();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.ExternalConnectionImpl <em>External Connection</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ExternalConnectionImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getExternalConnection()
		 * @generated
		 */
		EClass EXTERNAL_CONNECTION = eINSTANCE.getExternalConnection();

		/**
		 * The meta object literal for the '<em><b>Proxy</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERNAL_CONNECTION__PROXY = eINSTANCE.getExternalConnection_Proxy();

		/**
		 * The meta object literal for the '<em><b>Platform Service</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERNAL_CONNECTION__PLATFORM_SERVICE = eINSTANCE.getExternalConnection_PlatformService();

		/**
		 * The meta object literal for the '<em><b>Is Outbound</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_CONNECTION__IS_OUTBOUND = eINSTANCE.getExternalConnection_IsOutbound();

		/**
		 * The meta object literal for the '<em><b>Descriptor</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERNAL_CONNECTION__DESCRIPTOR = eINSTANCE.getExternalConnection_Descriptor();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.ProxyImpl <em>Proxy</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ProxyImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getProxy()
		 * @generated
		 */
		EClass PROXY = eINSTANCE.getProxy();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.ServiceProxyImpl <em>Service Proxy</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ServiceProxyImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getServiceProxy()
		 * @generated
		 */
		EClass SERVICE_PROXY = eINSTANCE.getServiceProxy();

		/**
		 * The meta object literal for the '<em><b>Software Service</b></em>' containment reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE_PROXY__SOFTWARE_SERVICE = eINSTANCE.getServiceProxy_SoftwareService();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.architecture.impl.UsageProxyImpl <em>Usage Proxy</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.UsageProxyImpl
		 * @see eu.cloudscaleproject.env.csm.architecture.impl.ArchitecturePackageImpl#getUsageProxy()
		 * @generated
		 */
		EClass USAGE_PROXY = eINSTANCE.getUsageProxy();

	}

} // ArchitecturePackage
