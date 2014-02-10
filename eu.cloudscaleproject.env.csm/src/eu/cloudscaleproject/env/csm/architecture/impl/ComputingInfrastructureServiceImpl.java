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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService;
import eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Computing Infrastructure Service</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.ComputingInfrastructureServiceImpl#getDescriptor <em>Descriptor</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.ComputingInfrastructureServiceImpl#getPlatformServices <em>Platform Services</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComputingInfrastructureServiceImpl extends
		InfrastructureServiceImpl implements ComputingInfrastructureService {
	/**
	 * The cached value of the '{@link #getDescriptor() <em>Descriptor</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDescriptor()
	 * @generated
	 * @ordered
	 */
	protected ComputingInfrastructureServiceDescriptor descriptor;

	/**
	 * The cached value of the '{@link #getPlatformServices() <em>Platform Services</em>}' reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getPlatformServices()
	 * @generated
	 * @ordered
	 */
	protected EList<DeployablePlatformService> platformServices;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ComputingInfrastructureServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ComputingInfrastructureServiceDescriptor getDescriptor() {
		if (descriptor != null && descriptor.eIsProxy()) {
			InternalEObject oldDescriptor = (InternalEObject)descriptor;
			descriptor = (ComputingInfrastructureServiceDescriptor)eResolveProxy(oldDescriptor);
			if (descriptor != oldDescriptor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTOR, oldDescriptor, descriptor));
			}
		}
		return descriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ComputingInfrastructureServiceDescriptor basicGetDescriptor() {
		return descriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescriptor(
			ComputingInfrastructureServiceDescriptor newDescriptor) {
		ComputingInfrastructureServiceDescriptor oldDescriptor = descriptor;
		descriptor = newDescriptor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTOR, oldDescriptor, descriptor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DeployablePlatformService> getPlatformServices() {
		if (platformServices == null) {
			platformServices = new EObjectWithInverseResolvingEList<DeployablePlatformService>(DeployablePlatformService.class, this, ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE__PLATFORM_SERVICES, ArchitecturePackage.DEPLOYABLE_PLATFORM_SERVICE__COMPUTING_INFRASTRUCTURE_SERVICE);
		}
		return platformServices;
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
			case ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE__PLATFORM_SERVICES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPlatformServices()).basicAdd(otherEnd, msgs);
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
			case ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE__PLATFORM_SERVICES:
				return ((InternalEList<?>)getPlatformServices()).basicRemove(otherEnd, msgs);
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
			case ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTOR:
				if (resolve) return getDescriptor();
				return basicGetDescriptor();
			case ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE__PLATFORM_SERVICES:
				return getPlatformServices();
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
			case ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTOR:
				setDescriptor((ComputingInfrastructureServiceDescriptor)newValue);
				return;
			case ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE__PLATFORM_SERVICES:
				getPlatformServices().clear();
				getPlatformServices().addAll((Collection<? extends DeployablePlatformService>)newValue);
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
			case ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTOR:
				setDescriptor((ComputingInfrastructureServiceDescriptor)null);
				return;
			case ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE__PLATFORM_SERVICES:
				getPlatformServices().clear();
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
			case ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTOR:
				return descriptor != null;
			case ArchitecturePackage.COMPUTING_INFRASTRUCTURE_SERVICE__PLATFORM_SERVICES:
				return platformServices != null && !platformServices.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // ComputingInfrastructureServiceImpl
