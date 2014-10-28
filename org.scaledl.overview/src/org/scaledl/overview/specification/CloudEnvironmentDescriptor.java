/**
 */
package org.scaledl.overview.specification;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cloud Environment Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The CloudProviderDecscriptor specifies the global properties of the cloud providers (see CloudProvider). 
 * In the current version only Entity properties are available; name and description.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.CloudEnvironmentDescriptor#getAvailabilityZones <em>Availability Zones</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.CloudEnvironmentDescriptor#getRegions <em>Regions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.specification.SpecificationPackage#getCloudEnvironmentDescriptor()
 * @model
 * @generated
 */
public interface CloudEnvironmentDescriptor extends Descriptor {
	/**
	 * Returns the value of the '<em><b>Availability Zones</b></em>' containment reference list.
	 * The list contents are of type {@link org.scaledl.overview.specification.AvailabilityZoneDescriptor}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Availability Zones</em>' containment reference list.
	 * @see org.scaledl.overview.specification.SpecificationPackage#getCloudEnvironmentDescriptor_AvailabilityZones()
	 * @model containment="true"
	 * @generated
	 */
	EList<AvailabilityZoneDescriptor> getAvailabilityZones();

	/**
	 * Returns the value of the '<em><b>Regions</b></em>' containment reference list.
	 * The list contents are of type {@link org.scaledl.overview.specification.RegionDescriptor}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Regions</em>' containment reference list.
	 * @see org.scaledl.overview.specification.SpecificationPackage#getCloudEnvironmentDescriptor_Regions()
	 * @model containment="true"
	 * @generated
	 */
	EList<RegionDescriptor> getRegions();

} // CloudEnvironmentDescriptor
