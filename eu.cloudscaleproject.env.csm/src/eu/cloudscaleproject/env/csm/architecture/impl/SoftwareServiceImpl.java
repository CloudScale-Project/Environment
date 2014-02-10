/**
 */
package eu.cloudscaleproject.env.csm.architecture.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;
import eu.cloudscaleproject.env.csm.architecture.SoftwareLayer;
import eu.cloudscaleproject.env.csm.architecture.SoftwareService;
import eu.cloudscaleproject.env.csm.core.CorePackage;
import eu.cloudscaleproject.env.csm.core.OperationInterface;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Software Service</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.SoftwareServiceImpl#getProvidedInterfaces <em>Provided Interfaces</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.SoftwareServiceImpl#getRequiredInterfaces <em>Required Interfaces</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.SoftwareServiceImpl#getSoftwareLayer <em>Software Layer</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class SoftwareServiceImpl extends ServiceImpl implements
		SoftwareService {
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
	protected SoftwareServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.SOFTWARE_SERVICE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OperationInterface> getProvidedInterfaces() {
		if (providedInterfaces == null) {
			providedInterfaces = new EObjectContainmentWithInverseEList<OperationInterface>(OperationInterface.class, this, ArchitecturePackage.SOFTWARE_SERVICE__PROVIDED_INTERFACES, CorePackage.OPERATION_INTERFACE__PROVIDING_CONTAINER);
		}
		return providedInterfaces;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OperationInterface> getRequiredInterfaces() {
		if (requiredInterfaces == null) {
			requiredInterfaces = new EObjectWithInverseResolvingEList.ManyInverse<OperationInterface>(OperationInterface.class, this, ArchitecturePackage.SOFTWARE_SERVICE__REQUIRED_INTERFACES, CorePackage.OPERATION_INTERFACE__REQUIRING_CONTAINER);
		}
		return requiredInterfaces;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareLayer getSoftwareLayer() {
		if (eContainerFeatureID() != ArchitecturePackage.SOFTWARE_SERVICE__SOFTWARE_LAYER) return null;
		return (SoftwareLayer)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSoftwareLayer(
			SoftwareLayer newSoftwareLayer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newSoftwareLayer, ArchitecturePackage.SOFTWARE_SERVICE__SOFTWARE_LAYER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSoftwareLayer(SoftwareLayer newSoftwareLayer) {
		if (newSoftwareLayer != eInternalContainer() || (eContainerFeatureID() != ArchitecturePackage.SOFTWARE_SERVICE__SOFTWARE_LAYER && newSoftwareLayer != null)) {
			if (EcoreUtil.isAncestor(this, newSoftwareLayer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSoftwareLayer != null)
				msgs = ((InternalEObject)newSoftwareLayer).eInverseAdd(this, ArchitecturePackage.SOFTWARE_LAYER__SERVICES, SoftwareLayer.class, msgs);
			msgs = basicSetSoftwareLayer(newSoftwareLayer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.SOFTWARE_SERVICE__SOFTWARE_LAYER, newSoftwareLayer, newSoftwareLayer));
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
			case ArchitecturePackage.SOFTWARE_SERVICE__PROVIDED_INTERFACES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getProvidedInterfaces()).basicAdd(otherEnd, msgs);
			case ArchitecturePackage.SOFTWARE_SERVICE__REQUIRED_INTERFACES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRequiredInterfaces()).basicAdd(otherEnd, msgs);
			case ArchitecturePackage.SOFTWARE_SERVICE__SOFTWARE_LAYER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSoftwareLayer((SoftwareLayer)otherEnd, msgs);
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
			case ArchitecturePackage.SOFTWARE_SERVICE__PROVIDED_INTERFACES:
				return ((InternalEList<?>)getProvidedInterfaces()).basicRemove(otherEnd, msgs);
			case ArchitecturePackage.SOFTWARE_SERVICE__REQUIRED_INTERFACES:
				return ((InternalEList<?>)getRequiredInterfaces()).basicRemove(otherEnd, msgs);
			case ArchitecturePackage.SOFTWARE_SERVICE__SOFTWARE_LAYER:
				return basicSetSoftwareLayer(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ArchitecturePackage.SOFTWARE_SERVICE__SOFTWARE_LAYER:
				return eInternalContainer().eInverseRemove(this, ArchitecturePackage.SOFTWARE_LAYER__SERVICES, SoftwareLayer.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ArchitecturePackage.SOFTWARE_SERVICE__PROVIDED_INTERFACES:
				return getProvidedInterfaces();
			case ArchitecturePackage.SOFTWARE_SERVICE__REQUIRED_INTERFACES:
				return getRequiredInterfaces();
			case ArchitecturePackage.SOFTWARE_SERVICE__SOFTWARE_LAYER:
				return getSoftwareLayer();
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
			case ArchitecturePackage.SOFTWARE_SERVICE__PROVIDED_INTERFACES:
				getProvidedInterfaces().clear();
				getProvidedInterfaces().addAll((Collection<? extends OperationInterface>)newValue);
				return;
			case ArchitecturePackage.SOFTWARE_SERVICE__REQUIRED_INTERFACES:
				getRequiredInterfaces().clear();
				getRequiredInterfaces().addAll((Collection<? extends OperationInterface>)newValue);
				return;
			case ArchitecturePackage.SOFTWARE_SERVICE__SOFTWARE_LAYER:
				setSoftwareLayer((SoftwareLayer)newValue);
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
			case ArchitecturePackage.SOFTWARE_SERVICE__PROVIDED_INTERFACES:
				getProvidedInterfaces().clear();
				return;
			case ArchitecturePackage.SOFTWARE_SERVICE__REQUIRED_INTERFACES:
				getRequiredInterfaces().clear();
				return;
			case ArchitecturePackage.SOFTWARE_SERVICE__SOFTWARE_LAYER:
				setSoftwareLayer((SoftwareLayer)null);
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
			case ArchitecturePackage.SOFTWARE_SERVICE__PROVIDED_INTERFACES:
				return providedInterfaces != null && !providedInterfaces.isEmpty();
			case ArchitecturePackage.SOFTWARE_SERVICE__REQUIRED_INTERFACES:
				return requiredInterfaces != null && !requiredInterfaces.isEmpty();
			case ArchitecturePackage.SOFTWARE_SERVICE__SOFTWARE_LAYER:
				return getSoftwareLayer() != null;
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
				case ArchitecturePackage.SOFTWARE_SERVICE__PROVIDED_INTERFACES: return ArchitecturePackage.OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES;
				case ArchitecturePackage.SOFTWARE_SERVICE__REQUIRED_INTERFACES: return ArchitecturePackage.OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES;
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
				case ArchitecturePackage.OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES: return ArchitecturePackage.SOFTWARE_SERVICE__PROVIDED_INTERFACES;
				case ArchitecturePackage.OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES: return ArchitecturePackage.SOFTWARE_SERVICE__REQUIRED_INTERFACES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} // SoftwareServiceImpl
