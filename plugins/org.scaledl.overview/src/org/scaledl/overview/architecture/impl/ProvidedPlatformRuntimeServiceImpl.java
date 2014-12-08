/**
 */
package org.scaledl.overview.architecture.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.scaledl.overview.architecture.ArchitecturePackage;
import org.scaledl.overview.architecture.ProvidedPlatformRuntimeService;
import org.scaledl.overview.architecture.ProvidedService;

import org.scaledl.overview.deployment.ServiceDeployment;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Provided Platform Runtime Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.architecture.impl.ProvidedPlatformRuntimeServiceImpl#getDeployment <em>Deployment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProvidedPlatformRuntimeServiceImpl extends PlatformRuntimeServiceImpl implements ProvidedPlatformRuntimeService {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProvidedPlatformRuntimeServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.PROVIDED_PLATFORM_RUNTIME_SERVICE;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.PROVIDED_PLATFORM_RUNTIME_SERVICE__DEPLOYMENT, oldDeployment, deployment));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.PROVIDED_PLATFORM_RUNTIME_SERVICE__DEPLOYMENT, oldDeployment, deployment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ArchitecturePackage.PROVIDED_PLATFORM_RUNTIME_SERVICE__DEPLOYMENT:
				if (resolve) return getDeployment();
				return basicGetDeployment();
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
			case ArchitecturePackage.PROVIDED_PLATFORM_RUNTIME_SERVICE__DEPLOYMENT:
				setDeployment((ServiceDeployment)newValue);
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
			case ArchitecturePackage.PROVIDED_PLATFORM_RUNTIME_SERVICE__DEPLOYMENT:
				setDeployment((ServiceDeployment)null);
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
			case ArchitecturePackage.PROVIDED_PLATFORM_RUNTIME_SERVICE__DEPLOYMENT:
				return deployment != null;
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
				case ArchitecturePackage.PROVIDED_PLATFORM_RUNTIME_SERVICE__DEPLOYMENT: return ArchitecturePackage.PROVIDED_SERVICE__DEPLOYMENT;
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
				case ArchitecturePackage.PROVIDED_SERVICE__DEPLOYMENT: return ArchitecturePackage.PROVIDED_PLATFORM_RUNTIME_SERVICE__DEPLOYMENT;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //ProvidedPlatformRuntimeServiceImpl
