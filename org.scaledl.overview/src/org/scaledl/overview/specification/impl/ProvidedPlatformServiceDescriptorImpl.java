/**
 */
package org.scaledl.overview.specification.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.ProvidedPlatformServiceDescriptor;
import org.scaledl.overview.specification.ProvidedServiceDescriptor;
import org.scaledl.overview.specification.SpecificationPackage;
import org.scaledl.overview.specification.sla.Sla;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Provided Platform Service Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.impl.ProvidedPlatformServiceDescriptorImpl#getSla <em>Sla</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.ProvidedPlatformServiceDescriptorImpl#getInfrastructureServiceDescriptor <em>Infrastructure Service Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProvidedPlatformServiceDescriptorImpl extends PlatformServiceDescriptorImpl implements ProvidedPlatformServiceDescriptor {
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
	 * The cached value of the '{@link #getInfrastructureServiceDescriptor() <em>Infrastructure Service Descriptor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInfrastructureServiceDescriptor()
	 * @generated
	 * @ordered
	 */
	protected ComputingInfrastructureServiceDescriptor infrastructureServiceDescriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProvidedPlatformServiceDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SpecificationPackage.Literals.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__SLA, oldSla, newSla);
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
				msgs = ((InternalEObject)sla).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__SLA, null, msgs);
			if (newSla != null)
				msgs = ((InternalEObject)newSla).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__SLA, null, msgs);
			msgs = basicSetSla(newSla, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__SLA, newSla, newSla));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComputingInfrastructureServiceDescriptor getInfrastructureServiceDescriptor() {
		if (infrastructureServiceDescriptor != null && infrastructureServiceDescriptor.eIsProxy()) {
			InternalEObject oldInfrastructureServiceDescriptor = (InternalEObject)infrastructureServiceDescriptor;
			infrastructureServiceDescriptor = (ComputingInfrastructureServiceDescriptor)eResolveProxy(oldInfrastructureServiceDescriptor);
			if (infrastructureServiceDescriptor != oldInfrastructureServiceDescriptor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__INFRASTRUCTURE_SERVICE_DESCRIPTOR, oldInfrastructureServiceDescriptor, infrastructureServiceDescriptor));
			}
		}
		return infrastructureServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComputingInfrastructureServiceDescriptor basicGetInfrastructureServiceDescriptor() {
		return infrastructureServiceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInfrastructureServiceDescriptor(ComputingInfrastructureServiceDescriptor newInfrastructureServiceDescriptor) {
		ComputingInfrastructureServiceDescriptor oldInfrastructureServiceDescriptor = infrastructureServiceDescriptor;
		infrastructureServiceDescriptor = newInfrastructureServiceDescriptor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__INFRASTRUCTURE_SERVICE_DESCRIPTOR, oldInfrastructureServiceDescriptor, infrastructureServiceDescriptor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__SLA:
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
			case SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__SLA:
				return getSla();
			case SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__INFRASTRUCTURE_SERVICE_DESCRIPTOR:
				if (resolve) return getInfrastructureServiceDescriptor();
				return basicGetInfrastructureServiceDescriptor();
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
			case SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__SLA:
				setSla((Sla)newValue);
				return;
			case SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__INFRASTRUCTURE_SERVICE_DESCRIPTOR:
				setInfrastructureServiceDescriptor((ComputingInfrastructureServiceDescriptor)newValue);
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
			case SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__SLA:
				setSla((Sla)null);
				return;
			case SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__INFRASTRUCTURE_SERVICE_DESCRIPTOR:
				setInfrastructureServiceDescriptor((ComputingInfrastructureServiceDescriptor)null);
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
			case SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__SLA:
				return sla != null;
			case SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__INFRASTRUCTURE_SERVICE_DESCRIPTOR:
				return infrastructureServiceDescriptor != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == ProvidedServiceDescriptor.class) {
			switch (derivedFeatureID) {
				case SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__SLA: return SpecificationPackage.PROVIDED_SERVICE_DESCRIPTOR__SLA;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == ProvidedServiceDescriptor.class) {
			switch (baseFeatureID) {
				case SpecificationPackage.PROVIDED_SERVICE_DESCRIPTOR__SLA: return SpecificationPackage.PROVIDED_PLATFORM_SERVICE_DESCRIPTOR__SLA;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //ProvidedPlatformServiceDescriptorImpl
