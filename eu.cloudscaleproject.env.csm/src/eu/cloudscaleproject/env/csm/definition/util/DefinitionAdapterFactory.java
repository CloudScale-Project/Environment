/**
 */
package eu.cloudscaleproject.env.csm.definition.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

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
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * @see eu.cloudscaleproject.env.csm.definition.DefinitionPackage
 * @generated
 */
public class DefinitionAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static DefinitionPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public DefinitionAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = DefinitionPackage.eINSTANCE;
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
	protected DefinitionSwitch<Adapter> modelSwitch = new DefinitionSwitch<Adapter>() {
			@Override
			public Adapter caseDefinition(Definition object) {
				return createDefinitionAdapter();
			}
			@Override
			public Adapter caseSystemDefinition(SystemDefinition object) {
				return createSystemDefinitionAdapter();
			}
			@Override
			public Adapter caseCloudDefinition(CloudDefinition object) {
				return createCloudDefinitionAdapter();
			}
			@Override
			public Adapter caseSoftwareDefinition(SoftwareDefinition object) {
				return createSoftwareDefinitionAdapter();
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
			public Adapter caseDeployablePlatformServiceDescriptor(DeployablePlatformServiceDescriptor object) {
				return createDeployablePlatformServiceDescriptorAdapter();
			}
			@Override
			public Adapter caseExternalPlatformServiceDescriptor(ExternalPlatformServiceDescriptor object) {
				return createExternalPlatformServiceDescriptorAdapter();
			}
			@Override
			public Adapter caseDeployableRuntimeServiceDescriptor(DeployableRuntimeServiceDescriptor object) {
				return createDeployableRuntimeServiceDescriptorAdapter();
			}
			@Override
			public Adapter caseExternalRuntimeServiceDescriptor(ExternalRuntimeServiceDescriptor object) {
				return createExternalRuntimeServiceDescriptorAdapter();
			}
			@Override
			public Adapter caseDeployableSupportServiceDescriptor(DeployableSupportServiceDescriptor object) {
				return createDeployableSupportServiceDescriptorAdapter();
			}
			@Override
			public Adapter caseExternalSupportServiceDescriptor(ExternalSupportServiceDescriptor object) {
				return createExternalSupportServiceDescriptorAdapter();
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
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.definition.Definition <em>Definition</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.definition.Definition
	 * @generated
	 */
	public Adapter createDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.definition.SystemDefinition <em>System Definition</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.definition.SystemDefinition
	 * @generated
	 */
	public Adapter createSystemDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.definition.CloudDefinition <em>Cloud Definition</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.definition.CloudDefinition
	 * @generated
	 */
	public Adapter createCloudDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.definition.SoftwareDefinition <em>Software Definition</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.definition.SoftwareDefinition
	 * @generated
	 */
	public Adapter createSoftwareDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.definition.Descriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.definition.Descriptor
	 * @generated
	 */
	public Adapter createDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor <em>Cloud Environment Descriptor</em>}'.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor
	 * @generated
	 */
	public Adapter createCloudEnvironmentDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.definition.RegionDescriptor <em>Region Descriptor</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.definition.RegionDescriptor
	 * @generated
	 */
	public Adapter createRegionDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.definition.AvailabilityZoneDescriptor <em>Availability Zone Descriptor</em>}'.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.definition.AvailabilityZoneDescriptor
	 * @generated
	 */
	public Adapter createAvailabilityZoneDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.definition.InfrastructureServiceDescriptor <em>Infrastructure Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.definition.InfrastructureServiceDescriptor
	 * @generated
	 */
	public Adapter createInfrastructureServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor <em>Network Infrastructure Service Descriptor</em>}'.
	 * <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor
	 * @generated
	 */
	public Adapter createNetworkInfrastructureServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor
	 * <em>Computing Infrastructure Service Descriptor</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor
	 * @generated
	 */
	public Adapter createComputingInfrastructureServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.definition.DeployablePlatformServiceDescriptor <em>Deployable Platform Service Descriptor</em>}'.
	 * <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.definition.DeployablePlatformServiceDescriptor
	 * @generated
	 */
	public Adapter createDeployablePlatformServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.definition.ExternalPlatformServiceDescriptor <em>External Platform Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.definition.ExternalPlatformServiceDescriptor
	 * @generated
	 */
	public Adapter createExternalPlatformServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.definition.DeployableRuntimeServiceDescriptor <em>Deployable Runtime Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.definition.DeployableRuntimeServiceDescriptor
	 * @generated
	 */
	public Adapter createDeployableRuntimeServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.definition.ExternalRuntimeServiceDescriptor <em>External Runtime Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.definition.ExternalRuntimeServiceDescriptor
	 * @generated
	 */
	public Adapter createExternalRuntimeServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.definition.DeployableSupportServiceDescriptor <em>Deployable Support Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.definition.DeployableSupportServiceDescriptor
	 * @generated
	 */
	public Adapter createDeployableSupportServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.cloudscaleproject.env.csm.definition.ExternalSupportServiceDescriptor <em>External Support Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.cloudscaleproject.env.csm.definition.ExternalSupportServiceDescriptor
	 * @generated
	 */
	public Adapter createExternalSupportServiceDescriptorAdapter() {
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

} // DefinitionAdapterFactory
