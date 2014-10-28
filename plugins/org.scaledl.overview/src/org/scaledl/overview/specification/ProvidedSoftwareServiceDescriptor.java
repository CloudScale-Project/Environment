/**
 */
package org.scaledl.overview.specification;

import org.eclipse.emf.common.util.EList;
import org.scaledl.overview.application.Operation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provided Software Service Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.specification.ProvidedSoftwareServiceDescriptor#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.specification.SpecificationPackage#getProvidedSoftwareServiceDescriptor()
 * @model
 * @generated
 */
public interface ProvidedSoftwareServiceDescriptor extends SoftwareServiceDescriptor, ProvidedServiceDescriptor {
	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
	 * The list contents are of type {@link org.scaledl.overview.application.Operation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' containment reference list.
	 * @see org.scaledl.overview.specification.SpecificationPackage#getProvidedSoftwareServiceDescriptor_Operations()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Operation> getOperations();

} // ProvidedSoftwareServiceDescriptor
