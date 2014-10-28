/**
 */
package org.scaledl.overview.deployment.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.scaledl.overview.core.Entity;
import org.scaledl.overview.deployment.ClusteredComputingEnvironment;
import org.scaledl.overview.deployment.ComputingEnvironment;
import org.scaledl.overview.deployment.Deployment;
import org.scaledl.overview.deployment.DeploymentPackage;
import org.scaledl.overview.deployment.GenericDeployment;
import org.scaledl.overview.deployment.LoadBalancer;
import org.scaledl.overview.deployment.RuntimeDeployment;
import org.scaledl.overview.deployment.ScalableComputingEnvironment;
import org.scaledl.overview.deployment.ScalingManager;
import org.scaledl.overview.deployment.ScalingPolicy;
import org.scaledl.overview.deployment.ServiceDeployment;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.scaledl.overview.deployment.DeploymentPackage
 * @generated
 */
public class DeploymentSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DeploymentPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentSwitch() {
		if (modelPackage == null) {
			modelPackage = DeploymentPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case DeploymentPackage.DEPLOYMENT: {
				Deployment deployment = (Deployment)theEObject;
				T result = caseDeployment(deployment);
				if (result == null) result = caseEntity(deployment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DeploymentPackage.SERVICE_DEPLOYMENT: {
				ServiceDeployment serviceDeployment = (ServiceDeployment)theEObject;
				T result = caseServiceDeployment(serviceDeployment);
				if (result == null) result = caseEntity(serviceDeployment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DeploymentPackage.GENERIC_DEPLOYMENT: {
				GenericDeployment genericDeployment = (GenericDeployment)theEObject;
				T result = caseGenericDeployment(genericDeployment);
				if (result == null) result = caseServiceDeployment(genericDeployment);
				if (result == null) result = caseEntity(genericDeployment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DeploymentPackage.RUNTIME_DEPLOYMENT: {
				RuntimeDeployment runtimeDeployment = (RuntimeDeployment)theEObject;
				T result = caseRuntimeDeployment(runtimeDeployment);
				if (result == null) result = caseServiceDeployment(runtimeDeployment);
				if (result == null) result = caseEntity(runtimeDeployment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DeploymentPackage.COMPUTING_ENVIRONMENT: {
				ComputingEnvironment computingEnvironment = (ComputingEnvironment)theEObject;
				T result = caseComputingEnvironment(computingEnvironment);
				if (result == null) result = caseEntity(computingEnvironment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DeploymentPackage.CLUSTERED_COMPUTING_ENVIRONMENT: {
				ClusteredComputingEnvironment clusteredComputingEnvironment = (ClusteredComputingEnvironment)theEObject;
				T result = caseClusteredComputingEnvironment(clusteredComputingEnvironment);
				if (result == null) result = caseComputingEnvironment(clusteredComputingEnvironment);
				if (result == null) result = caseEntity(clusteredComputingEnvironment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DeploymentPackage.SCALABLE_COMPUTING_ENVIRONMENT: {
				ScalableComputingEnvironment scalableComputingEnvironment = (ScalableComputingEnvironment)theEObject;
				T result = caseScalableComputingEnvironment(scalableComputingEnvironment);
				if (result == null) result = caseClusteredComputingEnvironment(scalableComputingEnvironment);
				if (result == null) result = caseComputingEnvironment(scalableComputingEnvironment);
				if (result == null) result = caseEntity(scalableComputingEnvironment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DeploymentPackage.SCALING_MANAGER: {
				ScalingManager scalingManager = (ScalingManager)theEObject;
				T result = caseScalingManager(scalingManager);
				if (result == null) result = caseEntity(scalingManager);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DeploymentPackage.SCALING_POLICY: {
				ScalingPolicy scalingPolicy = (ScalingPolicy)theEObject;
				T result = caseScalingPolicy(scalingPolicy);
				if (result == null) result = caseEntity(scalingPolicy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DeploymentPackage.LOAD_BALANCER: {
				LoadBalancer loadBalancer = (LoadBalancer)theEObject;
				T result = caseLoadBalancer(loadBalancer);
				if (result == null) result = caseEntity(loadBalancer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deployment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deployment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeployment(Deployment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service Deployment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service Deployment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseServiceDeployment(ServiceDeployment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Deployment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Deployment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGenericDeployment(GenericDeployment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Runtime Deployment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Runtime Deployment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRuntimeDeployment(RuntimeDeployment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Computing Environment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Computing Environment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComputingEnvironment(ComputingEnvironment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Clustered Computing Environment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Clustered Computing Environment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClusteredComputingEnvironment(ClusteredComputingEnvironment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scalable Computing Environment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scalable Computing Environment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScalableComputingEnvironment(ScalableComputingEnvironment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scaling Manager</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scaling Manager</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScalingManager(ScalingManager object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scaling Policy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scaling Policy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScalingPolicy(ScalingPolicy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Load Balancer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Load Balancer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLoadBalancer(LoadBalancer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntity(Entity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //DeploymentSwitch
