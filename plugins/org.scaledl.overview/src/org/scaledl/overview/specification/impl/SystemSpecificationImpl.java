/**
 */
package org.scaledl.overview.specification.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.scaledl.overview.specification.Descriptor;
import org.scaledl.overview.specification.SpecificationPackage;
import org.scaledl.overview.specification.SystemSpecification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.impl.SystemSpecificationImpl#getDescriptors <em>Descriptors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SystemSpecificationImpl extends SpecificationImpl implements SystemSpecification {
	/**
	 * The cached value of the '{@link #getDescriptors() <em>Descriptors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptors()
	 * @generated
	 * @ordered
	 */
	protected EList<Descriptor> descriptors;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemSpecificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SpecificationPackage.Literals.SYSTEM_SPECIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Descriptor> getDescriptors() {
		if (descriptors == null) {
			descriptors = new EObjectContainmentEList<Descriptor>(Descriptor.class, this, SpecificationPackage.SYSTEM_SPECIFICATION__DESCRIPTORS);
		}
		return descriptors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SpecificationPackage.SYSTEM_SPECIFICATION__DESCRIPTORS:
				return ((InternalEList<?>)getDescriptors()).basicRemove(otherEnd, msgs);
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
			case SpecificationPackage.SYSTEM_SPECIFICATION__DESCRIPTORS:
				return getDescriptors();
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
			case SpecificationPackage.SYSTEM_SPECIFICATION__DESCRIPTORS:
				getDescriptors().clear();
				getDescriptors().addAll((Collection<? extends Descriptor>)newValue);
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
			case SpecificationPackage.SYSTEM_SPECIFICATION__DESCRIPTORS:
				getDescriptors().clear();
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
			case SpecificationPackage.SYSTEM_SPECIFICATION__DESCRIPTORS:
				return descriptors != null && !descriptors.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SystemSpecificationImpl
