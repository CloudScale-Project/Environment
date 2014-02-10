/**
 */
package eu.cloudscaleproject.env.csm.architecture.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.ExternalPlatformService;
import eu.cloudscaleproject.env.csm.definition.ExternalPlatformServiceDescriptor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>External Platform Service</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.ExternalPlatformServiceImpl#getDescriptor <em>Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ExternalPlatformServiceImpl extends PlatformServiceImpl
		implements ExternalPlatformService {
	/**
	 * The cached value of the '{@link #getDescriptor() <em>Descriptor</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDescriptor()
	 * @generated
	 * @ordered
	 */
	protected ExternalPlatformServiceDescriptor descriptor;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ExternalPlatformServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.EXTERNAL_PLATFORM_SERVICE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalPlatformServiceDescriptor getDescriptor() {
		if (descriptor != null && descriptor.eIsProxy()) {
			InternalEObject oldDescriptor = (InternalEObject)descriptor;
			descriptor = (ExternalPlatformServiceDescriptor)eResolveProxy(oldDescriptor);
			if (descriptor != oldDescriptor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.EXTERNAL_PLATFORM_SERVICE__DESCRIPTOR, oldDescriptor, descriptor));
			}
		}
		return descriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalPlatformServiceDescriptor basicGetDescriptor() {
		return descriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescriptor(ExternalPlatformServiceDescriptor newDescriptor) {
		ExternalPlatformServiceDescriptor oldDescriptor = descriptor;
		descriptor = newDescriptor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.EXTERNAL_PLATFORM_SERVICE__DESCRIPTOR, oldDescriptor, descriptor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ArchitecturePackage.EXTERNAL_PLATFORM_SERVICE__DESCRIPTOR:
				if (resolve) return getDescriptor();
				return basicGetDescriptor();
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
			case ArchitecturePackage.EXTERNAL_PLATFORM_SERVICE__DESCRIPTOR:
				setDescriptor((ExternalPlatformServiceDescriptor)newValue);
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
			case ArchitecturePackage.EXTERNAL_PLATFORM_SERVICE__DESCRIPTOR:
				setDescriptor((ExternalPlatformServiceDescriptor)null);
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
			case ArchitecturePackage.EXTERNAL_PLATFORM_SERVICE__DESCRIPTOR:
				return descriptor != null;
		}
		return super.eIsSet(featureID);
	}

} // ExternalPlatformServiceImpl
