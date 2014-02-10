/**
 */
package eu.cloudscaleproject.env.csm.architecture;

import eu.cloudscaleproject.env.csm.definition.DeployablePlatformServiceDescriptor;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Deployable Platform Service</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService#getComputingInfrastructureService <em>Computing Infrastructure Service</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService#getDescriptor <em>Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getDeployablePlatformService()
 * @model abstract="true"
 * @generated
 */
public interface DeployablePlatformService extends PlatformService {
	/**
	 * Returns the value of the '<em><b>Computing Infrastructure Service</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService#getPlatformServices <em>Platform Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Computing Infrastructure Service</em>'
	 * reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Computing Infrastructure Service</em>' reference.
	 * @see #setComputingInfrastructureService(ComputingInfrastructureService)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getDeployablePlatformService_ComputingInfrastructureService()
	 * @see eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService#getPlatformServices
	 * @model opposite="platformServices"
	 * @generated
	 */
	ComputingInfrastructureService getComputingInfrastructureService();

	/**
	 * Sets the value of the '
	 * {@link eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService#getComputingInfrastructureService
	 * <em>Computing Infrastructure Service</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '
	 *            <em>Computing Infrastructure Service</em>' reference.
	 * @see #getComputingInfrastructureService()
	 * @generated
	 */
	void setComputingInfrastructureService(ComputingInfrastructureService value);

	/**
	 * Returns the value of the '<em><b>Descriptor</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptor</em>' reference isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Descriptor</em>' reference.
	 * @see #setDescriptor(DeployablePlatformServiceDescriptor)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getDeployablePlatformService_Descriptor()
	 * @model required="true"
	 * @generated
	 */
	DeployablePlatformServiceDescriptor getDescriptor();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService#getDescriptor <em>Descriptor</em>}' reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Descriptor</em>' reference.
	 * @see #getDescriptor()
	 * @generated
	 */
	void setDescriptor(DeployablePlatformServiceDescriptor value);

} // DeployablePlatformService
