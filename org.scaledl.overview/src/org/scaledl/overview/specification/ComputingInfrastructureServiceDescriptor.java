/**
 */
package org.scaledl.overview.specification;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Computing Infrastructure Service Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The ServiceDescriptor specify properties of PaaS services. 
 * In the current version only entity properties are available; name and description.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor#getComputingResourceDescriptors <em>Computing Resource Descriptors</em>}</li>
 *   <li>{@link org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor#isGeneralPurpose <em>General Purpose</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.specification.SpecificationPackage#getComputingInfrastructureServiceDescriptor()
 * @model
 * @generated
 */
public interface ComputingInfrastructureServiceDescriptor extends InfrastructureServiceDescriptor {
	/**
	 * Returns the value of the '<em><b>Computing Resource Descriptors</b></em>' containment reference list.
	 * The list contents are of type {@link org.scaledl.overview.specification.ComputingResourceDescriptor}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Computing Resource Descriptors</em>' containment reference list.
	 * @see org.scaledl.overview.specification.SpecificationPackage#getComputingInfrastructureServiceDescriptor_ComputingResourceDescriptors()
	 * @model containment="true"
	 * @generated
	 */
	EList<ComputingResourceDescriptor> getComputingResourceDescriptors();

	/**
	 * Returns the value of the '<em><b>General Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>General Purpose</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>General Purpose</em>' attribute.
	 * @see #setGeneralPurpose(boolean)
	 * @see org.scaledl.overview.specification.SpecificationPackage#getComputingInfrastructureServiceDescriptor_GeneralPurpose()
	 * @model
	 * @generated
	 */
	boolean isGeneralPurpose();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor#isGeneralPurpose <em>General Purpose</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>General Purpose</em>' attribute.
	 * @see #isGeneralPurpose()
	 * @generated
	 */
	void setGeneralPurpose(boolean value);

} // ComputingInfrastructureServiceDescriptor
