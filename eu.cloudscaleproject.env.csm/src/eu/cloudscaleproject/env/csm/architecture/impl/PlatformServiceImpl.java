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
import eu.cloudscaleproject.env.csm.architecture.PlatformLayer;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Platform Service</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.PlatformServiceImpl#getPlatformLayer <em>Platform Layer</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PlatformServiceImpl extends ServiceImpl implements
		PlatformService {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected PlatformServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.PLATFORM_SERVICE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformLayer getPlatformLayer() {
		if (eContainerFeatureID() != ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER) return null;
		return (PlatformLayer)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPlatformLayer(
			PlatformLayer newPlatformLayer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPlatformLayer, ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPlatformLayer((PlatformLayer)otherEnd, msgs);
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
			case ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER:
				return basicSetPlatformLayer(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER:
				return eInternalContainer().eInverseRemove(this, ArchitecturePackage.PLATFORM_LAYER__SERVICES, PlatformLayer.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER:
				return getPlatformLayer();
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
			case ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER:
				setPlatformLayer((PlatformLayer)newValue);
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
			case ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER:
				setPlatformLayer((PlatformLayer)null);
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
			case ArchitecturePackage.PLATFORM_SERVICE__PLATFORM_LAYER:
				return getPlatformLayer() != null;
		}
		return super.eIsSet(featureID);
	}

} // PlatformServiceImpl
