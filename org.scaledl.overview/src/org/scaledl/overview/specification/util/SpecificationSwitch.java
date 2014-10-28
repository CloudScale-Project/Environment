/**
 */
package org.scaledl.overview.specification.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.scaledl.overview.core.Entity;
import org.scaledl.overview.specification.AvailabilityZoneDescriptor;
import org.scaledl.overview.specification.CloudEnvironmentDescriptor;
import org.scaledl.overview.specification.CloudSpecification;
import org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.ComputingResourceDescriptor;
import org.scaledl.overview.specification.Descriptor;
import org.scaledl.overview.specification.ExternalSoftwareServiceDescriptor;
import org.scaledl.overview.specification.InfrastructureServiceDescriptor;
import org.scaledl.overview.specification.NetworkInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.PlatformRuntimeServiceDescriptor;
import org.scaledl.overview.specification.PlatformServiceDescriptor;
import org.scaledl.overview.specification.PlatformSupportServiceDescriptor;
import org.scaledl.overview.specification.ProvidedPlatformRuntimeServiceDescriptor;
import org.scaledl.overview.specification.ProvidedPlatformServiceDescriptor;
import org.scaledl.overview.specification.ProvidedPlatformSupportServiceDescriptor;
import org.scaledl.overview.specification.ProvidedServiceDescriptor;
import org.scaledl.overview.specification.ProvidedSoftwareServiceDescriptor;
import org.scaledl.overview.specification.RegionDescriptor;
import org.scaledl.overview.specification.ServiceDescriptor;
import org.scaledl.overview.specification.ServiceSpecification;
import org.scaledl.overview.specification.SoftwareServiceDescriptor;
import org.scaledl.overview.specification.Specification;
import org.scaledl.overview.specification.SpecificationPackage;
import org.scaledl.overview.specification.SystemSpecification;

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
 * @see org.scaledl.overview.specification.SpecificationPackage
 * @generated
 */
public class SpecificationSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SpecificationPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecificationSwitch() {
		if (modelPackage == null) {
			modelPackage = SpecificationPackage.eINSTANCE;
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
			case SpecificationPackage.SPECIFICATION: {
				Specification specification = (Specification)theEObject;
				T result = caseSpecification(specification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.SYSTEM_SPECIFICATION: {
				SystemSpecification systemSpecification = (SystemSpecification)theEObject;
				T result = caseSystemSpecification(systemSpecification);
				if (result == null) result = caseSpecification(systemSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.CLOUD_SPECIFICATION: {
				CloudSpecification cloudSpecification = (CloudSpecification)theEObject;
				T result = caseCloudSpecification(cloudSpecification);
				if (result == null) result = caseSpecification(cloudSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.SERVICE_SPECIFICATION: {
				ServiceSpecification serviceSpecification = (ServiceSpecification)theEObject;
				T result = caseServiceSpecification(serviceSpecification);
				if (result == null) result = caseSpecification(serviceSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.DESCRIPTOR: {
				Descriptor descriptor = (Descriptor)theEObject;
				T result = caseDescriptor(descriptor);
				if (result == null) result = caseEntity(descriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR: {
				CloudEnvironmentDescriptor cloudEnvironmentDescriptor = (CloudEnvironmentDescriptor)theEObject;
				T result = caseCloudEnvironmentDescriptor(cloudEnvironmentDescriptor);
				if (result == null) result = caseDescriptor(cloudEnvironmentDescriptor);
				if (result == null) result = caseEntity(cloudEnvironmentDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.REGION_DESCRIPTOR: {
				RegionDescriptor regionDescriptor = (RegionDescriptor)theEObject;
				T result = caseRegionDescriptor(regionDescriptor);
				if (result == null) result = caseDescriptor(regionDescriptor);
				if (result == null) result = caseEntity(regionDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.AVAILABILITY_ZONE_DESCRIPTOR: {
				AvailabilityZoneDescriptor availabilityZoneDescriptor = (AvailabilityZoneDescriptor)theEObject;
				T result = caseAvailabilityZoneDescriptor(availabilityZoneDescriptor);
				if (result == null) result = caseDescriptor(availabilityZoneDescriptor);
				if (result == null) result = caseEntity(availabilityZoneDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.SERVICE_DESCRIPTOR: {
				ServiceDescriptor serviceDescriptor = (ServiceDescriptor)theEObject;
				T result = caseServiceDescriptor(serviceDescriptor);
				if (result == null) result = caseDescriptor(serviceDescriptor);
				if (result == null) result = caseEntity(serviceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.PROVIDED_SERVICE_DESCRIPTOR: {
				ProvidedServiceDescriptor providedServiceDescriptor = (ProvidedServiceDescriptor)theEObject;
				T result = caseProvidedServiceDescriptor(providedServiceDescriptor);
				if (result == null) result = caseDescriptor(providedServiceDescriptor);
				if (result == null) result = caseEntity(providedServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.INFRASTRUCTURE_SERVICE_DESCRIPTOR: {
				InfrastructureServiceDescriptor infrastructureServiceDescriptor = (InfrastructureServiceDescriptor)theEObject;
				T result = caseInfrastructureServiceDescriptor(infrastructureServiceDescriptor);
				if (result == null) result = caseProvidedServiceDescriptor(infrastructureServiceDescriptor);
				if (result == null) result = caseDescriptor(infrastructureServiceDescriptor);
				if (result == null) result = caseEntity(infrastructureServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR: {
				NetworkInfrastructureServiceDescriptor networkInfrastructureServiceDescriptor = (NetworkInfrastructureServiceDescriptor)theEObject;
				T result = caseNetworkInfrastructureServiceDescriptor(networkInfrastructureServiceDescriptor);
				if (result == null) result = caseInfrastructureServiceDescriptor(networkInfrastructureServiceDescriptor);
				if (result == null) result = caseProvidedServiceDescriptor(networkInfrastructureServiceDescriptor);
				if (result == null) result = caseDescriptor(networkInfrastructureServiceDescriptor);
				if (result == null) result = caseEntity(networkInfrastructureServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR: {
				ComputingInfrastructureServiceDescriptor computingInfrastructureServiceDescriptor = (ComputingInfrastructureServiceDescriptor)theEObject;
				T result = caseComputingInfrastructureServiceDescriptor(computingInfrastructureServiceDescriptor);
				if (result == null) result = caseInfrastructureServiceDescriptor(computingInfrastructureServiceDescriptor);
				if (result == null) result = caseProvidedServiceDescriptor(computingInfrastructureServiceDescriptor);
				if (result == null) result = caseDescriptor(computingInfrastructureServiceDescriptor);
				if (result == null) result = caseEntity(computingInfrastructureServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.COMPUTING_RESOURCE_DESCRIPTOR: {
				ComputingResourceDescriptor computingResourceDescriptor = (ComputingResourceDescriptor)theEObject;
				T result = caseComputingResourceDescriptor(computingResourceDescriptor);
				if (result == null) result = caseDescriptor(computingResourceDescriptor);
				if (result == null) result = caseEntity(computingResourceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.PLATFORM_SERVICE_DESCRIPTOR: {
				PlatformServiceDescriptor platformServiceDescriptor = (PlatformServiceDescriptor)theEObject;
				T result = casePlatformServiceDescriptor(platformServiceDescriptor);
				if (result == null) result = caseServiceDescriptor(platformServiceDescriptor);
				if (result == null) result = caseDescriptor(platformServiceDescriptor);
				if (result == null) result = caseEntity(platformServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.PLATFORM_RUNTIME_SERVICE_DESCRIPTOR: {
				PlatformRuntimeServiceDescriptor platformRuntimeServiceDescriptor = (PlatformRuntimeServiceDescriptor)theEObject;
				T result = casePlatformRuntimeServiceDescriptor(platformRuntimeServiceDescriptor);
				if (result == null) result = casePlatformServiceDescriptor(platformRuntimeServiceDescriptor);
				if (result == null) result = caseServiceDescriptor(platformRuntimeServiceDescriptor);
				if (result == null) result = caseDescriptor(platformRuntimeServiceDescriptor);
				if (result == null) result = caseEntity(platformRuntimeServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.PLATFORM_SUPPORT_SERVICE_DESCRIPTOR: {
				PlatformSupportServiceDescriptor platformSupportServiceDescriptor = (PlatformSupportServiceDescriptor)theEObject;
				T result = casePlatformSupportServiceDescriptor(platformSupportServiceDescriptor);
				if (result == null) result = casePlatformServiceDescriptor(platformSupportServiceDescriptor);
				if (result == null) result = caseServiceDescriptor(platformSupportServiceDescriptor);
				if (result == null) result = caseDescriptor(platformSupportServiceDescriptor);
				if (result == null) result = caseEntity(platformSupportServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR: {
				ProvidedPlatformServiceDescriptor providedPlatformServiceDescriptor = (ProvidedPlatformServiceDescriptor)theEObject;
				T result = caseProvidedPlatformServiceDescriptor(providedPlatformServiceDescriptor);
				if (result == null) result = casePlatformServiceDescriptor(providedPlatformServiceDescriptor);
				if (result == null) result = caseProvidedServiceDescriptor(providedPlatformServiceDescriptor);
				if (result == null) result = caseServiceDescriptor(providedPlatformServiceDescriptor);
				if (result == null) result = caseDescriptor(providedPlatformServiceDescriptor);
				if (result == null) result = caseEntity(providedPlatformServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.PROVIDED_PLATFORM_RUNTIME_SERVICE_DESCRIPTOR: {
				ProvidedPlatformRuntimeServiceDescriptor providedPlatformRuntimeServiceDescriptor = (ProvidedPlatformRuntimeServiceDescriptor)theEObject;
				T result = caseProvidedPlatformRuntimeServiceDescriptor(providedPlatformRuntimeServiceDescriptor);
				if (result == null) result = caseProvidedPlatformServiceDescriptor(providedPlatformRuntimeServiceDescriptor);
				if (result == null) result = casePlatformServiceDescriptor(providedPlatformRuntimeServiceDescriptor);
				if (result == null) result = caseProvidedServiceDescriptor(providedPlatformRuntimeServiceDescriptor);
				if (result == null) result = caseServiceDescriptor(providedPlatformRuntimeServiceDescriptor);
				if (result == null) result = caseDescriptor(providedPlatformRuntimeServiceDescriptor);
				if (result == null) result = caseEntity(providedPlatformRuntimeServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.PROVIDED_PLATFORM_SUPPORT_SERVICE_DESCRIPTOR: {
				ProvidedPlatformSupportServiceDescriptor providedPlatformSupportServiceDescriptor = (ProvidedPlatformSupportServiceDescriptor)theEObject;
				T result = caseProvidedPlatformSupportServiceDescriptor(providedPlatformSupportServiceDescriptor);
				if (result == null) result = caseProvidedPlatformServiceDescriptor(providedPlatformSupportServiceDescriptor);
				if (result == null) result = casePlatformServiceDescriptor(providedPlatformSupportServiceDescriptor);
				if (result == null) result = caseProvidedServiceDescriptor(providedPlatformSupportServiceDescriptor);
				if (result == null) result = caseServiceDescriptor(providedPlatformSupportServiceDescriptor);
				if (result == null) result = caseDescriptor(providedPlatformSupportServiceDescriptor);
				if (result == null) result = caseEntity(providedPlatformSupportServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.SOFTWARE_SERVICE_DESCRIPTOR: {
				SoftwareServiceDescriptor softwareServiceDescriptor = (SoftwareServiceDescriptor)theEObject;
				T result = caseSoftwareServiceDescriptor(softwareServiceDescriptor);
				if (result == null) result = caseServiceDescriptor(softwareServiceDescriptor);
				if (result == null) result = caseDescriptor(softwareServiceDescriptor);
				if (result == null) result = caseEntity(softwareServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR: {
				ProvidedSoftwareServiceDescriptor providedSoftwareServiceDescriptor = (ProvidedSoftwareServiceDescriptor)theEObject;
				T result = caseProvidedSoftwareServiceDescriptor(providedSoftwareServiceDescriptor);
				if (result == null) result = caseSoftwareServiceDescriptor(providedSoftwareServiceDescriptor);
				if (result == null) result = caseProvidedServiceDescriptor(providedSoftwareServiceDescriptor);
				if (result == null) result = caseServiceDescriptor(providedSoftwareServiceDescriptor);
				if (result == null) result = caseDescriptor(providedSoftwareServiceDescriptor);
				if (result == null) result = caseEntity(providedSoftwareServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SpecificationPackage.EXTERNAL_SOFTWARE_SERVICE_DESCRIPTOR: {
				ExternalSoftwareServiceDescriptor externalSoftwareServiceDescriptor = (ExternalSoftwareServiceDescriptor)theEObject;
				T result = caseExternalSoftwareServiceDescriptor(externalSoftwareServiceDescriptor);
				if (result == null) result = caseProvidedSoftwareServiceDescriptor(externalSoftwareServiceDescriptor);
				if (result == null) result = caseSoftwareServiceDescriptor(externalSoftwareServiceDescriptor);
				if (result == null) result = caseProvidedServiceDescriptor(externalSoftwareServiceDescriptor);
				if (result == null) result = caseServiceDescriptor(externalSoftwareServiceDescriptor);
				if (result == null) result = caseDescriptor(externalSoftwareServiceDescriptor);
				if (result == null) result = caseEntity(externalSoftwareServiceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpecification(Specification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemSpecification(SystemSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cloud Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cloud Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCloudSpecification(CloudSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseServiceSpecification(ServiceSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Availability Zone Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAvailabilityZoneDescriptor(AvailabilityZoneDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseServiceDescriptor(ServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Provided Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Provided Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProvidedServiceDescriptor(ProvidedServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Infrastructure Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Infrastructure Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInfrastructureServiceDescriptor(InfrastructureServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Network Infrastructure Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Network Infrastructure Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNetworkInfrastructureServiceDescriptor(NetworkInfrastructureServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Computing Infrastructure Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Computing Infrastructure Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComputingInfrastructureServiceDescriptor(ComputingInfrastructureServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Computing Resource Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Computing Resource Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComputingResourceDescriptor(ComputingResourceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Platform Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Platform Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePlatformServiceDescriptor(PlatformServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Platform Runtime Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Platform Runtime Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePlatformRuntimeServiceDescriptor(PlatformRuntimeServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Platform Support Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Platform Support Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePlatformSupportServiceDescriptor(PlatformSupportServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Provided Platform Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Provided Platform Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProvidedPlatformServiceDescriptor(ProvidedPlatformServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Provided Platform Runtime Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Provided Platform Runtime Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProvidedPlatformRuntimeServiceDescriptor(ProvidedPlatformRuntimeServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Provided Platform Support Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Provided Platform Support Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProvidedPlatformSupportServiceDescriptor(ProvidedPlatformSupportServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Software Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Software Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSoftwareServiceDescriptor(SoftwareServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Provided Software Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Provided Software Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProvidedSoftwareServiceDescriptor(ProvidedSoftwareServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Software Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Software Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalSoftwareServiceDescriptor(ExternalSoftwareServiceDescriptor object) {
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

} //SpecificationSwitch
