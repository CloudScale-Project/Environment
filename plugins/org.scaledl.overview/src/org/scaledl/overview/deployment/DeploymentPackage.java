/**
 */
package org.scaledl.overview.deployment;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.scaledl.overview.deployment.DeploymentFactory
 * @model kind="package"
 * @generated
 */
public interface DeploymentPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "deployment";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org.scaledl.overview/ScaleDLOverviewComponentModel/Deployment/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "deployment";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DeploymentPackage eINSTANCE = org.scaledl.overview.deployment.impl.DeploymentPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.scaledl.overview.deployment.impl.DeploymentImpl <em>Deployment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.deployment.impl.DeploymentImpl
	 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getDeployment()
	 * @generated
	 */
	int DEPLOYMENT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Service Deployments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT__SERVICE_DEPLOYMENTS = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Deployment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.deployment.impl.ServiceDeploymentImpl <em>Service Deployment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.deployment.impl.ServiceDeploymentImpl
	 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getServiceDeployment()
	 * @generated
	 */
	int SERVICE_DEPLOYMENT = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DEPLOYMENT__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DEPLOYMENT__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DEPLOYMENT__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DEPLOYMENT__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DEPLOYMENT__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The number of structural features of the '<em>Service Deployment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DEPLOYMENT_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.deployment.impl.GenericDeploymentImpl <em>Generic Deployment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.deployment.impl.GenericDeploymentImpl
	 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getGenericDeployment()
	 * @generated
	 */
	int GENERIC_DEPLOYMENT = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_DEPLOYMENT__ID = SERVICE_DEPLOYMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_DEPLOYMENT__NAME = SERVICE_DEPLOYMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_DEPLOYMENT__DESCRIPTION = SERVICE_DEPLOYMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_DEPLOYMENT__AV_MAP = SERVICE_DEPLOYMENT__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_DEPLOYMENT__AE_MAP = SERVICE_DEPLOYMENT__AE_MAP;

	/**
	 * The number of structural features of the '<em>Generic Deployment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_DEPLOYMENT_FEATURE_COUNT = SERVICE_DEPLOYMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.deployment.impl.RuntimeDeploymentImpl <em>Runtime Deployment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.deployment.impl.RuntimeDeploymentImpl
	 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getRuntimeDeployment()
	 * @generated
	 */
	int RUNTIME_DEPLOYMENT = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_DEPLOYMENT__ID = SERVICE_DEPLOYMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_DEPLOYMENT__NAME = SERVICE_DEPLOYMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_DEPLOYMENT__DESCRIPTION = SERVICE_DEPLOYMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_DEPLOYMENT__AV_MAP = SERVICE_DEPLOYMENT__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_DEPLOYMENT__AE_MAP = SERVICE_DEPLOYMENT__AE_MAP;

	/**
	 * The feature id for the '<em><b>Computing Environment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_DEPLOYMENT__COMPUTING_ENVIRONMENT = SERVICE_DEPLOYMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Runtime Deployment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_DEPLOYMENT_FEATURE_COUNT = SERVICE_DEPLOYMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.deployment.impl.ComputingEnvironmentImpl <em>Computing Environment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.deployment.impl.ComputingEnvironmentImpl
	 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getComputingEnvironment()
	 * @generated
	 */
	int COMPUTING_ENVIRONMENT = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_ENVIRONMENT__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_ENVIRONMENT__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_ENVIRONMENT__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_ENVIRONMENT__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_ENVIRONMENT__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Instance Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_ENVIRONMENT__INSTANCE_DESCRIPTOR = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Computing Environment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPUTING_ENVIRONMENT_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.deployment.impl.ClusteredComputingEnvironmentImpl <em>Clustered Computing Environment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.deployment.impl.ClusteredComputingEnvironmentImpl
	 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getClusteredComputingEnvironment()
	 * @generated
	 */
	int CLUSTERED_COMPUTING_ENVIRONMENT = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTERED_COMPUTING_ENVIRONMENT__ID = COMPUTING_ENVIRONMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTERED_COMPUTING_ENVIRONMENT__NAME = COMPUTING_ENVIRONMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTERED_COMPUTING_ENVIRONMENT__DESCRIPTION = COMPUTING_ENVIRONMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTERED_COMPUTING_ENVIRONMENT__AV_MAP = COMPUTING_ENVIRONMENT__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTERED_COMPUTING_ENVIRONMENT__AE_MAP = COMPUTING_ENVIRONMENT__AE_MAP;

	/**
	 * The feature id for the '<em><b>Instance Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTERED_COMPUTING_ENVIRONMENT__INSTANCE_DESCRIPTOR = COMPUTING_ENVIRONMENT__INSTANCE_DESCRIPTOR;

	/**
	 * The feature id for the '<em><b>Load Balancer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTERED_COMPUTING_ENVIRONMENT__LOAD_BALANCER = COMPUTING_ENVIRONMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTERED_COMPUTING_ENVIRONMENT__SIZE = COMPUTING_ENVIRONMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Clustered Computing Environment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTERED_COMPUTING_ENVIRONMENT_FEATURE_COUNT = COMPUTING_ENVIRONMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.deployment.impl.ScalableComputingEnvironmentImpl <em>Scalable Computing Environment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.deployment.impl.ScalableComputingEnvironmentImpl
	 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getScalableComputingEnvironment()
	 * @generated
	 */
	int SCALABLE_COMPUTING_ENVIRONMENT = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALABLE_COMPUTING_ENVIRONMENT__ID = CLUSTERED_COMPUTING_ENVIRONMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALABLE_COMPUTING_ENVIRONMENT__NAME = CLUSTERED_COMPUTING_ENVIRONMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALABLE_COMPUTING_ENVIRONMENT__DESCRIPTION = CLUSTERED_COMPUTING_ENVIRONMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALABLE_COMPUTING_ENVIRONMENT__AV_MAP = CLUSTERED_COMPUTING_ENVIRONMENT__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALABLE_COMPUTING_ENVIRONMENT__AE_MAP = CLUSTERED_COMPUTING_ENVIRONMENT__AE_MAP;

	/**
	 * The feature id for the '<em><b>Instance Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALABLE_COMPUTING_ENVIRONMENT__INSTANCE_DESCRIPTOR = CLUSTERED_COMPUTING_ENVIRONMENT__INSTANCE_DESCRIPTOR;

	/**
	 * The feature id for the '<em><b>Load Balancer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALABLE_COMPUTING_ENVIRONMENT__LOAD_BALANCER = CLUSTERED_COMPUTING_ENVIRONMENT__LOAD_BALANCER;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALABLE_COMPUTING_ENVIRONMENT__SIZE = CLUSTERED_COMPUTING_ENVIRONMENT__SIZE;

	/**
	 * The feature id for the '<em><b>Scaling Manager</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALABLE_COMPUTING_ENVIRONMENT__SCALING_MANAGER = CLUSTERED_COMPUTING_ENVIRONMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Scalable Computing Environment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALABLE_COMPUTING_ENVIRONMENT_FEATURE_COUNT = CLUSTERED_COMPUTING_ENVIRONMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.deployment.impl.ScalingManagerImpl <em>Scaling Manager</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.deployment.impl.ScalingManagerImpl
	 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getScalingManager()
	 * @generated
	 */
	int SCALING_MANAGER = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_MANAGER__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_MANAGER__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_MANAGER__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_MANAGER__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_MANAGER__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Scaling Policy</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_MANAGER__SCALING_POLICY = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Maximum Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_MANAGER__MAXIMUM_SIZE = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Minimum Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_MANAGER__MINIMUM_SIZE = CorePackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Scaling Manager</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_MANAGER_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.deployment.impl.ScalingPolicyImpl <em>Scaling Policy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.deployment.impl.ScalingPolicyImpl
	 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getScalingPolicy()
	 * @generated
	 */
	int SCALING_POLICY = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_POLICY__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_POLICY__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_POLICY__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_POLICY__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_POLICY__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Scaling Policy Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_POLICY__SCALING_POLICY_TYPE = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Scaling Capacity Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_POLICY__SCALING_CAPACITY_TYPE = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Adjustment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_POLICY__ADJUSTMENT = CorePackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Cooldown</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_POLICY__COOLDOWN = CorePackage.ENTITY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Trigger</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_POLICY__TRIGGER = CorePackage.ENTITY_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Scaling Policy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALING_POLICY_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.deployment.impl.LoadBalancerImpl <em>Load Balancer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.deployment.impl.LoadBalancerImpl
	 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getLoadBalancer()
	 * @generated
	 */
	int LOAD_BALANCER = 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_BALANCER__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_BALANCER__NAME = CorePackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_BALANCER__DESCRIPTION = CorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Av Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_BALANCER__AV_MAP = CorePackage.ENTITY__AV_MAP;

	/**
	 * The feature id for the '<em><b>Ae Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_BALANCER__AE_MAP = CorePackage.ENTITY__AE_MAP;

	/**
	 * The feature id for the '<em><b>Scheduling Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_BALANCER__SCHEDULING_POLICY = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Load Balancer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_BALANCER_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.deployment.SchedulingPolicy <em>Scheduling Policy</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.deployment.SchedulingPolicy
	 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getSchedulingPolicy()
	 * @generated
	 */
	int SCHEDULING_POLICY = 10;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.deployment.ScalingPolicyType <em>Scaling Policy Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.deployment.ScalingPolicyType
	 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getScalingPolicyType()
	 * @generated
	 */
	int SCALING_POLICY_TYPE = 11;

	/**
	 * The meta object id for the '{@link org.scaledl.overview.deployment.ScalingCapacityType <em>Scaling Capacity Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.scaledl.overview.deployment.ScalingCapacityType
	 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getScalingCapacityType()
	 * @generated
	 */
	int SCALING_CAPACITY_TYPE = 12;


	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.deployment.Deployment <em>Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deployment</em>'.
	 * @see org.scaledl.overview.deployment.Deployment
	 * @generated
	 */
	EClass getDeployment();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.deployment.Deployment#getServiceDeployments <em>Service Deployments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Service Deployments</em>'.
	 * @see org.scaledl.overview.deployment.Deployment#getServiceDeployments()
	 * @see #getDeployment()
	 * @generated
	 */
	EReference getDeployment_ServiceDeployments();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.deployment.ServiceDeployment <em>Service Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Deployment</em>'.
	 * @see org.scaledl.overview.deployment.ServiceDeployment
	 * @generated
	 */
	EClass getServiceDeployment();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.deployment.GenericDeployment <em>Generic Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Deployment</em>'.
	 * @see org.scaledl.overview.deployment.GenericDeployment
	 * @generated
	 */
	EClass getGenericDeployment();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.deployment.RuntimeDeployment <em>Runtime Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Runtime Deployment</em>'.
	 * @see org.scaledl.overview.deployment.RuntimeDeployment
	 * @generated
	 */
	EClass getRuntimeDeployment();

	/**
	 * Returns the meta object for the containment reference '{@link org.scaledl.overview.deployment.RuntimeDeployment#getComputingEnvironment <em>Computing Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Computing Environment</em>'.
	 * @see org.scaledl.overview.deployment.RuntimeDeployment#getComputingEnvironment()
	 * @see #getRuntimeDeployment()
	 * @generated
	 */
	EReference getRuntimeDeployment_ComputingEnvironment();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.deployment.ComputingEnvironment <em>Computing Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Computing Environment</em>'.
	 * @see org.scaledl.overview.deployment.ComputingEnvironment
	 * @generated
	 */
	EClass getComputingEnvironment();

	/**
	 * Returns the meta object for the reference '{@link org.scaledl.overview.deployment.ComputingEnvironment#getInstanceDescriptor <em>Instance Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Instance Descriptor</em>'.
	 * @see org.scaledl.overview.deployment.ComputingEnvironment#getInstanceDescriptor()
	 * @see #getComputingEnvironment()
	 * @generated
	 */
	EReference getComputingEnvironment_InstanceDescriptor();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.deployment.ClusteredComputingEnvironment <em>Clustered Computing Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Clustered Computing Environment</em>'.
	 * @see org.scaledl.overview.deployment.ClusteredComputingEnvironment
	 * @generated
	 */
	EClass getClusteredComputingEnvironment();

	/**
	 * Returns the meta object for the containment reference '{@link org.scaledl.overview.deployment.ClusteredComputingEnvironment#getLoadBalancer <em>Load Balancer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Load Balancer</em>'.
	 * @see org.scaledl.overview.deployment.ClusteredComputingEnvironment#getLoadBalancer()
	 * @see #getClusteredComputingEnvironment()
	 * @generated
	 */
	EReference getClusteredComputingEnvironment_LoadBalancer();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.deployment.ClusteredComputingEnvironment#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see org.scaledl.overview.deployment.ClusteredComputingEnvironment#getSize()
	 * @see #getClusteredComputingEnvironment()
	 * @generated
	 */
	EAttribute getClusteredComputingEnvironment_Size();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.deployment.ScalableComputingEnvironment <em>Scalable Computing Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scalable Computing Environment</em>'.
	 * @see org.scaledl.overview.deployment.ScalableComputingEnvironment
	 * @generated
	 */
	EClass getScalableComputingEnvironment();

	/**
	 * Returns the meta object for the containment reference '{@link org.scaledl.overview.deployment.ScalableComputingEnvironment#getScalingManager <em>Scaling Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Scaling Manager</em>'.
	 * @see org.scaledl.overview.deployment.ScalableComputingEnvironment#getScalingManager()
	 * @see #getScalableComputingEnvironment()
	 * @generated
	 */
	EReference getScalableComputingEnvironment_ScalingManager();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.deployment.ScalingManager <em>Scaling Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scaling Manager</em>'.
	 * @see org.scaledl.overview.deployment.ScalingManager
	 * @generated
	 */
	EClass getScalingManager();

	/**
	 * Returns the meta object for the containment reference list '{@link org.scaledl.overview.deployment.ScalingManager#getScalingPolicy <em>Scaling Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Scaling Policy</em>'.
	 * @see org.scaledl.overview.deployment.ScalingManager#getScalingPolicy()
	 * @see #getScalingManager()
	 * @generated
	 */
	EReference getScalingManager_ScalingPolicy();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.deployment.ScalingManager#getMaximumSize <em>Maximum Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Size</em>'.
	 * @see org.scaledl.overview.deployment.ScalingManager#getMaximumSize()
	 * @see #getScalingManager()
	 * @generated
	 */
	EAttribute getScalingManager_MaximumSize();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.deployment.ScalingManager#getMinimumSize <em>Minimum Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minimum Size</em>'.
	 * @see org.scaledl.overview.deployment.ScalingManager#getMinimumSize()
	 * @see #getScalingManager()
	 * @generated
	 */
	EAttribute getScalingManager_MinimumSize();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.deployment.ScalingPolicy <em>Scaling Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scaling Policy</em>'.
	 * @see org.scaledl.overview.deployment.ScalingPolicy
	 * @generated
	 */
	EClass getScalingPolicy();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.deployment.ScalingPolicy#getScalingPolicyType <em>Scaling Policy Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scaling Policy Type</em>'.
	 * @see org.scaledl.overview.deployment.ScalingPolicy#getScalingPolicyType()
	 * @see #getScalingPolicy()
	 * @generated
	 */
	EAttribute getScalingPolicy_ScalingPolicyType();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.deployment.ScalingPolicy#getScalingCapacityType <em>Scaling Capacity Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scaling Capacity Type</em>'.
	 * @see org.scaledl.overview.deployment.ScalingPolicy#getScalingCapacityType()
	 * @see #getScalingPolicy()
	 * @generated
	 */
	EAttribute getScalingPolicy_ScalingCapacityType();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.deployment.ScalingPolicy#getAdjustment <em>Adjustment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Adjustment</em>'.
	 * @see org.scaledl.overview.deployment.ScalingPolicy#getAdjustment()
	 * @see #getScalingPolicy()
	 * @generated
	 */
	EAttribute getScalingPolicy_Adjustment();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.deployment.ScalingPolicy#getCooldown <em>Cooldown</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cooldown</em>'.
	 * @see org.scaledl.overview.deployment.ScalingPolicy#getCooldown()
	 * @see #getScalingPolicy()
	 * @generated
	 */
	EAttribute getScalingPolicy_Cooldown();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.deployment.ScalingPolicy#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trigger</em>'.
	 * @see org.scaledl.overview.deployment.ScalingPolicy#getTrigger()
	 * @see #getScalingPolicy()
	 * @generated
	 */
	EAttribute getScalingPolicy_Trigger();

	/**
	 * Returns the meta object for class '{@link org.scaledl.overview.deployment.LoadBalancer <em>Load Balancer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Load Balancer</em>'.
	 * @see org.scaledl.overview.deployment.LoadBalancer
	 * @generated
	 */
	EClass getLoadBalancer();

	/**
	 * Returns the meta object for the attribute '{@link org.scaledl.overview.deployment.LoadBalancer#getSchedulingPolicy <em>Scheduling Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scheduling Policy</em>'.
	 * @see org.scaledl.overview.deployment.LoadBalancer#getSchedulingPolicy()
	 * @see #getLoadBalancer()
	 * @generated
	 */
	EAttribute getLoadBalancer_SchedulingPolicy();

	/**
	 * Returns the meta object for enum '{@link org.scaledl.overview.deployment.SchedulingPolicy <em>Scheduling Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Scheduling Policy</em>'.
	 * @see org.scaledl.overview.deployment.SchedulingPolicy
	 * @generated
	 */
	EEnum getSchedulingPolicy();

	/**
	 * Returns the meta object for enum '{@link org.scaledl.overview.deployment.ScalingPolicyType <em>Scaling Policy Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Scaling Policy Type</em>'.
	 * @see org.scaledl.overview.deployment.ScalingPolicyType
	 * @generated
	 */
	EEnum getScalingPolicyType();

	/**
	 * Returns the meta object for enum '{@link org.scaledl.overview.deployment.ScalingCapacityType <em>Scaling Capacity Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Scaling Capacity Type</em>'.
	 * @see org.scaledl.overview.deployment.ScalingCapacityType
	 * @generated
	 */
	EEnum getScalingCapacityType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DeploymentFactory getDeploymentFactory();

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
		 * The meta object literal for the '{@link org.scaledl.overview.deployment.impl.DeploymentImpl <em>Deployment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.deployment.impl.DeploymentImpl
		 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getDeployment()
		 * @generated
		 */
		EClass DEPLOYMENT = eINSTANCE.getDeployment();

		/**
		 * The meta object literal for the '<em><b>Service Deployments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT__SERVICE_DEPLOYMENTS = eINSTANCE.getDeployment_ServiceDeployments();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.deployment.impl.ServiceDeploymentImpl <em>Service Deployment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.deployment.impl.ServiceDeploymentImpl
		 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getServiceDeployment()
		 * @generated
		 */
		EClass SERVICE_DEPLOYMENT = eINSTANCE.getServiceDeployment();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.deployment.impl.GenericDeploymentImpl <em>Generic Deployment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.deployment.impl.GenericDeploymentImpl
		 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getGenericDeployment()
		 * @generated
		 */
		EClass GENERIC_DEPLOYMENT = eINSTANCE.getGenericDeployment();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.deployment.impl.RuntimeDeploymentImpl <em>Runtime Deployment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.deployment.impl.RuntimeDeploymentImpl
		 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getRuntimeDeployment()
		 * @generated
		 */
		EClass RUNTIME_DEPLOYMENT = eINSTANCE.getRuntimeDeployment();

		/**
		 * The meta object literal for the '<em><b>Computing Environment</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RUNTIME_DEPLOYMENT__COMPUTING_ENVIRONMENT = eINSTANCE.getRuntimeDeployment_ComputingEnvironment();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.deployment.impl.ComputingEnvironmentImpl <em>Computing Environment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.deployment.impl.ComputingEnvironmentImpl
		 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getComputingEnvironment()
		 * @generated
		 */
		EClass COMPUTING_ENVIRONMENT = eINSTANCE.getComputingEnvironment();

		/**
		 * The meta object literal for the '<em><b>Instance Descriptor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPUTING_ENVIRONMENT__INSTANCE_DESCRIPTOR = eINSTANCE.getComputingEnvironment_InstanceDescriptor();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.deployment.impl.ClusteredComputingEnvironmentImpl <em>Clustered Computing Environment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.deployment.impl.ClusteredComputingEnvironmentImpl
		 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getClusteredComputingEnvironment()
		 * @generated
		 */
		EClass CLUSTERED_COMPUTING_ENVIRONMENT = eINSTANCE.getClusteredComputingEnvironment();

		/**
		 * The meta object literal for the '<em><b>Load Balancer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLUSTERED_COMPUTING_ENVIRONMENT__LOAD_BALANCER = eINSTANCE.getClusteredComputingEnvironment_LoadBalancer();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLUSTERED_COMPUTING_ENVIRONMENT__SIZE = eINSTANCE.getClusteredComputingEnvironment_Size();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.deployment.impl.ScalableComputingEnvironmentImpl <em>Scalable Computing Environment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.deployment.impl.ScalableComputingEnvironmentImpl
		 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getScalableComputingEnvironment()
		 * @generated
		 */
		EClass SCALABLE_COMPUTING_ENVIRONMENT = eINSTANCE.getScalableComputingEnvironment();

		/**
		 * The meta object literal for the '<em><b>Scaling Manager</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCALABLE_COMPUTING_ENVIRONMENT__SCALING_MANAGER = eINSTANCE.getScalableComputingEnvironment_ScalingManager();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.deployment.impl.ScalingManagerImpl <em>Scaling Manager</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.deployment.impl.ScalingManagerImpl
		 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getScalingManager()
		 * @generated
		 */
		EClass SCALING_MANAGER = eINSTANCE.getScalingManager();

		/**
		 * The meta object literal for the '<em><b>Scaling Policy</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCALING_MANAGER__SCALING_POLICY = eINSTANCE.getScalingManager_ScalingPolicy();

		/**
		 * The meta object literal for the '<em><b>Maximum Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCALING_MANAGER__MAXIMUM_SIZE = eINSTANCE.getScalingManager_MaximumSize();

		/**
		 * The meta object literal for the '<em><b>Minimum Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCALING_MANAGER__MINIMUM_SIZE = eINSTANCE.getScalingManager_MinimumSize();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.deployment.impl.ScalingPolicyImpl <em>Scaling Policy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.deployment.impl.ScalingPolicyImpl
		 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getScalingPolicy()
		 * @generated
		 */
		EClass SCALING_POLICY = eINSTANCE.getScalingPolicy();

		/**
		 * The meta object literal for the '<em><b>Scaling Policy Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCALING_POLICY__SCALING_POLICY_TYPE = eINSTANCE.getScalingPolicy_ScalingPolicyType();

		/**
		 * The meta object literal for the '<em><b>Scaling Capacity Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCALING_POLICY__SCALING_CAPACITY_TYPE = eINSTANCE.getScalingPolicy_ScalingCapacityType();

		/**
		 * The meta object literal for the '<em><b>Adjustment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCALING_POLICY__ADJUSTMENT = eINSTANCE.getScalingPolicy_Adjustment();

		/**
		 * The meta object literal for the '<em><b>Cooldown</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCALING_POLICY__COOLDOWN = eINSTANCE.getScalingPolicy_Cooldown();

		/**
		 * The meta object literal for the '<em><b>Trigger</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCALING_POLICY__TRIGGER = eINSTANCE.getScalingPolicy_Trigger();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.deployment.impl.LoadBalancerImpl <em>Load Balancer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.deployment.impl.LoadBalancerImpl
		 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getLoadBalancer()
		 * @generated
		 */
		EClass LOAD_BALANCER = eINSTANCE.getLoadBalancer();

		/**
		 * The meta object literal for the '<em><b>Scheduling Policy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD_BALANCER__SCHEDULING_POLICY = eINSTANCE.getLoadBalancer_SchedulingPolicy();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.deployment.SchedulingPolicy <em>Scheduling Policy</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.deployment.SchedulingPolicy
		 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getSchedulingPolicy()
		 * @generated
		 */
		EEnum SCHEDULING_POLICY = eINSTANCE.getSchedulingPolicy();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.deployment.ScalingPolicyType <em>Scaling Policy Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.deployment.ScalingPolicyType
		 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getScalingPolicyType()
		 * @generated
		 */
		EEnum SCALING_POLICY_TYPE = eINSTANCE.getScalingPolicyType();

		/**
		 * The meta object literal for the '{@link org.scaledl.overview.deployment.ScalingCapacityType <em>Scaling Capacity Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.scaledl.overview.deployment.ScalingCapacityType
		 * @see org.scaledl.overview.deployment.impl.DeploymentPackageImpl#getScalingCapacityType()
		 * @generated
		 */
		EEnum SCALING_CAPACITY_TYPE = eINSTANCE.getScalingCapacityType();

	}

} //DeploymentPackage
