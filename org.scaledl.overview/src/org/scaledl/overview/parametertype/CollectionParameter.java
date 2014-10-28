/**
 */
package org.scaledl.overview.parametertype;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collection Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.scaledl.overview.parametertype.CollectionParameter#getParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.overview.parametertype.ParametertypePackage#getCollectionParameter()
 * @model
 * @generated
 */
public interface CollectionParameter extends Parameter {
	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' reference.
	 * @see #setParameter(Parameter)
	 * @see org.scaledl.overview.parametertype.ParametertypePackage#getCollectionParameter_Parameter()
	 * @model required="true"
	 * @generated
	 */
	Parameter getParameter();

	/**
	 * Sets the value of the '{@link org.scaledl.overview.parametertype.CollectionParameter#getParameter <em>Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' reference.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(Parameter value);

} // CollectionParameter
