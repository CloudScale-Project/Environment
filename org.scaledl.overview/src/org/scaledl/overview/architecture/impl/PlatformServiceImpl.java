/**
 */
package org.scaledl.overview.architecture.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.scaledl.overview.architecture.ArchitecturePackage;
import org.scaledl.overview.architecture.ComputingInfrastructureService;
import org.scaledl.overview.architecture.PlatformLayer;
import org.scaledl.overview.architecture.PlatformService;
import org.scaledl.overview.core.impl.EntityImpl;
import org.scaledl.overview.specification.PlatformServiceDescriptor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Platform Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.architecture.impl.PlatformServiceImpl#getDescriptor <em>Descriptor</em>}</li>
 *   <li>{@link org.scaledl.overview.architecture.impl.PlatformServiceImpl#getComputingInfrastructureService <em>Computing Infrastructure Service</em>}</li>
 *   <li>{@link org.scaledl.overview.architecture.impl.PlatformServiceImpl#getPlatformLayer <em>Platform Layer</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PlatformServiceImpl extends EntityImpl implements PlatformService {
	/**
	 * The cached value of the '{@link #getDescriptor() <em>Descriptor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptor()
	 * @generated
	 * @ordered
	 */
	protected PlatformServiceDescriptor descriptor;

	/**
	 * The cached value of the '{@link #getComputingInfrastructureService() <em>Computing Infrastructure Service</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComputingInfrastructureService()
	 * @generated
	 * @ordered
	 */
	protected ComputingInfrastructureService computingInfrastructureService;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlatformServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.PLATFORM_SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformServiceDescriptor getDescriptor() {
		if (descriptor != null && descriptor.eIsProxy()) {
			InternalEObject oldDescriptor = (InternalEObject)descriptor;
			descriptor = (PlatformServiceDescriptor)eResolveProxy(oldDescriptor);
			if (descriptor != oldDescriptor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.PLATFORM_SERVICE__DESCRIPTOR, oldDescriptor, descriptor));
			}
		}
		return descriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformServiceDescriptor basicGetDescriptor() {
		return descriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescriptor(PlatformServiceDescriptor newDescriptor) {
		PlatformServiceDescriptor oldDescriptor = descriptor;
		descriptor = newDescriptor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.PLATFORM_SERVICE__DESCRIPTOR, oldDescriptor, descriptor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComputingInfrastructureService getComputingInfrastructureService() {
		if (computingInfrastructureService != null && computingInfrastructureService.eIsProxy()) {
			InternalEObject oldComputingInfrastructureService = (InternalEObject)computingInfrastructureService;
			computingInfrastructureService = (ComputingInfrastructureService)eResolveProxy(oldComputingInfrastructureService);
			if (computingInfrastructureService != oldComputingInfrastructureService) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE, oldComputingInfrastructureService, computingInfrastructureService));
			}
		}
		return computingInfrastructureService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComputingInfrastructureService basicGetComputingInfrastructureService() {
		return computingInfrastructureService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComputingInfrastructureService(ComputingInfrastructureService newComputingInfrastructureService, NotificationChain msgs) {
		ComputingInfrastructureService oldComputingInfrastructureService = computingInfrastructureService;
		computingInfrastructureService = newComputingInfrastructureService;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ArchitecturePackage.PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE, oldComputingInfrastructureService, newComputingInfrastructureService);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComputingInfrastructureService(ComputingInfrastructureService newComputingInfrastructureService) {
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
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE, newComputingInfrastructureService, newComputingInfrastructureService));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformLayer getPlatformLayer() {
		if (eContainerFeatureID() != ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER) return null;
		return (PlatformLayer)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPlatformLayer(PlatformLayer newPlatformLayer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPlatformLayer, ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPlatformLayer(PlatformLayer newPlatformLayer) {
		if (newPlatformLayer != eInternalContainer() || (eContainerFeatureID() != ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER && newPlatformLayer != null)) {
			if (EcoreUtil.isAncestor(this, newPlatformLayer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPlatformLayer != null)
				msgs = ((InternalEObject)newPlatformLayer).eInverseAdd(this, ArchitecturePackage.PLATFORM_LAYER__SERVICES, PlatformLayer.class, msgs);
			msgs = basicSetPlatformLayer(newPlatformLayer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER, newPlatformLayer, newPlatformLayer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ArchitecturePackage.PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE:
				if (computingInfrastructureService != null)
					msgs = ((InternalEObject)computingInfrastructureService).eInverseRemove(this, ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE__PLATFORM_SERVICES, ComputingInfrastructureService.class, msgs);
				return basicSetComputingInfrastructureService((ComputingInfrastructureService)otherEnd, msgs);
			case ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPlatformLayer((PlatformLayer)otherEnd, msgs);
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
			case ArchitecturePackage.PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE:
				return basicSetComputingInfrastructureService(null, msgs);
			case ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER:
				return basicSetPlatformLayer(null, msgs);
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
			case ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER:
				return eInternalContainer().eInverseRemove(this, ArchitecturePackage.PLATFORM_LAYER__SERVICES, PlatformLayer.class, msgs);
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
			case ArchitecturePackage.PLATFORM_SERVICE__DESCRIPTOR:
				if (resolve) return getDescriptor();
				return basicGetDescriptor();
			case ArchitecturePackage.PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE:
				if (resolve) return getComputingInfrastructureService();
				return basicGetComputingInfrastructureService();
			case ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER:
				return getPlatformLayer();
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
			case ArchitecturePackage.PLATFORM_SERVICE__DESCRIPTOR:
				setDescriptor((PlatformServiceDescriptor)newValue);
				return;
			case ArchitecturePackage.PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE:
				setComputingInfrastructureService((ComputingInfrastructureService)newValue);
				return;
			case ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER:
				setPlatformLayer((PlatformLayer)newValue);
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
			case ArchitecturePackage.PLATFORM_SERVICE__DESCRIPTOR:
				setDescriptor((PlatformServiceDescriptor)null);
				return;
			case ArchitecturePackage.PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE:
				setComputingInfrastructureService((ComputingInfrastructureService)null);
				return;
			case ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER:
				setPlatformLayer((PlatformLayer)null);
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
			case ArchitecturePackage.PLATFORM_SERVICE__DESCRIPTOR:
				return descriptor != null;
			case ArchitecturePackage.PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE:
				return computingInfrastructureService != null;
			case ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER:
				return getPlatformLayer() != null;
		}
		return super.eIsSet(featureID);
	}

} //PlatformServiceImpl
