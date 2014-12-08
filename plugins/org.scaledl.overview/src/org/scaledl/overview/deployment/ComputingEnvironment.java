/**
 */
package org.scaledl.overview.deployment;

import org.scaledl.overview.core.Entity;

import org.scaledl.overview.specification.ComputingResourceDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Computing Environment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.deployment.ComputingEnvironment#getInstanceDescriptor <em>Instance Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.deployment.DeploymentPackage#getComputingEnvironment()
 * @model
 * @generated
 */
public interface ComputingEnvironment extends Entity {
	/**
	 * Returns the value of the '<em><b>Instance Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instance Descriptor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance Descriptor</em>' reference.
	 * @see #setInstanceDescriptor(ComputingResourceDescriptor)
	 * @see org.scaledl.overview.deployment.DeploymentPackage#getComputingEnvironment_InstanceDescriptor()
	 * @model
	 * @generated
	 */
	ComputingResourceDescriptor getInstanceDescriptor();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.deployment.ComputingEnvironment#getInstanceDescriptor <em>Instance Descriptor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instance Descriptor</em>' reference.
	 * @see #getInstanceDescriptor()
	 * @generated
	 */
	void setInstanceDescriptor(ComputingResourceDescriptor value);

} // ComputingEnvironment
