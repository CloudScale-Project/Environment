/**
 */
package eu.cloudscaleproject.env.csm.architecture.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.HybridConnection;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Hybrid Connection</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.HybridConnectionImpl#getSourcePlatformService <em>Source Platform Service</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.HybridConnectionImpl#getTargetPlatformService <em>Target Platform Service</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.HybridConnectionImpl#getDescriptor <em>Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HybridConnectionImpl extends ConnectionImpl implements
		HybridConnection {
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
	 * The cached value of the '{@link #getTargetPlatformService() <em>Target Platform Service</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetPlatformService()
	 * @generated
	 * @ordered
	 */
	protected PlatformService targetPlatformService;

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
	protected HybridConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.HYBRID_CONNECTION;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.HYBRID_CONNECTION__SOURCE_PLATFORM_SERVICE, oldSourcePlatformService, sourcePlatformService));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.HYBRID_CONNECTION__SOURCE_PLATFORM_SERVICE, oldSourcePlatformService, sourcePlatformService));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformService getTargetPlatformService() {
		if (targetPlatformService != null && targetPlatformService.eIsProxy()) {
			InternalEObject oldTargetPlatformService = (InternalEObject)targetPlatformService;
			targetPlatformService = (PlatformService)eResolveProxy(oldTargetPlatformService);
			if (targetPlatformService != oldTargetPlatformService) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.HYBRID_CONNECTION__TARGET_PLATFORM_SERVICE, oldTargetPlatformService, targetPlatformService));
			}
		}
		return targetPlatformService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformService basicGetTargetPlatformService() {
		return targetPlatformService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetPlatformService(
			PlatformService newTargetPlatformService) {
		PlatformService oldTargetPlatformService = targetPlatformService;
		targetPlatformService = newTargetPlatformService;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.HYBRID_CONNECTION__TARGET_PLATFORM_SERVICE, oldTargetPlatformService, targetPlatformService));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.HYBRID_CONNECTION__DESCRIPTOR, oldDescriptor, descriptor));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.HYBRID_CONNECTION__DESCRIPTOR, oldDescriptor, descriptor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ArchitecturePackage.HYBRID_CONNECTION__SOURCE_PLATFORM_SERVICE:
				if (resolve) return getSourcePlatformService();
				return basicGetSourcePlatformService();
			case ArchitecturePackage.HYBRID_CONNECTION__TARGET_PLATFORM_SERVICE:
				if (resolve) return getTargetPlatformService();
				return basicGetTargetPlatformService();
			case ArchitecturePackage.HYBRID_CONNECTION__DESCRIPTOR:
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
			case ArchitecturePackage.HYBRID_CONNECTION__SOURCE_PLATFORM_SERVICE:
				setSourcePlatformService((PlatformService)newValue);
				return;
			case ArchitecturePackage.HYBRID_CONNECTION__TARGET_PLATFORM_SERVICE:
				setTargetPlatformService((PlatformService)newValue);
				return;
			case ArchitecturePackage.HYBRID_CONNECTION__DESCRIPTOR:
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
			case ArchitecturePackage.HYBRID_CONNECTION__SOURCE_PLATFORM_SERVICE:
				setSourcePlatformService((PlatformService)null);
				return;
			case ArchitecturePackage.HYBRID_CONNECTION__TARGET_PLATFORM_SERVICE:
				setTargetPlatformService((PlatformService)null);
				return;
			case ArchitecturePackage.HYBRID_CONNECTION__DESCRIPTOR:
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
			case ArchitecturePackage.HYBRID_CONNECTION__SOURCE_PLATFORM_SERVICE:
				return sourcePlatformService != null;
			case ArchitecturePackage.HYBRID_CONNECTION__TARGET_PLATFORM_SERVICE:
				return targetPlatformService != null;
			case ArchitecturePackage.HYBRID_CONNECTION__DESCRIPTOR:
				return descriptor != null;
		}
		return super.eIsSet(featureID);
	}

} // HybridConnectionImpl
