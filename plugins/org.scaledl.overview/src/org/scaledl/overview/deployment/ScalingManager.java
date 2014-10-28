/**
 */
package org.scaledl.overview.deployment;

import org.eclipse.emf.common.util.EList;
import org.scaledl.overview.core.Entity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scaling Manager</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.deployment.ScalingManager#getScalingPolicy <em>Scaling Policy</em>}</li>
 *   <li>{@link org.scaledl.overview.deployment.ScalingManager#getMaximumSize <em>Maximum Size</em>}</li>
 *   <li>{@link org.scaledl.overview.deployment.ScalingManager#getMinimumSize <em>Minimum Size</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.deployment.DeploymentPackage#getScalingManager()
 * @model
 * @generated
 */
public interface ScalingManager extends Entity {
	/**
	 * Returns the value of the '<em><b>Scaling Policy</b></em>' containment reference list.
	 * The list contents are of type {@link org.scaledl.overview.deployment.ScalingPolicy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scaling Policy</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scaling Policy</em>' containment reference list.
	 * @see org.scaledl.overview.deployment.DeploymentPackage#getScalingManager_ScalingPolicy()
	 * @model containment="true"
	 * @generated
	 */
	EList<ScalingPolicy> getScalingPolicy();

	/**
	 * Returns the value of the '<em><b>Maximum Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Size</em>' attribute.
	 * @see #setMaximumSize(int)
	 * @see org.scaledl.overview.deployment.DeploymentPackage#getScalingManager_MaximumSize()
	 * @model
	 * @generated
	 */
	int getMaximumSize();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.deployment.ScalingManager#getMaximumSize <em>Maximum Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Size</em>' attribute.
	 * @see #getMaximumSize()
	 * @generated
	 */
	void setMaximumSize(int value);

	/**
	 * Returns the value of the '<em><b>Minimum Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Minimum Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Minimum Size</em>' attribute.
	 * @see #setMinimumSize(int)
	 * @see org.scaledl.overview.deployment.DeploymentPackage#getScalingManager_MinimumSize()
	 * @model
	 * @generated
	 */
	int getMinimumSize();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.deployment.ScalingManager#getMinimumSize <em>Minimum Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Minimum Size</em>' attribute.
	 * @see #getMinimumSize()
	 * @generated
	 */
	void setMinimumSize(int value);

} // ScalingManager
