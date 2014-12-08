/**
 */
package org.scaledl.overview.deployment.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.scaledl.overview.deployment.ClusteredComputingEnvironment;
import org.scaledl.overview.deployment.DeploymentPackage;
import org.scaledl.overview.deployment.LoadBalancer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Clustered Computing Environment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.deployment.impl.ClusteredComputingEnvironmentImpl#getLoadBalancer <em>Load Balancer</em>}</li>
 *   <li>{@link org.scaledl.overview.deployment.impl.ClusteredComputingEnvironmentImpl#getSize <em>Size</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClusteredComputingEnvironmentImpl extends ComputingEnvironmentImpl implements ClusteredComputingEnvironment {
	/**
	 * The cached value of the '{@link #getLoadBalancer() <em>Load Balancer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadBalancer()
	 * @generated
	 * @ordered
	 */
	protected LoadBalancer loadBalancer;

	/**
	 * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected static final int SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected int size = SIZE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClusteredComputingEnvironmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DeploymentPackage.Literals.CLUSTERED_COMPUTING_ENVIRONMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoadBalancer getLoadBalancer() {
		return loadBalancer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLoadBalancer(LoadBalancer newLoadBalancer, NotificationChain msgs) {
		LoadBalancer oldLoadBalancer = loadBalancer;
		loadBalancer = newLoadBalancer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DeploymentPackage.CLUSTERED_COMPUTING_ENVIRONMENT__LOAD_BALANCER, oldLoadBalancer, newLoadBalancer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoadBalancer(LoadBalancer newLoadBalancer) {
		if (newLoadBalancer != loadBalancer) {
			NotificationChain msgs = null;
			if (loadBalancer != null)
				msgs = ((InternalEObject)loadBalancer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DeploymentPackage.CLUSTERED_COMPUTING_ENVIRONMENT__LOAD_BALANCER, null, msgs);
			if (newLoadBalancer != null)
				msgs = ((InternalEObject)newLoadBalancer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DeploymentPackage.CLUSTERED_COMPUTING_ENVIRONMENT__LOAD_BALANCER, null, msgs);
			msgs = basicSetLoadBalancer(newLoadBalancer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.CLUSTERED_COMPUTING_ENVIRONMENT__LOAD_BALANCER, newLoadBalancer, newLoadBalancer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSize() {
		return size;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSize(int newSize) {
		int oldSize = size;
		size = newSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.CLUSTERED_COMPUTING_ENVIRONMENT__SIZE, oldSize, size));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DeploymentPackage.CLUSTERED_COMPUTING_ENVIRONMENT__LOAD_BALANCER:
				return basicSetLoadBalancer(null, msgs);
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
			case DeploymentPackage.CLUSTERED_COMPUTING_ENVIRONMENT__LOAD_BALANCER:
				return getLoadBalancer();
			case DeploymentPackage.CLUSTERED_COMPUTING_ENVIRONMENT__SIZE:
				return getSize();
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
			case DeploymentPackage.CLUSTERED_COMPUTING_ENVIRONMENT__LOAD_BALANCER:
				setLoadBalancer((LoadBalancer)newValue);
				return;
			case DeploymentPackage.CLUSTERED_COMPUTING_ENVIRONMENT__SIZE:
				setSize((Integer)newValue);
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
			case DeploymentPackage.CLUSTERED_COMPUTING_ENVIRONMENT__LOAD_BALANCER:
				setLoadBalancer((LoadBalancer)null);
				return;
			case DeploymentPackage.CLUSTERED_COMPUTING_ENVIRONMENT__SIZE:
				setSize(SIZE_EDEFAULT);
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
			case DeploymentPackage.CLUSTERED_COMPUTING_ENVIRONMENT__LOAD_BALANCER:
				return loadBalancer != null;
			case DeploymentPackage.CLUSTERED_COMPUTING_ENVIRONMENT__SIZE:
				return size != SIZE_EDEFAULT;
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
		result.append(" (size: ");
		result.append(size);
		result.append(')');
		return result.toString();
	}

} //ClusteredComputingEnvironmentImpl
