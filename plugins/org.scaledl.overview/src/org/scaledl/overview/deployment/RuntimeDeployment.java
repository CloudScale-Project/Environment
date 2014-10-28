/**
 */
package org.scaledl.overview.deployment;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Runtime Deployment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.deployment.RuntimeDeployment#getComputingEnvironment <em>Computing Environment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.deployment.DeploymentPackage#getRuntimeDeployment()
 * @model
 * @generated
 */
public interface RuntimeDeployment extends ServiceDeployment {
	/**
	 * Returns the value of the '<em><b>Computing Environment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Computing Environment</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Computing Environment</em>' containment reference.
	 * @see #setComputingEnvironment(ComputingEnvironment)
	 * @see org.scaledl.overview.deployment.DeploymentPackage#getRuntimeDeployment_ComputingEnvironment()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ComputingEnvironment getComputingEnvironment();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.deployment.RuntimeDeployment#getComputingEnvironment <em>Computing Environment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Computing Environment</em>' containment reference.
	 * @see #getComputingEnvironment()
	 * @generated
	 */
	void setComputingEnvironment(ComputingEnvironment value);

} // RuntimeDeployment
