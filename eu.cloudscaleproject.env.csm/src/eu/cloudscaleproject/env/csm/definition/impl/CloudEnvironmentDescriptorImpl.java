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

import eu.cloudscaleproject.env.csm.definition.AvailabilityZoneDescriptor;
import eu.cloudscaleproject.env.csm.definition.CloudEnvironmentDescriptor;
import eu.cloudscaleproject.env.csm.definition.DefinitionPackage;
import eu.cloudscaleproject.env.csm.definition.RegionDescriptor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Cloud Environment Descriptor</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.impl.CloudEnvironmentDescriptorImpl#getAvailabilityZones <em>Availability Zones</em>}</li>
 *   <li>{@link eu.cloudscaleproject.env.csm.definition.impl.CloudEnvironmentDescriptorImpl#getRegions <em>Regions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CloudEnvironmentDescriptorImpl extends DescriptorImpl implements
		CloudEnvironmentDescriptor {
	/**
	 * The cached value of the '{@link #getAvailabilityZones()
	 * <em>Availability Zones</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAvailabilityZones()
	 * @generated
	 * @ordered
	 */
	protected EList<AvailabilityZoneDescriptor> availabilityZones;

	/**
	 * The cached value of the '{@link #getRegions() <em>Regions</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRegions()
	 * @generated
	 * @ordered
	 */
	protected EList<RegionDescriptor> regions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected CloudEnvironmentDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DefinitionPackage.Literals.CLOUD_ENVIRONMENT_DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AvailabilityZoneDescriptor> getAvailabilityZones() {
		if (availabilityZones == null) {
			availabilityZones = new EObjectContainmentEList<AvailabilityZoneDescriptor>(AvailabilityZoneDescriptor.class, this, DefinitionPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES);
		}
		return availabilityZones;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RegionDescriptor> getRegions() {
		if (regions == null) {
			regions = new EObjectContainmentEList<RegionDescriptor>(RegionDescriptor.class, this, DefinitionPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS);
		}
		return regions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DefinitionPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES:
				return ((InternalEList<?>)getAvailabilityZones()).basicRemove(otherEnd, msgs);
			case DefinitionPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS:
				return ((InternalEList<?>)getRegions()).basicRemove(otherEnd, msgs);
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
			case DefinitionPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES:
				return getAvailabilityZones();
			case DefinitionPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS:
				return getRegions();
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
			case DefinitionPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES:
				getAvailabilityZones().clear();
				getAvailabilityZones().addAll((Collection<? extends AvailabilityZoneDescriptor>)newValue);
				return;
			case DefinitionPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS:
				getRegions().clear();
				getRegions().addAll((Collection<? extends RegionDescriptor>)newValue);
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
			case DefinitionPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES:
				getAvailabilityZones().clear();
				return;
			case DefinitionPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS:
				getRegions().clear();
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
			case DefinitionPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__AVAILABILITY_ZONES:
				return availabilityZones != null && !availabilityZones.isEmpty();
			case DefinitionPackage.CLOUD_ENVIRONMENT_DESCRIPTOR__REGIONS:
				return regions != null && !regions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // CloudEnvironmentDescriptorImpl
