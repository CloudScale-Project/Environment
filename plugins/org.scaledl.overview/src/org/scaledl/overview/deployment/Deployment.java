/**
 */
package org.scaledl.overview.deployment;

import org.eclipse.emf.common.util.EList;
import org.scaledl.overview.core.Entity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deployment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.deployment.Deployment#getServiceDeployments <em>Service Deployments</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.deployment.DeploymentPackage#getDeployment()
 * @model
 * @generated
 */
public interface Deployment extends Entity {
	/**
	 * Returns the value of the '<em><b>Service Deployments</b></em>' containment reference list.
	 * The list contents are of type {@link org.scaledl.overview.deployment.ServiceDeployment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Deployments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Deployments</em>' containment reference list.
	 * @see org.scaledl.overview.deployment.DeploymentPackage#getDeployment_ServiceDeployments()
	 * @model containment="true"
	 * @generated
	 */
	EList<ServiceDeployment> getServiceDeployments();

} // Deployment
