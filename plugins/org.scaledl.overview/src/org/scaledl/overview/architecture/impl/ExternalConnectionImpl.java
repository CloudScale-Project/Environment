/**
 */
package org.scaledl.overview.architecture.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.scaledl.overview.application.OperationInterfaceContainer;

import org.scaledl.overview.architecture.ArchitecturePackage;
import org.scaledl.overview.architecture.ExternalConnection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>External Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.architecture.impl.ExternalConnectionImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.scaledl.overview.architecture.impl.ExternalConnectionImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.scaledl.overview.architecture.impl.ExternalConnectionImpl#getBandwidth <em>Bandwidth</em>}</li>
 *   <li>{@link org.scaledl.overview.architecture.impl.ExternalConnectionImpl#getLatency <em>Latency</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExternalConnectionImpl extends ConnectionImpl implements ExternalConnection {
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected OperationInterfaceContainer source;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected OperationInterfaceContainer target;

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
	protected ExternalConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.EXTERNAL_CONNECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationInterfaceContainer getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (OperationInterfaceContainer)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.EXTERNAL_CONNECTION__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationInterfaceContainer basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(OperationInterfaceContainer newSource) {
		OperationInterfaceContainer oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.EXTERNAL_CONNECTION__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationInterfaceContainer getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (OperationInterfaceContainer)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.EXTERNAL_CONNECTION__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationInterfaceContainer basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(OperationInterfaceContainer newTarget) {
		OperationInterfaceContainer oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.EXTERNAL_CONNECTION__TARGET, oldTarget, target));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.EXTERNAL_CONNECTION__BANDWIDTH, oldBandwidth, bandwidth));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.EXTERNAL_CONNECTION__LATENCY, oldLatency, latency));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ArchitecturePackage.EXTERNAL_CONNECTION__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case ArchitecturePackage.EXTERNAL_CONNECTION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case ArchitecturePackage.EXTERNAL_CONNECTION__BANDWIDTH:
				return getBandwidth();
			case ArchitecturePackage.EXTERNAL_CONNECTION__LATENCY:
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
			case ArchitecturePackage.EXTERNAL_CONNECTION__SOURCE:
				setSource((OperationInterfaceContainer)newValue);
				return;
			case ArchitecturePackage.EXTERNAL_CONNECTION__TARGET:
				setTarget((OperationInterfaceContainer)newValue);
				return;
			case ArchitecturePackage.EXTERNAL_CONNECTION__BANDWIDTH:
				setBandwidth((Integer)newValue);
				return;
			case ArchitecturePackage.EXTERNAL_CONNECTION__LATENCY:
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
			case ArchitecturePackage.EXTERNAL_CONNECTION__SOURCE:
				setSource((OperationInterfaceContainer)null);
				return;
			case ArchitecturePackage.EXTERNAL_CONNECTION__TARGET:
				setTarget((OperationInterfaceContainer)null);
				return;
			case ArchitecturePackage.EXTERNAL_CONNECTION__BANDWIDTH:
				setBandwidth(BANDWIDTH_EDEFAULT);
				return;
			case ArchitecturePackage.EXTERNAL_CONNECTION__LATENCY:
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
			case ArchitecturePackage.EXTERNAL_CONNECTION__SOURCE:
				return source != null;
			case ArchitecturePackage.EXTERNAL_CONNECTION__TARGET:
				return target != null;
			case ArchitecturePackage.EXTERNAL_CONNECTION__BANDWIDTH:
				return bandwidth != BANDWIDTH_EDEFAULT;
			case ArchitecturePackage.EXTERNAL_CONNECTION__LATENCY:
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
		result.append(" (bandwidth: ");
		result.append(bandwidth);
		result.append(", latency: ");
		result.append(latency);
		result.append(')');
		return result.toString();
	}

} //ExternalConnectionImpl
