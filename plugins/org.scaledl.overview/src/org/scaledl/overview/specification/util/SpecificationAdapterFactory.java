/**
 */
package org.scaledl.overview.specification.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.scaledl.overview.core.Entity;

import org.scaledl.overview.specification.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.scaledl.overview.specification.SpecificationPackage
 * @generated
 */
public class SpecificationAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SpecificationPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecificationAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = SpecificationPackage.eINSTANCE;
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
	protected SpecificationSwitch<Adapter> modelSwitch =
		new SpecificationSwitch<Adapter>() {
			@Override
			public Adapter caseSpecification(Specification object) {
				return createSpecificationAdapter();
			}
			@Override
			public Adapter caseSystemSpecification(SystemSpecification object) {
				return createSystemSpecificationAdapter();
			}
			@Override
			public Adapter caseCloudSpecification(CloudSpecification object) {
				return createCloudSpecificationAdapter();
			}
			@Override
			public Adapter caseServiceSpecification(ServiceSpecification object) {
				return createServiceSpecificationAdapter();
			}
			@Override
			public Adapter caseDescriptor(Descriptor object) {
				return createDescriptorAdapter();
			}
			@Override
			public Adapter caseCloudEnvironmentDescriptor(CloudEnvironmentDescriptor object) {
				return createCloudEnvironmentDescriptorAdapter();
			}
			@Override
			public Adapter caseRegionDescriptor(RegionDescriptor object) {
				return createRegionDescriptorAdapter();
			}
			@Override
			public Adapter caseAvailabilityZoneDescriptor(AvailabilityZoneDescriptor object) {
				return createAvailabilityZoneDescriptorAdapter();
			}
			@Override
			public Adapter caseServiceDescriptor(ServiceDescriptor object) {
				return createServiceDescriptorAdapter();
			}
			@Override
			public Adapter caseProvidedServiceDescriptor(ProvidedServiceDescriptor object) {
				return createProvidedServiceDescriptorAdapter();
			}
			@Override
			public Adapter caseInfrastructureServiceDescriptor(InfrastructureServiceDescriptor object) {
				return createInfrastructureServiceDescriptorAdapter();
			}
			@Override
			public Adapter caseNetworkInfrastructureServiceDescriptor(NetworkInfrastructureServiceDescriptor object) {
				return createNetworkInfrastructureServiceDescriptorAdapter();
			}
			@Override
			public Adapter caseComputingInfrastructureServiceDescriptor(ComputingInfrastructureServiceDescriptor object) {
				return createComputingInfrastructureServiceDescriptorAdapter();
			}
			@Override
			public Adapter caseComputingResourceDescriptor(ComputingResourceDescriptor object) {
				return createComputingResourceDescriptorAdapter();
			}
			@Override
			public Adapter casePlatformServiceDescriptor(PlatformServiceDescriptor object) {
				return createPlatformServiceDescriptorAdapter();
			}
			@Override
			public Adapter casePlatformRuntimeServiceDescriptor(PlatformRuntimeServiceDescriptor object) {
				return createPlatformRuntimeServiceDescriptorAdapter();
			}
			@Override
			public Adapter casePlatformSupportServiceDescriptor(PlatformSupportServiceDescriptor object) {
				return createPlatformSupportServiceDescriptorAdapter();
			}
			@Override
			public Adapter caseProvidedPlatformServiceDescriptor(ProvidedPlatformServiceDescriptor object) {
				return createProvidedPlatformServiceDescriptorAdapter();
			}
			@Override
			public Adapter caseProvidedPlatformRuntimeServiceDescriptor(ProvidedPlatformRuntimeServiceDescriptor object) {
				return createProvidedPlatformRuntimeServiceDescriptorAdapter();
			}
			@Override
			public Adapter caseProvidedPlatformSupportServiceDescriptor(ProvidedPlatformSupportServiceDescriptor object) {
				return createProvidedPlatformSupportServiceDescriptorAdapter();
			}
			@Override
			public Adapter caseSoftwareServiceDescriptor(SoftwareServiceDescriptor object) {
				return createSoftwareServiceDescriptorAdapter();
			}
			@Override
			public Adapter caseProvidedSoftwareServiceDescriptor(ProvidedSoftwareServiceDescriptor object) {
				return createProvidedSoftwareServiceDescriptorAdapter();
			}
			@Override
			public Adapter caseExternalSoftwareServiceDescriptor(ExternalSoftwareServiceDescriptor object) {
				return createExternalSoftwareServiceDescriptorAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.Specification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.Specification
	 * @generated
	 */
	public Adapter createSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.SystemSpecification <em>System Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.SystemSpecification
	 * @generated
	 */
	public Adapter createSystemSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.CloudSpecification <em>Cloud Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.CloudSpecification
	 * @generated
	 */
	public Adapter createCloudSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.ServiceSpecification <em>Service Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.ServiceSpecification
	 * @generated
	 */
	public Adapter createServiceSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.Descriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.Descriptor
	 * @generated
	 */
	public Adapter createDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.CloudEnvironmentDescriptor <em>Cloud Environment Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.CloudEnvironmentDescriptor
	 * @generated
	 */
	public Adapter createCloudEnvironmentDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.RegionDescriptor <em>Region Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.RegionDescriptor
	 * @generated
	 */
	public Adapter createRegionDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.AvailabilityZoneDescriptor <em>Availability Zone Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.AvailabilityZoneDescriptor
	 * @generated
	 */
	public Adapter createAvailabilityZoneDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.ServiceDescriptor <em>Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.ServiceDescriptor
	 * @generated
	 */
	public Adapter createServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.ProvidedServiceDescriptor <em>Provided Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.ProvidedServiceDescriptor
	 * @generated
	 */
	public Adapter createProvidedServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.InfrastructureServiceDescriptor <em>Infrastructure Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.InfrastructureServiceDescriptor
	 * @generated
	 */
	public Adapter createInfrastructureServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.NetworkInfrastructureServiceDescriptor <em>Network Infrastructure Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.NetworkInfrastructureServiceDescriptor
	 * @generated
	 */
	public Adapter createNetworkInfrastructureServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor <em>Computing Infrastructure Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor
	 * @generated
	 */
	public Adapter createComputingInfrastructureServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.ComputingResourceDescriptor <em>Computing Resource Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.ComputingResourceDescriptor
	 * @generated
	 */
	public Adapter createComputingResourceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.PlatformServiceDescriptor <em>Platform Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.PlatformServiceDescriptor
	 * @generated
	 */
	public Adapter createPlatformServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.PlatformRuntimeServiceDescriptor <em>Platform Runtime Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.PlatformRuntimeServiceDescriptor
	 * @generated
	 */
	public Adapter createPlatformRuntimeServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.PlatformSupportServiceDescriptor <em>Platform Support Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.PlatformSupportServiceDescriptor
	 * @generated
	 */
	public Adapter createPlatformSupportServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.ProvidedPlatformServiceDescriptor <em>Provided Platform Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.ProvidedPlatformServiceDescriptor
	 * @generated
	 */
	public Adapter createProvidedPlatformServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.ProvidedPlatformRuntimeServiceDescriptor <em>Provided Platform Runtime Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.ProvidedPlatformRuntimeServiceDescriptor
	 * @generated
	 */
	public Adapter createProvidedPlatformRuntimeServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.ProvidedPlatformSupportServiceDescriptor <em>Provided Platform Support Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.ProvidedPlatformSupportServiceDescriptor
	 * @generated
	 */
	public Adapter createProvidedPlatformSupportServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.SoftwareServiceDescriptor <em>Software Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.SoftwareServiceDescriptor
	 * @generated
	 */
	public Adapter createSoftwareServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.ProvidedSoftwareServiceDescriptor <em>Provided Software Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.ProvidedSoftwareServiceDescriptor
	 * @generated
	 */
	public Adapter createProvidedSoftwareServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.scaledl.overview.specification.ExternalSoftwareServiceDescriptor <em>External Software Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.scaledl.overview.specification.ExternalSoftwareServiceDescriptor
	 * @generated
	 */
	public Adapter createExternalSoftwareServiceDescriptorAdapter() {
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

} //SpecificationAdapterFactory
