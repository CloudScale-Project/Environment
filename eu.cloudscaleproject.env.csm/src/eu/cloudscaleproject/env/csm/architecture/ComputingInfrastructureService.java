/**
 */
package eu.cloudscaleproject.env.csm.architecture;

import org.eclipse.emf.common.util.EList;

import eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Computing Infrastructure Service</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The Instance model represents an IaaS computing node; virtual machine in the elastic cloud environment. The node components (see Node) can be deployed/installed on instances.
 * The behaviour and hardware are specified through instance descriptors (see InstanceDescriptor), which are specified in cloud provider descriptor; each IaaS cloud environment has a set of predefined computing nodes that can be used. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService#getDescriptor <em>Descriptor</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService#getPlatformServices <em>Platform Services</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getComputingInfrastructureService()
 * @model
 * @generated
 */
public interface ComputingInfrastructureService extends InfrastructureService {
	/**
	 * Returns the value of the '<em><b>Descriptor</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Descriptor</em>' reference.
	 * @see #setDescriptor(ComputingInfrastructureServiceDescriptor)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getComputingInfrastructureService_Descriptor()
	 * @model
	 * @generated
	 */
	ComputingInfrastructureServiceDescriptor getDescriptor();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService#getDescriptor <em>Descriptor</em>}' reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Descriptor</em>' reference.
	 * @see #getDescriptor()
	 * @generated
	 */
	void setDescriptor(ComputingInfrastructureServiceDescriptor value);

	/**
	 * Returns the value of the '<em><b>Platform Services</b></em>' reference
	 * list. The list contents are of type
	 * {@link eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService}
	 * . It is bidirectional and its opposite is '
	 * {@link eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService#getComputingInfrastructureService
	 * <em>Computing Infrastructure Service</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Platform Services</em>' reference list.
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getComputingInfrastructureService_PlatformServices()
	 * @see eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService#getComputingInfrastructureService
	 * @model opposite="computingInfrastructureService"
	 * @generated
	 */
	EList<DeployablePlatformService> getPlatformServices();

} // ComputingInfrastructureService
