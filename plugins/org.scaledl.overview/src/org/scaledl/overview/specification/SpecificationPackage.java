/**
 */
package org.scaledl.overview.specification;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
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
 * This package defines the descriptors, which are referenced by classes in Architecture package, and provides exact specification to cloud system components. Additionally package defines also two definition classes (i.e. CloudDefinition and SoftwareDefinition) that are used as descriptor containers, whose purpose is to extend available specifications.
 * 
 * <!-- end-model-doc -->
 * @see org.scaledl.overview.specification.SpecificationFactory
 * @model kind="package"
 * @generated
 */
public interface SpecificationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "specification";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org.scaledl.overview/ScaleDLOverviewComponentModel/Specification/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "specification";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SpecificationPackage eINSTANCE = org.scaledl.overview.specification.impl.SpecificationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.SpecificationImpl <em>Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.SpecificationImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getSpecification()
	 * @generated
	 */
	int SPECIFICATION = 0;

	/**
	 * The number of structural features of the '<em>Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.SystemSpecificationImpl <em>System Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.SystemSpecificationImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getSystemSpecification()
	 * @generated
	 */
	int SYSTEM_SPECIFICATION = 1;

	/**
	 * The feature id for the '<em><b>Descriptors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_SPECIFICATION__DESCRIPTORS = SPECIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>System Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_SPECIFICATION_FEATURE_COUNT = SPECIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.CloudSpecificationImpl <em>Cloud Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.CloudSpecificationImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getCloudSpecification()
	 * @generated
	 */
	int CLOUD_SPECIFICATION = 2;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_SPECIFICATION__DESCRIPTOR = SPECIFICATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Infrastructure Service Descriptors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_SPECIFICATION__INFRASTRUCTURE_SERVICE_DESCRIPTORS = SPECIFICATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Platform Service Descriptors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_SPECIFICATION__PLATFORM_SERVICE_DESCRIPTORS = SPECIFICATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Software Service Descriptors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_SPECIFICATION__SOFTWARE_SERVICE_DESCRIPTORS = SPECIFICATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Cloud Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_SPECIFICATION_FEATURE_COUNT = SPECIFICATION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.ServiceSpecificationImpl <em>Service Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.ServiceSpecificationImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getServiceSpecification()
	 * @generated
	 */
	int SERVICE_SPECIFICATION = 3;

	/**
	 * The feature id for the '<em><b>Service Descriptors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS = SPECIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Service Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SPECIFICATION_FEATURE_COUNT = SPECIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.Descriptor <em>Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.Descriptor
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getDescriptor()
	 * @generated
	 */
	int DESCRIPTOR = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__PROVIDER_ID = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.CloudEnvironmentDescriptorImpl <em>Cloud Environment Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.CloudEnvironmentDescriptorImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getCloudEnvironmentDescriptor()
	 * @generated
	 */
	int CLOUD_ENVIRONMENT_DESCRIPTOR = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT_DESCRIPTOR__ID = DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT_DESCRIPTOR__NAME = DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT_DESCRIPTOR__DESCRIPTION = DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT_DESCRIPTOR__AV_MAP = DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT_DESCRIPTOR__AE_MAP = DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT_DESCRIPTOR__PROVIDER_ID = DESCRIPTOR__PROVIDER_ID;

	/**
	 * The feature id for the '<em><b>Availability Zones</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES = DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Regions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.RegionDescriptorImpl <em>Region Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.RegionDescriptorImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getRegionDescriptor()
	 * @generated
	 */
	int REGION_DESCRIPTOR = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_DESCRIPTOR__ID = DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_DESCRIPTOR__NAME = DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_DESCRIPTOR__DESCRIPTION = DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_DESCRIPTOR__AV_MAP = DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_DESCRIPTOR__AE_MAP = DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_DESCRIPTOR__PROVIDER_ID = DESCRIPTOR__PROVIDER_ID;

	/**
	 * The number of structural features of the '<em>Region Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_DESCRIPTOR_FEATURE_COUNT = DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.AvailabilityZoneDescriptorImpl <em>Availability Zone Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.AvailabilityZoneDescriptorImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getAvailabilityZoneDescriptor()
	 * @generated
	 */
	int AVAILABILITY_ZONE_DESCRIPTOR = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_ZONE_DESCRIPTOR__ID = DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_ZONE_DESCRIPTOR__NAME = DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_ZONE_DESCRIPTOR__DESCRIPTION = DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_ZONE_DESCRIPTOR__AV_MAP = DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_ZONE_DESCRIPTOR__AE_MAP = DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_ZONE_DESCRIPTOR__PROVIDER_ID = DESCRIPTOR__PROVIDER_ID;

	/**
	 * The feature id for the '<em><b>Network Infrastructure Service Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_ZONE_DESCRIPTOR__NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR = DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Availability Zone Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_ZONE_DESCRIPTOR_FEATURE_COUNT = DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.ServiceDescriptor <em>Service Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.ServiceDescriptor
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getServiceDescriptor()
	 * @generated
	 */
	int SERVICE_DESCRIPTOR = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DESCRIPTOR__ID = DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DESCRIPTOR__NAME = DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DESCRIPTOR__DESCRIPTION = DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DESCRIPTOR__AV_MAP = DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DESCRIPTOR__AE_MAP = DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DESCRIPTOR__PROVIDER_ID = DESCRIPTOR__PROVIDER_ID;

	/**
	 * The number of structural features of the '<em>Service Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DESCRIPTOR_FEATURE_COUNT = DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.ProvidedServiceDescriptor <em>Provided Service Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.ProvidedServiceDescriptor
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getProvidedServiceDescriptor()
	 * @generated
	 */
	int PROVIDED_SERVICE_DESCRIPTOR = 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SERVICE_DESCRIPTOR__ID = DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SERVICE_DESCRIPTOR__NAME = DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SERVICE_DESCRIPTOR__DESCRIPTION = DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SERVICE_DESCRIPTOR__AV_MAP = DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SERVICE_DESCRIPTOR__AE_MAP = DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SERVICE_DESCRIPTOR__PROVIDER_ID = DESCRIPTOR__PROVIDER_ID;

	/**
	 * The feature id for the '<em><b>Sla</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SERVICE_DESCRIPTOR__SLA = DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Provided Service Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SERVICE_DESCRIPTOR_FEATURE_COUNT = DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.InfrastructureServiceDescriptor <em>Infrastructure Service Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.InfrastructureServiceDescriptor
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getInfrastructureServiceDescriptor()
	 * @generated
	 */
	int INFRASTRUCTURE_SERVICE_DESCRIPTOR = 10;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE_DESCRIPTOR__ID = PROVIDED_SERVICE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE_DESCRIPTOR__NAME = PROVIDED_SERVICE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE_DESCRIPTOR__DESCRIPTION = PROVIDED_SERVICE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE_DESCRIPTOR__AV_MAP = PROVIDED_SERVICE_DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE_DESCRIPTOR__AE_MAP = PROVIDED_SERVICE_DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID = PROVIDED_SERVICE_DESCRIPTOR__PROVIDER_ID;

	/**
	 * The feature id for the '<em><b>Sla</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA = PROVIDED_SERVICE_DESCRIPTOR__SLA;

	/**
	 * The number of structural features of the '<em>Infrastructure Service Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT = PROVIDED_SERVICE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.NetworkInfrastructureServiceDescriptorImpl <em>Network Infrastructure Service Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.NetworkInfrastructureServiceDescriptorImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getNetworkInfrastructureServiceDescriptor()
	 * @generated
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR = 11;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__ID = INFRASTRUCTURE_SERVICE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__NAME = INFRASTRUCTURE_SERVICE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__DESCRIPTION = INFRASTRUCTURE_SERVICE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__AV_MAP = INFRASTRUCTURE_SERVICE_DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__AE_MAP = INFRASTRUCTURE_SERVICE_DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID = INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID;

	/**
	 * The feature id for the '<em><b>Sla</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA = INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA;

	/**
	 * The feature id for the '<em><b>Bandwidth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH = INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Latency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY = INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Network Infrastructure Service Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT = INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.ComputingInfrastructureServiceDescriptorImpl <em>Computing Infrastructure Service Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.ComputingInfrastructureServiceDescriptorImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getComputingInfrastructureServiceDescriptor()
	 * @generated
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR = 12;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__ID = INFRASTRUCTURE_SERVICE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__NAME = INFRASTRUCTURE_SERVICE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__DESCRIPTION = INFRASTRUCTURE_SERVICE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__AV_MAP = INFRASTRUCTURE_SERVICE_DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__AE_MAP = INFRASTRUCTURE_SERVICE_DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID = INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID;

	/**
	 * The feature id for the '<em><b>Sla</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA = INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA;

	/**
	 * The feature id for the '<em><b>Computing Resource Descriptors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__COMPUTING_RESOURCE_DESCRIPTORS = INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>General Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__GENERAL_PURPOSE = INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Computing Infrastructure Service Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT = INFRASTRUCTURE_SERVICE_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.ComputingResourceDescriptorImpl <em>Computing Resource Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.ComputingResourceDescriptorImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getComputingResourceDescriptor()
	 * @generated
	 */
	int COMPUTING_RESOURCE_DESCRIPTOR = 13;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_RESOURCE_DESCRIPTOR__ID = DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_RESOURCE_DESCRIPTOR__NAME = DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_RESOURCE_DESCRIPTOR__DESCRIPTION = DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_RESOURCE_DESCRIPTOR__AV_MAP = DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_RESOURCE_DESCRIPTOR__AE_MAP = DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_RESOURCE_DESCRIPTOR__PROVIDER_ID = DESCRIPTOR__PROVIDER_ID;

	/**
	 * The feature id for the '<em><b>Editable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_RESOURCE_DESCRIPTOR__EDITABLE = DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Memory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_RESOURCE_DESCRIPTOR__MEMORY = DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Cpu</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_RESOURCE_DESCRIPTOR__CPU = DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Cpu Units</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_RESOURCE_DESCRIPTOR__CPU_UNITS = DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Storage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_RESOURCE_DESCRIPTOR__STORAGE = DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Computing Resource Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_RESOURCE_DESCRIPTOR_FEATURE_COUNT = DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.PlatformServiceDescriptorImpl <em>Platform Service Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.PlatformServiceDescriptorImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getPlatformServiceDescriptor()
	 * @generated
	 */
	int PLATFORM_SERVICE_DESCRIPTOR = 14;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE_DESCRIPTOR__ID = SERVICE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE_DESCRIPTOR__NAME = SERVICE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE_DESCRIPTOR__DESCRIPTION = SERVICE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE_DESCRIPTOR__AV_MAP = SERVICE_DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE_DESCRIPTOR__AE_MAP = SERVICE_DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE_DESCRIPTOR__PROVIDER_ID = SERVICE_DESCRIPTOR__PROVIDER_ID;

	/**
	 * The number of structural features of the '<em>Platform Service Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SERVICE_DESCRIPTOR_FEATURE_COUNT = SERVICE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.PlatformRuntimeServiceDescriptorImpl <em>Platform Runtime Service Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.PlatformRuntimeServiceDescriptorImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getPlatformRuntimeServiceDescriptor()
	 * @generated
	 */
	int PLATFORM_RUNTIME_SERVICE_DESCRIPTOR = 15;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_RUNTIME_SERVICE_DESCRIPTOR__ID = PLATFORM_SERVICE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_RUNTIME_SERVICE_DESCRIPTOR__NAME = PLATFORM_SERVICE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_RUNTIME_SERVICE_DESCRIPTOR__DESCRIPTION = PLATFORM_SERVICE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_RUNTIME_SERVICE_DESCRIPTOR__AV_MAP = PLATFORM_SERVICE_DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_RUNTIME_SERVICE_DESCRIPTOR__AE_MAP = PLATFORM_SERVICE_DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_RUNTIME_SERVICE_DESCRIPTOR__PROVIDER_ID = PLATFORM_SERVICE_DESCRIPTOR__PROVIDER_ID;

	/**
	 * The number of structural features of the '<em>Platform Runtime Service Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_RUNTIME_SERVICE_DESCRIPTOR_FEATURE_COUNT = PLATFORM_SERVICE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.PlatformSupportServiceDescriptorImpl <em>Platform Support Service Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.PlatformSupportServiceDescriptorImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getPlatformSupportServiceDescriptor()
	 * @generated
	 */
	int PLATFORM_SUPPORT_SERVICE_DESCRIPTOR = 16;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SUPPORT_SERVICE_DESCRIPTOR__ID = PLATFORM_SERVICE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SUPPORT_SERVICE_DESCRIPTOR__NAME = PLATFORM_SERVICE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SUPPORT_SERVICE_DESCRIPTOR__DESCRIPTION = PLATFORM_SERVICE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SUPPORT_SERVICE_DESCRIPTOR__AV_MAP = PLATFORM_SERVICE_DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SUPPORT_SERVICE_DESCRIPTOR__AE_MAP = PLATFORM_SERVICE_DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SUPPORT_SERVICE_DESCRIPTOR__PROVIDER_ID = PLATFORM_SERVICE_DESCRIPTOR__PROVIDER_ID;

	/**
	 * The number of structural features of the '<em>Platform Support Service Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_SUPPORT_SERVICE_DESCRIPTOR_FEATURE_COUNT = PLATFORM_SERVICE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.ProvidedPlatformServiceDescriptorImpl <em>Provided Platform Service Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.ProvidedPlatformServiceDescriptorImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getProvidedPlatformServiceDescriptor()
	 * @generated
	 */
	int PROVIDED_PLATFORM_SERVICE_DESCRIPTOR = 17;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__ID = PLATFORM_SERVICE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__NAME = PLATFORM_SERVICE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__DESCRIPTION = PLATFORM_SERVICE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__AV_MAP = PLATFORM_SERVICE_DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__AE_MAP = PLATFORM_SERVICE_DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__PROVIDER_ID = PLATFORM_SERVICE_DESCRIPTOR__PROVIDER_ID;

	/**
	 * The feature id for the '<em><b>Sla</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__SLA = PLATFORM_SERVICE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Infrastructure Service Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__INFRASTRUCTURE_SERVICE_DESCRIPTOR = PLATFORM_SERVICE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Provided Platform Service Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SERVICE_DESCRIPTOR_FEATURE_COUNT = PLATFORM_SERVICE_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.ProvidedPlatformRuntimeServiceDescriptorImpl <em>Provided Platform Runtime Service Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.ProvidedPlatformRuntimeServiceDescriptorImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getProvidedPlatformRuntimeServiceDescriptor()
	 * @generated
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE_DESCRIPTOR = 18;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE_DESCRIPTOR__ID = PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE_DESCRIPTOR__NAME = PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE_DESCRIPTOR__DESCRIPTION = PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE_DESCRIPTOR__AV_MAP = PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE_DESCRIPTOR__AE_MAP = PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE_DESCRIPTOR__PROVIDER_ID = PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__PROVIDER_ID;

	/**
	 * The feature id for the '<em><b>Sla</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE_DESCRIPTOR__SLA = PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__SLA;

	/**
	 * The feature id for the '<em><b>Infrastructure Service Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE_DESCRIPTOR__INFRASTRUCTURE_SERVICE_DESCRIPTOR = PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__INFRASTRUCTURE_SERVICE_DESCRIPTOR;

	/**
	 * The number of structural features of the '<em>Provided Platform Runtime Service Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_RUNTIME_SERVICE_DESCRIPTOR_FEATURE_COUNT = PROVIDED_PLATFORM_SERVICE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.ProvidedPlatformSupportServiceDescriptorImpl <em>Provided Platform Support Service Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.ProvidedPlatformSupportServiceDescriptorImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getProvidedPlatformSupportServiceDescriptor()
	 * @generated
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE_DESCRIPTOR = 19;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE_DESCRIPTOR__ID = PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE_DESCRIPTOR__NAME = PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE_DESCRIPTOR__DESCRIPTION = PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE_DESCRIPTOR__AV_MAP = PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE_DESCRIPTOR__AE_MAP = PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE_DESCRIPTOR__PROVIDER_ID = PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__PROVIDER_ID;

	/**
	 * The feature id for the '<em><b>Sla</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE_DESCRIPTOR__SLA = PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__SLA;

	/**
	 * The feature id for the '<em><b>Infrastructure Service Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE_DESCRIPTOR__INFRASTRUCTURE_SERVICE_DESCRIPTOR = PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__INFRASTRUCTURE_SERVICE_DESCRIPTOR;

	/**
	 * The number of structural features of the '<em>Provided Platform Support Service Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_PLATFORM_SUPPORT_SERVICE_DESCRIPTOR_FEATURE_COUNT = PROVIDED_PLATFORM_SERVICE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.SoftwareServiceDescriptorImpl <em>Software Service Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.SoftwareServiceDescriptorImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getSoftwareServiceDescriptor()
	 * @generated
	 */
	int SOFTWARE_SERVICE_DESCRIPTOR = 20;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_DESCRIPTOR__ID = SERVICE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_DESCRIPTOR__NAME = SERVICE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_DESCRIPTOR__DESCRIPTION = SERVICE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_DESCRIPTOR__AV_MAP = SERVICE_DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_DESCRIPTOR__AE_MAP = SERVICE_DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_DESCRIPTOR__PROVIDER_ID = SERVICE_DESCRIPTOR__PROVIDER_ID;

	/**
	 * The number of structural features of the '<em>Software Service Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SERVICE_DESCRIPTOR_FEATURE_COUNT = SERVICE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.ProvidedSoftwareServiceDescriptorImpl <em>Provided Software Service Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.ProvidedSoftwareServiceDescriptorImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getProvidedSoftwareServiceDescriptor()
	 * @generated
	 */
	int PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR = 21;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__ID = SOFTWARE_SERVICE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__NAME = SOFTWARE_SERVICE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__DESCRIPTION = SOFTWARE_SERVICE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__AV_MAP = SOFTWARE_SERVICE_DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__AE_MAP = SOFTWARE_SERVICE_DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__PROVIDER_ID = SOFTWARE_SERVICE_DESCRIPTOR__PROVIDER_ID;

	/**
	 * The feature id for the '<em><b>Sla</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__SLA = SOFTWARE_SERVICE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__OPERATIONS = SOFTWARE_SERVICE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Provided Software Service Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR_FEATURE_COUNT = SOFTWARE_SERVICE_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.specification.impl.ExternalSoftwareServiceDescriptorImpl <em>External Software Service Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.specification.impl.ExternalSoftwareServiceDescriptorImpl
	 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getExternalSoftwareServiceDescriptor()
	 * @generated
	 */
	int EXTERNAL_SOFTWARE_SERVICE_DESCRIPTOR = 22;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE_DESCRIPTOR__ID = PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE_DESCRIPTOR__NAME = PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE_DESCRIPTOR__DESCRIPTION = PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE_DESCRIPTOR__AV_MAP = PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE_DESCRIPTOR__AE_MAP = PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__AE_MAP;

	/**
	 * The feature id for the '<em><b>Provider ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE_DESCRIPTOR__PROVIDER_ID = PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__PROVIDER_ID;

	/**
	 * The feature id for the '<em><b>Sla</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE_DESCRIPTOR__SLA = PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__SLA;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE_DESCRIPTOR__OPERATIONS = PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__OPERATIONS;

	/**
	 * The number of structural features of the '<em>External Software Service Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_SOFTWARE_SERVICE_DESCRIPTOR_FEATURE_COUNT = PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.Specification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specification</em>'.
	 * @see org.scaledl.overview.specification.Specification
	 * @generated
	 */
	EClass getSpecification();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.SystemSpecification <em>System Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Specification</em>'.
	 * @see org.scaledl.overview.specification.SystemSpecification
	 * @generated
	 */
	EClass getSystemSpecification();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.specification.SystemSpecification#getDescriptors <em>Descriptors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptors</em>'.
	 * @see org.scaledl.overview.specification.SystemSpecification#getDescriptors()
	 * @see #getSystemSpecification()
	 * @generated
	 */
	EReference getSystemSpecification_Descriptors();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.CloudSpecification <em>Cloud Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cloud Specification</em>'.
	 * @see org.scaledl.overview.specification.CloudSpecification
	 * @generated
	 */
	EClass getCloudSpecification();

	/**
	 * Returns the meta object for the containment reference '{@link org.scaledl.overview.specification.CloudSpecification#getDescriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Descriptor</em>'.
	 * @see org.scaledl.overview.specification.CloudSpecification#getDescriptor()
	 * @see #getCloudSpecification()
	 * @generated
	 */
	EReference getCloudSpecification_Descriptor();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.specification.CloudSpecification#getInfrastructureServiceDescriptors <em>Infrastructure Service Descriptors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Infrastructure Service Descriptors</em>'.
	 * @see org.scaledl.overview.specification.CloudSpecification#getInfrastructureServiceDescriptors()
	 * @see #getCloudSpecification()
	 * @generated
	 */
	EReference getCloudSpecification_InfrastructureServiceDescriptors();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.specification.CloudSpecification#getPlatformServiceDescriptors <em>Platform Service Descriptors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Platform Service Descriptors</em>'.
	 * @see org.scaledl.overview.specification.CloudSpecification#getPlatformServiceDescriptors()
	 * @see #getCloudSpecification()
	 * @generated
	 */
	EReference getCloudSpecification_PlatformServiceDescriptors();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.specification.CloudSpecification#getSoftwareServiceDescriptors <em>Software Service Descriptors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Software Service Descriptors</em>'.
	 * @see org.scaledl.overview.specification.CloudSpecification#getSoftwareServiceDescriptors()
	 * @see #getCloudSpecification()
	 * @generated
	 */
	EReference getCloudSpecification_SoftwareServiceDescriptors();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.ServiceSpecification <em>Service Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Specification</em>'.
	 * @see org.scaledl.overview.specification.ServiceSpecification
	 * @generated
	 */
	EClass getServiceSpecification();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.specification.ServiceSpecification#getServiceDescriptors <em>Service Descriptors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Service Descriptors</em>'.
	 * @see org.scaledl.overview.specification.ServiceSpecification#getServiceDescriptors()
	 * @see #getServiceSpecification()
	 * @generated
	 */
	EReference getServiceSpecification_ServiceDescriptors();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.Descriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Descriptor</em>'.
	 * @see org.scaledl.overview.specification.Descriptor
	 * @generated
	 */
	EClass getDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.specification.Descriptor#getProviderID <em>Provider ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Provider ID</em>'.
	 * @see org.scaledl.overview.specification.Descriptor#getProviderID()
	 * @see #getDescriptor()
	 * @generated
	 */
	EAttribute getDescriptor_ProviderID();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.CloudEnvironmentDescriptor <em>Cloud Environment Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cloud Environment Descriptor</em>'.
	 * @see org.scaledl.overview.specification.CloudEnvironmentDescriptor
	 * @generated
	 */
	EClass getCloudEnvironmentDescriptor();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.specification.CloudEnvironmentDescriptor#getAvailabilityZones <em>Availability Zones</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Availability Zones</em>'.
	 * @see org.scaledl.overview.specification.CloudEnvironmentDescriptor#getAvailabilityZones()
	 * @see #getCloudEnvironmentDescriptor()
	 * @generated
	 */
	EReference getCloudEnvironmentDescriptor_AvailabilityZones();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.specification.CloudEnvironmentDescriptor#getRegions <em>Regions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Regions</em>'.
	 * @see org.scaledl.overview.specification.CloudEnvironmentDescriptor#getRegions()
	 * @see #getCloudEnvironmentDescriptor()
	 * @generated
	 */
	EReference getCloudEnvironmentDescriptor_Regions();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.RegionDescriptor <em>Region Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Region Descriptor</em>'.
	 * @see org.scaledl.overview.specification.RegionDescriptor
	 * @generated
	 */
	EClass getRegionDescriptor();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.AvailabilityZoneDescriptor <em>Availability Zone Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Availability Zone Descriptor</em>'.
	 * @see org.scaledl.overview.specification.AvailabilityZoneDescriptor
	 * @generated
	 */
	EClass getAvailabilityZoneDescriptor();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.specification.AvailabilityZoneDescriptor#getNetworkInfrastructureServiceDescriptor <em>Network Infrastructure Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Network Infrastructure Service Descriptor</em>'.
	 * @see org.scaledl.overview.specification.AvailabilityZoneDescriptor#getNetworkInfrastructureServiceDescriptor()
	 * @see #getAvailabilityZoneDescriptor()
	 * @generated
	 */
	EReference getAvailabilityZoneDescriptor_NetworkInfrastructureServiceDescriptor();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.ServiceDescriptor <em>Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Descriptor</em>'.
	 * @see org.scaledl.overview.specification.ServiceDescriptor
	 * @generated
	 */
	EClass getServiceDescriptor();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.ProvidedServiceDescriptor <em>Provided Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provided Service Descriptor</em>'.
	 * @see org.scaledl.overview.specification.ProvidedServiceDescriptor
	 * @generated
	 */
	EClass getProvidedServiceDescriptor();

	/**
	 * Returns the meta object for the containment reference '{@link org.scaledl.overview.specification.ProvidedServiceDescriptor#getSla <em>Sla</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sla</em>'.
	 * @see org.scaledl.overview.specification.ProvidedServiceDescriptor#getSla()
	 * @see #getProvidedServiceDescriptor()
	 * @generated
	 */
	EReference getProvidedServiceDescriptor_Sla();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.InfrastructureServiceDescriptor <em>Infrastructure Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Infrastructure Service Descriptor</em>'.
	 * @see org.scaledl.overview.specification.InfrastructureServiceDescriptor
	 * @generated
	 */
	EClass getInfrastructureServiceDescriptor();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.NetworkInfrastructureServiceDescriptor <em>Network Infrastructure Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Network Infrastructure Service Descriptor</em>'.
	 * @see org.scaledl.overview.specification.NetworkInfrastructureServiceDescriptor
	 * @generated
	 */
	EClass getNetworkInfrastructureServiceDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.specification.NetworkInfrastructureServiceDescriptor#getBandwidth <em>Bandwidth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bandwidth</em>'.
	 * @see org.scaledl.overview.specification.NetworkInfrastructureServiceDescriptor#getBandwidth()
	 * @see #getNetworkInfrastructureServiceDescriptor()
	 * @generated
	 */
	EAttribute getNetworkInfrastructureServiceDescriptor_Bandwidth();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.specification.NetworkInfrastructureServiceDescriptor#getLatency <em>Latency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Latency</em>'.
	 * @see org.scaledl.overview.specification.NetworkInfrastructureServiceDescriptor#getLatency()
	 * @see #getNetworkInfrastructureServiceDescriptor()
	 * @generated
	 */
	EAttribute getNetworkInfrastructureServiceDescriptor_Latency();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor <em>Computing Infrastructure Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Computing Infrastructure Service Descriptor</em>'.
	 * @see org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor
	 * @generated
	 */
	EClass getComputingInfrastructureServiceDescriptor();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor#getComputingResourceDescriptors <em>Computing Resource Descriptors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Computing Resource Descriptors</em>'.
	 * @see org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor#getComputingResourceDescriptors()
	 * @see #getComputingInfrastructureServiceDescriptor()
	 * @generated
	 */
	EReference getComputingInfrastructureServiceDescriptor_ComputingResourceDescriptors();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor#isGeneralPurpose <em>General Purpose</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>General Purpose</em>'.
	 * @see org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor#isGeneralPurpose()
	 * @see #getComputingInfrastructureServiceDescriptor()
	 * @generated
	 */
	EAttribute getComputingInfrastructureServiceDescriptor_GeneralPurpose();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.ComputingResourceDescriptor <em>Computing Resource Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Computing Resource Descriptor</em>'.
	 * @see org.scaledl.overview.specification.ComputingResourceDescriptor
	 * @generated
	 */
	EClass getComputingResourceDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.specification.ComputingResourceDescriptor#isEditable <em>Editable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Editable</em>'.
	 * @see org.scaledl.overview.specification.ComputingResourceDescriptor#isEditable()
	 * @see #getComputingResourceDescriptor()
	 * @generated
	 */
	EAttribute getComputingResourceDescriptor_Editable();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.specification.ComputingResourceDescriptor#getMemory <em>Memory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Memory</em>'.
	 * @see org.scaledl.overview.specification.ComputingResourceDescriptor#getMemory()
	 * @see #getComputingResourceDescriptor()
	 * @generated
	 */
	EAttribute getComputingResourceDescriptor_Memory();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.specification.ComputingResourceDescriptor#getCpu <em>Cpu</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cpu</em>'.
	 * @see org.scaledl.overview.specification.ComputingResourceDescriptor#getCpu()
	 * @see #getComputingResourceDescriptor()
	 * @generated
	 */
	EAttribute getComputingResourceDescriptor_Cpu();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.specification.ComputingResourceDescriptor#getCpuUnits <em>Cpu Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cpu Units</em>'.
	 * @see org.scaledl.overview.specification.ComputingResourceDescriptor#getCpuUnits()
	 * @see #getComputingResourceDescriptor()
	 * @generated
	 */
	EAttribute getComputingResourceDescriptor_CpuUnits();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.specification.ComputingResourceDescriptor#getStorage <em>Storage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Storage</em>'.
	 * @see org.scaledl.overview.specification.ComputingResourceDescriptor#getStorage()
	 * @see #getComputingResourceDescriptor()
	 * @generated
	 */
	EAttribute getComputingResourceDescriptor_Storage();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.PlatformServiceDescriptor <em>Platform Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Platform Service Descriptor</em>'.
	 * @see org.scaledl.overview.specification.PlatformServiceDescriptor
	 * @generated
	 */
	EClass getPlatformServiceDescriptor();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.PlatformRuntimeServiceDescriptor <em>Platform Runtime Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Platform Runtime Service Descriptor</em>'.
	 * @see org.scaledl.overview.specification.PlatformRuntimeServiceDescriptor
	 * @generated
	 */
	EClass getPlatformRuntimeServiceDescriptor();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.PlatformSupportServiceDescriptor <em>Platform Support Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Platform Support Service Descriptor</em>'.
	 * @see org.scaledl.overview.specification.PlatformSupportServiceDescriptor
	 * @generated
	 */
	EClass getPlatformSupportServiceDescriptor();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.ProvidedPlatformServiceDescriptor <em>Provided Platform Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provided Platform Service Descriptor</em>'.
	 * @see org.scaledl.overview.specification.ProvidedPlatformServiceDescriptor
	 * @generated
	 */
	EClass getProvidedPlatformServiceDescriptor();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.specification.ProvidedPlatformServiceDescriptor#getInfrastructureServiceDescriptor <em>Infrastructure Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Infrastructure Service Descriptor</em>'.
	 * @see org.scaledl.overview.specification.ProvidedPlatformServiceDescriptor#getInfrastructureServiceDescriptor()
	 * @see #getProvidedPlatformServiceDescriptor()
	 * @generated
	 */
	EReference getProvidedPlatformServiceDescriptor_InfrastructureServiceDescriptor();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.ProvidedPlatformRuntimeServiceDescriptor <em>Provided Platform Runtime Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provided Platform Runtime Service Descriptor</em>'.
	 * @see org.scaledl.overview.specification.ProvidedPlatformRuntimeServiceDescriptor
	 * @generated
	 */
	EClass getProvidedPlatformRuntimeServiceDescriptor();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.ProvidedPlatformSupportServiceDescriptor <em>Provided Platform Support Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provided Platform Support Service Descriptor</em>'.
	 * @see org.scaledl.overview.specification.ProvidedPlatformSupportServiceDescriptor
	 * @generated
	 */
	EClass getProvidedPlatformSupportServiceDescriptor();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.SoftwareServiceDescriptor <em>Software Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Software Service Descriptor</em>'.
	 * @see org.scaledl.overview.specification.SoftwareServiceDescriptor
	 * @generated
	 */
	EClass getSoftwareServiceDescriptor();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.ProvidedSoftwareServiceDescriptor <em>Provided Software Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provided Software Service Descriptor</em>'.
	 * @see org.scaledl.overview.specification.ProvidedSoftwareServiceDescriptor
	 * @generated
	 */
	EClass getProvidedSoftwareServiceDescriptor();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.specification.ProvidedSoftwareServiceDescriptor#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see org.scaledl.overview.specification.ProvidedSoftwareServiceDescriptor#getOperations()
	 * @see #getProvidedSoftwareServiceDescriptor()
	 * @generated
	 */
	EReference getProvidedSoftwareServiceDescriptor_Operations();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.specification.ExternalSoftwareServiceDescriptor <em>External Software Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Software Service Descriptor</em>'.
	 * @see org.scaledl.overview.specification.ExternalSoftwareServiceDescriptor
	 * @generated
	 */
	EClass getExternalSoftwareServiceDescriptor();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SpecificationFactory getSpecificationFactory();

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
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.SpecificationImpl <em>Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.SpecificationImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getSpecification()
		 * @generated
		 */
		EClass SPECIFICATION = eINSTANCE.getSpecification();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.SystemSpecificationImpl <em>System Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.SystemSpecificationImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getSystemSpecification()
		 * @generated
		 */
		EClass SYSTEM_SPECIFICATION = eINSTANCE.getSystemSpecification();

		/**
		 * The meta object literal for the '<em><b>Descriptors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_SPECIFICATION__DESCRIPTORS = eINSTANCE.getSystemSpecification_Descriptors();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.CloudSpecificationImpl <em>Cloud Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.CloudSpecificationImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getCloudSpecification()
		 * @generated
		 */
		EClass CLOUD_SPECIFICATION = eINSTANCE.getCloudSpecification();

		/**
		 * The meta object literal for the '<em><b>Descriptor</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_SPECIFICATION__DESCRIPTOR = eINSTANCE.getCloudSpecification_Descriptor();

		/**
		 * The meta object literal for the '<em><b>Infrastructure Service Descriptors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_SPECIFICATION__INFRASTRUCTURE_SERVICE_DESCRIPTORS = eINSTANCE.getCloudSpecification_InfrastructureServiceDescriptors();

		/**
		 * The meta object literal for the '<em><b>Platform Service Descriptors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_SPECIFICATION__PLATFORM_SERVICE_DESCRIPTORS = eINSTANCE.getCloudSpecification_PlatformServiceDescriptors();

		/**
		 * The meta object literal for the '<em><b>Software Service Descriptors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_SPECIFICATION__SOFTWARE_SERVICE_DESCRIPTORS = eINSTANCE.getCloudSpecification_SoftwareServiceDescriptors();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.ServiceSpecificationImpl <em>Service Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.ServiceSpecificationImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getServiceSpecification()
		 * @generated
		 */
		EClass SERVICE_SPECIFICATION = eINSTANCE.getServiceSpecification();

		/**
		 * The meta object literal for the '<em><b>Service Descriptors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS = eINSTANCE.getServiceSpecification_ServiceDescriptors();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.Descriptor <em>Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.Descriptor
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getDescriptor()
		 * @generated
		 */
		EClass DESCRIPTOR = eINSTANCE.getDescriptor();

		/**
		 * The meta object literal for the '<em><b>Provider ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIPTOR__PROVIDER_ID = eINSTANCE.getDescriptor_ProviderID();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.CloudEnvironmentDescriptorImpl <em>Cloud Environment Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.CloudEnvironmentDescriptorImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getCloudEnvironmentDescriptor()
		 * @generated
		 */
		EClass CLOUD_ENVIRONMENT_DESCRIPTOR = eINSTANCE.getCloudEnvironmentDescriptor();

		/**
		 * The meta object literal for the '<em><b>Availability Zones</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES = eINSTANCE.getCloudEnvironmentDescriptor_AvailabilityZones();

		/**
		 * The meta object literal for the '<em><b>Regions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS = eINSTANCE.getCloudEnvironmentDescriptor_Regions();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.RegionDescriptorImpl <em>Region Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.RegionDescriptorImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getRegionDescriptor()
		 * @generated
		 */
		EClass REGION_DESCRIPTOR = eINSTANCE.getRegionDescriptor();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.AvailabilityZoneDescriptorImpl <em>Availability Zone Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.AvailabilityZoneDescriptorImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getAvailabilityZoneDescriptor()
		 * @generated
		 */
		EClass AVAILABILITY_ZONE_DESCRIPTOR = eINSTANCE.getAvailabilityZoneDescriptor();

		/**
		 * The meta object literal for the '<em><b>Network Infrastructure Service Descriptor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AVAILABILITY_ZONE_DESCRIPTOR__NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR = eINSTANCE.getAvailabilityZoneDescriptor_NetworkInfrastructureServiceDescriptor();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.ServiceDescriptor <em>Service Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.ServiceDescriptor
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getServiceDescriptor()
		 * @generated
		 */
		EClass SERVICE_DESCRIPTOR = eINSTANCE.getServiceDescriptor();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.ProvidedServiceDescriptor <em>Provided Service Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.ProvidedServiceDescriptor
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getProvidedServiceDescriptor()
		 * @generated
		 */
		EClass PROVIDED_SERVICE_DESCRIPTOR = eINSTANCE.getProvidedServiceDescriptor();

		/**
		 * The meta object literal for the '<em><b>Sla</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROVIDED_SERVICE_DESCRIPTOR__SLA = eINSTANCE.getProvidedServiceDescriptor_Sla();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.InfrastructureServiceDescriptor <em>Infrastructure Service Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.InfrastructureServiceDescriptor
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getInfrastructureServiceDescriptor()
		 * @generated
		 */
		EClass INFRASTRUCTURE_SERVICE_DESCRIPTOR = eINSTANCE.getInfrastructureServiceDescriptor();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.NetworkInfrastructureServiceDescriptorImpl <em>Network Infrastructure Service Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.NetworkInfrastructureServiceDescriptorImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getNetworkInfrastructureServiceDescriptor()
		 * @generated
		 */
		EClass NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR = eINSTANCE.getNetworkInfrastructureServiceDescriptor();

		/**
		 * The meta object literal for the '<em><b>Bandwidth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH = eINSTANCE.getNetworkInfrastructureServiceDescriptor_Bandwidth();

		/**
		 * The meta object literal for the '<em><b>Latency</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY = eINSTANCE.getNetworkInfrastructureServiceDescriptor_Latency();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.ComputingInfrastructureServiceDescriptorImpl <em>Computing Infrastructure Service Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.ComputingInfrastructureServiceDescriptorImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getComputingInfrastructureServiceDescriptor()
		 * @generated
		 */
		EClass COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR = eINSTANCE.getComputingInfrastructureServiceDescriptor();

		/**
		 * The meta object literal for the '<em><b>Computing Resource Descriptors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__COMPUTING_RESOURCE_DESCRIPTORS = eINSTANCE.getComputingInfrastructureServiceDescriptor_ComputingResourceDescriptors();

		/**
		 * The meta object literal for the '<em><b>General Purpose</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__GENERAL_PURPOSE = eINSTANCE.getComputingInfrastructureServiceDescriptor_GeneralPurpose();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.ComputingResourceDescriptorImpl <em>Computing Resource Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.ComputingResourceDescriptorImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getComputingResourceDescriptor()
		 * @generated
		 */
		EClass COMPUTING_RESOURCE_DESCRIPTOR = eINSTANCE.getComputingResourceDescriptor();

		/**
		 * The meta object literal for the '<em><b>Editable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPUTING_RESOURCE_DESCRIPTOR__EDITABLE = eINSTANCE.getComputingResourceDescriptor_Editable();

		/**
		 * The meta object literal for the '<em><b>Memory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPUTING_RESOURCE_DESCRIPTOR__MEMORY = eINSTANCE.getComputingResourceDescriptor_Memory();

		/**
		 * The meta object literal for the '<em><b>Cpu</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPUTING_RESOURCE_DESCRIPTOR__CPU = eINSTANCE.getComputingResourceDescriptor_Cpu();

		/**
		 * The meta object literal for the '<em><b>Cpu Units</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPUTING_RESOURCE_DESCRIPTOR__CPU_UNITS = eINSTANCE.getComputingResourceDescriptor_CpuUnits();

		/**
		 * The meta object literal for the '<em><b>Storage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPUTING_RESOURCE_DESCRIPTOR__STORAGE = eINSTANCE.getComputingResourceDescriptor_Storage();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.PlatformServiceDescriptorImpl <em>Platform Service Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.PlatformServiceDescriptorImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getPlatformServiceDescriptor()
		 * @generated
		 */
		EClass PLATFORM_SERVICE_DESCRIPTOR = eINSTANCE.getPlatformServiceDescriptor();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.PlatformRuntimeServiceDescriptorImpl <em>Platform Runtime Service Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.PlatformRuntimeServiceDescriptorImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getPlatformRuntimeServiceDescriptor()
		 * @generated
		 */
		EClass PLATFORM_RUNTIME_SERVICE_DESCRIPTOR = eINSTANCE.getPlatformRuntimeServiceDescriptor();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.PlatformSupportServiceDescriptorImpl <em>Platform Support Service Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.PlatformSupportServiceDescriptorImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getPlatformSupportServiceDescriptor()
		 * @generated
		 */
		EClass PLATFORM_SUPPORT_SERVICE_DESCRIPTOR = eINSTANCE.getPlatformSupportServiceDescriptor();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.ProvidedPlatformServiceDescriptorImpl <em>Provided Platform Service Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.ProvidedPlatformServiceDescriptorImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getProvidedPlatformServiceDescriptor()
		 * @generated
		 */
		EClass PROVIDED_PLATFORM_SERVICE_DESCRIPTOR = eINSTANCE.getProvidedPlatformServiceDescriptor();

		/**
		 * The meta object literal for the '<em><b>Infrastructure Service Descriptor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__INFRASTRUCTURE_SERVICE_DESCRIPTOR = eINSTANCE.getProvidedPlatformServiceDescriptor_InfrastructureServiceDescriptor();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.ProvidedPlatformRuntimeServiceDescriptorImpl <em>Provided Platform Runtime Service Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.ProvidedPlatformRuntimeServiceDescriptorImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getProvidedPlatformRuntimeServiceDescriptor()
		 * @generated
		 */
		EClass PROVIDED_PLATFORM_RUNTIME_SERVICE_DESCRIPTOR = eINSTANCE.getProvidedPlatformRuntimeServiceDescriptor();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.ProvidedPlatformSupportServiceDescriptorImpl <em>Provided Platform Support Service Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.ProvidedPlatformSupportServiceDescriptorImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getProvidedPlatformSupportServiceDescriptor()
		 * @generated
		 */
		EClass PROVIDED_PLATFORM_SUPPORT_SERVICE_DESCRIPTOR = eINSTANCE.getProvidedPlatformSupportServiceDescriptor();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.SoftwareServiceDescriptorImpl <em>Software Service Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.SoftwareServiceDescriptorImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getSoftwareServiceDescriptor()
		 * @generated
		 */
		EClass SOFTWARE_SERVICE_DESCRIPTOR = eINSTANCE.getSoftwareServiceDescriptor();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.ProvidedSoftwareServiceDescriptorImpl <em>Provided Software Service Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.ProvidedSoftwareServiceDescriptorImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getProvidedSoftwareServiceDescriptor()
		 * @generated
		 */
		EClass PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR = eINSTANCE.getProvidedSoftwareServiceDescriptor();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__OPERATIONS = eINSTANCE.getProvidedSoftwareServiceDescriptor_Operations();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.specification.impl.ExternalSoftwareServiceDescriptorImpl <em>External Software Service Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.specification.impl.ExternalSoftwareServiceDescriptorImpl
		 * @see org.scaledl.overview.specification.impl.SpecificationPackageImpl#getExternalSoftwareServiceDescriptor()
		 * @generated
		 */
		EClass EXTERNAL_SOFTWARE_SERVICE_DESCRIPTOR = eINSTANCE.getExternalSoftwareServiceDescriptor();

	}

} //SpecificationPackage
