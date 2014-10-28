/**
 */
package org.scaledl.overview.architecture;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.scaledl.overview.architecture.ArchitecturePackage
 * @generated
 */
public interface ArchitectureFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ArchitectureFactory eINSTANCE = org.scaledl.overview.architecture.impl.ArchitectureFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Architecture</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Architecture</em>'.
	 * @generated
	 */
	Architecture createArchitecture();

	/**
	 * Returns a new object of class '<em>Cloud Environment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cloud Environment</em>'.
	 * @generated
	 */
	CloudEnvironment createCloudEnvironment();

	/**
	 * Returns a new object of class '<em>Infrastructure Layer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Infrastructure Layer</em>'.
	 * @generated
	 */
	InfrastructureLayer createInfrastructureLayer();

	/**
	 * Returns a new object of class '<em>Platform Layer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Platform Layer</em>'.
	 * @generated
	 */
	PlatformLayer createPlatformLayer();

	/**
	 * Returns a new object of class '<em>Software Layer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Software Layer</em>'.
	 * @generated
	 */
	SoftwareLayer createSoftwareLayer();

	/**
	 * Returns a new object of class '<em>Computing Infrastructure Service</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Computing Infrastructure Service</em>'.
	 * @generated
	 */
	ComputingInfrastructureService createComputingInfrastructureService();

	/**
	 * Returns a new object of class '<em>Platform Runtime Service</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Platform Runtime Service</em>'.
	 * @generated
	 */
	PlatformRuntimeService createPlatformRuntimeService();

	/**
	 * Returns a new object of class '<em>Platform Support Service</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Platform Support Service</em>'.
	 * @generated
	 */
	PlatformSupportService createPlatformSupportService();

	/**
	 * Returns a new object of class '<em>Provided Platform Runtime Service</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Provided Platform Runtime Service</em>'.
	 * @generated
	 */
	ProvidedPlatformRuntimeService createProvidedPlatformRuntimeService();

	/**
	 * Returns a new object of class '<em>Provided Platform Support Service</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Provided Platform Support Service</em>'.
	 * @generated
	 */
	ProvidedPlatformSupportService createProvidedPlatformSupportService();

	/**
	 * Returns a new object of class '<em>Software Service</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Software Service</em>'.
	 * @generated
	 */
	SoftwareService createSoftwareService();

	/**
	 * Returns a new object of class '<em>Provided Software Service</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Provided Software Service</em>'.
	 * @generated
	 */
	ProvidedSoftwareService createProvidedSoftwareService();

	/**
	 * Returns a new object of class '<em>External Software Service</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>External Software Service</em>'.
	 * @generated
	 */
	ExternalSoftwareService createExternalSoftwareService();

	/**
	 * Returns a new object of class '<em>Internal Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Internal Connection</em>'.
	 * @generated
	 */
	InternalConnection createInternalConnection();

	/**
	 * Returns a new object of class '<em>External Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>External Connection</em>'.
	 * @generated
	 */
	ExternalConnection createExternalConnection();

	/**
	 * Returns a new object of class '<em>Service Proxy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Proxy</em>'.
	 * @generated
	 */
	ServiceProxy createServiceProxy();

	/**
	 * Returns a new object of class '<em>Usage Proxy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Usage Proxy</em>'.
	 * @generated
	 */
	UsageProxy createUsageProxy();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ArchitecturePackage getArchitecturePackage();

} //ArchitectureFactory
