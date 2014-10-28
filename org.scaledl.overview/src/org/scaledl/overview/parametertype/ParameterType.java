/**
 */
package org.scaledl.overview.parametertype;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.parametertype.ParameterType#getTypes <em>Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.parametertype.ParametertypePackage#getParameterType()
 * @model
 * @generated
 */
public interface ParameterType extends EObject {
	/**
	 * Returns the value of the '<em><b>Types</b></em>' containment reference list.
	 * The list contents are of type {@link org.scaledl.overview.parametertype.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Types</em>' containment reference list.
	 * @see org.scaledl.overview.parametertype.ParametertypePackage#getParameterType_Types()
	 * @model containment="true"
	 * @generated
	 */
	EList<Parameter> getTypes();

} // ParameterType
