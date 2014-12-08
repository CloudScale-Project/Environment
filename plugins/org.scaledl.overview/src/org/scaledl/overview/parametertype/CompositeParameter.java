/**
 */
package org.scaledl.overview.parametertype;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.parametertype.CompositeParameter#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.scaledl.overview.parametertype.CompositeParameter#getExtends <em>Extends</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.parametertype.ParametertypePackage#getCompositeParameter()
 * @model
 * @generated
 */
public interface CompositeParameter extends Parameter {
	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' reference list.
	 * The list contents are of type {@link org.scaledl.overview.parametertype.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' reference list.
	 * @see org.scaledl.overview.parametertype.ParametertypePackage#getCompositeParameter_Parameters()
	 * @model
	 * @generated
	 */
	EList<Parameter> getParameters();

	/**
	 * Returns the value of the '<em><b>Extends</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extends</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extends</em>' reference.
	 * @see #setExtends(Parameter)
	 * @see org.scaledl.overview.parametertype.ParametertypePackage#getCompositeParameter_Extends()
	 * @model
	 * @generated
	 */
	Parameter getExtends();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.parametertype.CompositeParameter#getExtends <em>Extends</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extends</em>' reference.
	 * @see #getExtends()
	 * @generated
	 */
	void setExtends(Parameter value);

} // CompositeParameter
