/**
 */
package org.scaledl.overview.specification.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.scaledl.overview.core.impl.EntityImpl;
import org.scaledl.overview.specification.AvailabilityZoneDescriptor;
import org.scaledl.overview.specification.CloudEnvironmentDescriptor;
import org.scaledl.overview.specification.RegionDescriptor;
import org.scaledl.overview.specification.SpecificationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cloud Environment Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.impl.CloudEnvironmentDescriptorImpl#getProviderID <em>Provider ID</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.CloudEnvironmentDescriptorImpl#getAvailabilityZones <em>Availability Zones</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.CloudEnvironmentDescriptorImpl#getRegions <em>Regions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CloudEnvironmentDescriptorImpl extends EntityImpl implements CloudEnvironmentDescriptor {
	/**
	 * The default value of the '{@link #getProviderID() <em>Provider ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProviderID()
	 * @generated
	 * @ordered
	 */
	protected static final String PROVIDER_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProviderID() <em>Provider ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProviderID()
	 * @generated
	 * @ordered
	 */
	protected String providerID = PROVIDER_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAvailabilityZones() <em>Availability Zones</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailabilityZones()
	 * @generated
	 * @ordered
	 */
	protected EList<AvailabilityZoneDescriptor> availabilityZones;

	/**
	 * The cached value of the '{@link #getRegions() <em>Regions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegions()
	 * @generated
	 * @ordered
	 */
	protected EList<RegionDescriptor> regions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CloudEnvironmentDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SpecificationPackage.Literals.CLOUD_ENVIRONMENT_DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProviderID() {
		return providerID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProviderID(String newProviderID) {
		String oldProviderID = providerID;
		providerID = newProviderID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__PROVIDER_ID, oldProviderID, providerID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AvailabilityZoneDescriptor> getAvailabilityZones() {
		if (availabilityZones == null) {
			availabilityZones = new EObjectContainmentEList<AvailabilityZoneDescriptor>(AvailabilityZoneDescriptor.class, this, SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES);
		}
		return availabilityZones;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RegionDescriptor> getRegions() {
		if (regions == null) {
			regions = new EObjectContainmentEList<RegionDescriptor>(RegionDescriptor.class, this, SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS);
		}
		return regions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES:
				return ((InternalEList<?>)getAvailabilityZones()).basicRemove(otherEnd, msgs);
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS:
				return ((InternalEList<?>)getRegions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__PROVIDER_ID:
				return getProviderID();
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES:
				return getAvailabilityZones();
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS:
				return getRegions();
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
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__PROVIDER_ID:
				setProviderID((String)newValue);
				return;
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES:
				getAvailabilityZones().clear();
				getAvailabilityZones().addAll((Collection<? extends AvailabilityZoneDescriptor>)newValue);
				return;
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS:
				getRegions().clear();
				getRegions().addAll((Collection<? extends RegionDescriptor>)newValue);
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
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__PROVIDER_ID:
				setProviderID(PROVIDER_ID_EDEFAULT);
				return;
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES:
				getAvailabilityZones().clear();
				return;
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS:
				getRegions().clear();
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
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__PROVIDER_ID:
				return PROVIDER_ID_EDEFAULT == null ? providerID != null : !PROVIDER_ID_EDEFAULT.equals(providerID);
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES:
				return availabilityZones != null && !availabilityZones.isEmpty();
			case SpecificationPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS:
				return regions != null && !regions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (providerID: ");
		result.append(providerID);
		result.append(')');
		return result.toString();
	}

} //CloudEnvironmentDescriptorImpl
