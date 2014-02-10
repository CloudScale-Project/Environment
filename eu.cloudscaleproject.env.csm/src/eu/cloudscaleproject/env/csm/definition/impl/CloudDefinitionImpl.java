/**
 */
package eu.cloudscaleproject.env.csm.definition.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.cloudscaleproject.env.csm.definition.CloudDefinition;
import eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor;
import eu.cloudscaleproject.env.csm.definition.DefinitionPackage;
import eu.cloudscaleproject.env.csm.definition.ExternalPlatformServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.InfrastructureServiceDescriptor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Cloud Definition</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.impl.CloudDefinitionImpl#getDescriptor <em>Descriptor</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.impl.CloudDefinitionImpl#getInfrastructureDescriptors <em>Infrastructure Descriptors</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.impl.CloudDefinitionImpl#getPlatformDescriptors <em>Platform Descriptors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CloudDefinitionImpl extends DefinitionImpl implements
		CloudDefinition {
	/**
	 * The cached value of the '{@link #getDescriptor() <em>Descriptor</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDescriptor()
	 * @generated
	 * @ordered
	 */
	protected CloudEnvironmentDescriptor descriptor;

	/**
	 * The cached value of the '{@link #getInfrastructureDescriptors()
	 * <em>Infrastructure Descriptors</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getInfrastructureDescriptors()
	 * @generated
	 * @ordered
	 */
	protected EList<InfrastructureServiceDescriptor> infrastructureDescriptors;

	/**
	 * The cached value of the '{@link #getPlatformDescriptors()
	 * <em>Platform Descriptors</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPlatformDescriptors()
	 * @generated
	 * @ordered
	 */
	protected EList<ExternalPlatformServiceDescriptor> platformDescriptors;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected CloudDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DefinitionPackage.Literals.CLOUD_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CloudEnvironmentDescriptor getDescriptor() {
		return descriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDescriptor(
			CloudEnvironmentDescriptor newDescriptor, NotificationChain msgs) {
		CloudEnvironmentDescriptor oldDescriptor = descriptor;
		descriptor = newDescriptor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DefinitionPackage.CLOUD_DEFINITION__DESCRIPTOR, oldDescriptor, newDescriptor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescriptor(CloudEnvironmentDescriptor newDescriptor) {
		if (newDescriptor != descriptor) {
			NotificationChain msgs = null;
			if (descriptor != null)
				msgs = ((InternalEObject)descriptor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DefinitionPackage.CLOUD_DEFINITION__DESCRIPTOR, null, msgs);
			if (newDescriptor != null)
				msgs = ((InternalEObject)newDescriptor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DefinitionPackage.CLOUD_DEFINITION__DESCRIPTOR, null, msgs);
			msgs = basicSetDescriptor(newDescriptor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DefinitionPackage.CLOUD_DEFINITION__DESCRIPTOR, newDescriptor, newDescriptor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InfrastructureServiceDescriptor> getInfrastructureDescriptors() {
		if (infrastructureDescriptors == null) {
			infrastructureDescriptors = new EObjectContainmentEList<InfrastructureServiceDescriptor>(InfrastructureServiceDescriptor.class, this, DefinitionPackage.CLOUD_DEFINITION__INFRASTRUCTURE_DESCRIPTORS);
		}
		return infrastructureDescriptors;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExternalPlatformServiceDescriptor> getPlatformDescriptors() {
		if (platformDescriptors == null) {
			platformDescriptors = new EObjectContainmentEList<ExternalPlatformServiceDescriptor>(ExternalPlatformServiceDescriptor.class, this, DefinitionPackage.CLOUD_DEFINITION__PLATFORM_DESCRIPTORS);
		}
		return platformDescriptors;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DefinitionPackage.CLOUD_DEFINITION__DESCRIPTOR:
				return basicSetDescriptor(null, msgs);
			case DefinitionPackage.CLOUD_DEFINITION__INFRASTRUCTURE_DESCRIPTORS:
				return ((InternalEList<?>)getInfrastructureDescriptors()).basicRemove(otherEnd, msgs);
			case DefinitionPackage.CLOUD_DEFINITION__PLATFORM_DESCRIPTORS:
				return ((InternalEList<?>)getPlatformDescriptors()).basicRemove(otherEnd, msgs);
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
			case DefinitionPackage.CLOUD_DEFINITION__DESCRIPTOR:
				return getDescriptor();
			case DefinitionPackage.CLOUD_DEFINITION__INFRASTRUCTURE_DESCRIPTORS:
				return getInfrastructureDescriptors();
			case DefinitionPackage.CLOUD_DEFINITION__PLATFORM_DESCRIPTORS:
				return getPlatformDescriptors();
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
			case DefinitionPackage.CLOUD_DEFINITION__DESCRIPTOR:
				setDescriptor((CloudEnvironmentDescriptor)newValue);
				return;
			case DefinitionPackage.CLOUD_DEFINITION__INFRASTRUCTURE_DESCRIPTORS:
				getInfrastructureDescriptors().clear();
				getInfrastructureDescriptors().addAll((Collection<? extends InfrastructureServiceDescriptor>)newValue);
				return;
			case DefinitionPackage.CLOUD_DEFINITION__PLATFORM_DESCRIPTORS:
				getPlatformDescriptors().clear();
				getPlatformDescriptors().addAll((Collection<? extends ExternalPlatformServiceDescriptor>)newValue);
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
			case DefinitionPackage.CLOUD_DEFINITION__DESCRIPTOR:
				setDescriptor((CloudEnvironmentDescriptor)null);
				return;
			case DefinitionPackage.CLOUD_DEFINITION__INFRASTRUCTURE_DESCRIPTORS:
				getInfrastructureDescriptors().clear();
				return;
			case DefinitionPackage.CLOUD_DEFINITION__PLATFORM_DESCRIPTORS:
				getPlatformDescriptors().clear();
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
			case DefinitionPackage.CLOUD_DEFINITION__DESCRIPTOR:
				return descriptor != null;
			case DefinitionPackage.CLOUD_DEFINITION__INFRASTRUCTURE_DESCRIPTORS:
				return infrastructureDescriptors != null && !infrastructureDescriptors.isEmpty();
			case DefinitionPackage.CLOUD_DEFINITION__PLATFORM_DESCRIPTORS:
				return platformDescriptors != null && !platformDescriptors.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // CloudDefinitionImpl
