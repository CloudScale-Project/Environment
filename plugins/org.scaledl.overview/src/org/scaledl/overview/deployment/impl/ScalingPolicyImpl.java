/**
 */
package org.scaledl.overview.deployment.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.scaledl.overview.core.impl.EntityImpl;

import org.scaledl.overview.deployment.DeploymentPackage;
import org.scaledl.overview.deployment.ScalingCapacityType;
import org.scaledl.overview.deployment.ScalingPolicy;
import org.scaledl.overview.deployment.ScalingPolicyType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scaling Policy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.deployment.impl.ScalingPolicyImpl#getScalingPolicyType <em>Scaling Policy Type</em>}</li>
 *   <li>{@link org.scaledl.overview.deployment.impl.ScalingPolicyImpl#getScalingCapacityType <em>Scaling Capacity Type</em>}</li>
 *   <li>{@link org.scaledl.overview.deployment.impl.ScalingPolicyImpl#getAdjustment <em>Adjustment</em>}</li>
 *   <li>{@link org.scaledl.overview.deployment.impl.ScalingPolicyImpl#getCooldown <em>Cooldown</em>}</li>
 *   <li>{@link org.scaledl.overview.deployment.impl.ScalingPolicyImpl#getTrigger <em>Trigger</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScalingPolicyImpl extends EntityImpl implements ScalingPolicy {
	/**
	 * The default value of the '{@link #getScalingPolicyType() <em>Scaling Policy Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScalingPolicyType()
	 * @generated
	 * @ordered
	 */
	protected static final ScalingPolicyType SCALING_POLICY_TYPE_EDEFAULT = ScalingPolicyType.SCALING_UP_POLICY;

	/**
	 * The cached value of the '{@link #getScalingPolicyType() <em>Scaling Policy Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScalingPolicyType()
	 * @generated
	 * @ordered
	 */
	protected ScalingPolicyType scalingPolicyType = SCALING_POLICY_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getScalingCapacityType() <em>Scaling Capacity Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScalingCapacityType()
	 * @generated
	 * @ordered
	 */
	protected static final ScalingCapacityType SCALING_CAPACITY_TYPE_EDEFAULT = ScalingCapacityType.CHANGE_IN_CAPACITY;

	/**
	 * The cached value of the '{@link #getScalingCapacityType() <em>Scaling Capacity Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScalingCapacityType()
	 * @generated
	 * @ordered
	 */
	protected ScalingCapacityType scalingCapacityType = SCALING_CAPACITY_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getAdjustment() <em>Adjustment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdjustment()
	 * @generated
	 * @ordered
	 */
	protected static final float ADJUSTMENT_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getAdjustment() <em>Adjustment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdjustment()
	 * @generated
	 * @ordered
	 */
	protected float adjustment = ADJUSTMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getCooldown() <em>Cooldown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCooldown()
	 * @generated
	 * @ordered
	 */
	protected static final int COOLDOWN_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCooldown() <em>Cooldown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCooldown()
	 * @generated
	 * @ordered
	 */
	protected int cooldown = COOLDOWN_EDEFAULT;

	/**
	 * The default value of the '{@link #getTrigger() <em>Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrigger()
	 * @generated
	 * @ordered
	 */
	protected static final String TRIGGER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTrigger() <em>Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrigger()
	 * @generated
	 * @ordered
	 */
	protected String trigger = TRIGGER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScalingPolicyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DeploymentPackage.Literals.SCALING_POLICY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScalingPolicyType getScalingPolicyType() {
		return scalingPolicyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScalingPolicyType(ScalingPolicyType newScalingPolicyType) {
		ScalingPolicyType oldScalingPolicyType = scalingPolicyType;
		scalingPolicyType = newScalingPolicyType == null ? SCALING_POLICY_TYPE_EDEFAULT : newScalingPolicyType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.SCALING_POLICY__SCALING_POLICY_TYPE, oldScalingPolicyType, scalingPolicyType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScalingCapacityType getScalingCapacityType() {
		return scalingCapacityType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScalingCapacityType(ScalingCapacityType newScalingCapacityType) {
		ScalingCapacityType oldScalingCapacityType = scalingCapacityType;
		scalingCapacityType = newScalingCapacityType == null ? SCALING_CAPACITY_TYPE_EDEFAULT : newScalingCapacityType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.SCALING_POLICY__SCALING_CAPACITY_TYPE, oldScalingCapacityType, scalingCapacityType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getAdjustment() {
		return adjustment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdjustment(float newAdjustment) {
		float oldAdjustment = adjustment;
		adjustment = newAdjustment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.SCALING_POLICY__ADJUSTMENT, oldAdjustment, adjustment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCooldown() {
		return cooldown;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCooldown(int newCooldown) {
		int oldCooldown = cooldown;
		cooldown = newCooldown;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.SCALING_POLICY__COOLDOWN, oldCooldown, cooldown));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTrigger() {
		return trigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrigger(String newTrigger) {
		String oldTrigger = trigger;
		trigger = newTrigger;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.SCALING_POLICY__TRIGGER, oldTrigger, trigger));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DeploymentPackage.SCALING_POLICY__SCALING_POLICY_TYPE:
				return getScalingPolicyType();
			case DeploymentPackage.SCALING_POLICY__SCALING_CAPACITY_TYPE:
				return getScalingCapacityType();
			case DeploymentPackage.SCALING_POLICY__ADJUSTMENT:
				return getAdjustment();
			case DeploymentPackage.SCALING_POLICY__COOLDOWN:
				return getCooldown();
			case DeploymentPackage.SCALING_POLICY__TRIGGER:
				return getTrigger();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DeploymentPackage.SCALING_POLICY__SCALING_POLICY_TYPE:
				setScalingPolicyType((ScalingPolicyType)newValue);
				return;
			case DeploymentPackage.SCALING_POLICY__SCALING_CAPACITY_TYPE:
				setScalingCapacityType((ScalingCapacityType)newValue);
				return;
			case DeploymentPackage.SCALING_POLICY__ADJUSTMENT:
				setAdjustment((Float)newValue);
				return;
			case DeploymentPackage.SCALING_POLICY__COOLDOWN:
				setCooldown((Integer)newValue);
				return;
			case DeploymentPackage.SCALING_POLICY__TRIGGER:
				setTrigger((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case DeploymentPackage.SCALING_POLICY__SCALING_POLICY_TYPE:
				setScalingPolicyType(SCALING_POLICY_TYPE_EDEFAULT);
				return;
			case DeploymentPackage.SCALING_POLICY__SCALING_CAPACITY_TYPE:
				setScalingCapacityType(SCALING_CAPACITY_TYPE_EDEFAULT);
				return;
			case DeploymentPackage.SCALING_POLICY__ADJUSTMENT:
				setAdjustment(ADJUSTMENT_EDEFAULT);
				return;
			case DeploymentPackage.SCALING_POLICY__COOLDOWN:
				setCooldown(COOLDOWN_EDEFAULT);
				return;
			case DeploymentPackage.SCALING_POLICY__TRIGGER:
				setTrigger(TRIGGER_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DeploymentPackage.SCALING_POLICY__SCALING_POLICY_TYPE:
				return scalingPolicyType != SCALING_POLICY_TYPE_EDEFAULT;
			case DeploymentPackage.SCALING_POLICY__SCALING_CAPACITY_TYPE:
				return scalingCapacityType != SCALING_CAPACITY_TYPE_EDEFAULT;
			case DeploymentPackage.SCALING_POLICY__ADJUSTMENT:
				return adjustment != ADJUSTMENT_EDEFAULT;
			case DeploymentPackage.SCALING_POLICY__COOLDOWN:
				return cooldown != COOLDOWN_EDEFAULT;
			case DeploymentPackage.SCALING_POLICY__TRIGGER:
				return TRIGGER_EDEFAULT == null ? trigger != null : !TRIGGER_EDEFAULT.equals(trigger);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (scalingPolicyType: ");
		result.append(scalingPolicyType);
		result.append(", scalingCapacityType: ");
		result.append(scalingCapacityType);
		result.append(", adjustment: ");
		result.append(adjustment);
		result.append(", cooldown: ");
		result.append(cooldown);
		result.append(", trigger: ");
		result.append(trigger);
		result.append(')');
		return result.toString();
	}

} //ScalingPolicyImpl
