/**
 */
package eu.cloudscaleproject.env.csm.definition;

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
 * <!-- end-user-doc --> <!-- begin-model-doc --> This package defines the
 * descriptors, which are referenced by classes in Architecture package, and
 * provides exact specification to cloud system components. Additionally package
 * defines also two definition classes (i.e. CloudDefinition and
 * SoftwareDefinition) that are used as descriptor containers, whose purpose is
 * to extend available specifications.
 * 
 * <!-- end-model-doc -->
 * 
 * @see eu.cloudscaleproject.env.csm.definition.DefinitionFactory
 * @model kind="package"
 * @generated
 */
public interface DefinitionPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "definition";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://eu.cloudscaleproject.env/CloudscaleComponentModel/Definition/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "definition";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	DefinitionPackage eINSTANCE = eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl.init();

	/**
	 * The meta object id for the '
	 * {@link eu.cloudscaleproject.env.csm.definition.impl.DefinitionImpl
	 * <em>Definition</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionImpl
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getDefinition()
	 * @generated
	 */
	int DEFINITION = 0;

	/**
	 * The number of structural features of the '<em>Definition</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.definition.impl.SystemDefinitionImpl <em>System Definition</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.definition.impl.SystemDefinitionImpl
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getSystemDefinition()
	 * @generated
	 */
	int SYSTEM_DEFINITION = 1;

	/**
	 * The feature id for the '<em><b>Descriptors</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_DEFINITION__DESCRIPTORS = DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>System Definition</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.definition.impl.CloudDefinitionImpl <em>Cloud Definition</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.definition.impl.CloudDefinitionImpl
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getCloudDefinition()
	 * @generated
	 */
	int CLOUD_DEFINITION = 2;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_DEFINITION__DESCRIPTOR = DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Infrastructure Descriptors</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_DEFINITION__INFRASTRUCTURE_DESCRIPTORS = DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Platform Descriptors</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_DEFINITION__PLATFORM_DESCRIPTORS = DEFINITION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Cloud Definition</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.definition.impl.SoftwareDefinitionImpl <em>Software Definition</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.definition.impl.SoftwareDefinitionImpl
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getSoftwareDefinition()
	 * @generated
	 */
	int SOFTWARE_DEFINITION = 3;

	/**
	 * The feature id for the '<em><b>Deployable Platform Service Descriptors</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_DEFINITION__DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTORS = DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Software Definition</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '
	 * {@link eu.cloudscaleproject.env.csm.definition.impl.DescriptorImpl
	 * <em>Descriptor</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DescriptorImpl
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getDescriptor()
	 * @generated
	 */
	int DESCRIPTOR = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__PROVIDER_ID = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Descriptor</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.definition.impl.CloudEnvironmentDescriptorImpl <em>Cloud Environment Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.definition.impl.CloudEnvironmentDescriptorImpl
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getCloudEnvironmentDescriptor()
	 * @generated
	 */
	int CLOUD_ENVIRONMENT_DESCRIPTOR = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT_DESCRIPTOR__ID = DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT_DESCRIPTOR__NAME = DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT_DESCRIPTOR__DESCRIPTION = DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT_DESCRIPTOR__AV_MAP = DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT_DESCRIPTOR__AE_MAP = DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT_DESCRIPTOR__PROVIDER_ID = DESCRIPTOR__PROVIDER_ID;

	/**
	 * The feature id for the '<em><b>Availability Zones</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES = DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Regions</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS = DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Cloud Environment Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT_DESCRIPTOR_FEATURE_COUNT = DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.definition.impl.RegionDescriptorImpl <em>Region Descriptor</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.definition.impl.RegionDescriptorImpl
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getRegionDescriptor()
	 * @generated
	 */
	int REGION_DESCRIPTOR = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REGION_DESCRIPTOR__ID = DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REGION_DESCRIPTOR__NAME = DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REGION_DESCRIPTOR__DESCRIPTION = DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_DESCRIPTOR__AV_MAP = DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_DESCRIPTOR__AE_MAP = DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REGION_DESCRIPTOR__PROVIDER_ID = DESCRIPTOR__PROVIDER_ID;

	/**
	 * The number of structural features of the '<em>Region Descriptor</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_DESCRIPTOR_FEATURE_COUNT = DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.definition.impl.AvailabilityZoneDescriptorImpl <em>Availability Zone Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.definition.impl.AvailabilityZoneDescriptorImpl
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getAvailabilityZoneDescriptor()
	 * @generated
	 */
	int AVAILABILITY_ZONE_DESCRIPTOR = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_ZONE_DESCRIPTOR__ID = DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_ZONE_DESCRIPTOR__NAME = DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_ZONE_DESCRIPTOR__DESCRIPTION = DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_ZONE_DESCRIPTOR__AV_MAP = DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_ZONE_DESCRIPTOR__AE_MAP = DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_ZONE_DESCRIPTOR__PROVIDER_ID = DESCRIPTOR__PROVIDER_ID;

	/**
	 * The feature id for the '<em><b>Internal Connection Descriptor</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_ZONE_DESCRIPTOR__INTERNAL_CONNECTION_DESCRIPTOR = DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Availability Zone Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_ZONE_DESCRIPTOR_FEATURE_COUNT = DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.definition.impl.InfrastructureServiceDescriptorImpl <em>Infrastructure Service Descriptor</em>}' class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.definition.impl.InfrastructureServiceDescriptorImpl
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getInfrastructureServiceDescriptor()
	 * @generated
	 */
	int INFRASTRUCTURE_SERVICE_DESCRIPTOR = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE_DESCRIPTOR__ID = DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE_DESCRIPTOR__NAME = DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE_DESCRIPTOR__DESCRIPTION = DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE_DESCRIPTOR__AV_MAP = DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE_DESCRIPTOR__AE_MAP = DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID = DESCRIPTOR__PROVIDER_ID;

	/**
	 * The number of structural features of the '<em>Infrastructure Service Descriptor</em>' class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT = DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '
	 * {@link eu.cloudscaleproject.env.csm.definition.impl.NetworkInfrastructureServiceDescriptorImpl
	 * <em>Network Infrastructure Service Descriptor</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eu.cloudscaleproject.env.csm.definition.impl.NetworkInfrastructureServiceDescriptorImpl
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getNetworkInfrastructureServiceDescriptor()
	 * @generated
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR = 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__ID = INFRASTRUCTURE_SERVICE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__NAME = INFRASTRUCTURE_SERVICE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__DESCRIPTION = INFRASTRUCTURE_SERVICE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__AV_MAP = INFRASTRUCTURE_SERVICE_DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__AE_MAP = INFRASTRUCTURE_SERVICE_DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID = INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID;

	/**
	 * The feature id for the '<em><b>Bandwidth</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH = INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Latency</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY = INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '
	 * <em>Network Infrastructure Service Descriptor</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT = INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '
	 * {@link eu.cloudscaleproject.env.csm.definition.impl.ComputingInfrastructureServiceDescriptorImpl
	 * <em>Computing Infrastructure Service Descriptor</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eu.cloudscaleproject.env.csm.definition.impl.ComputingInfrastructureServiceDescriptorImpl
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getComputingInfrastructureServiceDescriptor()
	 * @generated
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR = 10;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__ID = INFRASTRUCTURE_SERVICE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__NAME = INFRASTRUCTURE_SERVICE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__DESCRIPTION = INFRASTRUCTURE_SERVICE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__AV_MAP = INFRASTRUCTURE_SERVICE_DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__AE_MAP = INFRASTRUCTURE_SERVICE_DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID = INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID;

	/**
	 * The feature id for the '<em><b>Memory</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__MEMORY = INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cpu</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU = INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Cpu Units</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU_UNITS = INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Storage</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__STORAGE = INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '
	 * <em>Computing Infrastructure Service Descriptor</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT = INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '
	 * {@link eu.cloudscaleproject.env.csm.definition.DeployablePlatformServiceDescriptor
	 * <em>Deployable Platform Service Descriptor</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eu.cloudscaleproject.env.csm.definition.DeployablePlatformServiceDescriptor
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getDeployablePlatformServiceDescriptor()
	 * @generated
	 */
	int DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR = 11;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR__ID = DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR__NAME = DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR__DESCRIPTION = DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR__AV_MAP = DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR__AE_MAP = DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR__PROVIDER_ID = DESCRIPTOR__PROVIDER_ID;

	/**
	 * The number of structural features of the '
	 * <em>Deployable Platform Service Descriptor</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR_FEATURE_COUNT = DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '
	 * {@link eu.cloudscaleproject.env.csm.definition.ExternalPlatformServiceDescriptor
	 * <em>External Platform Service Descriptor</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eu.cloudscaleproject.env.csm.definition.ExternalPlatformServiceDescriptor
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getExternalPlatformServiceDescriptor()
	 * @generated
	 */
	int EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR = 12;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR__ID = DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR__NAME = DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR__DESCRIPTION = DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR__AV_MAP = DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR__AE_MAP = DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR__PROVIDER_ID = DESCRIPTOR__PROVIDER_ID;

	/**
	 * The number of structural features of the '<em>External Platform Service Descriptor</em>' class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR_FEATURE_COUNT = DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '
	 * {@link eu.cloudscaleproject.env.csm.definition.impl.DeployableRuntimeServiceDescriptorImpl
	 * <em>Deployable Runtime Service Descriptor</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DeployableRuntimeServiceDescriptorImpl
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getDeployableRuntimeServiceDescriptor()
	 * @generated
	 */
	int DEPLOYABLE_RUNTIME_SERVICE_DESCRIPTOR = 13;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_RUNTIME_SERVICE_DESCRIPTOR__ID = DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_RUNTIME_SERVICE_DESCRIPTOR__NAME = DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_RUNTIME_SERVICE_DESCRIPTOR__DESCRIPTION = DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_RUNTIME_SERVICE_DESCRIPTOR__AV_MAP = DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_RUNTIME_SERVICE_DESCRIPTOR__AE_MAP = DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_RUNTIME_SERVICE_DESCRIPTOR__PROVIDER_ID = DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR__PROVIDER_ID;

	/**
	 * The number of structural features of the '
	 * <em>Deployable Runtime Service Descriptor</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_RUNTIME_SERVICE_DESCRIPTOR_FEATURE_COUNT = DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.definition.impl.ExternalRuntimeServiceDescriptorImpl <em>External Runtime Service Descriptor</em>}' class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.definition.impl.ExternalRuntimeServiceDescriptorImpl
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getExternalRuntimeServiceDescriptor()
	 * @generated
	 */
	int EXTERNAL_RUNTIME_SERVICE_DESCRIPTOR = 14;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RUNTIME_SERVICE_DESCRIPTOR__ID = EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RUNTIME_SERVICE_DESCRIPTOR__NAME = EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RUNTIME_SERVICE_DESCRIPTOR__DESCRIPTION = EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RUNTIME_SERVICE_DESCRIPTOR__AV_MAP = EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RUNTIME_SERVICE_DESCRIPTOR__AE_MAP = EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RUNTIME_SERVICE_DESCRIPTOR__PROVIDER_ID = EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR__PROVIDER_ID;

	/**
	 * The number of structural features of the '<em>External Runtime Service Descriptor</em>' class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RUNTIME_SERVICE_DESCRIPTOR_FEATURE_COUNT = EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '
	 * {@link eu.cloudscaleproject.env.csm.definition.impl.DeployableSupportServiceDescriptorImpl
	 * <em>Deployable Support Service Descriptor</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DeployableSupportServiceDescriptorImpl
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getDeployableSupportServiceDescriptor()
	 * @generated
	 */
	int DEPLOYABLE_SUPPORT_SERVICE_DESCRIPTOR = 15;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SUPPORT_SERVICE_DESCRIPTOR__ID = DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SUPPORT_SERVICE_DESCRIPTOR__NAME = DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SUPPORT_SERVICE_DESCRIPTOR__DESCRIPTION = DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SUPPORT_SERVICE_DESCRIPTOR__AV_MAP = DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SUPPORT_SERVICE_DESCRIPTOR__AE_MAP = DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SUPPORT_SERVICE_DESCRIPTOR__PROVIDER_ID = DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR__PROVIDER_ID;

	/**
	 * The number of structural features of the '
	 * <em>Deployable Support Service Descriptor</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPLOYABLE_SUPPORT_SERVICE_DESCRIPTOR_FEATURE_COUNT = DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.cloudscaleproject.env.csm.definition.impl.ExternalSupportServiceDescriptorImpl <em>External Support Service Descriptor</em>}' class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see eu.cloudscaleproject.env.csm.definition.impl.ExternalSupportServiceDescriptorImpl
	 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getExternalSupportServiceDescriptor()
	 * @generated
	 */
	int EXTERNAL_SUPPORT_SERVICE_DESCRIPTOR = 16;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SUPPORT_SERVICE_DESCRIPTOR__ID = EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SUPPORT_SERVICE_DESCRIPTOR__NAME = EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SUPPORT_SERVICE_DESCRIPTOR__DESCRIPTION = EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SUPPORT_SERVICE_DESCRIPTOR__AV_MAP = EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SUPPORT_SERVICE_DESCRIPTOR__AE_MAP = EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SUPPORT_SERVICE_DESCRIPTOR__PROVIDER_ID = EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR__PROVIDER_ID;

	/**
	 * The number of structural features of the '<em>External Support Service Descriptor</em>' class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SUPPORT_SERVICE_DESCRIPTOR_FEATURE_COUNT = EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.definition.Definition <em>Definition</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Definition</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.Definition
	 * @generated
	 */
	EClass getDefinition();

	/**
	 * Returns the meta object for class '
	 * {@link eu.cloudscaleproject.env.csm.definition.SystemDefinition
	 * <em>System Definition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>System Definition</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.SystemDefinition
	 * @generated
	 */
	EClass getSystemDefinition();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.csm.definition.SystemDefinition#getDescriptors <em>Descriptors</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptors</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.SystemDefinition#getDescriptors()
	 * @see #getSystemDefinition()
	 * @generated
	 */
	EReference getSystemDefinition_Descriptors();

	/**
	 * Returns the meta object for class '
	 * {@link eu.cloudscaleproject.env.csm.definition.CloudDefinition
	 * <em>Cloud Definition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Cloud Definition</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.CloudDefinition
	 * @generated
	 */
	EClass getCloudDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link eu.cloudscaleproject.env.csm.definition.CloudDefinition#getDescriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.CloudDefinition#getDescriptor()
	 * @see #getCloudDefinition()
	 * @generated
	 */
	EReference getCloudDefinition_Descriptor();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.csm.definition.CloudDefinition#getInfrastructureDescriptors <em>Infrastructure Descriptors</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Infrastructure Descriptors</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.CloudDefinition#getInfrastructureDescriptors()
	 * @see #getCloudDefinition()
	 * @generated
	 */
	EReference getCloudDefinition_InfrastructureDescriptors();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.csm.definition.CloudDefinition#getPlatformDescriptors <em>Platform Descriptors</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Platform Descriptors</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.CloudDefinition#getPlatformDescriptors()
	 * @see #getCloudDefinition()
	 * @generated
	 */
	EReference getCloudDefinition_PlatformDescriptors();

	/**
	 * Returns the meta object for class '
	 * {@link eu.cloudscaleproject.env.csm.definition.SoftwareDefinition
	 * <em>Software Definition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Software Definition</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.SoftwareDefinition
	 * @generated
	 */
	EClass getSoftwareDefinition();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.csm.definition.SoftwareDefinition#getDeployablePlatformServiceDescriptors <em>Deployable Platform Service Descriptors</em>}'.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Deployable Platform Service Descriptors</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.SoftwareDefinition#getDeployablePlatformServiceDescriptors()
	 * @see #getSoftwareDefinition()
	 * @generated
	 */
	EReference getSoftwareDefinition_DeployablePlatformServiceDescriptors();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.definition.Descriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.Descriptor
	 * @generated
	 */
	EClass getDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.csm.definition.Descriptor#getProviderID <em>Provider ID</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Provider ID</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.Descriptor#getProviderID()
	 * @see #getDescriptor()
	 * @generated
	 */
	EAttribute getDescriptor_ProviderID();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor <em>Cloud Environment Descriptor</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>Cloud Environment Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor
	 * @generated
	 */
	EClass getCloudEnvironmentDescriptor();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor#getAvailabilityZones
	 * <em>Availability Zones</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Availability Zones</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor#getAvailabilityZones()
	 * @see #getCloudEnvironmentDescriptor()
	 * @generated
	 */
	EReference getCloudEnvironmentDescriptor_AvailabilityZones();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor#getRegions <em>Regions</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Regions</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor#getRegions()
	 * @see #getCloudEnvironmentDescriptor()
	 * @generated
	 */
	EReference getCloudEnvironmentDescriptor_Regions();

	/**
	 * Returns the meta object for class '
	 * {@link eu.cloudscaleproject.env.csm.definition.RegionDescriptor
	 * <em>Region Descriptor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Region Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.RegionDescriptor
	 * @generated
	 */
	EClass getRegionDescriptor();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.definition.AvailabilityZoneDescriptor <em>Availability Zone Descriptor</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>Availability Zone Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.AvailabilityZoneDescriptor
	 * @generated
	 */
	EClass getAvailabilityZoneDescriptor();

	/**
	 * Returns the meta object for the containment reference '{@link eu.cloudscaleproject.env.csm.definition.AvailabilityZoneDescriptor#getInternalConnectionDescriptor <em>Internal Connection Descriptor</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the containment reference '<em>Internal Connection Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.AvailabilityZoneDescriptor#getInternalConnectionDescriptor()
	 * @see #getAvailabilityZoneDescriptor()
	 * @generated
	 */
	EReference getAvailabilityZoneDescriptor_InternalConnectionDescriptor();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.definition.InfrastructureServiceDescriptor <em>Infrastructure Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Infrastructure Service Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.InfrastructureServiceDescriptor
	 * @generated
	 */
	EClass getInfrastructureServiceDescriptor();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor <em>Network Infrastructure Service Descriptor</em>}'.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Network Infrastructure Service Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor
	 * @generated
	 */
	EClass getNetworkInfrastructureServiceDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor#getBandwidth <em>Bandwidth</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bandwidth</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor#getBandwidth()
	 * @see #getNetworkInfrastructureServiceDescriptor()
	 * @generated
	 */
	EAttribute getNetworkInfrastructureServiceDescriptor_Bandwidth();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor#getLatency <em>Latency</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Latency</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor#getLatency()
	 * @see #getNetworkInfrastructureServiceDescriptor()
	 * @generated
	 */
	EAttribute getNetworkInfrastructureServiceDescriptor_Latency();

	/**
	 * Returns the meta object for class '
	 * {@link eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor
	 * <em>Computing Infrastructure Service Descriptor</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '
	 *         <em>Computing Infrastructure Service Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor
	 * @generated
	 */
	EClass getComputingInfrastructureServiceDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor#getMemory <em>Memory</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Memory</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor#getMemory()
	 * @see #getComputingInfrastructureServiceDescriptor()
	 * @generated
	 */
	EAttribute getComputingInfrastructureServiceDescriptor_Memory();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor#getCpu <em>Cpu</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cpu</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor#getCpu()
	 * @see #getComputingInfrastructureServiceDescriptor()
	 * @generated
	 */
	EAttribute getComputingInfrastructureServiceDescriptor_Cpu();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor#getCpuUnits <em>Cpu Units</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cpu Units</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor#getCpuUnits()
	 * @see #getComputingInfrastructureServiceDescriptor()
	 * @generated
	 */
	EAttribute getComputingInfrastructureServiceDescriptor_CpuUnits();

	/**
	 * Returns the meta object for the attribute '{@link eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor#getStorage <em>Storage</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Storage</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor#getStorage()
	 * @see #getComputingInfrastructureServiceDescriptor()
	 * @generated
	 */
	EAttribute getComputingInfrastructureServiceDescriptor_Storage();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.definition.DeployablePlatformServiceDescriptor <em>Deployable Platform Service Descriptor</em>}'.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deployable Platform Service Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.DeployablePlatformServiceDescriptor
	 * @generated
	 */
	EClass getDeployablePlatformServiceDescriptor();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.definition.ExternalPlatformServiceDescriptor <em>External Platform Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Platform Service Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.ExternalPlatformServiceDescriptor
	 * @generated
	 */
	EClass getExternalPlatformServiceDescriptor();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.definition.DeployableRuntimeServiceDescriptor <em>Deployable Runtime Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deployable Runtime Service Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.DeployableRuntimeServiceDescriptor
	 * @generated
	 */
	EClass getDeployableRuntimeServiceDescriptor();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.definition.ExternalRuntimeServiceDescriptor <em>External Runtime Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Runtime Service Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.ExternalRuntimeServiceDescriptor
	 * @generated
	 */
	EClass getExternalRuntimeServiceDescriptor();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.definition.DeployableSupportServiceDescriptor <em>Deployable Support Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deployable Support Service Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.DeployableSupportServiceDescriptor
	 * @generated
	 */
	EClass getDeployableSupportServiceDescriptor();

	/**
	 * Returns the meta object for class '{@link eu.cloudscaleproject.env.csm.definition.ExternalSupportServiceDescriptor <em>External Support Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Support Service Descriptor</em>'.
	 * @see eu.cloudscaleproject.env.csm.definition.ExternalSupportServiceDescriptor
	 * @generated
	 */
	EClass getExternalSupportServiceDescriptor();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DefinitionFactory getDefinitionFactory();

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
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.definition.impl.DefinitionImpl <em>Definition</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionImpl
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getDefinition()
		 * @generated
		 */
		EClass DEFINITION = eINSTANCE.getDefinition();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.definition.impl.SystemDefinitionImpl <em>System Definition</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.definition.impl.SystemDefinitionImpl
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getSystemDefinition()
		 * @generated
		 */
		EClass SYSTEM_DEFINITION = eINSTANCE.getSystemDefinition();

		/**
		 * The meta object literal for the '<em><b>Descriptors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_DEFINITION__DESCRIPTORS = eINSTANCE.getSystemDefinition_Descriptors();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.definition.impl.CloudDefinitionImpl <em>Cloud Definition</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.definition.impl.CloudDefinitionImpl
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getCloudDefinition()
		 * @generated
		 */
		EClass CLOUD_DEFINITION = eINSTANCE.getCloudDefinition();

		/**
		 * The meta object literal for the '<em><b>Descriptor</b></em>' containment reference feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_DEFINITION__DESCRIPTOR = eINSTANCE.getCloudDefinition_Descriptor();

		/**
		 * The meta object literal for the '<em><b>Infrastructure Descriptors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_DEFINITION__INFRASTRUCTURE_DESCRIPTORS = eINSTANCE.getCloudDefinition_InfrastructureDescriptors();

		/**
		 * The meta object literal for the '<em><b>Platform Descriptors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_DEFINITION__PLATFORM_DESCRIPTORS = eINSTANCE.getCloudDefinition_PlatformDescriptors();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.definition.impl.SoftwareDefinitionImpl <em>Software Definition</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.definition.impl.SoftwareDefinitionImpl
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getSoftwareDefinition()
		 * @generated
		 */
		EClass SOFTWARE_DEFINITION = eINSTANCE.getSoftwareDefinition();

		/**
		 * The meta object literal for the '<em><b>Deployable Platform Service Descriptors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOFTWARE_DEFINITION__DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTORS = eINSTANCE.getSoftwareDefinition_DeployablePlatformServiceDescriptors();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.definition.impl.DescriptorImpl <em>Descriptor</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DescriptorImpl
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getDescriptor()
		 * @generated
		 */
		EClass DESCRIPTOR = eINSTANCE.getDescriptor();

		/**
		 * The meta object literal for the '<em><b>Provider ID</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIPTOR__PROVIDER_ID = eINSTANCE.getDescriptor_ProviderID();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.definition.impl.CloudEnvironmentDescriptorImpl <em>Cloud Environment Descriptor</em>}' class.
		 * <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.definition.impl.CloudEnvironmentDescriptorImpl
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getCloudEnvironmentDescriptor()
		 * @generated
		 */
		EClass CLOUD_ENVIRONMENT_DESCRIPTOR = eINSTANCE.getCloudEnvironmentDescriptor();

		/**
		 * The meta object literal for the '<em><b>Availability Zones</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES = eINSTANCE.getCloudEnvironmentDescriptor_AvailabilityZones();

		/**
		 * The meta object literal for the '<em><b>Regions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS = eINSTANCE.getCloudEnvironmentDescriptor_Regions();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.definition.impl.RegionDescriptorImpl <em>Region Descriptor</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.definition.impl.RegionDescriptorImpl
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getRegionDescriptor()
		 * @generated
		 */
		EClass REGION_DESCRIPTOR = eINSTANCE.getRegionDescriptor();

		/**
		 * The meta object literal for the '{@link eu.cloudscaleproject.env.csm.definition.impl.AvailabilityZoneDescriptorImpl <em>Availability Zone Descriptor</em>}' class.
		 * <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * @see eu.cloudscaleproject.env.csm.definition.impl.AvailabilityZoneDescriptorImpl
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getAvailabilityZoneDescriptor()
		 * @generated
		 */
		EClass AVAILABILITY_ZONE_DESCRIPTOR = eINSTANCE.getAvailabilityZoneDescriptor();

		/**
		 * The meta object literal for the '<em><b>Internal Connection Descriptor</b></em>' containment reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference AVAILABILITY_ZONE_DESCRIPTOR__INTERNAL_CONNECTION_DESCRIPTOR = eINSTANCE.getAvailabilityZoneDescriptor_InternalConnectionDescriptor();

		/**
		 * The meta object literal for the '
		 * {@link eu.cloudscaleproject.env.csm.definition.impl.InfrastructureServiceDescriptorImpl
		 * <em>Infrastructure Service Descriptor</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eu.cloudscaleproject.env.csm.definition.impl.InfrastructureServiceDescriptorImpl
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getInfrastructureServiceDescriptor()
		 * @generated
		 */
		EClass INFRASTRUCTURE_SERVICE_DESCRIPTOR = eINSTANCE.getInfrastructureServiceDescriptor();

		/**
		 * The meta object literal for the '
		 * {@link eu.cloudscaleproject.env.csm.definition.impl.NetworkInfrastructureServiceDescriptorImpl
		 * <em>Network Infrastructure Service Descriptor</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eu.cloudscaleproject.env.csm.definition.impl.NetworkInfrastructureServiceDescriptorImpl
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getNetworkInfrastructureServiceDescriptor()
		 * @generated
		 */
		EClass NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR = eINSTANCE.getNetworkInfrastructureServiceDescriptor();

		/**
		 * The meta object literal for the '<em><b>Bandwidth</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH = eINSTANCE.getNetworkInfrastructureServiceDescriptor_Bandwidth();

		/**
		 * The meta object literal for the '<em><b>Latency</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY = eINSTANCE.getNetworkInfrastructureServiceDescriptor_Latency();

		/**
		 * The meta object literal for the '
		 * {@link eu.cloudscaleproject.env.csm.definition.impl.ComputingInfrastructureServiceDescriptorImpl
		 * <em>Computing Infrastructure Service Descriptor</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eu.cloudscaleproject.env.csm.definition.impl.ComputingInfrastructureServiceDescriptorImpl
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getComputingInfrastructureServiceDescriptor()
		 * @generated
		 */
		EClass COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR = eINSTANCE.getComputingInfrastructureServiceDescriptor();

		/**
		 * The meta object literal for the '<em><b>Memory</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__MEMORY = eINSTANCE.getComputingInfrastructureServiceDescriptor_Memory();

		/**
		 * The meta object literal for the '<em><b>Cpu</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU = eINSTANCE.getComputingInfrastructureServiceDescriptor_Cpu();

		/**
		 * The meta object literal for the '<em><b>Cpu Units</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU_UNITS = eINSTANCE.getComputingInfrastructureServiceDescriptor_CpuUnits();

		/**
		 * The meta object literal for the '<em><b>Storage</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__STORAGE = eINSTANCE.getComputingInfrastructureServiceDescriptor_Storage();

		/**
		 * The meta object literal for the '
		 * {@link eu.cloudscaleproject.env.csm.definition.DeployablePlatformServiceDescriptor
		 * <em>Deployable Platform Service Descriptor</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eu.cloudscaleproject.env.csm.definition.DeployablePlatformServiceDescriptor
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getDeployablePlatformServiceDescriptor()
		 * @generated
		 */
		EClass DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR = eINSTANCE.getDeployablePlatformServiceDescriptor();

		/**
		 * The meta object literal for the '
		 * {@link eu.cloudscaleproject.env.csm.definition.ExternalPlatformServiceDescriptor
		 * <em>External Platform Service Descriptor</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eu.cloudscaleproject.env.csm.definition.ExternalPlatformServiceDescriptor
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getExternalPlatformServiceDescriptor()
		 * @generated
		 */
		EClass EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR = eINSTANCE.getExternalPlatformServiceDescriptor();

		/**
		 * The meta object literal for the '
		 * {@link eu.cloudscaleproject.env.csm.definition.impl.DeployableRuntimeServiceDescriptorImpl
		 * <em>Deployable Runtime Service Descriptor</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DeployableRuntimeServiceDescriptorImpl
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getDeployableRuntimeServiceDescriptor()
		 * @generated
		 */
		EClass DEPLOYABLE_RUNTIME_SERVICE_DESCRIPTOR = eINSTANCE.getDeployableRuntimeServiceDescriptor();

		/**
		 * The meta object literal for the '
		 * {@link eu.cloudscaleproject.env.csm.definition.impl.ExternalRuntimeServiceDescriptorImpl
		 * <em>External Runtime Service Descriptor</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eu.cloudscaleproject.env.csm.definition.impl.ExternalRuntimeServiceDescriptorImpl
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getExternalRuntimeServiceDescriptor()
		 * @generated
		 */
		EClass EXTERNAL_RUNTIME_SERVICE_DESCRIPTOR = eINSTANCE.getExternalRuntimeServiceDescriptor();

		/**
		 * The meta object literal for the '
		 * {@link eu.cloudscaleproject.env.csm.definition.impl.DeployableSupportServiceDescriptorImpl
		 * <em>Deployable Support Service Descriptor</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DeployableSupportServiceDescriptorImpl
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getDeployableSupportServiceDescriptor()
		 * @generated
		 */
		EClass DEPLOYABLE_SUPPORT_SERVICE_DESCRIPTOR = eINSTANCE.getDeployableSupportServiceDescriptor();

		/**
		 * The meta object literal for the '
		 * {@link eu.cloudscaleproject.env.csm.definition.impl.ExternalSupportServiceDescriptorImpl
		 * <em>External Support Service Descriptor</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eu.cloudscaleproject.env.csm.definition.impl.ExternalSupportServiceDescriptorImpl
		 * @see eu.cloudscaleproject.env.csm.definition.impl.DefinitionPackageImpl#getExternalSupportServiceDescriptor()
		 * @generated
		 */
		EClass EXTERNAL_SUPPORT_SERVICE_DESCRIPTOR = eINSTANCE.getExternalSupportServiceDescriptor();

	}

} // DefinitionPackage
