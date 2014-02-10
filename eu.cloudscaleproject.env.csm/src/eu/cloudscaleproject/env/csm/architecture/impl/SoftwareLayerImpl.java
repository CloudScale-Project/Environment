/**
 */
package eu.cloudscaleproject.env.csm.architecture.impl;

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

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.SoftwareLayer;
import eu.cloudscaleproject.env.csm.architecture.SoftwareService;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Software Layer</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.SoftwareLayerImpl#getServices <em>Services</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.SoftwareLayerImpl#getCloudProvider <em>Cloud Provider</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SoftwareLayerImpl extends EObjectImpl implements SoftwareLayer {
	/**
	 * The cached value of the '{@link #getServices() <em>Services</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getServices()
	 * @generated
	 * @ordered
	 */
	protected EList<SoftwareService> services;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected SoftwareLayerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.SOFTWARE_LAYER;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SoftwareService> getServices() {
		if (services == null) {
			services = new EObjectContainmentWithInverseEList<SoftwareService>(SoftwareService.class, this, ArchitecturePackage.SOFTWARE_LAYER__SERVICES, ArchitecturePackage.SOFTWARE_SERVICE__SOFTWARE_LAYER);
		}
		return services;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CloudEnvironment getCloudProvider() {
		if (eContainerFeatureID() != ArchitecturePackage.SOFTWARE_LAYER__CLOUD_PROVIDER) return null;
		return (CloudEnvironment)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCloudProvider(
			CloudEnvironment newCloudProvider, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCloudProvider, ArchitecturePackage.SOFTWARE_LAYER__CLOUD_PROVIDER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setCloudProvider(CloudEnvironment newCloudProvider) {
		if (newCloudProvider != eInternalContainer() || (eContainerFeatureID() != ArchitecturePackage.SOFTWARE_LAYER__CLOUD_PROVIDER && newCloudProvider != null)) {
			if (EcoreUtil.isAncestor(this, newCloudProvider))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCloudProvider != null)
				msgs = ((InternalEObject)newCloudProvider).eInverseAdd(this, ArchitecturePackage.CLOUD_ENVIRONMENT__SOFTWARE_LAYER, CloudEnvironment.class, msgs);
			msgs = basicSetCloudProvider(newCloudProvider, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.SOFTWARE_LAYER__CLOUD_PROVIDER, newCloudProvider, newCloudProvider));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ArchitecturePackage.SOFTWARE_LAYER__SERVICES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getServices()).basicAdd(otherEnd, msgs);
			case ArchitecturePackage.SOFTWARE_LAYER__CLOUD_PROVIDER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCloudProvider((CloudEnvironment)otherEnd, msgs);
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
			case ArchitecturePackage.SOFTWARE_LAYER__SERVICES:
				return ((InternalEList<?>)getServices()).basicRemove(otherEnd, msgs);
			case ArchitecturePackage.SOFTWARE_LAYER__CLOUD_PROVIDER:
				return basicSetCloudProvider(null, msgs);
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
			case ArchitecturePackage.SOFTWARE_LAYER__CLOUD_PROVIDER:
				return eInternalContainer().eInverseRemove(this, ArchitecturePackage.CLOUD_ENVIRONMENT__SOFTWARE_LAYER, CloudEnvironment.class, msgs);
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
			case ArchitecturePackage.SOFTWARE_LAYER__SERVICES:
				return getServices();
			case ArchitecturePackage.SOFTWARE_LAYER__CLOUD_PROVIDER:
				return getCloudProvider();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ArchitecturePackage.SOFTWARE_LAYER__SERVICES:
				getServices().clear();
				getServices().addAll((Collection<? extends SoftwareService>)newValue);
				return;
			case ArchitecturePackage.SOFTWARE_LAYER__CLOUD_PROVIDER:
				setCloudProvider((CloudEnvironment)newValue);
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
			case ArchitecturePackage.SOFTWARE_LAYER__SERVICES:
				getServices().clear();
				return;
			case ArchitecturePackage.SOFTWARE_LAYER__CLOUD_PROVIDER:
				setCloudProvider((CloudEnvironment)null);
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
			case ArchitecturePackage.SOFTWARE_LAYER__SERVICES:
				return services != null && !services.isEmpty();
			case ArchitecturePackage.SOFTWARE_LAYER__CLOUD_PROVIDER:
				return getCloudProvider() != null;
		}
		return super.eIsSet(featureID);
	}

} // SoftwareLayerImpl
