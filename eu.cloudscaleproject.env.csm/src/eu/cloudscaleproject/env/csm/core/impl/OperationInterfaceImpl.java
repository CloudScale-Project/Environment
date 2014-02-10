/**
 */
package eu.cloudscaleproject.env.csm.core.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;
import eu.cloudscaleproject.env.csm.core.CorePackage;
import eu.cloudscaleproject.env.csm.core.Operation;
import eu.cloudscaleproject.env.csm.core.OperationInterface;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Operation Interface</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.impl.OperationInterfaceImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.impl.OperationInterfaceImpl#getProvidingContainer <em>Providing Container</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.core.impl.OperationInterfaceImpl#getRequiringContainer <em>Requiring Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationInterfaceImpl extends EntityImpl implements
		OperationInterface {
	/**
	 * The cached value of the '{@link #getOperations() <em>Operations</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<Operation> operations;

	/**
	 * The cached value of the '{@link #getRequiringContainer() <em>Requiring Container</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiringContainer()
	 * @generated
	 * @ordered
	 */
	protected EList<OperationInterfaceContainer> requiringContainer;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationInterfaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.OPERATION_INTERFACE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Operation> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentEList<Operation>(Operation.class, this, CorePackage.OPERATION_INTERFACE__OPERATIONS);
		}
		return operations;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OperationInterfaceContainer getProvidingContainer() {
		if (eContainerFeatureID() != CorePackage.OPERATION_INTERFACE__PROVIDING_CONTAINER) return null;
		return (OperationInterfaceContainer)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProvidingContainer(
			OperationInterfaceContainer newProvidingContainer,
			NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newProvidingContainer, CorePackage.OPERATION_INTERFACE__PROVIDING_CONTAINER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setProvidingContainer(
			OperationInterfaceContainer newProvidingContainer) {
		if (newProvidingContainer != eInternalContainer() || (eContainerFeatureID() != CorePackage.OPERATION_INTERFACE__PROVIDING_CONTAINER && newProvidingContainer != null)) {
			if (EcoreUtil.isAncestor(this, newProvidingContainer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newProvidingContainer != null)
				msgs = ((InternalEObject)newProvidingContainer).eInverseAdd(this, ArchitecturePackage.OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES, OperationInterfaceContainer.class, msgs);
			msgs = basicSetProvidingContainer(newProvidingContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.OPERATION_INTERFACE__PROVIDING_CONTAINER, newProvidingContainer, newProvidingContainer));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OperationInterfaceContainer> getRequiringContainer() {
		if (requiringContainer == null) {
			requiringContainer = new EObjectWithInverseResolvingEList.ManyInverse<OperationInterfaceContainer>(OperationInterfaceContainer.class, this, CorePackage.OPERATION_INTERFACE__REQUIRING_CONTAINER, ArchitecturePackage.OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES);
		}
		return requiringContainer;
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
			case CorePackage.OPERATION_INTERFACE__PROVIDING_CONTAINER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetProvidingContainer((OperationInterfaceContainer)otherEnd, msgs);
			case CorePackage.OPERATION_INTERFACE__REQUIRING_CONTAINER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRequiringContainer()).basicAdd(otherEnd, msgs);
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
			case CorePackage.OPERATION_INTERFACE__OPERATIONS:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
			case CorePackage.OPERATION_INTERFACE__PROVIDING_CONTAINER:
				return basicSetProvidingContainer(null, msgs);
			case CorePackage.OPERATION_INTERFACE__REQUIRING_CONTAINER:
				return ((InternalEList<?>)getRequiringContainer()).basicRemove(otherEnd, msgs);
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
			case CorePackage.OPERATION_INTERFACE__PROVIDING_CONTAINER:
				return eInternalContainer().eInverseRemove(this, ArchitecturePackage.OPERATION_INTERFACE_CONTAINER__PROVIDED_INTERFACES, OperationInterfaceContainer.class, msgs);
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
			case CorePackage.OPERATION_INTERFACE__OPERATIONS:
				return getOperations();
			case CorePackage.OPERATION_INTERFACE__PROVIDING_CONTAINER:
				return getProvidingContainer();
			case CorePackage.OPERATION_INTERFACE__REQUIRING_CONTAINER:
				return getRequiringContainer();
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
			case CorePackage.OPERATION_INTERFACE__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case CorePackage.OPERATION_INTERFACE__PROVIDING_CONTAINER:
				setProvidingContainer((OperationInterfaceContainer)newValue);
				return;
			case CorePackage.OPERATION_INTERFACE__REQUIRING_CONTAINER:
				getRequiringContainer().clear();
				getRequiringContainer().addAll((Collection<? extends OperationInterfaceContainer>)newValue);
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
			case CorePackage.OPERATION_INTERFACE__OPERATIONS:
				getOperations().clear();
				return;
			case CorePackage.OPERATION_INTERFACE__PROVIDING_CONTAINER:
				setProvidingContainer((OperationInterfaceContainer)null);
				return;
			case CorePackage.OPERATION_INTERFACE__REQUIRING_CONTAINER:
				getRequiringContainer().clear();
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
			case CorePackage.OPERATION_INTERFACE__OPERATIONS:
				return operations != null && !operations.isEmpty();
			case CorePackage.OPERATION_INTERFACE__PROVIDING_CONTAINER:
				return getProvidingContainer() != null;
			case CorePackage.OPERATION_INTERFACE__REQUIRING_CONTAINER:
				return requiringContainer != null && !requiringContainer.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // OperationInterfaceImpl
