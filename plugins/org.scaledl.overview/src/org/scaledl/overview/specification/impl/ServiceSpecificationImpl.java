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

import org.scaledl.overview.specification.ServiceDescriptor;
import org.scaledl.overview.specification.ServiceSpecification;
import org.scaledl.overview.specification.SpecificationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.impl.ServiceSpecificationImpl#getServiceDescriptors <em>Service Descriptors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceSpecificationImpl extends SpecificationImpl implements ServiceSpecification {
	/**
	 * The cached value of the '{@link #getServiceDescriptors() <em>Service Descriptors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceDescriptors()
	 * @generated
	 * @ordered
	 */
	protected EList<ServiceDescriptor> serviceDescriptors;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceSpecificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SpecificationPackage.Literals.SERVICE_SPECIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ServiceDescriptor> getServiceDescriptors() {
		if (serviceDescriptors == null) {
			serviceDescriptors = new EObjectContainmentEList<ServiceDescriptor>(ServiceDescriptor.class, this, SpecificationPackage.SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS);
		}
		return serviceDescriptors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SpecificationPackage.SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS:
				return ((InternalEList<?>)getServiceDescriptors()).basicRemove(otherEnd, msgs);
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
			case SpecificationPackage.SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS:
				return getServiceDescriptors();
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
			case SpecificationPackage.SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS:
				getServiceDescriptors().clear();
				getServiceDescriptors().addAll((Collection<? extends ServiceDescriptor>)newValue);
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
			case SpecificationPackage.SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS:
				getServiceDescriptors().clear();
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
			case SpecificationPackage.SERVICE_SPECIFICATION__SERVICE_DESCRIPTORS:
				return serviceDescriptors != null && !serviceDescriptors.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ServiceSpecificationImpl
