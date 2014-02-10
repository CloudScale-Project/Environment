/**
 */
package eu.cloudscaleproject.env.csm.definition.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.cloudscaleproject.env.csm.definition.DefinitionPackage;
import eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Network Infrastructure Service Descriptor</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link eu.cloudscaleproject.env.csm.definition.impl.NetworkInfrastructureServiceDescriptorImpl#getBandwidth
 * <em>Bandwidth</em>}</li>
 * <li>
 * {@link eu.cloudscaleproject.env.csm.definition.impl.NetworkInfrastructureServiceDescriptorImpl#getLatency
 * <em>Latency</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class NetworkInfrastructureServiceDescriptorImpl extends
		InfrastructureServiceDescriptorImpl implements
		NetworkInfrastructureServiceDescriptor {
	/**
	 * The default value of the '{@link #getBandwidth() <em>Bandwidth</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getBandwidth()
	 * @generated
	 * @ordered
	 */
	protected static final int BANDWIDTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getBandwidth() <em>Bandwidth</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getBandwidth()
	 * @generated
	 * @ordered
	 */
	protected int bandwidth = BANDWIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getLatency() <em>Latency</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLatency()
	 * @generated
	 * @ordered
	 */
	protected static final int LATENCY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLatency() <em>Latency</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLatency()
	 * @generated
	 * @ordered
	 */
	protected int latency = LATENCY_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected NetworkInfrastructureServiceDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DefinitionPackage.Literals.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getBandwidth() {
		return bandwidth;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setBandwidth(int newBandwidth) {
		int oldBandwidth = bandwidth;
		bandwidth = newBandwidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DefinitionPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH, oldBandwidth, bandwidth));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getLatency() {
		return latency;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setLatency(int newLatency) {
		int oldLatency = latency;
		latency = newLatency;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DefinitionPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY, oldLatency, latency));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DefinitionPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH:
				return getBandwidth();
			case DefinitionPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY:
				return getLatency();
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
			case DefinitionPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH:
				setBandwidth((Integer)newValue);
				return;
			case DefinitionPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY:
				setLatency((Integer)newValue);
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
			case DefinitionPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH:
				setBandwidth(BANDWIDTH_EDEFAULT);
				return;
			case DefinitionPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY:
				setLatency(LATENCY_EDEFAULT);
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
			case DefinitionPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__BANDWIDTH:
				return bandwidth != BANDWIDTH_EDEFAULT;
			case DefinitionPackage.NETWORK_INFRASTRUCTURE_SERVICE_DESCRIPTOR__LATENCY:
				return latency != LATENCY_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (bandwidth: ");
		result.append(bandwidth);
		result.append(", latency: ");
		result.append(latency);
		result.append(')');
		return result.toString();
	}

} // NetworkInfrastructureServiceDescriptorImpl
