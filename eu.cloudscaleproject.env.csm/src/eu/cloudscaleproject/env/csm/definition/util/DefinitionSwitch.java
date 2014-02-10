/**
 */
package eu.cloudscaleproject.env.csm.definition.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import eu.cloudscaleproject.env.csm.core.Entity;
import eu.cloudscaleproject.env.csm.definition.AvailabilityZoneDescriptor;
import eu.cloudscaleproject.env.csm.definition.CloudDefinition;
import eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor;
import eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.Definition;
import eu.cloudscaleproject.env.csm.definition.DefinitionPackage;
import eu.cloudscaleproject.env.csm.definition.DeployablePlatformServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.DeployableRuntimeServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.DeployableSupportServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.Descriptor;
import eu.cloudscaleproject.env.csm.definition.ExternalPlatformServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.ExternalRuntimeServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.ExternalSupportServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.InfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.RegionDescriptor;
import eu.cloudscaleproject.env.csm.definition.SoftwareDefinition;
import eu.cloudscaleproject.env.csm.definition.SystemDefinition;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage
 * @generated
 */
public class DefinitionSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static DefinitionPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public DefinitionSwitch() {
		if (modelPackage == null) {
			modelPackage = DefinitionPackage.eINSTANCE;
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
			case DefinitionPackage.DEFINITION: {
				Definition definition = (Definition)theEObject;
				T result = caseDefinition(definition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DefinitionPackage.SYSTEM_DEFINITION: {
				SystemDefinition systemDefinition = (SystemDefinition)theEObject;
				T result = caseSystemDefinition(systemDefinition);
				if (result == null) result = caseDefinition(systemDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DefinitionPackage.CLOUD_DEFINITION: {
				CloudDefinition cloudDefinition = (CloudDefinition)theEObject;
				T result = caseCloudDefinition(cloudDefinition);
				if (result == null) result = caseDefinition(cloudDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DefinitionPackage.SOFTWARE_DEFINITION: {
				SoftwareDefinition softwareDefinition = (SoftwareDefinition)theEObject;
				T result = caseSoftwareDefinition(softwareDefinition);
				if (result == null) result = caseDefinition(softwareDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DefinitionPackage.DESCRIPTOR: {
				Descriptor descriptor = (Descriptor)theEObject;
				T result = caseDescriptor(descriptor);
				if (result == null) result = caseEntity(descriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DefinitionPackage.CLOUD_ENVIRONMENT_DESCRIPTOR: {
				CloudEnvironmentDescriptor cloudEnvironmentDescriptor = (CloudEnvironmentDescriptor)theEObject;
				T result = caseCloudEnvironmentDescriptor(cloudEnvironmentDescriptor);
				if (result == null) result = caseDescriptor(cloudEnvironmentDescriptor);
				if (result == null) result = caseEntity(cloudEnvironmentDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DefinitionPackage.REGION_DESCRIPTOR: {
				RegionDescriptor regionDescriptor = (RegionDescriptor)theEObject;
				T result = caseRegionDescriptor(regionDescriptor);
				if (result == null) result = caseDescriptor(regionDescriptor);
				if (result == null) result = caseEntity(regionDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DefinitionPackage.AVAILABILITY_ZONE_DESCRIPTOR: {
				AvailabilityZoneDescriptor availabilityZoneDescriptor = (AvailabilityZoneDescriptor)theEObject;
				T result = caseAvailabilityZoneDescriptor(availabilityZoneDescriptor);
				if (result == null) result = caseDescriptor(availabilityZoneDescriptor);
				if (result == null) result = caseEntity(availabilityZoneDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DefinitionPackage.INFRASTRUCTURE_SERVICE_DESCRIPTOR: {
				InfrastructureServiceDescriptor infrastructureServiceDescriptor = (InfrastructureServiceDescriptor)theEObject;
				T result = caseInfrastructureServiceDescriptor(infrastructureServiceDescriptor);
				if (result == null) result = caseDescriptor(infrastructureServiceDescriptor);
				if (result == null) result = caseEntity(infrastructureServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DefinitionPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR: {
				NetworkInfrastructureServiceDescriptor networkInfrastructureServiceDescriptor = (NetworkInfrastructureServiceDescriptor)theEObject;
				T result = caseNetworkInfrastructureServiceDescriptor(networkInfrastructureServiceDescriptor);
				if (result == null) result = caseInfrastructureServiceDescriptor(networkInfrastructureServiceDescriptor);
				if (result == null) result = caseDescriptor(networkInfrastructureServiceDescriptor);
				if (result == null) result = caseEntity(networkInfrastructureServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR: {
				ComputingInfrastructureServiceDescriptor computingInfrastructureServiceDescriptor = (ComputingInfrastructureServiceDescriptor)theEObject;
				T result = caseComputingInfrastructureServiceDescriptor(computingInfrastructureServiceDescriptor);
				if (result == null) result = caseInfrastructureServiceDescriptor(computingInfrastructureServiceDescriptor);
				if (result == null) result = caseDescriptor(computingInfrastructureServiceDescriptor);
				if (result == null) result = caseEntity(computingInfrastructureServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DefinitionPackage.DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTOR: {
				DeployablePlatformServiceDescriptor deployablePlatformServiceDescriptor = (DeployablePlatformServiceDescriptor)theEObject;
				T result = caseDeployablePlatformServiceDescriptor(deployablePlatformServiceDescriptor);
				if (result == null) result = caseDescriptor(deployablePlatformServiceDescriptor);
				if (result == null) result = caseEntity(deployablePlatformServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DefinitionPackage.EXTERNAL_PLATFORM_SERVICE_DESCRIPTOR: {
				ExternalPlatformServiceDescriptor externalPlatformServiceDescriptor = (ExternalPlatformServiceDescriptor)theEObject;
				T result = caseExternalPlatformServiceDescriptor(externalPlatformServiceDescriptor);
				if (result == null) result = caseDescriptor(externalPlatformServiceDescriptor);
				if (result == null) result = caseEntity(externalPlatformServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DefinitionPackage.DEPLOYABLE_RUNTIME_SERVICE_DESCRIPTOR: {
				DeployableRuntimeServiceDescriptor deployableRuntimeServiceDescriptor = (DeployableRuntimeServiceDescriptor)theEObject;
				T result = caseDeployableRuntimeServiceDescriptor(deployableRuntimeServiceDescriptor);
				if (result == null) result = caseDeployablePlatformServiceDescriptor(deployableRuntimeServiceDescriptor);
				if (result == null) result = caseDescriptor(deployableRuntimeServiceDescriptor);
				if (result == null) result = caseEntity(deployableRuntimeServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DefinitionPackage.EXTERNAL_RUNTIME_SERVICE_DESCRIPTOR: {
				ExternalRuntimeServiceDescriptor externalRuntimeServiceDescriptor = (ExternalRuntimeServiceDescriptor)theEObject;
				T result = caseExternalRuntimeServiceDescriptor(externalRuntimeServiceDescriptor);
				if (result == null) result = caseExternalPlatformServiceDescriptor(externalRuntimeServiceDescriptor);
				if (result == null) result = caseDescriptor(externalRuntimeServiceDescriptor);
				if (result == null) result = caseEntity(externalRuntimeServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DefinitionPackage.DEPLOYABLE_SUPPORT_SERVICE_DESCRIPTOR: {
				DeployableSupportServiceDescriptor deployableSupportServiceDescriptor = (DeployableSupportServiceDescriptor)theEObject;
				T result = caseDeployableSupportServiceDescriptor(deployableSupportServiceDescriptor);
				if (result == null) result = caseDeployablePlatformServiceDescriptor(deployableSupportServiceDescriptor);
				if (result == null) result = caseDescriptor(deployableSupportServiceDescriptor);
				if (result == null) result = caseEntity(deployableSupportServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DefinitionPackage.EXTERNAL_SUPPORT_SERVICE_DESCRIPTOR: {
				ExternalSupportServiceDescriptor externalSupportServiceDescriptor = (ExternalSupportServiceDescriptor)theEObject;
				T result = caseExternalSupportServiceDescriptor(externalSupportServiceDescriptor);
				if (result == null) result = caseExternalPlatformServiceDescriptor(externalSupportServiceDescriptor);
				if (result == null) result = caseDescriptor(externalSupportServiceDescriptor);
				if (result == null) result = caseEntity(externalSupportServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Definition</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDefinition(Definition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Definition</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemDefinition(SystemDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cloud Definition</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cloud Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCloudDefinition(CloudDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Software Definition</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Software Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSoftwareDefinition(SoftwareDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Descriptor</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDescriptor(Descriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cloud Environment Descriptor</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cloud Environment Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCloudEnvironmentDescriptor(CloudEnvironmentDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Region Descriptor</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Region Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRegionDescriptor(RegionDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Availability Zone Descriptor</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Availability Zone Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAvailabilityZoneDescriptor(AvailabilityZoneDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Infrastructure Service Descriptor</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Infrastructure Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInfrastructureServiceDescriptor(
			InfrastructureServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Network Infrastructure Service Descriptor</em>'.
	 * <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Network Infrastructure Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNetworkInfrastructureServiceDescriptor(
			NetworkInfrastructureServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Computing Infrastructure Service Descriptor</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null
	 * result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Computing Infrastructure Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComputingInfrastructureServiceDescriptor(
			ComputingInfrastructureServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deployable Platform Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deployable Platform Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeployablePlatformServiceDescriptor(
			DeployablePlatformServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Platform Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Platform Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalPlatformServiceDescriptor(
			ExternalPlatformServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deployable Runtime Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deployable Runtime Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeployableRuntimeServiceDescriptor(
			DeployableRuntimeServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Runtime Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Runtime Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalRuntimeServiceDescriptor(
			ExternalRuntimeServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deployable Support Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deployable Support Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeployableSupportServiceDescriptor(
			DeployableSupportServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Support Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Support Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalSupportServiceDescriptor(
			ExternalSupportServiceDescriptor object) {
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

} // DefinitionSwitch
