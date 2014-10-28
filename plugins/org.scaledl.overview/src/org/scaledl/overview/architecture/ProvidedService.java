/**
 */
package org.scaledl.overview.architecture;

import org.scaledl.overview.deployment.ServiceDeployment;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provided Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.architecture.ProvidedService#getDeployment <em>Deployment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.architecture.ArchitecturePackage#getProvidedService()
 * @model abstract="true"
 * @generated
 */
public interface ProvidedService extends Service {
	/**
	 * Returns the value of the '<em><b>Deployment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployment</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deployment</em>' reference.
	 * @see #setDeployment(ServiceDeployment)
	 * @see org.scaledl.overview.architecture.ArchitecturePackage#getProvidedService_Deployment()
	 * @model
	 * @generated
	 */
	ServiceDeployment getDeployment();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.architecture.ProvidedService#getDeployment <em>Deployment</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deployment</em>' reference.
	 * @see #getDeployment()
	 * @generated
	 */
	void setDeployment(ServiceDeployment value);

} // ProvidedService
