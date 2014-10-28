/**
 */
package org.scaledl.overview.specification;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.scaledl.overview.specification.SpecificationPackage
 * @generated
 */
public interface SpecificationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SpecificationFactory eINSTANCE = org.scaledl.overview.specification.impl.SpecificationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>System Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>System Specification</em>'.
	 * @generated
	 */
	SystemSpecification createSystemSpecification();

	/**
	 * Returns a new object of class '<em>Cloud Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cloud Specification</em>'.
	 * @generated
	 */
	CloudSpecification createCloudSpecification();

	/**
	 * Returns a new object of class '<em>Service Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Specification</em>'.
	 * @generated
	 */
	ServiceSpecification createServiceSpecification();

	/**
	 * Returns a new object of class '<em>Cloud Environment Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cloud Environment Descriptor</em>'.
	 * @generated
	 */
	CloudEnvironmentDescriptor createCloudEnvironmentDescriptor();

	/**
	 * Returns a new object of class '<em>Region Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Region Descriptor</em>'.
	 * @generated
	 */
	RegionDescriptor createRegionDescriptor();

	/**
	 * Returns a new object of class '<em>Availability Zone Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Availability Zone Descriptor</em>'.
	 * @generated
	 */
	AvailabilityZoneDescriptor createAvailabilityZoneDescriptor();

	/**
	 * Returns a new object of class '<em>Network Infrastructure Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Network Infrastructure Service Descriptor</em>'.
	 * @generated
	 */
	NetworkInfrastructureServiceDescriptor createNetworkInfrastructureServiceDescriptor();

	/**
	 * Returns a new object of class '<em>Computing Infrastructure Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Computing Infrastructure Service Descriptor</em>'.
	 * @generated
	 */
	ComputingInfrastructureServiceDescriptor createComputingInfrastructureServiceDescriptor();

	/**
	 * Returns a new object of class '<em>Computing Resource Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Computing Resource Descriptor</em>'.
	 * @generated
	 */
	ComputingResourceDescriptor createComputingResourceDescriptor();

	/**
	 * Returns a new object of class '<em>Platform Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Platform Service Descriptor</em>'.
	 * @generated
	 */
	PlatformServiceDescriptor createPlatformServiceDescriptor();

	/**
	 * Returns a new object of class '<em>Platform Runtime Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Platform Runtime Service Descriptor</em>'.
	 * @generated
	 */
	PlatformRuntimeServiceDescriptor createPlatformRuntimeServiceDescriptor();

	/**
	 * Returns a new object of class '<em>Platform Support Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Platform Support Service Descriptor</em>'.
	 * @generated
	 */
	PlatformSupportServiceDescriptor createPlatformSupportServiceDescriptor();

	/**
	 * Returns a new object of class '<em>Provided Platform Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Provided Platform Service Descriptor</em>'.
	 * @generated
	 */
	ProvidedPlatformServiceDescriptor createProvidedPlatformServiceDescriptor();

	/**
	 * Returns a new object of class '<em>Provided Platform Runtime Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Provided Platform Runtime Service Descriptor</em>'.
	 * @generated
	 */
	ProvidedPlatformRuntimeServiceDescriptor createProvidedPlatformRuntimeServiceDescriptor();

	/**
	 * Returns a new object of class '<em>Provided Platform Support Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Provided Platform Support Service Descriptor</em>'.
	 * @generated
	 */
	ProvidedPlatformSupportServiceDescriptor createProvidedPlatformSupportServiceDescriptor();

	/**
	 * Returns a new object of class '<em>Software Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Software Service Descriptor</em>'.
	 * @generated
	 */
	SoftwareServiceDescriptor createSoftwareServiceDescriptor();

	/**
	 * Returns a new object of class '<em>Provided Software Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Provided Software Service Descriptor</em>'.
	 * @generated
	 */
	ProvidedSoftwareServiceDescriptor createProvidedSoftwareServiceDescriptor();

	/**
	 * Returns a new object of class '<em>External Software Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>External Software Service Descriptor</em>'.
	 * @generated
	 */
	ExternalSoftwareServiceDescriptor createExternalSoftwareServiceDescriptor();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SpecificationPackage getSpecificationPackage();

} //SpecificationFactory
