/**
 */
package org.scaledl.overview.deployment.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.scaledl.overview.core.impl.EntityImpl;
import org.scaledl.overview.deployment.ComputingEnvironment;
import org.scaledl.overview.deployment.DeploymentPackage;
import org.scaledl.overview.specification.ComputingResourceDescriptor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Computing Environment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.deployment.impl.ComputingEnvironmentImpl#getInstanceDescriptor <em>Instance Descriptor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComputingEnvironmentImpl extends EntityImpl implements ComputingEnvironment {
	/**
	 * The cached value of the '{@link #getInstanceDescriptor() <em>Instance Descriptor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceDescriptor()
	 * @generated
	 * @ordered
	 */
	protected ComputingResourceDescriptor instanceDescriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComputingEnvironmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DeploymentPackage.Literals.COMPUTING_ENVIRONMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComputingResourceDescriptor getInstanceDescriptor() {
		if (instanceDescriptor != null && instanceDescriptor.eIsProxy()) {
			InternalEObject oldInstanceDescriptor = (InternalEObject)instanceDescriptor;
			instanceDescriptor = (ComputingResourceDescriptor)eResolveProxy(oldInstanceDescriptor);
			if (instanceDescriptor != oldInstanceDescriptor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.COMPUTING_ENVIRONMENT__INSTANCE_DESCRIPTOR, oldInstanceDescriptor, instanceDescriptor));
			}
		}
		return instanceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComputingResourceDescriptor basicGetInstanceDescriptor() {
		return instanceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstanceDescriptor(ComputingResourceDescriptor newInstanceDescriptor) {
		ComputingResourceDescriptor oldInstanceDescriptor = instanceDescriptor;
		instanceDescriptor = newInstanceDescriptor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.COMPUTING_ENVIRONMENT__INSTANCE_DESCRIPTOR, oldInstanceDescriptor, instanceDescriptor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DeploymentPackage.COMPUTING_ENVIRONMENT__INSTANCE_DESCRIPTOR:
				if (resolve) return getInstanceDescriptor();
				return basicGetInstanceDescriptor();
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
			case DeploymentPackage.COMPUTING_ENVIRONMENT__INSTANCE_DESCRIPTOR:
				setInstanceDescriptor((ComputingResourceDescriptor)newValue);
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
			case DeploymentPackage.COMPUTING_ENVIRONMENT__INSTANCE_DESCRIPTOR:
				setInstanceDescriptor((ComputingResourceDescriptor)null);
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
			case DeploymentPackage.COMPUTING_ENVIRONMENT__INSTANCE_DESCRIPTOR:
				return instanceDescriptor != null;
		}
		return super.eIsSet(featureID);
	}

} //ComputingEnvironmentImpl
