/**
 */
package eu.cloudscaleproject.env.csm.architecture.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService;
import eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Deployable Software Service</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.DeployableSoftwareServiceImpl#getRuntimePlatformService <em>Runtime Platform Service</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DeployableSoftwareServiceImpl extends SoftwareServiceImpl
		implements DeployableSoftwareService {
	/**
	 * The cached value of the '{@link #getRuntimePlatformService() <em>Runtime Platform Service</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRuntimePlatformService()
	 * @generated
	 * @ordered
	 */
	protected SoftwareServiceContainer runtimePlatformService;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected DeployableSoftwareServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.DEPLOYABLE_SOFTWARE_SERVICE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareServiceContainer getRuntimePlatformService() {
		if (runtimePlatformService != null && runtimePlatformService.eIsProxy()) {
			InternalEObject oldRuntimePlatformService = (InternalEObject)runtimePlatformService;
			runtimePlatformService = (SoftwareServiceContainer)eResolveProxy(oldRuntimePlatformService);
			if (runtimePlatformService != oldRuntimePlatformService) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.DEPLOYABLE_SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE, oldRuntimePlatformService, runtimePlatformService));
			}
		}
		return runtimePlatformService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareServiceContainer basicGetRuntimePlatformService() {
		return runtimePlatformService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRuntimePlatformService(
			SoftwareServiceContainer newRuntimePlatformService,
			NotificationChain msgs) {
		SoftwareServiceContainer oldRuntimePlatformService = runtimePlatformService;
		runtimePlatformService = newRuntimePlatformService;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ArchitecturePackage.DEPLOYABLE_SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE, oldRuntimePlatformService, newRuntimePlatformService);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setRuntimePlatformService(
			SoftwareServiceContainer newRuntimePlatformService) {
		if (newRuntimePlatformService != runtimePlatformService) {
			NotificationChain msgs = null;
			if (runtimePlatformService != null)
				msgs = ((InternalEObject)runtimePlatformService).eInverseRemove(this, ArchitecturePackage.SOFTWARE_SERVICE_CONTAINER__SOFTWARE_SERVICES, SoftwareServiceContainer.class, msgs);
			if (newRuntimePlatformService != null)
				msgs = ((InternalEObject)newRuntimePlatformService).eInverseAdd(this, ArchitecturePackage.SOFTWARE_SERVICE_CONTAINER__SOFTWARE_SERVICES, SoftwareServiceContainer.class, msgs);
			msgs = basicSetRuntimePlatformService(newRuntimePlatformService, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.DEPLOYABLE_SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE, newRuntimePlatformService, newRuntimePlatformService));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ArchitecturePackage.DEPLOYABLE_SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE:
				if (runtimePlatformService != null)
					msgs = ((InternalEObject)runtimePlatformService).eInverseRemove(this, ArchitecturePackage.SOFTWARE_SERVICE_CONTAINER__SOFTWARE_SERVICES, SoftwareServiceContainer.class, msgs);
				return basicSetRuntimePlatformService((SoftwareServiceContainer)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ArchitecturePackage.DEPLOYABLE_SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE:
				return basicSetRuntimePlatformService(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ArchitecturePackage.DEPLOYABLE_SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE:
				if (resolve) return getRuntimePlatformService();
				return basicGetRuntimePlatformService();
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
			case ArchitecturePackage.DEPLOYABLE_SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE:
				setRuntimePlatformService((SoftwareServiceContainer)newValue);
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
			case ArchitecturePackage.DEPLOYABLE_SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE:
				setRuntimePlatformService((SoftwareServiceContainer)null);
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
			case ArchitecturePackage.DEPLOYABLE_SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE:
				return runtimePlatformService != null;
		}
		return super.eIsSet(featureID);
	}

} // DeployableSoftwareServiceImpl
