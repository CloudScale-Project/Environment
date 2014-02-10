/**
 */
package eu.cloudscaleproject.env.csm.architecture.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService;
import eu.cloudscaleproject.env.csm.definition.DeployablePlatformServiceDescriptor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Deployable Platform Service</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.DeployablePlatformServiceImpl#getComputingInfrastructureService <em>Computing Infrastructure Service</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.DeployablePlatformServiceImpl#getDescriptor <em>Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class DeployablePlatformServiceImpl extends PlatformServiceImpl
		implements DeployablePlatformService {
	/**
	 * The cached value of the '{@link #getComputingInfrastructureService()
	 * <em>Computing Infrastructure Service</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getComputingInfrastructureService()
	 * @generated
	 * @ordered
	 */
	protected ComputingInfrastructureService computingInfrastructureService;

	/**
	 * The cached value of the '{@link #getDescriptor() <em>Descriptor</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDescriptor()
	 * @generated
	 * @ordered
	 */
	protected DeployablePlatformServiceDescriptor descriptor;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected DeployablePlatformServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.DEPLOYABLE_PLATFORM_SERVICE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ComputingInfrastructureService getComputingInfrastructureService() {
		if (computingInfrastructureService != null && computingInfrastructureService.eIsProxy()) {
			InternalEObject oldComputingInfrastructureService = (InternalEObject)computingInfrastructureService;
			computingInfrastructureService = (ComputingInfrastructureService)eResolveProxy(oldComputingInfrastructureService);
			if (computingInfrastructureService != oldComputingInfrastructureService) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.DEPLOYABLE_PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE, oldComputingInfrastructureService, computingInfrastructureService));
			}
		}
		return computingInfrastructureService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ComputingInfrastructureService basicGetComputingInfrastructureService() {
		return computingInfrastructureService;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComputingInfrastructureService(
			ComputingInfrastructureService newComputingInfrastructureService,
			NotificationChain msgs) {
		ComputingInfrastructureService oldComputingInfrastructureService = computingInfrastructureService;
		computingInfrastructureService = newComputingInfrastructureService;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ArchitecturePackage.DEPLOYABLE_PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE, oldComputingInfrastructureService, newComputingInfrastructureService);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setComputingInfrastructureService(
			ComputingInfrastructureService newComputingInfrastructureService) {
		if (newComputingInfrastructureService != computingInfrastructureService) {
			NotificationChain msgs = null;
			if (computingInfrastructureService != null)
				msgs = ((InternalEObject)computingInfrastructureService).eInverseRemove(this, ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE__PLATFORM_SERVICES, ComputingInfrastructureService.class, msgs);
			if (newComputingInfrastructureService != null)
				msgs = ((InternalEObject)newComputingInfrastructureService).eInverseAdd(this, ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE__PLATFORM_SERVICES, ComputingInfrastructureService.class, msgs);
			msgs = basicSetComputingInfrastructureService(newComputingInfrastructureService, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.DEPLOYABLE_PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE, newComputingInfrastructureService, newComputingInfrastructureService));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public DeployablePlatformServiceDescriptor getDescriptor() {
		if (descriptor != null && descriptor.eIsProxy()) {
			InternalEObject oldDescriptor = (InternalEObject)descriptor;
			descriptor = (DeployablePlatformServiceDescriptor)eResolveProxy(oldDescriptor);
			if (descriptor != oldDescriptor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.DEPLOYABLE_PLATFORM_SERVICE__DESCRIPTOR, oldDescriptor, descriptor));
			}
		}
		return descriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public DeployablePlatformServiceDescriptor basicGetDescriptor() {
		return descriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescriptor(DeployablePlatformServiceDescriptor newDescriptor) {
		DeployablePlatformServiceDescriptor oldDescriptor = descriptor;
		descriptor = newDescriptor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.DEPLOYABLE_PLATFORM_SERVICE__DESCRIPTOR, oldDescriptor, descriptor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ArchitecturePackage.DEPLOYABLE_PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE:
				if (computingInfrastructureService != null)
					msgs = ((InternalEObject)computingInfrastructureService).eInverseRemove(this, ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE__PLATFORM_SERVICES, ComputingInfrastructureService.class, msgs);
				return basicSetComputingInfrastructureService((ComputingInfrastructureService)otherEnd, msgs);
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
			case ArchitecturePackage.DEPLOYABLE_PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE:
				return basicSetComputingInfrastructureService(null, msgs);
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
			case ArchitecturePackage.DEPLOYABLE_PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE:
				if (resolve) return getComputingInfrastructureService();
				return basicGetComputingInfrastructureService();
			case ArchitecturePackage.DEPLOYABLE_PLATFORM_SERVICE__DESCRIPTOR:
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
			case ArchitecturePackage.DEPLOYABLE_PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE:
				setComputingInfrastructureService((ComputingInfrastructureService)newValue);
				return;
			case ArchitecturePackage.DEPLOYABLE_PLATFORM_SERVICE__DESCRIPTOR:
				setDescriptor((DeployablePlatformServiceDescriptor)newValue);
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
			case ArchitecturePackage.DEPLOYABLE_PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE:
				setComputingInfrastructureService((ComputingInfrastructureService)null);
				return;
			case ArchitecturePackage.DEPLOYABLE_PLATFORM_SERVICE__DESCRIPTOR:
				setDescriptor((DeployablePlatformServiceDescriptor)null);
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
			case ArchitecturePackage.DEPLOYABLE_PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE:
				return computingInfrastructureService != null;
			case ArchitecturePackage.DEPLOYABLE_PLATFORM_SERVICE__DESCRIPTOR:
				return descriptor != null;
		}
		return super.eIsSet(featureID);
	}

} // DeployablePlatformServiceImpl
