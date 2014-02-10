/**
 */
package eu.cloudscaleproject.env.csm.architecture.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.ExternalSoftwareService;
import eu.cloudscaleproject.env.csm.architecture.ServiceProxy;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>External Software Service</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.ExternalSoftwareServiceImpl#getServiceProxy <em>Service Proxy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExternalSoftwareServiceImpl extends SoftwareServiceImpl implements
		ExternalSoftwareService {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ExternalSoftwareServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
		if (eContainerFeatureID() != ArchitecturePackage.EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY) return null;
		return (ServiceProxy)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetServiceProxy(ServiceProxy newServiceProxy, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newServiceProxy, ArchitecturePackage.EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceProxy(ServiceProxy newServiceProxy) {
		if (newServiceProxy != eInternalContainer() || (eContainerFeatureID() != ArchitecturePackage.EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY && newServiceProxy != null)) {
			if (EcoreUtil.isAncestor(this, newServiceProxy))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newServiceProxy != null)
				msgs = ((InternalEObject)newServiceProxy).eInverseAdd(this, ArchitecturePackage.SERVICE_PROXY__SOFTWARE_SERVICE, ServiceProxy.class, msgs);
			msgs = basicSetServiceProxy(newServiceProxy, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY, newServiceProxy, newServiceProxy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ArchitecturePackage.EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetServiceProxy((ServiceProxy)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ArchitecturePackage.EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY:
				return basicSetServiceProxy(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ArchitecturePackage.EXTERNAL_SOFTWARE_SERVICE__SERVICE_PROXY:
				return eInternalContainer().eInverseRemove(this, ArchitecturePackage.SERVICE_PROXY__SOFTWARE_SERVICE, ServiceProxy.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
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
				return getServiceProxy();
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
				return getServiceProxy() != null;
		}
		return super.eIsSet(featureID);
	}

} // ExternalSoftwareServiceImpl
