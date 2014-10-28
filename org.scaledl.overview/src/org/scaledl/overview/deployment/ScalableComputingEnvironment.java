/**
 */
package org.scaledl.overview.deployment;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scalable Computing Environment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.deployment.ScalableComputingEnvironment#getScalingManager <em>Scaling Manager</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.deployment.DeploymentPackage#getScalableComputingEnvironment()
 * @model
 * @generated
 */
public interface ScalableComputingEnvironment extends ClusteredComputingEnvironment {
	/**
	 * Returns the value of the '<em><b>Scaling Manager</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scaling Manager</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scaling Manager</em>' containment reference.
	 * @see #setScalingManager(ScalingManager)
	 * @see org.scaledl.overview.deployment.DeploymentPackage#getScalableComputingEnvironment_ScalingManager()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ScalingManager getScalingManager();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.deployment.ScalableComputingEnvironment#getScalingManager <em>Scaling Manager</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scaling Manager</em>' containment reference.
	 * @see #getScalingManager()
	 * @generated
	 */
	void setScalingManager(ScalingManager value);

} // ScalableComputingEnvironment
