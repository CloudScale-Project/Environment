/**
 */
package eu.cloudscaleproject.env.csm.architecture.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import eu.cloudscaleproject.env.csm.architecture.Architecture;
import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.Connection;
import eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService;
import eu.cloudscaleproject.env.csm.architecture.DeployableRuntimeService;
import eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService;
import eu.cloudscaleproject.env.csm.architecture.DeployableSupportService;
import eu.cloudscaleproject.env.csm.architecture.ExternalConnection;
import eu.cloudscaleproject.env.csm.architecture.ExternalPlatformService;
import eu.cloudscaleproject.env.csm.architecture.ExternalRuntimeService;
import eu.cloudscaleproject.env.csm.architecture.ExternalSoftwareService;
import eu.cloudscaleproject.env.csm.architecture.ExternalSupportService;
import eu.cloudscaleproject.env.csm.architecture.HybridConnection;
import eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer;
import eu.cloudscaleproject.env.csm.architecture.InfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.InternalConnection;
import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;
import eu.cloudscaleproject.env.csm.architecture.PlatformLayer;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.architecture.Proxy;
import eu.cloudscaleproject.env.csm.architecture.Service;
import eu.cloudscaleproject.env.csm.architecture.ServiceProxy;
import eu.cloudscaleproject.env.csm.architecture.SoftwareLayer;
import eu.cloudscaleproject.env.csm.architecture.SoftwareService;
import eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer;
import eu.cloudscaleproject.env.csm.architecture.UsageProxy;
import eu.cloudscaleproject.env.csm.core.Entity;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage
 * @generated
 */
public class ArchitectureAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static ArchitecturePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public ArchitectureAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ArchitecturePackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc --> This implementation returns <code>true</code> if
	 * the object is either the model's package or is an instance object of the
	 * model. <!-- end-user-doc -->
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
	 * The switch that delegates to the <code>createXXX</code> methods. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ArchitectureSwitch<Adapter> modelSwitch = new ArchitectureSwitch<Adapter>() {
			@Override
			public Adapter caseArchitecture(Architecture object) {
				return createArchitectureAdapter();
			}
			@Override
			public Adapter caseCloudEnvironment(CloudEnvironment object) {
				return createCloudEnvironmentAdapter();
			}
			@Override
			public Adapter caseInfrastructureLayer(InfrastructureLayer object) {
				return createInfrastructureLayerAdapter();
			}
			@Override
			public Adapter casePlatformLayer(PlatformLayer object) {
				return createPlatformLayerAdapter();
			}
			@Override
			public Adapter caseSoftwareLayer(SoftwareLayer object) {
				return createSoftwareLayerAdapter();
			}
			@Override
			public Adapter caseService(Service object) {
				return createServiceAdapter();
			}
			@Override
			public Adapter caseInfrastructureService(InfrastructureService object) {
				return createInfrastructureServiceAdapter();
			}
			@Override
			public Adapter casePlatformService(PlatformService object) {
				return createPlatformServiceAdapter();
			}
			@Override
			public Adapter caseSoftwareService(SoftwareService object) {
				return createSoftwareServiceAdapter();
			}
			@Override
			public Adapter caseDeployablePlatformService(DeployablePlatformService object) {
				return createDeployablePlatformServiceAdapter();
			}
			@Override
			public Adapter caseExternalPlatformService(ExternalPlatformService object) {
				return createExternalPlatformServiceAdapter();
			}
			@Override
			public Adapter caseDeployableRuntimeService(DeployableRuntimeService object) {
				return createDeployableRuntimeServiceAdapter();
			}
			@Override
			public Adapter caseDeployableSupportService(DeployableSupportService object) {
				return createDeployableSupportServiceAdapter();
			}
			@Override
			public Adapter caseExternalRuntimeService(ExternalRuntimeService object) {
				return createExternalRuntimeServiceAdapter();
			}
			@Override
			public Adapter caseExternalSupportService(ExternalSupportService object) {
				return createExternalSupportServiceAdapter();
			}
			@Override
			public Adapter caseExternalSoftwareService(ExternalSoftwareService object) {
				return createExternalSoftwareServiceAdapter();
			}
			@Override
			public Adapter caseDeployableSoftwareService(DeployableSoftwareService object) {
				return createDeployableSoftwareServiceAdapter();
			}
			@Override
			public Adapter caseComputingInfrastructureService(ComputingInfrastructureService object) {
				return createComputingInfrastructureServiceAdapter();
			}
			@Override
			public Adapter caseSoftwareServiceContainer(SoftwareServiceContainer object) {
				return createSoftwareServiceContainerAdapter();
			}
			@Override
			public Adapter caseOperationInterfaceContainer(OperationInterfaceContainer object) {
				return createOperationInterfaceContainerAdapter();
			}
			@Override
			public Adapter caseConnection(Connection object) {
				return createConnectionAdapter();
			}
			@Override
			public Adapter caseInternalConnection(InternalConnection object) {
				return createInternalConnectionAdapter();
			}
			@Override
			public Adapter caseHybridConnection(HybridConnection object) {
				return createHybridConnectionAdapter();
			}
			@Override
			public Adapter caseExternalConnection(ExternalConnection object) {
				return createExternalConnectionAdapter();
			}
			@Override
			public Adapter caseProxy(Proxy object) {
				return createProxyAdapter();
			}
			@Override
			public Adapter caseServiceProxy(ServiceProxy object) {
				return createServiceProxyAdapter();
			}
			@Override
			public Adapter caseUsageProxy(UsageProxy object) {
				return createUsageProxyAdapter();
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
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.Architecture <em>Architecture</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.Architecture
	 * @generated
	 */
	public Adapter createArchitectureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.CloudEnvironment <em>Cloud Environment</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.CloudEnvironment
	 * @generated
	 */
	public Adapter createCloudEnvironmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer <em>Infrastructure Layer</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer
	 * @generated
	 */
	public Adapter createInfrastructureLayerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.PlatformLayer <em>Platform Layer</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.PlatformLayer
	 * @generated
	 */
	public Adapter createPlatformLayerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.SoftwareLayer <em>Software Layer</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.SoftwareLayer
	 * @generated
	 */
	public Adapter createSoftwareLayerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link eu.cloudscaleproject.env.csm.architecture.Service
	 * <em>Service</em>}'. <!-- begin-user-doc --> This default implementation
	 * returns null so that we can easily ignore cases; it's useful to ignore a
	 * case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.Service
	 * @generated
	 */
	public Adapter createServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.InfrastructureService <em>Infrastructure Service</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.InfrastructureService
	 * @generated
	 */
	public Adapter createInfrastructureServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.PlatformService <em>Platform Service</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.PlatformService
	 * @generated
	 */
	public Adapter createPlatformServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.SoftwareService <em>Software Service</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.SoftwareService
	 * @generated
	 */
	public Adapter createSoftwareServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService <em>Deployable Platform Service</em>}'.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService
	 * @generated
	 */
	public Adapter createDeployablePlatformServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.ExternalPlatformService <em>External Platform Service</em>}'.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.ExternalPlatformService
	 * @generated
	 */
	public Adapter createExternalPlatformServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.DeployableRuntimeService <em>Deployable Runtime Service</em>}'.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.DeployableRuntimeService
	 * @generated
	 */
	public Adapter createDeployableRuntimeServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.DeployableSupportService <em>Deployable Support Service</em>}'.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.DeployableSupportService
	 * @generated
	 */
	public Adapter createDeployableSupportServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.ExternalRuntimeService <em>External Runtime Service</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.ExternalRuntimeService
	 * @generated
	 */
	public Adapter createExternalRuntimeServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.ExternalSupportService <em>External Support Service</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.ExternalSupportService
	 * @generated
	 */
	public Adapter createExternalSupportServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.ExternalSoftwareService <em>External Software Service</em>}'.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.ExternalSoftwareService
	 * @generated
	 */
	public Adapter createExternalSoftwareServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService <em>Deployable Software Service</em>}'.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService
	 * @generated
	 */
	public Adapter createDeployableSoftwareServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService <em>Computing Infrastructure Service</em>}'.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService
	 * @generated
	 */
	public Adapter createComputingInfrastructureServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer <em>Software Service Container</em>}'.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer
	 * @generated
	 */
	public Adapter createSoftwareServiceContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer <em>Operation Interface Container</em>}'.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer
	 * @generated
	 */
	public Adapter createOperationInterfaceContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.Connection
	 * @generated
	 */
	public Adapter createConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.InternalConnection <em>Internal Connection</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.InternalConnection
	 * @generated
	 */
	public Adapter createInternalConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.HybridConnection <em>Hybrid Connection</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.HybridConnection
	 * @generated
	 */
	public Adapter createHybridConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.ExternalConnection <em>External Connection</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.ExternalConnection
	 * @generated
	 */
	public Adapter createExternalConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.Proxy <em>Proxy</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.Proxy
	 * @generated
	 */
	public Adapter createProxyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.ServiceProxy <em>Service Proxy</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.ServiceProxy
	 * @generated
	 */
	public Adapter createServiceProxyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.architecture.UsageProxy <em>Usage Proxy</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.architecture.UsageProxy
	 * @generated
	 */
	public Adapter createUsageProxyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link eu.cloudscaleproject.env.csm.core.Entity <em>Entity</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.core.Entity
	 * @generated
	 */
	public Adapter createEntityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // ArchitectureAdapterFactory
