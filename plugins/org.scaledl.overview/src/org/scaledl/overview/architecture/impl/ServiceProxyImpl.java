/**
 */
package org.scaledl.overview.architecture.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.scaledl.overview.architecture.ArchitecturePackage;
import org.scaledl.overview.architecture.ServiceProxy;
import org.scaledl.overview.architecture.SoftwareService;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Proxy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.architecture.impl.ServiceProxyImpl#getSoftwareService <em>Software Service</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceProxyImpl extends ProxyImpl implements ServiceProxy {
	/**
	 * The cached value of the '{@link #getSoftwareService() <em>Software Service</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoftwareService()
	 * @generated
	 * @ordered
	 */
	protected SoftwareService softwareService;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceProxyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.SERVICE_PROXY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareService getSoftwareService() {
		return softwareService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSoftwareService(SoftwareService newSoftwareService, NotificationChain msgs) {
		SoftwareService oldSoftwareService = softwareService;
		softwareService = newSoftwareService;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ArchitecturePackage.SERVICE_PROXY__SOFTWARE_SERVICE, oldSoftwareService, newSoftwareService);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSoftwareService(SoftwareService newSoftwareService) {
		if (newSoftwareService != softwareService) {
			NotificationChain msgs = null;
			if (softwareService != null)
				msgs = ((InternalEObject)softwareService).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ArchitecturePackage.SERVICE_PROXY__SOFTWARE_SERVICE, null, msgs);
			if (newSoftwareService != null)
				msgs = ((InternalEObject)newSoftwareService).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ArchitecturePackage.SERVICE_PROXY__SOFTWARE_SERVICE, null, msgs);
			msgs = basicSetSoftwareService(newSoftwareService, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.SERVICE_PROXY__SOFTWARE_SERVICE, newSoftwareService, newSoftwareService));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ArchitecturePackage.SERVICE_PROXY__SOFTWARE_SERVICE:
				return basicSetSoftwareService(null, msgs);
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
			case ArchitecturePackage.SERVICE_PROXY__SOFTWARE_SERVICE:
				return getSoftwareService();
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
			case ArchitecturePackage.SERVICE_PROXY__SOFTWARE_SERVICE:
				setSoftwareService((SoftwareService)newValue);
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
			case ArchitecturePackage.SERVICE_PROXY__SOFTWARE_SERVICE:
				setSoftwareService((SoftwareService)null);
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
			case ArchitecturePackage.SERVICE_PROXY__SOFTWARE_SERVICE:
				return softwareService != null;
		}
		return super.eIsSet(featureID);
	}

} //ServiceProxyImpl
