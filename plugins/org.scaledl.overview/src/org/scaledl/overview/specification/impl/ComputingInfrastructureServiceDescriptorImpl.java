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

import org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.ComputingResourceDescriptor;
import org.scaledl.overview.specification.SpecificationPackage;

import org.scaledl.overview.specification.sla.Sla;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Computing Infrastructure Service Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.impl.ComputingInfrastructureServiceDescriptorImpl#getProviderID <em>Provider ID</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.ComputingInfrastructureServiceDescriptorImpl#getSla <em>Sla</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.ComputingInfrastructureServiceDescriptorImpl#getComputingResourceDescriptors <em>Computing Resource Descriptors</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.ComputingInfrastructureServiceDescriptorImpl#isGeneralPurpose <em>General Purpose</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComputingInfrastructureServiceDescriptorImpl extends EntityImpl implements ComputingInfrastructureServiceDescriptor {
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
	 * The cached value of the '{@link #getComputingResourceDescriptors() <em>Computing Resource Descriptors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComputingResourceDescriptors()
	 * @generated
	 * @ordered
	 */
	protected EList<ComputingResourceDescriptor> computingResourceDescriptors;

	/**
	 * The default value of the '{@link #isGeneralPurpose() <em>General Purpose</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGeneralPurpose()
	 * @generated
	 * @ordered
	 */
	protected static final boolean GENERAL_PURPOSE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isGeneralPurpose() <em>General Purpose</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGeneralPurpose()
	 * @generated
	 * @ordered
	 */
	protected boolean generalPurpose = GENERAL_PURPOSE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComputingInfrastructureServiceDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SpecificationPackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR;
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
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID, oldProviderID, providerID));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA, oldSla, newSla);
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
				msgs = ((InternalEObject)sla).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA, null, msgs);
			if (newSla != null)
				msgs = ((InternalEObject)newSla).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA, null, msgs);
			msgs = basicSetSla(newSla, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA, newSla, newSla));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ComputingResourceDescriptor> getComputingResourceDescriptors() {
		if (computingResourceDescriptors == null) {
			computingResourceDescriptors = new EObjectContainmentEList<ComputingResourceDescriptor>(ComputingResourceDescriptor.class, this, SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__COMPUTING_RESOURCE_DESCRIPTORS);
		}
		return computingResourceDescriptors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isGeneralPurpose() {
		return generalPurpose;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGeneralPurpose(boolean newGeneralPurpose) {
		boolean oldGeneralPurpose = generalPurpose;
		generalPurpose = newGeneralPurpose;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__GENERAL_PURPOSE, oldGeneralPurpose, generalPurpose));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA:
				return basicSetSla(null, msgs);
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__COMPUTING_RESOURCE_DESCRIPTORS:
				return ((InternalEList<?>)getComputingResourceDescriptors()).basicRemove(otherEnd, msgs);
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
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID:
				return getProviderID();
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA:
				return getSla();
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__COMPUTING_RESOURCE_DESCRIPTORS:
				return getComputingResourceDescriptors();
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__GENERAL_PURPOSE:
				return isGeneralPurpose();
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
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID:
				setProviderID((String)newValue);
				return;
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA:
				setSla((Sla)newValue);
				return;
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__COMPUTING_RESOURCE_DESCRIPTORS:
				getComputingResourceDescriptors().clear();
				getComputingResourceDescriptors().addAll((Collection<? extends ComputingResourceDescriptor>)newValue);
				return;
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__GENERAL_PURPOSE:
				setGeneralPurpose((Boolean)newValue);
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
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID:
				setProviderID(PROVIDER_ID_EDEFAULT);
				return;
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA:
				setSla((Sla)null);
				return;
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__COMPUTING_RESOURCE_DESCRIPTORS:
				getComputingResourceDescriptors().clear();
				return;
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__GENERAL_PURPOSE:
				setGeneralPurpose(GENERAL_PURPOSE_EDEFAULT);
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
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__PROVIDER_ID:
				return PROVIDER_ID_EDEFAULT == null ? providerID != null : !PROVIDER_ID_EDEFAULT.equals(providerID);
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__SLA:
				return sla != null;
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__COMPUTING_RESOURCE_DESCRIPTORS:
				return computingResourceDescriptors != null && !computingResourceDescriptors.isEmpty();
			case SpecificationPackage.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__GENERAL_PURPOSE:
				return generalPurpose != GENERAL_PURPOSE_EDEFAULT;
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
		result.append(", generalPurpose: ");
		result.append(generalPurpose);
		result.append(')');
		return result.toString();
	}

} //ComputingInfrastructureServiceDescriptorImpl
