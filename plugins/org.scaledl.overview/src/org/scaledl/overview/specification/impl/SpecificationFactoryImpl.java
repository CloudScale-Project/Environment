/**
 */
package org.scaledl.overview.specification.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.scaledl.overview.specification.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SpecificationFactoryImpl extends EFactoryImpl implements SpecificationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SpecificationFactory init() {
		try {
			SpecificationFactory theSpecificationFactory = (SpecificationFactory)EPackage.Registry.INSTANCE.getEFactory(SpecificationPackage.eNS_URI);
			if (theSpecificationFactory != null) {
				return theSpecificationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SpecificationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecificationFactoryImpl() {
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
			case SpecificationPackage.SYSTEM_SPECIFICATION: return createSystemSpecification();
			case SpecificationPackage.CLOUD_SPECIFICATION: return createCloudSpecification();
			case SpecificationPackage.SERVICE_SPECIFICATION: return createServiceSpecification();
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR: return createCloudEnvironmentDescriptor();
			case SpecificationPackage.REGION_DESCRIPTOR: return createRegionDescriptor();
			case SpecificationPackage.AVAILABILITY_ZONE_DESCRIPTOR: return createAvailabilityZoneDescriptor();
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR: return createNetworkInfrastructureServiceDescriptor();
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR: return createComputingInfrastructureServiceDescriptor();
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR: return createComputingResourceDescriptor();
			case SpecificationPackage.PLATFORM_SERVICE_DESCRIPTOR: return createPlatformServiceDescriptor();
			case SpecificationPackage.PLATFORM_RUNTIME_SERVICE_DESCRIPTOR: return createPlatformRuntimeServiceDescriptor();
			case SpecificationPackage.PLATFORM_SUPPORT_SERVICE_DESCRIPTOR: return createPlatformSupportServiceDescriptor();
			case SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR: return createProvidedPlatformServiceDescriptor();
			case SpecificationPackage.PROVIDED_PLATFORM_RUNTIME_SERVICE_DESCRIPTOR: return createProvidedPlatformRuntimeServiceDescriptor();
			case SpecificationPackage.PROVIDED_PLATFORM_SUPPORT_SERVICE_DESCRIPTOR: return createProvidedPlatformSupportServiceDescriptor();
			case SpecificationPackage.SOFTWARE_SERVICE_DESCRIPTOR: return createSoftwareServiceDescriptor();
			case SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR: return createProvidedSoftwareServiceDescriptor();
			case SpecificationPackage.EXTERNAL_SOFTWARE_SERVICE_DESCRIPTOR: return createExternalSoftwareServiceDescriptor();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemSpecification createSystemSpecification() {
		SystemSpecificationImpl systemSpecification = new SystemSpecificationImpl();
		return systemSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CloudSpecification createCloudSpecification() {
		CloudSpecificationImpl cloudSpecification = new CloudSpecificationImpl();
		return cloudSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceSpecification createServiceSpecification() {
		ServiceSpecificationImpl serviceSpecification = new ServiceSpecificationImpl();
		return serviceSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CloudEnvironmentDescriptor createCloudEnvironmentDescriptor() {
		CloudEnvironmentDescriptorImpl cloudEnvironmentDescriptor = new CloudEnvironmentDescriptorImpl();
		return cloudEnvironmentDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RegionDescriptor createRegionDescriptor() {
		RegionDescriptorImpl regionDescriptor = new RegionDescriptorImpl();
		return regionDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AvailabilityZoneDescriptor createAvailabilityZoneDescriptor() {
		AvailabilityZoneDescriptorImpl availabilityZoneDescriptor = new AvailabilityZoneDescriptorImpl();
		return availabilityZoneDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkInfrastructureServiceDescriptor createNetworkInfrastructureServiceDescriptor() {
		NetworkInfrastructureServiceDescriptorImpl networkInfrastructureServiceDescriptor = new NetworkInfrastructureServiceDescriptorImpl();
		return networkInfrastructureServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComputingInfrastructureServiceDescriptor createComputingInfrastructureServiceDescriptor() {
		ComputingInfrastructureServiceDescriptorImpl computingInfrastructureServiceDescriptor = new ComputingInfrastructureServiceDescriptorImpl();
		return computingInfrastructureServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComputingResourceDescriptor createComputingResourceDescriptor() {
		ComputingResourceDescriptorImpl computingResourceDescriptor = new ComputingResourceDescriptorImpl();
		return computingResourceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformServiceDescriptor createPlatformServiceDescriptor() {
		PlatformServiceDescriptorImpl platformServiceDescriptor = new PlatformServiceDescriptorImpl();
		return platformServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformRuntimeServiceDescriptor createPlatformRuntimeServiceDescriptor() {
		PlatformRuntimeServiceDescriptorImpl platformRuntimeServiceDescriptor = new PlatformRuntimeServiceDescriptorImpl();
		return platformRuntimeServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformSupportServiceDescriptor createPlatformSupportServiceDescriptor() {
		PlatformSupportServiceDescriptorImpl platformSupportServiceDescriptor = new PlatformSupportServiceDescriptorImpl();
		return platformSupportServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvidedPlatformServiceDescriptor createProvidedPlatformServiceDescriptor() {
		ProvidedPlatformServiceDescriptorImpl providedPlatformServiceDescriptor = new ProvidedPlatformServiceDescriptorImpl();
		return providedPlatformServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvidedPlatformRuntimeServiceDescriptor createProvidedPlatformRuntimeServiceDescriptor() {
		ProvidedPlatformRuntimeServiceDescriptorImpl providedPlatformRuntimeServiceDescriptor = new ProvidedPlatformRuntimeServiceDescriptorImpl();
		return providedPlatformRuntimeServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvidedPlatformSupportServiceDescriptor createProvidedPlatformSupportServiceDescriptor() {
		ProvidedPlatformSupportServiceDescriptorImpl providedPlatformSupportServiceDescriptor = new ProvidedPlatformSupportServiceDescriptorImpl();
		return providedPlatformSupportServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareServiceDescriptor createSoftwareServiceDescriptor() {
		SoftwareServiceDescriptorImpl softwareServiceDescriptor = new SoftwareServiceDescriptorImpl();
		return softwareServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvidedSoftwareServiceDescriptor createProvidedSoftwareServiceDescriptor() {
		ProvidedSoftwareServiceDescriptorImpl providedSoftwareServiceDescriptor = new ProvidedSoftwareServiceDescriptorImpl();
		return providedSoftwareServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalSoftwareServiceDescriptor createExternalSoftwareServiceDescriptor() {
		ExternalSoftwareServiceDescriptorImpl externalSoftwareServiceDescriptor = new ExternalSoftwareServiceDescriptorImpl();
		return externalSoftwareServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecificationPackage getSpecificationPackage() {
		return (SpecificationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SpecificationPackage getPackage() {
		return SpecificationPackage.eINSTANCE;
	}

} //SpecificationFactoryImpl
