/**
 */
package org.scaledl.overview.deployment.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.scaledl.overview.core.impl.EntityImpl;
import org.scaledl.overview.deployment.DeploymentPackage;
import org.scaledl.overview.deployment.ScalingManager;
import org.scaledl.overview.deployment.ScalingPolicy;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scaling Manager</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.deployment.impl.ScalingManagerImpl#getScalingPolicy <em>Scaling Policy</em>}</li>
 *   <li>{@link org.scaledl.overview.deployment.impl.ScalingManagerImpl#getMaximumSize <em>Maximum Size</em>}</li>
 *   <li>{@link org.scaledl.overview.deployment.impl.ScalingManagerImpl#getMinimumSize <em>Minimum Size</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScalingManagerImpl extends EntityImpl implements ScalingManager {
	/**
	 * The cached value of the '{@link #getScalingPolicy() <em>Scaling Policy</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScalingPolicy()
	 * @generated
	 * @ordered
	 */
	protected EList<ScalingPolicy> scalingPolicy;

	/**
	 * The default value of the '{@link #getMaximumSize() <em>Maximum Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumSize()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumSize() <em>Maximum Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumSize()
	 * @generated
	 * @ordered
	 */
	protected int maximumSize = MAXIMUM_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinimumSize() <em>Minimum Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinimumSize()
	 * @generated
	 * @ordered
	 */
	protected static final int MINIMUM_SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMinimumSize() <em>Minimum Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinimumSize()
	 * @generated
	 * @ordered
	 */
	protected int minimumSize = MINIMUM_SIZE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScalingManagerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DeploymentPackage.Literals.SCALING_MANAGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScalingPolicy> getScalingPolicy() {
		if (scalingPolicy == null) {
			scalingPolicy = new EObjectContainmentEList<ScalingPolicy>(ScalingPolicy.class, this, DeploymentPackage.SCALING_MANAGER__SCALING_POLICY);
		}
		return scalingPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumSize() {
		return maximumSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumSize(int newMaximumSize) {
		int oldMaximumSize = maximumSize;
		maximumSize = newMaximumSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.SCALING_MANAGER__MAXIMUM_SIZE, oldMaximumSize, maximumSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMinimumSize() {
		return minimumSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinimumSize(int newMinimumSize) {
		int oldMinimumSize = minimumSize;
		minimumSize = newMinimumSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.SCALING_MANAGER__MINIMUM_SIZE, oldMinimumSize, minimumSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DeploymentPackage.SCALING_MANAGER__SCALING_POLICY:
				return ((InternalEList<?>)getScalingPolicy()).basicRemove(otherEnd, msgs);
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
			case DeploymentPackage.SCALING_MANAGER__SCALING_POLICY:
				return getScalingPolicy();
			case DeploymentPackage.SCALING_MANAGER__MAXIMUM_SIZE:
				return getMaximumSize();
			case DeploymentPackage.SCALING_MANAGER__MINIMUM_SIZE:
				return getMinimumSize();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DeploymentPackage.SCALING_MANAGER__SCALING_POLICY:
				getScalingPolicy().clear();
				getScalingPolicy().addAll((Collection<? extends ScalingPolicy>)newValue);
				return;
			case DeploymentPackage.SCALING_MANAGER__MAXIMUM_SIZE:
				setMaximumSize((Integer)newValue);
				return;
			case DeploymentPackage.SCALING_MANAGER__MINIMUM_SIZE:
				setMinimumSize((Integer)newValue);
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
			case DeploymentPackage.SCALING_MANAGER__SCALING_POLICY:
				getScalingPolicy().clear();
				return;
			case DeploymentPackage.SCALING_MANAGER__MAXIMUM_SIZE:
				setMaximumSize(MAXIMUM_SIZE_EDEFAULT);
				return;
			case DeploymentPackage.SCALING_MANAGER__MINIMUM_SIZE:
				setMinimumSize(MINIMUM_SIZE_EDEFAULT);
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
			case DeploymentPackage.SCALING_MANAGER__SCALING_POLICY:
				return scalingPolicy != null && !scalingPolicy.isEmpty();
			case DeploymentPackage.SCALING_MANAGER__MAXIMUM_SIZE:
				return maximumSize != MAXIMUM_SIZE_EDEFAULT;
			case DeploymentPackage.SCALING_MANAGER__MINIMUM_SIZE:
				return minimumSize != MINIMUM_SIZE_EDEFAULT;
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
		result.append(" (maximumSize: ");
		result.append(maximumSize);
		result.append(", minimumSize: ");
		result.append(minimumSize);
		result.append(')');
		return result.toString();
	}

} //ScalingManagerImpl
