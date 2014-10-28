/**
 */
package org.scaledl.overview.deployment;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.scaledl.overview.deployment.DeploymentPackage
 * @generated
 */
public interface DeploymentFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DeploymentFactory eINSTANCE = org.scaledl.overview.deployment.impl.DeploymentFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Deployment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deployment</em>'.
	 * @generated
	 */
	Deployment createDeployment();

	/**
	 * Returns a new object of class '<em>Service Deployment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Deployment</em>'.
	 * @generated
	 */
	ServiceDeployment createServiceDeployment();

	/**
	 * Returns a new object of class '<em>Generic Deployment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generic Deployment</em>'.
	 * @generated
	 */
	GenericDeployment createGenericDeployment();

	/**
	 * Returns a new object of class '<em>Runtime Deployment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Runtime Deployment</em>'.
	 * @generated
	 */
	RuntimeDeployment createRuntimeDeployment();

	/**
	 * Returns a new object of class '<em>Computing Environment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Computing Environment</em>'.
	 * @generated
	 */
	ComputingEnvironment createComputingEnvironment();

	/**
	 * Returns a new object of class '<em>Clustered Computing Environment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Clustered Computing Environment</em>'.
	 * @generated
	 */
	ClusteredComputingEnvironment createClusteredComputingEnvironment();

	/**
	 * Returns a new object of class '<em>Scalable Computing Environment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scalable Computing Environment</em>'.
	 * @generated
	 */
	ScalableComputingEnvironment createScalableComputingEnvironment();

	/**
	 * Returns a new object of class '<em>Scaling Manager</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scaling Manager</em>'.
	 * @generated
	 */
	ScalingManager createScalingManager();

	/**
	 * Returns a new object of class '<em>Scaling Policy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scaling Policy</em>'.
	 * @generated
	 */
	ScalingPolicy createScalingPolicy();

	/**
	 * Returns a new object of class '<em>Load Balancer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Load Balancer</em>'.
	 * @generated
	 */
	LoadBalancer createLoadBalancer();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DeploymentPackage getDeploymentPackage();

} //DeploymentFactory
