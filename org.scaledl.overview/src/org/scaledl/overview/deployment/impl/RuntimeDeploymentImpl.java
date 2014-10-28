/**
 */
package org.scaledl.overview.deployment.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.scaledl.overview.deployment.ComputingEnvironment;
import org.scaledl.overview.deployment.DeploymentPackage;
import org.scaledl.overview.deployment.RuntimeDeployment;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Runtime Deployment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.deployment.impl.RuntimeDeploymentImpl#getComputingEnvironment <em>Computing Environment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RuntimeDeploymentImpl extends ServiceDeploymentImpl implements RuntimeDeployment {
	/**
	 * The cached value of the '{@link #getComputingEnvironment() <em>Computing Environment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComputingEnvironment()
	 * @generated
	 * @ordered
	 */
	protected ComputingEnvironment computingEnvironment;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RuntimeDeploymentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DeploymentPackage.Literals.RUNTIME_DEPLOYMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComputingEnvironment getComputingEnvironment() {
		return computingEnvironment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComputingEnvironment(ComputingEnvironment newComputingEnvironment, NotificationChain msgs) {
		ComputingEnvironment oldComputingEnvironment = computingEnvironment;
		computingEnvironment = newComputingEnvironment;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DeploymentPackage.RUNTIME_DEPLOYMENT__COMPUTING_ENVIRONMENT, oldComputingEnvironment, newComputingEnvironment);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComputingEnvironment(ComputingEnvironment newComputingEnvironment) {
		if (newComputingEnvironment != computingEnvironment) {
			NotificationChain msgs = null;
			if (computingEnvironment != null)
				msgs = ((InternalEObject)computingEnvironment).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DeploymentPackage.RUNTIME_DEPLOYMENT__COMPUTING_ENVIRONMENT, null, msgs);
			if (newComputingEnvironment != null)
				msgs = ((InternalEObject)newComputingEnvironment).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DeploymentPackage.RUNTIME_DEPLOYMENT__COMPUTING_ENVIRONMENT, null, msgs);
			msgs = basicSetComputingEnvironment(newComputingEnvironment, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.RUNTIME_DEPLOYMENT__COMPUTING_ENVIRONMENT, newComputingEnvironment, newComputingEnvironment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DeploymentPackage.RUNTIME_DEPLOYMENT__COMPUTING_ENVIRONMENT:
				return basicSetComputingEnvironment(null, msgs);
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
			case DeploymentPackage.RUNTIME_DEPLOYMENT__COMPUTING_ENVIRONMENT:
				return getComputingEnvironment();
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
			case DeploymentPackage.RUNTIME_DEPLOYMENT__COMPUTING_ENVIRONMENT:
				setComputingEnvironment((ComputingEnvironment)newValue);
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
			case DeploymentPackage.RUNTIME_DEPLOYMENT__COMPUTING_ENVIRONMENT:
				setComputingEnvironment((ComputingEnvironment)null);
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
			case DeploymentPackage.RUNTIME_DEPLOYMENT__COMPUTING_ENVIRONMENT:
				return computingEnvironment != null;
		}
		return super.eIsSet(featureID);
	}

} //RuntimeDeploymentImpl
