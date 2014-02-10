/**
 */
package eu.cloudscaleproject.env.csm.architecture;

import org.eclipse.emf.common.util.EList;

import eu.cloudscaleproject.env.csm.core.Entity;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Software Service Container</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer#getSoftwareServices <em>Software Services</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getSoftwareServiceContainer()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface SoftwareServiceContainer extends Entity {
	/**
	 * Returns the value of the '<em><b>Software Services</b></em>' reference list.
	 * The list contents are of type {@link eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService}.
	 * It is bidirectional and its opposite is '{@link eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService#getRuntimePlatformService <em>Runtime Platform Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Software Services</em>' reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Software Services</em>' reference list.
	 * @see eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage#getSoftwareServiceContainer_SoftwareServices()
	 * @see eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService#getRuntimePlatformService
	 * @model opposite="runtimePlatformService"
	 * @generated
	 */
	EList<DeployableSoftwareService> getSoftwareServices();

} // SoftwareServiceContainer
