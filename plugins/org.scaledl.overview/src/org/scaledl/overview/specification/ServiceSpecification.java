/**
 */
package org.scaledl.overview.specification;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The SoftwareDefinition model specifies the deployable 3rd party software that can be used to specify node components (see Node) or used operating systems on the instances (see Instance).
 * This model is not meant to be included into CSM model, but it provides ability for specifying different software bundles, which descriptors can be assigned to CSM model entities.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.ServiceSpecification#getServiceDescriptors <em>Service Descriptors</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.specification.SpecificationPackage#getServiceSpecification()
 * @model
 * @generated
 */
public interface ServiceSpecification extends Specification {
	/**
	 * Returns the value of the '<em><b>Service Descriptors</b></em>' containment reference list.
	 * The list contents are of type {@link org.scaledl.overview.specification.ServiceDescriptor}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Service Descriptors</em>' containment reference list.
	 * @see org.scaledl.overview.specification.SpecificationPackage#getServiceSpecification_ServiceDescriptors()
	 * @model containment="true"
	 * @generated
	 */
	EList<ServiceDescriptor> getServiceDescriptors();

} // ServiceSpecification
