/**
 */
package org.scaledl.overview.specification.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.scaledl.overview.core.impl.EntityImpl;
import org.scaledl.overview.specification.AvailabilityZoneDescriptor;
import org.scaledl.overview.specification.NetworkInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.SpecificationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Availability Zone Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.impl.AvailabilityZoneDescriptorImpl#getProviderID <em>Provider ID</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.AvailabilityZoneDescriptorImpl#getNetworkInfrastructureServiceDescriptor <em>Network Infrastructure Service Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AvailabilityZoneDescriptorImpl extends EntityImpl implements AvailabilityZoneDescriptor {
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
	 * The cached value of the '{@link #getNetworkInfrastructureServiceDescriptor() <em>Network Infrastructure Service Descriptor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNetworkInfrastructureServiceDescriptor()
	 * @generated
	 * @ordered
	 */
	protected NetworkInfrastructureServiceDescriptor networkInfrastructureServiceDescriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AvailabilityZoneDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SpecificationPackage.Literals.AVAILABILITY_ZONE_DESCRIPTOR;
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
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.AVAILABILITY_ZONE_DESCRIPTOR__PROVIDER_ID, oldProviderID, providerID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkInfrastructureServiceDescriptor getNetworkInfrastructureServiceDescriptor() {
		if (networkInfrastructureServiceDescriptor != null && networkInfrastructureServiceDescriptor.eIsProxy()) {
			InternalEObject oldNetworkInfrastructureServiceDescriptor = (InternalEObject)networkInfrastructureServiceDescriptor;
			networkInfrastructureServiceDescriptor = (NetworkInfrastructureServiceDescriptor)eResolveProxy(oldNetworkInfrastructureServiceDescriptor);
			if (networkInfrastructureServiceDescriptor != oldNetworkInfrastructureServiceDescriptor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SpecificationPackage.AVAILABILITY_ZONE_DESCRIPTOR__NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR, oldNetworkInfrastructureServiceDescriptor, networkInfrastructureServiceDescriptor));
			}
		}
		return networkInfrastructureServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkInfrastructureServiceDescriptor basicGetNetworkInfrastructureServiceDescriptor() {
		return networkInfrastructureServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNetworkInfrastructureServiceDescriptor(NetworkInfrastructureServiceDescriptor newNetworkInfrastructureServiceDescriptor) {
		NetworkInfrastructureServiceDescriptor oldNetworkInfrastructureServiceDescriptor = networkInfrastructureServiceDescriptor;
		networkInfrastructureServiceDescriptor = newNetworkInfrastructureServiceDescriptor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.AVAILABILITY_ZONE_DESCRIPTOR__NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR, oldNetworkInfrastructureServiceDescriptor, networkInfrastructureServiceDescriptor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SpecificationPackage.AVAILABILITY_ZONE_DESCRIPTOR__PROVIDER_ID:
				return getProviderID();
			case SpecificationPackage.AVAILABILITY_ZONE_DESCRIPTOR__NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR:
				if (resolve) return getNetworkInfrastructureServiceDescriptor();
				return basicGetNetworkInfrastructureServiceDescriptor();
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
			case SpecificationPackage.AVAILABILITY_ZONE_DESCRIPTOR__PROVIDER_ID:
				setProviderID((String)newValue);
				return;
			case SpecificationPackage.AVAILABILITY_ZONE_DESCRIPTOR__NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR:
				setNetworkInfrastructureServiceDescriptor((NetworkInfrastructureServiceDescriptor)newValue);
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
			case SpecificationPackage.AVAILABILITY_ZONE_DESCRIPTOR__PROVIDER_ID:
				setProviderID(PROVIDER_ID_EDEFAULT);
				return;
			case SpecificationPackage.AVAILABILITY_ZONE_DESCRIPTOR__NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR:
				setNetworkInfrastructureServiceDescriptor((NetworkInfrastructureServiceDescriptor)null);
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
			case SpecificationPackage.AVAILABILITY_ZONE_DESCRIPTOR__PROVIDER_ID:
				return PROVIDER_ID_EDEFAULT == null ? providerID != null : !PROVIDER_ID_EDEFAULT.equals(providerID);
			case SpecificationPackage.AVAILABILITY_ZONE_DESCRIPTOR__NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR:
				return networkInfrastructureServiceDescriptor != null;
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

} //AvailabilityZoneDescriptorImpl
