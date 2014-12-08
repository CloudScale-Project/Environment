/**
 */
package org.scaledl.overview.deployment.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.scaledl.overview.deployment.DeploymentPackage;
import org.scaledl.overview.deployment.ScalableComputingEnvironment;
import org.scaledl.overview.deployment.ScalingManager;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scalable Computing Environment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.deployment.impl.ScalableComputingEnvironmentImpl#getScalingManager <em>Scaling Manager</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScalableComputingEnvironmentImpl extends ClusteredComputingEnvironmentImpl implements ScalableComputingEnvironment {
	/**
	 * The cached value of the '{@link #getScalingManager() <em>Scaling Manager</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScalingManager()
	 * @generated
	 * @ordered
	 */
	protected ScalingManager scalingManager;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScalableComputingEnvironmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DeploymentPackage.Literals.SCALABLE_COMPUTING_ENVIRONMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScalingManager getScalingManager() {
		return scalingManager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScalingManager(ScalingManager newScalingManager, NotificationChain msgs) {
		ScalingManager oldScalingManager = scalingManager;
		scalingManager = newScalingManager;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DeploymentPackage.SCALABLE_COMPUTING_ENVIRONMENT__SCALING_MANAGER, oldScalingManager, newScalingManager);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScalingManager(ScalingManager newScalingManager) {
		if (newScalingManager != scalingManager) {
			NotificationChain msgs = null;
			if (scalingManager != null)
				msgs = ((InternalEObject)scalingManager).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DeploymentPackage.SCALABLE_COMPUTING_ENVIRONMENT__SCALING_MANAGER, null, msgs);
			if (newScalingManager != null)
				msgs = ((InternalEObject)newScalingManager).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DeploymentPackage.SCALABLE_COMPUTING_ENVIRONMENT__SCALING_MANAGER, null, msgs);
			msgs = basicSetScalingManager(newScalingManager, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.SCALABLE_COMPUTING_ENVIRONMENT__SCALING_MANAGER, newScalingManager, newScalingManager));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DeploymentPackage.SCALABLE_COMPUTING_ENVIRONMENT__SCALING_MANAGER:
				return basicSetScalingManager(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DeploymentPackage.SCALABLE_COMPUTING_ENVIRONMENT__SCALING_MANAGER:
				return getScalingManager();
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
			case DeploymentPackage.SCALABLE_COMPUTING_ENVIRONMENT__SCALING_MANAGER:
				setScalingManager((ScalingManager)newValue);
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
			case DeploymentPackage.SCALABLE_COMPUTING_ENVIRONMENT__SCALING_MANAGER:
				setScalingManager((ScalingManager)null);
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
			case DeploymentPackage.SCALABLE_COMPUTING_ENVIRONMENT__SCALING_MANAGER:
				return scalingManager != null;
		}
		return super.eIsSet(featureID);
	}

} //ScalableComputingEnvironmentImpl
