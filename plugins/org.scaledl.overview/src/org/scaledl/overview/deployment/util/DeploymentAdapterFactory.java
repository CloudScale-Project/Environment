/**
 */
package org.scaledl.overview.deployment.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.scaledl.overview.core.Entity;

import org.scaledl.overview.deployment.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.scaledl.overview.deployment.DeploymentPackage
 * @generated
 */
public class DeploymentAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DeploymentPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = DeploymentPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeploymentSwitch<Adapter> modelSwitch =
		new DeploymentSwitch<Adapter>() {
			@Override
			public Adapter caseDeployment(Deployment object) {
				return createDeploymentAdapter();
			}
			@Override
			public Adapter caseServiceDeployment(ServiceDeployment object) {
				return createServiceDeploymentAdapter();
			}
			@Override
			public Adapter caseGenericDeployment(GenericDeployment object) {
				return createGenericDeploymentAdapter();
			}
			@Override
			public Adapter caseRuntimeDeployment(RuntimeDeployment object) {
				return createRuntimeDeploymentAdapter();
			}
			@Override
			public Adapter caseComputingEnvironment(ComputingEnvironment object) {
				return createComputingEnvironmentAdapter();
			}
			@Override
			public Adapter caseClusteredComputingEnvironment(ClusteredComputingEnvironment object) {
				return createClusteredComputingEnvironmentAdapter();
			}
			@Override
			public Adapter caseScalableComputingEnvironment(ScalableComputingEnvironment object) {
				return createScalableComputingEnvironmentAdapter();
			}
			@Override
			public Adapter caseScalingManager(ScalingManager object) {
				return createScalingManagerAdapter();
			}
			@Override
			public Adapter caseScalingPolicy(ScalingPolicy object) {
				return createScalingPolicyAdapter();
			}
			@Override
			public Adapter caseLoadBalancer(LoadBalancer object) {
				return createLoadBalancerAdapter();
			}
			@Override
			public Adapter caseEntity(Entity object) {
				return createEntityAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.deployment.Deployment <em>Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.deployment.Deployment
	 * @generated
	 */
	public Adapter createDeploymentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.deployment.ServiceDeployment <em>Service Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.deployment.ServiceDeployment
	 * @generated
	 */
	public Adapter createServiceDeploymentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.deployment.GenericDeployment <em>Generic Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.deployment.GenericDeployment
	 * @generated
	 */
	public Adapter createGenericDeploymentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.deployment.RuntimeDeployment <em>Runtime Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.deployment.RuntimeDeployment
	 * @generated
	 */
	public Adapter createRuntimeDeploymentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.deployment.ComputingEnvironment <em>Computing Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.deployment.ComputingEnvironment
	 * @generated
	 */
	public Adapter createComputingEnvironmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.deployment.ClusteredComputingEnvironment <em>Clustered Computing Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.deployment.ClusteredComputingEnvironment
	 * @generated
	 */
	public Adapter createClusteredComputingEnvironmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.deployment.ScalableComputingEnvironment <em>Scalable Computing Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.deployment.ScalableComputingEnvironment
	 * @generated
	 */
	public Adapter createScalableComputingEnvironmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.deployment.ScalingManager <em>Scaling Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.deployment.ScalingManager
	 * @generated
	 */
	public Adapter createScalingManagerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.deployment.ScalingPolicy <em>Scaling Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.deployment.ScalingPolicy
	 * @generated
	 */
	public Adapter createScalingPolicyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.deployment.LoadBalancer <em>Load Balancer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.deployment.LoadBalancer
	 * @generated
	 */
	public Adapter createLoadBalancerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.core.Entity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.core.Entity
	 * @generated
	 */
	public Adapter createEntityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //DeploymentAdapterFactory
