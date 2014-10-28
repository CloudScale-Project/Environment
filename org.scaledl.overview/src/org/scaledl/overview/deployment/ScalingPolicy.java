/**
 */
package org.scaledl.overview.deployment;

import org.scaledl.overview.core.Entity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scaling Policy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.deployment.ScalingPolicy#getScalingPolicyType <em>Scaling Policy Type</em>}</li>
 *   <li>{@link org.scaledl.overview.deployment.ScalingPolicy#getScalingCapacityType <em>Scaling Capacity Type</em>}</li>
 *   <li>{@link org.scaledl.overview.deployment.ScalingPolicy#getAdjustment <em>Adjustment</em>}</li>
 *   <li>{@link org.scaledl.overview.deployment.ScalingPolicy#getCooldown <em>Cooldown</em>}</li>
 *   <li>{@link org.scaledl.overview.deployment.ScalingPolicy#getTrigger <em>Trigger</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.deployment.DeploymentPackage#getScalingPolicy()
 * @model
 * @generated
 */
public interface ScalingPolicy extends Entity {
	/**
	 * Returns the value of the '<em><b>Scaling Policy Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.scaledl.overview.deployment.ScalingPolicyType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scaling Policy Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scaling Policy Type</em>' attribute.
	 * @see org.scaledl.overview.deployment.ScalingPolicyType
	 * @see #setScalingPolicyType(ScalingPolicyType)
	 * @see org.scaledl.overview.deployment.DeploymentPackage#getScalingPolicy_ScalingPolicyType()
	 * @model required="true"
	 * @generated
	 */
	ScalingPolicyType getScalingPolicyType();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.deployment.ScalingPolicy#getScalingPolicyType <em>Scaling Policy Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scaling Policy Type</em>' attribute.
	 * @see org.scaledl.overview.deployment.ScalingPolicyType
	 * @see #getScalingPolicyType()
	 * @generated
	 */
	void setScalingPolicyType(ScalingPolicyType value);

	/**
	 * Returns the value of the '<em><b>Scaling Capacity Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.scaledl.overview.deployment.ScalingCapacityType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scaling Capacity Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scaling Capacity Type</em>' attribute.
	 * @see org.scaledl.overview.deployment.ScalingCapacityType
	 * @see #setScalingCapacityType(ScalingCapacityType)
	 * @see org.scaledl.overview.deployment.DeploymentPackage#getScalingPolicy_ScalingCapacityType()
	 * @model required="true"
	 * @generated
	 */
	ScalingCapacityType getScalingCapacityType();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.deployment.ScalingPolicy#getScalingCapacityType <em>Scaling Capacity Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scaling Capacity Type</em>' attribute.
	 * @see org.scaledl.overview.deployment.ScalingCapacityType
	 * @see #getScalingCapacityType()
	 * @generated
	 */
	void setScalingCapacityType(ScalingCapacityType value);

	/**
	 * Returns the value of the '<em><b>Adjustment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Adjustment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Adjustment</em>' attribute.
	 * @see #setAdjustment(float)
	 * @see org.scaledl.overview.deployment.DeploymentPackage#getScalingPolicy_Adjustment()
	 * @model
	 * @generated
	 */
	float getAdjustment();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.deployment.ScalingPolicy#getAdjustment <em>Adjustment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Adjustment</em>' attribute.
	 * @see #getAdjustment()
	 * @generated
	 */
	void setAdjustment(float value);

	/**
	 * Returns the value of the '<em><b>Cooldown</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cooldown</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cooldown</em>' attribute.
	 * @see #setCooldown(int)
	 * @see org.scaledl.overview.deployment.DeploymentPackage#getScalingPolicy_Cooldown()
	 * @model
	 * @generated
	 */
	int getCooldown();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.deployment.ScalingPolicy#getCooldown <em>Cooldown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cooldown</em>' attribute.
	 * @see #getCooldown()
	 * @generated
	 */
	void setCooldown(int value);

	/**
	 * Returns the value of the '<em><b>Trigger</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trigger</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trigger</em>' attribute.
	 * @see #setTrigger(String)
	 * @see org.scaledl.overview.deployment.DeploymentPackage#getScalingPolicy_Trigger()
	 * @model
	 * @generated
	 */
	String getTrigger();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.deployment.ScalingPolicy#getTrigger <em>Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trigger</em>' attribute.
	 * @see #getTrigger()
	 * @generated
	 */
	void setTrigger(String value);

} // ScalingPolicy
