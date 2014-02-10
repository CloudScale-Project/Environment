/**
 */
package eu.cloudscaleproject.env.csm.architecture.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.InternalConnection;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Internal Connection</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.InternalConnectionImpl#getSourcePlatformService <em>Source Platform Service</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.InternalConnectionImpl#getDestinationPlatformService <em>Destination Platform Service</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InternalConnectionImpl extends ConnectionImpl implements
		InternalConnection {
	/**
	 * The cached value of the '{@link #getSourcePlatformService() <em>Source Platform Service</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourcePlatformService()
	 * @generated
	 * @ordered
	 */
	protected PlatformService sourcePlatformService;

	/**
	 * The cached value of the '{@link #getDestinationPlatformService() <em>Destination Platform Service</em>}' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #getDestinationPlatformService()
	 * @generated
	 * @ordered
	 */
	protected PlatformService destinationPlatformService;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected InternalConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.INTERNAL_CONNECTION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformService getSourcePlatformService() {
		if (sourcePlatformService != null && sourcePlatformService.eIsProxy()) {
			InternalEObject oldSourcePlatformService = (InternalEObject)sourcePlatformService;
			sourcePlatformService = (PlatformService)eResolveProxy(oldSourcePlatformService);
			if (sourcePlatformService != oldSourcePlatformService) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.INTERNAL_CONNECTION__SOURCE_PLATFORM_SERVICE, oldSourcePlatformService, sourcePlatformService));
			}
		}
		return sourcePlatformService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformService basicGetSourcePlatformService() {
		return sourcePlatformService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourcePlatformService(
			PlatformService newSourcePlatformService) {
		PlatformService oldSourcePlatformService = sourcePlatformService;
		sourcePlatformService = newSourcePlatformService;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.INTERNAL_CONNECTION__SOURCE_PLATFORM_SERVICE, oldSourcePlatformService, sourcePlatformService));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformService getDestinationPlatformService() {
		if (destinationPlatformService != null && destinationPlatformService.eIsProxy()) {
			InternalEObject oldDestinationPlatformService = (InternalEObject)destinationPlatformService;
			destinationPlatformService = (PlatformService)eResolveProxy(oldDestinationPlatformService);
			if (destinationPlatformService != oldDestinationPlatformService) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.INTERNAL_CONNECTION__DESTINATION_PLATFORM_SERVICE, oldDestinationPlatformService, destinationPlatformService));
			}
		}
		return destinationPlatformService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformService basicGetDestinationPlatformService() {
		return destinationPlatformService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDestinationPlatformService(
			PlatformService newDestinationPlatformService) {
		PlatformService oldDestinationPlatformService = destinationPlatformService;
		destinationPlatformService = newDestinationPlatformService;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.INTERNAL_CONNECTION__DESTINATION_PLATFORM_SERVICE, oldDestinationPlatformService, destinationPlatformService));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ArchitecturePackage.INTERNAL_CONNECTION__SOURCE_PLATFORM_SERVICE:
				if (resolve) return getSourcePlatformService();
				return basicGetSourcePlatformService();
			case ArchitecturePackage.INTERNAL_CONNECTION__DESTINATION_PLATFORM_SERVICE:
				if (resolve) return getDestinationPlatformService();
				return basicGetDestinationPlatformService();
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
			case ArchitecturePackage.INTERNAL_CONNECTION__SOURCE_PLATFORM_SERVICE:
				setSourcePlatformService((PlatformService)newValue);
				return;
			case ArchitecturePackage.INTERNAL_CONNECTION__DESTINATION_PLATFORM_SERVICE:
				setDestinationPlatformService((PlatformService)newValue);
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
			case ArchitecturePackage.INTERNAL_CONNECTION__SOURCE_PLATFORM_SERVICE:
				setSourcePlatformService((PlatformService)null);
				return;
			case ArchitecturePackage.INTERNAL_CONNECTION__DESTINATION_PLATFORM_SERVICE:
				setDestinationPlatformService((PlatformService)null);
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
			case ArchitecturePackage.INTERNAL_CONNECTION__SOURCE_PLATFORM_SERVICE:
				return sourcePlatformService != null;
			case ArchitecturePackage.INTERNAL_CONNECTION__DESTINATION_PLATFORM_SERVICE:
				return destinationPlatformService != null;
		}
		return super.eIsSet(featureID);
	}

} // InternalConnectionImpl
