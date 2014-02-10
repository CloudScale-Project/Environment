/**
 */
package eu.cloudscaleproject.env.csm.architecture.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.ExternalSupportService;
import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;
import eu.cloudscaleproject.env.csm.core.CorePackage;
import eu.cloudscaleproject.env.csm.core.OperationInterface;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>External Support Service</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.ExternalSupportServiceImpl#getProvidedInterfaces <em>Provided Interfaces</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.ExternalSupportServiceImpl#getRequiredInterfaces <em>Required Interfaces</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExternalSupportServiceImpl extends ExternalPlatformServiceImpl
		implements ExternalSupportService {
	/**
	 * The cached value of the '{@link #getProvidedInterfaces()
	 * <em>Provided Interfaces</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProvidedInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList<OperationInterface> providedInterfaces;

	/**
	 * The cached value of the '{@link #getRequiredInterfaces() <em>Required Interfaces</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList<OperationInterface> requiredInterfaces;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ExternalSupportServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.EXTERNAL_SUPPORT_SERVICE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OperationInterface> getProvidedInterfaces() {
		if (providedInterfaces == null) {
			providedInterfaces = new EObjectContainmentWithInverseEList<OperationInterface>(OperationInterface.class, this, ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE__PROVIDED_INTERFACES, CorePackage.OPERATION_INTERFACE__PROVIDING_CONTAINER);
		}
		return providedInterfaces;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OperationInterface> getRequiredInterfaces() {
		if (requiredInterfaces == null) {
			requiredInterfaces = new EObjectWithInverseResolvingEList.ManyInverse<OperationInterface>(OperationInterface.class, this, ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE__REQUIRED_INTERFACES, CorePackage.OPERATION_INTERFACE__REQUIRING_CONTAINER);
		}
		return requiredInterfaces;
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
			case ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE__PROVIDED_INTERFACES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getProvidedInterfaces()).basicAdd(otherEnd, msgs);
			case ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE__REQUIRED_INTERFACES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRequiredInterfaces()).basicAdd(otherEnd, msgs);
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
			case ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE__PROVIDED_INTERFACES:
				return ((InternalEList<?>)getProvidedInterfaces()).basicRemove(otherEnd, msgs);
			case ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE__REQUIRED_INTERFACES:
				return ((InternalEList<?>)getRequiredInterfaces()).basicRemove(otherEnd, msgs);
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
			case ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE__PROVIDED_INTERFACES:
				return getProvidedInterfaces();
			case ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE__REQUIRED_INTERFACES:
				return getRequiredInterfaces();
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
			case ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE__PROVIDED_INTERFACES:
				getProvidedInterfaces().clear();
				getProvidedInterfaces().addAll((Collection<? extends OperationInterface>)newValue);
				return;
			case ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE__REQUIRED_INTERFACES:
				getRequiredInterfaces().clear();
				getRequiredInterfaces().addAll((Collection<? extends OperationInterface>)newValue);
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
			case ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE__PROVIDED_INTERFACES:
				getProvidedInterfaces().clear();
				return;
			case ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE__REQUIRED_INTERFACES:
				getRequiredInterfaces().clear();
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
			case ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE__PROVIDED_INTERFACES:
				return providedInterfaces != null && !providedInterfaces.isEmpty();
			case ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE__REQUIRED_INTERFACES:
				return requiredInterfaces != null && !requiredInterfaces.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == OperationInterfaceContainer.class) {
			switch (derivedFeatureID) {
				case ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE__PROVIDED_INTERFACES: return ArchitecturePackage.OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES;
				case ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE__REQUIRED_INTERFACES: return ArchitecturePackage.OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES;
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
		if (baseClass == OperationInterfaceContainer.class) {
			switch (baseFeatureID) {
				case ArchitecturePackage.OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES: return ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE__PROVIDED_INTERFACES;
				case ArchitecturePackage.OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES: return ArchitecturePackage.EXTERNAL_SUPPORT_SERVICE__REQUIRED_INTERFACES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} // ExternalSupportServiceImpl
