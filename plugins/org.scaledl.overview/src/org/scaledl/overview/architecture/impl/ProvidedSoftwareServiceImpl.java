/**
 */
package org.scaledl.overview.architecture.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.scaledl.overview.architecture.ArchitecturePackage;
import org.scaledl.overview.architecture.ProvidedService;
import org.scaledl.overview.architecture.ProvidedSoftwareService;
import org.scaledl.overview.deployment.ServiceDeployment;
import org.scaledl.overview.specification.ProvidedSoftwareServiceDescriptor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Provided Software Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.architecture.impl.ProvidedSoftwareServiceImpl#getDeployment <em>Deployment</em>}</li>
 *   <li>{@link org.scaledl.overview.architecture.impl.ProvidedSoftwareServiceImpl#getDescriptor <em>Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProvidedSoftwareServiceImpl extends SoftwareServiceImpl implements ProvidedSoftwareService {
	/**
	 * The cached value of the '{@link #getDeployment() <em>Deployment</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeployment()
	 * @generated
	 * @ordered
	 */
	protected ServiceDeployment deployment;

	/**
	 * The cached value of the '{@link #getDescriptor() <em>Descriptor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptor()
	 * @generated
	 * @ordered
	 */
	protected ProvidedSoftwareServiceDescriptor descriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProvidedSoftwareServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.PROVIDED_SOFTWARE_SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceDeployment getDeployment() {
		if (deployment != null && deployment.eIsProxy()) {
			InternalEObject oldDeployment = (InternalEObject)deployment;
			deployment = (ServiceDeployment)eResolveProxy(oldDeployment);
			if (deployment != oldDeployment) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.PROVIDED_SOFTWARE_SERVICE__DEPLOYMENT, oldDeployment, deployment));
			}
		}
		return deployment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceDeployment basicGetDeployment() {
		return deployment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeployment(ServiceDeployment newDeployment) {
		ServiceDeployment oldDeployment = deployment;
		deployment = newDeployment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.PROVIDED_SOFTWARE_SERVICE__DEPLOYMENT, oldDeployment, deployment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvidedSoftwareServiceDescriptor getDescriptor() {
		if (descriptor != null && descriptor.eIsProxy()) {
			InternalEObject oldDescriptor = (InternalEObject)descriptor;
			descriptor = (ProvidedSoftwareServiceDescriptor)eResolveProxy(oldDescriptor);
			if (descriptor != oldDescriptor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.PROVIDED_SOFTWARE_SERVICE__DESCRIPTOR, oldDescriptor, descriptor));
			}
		}
		return descriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvidedSoftwareServiceDescriptor basicGetDescriptor() {
		return descriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescriptor(ProvidedSoftwareServiceDescriptor newDescriptor) {
		ProvidedSoftwareServiceDescriptor oldDescriptor = descriptor;
		descriptor = newDescriptor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.PROVIDED_SOFTWARE_SERVICE__DESCRIPTOR, oldDescriptor, descriptor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ArchitecturePackage.PROVIDED_SOFTWARE_SERVICE__DEPLOYMENT:
				if (resolve) return getDeployment();
				return basicGetDeployment();
			case ArchitecturePackage.PROVIDED_SOFTWARE_SERVICE__DESCRIPTOR:
				if (resolve) return getDescriptor();
				return basicGetDescriptor();
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
			case ArchitecturePackage.PROVIDED_SOFTWARE_SERVICE__DEPLOYMENT:
				setDeployment((ServiceDeployment)newValue);
				return;
			case ArchitecturePackage.PROVIDED_SOFTWARE_SERVICE__DESCRIPTOR:
				setDescriptor((ProvidedSoftwareServiceDescriptor)newValue);
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
			case ArchitecturePackage.PROVIDED_SOFTWARE_SERVICE__DEPLOYMENT:
				setDeployment((ServiceDeployment)null);
				return;
			case ArchitecturePackage.PROVIDED_SOFTWARE_SERVICE__DESCRIPTOR:
				setDescriptor((ProvidedSoftwareServiceDescriptor)null);
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
			case ArchitecturePackage.PROVIDED_SOFTWARE_SERVICE__DEPLOYMENT:
				return deployment != null;
			case ArchitecturePackage.PROVIDED_SOFTWARE_SERVICE__DESCRIPTOR:
				return descriptor != null;
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
		if (baseClass == ProvidedService.class) {
			switch (derivedFeatureID) {
				case ArchitecturePackage.PROVIDED_SOFTWARE_SERVICE__DEPLOYMENT: return ArchitecturePackage.PROVIDED_SERVICE__DEPLOYMENT;
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
		if (baseClass == ProvidedService.class) {
			switch (baseFeatureID) {
				case ArchitecturePackage.PROVIDED_SERVICE__DEPLOYMENT: return ArchitecturePackage.PROVIDED_SOFTWARE_SERVICE__DEPLOYMENT;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //ProvidedSoftwareServiceImpl
