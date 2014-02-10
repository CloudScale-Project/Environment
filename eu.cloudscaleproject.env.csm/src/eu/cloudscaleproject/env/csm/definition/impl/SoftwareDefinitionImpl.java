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
import eu.cloudscaleproject.env.csm.definition.DeployablePlatformServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.SoftwareDefinition;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Software Definition</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.impl.SoftwareDefinitionImpl#getDeployablePlatformServiceDescriptors <em>Deployable Platform Service Descriptors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SoftwareDefinitionImpl extends DefinitionImpl implements
		SoftwareDefinition {
	/**
	 * The cached value of the '{@link #getDeployablePlatformServiceDescriptors() <em>Deployable Platform Service Descriptors</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDeployablePlatformServiceDescriptors()
	 * @generated
	 * @ordered
	 */
	protected EList<DeployablePlatformServiceDescriptor> deployablePlatformServiceDescriptors;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected SoftwareDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DefinitionPackage.Literals.SOFTWARE_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DeployablePlatformServiceDescriptor> getDeployablePlatformServiceDescriptors() {
		if (deployablePlatformServiceDescriptors == null) {
			deployablePlatformServiceDescriptors = new EObjectContainmentEList<DeployablePlatformServiceDescriptor>(DeployablePlatformServiceDescriptor.class, this, DefinitionPackage.SOFTWARE_DEFINITION__DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTORS);
		}
		return deployablePlatformServiceDescriptors;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DefinitionPackage.SOFTWARE_DEFINITION__DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTORS:
				return ((InternalEList<?>)getDeployablePlatformServiceDescriptors()).basicRemove(otherEnd, msgs);
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
			case DefinitionPackage.SOFTWARE_DEFINITION__DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTORS:
				return getDeployablePlatformServiceDescriptors();
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
			case DefinitionPackage.SOFTWARE_DEFINITION__DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTORS:
				getDeployablePlatformServiceDescriptors().clear();
				getDeployablePlatformServiceDescriptors().addAll((Collection<? extends DeployablePlatformServiceDescriptor>)newValue);
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
			case DefinitionPackage.SOFTWARE_DEFINITION__DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTORS:
				getDeployablePlatformServiceDescriptors().clear();
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
			case DefinitionPackage.SOFTWARE_DEFINITION__DEPLOYABLE_PLATFORM_SERVICE_DESCRIPTORS:
				return deployablePlatformServiceDescriptors != null && !deployablePlatformServiceDescriptors.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // SoftwareDefinitionImpl
