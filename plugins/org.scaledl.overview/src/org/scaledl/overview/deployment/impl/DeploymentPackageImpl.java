/**
 */
package org.scaledl.overview.deployment.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.scaledl.overview.OverviewPackage;

import org.scaledl.overview.application.ApplicationPackage;

import org.scaledl.overview.application.impl.ApplicationPackageImpl;

import org.scaledl.overview.architecture.ArchitecturePackage;

import org.scaledl.overview.architecture.impl.ArchitecturePackageImpl;

import org.scaledl.overview.core.CorePackage;

import org.scaledl.overview.core.impl.CorePackageImpl;

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

import org.scaledl.overview.impl.OverviewPackageImpl;

import org.scaledl.overview.parametertype.ParametertypePackage;

import org.scaledl.overview.parametertype.impl.ParametertypePackageImpl;

import org.scaledl.overview.specification.SpecificationPackage;

import org.scaledl.overview.specification.impl.SpecificationPackageImpl;

import org.scaledl.overview.specification.sla.SlaPackage;

import org.scaledl.overview.specification.sla.impl.SlaPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DeploymentPackageImpl extends EPackageImpl implements DeploymentPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deploymentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceDeploymentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericDeploymentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass runtimeDeploymentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass computingEnvironmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass clusteredComputingEnvironmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scalableComputingEnvironmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scalingManagerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scalingPolicyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass loadBalancerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum schedulingPolicyEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum scalingPolicyTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum scalingCapacityTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.scaledl.overview.deployment.DeploymentPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DeploymentPackageImpl() {
		super(eNS_URI, DeploymentFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link DeploymentPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DeploymentPackage init() {
		if (isInited) return (DeploymentPackage)EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI);

		// Obtain or create and register package
		DeploymentPackageImpl theDeploymentPackage = (DeploymentPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DeploymentPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DeploymentPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		OverviewPackageImpl theOverviewPackage = (OverviewPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OverviewPackage.eNS_URI) instanceof OverviewPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OverviewPackage.eNS_URI) : OverviewPackage.eINSTANCE);
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);
		ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) instanceof ApplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) : ApplicationPackage.eINSTANCE);
		ArchitecturePackageImpl theArchitecturePackage = (ArchitecturePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ArchitecturePackage.eNS_URI) instanceof ArchitecturePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ArchitecturePackage.eNS_URI) : ArchitecturePackage.eINSTANCE);
		SpecificationPackageImpl theSpecificationPackage = (SpecificationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI) instanceof SpecificationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI) : SpecificationPackage.eINSTANCE);
		SlaPackageImpl theSlaPackage = (SlaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SlaPackage.eNS_URI) instanceof SlaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SlaPackage.eNS_URI) : SlaPackage.eINSTANCE);
		ParametertypePackageImpl theParametertypePackage = (ParametertypePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ParametertypePackage.eNS_URI) instanceof ParametertypePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ParametertypePackage.eNS_URI) : ParametertypePackage.eINSTANCE);

		// Create package meta-data objects
		theDeploymentPackage.createPackageContents();
		theOverviewPackage.createPackageContents();
		theCorePackage.createPackageContents();
		theApplicationPackage.createPackageContents();
		theArchitecturePackage.createPackageContents();
		theSpecificationPackage.createPackageContents();
		theSlaPackage.createPackageContents();
		theParametertypePackage.createPackageContents();

		// Initialize created meta-data
		theDeploymentPackage.initializePackageContents();
		theOverviewPackage.initializePackageContents();
		theCorePackage.initializePackageContents();
		theApplicationPackage.initializePackageContents();
		theArchitecturePackage.initializePackageContents();
		theSpecificationPackage.initializePackageContents();
		theSlaPackage.initializePackageContents();
		theParametertypePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDeploymentPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DeploymentPackage.eNS_URI, theDeploymentPackage);
		return theDeploymentPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeployment() {
		return deploymentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeployment_ServiceDeployments() {
		return (EReference)deploymentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServiceDeployment() {
		return serviceDeploymentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericDeployment() {
		return genericDeploymentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRuntimeDeployment() {
		return runtimeDeploymentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRuntimeDeployment_ComputingEnvironment() {
		return (EReference)runtimeDeploymentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComputingEnvironment() {
		return computingEnvironmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComputingEnvironment_InstanceDescriptor() {
		return (EReference)computingEnvironmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClusteredComputingEnvironment() {
		return clusteredComputingEnvironmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClusteredComputingEnvironment_LoadBalancer() {
		return (EReference)clusteredComputingEnvironmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClusteredComputingEnvironment_Size() {
		return (EAttribute)clusteredComputingEnvironmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScalableComputingEnvironment() {
		return scalableComputingEnvironmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScalableComputingEnvironment_ScalingManager() {
		return (EReference)scalableComputingEnvironmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScalingManager() {
		return scalingManagerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScalingManager_ScalingPolicy() {
		return (EReference)scalingManagerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScalingManager_MaximumSize() {
		return (EAttribute)scalingManagerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScalingManager_MinimumSize() {
		return (EAttribute)scalingManagerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScalingPolicy() {
		return scalingPolicyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScalingPolicy_ScalingPolicyType() {
		return (EAttribute)scalingPolicyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScalingPolicy_ScalingCapacityType() {
		return (EAttribute)scalingPolicyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScalingPolicy_Adjustment() {
		return (EAttribute)scalingPolicyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScalingPolicy_Cooldown() {
		return (EAttribute)scalingPolicyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScalingPolicy_Trigger() {
		return (EAttribute)scalingPolicyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLoadBalancer() {
		return loadBalancerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLoadBalancer_SchedulingPolicy() {
		return (EAttribute)loadBalancerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSchedulingPolicy() {
		return schedulingPolicyEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getScalingPolicyType() {
		return scalingPolicyTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getScalingCapacityType() {
		return scalingCapacityTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentFactory getDeploymentFactory() {
		return (DeploymentFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		deploymentEClass = createEClass(DEPLOYMENT);
		createEReference(deploymentEClass, DEPLOYMENT__SERVICE_DEPLOYMENTS);

		serviceDeploymentEClass = createEClass(SERVICE_DEPLOYMENT);

		genericDeploymentEClass = createEClass(GENERIC_DEPLOYMENT);

		runtimeDeploymentEClass = createEClass(RUNTIME_DEPLOYMENT);
		createEReference(runtimeDeploymentEClass, RUNTIME_DEPLOYMENT__COMPUTING_ENVIRONMENT);

		computingEnvironmentEClass = createEClass(COMPUTING_ENVIRONMENT);
		createEReference(computingEnvironmentEClass, COMPUTING_ENVIRONMENT__INSTANCE_DESCRIPTOR);

		clusteredComputingEnvironmentEClass = createEClass(CLUSTERED_COMPUTING_ENVIRONMENT);
		createEReference(clusteredComputingEnvironmentEClass, CLUSTERED_COMPUTING_ENVIRONMENT__LOAD_BALANCER);
		createEAttribute(clusteredComputingEnvironmentEClass, CLUSTERED_COMPUTING_ENVIRONMENT__SIZE);

		scalableComputingEnvironmentEClass = createEClass(SCALABLE_COMPUTING_ENVIRONMENT);
		createEReference(scalableComputingEnvironmentEClass, SCALABLE_COMPUTING_ENVIRONMENT__SCALING_MANAGER);

		scalingManagerEClass = createEClass(SCALING_MANAGER);
		createEReference(scalingManagerEClass, SCALING_MANAGER__SCALING_POLICY);
		createEAttribute(scalingManagerEClass, SCALING_MANAGER__MAXIMUM_SIZE);
		createEAttribute(scalingManagerEClass, SCALING_MANAGER__MINIMUM_SIZE);

		scalingPolicyEClass = createEClass(SCALING_POLICY);
		createEAttribute(scalingPolicyEClass, SCALING_POLICY__SCALING_POLICY_TYPE);
		createEAttribute(scalingPolicyEClass, SCALING_POLICY__SCALING_CAPACITY_TYPE);
		createEAttribute(scalingPolicyEClass, SCALING_POLICY__ADJUSTMENT);
		createEAttribute(scalingPolicyEClass, SCALING_POLICY__COOLDOWN);
		createEAttribute(scalingPolicyEClass, SCALING_POLICY__TRIGGER);

		loadBalancerEClass = createEClass(LOAD_BALANCER);
		createEAttribute(loadBalancerEClass, LOAD_BALANCER__SCHEDULING_POLICY);

		// Create enums
		schedulingPolicyEEnum = createEEnum(SCHEDULING_POLICY);
		scalingPolicyTypeEEnum = createEEnum(SCALING_POLICY_TYPE);
		scalingCapacityTypeEEnum = createEEnum(SCALING_CAPACITY_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);
		SpecificationPackage theSpecificationPackage = (SpecificationPackage)EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		deploymentEClass.getESuperTypes().add(theCorePackage.getEntity());
		serviceDeploymentEClass.getESuperTypes().add(theCorePackage.getEntity());
		genericDeploymentEClass.getESuperTypes().add(this.getServiceDeployment());
		runtimeDeploymentEClass.getESuperTypes().add(this.getServiceDeployment());
		computingEnvironmentEClass.getESuperTypes().add(theCorePackage.getEntity());
		clusteredComputingEnvironmentEClass.getESuperTypes().add(this.getComputingEnvironment());
		scalableComputingEnvironmentEClass.getESuperTypes().add(this.getClusteredComputingEnvironment());
		scalingManagerEClass.getESuperTypes().add(theCorePackage.getEntity());
		scalingPolicyEClass.getESuperTypes().add(theCorePackage.getEntity());
		loadBalancerEClass.getESuperTypes().add(theCorePackage.getEntity());

		// Initialize classes and features; add operations and parameters
		initEClass(deploymentEClass, Deployment.class, "Deployment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDeployment_ServiceDeployments(), this.getServiceDeployment(), null, "serviceDeployments", null, 0, -1, Deployment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceDeploymentEClass, ServiceDeployment.class, "ServiceDeployment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(genericDeploymentEClass, GenericDeployment.class, "GenericDeployment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(runtimeDeploymentEClass, RuntimeDeployment.class, "RuntimeDeployment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRuntimeDeployment_ComputingEnvironment(), this.getComputingEnvironment(), null, "computingEnvironment", null, 1, 1, RuntimeDeployment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(computingEnvironmentEClass, ComputingEnvironment.class, "ComputingEnvironment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComputingEnvironment_InstanceDescriptor(), theSpecificationPackage.getComputingResourceDescriptor(), null, "instanceDescriptor", null, 0, 1, ComputingEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(clusteredComputingEnvironmentEClass, ClusteredComputingEnvironment.class, "ClusteredComputingEnvironment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClusteredComputingEnvironment_LoadBalancer(), this.getLoadBalancer(), null, "loadBalancer", null, 1, 1, ClusteredComputingEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClusteredComputingEnvironment_Size(), ecorePackage.getEInt(), "size", null, 0, 1, ClusteredComputingEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scalableComputingEnvironmentEClass, ScalableComputingEnvironment.class, "ScalableComputingEnvironment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScalableComputingEnvironment_ScalingManager(), this.getScalingManager(), null, "scalingManager", null, 1, 1, ScalableComputingEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scalingManagerEClass, ScalingManager.class, "ScalingManager", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScalingManager_ScalingPolicy(), this.getScalingPolicy(), null, "scalingPolicy", null, 0, -1, ScalingManager.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScalingManager_MaximumSize(), ecorePackage.getEInt(), "maximumSize", null, 0, 1, ScalingManager.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScalingManager_MinimumSize(), ecorePackage.getEInt(), "minimumSize", null, 0, 1, ScalingManager.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scalingPolicyEClass, ScalingPolicy.class, "ScalingPolicy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScalingPolicy_ScalingPolicyType(), this.getScalingPolicyType(), "scalingPolicyType", null, 1, 1, ScalingPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScalingPolicy_ScalingCapacityType(), this.getScalingCapacityType(), "scalingCapacityType", null, 1, 1, ScalingPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScalingPolicy_Adjustment(), ecorePackage.getEFloat(), "adjustment", null, 0, 1, ScalingPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScalingPolicy_Cooldown(), ecorePackage.getEInt(), "cooldown", null, 0, 1, ScalingPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScalingPolicy_Trigger(), ecorePackage.getEString(), "trigger", null, 0, 1, ScalingPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(loadBalancerEClass, LoadBalancer.class, "LoadBalancer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLoadBalancer_SchedulingPolicy(), this.getSchedulingPolicy(), "schedulingPolicy", null, 1, 1, LoadBalancer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(schedulingPolicyEEnum, SchedulingPolicy.class, "SchedulingPolicy");
		addEEnumLiteral(schedulingPolicyEEnum, SchedulingPolicy.ROUND_ROBIN);
		addEEnumLiteral(schedulingPolicyEEnum, SchedulingPolicy.CUSTOM);

		initEEnum(scalingPolicyTypeEEnum, ScalingPolicyType.class, "ScalingPolicyType");
		addEEnumLiteral(scalingPolicyTypeEEnum, ScalingPolicyType.SCALING_UP_POLICY);
		addEEnumLiteral(scalingPolicyTypeEEnum, ScalingPolicyType.SCALING_DOWN_POLICY);
		addEEnumLiteral(scalingPolicyTypeEEnum, ScalingPolicyType.PRECENT_CHANGE_IN_CAPACITY);

		initEEnum(scalingCapacityTypeEEnum, ScalingCapacityType.class, "ScalingCapacityType");
		addEEnumLiteral(scalingCapacityTypeEEnum, ScalingCapacityType.CHANGE_IN_CAPACITY);
		addEEnumLiteral(scalingCapacityTypeEEnum, ScalingCapacityType.EXACT_CAPACITY);
		addEEnumLiteral(scalingCapacityTypeEEnum, ScalingCapacityType.PRECENT_CHANGE_IN_CAPACITY);
	}

} //DeploymentPackageImpl
