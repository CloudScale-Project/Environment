/**
 */
package org.scaledl.overview.architecture.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.scaledl.overview.architecture.ArchitecturePackage;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.InfrastructureLayer;
import org.scaledl.overview.architecture.InfrastructureService;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Infrastructure Layer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.architecture.impl.InfrastructureLayerImpl#getServices <em>Services</em>}</li>
 *   <li>{@link org.scaledl.overview.architecture.impl.InfrastructureLayerImpl#getCloudEnvironment <em>Cloud Environment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InfrastructureLayerImpl extends EObjectImpl implements InfrastructureLayer {
	/**
	 * The cached value of the '{@link #getServices() <em>Services</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServices()
	 * @generated
	 * @ordered
	 */
	protected EList<InfrastructureService> services;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InfrastructureLayerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.INFRASTRUCTURE_LAYER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InfrastructureService> getServices() {
		if (services == null) {
			services = new EObjectContainmentWithInverseEList<InfrastructureService>(InfrastructureService.class, this, ArchitecturePackage.INFRASTRUCTURE_LAYER__SERVICES, ArchitecturePackage.INFRASTRUCTURE_SERVICE__INFRASTRUCTURE_LAYER);
		}
		return services;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CloudEnvironment getCloudEnvironment() {
		if (eContainerFeatureID() != ArchitecturePackage.INFRASTRUCTURE_LAYER__CLOUD_ENVIRONMENT) return null;
		return (CloudEnvironment)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCloudEnvironment(CloudEnvironment newCloudEnvironment, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCloudEnvironment, ArchitecturePackage.INFRASTRUCTURE_LAYER__CLOUD_ENVIRONMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCloudEnvironment(CloudEnvironment newCloudEnvironment) {
		if (newCloudEnvironment != eInternalContainer() || (eContainerFeatureID() != ArchitecturePackage.INFRASTRUCTURE_LAYER__CLOUD_ENVIRONMENT && newCloudEnvironment != null)) {
			if (EcoreUtil.isAncestor(this, newCloudEnvironment))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCloudEnvironment != null)
				msgs = ((InternalEObject)newCloudEnvironment).eInverseAdd(this, ArchitecturePackage.CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER, CloudEnvironment.class, msgs);
			msgs = basicSetCloudEnvironment(newCloudEnvironment, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.INFRASTRUCTURE_LAYER__CLOUD_ENVIRONMENT, newCloudEnvironment, newCloudEnvironment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ArchitecturePackage.INFRASTRUCTURE_LAYER__SERVICES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getServices()).basicAdd(otherEnd, msgs);
			case ArchitecturePackage.INFRASTRUCTURE_LAYER__CLOUD_ENVIRONMENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCloudEnvironment((CloudEnvironment)otherEnd, msgs);
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
			case ArchitecturePackage.INFRASTRUCTURE_LAYER__SERVICES:
				return ((InternalEList<?>)getServices()).basicRemove(otherEnd, msgs);
			case ArchitecturePackage.INFRASTRUCTURE_LAYER__CLOUD_ENVIRONMENT:
				return basicSetCloudEnvironment(null, msgs);
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
			case ArchitecturePackage.INFRASTRUCTURE_LAYER__CLOUD_ENVIRONMENT:
				return eInternalContainer().eInverseRemove(this, ArchitecturePackage.CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER, CloudEnvironment.class, msgs);
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
			case ArchitecturePackage.INFRASTRUCTURE_LAYER__SERVICES:
				return getServices();
			case ArchitecturePackage.INFRASTRUCTURE_LAYER__CLOUD_ENVIRONMENT:
				return getCloudEnvironment();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ArchitecturePackage.INFRASTRUCTURE_LAYER__SERVICES:
				getServices().clear();
				getServices().addAll((Collection<? extends InfrastructureService>)newValue);
				return;
			case ArchitecturePackage.INFRASTRUCTURE_LAYER__CLOUD_ENVIRONMENT:
				setCloudEnvironment((CloudEnvironment)newValue);
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
			case ArchitecturePackage.INFRASTRUCTURE_LAYER__SERVICES:
				getServices().clear();
				return;
			case ArchitecturePackage.INFRASTRUCTURE_LAYER__CLOUD_ENVIRONMENT:
				setCloudEnvironment((CloudEnvironment)null);
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
			case ArchitecturePackage.INFRASTRUCTURE_LAYER__SERVICES:
				return services != null && !services.isEmpty();
			case ArchitecturePackage.INFRASTRUCTURE_LAYER__CLOUD_ENVIRONMENT:
				return getCloudEnvironment() != null;
		}
		return super.eIsSet(featureID);
	}

} //InfrastructureLayerImpl
