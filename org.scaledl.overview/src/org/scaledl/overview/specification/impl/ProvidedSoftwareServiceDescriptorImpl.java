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
import org.scaledl.overview.application.Operation;
import org.scaledl.overview.specification.ProvidedServiceDescriptor;
import org.scaledl.overview.specification.ProvidedSoftwareServiceDescriptor;
import org.scaledl.overview.specification.SpecificationPackage;
import org.scaledl.overview.specification.sla.Sla;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Provided Software Service Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.impl.ProvidedSoftwareServiceDescriptorImpl#getSla <em>Sla</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.ProvidedSoftwareServiceDescriptorImpl#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProvidedSoftwareServiceDescriptorImpl extends SoftwareServiceDescriptorImpl implements ProvidedSoftwareServiceDescriptor {
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
	 * The cached value of the '{@link #getOperations() <em>Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<Operation> operations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProvidedSoftwareServiceDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SpecificationPackage.Literals.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__SLA, oldSla, newSla);
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
				msgs = ((InternalEObject)sla).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__SLA, null, msgs);
			if (newSla != null)
				msgs = ((InternalEObject)newSla).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__SLA, null, msgs);
			msgs = basicSetSla(newSla, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__SLA, newSla, newSla));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Operation> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentEList<Operation>(Operation.class, this, SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__OPERATIONS);
		}
		return operations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__SLA:
				return basicSetSla(null, msgs);
			case SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__OPERATIONS:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
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
			case SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__SLA:
				return getSla();
			case SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__OPERATIONS:
				return getOperations();
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
			case SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__SLA:
				setSla((Sla)newValue);
				return;
			case SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection<? extends Operation>)newValue);
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
			case SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__SLA:
				setSla((Sla)null);
				return;
			case SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__OPERATIONS:
				getOperations().clear();
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
			case SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__SLA:
				return sla != null;
			case SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__OPERATIONS:
				return operations != null && !operations.isEmpty();
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
				case SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__SLA: return SpecificationPackage.PROVIDED_SERVICE_DESCRIPTOR__SLA;
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
				case SpecificationPackage.PROVIDED_SERVICE_DESCRIPTOR__SLA: return SpecificationPackage.PROVIDED_SOFTWARE_SERVICE_DESCRIPTOR__SLA;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //ProvidedSoftwareServiceDescriptorImpl
