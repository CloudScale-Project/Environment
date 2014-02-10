/**
 */
package eu.cloudscaleproject.env.csm.architecture.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.DeployableRuntimeService;
import eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService;
import eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Deployable Runtime Service</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.DeployableRuntimeServiceImpl#getSoftwareServices <em>Software Services</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DeployableRuntimeServiceImpl extends DeployablePlatformServiceImpl
		implements DeployableRuntimeService {
	/**
	 * The cached value of the '{@link #getSoftwareServices() <em>Software Services</em>}' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getSoftwareServices()
	 * @generated
	 * @ordered
	 */
	protected EList<DeployableSoftwareService> softwareServices;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected DeployableRuntimeServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.DEPLOYABLE_RUNTIME_SERVICE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DeployableSoftwareService> getSoftwareServices() {
		if (softwareServices == null) {
			softwareServices = new EObjectWithInverseResolvingEList<DeployableSoftwareService>(DeployableSoftwareService.class, this, ArchitecturePackage.DEPLOYABLE_RUNTIME_SERVICE__SOFTWARE_SERVICES, ArchitecturePackage.DEPLOYABLE_SOFTWARE_SERVICE__RUNTIME_PLATFORM_SERVICE);
		}
		return softwareServices;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ArchitecturePackage.DEPLOYABLE_RUNTIME_SERVICE__SOFTWARE_SERVICES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSoftwareServices()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ArchitecturePackage.DEPLOYABLE_RUNTIME_SERVICE__SOFTWARE_SERVICES:
				return ((InternalEList<?>)getSoftwareServices()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ArchitecturePackage.DEPLOYABLE_RUNTIME_SERVICE__SOFTWARE_SERVICES:
				return getSoftwareServices();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ArchitecturePackage.DEPLOYABLE_RUNTIME_SERVICE__SOFTWARE_SERVICES:
				getSoftwareServices().clear();
				getSoftwareServices().addAll((Collection<? extends DeployableSoftwareService>)newValue);
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
			case ArchitecturePackage.DEPLOYABLE_RUNTIME_SERVICE__SOFTWARE_SERVICES:
				getSoftwareServices().clear();
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
			case ArchitecturePackage.DEPLOYABLE_RUNTIME_SERVICE__SOFTWARE_SERVICES:
				return softwareServices != null && !softwareServices.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == SoftwareServiceContainer.class) {
			switch (derivedFeatureID) {
				case ArchitecturePackage.DEPLOYABLE_RUNTIME_SERVICE__SOFTWARE_SERVICES: return ArchitecturePackage.SOFTWARE_SERVICE_CONTAINER__SOFTWARE_SERVICES;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == SoftwareServiceContainer.class) {
			switch (baseFeatureID) {
				case ArchitecturePackage.SOFTWARE_SERVICE_CONTAINER__SOFTWARE_SERVICES: return ArchitecturePackage.DEPLOYABLE_RUNTIME_SERVICE__SOFTWARE_SERVICES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} // DeployableRuntimeServiceImpl
