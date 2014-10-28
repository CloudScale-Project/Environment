/**
 */
package org.scaledl.overview.deployment;

import org.scaledl.overview.core.Entity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Load Balancer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.deployment.LoadBalancer#getSchedulingPolicy <em>Scheduling Policy</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.deployment.DeploymentPackage#getLoadBalancer()
 * @model
 * @generated
 */
public interface LoadBalancer extends Entity {
	/**
	 * Returns the value of the '<em><b>Scheduling Policy</b></em>' attribute.
	 * The literals are from the enumeration {@link org.scaledl.overview.deployment.SchedulingPolicy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scheduling Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scheduling Policy</em>' attribute.
	 * @see org.scaledl.overview.deployment.SchedulingPolicy
	 * @see #setSchedulingPolicy(SchedulingPolicy)
	 * @see org.scaledl.overview.deployment.DeploymentPackage#getLoadBalancer_SchedulingPolicy()
	 * @model required="true"
	 * @generated
	 */
	SchedulingPolicy getSchedulingPolicy();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.deployment.LoadBalancer#getSchedulingPolicy <em>Scheduling Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scheduling Policy</em>' attribute.
	 * @see org.scaledl.overview.deployment.SchedulingPolicy
	 * @see #getSchedulingPolicy()
	 * @generated
	 */
	void setSchedulingPolicy(SchedulingPolicy value);

} // LoadBalancer
