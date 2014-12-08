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
import org.scaledl.overview.architecture.InfrastructureLayer;
import org.scaledl.overview.architecture.InfrastructureService;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Infrastructure Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.architecture.impl.InfrastructureServiceImpl#getInfrastructureLayer <em>Infrastructure Layer</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class InfrastructureServiceImpl extends ProvidedServiceImpl implements InfrastructureService {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InfrastructureServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.INFRASTRUCTURE_SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InfrastructureLayer getInfrastructureLayer() {
		if (eContainerFeatureID() != ArchitecturePackage.INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER) return null;
		return (InfrastructureLayer)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInfrastructureLayer(InfrastructureLayer newInfrastructureLayer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newInfrastructureLayer, ArchitecturePackage.INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInfrastructureLayer(InfrastructureLayer newInfrastructureLayer) {
		if (newInfrastructureLayer != eInternalContainer() || (eContainerFeatureID() != ArchitecturePackage.INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER && newInfrastructureLayer != null)) {
			if (EcoreUtil.isAncestor(this, newInfrastructureLayer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInfrastructureLayer != null)
				msgs = ((InternalEObject)newInfrastructureLayer).eInverseAdd(this, ArchitecturePackage.INFRASTRUCTURE_LAYER__SERVICES, InfrastructureLayer.class, msgs);
			msgs = basicSetInfrastructureLayer(newInfrastructureLayer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER, newInfrastructureLayer, newInfrastructureLayer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ArchitecturePackage.INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetInfrastructureLayer((InfrastructureLayer)otherEnd, msgs);
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
			case ArchitecturePackage.INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER:
				return basicSetInfrastructureLayer(null, msgs);
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
			case ArchitecturePackage.INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER:
				return eInternalContainer().eInverseRemove(this, ArchitecturePackage.INFRASTRUCTURE_LAYER__SERVICES, InfrastructureLayer.class, msgs);
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
			case ArchitecturePackage.INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER:
				return getInfrastructureLayer();
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
			case ArchitecturePackage.INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER:
				setInfrastructureLayer((InfrastructureLayer)newValue);
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
			case ArchitecturePackage.INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER:
				setInfrastructureLayer((InfrastructureLayer)null);
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
			case ArchitecturePackage.INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER:
				return getInfrastructureLayer() != null;
		}
		return super.eIsSet(featureID);
	}

} //InfrastructureServiceImpl
