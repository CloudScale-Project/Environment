/**
 */
package eu.cloudscaleproject.env.csm.definition.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.cloudscaleproject.env.csm.definition.DefinitionPackage;
import eu.cloudscaleproject.env.csm.definition.Descriptor;
import eu.cloudscaleproject.env.csm.definition.SystemDefinition;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>System Definition</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.impl.SystemDefinitionImpl#getDescriptors <em>Descriptors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SystemDefinitionImpl extends DefinitionImpl implements
		SystemDefinition {
	/**
	 * The cached value of the '{@link #getDescriptors() <em>Descriptors</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDescriptors()
	 * @generated
	 * @ordered
	 */
	protected EList<Descriptor> descriptors;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DefinitionPackage.Literals.SYSTEM_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Descriptor> getDescriptors() {
		if (descriptors == null) {
			descriptors = new EObjectContainmentEList<Descriptor>(Descriptor.class, this, DefinitionPackage.SYSTEM_DEFINITION__DESCRIPTORS);
		}
		return descriptors;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DefinitionPackage.SYSTEM_DEFINITION__DESCRIPTORS:
				return ((InternalEList<?>)getDescriptors()).basicRemove(otherEnd, msgs);
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
			case DefinitionPackage.SYSTEM_DEFINITION__DESCRIPTORS:
				return getDescriptors();
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
			case DefinitionPackage.SYSTEM_DEFINITION__DESCRIPTORS:
				getDescriptors().clear();
				getDescriptors().addAll((Collection<? extends Descriptor>)newValue);
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
			case DefinitionPackage.SYSTEM_DEFINITION__DESCRIPTORS:
				getDescriptors().clear();
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
			case DefinitionPackage.SYSTEM_DEFINITION__DESCRIPTORS:
				return descriptors != null && !descriptors.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // SystemDefinitionImpl
