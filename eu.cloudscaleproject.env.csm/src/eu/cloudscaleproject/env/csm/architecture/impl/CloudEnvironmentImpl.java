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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.cloudscaleproject.env.csm.architecture.Architecture;
import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer;
import eu.cloudscaleproject.env.csm.architecture.InternalConnection;
import eu.cloudscaleproject.env.csm.architecture.PlatformLayer;
import eu.cloudscaleproject.env.csm.architecture.SoftwareLayer;
import eu.cloudscaleproject.env.csm.core.impl.EntityImpl;
import eu.cloudscaleproject.env.csm.definition.AvailabilityZoneDescriptor;
import eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Cloud Environment</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.CloudEnvironmentImpl#getInternalConnections <em>Internal Connections</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.CloudEnvironmentImpl#getCloudProviderDescriptor <em>Cloud Provider Descriptor</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.CloudEnvironmentImpl#getAvailabilityZoneDescriptor <em>Availability Zone Descriptor</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.CloudEnvironmentImpl#getArchitecture <em>Architecture</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.CloudEnvironmentImpl#getInfrastructureLayer <em>Infrastructure Layer</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.CloudEnvironmentImpl#getPlatformLayer <em>Platform Layer</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.architecture.impl.CloudEnvironmentImpl#getSoftwareLayer <em>Software Layer</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CloudEnvironmentImpl extends EntityImpl implements
		CloudEnvironment {
	/**
	 * The cached value of the '{@link #getInternalConnections()
	 * <em>Internal Connections</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getInternalConnections()
	 * @generated
	 * @ordered
	 */
	protected EList<InternalConnection> internalConnections;

	/**
	 * The cached value of the '{@link #getCloudProviderDescriptor() <em>Cloud Provider Descriptor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudProviderDescriptor()
	 * @generated
	 * @ordered
	 */
	protected CloudEnvironmentDescriptor cloudProviderDescriptor;

	/**
	 * The cached value of the '{@link #getAvailabilityZoneDescriptor() <em>Availability Zone Descriptor</em>}' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #getAvailabilityZoneDescriptor()
	 * @generated
	 * @ordered
	 */
	protected AvailabilityZoneDescriptor availabilityZoneDescriptor;

	/**
	 * The cached value of the '{@link #getInfrastructureLayer()
	 * <em>Infrastructure Layer</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getInfrastructureLayer()
	 * @generated
	 * @ordered
	 */
	protected InfrastructureLayer infrastructureLayer;

	/**
	 * The cached value of the '{@link #getPlatformLayer() <em>Platform Layer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlatformLayer()
	 * @generated
	 * @ordered
	 */
	protected PlatformLayer platformLayer;

	/**
	 * The cached value of the '{@link #getSoftwareLayer() <em>Software Layer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoftwareLayer()
	 * @generated
	 * @ordered
	 */
	protected SoftwareLayer softwareLayer;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected CloudEnvironmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArchitecturePackage.Literals.CLOUD_ENVIRONMENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InternalConnection> getInternalConnections() {
		if (internalConnections == null) {
			internalConnections = new EObjectContainmentEList<InternalConnection>(InternalConnection.class, this, ArchitecturePackage.CLOUD_ENVIRONMENT__INTERNAL_CONNECTIONS);
		}
		return internalConnections;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CloudEnvironmentDescriptor getCloudProviderDescriptor() {
		if (cloudProviderDescriptor != null && cloudProviderDescriptor.eIsProxy()) {
			InternalEObject oldCloudProviderDescriptor = (InternalEObject)cloudProviderDescriptor;
			cloudProviderDescriptor = (CloudEnvironmentDescriptor)eResolveProxy(oldCloudProviderDescriptor);
			if (cloudProviderDescriptor != oldCloudProviderDescriptor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.CLOUD_ENVIRONMENT__CLOUD_PROVIDER_DESCRIPTOR, oldCloudProviderDescriptor, cloudProviderDescriptor));
			}
		}
		return cloudProviderDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CloudEnvironmentDescriptor basicGetCloudProviderDescriptor() {
		return cloudProviderDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setCloudProviderDescriptor(
			CloudEnvironmentDescriptor newCloudProviderDescriptor) {
		CloudEnvironmentDescriptor oldCloudProviderDescriptor = cloudProviderDescriptor;
		cloudProviderDescriptor = newCloudProviderDescriptor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.CLOUD_ENVIRONMENT__CLOUD_PROVIDER_DESCRIPTOR, oldCloudProviderDescriptor, cloudProviderDescriptor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public AvailabilityZoneDescriptor getAvailabilityZoneDescriptor() {
		if (availabilityZoneDescriptor != null && availabilityZoneDescriptor.eIsProxy()) {
			InternalEObject oldAvailabilityZoneDescriptor = (InternalEObject)availabilityZoneDescriptor;
			availabilityZoneDescriptor = (AvailabilityZoneDescriptor)eResolveProxy(oldAvailabilityZoneDescriptor);
			if (availabilityZoneDescriptor != oldAvailabilityZoneDescriptor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArchitecturePackage.CLOUD_ENVIRONMENT__AVAILABILITY_ZONE_DESCRIPTOR, oldAvailabilityZoneDescriptor, availabilityZoneDescriptor));
			}
		}
		return availabilityZoneDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public AvailabilityZoneDescriptor basicGetAvailabilityZoneDescriptor() {
		return availabilityZoneDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setAvailabilityZoneDescriptor(
			AvailabilityZoneDescriptor newAvailabilityZoneDescriptor) {
		AvailabilityZoneDescriptor oldAvailabilityZoneDescriptor = availabilityZoneDescriptor;
		availabilityZoneDescriptor = newAvailabilityZoneDescriptor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.CLOUD_ENVIRONMENT__AVAILABILITY_ZONE_DESCRIPTOR, oldAvailabilityZoneDescriptor, availabilityZoneDescriptor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Architecture getArchitecture() {
		if (eContainerFeatureID() != ArchitecturePackage.CLOUD_ENVIRONMENT__ARCHITECTURE) return null;
		return (Architecture)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArchitecture(Architecture newArchitecture,
			NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newArchitecture, ArchitecturePackage.CLOUD_ENVIRONMENT__ARCHITECTURE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setArchitecture(Architecture newArchitecture) {
		if (newArchitecture != eInternalContainer() || (eContainerFeatureID() != ArchitecturePackage.CLOUD_ENVIRONMENT__ARCHITECTURE && newArchitecture != null)) {
			if (EcoreUtil.isAncestor(this, newArchitecture))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newArchitecture != null)
				msgs = ((InternalEObject)newArchitecture).eInverseAdd(this, ArchitecturePackage.ARCHITECTURE__CLOUD_PROVIDERS, Architecture.class, msgs);
			msgs = basicSetArchitecture(newArchitecture, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.CLOUD_ENVIRONMENT__ARCHITECTURE, newArchitecture, newArchitecture));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public InfrastructureLayer getInfrastructureLayer() {
		return infrastructureLayer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInfrastructureLayer(
			InfrastructureLayer newInfrastructureLayer, NotificationChain msgs) {
		InfrastructureLayer oldInfrastructureLayer = infrastructureLayer;
		infrastructureLayer = newInfrastructureLayer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ArchitecturePackage.CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER, oldInfrastructureLayer, newInfrastructureLayer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setInfrastructureLayer(
			InfrastructureLayer newInfrastructureLayer) {
		if (newInfrastructureLayer != infrastructureLayer) {
			NotificationChain msgs = null;
			if (infrastructureLayer != null)
				msgs = ((InternalEObject)infrastructureLayer).eInverseRemove(this, ArchitecturePackage.INFRASTRUCTURE_LAYER__CLOUD_PROVIDER, InfrastructureLayer.class, msgs);
			if (newInfrastructureLayer != null)
				msgs = ((InternalEObject)newInfrastructureLayer).eInverseAdd(this, ArchitecturePackage.INFRASTRUCTURE_LAYER__CLOUD_PROVIDER, InfrastructureLayer.class, msgs);
			msgs = basicSetInfrastructureLayer(newInfrastructureLayer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER, newInfrastructureLayer, newInfrastructureLayer));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformLayer getPlatformLayer() {
		return platformLayer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPlatformLayer(
			PlatformLayer newPlatformLayer, NotificationChain msgs) {
		PlatformLayer oldPlatformLayer = platformLayer;
		platformLayer = newPlatformLayer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ArchitecturePackage.CLOUD_ENVIRONMENT__PLATFORM_LAYER, oldPlatformLayer, newPlatformLayer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPlatformLayer(PlatformLayer newPlatformLayer) {
		if (newPlatformLayer != platformLayer) {
			NotificationChain msgs = null;
			if (platformLayer != null)
				msgs = ((InternalEObject)platformLayer).eInverseRemove(this, ArchitecturePackage.PLATFORM_LAYER__CLOUD_ENVIRONMENT, PlatformLayer.class, msgs);
			if (newPlatformLayer != null)
				msgs = ((InternalEObject)newPlatformLayer).eInverseAdd(this, ArchitecturePackage.PLATFORM_LAYER__CLOUD_ENVIRONMENT, PlatformLayer.class, msgs);
			msgs = basicSetPlatformLayer(newPlatformLayer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.CLOUD_ENVIRONMENT__PLATFORM_LAYER, newPlatformLayer, newPlatformLayer));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareLayer getSoftwareLayer() {
		return softwareLayer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSoftwareLayer(
			SoftwareLayer newSoftwareLayer, NotificationChain msgs) {
		SoftwareLayer oldSoftwareLayer = softwareLayer;
		softwareLayer = newSoftwareLayer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ArchitecturePackage.CLOUD_ENVIRONMENT__SOFTWARE_LAYER, oldSoftwareLayer, newSoftwareLayer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSoftwareLayer(SoftwareLayer newSoftwareLayer) {
		if (newSoftwareLayer != softwareLayer) {
			NotificationChain msgs = null;
			if (softwareLayer != null)
				msgs = ((InternalEObject)softwareLayer).eInverseRemove(this, ArchitecturePackage.SOFTWARE_LAYER__CLOUD_PROVIDER, SoftwareLayer.class, msgs);
			if (newSoftwareLayer != null)
				msgs = ((InternalEObject)newSoftwareLayer).eInverseAdd(this, ArchitecturePackage.SOFTWARE_LAYER__CLOUD_PROVIDER, SoftwareLayer.class, msgs);
			msgs = basicSetSoftwareLayer(newSoftwareLayer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArchitecturePackage.CLOUD_ENVIRONMENT__SOFTWARE_LAYER, newSoftwareLayer, newSoftwareLayer));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ArchitecturePackage.CLOUD_ENVIRONMENT__ARCHITECTURE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetArchitecture((Architecture)otherEnd, msgs);
			case ArchitecturePackage.CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER:
				if (infrastructureLayer != null)
					msgs = ((InternalEObject)infrastructureLayer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ArchitecturePackage.CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER, null, msgs);
				return basicSetInfrastructureLayer((InfrastructureLayer)otherEnd, msgs);
			case ArchitecturePackage.CLOUD_ENVIRONMENT__PLATFORM_LAYER:
				if (platformLayer != null)
					msgs = ((InternalEObject)platformLayer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ArchitecturePackage.CLOUD_ENVIRONMENT__PLATFORM_LAYER, null, msgs);
				return basicSetPlatformLayer((PlatformLayer)otherEnd, msgs);
			case ArchitecturePackage.CLOUD_ENVIRONMENT__SOFTWARE_LAYER:
				if (softwareLayer != null)
					msgs = ((InternalEObject)softwareLayer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ArchitecturePackage.CLOUD_ENVIRONMENT__SOFTWARE_LAYER, null, msgs);
				return basicSetSoftwareLayer((SoftwareLayer)otherEnd, msgs);
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
			case ArchitecturePackage.CLOUD_ENVIRONMENT__INTERNAL_CONNECTIONS:
				return ((InternalEList<?>)getInternalConnections()).basicRemove(otherEnd, msgs);
			case ArchitecturePackage.CLOUD_ENVIRONMENT__ARCHITECTURE:
				return basicSetArchitecture(null, msgs);
			case ArchitecturePackage.CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER:
				return basicSetInfrastructureLayer(null, msgs);
			case ArchitecturePackage.CLOUD_ENVIRONMENT__PLATFORM_LAYER:
				return basicSetPlatformLayer(null, msgs);
			case ArchitecturePackage.CLOUD_ENVIRONMENT__SOFTWARE_LAYER:
				return basicSetSoftwareLayer(null, msgs);
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
			case ArchitecturePackage.CLOUD_ENVIRONMENT__ARCHITECTURE:
				return eInternalContainer().eInverseRemove(this, ArchitecturePackage.ARCHITECTURE__CLOUD_PROVIDERS, Architecture.class, msgs);
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
			case ArchitecturePackage.CLOUD_ENVIRONMENT__INTERNAL_CONNECTIONS:
				return getInternalConnections();
			case ArchitecturePackage.CLOUD_ENVIRONMENT__CLOUD_PROVIDER_DESCRIPTOR:
				if (resolve) return getCloudProviderDescriptor();
				return basicGetCloudProviderDescriptor();
			case ArchitecturePackage.CLOUD_ENVIRONMENT__AVAILABILITY_ZONE_DESCRIPTOR:
				if (resolve) return getAvailabilityZoneDescriptor();
				return basicGetAvailabilityZoneDescriptor();
			case ArchitecturePackage.CLOUD_ENVIRONMENT__ARCHITECTURE:
				return getArchitecture();
			case ArchitecturePackage.CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER:
				return getInfrastructureLayer();
			case ArchitecturePackage.CLOUD_ENVIRONMENT__PLATFORM_LAYER:
				return getPlatformLayer();
			case ArchitecturePackage.CLOUD_ENVIRONMENT__SOFTWARE_LAYER:
				return getSoftwareLayer();
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
			case ArchitecturePackage.CLOUD_ENVIRONMENT__INTERNAL_CONNECTIONS:
				getInternalConnections().clear();
				getInternalConnections().addAll((Collection<? extends InternalConnection>)newValue);
				return;
			case ArchitecturePackage.CLOUD_ENVIRONMENT__CLOUD_PROVIDER_DESCRIPTOR:
				setCloudProviderDescriptor((CloudEnvironmentDescriptor)newValue);
				return;
			case ArchitecturePackage.CLOUD_ENVIRONMENT__AVAILABILITY_ZONE_DESCRIPTOR:
				setAvailabilityZoneDescriptor((AvailabilityZoneDescriptor)newValue);
				return;
			case ArchitecturePackage.CLOUD_ENVIRONMENT__ARCHITECTURE:
				setArchitecture((Architecture)newValue);
				return;
			case ArchitecturePackage.CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER:
				setInfrastructureLayer((InfrastructureLayer)newValue);
				return;
			case ArchitecturePackage.CLOUD_ENVIRONMENT__PLATFORM_LAYER:
				setPlatformLayer((PlatformLayer)newValue);
				return;
			case ArchitecturePackage.CLOUD_ENVIRONMENT__SOFTWARE_LAYER:
				setSoftwareLayer((SoftwareLayer)newValue);
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
			case ArchitecturePackage.CLOUD_ENVIRONMENT__INTERNAL_CONNECTIONS:
				getInternalConnections().clear();
				return;
			case ArchitecturePackage.CLOUD_ENVIRONMENT__CLOUD_PROVIDER_DESCRIPTOR:
				setCloudProviderDescriptor((CloudEnvironmentDescriptor)null);
				return;
			case ArchitecturePackage.CLOUD_ENVIRONMENT__AVAILABILITY_ZONE_DESCRIPTOR:
				setAvailabilityZoneDescriptor((AvailabilityZoneDescriptor)null);
				return;
			case ArchitecturePackage.CLOUD_ENVIRONMENT__ARCHITECTURE:
				setArchitecture((Architecture)null);
				return;
			case ArchitecturePackage.CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER:
				setInfrastructureLayer((InfrastructureLayer)null);
				return;
			case ArchitecturePackage.CLOUD_ENVIRONMENT__PLATFORM_LAYER:
				setPlatformLayer((PlatformLayer)null);
				return;
			case ArchitecturePackage.CLOUD_ENVIRONMENT__SOFTWARE_LAYER:
				setSoftwareLayer((SoftwareLayer)null);
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
			case ArchitecturePackage.CLOUD_ENVIRONMENT__INTERNAL_CONNECTIONS:
				return internalConnections != null && !internalConnections.isEmpty();
			case ArchitecturePackage.CLOUD_ENVIRONMENT__CLOUD_PROVIDER_DESCRIPTOR:
				return cloudProviderDescriptor != null;
			case ArchitecturePackage.CLOUD_ENVIRONMENT__AVAILABILITY_ZONE_DESCRIPTOR:
				return availabilityZoneDescriptor != null;
			case ArchitecturePackage.CLOUD_ENVIRONMENT__ARCHITECTURE:
				return getArchitecture() != null;
			case ArchitecturePackage.CLOUD_ENVIRONMENT__INFRASTRUCTURE_LAYER:
				return infrastructureLayer != null;
			case ArchitecturePackage.CLOUD_ENVIRONMENT__PLATFORM_LAYER:
				return platformLayer != null;
			case ArchitecturePackage.CLOUD_ENVIRONMENT__SOFTWARE_LAYER:
				return softwareLayer != null;
		}
		return super.eIsSet(featureID);
	}

} // CloudEnvironmentImpl
