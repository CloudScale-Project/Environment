/**
 */
package eu.cloudscaleproject.env.csm.definition.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.cloudscaleproject.env.csm.core.impl.EntityImpl;
import eu.cloudscaleproject.env.csm.definition.DefinitionPackage;
import eu.cloudscaleproject.env.csm.definition.Descriptor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Descriptor</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.impl.DescriptorImpl#getProviderID <em>Provider ID</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DescriptorImpl extends EntityImpl implements Descriptor {
	/**
	 * The default value of the '{@link #getProviderID() <em>Provider ID</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getProviderID()
	 * @generated
	 * @ordered
	 */
	protected static final String PROVIDER_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProviderID() <em>Provider ID</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getProviderID()
	 * @generated
	 * @ordered
	 */
	protected String providerID = PROVIDER_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected DescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DefinitionPackage.Literals.DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getProviderID() {
		return providerID;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setProviderID(String newProviderID) {
		String oldProviderID = providerID;
		providerID = newProviderID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DefinitionPackage.DESCRIPTOR__PROVIDER_ID, oldProviderID, providerID));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DefinitionPackage.DESCRIPTOR__PROVIDER_ID:
				return getProviderID();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DefinitionPackage.DESCRIPTOR__PROVIDER_ID:
				setProviderID((String)newValue);
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
			case DefinitionPackage.DESCRIPTOR__PROVIDER_ID:
				setProviderID(PROVIDER_ID_EDEFAULT);
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
			case DefinitionPackage.DESCRIPTOR__PROVIDER_ID:
				return PROVIDER_ID_EDEFAULT == null ? providerID != null : !PROVIDER_ID_EDEFAULT.equals(providerID);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (providerID: ");
		result.append(providerID);
		result.append(')');
		return result.toString();
	}

} // DescriptorImpl
