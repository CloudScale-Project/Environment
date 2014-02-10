/**
 */
package eu.cloudscaleproject.env.csm.architecture.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.ExternalConnection;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.architecture.Proxy;
import eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>External Connection</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.ExternalConnectionImpl#getProxy <em>Proxy</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.ExternalConnectionImpl#getPlatformService <em>Platform Service</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.ExternalConnectionImpl#getIsOutbound <em>Is Outbound</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.ExternalConnectionImpl#getDescriptor <em>Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExternalConnectionImpl extends ConnectionImpl implements
		ExternalConnection {
	/**
	 * The cached value of the '{@link #getProxy() <em>Proxy</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getProxy()
	 * @generated
	 * @ordered
	 */
	protected Proxy proxy;

	/**
	 * The cached value of the '{@link #getPlatformService() <em>Platform Service</em>}' reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getPlatformService()
	 * @generated
	 * @ordered
	 */
	protected PlatformService platformService;

	/**
	 * The default value of the '{@link #getIsOutbound() <em>Is Outbound</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIsOutbound()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_OUTBOUND_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsOutbound() <em>Is Outbound</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIsOutbound()
	 * @generated
	 * @ordered
	 */
	protected Boolean isOutbound = IS_OUTBOUND_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDescriptor() <em>Descriptor</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDescriptor()
	 * @generated
	 * @ordered
	 */
	protected NetworkInfrastructureServiceDescriptor descriptor;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ExternalConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.EXTERNAL_CONNECTION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Proxy getProxy() {
		if (proxy != null && proxy.eIsProxy()) {
			InternalEObject oldProxy = (InternalEObject)proxy;
			proxy = (Proxy)eResolveProxy(oldProxy);
			if (proxy != oldProxy) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.EXTERNAL_CONNECTION__PROXY, oldProxy, proxy));
			}
		}
		return proxy;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Proxy basicGetProxy() {
		return proxy;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setProxy(Proxy newProxy) {
		Proxy oldProxy = proxy;
		proxy = newProxy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.EXTERNAL_CONNECTION__PROXY, oldProxy, proxy));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformService getPlatformService() {
		if (platformService != null && platformService.eIsProxy()) {
			InternalEObject oldPlatformService = (InternalEObject)platformService;
			platformService = (PlatformService)eResolveProxy(oldPlatformService);
			if (platformService != oldPlatformService) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.EXTERNAL_CONNECTION__PLATFORM_SERVICE, oldPlatformService, platformService));
			}
		}
		return platformService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformService basicGetPlatformService() {
		return platformService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPlatformService(PlatformService newPlatformService) {
		PlatformService oldPlatformService = platformService;
		platformService = newPlatformService;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.EXTERNAL_CONNECTION__PLATFORM_SERVICE, oldPlatformService, platformService));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsOutbound() {
		return isOutbound;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsOutbound(Boolean newIsOutbound) {
		Boolean oldIsOutbound = isOutbound;
		isOutbound = newIsOutbound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.EXTERNAL_CONNECTION__IS_OUTBOUND, oldIsOutbound, isOutbound));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkInfrastructureServiceDescriptor getDescriptor() {
		if (descriptor != null && descriptor.eIsProxy()) {
			InternalEObject oldDescriptor = (InternalEObject)descriptor;
			descriptor = (NetworkInfrastructureServiceDescriptor)eResolveProxy(oldDescriptor);
			if (descriptor != oldDescriptor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.EXTERNAL_CONNECTION__DESCRIPTOR, oldDescriptor, descriptor));
			}
		}
		return descriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkInfrastructureServiceDescriptor basicGetDescriptor() {
		return descriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescriptor(
			NetworkInfrastructureServiceDescriptor newDescriptor) {
		NetworkInfrastructureServiceDescriptor oldDescriptor = descriptor;
		descriptor = newDescriptor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.EXTERNAL_CONNECTION__DESCRIPTOR, oldDescriptor, descriptor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ArchitecturePackage.EXTERNAL_CONNECTION__PROXY:
				if (resolve) return getProxy();
				return basicGetProxy();
			case ArchitecturePackage.EXTERNAL_CONNECTION__PLATFORM_SERVICE:
				if (resolve) return getPlatformService();
				return basicGetPlatformService();
			case ArchitecturePackage.EXTERNAL_CONNECTION__IS_OUTBOUND:
				return getIsOutbound();
			case ArchitecturePackage.EXTERNAL_CONNECTION__DESCRIPTOR:
				if (resolve) return getDescriptor();
				return basicGetDescriptor();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ArchitecturePackage.EXTERNAL_CONNECTION__PROXY:
				setProxy((Proxy)newValue);
				return;
			case ArchitecturePackage.EXTERNAL_CONNECTION__PLATFORM_SERVICE:
				setPlatformService((PlatformService)newValue);
				return;
			case ArchitecturePackage.EXTERNAL_CONNECTION__IS_OUTBOUND:
				setIsOutbound((Boolean)newValue);
				return;
			case ArchitecturePackage.EXTERNAL_CONNECTION__DESCRIPTOR:
				setDescriptor((NetworkInfrastructureServiceDescriptor)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ArchitecturePackage.EXTERNAL_CONNECTION__PROXY:
				setProxy((Proxy)null);
				return;
			case ArchitecturePackage.EXTERNAL_CONNECTION__PLATFORM_SERVICE:
				setPlatformService((PlatformService)null);
				return;
			case ArchitecturePackage.EXTERNAL_CONNECTION__IS_OUTBOUND:
				setIsOutbound(IS_OUTBOUND_EDEFAULT);
				return;
			case ArchitecturePackage.EXTERNAL_CONNECTION__DESCRIPTOR:
				setDescriptor((NetworkInfrastructureServiceDescriptor)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ArchitecturePackage.EXTERNAL_CONNECTION__PROXY:
				return proxy != null;
			case ArchitecturePackage.EXTERNAL_CONNECTION__PLATFORM_SERVICE:
				return platformService != null;
			case ArchitecturePackage.EXTERNAL_CONNECTION__IS_OUTBOUND:
				return IS_OUTBOUND_EDEFAULT == null ? isOutbound != null : !IS_OUTBOUND_EDEFAULT.equals(isOutbound);
			case ArchitecturePackage.EXTERNAL_CONNECTION__DESCRIPTOR:
				return descriptor != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (isOutbound: ");
		result.append(isOutbound);
		result.append(')');
		return result.toString();
	}

} // ExternalConnectionImpl
