/**
 */
package eu.cloudscaleproject.env.csm.architecture;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage
 * @generated
 */
public interface ArchitectureFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	ArchitectureFactory eINSTANCE = eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Architecture</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Architecture</em>'.
	 * @generated
	 */
	Architecture createArchitecture();

	/**
	 * Returns a new object of class '<em>Cloud Environment</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Cloud Environment</em>'.
	 * @generated
	 */
	CloudEnvironment createCloudEnvironment();

	/**
	 * Returns a new object of class '<em>Infrastructure Layer</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Infrastructure Layer</em>'.
	 * @generated
	 */
	InfrastructureLayer createInfrastructureLayer();

	/**
	 * Returns a new object of class '<em>Platform Layer</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Platform Layer</em>'.
	 * @generated
	 */
	PlatformLayer createPlatformLayer();

	/**
	 * Returns a new object of class '<em>Software Layer</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Software Layer</em>'.
	 * @generated
	 */
	SoftwareLayer createSoftwareLayer();

	/**
	 * Returns a new object of class '<em>Deployable Runtime Service</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Deployable Runtime Service</em>'.
	 * @generated
	 */
	DeployableRuntimeService createDeployableRuntimeService();

	/**
	 * Returns a new object of class '<em>Deployable Support Service</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Deployable Support Service</em>'.
	 * @generated
	 */
	DeployableSupportService createDeployableSupportService();

	/**
	 * Returns a new object of class '<em>External Runtime Service</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>External Runtime Service</em>'.
	 * @generated
	 */
	ExternalRuntimeService createExternalRuntimeService();

	/**
	 * Returns a new object of class '<em>External Support Service</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>External Support Service</em>'.
	 * @generated
	 */
	ExternalSupportService createExternalSupportService();

	/**
	 * Returns a new object of class '<em>External Software Service</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>External Software Service</em>'.
	 * @generated
	 */
	ExternalSoftwareService createExternalSoftwareService();

	/**
	 * Returns a new object of class '<em>Deployable Software Service</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Deployable Software Service</em>'.
	 * @generated
	 */
	DeployableSoftwareService createDeployableSoftwareService();

	/**
	 * Returns a new object of class '<em>Computing Infrastructure Service</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Computing Infrastructure Service</em>'.
	 * @generated
	 */
	ComputingInfrastructureService createComputingInfrastructureService();

	/**
	 * Returns a new object of class '<em>Internal Connection</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Internal Connection</em>'.
	 * @generated
	 */
	InternalConnection createInternalConnection();

	/**
	 * Returns a new object of class '<em>Hybrid Connection</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Hybrid Connection</em>'.
	 * @generated
	 */
	HybridConnection createHybridConnection();

	/**
	 * Returns a new object of class '<em>External Connection</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>External Connection</em>'.
	 * @generated
	 */
	ExternalConnection createExternalConnection();

	/**
	 * Returns a new object of class '<em>Service Proxy</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Service Proxy</em>'.
	 * @generated
	 */
	ServiceProxy createServiceProxy();

	/**
	 * Returns a new object of class '<em>Usage Proxy</em>'.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
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

} // ArchitectureFactory
