/**
 */
package org.scaledl.overview.architecture.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.scaledl.overview.architecture.ArchitecturePackage;
import org.scaledl.overview.architecture.ExternalSoftwareService;
import org.scaledl.overview.architecture.ServiceProxy;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>External Software Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.architecture.impl.ExternalSoftwareServiceImpl#getServiceProxy <em>Service Proxy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExternalSoftwareServiceImpl extends ProvidedSoftwareServiceImpl implements ExternalSoftwareService {
	/**
	 * The cached value of the '{@link #getServiceProxy() <em>Service Proxy</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceProxy()
	 * @generated
	 * @ordered
	 */
	protected ServiceProxy serviceProxy;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExternalSoftwareServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.EXTERNAL_SOFTWARE_SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceProxy getServiceProxy() {
		if (serviceProxy != null && serviceProxy.eIsProxy()) {
			InternalEObject oldServiceProxy = (InternalEObject)serviceProxy;
			serviceProxy = (ServiceProxy)eResolveProxy(oldServiceProxy);
			if (serviceProxy != oldServiceProxy) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY, oldServiceProxy, serviceProxy));
			}
		}
		return serviceProxy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceProxy basicGetServiceProxy() {
		return serviceProxy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceProxy(ServiceProxy newServiceProxy) {
		ServiceProxy oldServiceProxy = serviceProxy;
		serviceProxy = newServiceProxy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY, oldServiceProxy, serviceProxy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ArchitecturePackage.EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY:
				if (resolve) return getServiceProxy();
				return basicGetServiceProxy();
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
			case ArchitecturePackage.EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY:
				setServiceProxy((ServiceProxy)newValue);
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
			case ArchitecturePackage.EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY:
				setServiceProxy((ServiceProxy)null);
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
			case ArchitecturePackage.EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY:
				return serviceProxy != null;
		}
		return super.eIsSet(featureID);
	}

} //ExternalSoftwareServiceImpl
