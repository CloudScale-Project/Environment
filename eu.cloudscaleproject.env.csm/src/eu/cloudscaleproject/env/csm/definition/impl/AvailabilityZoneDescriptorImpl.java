/**
 */
package eu.cloudscaleproject.env.csm.definition.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.cloudscaleproject.env.csm.definition.AvailabilityZoneDescriptor;
import eu.cloudscaleproject.env.csm.definition.DefinitionPackage;
import eu.cloudscaleproject.env.csm.definition.NetworkInfrastructureServiceDescriptor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Availability Zone Descriptor</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.impl.AvailabilityZoneDescriptorImpl#getInternalConnectionDescriptor <em>Internal Connection Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AvailabilityZoneDescriptorImpl extends DescriptorImpl implements
		AvailabilityZoneDescriptor {
	/**
	 * The cached value of the '{@link #getInternalConnectionDescriptor()
	 * <em>Internal Connection Descriptor</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getInternalConnectionDescriptor()
	 * @generated
	 * @ordered
	 */
	protected NetworkInfrastructureServiceDescriptor internalConnectionDescriptor;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected AvailabilityZoneDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DefinitionPackage.Literals.AVAILABILITY_ZONE_DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkInfrastructureServiceDescriptor getInternalConnectionDescriptor() {
		return internalConnectionDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInternalConnectionDescriptor(
			NetworkInfrastructureServiceDescriptor newInternalConnectionDescriptor,
			NotificationChain msgs) {
		NetworkInfrastructureServiceDescriptor oldInternalConnectionDescriptor = internalConnectionDescriptor;
		internalConnectionDescriptor = newInternalConnectionDescriptor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DefinitionPackage.AVAILABILITY_ZONE_DESCRIPTOR__INTERNAL_CONNECTION_DESCRIPTOR, oldInternalConnectionDescriptor, newInternalConnectionDescriptor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setInternalConnectionDescriptor(
			NetworkInfrastructureServiceDescriptor newInternalConnectionDescriptor) {
		if (newInternalConnectionDescriptor != internalConnectionDescriptor) {
			NotificationChain msgs = null;
			if (internalConnectionDescriptor != null)
				msgs = ((InternalEObject)internalConnectionDescriptor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DefinitionPackage.AVAILABILITY_ZONE_DESCRIPTOR__INTERNAL_CONNECTION_DESCRIPTOR, null, msgs);
			if (newInternalConnectionDescriptor != null)
				msgs = ((InternalEObject)newInternalConnectionDescriptor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DefinitionPackage.AVAILABILITY_ZONE_DESCRIPTOR__INTERNAL_CONNECTION_DESCRIPTOR, null, msgs);
			msgs = basicSetInternalConnectionDescriptor(newInternalConnectionDescriptor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DefinitionPackage.AVAILABILITY_ZONE_DESCRIPTOR__INTERNAL_CONNECTION_DESCRIPTOR, newInternalConnectionDescriptor, newInternalConnectionDescriptor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DefinitionPackage.AVAILABILITY_ZONE_DESCRIPTOR__INTERNAL_CONNECTION_DESCRIPTOR:
				return basicSetInternalConnectionDescriptor(null, msgs);
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
			case DefinitionPackage.AVAILABILITY_ZONE_DESCRIPTOR__INTERNAL_CONNECTION_DESCRIPTOR:
				return getInternalConnectionDescriptor();
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
			case DefinitionPackage.AVAILABILITY_ZONE_DESCRIPTOR__INTERNAL_CONNECTION_DESCRIPTOR:
				setInternalConnectionDescriptor((NetworkInfrastructureServiceDescriptor)newValue);
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
			case DefinitionPackage.AVAILABILITY_ZONE_DESCRIPTOR__INTERNAL_CONNECTION_DESCRIPTOR:
				setInternalConnectionDescriptor((NetworkInfrastructureServiceDescriptor)null);
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
			case DefinitionPackage.AVAILABILITY_ZONE_DESCRIPTOR__INTERNAL_CONNECTION_DESCRIPTOR:
				return internalConnectionDescriptor != null;
		}
		return super.eIsSet(featureID);
	}

} // AvailabilityZoneDescriptorImpl
