/**
 */
package org.scaledl.overview.specification.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.scaledl.overview.core.impl.EntityImpl;

import org.scaledl.overview.specification.NetworkInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.SpecificationPackage;

import org.scaledl.overview.specification.sla.Sla;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Network Infrastructure Service Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.impl.NetworkInfrastructureServiceDescriptorImpl#getProviderID <em>Provider ID</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.NetworkInfrastructureServiceDescriptorImpl#getSla <em>Sla</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.NetworkInfrastructureServiceDescriptorImpl#getBandwidth <em>Bandwidth</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.NetworkInfrastructureServiceDescriptorImpl#getLatency <em>Latency</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NetworkInfrastructureServiceDescriptorImpl extends EntityImpl implements NetworkInfrastructureServiceDescriptor {
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
	 * The cached value of the '{@link #getSla() <em>Sla</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSla()
	 * @generated
	 * @ordered
	 */
	protected Sla sla;

	/**
	 * The default value of the '{@link #getBandwidth() <em>Bandwidth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBandwidth()
	 * @generated
	 * @ordered
	 */
	protected static final int BANDWIDTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getBandwidth() <em>Bandwidth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBandwidth()
	 * @generated
	 * @ordered
	 */
	protected int bandwidth = BANDWIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getLatency() <em>Latency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLatency()
	 * @generated
	 * @ordered
	 */
	protected static final int LATENCY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLatency() <em>Latency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLatency()
	 * @generated
	 * @ordered
	 */
	protected int latency = LATENCY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NetworkInfrastructureServiceDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SpecificationPackage.Literals.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR;
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
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID, oldProviderID, providerID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sla getSla() {
		return sla;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSla(Sla newSla, NotificationChain msgs) {
		Sla oldSla = sla;
		sla = newSla;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA, oldSla, newSla);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSla(Sla newSla) {
		if (newSla != sla) {
			NotificationChain msgs = null;
			if (sla != null)
				msgs = ((InternalEObject)sla).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA, null, msgs);
			if (newSla != null)
				msgs = ((InternalEObject)newSla).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA, null, msgs);
			msgs = basicSetSla(newSla, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA, newSla, newSla));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getBandwidth() {
		return bandwidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBandwidth(int newBandwidth) {
		int oldBandwidth = bandwidth;
		bandwidth = newBandwidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH, oldBandwidth, bandwidth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLatency() {
		return latency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLatency(int newLatency) {
		int oldLatency = latency;
		latency = newLatency;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY, oldLatency, latency));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA:
				return basicSetSla(null, msgs);
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
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID:
				return getProviderID();
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA:
				return getSla();
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH:
				return getBandwidth();
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY:
				return getLatency();
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
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID:
				setProviderID((String)newValue);
				return;
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA:
				setSla((Sla)newValue);
				return;
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH:
				setBandwidth((Integer)newValue);
				return;
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY:
				setLatency((Integer)newValue);
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
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID:
				setProviderID(PROVIDER_ID_EDEFAULT);
				return;
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA:
				setSla((Sla)null);
				return;
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH:
				setBandwidth(BANDWIDTH_EDEFAULT);
				return;
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY:
				setLatency(LATENCY_EDEFAULT);
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
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID:
				return PROVIDER_ID_EDEFAULT == null ? providerID != null : !PROVIDER_ID_EDEFAULT.equals(providerID);
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA:
				return sla != null;
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH:
				return bandwidth != BANDWIDTH_EDEFAULT;
			case SpecificationPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY:
				return latency != LATENCY_EDEFAULT;
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
		result.append(", bandwidth: ");
		result.append(bandwidth);
		result.append(", latency: ");
		result.append(latency);
		result.append(')');
		return result.toString();
	}

} //NetworkInfrastructureServiceDescriptorImpl
