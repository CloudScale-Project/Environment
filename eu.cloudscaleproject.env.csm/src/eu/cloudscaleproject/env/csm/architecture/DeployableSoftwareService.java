/**
 */
package eu.cloudscaleproject.env.csm.architecture;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Deployable Software Service</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService#getRuntimePlatformService <em>Runtime Platform Service</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getDeployableSoftwareService()
 * @model
 * @generated
 */
public interface DeployableSoftwareService extends SoftwareService {
	/**
	 * Returns the value of the '<em><b>Runtime Platform Service</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer#getSoftwareServices <em>Software Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Runtime Platform Service</em>' reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Runtime Platform Service</em>' reference.
	 * @see #setRuntimePlatformService(SoftwareServiceContainer)
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getDeployableSoftwareService_RuntimePlatformService()
	 * @see eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer#getSoftwareServices
	 * @model opposite="softwareServices"
	 * @generated
	 */
	SoftwareServiceContainer getRuntimePlatformService();

	/**
	 * Sets the value of the '{@link eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService#getRuntimePlatformService <em>Runtime Platform Service</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Runtime Platform Service</em>' reference.
	 * @see #getRuntimePlatformService()
	 * @generated
	 */
	void setRuntimePlatformService(SoftwareServiceContainer value);

} // DeployableSoftwareService
