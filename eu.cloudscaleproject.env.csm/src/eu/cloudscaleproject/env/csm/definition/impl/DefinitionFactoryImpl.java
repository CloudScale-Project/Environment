/**
 */
package eu.cloudscaleproject.env.csm.definition.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import eu.cloudscaleproject.env.csm.definition.AvailabilityZoneDescriptor;
import eu.cloudscaleproject.env.csm.definition.CloudDefinition;
import eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor;
import eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.DefinitionFactory;
import eu.cloudscaleproject.env.csm.definition.DefinitionPackage;
import eu.cloudscaleproject.env.csm.definition.DeployableRuntimeServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.DeployableSupportServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.Descriptor;
import eu.cloudscaleproject.env.csm.definition.ExternalRuntimeServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.ExternalSupportServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.InfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.RegionDescriptor;
import eu.cloudscaleproject.env.csm.definition.SoftwareDefinition;
import eu.cloudscaleproject.env.csm.definition.SystemDefinition;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class DefinitionFactoryImpl extends EFactoryImpl implements
		DefinitionFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static DefinitionFactory init() {
		try {
			DefinitionFactory theDefinitionFactory = (DefinitionFactory)EPackage.Registry.INSTANCE.getEFactory("http://eu.cloudscaleproject.env/CloudscaleComponentModel/Definition/1.0"); 
			if (theDefinitionFactory != null) {
				return theDefinitionFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DefinitionFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public DefinitionFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case DefinitionPackage.SYSTEM_DEFINITION: return createSystemDefinition();
			case DefinitionPackage.CLOUD_DEFINITION: return createCloudDefinition();
			case DefinitionPackage.SOFTWARE_DEFINITION: return createSoftwareDefinition();
			case DefinitionPackage.DESCRIPTOR: return createDescriptor();
			case DefinitionPackage.CLOUD_ENVIRONMENT_DESCRIPTOR: return createCloudEnvironmentDescriptor();
			case DefinitionPackage.REGION_DESCRIPTOR: return createRegionDescriptor();
			case DefinitionPackage.AVAILABILITY_ZONE_DESCRIPTOR: return createAvailabilityZoneDescriptor();
			case DefinitionPackage.INFRASTRUCTURE_SERVICE_DESCRIPTOR: return createInfrastructureServiceDescriptor();
			case DefinitionPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR: return createNetworkInfrastructureServiceDescriptor();
			case DefinitionPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR: return createComputingInfrastructureServiceDescriptor();
			case DefinitionPackage.DEPLOYABLE_RUNTIME_SERVICE_DESCRIPTOR: return createDeployableRuntimeServiceDescriptor();
			case DefinitionPackage.EXTERNAL_RUNTIME_SERVICE_DESCRIPTOR: return createExternalRuntimeServiceDescriptor();
			case DefinitionPackage.DEPLOYABLE_SUPPORT_SERVICE_DESCRIPTOR: return createDeployableSupportServiceDescriptor();
			case DefinitionPackage.EXTERNAL_SUPPORT_SERVICE_DESCRIPTOR: return createExternalSupportServiceDescriptor();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SystemDefinition createSystemDefinition() {
		SystemDefinitionImpl systemDefinition = new SystemDefinitionImpl();
		return systemDefinition;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CloudDefinition createCloudDefinition() {
		CloudDefinitionImpl cloudDefinition = new CloudDefinitionImpl();
		return cloudDefinition;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareDefinition createSoftwareDefinition() {
		SoftwareDefinitionImpl softwareDefinition = new SoftwareDefinitionImpl();
		return softwareDefinition;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Descriptor createDescriptor() {
		DescriptorImpl descriptor = new DescriptorImpl();
		return descriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CloudEnvironmentDescriptor createCloudEnvironmentDescriptor() {
		CloudEnvironmentDescriptorImpl cloudEnvironmentDescriptor = new CloudEnvironmentDescriptorImpl();
		return cloudEnvironmentDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public RegionDescriptor createRegionDescriptor() {
		RegionDescriptorImpl regionDescriptor = new RegionDescriptorImpl();
		return regionDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public AvailabilityZoneDescriptor createAvailabilityZoneDescriptor() {
		AvailabilityZoneDescriptorImpl availabilityZoneDescriptor = new AvailabilityZoneDescriptorImpl();
		return availabilityZoneDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public InfrastructureServiceDescriptor createInfrastructureServiceDescriptor() {
		InfrastructureServiceDescriptorImpl infrastructureServiceDescriptor = new InfrastructureServiceDescriptorImpl();
		return infrastructureServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkInfrastructureServiceDescriptor createNetworkInfrastructureServiceDescriptor() {
		NetworkInfrastructureServiceDescriptorImpl networkInfrastructureServiceDescriptor = new NetworkInfrastructureServiceDescriptorImpl();
		return networkInfrastructureServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ComputingInfrastructureServiceDescriptor createComputingInfrastructureServiceDescriptor() {
		ComputingInfrastructureServiceDescriptorImpl computingInfrastructureServiceDescriptor = new ComputingInfrastructureServiceDescriptorImpl();
		return computingInfrastructureServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public DeployableRuntimeServiceDescriptor createDeployableRuntimeServiceDescriptor() {
		DeployableRuntimeServiceDescriptorImpl deployableRuntimeServiceDescriptor = new DeployableRuntimeServiceDescriptorImpl();
		return deployableRuntimeServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalRuntimeServiceDescriptor createExternalRuntimeServiceDescriptor() {
		ExternalRuntimeServiceDescriptorImpl externalRuntimeServiceDescriptor = new ExternalRuntimeServiceDescriptorImpl();
		return externalRuntimeServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public DeployableSupportServiceDescriptor createDeployableSupportServiceDescriptor() {
		DeployableSupportServiceDescriptorImpl deployableSupportServiceDescriptor = new DeployableSupportServiceDescriptorImpl();
		return deployableSupportServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalSupportServiceDescriptor createExternalSupportServiceDescriptor() {
		ExternalSupportServiceDescriptorImpl externalSupportServiceDescriptor = new ExternalSupportServiceDescriptorImpl();
		return externalSupportServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public DefinitionPackage getDefinitionPackage() {
		return (DefinitionPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DefinitionPackage getPackage() {
		return DefinitionPackage.eINSTANCE;
	}

} // DefinitionFactoryImpl
