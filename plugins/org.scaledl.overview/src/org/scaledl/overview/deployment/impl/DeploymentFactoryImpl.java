/**
 */
package org.scaledl.overview.deployment.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.scaledl.overview.deployment.ClusteredComputingEnvironment;
import org.scaledl.overview.deployment.ComputingEnvironment;
import org.scaledl.overview.deployment.Deployment;
import org.scaledl.overview.deployment.DeploymentFactory;
import org.scaledl.overview.deployment.DeploymentPackage;
import org.scaledl.overview.deployment.GenericDeployment;
import org.scaledl.overview.deployment.LoadBalancer;
import org.scaledl.overview.deployment.RuntimeDeployment;
import org.scaledl.overview.deployment.ScalableComputingEnvironment;
import org.scaledl.overview.deployment.ScalingCapacityType;
import org.scaledl.overview.deployment.ScalingManager;
import org.scaledl.overview.deployment.ScalingPolicy;
import org.scaledl.overview.deployment.ScalingPolicyType;
import org.scaledl.overview.deployment.SchedulingPolicy;
import org.scaledl.overview.deployment.ServiceDeployment;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DeploymentFactoryImpl extends EFactoryImpl implements DeploymentFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DeploymentFactory init() {
		try {
			DeploymentFactory theDeploymentFactory = (DeploymentFactory)EPackage.Registry.INSTANCE.getEFactory(DeploymentPackage.eNS_URI);
			if (theDeploymentFactory != null) {
				return theDeploymentFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DeploymentFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentFactoryImpl() {
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
			case DeploymentPackage.DEPLOYMENT: return createDeployment();
			case DeploymentPackage.SERVICE_DEPLOYMENT: return createServiceDeployment();
			case DeploymentPackage.GENERIC_DEPLOYMENT: return createGenericDeployment();
			case DeploymentPackage.RUNTIME_DEPLOYMENT: return createRuntimeDeployment();
			case DeploymentPackage.COMPUTING_ENVIRONMENT: return createComputingEnvironment();
			case DeploymentPackage.CLUSTERED_COMPUTING_ENVIRONMENT: return createClusteredComputingEnvironment();
			case DeploymentPackage.SCALABLE_COMPUTING_ENVIRONMENT: return createScalableComputingEnvironment();
			case DeploymentPackage.SCALING_MANAGER: return createScalingManager();
			case DeploymentPackage.SCALING_POLICY: return createScalingPolicy();
			case DeploymentPackage.LOAD_BALANCER: return createLoadBalancer();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case DeploymentPackage.SCHEDULING_POLICY:
				return createSchedulingPolicyFromString(eDataType, initialValue);
			case DeploymentPackage.SCALING_POLICY_TYPE:
				return createScalingPolicyTypeFromString(eDataType, initialValue);
			case DeploymentPackage.SCALING_CAPACITY_TYPE:
				return createScalingCapacityTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case DeploymentPackage.SCHEDULING_POLICY:
				return convertSchedulingPolicyToString(eDataType, instanceValue);
			case DeploymentPackage.SCALING_POLICY_TYPE:
				return convertScalingPolicyTypeToString(eDataType, instanceValue);
			case DeploymentPackage.SCALING_CAPACITY_TYPE:
				return convertScalingCapacityTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Deployment createDeployment() {
		DeploymentImpl deployment = new DeploymentImpl();
		return deployment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceDeployment createServiceDeployment() {
		ServiceDeploymentImpl serviceDeployment = new ServiceDeploymentImpl();
		return serviceDeployment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericDeployment createGenericDeployment() {
		GenericDeploymentImpl genericDeployment = new GenericDeploymentImpl();
		return genericDeployment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuntimeDeployment createRuntimeDeployment() {
		RuntimeDeploymentImpl runtimeDeployment = new RuntimeDeploymentImpl();
		return runtimeDeployment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComputingEnvironment createComputingEnvironment() {
		ComputingEnvironmentImpl computingEnvironment = new ComputingEnvironmentImpl();
		return computingEnvironment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClusteredComputingEnvironment createClusteredComputingEnvironment() {
		ClusteredComputingEnvironmentImpl clusteredComputingEnvironment = new ClusteredComputingEnvironmentImpl();
		return clusteredComputingEnvironment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScalableComputingEnvironment createScalableComputingEnvironment() {
		ScalableComputingEnvironmentImpl scalableComputingEnvironment = new ScalableComputingEnvironmentImpl();
		return scalableComputingEnvironment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScalingManager createScalingManager() {
		ScalingManagerImpl scalingManager = new ScalingManagerImpl();
		return scalingManager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScalingPolicy createScalingPolicy() {
		ScalingPolicyImpl scalingPolicy = new ScalingPolicyImpl();
		return scalingPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoadBalancer createLoadBalancer() {
		LoadBalancerImpl loadBalancer = new LoadBalancerImpl();
		return loadBalancer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SchedulingPolicy createSchedulingPolicyFromString(EDataType eDataType, String initialValue) {
		SchedulingPolicy result = SchedulingPolicy.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSchedulingPolicyToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScalingPolicyType createScalingPolicyTypeFromString(EDataType eDataType, String initialValue) {
		ScalingPolicyType result = ScalingPolicyType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertScalingPolicyTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScalingCapacityType createScalingCapacityTypeFromString(EDataType eDataType, String initialValue) {
		ScalingCapacityType result = ScalingCapacityType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertScalingCapacityTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentPackage getDeploymentPackage() {
		return (DeploymentPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DeploymentPackage getPackage() {
		return DeploymentPackage.eINSTANCE;
	}

} //DeploymentFactoryImpl
