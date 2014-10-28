/**
 */
package org.scaledl.overview.specification.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.scaledl.overview.specification.CloudEnvironmentDescriptor;
import org.scaledl.overview.specification.CloudSpecification;
import org.scaledl.overview.specification.InfrastructureServiceDescriptor;
import org.scaledl.overview.specification.ProvidedServiceDescriptor;
import org.scaledl.overview.specification.SpecificationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cloud Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.impl.CloudSpecificationImpl#getDescriptor <em>Descriptor</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.CloudSpecificationImpl#getInfrastructureServiceDescriptors <em>Infrastructure Service Descriptors</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.CloudSpecificationImpl#getPlatformServiceDescriptors <em>Platform Service Descriptors</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.impl.CloudSpecificationImpl#getSoftwareServiceDescriptors <em>Software Service Descriptors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CloudSpecificationImpl extends SpecificationImpl implements CloudSpecification {
	/**
	 * The cached value of the '{@link #getDescriptor() <em>Descriptor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptor()
	 * @generated
	 * @ordered
	 */
	protected CloudEnvironmentDescriptor descriptor;

	/**
	 * The cached value of the '{@link #getInfrastructureServiceDescriptors() <em>Infrastructure Service Descriptors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInfrastructureServiceDescriptors()
	 * @generated
	 * @ordered
	 */
	protected EList<InfrastructureServiceDescriptor> infrastructureServiceDescriptors;

	/**
	 * The cached value of the '{@link #getPlatformServiceDescriptors() <em>Platform Service Descriptors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlatformServiceDescriptors()
	 * @generated
	 * @ordered
	 */
	protected EList<ProvidedServiceDescriptor> platformServiceDescriptors;

	/**
	 * The cached value of the '{@link #getSoftwareServiceDescriptors() <em>Software Service Descriptors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoftwareServiceDescriptors()
	 * @generated
	 * @ordered
	 */
	protected EList<ProvidedServiceDescriptor> softwareServiceDescriptors;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CloudSpecificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SpecificationPackage.Literals.CLOUD_SPECIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CloudEnvironmentDescriptor getDescriptor() {
		return descriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDescriptor(CloudEnvironmentDescriptor newDescriptor, NotificationChain msgs) {
		CloudEnvironmentDescriptor oldDescriptor = descriptor;
		descriptor = newDescriptor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SpecificationPackage.CLOUD_SPECIFICATION__DESCRIPTOR, oldDescriptor, newDescriptor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescriptor(CloudEnvironmentDescriptor newDescriptor) {
		if (newDescriptor != descriptor) {
			NotificationChain msgs = null;
			if (descriptor != null)
				msgs = ((InternalEObject)descriptor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SpecificationPackage.CLOUD_SPECIFICATION__DESCRIPTOR, null, msgs);
			if (newDescriptor != null)
				msgs = ((InternalEObject)newDescriptor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SpecificationPackage.CLOUD_SPECIFICATION__DESCRIPTOR, null, msgs);
			msgs = basicSetDescriptor(newDescriptor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.CLOUD_SPECIFICATION__DESCRIPTOR, newDescriptor, newDescriptor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InfrastructureServiceDescriptor> getInfrastructureServiceDescriptors() {
		if (infrastructureServiceDescriptors == null) {
			infrastructureServiceDescriptors = new EObjectContainmentEList<InfrastructureServiceDescriptor>(InfrastructureServiceDescriptor.class, this, SpecificationPackage.CLOUD_SPECIFICATION__INFRASTRUCTURE_SERVICE_DESCRIPTORS);
		}
		return infrastructureServiceDescriptors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProvidedServiceDescriptor> getPlatformServiceDescriptors() {
		if (platformServiceDescriptors == null) {
			platformServiceDescriptors = new EObjectContainmentEList<ProvidedServiceDescriptor>(ProvidedServiceDescriptor.class, this, SpecificationPackage.CLOUD_SPECIFICATION__PLATFORM_SERVICE_DESCRIPTORS);
		}
		return platformServiceDescriptors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProvidedServiceDescriptor> getSoftwareServiceDescriptors() {
		if (softwareServiceDescriptors == null) {
			softwareServiceDescriptors = new EObjectContainmentEList<ProvidedServiceDescriptor>(ProvidedServiceDescriptor.class, this, SpecificationPackage.CLOUD_SPECIFICATION__SOFTWARE_SERVICE_DESCRIPTORS);
		}
		return softwareServiceDescriptors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SpecificationPackage.CLOUD_SPECIFICATION__DESCRIPTOR:
				return basicSetDescriptor(null, msgs);
			case SpecificationPackage.CLOUD_SPECIFICATION__INFRASTRUCTURE_SERVICE_DESCRIPTORS:
				return ((InternalEList<?>)getInfrastructureServiceDescriptors()).basicRemove(otherEnd, msgs);
			case SpecificationPackage.CLOUD_SPECIFICATION__PLATFORM_SERVICE_DESCRIPTORS:
				return ((InternalEList<?>)getPlatformServiceDescriptors()).basicRemove(otherEnd, msgs);
			case SpecificationPackage.CLOUD_SPECIFICATION__SOFTWARE_SERVICE_DESCRIPTORS:
				return ((InternalEList<?>)getSoftwareServiceDescriptors()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SpecificationPackage.CLOUD_SPECIFICATION__DESCRIPTOR:
				return getDescriptor();
			case SpecificationPackage.CLOUD_SPECIFICATION__INFRASTRUCTURE_SERVICE_DESCRIPTORS:
				return getInfrastructureServiceDescriptors();
			case SpecificationPackage.CLOUD_SPECIFICATION__PLATFORM_SERVICE_DESCRIPTORS:
				return getPlatformServiceDescriptors();
			case SpecificationPackage.CLOUD_SPECIFICATION__SOFTWARE_SERVICE_DESCRIPTORS:
				return getSoftwareServiceDescriptors();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SpecificationPackage.CLOUD_SPECIFICATION__DESCRIPTOR:
				setDescriptor((CloudEnvironmentDescriptor)newValue);
				return;
			case SpecificationPackage.CLOUD_SPECIFICATION__INFRASTRUCTURE_SERVICE_DESCRIPTORS:
				getInfrastructureServiceDescriptors().clear();
				getInfrastructureServiceDescriptors().addAll((Collection<? extends InfrastructureServiceDescriptor>)newValue);
				return;
			case SpecificationPackage.CLOUD_SPECIFICATION__PLATFORM_SERVICE_DESCRIPTORS:
				getPlatformServiceDescriptors().clear();
				getPlatformServiceDescriptors().addAll((Collection<? extends ProvidedServiceDescriptor>)newValue);
				return;
			case SpecificationPackage.CLOUD_SPECIFICATION__SOFTWARE_SERVICE_DESCRIPTORS:
				getSoftwareServiceDescriptors().clear();
				getSoftwareServiceDescriptors().addAll((Collection<? extends ProvidedServiceDescriptor>)newValue);
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
			case SpecificationPackage.CLOUD_SPECIFICATION__DESCRIPTOR:
				setDescriptor((CloudEnvironmentDescriptor)null);
				return;
			case SpecificationPackage.CLOUD_SPECIFICATION__INFRASTRUCTURE_SERVICE_DESCRIPTORS:
				getInfrastructureServiceDescriptors().clear();
				return;
			case SpecificationPackage.CLOUD_SPECIFICATION__PLATFORM_SERVICE_DESCRIPTORS:
				getPlatformServiceDescriptors().clear();
				return;
			case SpecificationPackage.CLOUD_SPECIFICATION__SOFTWARE_SERVICE_DESCRIPTORS:
				getSoftwareServiceDescriptors().clear();
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
			case SpecificationPackage.CLOUD_SPECIFICATION__DESCRIPTOR:
				return descriptor != null;
			case SpecificationPackage.CLOUD_SPECIFICATION__INFRASTRUCTURE_SERVICE_DESCRIPTORS:
				return infrastructureServiceDescriptors != null && !infrastructureServiceDescriptors.isEmpty();
			case SpecificationPackage.CLOUD_SPECIFICATION__PLATFORM_SERVICE_DESCRIPTORS:
				return platformServiceDescriptors != null && !platformServiceDescriptors.isEmpty();
			case SpecificationPackage.CLOUD_SPECIFICATION__SOFTWARE_SERVICE_DESCRIPTORS:
				return softwareServiceDescriptors != null && !softwareServiceDescriptors.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CloudSpecificationImpl
