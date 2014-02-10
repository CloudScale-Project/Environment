/**
 */
package eu.cloudscaleproject.env.csm.architecture.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

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
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage
 * @generated
 */
public class ArchitectureSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static ArchitecturePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public ArchitectureSwitch() {
		if (modelPackage == null) {
			modelPackage = ArchitecturePackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
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
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ArchitecturePackage.ARCHITECTURE: {
				Architecture architecture = (Architecture)theEObject;
				T result = caseArchitecture(architecture);
				if (result == null) result = caseEntity(architecture);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.CLOUD_ENVIRONMENT: {
				CloudEnvironment cloudEnvironment = (CloudEnvironment)theEObject;
				T result = caseCloudEnvironment(cloudEnvironment);
				if (result == null) result = caseEntity(cloudEnvironment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.INFRASTRUCTURE_LAYER: {
				InfrastructureLayer infrastructureLayer = (InfrastructureLayer)theEObject;
				T result = caseInfrastructureLayer(infrastructureLayer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.PLATFORM_LAYER: {
				PlatformLayer platformLayer = (PlatformLayer)theEObject;
				T result = casePlatformLayer(platformLayer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.SOFTWARE_LAYER: {
				SoftwareLayer softwareLayer = (SoftwareLayer)theEObject;
				T result = caseSoftwareLayer(softwareLayer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.SERVICE: {
				Service service = (Service)theEObject;
				T result = caseService(service);
				if (result == null) result = caseEntity(service);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.INFRASTRUCTURE_SERVICE: {
				InfrastructureService infrastructureService = (InfrastructureService)theEObject;
				T result = caseInfrastructureService(infrastructureService);
				if (result == null) result = caseService(infrastructureService);
				if (result == null) result = caseEntity(infrastructureService);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.PLATFORM_SERVICE: {
				PlatformService platformService = (PlatformService)theEObject;
				T result = casePlatformService(platformService);
				if (result == null) result = caseService(platformService);
				if (result == null) result = caseEntity(platformService);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.SOFTWARE_SERVICE: {
				SoftwareService softwareService = (SoftwareService)theEObject;
				T result = caseSoftwareService(softwareService);
				if (result == null) result = caseService(softwareService);
				if (result == null) result = caseOperationInterfaceContainer(softwareService);
				if (result == null) result = caseEntity(softwareService);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.DEPLOYABLE_PLATFORM_SERVICE: {
				DeployablePlatformService deployablePlatformService = (DeployablePlatformService)theEObject;
				T result = caseDeployablePlatformService(deployablePlatformService);
				if (result == null) result = casePlatformService(deployablePlatformService);
				if (result == null) result = caseService(deployablePlatformService);
				if (result == null) result = caseEntity(deployablePlatformService);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.EXTERNAL_PLATFORM_SERVICE: {
				ExternalPlatformService externalPlatformService = (ExternalPlatformService)theEObject;
				T result = caseExternalPlatformService(externalPlatformService);
				if (result == null) result = casePlatformService(externalPlatformService);
				if (result == null) result = caseService(externalPlatformService);
				if (result == null) result = caseEntity(externalPlatformService);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.DEPLOYABLE_RUNTIME_SERVICE: {
				DeployableRuntimeService deployableRuntimeService = (DeployableRuntimeService)theEObject;
				T result = caseDeployableRuntimeService(deployableRuntimeService);
				if (result == null) result = caseDeployablePlatformService(deployableRuntimeService);
				if (result == null) result = caseSoftwareServiceContainer(deployableRuntimeService);
				if (result == null) result = casePlatformService(deployableRuntimeService);
				if (result == null) result = caseService(deployableRuntimeService);
				if (result == null) result = caseEntity(deployableRuntimeService);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.DEPLOYABLE_SUPPORT_SERVICE: {
				DeployableSupportService deployableSupportService = (DeployableSupportService)theEObject;
				T result = caseDeployableSupportService(deployableSupportService);
				if (result == null) result = caseDeployablePlatformService(deployableSupportService);
				if (result == null) result = caseOperationInterfaceContainer(deployableSupportService);
				if (result == null) result = casePlatformService(deployableSupportService);
				if (result == null) result = caseService(deployableSupportService);
				if (result == null) result = caseEntity(deployableSupportService);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.EXTERNAL_RUNTIME_SERVICE: {
				ExternalRuntimeService externalRuntimeService = (ExternalRuntimeService)theEObject;
				T result = caseExternalRuntimeService(externalRuntimeService);
				if (result == null) result = caseExternalPlatformService(externalRuntimeService);
				if (result == null) result = caseSoftwareServiceContainer(externalRuntimeService);
				if (result == null) result = casePlatformService(externalRuntimeService);
				if (result == null) result = caseService(externalRuntimeService);
				if (result == null) result = caseEntity(externalRuntimeService);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE: {
				ExternalSupportService externalSupportService = (ExternalSupportService)theEObject;
				T result = caseExternalSupportService(externalSupportService);
				if (result == null) result = caseExternalPlatformService(externalSupportService);
				if (result == null) result = caseOperationInterfaceContainer(externalSupportService);
				if (result == null) result = casePlatformService(externalSupportService);
				if (result == null) result = caseService(externalSupportService);
				if (result == null) result = caseEntity(externalSupportService);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.EXTERNAL_SOFTWARE_SERVICE: {
				ExternalSoftwareService externalSoftwareService = (ExternalSoftwareService)theEObject;
				T result = caseExternalSoftwareService(externalSoftwareService);
				if (result == null) result = caseSoftwareService(externalSoftwareService);
				if (result == null) result = caseService(externalSoftwareService);
				if (result == null) result = caseOperationInterfaceContainer(externalSoftwareService);
				if (result == null) result = caseEntity(externalSoftwareService);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.DEPLOYABLE_SOFTWARE_SERVICE: {
				DeployableSoftwareService deployableSoftwareService = (DeployableSoftwareService)theEObject;
				T result = caseDeployableSoftwareService(deployableSoftwareService);
				if (result == null) result = caseSoftwareService(deployableSoftwareService);
				if (result == null) result = caseService(deployableSoftwareService);
				if (result == null) result = caseOperationInterfaceContainer(deployableSoftwareService);
				if (result == null) result = caseEntity(deployableSoftwareService);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE: {
				ComputingInfrastructureService computingInfrastructureService = (ComputingInfrastructureService)theEObject;
				T result = caseComputingInfrastructureService(computingInfrastructureService);
				if (result == null) result = caseInfrastructureService(computingInfrastructureService);
				if (result == null) result = caseService(computingInfrastructureService);
				if (result == null) result = caseEntity(computingInfrastructureService);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.SOFTWARE_SERVICE_CONTAINER: {
				SoftwareServiceContainer softwareServiceContainer = (SoftwareServiceContainer)theEObject;
				T result = caseSoftwareServiceContainer(softwareServiceContainer);
				if (result == null) result = caseEntity(softwareServiceContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.OPERATION_INTERFACE_CONTAINER: {
				OperationInterfaceContainer operationInterfaceContainer = (OperationInterfaceContainer)theEObject;
				T result = caseOperationInterfaceContainer(operationInterfaceContainer);
				if (result == null) result = caseEntity(operationInterfaceContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.CONNECTION: {
				Connection connection = (Connection)theEObject;
				T result = caseConnection(connection);
				if (result == null) result = caseEntity(connection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.INTERNAL_CONNECTION: {
				InternalConnection internalConnection = (InternalConnection)theEObject;
				T result = caseInternalConnection(internalConnection);
				if (result == null) result = caseConnection(internalConnection);
				if (result == null) result = caseEntity(internalConnection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.HYBRID_CONNECTION: {
				HybridConnection hybridConnection = (HybridConnection)theEObject;
				T result = caseHybridConnection(hybridConnection);
				if (result == null) result = caseConnection(hybridConnection);
				if (result == null) result = caseEntity(hybridConnection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.EXTERNAL_CONNECTION: {
				ExternalConnection externalConnection = (ExternalConnection)theEObject;
				T result = caseExternalConnection(externalConnection);
				if (result == null) result = caseConnection(externalConnection);
				if (result == null) result = caseEntity(externalConnection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.PROXY: {
				Proxy proxy = (Proxy)theEObject;
				T result = caseProxy(proxy);
				if (result == null) result = caseEntity(proxy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.SERVICE_PROXY: {
				ServiceProxy serviceProxy = (ServiceProxy)theEObject;
				T result = caseServiceProxy(serviceProxy);
				if (result == null) result = caseProxy(serviceProxy);
				if (result == null) result = caseEntity(serviceProxy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ArchitecturePackage.USAGE_PROXY: {
				UsageProxy usageProxy = (UsageProxy)theEObject;
				T result = caseUsageProxy(usageProxy);
				if (result == null) result = caseProxy(usageProxy);
				if (result == null) result = caseOperationInterfaceContainer(usageProxy);
				if (result == null) result = caseEntity(usageProxy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Architecture</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Architecture</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArchitecture(Architecture object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cloud Environment</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cloud Environment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCloudEnvironment(CloudEnvironment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Infrastructure Layer</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Infrastructure Layer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInfrastructureLayer(InfrastructureLayer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Platform Layer</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Platform Layer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePlatformLayer(PlatformLayer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Software Layer</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Software Layer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSoftwareLayer(SoftwareLayer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseService(Service object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Infrastructure Service</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Infrastructure Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInfrastructureService(InfrastructureService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Platform Service</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Platform Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePlatformService(PlatformService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Software Service</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Software Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSoftwareService(SoftwareService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deployable Platform Service</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deployable Platform Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeployablePlatformService(DeployablePlatformService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Platform Service</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Platform Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalPlatformService(ExternalPlatformService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deployable Runtime Service</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deployable Runtime Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeployableRuntimeService(DeployableRuntimeService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deployable Support Service</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deployable Support Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeployableSupportService(DeployableSupportService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Runtime Service</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Runtime Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalRuntimeService(ExternalRuntimeService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Support Service</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Support Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalSupportService(ExternalSupportService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Software Service</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Software Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalSoftwareService(ExternalSoftwareService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deployable Software Service</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deployable Software Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeployableSoftwareService(DeployableSoftwareService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Computing Infrastructure Service</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Computing Infrastructure Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComputingInfrastructureService(
			ComputingInfrastructureService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Software Service Container</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Software Service Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSoftwareServiceContainer(SoftwareServiceContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Interface Container</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Interface Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationInterfaceContainer(OperationInterfaceContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnection(Connection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Internal Connection</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Internal Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInternalConnection(InternalConnection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hybrid Connection</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hybrid Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHybridConnection(HybridConnection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Connection</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalConnection(ExternalConnection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Proxy</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Proxy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProxy(Proxy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service Proxy</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service Proxy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseServiceProxy(ServiceProxy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Usage Proxy</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Usage Proxy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUsageProxy(UsageProxy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entity</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
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
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch, but this is
	 * the last case anyway. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} // ArchitectureSwitch
