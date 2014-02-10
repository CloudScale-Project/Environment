/**
 */
package eu.cloudscaleproject.env.csm.definition;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage
 * @generated
 */
public interface DefinitionFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	DefinitionFactory eINSTANCE = eu.cloudscaleproject.env.csm.definition.impl.DefinitionFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>System Definition</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>System Definition</em>'.
	 * @generated
	 */
	SystemDefinition createSystemDefinition();

	/**
	 * Returns a new object of class '<em>Cloud Definition</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Cloud Definition</em>'.
	 * @generated
	 */
	CloudDefinition createCloudDefinition();

	/**
	 * Returns a new object of class '<em>Software Definition</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Software Definition</em>'.
	 * @generated
	 */
	SoftwareDefinition createSoftwareDefinition();

	/**
	 * Returns a new object of class '<em>Descriptor</em>'.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Descriptor</em>'.
	 * @generated
	 */
	Descriptor createDescriptor();

	/**
	 * Returns a new object of class '<em>Cloud Environment Descriptor</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Cloud Environment Descriptor</em>'.
	 * @generated
	 */
	CloudEnvironmentDescriptor createCloudEnvironmentDescriptor();

	/**
	 * Returns a new object of class '<em>Region Descriptor</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Region Descriptor</em>'.
	 * @generated
	 */
	RegionDescriptor createRegionDescriptor();

	/**
	 * Returns a new object of class '<em>Availability Zone Descriptor</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Availability Zone Descriptor</em>'.
	 * @generated
	 */
	AvailabilityZoneDescriptor createAvailabilityZoneDescriptor();

	/**
	 * Returns a new object of class '<em>Infrastructure Service Descriptor</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Infrastructure Service Descriptor</em>'.
	 * @generated
	 */
	InfrastructureServiceDescriptor createInfrastructureServiceDescriptor();

	/**
	 * Returns a new object of class '<em>Network Infrastructure Service Descriptor</em>'.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Network Infrastructure Service Descriptor</em>'.
	 * @generated
	 */
	NetworkInfrastructureServiceDescriptor createNetworkInfrastructureServiceDescriptor();

	/**
	 * Returns a new object of class '
	 * <em>Computing Infrastructure Service Descriptor</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '
	 *         <em>Computing Infrastructure Service Descriptor</em>'.
	 * @generated
	 */
	ComputingInfrastructureServiceDescriptor createComputingInfrastructureServiceDescriptor();

	/**
	 * Returns a new object of class '<em>Deployable Runtime Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deployable Runtime Service Descriptor</em>'.
	 * @generated
	 */
	DeployableRuntimeServiceDescriptor createDeployableRuntimeServiceDescriptor();

	/**
	 * Returns a new object of class '<em>External Runtime Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>External Runtime Service Descriptor</em>'.
	 * @generated
	 */
	ExternalRuntimeServiceDescriptor createExternalRuntimeServiceDescriptor();

	/**
	 * Returns a new object of class '<em>Deployable Support Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deployable Support Service Descriptor</em>'.
	 * @generated
	 */
	DeployableSupportServiceDescriptor createDeployableSupportServiceDescriptor();

	/**
	 * Returns a new object of class '<em>External Support Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>External Support Service Descriptor</em>'.
	 * @generated
	 */
	ExternalSupportServiceDescriptor createExternalSupportServiceDescriptor();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DefinitionPackage getDefinitionPackage();

} // DefinitionFactory
