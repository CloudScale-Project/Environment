/**
 */
package org.scaledl.overview.architecture.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.scaledl.overview.application.OperationInterfaceContainer;

import org.scaledl.overview.architecture.*;

import org.scaledl.overview.core.Entity;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.scaledl.overview.architecture.ArchitecturePackage
 * @generated
 */
public class ArchitectureAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ArchitecturePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchitectureAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ArchitecturePackage.eINSTANCE;
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
	protected ArchitectureSwitch<Adapter> modelSwitch =
		new ArchitectureSwitch<Adapter>() {
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
			public Adapter caseProvidedService(ProvidedService object) {
				return createProvidedServiceAdapter();
			}
			@Override
			public Adapter caseInfrastructureService(InfrastructureService object) {
				return createInfrastructureServiceAdapter();
			}
			@Override
			public Adapter caseComputingInfrastructureService(ComputingInfrastructureService object) {
				return createComputingInfrastructureServiceAdapter();
			}
			@Override
			public Adapter casePlatformService(PlatformService object) {
				return createPlatformServiceAdapter();
			}
			@Override
			public Adapter casePlatformRuntimeService(PlatformRuntimeService object) {
				return createPlatformRuntimeServiceAdapter();
			}
			@Override
			public Adapter casePlatformSupportService(PlatformSupportService object) {
				return createPlatformSupportServiceAdapter();
			}
			@Override
			public Adapter caseProvidedPlatformRuntimeService(ProvidedPlatformRuntimeService object) {
				return createProvidedPlatformRuntimeServiceAdapter();
			}
			@Override
			public Adapter caseProvidedPlatformSupportService(ProvidedPlatformSupportService object) {
				return createProvidedPlatformSupportServiceAdapter();
			}
			@Override
			public Adapter caseSoftwareService(SoftwareService object) {
				return createSoftwareServiceAdapter();
			}
			@Override
			public Adapter caseProvidedSoftwareService(ProvidedSoftwareService object) {
				return createProvidedSoftwareServiceAdapter();
			}
			@Override
			public Adapter caseExternalSoftwareService(ExternalSoftwareService object) {
				return createExternalSoftwareServiceAdapter();
			}
			@Override
			public Adapter caseSoftwareServiceContainer(SoftwareServiceContainer object) {
				return createSoftwareServiceContainerAdapter();
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
			public Adapter caseOperationInterfaceContainer(OperationInterfaceContainer object) {
				return createOperationInterfaceContainerAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.Architecture <em>Architecture</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.Architecture
	 * @generated
	 */
	public Adapter createArchitectureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.CloudEnvironment <em>Cloud Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.CloudEnvironment
	 * @generated
	 */
	public Adapter createCloudEnvironmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.InfrastructureLayer <em>Infrastructure Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.InfrastructureLayer
	 * @generated
	 */
	public Adapter createInfrastructureLayerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.PlatformLayer <em>Platform Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.PlatformLayer
	 * @generated
	 */
	public Adapter createPlatformLayerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.SoftwareLayer <em>Software Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.SoftwareLayer
	 * @generated
	 */
	public Adapter createSoftwareLayerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.Service <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.Service
	 * @generated
	 */
	public Adapter createServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.ProvidedService <em>Provided Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.ProvidedService
	 * @generated
	 */
	public Adapter createProvidedServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.InfrastructureService <em>Infrastructure Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.InfrastructureService
	 * @generated
	 */
	public Adapter createInfrastructureServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.ComputingInfrastructureService <em>Computing Infrastructure Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.ComputingInfrastructureService
	 * @generated
	 */
	public Adapter createComputingInfrastructureServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.PlatformService <em>Platform Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.PlatformService
	 * @generated
	 */
	public Adapter createPlatformServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.PlatformRuntimeService <em>Platform Runtime Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.PlatformRuntimeService
	 * @generated
	 */
	public Adapter createPlatformRuntimeServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.PlatformSupportService <em>Platform Support Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.PlatformSupportService
	 * @generated
	 */
	public Adapter createPlatformSupportServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.ProvidedPlatformRuntimeService <em>Provided Platform Runtime Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.ProvidedPlatformRuntimeService
	 * @generated
	 */
	public Adapter createProvidedPlatformRuntimeServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.ProvidedPlatformSupportService <em>Provided Platform Support Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.ProvidedPlatformSupportService
	 * @generated
	 */
	public Adapter createProvidedPlatformSupportServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.SoftwareService <em>Software Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.SoftwareService
	 * @generated
	 */
	public Adapter createSoftwareServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.ProvidedSoftwareService <em>Provided Software Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.ProvidedSoftwareService
	 * @generated
	 */
	public Adapter createProvidedSoftwareServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.ExternalSoftwareService <em>External Software Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.ExternalSoftwareService
	 * @generated
	 */
	public Adapter createExternalSoftwareServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.SoftwareServiceContainer <em>Software Service Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.SoftwareServiceContainer
	 * @generated
	 */
	public Adapter createSoftwareServiceContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.Connection
	 * @generated
	 */
	public Adapter createConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.InternalConnection <em>Internal Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.InternalConnection
	 * @generated
	 */
	public Adapter createInternalConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.ExternalConnection <em>External Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.ExternalConnection
	 * @generated
	 */
	public Adapter createExternalConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.Proxy <em>Proxy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.Proxy
	 * @generated
	 */
	public Adapter createProxyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.ServiceProxy <em>Service Proxy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.ServiceProxy
	 * @generated
	 */
	public Adapter createServiceProxyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.architecture.UsageProxy <em>Usage Proxy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.architecture.UsageProxy
	 * @generated
	 */
	public Adapter createUsageProxyAdapter() {
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
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.application.OperationInterfaceContainer <em>Operation Interface Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.application.OperationInterfaceContainer
	 * @generated
	 */
	public Adapter createOperationInterfaceContainerAdapter() {
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

} //ArchitectureAdapterFactory
