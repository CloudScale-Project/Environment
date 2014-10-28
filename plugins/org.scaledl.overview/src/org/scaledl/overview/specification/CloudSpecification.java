/**
 */
package org.scaledl.overview.specification;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cloud Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The CloudDefinition model specifies all the details of the cloud provider (see CloudProvider). It consists of cloud provider descriptor, instance descriptors (in case the IaaS clouds), service descriptors (in case of PaaS clouds) and connection descriptors, specifying internal connections (e.g. bandwidth and latency). 
 * This model is not meant to be included into CSM model, but it provides ability for specifying different cloud environments, which descriptors can be assigned to CSM model entities.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.CloudSpecification#getDescriptor <em>Descriptor</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.CloudSpecification#getInfrastructureServiceDescriptors <em>Infrastructure Service Descriptors</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.CloudSpecification#getPlatformServiceDescriptors <em>Platform Service Descriptors</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.CloudSpecification#getSoftwareServiceDescriptors <em>Software Service Descriptors</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.specification.SpecificationPackage#getCloudSpecification()
 * @model
 * @generated
 */
public interface CloudSpecification extends Specification {
	/**
	 * Returns the value of the '<em><b>Descriptor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Descriptor</em>' containment reference.
	 * @see #setDescriptor(CloudEnvironmentDescriptor)
	 * @see org.scaledl.overview.specification.SpecificationPackage#getCloudSpecification_Descriptor()
	 * @model containment="true" required="true"
	 * @generated
	 */
	CloudEnvironmentDescriptor getDescriptor();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.specification.CloudSpecification#getDescriptor <em>Descriptor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Descriptor</em>' containment reference.
	 * @see #getDescriptor()
	 * @generated
	 */
	void setDescriptor(CloudEnvironmentDescriptor value);

	/**
	 * Returns the value of the '<em><b>Infrastructure Service Descriptors</b></em>' containment reference list.
	 * The list contents are of type {@link org.scaledl.overview.specification.InfrastructureServiceDescriptor}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Infrastructure Service Descriptors</em>' containment reference list.
	 * @see org.scaledl.overview.specification.SpecificationPackage#getCloudSpecification_InfrastructureServiceDescriptors()
	 * @model containment="true"
	 * @generated
	 */
	EList<InfrastructureServiceDescriptor> getInfrastructureServiceDescriptors();

	/**
	 * Returns the value of the '<em><b>Platform Service Descriptors</b></em>' containment reference list.
	 * The list contents are of type {@link org.scaledl.overview.specification.ProvidedServiceDescriptor}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Platform Service Descriptors</em>' containment reference list.
	 * @see org.scaledl.overview.specification.SpecificationPackage#getCloudSpecification_PlatformServiceDescriptors()
	 * @model containment="true"
	 * @generated
	 */
	EList<ProvidedServiceDescriptor> getPlatformServiceDescriptors();

	/**
	 * Returns the value of the '<em><b>Software Service Descriptors</b></em>' containment reference list.
	 * The list contents are of type {@link org.scaledl.overview.specification.ProvidedServiceDescriptor}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Software Service Descriptors</em>' containment reference list.
	 * @see org.scaledl.overview.specification.SpecificationPackage#getCloudSpecification_SoftwareServiceDescriptors()
	 * @model containment="true"
	 * @generated
	 */
	EList<ProvidedServiceDescriptor> getSoftwareServiceDescriptors();

} // CloudSpecification
